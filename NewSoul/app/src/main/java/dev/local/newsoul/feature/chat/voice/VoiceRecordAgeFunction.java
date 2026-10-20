package dev.local.newsoul.feature.chat.voice;

import java.util.function.ToLongFunction;

/**
 * 按创建时间戳取值的函数（原混淆类 {@code g3}）。
 *
 * <p>用于 {@link VoiceLibraryStore#list()} 按 {@link VoiceRecord#createdAtMillis()} 倒序排序。</p>
 */
public final class VoiceRecordAgeFunction implements ToLongFunction<VoiceRecord> {

    @Override
    public long applyAsLong(VoiceRecord record) {
        return record.createdAtMillis();
    }
}
