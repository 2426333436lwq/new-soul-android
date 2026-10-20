package dev.local.newsoul.feature.chat.voice;

import android.app.Activity;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * 语音入口长按监听（原 {@code O2}）。
 *
 * <p>长按输入区语音图标即打开语音库：先关闭既有对话框，再记录新对话框并打开列表页。
 * 打开失败提示「暂时无法打开语音库」，无论成功失败都消费该次长按（返回 {@code true}）。</p>
 */
final class VoiceLibraryEntryListener implements View.OnLongClickListener {

    /** 打开失败提示（原字面量）。 */
    private static final String FAILURE_MESSAGE = "暂时无法打开语音库";

    private final VoiceLibrary library;
    private final Activity activity;
    private final WeakReference<Object> hostReference;

    VoiceLibraryEntryListener(VoiceLibrary library, Activity activity,
            WeakReference<Object> hostReference) {
        this.library = library;
        this.activity = activity;
        this.hostReference = hostReference;
    }

    @Override
    public boolean onLongClick(View view) {
        try {
            Object host = hostReference.get();
            if (host == null) {
                return false;
            }
            VoiceLibraryDialog dialog = new VoiceLibraryDialog(library, host);
            VoiceLibraryDialog previous = library.currentDialog();
            if (previous != null) {
                previous.close();
            }
            library.setCurrentDialog(dialog);
            dialog.openLibrary();
        } catch (Throwable error) {
            VoiceLibrary.failure(activity, FAILURE_MESSAGE, error);
        }
        return true;
    }
}
