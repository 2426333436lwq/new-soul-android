package dev.local.newsoul.feature.chat.repeat;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.feature.chat.ChatBridges;
import dev.local.newsoul.feature.chat.HostAccess;
import dev.local.newsoul.feature.chat.MessageRewrite;
import dev.local.newsoul.framework.Logger;

/**
 * 复读菜单渲染（原模块 {@code F.a} 与回退渲染 {@code F.i}）。
 *
 * <p>在宿主原生长按菜单上追加自定义项：</p>
 * <ul>
 *   <li><b>复读</b>（{@value #ACTION_REPEAT}）—— 始终尝试添加，图标缺失时整体放弃；</li>
 *   <li><b>转发</b>（{@value #ACTION_FORWARD}）—— 仅当转发功能域已登记能力且宿主分享图标存在时添加；</li>
 *   <li><b>存入语音库</b>（{@value #ACTION_VOICE_LIBRARY}）—— 仅当语音库功能域已登记能力且消息为语音时添加。</li>
 * </ul>
 *
 * <p>菜单项与上下文（{@link RepeatEntry}）在添加时同步登记，供点击 hooker 按菜单项反查
 * 该动作作用于哪条消息。若菜单中已存在任一自定义项（重复渲染），直接返回原菜单不做改动。</p>
 *
 * <p>两条渲染路径的区别：{@link #render} 用于宿主主动渲染菜单的回调，异常向上传播由 hooker
 * 兜底；{@link #renderFallback} 用于「宿主未走菜单渲染、但有长按上下文残留」的兜底路径，
 * 内部吞掉全部异常并返回原菜单 —— 与原实现的容错差异一致。</p>
 */
public final class RepeatMenu {

    /** 复读动作码（原模块自定义 action）。 */
    public static final int ACTION_REPEAT = 0x4e5301;

    /** 转发动作码（原模块自定义 action）。 */
    public static final int ACTION_FORWARD = 0x4e5302;

    /** 存入语音库动作码（原模块自定义 action）。 */
    public static final int ACTION_VOICE_LIBRARY = 0x4e5303;

    private static final String TITLE_REPEAT = "复读";
    private static final String TITLE_FORWARD = "转发";
    private static final String TITLE_VOICE_LIBRARY = "存入语音库";

    /** 复读项图标资源名（宿主内置）。 */
    private static final String ICON_REPEAT = "c_ct_resend_menu";

    /** 转发项图标资源名（宿主内置）。 */
    private static final String ICON_FORWARD = "c_ct_longclick_menu_share";

    /** 存入语音库项图标资源名（宿主内置）。 */
    private static final String ICON_VOICE_LIBRARY = "c_ct_chat_icon_photo_download";

    private static final String ICON_TYPE = "drawable";

    /** 会话 Fragment 中的对端 id 字段（宿主私有）。 */
    private static final String OWNER_PEER_FIELD = "L";

    /** 语音消息类型。 */
    private static final int MESSAGE_TYPE_AUDIO = 5;

    /** 宿主菜单渲染 hook 是否已就位（原模块静态字段 {@code F.o}）。 */
    private static volatile boolean nativeMenuInstalled;

    private final Method currentAccount;
    private final Method actionCode;
    private final Constructor<?> menuItem;
    private final RepeatAction action;

    public RepeatMenu(Method currentAccount, Method actionCode, Constructor<?> menuItem,
                      RepeatAction action) {
        this.currentAccount = currentAccount;
        this.actionCode = actionCode;
        this.menuItem = menuItem;
        this.action = action;
    }

    /** 标记宿主菜单渲染 hook 已安装，回退渲染路径据此启用。 */
    public static void markNativeMenuInstalled() {
        nativeMenuInstalled = true;
    }

