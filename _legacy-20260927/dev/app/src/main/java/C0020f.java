package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: dev.local.newsoul.C0020f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0020f implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0020f(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final Object intercept(XposedInterface.Chain chain) throws Throwable {
        switch (this.a) {
            case 0:
                Object objProceed = chain.proceed();
                View view = (View) chain.getThisObject();
                Context context = view.getContext();
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    AbstractC0036j.h = context;
                }
                AbstractC0036j.i(view, (String) this.b);
                return objProceed;
            case 1:
                if (!Q0.R((Activity) chain.getThisObject())) {
                    return chain.proceed();
                }
                Q0.w("privacy repeat blocked consume: route=" + ((String) this.b));
                return null;
            default:
                Object objProceed2 = chain.proceed();
                if (!Boolean.TRUE.equals(objProceed2) || !Q0.q("chat_remove_social_score_limit")) {
                    return objProceed2;
                }
                Log.i("SoulHook102", "social score gate bypassed: " + ((Method) this.b).getName());
                return Boolean.FALSE;
        }
    }
}
