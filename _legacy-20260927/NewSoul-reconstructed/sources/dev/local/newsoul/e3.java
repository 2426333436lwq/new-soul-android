/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
 *  android.graphics.Paint$Cap
 *  android.graphics.Paint$Join
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.drawable.Drawable
 */
package dev.local.newsoul;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

public final class e3
extends Drawable {
    public final Paint a;
    public final Path b;
    public final Path c;
    public final int d;

    public e3(float f) {
        Path path;
        Path path2;
        Paint paint;
        this.a = paint = new Paint(1);
        this.b = path2 = new Path();
        this.c = path = new Path();
        this.d = Math.round(f * 24.0f);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.65f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        path2.moveTo(12.0f, 4.0f);
        path2.lineTo(12.0f, 14.0f);
        path2.moveTo(8.0f, 10.0f);
        path2.lineTo(12.0f, 14.0f);
        path2.lineTo(16.0f, 10.0f);
        path.moveTo(4.0f, 14.0f);
        path.lineTo(4.0f, 18.0f);
        path.quadTo(4.0f, 20.0f, 6.0f, 20.0f);
        path.lineTo(18.0f, 20.0f);
        path.quadTo(20.0f, 20.0f, 20.0f, 18.0f);
        path.lineTo(20.0f, 14.0f);
    }

    public final void draw(Canvas canvas) {
        float f = Math.min(this.getBounds().width(), this.getBounds().height());
        if (f <= 0.0f) {
            return;
        }
        int n = canvas.save();
        float f2 = this.getBounds().left;
        float f4 = ((float)this.getBounds().width() - f) / 2.0f;
        float f5 = this.getBounds().top;
        canvas.translate(f4 + f2, ((float)this.getBounds().height() - f) / 2.0f + f5);
        canvas.scale(f /= 24.0f, f);
        Path path = this.b;
        Paint paint = this.a;
        canvas.drawPath(path, paint);
        canvas.drawPath(this.c, paint);
        canvas.restoreToCount(n);
    }

    public final int getIntrinsicHeight() {
        return this.d;
    }

    public final int getIntrinsicWidth() {
        return this.d;
    }

    public final int getOpacity() {
        return -3;
    }

    public final void setAlpha(int n) {
        this.a.setAlpha(n);
        this.invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
}

