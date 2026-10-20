package dev.local.newsoul.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.local.newsoul.feature.ad.AdBlockFeature;
import dev.local.newsoul.feature.chat.forward.MessageForwardFeature;
import dev.local.newsoul.feature.chat.game.GameResultFeature;
import dev.local.newsoul.feature.chat.gift.GiftChatLimitFeature;
import dev.local.newsoul.feature.chat.recall.AntiRecallFeature;
import dev.local.newsoul.feature.chat.recall.OwnRecallLimitFeature;
import dev.local.newsoul.feature.chat.recall.RecallBubbleFeature;
import dev.local.newsoul.feature.chat.repeat.RepeatFeature;
import dev.local.newsoul.feature.chat.socialscore.SocialScoreLimitFeature;
import dev.local.newsoul.feature.chat.typing.HideTypingFeature;
import dev.local.newsoul.feature.chat.voice.VoiceLibraryFeature;
import dev.local.newsoul.feature.download.NoWatermarkFeature;
import dev.local.newsoul.feature.feed.FeedFilterFeature;
import dev.local.newsoul.feature.post.PostCopyFeature;
import dev.local.newsoul.feature.post.PostSharePermissionFeature;
import dev.local.newsoul.feature.privacy.PrivacyProtectFeature;
import dev.local.newsoul.feature.profile.ProfileBookmarkFeature;
import dev.local.newsoul.feature.profile.disguise.ProfileDisguiseFeature;
import dev.local.newsoul.feature.settings.SettingsEntryFeature;
import dev.local.newsoul.feature.soulmatch.MatchMusicFeature;
import dev.local.newsoul.feature.square.SquareLikeFeature;
import dev.local.newsoul.feature.startup.SplashSkipFeature;
import dev.local.newsoul.feature.startup.StartupToastFeature;
import dev.local.newsoul.framework.Guard;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 功能域注册表。
 *
 * <p>注册顺序与原模块 {@code SoulHook.onPackageReady} 中的安装器调用顺序保持一致 ——
 * 该顺序是有意义的：例如反撤回需要先于聊天限制安装，启动相关 hook 需要在主页 hook 之前就位。</p>
 *
 * <p>新增功能域时，只需实现 {@link FeatureInstaller} 并在此注册；新增宿主版本适配时，
 * 在 {@link dev.local.newsoul.host.HostAdapters} 中注册对应的适配器即可。</p>
 */
public final class Features {

    private static final List<FeatureInstaller> INSTALLERS = new ArrayList<>();

