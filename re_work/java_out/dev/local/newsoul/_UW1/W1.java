package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/W1 ====
// super: Ljava/lang/Object
// implements: View$OnClickListene
// source: "SourceFile"

public final  class W1 extends Ljava/lang/Object implements Landroid/view/View$OnClickListener {

    public final int a;
    public final ArrayList b;
    public final Runnable c;
    public final EditText d;
    public final Object e;
    public final Object f;
    public final Object g;

    public void super(EditText p0) {
      super();
      this.a = 0x1;
      this.b = p1;
      this.e = p2;
      this.c = p3;
      this.f = p4;
      this.g = p5;
      this.d = p6;
      return;
    }

    public void super(d2[] p0) {
      super();
      this.a = 0x0;
      this.e = p1;
      this.b = p2;
      this.d = p3;
      this.f = p4;
      this.g = p5;
      this.c = p6;
      return;
    }

    public final void onClick(View p0) {
      switch (p0.a) { /* :pswitch_data_19a */ }
      if (Ljava/lang/String.join("\\n", p0.b) != 0) goto :cond_22;
      :try_start_1f:
      goto :goto_23;
      :cond_22:
      :goto_23:
      if ("".length() > 0x2ee0) goto :cond_b2;
      new Ljava/util/LinkedHashSet();
      :goto_39:
      if (v9 >= 0x2ee0) goto :cond_64;
      if (""[v9].trim().toLowerCase(Locale.ROOT).isEmpty() == 0) goto :cond_50;
      goto :goto_59;
      :cond_50:
      if (""[v9].trim().toLowerCase(Locale.ROOT).length() > 0x64) goto :cond_5c;
      :goto_59:
      goto :goto_39;
      :cond_5c:
      new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      throw new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      :cond_64:
      if ("".length().size() > 0x64) goto :cond_aa;
      new Ljava/util/ArrayList("".length());
      (SharedPreferences) p0.e.edit().putString("feed_keywords_v1", Ljava/lang/String.join("\\n", p0.b)).apply();
      p0.c.run();
      new Ljava/lang/StringBuilder("已保存 ");
      Landroid/widget/Toast.makeText((Activity) p0.f, new Ljava/lang/StringBuilder("已保存 ").toString(), v8).show();
      (q2) p0.g.e.dismiss();
      goto :goto_c3;
      :catch_a8:
      goto :goto_ba;
      :cond_aa:
      new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      throw new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      :cond_b2:
      new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      throw new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      :try_end_ba:
      :goto_ba:
      p0.d.setError(e.getMessage());
      :goto_c3:
      return;
      :pswitch_c4:
      if ((String[]) p0.e[0x0] != 0) goto :cond_d1;
      goto :goto_198;
      :cond_d1:
      :goto_d8:
      if ("已保存 ".hasNext() == 0) goto :cond_ec;
      goto :goto_d8;
      :cond_ec:
      if ((Ljava/lang/String.join("\\n", p0.b) | (String) "已保存 ".next().equalsIgnoreCase((String[]) p0.e[0x0])) != 0) goto :cond_187;
      :try_start_ee:
      new Ljava/lang/StringBuilder();
      if (new Ljava/lang/StringBuilder().toString() != 0) goto :cond_10a;
      :cond_10a:
      if (new Ljava/lang/StringBuilder().toString().length() > 0x2ee0) goto :cond_174;
      new Ljava/util/LinkedHashSet();
      :goto_11f:
      if (new Ljava/lang/StringBuilder() >= 0x2ee0) goto :cond_14a;
      if (new Ljava/lang/StringBuilder().toString()[new Ljava/lang/StringBuilder()].trim().toLowerCase(Locale.ROOT).isEmpty() == 0) goto :cond_136;
      goto :goto_13f;
      :cond_136:
      if (new Ljava/lang/StringBuilder().toString()[new Ljava/lang/StringBuilder()].trim().toLowerCase(Locale.ROOT).length() > 0x64) goto :cond_142;
      :goto_13f:
      goto :goto_11f;
      :cond_142:
      new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      throw new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      :cond_14a:
      if (new Ljava/lang/StringBuilder().toString().length().size() > 0x64) goto :cond_16c;
      new Ljava/util/ArrayList(new Ljava/lang/StringBuilder().toString().length());
      :try_end_158:
      (String[]) p0.e[0x0].add(Ljava/lang/Math.min((int[]) p0.f[0x0], (String[]) p0.e[0x0].size()), (String[]) p0.e[0x0]);
      goto :goto_187;
      :cond_16c:
      :try_start_16c:
      new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      throw new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      :cond_174:
      new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      throw new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      :try_end_17c:
      :catch_17c:
      p0.d.setError(e.getMessage());
      goto :goto_198;
      :cond_187:
      :goto_187:
      p0.d[0x0] = 0x0;
      (TextView) p0.g.setVisibility(0x8);
      p0.c.run();
      :goto_198:
      return;
      :pswitch_data_19a:

      // .packed-switch 0x0
      // :pswitch_c4

      // try :try_start_1f..:try_end_ba catch (Ljava/lang/IllegalArgumentException) -> :catch_a8
      // try :try_start_ee..:try_end_158 catch (Ljava/lang/IllegalArgumentException) -> :catch_17c
      // try :try_start_16c..:try_end_17c catch (Ljava/lang/IllegalArgumentException) -> :catch_17c
    }

}