package dev.local.newsoul;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import io.github.libxposed.api.XposedInterface;
import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: dev.local.newsoul.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0004b implements XposedInterface.Hooker {
    public final /* synthetic */ int a;
    public final /* synthetic */ Field b;
    public final /* synthetic */ Field c;
    public final /* synthetic */ Field d;
    public final /* synthetic */ Method e;

    public /* synthetic */ C0004b(Field field, Field field2, Field field3, Method method, int i) {
        this.a = i;
        this.b = field;
        this.c = field2;
        this.d = field3;
        this.e = method;
    }

    public final Object intercept(XposedInterface.Chain chain) throws IllegalAccessException {
        switch (this.a) {
            case 0:
                Object thisObject = chain.getThisObject();
                Object obj = this.b.get(thisObject);
                Object obj2 = obj instanceof Reference ? ((Reference) obj).get() : null;
                if (!(obj2 instanceof Context)) {
                    return chain.proceed();
                }
                String str = (String) AbstractC0036j.f.invoke(this.c.get(thisObject), null);
                String str2 = (String) AbstractC0036j.e.get(thisObject);
                if (str2 == null) {
                    str2 = (String) AbstractC0036j.c.get(str);
                }
                if (str2 == null || !AbstractC0036j.b((Context) obj2, str2)) {
                    return chain.proceed();
                }
                Object obj3 = this.d.get(thisObject);
                if (obj3 != null) {
                    AbstractC0036j.i.post(new RunnableC0024g(this.e, obj3, 0));
                }
                AbstractC0036j.e("ad blocked: " + str2 + " slot=" + str);
                return null;
            default:
                Field field = this.c;
                Field field2 = this.d;
                Method method = this.e;
                Object arg = chain.getArg(0);
                if (arg == null || this.b.getInt(arg) != -220922) {
                    return chain.proceed();
                }
                boolean z = true;
                Context context = ((View) chain.getArg(1)).getContext();
                try {
                    Object obj4 = field.get(chain.getThisObject());
                    String str3 = obj4 == null ? null : (String) field2.get(obj4);
                    if (str3 != null && !str3.trim().isEmpty()) {
                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(ClipboardManager.class);
                        if (clipboardManager == null) {
                            throw new IllegalStateException("Clipboard unavailable");
                        }
                        clipboardManager.setPrimaryClip(ClipData.newPlainText("帖子文案", str3));
                        ClipData primaryClip = clipboardManager.getPrimaryClip();
                        if (primaryClip == null || primaryClip.getItemCount() != 1 || !str3.contentEquals(primaryClip.getItemAt(0).getText())) {
                            z = false;
                        }
                        Log.i("SoulHook102", "post text copied; chars=" + str3.length() + "; verified=" + z);
                        Toast.makeText(context, "已复制文案", 0).show();
                        method.invoke(chain.getThisObject(), null);
                        return Boolean.FALSE;
                    }
                    Toast.makeText(context, "该帖子暂无可复制文案", 0).show();
                    return Boolean.FALSE;
                } catch (Throwable th) {
                    Log.e("SoulHook102", "post text copy failed", th);
                    Toast.makeText(context, "复制失败，请重新打开分享页", 0).show();
                }
                break;
        }
    }
}
