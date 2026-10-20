package dev.local.newsoul.feature.chat.voice;

import android.media.AudioAttributes;
import android.media.MediaPlayer;

/**
 * 试听动作（原 {@code Q2} 动作码 0）。
 *
 * <p>已有播放器时停止试听；否则读取音频并异步准备播放，期间挂接准备/完成/错误三个监听。</p>
 */
final class VoicePlayAction implements RunnableAction {

    private final VoiceLibraryDialog dialog;
    private final VoiceRecord record;

    VoicePlayAction(VoiceLibraryDialog dialog, VoiceRecord record) {
        this.dialog = dialog;
        this.record = record;
    }

    @Override
    public void run() {
        dialog.requireValid();
        if (dialog.mediaPlayer != null) {
            dialog.stopPlayback();
            return;
        }
        VoiceRecord fresh;
        try {
            fresh = dialog.store.read(record.id());
        } catch (Exception error) {
            throw new IllegalStateException(error);
        }
        MediaPlayer player = new MediaPlayer();
        dialog.mediaPlayer = player;
        player.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build());
        player.setOnPreparedListener(new VoicePlayPreparedListener(dialog));
        player.setOnCompletionListener(new VoicePlayCompletionListener(dialog));
        player.setOnErrorListener(new VoicePlayErrorListener(dialog));
        try {
            player.setDataSource(fresh.audioFile().getAbsolutePath());
            player.prepareAsync();
        } catch (Throwable error) {
            dialog.stopPlayback();
            if (error instanceof RuntimeException) {
                throw (RuntimeException) error;
            }
            throw new IllegalStateException(error);
        }
    }
}
