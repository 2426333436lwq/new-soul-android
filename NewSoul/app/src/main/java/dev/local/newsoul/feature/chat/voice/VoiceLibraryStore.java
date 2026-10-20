package dev.local.newsoul.feature.chat.voice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * 按账号隔离的本地语音库存储（原混淆类 {@code x1} 的语音库分支，构造时 {@code mode == 1}）。
 *
 * <p>目录布局：{@code <filesDir>/newsoul-voice-library/<accountSha256>/}，其中：
 * 每条记录由 {@code <uuid>.meta}（元数据）与 {@code <uuid>.audio}（音频）组成。
 * 写入时先写 {@code <uuid>.part} 再原子改名，避免半成品被读取。</p>
 *
 * <p>容量约束（原实现硬编码，此处提取为命名常量）：
 * 单条音频不超过 20 MB、库总量不超过 500 MB、记录数不超过 200 条、备注不超过 120 字。</p>
 *
 * <p>并发：{@link #store}/{@link #list}/{@link #read} 使用实例级 {@code synchronized}，
 * 与原实例方法 {@code declared-synchronized} 语义一致；{@link #writeMetadata} 与原实现一致不加锁。</p>
 */
public final class VoiceLibraryStore {

    /** 存储根目录名。 */
    private static final String DIRECTORY_NAME = "newsoul-voice-library";

    /** 元数据文件后缀（与 {@link MetaFileNameFilter} 保持一致）。 */
    static final String META_SUFFIX = ".meta";

    /** 音频文件后缀。 */
    private static final String AUDIO_SUFFIX = ".audio";

    /** 写入中的音频临时后缀。 */
    private static final String PART_SUFFIX = ".part";

    /** 元数据临时文件后缀。 */
    private static final String META_TEMP_SUFFIX = ".meta.tmp";

    /** 删除中的元数据暂存后缀（原删除流程的 {@code ".deleted"}）。 */
    private static final String DELETED_SUFFIX = ".deleted";

    /** 元数据文件魔数 {@code 0x4e535631}（"NSV1"）。 */
    private static final int RECORD_MAGIC = 0x4e535631;

    /** 拷贝缓冲区大小：16 KiB。 */
    private static final int COPY_BUFFER_BYTES = 0x4000;

    /** 单条音频体积上限：20 MB。 */
    private static final int MAX_AUDIO_BYTES = 0x1400000;

    /** 语音库总体积上限：500 MB。 */
    private static final long MAX_LIBRARY_BYTES = 0x1f400000L;

    /** 语音库记录数上限：200 条。 */
    private static final int MAX_RECORD_COUNT = 0xc8;

    /** 备注最大长度：120 字。 */
    private static final int REMARK_MAX_LENGTH = 0x78;

    /** 音频头部探测字节数。 */
    private static final int EXTENSION_PROBE_BYTES = 0x10;

    /** 记录 id 的合法格式：小写 UUID。 */
    private static final String UUID_PATTERN = "[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}";

    /** 当前账号的语音库目录。 */
    private final File directory;

    /**
     * @param filesDir  宿主应用私有目录（通常是 {@code Context#getFilesDir()}）
     * @param accountId 当前账号 id；为空或 {@code null} 时抛出异常
     * @throws IOException 目录无法创建（{@code "Cannot create library"}）
     */
    public VoiceLibraryStore(File filesDir, String accountId) throws IOException {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("Missing account");
        }
        String hashed = sha256Hex(accountId);
        File target = new File(filesDir, DIRECTORY_NAME + "/" + hashed);
        if (!target.isDirectory() && !target.mkdirs()) {
            throw new IOException("Cannot create library");
        }
        this.directory = target;
    }

    /** 计算账号 id 的 SHA-256 十六进制表示（小写）。 */
    private static String sha256Hex(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                builder.append(String.format(Locale.ROOT, "%02x", b & 0xff));
            }
            return builder.toString();
        } catch (Exception error) {
            throw new IllegalStateException("SHA-256 unavailable", error);
        }
    }

    /**
     * 限量拷贝音频流：累计超过 {@value #MAX_AUDIO_BYTES} 字节（20 MB）抛「语音文件超过 20 MB」，
     * 总长度为 0 抛「语音文件为空」（原静态方法 {@code x1.b}）。
     */
    static void copyBounded(InputStream input, FileOutputStream output) throws IOException {
        byte[] buffer = new byte[COPY_BUFFER_BYTES];
        long total = 0L;
        int read;
        while ((read = input.read(buffer)) != -1) {
            total += read;
            if (total > MAX_AUDIO_BYTES) {
                throw new IOException("语音文件超过 20 MB");
            }
            output.write(buffer, 0, read);
        }
        if (total == 0L) {
            throw new IOException("语音文件为空");
        }
    }

    /** 按音频头部魔数推断扩展名，无法识别时返回 {@code .audio}（原静态方法 {@code x1.c}）。 */
    static String detectExtension(File file) throws IOException {
        byte[] head = new byte[EXTENSION_PROBE_BYTES];
        int read;
        try (InputStream input = new FileInputStream(file)) {
            read = input.read(head);
        }
        int length = Math.max(0, read);
        String prefix = new String(head, 0, length, StandardCharsets.ISO_8859_1);
        if (prefix.startsWith("#!AMR")) {
            return ".amr";
        }
        if (prefix.startsWith("RIFF")) {
            return ".wav";
        }
        if (prefix.startsWith("OggS")) {
            return ".ogg";
        }
        if (length >= 8 && prefix.substring(4, 8).equals("ftyp")) {
            return ".m4a";
        }
        if (prefix.startsWith("fLaC")) {
            return ".flac";
        }
        if (length >= 2 && (head[0] & 0xff) == 0xff && (head[1] & 0xf6) == 0xf0) {
            return ".aac";
        }
        if (prefix.startsWith("ID3")
                || (length >= 2 && (head[0] & 0xff) == 0xff && (head[1] & 0xe0) == 0xe0)) {
            return ".mp3";
        }
        return ".audio";
    }

    /** 校验并裁剪备注（原静态方法 {@code x1.e}）。 */
    static String requireRemark(String remark) {
        String trimmed = remark == null ? "" : remark.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("请填写备注");
        }
        if (trimmed.length() > REMARK_MAX_LENGTH) {
            throw new IllegalArgumentException("备注最多 120 字");
        }
        return trimmed;
    }

    /**
     * 保存一条语音（原实例方法 {@code x1.a}）：先校验备注与时长、检查容量，再落盘音频与元数据。
     *
     * @throws IllegalArgumentException 备注非法或时长不大于 0（{@code "Invalid duration"}）
     * @throws IOException              容量已满（{@code "语音库已满，请先删除部分语音"}）或 IO 失败
     */
    synchronized void store(InputStream input, String remark, int durationSeconds) throws IOException {
        String normalizedRemark = requireRemark(remark);
        if (durationSeconds <= 0) {
            throw new IllegalArgumentException("Invalid duration");
        }
        List<VoiceRecord> existing = list();
        long totalBytes = 0L;
        for (VoiceRecord record : existing) {
            totalBytes += record.audioFile().length();
        }
        if (existing.size() >= MAX_RECORD_COUNT || totalBytes + MAX_AUDIO_BYTES > MAX_LIBRARY_BYTES) {
            throw new IOException("语音库已满，请先删除部分语音");
        }
        String id = UUID.randomUUID().toString();
        File audioFile = new File(directory, id + AUDIO_SUFFIX);
        File partFile = new File(directory, id + PART_SUFFIX);
        VoiceRecord record = new VoiceRecord(id, normalizedRemark, durationSeconds,
                System.currentTimeMillis(), audioFile);
        try {
            try (FileOutputStream output = new FileOutputStream(partFile)) {
                copyBounded(input, output);
                output.getFD().sync();
            }
            Files.move(partFile.toPath(), audioFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
            writeMetadata(record);
        } catch (IOException | RuntimeException error) {
            Files.deleteIfExists(partFile.toPath());
            Files.deleteIfExists(audioFile.toPath());
            throw error;
        }
    }

    /**
     * 列出全部语音记录，按创建时间倒序（原实例方法 {@code x1.d}）。
     *
     * @throws IOException 目录不可读（{@code "Cannot read library"}）或任一记录损坏
     *                     （{@code "语音库记录损坏，请保留数据后排查"}）
     */
    synchronized List<VoiceRecord> list() throws IOException {
        File[] files = directory.listFiles(new MetaFileNameFilter());
        if (files == null) {
            throw new IOException("Cannot read library");
        }
        List<VoiceRecord> records = new ArrayList<>(files.length);
        for (File file : files) {
            String name = file.getName();
            String id = name.substring(0, name.length() - META_SUFFIX.length());
            try {
                records.add(read(id));
            } catch (IllegalArgumentException | IOException error) {
                throw new IOException("语音库记录损坏，请保留数据后排查", error);
            }
        }
        records.sort(Comparator.comparingLong(new VoiceRecordAgeFunction()).reversed());
        return records;
    }

    /**
     * 按 id 读取一条记录（原实例方法 {@code x1.g}）。
     *
     * @throws IllegalArgumentException id 非法（{@code "Invalid voice id"}）
     * @throws IOException              魔数不符（{@code "Unknown voice record"}）或音频缺失/超限
     *                                  （{@code "Missing voice file"}）
     */
    synchronized VoiceRecord read(String id) throws IOException {
        if (id == null || !id.matches(UUID_PATTERN)) {
            throw new IllegalArgumentException("Invalid voice id");
        }
        File metaFile = new File(directory, id + META_SUFFIX);
        String remark;
        int durationSeconds;
        long createdAtMillis;
        try (DataInputStream input = new DataInputStream(new FileInputStream(metaFile))) {
            if (input.readInt() != RECORD_MAGIC) {
                throw new IOException("Unknown voice record");
            }
            remark = input.readUTF();
            durationSeconds = input.readInt();
            createdAtMillis = input.readLong();
        }
        File audioFile = new File(directory, id + AUDIO_SUFFIX);
        if (durationSeconds <= 0
                || remark.length() > REMARK_MAX_LENGTH
                || !audioFile.isFile()
                || audioFile.length() <= 0
                || audioFile.length() > MAX_AUDIO_BYTES) {
            throw new IOException("Missing voice file");
        }
        return new VoiceRecord(id, remark, durationSeconds, createdAtMillis, audioFile);
    }

    /**
     * 原子写入记录元数据（原实例方法 {@code x1.k}）：先写 {@code .meta.tmp} 并 fsync，
     * 再原子改名覆盖 {@code .meta}；任一步失败都清理临时文件。
     */
    void writeMetadata(VoiceRecord record) throws IOException {
        File tempFile = new File(directory, record.id() + META_TEMP_SUFFIX);
        File metaFile = new File(directory, record.id() + META_SUFFIX);
        try {
            try (FileOutputStream fileOutput = new FileOutputStream(tempFile)) {
                DataOutputStream output = new DataOutputStream(fileOutput);
                output.writeInt(RECORD_MAGIC);
                output.writeUTF(record.remark());
                output.writeInt(record.durationSeconds());
                output.writeLong(record.createdAtMillis());
                output.flush();
                fileOutput.getFD().sync();
            }
            Files.move(tempFile.toPath(), metaFile.toPath(),
                    StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(tempFile.toPath());
        } catch (IOException | RuntimeException error) {
            Files.deleteIfExists(tempFile.toPath());
            throw error;
        }
    }

    /**
     * 删除一条语音（原删除流程：{@code Q2} 分支 3 内联实现）。
     *
     * <p>先把元数据 {@code <id>.meta} 原子改名为 {@code <id>.deleted}，再删除音频文件；
     * 音频删除失败时把暂存的元数据移回原位并重新抛出异常，保证不会出现「元数据在、音频丢」的中间态。</p>
     *
     * @throws IllegalArgumentException id 非法（{@code "Invalid voice id"}）
     * @throws IOException              记录不存在或删除失败
     */
    synchronized void delete(String id) throws IOException {
        VoiceRecord record = read(id);
        File metaFile = new File(directory, id + META_SUFFIX);
        File deletedFile = new File(directory, id + DELETED_SUFFIX);
        Files.move(metaFile.toPath(), deletedFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
        try {
            Files.delete(record.audioFile().toPath());
            Files.deleteIfExists(deletedFile.toPath());
        } catch (IOException error) {
            Files.move(deletedFile.toPath(), metaFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
            throw error;
        }
    }
}
