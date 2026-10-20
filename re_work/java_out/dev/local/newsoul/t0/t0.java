package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/t0 ====
// super: Ljava/lang/Object
// implements: BooleanSupplie
// source: "SourceFile"

public final  class t0 extends Ljava/lang/Object implements Ljava/util/function/BooleanSupplier {

    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;

    public void super(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;I p0) {
      super();
      this.a = p5;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      return;
    }

    public final boolean getAsBoolean() {
      switch (p0.a) { /* :pswitch_data_15a */ }
      if ((EditText) p0.b.getText().toString().trim().isEmpty() == 0) goto :cond_2a;
      goto :goto_110;
      :cond_2a:
      :try_start_2b:
      new Ljava/lang/StringBuilder();
      :try_end_41:
      if ("\\n" != 0) goto :cond_46;
      :cond_46:
      :try_start_46:
      if ("\\n".length() > 0x2ee0) goto :cond_100;
      new Ljava/util/LinkedHashSet();
      :goto_59:
      if (v11 >= 0x2ee0) goto :cond_84;
      if ("\\n"[v11].trim().toLowerCase(Locale.ROOT).isEmpty() == 0) goto :cond_70;
      goto :goto_79;
      :cond_70:
      if ("\\n"[v11].trim().toLowerCase(Locale.ROOT).length() > 0x64) goto :cond_7c;
      :goto_79:
      goto :goto_59;
      :cond_7c:
      new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      throw new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      :cond_84:
      if ("\\n".length().size() > 0x64) goto :cond_f8;
      new Ljava/util/ArrayList("\\n".length());
      new Ljava/util/HashSet();
      :goto_9b:
      if ("\\n".length().hasNext() == 0) goto :cond_b3;
      goto :goto_9b;
      :catch_b1:
      goto :goto_108;
      :cond_b3:
      :try_end_b8:
      :goto_b9:
      if ("\\n".length() >= (EditText) p0.b.getText().toString().trim()) goto :cond_df;
      :try_start_bf:
      if ("\\\\R"["\\n".length()].trim().isEmpty() != 0) goto :cond_dc;
      if ("\\n".length().size().add("\\\\R"["\\n".length()].trim().toLowerCase(Locale.ROOT)) == 0) goto :cond_dc;
      (String[]) p0.d[(EditText) p0.b.getText().toString().trim().isEmpty()] = "\\\\R"["\\n".length()].trim();
      :cond_dc:
      goto :goto_b9;
      :cond_df:
      (EditText) p0.b.setText("");
      (EditText) p0.b.setError(0x0);
      (Runnable) p0.e.run();
      new Ldev/local/newsoul/g((String[]) p0.d, (d2) (Runnable) p0.e, 0xb);
      goto :goto_110;
      :cond_f8:
      new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      throw new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      :cond_100:
      new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      throw new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      :try_end_108:
      :goto_108:
      (EditText) p0.b.setError(new Ldev/local/newsoul/g((String[]) p0.d, (d2) (Runnable) p0.e, 0xb).getMessage());
      :goto_110:
      return new Ldev/local/newsoul/g((String[]) p0.d, (d2) (Runnable) p0.e, 0xb);
      :pswitch_111:
      if (((y0) p0.b.f.equals((y0) p0.b.b()) ^ 0x1) != 0) goto :cond_158;
      if (((y0) p0.c.f.equals((y0) p0.c.b()) ^ 0x1) != 0) goto :cond_158;
      if (((y0) p0.d.f.equals((y0) p0.d.b()) ^ 0x1) != 0) goto :cond_158;
      if (((y0) p0.e.f.equals((y0) p0.e.b()) ^ 0x1) == 0) goto :cond_157;
      goto :goto_158;
      :cond_157:
      :cond_158:
      :goto_158:
      return ((y0) p0.e.f.equals((y0) p0.e.b()) ^ 0x1);
      :pswitch_data_15a:

      // .packed-switch 0x0
      // :pswitch_111

      // try :try_start_2b..:try_end_41 catch (Ljava/lang/IllegalArgumentException) -> :catch_b1
      // try :try_start_46..:try_end_b8 catch (Ljava/lang/IllegalArgumentException) -> :catch_b1
      // try :try_start_bf..:try_end_108 catch (Ljava/lang/IllegalArgumentException) -> :catch_b1
    }

}