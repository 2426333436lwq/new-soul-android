package dev.local.newsoul.ui;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.local.newsoul.config.ConfigStore;
import dev.local.newsoul.config.SettingDefaults;
import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.NightMode;

/**
 * 设置面板（原 {@code Z0.g} 的总开关板 + {@code w2} 的设置开关行）。
 *
 * <p>分组顺序沿用原模块：广告 → 隐私 → 信息流 → 广场 → 聊天 → 启动其它 → 配置。
 * 开关行直接读写 {@link ConfigStore}；各功能域在 hook 时读取同一存储，因此改完即时生效，
 * 不需要重启宿主进程。</p>
 *
 * <p>自绘文本开关而非 {@code Switch}：宿主主题差异大，文本行在深浅两套主题下都能保证
 * 对比度，也不需要额外资源。</p>
 */
public final class SettingsPanel {

    private SettingsPanel() {
    }

    /** 打开模块设置面板。 */
    public static void show(Activity host) {
        if (host == null || host.isFinishing()) {
            return;
        }
        Context context = host;
        boolean dark = NightMode.isNightMode(host);
        ConfigStore store = ConfigStore.from(context);

        ModuleDialog dialog = new ModuleDialog(host, dark);
        dialog.setHelpText("修改后立即生效；导出配置不含密钥与聊天记录。");

        // ModuleDialog.show(title, positive) 会把 content() 包进 ScrollView，
        // 因此这里只负责往 content() 里填行。
        LinearLayout content = dialog.content();
        int pad = ViewKit.dp(context, 0x04);
        content.setPadding(pad, pad, pad, pad);

        addStatus(dialog, content, store);
        addGroup(dialog, content, store, "广告屏蔽", AD_KEYS, "勾选表示屏蔽对应位置的广告。");
        addGroup(dialog, content, store, "隐私", PRIVACY_KEYS, "仅适用于收到的隐私图片和视频。");
        addGroup(dialog, content, store, "信息流过滤", FEED_KEYS,
                "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。");
        addGroup(dialog, content, store, "广场", SQUARE_KEYS,
                "自动点赞开启且就绪时，一键点赞配置暂不生效。");
        addGroup(dialog, content, store, "聊天", CHAT_KEYS,
                "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。");
        addGroup(dialog, content, store, "启动与其它", MISC_KEYS,
                "跳过启动广告页，匹配页背景音乐静音。");
        addConfigSection(dialog, content, store);

        dialog.show("New Soul 设置", "关闭");
    }

    // ------------------------------------------------------------------ 分组键表

    private static final String[] AD_KEYS = {
            SettingsKeys.AD_FEED, SettingsKeys.AD_DETAIL, SettingsKeys.AD_VIDEO,
            SettingsKeys.AD_TAG, SettingsKeys.AD_TAG_WEB, SettingsKeys.AD_SEARCH,
            SettingsKeys.AD_GROUP, SettingsKeys.AD_PLANET, SettingsKeys.AD_ME,
            SettingsKeys.AD_CONVERSATION, SettingsKeys.AD_ENVELOPE, SettingsKeys.AD_NOTICE,
            SettingsKeys.AD_CHAT_TOOLS, SettingsKeys.AD_FLOAT, SettingsKeys.AD_INTERSTITIAL,
            SettingsKeys.AD_REWARD_CHAT, SettingsKeys.AD_REWARD_DRAMA, SettingsKeys.AD_REWARD_WEB,
    };

    private static final String[] PRIVACY_KEYS = {
            SettingsKeys.PRIVACY_ALLOW_SCREENSHOT,
            SettingsKeys.PRIVACY_ALLOW_REPEAT,
            SettingsKeys.PRIVACY_ALLOW_DOWNLOAD,
            SettingsKeys.DOWNLOAD_IMAGE_NO_WATERMARK,
            SettingsKeys.DOWNLOAD_VIDEO_NO_WATERMARK,
    };

    private static final String[] FEED_KEYS = {
            SettingsKeys.FEED_FILTER_ENABLED,
            SettingsKeys.FEED_KEYWORDS_ENABLED_V1,
            SettingsKeys.FEED_FILTER_KEEP_UNKNOWN,
    };

    private static final String[] SQUARE_KEYS = {
            SettingsKeys.SQUARE_VISIBLE_LIKE,
            SettingsKeys.SQUARE_LIKE_ALL_LOADED,
            SettingsKeys.SQUARE_AUTO_LIKE,
    };

