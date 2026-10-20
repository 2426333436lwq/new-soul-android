package dev.local.newsoul;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class E0 extends View {
    public final int a;
    public final Paint b;

    public E0(Context context, int i) {
        super(context);
        this.b = new Paint(1);
        this.a = i;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(getWidth() / 60.0f, getHeight() / 60.0f);
        Paint paint = this.b;
        paint.setColor(-328964);
        canvas.drawRoundRect(5.0f, 5.0f, 55.0f, 55.0f, 11.0f, 11.0f, paint);
        paint.setColor(-13355968);
        int i = this.a;
        if (i % 2 == 1) {
            canvas.drawCircle(30.0f, 30.0f, 3.7f, paint);
        }
        if (i >= 2) {
            canvas.drawCircle(18.0f, 18.0f, 3.7f, paint);
            canvas.drawCircle(42.0f, 42.0f, 3.7f, paint);
        }
        if (i >= 4) {
            canvas.drawCircle(42.0f, 18.0f, 3.7f, paint);
            canvas.drawCircle(18.0f, 42.0f, 3.7f, paint);
        }
        if (i == 6) {
            canvas.drawCircle(18.0f, 30.0f, 3.7f, paint);
            canvas.drawCircle(42.0f, 30.0f, 3.7f, paint);
        }
        canvas.restore();
    }
}
