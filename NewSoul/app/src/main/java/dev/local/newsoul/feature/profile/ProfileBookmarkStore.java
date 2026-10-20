package dev.local.newsoul.feature.profile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

/**
 * 插眼记录文件存储（原 {@code x1} 的书签分支）。
 *
 * <p>原 {@code x1} 是同时承载「语音库」与「插眼」两种模式的文件存储；语音库部分已在
 * {@code feature.chat.voice.VoiceLibraryStore} 单独实现，本类只保留书签分支的逐字语义。</p>
 *
 * <p>文件位于 {@code <filesDir>/newsoul-profile-bookmarks/<sha256(账号)>.bin}，
 * 采用「魔数 + 条数 + 逐条 key/昵称/时间戳」的二进制格式，写盘走临时文件 + 原子移动。</p>
 */
final class ProfileBookmarkStore {

    /** 写盘互斥锁（原静态字段 {@code x1.b}）。 */
    static final Object LOCK = new Object();

    /** 文件魔数（原 {@code 0x4e535031}）。 */
    private static final int MAGIC = 0x4e535031;

    /** 最大记录数（原 {@code 0x3e8}）。 */
    private static final int MAX_ENTRIES = 1000;

    /** 主页标识最大长度（原 {@code 0x200}）。 */
    private static final int MAX_KEY_LENGTH = 0x200;

    /** 昵称最大长度（原 {@code 0x78}）。 */
    private static final int MAX_NAME_LENGTH = 0x78;

    /** 存储目录名（原字面量）。 */
    private static final String DIRECTORY = "newsoul-profile-bookmarks";

    private final File file;

    ProfileBookmarkStore(File filesDir, String account) {
        if (account == null || account.trim().isEmpty()) {
            throw new IllegalArgumentException("请先登录 Soul");
        }
        this.file = new File(new File(filesDir, DIRECTORY), sha256(account) + ".bin");
    }

    /**
     * 校验主页标识（原静态方法 {@code x1.j}）：非空、trim 非空、长度不超过 {@code 0x200} 且无首尾空白。
     */
    static void requireKey(String key) {
        if (key != null
                && !key.trim().isEmpty()
                && key.length() <= MAX_KEY_LENGTH
                && key.equals(key.trim())) {
            return;
        }
        throw new IllegalArgumentException("主页标识不可用");
    }

    /** 读取全部插眼记录（原 {@code x1.h}）；文件不存在返回空列表。 */
    ArrayList<ProfileBookmarkEntry> readAll() throws IOException {
        ArrayList<ProfileBookmarkEntry> entries = new ArrayList<>();
        if (!file.exists()) {
            return entries;
        }
        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            if (input.readInt() != MAGIC) {
                throw new IOException("插眼记录格式异常");
            }
            int count = input.readInt();
            if (count < 0 || count > MAX_ENTRIES) {
                throw new IOException("插眼记录数量异常");
            }
            HashSet<String> seen = new HashSet<>();
            for (int index = 0; index < count; index++) {
                String key = input.readUTF();
                String name = input.readUTF();
                long timestamp = input.readLong();
                try {
                    requireKey(key);
                } catch (IllegalArgumentException error) {
                    throw new IOException("插眼记录损坏", error);
                }
                boolean damaged = name.trim().isEmpty()
                        || name.length() > MAX_NAME_LENGTH
                        || timestamp < 0L
                        || !seen.add(key);
                if (damaged) {
                    throw new IOException("插眼记录损坏");
                }
                entries.add(new ProfileBookmarkEntry(key, name, timestamp));
            }
            if (input.read() != -1) {
                throw new IOException("插眼记录格式异常");
            }
        }
        return entries;
    }

    /** 新增或更新插眼记录（原 {@code x1.f}）；已达上限时抛 {@code IOException}。 */
    void addOrUpdate(String key, String name) throws IOException {
        requireKey(key);
        String trimmed = name.trim();
        if (trimmed.isEmpty() || trimmed.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("昵称不可用");
        }
        synchronized (LOCK) {
            ArrayList<ProfileBookmarkEntry> entries = readAll();
            for (int index = 0; index < entries.size(); index++) {
                ProfileBookmarkEntry entry = entries.get(index);
                if (entry.key.equals(key)) {
                    entries.set(index, new ProfileBookmarkEntry(key, trimmed, entry.timestamp));
                    writeAll(entries);
                    return;
                }
            }
            if (entries.size() < MAX_ENTRIES) {
                entries.add(0, new ProfileBookmarkEntry(key, trimmed, System.currentTimeMillis()));
                writeAll(entries);
                return;
            }
            throw new IOException("插眼列表已满，请先移除部分记录");
        }
    }

    /** 按主页标识移除插眼记录（原 {@code x1.i}），没删到则不写盘。 */
    void remove(String key) throws IOException {
        requireKey(key);
        synchronized (LOCK) {
            ArrayList<ProfileBookmarkEntry> entries = readAll();
            boolean removed = entries.removeIf(entry -> entry.key.equals(key));
            if (removed) {
                writeAll(entries);
            }
        }
    }

    /** 原子写盘（原 {@code x1.l}）：临时文件 + {@code fsync} + 原子移动 + 清理临时文件。 */
    void writeAll(ArrayList<ProfileBookmarkEntry> entries) throws IOException {
        File parent = file.getParentFile();
        if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException("无法创建插眼列表");
        }
        File temporary = new File(parent, file.getName() + ".tmp");
        try (FileOutputStream output = new FileOutputStream(temporary);
                DataOutputStream data = new DataOutputStream(output)) {
            data.writeInt(MAGIC);
            data.writeInt(entries.size());
            for (ProfileBookmarkEntry entry : entries) {
                data.writeUTF(entry.key);
                data.writeUTF(entry.name);
                data.writeLong(entry.timestamp);
            }
            data.flush();
            output.getFD().sync();
        }
        Files.move(temporary.toPath(), file.toPath(),
                StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        Files.deleteIfExists(temporary.toPath());
    }

    /** 账号文件名散列（原 {@code x1} 构造器内联的 SHA-256 十六进制串）。 */
    private static String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                builder.append(String.format(Locale.ROOT, "%02x", b & 0xFF));
            }
            return builder.toString();
        } catch (Exception error) {
            throw new RuntimeException("SHA-256 unavailable", error);
        }
    }
}
