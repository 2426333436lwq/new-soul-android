package dev.local.newsoul.feature.chat.voice;

import java.lang.ref.WeakReference;

import dev.local.newsoul.framework.Logger;

/**
 * 延迟探测语音入口的后台任务（原 {@code g} 索引 14）。
 *
 * <p>宿主对象以 {@link WeakReference} 持有；对象已回收时不处理。探测失败只记录
 * {@code voice library entry unavailable}，不影响宿主。</p>
 */
final class VoiceEntryTask implements Runnable {

    private final WeakReference<Object> hostReference;
    private final VoiceLibrary library;

    VoiceEntryTask(WeakReference<Object> hostReference, VoiceLibrary library) {
        this.hostReference = hostReference;
        this.library = library;
    }

    @Override
    public void run() {
        try {
            Object host = hostReference.get();
            if (host != null) {
                library.attachEntryPoints(host);
            }
        } catch (Throwable error) {
            Logger.w("voice library entry unavailable", error);
        }
    }
}
