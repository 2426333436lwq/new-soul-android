package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Iterator;

/* JADX INFO: renamed from: dev.local.newsoul.C0086v2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0086v2 {
    public static final String[] n = {"聊天", "广场", "主页", "广告", "其他"};
    public final C0067q2 a;
    public final Z0 e;
    public final HorizontalScrollView f;
    public final C0078t2 g;
    public final M0 h;
    public int j;
    public int k;
    public float l;
    public boolean m;
    public final C0067q2[] b = new C0067q2[5];
    public final ScrollView[] c = new ScrollView[5];
    public final TextView[] d = new TextView[5];
    public int i = -1;

    public C0086v2(Activity activity, boolean z) {
        C0067q2 c0067q2 = new C0067q2(activity, z);
        this.a = c0067q2;
        c0067q2.t = "长按 Soul 底部导航栏的图标，可以快速打开插件设置。";
        L2 l2 = c0067q2.c;
        l2.c = true;
        C0041k0 c0041k0 = new C0041k0(this);
        Context context = c0067q2.g;
        this.e = new Z0(context, c0041k0);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        this.f = horizontalScrollView;
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.setFillViewport(true);
        M0 m0 = new M0(context, l2, false);
        this.h = m0;
        m0.setPadding(AbstractC0090w2.i(context, 4), AbstractC0090w2.i(context, 4), AbstractC0090w2.i(context, 4), AbstractC0090w2.i(context, 4));
        C0078t2 c0078t2 = new C0078t2(this, context, context);
        this.g = c0078t2;
        c0078t2.setGravity(16);
        AbstractC0090w2.v(c0078t2, null, 5);
        c0078t2.setContentDescription("设置分类");
        for (int i = 0; i < 5; i++) {
            TextView textViewN = AbstractC0090w2.n(context, n[i], 15, this.a.j);
            this.d[i] = textViewN;
            textViewN.setSingleLine(true);
            textViewN.setGravity(17);
            textViewN.setPadding(AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 8), AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 8));
            textViewN.setMinHeight(AbstractC0090w2.i(context, 48));
            AbstractC0090w2.w(textViewN, i);
            textViewN.setOnClickListener(new ViewOnClickListenerC0092x0(this, i, 2));
            this.g.addView(textViewN, new LinearLayout.LayoutParams(-2, -2));
        }
        this.h.addView(this.g, new FrameLayout.LayoutParams(-1, -2));
        this.f.addView(this.h, new FrameLayout.LayoutParams(-2, -2));
        this.g.addOnLayoutChangeListener(new s2(this));
    }

    public final void a() {
        int i = this.j;
        TextView[] textViewArr = this.d;
        TextView textView = textViewArr[i];
        TextView textView2 = textViewArr[this.k];
        if (textView == null || textView2 == null || textView.getWidth() == 0) {
            return;
        }
        float left = ((textView2.getLeft() - textView.getLeft()) * this.l) + textView.getLeft();
        int iRound = Math.round(((textView2.getWidth() - textView.getWidth()) * this.l) + textView.getWidth());
        this.h.c(this.g.getLeft() + left, iRound, this.m);
        C0067q2 c0067q2 = this.a;
        boolean z = c0067q2.b;
        int iQ = AbstractC0090w2.q(z ? AbstractC0090w2.b : -16746133, z, z ? -14596798 : -2493459);
        int i2 = 0;
        while (i2 < textViewArr.length) {
            float f = i2 == this.j ? 1.0f - this.l : 0.0f;
            if (i2 == this.k) {
                f += this.l;
            }
            TextView textView3 = textViewArr[i2];
            int i3 = c0067q2.j;
            textView3.setTextColor(Color.rgb(Math.round(((Color.red(iQ) - Color.red(i3)) * f) + Color.red(i3)), Math.round(((Color.green(iQ) - Color.green(i3)) * f) + Color.green(i3)), Math.round(((Color.blue(iQ) - Color.blue(i3)) * f) + Color.blue(i3))));
            i2++;
        }
        int i4 = AbstractC0090w2.i(c0067q2.g, 8) + Math.round(left) + iRound;
        HorizontalScrollView horizontalScrollView = this.f;
        if (left < horizontalScrollView.getScrollX()) {
            horizontalScrollView.scrollTo(Math.round(left), 0);
            return;
        }
        if (i4 > horizontalScrollView.getWidth() + horizontalScrollView.getScrollX()) {
            horizontalScrollView.scrollTo(i4 - horizontalScrollView.getWidth(), 0);
        }
    }

    public final void b(int i, boolean z) {
        Z0 z0 = this.e;
        if (i < 0) {
            z0.getClass();
            return;
        }
        if (i >= z0.b.length) {
            return;
        }
        z0.b();
        if (z0.f < 0 || !z) {
            z0.c(i);
            return;
        }
        z0.a();
        int i2 = z0.g;
        int i3 = z0.h;
        if (i2 != i3 || z0.k) {
            if (z0.k || !(i == i3 || i == i2)) {
                z0.i(i);
                return;
            } else {
                z0.k(i == i3);
                return;
            }
        }
        int i4 = z0.f;
        if (i4 == i) {
            return;
        }
        z0.g = i4;
        z0.h = i;
        z0.i = 0.0f;
        z0.j = Math.abs(i - i4) > 1;
        z0.g(z0.g);
        z0.g(z0.h);
        z0.j();
        z0.k(true);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void c() {
        C0067q2 c0067q2 = this.a;
        C0082u2 c0082u2 = new C0082u2(this, c0067q2.g);
        c0082u2.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        Context context = c0067q2.g;
        int i = AbstractC0090w2.i(context, 16);
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        layoutParams.topMargin = AbstractC0090w2.i(context, 4);
        layoutParams.bottomMargin = AbstractC0090w2.i(context, 8);
        c0082u2.addView(this.f, layoutParams);
        c0082u2.addView(this.e, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        b(0, false);
        c0067q2.i("New Soul", "完成", c0082u2);
        Iterator it = c0067q2.p.iterator();
        while (it.hasNext()) {
            ((C0067q2) it.next()).e = c0067q2.e;
        }
        Activity activity = c0067q2.a;
        int iWidth = activity.getResources().getDisplayMetrics().widthPixels;
        if (Build.VERSION.SDK_INT >= 30) {
            iWidth = activity.getWindowManager().getCurrentWindowMetrics().getBounds().width();
        }
        c0067q2.e.getWindow().setLayout(Math.min(AbstractC0090w2.i(context, 560), iWidth - AbstractC0090w2.i(context, 24)), -2);
        d();
    }

    public final void d() {
        C0067q2 c0067q2 = this.a;
        if (c0067q2.f == null || this.i < 0) {
            return;
        }
        while (c0067q2.f.getChildCount() > 1) {
            c0067q2.f.removeViewAt(1);
        }
        C0067q2 c0067q3 = this.b[this.i];
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(c0067q2.t);
        if (c0067q3.t != null) {
            spannableStringBuilder.append((CharSequence) "\n\n").append((CharSequence) n[this.i]).append((CharSequence) "：\n").append(c0067q3.t);
        }
        c0067q2.f.addView(AbstractC0090w2.l(c0067q2, "插件设置", spannableStringBuilder));
    }
}
