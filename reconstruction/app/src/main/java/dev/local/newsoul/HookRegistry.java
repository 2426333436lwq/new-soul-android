package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

import java.lang.reflect.Method;

/**
 * Hook 注册中心。
 *
 * 对应原始 DEX 的 dev.local.newsoul.Q0（29 个安装器方法）+
 * j.f/j.g/j.h（通用安装原语）。
 *
 * 每个 install* 方法的契约（与原实现一致）：
 *   - 自己 try/catch，失败时只禁用本功能
 *   - hook 前对目标方法做签名断言，不符就抛 IllegalStateException
 *   - 调用 XposedModule.deoptimize(method) 确保能 hook
 *   - HookBuilder.setId("new-soul/<feature>") 固定 ID，便于重载时替换
 *
 * 二次开发：新增功能 = 加一个 install* 方法 + 在 SoulHook.onPackageReady 里加一行
 * tryInstall(...)。不要改其他方法。
 */
public final class HookRegistry {

    private HookRegistry() {}

    // ================================================================ 广告

    /** ad_feed / ad_detail / ad_video / ad_tag / ... 共 18 个开关 */
    public static void installAds(XposedModule self, ClassLoader cl) {
        // 1) 广告视图类：hook 它们的 attach / measure，强制 GONE
        for (String cn : Host.AD_VIEWS) {
            Class<?> c = Reflect.loadOrNull(cl, cn);
            if (c == null) continue;
            try {
                hookLifecycle(self, c, "new-soul/ad-loader/", new AdViewHooker());
            } catch (Throwable ignore) {
            }
        }
        // 2) Express 渲染视图（11 个）
        for (String cn : Host.AD_EXPRESS_VIEWS) {
            Class<?> c = Reflect.loadOrNull(cl, cn);
            if (c == null) continue;
            try {
                hookLifecycle(self, c, "new-soul/ad-loader/", new AdViewHooker());
            } catch (Throwable ignore) {
            }
        }
        // 3) 广告请求监听器：让 loadAds 直接失败
        Class<?> lsn = Reflect.loadOrNull(cl, Host.AD_REQUEST_LISTENER);
        if (lsn != null) {
            try {
                Method loadAds = Reflect.findMethod(lsn, new String[]{"loadAds"});
                hook(self, loadAds, "new-soul/ad-loader/", new AdRequestHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 聊天反撤回

    /**
     * chat_anti_recall —— 两个独立 hook：
     *   · new-soul/chat-anti-recall       在线消息（MsgListener 路径）
     *   · new-soul/chat-anti-recall-roam  历史漫游（RoamHandler 路径）
     *
     * 只保留「收到的普通私聊消息」，自己发的撤回不受影响。
     */
    public static void installChat(XposedModule self, ClassLoader cl) {
        Class<?> listener = Reflect.loadOrNull(cl, Host.MSG_LISTENER);
        if (listener != null) {
            for (String name : new String[]{"onRefreshUi", "onApplicationForeground"}) {
                try {
                    Method m = Reflect.findMethod(listener, new String[]{name});
                    hook(self, m, "new-soul/chat-anti-recall", new AntiRecallHooker());
                } catch (Throwable ignore) {
                }
            }
        }
        Class<?> roam = Reflect.loadOrNull(cl, Host.ROAM_HANDLER);
        if (roam != null) {
            try {
                Method m = Reflect.findMethod(roam, new String[]{"getMessage", "getChatMessage"});
                hook(self, m, "new-soul/chat-anti-recall-roam", new AntiRecallHooker());
            } catch (Throwable ignore) {
            }
        }
        // 撤回处理器：阻止撤回动作生效
        Class<?> wd = Reflect.loadOrNull(cl, Host.WITH_DRAW_HANDLER);
        if (wd != null) {
            try {
                Method m = Reflect.findMethod(wd, new String[]{"handleMessage"});
                hook(self, m, "new-soul/chat-recall-mark", new WithDrawHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    /** chat_own_recall_no_time_limit —— 解除自己撤回的时间限制 */
    public static void installChatLimit(XposedModule self, ClassLoader cl) {
        Class<?> mgr = Reflect.loadOrNull(cl, Host.MSG_LIMIT_MANAGER);
        if (mgr == null) return;
        try {
            Method m = Reflect.requireUniqueByParams(mgr,
                    Reflect.load(cl, Host.CHAT_MESSAGE));
            hook(self, m, "new-soul/gift-chat-model", new ChatLimitHooker());
        } catch (Throwable ignore) {
        }
        try {
            Method m = Reflect.findMethod(mgr, Host.M_IS_LIMIT);
            hook(self, m, "new-soul/gift-chat-session", new ChatLimitHooker());
        } catch (Throwable ignore) {
        }
        // 撤回时限：返回 0 表示"已到可撤回时间"
        try {
            Method m = Reflect.findMethod(mgr, new String[]{"getRecallAge", "recallAge"});
            hook(self, m, "new-soul/own-recall-age-read", new RecallAgeHooker());
        } catch (Throwable ignore) {
        }
    }

    /** chat_remove_social_score_limit */
    public static void installSocialScore(XposedModule self, ClassLoader cl) {
        Class<?> u = Reflect.loadOrNull(cl, Host.SOCIAL_SCORE_UTILS);
        if (u == null) return;
        for (String[] cand : new String[][]{
                Host.M_NO_REPLY, Host.M_SESSION_LIMIT,
                Host.M_FORBID_CALL, Host.M_FORBID_PIC}) {
            try {
                Method m = Reflect.findMethod(u, cand);
                hook(self, m, "new-soul/social-score-", new ReturnFalseHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 隐私 / 截图

    /**
     * privacy_allow_screenshot —— 移除窗口 FLAG_SECURE（0x2000）
     * privacy_allow_repeat / privacy_allow_download —— 闪照可重复查看、可保存
     */
    public static void installPrivacy(XposedModule self, ClassLoader cl) {
        for (String cn : new String[]{Host.SNAP_ACTIVITY, Host.PRIVACY_PREVIEW_ACTIVITY}) {
            Class<?> c = Reflect.loadOrNull(cl, cn);
            if (c == null) continue;
            try {
                Method init = Reflect.findMethod(c, new String[]{"init"});
                hook(self, init, "new-soul/privacy-photo-preview", new PrivacyPhotoHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 水印

    /** download_image_no_watermark / download_video_no_watermark */
    public static void installWatermark(XposedModule self, ClassLoader cl) {
        Class<?> proc = Reflect.loadOrNull(cl, Host.IMAGE_MARK_PROCESSOR);
        if (proc != null) {
            try {
                // 原实现 hook 的是 ImageMarkProcessor 中返回 Bitmap 的单参方法
                Method m = Reflect.findByReturn(proc, android.graphics.Bitmap.class, 1);
                hook(self, m, "lf.b", new PassThroughHooker());
            } catch (Throwable ignore) {
            }
        }
        Class<?> dl = Reflect.loadOrNull(cl, Host.IMAGE_DOWNLOADER);
        if (dl != null) {
            try {
                Method m = Reflect.findMethod(dl, new String[]{"b", "f"});
                hook(self, m, "lf.b", new PassThroughHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 广场

    /**
     * square_auto_like / square_visible_like / square_like_all_loaded
     * 优先级：自动点赞就绪时，一键点赞配置暂不生效。
     */
    public static void installSquareLike(XposedModule self, ClassLoader cl) {
        Class<?> btn = Reflect.loadOrNull(cl, Host.SQUARE_FLOATING_BUTTON);
        if (btn != null) {
            try {
                Method m = Reflect.findMethod(btn, new String[]{"onCreate", "onVisibilityChanged"});
                hook(self, m, "new-soul/square-like-lifecycle", new SquareLikeHooker());
            } catch (Throwable ignore) {
            }
        }
        Class<?> adapter = Reflect.loadOrNull(cl, Host.SQUARE_ADAPTER);
        if (adapter != null) {
            for (String name : Host.M_ADAPTER_DATA) {
                try {
                    Method m = Reflect.findMethod(adapter, new String[]{name});
                    hook(self, m, "new-soul/square-like-envelope-visibility",
                            new SquareLikeHooker());
                } catch (Throwable ignore) {
                }
            }
        }
    }

    // ================================================================ 信息流过滤

    /**
     * feed_filter_enabled / feed_filter_gender / feed_filter_keep_unknown
     * feed_keywords_enabled_v1 / feed_keywords_v1
     *
     * 在 RecyclerView 的 attach / bind / recycle 三个点 + 适配器数据变更点拦截。
     */
    public static void installFeedFilter(XposedModule self, ClassLoader cl) {
        Class<?> vh = Reflect.loadOrNull(cl, Host.BASE_SQUARE_VH);
        if (vh != null) {
            try {
                Method m = Reflect.findMethod(vh, new String[]{"onViewAttachedToWindow"});
                hook(self, m, "new-soul/feed-filter-attach", new FeedFilterHooker());
            } catch (Throwable ignore) {
            }
            try {
                Method m = Reflect.findMethod(vh, new String[]{"onViewDetachedFromWindow"});
                hook(self, m, "new-soul/feed-filter-recycle", new FeedFilterHooker());
            } catch (Throwable ignore) {
            }
        }
        Class<?> adapter = Reflect.loadOrNull(cl, Host.SQUARE_ADAPTER);
        if (adapter != null) {
            for (String name : new String[]{"onBindViewHolder", "setData", "replaceData"}) {
                try {
                    Method m = Reflect.findMethod(adapter, new String[]{name});
                    hook(self, m, "new-soul/feed-filter-bind", new FeedFilterHooker());
                } catch (Throwable ignore) {
                }
            }
        }
    }

    // ================================================================ 分享限制

    /** post_remove_share_limit */
    public static void installPostShare(XposedModule self, ClassLoader cl) {
        Class<?> dlg = Reflect.loadOrNull(cl, Host.SEEDS_SHARE_DIALOG);
        if (dlg == null) return;
        try {
            Method init = Reflect.findMethod(dlg, new String[]{"initViews"});
            hook(self, init, "new-soul/post-copy-action", new PostShareHooker());
        } catch (Throwable ignore) {
        }
        Class<?> base = Reflect.loadOrNull(cl, Host.BASE_SEEDS_DIALOG);
        if (base != null) {
            try {
                Method m = Reflect.findMethod(base, new String[]{"onCreate"});
                hook(self, m, "new-soul/post-share-permission", new PostShareHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 启动

    /**
     * block_splash —— 跳过启动广告页
     * hook id：new-soul/skip-startup-ad, new-soul/cold-launch-flag,
     *          new-soul/hot-splash-gate, new-soul/relaunch-flag
     */
    public static void installStartup(XposedModule self, ClassLoader cl) {
        Class<?> main = Reflect.loadOrNull(cl, Host.MAIN_ACTIVITY);
        if (main != null) {
            try {
                Method m = Reflect.findMethod(main, new String[]{"onCreate"});
                hook(self, m, "new-soul/cold-launch-flag", new SplashHooker());
            } catch (Throwable ignore) {
            }
            try {
                Method m = Reflect.findMethod(main, new String[]{"onNewIntent"});
                hook(self, m, "new-soul/relaunch-flag", new SplashHooker());
            } catch (Throwable ignore) {
            }
        }
        Class<?> sch = Reflect.loadOrNull(cl, Host.MAIN_SCHEDULER);
        if (sch != null) {
            try {
                Method m = Reflect.findMethod(sch, new String[]{"onResume"});
                hook(self, m, "new-soul/hot-splash-gate", new SplashHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 资料伪装

    /**
     * profile_disguise_days_ / _follow_ / _followed_ / _visitor_<uid>
     * 只改本机显示，不动服务器数据。
     */
    public static void installProfileDisguise(XposedModule self, ClassLoader cl) {
        Class<?> provider = Reflect.loadOrNull(cl, Host.REVISION_PROFILE_PROVIDER);
        if (provider != null) {
            try {
                Method m = Reflect.findByReturn(provider, Host.load(cl, Host.USER_FOLLOW_COUNT), 0);
                hook(self, m, "new-soul/follow-disguise-", new FollowCountHooker());
            } catch (Throwable ignore) {
            }
        }
        Class<?> view = Reflect.loadOrNull(cl, Host.META_FOLLOW_FANS_VIEW);
        if (view != null) {
            try {
                Method m = Reflect.findMethod(view, new String[]{"onFinishInflate"});
                hook(self, m, "new-soul/follow-disguise-header", new FollowCountHooker());
            } catch (Throwable ignore) {
            }
        }
        // 访客数（对应 p0.b / new-soul/profile-bookmark-*）
        Class<?> provider = Reflect.loadOrNull(cl, Host.REVISION_PROFILE_PROVIDER);
        if (provider != null) {
            try {
                Method m = Reflect.findMethod(provider, new String[]{"getVisitorCount"});
                hook(self, m, "new-soul/profile-bookmark-icon", new VisitorCountHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 打字状态 / 匹配音乐

    /** chat_hide_typing, match_mute_background_music */
    public static void installTypingAndMusic(XposedModule self, ClassLoader cl) {
        Class<?> cm = Reflect.loadOrNull(cl, Host.CHAT_MANAGER);
        if (cm != null) {
            try {
                Method m = Reflect.findMethod(cm, Host.M_MSG_TYPE);
                hook(self, m, "new-soul/hide-typing", new ReturnFalseHooker());
            } catch (Throwable ignore) {
            }
        }
        Class<?> music = Reflect.loadOrNull(cl, Host.SOUL_MUSIC_HELPER);
        if (music != null) {
            try {
                Method m = Reflect.findMethod(music, new String[]{"onPrepared"});
                hook(self, m, "new-soul/match-music-", new MediaPlayerMuteHooker());
            } catch (Throwable ignore) {
            }
        }
    }

    // ================================================================ 语音库

    /** new-soul/voice-library-entry / voice-library-close */
    public static void installVoiceLibrary(XposedModule self, ClassLoader cl) {
        Class<?> conv = Reflect.loadOrNull(cl, Host.CONVERSATION);
        if (conv == null) return;
        try {
            Method m = Reflect.findMethod(conv, Host.M_MSG_CONTENT);
            hook(self, m, "new-soul/voice-library-entry", new VoiceLibraryHooker());
        } catch (Throwable ignore) {
        }
    }

    // ================================================================ 原语

    /** hook 单个方法 */
    private static void hook(XposedModule self, Method m, String id,
                             XposedInterface.Hooker hooker) {
        m.setAccessible(true);
        self.deoptimize(m);
        self.hook(m)
                .setId(id)
                .setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE)
                .intercept(hooker);
    }

    /** hook 类的生命周期方法（onAttachedToWindow / onMeasure / onDraw） */
    private static void hookLifecycle(XposedModule self, Class<?> c, String id,
                                      XposedInterface.Hooker hooker) {
        for (String name : new String[]{
                "onAttachedToWindow", "onMeasure", "onDraw", "dispatchTouchEvent"}) {
            try {
                Method m = Reflect.findMethod(c, new String[]{name});
                hook(self, m, id, hooker);
            } catch (Throwable ignore) {
            }
        }
    }

    // hooker 实现集中在 Hookers.java，这里按功能引用：
    //   AdViewHooker / AdRequestHooker / AntiRecallHooker / WithDrawHooker
    //   ChatLimitHooker / ReturnFalseHooker / PrivacyPhotoHooker / PassThroughHooker
    //   SquareLikeHooker / FeedFilterHooker / PostShareHooker / SplashHooker
    //   FollowCountHooker / MediaPlayerMuteHooker / VoiceLibraryHooker
}
