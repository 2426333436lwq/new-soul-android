package dev.local.newsoul.feature.chat.voice;

import android.media.MediaPlayer;

/**
 * 试听播放完成监听（原匿名类 {@code S2}）。
 *
 * <p>播放自然结束时停止并释放播放器，复位试听按钮。</p>
 */
final class VoicePlayCompletionListener implements MediaPlayer.OnCompletionListener {

    private final VoiceLibraryDialog dialog;

    VoicePlayCompletionListener(VoiceLibraryDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        dialog.stopPlayback();
    }
}
