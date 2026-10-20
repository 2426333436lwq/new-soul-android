package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/E0 ====
// super: Landroid/view/View
// source: "SourceFile"

public final  class E0 extends Landroid/view/View {

    public final int a;
    public final Paint b;

    public void super(Landroid/content/Context;I p0) {
      super(p1);
      new Landroid/graphics/Paint(0x1);
      this.b = new Landroid/graphics/Paint(0x1);
      this.a = p2;
      return;
    }

    public final void onDraw(Canvas p0) {
      super.onDraw(p1);
      p1.scale((((float) p0.getWidth()) / 0x42700000    # 60.0f), (((float) p0.getHeight()) / 0x42700000    # 60.0f));
      p0.b.setColor(-0x50504);
      p1.drawRoundRect(0x40a00000    # 5.0f, 0x40a00000    # 5.0f, 0x425c0000    # 55.0f, 0x425c0000    # 55.0f, 0x41300000    # 11.0f, 0x41300000    # 11.0f, p0.b);
      p0.b.setColor(-0xcbcbc0);
      if ((p0.a % 0x2) != 0x1) goto :cond_45;
      p1.drawCircle(0x41f00000    # 30.0f, 0x41f00000    # 30.0f, 0x406ccccd    # 3.7f, p0.b);
      :cond_45:
      if (p0.a < 0x2) goto :cond_52;
      p1.drawCircle(0x41900000    # 18.0f, 0x41900000    # 18.0f, 0x406ccccd    # 3.7f, p0.b);
      p1.drawCircle(0x42280000    # 42.0f, 0x42280000    # 42.0f, 0x406ccccd    # 3.7f, p0.b);
      :cond_52:
      if (p0.a < 0x4) goto :cond_5b;
      p1.drawCircle(0x42280000    # 42.0f, 0x41900000    # 18.0f, 0x406ccccd    # 3.7f, p0.b);
      p1.drawCircle(0x41900000    # 18.0f, 0x42280000    # 42.0f, 0x406ccccd    # 3.7f, p0.b);
      :cond_5b:
      if (p0.a != 0x6) goto :cond_64;
      p1.drawCircle(0x41900000    # 18.0f, 0x41f00000    # 30.0f, 0x406ccccd    # 3.7f, p0.b);
      p1.drawCircle(0x42280000    # 42.0f, 0x41f00000    # 30.0f, 0x406ccccd    # 3.7f, p0.b);
      :cond_64:
      p1.restore();
      return;
    }

}