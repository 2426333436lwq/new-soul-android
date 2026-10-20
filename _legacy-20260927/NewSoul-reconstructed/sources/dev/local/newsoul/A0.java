/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Toast
 */
package dev.local.newsoul;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import dev.local.newsoul.I2;
import dev.local.newsoul.b3;
import java.util.function.BooleanSupplier;

public final class A0
implements View.OnClickListener {
    public final int a;
    public final Object b;

    public /* synthetic */ A0(int n, Object object) {
        this.a = n;
        this.b = object;
    }

    public final void onClick(View object) {
        switch (this.a) {
            default: {
                object = (b3)this.b;
                if (((b3)object).n) {
                    ((b3)object).c();
                } else {
                    try {
                        ((b3)object).f();
                    }
                    catch (Throwable throwable) {
                        ((b3)object).b();
                    }
                }
                return;
            }
            case 4: {
                object = (I2)this.b;
                if (((I2)object).b()) {
                    Toast.makeText((Context)((I2)object).a, (CharSequence)"\u81ea\u52a8\u70b9\u8d5e\u5df2\u5f00\u542f\uff0c\u957f\u6309\u7231\u5fc3\u53ef\u5173\u95ed", (int)0).show();
                } else if (((I2)object).m) {
                    ((I2)object).e(true);
                } else {
                    ((I2)object).l(false);
                }
                return;
            }
            case 3: {
                ((BooleanSupplier)this.b).getAsBoolean();
                return;
            }
            case 2: {
                object = (Activity)this.b;
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)"https://t.me/+3Hl2lJ1X-oZlZTZl"));
                    object.startActivity(intent);
                }
                catch (ActivityNotFoundException activityNotFoundException) {
                    Toast.makeText((Context)object, (CharSequence)"\u6ca1\u6709\u53ef\u6253\u5f00\u94fe\u63a5\u7684\u5e94\u7528", (int)0).show();
                }
                return;
            }
            case 1: {
                ((Runnable[])this.b)[0].run();
                return;
            }
            case 0: 
        }
        ((Dialog)this.b).dismiss();
    }
}

