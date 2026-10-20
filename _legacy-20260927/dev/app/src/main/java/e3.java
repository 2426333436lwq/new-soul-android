package dev.local.newsoul;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class e3 extends Drawable {
    public final Paint a;
    public final Path b;
    public final Path c;
    public final int d;

    public e3(float f) {
        Paint paint = new Paint(1);
        this.a = paint;
        Path path = new Path();
        this.b = path;
        Path path2 = new Path();
        this.c = path2;
        this.d = Math.round(f * 24.0f);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.65f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        path.moveTo(12.0f, 4.0f);
        path.lineTo(12.0f, 14.0f);
        path.moveTo(8.0f, 10.0f);
        path.lineTo(12.0f, 14.0f);
        path.lineTo(16.0f, 10.0f);
        path2.moveTo(4.0f, 14.0f);
        path2.lineTo(4.0f, 18.0f);
        path2.quadTo(4.0f, 20.0f, 6.0f, 20.0f);
        path2.lineTo(18.0f, 20.0f);
        path2.quadTo(20.0f, 20.0f, 20.0f, 18.0f);
        path2.lineTo(20.0f, 14.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        float fMin = Math.min(getBounds().width(), getBounds().height());
        if (fMin <= 0.0f) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(((getBounds().width() - fMin) / 2.0f) + getBounds().left, ((getBounds().height() - fMin) / 2.0f) + getBounds().top);
        float f = fMin / 24.0f;
        canvas.scale(f, f);
        Path path = this.b;
        Paint paint = this.a;
        canvas.drawPath(path, paint);
        canvas.drawPath(this.c, paint);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.a.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
