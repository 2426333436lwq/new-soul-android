package dev.local.newsoul.feature.profile;

/**
 * 插眼记录条目（原 {@code w1}）。
 *
 * <p>三个字段逐字对应：{@code a} 主页标识、{@code b} 昵称、{@code c} 记录时间戳。</p>
 */
final class ProfileBookmarkEntry {

    /** 主页标识（原 {@code w1.a}）。 */
    final String key;

    /** 昵称（原 {@code w1.b}）。 */
    final String name;

    /** 记录时间戳（原 {@code w1.c}）。 */
    final long timestamp;

    ProfileBookmarkEntry(String key, String name, long timestamp) {
        this.key = key;
        this.name = name;
        this.timestamp = timestamp;
    }
}
