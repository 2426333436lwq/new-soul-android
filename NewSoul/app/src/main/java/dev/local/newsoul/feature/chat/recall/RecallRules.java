package dev.local.newsoul.feature.chat.recall;

import android.content.Context;

import java.lang.reflect.Method;

import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.MessageValues;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;

/**
 * 撤回保留判定（原 {@code Q0.S}）。
 *
 * <p>宿主把「对方撤回了一条消息」表达为列表里的撤回通知项。判定的目标是：
 * 这条通知是否对应一条<b>仍能取到原文</b>的消息 —— 只有能取到原文才值得保留。</p>
 *
 * <p>判定链路（会话标识必须与发送方/接收方吻合，且同会话内存在同 ID 的原消息）：</p>
 * <ol>
 *   <li>通知本身必须属于当前私聊会话（{@link MessageValues#matchesRecallSession}）；</li>
 *   <li>类型必须是撤回通知，且不是「闪照」快照；</li>
 *   <li>按发送方取会话，再从会话缓存按消息 ID 找回原消息；缓存没有则走全局消息查找；</li>
 *   <li>原消息必须可读、有内容，且收发方与通知一致；</li>
 *   <li>原消息的消息 ID 必须与通知的消息 ID <b>不同</b>（相同时说明拿到的是通知自身）。</li>
 * </ol>
 */
final class RecallRules {

    /** 收到撤回通知时打印的日志前缀。 */
    static final String RETAINED_PRIVATE_LOG = "recall retained; source=private; type=";

    private final MessageValues messages;
    private final Method conversationOf;
    private final Method messageOf;
    private final Method messageById;

    private RecallRules(MessageValues messages, Method conversationOf, Method messageOf,
                        Method messageById) {
        this.messages = messages;
        this.conversationOf = conversationOf;
        this.messageOf = messageOf;
        this.messageById = messageById;
    }

    static RecallRules resolve(ModuleContext context, MessageValues messages) {
        Class<?> chatManager = context.loadHostClass(HostClasses.IMLIB_CHAT_MANAGER);
        Class<?> conversation = context.loadHostClass(HostClasses.IMLIB_CONVERSATION);
        return new RecallRules(
                messages,
                Reflect.findCompatibleMethod(chatManager, "F", String.class),
                Reflect.findCompatibleMethod(conversation, "getMessage", String.class),
                Reflect.findCompatibleMethod(chatManager, "K", String.class));
    }

    boolean isReady() {
        return messages.isReady() && conversationOf != null && messageOf != null && messageById != null;
    }

    /**
     * 判断某条撤回通知是否应被保留（从撤回列表中移除）。
     *
     * @param chatManager 宿主 {@code ChatManager} 实例
     * @param item        列表中的撤回通知项
     */
    boolean shouldRetain(Context context, Object chatManager, Object item) {
        if (!messages.matchesRecallSession(item)) {
            return false;
        }
        if (messages.msgTypeOf(item) != MessageValues.MSG_TYPE_RECALL) {
            return false;
        }
        if ("1".equals(messages.transExtOf(item, "isSnap"))) {
            return false;
        }
        String msgId = messages.msgIdOf(item);
        Object original = findOriginal(chatManager, messages.fromOf(item), msgId);
        if (!messages.matchesRecallSession(original) || !messages.hasContent(original)) {
            return false;
        }
        if (!equals(messages.fromOf(item), messages.fromOf(original))) {
            return false;
        }
        if (!equals(messages.toOf(item), messages.toOf(original))) {
            return false;
        }
        // 消息 ID 相同说明取到的仍是通知自身，而非被撤回的原消息。
        if (msgId == null || msgId.equals(messages.msgIdOf(original))) {
            return false;
        }
        if (!RecallStore.remember(context, messages.recallFingerprint(original))) {
            return false;
        }
        Logger.i(RETAINED_PRIVATE_LOG + messages.msgTypeOf(original));
        return true;
    }

    /** 会话缓存优先，缺失时回退到全局消息查找（原 {@code ChatManager.F → Conversation.getMessage → ChatManager.K}）。 */
    private Object findOriginal(Object chatManager, String from, String msgId) {
        if (msgId == null) {
            return null;
        }
        Object conversation = Reflect.invoke(chatManager, conversationOf, from);
        Object message = conversation == null ? null : Reflect.invoke(conversation, messageOf, msgId);
        if (message == null) {
            message = Reflect.invoke(chatManager, messageById, msgId);
        }
        return message;
    }

    private static boolean equals(String left, String right) {
        return left == null ? right == null : left.equals(right);
    }
}