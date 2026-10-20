/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import dev.local.newsoul.h3;
import dev.local.newsoul.w1_0;
import dev.local.newsoul.w_0;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/*
 * Renamed from dev.local.newsoul.x1
 */
public final class x1_0 {
    public static final Object b = new Object();
    public final File a;

    public x1_0(File file, String object, int n) {
        switch (n) {
            default: {
                if (object != null && !((String)object).trim().isEmpty()) {
                    object = MessageDigest.getInstance("SHA-256").digest(((String)object).getBytes(StandardCharsets.UTF_8));
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Object object2 : object) {
                        stringBuilder.append(String.format(Locale.ROOT, "%02x", object2 & 0xFF));
                    }
                    this.a = new File(new File(file, "newsoul-profile-bookmarks"), String.valueOf(stringBuilder).concat(".bin"));
                    return;
                }
                throw new IllegalArgumentException("\u8bf7\u5148\u767b\u5f55 Soul");
            }
            case 1: 
        }
        if (object != null && !((String)object).isEmpty()) {
            byte[] byArray = MessageDigest.getInstance("SHA-256").digest(((String)object).getBytes(StandardCharsets.UTF_8));
            object = new StringBuilder();
            for (byte by : byArray) {
                ((StringBuilder)object).append(String.format(Locale.ROOT, "%02x", by & 0xFF));
            }
            this.a = file = new File(file, "newsoul-voice-library/".concat(String.valueOf(object)));
            if (!file.isDirectory() && !file.mkdirs()) {
                throw new IOException("Cannot create library");
            }
            return;
        }
        throw new IllegalArgumentException("Missing account");
    }

    public static void b(InputStream inputStream, FileOutputStream fileOutputStream) {
        int n;
        byte[] byArray = new byte[16384];
        long l = 0L;
        while ((n = inputStream.read(byArray)) != -1) {
            if ((l += (long)n) <= 0x1400000L) {
                ((OutputStream)fileOutputStream).write(byArray, 0, n);
                continue;
            }
            throw new IOException("\u8bed\u97f3\u6587\u4ef6\u8d85\u8fc7 20 MB");
        }
        if (l != 0L) {
            return;
        }
        throw new IOException("\u8bed\u97f3\u6587\u4ef6\u4e3a\u7a7a");
    }

    public static String c(File object) {
        int n;
        FileInputStream fileInputStream;
        byte[] byArray;
        block11: {
            byArray = new byte[16];
            fileInputStream = new FileInputStream((File)object);
            n = ((InputStream)fileInputStream).read(byArray);
            object = new String(byArray, 0, Math.max(0, n), StandardCharsets.ISO_8859_1);
            if (((String)object).startsWith("#!AMR")) {
                return ".amr";
            }
            if (((String)object).startsWith("RIFF")) {
                return ".wav";
            }
            if (((String)object).startsWith("OggS")) {
                return ".ogg";
            }
            if (n >= 8 && ((String)object).substring(4, 8).equals("ftyp")) {
                return ".m4a";
            }
            if (!((String)object).startsWith("fLaC")) break block11;
            return ".flac";
        }
        if (n >= 2 && (byArray[0] & 0xFF) == 255 && (byArray[1] & 0xF6) == 240) {
            return ".aac";
        }
        if (!(((String)object).startsWith("ID3") || n >= 2 && (byArray[0] & 0xFF) == 255 && (byArray[1] & 0xE0) == 224)) {
            return ".audio";
        }
        return ".mp3";
        finally {
            ((InputStream)fileInputStream).close();
        }
    }

    public static String e(String string) {
        if (!(string = string == null ? "" : string.trim()).isEmpty()) {
            if (string.length() <= 120) {
                return string;
            }
            throw new IllegalArgumentException("\u5907\u6ce8\u6700\u591a 120 \u5b57");
        }
        throw new IllegalArgumentException("\u8bf7\u586b\u5199\u5907\u6ce8");
    }

    public static void j(String string) {
        if (string != null && !string.trim().isEmpty() && string.length() <= 512 && string.equals(string.trim())) {
            return;
        }
        throw new IllegalArgumentException("\u4e3b\u9875\u6807\u8bc6\u4e0d\u53ef\u7528");
    }

    /*
     * Exception decompiling
     */
    public void a(FileInputStream var1_1, String var2_5, int var3_6) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Back jump on a try block [egrp 3[TRYBLOCK] [3 : 64->74)] java.lang.Throwable
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.insertExceptionBlocks(Op02WithProcessedDataAndRefs.java:2283)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:415)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList d() {
        synchronized (this) {
            Throwable throwable2;
            block11: {
                ArrayList<h3> arrayList;
                Object object;
                try {
                    int n;
                    object = this.a;
                    arrayList = new ArrayList<h3>();
                    object = object.listFiles((FilenameFilter)((Object)arrayList));
                    if (object != null) {
                        arrayList = new ArrayList<h3>();
                        n = ((File[])object).length;
                    } else {
                        arrayList = new ArrayList<h3>("Cannot read library");
                        throw arrayList;
                    }
                    for (int i = 0; i < n; ++i) {
                        Object object2 = object[i];
                        object2 = ((File)object2).getName().substring(0, ((File)object2).getName().length() - 5);
                        try {
                            arrayList.add(this.g((String)object2));
                            continue;
                        }
                        catch (IOException iOException) {
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            // empty catch block
                        }
                        object = new IOException("\u8bed\u97f3\u5e93\u8bb0\u5f55\u635f\u574f\uff0c\u8bf7\u4fdd\u7559\u6570\u636e\u540e\u6392\u67e5", (Throwable)((Object)arrayList));
                        throw object;
                    }
                }
                catch (Throwable throwable2) {
                    break block11;
                }
                object = new Object();
                arrayList.sort(Comparator.comparingLong(object).reversed());
                return arrayList;
            }
            throw throwable2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void f(String object, String object2) {
        x1_0.j((String)object);
        String string = ((String)object2).trim();
        if (string.isEmpty()) throw new IllegalArgumentException("\u6635\u79f0\u4e0d\u53ef\u7528");
        if (string.length() > 120) throw new IllegalArgumentException("\u6635\u79f0\u4e0d\u53ef\u7528");
        object2 = b;
        // MONITORENTER : object2
        ArrayList arrayList = this.h();
        for (int i = 0; i < arrayList.size(); ++i) {
            if (!((w1_0)arrayList.get((int)i)).a.equals(object)) continue;
            w1_0 w1_02 = new w1_0((String)object, string, ((w1_0)arrayList.get((int)i)).c);
            arrayList.set(i, w1_02);
            this.l(arrayList);
            // MONITOREXIT : object2
            return;
        }
        if (arrayList.size() < 1000) {
            w1_0 w1_03 = new w1_0((String)object, string, System.currentTimeMillis());
            arrayList.add(0, w1_03);
            this.l(arrayList);
            // MONITOREXIT : object2
            return;
        }
        object = new IOException("\u63d2\u773c\u5217\u8868\u5df2\u6ee1\uff0c\u8bf7\u5148\u79fb\u9664\u90e8\u5206\u8bb0\u5f55");
        throw object;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public h3 g(String object) {
        synchronized (this) {
            Throwable throwable3;
            block14: {
                block10: {
                    Throwable throwable2222;
                    DataInputStream dataInputStream;
                    block13: {
                        block11: {
                            block12: {
                                if (object == null) break block10;
                                if (!((String)object).matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}")) break block10;
                                File file = new File(this.a, ((String)object).concat(".meta"));
                                Object object2 = new FileInputStream(file);
                                dataInputStream = new DataInputStream((InputStream)object2);
                                {
                                    catch (Throwable throwable3) {}
                                }
                                if (dataInputStream.readInt() != 1314084401) break block11;
                                object2 = dataInputStream.readUTF();
                                int n = dataInputStream.readInt();
                                long l = dataInputStream.readLong();
                                file = new File(this.a, ((String)object).concat(".audio"));
                                if (n <= 0 || ((String)object2).length() > 120 || !file.isFile() || file.length() <= 0L || file.length() > 0x1400000L) break block12;
                                h3 h32 = new h3((String)object, (String)object2, n, l, file);
                                ((InputStream)dataInputStream).close();
                                return h32;
                            }
                            try {
                                object = new IOException("Missing voice file");
                                throw object;
                            }
                            catch (Throwable throwable2222) {}
                            break block13;
                        }
                        object = new IOException("Unknown voice record");
                        throw object;
                    }
                    try {
                        ((InputStream)dataInputStream).close();
                        throw throwable2222;
                    }
                    catch (Throwable throwable4) {
                        throwable2222.addSuppressed(throwable4);
                        throw throwable2222;
                    }
                    break block14;
                }
                object = new IllegalArgumentException("Invalid voice id");
                throw object;
            }
            throw throwable3;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrayList h() {
        Serializable serializable = new ArrayList();
        Object object = this.a;
        if (!((File)object).exists()) {
            return serializable;
        }
        object = new DataInputStream(new FileInputStream((File)object));
        try {
            HashSet<String> hashSet;
            int n;
            int n2;
            block15: {
                n2 = ((DataInputStream)object).readInt();
                if (n2 == 1314082865) {
                    n = ((DataInputStream)object).readInt();
                    if (n >= 0 && n <= 1000) {
                        hashSet = new HashSet<String>();
                        break block15;
                    } else {
                        serializable = new IOException("\u63d2\u773c\u8bb0\u5f55\u6570\u91cf\u5f02\u5e38");
                        throw serializable;
                    }
                }
                serializable = new IOException("\u63d2\u773c\u8bb0\u5f55\u683c\u5f0f\u5f02\u5e38");
                throw serializable;
            }
            for (n2 = 0; n2 < n; ++n2) {
                String string = ((DataInputStream)object).readUTF();
                String string2 = ((DataInputStream)object).readUTF();
                long l = ((DataInputStream)object).readLong();
                try {
                    x1_0.j(string);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    serializable = new IOException("\u63d2\u773c\u8bb0\u5f55\u635f\u574f", illegalArgumentException);
                    throw serializable;
                }
                if (!string2.trim().isEmpty() && string2.length() <= 120 && l >= 0L && hashSet.add(string)) {
                    w1_0 w1_02 = new w1_0(string, string2, l);
                    ((ArrayList)serializable).add(w1_02);
                    continue;
                }
                serializable = new IOException("\u63d2\u773c\u8bb0\u5f55\u635f\u574f");
                throw serializable;
            }
            n2 = ((InputStream)object).read();
            if (n2 == -1) {
                ((InputStream)object).close();
                return serializable;
            }
            serializable = new IOException("\u63d2\u773c\u8bb0\u5f55\u683c\u5f0f\u5f02\u5e38");
            throw serializable;
        }
        catch (Throwable throwable) {}
        try {
            ((InputStream)object).close();
            throw throwable;
        }
        catch (Throwable throwable) {
            throwable.addSuppressed(throwable);
        }
        throw throwable;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void i(String string) {
        x1_0.j(string);
        Object object = b;
        synchronized (object) {
            ArrayList arrayList = this.h();
            w_0 w_02 = new w_0(1, string);
            if (arrayList.removeIf(w_02)) {
                this.l(arrayList);
            }
            return;
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void k(h3 object) {
        Throwable throwable522222;
        Throwable throwable2222222;
        File file = this.a;
        Comparable<StringBuilder> comparable = new StringBuilder();
        String string = ((h3)object).a;
        ((StringBuilder)comparable).append(string);
        ((StringBuilder)comparable).append(".meta.tmp");
        comparable = new File(file, ((StringBuilder)comparable).toString());
        Object object2 = new FileOutputStream((File)comparable);
        {
            catch (Throwable throwable2222222) {}
        }
        Object object3 = new DataOutputStream((OutputStream)object2);
        ((DataOutputStream)object3).writeInt(1314084401);
        ((DataOutputStream)object3).writeUTF(((h3)object).b);
        ((DataOutputStream)object3).writeInt(((h3)object).c);
        ((DataOutputStream)object3).writeLong(((h3)object).d);
        ((DataOutputStream)object3).flush();
        ((FileOutputStream)object2).getFD().sync();
        ((OutputStream)object3).close();
        ((FileOutputStream)object2).close();
        object2 = ((File)comparable).toPath();
        object3 = new StringBuilder();
        ((StringBuilder)object3).append(string);
        ((StringBuilder)object3).append(".meta");
        object = new File(file, ((StringBuilder)object3).toString());
        Files.move((Path)object2, ((File)object).toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        Files.deleteIfExists(((File)comparable).toPath());
        return;
        catch (Throwable throwable3) {
            try {
                ((OutputStream)object3).close();
                throw throwable3;
            }
            catch (Throwable throwable4) {
                try {
                    throwable3.addSuppressed(throwable4);
                    throw throwable3;
                }
                catch (Throwable throwable522222) {}
            }
        }
        try {
            ((FileOutputStream)object2).close();
            throw throwable522222;
        }
        catch (Throwable throwable6) {
            throwable522222.addSuppressed(throwable6);
            throw throwable522222;
        }
        Files.deleteIfExists(((File)comparable).toPath());
        throw throwable2222222;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void l(ArrayList object) {
        Throwable throwable3222222;
        File file;
        block16: {
            File file2 = this.a;
            file = file2.getParentFile();
            if (!file.isDirectory()) {
                if (!file.mkdirs()) throw new IOException("\u65e0\u6cd5\u521b\u5efa\u63d2\u773c\u5217\u8868");
            }
            Object object2 = file2.getName();
            Object object3 = new StringBuilder();
            ((StringBuilder)object3).append((String)object2);
            ((StringBuilder)object3).append(".tmp");
            file = new File(file, ((StringBuilder)object3).toString());
            object2 = new FileOutputStream(file);
            {
                Throwable throwable22222222;
                block17: {
                    catch (Throwable throwable3222222) {
                        break block16;
                    }
                    object3 = new DataOutputStream((OutputStream)object2);
                    {
                        Throwable throwable42222222;
                        block18: {
                            catch (Throwable throwable22222222) {
                                break block17;
                            }
                            try {
                                ((DataOutputStream)object3).writeInt(1314082865);
                                ((DataOutputStream)object3).writeInt(((ArrayList)object).size());
                                Iterator iterator = ((ArrayList)object).iterator();
                                while (iterator.hasNext()) {
                                    object = (w1_0)iterator.next();
                                    ((DataOutputStream)object3).writeUTF(((w1_0)object).a);
                                    ((DataOutputStream)object3).writeUTF(((w1_0)object).b);
                                    ((DataOutputStream)object3).writeLong(((w1_0)object).c);
                                }
                            }
                            catch (Throwable throwable42222222) {
                                break block18;
                            }
                            ((DataOutputStream)object3).flush();
                            ((FileOutputStream)object2).getFD().sync();
                            {
                                ((OutputStream)object3).close();
                            }
                            {
                                ((FileOutputStream)object2).close();
                                Files.move(file.toPath(), file2.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                            }
                            Files.deleteIfExists(file.toPath());
                            return;
                        }
                        try {
                            ((OutputStream)object3).close();
                            throw throwable42222222;
                        }
                        catch (Throwable throwable5) {}
                        {
                            throwable42222222.addSuppressed(throwable5);
                            throw throwable42222222;
                        }
                    }
                }
                try {
                    ((FileOutputStream)object2).close();
                    throw throwable22222222;
                }
                catch (Throwable throwable6) {}
                {
                    throwable22222222.addSuppressed(throwable6);
                    throw throwable22222222;
                }
            }
        }
        Files.deleteIfExists(file.toPath());
        throw throwable3222222;
    }
}

