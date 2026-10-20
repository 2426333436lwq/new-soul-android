package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/G1 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class G1 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;

    public void super(TextView p0) {
      super();
      this.a = 0x1;
      this.c = p1;
      this.d = p2;
      this.b = "正常检测项";
      return;
    }

    public void super(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p4;
      this.c = p1;
      this.d = p2;
      this.b = p3;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_ce */ }
      if ((b3) p0.c.n == 0) goto :cond_16;
      goto :goto_38;
      :cond_16:
      :try_start_16:
      (b3) p0.c.j();
      (P2) p0.d.a(Ldev/local/newsoul/x1.e((EditText) p0.b.getText().toString()));
      :try_end_28:
      goto :goto_38;
      :catchall_29:
      if ((e instanceof IllegalArgumentException) == 0) goto :cond_33;
      goto :goto_35;
      :cond_33:
      :goto_35:
      (EditText) p0.b.setError(e.getMessage());
      :goto_38:
      return;
      :pswitch_39:
      if ((TextView) p0.c.getVisibility() == 0) goto :cond_46;
      goto :goto_47;
      :cond_46:
      :goto_47:
      if (0x1 == 0) goto :cond_4a;
      goto :goto_4c;
      :cond_4a:
      :goto_4c:
      (TextView) p0.c.setVisibility(0x0);
      if (0x1 == 0) goto :cond_64;
      new Ljava/lang/StringBuilder("收起");
      goto :goto_65;
      :cond_64:
      :goto_65:
      (TextView) p0.d.setText(new Ljava/lang/StringBuilder("收起").toString());
      if (0x1 == 0) goto :cond_71;
      goto :goto_73;
      :cond_71:
      :goto_73:
      new Ljava/lang/StringBuilder();
      (TextView) p0.d.setContentDescription(new Ljava/lang/StringBuilder().toString());
      return;
      :pswitch_86:
      new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g, "", 0xf, new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).i).setText((CharSequence) p0.d);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g, "", 0xf, new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).i).setLineSpacing(((float) Ldev/local/newsoul/w2.i(new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g, 0x4)), 0x3f800000    # 1.0f);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g, "", 0xf, new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).i).setGravity(0x800003);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g, "", 0xf, new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).i).setBreakStrategy(0x0);
      Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g, "", 0xf, new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).i).setHyphenationFrequency(0x0);
      new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g, "", 0xf, new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).i));
      new Ldev/local/newsoul/q2((q2) p0.c.a, (q2) p0.c.b).g((String) p0.b, "知道了");
      return;
      :pswitch_data_ce:

      // .packed-switch 0x0
      // :pswitch_86
      // :pswitch_39

      // .catchall {:try_start_16 .. :try_end_28} :catchall_29
    }

}