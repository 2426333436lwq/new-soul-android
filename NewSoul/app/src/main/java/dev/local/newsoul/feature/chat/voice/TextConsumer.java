package dev.local.newsoul.feature.chat.voice;

/**
 * 文本消费回调（原接口 {@code P2}）。
 *
 * <p>用于把「对话框中的文本输入结果」回传给业务方，例如保存备注、修改备注。</p>
 */
@FunctionalInterface
public interface TextConsumer {

    /** 接收一段文本。 */
    void accept(String text);
}
