package dev.local.newsoul;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/* JADX INFO: renamed from: dev.local.newsoul.i2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0035i2 extends View {
    public final Paint a;
    public final boolean b;

    public C0035i2(Context context, int i, boolean z) {
        super(context);
        Paint paint = new Paint(1);
        this.a = paint;
        this.b = z;
        paint.setColor(i);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(getWidth() / 28.0f, getHeight() / 28.0f);
        Paint paint = this.a;
        boolean z = this.b;
        paint.setStrokeWidth(z ? 3.0f : 1.7f);
        if (z) {
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
