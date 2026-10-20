package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 设置面板 UI。
 *
 * 对应原始 DEX 的 q2（SettingsPanel）+ w2（UiFactory）+ r2（ToggleSwitch）+
 * Z0.g（SettingsSwitchboard）+ v2（SettingsTabs）。
 *
 * 原实现的 UI 结构：
 *   - 顶部横向标签页（v2 + HorizontalScrollView）分组：
 *       广告 / 聊天 / 隐私 / 广场 / 主页 / 其他
 *   - 每个标签页一个 ScrollView + LinearLayout
 *   - 开关用自定义 ToggleSwitch（extends Switch），带长按说明
 *   - 底部：导入 / 导出配置、TG 群组、打赏、Hook 诊断
 *
 * 二次开发：加新功能 = 在 FeatureCatalog.ALL_BOOLS 里加键名，
 * 然后在 buildGroup("其他") 里加一行 addSwitch(...)。
 */
public final class SettingsUi {

    private SettingsUi() {}

    private static final String[] GROUPS = {
            "广告", "聊天", "隐私", "广场", "主页", "其他",
    };

    public static void open(Activity host) {
        Context ctx = host.getApplicationContext();
        ContextHolder.set(ctx);

        LinearLayout root = new LinearLayout(ctx);
        root.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(ctx);
        LinearLayout content = new LinearLayout(ctx);
        content.setOrientation(LinearLayout.VERTICAL);
        int pad = dp(ctx, 16);
        content.setPadding(pad, pad, pad, pad);
        scroll.addView(content);

        // ---- 版本与状态 ----
        content.addView(header(ctx));

        // ---- 分组开关 ----
        addGroup(ctx, content, "广告", FeatureCatalog.ADS);
        addGroup(ctx, content, "聊天", new String[]{
                "chat_anti_recall", "chat_own_recall_no_time_limit",
                "chat_remove_gift_gate", "chat_remove_bell_gift_gate",
                "chat_remove_social_score_limit", "chat_hide_typing",
                "chat_limit_session_type", "chat_recall_style",
                "game_dice", "game_finger", "match_mute_background_music",
        });
        addGroup(ctx, content, "隐私", new String[]{
                "privacy_allow_screenshot", "privacy_allow_repeat",
                "privacy_allow_download", "download_image_no_watermark",
                "download_video_no_watermark",
        });
        addGroup(ctx, content, "广场", FeatureCatalog.FEED);
        addGroup(ctx, content, "主页", new String[]{
                "square_visible_like", "post_remove_share_limit",
                "block_splash", "startup_toast", "page_log",
        });

        root.addView(scroll);

        new AlertDialog.Builder(host)
                .setTitle("New Soul 设置")
                .setView(root)
                .setNegativeButton("关闭", null)
                .setNeutralButton("导出配置", (d, w) -> export(ctx))
                .show();
    }

    // ------------------------------------------------------------------ 分组

    private static void addGroup(Context ctx, LinearLayout parent, String title, String[] keys) {
        TextView tv = new TextView(ctx);
        tv.setText(title);
        tv.setTextSize(16f);
        tv.setPadding(0, dp(ctx, 12), 0, dp(ctx, 4));
        parent.addView(tv);

        for (String key : keys) {
            parent.addView(new ToggleSwitch(ctx, key));
        }
    }

    // ------------------------------------------------------------------ 头部

    private static TextView header(Context ctx) {
        TextView tv = new TextView(ctx);
        tv.setText("New Soul 1.34.0 · 宿主 Soul " + Host.TARGET_SOUL_VERSION
                + "\n配置导出不含密钥与聊天记录");
        tv.setTextSize(13f);
        return tv;
    }

    // ------------------------------------------------------------------ 导入导出

    private static void export(Context ctx) {
        String json = FeatureCatalog.export(ctx);
        // 原实现写入 SharedPreferences 的 newsoul_debug，再分享
        ctx.getSharedPreferences(FeatureCatalog.KEY_DEBUG, Context.MODE_PRIVATE)
                .edit()
                .putString("config", json)
                .apply();
    }

    // ------------------------------------------------------------------

    private static int dp(Context ctx, int v) {
        return (int) (v * ctx.getResources().getDisplayMetrics().density + 0.5f);
    }
}
