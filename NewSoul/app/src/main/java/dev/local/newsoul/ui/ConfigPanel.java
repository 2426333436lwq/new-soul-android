package dev.local.newsoul.ui;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import java.util.Map;

import dev.local.newsoul.config.ConfigIO;
import dev.local.newsoul.config.ConfigStore;
import dev.local.newsoul.config.SettingDefaults;

/**
 * 配置导入 / 导出交互（原 {@code K} 类 + {@code ConfigIO}）。
 *
 * <p>原模块把导出结果写进 {@code newsoul_debug} 存储再由设置界面取用；此处改为
 * <b>剪贴板收口</b>：导出即复制，导入即粘贴。理由：模块运行在宿主进程内，没有自己的
 * Activity，剪贴板是唯一不依赖额外权限（无需 {@code READ/WRITE_EXTERNAL_STORAGE}、
 * 无需 {@code FileProvider}）的稳定通道，也便于用户在设备间搬运。</p>
 *
 * <p>格式与校验完全走 {@link ConfigIO}：非法输入整体拒绝，不做部分写入。</p>
 */
final class ConfigPanel {

    private ConfigPanel() {
    }

    /** 导出当前设置到剪贴板。 */
    static void exportToClipboard(ModuleDialog dialog, ConfigStore store) {
        Context context = dialog.context();
        try {
            String json = ConfigIO.export(store.snapshot());
            ClipboardManager clipboard =
                    (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard == null) {
                toast(context, "导出失败：剪贴板不可用");
                return;
            }
            clipboard.setPrimaryClip(ClipData.newPlainText("new-soul-config", json));
            toast(context, "配置已复制到剪贴板（" + json.length() + " 字符）");
        } catch (IllegalArgumentException exception) {
            toast(context, "导出失败：配置内容无效");
        } catch (Throwable throwable) {
            toast(context, "导出失败：" + throwable.getClass().getSimpleName());
        }
    }

    /** 从剪贴板导入设置。 */
    static void importFromClipboard(ModuleDialog dialog, ConfigStore store, Runnable onApplied) {
        Context context = dialog.context();
        try {
            ClipboardManager clipboard =
                    (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard == null || !clipboard.hasPrimaryClip()
                    || clipboard.getPrimaryClip() == null
                    || clipboard.getPrimaryClip().getItemCount() == 0) {
                toast(context, "导入失败：剪贴板为空");
                return;
            }
            CharSequence text = clipboard.getPrimaryClip().getItemAt(0).getText();
            if (text == null) {
                toast(context, "导入失败：剪贴板内容不是文本");
                return;
            }

            Map<String, Object> values = ConfigIO.parse(text.toString());
            store.putAll(values);
            toast(context, "已导入 " + values.size() + " 项设置");
            if (onApplied != null) {
                onApplied.run();
            }
        } catch (IllegalArgumentException exception) {
            toast(context, "导入失败：配置格式或内容无效");
        } catch (Throwable throwable) {
            toast(context, "导入失败：" + throwable.getClass().getSimpleName());
        }
    }

    /** 恢复默认：确认后清空主设置存储（缺省值由 {@link SettingDefaults} 提供）。 */
    static void confirmReset(ModuleDialog dialog, ConfigStore store, Runnable onApplied) {
        Activity activity = dialog.activity();
        new android.app.AlertDialog.Builder(activity)
                .setTitle("恢复默认设置")
                .setMessage("将清除全部已保存的开关状态，恢复到默认值。此操作不可撤销。")
                .setNegativeButton("取消", null)
                .setPositiveButton("恢复", (d, which) -> {
                    try {
                        android.content.SharedPreferences prefs = store.raw();
                        if (prefs != null) {
                            prefs.edit().clear().apply();
                        }
                        toast(dialog.context(), "已恢复默认设置");
                        if (onApplied != null) {
                            onApplied.run();
                        }
                    } catch (Throwable throwable) {
                        toast(dialog.context(), "恢复失败：" + throwable.getClass().getSimpleName());
                    }
                })
                .show();
    }

    private static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
