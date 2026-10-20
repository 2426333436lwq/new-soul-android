package dev.local.newsoul.feature.chat.voice;

/**
 * 无参回调（原接口 {@code c3}）。
 *
 * <p>原模块把大量「点击后执行」的回调抽象为带 {@code int} 分派字段的合成类；重构后按职责
 * 拆分为具名类，统一通过本接口表达「一段可执行的动作」。</p>
 */
@FunctionalInterface
public interface RunnableAction {

    /** 执行动作。 */
    void run();
}