    static {
        // 注册顺序与原模块 onPackageReady 的安装顺序一致，注释标出原安装器所处位置。
        // 尚未落地的功能域保留占位说明，新增时按序插入，勿随意调整相对顺序。

        // 1. 撤回标记 + 反撤回（私聊 / 漫游）+ 自己消息撤回时限
        //    顺序有意义：撤回标记需先于反撤回安装，撤回时限需在聊天限制之前就位。
        register(new RecallBubbleFeature());
        register(new AntiRecallFeature());
        register(new OwnRecallLimitFeature());

        // 2. 隐藏打字 / 匹配音乐
        register(new HideTypingFeature());
        register(new MatchMusicFeature());

        // 3. 长按菜单 / 复读
        //    注册顺序有意义：转发与语音库需先安装并登记能力，复读菜单才能据此决定是否追加
        //    对应菜单项（见 ChatBridges）。后续功能域按「转发 → 语音库 → 复读」插入本行之前。
        register(new MessageForwardFeature());
        register(new VoiceLibraryFeature());
        register(new RepeatFeature());

        // 4. 聊天限制（礼物）+ 5. 社交分
        //    原模块在语音库/转发/复读（F.h）之后依次安装 Q0.z（礼物）与 Q0.E（社交分）。
        register(new GiftChatLimitFeature());
        register(new SocialScoreLimitFeature());

        // 5.5 骰子 / 猜拳结果控制（独立小功能，不依赖其它功能域）
        register(new GameResultFeature());

        // 6. 隐私
        register(new PrivacyProtectFeature());

        // 6.5 广告屏蔽（18 个 ad_* 开关，视图 / 适配器 / 请求三层）
        //     原模块在 onPackageReady 早期即安装广告 hook（Q0.A/B/C 段之前），
        //     放在隐私之后、广场相关之前，避免广场适配器 hook 与之竞争同一方法。
        register(new AdBlockFeature());

        // 7. 无水印
        register(new NoWatermarkFeature());

        register(new PostCopyFeature());
        register(new PostSharePermissionFeature());

        // 资料页书签（插眼）
        register(new ProfileBookmarkFeature());

        // 10. 会话列表
        // 11. 资料页伪装
        register(new ProfileDisguiseFeature());
        // 12. 信息流过滤（性别 + 屏蔽词，挂钩 ViewHolder 三生命周期）
        register(new FeedFilterFeature());
        // 13. 广场点赞（可见 / 已加载 / 自动三种范围）
        register(new SquareLikeFeature());
        // 14. 广场入口
        // 15. 启动 / 主页
        //     冷启动 / 重入 / 热启动三条路径分别对应
        //     new-soul/cold-launch-flag、relaunch-flag、hot-splash-gate。
        register(new SplashSkipFeature());

        // 16. Activity.onResume
        register(new StartupToastFeature());

        // 17. 设置入口（hook 宿主私有 H5Activity.m1，在设置页标题栏注入入口按钮）
        //     必须最后注册：它依赖设置面板与 ConfigStore 就绪。
        register(new SettingsEntryFeature());
    }

    private Features() {
    }

    public static void register(FeatureInstaller installer) {
        if (installer != null) {
            INSTALLERS.add(installer);
        }
    }

    public static List<FeatureInstaller> all() {
        return Collections.unmodifiableList(INSTALLERS);
    }

    /**
     * 依次安装全部功能域；单个功能域失败只记录日志，不中断后续安装。
     *
     * <p>用 {@link Guard} 而不是裸 try/catch：宿主版本不匹配时最常见的是
     * {@code NoClassDefFoundError} / {@code ExceptionInInitializerError}，
     * 它们是 {@link Error}，只捕 {@code Exception} 会让其穿透到宿主线程，
     * 直接表现为「Soul 打不开」。{@code Guard} 连 {@link Error} 一起拦。</p>
     */
    public static void installAll(ModuleContext context) {
        for (FeatureInstaller installer : INSTALLERS) {
            String id;
            try {
                id = installer.id();
            } catch (Throwable throwable) {
                id = installer.getClass().getSimpleName();
            }
            // 功能级熔断：设置里 newsoul_feature_off_<id>=true 时跳过该功能域。
            // 用途：宿主出现白屏/崩溃时，无需卸载模块即可逐个关掉功能域做二分定位。
            if (isFeatureDisabled(context, id)) {
                Logger.i("feature disabled by config: " + id);
                continue;
            }
            Guard.guard("feature:" + id, () -> installer.install(context));
        }
    }

    /**
     * 判断某个功能域是否被配置熔断。
     *
     * <p>键名规则：{@code newsoul_feature_off_<id>}，例如
     * {@code newsoul_feature_off_splash-skip}。用 SharedPreferences 而不是系统属性，
     * 是因为改设置值可以通过配置导入完成，不需要 adb。</p>
     */
    private static boolean isFeatureDisabled(ModuleContext context, String id) {
        try {
            android.content.Context host = context.hostContext();
            if (host == null) {
                return false;
            }
            android.content.SharedPreferences prefs = host.getSharedPreferences(
                    dev.local.newsoul.config.SettingsKeys.PREFS_MAIN,
                    android.content.Context.MODE_PRIVATE);
            return prefs.getBoolean("newsoul_feature_off_" + id, false);
        } catch (Throwable throwable) {
            return false;
        }
    }
}