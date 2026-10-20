package dev.local.newsoul.ui;

import android.content.DialogInterface;

/**
 * 空对话框按钮监听（原 {@code L} 的索引 1 分支）。
 *
 * <p>用于「取消」等按钮：点击后不执行任何动作，由对话框自身的取消/关闭流程接管。</p>
 */
final class NoOpClickListener implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // 空操作，保留语义占位。
    }
}
