package dev.local.newsoul;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;
import io.github.libxposed.api.XposedInterface;
import java.util.List;

/* 本文件合并了仅大小写不同的两个类 V / v（原 dex 类名保持不变；文件系统大小写不敏感所致） */
/* JADX INFO: loaded from: V.class */
public abstract class V {
    public static volatile long f;
    public static volatile long g;
    public static Context i;
    public static SharedPreferences j;
    public static Y k;
    public static int m;
    public static long n;
    public static final ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();
    public static final Handler b = new Handler(Looper.getMainLooper());
    public static final ArrayList c = new ArrayList();
    public static final LinkedHashMap<String, String> d = new LinkedHashMap<>();
    public static final String e = UUID.randomUUID().toString();
    public static volatile String h = "未开启";
    public static String l = "unknown";

    public static boolean a() {
        long jCurrentTimeMillis = f - System.currentTimeMillis();
        return jCurrentTimeMillis > 0 && jCurrentTimeMillis <= 1800000;
    }

    public static void b(String str, String str2) {
        ArrayList arrayList = c;
        synchronized (arrayList) {
            if (arrayList.size() >= 256) {
                m++;
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("time", System.currentTimeMillis());
                jSONObject.put("run", e);
                long C0039j2 = n + 1;
                n = C0039j2;
                jSONObject.put("sequence", C0039j2);
                jSONObject.put("point", str);
                String strSubstring = str2;
                if (str2.length() > 6000) {
                    strSubstring = str2.substring(0, 6000);
                }
                jSONObject.put("facts", strSubstring);
                arrayList.add(jSONObject.toString());
            } catch (Exception C0019e2) {
            }
        }
    }

