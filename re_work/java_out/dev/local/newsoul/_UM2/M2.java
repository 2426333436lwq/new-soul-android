package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/M2 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class M2 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final Activity a;
    public final q2 b;
    public final TextView c;
    public final String d;
    public final TextView e;
    public final boolean f;
    public final Y g;
    public final String h;
    public final String[] i;

    public void super(String p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = 0x0;
      this.g = p6;
      this.h = p7;
      this.i = p8;
      return;
    }

    public final void run() {
      if (p0.a.isFinishing() != 0) goto :cond_b5;
      if (p0.a.isDestroyed() != 0) goto :cond_b5;
      if (p0.b.e == 0) goto :cond_b5;
      if (p0.b.e.isShowing() != 0) goto :cond_1c;
      goto :goto_b5;
      :cond_1c:
      p0.c.setEnabled(0x1);
      p0.c.setAlpha(0x3f800000    # 1.0f);
      if (p0.d == 0) goto :cond_34;
      p0.e.setText("检测失败，请重试");
      goto :goto_b5;
      :cond_34:
      if (p0.f == 0) goto :cond_52;
      new Ljava/lang/StringBuilder("发布渠道版本：");
      p0.e.setText(new Ljava/lang/StringBuilder("发布渠道版本：").toString());
      goto :goto_9e;
      :cond_52:
      if (p0.h != 0) goto :cond_6f;
      new Ljava/lang/StringBuilder("最新版本：");
      p0.e.setText(new Ljava/lang/StringBuilder("最新版本：").toString());
      goto :goto_9e;
      :cond_6f:
      if (Ldev/local/newsoul/T0.c((String) p0.g.a, p0.h) <= 0) goto :cond_7c;
      goto :goto_83;
      :cond_7c:
      if ("发现新版本" != 0) goto :cond_81;
      goto :goto_83;
      :cond_81:
      :goto_83:
      new Ljava/lang/StringBuilder(new Ljava/lang/StringBuilder("最新版本：").toString());
      p0.e.setText(new Ljava/lang/StringBuilder(new Ljava/lang/StringBuilder("最新版本：").toString()).toString());
      :goto_9e:
      if (p0.f != 0) goto :cond_ac;
      if (p0.h == 0) goto :cond_ac;
      if (Ldev/local/newsoul/T0.c((String) p0.g.a, p0.h) <= 0) goto :cond_b5;
      :cond_ac:
      p0.i[0x0] = (String) p0.g.b;
      :cond_b5:
      :goto_b5:
      return;
    }

}