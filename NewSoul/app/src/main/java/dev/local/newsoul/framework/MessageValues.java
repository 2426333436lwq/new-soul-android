package dev.local.newsoul.framework;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 宿主消息对象（{@code ImMessage}）的反射访问器。
 *
 * <p>反撤回、撤回标记、隐私等功能域都需要读取「当前会话标识」与消息的收发方信息。
 * 这些反射目标在安装时统一解析一次，避免在每次 hook 回调中重复搜索方法。</p>
 *
 * <p>同时提供两个派生能力：</p>
 * <ul>
 *   <li>{@link #recallFingerprint(Object)} —— 由会话标识与收发方、消息 ID 生成稳定指纹，
 *       用作「已保留的撤回消息」索引键。</li>
 *   <li>{@link #matchesRecallSession(Object)} —— 判断消息是否属于当前打开的私聊会话，
 *       用于决定是否展示撤回气泡。</li>
 * </ul>
 */
public final class MessageValues {

    /** 宿主消息类型：撤回通知。 */
    public static final int MSG_TYPE_RECALL = 9;

    /** 撤回保留判定使用的快照扩展键。 */
    private static final String EXT_IS_SNAP = "isSnap";

    /** 会话标识提供者：宿主静态无参方法，返回当前会话的标识字符串。 */
    private final Method sessionId;
    private final Method chatMessage;
    private final Method from;
    private final Method to;
    private final Method msgId;
    private final Method anonymity;
    private final Method msgType;
    private final Method msgContent;
    private final Method transExt;

    private MessageValues(Method sessionId, Method chatMessage, Method from, Method to,
                          Method msgId, Method anonymity, Method msgType,
                          Method msgContent, Method transExt) {
        this.sessionId = sessionId;
        this.chatMessage = chatMessage;
        this.from = from;
        this.to = to;
        this.msgId = msgId;
        this.anonymity = anonymity;
        this.msgType = msgType;
        this.msgContent = msgContent;
        this.transExt = transExt;
    }

    /** 从宿主类加载器解析全部访问方法；缺失的项为 {@code null}。 */
    public static MessageValues from(ModuleContext context) {
        Class<?> session = context.loadHostClass(HostClasses.IMLIB_SESSION_PROVIDER);
        Class<?> message = context.loadHostClass(HostClasses.IMLIB_IM_MESSAGE);
        Class<?> chat = context.loadHostClass(HostClasses.IMLIB_CHAT_MESSAGE);
        return new MessageValues(
                Reflect.findDeclaredMethod(session, "g"),
                Reflect.findDeclaredMethod(message, "getChatMessage"),
                Reflect.findDeclaredMethod(message, "getFrom"),
                Reflect.findDeclaredMethod(message, "getTo"),
                Reflect.findDeclaredMethod(message, "getMsgId"),
                Reflect.findDeclaredMethod(message, "isAnonymityChat"),
                Reflect.findDeclaredMethod(chat, "getMsgType"),
                Reflect.findDeclaredMethod(chat, "getMsgContent"),
                Reflect.findDeclaredMethod(chat, "getStringTransExt", String.class));
    }

    /** 关键访问方法是否全部就绪；不就绪时依赖本类的功能域应整体降级。 */
    public boolean isReady() {
        return sessionId != null && chatMessage != null && from != null
                && to != null && msgId != null && anonymity != null;
    }

    /** 消息承载的会话对象（{@code ImMessage#getChatMessage()}）。 */
    public Object chatMessageOf(Object message) {
        return Reflect.invoke(message, chatMessage);
    }

    /** 消息类型（{@code ChatMessage#getMsgType()}）；不可用时返回 {@code -1}。 */
    public int msgTypeOf(Object message) {
        Object chat = chatMessageOf(message);
        Object type = chat == null ? null : Reflect.invoke(chat, msgType);
        return type instanceof Integer ? (Integer) type : -1;
    }

    /** 消息内容（{@code ChatMessage#getMsgContent()}）。 */
    public Object msgContentOf(Object message) {
        Object chat = chatMessageOf(message);
        return chat == null ? null : Reflect.invoke(chat, msgContent);
    }

    /**
     * 消息是否携带可保留的内容。
     *
     * <p>撤回通知（{@link #MSG_TYPE_RECALL}）本身不算内容；其余类型要求内容非空。</p>
     */
    public boolean hasContent(Object message) {
        if (msgTypeOf(message) == MSG_TYPE_RECALL) {
            return false;
        }
        return msgContentOf(message) != null;
    }

    /** 读取会话快照扩展字段（{@code ChatMessage#getStringTransExt(String)}）。 */
    public String transExtOf(Object message, String key) {
        Object chat = chatMessageOf(message);
        if (chat == null || transExt == null) {
            return null;
        }
        Object value = Reflect.invoke(chat, transExt, key);
        return value instanceof String ? (String) value : null;
    }

    /** 消息 ID（{@code ImMessage#getMsgId()}）。 */
    public String msgIdOf(Object message) {
        return asText(Reflect.invoke(message, msgId));
    }

    /** 发送方（{@code ImMessage#getFrom()}）。 */
    public String fromOf(Object message) {
        return asText(Reflect.invoke(message, from));
    }

    /** 接收方（{@code ImMessage#getTo()}）。 */
    public String toOf(Object message) {
        return asText(Reflect.invoke(message, to));
    }

    /** 当前会话标识（{@code j0.g()}）。 */
    public String sessionIdOf() {
        return asText(Reflect.invoke(null, sessionId));
    }

    /**
     * 生成消息指纹：{@code 会话标识\n发送方\n接收方\n消息 ID} 的 SHA-256（小写十六进制）。
     *
     * <p>用于在「撤回消息保留库」中按指纹判断某条消息是否已被保留。</p>
     */
    public String recallFingerprint(Object message) {
        String raw = String.valueOf(Reflect.invoke(null, sessionId))
                + "\n" + String.valueOf(Reflect.invoke(message, from))
                + "\n" + String.valueOf(Reflect.invoke(message, to))
                + "\n" + String.valueOf(Reflect.invoke(message, msgId));
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return ValueCodec.toHex(digest.digest(raw.getBytes(StandardCharsets.UTF_8)));
        } catch (Throwable throwable) {
            Logger.w("recall fingerprint unavailable", throwable);
            return "";
        }
    }

    /**
     * 判断消息是否属于当前打开的私聊会话。
     *
     * <p>排除匿名聊天与无会话消息；要求会话标识非空、发送方非空且不等于会话标识，
     * 并且接收方等于会话标识。</p>
     */
    public boolean matchesRecallSession(Object message) {
        if (message == null) {
            return false;
        }
        if (Boolean.TRUE.equals(Reflect.invoke(message, anonymity))) {
            return false;
        }
        if (Reflect.invoke(message, chatMessage) == null) {
            return false;
        }
        String session = sessionIdOf();
        String sender = fromOf(message);
        String receiver = toOf(message);
        if (session == null || session.isEmpty()) {
            return false;
        }
        if (sender == null || sender.isEmpty()) {
            return false;
        }
        if (sender.equals(session)) {
            return false;
        }
        return session.equals(receiver);
    }

    /**
     * 生成消息诊断描述（仅诊断会话激活时输出详情）。
     *
     * <p>诊断上报子系统属原作者专属部分，本重构不实现，因此恒返回空串；
     * 保留完整分支语义以便后续接入。</p>
     */
    public String describeMessage(Object message) {
        if (!diagnosticsActive()) {
            return "";
        }
        if (message == null) {
            return "message_present=false";
        }
        try {
            Object chat = chatMessageOf(message);
            if (chat == null) {
                return "message_present=true; chat_present=false";
            }
            Object type = msgType != null ? Reflect.invoke(chat, msgType) : null;
            String typeText = type instanceof Integer ? String.valueOf(type) : "unknown";
            return "message_present=true; type=" + typeText;
        } catch (Throwable throwable) {
            return "message_present=true; type=unavailable; error=" + throwable.getClass().getName();
        }
    }

    /** 诊断会话是否激活；对应原实现 {@code V.a()}，默认窗口未开启。 */
    private static boolean diagnosticsActive() {
        return false;
    }

    private static String asText(Object value) {
        return value instanceof String ? (String) value : null;
    }
}