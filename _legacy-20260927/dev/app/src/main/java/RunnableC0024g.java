package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: dev.local.newsoul.RunnableC0024g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0024g implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ RunnableC0024g(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        switch (this.a) {
            case 0:
                try {
                    ((Method) this.b).invoke(this.c, -102, "new soul: disabled by user");
                    return;
                } catch (Throwable th) {
                    AbstractC0036j.c("ad failure callback", th);
                    return;
                }
            case 1:
                AbstractC0025g0.b((Activity) this.b, (C0001a0) this.c, "导出失败，请确认已有记录后重试");
                return;
            case 2:
                WeakHashMap weakHashMap = AbstractC0068r0.b;
                TextView textView = (TextView) this.b;
                if (weakHashMap.get(textView) != this.c) {
                    return;
                }
                weakHashMap.remove(textView);
                int i = 0;
                View view = textView;
                while (true) {
                    if (i < 12) {
                        if (!view.isClickable()) {
                            Object parent = view.getParent();
                            if (parent instanceof View) {
                                view = (View) parent;
                                i++;
                            }
                        }
                    }
                    view = textView;
                }
                WeakHashMap weakHashMap2 = AbstractC0068r0.a;
                ViewOnLongClickListenerC0065q0 viewOnLongClickListenerC0065q0 = (ViewOnLongClickListenerC0065q0) weakHashMap2.get(textView);
                if (viewOnLongClickListenerC0065q0 == null || viewOnLongClickListenerC0065q0.b.get() != view || view == null) {
                    if (viewOnLongClickListenerC0065q0 != null) {
                        weakHashMap2.remove(textView);
                        View view2 = (View) viewOnLongClickListenerC0065q0.b.get();
                        if (view2 != null) {
                            view2.setOnLongClickListener(null);
                            view2.setLongClickable(viewOnLongClickListenerC0065q0.c);
                        }
                    }
                    if (view == null || view.hasOnLongClickListeners()) {
                        return;
                    }
                    ViewOnLongClickListenerC0065q0 viewOnLongClickListenerC0065q1 = new ViewOnLongClickListenerC0065q0(textView, view);
                    weakHashMap2.put(textView, viewOnLongClickListenerC0065q1);
                    view.setOnLongClickListener(viewOnLongClickListenerC0065q1);
                    return;
                }
                return;
            case 3:
                Activity activity = (Activity) this.b;
                if (activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                AbstractC0090w2.t(activity, (activity.getResources().getConfiguration().uiMode & 48) == 32, true, (String) this.c);
                return;
            case 4:
                TextView textView2 = (TextView) this.b;
                textView2.setEnabled(true);
                textView2.setText("保存原图");
                Toast.makeText((Activity) this.c, "保存失败，请稍后重试", 0).show();
                return;
            case 5:
                if (AbstractC0081u1.i((C0067q2) this.b)) {
                    ((Runnable[]) this.c)[0].run();
                    return;
                }
                return;
            case 6:
                int i2 = 0;
                for (String str2 : ((SharedPreferences) this.b).getString("feed_keywords_v1", "").split("\\R")) {
                    if (!str2.trim().isEmpty()) {
                        i2++;
                    }
                }
                ((TextView) this.c).setText("编辑屏蔽词（" + i2 + "）");
                return;
            case 7:
                boolean z = Q0.Q;
                boolean z2 = Q0.R;
                if (z && z2) {
                    str = "";
                } else if (z) {
                    str = "部分可用：隐私照片 / 视频可用，闪照尚未就绪";
                } else {
                    str = z2 ? "部分可用：闪照可用，隐私照片 / 视频尚未就绪" : "多次查看尚未就绪：隐私照片 / 视频与闪照均未就绪";
                }
                TextView textView3 = (TextView) this.b;
                AbstractC0090w2.s(textView3, str);
                textView3.setVisibility(str.isEmpty() ? 8 : 0);
                ((C0031h2) this.c).a();
                return;
            case 8:
                C0067q2 c0067q2 = (C0067q2) this.b;
                C0067q2 c0067q3 = new C0067q2(c0067q2.a, c0067q2.b);
                String string = ((SharedPreferences) this.c).getString("chat_recall_style", "below");
                LinearLayout linearLayout = c0067q3.h;
                Context context = c0067q3.g;
                int i3 = c0067q3.j;
                linearLayout.addView(AbstractC0090w2.n(context, "效果预览 · 不发送消息", 13, i3));
                try {
                    linearLayout.addView(AbstractC0101z1.f(context, string), new LinearLayout.LayoutParams(-1, -2));
                    Log.i("SoulHook102", "recall style preview; style=" + string + "; no message sent");
                    break;
                } catch (Throwable th2) {
                    linearLayout.addView(AbstractC0090w2.n(context, "当前版本无法显示预览", 13, i3));
                    Log.e("SoulHook102", "recall preview unavailable", th2);
                }
                c0067q3.g("撤回效果预览", "返回");
                return;
            case 9:
                ((Switch) this.b).setEnabled(((BooleanSupplier) this.c).getAsBoolean());
                return;
            case 10:
                Switch r0 = (Switch) this.c;
                ((View) this.b).setVisibility((r0.isChecked() && r0.isEnabled()) ? 0 : 8);
                return;
            case 11:
                ((String[]) this.b)[0] = null;
                ((Runnable) this.c).run();
                return;
            case 12:
                WeakHashMap weakHashMap3 = B2.k;
                View view3 = (View) this.b;
                Object obj = weakHashMap3.get(view3);
                A2 a2 = (A2) this.c;
                if (obj == a2) {
                    B2.b(view3, a2);
                    return;
                }
                return;
            case 13:
                Activity activity2 = (Activity) this.b;
                String str3 = ((String[]) this.c)[0];
                if (str3 == null) {
                    str3 = "https://wwbnj.lanzoul.com/b007utki2d";
                }
                try {
                    activity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str3)).addCategory("android.intent.category.BROWSABLE"));
                    return;
                } catch (RuntimeException unused) {
                    Toast.makeText(activity2, "无法打开浏览器，请稍后重试", 0).show();
                    return;
                }
            case 14:
                WeakReference weakReference = (WeakReference) this.b;
                d3 d3Var = (d3) this.c;
                try {
                    Object obj2 = weakReference.get();
                    if (obj2 != null) {
                        d3Var.d(obj2);
                        return;
                    }
                    return;
                } catch (Throwable th3) {
                    Log.w("SoulHook102", "voice library entry unavailable", th3);
                    return;
                }
            case 15:
                b3 b3Var = (b3) this.b;
                b3Var.n = false;
                if (b3Var.m) {
                    return;
                }
                d3.f(b3Var.c, "读取语音失败", (Throwable) this.c);
                return;
            default:
                h3 h3Var = (h3) this.c;
                b3 b3Var2 = (b3) this.b;
                d3 d3Var2 = b3Var2.q;
                File file = null;
                try {
                    h3 h3VarG = b3Var2.f.g(h3Var.a);
                    File file2 = h3VarG.e;
                    File file3 = new File(b3Var2.c.getCacheDir(), "newsoul-voice-outgoing");
                    if (!file3.isDirectory() && !file3.mkdirs()) {
                        throw new IOException("Cannot create outgoing audio");
                    }
                    File file4 = new File(file3, String.valueOf(UUID.randomUUID()).concat(C0093x1.c(file2)));
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file2);
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file4);
                            try {
                                C0093x1.b(fileInputStream, fileOutputStream);
                                fileOutputStream.close();
                                fileInputStream.close();
                                d3Var2.a.post(new H(b3Var2, file4, h3VarG, 12));
                                return;
                            } catch (Throwable th4) {
                                try {
                                    fileOutputStream.close();
                                    break;
                                } catch (Throwable th5) {
                                    th4.addSuppressed(th5);
                                }
                                throw th4;
                            }
                        } catch (Throwable th6) {
                            try {
                                fileInputStream.close();
                                break;
                            } catch (Throwable th7) {
                                th6.addSuppressed(th7);
                            }
                            throw th6;
                        }
                    } catch (Throwable th8) {
                        file = file4;
                        if (file != null) {
                            file.delete();
                        }
                        d3Var2.a.post(new RunnableC0024g(b3Var2, th8, 15));
                        return;
                    }
                } catch (Throwable th9) {
                }
                break;
        }
    }
}
