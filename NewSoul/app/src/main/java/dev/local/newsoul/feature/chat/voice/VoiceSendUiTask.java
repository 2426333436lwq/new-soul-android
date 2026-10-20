package dev.local.newsoul.feature.chat.voice;

import java.io.File;

import dev.local.newsoul.framework.Logger;

/**
 * 语音发送界面任务（原 {@code H} 索引 12）。
 *
 * <p>在主线程调用宿主原生语音发送方法。与原实现一致：</p>
 * <ul>
 *   <li>校验会话状态、读取会话消息处理器（字段 {@code Q}）；</li>
 *   <li>发送调用本身失败时保留临时文件（便于用户排查），仅提示不重试；</li>
 *   <li>校验或取处理器失败时删除临时文件；</li>
 *   <li>无论成败都关闭对话框。</li>
 * </ul>
 */
final class VoiceSendUiTask implements Runnable {

    /** 会话消息处理器字段名（原字面量 {@code "Q"}）。 */
    private static final String CHAT_HANDLER_FIELD = "Q";

    /** 会话未就绪时的异常信息（原字面量）。 */
    private static final String CHAT_NOT_READY_MESSAGE = "聊天尚未准备好";

    /** 发送成功提示（原字面量）。 */
    private static final String SUBMITTED_MESSAGE = "已提交发送，请查看聊天中的消息状态";

    /** 发送失败提示（原字面量）。 */
    private static final String FAILURE_MESSAGE = "发送未完成，请查看消息状态";

    private final VoiceLibraryDialog dialog;
    private final File file;
    private final VoiceRecord record;

    VoiceSendUiTask(VoiceLibraryDialog dialog, File file, VoiceRecord record) {
        this.dialog = dialog;
        this.file = file;
        this.record = record;
    }

    @Override
    public void run() {
        boolean keepFile = true;
        try {
            try {
                dialog.requireValid();
                Object chatHandler = VoiceLibrary.readField(dialog.host, CHAT_HANDLER_FIELD);
                if (chatHandler == null) {
                    throw new IllegalStateException(CHAT_NOT_READY_MESSAGE);
                }
                try {
                    dialog.library.sendVoiceMethod().invoke(chatHandler, file.getAbsolutePath(),
                            record.durationSeconds(), null);
                    VoiceLibrary.toast(dialog.activity, SUBMITTED_MESSAGE);
                    Logger.i("voice library send submitted explicitly");
                    return;
                } catch (Throwable error) {
                    VoiceLibrary.failure(dialog.activity, FAILURE_MESSAGE, error);
                    return;
                }
            } catch (Throwable error) {
                keepFile = false;
                VoiceLibrary.failure(dialog.activity, FAILURE_MESSAGE, error);
                return;
            }
        } finally {
            if (!keepFile) {
                file.delete();
            }
            dialog.close();
        }
    }
}
