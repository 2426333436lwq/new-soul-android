/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
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

/*
 * Renamed from dev.local.newsoul.i1
 */
public final class i1_0
extends Drawable {
    public final Paint a = new Paint(1);
    public final float b;
    public int c = 255;
    public boolean d = true;

    public i1_0(float f) {
        this.b = f;
    }

    public final void draw(Canvas canvas) {
        float f = this.b;
        float f2 = Math.min(10.0f * f, (float)Math.min(this.getBounds().width(), this.getBounds().height()) * 0.26f);
        if (f2 <= 0.0f) {
            return;
        }
        float f4 = this.getBounds().right;
        float f5 = 0.65f * f2;
        f4 = f4 - f5 - f;
        f5 = (float)this.getBounds().top + f5 + f;
        float f6 = f2 / 2.0f;
        Path path = new Path();
        float f7 = f5 - f6;
        path.moveTo(f4, f7);
        float f8 = 0.17f * f6;
        float f9 = f4 + f8;
        f2 = f5 - f8;
        path.quadTo(f9, f2, f4 + f6, f5);
        float f10 = f5 + f8;
        path.quadTo(f9, f10, f4, f5 + f6);
        f8 = f4 - f8;
        path.quadTo(f8, f10, f4 - f6, f5);
        path.quadTo(f8, f2, f4, f7);
        path.close();
        Paint paint = this.a;
        paint.setAlpha(this.c);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(f * 0.5f);
        paint.setColor(-2031624);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-13049916);
        canvas.drawPath(path, paint);
    }

    public final int getOpacity() {
        return -3;
    }

    public final void setAlpha(int n) {
        this.c = n;
        this.invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
}

