package dev.local.newsoul.feature.profile.disguise;

import java.lang.reflect.Method;

/**
 * 单个计数文本的伪装绑定状态（原 {@code o0}）。
 *
 * <p>在视图绑定时快照原文与原始数值，之后每次刷新都基于该快照按当前设置为视图重算文本，
 * 因此设置变更或页面重绑都不会叠加、漂移。字段语义：</p>
 * <ul>
 *   <li>{@link #originalValue()} —— 绑定时刻的真实计数（{@code delta} 模式下的基数）</li>
 *   <li>{@link #settingPrefix()} —— 该文本对应的设置键前缀</li>
 *   <li>{@link #originalText()} —— 绑定时刻的原始文案（无锚点时也作回退显示）</li>
 *   <li>{@link #formatter()} —— 计数格式化方法（静态 {@code (int)CharSequence}），可为 {@code null}</li>
 *   <li>{@link #anchor()} —— 需要被替换的数字片段（如注册天数原文里的天数），无则为 {@code null}</li>
 * </ul>
 */
final class DisguiseBinding {

    private final int originalValue;
    private final String settingPrefix;
    private final CharSequence originalText;
    private final Method formatter;
    private final String anchor;

    DisguiseBinding(int originalValue, String settingPrefix, CharSequence originalText,
                    Method formatter, String anchor) {
        this.originalValue = originalValue;
        this.settingPrefix = settingPrefix;
        this.originalText = originalText;
        this.formatter = formatter;
        this.anchor = anchor;
    }

    int originalValue() {
        return originalValue;
    }

    String settingPrefix() {
        return settingPrefix;
    }

    CharSequence originalText() {
        return originalText;
    }

    Method formatter() {
        return formatter;
    }

    String anchor() {
        return anchor;
    }
}
