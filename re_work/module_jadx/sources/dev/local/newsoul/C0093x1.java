package dev.local.newsoul;

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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: renamed from: dev.local.newsoul.x1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0093x1 {
    public static final Object b = new Object();
    public final File a;

    public C0093x1(File file, String str, int i) throws IOException {
        switch (i) {
            case 1:
                if (str == null || str.isEmpty()) {
                    throw new IllegalArgumentException("Missing account");
                }
                byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (byte b2 : bArrDigest) {
                    sb.append(String.format(Locale.ROOT, "%02x", Integer.valueOf(b2 & 255)));
                }
                File file2 = new File(file, "newsoul-voice-library/".concat(String.valueOf(sb)));
                this.a = file2;
                if (!file2.isDirectory() && !file2.mkdirs()) {
                    throw new IOException("Cannot create library");
                }
                return;
            default:
                if (str == null || str.trim().isEmpty()) {
                    throw new IllegalArgumentException("请先登录 Soul");
                }
                byte[] bArrDigest2 = MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb2 = new StringBuilder();
                for (byte b3 : bArrDigest2) {
                    sb2.append(String.format(Locale.ROOT, "%02x", Integer.valueOf(b3 & 255)));
                }
                this.a = new File(new File(file, "newsoul-profile-bookmarks"), String.valueOf(sb2).concat(".bin"));
                return;
        }
    }

    public static void b(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[16384];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                if (j == 0) {
                    throw new IOException("语音文件为空");
                }
                return;
            } else {
                j += (long) i;
                if (j > 20971520) {
                    throw new IOException("语音文件超过 20 MB");
                }
                fileOutputStream.write(bArr, 0, i);
            }
        }
    }

    public static String c(File file) throws IOException {
        byte[] bArr = new byte[16];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            int i = fileInputStream.read(bArr);
            fileInputStream.close();
            String str = new String(bArr, 0, Math.max(0, i), StandardCharsets.ISO_8859_1);
            if (str.startsWith("#!AMR")) {
                return ".amr";
            }
            if (str.startsWith("RIFF")) {
                return ".wav";
            }
            if (str.startsWith("OggS")) {
                return ".ogg";
            }
            if (i >= 8 && str.substring(4, 8).equals("ftyp")) {
                return ".m4a";
            }
            if (str.startsWith("fLaC")) {
                return ".flac";
            }
            if (i >= 2 && (bArr[0] & 255) == 255 && (bArr[1] & 246) == 240) {
                return ".aac";
            }
            if (str.startsWith("ID3")) {
                return ".mp3";
            }
            return (i >= 2 && (bArr[0] & 255) == 255 && (bArr[1] & 224) == 224) ? ".mp3" : ".audio";
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String e(String str) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.isEmpty()) {
            throw new IllegalArgumentException("请填写备注");
        }
        if (strTrim.length() <= 120) {
            return strTrim;
        }
        throw new IllegalArgumentException("备注最多 120 字");
    }

    public static void j(String str) {
        if (str == null || str.trim().isEmpty() || str.length() > 512 || !str.equals(str.trim())) {
            throw new IllegalArgumentException("主页标识不可用");
        }
    }

    public synchronized void a(FileInputStream fileInputStream, String str, int i) {
        try {
            String strE = e(str);
            if (i <= 0) {
                throw new IllegalArgumentException("Invalid duration");
            }
            ArrayList arrayListD = d();
            Iterator it = arrayListD.iterator();
            long length = 0;
            while (it.hasNext()) {
                length += ((h3) it.next()).e.length();
            }
            if (arrayListD.size() >= 200 || length + 20971520 > 524288000) {
                throw new IOException("语音库已满，请先删除部分语音");
            }
            String string = UUID.randomUUID().toString();
            File file = new File(this.a, string + ".audio");
            File file2 = new File(this.a, string + ".part");
            h3 h3Var = new h3(string, strE, i, System.currentTimeMillis(), file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    b(fileInputStream, fileOutputStream);
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    Files.move(file2.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE);
                    k(h3Var);
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Files.deleteIfExists(file2.toPath());
                Files.deleteIfExists(file.toPath());
                throw th3;
            }
        } catch (Throwable th4) {
            throw th4;
        }
    }

    public synchronized ArrayList d() {
        ArrayList arrayList;
        File[] fileArrListFiles = this.a.listFiles(new f3());
        if (fileArrListFiles == null) {
            throw new IOException("Cannot read library");
        }
        arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            try {
                arrayList.add(g(file.getName().substring(0, file.getName().length() - 5)));
            } catch (IOException | IllegalArgumentException e) {
                throw new IOException("语音库记录损坏，请保留数据后排查", e);
            }
        }
        arrayList.sort(Comparator.comparingLong(new g3()).reversed());
        return arrayList;
    }

    public void f(String str, String str2) {
        j(str);
        String strTrim = str2.trim();
        if (strTrim.isEmpty() || strTrim.length() > 120) {
            throw new IllegalArgumentException("昵称不可用");
        }
        synchronized (b) {
            try {
                ArrayList arrayListH = h();
                for (int i = 0; i < arrayListH.size(); i++) {
                    if (((C0089w1) arrayListH.get(i)).a.equals(str)) {
                        arrayListH.set(i, new C0089w1(str, strTrim, ((C0089w1) arrayListH.get(i)).c));
                        l(arrayListH);
                        return;
                    }
                }
                if (arrayListH.size() >= 1000) {
                    throw new IOException("插眼列表已满，请先移除部分记录");
                }
                arrayListH.add(0, new C0089w1(str, strTrim, System.currentTimeMillis()));
                l(arrayListH);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized h3 g(String str) {
        h3 h3Var;
        if (str != null) {
            if (str.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}")) {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(new File(this.a, str.concat(".meta"))));
                try {
                    if (dataInputStream.readInt() != 1314084401) {
                        throw new IOException("Unknown voice record");
                    }
                    String utf = dataInputStream.readUTF();
                    int i = dataInputStream.readInt();
                    long j = dataInputStream.readLong();
                    File file = new File(this.a, str.concat(".audio"));
                    if (i <= 0 || utf.length() > 120 || !file.isFile() || file.length() <= 0 || file.length() > 20971520) {
                        throw new IOException("Missing voice file");
                    }
                    h3Var = new h3(str, utf, i, j, file);
                    dataInputStream.close();
                } catch (Throwable th) {
                    try {
                        dataInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        }
        throw new IllegalArgumentException("Invalid voice id");
        return h3Var;
    }

    public ArrayList h() throws IOException {
        ArrayList arrayList = new ArrayList();
        File file = this.a;
        if (!file.exists()) {
            return arrayList;
        }
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            if (dataInputStream.readInt() != 1314082865) {
                throw new IOException("插眼记录格式异常");
            }
            int i = dataInputStream.readInt();
            if (i < 0 || i > 1000) {
                throw new IOException("插眼记录数量异常");
            }
            HashSet hashSet = new HashSet();
            for (int i2 = 0; i2 < i; i2++) {
                String utf = dataInputStream.readUTF();
                String utf2 = dataInputStream.readUTF();
                long j = dataInputStream.readLong();
                try {
                    j(utf);
                    if (utf2.trim().isEmpty() || utf2.length() > 120 || j < 0 || !hashSet.add(utf)) {
                        throw new IOException("插眼记录损坏");
                    }
                    arrayList.add(new C0089w1(utf, utf2, j));
                } catch (IllegalArgumentException e) {
                    throw new IOException("插眼记录损坏", e);
                }
            }
            if (dataInputStream.read() != -1) {
                throw new IOException("插眼记录格式异常");
            }
            dataInputStream.close();
            return arrayList;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void i(String str) {
        j(str);
        synchronized (b) {
            ArrayList arrayListH = h();
            if (arrayListH.removeIf(new C0087w(1, str))) {
                l(arrayListH);
            }
        }
    }

    public void k(h3 h3Var) throws IOException {
        File file = this.a;
        StringBuilder sb = new StringBuilder();
        String str = h3Var.a;
        sb.append(str);
        sb.append(".meta.tmp");
        File file2 = new File(file, sb.toString());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                try {
                    dataOutputStream.writeInt(1314084401);
                    dataOutputStream.writeUTF(h3Var.b);
                    dataOutputStream.writeInt(h3Var.c);
                    dataOutputStream.writeLong(h3Var.d);
                    dataOutputStream.flush();
                    fileOutputStream.getFD().sync();
                    dataOutputStream.close();
                    fileOutputStream.close();
                    Files.move(file2.toPath(), new File(file, str + ".meta").toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                    Files.deleteIfExists(file2.toPath());
                } catch (Throwable th) {
                    try {
                        dataOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (Throwable th5) {
            Files.deleteIfExists(file2.toPath());
            throw th5;
        }
    }

    public void l(ArrayList arrayList) throws IOException {
        File file = this.a;
        File parentFile = file.getParentFile();
        if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
            throw new IOException("无法创建插眼列表");
        }
        File file2 = new File(parentFile, file.getName() + ".tmp");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                try {
                    dataOutputStream.writeInt(1314082865);
                    dataOutputStream.writeInt(arrayList.size());
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        C0089w1 c0089w1 = (C0089w1) it.next();
                        dataOutputStream.writeUTF(c0089w1.a);
                        dataOutputStream.writeUTF(c0089w1.b);
                        dataOutputStream.writeLong(c0089w1.c);
                    }
                    dataOutputStream.flush();
                    fileOutputStream.getFD().sync();
                    dataOutputStream.close();
                    fileOutputStream.close();
                    Files.move(file2.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                    Files.deleteIfExists(file2.toPath());
                } catch (Throwable th) {
                    try {
                        dataOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (Throwable th5) {
            Files.deleteIfExists(file2.toPath());
            throw th5;
        }
    }
}
