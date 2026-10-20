package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/E2 ====
// super: Landroid/view/View
// source: "SourceFile"

public final  class E2 extends Landroid/view/View {

    public final I2 a;
    public final Paint b;
    public final Path c;
    public int d;
    public boolean e;

    public void super(I2 p0) {
      super(p1);
      new Landroid/graphics/Paint(0x1);
      this.b = new Landroid/graphics/Paint(0x1);
      new Landroid/graphics/Path();
      this.c = new Landroid/graphics/Path();
      this.a = p2;
      new Landroid/view/View(p1).setFocusable(0x1);
      new Landroid/graphics/Path().moveTo(0x42080000    # 34.0f, 0x42340000    # 45.0f);
      new Landroid/graphics/Path().cubicTo(0x41f00000    # 30.0f, 0x42240000    # 41.0f, 0x41a80000    # 21.0f, 0x420c0000    # 35.0f, 0x41a80000    # 21.0f, 0x41e80000    # 29.0f);
      new Landroid/graphics/Path().cubicTo(0x41a80000    # 21.0f, 0x41b00000    # 22.0f, 0x41f00000    # 30.0f, 0x41a00000    # 20.0f, 0x42080000    # 34.0f, 0x41d80000    # 27.0f);
      new Landroid/graphics/Path().cubicTo(0x42180000    # 38.0f, 0x41a00000    # 20.0f, 0x423c0000    # 47.0f, 0x41b00000    # 22.0f, 0x423c0000    # 47.0f, 0x41e80000    # 29.0f);
      new Landroid/graphics/Path().cubicTo(0x423c0000    # 47.0f, 0x420c0000    # 35.0f, 0x42180000    # 38.0f, 0x42240000    # 41.0f, 0x42080000    # 34.0f, 0x42340000    # 45.0f);
      new Landroid/graphics/Path().close();
      return;
    }

    public final void a(IZ p0) {
      if (p0.d != p1) goto :cond_8;
      if (p0.e == p2) goto :cond_f;
      :cond_8:
      p0.d = p1;
      p0.e = p2;
      p0.invalidate();
      :cond_f:
      if (p0.a.b() == 0) goto :cond_2b;
      new Ljava/lang/StringBuilder("自动点赞已开启，长按关闭，待点赞 ");
      goto :goto_5e;
      :cond_2b:
      if (new Ljava/lang/StringBuilder("自动点赞已开启，长按关闭，待点赞 ") == 0) goto :cond_3f;
      new Ljava/lang/StringBuilder("正在点赞，点击停止，长按开启自动点赞，剩余 ");
      goto :goto_5e;
      :cond_3f:
      if ("正在点赞，点击停止，长按开启自动点赞，剩余 ".a() == 0) goto :cond_48;
      goto :goto_4a;
      :cond_48:
      :goto_4a:
      new Ljava/lang/StringBuilder();
      :goto_5e:
      p0.setContentDescription(new Ljava/lang/StringBuilder("正在点赞，点击停止，长按开启自动点赞，剩余 ").toString());
      return;
    }

    public final void onDraw(Canvas p0) {
      super.onDraw(p1);
      if ((p0.getResources().getConfiguration().uiMode & 0x30) != 0x20) goto :cond_15;
      goto :goto_16;
      :cond_15:
      :goto_16:
      p1.scale((((float) p0.getWidth()) / 0x42880000    # 68.0f), (((float) p0.getHeight()) / 0x42880000    # 68.0f));
      p0.b.setStyle(Paint$Style.FILL);
      if (0x1 == 0) goto :cond_37;
      goto :goto_3a;
      :cond_37:
      :goto_3a:
      p0.b.setColor(-0xecece0);
      p1.drawCircle(0x42080000    # 34.0f, 0x42080000    # 34.0f, 0x41c00000    # 24.0f, p0.b);
      p0.b.setStyle(Paint$Style.STROKE);
      p0.b.setStrokeWidth(0x3f4ccccd    # 0.8f);
      if (0x1 == 0) goto :cond_55;
      goto :goto_58;
      :cond_55:
      :goto_58:
      p0.b.setColor(-0xbbbba9);
      p1.drawCircle(0x42080000    # 34.0f, 0x42080000    # 34.0f, 0x41c00000    # 24.0f, p0.b);
      p0.b.setStrokeWidth(0x3fe66666    # 1.8f);
      p0.b.setStrokeCap(Paint$Cap.ROUND);
      p0.b.setStrokeJoin(Paint$Join.ROUND);
      if (p0.e != 0) goto :cond_83;
      if (p0.isPressed() == 0) goto :cond_79;
      goto :goto_83;
      :cond_79:
      if (0x1 == 0) goto :cond_7f;
      goto :goto_86;
      :cond_7f:
      goto :goto_86;
      :cond_83:
      :goto_83:
      :goto_86:
      p0.b.setColor(-0x7a7c67);
      p1.drawPath(p0.c, p0.b);
      if (p0.d <= 0) goto :cond_d2;
      p0.b.setStyle(Paint$Style.FILL);
      if (0x1 == 0) goto :cond_9b;
      goto :goto_9e;
      :cond_9b:
      :goto_9e:
      p0.b.setColor(-0xc6c6b3);
      p1.drawCircle(0x42500000    # 52.0f, 0x41800000    # 16.0f, 0x40e00000    # 7.0f, p0.b);
      if (0x1 == 0) goto :cond_b0;
      goto :goto_b3;
      :cond_b0:
      :goto_b3:
      p0.b.setColor(-0x2a2b1e);
      p0.b.setTextAlign(Paint$Align.CENTER);
      p0.b.setTextSize(0x41100000    # 9.0f);
      if (p0.d <= 0x63) goto :cond_c9;
      goto :goto_cd;
      :cond_c9:
      :goto_cd:
      p1.drawText("99", 0x42500000    # 52.0f, 0x41980000    # 19.0f, p0.b);
      :cond_d2:
      p1.restore();
      return;
    }

}