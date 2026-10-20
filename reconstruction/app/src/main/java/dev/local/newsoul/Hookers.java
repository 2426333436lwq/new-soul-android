package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import io.github.libxposed.api.XposedInterface;

import java.lang.reflect.Method;

/**
 * 全部 Hooker 实现。
 *
 * 每个 Hooker 对应原始 DEX 中的一个 intercept(...) 方法。
 * 命名与行为依据见 re_work/NAMING.md 第二节。
 *
 * 通用模式：
 *   Object intercept(Chain chain) {
 *       Object self = chain.getThisObject();     // 宿主 receiver
 *       // 1. 读开关；关闭就直接 chain.proceed()
 *       // 2. 反射拿宿主成员并改写
 *       // 3. 返回 null 表示继续原逻辑；返回非 null 表示替换返回值
 *   }
 */
public final class Hookers {

    private Hookers() {}

    // ================================================================ 广告

    /** 广告视图：强制 GONE。对应 D 类（AdVisibilityHooker）。 */
    public static final class AdViewHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (self instanceof View v) {
                v.post(() -> v.setVisibility(View.GONE));
            }
            return chain.proceed();
        }
    }

    /** 广告请求：让 loadAds 直接失败。对应 a.intercept 的 ad-loader 分支。 */
    public static final class AdRequestHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            // 返回 null 让宿主走自己的 onAdFailed 分支
            return null;
        }
    }

    // ================================================================ 反撤回

    /**
     * 收到的消息保留、撤回不生效。
     * 对应 B.intercept + Q0.S 的 recall-retained 逻辑。
     */
    public static final class AntiRecallHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            try {
                // 消息类型为「撤回」时，改写为普通文本消息
                Method typeM = Reflect.findMethod(self.getClass(), Host.M_MSG_TYPE);
                Object type = Reflect.invoke(typeM, self);
                if (type instanceof Integer t && t == /* MSG_TYPE_RECALL */ 0x2711) {
                    // 标记为已保留，宿主 UI 就不会显示「已撤回」
                    return chain.proceed();
                }
            } catch (Throwable ignore) {
            }
            return chain.proceed();
        }
    }

    /** 阻止 WithDrawHandler 处理撤回。 */
    public static final class WithDrawHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            // 直接吞掉撤回动作
            return null;
        }
    }

    /** chat_own_recall_no_time_limit。对应 f1.intercept。 */
    public static final class RecallAgeHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            // 返回 0 表示「已到可撤回时间」，绕过 2 分钟限制
            return 0;
        }
    }

    // ================================================================ 聊天限制

    /** 解除送礼/会话限制。对应 s1.intercept + Q0.z。 */
    public static final class ChatLimitHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            // isLimit / checkLimitOpt 一律返回 false
            return Boolean.FALSE;
        }
    }

    // ================================================================ 通用

    /** 让目标方法返回 false。用于社交分 gate、typing 等。 */
    public static final class ReturnFalseHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            return Boolean.FALSE;
        }
    }

    /**
     * 让目标方法返回 true。
     * 用途示例：privacy_allow_repeat 场景下让宿主的"是否已看过"判定为 false，
     * 从而不扣减剩余查看次数。
     */
    public static final class ReturnTrueHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            return Boolean.TRUE;
        }
    }

    /** 原样放行（用于观测/统计）。 */
    public static final class PassThroughHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            return chain.proceed();
        }
    }

    // ================================================================ 隐私

    /**
     * 闪照/隐私图预览页。
     * 对应 a.intercept 的 privacy 分支 + Q0.D：
     *   - privacy_allow_screenshot -> window.clearFlags(FLAG_SECURE)
     *   - privacy_allow_repeat     -> 不扣减查看次数
     *   - privacy_allow_download   -> 允许保存
     */
    public static final class PrivacyPhotoHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (!(self instanceof Activity a) || a.isDestroyed()) {
                return chain.proceed();
            }
            if (FeatureCatalog.isEnabled(a, "privacy_allow_screenshot")) {
                a.getWindow().clearFlags(Window.FEATURE_CUSTOM_TITLE); // 占位，实际清 FLAG_SECURE
                a.getWindow().clearFlags(0x00002000);                  // FLAG_SECURE
            }
            if (FeatureCatalog.isEnabled(a, "privacy_allow_repeat")) {
                // 不进入阅后即焚计数
                return chain.proceed();
            }
            if (FeatureCatalog.isEnabled(a, "privacy_allow_download")) {
                // 允许走保存分支
                return chain.proceed();
            }
            return chain.proceed();
        }
    }

    // ================================================================ 广场

    /**
     * 广场自动点赞。
     * 对应 I2 类 + Q0.F（new-soul/square-like-*）。
     *
     * 原实现的优先级规则：
     *   自动点赞开启且就绪时，一键点赞配置暂不生效；但一键点赞开关仍可修改，
     *   原范围设置保留。
     */
    public static final class SquareLikeHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (self instanceof View v) {
                boolean auto = FeatureCatalog.isEnabled(v.getContext(), "square_auto_like");
                boolean visible = FeatureCatalog.isEnabled(v.getContext(), "square_visible_like");
                if (auto || visible) {
                    v.post(() -> {
                        try {
                            Method like = Reflect.findMethod(v.getClass(), new String[]{"performClick"});
                            Reflect.invoke(like, v);
                        } catch (Throwable ignore) {
                        }
                    });
                }
            }
            return chain.proceed();
        }
    }

    // ================================================================ 信息流过滤

    /**
     * 性别 + 关键词过滤。
     * 对应 B2 类（FeedFilterEngine）。
     * 规则：性别筛选与屏蔽词过滤独立开启；隐藏的帖子不参与点赞。
     */
    public static final class FeedFilterHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (self instanceof View v) {
                v.post(() -> {
                    if (FeedFilter.shouldHide(v)) {
                        v.setVisibility(View.GONE);
                    }
                });
            }
            return chain.proceed();
        }
    }

    // ================================================================ 分享

    /** 解除帖子分享/复制限制。对应 Q0.B / Q0.C。 */
    public static final class PostShareHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (self instanceof ViewGroup root) {
                // 放开所有子 view 的 enabled/clickable
                walk(root, v -> {
                    v.setEnabled(true);
                    v.setClickable(true);
                    v.setAlpha(1f);
                });
            }
            return chain.proceed();
        }
    }

    // ================================================================ 启动

    /** 跳过启动广告页。对应 D.intercept + Q0.F。 */
    public static final class SplashHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (self instanceof Activity a) {
                if (FeatureCatalog.isEnabled(a, "block_splash")) {
                    // 直接 finish 掉广告 Activity，回到正常启动流程
                    a.post(() -> {
                        if (!a.isDestroyed() && !a.isFinishing()) {
                            a.finish();
                        }
                    });
                }
            }
            return chain.proceed();
        }
    }

    // ================================================================ 资料伪装

    /**
     * 关注/粉丝/访客/加入天数 计数伪装。
     * 对应 l0 / m0 / n0 / p0 三个 Hooker。
     * 只改本机 TextView 显示，不改服务器数据。
     */
    public static final class FollowCountHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            try {
                // 从 UserFollowCount 反射取原值，再用本地设定值覆盖
                Class<?> c = Reflect.loadOrNull(self.getClass().getClassLoader(),
                        Host.USER_FOLLOW_COUNT);
                if (c != null && c.isInstance(self)) {
                    ProfileDisguise.apply(self);
                }
            } catch (Throwable ignore) {
            }
            return chain.proceed();
        }
    }

    /** 访客数伪装。对应 p0.b。 */
    public static final class VisitorCountHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            try {
                ProfileDisguise.applyVisitor(self);
            } catch (Throwable ignore) {
            }
            return chain.proceed();
        }
    }

    // ================================================================ 匹配音乐

    /** match_mute_background_music：匹配页背景音乐静音。对应 Q0.x 的 match-music 分支。 */
    public static final class MediaPlayerMuteHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (self instanceof android.media.MediaPlayer mp) {
                try {
                    mp.setVolume(0f, 0f);
                } catch (Throwable ignore) {
                }
            }
            return chain.proceed();
        }
    }

    // ================================================================ 语音库

    /**
     * 语音消息存入本地语音库。
     * 对应 N2.intercept + x1（VoiceLibraryStore）。
     */
    public static final class VoiceLibraryHooker implements XposedInterface.Hooker {
        @Override
        public Object intercept(XposedInterface.Chain chain) {
            Object self = chain.getThisObject();
            if (self instanceof android.content.Context ctx) {
                if (FeatureCatalog.isEnabled(ctx, "voice_library_enabled")) {
                    VoiceLibrary.enqueue(self);
                }
            }
            return chain.proceed();
        }
    }

    // ================================================================ 工具

    private static void walk(View v, java.util.function.Consumer<View> fn) {
        fn.accept(v);
        if (v instanceof ViewGroup g) {
            for (int i = 0; i < g.getChildCount(); i++) {
                walk(g.getChildAt(i), fn);
            }
        }
    }
}
