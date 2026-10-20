package dev.local.newsoul.feature.chat.repeat;

/**
 * 复读上下文栈帧（原模块 {@code dev.local.newsoul.u}）。
 *
 * <p>宿主渲染长按菜单前会先调用 {@code ConversationFragment.ac(View, ImMessage, int)}，
 * 之后才可能走菜单渲染方法。此时「消息行视图 / 消息 / 会话 Fragment」都已经不在渲染方法的
 * 参数里了，模块在 {@code ac} 上挂钩，把这三者压入<b>线程私有栈</b>，
 * 供随后发生的菜单渲染按栈顶读取。</p>
 *
 * <p>实现 {@link AutoCloseable}：宿主方法返回后立即出栈，使栈深度严格跟随调用栈。
 * 每个栈帧只允许被消费一次（{@link #consume()}），避免同一次长按被重复渲染。</p>
 */
public final class RepeatContext implements AutoCloseable {

    private static final ThreadLocal<RepeatContext> STACK = new ThreadLocal<>();

    private final Object owner;
    private final Object message;
    private final Object rowView;
    private final RepeatContext previous;

    private boolean consumed;
    private boolean closed;

    public RepeatContext(Object owner, Object message, Object rowView) {
        this.owner = owner;
        this.message = message;
        this.rowView = rowView;
        this.previous = STACK.get();
        STACK.set(this);
    }

    /** 当前线程的栈顶帧；无则返回 {@code null}。 */
    public static RepeatContext current() {
        return STACK.get();
    }

    public Object owner() {
        return owner;
    }

    public Object message() {
        return message;
    }

    public Object rowView() {
        return rowView;
    }

    /** 标记为已消费；已消费或已关闭时返回 {@code false}。 */
    public boolean consume() {
        if (consumed || closed) {
            return false;
        }
        consumed = true;
        return true;
    }

    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        if (previous == null) {
            STACK.remove();
        } else {
            STACK.set(previous);
        }
    }
}