package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Q ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class Q extends Ljava/lang/Object {

    public final TextView a;
    public final TextView b;
    public final TextView c;

    public void super(TextView p0) {
      super();
      this.a = p2;
      this.b = p3;
      this.c = Ldev/local/newsoul/w2.c(p1, "");
      Ldev/local/newsoul/w2.c(p1, "").setAccessibilityLiveRegion(0x1);
      Ldev/local/newsoul/w2.c(p1, "").setVisibility(0x8);
      return;
    }

    public final void a(Ljava/lang/String;Z p0) {
      p0.c.setText(p1);
      p0.c.setVisibility(0x0);
      p0.a.setEnabled((p2 ^ 0x1));
      p0.b.setEnabled((p2 ^ 0x1));
      if (p2 == 0) goto :cond_1d;
      goto :goto_1e;
      :cond_1d:
      :goto_1e:
      p0.a.setAlpha(0x3f000000    # 0.5f);
      if (p2 == 0) goto :cond_24;
      :cond_24:
      p0.b.setAlpha(0x3f800000    # 1.0f);
      return;
    }

}