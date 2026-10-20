package dev.local.newsoul;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* JADX INFO: renamed from: dev.local.newsoul.v1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0085v1 extends Drawable {
    public final BitmapDrawable a;
    public final Paint b;
    public final Path c;
    public final int d;
    public final int e;
    public int f;

    public C0085v1(BitmapDrawable bitmapDrawable, int i) {
        Paint paint = new Paint(1);
        this.b = paint;
        Path path = new Path();
        this.c = path;
        this.f = 255;
        this.a = bitmapDrawable;
        this.e = i;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        this.d = bitmap.getPixel(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
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

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        BitmapDrawable bitmapDrawable = this.a;
        bitmapDrawable.setBounds(bounds);
        bitmapDrawable.draw(canvas);
        float fMin = Math.min(getBounds().width(), getBounds().height());
        if (fMin <= 0.0f) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(((getBounds().width() - fMin) / 2.0f) + getBounds().left, ((getBounds().height() - fMin) / 2.0f) + getBounds().top);
        float f = fMin / 48.0f;
        canvas.scale(f, f);
        Paint paint = this.b;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.d);
        paint.setAlpha(this.f);
        canvas.drawCircle(24.0f, 24.0f, 16.0f, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(this.e);
        paint.setAlpha(this.f);
        canvas.drawPath(this.c, paint);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.a.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.a.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.f = i;
        this.a.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        this.b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
