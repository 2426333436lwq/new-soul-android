package dev.local.newsoul.feature.chat.voice;

import android.content.Context;

/**
 * 「保存到语音库」提交回调（原 {@code H2} 的 {@code P2} 角色）。
 *
 * <p>校验会话状态后，把下载与落库动作提交为后台任务，成功后回到语音库列表页。</p>
 */
final class VoiceSaveSubmit implements TextConsumer {

    private final VoiceLibraryDialog dialog;
    private final String url;
    private final int duration;

    VoiceSaveSubmit(VoiceLibraryDialog dialog, String url, int duration) {
        this.dialog = dialog;
        this.url = url;
        this.duration = duration;
    }

    @Override
    public void accept(String remark) {
        dialog.requireValid();
        Context appContext = dialog.activity.getApplicationContext();
        dialog.submitBackground(new VoiceSaveTask(dialog, appContext, url, remark, duration),
                new VoicePostSaveAction(VoicePostSaveAction.OPEN_LIBRARY, dialog));
    }
}
