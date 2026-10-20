package dev.local.newsoul;

import android.content.Context;
import android.widget.Switch;

/**
 * 自定义开关。
 * 对应原始 DEX 的 r2（extends Switch）。
 *
 * 原实现要点：
 *   - 构造时读 SharedPreferences 恢复状态
 *   - setChecked 不会触发 OnCheckedChangeListener（避免初始化回写）
 *   - toggle() 手动转发
 *   - 长按显示该项说明（对应 UI 文案里的详细解释）
 */
public final class ToggleSwitch extends Switch {

    private final String key;
    private boolean programmatic = false;

    public ToggleSwitch(Context ctx, String key) {
        super(ctx);
        this.key = key;

        setText(key);
        // 恢复状态（不触发监听）
        programmatic = true;
        setChecked(FeatureCatalog.isEnabled(ctx, key));
        programmatic = false;

        setOnCheckedChangeListener((btn, checked) -> {
            if (programmatic) return;
            FeatureCatalog.put(ctx, key, checked);
        });

        // 长按显示说明
        setOnLongClickListener(v -> {
            String desc = describe(key);
            if (desc != null) {
                android.widget.Toast.makeText(ctx, desc, android.widget.Toast.LENGTH_LONG).show();
            }
            return true;
        });
    }

    @Override
    public void setChecked(boolean checked) {
        programmatic = true;
        super.setChecked(checked);
        programmatic = false;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    // ------------------------------------------------------------------

    /** 各开关的中文说明（来自原始 UI 字符串）。 */
    private static String describe(String key) {
        switch (key) {
            case "chat_anti_recall":
                return "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。";
            case "privacy_allow_screenshot":
                return "仅适用于收到的隐私图片和视频；开启后该类页面可截屏。";
            case "privacy_allow_repeat":
                return "查看时不扣减本机剩余查看次数。";
            case "download_image_no_watermark":
            case "download_video_no_watermark":
                return "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。";
            case "square_auto_like":
            case "square_visible_like":
                return "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，"
                        + "一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。";
            case "block_splash":
                return "跳过启动广告页，包括首次启动。";
            case "feed_filter_enabled":
                return "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。";
            default:
                return null;
        }
    }
}
