package dev.local.newsoul.feature.soulmatch;

import android.content.Context;
import android.media.MediaPlayer;

import java.lang.reflect.Method;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.GlobalContextProvider;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Prefs;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 灵魂匹配背景音乐静音（原 {@code Q0.x} 的音乐部分，hooker {@code a} case 3）。
 *
 * <p>宿主 {@code SoulMusicHelper} 负责播放匹配页背景音乐：
 * {@code k()} 触发播放、{@code onPrepared(MediaPlayer)} 在准备完成后启动播放、
 * {@code l()} 停止播放。模块在开关开启时直接拦截前两者（不调用 {@code proceed}），
 * 使背景音乐不发声；{@code l()} 只作去优化，保证停止链路不被内联跳过。</p>
 *
 * <p>开关 {@link SettingsKeys#MATCH_MUTE_BACKGROUND_MUSIC} 默认开启。</p>
 */
public final class MatchMusicFeature implements FeatureInstaller {

    private static final String UNAVAILABLE_LOG = "match music control unavailable";
    private static final String INSTALLED_LOG = "soul match music control installed";
    private static final String BLOCKED_LOG = "soul match background music blocked";

    @Override
    public String id() {
        return "soulmatch.mute-music";
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        Class<?> helper = context.loadHostClass(HostClasses.PLANET_SOUL_MUSIC_HELPER);
        Method start = Reflect.findDeclaredMethod(helper, "k");
        Method onPrepared = Reflect.findDeclaredMethod(helper, "onPrepared", MediaPlayer.class);
        Method stop = Reflect.findDeclaredMethod(helper, "l");
        GlobalContextProvider globalContext = GlobalContextProvider.resolve(context);

        if (start == null || onPrepared == null || stop == null || !globalContext.isReady()) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }

        try {
            XposedInterface.Hooker hooker = muteHooker(globalContext);
            intercept(module, start, hooker);
            intercept(module, onPrepared, hooker);
            Hooks.deoptimize(module, HookIds.MATCH_MUSIC_PREFIX + stop.getName(), stop);
        } catch (Throwable throwable) {
            Logger.e(UNAVAILABLE_LOG, throwable);
            return;
        }
        Logger.i(INSTALLED_LOG);
    }

    /** 按「原方法名」派生稳定 ID 安装 hook 并去优化。 */
    private static void intercept(XposedModule module, Method target, XposedInterface.Hooker hooker) {
        String id = HookIds.MATCH_MUSIC_PREFIX + target.getName();
        Hooks.intercept(module, id, target, hooker);
        Hooks.deoptimize(module, id, target);
    }

    /** 开关开启时拦截播放，使背景音乐静音。 */
    private static XposedInterface.Hooker muteHooker(GlobalContextProvider globalContext) {
        return chain -> {
            Context context = globalContext.get();
            if (context != null
                    && Prefs.getBoolean(context, SettingsKeys.MATCH_MUTE_BACKGROUND_MUSIC, true)) {
                Logger.i(BLOCKED_LOG);
                return null;
            }
            return chain.proceed();
        };
    }
}