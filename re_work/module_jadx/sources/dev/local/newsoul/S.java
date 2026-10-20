package dev.local.newsoul;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public abstract class S {
    public static XposedModule a;
    public static final Handler b = new Handler(Looper.getMainLooper());
    public static final ExecutorService c = Executors.newSingleThreadExecutor();
    public static final WeakHashMap d = new WeakHashMap();
    public static final HashSet e = new HashSet();
    public static final SecureRandom f = new SecureRandom();

    public static void a(Activity activity) throws NoSuchMethodException {
        Method declaredMethod;
        if (a == null) {
            throw new IllegalStateException();
        }
        Class<?> superclass = activity.getClass();
        while (true) {
            if (superclass == null) {
                declaredMethod = null;
                break;
            }
            try {
                Class cls = Integer.TYPE;
                declaredMethod = superclass.getDeclaredMethod("onActivityResult", cls, cls, Intent.class);
                break;
            } catch (NoSuchMethodException unused) {
                superclass = superclass.getSuperclass();
            }
        }
        if (declaredMethod == null) {
            throw new NoSuchMethodException();
        }
        HashSet hashSet = e;
        if (hashSet.contains(declaredMethod)) {
            return;
        }
        a.hook(declaredMethod).setId("new-soul/config-file-result").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0000a(12));
        hashSet.add(declaredMethod);
    }

    public static void b(Activity activity, P p, String str) {
        WeakHashMap weakHashMap = d;
        if (weakHashMap.get(activity) != p) {
            return;
        }
        weakHashMap.remove(activity);
        Q q = (Q) p.e.get();
        if (q != null) {
            q.a(str, false);
        } else {
            if (activity.isDestroyed() || activity.isDestroyed()) {
                return;
            }
            Toast.makeText(activity, str, 1).show();
        }
    }

    public static void c(C0067q2 c0067q2, Q q, boolean z) {
        String strA;
        Activity activity = c0067q2.a;
        WeakHashMap weakHashMap = d;
        try {
            P p = (P) weakHashMap.get(activity);
            if (p != null) {
                if (p.e.get() != q) {
                    q.a("配置操作进行中，请先完成或取消", false);
                    return;
                }
                return;
            }
            a(activity);
            if (z) {
                WeakHashMap weakHashMap2 = AbstractC0090w2.a;
                strA = G.a(activity.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0).getAll());
            } else {
                strA = null;
            }
            P p2 = new P(c0067q2, q, strA);
            Intent intent = new Intent(z ? "android.intent.action.CREATE_DOCUMENT" : "android.intent.action.OPEN_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType(z ? "application/json" : "*/*");
            if (z) {
                intent.putExtra("android.intent.extra.TITLE", "NewSoul-config-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ROOT).format(new Date()) + ".json");
            }
            weakHashMap.put(activity, p2);
            q.a(z ? "请选择保存位置" : "请选择配置文件", true);
            try {
                activity.startActivityForResult(intent, p2.a);
            } catch (RuntimeException e2) {
                weakHashMap.remove(activity);
                throw e2;
            }
        } catch (Exception unused) {
            q.a("无法打开配置文件操作，请重试", false);
        }
    }

    public static boolean d(Activity activity, P p) {
        AlertDialog alertDialog;
        Window window;
        C0067q2 c0067q2 = (C0067q2) p.d.get();
        return (activity.isFinishing() || activity.isDestroyed() || c0067q2 == null || c0067q2.a != activity || (alertDialog = c0067q2.e) == null || !alertDialog.isShowing() || (window = c0067q2.e.getWindow()) == null || !window.getDecorView().isAttachedToWindow()) ? false : true;
    }
}
