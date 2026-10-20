package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/i1 ====
// super: Landroid/graphics/drawable/Drawable
// source: "SourceFile"

public final  class i1 extends Landroid/graphics/drawable/Drawable {

    public final Paint a;
    public final float b;
    public int c;
    public boolean d;

    public void super(float p0) {
      super();
      new Landroid/graphics/Paint(0x1);
      this.a = new Landroid/graphics/Paint(0x1);
      this.c = 0xff;
      this.d = 0x1;
      this.b = p1;
      return;
    }

    public final void draw(Canvas p0) {
      if (compare(Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)), 0x0) > 0) goto :cond_28;
      return;
      :cond_28:
      new Landroid/graphics/Path();
      new Landroid/graphics/Path().moveTo(((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b), (((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b) - (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f)));
      new Landroid/graphics/Path().quadTo((((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b) + (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), (((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b) - (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), (((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b) + (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f)), ((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b));
      new Landroid/graphics/Path().quadTo((((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b) + (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), (((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b) + (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), ((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b), (((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b) + (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f)));
      new Landroid/graphics/Path().quadTo((((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b) - (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), (((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b) + (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), (((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b) - (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f)), ((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b));
      new Landroid/graphics/Path().quadTo((((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b) - (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), (((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b) - (0x3e2e147b    # 0.17f * (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f))), ((((float) p0.getBounds().right) - (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) - p0.b), (((((float) p0.getBounds().top) + (0x3f266666    # 0.65f * Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)))) + p0.b) - (Ljava/lang/Math.min((0x41200000    # 10.0f * p0.b), (((float) Ljava/lang/Math.min(p0.getBounds().width(), p0.getBounds().height())) * 0x3e851eb8    # 0.26f)) / 0x40000000    # 2.0f)));
      new Landroid/graphics/Path().close();
      p0.a.setAlpha(p0.c);
      p0.a.setStyle(Paint$Style.STROKE);
      p0.a.setStrokeWidth((p0.b * 0x3f000000    # 0.5f));
      p0.a.setColor(-0x1f0008);
      p1.drawPath(new Landroid/graphics/Path(), p0.a);
      p0.a.setStyle(Paint$Style.FILL);
      p0.a.setColor(-0xc7203c);
      p1.drawPath(new Landroid/graphics/Path(), p0.a);
      return;
    }

    public final int getOpacity() {
      return -0x3;
    }

    public final void setAlpha(int p0) {
      p0.c = p1;
      p0.invalidateSelf();
      return;
    }

    public final void setColorFilter(ColorFilter p0) {
      p0.invalidateSelf();
      return;
    }

}