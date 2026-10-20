package dev.local.newsoul.feature.chat.forward;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * 单次「消息转发」的会话上下文（原模块 {@code e1}）。
 *
 * <p>用户在聊天里长按消息并选择「转发」时创建本对象：记录发起时的账号、对端会话、
 * 消息类型与已复刻的消息体，并带上创建时刻。随后跳转宿主的选择会话页，
 * 用户勾选目标后再回到确认框；确认时用本对象校验「账号与会话未切换、未超时」，
 * 避免把消息转发到用户已离开的会话。</p>
 *
 * <p>有效期 10 分钟；{@link #registerSelection} 成功一次即标记为已消费，不能重复提交。</p>
 */
public final class ForwardContext {

    /** 上下文有效期（毫秒），与宿主选择会话页的停留上限一致。 */
    private static final long VALIDITY_MILLIS = 0x927c0L;

    /** 单次最多可选的转发目标数。 */
    private static final int MAX_TARGETS = 9;

    /** 仅单聊会话可作为转发目标。 */
    private static final int CHAT_TYPE_SINGLE = 0;

    private static final int TYPE_TEXT = 1;
    private static final int TYPE_IMAGE = 2;
    private static final int TYPE_IMAGES = 3;
    private static final int TYPE_AUDIO = 5;

    private final String accountId;
    private final String peerId;
    private final int messageType;
    private final Object body;
    private final long createdAt;

    private boolean consumed;

    /**
     * @param accountId   发起转发时的账号 id
     * @param peerId      发起转发时所在会话的对端 id
     * @param messageType 消息类型（文本 / 单图 / 多图 / 语音）
     * @param body        已复刻、可独立发送的消息体
     * @param createdAt   创建时刻（{@code SystemClock.elapsedRealtime()}）
     * @throws IllegalArgumentException 参数缺失或消息类型不受支持
     */
    public ForwardContext(String accountId, String peerId, int messageType, Object body, long createdAt) {
        boolean typeSupported = messageType == TYPE_TEXT || messageType == TYPE_IMAGE
                || messageType == TYPE_IMAGES || messageType == TYPE_AUDIO;
        if (accountId == null || accountId.isEmpty()
                || peerId == null || peerId.isEmpty()
                || body == null
                || !typeSupported) {
            throw new IllegalArgumentException("Invalid forward");
        }
        this.accountId = accountId;
        this.peerId = peerId;
        this.messageType = messageType;
        this.body = body;
        this.createdAt = createdAt;
    }

    /**
     * 登记一次转发提交（原模块 {@code e1.a}）。
     *
     * <p>逐项校验目标：必须为单聊、且不是发起会话本身或发起账号；目标必须是互不相同的字符串。
     * 任一项目不合法即整体失败。</p>
     *
     * @return 去重且保持顺序的目标列表；校验失败或上下文已失效时返回 {@code null}
     */
    public synchronized ArrayList<String> registerSelection(String accountId, ArrayList<String> targets, long now) {
        if (!isStillValid(accountId, now) || targets.isEmpty() || targets.size() > MAX_TARGETS) {
            return null;
        }
        LinkedHashSet<String> unique = new LinkedHashSet<>();
        for (String target : targets) {
            if (!allowsTarget(target, CHAT_TYPE_SINGLE)) {
                return null;
            }
            if (!unique.add(target)) {
                return null;
            }
        }
        this.consumed = true;
        return new ArrayList<>(unique);
    }

    /**
     * 上下文是否仍然有效（原模块 {@code e1.b}）。
     *
     * <p>要求：未被消费、账号一致、且创建后经过的时间在 {@code [0, 10 分钟]} 内
     * （时钟回拨同样视为失效）。</p>
     */
    public boolean isStillValid(String accountId, long now) {
        if (consumed || !this.accountId.equals(accountId)) {
            return false;
        }
        long age = now - createdAt;
        return age >= 0 && age <= VALIDITY_MILLIS;
    }

    /**
     * 判断某个会话能否作为转发目标（原模块 {@code e1.c}）。
     *
     * <p>仅单聊，且不能是发起会话本身或发起账号自己。</p>
     */
    public boolean allowsTarget(String candidate, int chatType) {
        if (chatType != CHAT_TYPE_SINGLE || candidate == null || candidate.isEmpty()) {
            return false;
        }
        return !accountId.equals(candidate) && !peerId.equals(candidate);
    }

    public String accountId() {
        return accountId;
    }

    public String peerId() {
        return peerId;
    }

    public int messageType() {
        return messageType;
    }

    public Object body() {
        return body;
    }
}
