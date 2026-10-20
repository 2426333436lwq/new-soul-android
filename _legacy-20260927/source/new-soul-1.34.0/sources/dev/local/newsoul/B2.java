package dev.local.newsoul;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class B2 {
    public static boolean a;
    public static Field b;
    public static Field c;
    public static Field d;
    public static Field e;
    public static Field f;
    public static String g;
    public static boolean i;
    public static Class l;
    public static Field m;
    public static Field n;
    public static boolean o;
    public static int p;
    public static C0041k0 h = new C0041k0("");
    public static final Handler j = new Handler(Looper.getMainLooper());
    public static final WeakHashMap k = new WeakHashMap();

    public static boolean a(Context context, Object obj) {
        if (!i || !l.isInstance(obj)) {
            return true;
        }
        SharedPreferences sharedPreferencesO = AbstractC0090w2.o(context);
        if (a && sharedPreferencesO.getBoolean("feed_keywords_enabled_v1", false)) {
            synchronized (B2.class) {
                String string = sharedPreferencesO.getString("feed_keywords_v1", "");
                if (!Objects.equals(string, g)) {
                    try {
                        h = new C0041k0(string);
                    } catch (IllegalArgumentException unused) {
                        h = new C0041k0("");
                    }
                    g = string;
                }
                if (!((List) h.a).isEmpty()) {
                    try {
                        if (!h.a((String) b.get(obj)) && !h.a((String) c.get(obj)) && !h.a((String) d.get(obj))) {
                            Object obj2 = e.get(obj);
                            if (obj2 instanceof Iterable) {
                                for (Object obj3 : (Iterable) obj2) {
                                    if (obj3 == null || !f.getDeclaringClass().isInstance(obj3) || !h.a((String) f.get(obj3))) {
                                    }
                                }
                            }
                        }
                        return false;
                    } catch (ReflectiveOperationException unused2) {
                    }
                }
            }
        }
        if (!sharedPreferencesO.getBoolean("feed_filter_enabled", false)) {
            return true;
        }
        String string2 = sharedPreferencesO.getString("feed_filter_gender", "FEMALE");
        String str = null;
        try {
            Object obj4 = m.get(obj);
            if (obj4 != null) {
                int i2 = n.getInt(obj4);
                if (i2 == 1) {
                    str = "FEMALE";
                } else if (i2 == 0) {
                    str = "MALE";
                }
            }
        } catch (ReflectiveOperationException unused3) {
        }
        boolean z = sharedPreferencesO.getBoolean("feed_filter_keep_unknown", false);
        if ("ANY".equals(string2)) {
            return true;
        }
        return ("MALE".equals(str) || "FEMALE".equals(str)) ? string2.equals(str) : z;
    }

    public static void b(View view, A2 a2) {
        if (i) {
            View view2 = view;
            while (view2 != null) {
                if (view2.getId() != -1) {
                    try {
                        if ("pager_square".equals(view2.getResources().getResourceEntryName(view2.getId()))) {
                            boolean zA = a(view.getContext(), a2.a);
                            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                            if (zA || a2.b || layoutParams == null) {
                                if (zA) {
                                    e(view, a2);
                                    return;
                                }
                                return;
                            }
                            a2.c = layoutParams.height;
                            a2.d = view.getVisibility();
                            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                                a2.e = marginLayoutParams.topMargin;
                                a2.f = marginLayoutParams.bottomMargin;
                                marginLayoutParams.bottomMargin = 0;
                                marginLayoutParams.topMargin = 0;
                            }
                            a2.b = true;
                            int i2 = p + 1;
                            p = i2;
                            if (i2 <= 5 || i2 % 25 == 0) {
                                Log.i("SoulHook102", "feed filter rows hidden=" + i2);
                            }
                            layoutParams.height = 0;
                            view.setVisibility(8);
                            view.setLayoutParams(layoutParams);
                            return;
                        }
                    } catch (RuntimeException unused) {
                    }
                }
                view2 = view2.getParent() instanceof View ? (View) view2.getParent() : null;
            }
        }
        e(view, a2);
    }

    public static void c(XposedModule xposedModule, ClassLoader classLoader) {
        try {
            Class<?> clsLoadClass = classLoader.loadClass("cn.soulapp.android.square.post.bean.Post");
            l = clsLoadClass;
            Field field = clsLoadClass.getField("postAuthorModel");
            m = field;
            n = field.getType().getField("gender");
            try {
                b = l.getField("content");
                c = l.getField("title");
                d = l.getField("contentTitle");
                e = l.getField("tags");
                f = classLoader.loadClass("cn.soulapp.android.square.bean.Tag").getField("name");
                a = true;
            } catch (ReflectiveOperationException e2) {
                Log.w("SoulHook102", "feed keyword fields unavailable", e2);
            }
            Class<?> clsLoadClass2 = classLoader.loadClass("cn.soulapp.android.component.square.main.squarepost.SquareAdapter");
            final Class<?> clsLoadClass3 = classLoader.loadClass("cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder");
            XposedInterface.HookBuilder id = xposedModule.hook(clsLoadClass2.getDeclaredMethod("d", clsLoadClass3, Object.class)).setId("new-soul/feed-filter-bind");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            final int i2 = 0;
            id.setExceptionMode(exceptionMode).intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.z2
                public final Object intercept(XposedInterface.Chain chain) {
                    switch (i2) {
                        case 0:
                            View view = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            WeakHashMap weakHashMap = B2.k;
                            B2.e(view, (A2) weakHashMap.remove(view));
                            Object objProceed = chain.proceed();
                            A2 a2 = new A2();
                            a2.a = chain.getArg(1);
                            weakHashMap.put(view, a2);
                            B2.b(view, a2);
                            view.post(new RunnableC0024g(view, a2, 12));
                            return objProceed;
                        case 1:
                            View view2 = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            B2.e(view2, (A2) B2.k.remove(view2));
                            return chain.proceed();
                        default:
                            Object objProceed2 = chain.proceed();
                            View view3 = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            A2 a3 = (A2) B2.k.get(view3);
                            if (a3 != null) {
                                B2.b(view3, a3);
                            }
                            return objProceed2;
                    }
                }
            });
            final int i3 = 1;
            xposedModule.hook(clsLoadClass2.getDeclaredMethod("j", clsLoadClass3)).setId("new-soul/feed-filter-recycle").setExceptionMode(exceptionMode).intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.z2
                public final Object intercept(XposedInterface.Chain chain) {
                    switch (i3) {
                        case 0:
                            View view = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            WeakHashMap weakHashMap = B2.k;
                            B2.e(view, (A2) weakHashMap.remove(view));
                            Object objProceed = chain.proceed();
                            A2 a2 = new A2();
                            a2.a = chain.getArg(1);
                            weakHashMap.put(view, a2);
                            B2.b(view, a2);
                            view.post(new RunnableC0024g(view, a2, 12));
                            return objProceed;
                        case 1:
                            View view2 = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            B2.e(view2, (A2) B2.k.remove(view2));
                            return chain.proceed();
                        default:
                            Object objProceed2 = chain.proceed();
                            View view3 = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            A2 a3 = (A2) B2.k.get(view3);
                            if (a3 != null) {
                                B2.b(view3, a3);
                            }
                            return objProceed2;
                    }
                }
            });
            final int i4 = 2;
            xposedModule.hook(clsLoadClass2.getDeclaredMethod("h", clsLoadClass3)).setId("new-soul/feed-filter-attach").setExceptionMode(exceptionMode).intercept(new XposedInterface.Hooker() { // from class: dev.local.newsoul.z2
                public final Object intercept(XposedInterface.Chain chain) {
                    switch (i4) {
                        case 0:
                            View view = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            WeakHashMap weakHashMap = B2.k;
                            B2.e(view, (A2) weakHashMap.remove(view));
                            Object objProceed = chain.proceed();
                            A2 a2 = new A2();
                            a2.a = chain.getArg(1);
                            weakHashMap.put(view, a2);
                            B2.b(view, a2);
                            view.post(new RunnableC0024g(view, a2, 12));
                            return objProceed;
                        case 1:
                            View view2 = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            B2.e(view2, (A2) B2.k.remove(view2));
                            return chain.proceed();
                        default:
                            Object objProceed2 = chain.proceed();
                            View view3 = (View) clsLoadClass3.getField("itemView").get(chain.getArg(0));
                            A2 a3 = (A2) B2.k.get(view3);
                            if (a3 != null) {
                                B2.b(view3, a3);
                            }
                            return objProceed2;
                    }
                }
            });
            i = true;
            Log.i("SoulHook102", "square feed filter ready");
        } catch (Throwable th) {
            Log.e("SoulHook102", "square feed filter unavailable", th);
        }
    }

    public static void d() {
        if (o) {
            return;
        }
        o = true;
        j.postDelayed(new RunnableC0028h(4), 80L);
    }

    public static void e(View view, A2 a2) {
        if (a2 == null || !a2.b) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = a2.c;
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.topMargin = a2.e;
                marginLayoutParams.bottomMargin = a2.f;
            }
            view.setLayoutParams(layoutParams);
        }
        view.setVisibility(a2.d);
        a2.b = false;
    }
}
