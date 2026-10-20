package dev.local.newsoul.feature.chat.voice;

import android.media.MediaPlayer;

/**
 * 试听准备完成监听（原匿名类 {@code R2}）。
 *
 * <p>仅当对话框未关闭、且当前播放器仍是发起播放的那一个时才真正开始播放；开始前重新校验
 * 会话状态，并把握手上的试听按钮文本切换为「停止试听」。任一环节失败即停止播放。</p>
 */
final class VoicePlayPreparedListener implements MediaPlayer.OnPreparedListener {

    /** 播放中按钮文本（原字面量）。 */
    private static final String PLAYING_LABEL = "停止试听";

    private final VoiceLibraryDialog dialog;

    VoicePlayPreparedListener(VoiceLibraryDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        if (dialog.closed || dialog.mediaPlayer != mediaPlayer) {
            return;
        }
        try {
            dialog.requireValid();
            mediaPlayer.start();
            if (dialog.previewButton != null) {
                dialog.previewButton.setText(PLAYING_LABEL);
            }
        } catch (Throwable error) {
            dialog.stopPlayback();
        }
    }
}
