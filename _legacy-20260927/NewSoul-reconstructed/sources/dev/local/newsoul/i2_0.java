/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Cap
 *  android.graphics.Paint$Join
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.view.View
 */
package dev.local.newsoul;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/*
 * Renamed from dev.local.newsoul.i2
 */
public final class i2_0
extends View {
    public final Paint a;
    public final boolean b;

    public i2_0(Context context, int n, boolean bl) {
        super(context);
        context = new Paint(1);
        this.a = context;
        this.b = bl;
        context.setColor(n);
        context.setStyle(Paint.Style.STROKE);
        context.setStrokeCap(Paint.Cap.ROUND);
        context.setStrokeJoin(Paint.Join.ROUND);
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale((float)this.getWidth() / 28.0f, (float)this.getHeight() / 28.0f);
        Paint paint = this.a;
        boolean bl = this.b;
        float f = bl ? 3.0f : 1.7f;
        paint.setStrokeWidth(f);
        if (bl) {
            Path path = new Path();
            path.moveTo(7.0f, 5.0f);
            path.lineTo(21.0f, 14.0f);
            path.lineTo(7.0f, 23.0f);
            canvas.drawPath(path, paint);
        } else {
            canvas.drawRoundRect(3.0f, 3.0f, 25.0f, 25.0f, 4.0f, 4.0f, paint);
            canvas.drawLine(8.0f, 8.0f, 8.0f, 10.0f, paint);
            canvas.drawCircle(8.0f, 13.0f, 2.4f, paint);
            canvas.drawLine(8.0f, 16.0f, 8.0f, 20.0f, paint);
            canvas.drawLine(18.0f, 8.0f, 18.0f, 12.0f, paint);
            canvas.drawCircle(18.0f, 15.0f, 2.4f, paint);
            canvas.drawLine(18.0f, 18.0f, 18.0f, 20.0f, paint);
        }
        canvas.restore();
    }
}

