package dev.local.newsoul;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: dev.local.newsoul.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0016e implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ AccessibleObject b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ AnnotatedElement d;

    public /* synthetic */ C0016e(AnnotatedElement annotatedElement, AccessibleObject accessibleObject, Object obj, int i) {
        this.a = i;
        this.d = annotatedElement;
        this.b = accessibleObject;
        this.c = obj;
    }

    public final Object intercept(XposedInterface.Chain chain) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke;
        int iIntValue;
        switch (this.a) {
            case 0:
                Object objProceed = chain.proceed();
                Object thisObject = chain.getThisObject();
                if (((Class) this.d).isInstance(thisObject)) {
                    Object objInvoke2 = ((Method) this.b).invoke(thisObject, null);
                    if ((objInvoke2 instanceof Context) && AbstractC0036j.b((Context) objInvoke2, "ad_interstitial")) {
                        ((Method) this.c).invoke(thisObject, null);
                        AbstractC0036j.e("ad dialog dismissed");
                    }
                }
                return objProceed;
            case 1:
                Object arg = chain.getArg(0);
                if (arg == null || !Q0.q("chat_hide_typing") || ((Integer) ((Method) this.b).invoke(arg, null)).intValue() != 1 || (objInvoke = ((Method) this.c).invoke(arg, null)) == null || ((iIntValue = ((Integer) ((Method) this.d).invoke(objInvoke, null)).intValue()) != 22 && iIntValue != 23)) {
                    return chain.proceed();
                }
                Log.i("SoulHook102", "typing command blocked; type=" + iIntValue);
                return null;
            case 2:
                Field field = (Field) this.d;
                Field field2 = (Field) this.b;
                Field field3 = (Field) this.c;
                Object objProceed2 = chain.proceed();
                try {
                    ImageView imageView = (ImageView) field.get(chain.getThisObject());
                    boolean z = field2.getInt(chain.getArg(0)) == 5133072;
                    if (z && (imageView.getDrawable() instanceof BitmapDrawable)) {
                        imageView.setImageDrawable(new C0085v1((BitmapDrawable) imageView.getDrawable(), ((TextView) field3.get(chain.getThisObject())).getCurrentTextColor()));
                    }
                    AbstractC0038j1.b(imageView, z);
                    break;
                } catch (Exception e) {
                    Log.w("SoulHook102", "bookmark icon binding failed", e);
                }
                return objProceed2;
            default:
                Object thisObject2 = chain.getThisObject();
                Object obj = ((Field) this.d).get(thisObject2);
                if (!(obj instanceof Context)) {
                    return chain.proceed();
                }
                if (!AbstractC0090w2.o((Context) obj).getBoolean("block_splash", true)) {
                    Log.i("SoulHook102", "new soul: splash filter OFF; original startup continues");
                    return chain.proceed();
                }
                ((Method) this.b).invoke(thisObject2, null);
                Log.i("SoulHook102", "new soul: splash blocked; MainScheduler.v -> s");
                ((XposedModule) this.c).log(4, "SoulHook102", "new soul: splash blocked; MainScheduler.v -> s");
                return null;
        }
    }

    public /* synthetic */ C0016e(Method method, Method method2, Method method3) {
        this.a = 1;
        this.b = method;
        this.c = method2;
        this.d = method3;
    }
}
