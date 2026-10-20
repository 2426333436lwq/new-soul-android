package dev.local.newsoul.feature.chat.repeat;

import android.app.Activity;

import java.lang.reflect.Method;

import dev.local.newsoul.feature.chat.HostAccess;
import dev.local.newsoul.feature.chat.MessageRewrite;
import dev.local.newsoul.framework.Logger;

/**
 * 复读执行器（原模块 {@code F.j} 与校验方法 {@code F.k}）。
 *
 * <p>复读分两条路径：</p>
 * <ul>
 *   <li><b>文本</b>：不重新发送消息，而是把原文回填到输入框（宿主 {@code y4.Y1}），
 *       保留用户手动修改的余地；回填需要宿主的媒体键盘与用户信息，取不到时按「聊天未就绪」处理。</li>
 *   <li><b>图片 / 多图 / 语音</b>：重建消息体后走完整发送链路 ——
 *       落库（{@code y4.u}）→ 组装发送消息（{@code ImMessage.createChatSendMsg}）→
 *       可发送判定（{@code chat.utils.f.a}）→ 发送（{@code y4.p1}）。</li>
 * </ul>
 *
 * <p>执行前必须通过 {@link #requireActivity(RepeatEntry)} 的会话归属复检：宿主会话可能在菜单
 * 弹出后被切换或销毁，此时一律拒绝执行。</p>
 *
 * <p>失败时抛出异常，由调用方（菜单点击 hooker）统一提示并放弃重试。</p>
 */
public final class RepeatAction {

    /** 会话 Fragment 中的发送者字段（宿主私有）。 */
    private static final String OWNER_SENDER_FIELD = "Q";

    /** 会话 Fragment 中的媒体键盘字段（宿主私有）。 */
    private static final String OWNER_KEYBOARD_FIELD = "s";

    /** 会话 Fragment 中的用户信息字段（宿主私有）。 */
    private static final String OWNER_USER_FIELD = "O";

    /** 会话 Fragment 中的对端 id 字段（宿主私有）。 */
    private static final String OWNER_PEER_FIELD = "L";

    /** 文本消息体的正文字段（宿主私有）。 */
    private static final String BODY_TEXT_FIELD = "text";

    /** 文本消息类型：走输入框回填而不是直接发送。 */
    private static final int TYPE_TEXT = 1;

    private final Method currentAccount;
    private final Method createChatMessage;
    private final Method setMsgContent;
    private final Method setMsgType;
    private final Method persistChatMessage;
    private final Method createSendMessage;
    private final Method canSendMessage;
    private final Method dispatchMessage;
    private final Method fillText;

    public RepeatAction(Method currentAccount, Method createChatMessage, Method setMsgContent,
                        Method setMsgType, Method persistChatMessage, Method createSendMessage,
                        Method canSendMessage, Method dispatchMessage, Method fillText) {
        this.currentAccount = currentAccount;
        this.createChatMessage = createChatMessage;
        this.setMsgContent = setMsgContent;
        this.setMsgType = setMsgType;
        this.persistChatMessage = persistChatMessage;
        this.createSendMessage = createSendMessage;
        this.canSendMessage = canSendMessage;
        this.dispatchMessage = dispatchMessage;
        this.fillText = fillText;
    }

    /**
     * 校验会话仍然有效并返回宿主 Activity（原 {@code F.k}）。
     *
     * <p>要求：Activity 存在且未结束、Fragment 处于 resumed、当前账号与对端 id 均与登记时一致、
     * 消息仍满足复读条件。任一不满足抛 {@code IllegalStateException("Chat changed")}。</p>
     */
    public Activity requireActivity(RepeatEntry entry) throws Exception {
        Object owner = entry.owner();
        Activity activity = (Activity) HostAccess.call(owner, "getActivity");
        boolean valid = activity != null
                && !activity.isFinishing()
                && !activity.isDestroyed()
                && ((Boolean) HostAccess.call(owner, "isResumed")).booleanValue()
                && entry.accountId().equals(currentAccount.invoke(null))
                && entry.peerId().equals(HostAccess.readField(owner, OWNER_PEER_FIELD))
                && RepeatEligibility.isEligible(entry.message(), entry.accountId(), entry.peerId());
        if (!valid) {
            throw new IllegalStateException("Chat changed");
        }
        return activity;
    }

    /** 复读一条消息（原 {@code F.j}）。 */
    public void repeat(RepeatEntry entry) throws Exception {
        requireActivity(entry);
        Object owner = entry.owner();
        Object chat = HostAccess.call(entry.message(), "getChatMessage");
        Object body = MessageRewrite.fromChatMessage(chat);
        int type = ((Integer) HostAccess.call(chat, "getMsgType")).intValue();

        Object sender = HostAccess.readField(owner, OWNER_SENDER_FIELD);
        if (sender == null) {
            throw new IllegalStateException("Sender unavailable");
        }
        if (type == TYPE_TEXT) {
            repeatText(owner, sender, body);
        } else {
            repeatBody(entry, sender, body, type);
        }
        Logger.i("repeat dispatched; type=" + type);
    }

    /** 文本复读：把原文回填到输入框。 */
    private void repeatText(Object owner, Object sender, Object body) throws Exception {
        Object keyboard = HostAccess.readField(owner, OWNER_KEYBOARD_FIELD);
        Object user = HostAccess.readField(owner, OWNER_USER_FIELD);
        if (keyboard == null || user == null) {
            throw new IllegalStateException("Chat not ready");
        }
        fillText.invoke(sender, HostAccess.readField(body, BODY_TEXT_FIELD), keyboard, user);
    }

    /** 图片 / 多图 / 语音复读：重建消息体后走完整发送链路。 */
    private void repeatBody(RepeatEntry entry, Object sender, Object body, int type) throws Exception {
        Object chatMessage = createChatMessage.invoke(null, entry.peerId());
        setMsgContent.invoke(chatMessage, body);
        setMsgType.invoke(chatMessage, type);
        persistChatMessage.invoke(null, chatMessage);

        Object sendMessage = createSendMessage.invoke(null, chatMessage, entry.peerId());
        if (!((Boolean) canSendMessage.invoke(null, sendMessage)).booleanValue()) {
            return;
        }
        dispatchMessage.invoke(sender, sendMessage);
    }
}