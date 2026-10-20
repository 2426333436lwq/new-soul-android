package dev.local.newsoul.feature.chat.voice;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 语音库元数据文件名过滤器（原混淆类 {@code f3}）。
 *
 * <p>仅保留以 {@code .meta} 结尾的文件，用于 {@link VoiceLibraryStore#list()} 枚举记录。</p>
 */
public final class MetaFileNameFilter implements FilenameFilter {

    /** 元数据文件后缀。 */
    static final String META_SUFFIX = ".meta";

    @Override
    public boolean accept(File dir, String name) {
        return name != null && name.endsWith(META_SUFFIX);
    }
}