    public static String c(Throwable th) {
        StringBuilder sb = new StringBuilder();
        int C0035i2 = 0;
        while (th != null && C0035i2 < 3) {
            sb.append(th.getClass().getName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            for (int i3 = 0; i3 < Math.min(stackTrace.length, 6); i3++) {
                sb.append("\n at ");
                sb.append(stackTrace[i3].getClassName());
                sb.append('.');
                sb.append(stackTrace[i3].getMethodName());
                sb.append(':');
                sb.append(stackTrace[i3].getLineNumber());
            }
            sb.append('\n');
            C0035i2++;
            th = th.getCause();
        }
        return sb.toString();
    }

    public static void d(String str, String str2) {
        synchronized (c) {
            if (a()) {
                b(str, str2);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.util.function.Function] */
    public static byte[] e() throws IOException {
        f();
        Y y = k;
        if (y == null) {
            throw new IOException();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        File[] fileArrListFiles = ((File) y.a).listFiles();
        File[] fileArr = fileArrListFiles;
        if (fileArrListFiles == null) {
            fileArr = new File[0];
        }
        Arrays.sort(fileArr, Comparator.comparing(new X()));
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        try {
            int C0035i2 = 0;
            for (File file : fileArr) {
                if (file.getName().endsWith(".chunk")) {
                    if (file.length() > 4194304 || byteArrayOutputStream.size() > 4718592) {
                        throw new IOException("Size limit");
                    }
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[8192];
                        while (true) {
                            int i3 = fileInputStream.read(bArr);
                            if (i3 == -1) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, i3);
                        }
                        fileInputStream.close();
                        zipOutputStream.closeEntry();
                        C0035i2++;
                    } catch (Throwable th2) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
            zipOutputStream.close();
            if (C0035i2 != 0) {
                return W.a(byteArrayOutputStream.toByteArray(), (PublicKey) y.b);
            }
            throw new IOException("No records");
        } catch (Throwable th4) {
            zipOutputStream.close();
            throw th4;
        }
    }

    public static void f() {
        ArrayList arrayList;
        if (k == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = c;
        synchronized (arrayList2) {
            if (m > 0) {
                if (arrayList2.size() == 256) {
                    arrayList2.remove(arrayList2.size() - 1);
                }
                b("records_dropped", "count=" + m);
                m = 0;
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
                sb.append('\n');
            }
            arrayList = c;
            arrayList.clear();
        }
        if (sb.length() == 0) {
            return;
        }
        Y y = k;
        byte[] bArrA = W.a(sb.toString().getBytes(StandardCharsets.UTF_8), (PublicKey) y.b);
        if (y.b() + ((long) bArrA.length) > 4194304) {
            l("记录已满，已停止");
            synchronized (arrayList) {
                arrayList.clear();
            }
        } else {
            String str = String.format(Locale.ROOT, "%013d-%s.chunk", Long.valueOf(System.currentTimeMillis()), UUID.randomUUID());
            File file = (File) y.a;
            File file2 = new File(file, str);
            File file3 = new File(file, file2.getName() + ".tmp");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                try {
                    fileOutputStream.write(bArrA);
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    if (!file3.renameTo(file2)) {
                        throw new IOException("Storage rename failed");
                    }
                    file3.delete();
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                file3.delete();
                throw th3;
            }
        }
        g = k.b();
    }

    public static boolean g() {
        boolean z;
        ArrayList arrayList = c;
        synchronized (arrayList) {
            z = g > 0 || !arrayList.isEmpty();
        }
        return z;
    }

    public static void h(Context context) {
        synchronized (V.class) {
            try {
                if (i != null) {
                    return;
                }
                Context applicationContext = context.getApplicationContext();
                i = applicationContext;
                if (applicationContext == null) {
                    return;
                }
                j = applicationContext.getSharedPreferences("newsoul_debug", 0);
                ScheduledExecutorService scheduledExecutorService = a;
                scheduledExecutorService.execute(new RunnableC0028h(2));
                scheduledExecutorService.scheduleAtFixedRate(new RunnableC0028h(3), 1L, 1L, TimeUnit.SECONDS);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void i(String str, Throwable th) {
        j(str, th == null ? "registered" : c(th));
    }

    public static void j(String str, String str2) {
        LinkedHashMap<String, String> linkedHashMap = d;
        synchronized (linkedHashMap) {
            linkedHashMap.put(str, str2);
        }
        d("registration." + str, str2);
    }

    public static void k(String str) {
        d(str, "format=1; host=" + m(i.getPackageName()) + "; module=" + m("dev.local.newsoul.public") + "; sdk=" + Build.VERSION.SDK_INT + "; android=" + Build.VERSION.RELEASE + "; model=" + Build.MANUFACTURER + "/" + Build.MODEL + "; framework=" + l);
        LinkedHashMap<String, String> linkedHashMap = d;
        synchronized (linkedHashMap) {
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                d("registration." + ((String) entry.getKey()), (String) entry.getValue());
            }
        }
        try {
            Iterator it = Q0.U(i).c.iterator();
            while (it.hasNext()) {
                d("compatibility", (String) it.next());
            }
        } catch (Throwable th) {
            d("compatibility.failed", c(th));
        }
        d("ready", "repeat=" + F.o + "; forward=" + F.p + "; library=" + d3.g);
    }

    public static void l(String str) {
        synchronized (c) {
            boolean z = f != 0;
            f = 0L;
            h = str;
            if (z) {
                b("session_stop", str);
            }
        }
        SharedPreferences sharedPreferences = j;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong("until", 0L).putString("status", str).commit();
        }
    }

    public static String m(String str) {
        try {
            PackageInfo packageInfo = i.getPackageManager().getPackageInfo(str, 0);
            return packageInfo.versionName + " (" + packageInfo.versionCode + ")";
        } catch (Exception C0019e2) {
            return "unknown";
        }
    }
}

/* JADX INFO: loaded from: v.class */
final /* synthetic */ class v implements XposedInterface.Hooker {
    public final int a;
    public final boolean b;
    public final String c;
    public final int d;

    public /* synthetic */ v(int i, boolean z, String str, int C0035i2) {
        this.a = i;
        this.b = z;
        this.c = str;
        this.d = C0035i2;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        Object objProceed;
        String str = this.c;
        int i = this.d;
        if (V.a()) {
            int C0035i2 = this.a;
            Object arg = C0035i2 < 0 ? null : chain.getArg(C0035i2);
            boolean z = this.b;
            C0095y yVarA = A.a(z, arg);
            C0099z zVar = yVarA.a;
            if (!z) {
                try {
                    zVar.c++;
                    A.b(arg);
                } catch (Throwable th) {
                    try {
                        yVarA.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            String str2 = str + ".enter";
            String strO = Q0.O(chain.getArg(i));
            int size = arg instanceof List ? ((List) arg).size() : -1;
            Q0.r(str2, strO + "; count=" + size + "; source=" + Q0.X());
            try {
                objProceed = chain.proceed();
                Q0.r(str + ".exit", zVar.a());
                yVarA.close();
            } catch (Throwable th3) {
                try {
                    Q0.s(str + ".host_failed", th3);
                    throw th3;
                } catch (Throwable th4) {
                    Q0.r(str + ".exit", zVar.a());
                    throw th4;
                }
            }
        } else {
            objProceed = chain.proceed();
        }
        return objProceed;
    }
}
