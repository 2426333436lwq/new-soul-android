package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/T1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class T1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final Activity a;
    public final q2 b;
    public final LinearLayout c;
    public final boolean d;
    public final String e;
    public final long f;
    public final boolean g;

    public void super(Landroid/app/Activity;Ldev/local/newsoul/q2;Landroid/widget/LinearLayout;ZLjava/lang/String;JZ p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      this.e = p5;
      this.f = p6;
      this.g = p8;
      return;
    }

    public final void run() {
      :try_start_4:
      :try_end_8:
      if (p0.d == 0) goto :cond_1d;
      new Ldev/local/newsoul/P0(v0.a, p0.e, (ArrayList) v0.c, v0.d);
      goto :goto_1e;
      :cond_1d:
      :goto_1e:
      v9.<init>(v1, v2, p0.g, new Ldev/local/newsoul/P0(v0.a, p0.e, (ArrayList) v0.c, v0.d), p0.d);
      goto :goto_56;
      :catchall_42:
      new Ldev/local/newsoul/H(v1, v2, p0.c, 0xb);
      v2.runOnUiThread(new Ldev/local/newsoul/H(v1, v2, p0.c, 0xb));
      :goto_56:
      return;

      // .catchall {:try_start_4 .. :try_end_8} :catchall_42
    }

}