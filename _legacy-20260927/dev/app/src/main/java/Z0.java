package dev.local.newsoul;

import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.AbsSeekBar;
import android.widget.AbsSpinner;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import io.github.libxposed.api.XposedModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes.dex */
public final class Z0 extends FrameLayout {
    public boolean A;
    public boolean B;
    public float C;
    public float D;
    public final C0041k0 a;
    public final ViewGroup[] b;
    public final int c;
    public final int d;
    public final int e;
    public int f;
    public int g;
    public int h;
    public float i;
    public boolean j;
    public boolean k;
    public boolean l;
    public float m;
    public float n;
    public final float[] o;
    public final float[] p;
    public final boolean[] q;
    public ValueAnimator r;
    public VelocityTracker s;
    public int t;
    public long u;
    public float v;
    public float w;
    public float x;
    public boolean y;
    public boolean z;

    public Z0(Context context, C0041k0 c0041k0) {
        super(context);
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.t = -1;
        this.a = c0041k0;
        this.b = new ViewGroup[5];
        this.o = new float[5];
        this.p = new float[5];
        this.q = new boolean[5];
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.c = viewConfiguration.getScaledTouchSlop();
        this.d = Math.max(viewConfiguration.getScaledMinimumFlingVelocity(), Math.round(getResources().getDisplayMetrics().density * 400.0f));
        this.e = viewConfiguration.getScaledMaximumFlingVelocity();
        setClipChildren(true);
    }

