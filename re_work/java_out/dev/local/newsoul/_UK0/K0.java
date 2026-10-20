package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/K0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class K0 extends Ljava/lang/Object {

    public final Paint a;
    public final Path b;
    public Y c;
    public boolean d;
    public Bitmap e;
    public Canvas f;

    public void super() {
      super();
      new Landroid/graphics/Paint(0x3);
      this.a = new Landroid/graphics/Paint(0x3);
      new Landroid/graphics/Path();
      this.b = new Landroid/graphics/Path();
      return;
    }

    public final void a(U2 p0) {
      if (p2 <= 0) goto :cond_139;
      if (p3 <= 0) goto :cond_139;
      if (p4.isEmpty() == 0) goto :cond_18;
      goto :goto_139;
      :cond_18:
      p0.b.reset();
      p0.b.addRoundRect(p4, p5, p5, Path$Direction.CW);
      :try_start_29:
      if (Build$VERSION.SDK_INT < 0x21) goto :cond_87;
      if (p1.isHardwareAccelerated() == 0) goto :cond_87;
      :try_end_38:
      if (p1.isHardwareAccelerated() != 0) goto :cond_87;
      :try_start_3a:
      if (p0.c != 0) goto :cond_4d;
      new Ldev/local/newsoul/Y();
      p0.c = new Ldev/local/newsoul/Y();
      goto :goto_4d;
      :catchall_46:
      goto :goto_135;
      :catch_4a:
      goto :goto_6e;
      :cond_4d:
      :goto_4d:
      :try_end_4f:
      :try_start_63:
      0x21.a(v3, v4, v5, v6, v7, v8, 0x0, v10, p5);
      :try_end_66:
      p1.restoreToCount(v10);
      return;
      :catchall_6a:
      goto :goto_135;
      :catch_6d:
      :goto_6e:
      :try_start_6f:
      p0.d = 0x21;
      if (p0.c == 0) goto :cond_7c;
      Ldev/local/newsoul/H0.g((RenderNode) p0.c.a);
      :cond_7c:
      p0.c = 0x0;
      goto :goto_89;
      :cond_87:
      :goto_89:
      if (p0.e == 0) goto :cond_9b;
      if (p0.e.getWidth() != p2) goto :cond_9b;
      if (p0.e.getHeight() == p3) goto :cond_ac;
      :cond_9b:
      p0.e = Landroid/graphics/Bitmap.createBitmap(p2, p3, Bitmap$Config.ARGB_8888);
      new Landroid/graphics/Canvas(p0.e);
      p0.f = new Landroid/graphics/Canvas(p0.e);
      :cond_ac:
      p0.e.eraseColor(0x0);
      p9.a(p0.f);
      p1.scale(((0x3e23d70a    # 0.16f * p6) + 0x3f800000    # 1.0f), ((0x3e23d70a    # 0.16f * p6) + 0x3f800000    # 1.0f), p4.centerX(), p4.centerY());
      :try_end_d2:
      :try_start_d5:
      p1.drawBitmap("SoulHook102", v5, v5, "Glass lens uses Canvas fallback");
      p1.restoreToCount(p0.e.getHeight());
      if (p8 == 0) goto :cond_ec;
      :goto_e9:
      goto :goto_f0;
      :cond_ec:
      goto :goto_e9;
      :goto_f0:
      p0.e.getHeight().<init>(0x0, p4.top, 0x0, p4.bottom, v7, 0x0, Shader$TileMode.CLAMP);
      p1.drawRoundRect(p4, p5, p5, "Glass lens uses Canvas fallback");
      "Glass lens uses Canvas fallback".setStyle(Paint$Style.STROKE);
      "Glass lens uses Canvas fallback".setStrokeWidth((0x3f19999a    # 0.6f * p7));
      if (p8 == 0) goto :cond_11f;
      goto :goto_122;
      :cond_11f:
      :goto_122:
      "Glass lens uses Canvas fallback".setColor(0x35ffffff);
      p1.drawRoundRect(p4, p5, p5, "Glass lens uses Canvas fallback");
      "Glass lens uses Canvas fallback".setStyle(Paint$Style.FILL);
      "Glass lens uses Canvas fallback".setColor(-0x1);
      :try_end_131:
      p1.restoreToCount(v10);
      return;
      :goto_135:
      p1.restoreToCount(v10);
      throw 0x35ffffff;
      :cond_139:
      :goto_139:
      return;

      // .catchall {:try_start_29 .. :try_end_38} :catchall_46
      // try :try_start_3a..:try_end_4f catch (Ljava/lang/RuntimeException) -> :catch_4a
      // .catchall {:try_start_3a .. :try_end_4f} :catchall_46
      // try :try_start_63..:try_end_66 catch (Ljava/lang/RuntimeException) -> :catch_6d
      // .catchall {:try_start_63 .. :try_end_66} :catchall_6a
      // .catchall {:try_start_6f .. :try_end_d2} :catchall_6a
      // .catchall {:try_start_d5 .. :try_end_131} :catchall_6a
    }

}