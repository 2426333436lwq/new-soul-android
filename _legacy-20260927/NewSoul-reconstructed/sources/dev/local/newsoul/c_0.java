/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  io.github.libxposed.api.XposedInterface$Chain
 *  io.github.libxposed.api.XposedInterface$Hooker
 */
package dev.local.newsoul;

import android.content.Context;
import dev.local.newsoul.j_0;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/*
 * Renamed from dev.local.newsoul.c
 */
public final class c_0
implements XposedInterface.Hooker {
    public final Class a;
    public final int b;
    public final boolean c;
    public final Class d;
    public final Class e;
    public final Method f;
    public final boolean g;

    public /* synthetic */ c_0(Class clazz, int n, boolean bl, Class clazz2, Class clazz3, Method method, boolean bl2) {
        this.a = clazz;
        this.b = n;
        this.c = bl;
        this.d = clazz2;
        this.e = clazz3;
        this.f = method;
        this.g = bl2;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object intercept(XposedInterface.Chain object) {
        void var1_8;
        block13: {
            int n;
            Object object2;
            Method method;
            Class clazz;
            Class clazz2;
            Object object3;
            block12: {
                Context context;
                block11: {
                    block10: {
                        object3 = this.a;
                        int n2 = this.b;
                        boolean bl = this.c;
                        clazz2 = this.d;
                        clazz = this.e;
                        method = this.f;
                        boolean bl2 = this.g;
                        if (!((Class)object3).isInstance(object.getThisObject())) break block10;
                        Context context2 = j_0.h;
                        object2 = null;
                        object3 = context2;
                        if (context2 == null) {
                            try {
                                context = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", null).invoke(null, null);
                                object3 = context2;
                                if (context instanceof Context) {
                                    object3 = context;
                                    if (object3 != null) {
                                        context = object3.getApplicationContext();
                                        if (context != null) {
                                            object3 = context;
                                        }
                                        j_0.h = object3;
                                    }
                                    object3 = j_0.h;
                                }
                            }
                            catch (Throwable throwable) {
                                object3 = context2;
                            }
                        }
                        if (object3 == null || !j_0.b((Context)object3, "ad_feed")) break block10;
                        object3 = object.getArg(n2);
                        if (bl && object3 instanceof Collection) break block11;
                        if (bl2 && object3 != null) break block12;
                        break block13;
                    }
                    Object object4 = object.proceed();
                    return var1_8;
                }
                object2 = (Object[])object3;
                object3 = new ArrayList(object2.size());
                context = object2.iterator();
                n = 0;
                block2: while (true) {
                    Object e;
                    block15: {
                        block16: {
                            block14: {
                                if (!context.hasNext()) break block14;
                                e = context.next();
                                if (e == null) break block15;
                                break block16;
                            }
                            if (n == 0) {
                                Object object5 = object.proceed();
                                return var1_8;
                            }
                            object2 = object.getArgs().toArray();
                            object2[n2] = object3;
                            object3 = method.getName();
                            StringBuilder stringBuilder = new StringBuilder("feed ad models filtered=");
                            stringBuilder.append(n);
                            stringBuilder.append(" via ");
                            stringBuilder.append((String)object3);
                            j_0.e(stringBuilder.toString());
                            Object object6 = object.proceed((Object[])object2);
                            return var1_8;
                        }
                        for (int i = 0; i < 2; ++i) {
                            object2 = (new Class[]{clazz2, clazz})[i];
                            if (object2 == null || !((Class)object2).isInstance(e)) continue;
                            ++n;
                            continue block2;
                        }
                    }
                    ((ArrayList)object3).add(e);
                }
            }
            for (n = 0; n < 2; ++n) {
                Class clazz3 = (new Class[]{clazz2, clazz})[n];
                if (clazz3 == null || !clazz3.isInstance(object3)) continue;
                String string = method.getName();
                object3 = new StringBuilder("feed ad model skipped via ");
                ((StringBuilder)object3).append(string);
                j_0.e(((StringBuilder)object3).toString());
                Object[] objectArray = object2;
                return var1_8;
            }
        }
        Object object7 = object.proceed();
        return var1_8;
    }
}

