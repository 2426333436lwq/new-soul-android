package dev.local.newsoul.feature.chat.repeat;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 复读条目上下文（原模块 {@code dev.local.newsoul.E}）。
 *
 * <p>每注入一个自定义长按菜单项，就同时登记一个本对象，记录该项作用于哪个会话、
 * 哪条消息，以及处理期用于校验「会话未变化」的账号与对端 id。</p>
 *
 * <p>菜单项与上下文的映射使用弱键表：菜单项由宿主持有，一旦菜单销毁即自动回收，
 * 不会因为模块保留引用而产生泄漏 —— 与原实现一致。</p>
 */
public final class RepeatEntry {

    private static final Map<Object, RepeatEntry> REGISTRY =
            Collections.synchronizedMap(new WeakHashMap<>());

    private final Object owner;
    private final Object message;
    private final String accountId;
    private final String peerId;

    private boolean handled;

    public RepeatEntry(Object owner, Object message, String accountId, String peerId) {
        this.owner = owner;
        this.message = message;
        this.accountId = accountId;
        this.peerId = peerId;
    }

    /** 登记菜单项对应的上下文。 */
    public static void register(Object menuItem, RepeatEntry entry) {
        REGISTRY.put(menuItem, entry);
    }

    /** 查询菜单项对应的上下文；不存在或已被回收时返回 {@code null}。 */
    public static RepeatEntry find(Object menuItem) {
        return REGISTRY.get(menuItem);
    }

    public Object owner() {
        return owner;
    }

    public Object message() {
        return message;
    }

    public String accountId() {
        return accountId;
    }

    public String peerId() {
        return peerId;
    }

    /** 标记为已处理；重复调用返回 {@code false}，保证同一条目只触发一次。 */
    public boolean markHandled() {
        if (handled) {
            return false;
        }
        handled = true;
        return true;
    }
}