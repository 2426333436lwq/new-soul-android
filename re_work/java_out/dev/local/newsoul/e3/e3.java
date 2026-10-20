package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/e3 ====
// super: Landroid/graphics/drawable/Drawable
// source: "SourceFile"

public final  class e3 extends Landroid/graphics/drawable/Drawable {

    public final Paint a;
    public final Path b;
    public final Path c;
    public final int d;

    public void super(float p0) {
      super();
      new Landroid/graphics/Paint(0x1);
      this.a = new Landroid/graphics/Paint(0x1);
      new Landroid/graphics/Path();
      this.b = new Landroid/graphics/Path();
      new Landroid/graphics/Path();
      this.c = new Landroid/graphics/Path();
      this.d = Ljava/lang/Math.round((p1 * 0x41c00000    # 24.0f));
      new Landroid/graphics/Paint(0x1).setColor(-0x1);
      new Landroid/graphics/Paint(0x1).setStyle(Paint$Style.STROKE);
      new Landroid/graphics/Paint(0x1).setStrokeWidth(0x3fd33333    # 1.65f);
      new Landroid/graphics/Paint(0x1).setStrokeCap(Paint$Cap.ROUND);
      new Landroid/graphics/Paint(0x1).setStrokeJoin(Paint$Join.ROUND);
      new Landroid/graphics/Path().moveTo(0x41400000    # 12.0f, 0x40800000    # 4.0f);
      new Landroid/graphics/Path().lineTo(0x41400000    # 12.0f, 0x41600000    # 14.0f);
      new Landroid/graphics/Path().moveTo(0x41000000    # 8.0f, 0x41200000    # 10.0f);
      new Landroid/graphics/Path().lineTo(0x41400000    # 12.0f, 0x41600000    # 14.0f);
      new Landroid/graphics/Path().lineTo(0x41800000    # 16.0f, 0x41200000    # 10.0f);
      new Landroid/graphics/Path().moveTo(0x40800000    # 4.0f, 0x41600000    # 14.0f);
      new Landroid/graphics/Path().lineTo(0x40800000    # 4.0f, 0x41900000    # 18.0f);
      new Landroid/graphics/Path().quadTo(0x40800000    # 4.0f, 0x41a00000    # 20.0f, 0x40c00000    # 6.0f, 0x41a00000    # 20.0f);
      new Landroid/graphics/Path().lineTo(0x41900000    # 18.0f, 0x41a00000    # 20.0f);
      new Landroid/graphics/Path().quadTo(0x41a00000    # 20.0f, 0x41a00000    # 20.0f, 0x41a00000    # 20.0f, 0x41900000    # 18.0f);
      new Landroid/graphics/Path().lineTo(0x41a00000    # 20.0f, 0x41600000    # 14.0f);
      return;
    }

    public final void draw(Canvas p0) {
      if (compare(((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())), 0x0) > 0) goto :cond_1b;
      return;
      :cond_1b:
      p1.translate((((((float) p0.getBounds().width()) - ((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height()))) / 0x40000000    # 2.0f) + ((float) p0.getBounds().left)), (((((float) p0.getBounds().height()) - ((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height()))) / 0x40000000    # 2.0f) + ((float) p0.getBounds().top)));
      p1.scale((((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) / 0x41c00000    # 24.0f), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) / 0x41c00000    # 24.0f));
      p1.drawPath(p0.b, p0.a);
      p1.drawPath(p0.c, p0.a);
      p1.restoreToCount(p1.save());
      return;
    }

    public final int getIntrinsicHeight() {
      return p0.d;
    }

    public final int getIntrinsicWidth() {
      return p0.d;
    }

    public final int getOpacity() {
      return -0x3;
    }

    public final void setAlpha(int p0) {
      p0.a.setAlpha(p1);
      p0.invalidateSelf();
      return;
    }

    public final void setColorFilter(ColorFilter p0) {
      p0.invalidateSelf();
      return;
    }

}