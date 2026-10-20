package dev.local.newsoul;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

/* JADX INFO: renamed from: dev.local.newsoul.C0067q2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0067q2 {
    public final Activity a;
    public final boolean b;
    public final L2 c;
    public final AlertDialog.Builder d;
    public AlertDialog e;
    public LinearLayout f;
    public final Context g;
    public final LinearLayout h;
    public final int i;
    public final int j;
    public final int k;
    public C0067q2 o;
    public boolean q;
    public BooleanSupplier r;
    public C0067q2 s;
    public CharSequence t;
    public final IdentityHashMap l = new IdentityHashMap();
    public final ArrayList m = new ArrayList();
    public final ArrayList<C0039j2> n = new ArrayList<>();
    public final ArrayList p = new ArrayList();

    public C0067q2(Activity activity, boolean z) {
        this.a = activity;
        this.b = z;
        this.i = z ? -1644817 : Color.rgb(51, 51, 51);
        int color = z ? -14145480 : -1184275;
        int identifier = activity.getResources().getIdentifier("color_s_04", "color", activity.getPackageName());
        if (identifier != 0) {
            try {
                color = activity.getResources().getColor(identifier, activity.getTheme());
            } catch (RuntimeException unused) {
            }
        }
        int i = z ? -15198168 : -1;
        this.j = AbstractC0090w2.q(z ? -8224106 : -10066317, z, i, color);
        this.k = AbstractC0090w2.q(z ? AbstractC0090w2.b : -16746133, z, i, color);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, z ? 16974374 : 16974394);
        this.d = builder;
        Context context = builder.getContext();
        this.g = context;
        this.c = new L2(z, context.getResources().getDisplayMetrics().density);
        LinearLayout linearLayout = new LinearLayout(context);
        this.h = linearLayout;
        linearLayout.setOrientation(1);
        linearLayout.setTag(this);
        linearLayout.setPadding(AbstractC0090w2.i(activity, 16), AbstractC0090w2.i(activity, 8), AbstractC0090w2.i(activity, 16), AbstractC0090w2.i(activity, 16));
    }

    public final void a(Runnable runnable) {
        this.m.add(runnable);
    }

    public final void b() {
        View decorView;
        WindowInsets rootWindowInsets;
        WindowInsetsController windowInsetsController;
        if (Build.VERSION.SDK_INT < 30 || (rootWindowInsets = (decorView = this.e.getWindow().getDecorView()).getRootWindowInsets()) == null || !rootWindowInsets.isVisible(WindowInsets.Type.ime()) || (windowInsetsController = decorView.getWindowInsetsController()) == null) {
            f();
        } else {
            windowInsetsController.hide(WindowInsets.Type.ime());
        }
    }

    public final void c(BooleanSupplier booleanSupplier) {
        if (this.e != null) {
            throw new IllegalStateException("Protect the draft before showing it");
        }
        this.r = booleanSupplier;
        this.d.setNegativeButton("取消", new L(1));
    }

    public final void d() {
        Iterator it = this.n.iterator();
        while (it.hasNext()) {
            ((C0039j2) it.next()).b();
        }
        Iterator it2 = this.m.iterator();
        while (it2.hasNext()) {
            ((Runnable) it2.next()).run();
        }
    }

    public final void e() {
        C0067q2 c0067q2 = this.o;
        if (c0067q2 != null) {
            c0067q2.e();
            return;
        }
        d();
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((C0067q2) it.next()).d();
        }
    }

    public final void f() {
        if (this.e.isShowing()) {
            Activity activity = this.a;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            if (!this.r.getAsBoolean()) {
                this.e.dismiss();
                return;
            }
            C0067q2 c0067q2 = this.s;
            if (c0067q2 == null || !c0067q2.e.isShowing()) {
                View currentFocus = this.e.getCurrentFocus();
                C0067q2 c0067q3 = new C0067q2(activity, this.b);
                this.s = c0067q3;
                c0067q3.h.addView(AbstractC0090w2.n(c0067q3.g, "有尚未保存的修改。放弃后这些修改不会生效。", 15, c0067q3.i));
                c0067q3.d.setNegativeButton("放弃修改", new o2(this));
                c0067q3.g("放弃未保存的修改？", "继续编辑");
                c0067q3.e.setCanceledOnTouchOutside(false);
                c0067q3.e.setOnDismissListener(new DialogInterfaceOnDismissListenerC0010c1(this, currentFocus, 1));
            }
        }
    }

    public final void g(String str, String str2) {
        ScrollView scrollView = new ScrollView(this.g);
        scrollView.addView(this.h);
        i(str, str2, scrollView);
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
    public final void h(final View view, String[] strArr, boolean z, final IntConsumer intConsumer) {
        C0067q2 c0067q2 = this;
        if (view.isAttachedToWindow() && view.isShown()) {
            Activity activity = c0067q2.a;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            Context context = c0067q2.g;
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(0, AbstractC0090w2.i(context, 4), 0, AbstractC0090w2.i(context, 4));
            ScrollView scrollView = new ScrollView(context);
            L2 l2 = c0067q2.c;
            l2.b(scrollView, 1, 14.0f);
            scrollView.setClipToOutline(true);
            scrollView.addView(linearLayout);
            PopupWindow popupWindow = new PopupWindow(context);
            popupWindow.setContentView(scrollView);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setInputMethodMode(2);
            boolean z2 = c0067q2.b;
            popupWindow.setElevation(AbstractC0090w2.i(context, z2 ? 4 : 8));
            int i = AbstractC0090w2.i(context, 176);
            int i2 = 0;
            while (i2 < strArr.length) {
                TextView textViewN = AbstractC0090w2.n(context, strArr[i2], 16, z ? c0067q2.i : c0067q2.j);
                textViewN.setGravity(8388627);
                final int i3 = i2;
                textViewN.setPadding(AbstractC0090w2.i(context, 16), AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 16), AbstractC0090w2.i(context, 12));
                textViewN.setMinHeight(AbstractC0090w2.i(context, 48));
                l2.b(textViewN, 12, L2.e(12));
                textViewN.setEnabled(z);
                AbstractC0090w2.g(textViewN);
                final PopupWindow popupWindow2 = popupWindow;
                boolean z3 = z2;
                PopupWindow popupWindow3 = popupWindow;
                textViewN.setOnClickListener(new l2(this, popupWindow3, view, intConsumer, i3));
                i = Math.max(i, AbstractC0090w2.i(context, 32) + ((int) Math.ceil(textViewN.getPaint().measureText(strArr[i3]))));
                linearLayout.addView(textViewN, new LinearLayout.LayoutParams(-1, -2));
                if (i3 < strArr.length - 1) {
                    View view2 = new View(context);
                    view2.setBackgroundColor(z3 ? -13224378 : -1447442);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, AbstractC0090w2.i(context, 1));
                    int i4 = AbstractC0090w2.i(context, 16);
                    layoutParams.rightMargin = i4;
                    layoutParams.leftMargin = i4;
                    linearLayout.addView(view2, layoutParams);
                }
                c0067q2 = this;
                popupWindow = popupWindow3;
                z2 = z3;
                i2 = i3 + 1;
            }
            PopupWindow popupWindow4 = popupWindow;
            Rect rect = new Rect();
            view.getWindowVisibleDisplayFrame(rect);
            int iMin = Math.min(i, Math.max(AbstractC0090w2.i(context, 48), Math.min(view.getRootView().getWidth(), rect.width()) - AbstractC0090w2.i(context, 24)));
            linearLayout.measure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            popupWindow4.setWidth(iMin);
            popupWindow4.setHeight(Math.min(linearLayout.getMeasuredHeight(), Math.max(AbstractC0090w2.i(context, 48), rect.height() - AbstractC0090w2.i(context, 24))));
            final ViewOnAttachStateChangeListenerC0063p2 viewOnAttachStateChangeListenerC0063p2 = new ViewOnAttachStateChangeListenerC0063p2(0, popupWindow4);
            view.addOnAttachStateChangeListener(viewOnAttachStateChangeListenerC0063p2);
            popupWindow4.setOnDismissListener(new m2(view, viewOnAttachStateChangeListenerC0063p2));
            try {
                popupWindow4.showAsDropDown(view, 0, AbstractC0090w2.i(context, 4), 8388613);
            } catch (RuntimeException e) {
                view.removeOnAttachStateChangeListener(viewOnAttachStateChangeListenerC0063p2);
                throw e;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r12v4, types: [dev.local.newsoul.k2, java.lang.Object] */
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
    public final void i(String str, String str2, ViewGroup viewGroup) {
        Typeface typefaceCreate;
        Context context = this.g;
        LinearLayout linearLayout = new LinearLayout(context);
        this.f = linearLayout;
        linearLayout.setGravity(16);
        Activity activity = this.a;
        linearLayout.setPadding(AbstractC0090w2.i(activity, 24), AbstractC0090w2.i(activity, 14), AbstractC0090w2.i(activity, 24), AbstractC0090w2.i(activity, 8));
        TextView textViewN = AbstractC0090w2.n(context, str, 18, this.i);
        ArrayList arrayList = this.p;
        if (arrayList.isEmpty()) {
            textViewN.setTypeface(Typeface.DEFAULT, 1);
        } else {
            try {
                typefaceCreate = new Typeface.Builder(activity.getAssets(), "fonts/tivf-bold-round.ttf").setWeight(500).build();
            } catch (RuntimeException unused) {
                typefaceCreate = null;
            }
            if (typefaceCreate == null) {
                typefaceCreate = Typeface.create("sans-serif-medium", 0);
            }
            textViewN.setTypeface(typefaceCreate);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            textViewN.setAccessibilityHeading(true);
        }
        linearLayout.addView(textViewN, new LinearLayout.LayoutParams(0, -2, 1.0f));
        CharSequence charSequence = this.t;
        if (charSequence != null) {
            linearLayout.addView(AbstractC0090w2.l(this, str, charSequence));
        }
        linearLayout.setMinimumHeight(AbstractC0090w2.i(context, arrayList.isEmpty() ? 58 : 70));
        AlertDialog alertDialogCreate = this.d.setCustomTitle(linearLayout).setView(viewGroup).setPositiveButton(str2, new L(1)).create();
        this.e = alertDialogCreate;
        alertDialogCreate.show();
        if (this.r != null) {
            this.e.setCanceledOnTouchOutside(false);
            this.e.getButton(-2).setOnClickListener(new ViewOnClickListenerC0072s0(this, 3));
            this.e.setOnKeyListener(new n2(this));
            if (i >= 33) {
                OnBackInvokedDispatcher onBackInvokedDispatcher = this.e.getOnBackInvokedDispatcher();
                OnBackInvokedCallback r12 = new k2(this);
                onBackInvokedDispatcher.registerOnBackInvokedCallback(0, r12);
                this.e.getWindow().getDecorView().addOnAttachStateChangeListener(new F1(onBackInvokedDispatcher, r12, 1));
            }
        }
        Window window = this.e.getWindow();
        L2 l2 = this.c;
        l2.getClass();
        Drawable drawableC = l2.c(1, L2.e(1));
        L2.i(window.getDecorView(), drawableC);
        window.setBackgroundDrawable(drawableC);
        int[] iArr = {-1, -2, -3};
        for (int i2 = 0; i2 < 3; i2++) {
            Button button = this.e.getButton(iArr[i2]);
            if (button != null) {
                l2.b(button, 9, L2.e(9));
                button.setTextColor(this.k);
                button.setMinHeight(AbstractC0090w2.i(context, 48));
                button.setMinWidth(AbstractC0090w2.i(context, 48));
            }
        }
    }
}
