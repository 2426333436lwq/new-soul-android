package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/P0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class P0 extends Ljava/lang/Object {

    public final String a;
    public final String b;
    public final List c;
    public final int d;
    public final int e;

    public void super(Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;I p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      this.d = p4;
      :cond_10:
      :goto_10:
      if (p1.hasNext() == 0) goto :cond_27;
      if ((String) p1.next().contains("Hook 未就绪") == 0) goto :cond_10;
      goto :goto_10;
      :cond_27:
      this.e = (p2 + 0x1);
      return;
    }

    public final String a(boolean p0) {
      new Ljava/lang/StringBuilder();
      :cond_b:
      :goto_b:
      if (v1.hasNext() == 0) goto :cond_4a;
      if ((String) v1.next().contains("Hook 点缺失") != 0) goto :cond_2b;
      if ((String) v1.next().contains("Hook 未就绪") == 0) goto :cond_29;
      goto :goto_2b;
      :cond_29:
      goto :goto_2c;
      :cond_2b:
      :goto_2b:
      :goto_2c:
      if (0x0 != p1) goto :cond_b;
      if (v0.length() <= 0) goto :cond_3f;
      :cond_3f:
      if ((String) v1.next().indexOf(0xa) >= 0) goto :cond_42;
      goto :goto_46;
      :cond_42:
      :goto_46:
      goto :goto_b;
      :cond_4a:
      return v0;
    }

    public final String b() {
      :cond_7:
      :goto_7:
      if (v0.hasNext() == 0) goto :cond_26;
      if ((String) v0.next().contains("Hook 点缺失") != 0) goto :cond_23;
      if ((String) v0.next().contains("Hook 未就绪") == 0) goto :cond_7;
      :cond_23:
      goto :goto_7;
      :cond_26:
      if ((v1 + 0x1) != 0) goto :cond_2b;
      goto :goto_3e;
      :cond_2b:
      new Ljava/lang/StringBuilder("部分检测项需要检查（");
      :goto_3e:
      if (p0.b == 0) goto :cond_53;
      if (p0.b.equals(p0.a) != 0) goto :cond_53;
      goto :goto_54;
      :cond_53:
      :goto_54:
      if (p0.a.startsWith("6.37.0") != 0) goto :cond_5e;
      :cond_5e:
      new Ljava/lang/StringBuilder();
      return new Ljava/lang/StringBuilder().toString();
    }

}