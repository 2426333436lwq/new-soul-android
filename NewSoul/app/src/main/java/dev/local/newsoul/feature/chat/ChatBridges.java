package dev.local.newsoul.feature.chat;

import android.app.Activity;

/**
 * 聊天功能域之间的能力桥。
 *
 * <p>复读菜单需要向长按菜单注入「转发」「存入语音库」项，并在这两项被点击时调用对应
 * 功能域的能力；但复读、转发、语音库是彼此独立安装的功能域。</p>
 *
 * <p>原模块用一个聚合类 {@code F} 的实例字段 {@code m}（转发）与静态标志 {@code d3.g}
 * （语音库）直接互相引用。重构后改为进程级注册：各功能域安装成功时登记自己的能力，
 * 复读菜单按需取用 —— 未登记则对应菜单项不出现，点击也不会发生。</p>
 *
 * <p>登记只发生在安装阶段（宿主进程启动、单线程），读取发生在 hook 回调，
 * 用 {@code volatile} 保证可见性。</p>
 */
public final class ChatBridges {

    /**
     * 消息转发入口（由转发功能域登记）。
     */
    public interface ForwardLauncher {

        /**
         * 打开转发目标选择页。
         *
         * @param activity    当前聊天 Activity
         * @param accountId   当前账号 id
         * @param peerId      对端会话 id
         * @param messageType 消息类型
         * @param body        已复刻的消息体
         */
        void launch(Activity activity, String accountId, String peerId, int messageType, Object body)
                throws Exception;
    }

    /**
     * 语音库存入口（由语音库功能域登记）。
     */
    public interface VoiceLibrarySaver {

        /**
         * 把一条语音消息存入本地语音库。
         *
         * @param host         会话 Fragment（宿主对象）
         * @param audioMessage 已复刻的语音消息体
         */
        void save(Object host, Object audioMessage) throws Exception;
    }

    private static volatile ForwardLauncher forwardLauncher;
    private static volatile VoiceLibrarySaver voiceLibrarySaver;

    private ChatBridges() {
    }

    public static void registerForwardLauncher(ForwardLauncher launcher) {
        forwardLauncher = launcher;
    }

    public static ForwardLauncher forwardLauncher() {
        return forwardLauncher;
    }

    public static boolean isForwardReady() {
        return forwardLauncher != null;
    }

    public static void registerVoiceLibrarySaver(VoiceLibrarySaver saver) {
        voiceLibrarySaver = saver;
    }

    public static VoiceLibrarySaver voiceLibrarySaver() {
        return voiceLibrarySaver;
    }

    public static boolean isVoiceLibraryReady() {
        return voiceLibrarySaver != null;
    }
}