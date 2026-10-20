package dev.local.newsoul;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

/* JADX INFO: renamed from: dev.local.newsoul.C0034i1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0034i1 extends Drawable {
    public final float b;
    public final Paint a = new Paint(1);
    public int c = 255;
    public boolean d = true;

    public C0034i1(float f) {
        this.b = f;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        float f = this.b;
        float fMin = Math.min(10.0f * f, Math.min(getBounds().width(), getBounds().height()) * 0.26f);
        if (fMin <= 0.0f) {
            return;
        }
        float f2 = 0.65f * fMin;
        float f3 = (getBounds().right - f2) - f;
        float f4 = getBounds().top + f2 + f;
        float f5 = fMin / 2.0f;
        Path path = new Path();
        float f6 = f4 - f5;
        path.moveTo(f3, f6);
        float f7 = 0.17f * f5;
        float f8 = f3 + f7;
        float f9 = f4 - f7;
        path.quadTo(f8, f9, f3 + f5, f4);
        float f10 = f4 + f7;
        path.quadTo(f8, f10, f3, f4 + f5);
        float f11 = f3 - f7;
        path.quadTo(f11, f10, f3 - f5, f4);
        path.quadTo(f11, f9, f3, f6);
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

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.c = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
