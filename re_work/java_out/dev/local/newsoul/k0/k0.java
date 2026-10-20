package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/k0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class k0 extends Ljava/lang/Object {

    public final Object a;

    public void super(v2 p0) {
      super();
      this.a = p1;
      return;
    }

    public void super(String p0) {
      super();
      if (p1 != 0) goto :cond_7;
      :cond_7:
      if (p1.length() > 0x2ee0) goto :cond_61;
      new Ljava/util/LinkedHashSet();
      :goto_1c:
      if (v2 >= 0x2ee0) goto :cond_47;
      if (p1[v2].trim().toLowerCase(Locale.ROOT).isEmpty() == 0) goto :cond_33;
      goto :goto_3c;
      :cond_33:
      if (p1[v2].trim().toLowerCase(Locale.ROOT).length() > 0x64) goto :cond_3f;
      :goto_3c:
      goto :goto_1c;
      :cond_3f:
      new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      throw new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      :cond_47:
      if (p1.length().size() > 0x64) goto :cond_59;
      new Ljava/util/ArrayList(p1.length());
      this.a = Ljava/util/Collections.unmodifiableList(new Ljava/util/ArrayList(p1.length()));
      return;
      :cond_59:
      new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      throw new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      :cond_61:
      new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      throw new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
    }

    public boolean a(String p0) {
      if (p1 == 0) goto :cond_2c;
      if (p1.isEmpty() == 0) goto :cond_a;
      goto :goto_2c;
      :cond_a:
      :cond_18:
      if (p1.isEmpty().hasNext() == 0) goto :cond_2c;
      if (p1.contains((String) p1.isEmpty().next()) == 0) goto :cond_18;
      return 0x0;
      :cond_2c:
      :goto_2c:
      return 0x0;
    }

}