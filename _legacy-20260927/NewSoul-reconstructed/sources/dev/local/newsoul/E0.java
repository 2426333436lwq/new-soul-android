/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.view.View
 */
package dev.local.newsoul;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public final class E0
extends View {
    public final int a;
    public final Paint b = new Paint(1);

    public E0(Context context, int n) {
        super(context);
        this.a = n;
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale((float)this.getWidth() / 60.0f, (float)this.getHeight() / 60.0f);
        Paint paint = this.b;
        paint.setColor(-328964);
        canvas.drawRoundRect(5.0f, 5.0f, 55.0f, 55.0f, 11.0f, 11.0f, paint);
        paint.setColor(-13355968);
        int n = this.a;
        if (n % 2 == 1) {
            canvas.drawCircle(30.0f, 30.0f, 3.7f, paint);
        }
        if (n >= 2) {
            canvas.drawCircle(18.0f, 18.0f, 3.7f, paint);
            canvas.drawCircle(42.0f, 42.0f, 3.7f, paint);
        }
        if (n >= 4) {
            canvas.drawCircle(42.0f, 18.0f, 3.7f, paint);
            canvas.drawCircle(18.0f, 42.0f, 3.7f, paint);
        }
        if (n == 6) {
            canvas.drawCircle(18.0f, 30.0f, 3.7f, paint);
            canvas.drawCircle(42.0f, 30.0f, 3.7f, paint);
        }
        canvas.restore();
    }
}