    /**
     * 在长按菜单末尾追加自定义项（原 {@code F.a}）。
     *
     * <p>返回原菜单（{@code result}）表示「未添加」；返回新列表表示已添加至少一项。
     * 本方法不做异常兜底，反射失败或宿主资源缺失导致的异常由调用方处理。</p>
     */
    public Object render(Object result, Object owner, Object message) throws Exception {
        if (!(result instanceof List)) {
            return result;
        }
        List<?> items = (List<?>) result;
        if (items.isEmpty()) {
            return result;
        }
        for (Object item : items) {
            int code = actionCodeOf(item);
            if (code == ACTION_REPEAT || code == ACTION_FORWARD || code == ACTION_VOICE_LIBRARY) {
                return result;
            }
        }

        String accountId = (String) currentAccount.invoke(null);
        String peerId = (String) HostAccess.readField(owner, OWNER_PEER_FIELD);
        if (!RepeatEligibility.isEligible(message, accountId, peerId)) {
            return result;
        }
        Object chat = HostAccess.call(message, "getChatMessage");
        MessageRewrite.fromChatMessage(chat);

        Context context = (Context) HostAccess.call(owner, "getContext");
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        int repeatIcon = resources.getIdentifier(ICON_REPEAT, ICON_TYPE, packageName);
        if (repeatIcon == 0) {
            return result;
        }

        List<Object> output = new ArrayList<>(items);
        output.add(register(owner, message, accountId, peerId,
                menuItem.newInstance(TITLE_REPEAT, repeatIcon, ACTION_REPEAT)));

        if (ChatBridges.isForwardReady()) {
            int shareIcon = resources.getIdentifier(ICON_FORWARD, ICON_TYPE, packageName);
            if (shareIcon != 0) {
                output.add(register(owner, message, accountId, peerId,
                        menuItem.newInstance(TITLE_FORWARD, shareIcon, ACTION_FORWARD)));
                Logger.i("message forward menu added");
            }
        }
        if (ChatBridges.isVoiceLibraryReady()
                && ((Integer) HostAccess.call(chat, "getMsgType")).intValue() == MESSAGE_TYPE_AUDIO) {
            int voiceIcon = resources.getIdentifier(ICON_VOICE_LIBRARY, ICON_TYPE, packageName);
            output.add(register(owner, message, accountId, peerId, menuItem.newInstance(
                    TITLE_VOICE_LIBRARY, voiceIcon != 0 ? voiceIcon : repeatIcon, ACTION_VOICE_LIBRARY)));
        }

        Logger.i("repeat menu added; type=" + HostAccess.call(chat, "getMsgType")
                + "; own=" + accountId.equals(HostAccess.call(message, "getFrom")));
        return output;
    }

    /**
     * 菜单渲染回退路径（原 {@code F.i}）。
     *
     * <p>宿主渲染长按菜单前会经过 {@code ConversationFragment.ac}，模块在那里把「宿主 / 消息 /
     * 消息行视图」压入线程私有栈。若宿主没有走菜单渲染方法（例如菜单由其它分支构造），
     * 则在此读取栈顶上下文重新渲染一次。</p>
     *
     * <p>栈帧只允许消费一次；渲染 hook 未就位、行视图已脱离窗口、会话已变化等任一情况
     * 都直接返回原菜单。</p>
     */
    public Object renderFallback(Object result) {
        RepeatContext context = RepeatContext.current();
        if (context == null || !context.consume()) {
            return result;
        }
        if (!nativeMenuInstalled) {
            return result;
        }
        try {
            Object rowView = context.rowView();
            if (!(rowView instanceof View) || !((View) rowView).isAttachedToWindow()) {
                return result;
            }
            String accountId = (String) currentAccount.invoke(null);
            String peerId = (String) HostAccess.readField(context.owner(), OWNER_PEER_FIELD);
            RepeatEntry entry = new RepeatEntry(context.owner(), context.message(), accountId, peerId);
            action.requireActivity(entry);
            return render(result, context.owner(), context.message());
        } catch (Throwable throwable) {
            Logger.w("repeat.fallback.skipped", throwable);
            return result;
        }
    }

    /** 读取菜单项的动作码（原 {@code F.c} 字段绑定的方法）。 */
    int actionCodeOf(Object item) throws Exception {
        return ((Integer) actionCode.invoke(item)).intValue();
    }

    /** 登记菜单项上下文并返回该菜单项，便于链式添加。 */
    private Object register(Object owner, Object message, String accountId, String peerId,
                            Object item) {
        RepeatEntry.register(item, new RepeatEntry(owner, message, accountId, peerId));
        return item;
    }
}