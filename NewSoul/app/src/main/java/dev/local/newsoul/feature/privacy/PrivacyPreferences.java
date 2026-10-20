package dev.local.newsoul.feature.privacy;

import android.app.Activity;
import android.content.Intent;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.Prefs;

/**
 * 隐私功能的开关判定辅助（原 {@code Q0.R}）。
 *
 * <p>判定语义与原实现逐字一致：仅当当前界面不是发送方（Intent extra {@code isSender} 为
 * {@code false}）且开关 {@link SettingsKeys#PRIVACY_ALLOW_REPEAT} 开启时才允许「重复查看 / 保存」；
 * 其余情况一律返回 {@code false}（默认关闭）。</p>
 */
public final class PrivacyPreferences {

    private static final String EXTRA_IS_SENDER = "isSender";

    private PrivacyPreferences() {
    }

    /** 当前界面是否允许重复消费；见类注释。 */
    public static boolean allowRepeat(Activity activity) {
        Intent intent = activity.getIntent();
        boolean isSender = intent.getBooleanExtra(EXTRA_IS_SENDER, false);
        if (!isSender) {
            return Prefs.getBoolean(activity, SettingsKeys.PRIVACY_ALLOW_REPEAT, false);
        }
        return false;
    }
}
