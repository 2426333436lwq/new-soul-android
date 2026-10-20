package dev.local.newsoul.feature.chat.voice;

import android.content.DialogInterface;

/**
 * 空按钮点击（原 {@code L} 的无副作用分支）。
 *
 * <p>「取消」等按钮的占位监听，实际行为由对话框自身的取消/关闭流程接管。</p>
 */
final class VoiceDialogNoOpClick implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // 空操作，保留语义占位。
    }
}
