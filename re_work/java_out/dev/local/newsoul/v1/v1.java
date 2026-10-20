package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/v1 ====
// super: Landroid/graphics/drawable/Drawable
// source: "SourceFile"

public final  class v1 extends Landroid/graphics/drawable/Drawable {

    public final BitmapDrawable a;
    public final Paint b;
    public final Path c;
    public final int d;
    public final int e;
    public int f;

    public void super(Landroid/graphics/drawable/BitmapDrawable;I p0) {
      super();
      new Landroid/graphics/Paint(0x1);
      this.b = new Landroid/graphics/Paint(0x1);
      new Landroid/graphics/Path();
      this.c = new Landroid/graphics/Path();
      this.f = 0xff;
      this.a = p1;
      this.e = p2;
      this.d = p1.getBitmap().getPixel((p1.getBitmap().getWidth() / 0x2), (p1.getBitmap().getHeight() / 0x2));
      new Landroid/graphics/Path().moveTo(0x41900000    # 18.0f, 0x41500000    # 13.0f);
      new Landroid/graphics/Path().lineTo(0x41f00000    # 30.0f, 0x41500000    # 13.0f);
      new Landroid/graphics/Path().quadTo(0x42000000    # 32.0f, 0x41500000    # 13.0f, 0x42000000    # 32.0f, 0x41700000    # 15.0f);
      new Landroid/graphics/Path().lineTo(0x42000000    # 32.0f, 0x420c0000    # 35.0f);
      new Landroid/graphics/Path().lineTo(0x41c00000    # 24.0f, 0x41f00000    # 30.0f);
      new Landroid/graphics/Path().lineTo(0x41800000    # 16.0f, 0x420c0000    # 35.0f);
      new Landroid/graphics/Path().lineTo(0x41800000    # 16.0f, 0x41700000    # 15.0f);
      new Landroid/graphics/Path().quadTo(0x41800000    # 16.0f, 0x41500000    # 13.0f, 0x41900000    # 18.0f, 0x41500000    # 13.0f);
      new Landroid/graphics/Path().close();
      new Landroid/graphics/Paint(0x1).setStrokeWidth(0x40000000    # 2.0f);
      new Landroid/graphics/Paint(0x1).setStrokeJoin(Paint$Join.ROUND);
      new Landroid/graphics/Paint(0x1).setStrokeCap(Paint$Cap.ROUND);
      return;
    }

    public final void draw(Canvas p0) {
      p0.a.setBounds(p0.getBounds());
      p0.a.draw(p1);
      if (compare(((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())), 0x0) > 0) goto :cond_27;
      return;
      :cond_27:
      p1.translate((((((float) p0.getBounds().width()) - ((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height()))) / 0x40000000    # 2.0f) + ((float) p0.getBounds().left)), (((((float) p0.getBounds().height()) - ((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height()))) / 0x40000000    # 2.0f) + ((float) p0.getBounds().top)));
      p1.scale((((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) / 0x42400000    # 48.0f), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) / 0x42400000    # 48.0f));
      p0.b.setStyle(Paint$Style.FILL);
      p0.b.setColor(p0.d);
      p0.b.setAlpha(p0.f);
      p1.drawCircle(0x41c00000    # 24.0f, 0x41c00000    # 24.0f, 0x41800000    # 16.0f, p0.b);
      p0.b.setStyle(Paint$Style.STROKE);
      p0.b.setColor(p0.e);
      p0.b.setAlpha(p0.f);
      p1.drawPath(p0.c, p0.b);
      p1.restoreToCount(p1.save());
      return;
    }

    public final int getIntrinsicHeight() {
      return p0.a.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
      return p0.a.getIntrinsicWidth();
    }

    public final int getOpacity() {
      return -0x3;
    }

    public final void setAlpha(int p0) {
      p0.f = p1;
      p0.a.setAlpha(p1);
      p0.invalidateSelf();
      return;
    }

    public final void setColorFilter(ColorFilter p0) {
      p0.a.setColorFilter(p1);
      p0.invalidateSelf();
      return;
    }

}