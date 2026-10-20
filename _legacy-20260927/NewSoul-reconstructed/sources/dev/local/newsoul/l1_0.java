/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.DownloadManager
 *  android.app.DownloadManager$Request
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.net.Uri
 *  android.os.Environment
 *  android.text.TextWatcher
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.A0;
import dev.local.newsoul.H;
import dev.local.newsoul.L2;
import dev.local.newsoul.Q0;
import dev.local.newsoul.R0;
import dev.local.newsoul.W1;
import dev.local.newsoul.X1;
import dev.local.newsoul.a2_0;
import dev.local.newsoul.b2_0;
import dev.local.newsoul.d2_0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.t0_0;
import dev.local.newsoul.w2;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Future;

/*
 * Renamed from dev.local.newsoul.l1
 */
public final class l1_0
implements View.OnClickListener {
    public final int a;
    public final Activity b;
    public final boolean c;
    public final Object d;

    public /* synthetic */ l1_0(Activity activity, boolean bl, g_0 g_02) {
        this.a = 1;
        this.b = activity;
        this.c = bl;
        this.d = g_02;
    }

    public /* synthetic */ l1_0(boolean bl, Activity activity, TextView textView) {
        this.a = 0;
        this.c = bl;
        this.b = activity;
        this.d = textView;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void onClick(View object) {
        Object object2;
        Object object3;
        TextView textView;
        Object object4;
        Activity activity;
        block20: {
            switch (this.a) {
                default: {
                    boolean bl = this.c;
                    Activity activity2 = this.b;
                    q2 q22 = new q2(activity2, bl);
                    object = activity2.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
                    q22.t = "\u8f93\u5165\u65f6\u67e5\u627e\u5df2\u6709\u8bcd\uff0c\u70b9\u51fb\u201c\u6dfb\u52a0\u201d\u52a0\u5165\u8349\u7a3f\uff1b\u4e5f\u53ef\u7c98\u8d34\u591a\u884c\u6279\u91cf\u6dfb\u52a0\u3002\u70b9\u51fb\u8bcd\u6761\u53f3\u4fa7\u53ef\u5220\u9664\u3002\u53ea\u6709\u5df2\u6dfb\u52a0\u7684\u8bcd\u4f1a\u5728\u70b9\u51fb\u201c\u4fdd\u5b58\u201d\u540e\u751f\u6548\u3002\n\n\u6700\u591a 100 \u4e2a\u8bcd\uff0c\u6bcf\u8bcd 100 \u5b57\u7b26\u3002\u82f1\u6587\u4e0d\u533a\u5206\u5927\u5c0f\u5199\u3002\u6b63\u6587\u3001\u6807\u9898\u6216\u8bdd\u9898\u5305\u542b\u4efb\u610f\u8bcd\u5373\u9690\u85cf\u3002";
                    ArrayList<String> arrayList = new ArrayList<String>();
                    EditText editText = object.getString("feed_keywords_v1", "").split("\\R");
                    int n = ((String[])editText).length;
                    int n2 = 0;
                    while (true) {
                        Object object5;
                        if (n2 >= n) {
                            object5 = new ArrayList(arrayList);
                            Object object6 = q22.g;
                            editText = new EditText(object6);
                            editText.setTextSize(16.0f);
                            n2 = q22.i;
                            editText.setTextColor(n2);
                            n = q22.j;
                            editText.setHintTextColor(n);
                            L2 l2 = q22.c;
                            l2.getClass();
                            l2.b((View)editText, 8, L2.e(8));
                            editText.setPadding(w2.i((Context)activity2, 14), w2.i((Context)activity2, 12), w2.i((Context)activity2, 14), w2.i((Context)activity2, 12));
                            editText.setGravity(8388627);
                            editText.setInputType(131073);
                            editText.setMinLines(1);
                            editText.setMaxLines(4);
                            editText.setMinHeight(w2.i((Context)activity2, 48));
                            editText.setHint((CharSequence)"\u67e5\u627e\u6216\u6dfb\u52a0\u5c4f\u853d\u8bcd");
                            editText.setContentDescription((CharSequence)"\u67e5\u627e\u6216\u6dfb\u52a0\u5c4f\u853d\u8bcd");
                            TextView textView2 = w2.m(q22, "\u6dfb\u52a0", "\u6dfb\u52a0\u5230\u5c4f\u853d\u8bcd\u5217\u8868");
                            textView2.setMinHeight(w2.i((Context)activity2, 48));
                            textView2.setMinWidth(w2.i((Context)activity2, 64));
                            textView2.setPadding(w2.i((Context)activity2, 18), w2.i((Context)activity2, 8), w2.i((Context)activity2, 18), w2.i((Context)activity2, 8));
                            textView2.setSelected(true);
                            l2.b((View)textView2, 5, L2.e(5));
                            w2.g((View)textView2);
                            Object object7 = new b2_0((Context)object6, activity2, textView2, editText);
                            object7.setGravity(16);
                            object7.addView((View)editText, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
                            object7.addView((View)textView2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
                            l2 = q22.h;
                            l2.addView((View)object7, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                            String[] stringArray = new LinearLayout(object6);
                            stringArray.setGravity(16);
                            stringArray.setPadding(0, w2.i((Context)activity2, 18), 0, w2.i((Context)activity2, 8));
                            stringArray.addView((View)w2.n(object6, "\u5c4f\u853d\u8bcd", 16, n2), (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -2, 1.0f));
                            object7 = w2.n(object6, "", 13, n);
                            stringArray.addView((View)object7);
                            l2.addView((View)stringArray);
                            stringArray = new String[]{null};
                            R0 r0 = new R0((Context)object6);
                            l2.addView((View)r0, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                            TextView textView3 = w2.m(q22, "", "\u64a4\u9500\u4e0a\u4e00\u6b21\u5220\u9664\u5c4f\u853d\u8bcd");
                            textView3.setMinHeight(w2.i((Context)activity2, 48));
                            textView3.setVisibility(8);
                            l2.addView((View)textView3, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
                            String[] stringArray2 = new String[]{null};
                            object6 = new int[1];
                            object6[0] = (Context)false;
                            object7 = new d2_0(editText, arrayList, textView2, r0, q22, activity2, stringArray, stringArray2, (int[])object6, textView3, (TextView)object7);
                            textView3.setOnClickListener((View.OnClickListener)new W1(stringArray2, arrayList, editText, (int[])object6, textView3, (d2_0)object7));
                            editText.addTextChangedListener((TextWatcher)new a2_0((Runnable)object7, 1));
                            textView2.setOnClickListener((View.OnClickListener)new A0(3, new t0_0(editText, arrayList, stringArray, object7, 1)));
                            ((d2_0)object7).run();
                            q22.c(new X1(arrayList, (ArrayList)object5));
                            q22.g("\u7f16\u8f91\u5c4f\u853d\u8bcd", "\u4fdd\u5b58");
                            q22.e.getWindow().setSoftInputMode(19);
                            l2.setFocusableInTouchMode(true);
                            l2.requestFocus();
                            q22.e.getButton(-1).setOnClickListener((View.OnClickListener)new W1(arrayList, (SharedPreferences)object, (g_0)((Runnable)this.d), activity2, q22, editText));
                            return;
                        }
                        object5 = editText[n2];
                        if (!((String)object5).trim().isEmpty()) {
                            arrayList.add(((String)object5).trim());
                        }
                        ++n2;
                    }
                }
                case 0: 
            }
            boolean bl = this.c;
            activity = this.b;
            if (bl) {
                object4 = activity.getIntent().getStringExtra("url");
                if (object4 != null && !((String)object4).isEmpty()) {
                    textView = (TextView)this.d;
                    textView.setEnabled(false);
                    textView.setText((CharSequence)"\u4fdd\u5b58\u4e2d\u2026");
                    try {
                        object = Q0.T.loadClass("com.bumptech.glide.Glide").getMethod("with", Context.class).invoke(null, activity);
                        object3 = object.getClass().getMethod("asBitmap", null).invoke(object, null);
                        object = object2 = object3.getClass();
                        break block20;
                    }
                    catch (Throwable throwable) {}
                    textView.setEnabled(true);
                    textView.setText((CharSequence)"\u4fdd\u5b58\u539f\u56fe");
                    Q0.S.log(6, "SoulHook102", "privacy photo download setup failed", throwable);
                    Toast.makeText((Context)activity, (CharSequence)"\u4fdd\u5b58\u529f\u80fd\u5f53\u524d\u4e0d\u53ef\u7528", (int)0).show();
                    return;
                }
                Toast.makeText((Context)activity, (CharSequence)"\u539f\u56fe\u5730\u5740\u4e0d\u53ef\u7528", (int)0).show();
                return;
            }
            String string = activity.getIntent().getStringExtra("url");
            if (string != null && !string.isEmpty()) {
                Throwable throwable2;
                block21: {
                    try {
                        long l = System.currentTimeMillis();
                        int n = string.indexOf(63);
                        object = n >= 0 ? string.substring(0, n) : string;
                        int n3 = ((String)object).lastIndexOf(47);
                        n = ((String)object).lastIndexOf(46);
                        if (n <= n3 || ((String)object).length() - n > 6 || !((String)(object = ((String)object).substring(n).toLowerCase(Locale.ROOT))).matches("\\.(mp4|mov|m4v|webm)")) {
                            object = ".mp4";
                        }
                        StringBuilder stringBuilder = new StringBuilder("NewSoul_");
                        stringBuilder.append(l);
                        stringBuilder.append((String)object);
                        object = stringBuilder.toString();
                        stringBuilder = new DownloadManager.Request(Uri.parse((String)string));
                        DownloadManager.Request request = stringBuilder.setTitle("New Soul \u9690\u79c1\u89c6\u9891").setDescription((CharSequence)"\u6b63\u5728\u4fdd\u5b58\u89c6\u9891").setMimeType("video/mp4").setNotificationVisibility(1);
                        string = Environment.DIRECTORY_MOVIES;
                        stringBuilder = new StringBuilder("New Soul/");
                        stringBuilder.append((String)object);
                        object = request.setDestinationInExternalPublicDir(string, stringBuilder.toString());
                        string = (DownloadManager)activity.getSystemService("download");
                        if (string != null) {
                            l = string.enqueue((DownloadManager.Request)object);
                            Toast.makeText((Context)activity, (CharSequence)"\u5df2\u5f00\u59cb\u4fdd\u5b58\u5230 Movies/New Soul", (int)0).show();
                            object = new StringBuilder("privacy video download enqueued: id=");
                            ((StringBuilder)object).append(l);
                            Q0.w(((StringBuilder)object).toString());
                            return;
                        }
                    }
                    catch (Throwable throwable2) {
                        break block21;
                    }
                    object = new IllegalStateException("DownloadManager unavailable");
                    throw object;
                }
                Q0.S.log(6, "SoulHook102", "privacy video download failed", throwable2);
                Toast.makeText((Context)activity, (CharSequence)"\u89c6\u9891\u4fdd\u5b58\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", (int)0).show();
                return;
            }
            Toast.makeText((Context)activity, (CharSequence)"\u89c6\u9891\u5730\u5740\u4e0d\u53ef\u7528", (int)0).show();
            return;
        }
        while (true) {
            Method method;
            if (object == null) {
                object = new NoSuchMethodException(((Class)object2).getName().concat(".load"));
                throw object;
            }
            try {
                method = ((Class)object).getDeclaredMethod("load", Object.class);
                ((AccessibleObject)method).setAccessible(true);
            }
            catch (NoSuchMethodException noSuchMethodException) {
                object = ((Class)object).getSuperclass();
                continue;
            }
            object = method.invoke(object3, object4);
            object = object.getClass().getMethod("submit", null).invoke(object, null);
            if (object instanceof Future) {
                object2 = (Future)object;
                object = new H(object2, activity, (Object)textView, 4);
                object4 = new Thread((Runnable)object, "NewSoul-privacy-photo-save");
                ((Thread)object4).start();
                return;
            }
            object = new IllegalStateException("Glide target is not Future");
            throw object;
        }
    }
}

