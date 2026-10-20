package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/f ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class f extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_82 */ }
      if (Boolean.TRUE.equals(p1.proceed()) == 0) goto :cond_36;
      if (Ldev/local/newsoul/Q0.q("chat_remove_social_score_limit") == 0) goto :cond_36;
      new Ljava/lang/StringBuilder("social score gate bypassed: ");
      :cond_36:
      return Ldev/local/newsoul/Q0.q("chat_remove_social_score_limit");
      :pswitch_37:
      if (Ldev/local/newsoul/Q0.R((Activity) p1.proceed().getThisObject()) != 0) goto :cond_48;
      goto :goto_5e;
      :cond_48:
      new Ljava/lang/StringBuilder("privacy repeat blocked consume: route=");
      Ldev/local/newsoul/Q0.w(new Ljava/lang/StringBuilder("privacy repeat blocked consume: route=").toString());
      :goto_5e:
      return Ldev/local/newsoul/Q0.R((Activity) p1.proceed().getThisObject());
      :pswitch_5f:
      if ((View) p1.proceed().proceed().getThisObject().getContext() != 0) goto :cond_70;
      goto :goto_79;
      :cond_70:
      if ((View) p1.proceed().proceed().getThisObject().getContext().getApplicationContext() == 0) goto :cond_77;
      :cond_77:
      j.h = (View) p1.proceed().proceed().getThisObject().getContext();
      :goto_79:
      Ldev/local/newsoul/j.i((View) p1.proceed().proceed().getThisObject(), (String) p0.b);
      return p1.proceed().proceed().proceed();
      :pswitch_data_82:

      // .packed-switch 0x0
      // :pswitch_5f
      // :pswitch_37
    }

}