package dev.local.newsoul.feature.chat.voice;

import android.media.MediaPlayer;

/**
 * 试听播放错误监听（原匿名类 {@code T2}）。
 *
 * <p>播放出错时停止并释放播放器，提示「暂时无法试听」，并消费该错误事件。</p>
 */
final class VoicePlayErrorListener implements MediaPlayer.OnErrorListener {

    /** 试听失败提示（原字面量）。 */
    private static final String PLAY_FAILURE_MESSAGE = "暂时无法试听";

    private final VoiceLibraryDialog dialog;

    VoicePlayErrorListener(VoiceLibraryDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        dialog.stopPlayback();
        VoiceLibrary.toast(dialog.activity, PLAY_FAILURE_MESSAGE);
        return true;
    }
}
