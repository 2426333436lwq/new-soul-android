package dev.local.newsoul.feature.chat.voice;

import io.github.libxposed.api.XposedInterface;

import java.lang.ref.WeakReference;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.feature.chat.ChatBridges;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 语音库功能域安装器（原 {@code F.h} 的语音安装段与 {@code N2} 匿名 hooker）。
 *
 * <p>安装成功后登记 {@link ChatBridges.VoiceLibrarySaver}，供复读菜单追加「存入语音库」项；
 * 任一宿主签名不匹配或 hook 失败即记录 {@value #UNAVAILABLE_LOG} 并放弃整域。</p>
 *
 * <p>入口 hook 在原方法 {@code proceed()} 之后，把宿主对象以 {@link WeakReference} 捕获并
 * 在 {@code 0/350/1000} 毫秒三次延迟探测语音入口（原实现用于等待输入区视图就绪）；
 * 关闭 hook 在 {@code proceed()} 之前关闭归属当前 Fragment 的对话框。</p>
 */
public final class VoiceLibraryFeature implements FeatureInstaller {

    /** 功能域标识。 */
    private static final String ID = "chat.voice-library";

    /** 安装成功日志（原字面量）。 */
    private static final String INSTALLED_LOG =
            "voice library installed; local storage and native audio upload";

    /** 安装失败日志（原字面量）。 */
    private static final String UNAVAILABLE_LOG = "voice library unavailable";

    /** 入口探测延迟（毫秒，原 {@code {0, 350, 1000}}）。 */
    private static final long[] ENTRY_PROBE_DELAYS_MILLIS = {0L, 350L, 1000L};

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void install(ModuleContext context) {
        VoiceLibrary library = new VoiceLibrary();
        try {
            library.install(context.module(), context.classLoader());
        } catch (Exception error) {
            Logger.e(UNAVAILABLE_LOG, error);
            return;
        }
        ChatBridges.registerVoiceLibrarySaver(library);
    }

    /** 会话恢复 hook（原 {@code N2} 索引 0）。 */
    static XposedInterface.Hooker entryHooker(VoiceLibrary library) {
        return chain -> {
            Object result = chain.proceed();
            WeakReference<Object> reference = new WeakReference<>(chain.getThisObject());
            for (long delay : ENTRY_PROBE_DELAYS_MILLIS) {
                library.mainHandler().postDelayed(new VoiceEntryTask(reference, library), delay);
            }
            return result;
        };
    }

    /** 会话暂停 hook（原 {@code N2} 索引 1）：在放行前关闭本 Fragment 的对话框。 */
    static XposedInterface.Hooker closeHooker(VoiceLibrary library) {
        return chain -> {
            Object self = chain.getThisObject();
            VoiceLibraryDialog current = library.currentDialog();
            if (current != null && current.host == self) {
                library.currentDialog().close();
            }
            return chain.proceed();
        };
    }
}
