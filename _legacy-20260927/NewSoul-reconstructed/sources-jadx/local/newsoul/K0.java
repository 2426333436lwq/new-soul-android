package dev.local.newsoul;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class K0 {
    public final Paint a = new Paint(3);
    public final Path b = new Path();
    public Y c;
    public boolean d;
    public Bitmap e;
    public Canvas f;

    /* JADX WARN: Code duplicated, block: B:42:0x009b A[Catch: all -> 0x006a, TryCatch #2 {all -> 0x006a, blocks: (B:24:0x0063, B:31:0x006f, B:33:0x0075, B:34:0x007c, B:36:0x0089, B:38:0x008d, B:40:0x0093, B:43:0x00ac, B:45:0x00d5, B:50:0x00f0, B:54:0x0122, B:42:0x009b), top: B:62:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:49:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:52:0x011b  */
    /* JADX WARN: Code duplicated, block: B:53:0x011f  */
    public final void a(Canvas canvas, int i, int i2, RectF rectF, float f, float f2, float f3, boolean z, U2 u2) {
        int i3;
        Shader shader;
        Bitmap bitmap;
        int i4;
        int i5;
        if (i <= 0 || i2 <= 0 || rectF.isEmpty()) {
            return;
        }
        Path path = this.b;
        path.reset();
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        int iSave = canvas.save();
        canvas.clipPath(path);
        try {
            if (Build.VERSION.SDK_INT < 33 || !canvas.isHardwareAccelerated() || this.d) {
                shader = null;
                i3 = iSave;
            } else {
                try {
                    if (this.c == null) {
                        this.c = new Y();
                    }
                    i3 = iSave;
                    try {
                        try {
                            this.c.a(canvas, i, i2, rectF, f, f2, f3, z, u2);
                            canvas.restoreToCount(i3);
                            return;
                        } catch (RuntimeException e) {
                            e = e;
                            this.d = true;
                            Y y = this.c;
                            if (y != null) {
                                ((RenderNode) y.a).discardDisplayList();
                            }
                            shader = null;
                            this.c = null;
                            Log.w("SoulHook102", "Glass lens uses Canvas fallback", e);
                            bitmap = this.e;
                            if (bitmap != null) {
                                this.e = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                                this.f = new Canvas(this.e);
                            } else {
                                this.e = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                                this.f = new Canvas(this.e);
                            }
                            this.e.eraseColor(0);
                            u2.a(this.f);
                            int iSave2 = canvas.save();
                            float f4 = (0.16f * f2) + 1.0f;
                            canvas.scale(f4, f4, rectF.centerX(), rectF.centerY());
                            Bitmap bitmap2 = this.e;
                            Paint paint = this.a;
                            canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint);
                            canvas.restoreToCount(iSave2);
                            Shader shader2 = shader;
                            float f5 = rectF.top;
                            float f6 = rectF.bottom;
                            if (z) {
                                i4 = 211541455;
                            } else {
                                i4 = 151027824;
                            }
                            paint.setShader(new LinearGradient(0.0f, f5, 0.0f, f6, i4, 0, Shader.TileMode.CLAMP));
                            canvas.drawRoundRect(rectF, f, f, paint);
                            paint.setShader(shader2);
                            paint.setStyle(Paint.Style.STROKE);
                            paint.setStrokeWidth(0.6f * f3);
                            if (z) {
                                i5 = 905969663;
                            } else {
                                i5 = 409036917;
                            }
                            paint.setColor(i5);
                            canvas.drawRoundRect(rectF, f, f, paint);
                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(-1);
                            canvas.restoreToCount(i3);
                        }
                    } catch (Throwable th) {
                        th = th;
                        canvas.restoreToCount(i3);
                        throw th;
                    }
                } catch (RuntimeException e2) {
                    e = e2;
                    i3 = iSave;
                }
            }
            bitmap = this.e;
            if (bitmap != null || bitmap.getWidth() != i || this.e.getHeight() != i2) {
                this.e = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.f = new Canvas(this.e);
            }
            this.e.eraseColor(0);
            u2.a(this.f);
            int iSave3 = canvas.save();
            float f7 = (0.16f * f2) + 1.0f;
            canvas.scale(f7, f7, rectF.centerX(), rectF.centerY());
            Bitmap bitmap3 = this.e;
            Paint paint2 = this.a;
            canvas.drawBitmap(bitmap3, 0.0f, 0.0f, paint2);
            canvas.restoreToCount(iSave3);
            Shader shader3 = shader;
            float f8 = rectF.top;
            float f9 = rectF.bottom;
            if (z) {
                i4 = 211541455;
            } else {
                i4 = 151027824;
            }
            paint2.setShader(new LinearGradient(0.0f, f8, 0.0f, f9, i4, 0, Shader.TileMode.CLAMP));
            canvas.drawRoundRect(rectF, f, f, paint2);
            paint2.setShader(shader3);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(0.6f * f3);
            if (z) {
                i5 = 905969663;
            } else {
                i5 = 409036917;
            }
            paint2.setColor(i5);
            canvas.drawRoundRect(rectF, f, f, paint2);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setColor(-1);
            canvas.restoreToCount(i3);
        } catch (Throwable th2) {
            th = th2;
            i3 = iSave;
        }
    }
}
