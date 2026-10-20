package dev.local.newsoul.feature.chat.recall;

import android.content.Context;
import android.content.SharedPreferences;

import dev.local.newsoul.config.SettingsKeys;

/**
 * 「已保留的撤回消息」索引库。
 *
 * <p>以消息指纹为键记录已保留过的消息，避免同一条撤回通知被重复保留或重复渲染。
 * 存储名与原模块逐字一致（{@link SettingsKeys#PREFS_RECALLED}），
 * 因此新版模块可以直接读取旧版遗留的记录。</p>
 */
final class RecallStore {

    private RecallStore() {
    }

    private static SharedPreferences preferences(Context context) {
        return context.getSharedPreferences(SettingsKeys.PREFS_RECALLED, Context.MODE_PRIVATE);
    }

    /**
     * 记录指纹；已存在时直接视为成功。
     *
     * @return 记录是否可用（写入失败或上下文不可用时为 {@code false}）
     */
    static boolean remember(Context context, String fingerprint) {
        if (context == null || fingerprint == null || fingerprint.isEmpty()) {
            return false;
        }
        SharedPreferences preferences = preferences(context);
        if (preferences.contains(fingerprint)) {
            return true;
        }
        return preferences.edit().putBoolean(fingerprint, true).commit();
    }

    /** 指纹是否已被保留过。 */
    static boolean contains(Context context, String fingerprint) {
        if (context == null || fingerprint == null || fingerprint.isEmpty()) {
            return false;
        }
        return preferences(context).contains(fingerprint);
    }
}