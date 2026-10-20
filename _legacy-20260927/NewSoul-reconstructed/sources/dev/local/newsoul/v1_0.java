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
 *  android.graphics.Rect
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 */
package dev.local.newsoul;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/*
 * Renamed from dev.local.newsoul.v1
 */
public final class v1_0
extends Drawable {
    public final BitmapDrawable a;
    public final Paint b;
    public final Path c;
    public final int d;
    public final int e;
    public int f;

    public v1_0(BitmapDrawable bitmapDrawable, int n) {
        Path path;
        Paint paint;
        this.b = paint = new Paint(1);
        this.c = path = new Path();
        this.f = 255;
        this.a = bitmapDrawable;
        this.e = n;
        bitmapDrawable = bitmapDrawable.getBitmap();
        this.d = bitmapDrawable.getPixel(bitmapDrawable.getWidth() / 2, bitmapDrawable.getHeight() / 2);
        path.moveTo(18.0f, 13.0f);
        path.lineTo(30.0f, 13.0f);
        path.quadTo(32.0f, 13.0f, 32.0f, 15.0f);
        path.lineTo(32.0f, 35.0f);
        path.lineTo(24.0f, 30.0f);
        path.lineTo(16.0f, 35.0f);
        path.lineTo(16.0f, 15.0f);
        path.quadTo(16.0f, 13.0f, 18.0f, 13.0f);
        path.close();
        paint.setStrokeWidth(2.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public final void draw(Canvas canvas) {
        Rect rect = this.getBounds();
        BitmapDrawable bitmapDrawable = this.a;
        bitmapDrawable.setBounds(rect);
        bitmapDrawable.draw(canvas);
        float f = Math.min(this.getBounds().width(), this.getBounds().height());
        if (f <= 0.0f) {
            return;
        }
        int n = canvas.save();
        float f2 = this.getBounds().left;
        float f4 = ((float)this.getBounds().width() - f) / 2.0f;
        float f5 = this.getBounds().top;
        canvas.translate(f4 + f2, ((float)this.getBounds().height() - f) / 2.0f + f5);
        f2 = f / 48.0f;
        canvas.scale(f2, f2);
        bitmapDrawable = this.b;
        bitmapDrawable.setStyle(Paint.Style.FILL);
        bitmapDrawable.setColor(this.d);
        bitmapDrawable.setAlpha(this.f);
        canvas.drawCircle(24.0f, 24.0f, 16.0f, (Paint)bitmapDrawable);
        bitmapDrawable.setStyle(Paint.Style.STROKE);
        bitmapDrawable.setColor(this.e);
        bitmapDrawable.setAlpha(this.f);
        canvas.drawPath(this.c, (Paint)bitmapDrawable);
        canvas.restoreToCount(n);
    }

    public final int getIntrinsicHeight() {
        return this.a.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
        return this.a.getIntrinsicWidth();
    }

    public final int getOpacity() {
        return -3;
    }

    public final void setAlpha(int n) {
        this.f = n;
        this.a.setAlpha(n);
        this.invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        this.b.setColorFilter(colorFilter);
        this.invalidateSelf();
    }
}

