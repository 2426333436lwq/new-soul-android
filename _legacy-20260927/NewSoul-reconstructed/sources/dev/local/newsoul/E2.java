/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Align
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
import dev.local.newsoul.I2;

public final class E2
extends View {
    public final I2 a;
    public final Paint b = new Paint(1);
    public final Path c;
    public int d;
    public boolean e;

    public E2(Context context, I2 i2) {
        super(context);
        context = new Path();
        this.c = context;
        this.a = i2;
        this.setFocusable(true);
        context.moveTo(34.0f, 45.0f);
        context.cubicTo(30.0f, 41.0f, 21.0f, 35.0f, 21.0f, 29.0f);
        context.cubicTo(21.0f, 22.0f, 30.0f, 20.0f, 34.0f, 27.0f);
        context.cubicTo(38.0f, 20.0f, 47.0f, 22.0f, 47.0f, 29.0f);
        context.cubicTo(47.0f, 35.0f, 38.0f, 41.0f, 34.0f, 45.0f);
        context.close();
    }

    public final void a(int n, boolean bl) {
        Object object;
        if (this.d != n || this.e != bl) {
            this.d = n;
            this.e = bl;
            this.invalidate();
        }
        if (((I2)(object = this.a)).b()) {
            object = new StringBuilder("\u81ea\u52a8\u70b9\u8d5e\u5df2\u5f00\u542f\uff0c\u957f\u6309\u5173\u95ed\uff0c\u5f85\u70b9\u8d5e ");
            ((StringBuilder)object).append(n);
            ((StringBuilder)object).append(" \u6761");
            object = ((StringBuilder)object).toString();
        } else if (bl) {
            object = new StringBuilder("\u6b63\u5728\u70b9\u8d5e\uff0c\u70b9\u51fb\u505c\u6b62\uff0c\u957f\u6309\u5f00\u542f\u81ea\u52a8\u70b9\u8d5e\uff0c\u5269\u4f59 ");
            ((StringBuilder)object).append(n);
            ((StringBuilder)object).append(" \u6761");
            object = ((StringBuilder)object).toString();
        } else {
            object = ((I2)object).a() ? "\u4e00\u952e\u70b9\u8d5e\u5df2\u52a0\u8f7d\u5e16\u5b50\uff0c\u53ef\u70b9\u8d5e " : "\u4e00\u952e\u70b9\u8d5e\u5f53\u524d\u53ef\u89c1\u5e16\u5b50\uff0c\u53ef\u70b9\u8d5e ";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((String)object);
            stringBuilder.append(n);
            stringBuilder.append(" \u6761\uff0c\u957f\u6309\u5f00\u542f\u81ea\u52a8\u70b9\u8d5e");
            object = stringBuilder.toString();
        }
        this.setContentDescription((CharSequence)object);
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean bl = (this.getResources().getConfiguration().uiMode & 0x30) == 32;
        canvas.save();
        canvas.scale((float)this.getWidth() / 68.0f, (float)this.getHeight() / 68.0f);
        Paint paint = this.b;
        Object object = Paint.Style.FILL;
        paint.setStyle(object);
        int n = bl ? -15527136 : -328966;
        paint.setColor(n);
        canvas.drawCircle(34.0f, 34.0f, 24.0f, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0.8f);
        n = bl ? -12303273 : -2763297;
        paint.setColor(n);
        canvas.drawCircle(34.0f, 34.0f, 24.0f, paint);
        paint.setStrokeWidth(1.8f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        n = !this.e && !this.isPressed() ? (bl ? -8948337 : -8027239) : -16730718;
        paint.setColor(n);
        canvas.drawPath(this.c, paint);
        if (this.d > 0) {
            paint.setStyle(object);
            n = bl ? -13026995 : -1447440;
            paint.setColor(n);
            canvas.drawCircle(52.0f, 16.0f, 7.0f, paint);
            n = bl ? -2763550 : -10066820;
            paint.setColor(n);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(9.0f);
            n = this.d;
            object = n > 99 ? "99" : String.valueOf(n);
            canvas.drawText((String)object, 52.0f, 19.0f, paint);
        }
        canvas.restore();
    }
}