    private static final String[] CHAT_KEYS = {
            SettingsKeys.CHAT_ANTI_RECALL,
            SettingsKeys.CHAT_OWN_RECALL_NO_TIME_LIMIT,
            SettingsKeys.CHAT_HIDE_TYPING,
            SettingsKeys.CHAT_REMOVE_GIFT_GATE,
            SettingsKeys.CHAT_REMOVE_BELL_GIFT_GATE,
            SettingsKeys.CHAT_REMOVE_SOCIAL_SCORE_LIMIT,
            SettingsKeys.POST_REMOVE_SHARE_LIMIT,
    };

    private static final String[] MISC_KEYS = {
            SettingsKeys.BLOCK_SPLASH,
            SettingsKeys.STARTUP_TOAST,
            SettingsKeys.MATCH_MUTE_BACKGROUND_MUSIC,
            SettingsKeys.GAME_DICE,
            SettingsKeys.GAME_FINGER,
            SettingsKeys.PAGE_LOG,
    };

    // ------------------------------------------------------------------ 构建

    private static void addStatus(ModuleDialog dialog, LinearLayout parent, ConfigStore store) {
        Context context = dialog.context();
        TextView status = ViewKit.text(context, "", 13f,
                dialog.secondaryColor());
        status.setPadding(0, 0, 0, ViewKit.dp(context, 0x0A));
        parent.addView(status);
        renderStatus(status, store);
    }

    private static void renderStatus(TextView view, ConfigStore store) {
        String[] keys = allToggleKeys();
        int enabled = 0;
        for (String key : keys) {
            if (store.getBoolean(key)) {
                enabled++;
            }
        }
        view.setText("已开启 " + enabled + " / " + keys.length + " 项");
    }

    private static void addGroup(ModuleDialog dialog, LinearLayout parent, ConfigStore store,
            String title, String[] keys, String description) {
        Context context = dialog.context();

        TextView header = ViewKit.text(context, title, 15f,
                dialog.titleColor());
        header.setPadding(ViewKit.dp(context, 0x0E), ViewKit.dp(context, 0x0C),
                ViewKit.dp(context, 0x0E), ViewKit.dp(context, 0x04));
        if (description != null && !description.isEmpty()) {
            header.setContentDescription(title + "，" + description);
        }
        parent.addView(header);

        for (String key : keys) {
            addToggleRow(dialog, parent, store, key);
        }
    }

    private static void addToggleRow(ModuleDialog dialog, LinearLayout parent, ConfigStore store,
            String key) {
        Context context = dialog.context();
        TextView row = new TextView(context);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setMaxLines(2);
        row.setSingleLine(false);
        ViewKit.styleListItem(dialog, parent, row);
        ViewKit.makeFocusable(row);

        renderToggle(dialog, row, store, key);
        row.setOnClickListener(v -> {
            boolean next = !store.getBoolean(key);
            store.put(key, next);
            renderToggle(dialog, row, store, key);
            Toast.makeText(context, labelOf(key) + (next ? " 已开启" : " 已关闭"),
                    Toast.LENGTH_SHORT).show();
        });
    }

    private static void renderToggle(ModuleDialog dialog, TextView row, ConfigStore store, String key) {
        boolean on = store.getBoolean(key);
        row.setText(labelOf(key) + "　　" + (on ? "已开启" : "已关闭"));
        row.setTextColor(on ? ViewKit.ACCENT_COLOR : dialog.secondaryColor());
        row.setContentDescription(labelOf(key)
                + (on ? "，已开启，点击关闭" : "，已关闭，点击开启"));
    }

    private static void addConfigSection(ModuleDialog dialog, LinearLayout parent, ConfigStore store) {
        Context context = dialog.context();

        TextView header = ViewKit.text(context, "配置", 15f,
                dialog.titleColor());
        header.setPadding(ViewKit.dp(context, 0x0E), ViewKit.dp(context, 0x12),
                ViewKit.dp(context, 0x0E), ViewKit.dp(context, 0x04));
        parent.addView(header);

        addAction(dialog, parent, "导出配置（复制到剪贴板）",
                v -> ConfigPanel.exportToClipboard(dialog, store));
        addAction(dialog, parent, "导入配置（从剪贴板）",
                v -> ConfigPanel.importFromClipboard(dialog, store,
                        () -> refreshAll(dialog)));
        addAction(dialog, parent, "恢复默认设置",
                v -> ConfigPanel.confirmReset(dialog, store, () -> refreshAll(dialog)));
    }

    private static void addAction(ModuleDialog dialog, LinearLayout parent, String label,
            android.view.View.OnClickListener listener) {
        TextView row = ViewKit.text(dialog.context(), label, 14f,
                dialog.secondaryColor());
        ViewKit.styleListItem(dialog, parent, row);
        ViewKit.makeFocusable(row);
        row.setOnClickListener(listener);
    }

