package dev.local.newsoul;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Future;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: dev.local.newsoul.ViewOnClickListenerC0046l1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnClickListenerC0046l1 implements View.OnClickListener {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Object d;

    public /* synthetic */ ViewOnClickListenerC0046l1(Activity activity, boolean z, RunnableC0024g runnableC0024g) {
        this.b = activity;
        this.c = z;
        this.d = runnableC0024g;
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0357  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String lowerCase;
        switch (this.a) {
            case 0:
                boolean z = this.c;
                Activity activity = this.b;
                if (z) {
                    String stringExtra = activity.getIntent().getStringExtra("url");
                    if (stringExtra == null || stringExtra.isEmpty()) {
                        Toast.makeText(activity, "原图地址不可用", 0).show();
                        return;
                    }
                    TextView textView = (TextView) this.d;
                    textView.setEnabled(false);
                    textView.setText("保存中…");
                    try {
                        Object objInvoke = Q0.T.loadClass("com.bumptech.glide.Glide").getMethod("with", Context.class).invoke(null, activity);
                        Object objInvoke2 = objInvoke.getClass().getMethod("asBitmap", null).invoke(objInvoke, null);
                        Class<?> cls = objInvoke2.getClass();
                        for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                            try {
                                Method declaredMethod = superclass.getDeclaredMethod("load", Object.class);
                                declaredMethod.setAccessible(true);
                                Object objInvoke3 = declaredMethod.invoke(objInvoke2, stringExtra);
                                Object objInvoke4 = objInvoke3.getClass().getMethod("submit", null).invoke(objInvoke3, null);
                                if (!(objInvoke4 instanceof Future)) {
                                    throw new IllegalStateException("Glide target is not Future");
                                }
                                new Thread(new H(objInvoke4, activity, (Object) textView, 4), "NewSoul-privacy-photo-save").start();
                                return;
                            } catch (NoSuchMethodException unused) {
                            }
                        }
                        throw new NoSuchMethodException(cls.getName().concat(".load"));
                    } catch (Throwable th) {
                        textView.setEnabled(true);
                        textView.setText("保存原图");
                        Q0.S.log(6, "SoulHook102", "privacy photo download setup failed", th);
                        Toast.makeText(activity, "保存功能当前不可用", 0).show();
                        return;
                    }
                }
                String stringExtra2 = activity.getIntent().getStringExtra("url");
                if (stringExtra2 == null || stringExtra2.isEmpty()) {
                    Toast.makeText(activity, "视频地址不可用", 0).show();
                    return;
                }
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    int iIndexOf = stringExtra2.indexOf(63);
                    String strSubstring = iIndexOf >= 0 ? stringExtra2.substring(0, iIndexOf) : stringExtra2;
                    int iLastIndexOf = strSubstring.lastIndexOf(47);
                    int iLastIndexOf2 = strSubstring.lastIndexOf(46);
                    if (iLastIndexOf2 <= iLastIndexOf || strSubstring.length() - iLastIndexOf2 > 6) {
                        lowerCase = ".mp4";
                    } else {
                        lowerCase = strSubstring.substring(iLastIndexOf2).toLowerCase(Locale.ROOT);
                        if (!lowerCase.matches("\\.(mp4|mov|m4v|webm)")) {
                            lowerCase = ".mp4";
                        }
                    }
                    DownloadManager.Request destinationInExternalPublicDir = new DownloadManager.Request(Uri.parse(stringExtra2)).setTitle("New Soul 隐私视频").setDescription("正在保存视频").setMimeType("video/mp4").setNotificationVisibility(1).setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES, "New Soul/" + ("NewSoul_" + jCurrentTimeMillis + lowerCase));
                    DownloadManager downloadManager = (DownloadManager) activity.getSystemService("download");
                    if (downloadManager == null) {
                        throw new IllegalStateException("DownloadManager unavailable");
                    }
                    long jEnqueue = downloadManager.enqueue(destinationInExternalPublicDir);
                    Toast.makeText(activity, "已开始保存到 Movies/New Soul", 0).show();
                    Q0.w("privacy video download enqueued: id=" + jEnqueue);
                    return;
                } catch (Throwable th2) {
                    Q0.S.log(6, "SoulHook102", "privacy video download failed", th2);
                    Toast.makeText(activity, "视频保存失败，请稍后重试", 0).show();
                    return;
                }
            default:
                boolean z2 = this.c;
                Activity activity2 = this.b;
                C0067q2 c0067q2 = new C0067q2(activity2, z2);
                SharedPreferences sharedPreferences = activity2.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0);
                c0067q2.t = "输入时查找已有词，点击“添加”加入草稿；也可粘贴多行批量添加。点击词条右侧可删除。只有已添加的词会在点击“保存”后生效。\n\n最多 100 个词，每词 100 字符。英文不区分大小写。正文、标题或话题包含任意词即隐藏。";
                final ArrayList arrayList = new ArrayList();
                for (String str : sharedPreferences.getString("feed_keywords_v1", "").split("\\R")) {
                    if (!str.trim().isEmpty()) {
                        arrayList.add(str.trim());
                    }
                }
                final ArrayList arrayList2 = new ArrayList(arrayList);
                Context context = c0067q2.g;
                EditText editText = new EditText(context);
                editText.setTextSize(16.0f);
                int i = c0067q2.i;
                editText.setTextColor(i);
                int i2 = c0067q2.j;
                editText.setHintTextColor(i2);
                L2 l2 = c0067q2.c;
                l2.getClass();
                l2.b(editText, 8, L2.e(8));
                editText.setPadding(AbstractC0090w2.i(activity2, 14), AbstractC0090w2.i(activity2, 12), AbstractC0090w2.i(activity2, 14), AbstractC0090w2.i(activity2, 12));
                editText.setGravity(8388627);
                editText.setInputType(131073);
                editText.setMinLines(1);
                editText.setMaxLines(4);
                editText.setMinHeight(AbstractC0090w2.i(activity2, 48));
                editText.setHint("查找或添加屏蔽词");
                editText.setContentDescription("查找或添加屏蔽词");
                TextView textViewM = AbstractC0090w2.m(c0067q2, "添加", "添加到屏蔽词列表");
                textViewM.setMinHeight(AbstractC0090w2.i(activity2, 48));
                textViewM.setMinWidth(AbstractC0090w2.i(activity2, 64));
                textViewM.setPadding(AbstractC0090w2.i(activity2, 18), AbstractC0090w2.i(activity2, 8), AbstractC0090w2.i(activity2, 18), AbstractC0090w2.i(activity2, 8));
                textViewM.setSelected(true);
                l2.b(textViewM, 5, L2.e(5));
                AbstractC0090w2.g(textViewM);
                C0007b2 c0007b2 = new C0007b2(context, activity2, textViewM, editText);
                c0007b2.setGravity(16);
                c0007b2.addView(editText, new LinearLayout.LayoutParams(0, -2, 1.0f));
                c0007b2.addView(textViewM, new LinearLayout.LayoutParams(-2, -2));
                LinearLayout linearLayout = c0067q2.h;
                linearLayout.addView(c0007b2, new LinearLayout.LayoutParams(-1, -2));
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setGravity(16);
                linearLayout2.setPadding(0, AbstractC0090w2.i(activity2, 18), 0, AbstractC0090w2.i(activity2, 8));
                linearLayout2.addView(AbstractC0090w2.n(context, "屏蔽词", 16, i), new LinearLayout.LayoutParams(0, -2, 1.0f));
                TextView textViewN = AbstractC0090w2.n(context, "", 13, i2);
                linearLayout2.addView(textViewN);
                linearLayout.addView(linearLayout2);
                String[] strArr = {null};
                R0 r0 = new R0(context);
                linearLayout.addView(r0, new LinearLayout.LayoutParams(-1, -2));
                TextView textViewM2 = AbstractC0090w2.m(c0067q2, "", "撤销上一次删除屏蔽词");
                textViewM2.setMinHeight(AbstractC0090w2.i(activity2, 48));
                textViewM2.setVisibility(8);
                linearLayout.addView(textViewM2, new LinearLayout.LayoutParams(-1, -2));
                String[] strArr2 = {null};
                int[] iArr = {0};
                RunnableC0015d2 runnableC0015d2 = new RunnableC0015d2(editText, arrayList, textViewM, r0, c0067q2, activity2, strArr, strArr2, iArr, textViewM2, textViewN);
                textViewM2.setOnClickListener(new W1(strArr2, arrayList, editText, iArr, textViewM2, runnableC0015d2));
                editText.addTextChangedListener(new C0003a2(runnableC0015d2, 1));
                textViewM.setOnClickListener(new A0(3, new C0076t0(editText, arrayList, strArr, runnableC0015d2, 1)));
                runnableC0015d2.run();
                c0067q2.c(new X1(arrayList, arrayList));
                c0067q2.g("编辑屏蔽词", "保存");
                c0067q2.e.getWindow().setSoftInputMode(19);
                linearLayout.setFocusableInTouchMode(true);
                linearLayout.requestFocus();
                c0067q2.e.getButton(-1).setOnClickListener(new W1(arrayList, sharedPreferences, (RunnableC0024g) ((Runnable) this.d), activity2, c0067q2, editText));
                return;
        }
    }

    public /* synthetic */ ViewOnClickListenerC0046l1(boolean z, Activity activity, TextView textView) {
        this.c = z;
        this.b = activity;
        this.d = textView;
    }
}
