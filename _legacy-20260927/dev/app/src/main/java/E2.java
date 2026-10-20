package dev.local.newsoul;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class E2 extends View {
    public final I2 a;
    public final Paint b;
    public final Path c;
    public int d;
    public boolean e;

    public E2(Context context, I2 i2) {
        super(context);
        this.b = new Paint(1);
        Path path = new Path();
        this.c = path;
        this.a = i2;
        setFocusable(true);
        path.moveTo(34.0f, 45.0f);
        path.cubicTo(30.0f, 41.0f, 21.0f, 35.0f, 21.0f, 29.0f);
        path.cubicTo(21.0f, 22.0f, 30.0f, 20.0f, 34.0f, 27.0f);
        path.cubicTo(38.0f, 20.0f, 47.0f, 22.0f, 47.0f, 29.0f);
        path.cubicTo(47.0f, 35.0f, 38.0f, 41.0f, 34.0f, 45.0f);
        path.close();
    }

    public final void a(int i, boolean z) {
        String str;
        if (this.d != i || this.e != z) {
            this.d = i;
            this.e = z;
            invalidate();
        }
        I2 i2 = this.a;
        if (i2.b()) {
            str = "自动点赞已开启，长按关闭，待点赞 " + i + " 条";
        } else if (z) {
            str = "正在点赞，点击停止，长按开启自动点赞，剩余 " + i + " 条";
        } else {
            str = (i2.a() ? "一键点赞已加载帖子，可点赞 " : "一键点赞当前可见帖子，可点赞 ") + i + " 条，长按开启自动点赞";
        }
        setContentDescription(str);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        boolean z = (getResources().getConfiguration().uiMode & 48) == 32;
        canvas.save();
        canvas.scale(getWidth() / 68.0f, getHeight() / 68.0f);
        Paint paint = this.b;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setColor(z ? -15527136 : -328966);
        canvas.drawCircle(34.0f, 34.0f, 24.0f, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0.8f);
        paint.setColor(z ? -12303273 : -2763297);
        canvas.drawCircle(34.0f, 34.0f, 24.0f, paint);
        paint.setStrokeWidth(1.8f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        if (this.e || isPressed()) {
            i = -16730718;
        } else {
            i = z ? -8948337 : -8027239;
        }
        paint.setColor(i);
        canvas.drawPath(this.c, paint);
        if (this.d > 0) {
            paint.setStyle(style);
            paint.setColor(z ? -13026995 : -1447440);
            canvas.drawCircle(52.0f, 16.0f, 7.0f, paint);
            paint.setColor(z ? -2763550 : -10066820);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(9.0f);
            int i2 = this.d;
            canvas.drawText(i2 > 99 ? "99" : String.valueOf(i2), 52.0f, 19.0f, paint);
        }
        canvas.restore();
    }
}
