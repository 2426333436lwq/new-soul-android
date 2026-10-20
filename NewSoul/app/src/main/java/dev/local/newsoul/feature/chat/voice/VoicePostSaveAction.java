package dev.local.newsoul.feature.chat.voice;

/**
 * 后台任务完成后的界面动作（原 {@code U2}）。
 *
 * <p>三种结果：关闭对话框（{@link #CLOSE}）、直接打开语音库列表（{@link #OPEN_LIBRARY}）、
 * 先提示「已保存到语音库」再打开列表（{@link #SAVED}）。</p>
 */
final class VoicePostSaveAction implements RunnableAction {

    /** 打开语音库列表（原动作码 0/3/default）。 */
    static final int OPEN_LIBRARY = 0;

    /** 关闭对话框（原动作码 1）。 */
    static final int CLOSE = 1;

    /** 提示已保存后打开列表（原动作码 2）。 */
    static final int SAVED = 2;

    /** 保存成功提示（原字面量）。 */
    private static final String SAVED_MESSAGE = "已保存到语音库";

    private final int action;
    private final VoiceLibraryDialog dialog;

    VoicePostSaveAction(int action, VoiceLibraryDialog dialog) {
        this.action = action;
        this.dialog = dialog;
    }

    @Override
    public void run() {
        switch (action) {
            case CLOSE:
                dialog.close();
                return;
            case SAVED:
                VoiceLibrary.toast(dialog.activity, SAVED_MESSAGE);
                dialog.openLibrary();
                return;
            default:
                dialog.openLibrary();
        }
    }
}
