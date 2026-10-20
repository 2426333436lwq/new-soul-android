package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/i2 ====
// super: Landroid/view/View
// source: "SourceFile"

public final  class i2 extends Landroid/view/View {

    public final Paint a;
    public final boolean b;

    public void super(Landroid/content/Context;IZ p0) {
      super(p1);
      new Landroid/graphics/Paint(0x1);
      this.a = new Landroid/graphics/Paint(0x1);
      this.b = p3;
      new Landroid/graphics/Paint(0x1).setColor(p2);
      new Landroid/graphics/Paint(0x1).setStyle(Paint$Style.STROKE);
      new Landroid/graphics/Paint(0x1).setStrokeCap(Paint$Cap.ROUND);
      new Landroid/graphics/Paint(0x1).setStrokeJoin(Paint$Join.ROUND);
      return;
    }

    public final void onDraw(Canvas p0) {
      super.onDraw(p1);
      p1.scale((((float) p0.getWidth()) / 0x41e00000    # 28.0f), (((float) p0.getHeight()) / 0x41e00000    # 28.0f));
      if (p0.b == 0) goto :cond_20;
      goto :goto_23;
      :cond_20:
      :goto_23:
      p0.a.setStrokeWidth(0x40400000    # 3.0f);
      if (p0.b == 0) goto :cond_44;
      new Landroid/graphics/Path();
      new Landroid/graphics/Path().moveTo(0x40e00000    # 7.0f, 0x40a00000    # 5.0f);
      new Landroid/graphics/Path().lineTo(0x41a80000    # 21.0f, 0x41600000    # 14.0f);
      new Landroid/graphics/Path().lineTo(0x40e00000    # 7.0f, 0x41b80000    # 23.0f);
      p1.drawPath(new Landroid/graphics/Path(), p0.a);
      goto :goto_8b;
      :cond_44:
      p1.drawRoundRect(0x40400000    # 3.0f, 0x40400000    # 3.0f, 0x41c80000    # 25.0f, 0x41c80000    # 25.0f, 0x40800000    # 4.0f, 0x40800000    # 4.0f, p0.a);
      p1.drawLine(0x41000000    # 8.0f, 0x41000000    # 8.0f, 0x41000000    # 8.0f, 0x41200000    # 10.0f, p0.a);
      p1.drawCircle(0x41000000    # 8.0f, 0x41500000    # 13.0f, 0x4019999a    # 2.4f, p0.a);
      p1.drawLine(0x41000000    # 8.0f, 0x41800000    # 16.0f, 0x41000000    # 8.0f, 0x41a00000    # 20.0f, p0.a);
      p1.drawLine(0x41900000    # 18.0f, 0x41000000    # 8.0f, 0x41900000    # 18.0f, 0x41400000    # 12.0f, p0.a);
      p1.drawCircle(0x41900000    # 18.0f, 0x41700000    # 15.0f, 0x4019999a    # 2.4f, p0.a);
      p1.drawLine(0x41900000    # 18.0f, 0x41900000    # 18.0f, 0x41900000    # 18.0f, 0x41a00000    # 20.0f, p0.a);
      :goto_8b:
      p1.restore();
      return;
    }

}