    /** 导入 / 重置后重建面板（最简单可靠，避免逐行同步状态）。 */
    private static void refreshAll(ModuleDialog dialog) {
        Activity activity = dialog.activity();
        dialog.close();
        show(activity);
    }

    // ------------------------------------------------------------------ 键名标签

    /** 全部布尔开关键（统计与遍历用），顺序取 {@link SettingDefaults} 的声明顺序。 */
    public static String[] allToggleKeys() {
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, Object> entry : SettingDefaults.defaults().entrySet()) {
            if (entry.getValue() instanceof Boolean) {
                keys.add(entry.getKey());
            }
        }
        return keys.toArray(new String[0]);
    }

    /** 键名 → 中文标签。未列出的键直接回显键名，便于排障。 */
    public static String labelOf(String key) {
        switch (key) {
            case SettingsKeys.AD_FEED: return "广场信息流广告";
            case SettingsKeys.AD_DETAIL: return "帖子详情广告";
            case SettingsKeys.AD_VIDEO: return "视频广告";
            case SettingsKeys.AD_TAG: return "标签页广告";
            case SettingsKeys.AD_TAG_WEB: return "标签网页广告";
            case SettingsKeys.AD_SEARCH: return "搜索页广告";
            case SettingsKeys.AD_GROUP: return "群组广告";
            case SettingsKeys.AD_PLANET: return "星球广告";
            case SettingsKeys.AD_ME: return "我的页广告";
            case SettingsKeys.AD_CONVERSATION: return "会话列表广告";
            case SettingsKeys.AD_ENVELOPE: return "信封消息广告";
            case SettingsKeys.AD_NOTICE: return "系统通知广告";
            case SettingsKeys.AD_CHAT_TOOLS: return "聊天工具广告";
            case SettingsKeys.AD_FLOAT: return "悬浮广告";
            case SettingsKeys.AD_INTERSTITIAL: return "插屏广告";
            case SettingsKeys.AD_REWARD_CHAT: return "聊天激励广告";
            case SettingsKeys.AD_REWARD_DRAMA: return "短剧激励广告";
            case SettingsKeys.AD_REWARD_WEB: return "网页激励广告";

            case SettingsKeys.PRIVACY_ALLOW_SCREENSHOT: return "隐私图允许截屏";
            case SettingsKeys.PRIVACY_ALLOW_REPEAT: return "闪照可重复查看";
            case SettingsKeys.PRIVACY_ALLOW_DOWNLOAD: return "隐私图允许保存";
            case SettingsKeys.DOWNLOAD_IMAGE_NO_WATERMARK: return "图片无水印";
            case SettingsKeys.DOWNLOAD_VIDEO_NO_WATERMARK: return "视频无水印";

            case SettingsKeys.FEED_FILTER_ENABLED: return "开启性别筛选";
            case SettingsKeys.FEED_KEYWORDS_ENABLED_V1: return "开启屏蔽词过滤";
            case SettingsKeys.FEED_FILTER_KEEP_UNKNOWN: return "保留性别未知";

            case SettingsKeys.SQUARE_VISIBLE_LIKE: return "可见卡片一键点赞";
            case SettingsKeys.SQUARE_LIKE_ALL_LOADED: return "已加载全部点赞";
            case SettingsKeys.SQUARE_AUTO_LIKE: return "自动点赞";

            case SettingsKeys.CHAT_ANTI_RECALL: return "防撤回";
            case SettingsKeys.CHAT_OWN_RECALL_NO_TIME_LIMIT: return "自己撤回不限时";
            case SettingsKeys.CHAT_HIDE_TYPING: return "隐藏正在输入";
            case SettingsKeys.CHAT_REMOVE_GIFT_GATE: return "解除礼物限制";
            case SettingsKeys.CHAT_REMOVE_BELL_GIFT_GATE: return "解除铃铛礼物限制";
            case SettingsKeys.CHAT_REMOVE_SOCIAL_SCORE_LIMIT: return "解除社交分限制";
            case SettingsKeys.POST_REMOVE_SHARE_LIMIT: return "解除帖子分享限制";

            case SettingsKeys.BLOCK_SPLASH: return "跳过启动广告";
            case SettingsKeys.STARTUP_TOAST: return "启动提示";
            case SettingsKeys.MATCH_MUTE_BACKGROUND_MUSIC: return "匹配页静音";
            case SettingsKeys.GAME_DICE: return "骰子可控";
            case SettingsKeys.GAME_FINGER: return "猜拳可控";
            case SettingsKeys.PAGE_LOG: return "页面日志";
            default: return key;
        }
    }
}
