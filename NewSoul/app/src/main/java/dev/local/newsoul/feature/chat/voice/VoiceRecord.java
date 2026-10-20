package dev.local.newsoul.feature.chat.voice;

import java.io.File;

/**
 * 语音库中的一条语音记录（原混淆类 {@code h3}）。
 *
 * <p>对应磁盘上的一对文件：{@code <id>.meta}（元数据）与 {@code <id>.audio}（音频数据）。
 * 字段与 {@link VoiceLibraryStore} 的序列化格式一一对应，本身不持有任何宿主类型，
 * 因此可以在任意线程读写。</p>
 */
public final class VoiceRecord {

    /** 记录标识：UUID 字符串，同时是文件名前缀。 */
    private final String id;

    /** 用户备注（已裁剪、非空、最多 120 字）。 */
    private final String remark;

    /** 语音时长，单位秒；必须大于 0。 */
    private final int durationSeconds;

    /** 创建时间戳，毫秒；用于列表倒序排序。 */
    private final long createdAtMillis;

    /** 音频文件（{@code <id>.audio}）。 */
    private final File audioFile;

    public VoiceRecord(String id, String remark, int durationSeconds, long createdAtMillis, File audioFile) {
        this.id = id;
        this.remark = remark;
        this.durationSeconds = durationSeconds;
        this.createdAtMillis = createdAtMillis;
        this.audioFile = audioFile;
    }

    public String id() {
        return id;
    }

    public String remark() {
        return remark;
    }

    public int durationSeconds() {
        return durationSeconds;
    }

    public long createdAtMillis() {
        return createdAtMillis;
    }

    public File audioFile() {
        return audioFile;
    }

    /** 返回一条仅备注不同的副本，用于「修改备注」时复用原 id/时长/时间/文件。 */
    public VoiceRecord withRemark(String newRemark) {
        return new VoiceRecord(id, newRemark, durationSeconds, createdAtMillis, audioFile);
    }
}
