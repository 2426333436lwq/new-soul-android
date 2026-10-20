package dev.local.newsoul;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class H implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ H(Activity activity, Object obj, Object obj2, int i) {
        this.a = i;
        this.c = activity;
        this.b = obj;
        this.d = obj2;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0162 A[DONT_GENERATE] */
    @Override // java.lang.Runnable
    public final void run() {
        String str;
        final C0067q2 c0067q2;
        int i = 1;
        final int i2 = 0;
        Object obj = this.c;
        Object obj2 = this.d;
        Object obj3 = this.b;
        switch (this.a) {
            case 0:
                Uri uri = (Uri) obj3;
                final P p = (P) obj2;
                final Activity activity = (Activity) obj;
                Handler handler = S.b;
                try {
                    if (!"content".equals(uri.getScheme())) {
                        throw new IllegalArgumentException();
                    }
                    if (p.c != null) {
                        OutputStream outputStreamOpenOutputStream = activity.getContentResolver().openOutputStream(uri, "wt");
                        try {
                            if (outputStreamOpenOutputStream == null) {
                                throw new IOException();
                            }
                            outputStreamOpenOutputStream.write(p.c.getBytes(StandardCharsets.UTF_8));
                            outputStreamOpenOutputStream.flush();
                            outputStreamOpenOutputStream.close();
                            handler.post(new J(activity, p, i2));
                            return;
                        } catch (Throwable th) {
                            if (outputStreamOpenOutputStream == null) {
                                throw th;
                            }
                            try {
                                outputStreamOpenOutputStream.close();
                                throw th;
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                                throw th;
                            }
                        }
                    }
                    InputStream inputStreamOpenInputStream = activity.getContentResolver().openInputStream(uri);
                    try {
                        if (inputStreamOpenInputStream == null) {
                            throw new IOException();
                        }
                        LinkedHashMap linkedHashMap = G.a;
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i3 = inputStreamOpenInputStream.read(bArr);
                            if (i3 == -1) {
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                inputStreamOpenInputStream.close();
                                handler.post(new K(activity, p, G.c(StandardCharsets.UTF_8.newDecoder().decode(ByteBuffer.wrap(byteArray)).toString())));
                                return;
                            } else {
                                if (i3 > 131072 - byteArrayOutputStream.size()) {
                                    throw new IOException();
                                }
                                byteArrayOutputStream.write(bArr, 0, i3);
                            }
                        }
                    } catch (Throwable th3) {
                        if (inputStreamOpenInputStream == null) {
                            throw th3;
                        }
                        try {
                            inputStreamOpenInputStream.close();
                            throw th3;
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                            throw th3;
                        }
                    }
                } catch (Exception unused) {
                    handler.post( new J(activity, p, i));
                    return;
                }
            case 1:
                Uri uri2 = (Uri) obj3;
                Activity activity2 = (Activity) obj;
                C0001a0 c0001a0 = (C0001a0) obj2;
                try {
                    if (!"content".equals(uri2.getScheme())) {
                        throw new IOException();
                    }
                    OutputStream outputStreamOpenOutputStream2 = activity2.getContentResolver().openOutputStream(uri2, "wt");
                    try {
                        if (outputStreamOpenOutputStream2 == null) {
                            throw new IOException();
                        }
                        outputStreamOpenOutputStream2.write(c0001a0.c);
                        outputStreamOpenOutputStream2.flush();
                        outputStreamOpenOutputStream2.close();
                        str = "记录已导出，可发送给作者";
                        AbstractC0025g0.b.post(new H(activity2, (Object) c0001a0, (Object) str, 2));
                        return;
                    } catch (Throwable th5) {
                        if (outputStreamOpenOutputStream2 == null) {
                            throw th5;
                        }
                        try {
                            outputStreamOpenOutputStream2.close();
                            throw th5;
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                            throw th5;
                        }
                    }
                } catch (Exception unused2) {
                    str = "保存失败，请重新选择位置";
                }
                break;
            case 2:
                AbstractC0025g0.b((Activity) obj, (C0001a0) obj3, (String) obj2);
                return;
            case 3:
                Activity activity3 = (Activity) obj;
                C0021f0 c0021f0 = (C0021f0) obj3;
                C0001a0 c0001a1 = (C0001a0) obj2;
                Handler handler2 = AbstractC0025g0.b;
                try {
                    handler2.post(new Z(activity3, c0021f0, c0001a1, V.e(), 0));
                    return;
                } catch (Exception unused3) {
                    handler2.post(new RunnableC0024g(activity3, c0001a1, i));
                    return;
                }
            case 4:
                Activity activity4 = (Activity) obj;
                TextView textView = (TextView) obj2;
                try {
                    Object obj4 = ((Future) obj3).get();
                    if (!(obj4 instanceof Bitmap)) {
                        throw new IllegalStateException("Resource is not bitmap");
                    }
                    Uri uriT = Q0.T(activity4, (Bitmap) obj4);
                    activity4.runOnUiThread(new H(textView, activity4, uriT));
                    Q0.w("privacy photo saved: ".concat(String.valueOf(uriT)));
                    return;
                } catch (Throwable th7) {
                    Q0.S.log(6, "SoulHook102", "privacy photo download failed", th7);
                    activity4.runOnUiThread(new RunnableC0024g(textView, activity4, 4));
                    return;
                }
            case 5:
                TextView textView2 = (TextView) obj2;
                textView2.setEnabled(true);
                textView2.setText("保存原图");
                Toast.makeText((Activity) obj, ((Uri) obj3) == null ? "保存失败" : "已保存到 Pictures/New Soul", 0).show();
                return;
            case 6:
                C0077t1 c0077t1 = (C0077t1) obj3;
                Activity activity5 = (Activity) obj;
                Handler handler3 = AbstractC0081u1.a;
                try {
                    AbstractC0081u1.h(c0077t1.a);
                    C0093x1 c0093x1 = new C0093x1(activity5.getFilesDir(), c0077t1.a, 0);
                    boolean z = c0077t1.f;
                    String str2 = c0077t1.b;
                    if (z) {
                        c0093x1.i(str2);
                    } else {
                        c0093x1.f(str2, c0077t1.c);
                    }
                    handler3.post(new RunnableC0064q(c0077t1, activity5, z, obj2));
                    return;
                } catch (Exception e) {
                    handler3.post(new H((Object) c0077t1, activity5, (Object) e, 7));
                    return;
                }
            case 7:
                ((C0077t1) obj3).g = false;
                AbstractC0081u1.e((Activity) obj, (Exception) obj2);
                return;
            case 8:
                C0067q2 c0067q3 = (C0067q2) obj3;
                if (AbstractC0081u1.i(c0067q3)) {
                    LinearLayout linearLayout = c0067q3.h;
                    linearLayout.removeAllViews();
                    linearLayout.addView(AbstractC0090w2.n(c0067q3.g, "读取失败，原有记录已保留", 14, c0067q3.j));
                    TextView textViewA = AbstractC0081u1.a(c0067q3, "重新加载");
                    textViewA.setOnClickListener(new A0(1, (Runnable[]) obj2));
                    linearLayout.addView(textViewA);
                    Log.w("SoulHook102", "bookmark list read failed", (Exception) obj);
                    return;
                }
                return;
            case 9:
                ((TextView) obj3).setEnabled(true);
                AbstractC0081u1.e((Activity) obj, (Exception) obj2);
                return;
            case 10:
                ((C1) obj3).b(((SharedPreferences) obj2).getString("chat_recall_style", "below"), (String[]) obj, false);
                return;
            case 11:
                C0067q2 c0067q4 = (C0067q2) obj3;
                if (c0067q4.e.isShowing()) {
                    Activity activity6 = (Activity) obj;
                    if (activity6.isFinishing() || activity6.isDestroyed()) {
                        return;
                    }
                    LinearLayout linearLayout2 = (LinearLayout) obj2;
                    linearLayout2.removeAllViews();
                    int i4 = c0067q4.i;
                    Context context = c0067q4.g;
                    linearLayout2.addView(AbstractC0090w2.n(context, "检测失败，请重新检测", 16, i4));
                    linearLayout2.addView(AbstractC0090w2.n(context, "未能完成本次检查，已有设置未改变。", 13, c0067q4.j));
                    c0067q4.e.getButton(-3).setVisibility(0);
                    c0067q4.e.getButton(-3).setEnabled(true);
                    return;
                }
                return;
            case 12:
                File file = (File) obj2;
                h3 h3Var = (h3) obj;
                b3 b3Var = (b3) obj3;
                Activity activity7 = b3Var.c;
                try {
                    try {
                        b3Var.j();
                        Object objG = d3.g(b3Var.a, "Q");
                        if (objG == null) {
                            throw new IllegalStateException("聊天尚未准备好");
                        }
                        try {
                            b3Var.q.e.invoke(objG, file.getAbsolutePath(), Integer.valueOf(h3Var.c), null);
                            d3.i(activity7, "已提交发送，请查看聊天中的消息状态");
                            Log.i("SoulHook102", "voice library send submitted explicitly");
                            break;
                        } catch (Throwable th8) {
                            d3.f(activity7, "发送未完成，请查看消息状态", th8);
                        }
                        return;
                    } catch (Throwable th9) {
                        i = 0;
                        d3.f(activity7, "发送未完成，请查看消息状态", th9);
                        return;
                    }
                } finally {
                    if (i == 0) {
                        file.delete();
                    }
                    b3Var.b();
                }
            default:
                final b3 b3Var2 = (b3) obj3;
                if (b3Var2.m || b3Var2.h != (c0067q2 = (C0067q2) obj2)) {
                    return;
                }
                try {
                    b3Var2.j();
                    c0067q2.h.removeAllViews();
                    Context context2 = c0067q2.g;
                    EditText editText = new EditText(context2);
                    editText.setTextColor(c0067q2.i);
                    int i5 = c0067q2.j;
                    editText.setHintTextColor(i5);
                    editText.setHint("搜索备注");
                    editText.setContentDescription("搜索语音备注");
                    editText.setSingleLine(true);
                    editText.setMinHeight(AbstractC0090w2.i(context2, 48));
                    editText.setTextSize(15.0f);
                    editText.setPadding(AbstractC0090w2.i(context2, 14), AbstractC0090w2.i(context2, 10), AbstractC0090w2.i(context2, 14), AbstractC0090w2.i(context2, 10));
                    L2 l2 = c0067q2.c;
                    l2.getClass();
                    l2.b(editText, 8, L2.e(8));
                    Drawable drawableMutate = context2.getDrawable(R.drawable.ic_menu_search).mutate();
                    drawableMutate.setTint(i5);
                    int i6 = AbstractC0090w2.i(context2, 20);
                    drawableMutate.setBounds(0, 0, i6, i6);
                    editText.setCompoundDrawablesRelative(drawableMutate, null, null, null);
                    editText.setCompoundDrawablePadding(AbstractC0090w2.i(context2, 8));
                    editText.setText(b3Var2.k);
                    editText.setSelection(editText.length());
                    LinearLayout linearLayout3 = c0067q2.h;
                    linearLayout3.addView(editText, new LinearLayout.LayoutParams(-1, -2));
                    LinearLayout linearLayout4 = new LinearLayout(context2);
                    linearLayout4.setOrientation(1);
                    linearLayout4.setPadding(0, AbstractC0090w2.i(context2, 12), 0, 0);
                    linearLayout3.addView(linearLayout4, new LinearLayout.LayoutParams(-1, -2));
                    List list = (List) obj;
                    ArrayList arrayList = (ArrayList) list;
                    b3Var2.h(c0067q2, linearLayout4, arrayList);
                    editText.addTextChangedListener(new a3(b3Var2, c0067q2, linearLayout4, arrayList));
                    linearLayout4.setFocusableInTouchMode(true);
                    linearLayout4.requestFocus();
                    if (linearLayout3.getParent() instanceof ScrollView) {
                        final ScrollView scrollView = (ScrollView) linearLayout3.getParent();
                        final int i7 = b3Var2.j;
                        scrollView.post(new Y2(b3Var2, c0067q2, scrollView, i7));
                    }
                    Log.i("SoulHook102", "voice library shown; count=" + list.size());
                    return;
                } catch (Exception unused4) {
                    b3Var2.b();
                    return;
                }
        }
    }

    public /* synthetic */ H(TextView textView, Activity activity, Uri uri) {
        this.a = 5;
        this.d = textView;
        this.c = activity;
        this.b = uri;
    }

    public /* synthetic */ H(Object obj, Activity activity, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = activity;
        this.d = obj2;
    }

    public /* synthetic */ H(Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.b = obj;
        this.d = obj2;
        this.c = obj3;
    }
}
