package dev.local.newsoul.framework;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import dev.local.newsoul.config.ConfigStore;

/**
 * 按宿主 {@link Context} 访问主设置存储。
 *
 * <p>hook 回调只能拿到宿主 Context（Activity、Application 或 View 的 Context），
 * 而设置存储的读取在进程内是稳定的。为避免每次回调都重新解析 SharedPreferences，
 * 这里以 Context 为弱键缓存 {@link ConfigStore} —— 与原模块的缓存语义一致。</p>
 *
 * <p>与 {@link ConfigStore} 的分工：本类面向 hook 回调，读取时使用调用方显式给定的默认值
 * （对应原模块 {@code prefs.getBoolean(key, true)} 这类硬编码默认）；{@link ConfigStore}
 * 面向模块内部，默认值取自 {@link dev.local.newsoul.config.SettingDefaults}。</p>
 */
public final class Prefs {

    private static final Map<Context, ConfigStore> CACHE =
            Collections.synchronizedMap(new WeakHashMap<>());

    private Prefs() {
    }

    /** 取得（并缓存）该上下文对应的设置访问器。 */
    public static ConfigStore of(Context context) {
        if (context == null) {
            return new ConfigStore(null);
        }
        Context application = context.getApplicationContext();
        Context key = application != null ? application : context;
        ConfigStore store = CACHE.get(key);
        if (store == null) {
            store = ConfigStore.from(key);
            CACHE.put(key, store);
        }
        return store;
    }

    /** 读取布尔开关；上下文不可用时返回 {@code fallback}。 */
    public static boolean getBoolean(Context context, String key, boolean fallback) {
        if (context == null) {
            return fallback;
        }
        SharedPreferences preferences = of(context).raw();
        return preferences == null ? fallback : preferences.getBoolean(key, fallback);
    }

    /** 读取字符串设置；上下文不可用时返回 {@code fallback}。 */
    public static String getString(Context context, String key, String fallback) {
        if (context == null) {
            return fallback;
        }
        SharedPreferences preferences = of(context).raw();
        return preferences == null ? fallback : preferences.getString(key, fallback);
    }
}