package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/d ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class d extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final boolean a;
    public final String b;

    public void super(Ljava/lang/String;Z p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      if (p0.a == 0) goto :cond_e;
      goto :goto_12;
      :cond_e:
      :goto_12:
      :try_start_12:
      :try_end_22:
      goto :goto_43;
      :catch_23:
      :try_start_24:
      :try_end_42:
      :catch_43:
      :goto_43:
      if (0x0 == 0) goto :cond_4a;
      Ldev/local/newsoul/j.i(0x0, p0.b);
      :cond_4a:
      return p1.proceed();

      // try :try_start_12..:try_end_22 catch (Ljava/lang/Exception) -> :catch_23
      // try :try_start_24..:try_end_42 catch (Ljava/lang/Exception) -> :catch_43
    }

}