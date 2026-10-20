package dev.local.newsoul.config;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 主设置存储访问层。
 *
 * <p>封装对 {@link SettingsKeys#PREFS_MAIN} 的读写，并提供「缺失即取默认值」的语义 ——
 * 与原模块一致：未写入过的键由 {@link SettingDefaults} 决定，而不是 {@code false}。</p>
 */
public final class ConfigStore {

    private final Context context;

    /** SharedPreferences 实例在进程内稳定，首次访问时解析并缓存。 */
    private volatile SharedPreferences preferences;

    public ConfigStore(Context context) {
        this.context = context;
    }

    /** 从上下文创建主设置存储访问器。 */
    public static ConfigStore from(Context context) {
        return new ConfigStore(context);
    }

    /** 底层 SharedPreferences（可能为 {@code null}：上下文不可用时）。 */
    public SharedPreferences raw() {
        SharedPreferences cached = preferences;
        if (cached != null) {
            return cached;
        }
        if (context == null) {
            return null;
        }
        synchronized (this) {
            if (preferences == null) {
                preferences = context.getSharedPreferences(SettingsKeys.PREFS_MAIN, Context.MODE_PRIVATE);
            }
            return preferences;
        }
    }

    public boolean getBoolean(String key) {
        SharedPreferences prefs = raw();
        Object fallback = SettingDefaults.defaultValue(key);
        boolean fallbackValue = fallback instanceof Boolean && (Boolean) fallback;
        return prefs == null ? fallbackValue : prefs.getBoolean(key, fallbackValue);
    }

    public int getInt(String key) {
        SharedPreferences prefs = raw();
        Object fallback = SettingDefaults.defaultValue(key);
        int fallbackValue = fallback instanceof Number ? ((Number) fallback).intValue() : 0;
        return prefs == null ? fallbackValue : prefs.getInt(key, fallbackValue);
    }

    public String getString(String key) {
        SharedPreferences prefs = raw();
        Object fallback = SettingDefaults.defaultValue(key);
        String fallbackValue = fallback instanceof String ? (String) fallback : "";
        return prefs == null ? fallbackValue : prefs.getString(key, fallbackValue);
    }

    /** 写入单个键；值类型必须为 {@link Boolean} / {@link Integer} / {@link String}。 */
    public void put(String key, Object value) {
        SharedPreferences prefs = raw();
        if (prefs == null) {
            return;
        }
        SharedPreferences.Editor editor = prefs.edit();
        write(editor, key, value);
        editor.apply();
    }

    /** 批量写入（导入配置时使用），一次提交。 */
    public void putAll(Map<String, Object> values) {
        SharedPreferences prefs = raw();
        if (prefs == null) {
            return;
        }
        SharedPreferences.Editor editor = prefs.edit();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            write(editor, entry.getKey(), entry.getValue());
        }
        editor.apply();
    }

    /** 导出当前存储的全部键值（用于配置导出）。 */
    public Map<String, Object> snapshot() {
        SharedPreferences prefs = raw();
        if (prefs == null) {
            return new LinkedHashMap<>();
        }
        return new LinkedHashMap<>(prefs.getAll());
    }

    private static void write(SharedPreferences.Editor editor, String key, Object value) {
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        }
    }
}