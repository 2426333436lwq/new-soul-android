package dev.local.newsoul.feature.profile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * 插眼图标染色 Drawable（原 {@code v1}）。
 *
 * <p>包裹宿主菜单图标位图，并在其中心叠加一枚「圆底 + 书签」标记：
 * 圆底填充色取原位图中心像素色、书签描边色取菜单文字色。绘制参数与原实现逐字一致 ——
 * 标记以 {@code 48.0f} 为设计基准、随可用方形边长等比缩放。</p>
 */
final class ProfileBookmarkIconDrawable extends Drawable {

    private final BitmapDrawable source;
    private final Paint paint;
    private final Path bookmarkPath;
    private final int fillColor;
    private final int strokeColor;
    private int alpha = 0xFF;

    ProfileBookmarkIconDrawable(BitmapDrawable source, int strokeColor) {
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.bookmarkPath = new Path();
        this.alpha = 0xFF;
        this.source = source;
        this.strokeColor = strokeColor;
        Bitmap bitmap = source.getBitmap();
        this.fillColor = bitmap.getPixel(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        bookmarkPath.moveTo(18.0f, 13.0f);
        bookmarkPath.lineTo(30.0f, 13.0f);
        bookmarkPath.quadTo(32.0f, 13.0f, 32.0f, 15.0f);
        bookmarkPath.lineTo(32.0f, 35.0f);
        bookmarkPath.lineTo(24.0f, 30.0f);
        bookmarkPath.lineTo(16.0f, 35.0f);
        bookmarkPath.lineTo(16.0f, 15.0f);
        bookmarkPath.quadTo(16.0f, 13.0f, 18.0f, 13.0f);
        bookmarkPath.close();
        paint.setStrokeWidth(2.0f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        source.setBounds(bounds);
        source.draw(canvas);

        float size = Math.min(bounds.width(), bounds.height());
        if (size <= 0f) {
            return;
        }
        int saved = canvas.save();
        canvas.translate(
                bounds.left + (bounds.width() - size) / 2.0f,
                bounds.top + (bounds.height() - size) / 2.0f);
        canvas.scale(size / 48.0f, size / 48.0f);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
        paint.setAlpha(alpha);
        canvas.drawCircle(24.0f, 24.0f, 16.0f, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokeColor);
        paint.setAlpha(alpha);
        canvas.drawPath(bookmarkPath, paint);

        canvas.restoreToCount(saved);
    }

    @Override
    public int getIntrinsicWidth() {
        return source.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return source.getIntrinsicHeight();
    }

    @Override
    public int getOpacity() {
        return -3;
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        source.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        source.setColorFilter(colorFilter);
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