    public static boolean h(View view, float f, float f2) {
        if (view.getVisibility() != 0 || f < 0.0f || f2 < 0.0f || f >= view.getWidth() || f2 >= view.getHeight()) {
            return false;
        }
        if (((view instanceof CompoundButton) && !(view instanceof CheckBox)) || (view instanceof AbsSpinner) || (view instanceof EditText) || (view instanceof HorizontalScrollView) || (view instanceof AbsSeekBar) || (view instanceof WebView) || (((view instanceof TextView) && ((TextView) view).isTextSelectable()) || view.canScrollHorizontally(-1) || view.canScrollHorizontally(1))) {
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (h(childAt, ((viewGroup.getScrollX() + f) - childAt.getLeft()) - childAt.getTranslationX(), ((viewGroup.getScrollY() + f2) - childAt.getTop()) - childAt.getTranslationY())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void a() {
        ValueAnimator valueAnimator = this.r;
        this.r = null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public final void b() {
        VelocityTracker velocityTracker = this.s;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.s = null;
        this.t = -1;
        this.B = false;
        this.A = false;
        this.z = false;
        this.y = false;
        this.x = 0.0f;
    }

    public final void c(int i) {
        int iQ;
        a();
        g(i);
        boolean z = this.f != i;
        this.h = i;
        this.g = i;
        this.f = i;
        this.i = 0.0f;
        this.k = false;
        this.j = false;
        j();
        if (!z) {
            return;
        }
        C0086v2 c0086v2 = (C0086v2) this.a.a;
        c0086v2.i = i;
        c0086v2.b[i].e();
        int i2 = 0;
        while (true) {
            TextView[] textViewArr = c0086v2.d;
            if (i2 >= textViewArr.length) {
                c0086v2.d();
                return;
            }
            textViewArr[i2].setSelected(i2 == i);
            TextView textView = textViewArr[i2];
            C0067q2 c0067q2 = c0086v2.a;
            if (i2 == i) {
                boolean z2 = c0067q2.b;
                iQ = AbstractC0090w2.q(z2 ? AbstractC0090w2.b : -16746133, z2, z2 ? -14596798 : -2493459);
            } else {
                iQ = c0067q2.j;
            }
            textView.setTextColor(iQ);
            textViewArr[i2].setTypeface(Typeface.DEFAULT, i2 == i ? 1 : 0);
            i2++;
        }
    }

    public final void d() {
        boolean z = this.t >= 0;
        if (z) {
            MotionEvent motionEventObtain = MotionEvent.obtain(this.u, SystemClock.uptimeMillis(), 3, this.v, this.w, 0);
            try {
                super.dispatchTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            } catch (Throwable th) {
                motionEventObtain.recycle();
                throw th;
            }
        }
        int i = this.f;
        if (i >= 0) {
            if (!this.y) {
                i = this.i >= 0.5f ? this.h : this.g;
            }
            a();
            if (i >= 0) {
                c(i);
            }
        }
        b();
        this.A = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            boolean z = (this.r == null && this.g == this.h && !this.k) ? false : true;
            a();
            b();
            this.B = z;
            this.C = this.i;
            float fSignum = this.k ? Math.signum(this.h - this.n) : Integer.compare(this.h, this.g);
            this.D = fSignum;
            if (fSignum == 0.0f) {
                this.D = 1.0f;
            }
            this.t = motionEvent.getPointerId(0);
            this.u = motionEvent.getDownTime();
            this.v = motionEvent.getX();
            float y = motionEvent.getY();
            this.w = y;
            this.z = !this.B && h(this, this.v, y);
            this.s = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker = this.s;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        if (actionMasked == 5) {
            if (this.y || this.B) {
                this.y = false;
                this.A = true;
                k(false);
            } else {
                this.z = true;
            }
        }
        if (this.A) {
            if (actionMasked == 1 || actionMasked == 3) {
                b();
            }
            return true;
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (actionMasked == 1 || actionMasked == 3) {
            b();
        }
        return zDispatchTouchEvent;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0047  */
    public final void e(MotionEvent motionEvent) {
        char c;
        if (this.y || this.z || this.t < 0 || motionEvent.getPointerCount() != 1) {
            return;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(this.t);
        if (iFindPointerIndex < 0) {
            this.z = true;
            return;
        }
        float x = motionEvent.getX(iFindPointerIndex) - this.v;
        float y = motionEvent.getY(iFindPointerIndex) - this.w;
        float f = this.c;
        float fAbs = Math.abs(x);
        float fAbs2 = Math.abs(y);
        if (fAbs2 > f && fAbs2 >= 0.8f * fAbs) {
            c = 2;
        } else if (fAbs > f && fAbs > 1.5f * fAbs2) {
            c = 1;
        } else if (Math.max(fAbs, fAbs2) > f * 2.0f) {
            c = 2;
        } else {
            c = 0;
        }
        if (c == 2) {
            this.z = true;
        }
        if (c != 1) {
            return;
        }
        this.y = true;
        if (!this.B) {
            this.g = this.f;
            this.j = false;
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        f(x);
    }

    public final void f(float f) {
        this.x = f;
        float fMin = 0.0f;
        if (this.B) {
            float width = getWidth() * (this.j ? 0.18f : 1.0f);
            this.i = width == 0.0f ? this.C : Math.max(0.0f, Math.min(1.0f, this.C - ((f * this.D) / width)));
            j();
            return;
        }
        int iMax = this.f;
        int length = this.b.length;
        if (f != 0.0f) {
            iMax = Math.max(0, Math.min(length - 1, iMax + (f >= 0.0f ? -1 : 1)));
        }
        this.h = iMax;
        g(iMax);
        if (this.h != this.f && getWidth() != 0) {
            fMin = Math.min(1.0f, Math.abs(f) / getWidth());
        }
        this.i = fMin;
        j();
    }

    /* JADX WARN: Type inference failed for: r14v3, types: [dev.local.newsoul.K1] */
    /* JADX WARN: Type inference failed for: r2v93, types: [dev.local.newsoul.L1, java.lang.Object] */
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
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public final void g(int i) {
        C0067q2 c0067q2;
        char c;
        int i2;
        ViewGroup[] viewGroupArr = this.b;
        if (viewGroupArr[i] == null) {
            C0086v2 c0086v2 = (C0086v2) this.a.a;
            C0067q2[] c0067q2Arr = c0086v2.b;
            C0067q2 c0067q3 = c0067q2Arr[i];
            ScrollView[] scrollViewArr = c0086v2.c;
            if (c0067q3 == null) {
                C0067q2 c0067q4 = c0086v2.a;
                final C0067q2 c0067q5 = new C0067q2(c0067q4.a, c0067q4.b);
                c0067q5.q = true;
                L2 l2 = c0067q5.c;
                l2.c = true;
                LinearLayout linearLayout = c0067q5.h;
                linearLayout.setClipChildren(false);
                linearLayout.setClipToPadding(false);
                c0067q5.o = c0067q4;
                c0067q5.e = c0067q4.e;
                c0067q2Arr[i] = c0067q5;
                c0067q4.p.add(c0067q5);
                IdentityHashMap identityHashMap = c0067q5.l;
                int i3 = c0067q5.i;
                Activity activity = c0067q5.a;
                Context context = c0067q5.g;
                if (i != 0) {
                    boolean z = c0067q5.b;
                    int i4 = c0067q5.k;
                    if (i != 1) {
                        int i5 = c0067q5.j;
                        if (i != 2) {
                            if (i != 3) {
                                SharedPreferences sharedPreferencesO = AbstractC0090w2.o(activity);
                                LinearLayout linearLayoutR = AbstractC0090w2.r(c0067q5, "版本与检测", null, null);
                                try {
                                    Context contextCreatePackageContext = activity.createPackageContext("dev.local.newsoul.public", 0);
                                    linearLayoutR.addView(AbstractC0090w2.n(context, "版本 " + contextCreatePackageContext.getPackageManager().getPackageInfo(contextCreatePackageContext.getPackageName(), 0).versionName, 15, i3));
                                    i2 = 15;
                                } catch (Exception unused) {
                                    i2 = 15;
                                    linearLayoutR.addView(AbstractC0090w2.n(context, "版本信息暂不可用", 15, i5));
                                }
                                TextView textViewN = AbstractC0090w2.n(context, "检查更新", i2, i4);
                                AbstractC0090w2.g(textViewN);
                                textViewN.setOnClickListener(new ViewOnClickListenerC0072s0(c0067q5, 4));
                                TextView textViewN2 = AbstractC0090w2.n(context, "功能兼容性检测", i2, i4);
                                AbstractC0090w2.g(textViewN2);
                                textViewN2.setContentDescription("检测当前 Soul 版本的功能兼容性");
                                textViewN2.setOnClickListener(new N1(activity, z, 1));
                                AbstractC0090w2.a(c0067q5, linearLayoutR, textViewN, textViewN2);
                                LinearLayout linearLayoutR2 = AbstractC0090w2.r(c0067q5, "交流与支持", null, null);
                                TextView textViewN3 = AbstractC0090w2.n(context, "加入 TG 群组", 15, i4);
                                AbstractC0090w2.g(textViewN3);
                                textViewN3.setContentDescription("加入 TG 群组，打开 t.me 链接");
                                textViewN3.setOnClickListener(new A0(2, activity));
                                TextView textViewN4 = AbstractC0090w2.n(context, "支持作者", 15, i4);
                                AbstractC0090w2.g(textViewN4);
                                textViewN4.setOnClickListener(new N1(activity, z, 2));
                                AbstractC0090w2.a(c0067q5, linearLayoutR2, textViewN3, textViewN4);
                                LinearLayout linearLayoutR3 = AbstractC0090w2.r(c0067q5, "运行选项", null, null);
                                AbstractC0090w2.d(linearLayoutR3, sharedPreferencesO, "page_log", "记录页面日志", "用于排查页面问题，日志包含页面类名。", i3);
                                AbstractC0090w2.e(AbstractC0090w2.d(linearLayoutR3, sharedPreferencesO, "startup_toast", "显示启动提示", "", i3), c0067q5, new RunnableC0048m(activity, 4));
                                TextView textViewN5 = AbstractC0090w2.n(context, "调试", 15, i4);
                                AbstractC0090w2.g(textViewN5);
                                textViewN5.setOnClickListener(new N1(activity, z, 3));
                                AbstractC0090w2.x(c0067q5, textViewN5);
                                linearLayoutR3.addView(textViewN5, new LinearLayout.LayoutParams(-1, -2));
                                XposedModule xposedModule = S.a;
                                LinearLayout linearLayoutR4 = AbstractC0090w2.r(c0067q5, "配置迁移", "导出插件设置，不包含密钥、聊天记录或账号凭据。导入只覆盖文件中支持的设置，现有密钥保持不变。", null);
                                TextView textViewN6 = AbstractC0090w2.n(context, "导出配置", 15, i4);
                                TextView textViewN7 = AbstractC0090w2.n(context, "导入配置", 15, i4);
                                AbstractC0090w2.g(textViewN6);
                                AbstractC0090w2.g(textViewN7);
                                AbstractC0090w2.a(c0067q5, linearLayoutR4, textViewN6, textViewN7);
                                final Q q = new Q(linearLayoutR4, textViewN6, textViewN7);
                                final int i6 = 1;
                                textViewN6.setOnClickListener(new I(c0067q5, q, i6));
                                final int i7 = 0;
                                textViewN7.setOnClickListener(new I(c0067q5, q, i7));
                            } else {
                                final SharedPreferences sharedPreferencesO2 = AbstractC0090w2.o(activity);
                                c0067q5.t = "勾选表示屏蔽对应广告。点击分组旁的更多按钮或长按分组标题，可屏蔽或取消屏蔽本组；未就绪项会跳过。已显示的广告可能需重新进入页面或重启 Soul 后消失。";
                                String str = "激励广告";
                                String[][] strArr = {new String[]{"启动与浮层", "block_splash", "ad_float", "ad_interstitial"}, new String[]{"广场与浏览", "ad_feed", "ad_detail", "ad_video", "ad_tag", "ad_tag_web", "ad_search"}, new String[]{"聊天与通知", "ad_group", "ad_conversation", "ad_envelope", "ad_notice", "ad_chat_tools"}, new String[]{"其他页面推广", "ad_planet", "ad_me"}, new String[]{"激励广告", "ad_reward_chat", "ad_reward_drama", "ad_reward_web"}};
                                HashMap map = new HashMap();
                                String[][] strArr2 = AbstractC0036j.a;
                                for (int i8 = 0; i8 < 19; i8++) {
                                    String[] strArr3 = strArr2[i8];
                                    map.put(strArr3[0], strArr3);
                                }
                                char c2 = 0;
                                int i9 = 0;
                                int i10 = 5;
                                while (i9 < i10) {
                                    String[] strArr4 = strArr[i9];
                                    ArrayList arrayList = new ArrayList();
                                    String[][] strArr5 = strArr;
                                    LinearLayout linearLayoutR5 = AbstractC0090w2.r(c0067q5, strArr4[c2], null, new K1(c0067q5, sharedPreferencesO2, arrayList));
                                    if (str.equals(strArr4[c2])) {
                                        AbstractC0090w2.c(linearLayoutR5, "屏蔽后不会获得奖励或解锁内容。");
                                    }
                                    int i11 = 1;
                                    while (i11 < strArr4.length) {
                                        final String[] strArr6 = (String[]) map.get(strArr4[i11]);
                                        HashMap map2 = map;
                                        Objects.requireNonNull(strArr6, strArr4[i11]);
                                        final L1 r2 = new L1(strArr6);
                                        String[] strArr7 = strArr4;
                                        LinearLayout linearLayout2 = new LinearLayout(context);
                                        String str2 = str;
                                        linearLayout2.setGravity(16);
                                        C0067q2 c0067q6 = c0067q4;
                                        linearLayout2.setMinimumHeight(AbstractC0090w2.i(context, 52));
                                        CheckBox checkBox = new CheckBox(context);
                                        checkBox.setTextSize(16.0f);
                                        int i12 = i9;
                                        int i13 = i11;
                                        ArrayList arrayList2 = arrayList;
                                        checkBox.setTextColor(new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{i5, i3}));
                                        int i14 = i3;
                                        int[][] iArr = {new int[]{-16842910}, new int[]{R.attr.state_checked}, new int[0]};
                                        boolean z2 = l2.a;
                                        checkBox.setButtonTintList(new ColorStateList(iArr, new int[]{z2 ? -10329489 : -4735550, l2.a(), z2 ? -8224106 : -10066317}));
                                        l2.b(checkBox, 12, L2.e(12));
                                        checkBox.setGravity(8388627);
                                        checkBox.setMinHeight(AbstractC0090w2.i(context, 52));
                                        checkBox.setPadding(AbstractC0090w2.i(context, 4), AbstractC0090w2.i(context, 4), AbstractC0090w2.i(context, 8), AbstractC0090w2.i(context, 4));
                                        linearLayout2.addView(checkBox, new LinearLayout.LayoutParams(0, -2, 1.0f));
                                        if (strArr6[2].isEmpty()) {
                                            c = 1;
                                        } else {
                                            c = 1;
                                            linearLayout2.addView(AbstractC0090w2.l(c0067q5, strArr6[1], strArr6[2]));
                                        }
                                        linearLayoutR5.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
                                        final C0027g2 c0027g2 = new C0027g2(strArr6[0], strArr6[c], r2);
                                        C0031h2 c0031h2 = (C0031h2) identityHashMap.get(linearLayoutR5);
                                        if (c0031h2 == null) {
                                            c0031h2 = new C0031h2(c0067q5, linearLayoutR5);
                                            identityHashMap.put(linearLayoutR5, c0031h2);
                                        }
                                        c0031h2.a.add(checkBox);
                                        final RunnableC0050m1 runnableC0050m1 = new RunnableC0050m1(c0027g2, r2, checkBox, sharedPreferencesO2, c0031h2, 3);
                                        checkBox.setOnCheckedChangeListener(new Q1(c0027g2, r2, runnableC0050m1, sharedPreferencesO2));
                                        c0067q5.a(runnableC0050m1);
                                        runnableC0050m1.run();
                                        arrayList2.add(c0027g2);
                                        arrayList = arrayList2;
                                        i11 = i13 + 1;
                                        map = map2;
                                        strArr4 = strArr7;
                                        str = str2;
                                        i9 = i12;
                                        c0067q4 = c0067q6;
                                        i3 = i14;
                                    }
                                    HashMap map3 = map;
                                    int i15 = i9;
                                    C0031h2 c0031h3 = (C0031h2) identityHashMap.get(linearLayoutR5);
                                    linearLayoutR5.removeView(c0031h3.e);
                                    View view = c0031h3.d;
                                    linearLayoutR5.removeView(view);
                                    linearLayoutR5.addView(c0031h3.e);
                                    linearLayoutR5.addView(view);
                                    i9 = i15 + 1;
                                    strArr = strArr5;
                                    map = map3;
                                    i10 = 5;
                                    c2 = 0;
                                }
                            }
                            c0067q2 = c0067q4;
                        } else {
                            viewGroupArr = viewGroupArr;
                            linearLayout = linearLayout;
                            scrollViewArr = scrollViewArr;
                            c0067q2 = c0067q4;
                            Handler handler = AbstractC0081u1.a;
                            LinearLayout linearLayoutR6 = AbstractC0090w2.r(c0067q5, "主页插眼", "在用户主页打开右上角菜单，点击「插眼」。\n\n列表中点击昵称打开主页，长按记录可移除。记录保存在本机，按当前登录账号分别保存；不会关注对方。重新打开主页仍遵循 Soul 原有的访问行为。", null);
                            TextView textViewA = AbstractC0081u1.a(c0067q5, "插眼列表");
                            textViewA.setOnClickListener(new ViewOnClickListenerC0072s0(c0067q5, 1));
                            linearLayoutR6.addView(textViewA, new LinearLayout.LayoutParams(-1, -2));
                            if (!AbstractC0081u1.f) {
                                linearLayoutR6.addView(AbstractC0090w2.n(context, "当前版本的主页插眼入口尚未就绪", 13, i5));
                            }
                            WeakHashMap weakHashMap = AbstractC0100z0.a;
                            LinearLayout linearLayoutR7 = AbstractC0090w2.r(c0067q5, "个人主页", null, null);
                            TextView textViewN8 = AbstractC0090w2.n(context, "信息伪装", 16, i3);
                            textViewN8.setGravity(16);
                            textViewN8.setMinHeight(AbstractC0090w2.i(context, 48));
                            textViewN8.setPadding(AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 10), AbstractC0090w2.i(context, 12), AbstractC0090w2.i(context, 10));
                            l2.b(textViewN8, 12, L2.e(12));
                            AbstractC0090w2.g(textViewN8);
                            textViewN8.setOnClickListener(new ViewOnClickListenerC0072s0(c0067q5, 0));
                            linearLayoutR7.addView(textViewN8, new LinearLayout.LayoutParams(-1, -2));
                        }
                    } else {
                        viewGroupArr = viewGroupArr;
                        linearLayout = linearLayout;
                        scrollViewArr = scrollViewArr;
                        c0067q2 = c0067q4;
                        SharedPreferences sharedPreferencesO3 = AbstractC0090w2.o(activity);
                        LinearLayout linearLayoutR8 = AbstractC0090w2.r(c0067q5, "帖子筛选", "性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞。性别按作者资料判断。\n\n屏蔽词匹配正文、标题和话题，英文不区分大小写；不识别图片内文字、语音或评论。", null);
                        String str3 = B2.i ? "" : " · 当前版本未就绪";
                        int i16 = c0067q5.i;
                        C0070r2 c0070r2D = AbstractC0090w2.d(linearLayoutR8, sharedPreferencesO3, "feed_filter_enabled", "按性别筛选", str3, i16);
                        final int i17 = 0;
                        AbstractC0090w2.f(c0067q5, c0070r2D, new H1(i17));
                        LinearLayout linearLayout3 = new LinearLayout(context);
                        linearLayout3.setOrientation(1);
                        linearLayout3.setTag(c0067q5);
                        linearLayoutR8.addView(linearLayout3, new LinearLayout.LayoutParams(-1, -2));
                        TextView textViewN9 = AbstractC0090w2.n(context, "显示性别", 16, i3);
                        textViewN9.setPadding(0, AbstractC0090w2.i(context, 12), 0, AbstractC0090w2.i(context, 10));
                        linearLayout3.addView(textViewN9);
                        String[] strArr8 = {"ANY", "FEMALE", "MALE"};
                        C1 c1 = new C1(c0067q5, textViewN9, new String[]{"不限", "仅女生", "仅男生"}, 15);
                        linearLayout3.addView(c1, new LinearLayout.LayoutParams(-1, -2));
                        RunnableC0050m1 runnableC0050m2 = new RunnableC0050m1(sharedPreferencesO3, c1, strArr8, (View) AbstractC0090w2.d(linearLayout3, sharedPreferencesO3, "feed_filter_keep_unknown", "保留性别未知的帖子", "", c0067q5.i).getParent(), AbstractC0090w2.c(linearLayout3, "不限包含性别未知的帖子。"), 2);
                        c1.h = new P1(sharedPreferencesO3, strArr8, c1, runnableC0050m2);
                        runnableC0050m2.run();
                        c0067q5.a(runnableC0050m2);
                        c0070r2D.a(new RunnableC0024g(linearLayout3, c0070r2D, 10));
                        final int i18 = 3;
                        AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR8, sharedPreferencesO3, "feed_keywords_enabled_v1", "启用屏蔽词过滤", (B2.a && B2.i) ? "" : "当前版本未就绪", i16), new H1(i18));
                        TextView textViewN10 = AbstractC0090w2.n(context, "", 16, i4);
                        RunnableC0024g runnableC0024g = new RunnableC0024g(sharedPreferencesO3, textViewN10, 6);
                        runnableC0024g.run();
                        c0067q5.a(runnableC0024g);
                        textViewN10.setGravity(16);
                        textViewN10.setMinHeight(AbstractC0090w2.i(context, 48));
                        AbstractC0090w2.g(textViewN10);
                        linearLayoutR8.addView(textViewN10, new LinearLayout.LayoutParams(-1, -2));
                        textViewN10.setOnClickListener(new ViewOnClickListenerC0046l1(activity, z, runnableC0024g));
                        LinearLayout linearLayoutR9 = AbstractC0090w2.r(c0067q5, "点赞", "一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单击停止。关闭一键点赞不会关闭自动点赞。范围关闭时只点赞屏幕内帖子，开启时包含屏幕外已加载帖子。\n\n自动点赞：处理当前列表已加载的全部帖子，新加载的帖子也会继续处理。离开广场或打开设置时暂停，返回后继续；长按广场爱心也可开启或关闭，失败时自动关闭。\n\n两种方式都跳过已点赞和被筛选隐藏的帖子，不自动加载更多。", null);
                        C0070r2 c0070r2D2 = AbstractC0090w2.d(linearLayoutR9, sharedPreferencesO3, "square_auto_like", "自动点赞", "", i16);
                        final int i19 = 4;
                        AbstractC0090w2.f(c0067q5, c0070r2D2, new H1(i19));
                        C0070r2 c0070r2D3 = AbstractC0090w2.d(linearLayoutR9, sharedPreferencesO3, "square_visible_like", "一键点赞", "", i16);
                        final int i20 = 5;
                        AbstractC0090w2.f(c0067q5, c0070r2D3, new H1(i20));
                        C0070r2 c0070r2D4 = AbstractC0090w2.d(linearLayoutR9, sharedPreferencesO3, "square_like_all_loaded", "包含屏幕外已加载帖子", "", i16);
                        final int i21 = 6;
                        AbstractC0090w2.f(c0067q5, c0070r2D4, new H1(i21));
                        View view2 = (View) c0070r2D4.getParent();
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view2.getLayoutParams();
                        layoutParams.leftMargin = AbstractC0090w2.i(context, 16);
                        view2.setLayoutParams(layoutParams);
                        int iIndexOfChild = linearLayoutR9.indexOfChild(view2);
                        linearLayoutR9.removeView(view2);
                        C0039j2 c0039j2 = new C0039j2(context);
                        c0039j2.addView(view2, new FrameLayout.LayoutParams((ViewGroup.MarginLayoutParams) view2.getLayoutParams()));
                        c0039j2.setClipChildren(true);
                        linearLayoutR9.addView(c0039j2, iIndexOfChild, new LinearLayout.LayoutParams(-1, -2));
                        c0067q5.n.add(c0039j2);
                        TextView textViewC = AbstractC0090w2.c(linearLayoutR9, "");
                        RunnableC0050m1 runnableC0050m3 = new RunnableC0050m1(c0070r2D2, c0070r2D3, c0039j2, textViewC, new Z(c0070r2D2, c0070r2D3, c0070r2D4, textViewC, 2), 1);
                        c0070r2D2.a(runnableC0050m3);
                        c0070r2D3.a(runnableC0050m3);
                        c0070r2D4.a(runnableC0050m3);
                        LinearLayout linearLayoutR10 = AbstractC0090w2.r(c0067q5, "下载与分享", "无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自带的水印无法去除。\n\n分享限制：解除帖子分享面板的本地限制，修改后重新打开分享面板生效；实际分享以服务器处理为准。", null);
                        final int i22 = 7;
                        AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR10, sharedPreferencesO3, "download_image_no_watermark", "图片无水印", "", i16), new H1(i22));
                        final int i23 = 1;
                        AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR10, sharedPreferencesO3, "download_video_no_watermark", "视频无水印", "", i16), new H1(i23));
                        final int i24 = 2;
                        AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR10, sharedPreferencesO3, "post_remove_share_limit", "去除分享限制", "", i16), new H1(i24));
                    }
                } else {
                    viewGroupArr = viewGroupArr;
                    linearLayout = linearLayout;
                    scrollViewArr = scrollViewArr;
                    c0067q2 = c0067q4;
                    final SharedPreferences sharedPreferencesO4 = AbstractC0090w2.o(activity);
                    LinearLayout linearLayoutR11 = AbstractC0090w2.r(c0067q5, "聊天小工具", "骰子与猜拳\n私聊发送前选择结果；预览不会发送消息。", null);
                    String str4 = AbstractC0075t.a ? "" : " · 未就绪";
                    int i25 = c0067q5.i;
                    C0070r2 c0070r2D5 = AbstractC0090w2.d(linearLayoutR11, sharedPreferencesO4, "game_dice", "自定义骰子", str4, i25);
                    final int i26 = 8;
                    AbstractC0090w2.f(c0067q5, c0070r2D5, new H1(i26));
                    AbstractC0090w2.e(c0070r2D5, c0067q5, new RunnableC0048m(activity, 2));
                    C0070r2 c0070r2D6 = AbstractC0090w2.d(linearLayoutR11, sharedPreferencesO4, "game_finger", "自定义猜拳", AbstractC0075t.b ? "" : " · 未就绪", i25);
                    final int i27 = 15;
                    AbstractC0090w2.f(c0067q5, c0070r2D6, new H1(i27));
                    AbstractC0090w2.e(c0070r2D6, c0067q5, new RunnableC0048m(activity, 3));
                    LinearLayout linearLayoutR12 = AbstractC0090w2.r(c0067q5, "消息撤回", "仅保留开启后收到的普通私聊消息，自己的撤回不受影响。已被清除的历史消息无法恢复；媒体仍取决于本地文件或原地址是否可用。关闭后停止拦截，已保留消息不自动删除。", null);
                    C0070r2 c0070r2D7 = AbstractC0090w2.d(linearLayoutR12, sharedPreferencesO4, "chat_anti_recall", "聊天防撤回", Q0.k ? "" : " · 当前版本未就绪", i25);
                    final int i28 = 16;
                    AbstractC0090w2.f(c0067q5, c0070r2D7, new H1(i28));
                    AbstractC0090w2.e(c0070r2D7, c0067q5, new RunnableC0024g(c0067q5, sharedPreferencesO4, 8));
                    LinearLayout linearLayout4 = new LinearLayout(context);
                    linearLayout4.setOrientation(1);
                    linearLayout4.setTag(c0067q5);
                    linearLayoutR12.addView(linearLayout4, new LinearLayout.LayoutParams(-1, -2));
                    TextView textViewN11 = AbstractC0090w2.n(context, "撤回标记样式", 15, i3);
                    textViewN11.setPadding(0, AbstractC0090w2.i(context, 12), 0, AbstractC0090w2.i(context, 8));
                    linearLayout4.addView(textViewN11);
                    final String[] strArr9 = {"below", "nickname"};
                    final C1 c3 = new C1(c0067q5, textViewN11, new String[]{"气泡下方小字", "昵称旁标签"}, 13);
                    linearLayout4.addView(c3, new LinearLayout.LayoutParams(-1, -2));
                    int i29 = 10;
                    H h = new H(c3, sharedPreferencesO4, strArr9, i29);
                    c3.h = new R1(sharedPreferencesO4, strArr9, c3);
                    h.run();
                    c0067q5.a(h);
                    c0070r2D7.a(new RunnableC0024g(linearLayout4, c0070r2D7, i29));
                    final int i30 = 17;
                    AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR12, sharedPreferencesO4, "chat_own_recall_no_time_limit", "撤回不受两分钟限制", "仅移除自己消息的本地时间检查，实际能否撤回以服务器处理为准。".concat(AbstractC0026g1.a ? "" : " · 当前版本未就绪"), i25), new H1(i30));
                    LinearLayout linearLayoutR13 = AbstractC0090w2.r(c0067q5, "聊天限制", "聊天限制修改后重新进入会话；实际发送仍以服务器处理为准。", null);
                    final int i31 = 18;
                    AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR13, sharedPreferencesO4, "chat_remove_gift_gate", "去除送礼聊天限制", "移除已适配的本地送礼开聊与消息额度门槛。".concat(Q0.e ? "" : " · 当前版本未就绪"), i25), new H1(i31));
                    final int i32 = 19;
                    AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR13, sharedPreferencesO4, "chat_remove_bell_gift_gate", "去除奇遇铃送礼限制", Q0.f ? "" : " · 当前版本未就绪", i25), new H1(i32));
                    final int i33 = 20;
                    AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR13, sharedPreferencesO4, "chat_remove_social_score_limit", "去除礼仪分限制", "解除本地礼仪分开聊、等待回复、图片和通话限制及相关弹窗，不修改实际礼仪分。".concat((Q0.U && Q0.V) ? "" : " · 当前版本未就绪"), i25), new H1(i33));
                    LinearLayout linearLayoutR14 = AbstractC0090w2.r(c0067q5, "聊天与匹配", null, null);
                    final int i34 = 9;
                    AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR14, sharedPreferencesO4, "chat_hide_typing", "禁止上报输入状态", "不向对方发送正在输入或结束输入状态。".concat(Q0.b ? "" : " · 当前版本未就绪"), i25), new H1(i34));
                    final int i35 = 10;
                    AbstractC0090w2.f(c0067q5, AbstractC0090w2.d(linearLayoutR14, sharedPreferencesO4, "match_mute_background_music", "关闭匹配背景音乐", "下次进入匹配生效，保留匹配振动。".concat(Q0.c ? "" : " · 当前版本未就绪"), i25), new H1(i35));
                    LinearLayout linearLayoutR15 = AbstractC0090w2.r(c0067q5, "隐私照片 / 视频", "仅适用于收到的隐私图片和视频；开启后重新打开媒体生效。已撤回、已过期或服务器不再提供的文件无法恢复。", null);
                    String str5 = Q0.P ? "" : " · 当前版本未就绪";
                    C0070r2 c0070r2D8 = AbstractC0090w2.d(linearLayoutR15, sharedPreferencesO4, "privacy_allow_download", "允许下载", "保存目录：\n图片：Pictures/New Soul\n视频：Movies/New Soul".concat(str5), i25);
                    C0070r2 c0070r2D9 = AbstractC0090w2.d(linearLayoutR15, sharedPreferencesO4, "privacy_allow_screenshot", "允许截屏", str5, i25);
                    C0070r2 c0070r2D10 = AbstractC0090w2.d(linearLayoutR15, sharedPreferencesO4, "privacy_allow_repeat", "允许多次查看", "查看时不扣减本地次数，也不向对方发送已阅状态。", i25);
                    final int i36 = 11;
                    AbstractC0090w2.f(c0067q5, c0070r2D8, new H1(i36));
                    final int i37 = 12;
                    AbstractC0090w2.f(c0067q5, c0070r2D9, new H1(i37));
                    final int i38 = 13;
                    AbstractC0090w2.f(c0067q5, c0070r2D10, new H1(i38));
                    TextView textViewC2 = AbstractC0090w2.c(linearLayoutR15, "");
                    C0031h2 c0031h4 = (C0031h2) identityHashMap.get(linearLayoutR15);
                    c0031h4.b.add(c0070r2D10);
                    final int i39 = 14;
                    c0031h4.c.add(new H1(i39));
                    RunnableC0024g runnableC0024g2 = new RunnableC0024g(textViewC2, c0031h4, 7);
                    c0067q5.a(runnableC0024g2);
                    runnableC0024g2.run();
                    TextView textView = c0031h4.e;
                    linearLayoutR15.removeView(textView);
                    TextView textView2 = c0031h4.d;
                    linearLayoutR15.removeView(textView2);
                    linearLayoutR15.addView(textView);
                    linearLayoutR15.addView(textView2);
                }
                ScrollView scrollView = new ScrollView(c0067q2.g);
                scrollView.setFillViewport(false);
                scrollView.setClipToPadding(false);
                scrollView.addView(linearLayout);
                scrollViewArr[i] = scrollView;
                c0067q5.e();
            } else {
                viewGroupArr = viewGroupArr;
                scrollViewArr = scrollViewArr;
            }
            ScrollView scrollView2 = scrollViewArr[i];
            viewGroupArr[i] = scrollView2;
            scrollView2.setVisibility(4);
            addView(scrollView2, new FrameLayout.LayoutParams(-1, -1));
        } else {
            viewGroupArr = viewGroupArr;
        }
        ViewGroup viewGroup = viewGroupArr[i];
    }

    public final void i(int i) {
        a();
        g(i);
        float f = this.m;
        this.n = f;
        float fSignum = Math.signum(i - f);
        if (fSignum == 0.0f) {
            fSignum = 1.0f;
        }
        int i2 = 0;
        while (true) {
            ViewGroup[] viewGroupArr = this.b;
            boolean z = true;
            if (i2 >= viewGroupArr.length) {
                this.g = this.f;
                this.h = i;
                this.i = 0.0f;
                this.k = true;
                this.j = true;
                j();
                k(true);
                return;
            }
            ViewGroup viewGroup = viewGroupArr[i2];
            boolean z2 = viewGroup != null && viewGroup.getVisibility() == 0;
            if (!z2 && i2 != i) {
                z = false;
            }
            this.q[i2] = z;
            this.o[i2] = z2 ? viewGroup.getTranslationX() : getWidth() * fSignum * 0.18f;
            this.p[i2] = z2 ? viewGroup.getAlpha() : 0.0f;
            i2++;
        }
    }

    public final void j() {
        ViewGroup[] viewGroupArr;
        boolean z;
        float f;
        int i = this.g;
        if (i < 0) {
            return;
        }
        float fSignum = this.k ? Math.signum(this.h - this.n) : Integer.compare(this.h, i);
        if (this.k && fSignum == 0.0f) {
            fSignum = 1.0f;
        }
        float width = getWidth() * (this.j ? 0.18f : 1.0f);
        int i2 = 0;
        while (true) {
            viewGroupArr = this.b;
            z = true;
            if (i2 >= viewGroupArr.length) {
                break;
            }
            ViewGroup viewGroup = viewGroupArr[i2];
            if (viewGroup != null) {
                boolean z2 = this.k ? this.q[i2] : i2 == this.g || i2 == this.h;
                viewGroup.setVisibility(z2 ? 0 : 4);
                z = i2 == this.f;
                viewGroup.setImportantForAccessibility(z ? 0 : 4);
                viewGroup.setDescendantFocusability(z ? 262144 : 393216);
                if (!z2) {
                    viewGroup.setTranslationX(0.0f);
                    viewGroup.setAlpha(1.0f);
                } else if (this.k) {
                    int i3 = this.h;
                    float[] fArr = this.o;
                    float f2 = i2 == i3 ? 0.0f : fArr[i2] - (fSignum * width);
                    float f3 = i2 == i3 ? 1.0f : 0.0f;
                    float f4 = fArr[i2];
                    viewGroup.setTranslationX(((f2 - f4) * this.i) + f4);
                    float f5 = this.p[i2];
                    viewGroup.setAlpha(((f3 - f5) * this.i) + f5);
                } else if (i2 == this.g) {
                    viewGroup.setTranslationX((-fSignum) * width * this.i);
                    viewGroup.setAlpha(this.j ? 1.0f - this.i : 1.0f);
                } else {
                    viewGroup.setTranslationX((1.0f - this.i) * fSignum * width);
                    viewGroup.setAlpha(this.j ? this.i : 1.0f);
                }
            }
            i2++;
        }
        if (this.k) {
            float f6 = this.n;
            f = ((this.h - f6) * this.i) + f6;
        } else {
            int i4 = this.g;
            f = i4 + ((this.h - i4) * this.i);
        }
        this.m = f;
        int iMax = Math.max(0, Math.min(viewGroupArr.length - 1, (int) Math.floor(f)));
        int iMin = Math.min(viewGroupArr.length - 1, iMax + 1);
        float fMax = Math.max(0.0f, Math.min(1.0f, this.m - iMax));
        if (this.g == this.h && !this.k) {
            z = false;
        }
        C0086v2 c0086v2 = (C0086v2) this.a.a;
        for (C0067q2 c0067q2 : c0086v2.b) {
            if (c0067q2 != null) {
                for (C0039j2 c0039j2 : c0067q2.n) {
                    if (c0039j2.a != null) {
                        c0039j2.b();
                    }
                }
            }
        }
        c0086v2.j = iMax;
        c0086v2.k = iMin;
        c0086v2.l = fMax;
        c0086v2.m = z;
        c0086v2.a();
    }

    public final void k(boolean z) {
        if (this.k && !z) {
            i(this.f);
            return;
        }
        this.l = z;
        a();
        int i = z ? this.h : this.g;
        if (!ValueAnimator.areAnimatorsEnabled() || !isAttachedToWindow() || getWidth() == 0) {
            c(i);
            return;
        }
        float f = z ? 1.0f : 0.0f;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.i, f);
        this.r = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(Math.max(90, Math.round(Math.abs(f - this.i) * 210.0f)));
        valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new L0(this, 1));
        valueAnimatorOfFloat.addListener(new Y0(this, i));
        valueAnimatorOfFloat.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        d();
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 2) {
            e(motionEvent);
        }
        return this.y || this.B;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        d();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        float xVelocity;
        boolean zC;
        int iMax;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 2) {
            e(motionEvent);
            int iFindPointerIndex = motionEvent.findPointerIndex(this.t);
            if (this.y && iFindPointerIndex >= 0) {
                f(motionEvent.getX(iFindPointerIndex) - this.v);
            }
        } else if (actionMasked == 1 || actionMasked == 3) {
            boolean z = false;
            if (this.y) {
                VelocityTracker velocityTracker = this.s;
                if (velocityTracker == null || actionMasked == 3) {
                    xVelocity = 0.0f;
                } else {
                    velocityTracker.computeCurrentVelocity(1000, this.e);
                    xVelocity = this.s.getXVelocity(this.t);
                }
                boolean z2 = this.B;
                int i = this.d;
                if (z2) {
                    zC = Q0.c(this.i, (-xVelocity) * this.D, i, actionMasked == 3);
                } else {
                    int i2 = this.f;
                    int length = this.b.length;
                    float f = this.x;
                    float width = getWidth();
                    float f2 = i;
                    if (actionMasked != 3 && width > 0.0f) {
                        if (f == 0.0f) {
                            iMax = i2;
                        } else {
                            iMax = Math.max(0, Math.min(length - 1, (f < 0.0f ? 1 : -1) + i2));
                        }
                        if (iMax != i2) {
                            if (Q0.c(Math.min(1.0f, Math.abs(f) / width), xVelocity * (f >= 0.0f ? 1 : -1), f2, false)) {
                                i2 = iMax;
                            }
                        }
                    }
                    zC = i2 != this.f;
                }
                this.y = false;
                k(zC);
            } else if (this.B) {
                if (actionMasked != 3 && this.l) {
                    z = true;
                }
                k(z);
            }
        }
        return true;
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        if (z) {
            return;
        }
        d();
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            return;
        }
        d();
    }
}
