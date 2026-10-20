package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class d3 {
    public static boolean g;
    public final Handler a = new Handler(Looper.getMainLooper());
    public final ExecutorService b = Executors.newSingleThreadExecutor();
    public final WeakHashMap c = new WeakHashMap();
    public Method d;
    public Method e;
    public b3 f;

    public static TextView a(C0067q2 c0067q2, String str, c3 c3Var) {
        int i = c0067q2.i;
        Context context = c0067q2.g;
        TextView textViewN = AbstractC0090w2.n(context, str, 15, i);
        textViewN.setGravity(16);
        int i2 = AbstractC0090w2.i(context, 12);
        textViewN.setPadding(i2, i2, i2, i2);
        textViewN.setMinHeight(AbstractC0090w2.i(context, 48));
        textViewN.setSingleLine(false);
        textViewN.setEllipsize(null);
        AbstractC0090w2.g(textViewN);
        L2 l2 = c0067q2.c;
        l2.getClass();
        l2.b(textViewN, 9, L2.e(9));
        textViewN.setOnClickListener(new C0(c3Var, c0067q2, 5));
        return textViewN;
    }

    public static void b(File file, String str) throws IOException {
        for (int i = 0; i < 6; i++) {
            if (!Q0.N(str)) {
                throw new IOException("Invalid audio address");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setInstanceFollowRedirects(false);
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 300 || responseCode >= 400) {
                    if (responseCode != 200) {
                        throw new IOException("音频下载失败（" + responseCode + "）");
                    }
                    if (httpURLConnection.getContentLengthLong() > 20971520) {
                        throw new IOException("语音文件过大");
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            C0093x1.b(inputStream, fileOutputStream);
                            fileOutputStream.close();
                            inputStream.close();
                            httpURLConnection.disconnect();
                            return;
                        } catch (Throwable th) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                        }
                        throw th3;
                    }
                }
                String headerField = httpURLConnection.getHeaderField("Location");
                if (headerField == null) {
                    throw new IOException("Invalid redirect");
                }
                str = new URL(new URL(str), headerField).toString();
                httpURLConnection.disconnect();
            } catch (Throwable th5) {
                httpURLConnection.disconnect();
                throw th5;
            }
        }
        throw new IOException("Too many redirects");
    }

    public static Object c(Object obj) throws NoSuchFieldException {
        Field field = null;
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            for (Field field2 : superclass.getDeclaredFields()) {
                if (!Modifier.isStatic(field2.getModifiers()) && field2.getType().getName().equals("cn.soulapp.imlib.Conversation")) {
                    if (field != null) {
                        throw new IllegalStateException("Ambiguous conversation field");
                    }
                    field = field2;
                }
            }
        }
        if (field == null) {
            throw new NoSuchFieldException("cn.soulapp.imlib.Conversation");
        }
        field.setAccessible(true);
        return field.get(obj);
    }

    public static Object e(Object obj, String str) {
        return obj.getClass().getMethod(str, null).invoke(obj, null);
    }

    public static void f(Context context, String str, Throwable th) {
        Log.w("SoulHook102", "voice library operation failed; no automatic send retry", th);
        i(context, str);
    }

    public static Object g(Object obj, String str) throws NoSuchFieldException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException(str);
    }

    public static void i(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public final void d(Object obj) {
        View view;
        final Activity activity = (Activity) e(obj, "getActivity");
        if (activity == null || !((Boolean) e(obj, "isResumed")).booleanValue() || (view = (View) e(obj, "getView")) == null) {
            return;
        }
        String[] strArr = {"menu_tab_voice_inner_outside", "menu_tab_voice_inner", "menu_tab_voice"};
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            int identifier = activity.getResources().getIdentifier(str, "id", activity.getPackageName());
            View viewFindViewById = identifier == 0 ? null : view.findViewById(identifier);
            if (viewFindViewById != null) {
                WeakHashMap weakHashMap = this.c;
                if (!weakHashMap.containsKey(viewFindViewById) && !viewFindViewById.hasOnLongClickListeners()) {
                    final WeakReference weakReference = new WeakReference(obj);
                    viewFindViewById.setOnLongClickListener(new View.OnLongClickListener() { // from class: dev.local.newsoul.O2
                        @Override // android.view.View.OnLongClickListener
                        public final boolean onLongClick(View view2) {
                            WeakReference weakReference2 = weakReference;
                            d3 d3Var = this.a;
                            d3Var.getClass();
                            try {
                                Object obj2 = weakReference2.get();
                                if (obj2 == null) {
                                    return false;
                                }
                                b3 b3Var = new b3(d3Var, obj2);
                                b3 b3Var2 = d3Var.f;
                                if (b3Var2 != null) {
                                    b3Var2.b();
                                }
                                d3Var.f = b3Var;
                                b3Var.f();
                            } catch (Throwable th) {
                                d3.f(activity, "暂时无法打开语音库", th);
                            }
                            return true;
                        }
                    });
                    if ("menu_tab_voice_inner_outside".equals(str)) {
                        viewFindViewById.setOnTouchListener(null);
                    }
                    weakHashMap.put(viewFindViewById, Boolean.TRUE);
                }
            }
        }
    }

    public final void h(Object obj, Object obj2) {
        b3 b3Var = new b3(this, obj);
        String str = (String) g(obj2, "url");
        int iIntValue = ((Integer) g(obj2, "duration")).intValue();
        if (!Q0.N(str) || iIntValue <= 0) {
            throw new IllegalArgumentException("Invalid voice");
        }
        b3 b3Var2 = this.f;
        if (b3Var2 != null) {
            b3Var2.b();
        }
        this.f = b3Var;
        b3Var.e("保存到语音库", "", new H2(b3Var, str, iIntValue), new U2(1, b3Var));
    }
}
