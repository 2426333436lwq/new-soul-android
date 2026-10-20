/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Switch
 *  android.widget.TextView
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import dev.local.newsoul.P0;
import dev.local.newsoul.b3;
import dev.local.newsoul.c3;
import dev.local.newsoul.d1_0;
import dev.local.newsoul.d3;
import dev.local.newsoul.q2;
import java.util.function.Consumer;

public final class C0
implements View.OnClickListener {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ C0(Object object, Object object2, int n) {
        this.a = n;
        this.b = object;
        this.c = object2;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                c3 c32 = (c3)this.c;
                object = (b3)this.b;
                if (((b3)object).n) {
                    ((b3)object).c();
                } else {
                    try {
                        ((b3)object).j();
                        c32.run();
                    }
                    catch (Throwable throwable) {
                        ((b3)object).b();
                    }
                }
                return;
            }
            case 5: {
                object = (c3)this.b;
                try {
                    object.run();
                }
                catch (Throwable throwable) {
                    d3.f((Context)((q2)this.c).a, "\u64cd\u4f5c\u672a\u5b8c\u6210\uff0c\u8bf7\u91cd\u65b0\u6253\u5f00\u8bed\u97f3\u5e93", throwable);
                }
                return;
            }
            case 4: {
                ((Consumer)this.b).accept((TextView)this.c);
                return;
            }
            case 3: {
                object = (Switch)this.b;
                if (object.isChecked() && object.isEnabled()) {
                    ((Runnable)this.c).run();
                }
                return;
            }
            case 2: {
                object = (Activity)this.b;
                ClipboardManager clipboardManager = (ClipboardManager)object.getSystemService("clipboard");
                if (clipboardManager != null) {
                    P0 p0 = (P0)this.c;
                    StringBuilder stringBuilder = new StringBuilder(p0.b());
                    String string = p0.a(true);
                    if (!string.isEmpty()) {
                        stringBuilder.append("\n\n\u9700\u8981\u68c0\u67e5\n");
                        stringBuilder.append(string);
                    }
                    if (!(string = p0.a(false)).isEmpty()) {
                        stringBuilder.append("\n\n\u6b63\u5e38\u68c0\u6d4b\u9879\n");
                        stringBuilder.append(string);
                    }
                    stringBuilder.append("\n\n\u68c0\u6d4b\u901a\u8fc7\u4e0d\u4ee3\u8868\u529f\u80fd\u5b9e\u6d4b\u901a\u8fc7\u3002");
                    clipboardManager.setPrimaryClip(ClipData.newPlainText((CharSequence)"New Soul \u529f\u80fd\u517c\u5bb9\u6027\u68c0\u6d4b", (CharSequence)stringBuilder.toString()));
                    Toast.makeText((Context)object, (CharSequence)"\u68c0\u6d4b\u7ed3\u679c\u5df2\u590d\u5236", (int)0).show();
                }
                return;
            }
            case 1: {
                object = (Activity)this.c;
                d1_0 d1_02 = (d1_0)this.b;
                d1_02.getClass();
                try {
                    d1_02.b((Activity)object);
                }
                catch (Throwable throwable) {
                    d1_0.c((Activity)object, throwable);
                }
                return;
            }
            case 0: 
        }
        ((int[])this.b)[0] = 0;
        ((Runnable)this.c).run();
    }
}

