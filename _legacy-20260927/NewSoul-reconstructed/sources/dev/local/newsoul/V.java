/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.pm.PackageInfo
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.os.Looper
 *  org.json.JSONObject
 */
package dev.local.newsoul;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import dev.local.newsoul.F;
import dev.local.newsoul.Q0;
import dev.local.newsoul.W;
import dev.local.newsoul.Y;
import dev.local.newsoul.d3;
import dev.local.newsoul.h_0;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;

public abstract class V {
    public static final ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();
    public static final Handler b = new Handler(Looper.getMainLooper());
    public static final ArrayList c = new ArrayList();
    public static final LinkedHashMap d = new LinkedHashMap();
    public static final String e = UUID.randomUUID().toString();
    public static volatile long f;
    public static volatile long g;
    public static volatile String h;
    public static Context i;
    public static SharedPreferences j;
    public static Y k;
    public static String l;
    public static int m;
    public static long n;

    static {
        h = "\u672a\u5f00\u542f";
        l = "unknown";
    }

    public static boolean a() {
        long l = f - System.currentTimeMillis();
        boolean bl = l > 0L && l <= 1800000L;
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(String string, String string2) {
        ArrayList arrayList = c;
        synchronized (arrayList) {
            block7: {
                Throwable throwable2;
                block8: {
                    try {
                        if (arrayList.size() >= 256) {
                            ++m;
                            return;
                        }
                    }
                    catch (Throwable throwable2) {
                        break block8;
                    }
                    try {
                        long l;
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("time", System.currentTimeMillis());
                        jSONObject.put("run", (Object)e);
                        n = l = n + 1L;
                        jSONObject.put("sequence", l);
                        jSONObject.put("point", (Object)string);
                        string = string2;
                        if (string2.length() > 6000) {
                            string = string2.substring(0, 6000);
                        }
                        jSONObject.put("facts", (Object)string);
                        arrayList.add(jSONObject.toString());
                        break block7;
                    }
                    catch (Exception exception) {}
                }
                throw throwable2;
            }
            return;
        }
    }

    public static String c(Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; throwable != null && i < 3; ++i, throwable = throwable.getCause()) {
            stringBuilder.append(throwable.getClass().getName());
            StackTraceElement[] stackTraceElementArray = throwable.getStackTrace();
            for (int j = 0; j < Math.min(stackTraceElementArray.length, 6); ++j) {
                stringBuilder.append("\n at ");
                stringBuilder.append(stackTraceElementArray[j].getClassName());
                stringBuilder.append('.');
                stringBuilder.append(stackTraceElementArray[j].getMethodName());
                stringBuilder.append(':');
                stringBuilder.append(stackTraceElementArray[j].getLineNumber());
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void d(String string, String string2) {
        ArrayList arrayList = c;
        synchronized (arrayList) {
            Throwable throwable2;
            block4: {
                block3: {
                    try {
                        if (!V.a()) break block3;
                        V.b(string, string2);
                    }
                    catch (Throwable throwable2) {
                        break block4;
                    }
                }
                return;
            }
            throw throwable2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static byte[] e() {
        Throwable throwable4;
        Object object;
        block17: {
            Object object2;
            block16: {
                Throwable throwable22;
                Object object3;
                V.f();
                Y y = k;
                if (y == null) throw new IOException();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                object = ((File)y.a).listFiles();
                object2 = object;
                if (object == null) {
                    object2 = new File[]{};
                }
                Arrays.sort(object2, Comparator.comparing(new Object()));
                object = new ZipOutputStream(byteArrayOutputStream);
                int n = ((File[])object2).length;
                int n2 = 0;
                int n3 = 0;
                while (true) {
                    block15: {
                        if (n2 >= n) {
                            ((ZipOutputStream)object).close();
                            if (n3 == 0) throw new IOException("No records");
                            return W.a(byteArrayOutputStream.toByteArray(), (PublicKey)y.b);
                        }
                        Object object4 = object2[n2];
                        if (!((File)object4).getName().endsWith(".chunk")) break block15;
                        if (((File)object4).length() > 0x400000L || (long)byteArrayOutputStream.size() > 0x480000L) break block16;
                        object3 = new ZipEntry(((File)object4).getName());
                        ((ZipOutputStream)object).putNextEntry((ZipEntry)object3);
                        object3 = new FileInputStream((File)object4);
                        try {
                            int n4;
                            object4 = new byte[8192];
                            while ((n4 = ((InputStream)object3).read((byte[])object4)) != -1) {
                                ((ZipOutputStream)object).write((byte[])object4, 0, n4);
                            }
                        }
                        catch (Throwable throwable22) {
                            break;
                        }
                        ((InputStream)object3).close();
                        ((ZipOutputStream)object).closeEntry();
                        ++n3;
                    }
                    ++n2;
                }
                try {
                    ((InputStream)object3).close();
                    throw throwable22;
                }
                catch (Throwable throwable3) {
                    try {
                        throwable22.addSuppressed(throwable3);
                        throw throwable22;
                    }
                    catch (Throwable throwable4) {}
                }
                break block17;
            }
            object2 = new IOException("Size limit");
            throw object2;
        }
        try {
            ((ZipOutputStream)object).close();
            throw throwable4;
        }
        catch (Throwable throwable5) {
            throwable4.addSuppressed(throwable5);
        }
        throw throwable4;
    }

    /*
     * Exception decompiling
     */
    public static void f() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Back jump on a try block [egrp 1[TRYBLOCK] [2 : 94->147)] java.lang.Throwable
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
    public static boolean g() {
        ArrayList arrayList = c;
        synchronized (arrayList) {
            if (g > 0L) return true;
            if (arrayList.isEmpty()) return false;
            return true;
        }
    }

    public static void h(Context object) {
        synchronized (V.class) {
            Object object2;
            block7: {
                block6: {
                    object2 = i;
                    if (object2 == null) break block6;
                    return;
                }
                object = object.getApplicationContext();
                i = object;
                if (object != null) break block7;
                return;
            }
            j = object.getSharedPreferences("newsoul_debug", 0);
            object = a;
            object2 = new h_0(2);
            object.execute((Runnable)object2);
            object2 = new h_0(3);
            object.scheduleAtFixedRate((Runnable)object2, 1L, 1L, TimeUnit.SECONDS);
            return;
        }
    }

    public static void i(String string, Throwable object) {
        object = object == null ? "registered" : V.c((Throwable)object);
        V.j(string, (String)object);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void j(String string, String string2) {
        Serializable serializable = d;
        synchronized (serializable) {
            serializable.put(string, string2);
        }
        serializable = new StringBuilder("registration.");
        ((StringBuilder)serializable).append(string);
        V.d(((StringBuilder)serializable).toString(), string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void k(String object) {
        String string = V.m(i.getPackageName());
        CharSequence charSequence = V.m("dev.local.newsoul.public");
        int n = Build.VERSION.SDK_INT;
        String string2 = Build.VERSION.RELEASE;
        String string3 = Build.MANUFACTURER;
        String string4 = Build.MODEL;
        Object object2222 = l;
        StringBuilder stringBuilder = new StringBuilder("format=1; host=");
        stringBuilder.append(string);
        stringBuilder.append("; module=");
        stringBuilder.append((String)charSequence);
        stringBuilder.append("; sdk=");
        stringBuilder.append(n);
        stringBuilder.append("; android=");
        stringBuilder.append(string2);
        stringBuilder.append("; model=");
        stringBuilder.append(string3);
        stringBuilder.append("/");
        stringBuilder.append(string4);
        stringBuilder.append("; framework=");
        stringBuilder.append((String)object2222);
        V.d((String)object, stringBuilder.toString());
        object = d;
        // MONITORENTER : object
        for (Object object2222 : ((LinkedHashMap)object).entrySet()) {
            string2 = (String)object2222.getKey();
            charSequence = new StringBuilder();
            ((StringBuilder)charSequence).append("registration.");
            ((StringBuilder)charSequence).append(string2);
            V.d(((StringBuilder)charSequence).toString(), (String)object2222.getValue());
        }
        try {
            object = Q0.U((Context)V.i).c.iterator();
            while (object.hasNext()) {
                V.d("compatibility", (String)object.next());
            }
        }
        catch (Throwable throwable) {
            V.d("compatibility.failed", V.c(throwable));
        }
        boolean bl = F.o;
        boolean bl2 = F.p;
        boolean bl3 = d3.g;
        object = new StringBuilder("repeat=");
        ((StringBuilder)object).append(bl);
        ((StringBuilder)object).append("; forward=");
        ((StringBuilder)object).append(bl2);
        ((StringBuilder)object).append("; library=");
        ((StringBuilder)object).append(bl3);
        V.d("ready", ((StringBuilder)object).toString());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void l(String string) {
        ArrayList arrayList = c;
        // MONITORENTER : arrayList
        boolean bl = f != 0L;
        f = 0L;
        h = string;
        if (bl) {
            V.b("session_stop", string);
        }
        arrayList = j;
        if (arrayList == null) return;
        arrayList.edit().putLong("until", 0L).putString("status", string).commit();
    }

    public static String m(String string) {
        try {
            Object object = i.getPackageManager().getPackageInfo(string, 0);
            string = ((PackageInfo)object).versionName;
            int n = ((PackageInfo)object).versionCode;
            object = new StringBuilder();
            ((StringBuilder)object).append(string);
            ((StringBuilder)object).append(" (");
            ((StringBuilder)object).append(n);
            ((StringBuilder)object).append(")");
            string = ((StringBuilder)object).toString();
            return string;
        }
        catch (Exception exception) {
            return "unknown";
        }
    }
}

