package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/w ====
// super: Ljava/lang/Object
// implements: Predicat
// source: "SourceFile"

public final  class w extends Ljava/lang/Object implements Ljava/util/function/Predicate {

    public final int a;
    public final Object b;

    public void super(ILjava/lang/Object; p0) {
      super();
      this.a = p1;
      this.b = p2;
      return;
    }

    public final boolean test(Object p0) {
      switch (p0.a) { /* :pswitch_data_2c */ }
      return (String) p0.b;
      :pswitch_12:
      if ((x) p1.a.get() == 0) goto :cond_29;
      if ((x) p1.a.get() != p0.b) goto :cond_27;
      goto :goto_29;
      :cond_27:
      goto :goto_2a;
      :cond_29:
      :goto_29:
      :goto_2a:
      return p0.b;
      :pswitch_data_2c:

      // .packed-switch 0x0
      // :pswitch_12
    }

}