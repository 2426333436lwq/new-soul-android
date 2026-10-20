package dev.local.newsoul.feature.chat.repeat;

import dev.local.newsoul.feature.chat.HostAccess;

/**
 * 复读归属校验（原模块 {@code F.f}）。
 *
 * <p>决定一条消息能否被复读 / 转发 / 存入语音库。校验分三层：</p>
 * <ol>
 *   <li><b>会话归属</b>：消息的收发双方必须与「当前账号 + 对端 id」双向匹配；</li>
 *   <li><b>载体存在</b>：必须携带 {@code ChatMessage}；</li>
 *   <li><b>类型可重建</b>：非匿名、非阅后即焚（{@code getSnapChat() == 0} 且 {@code isSnap} 扩展未标记），
 *       且消息体类型属于文本 / 单图 / 多图 / 语音。</li>
 * </ol>
 *
 * <p>任一环节不满足即返回 {@code false}；反射调用失败时异常向上传播，交由调用方处理
 * —— 与原实现一致，不在此处吞掉异常。</p>
 */
public final class RepeatEligibility {

    private static final int ENVELOPE_TEXT = 1;

    /** 会话快照扩展键：标记为阅后即焚。 */
    private static final String EXT_IS_SNAP = "isSnap";
    private static final String SNAP_ZERO = "0";
    private static final String SNAP_FALSE = "false";

    /** 可重建的消息体类型：文本 / 单图 / 多图 / 语音。 */
    private static final int[] REPEATABLE_TYPES = {1, 2, 3, 5};

    private RepeatEligibility() {
    }

    /**
     * 判断消息是否可复读。
     *
     * @param message   宿主消息对象（{@code ImMessage}）
     * @param accountId 当前账号 id
     * @param peerId    对端会话 id
     */
    public static boolean isEligible(Object message, String accountId, String peerId)
            throws ReflectiveOperationException {
        if (message == null) {
            return false;
        }
        String from = (String) HostAccess.call(message, "getFrom");
        String to = (String) HostAccess.call(message, "getTo");
        boolean ownershipMatches = accountId != null && !accountId.isEmpty()
                && peerId != null && !peerId.isEmpty()
                && ((accountId.equals(from) && peerId.equals(to))
                || (peerId.equals(from) && accountId.equals(to)));
        if (!ownershipMatches) {
            return false;
        }
        Object chat = HostAccess.call(message, "getChatMessage");
        if (chat == null) {
            return false;
        }
        int envelopeType = ((Integer) HostAccess.call(message, "getMsgType")).intValue();
        int bodyType = ((Integer) HostAccess.call(chat, "getMsgType")).intValue();
        int snap = ((Integer) HostAccess.call(chat, "getSnapChat")).intValue();
        String snapMarked = (String) chat.getClass()
                .getMethod("getStringTransExt", String.class)
                .invoke(chat, EXT_IS_SNAP);
        boolean anonymous = ((Boolean) HostAccess.call(message, "isAnonymityChat")).booleanValue();
        boolean snapMarkedReal = snapMarked != null && !snapMarked.isEmpty()
                && !SNAP_ZERO.equals(snapMarked) && !SNAP_FALSE.equals(snapMarked);
        if (envelopeType != ENVELOPE_TEXT || anonymous || snap != 0 || snapMarkedReal) {
            return false;
        }
        for (int type : REPEATABLE_TYPES) {
            if (bodyType == type) {
                return true;
            }
        }
        return false;
    }
}