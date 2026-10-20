package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/f1 ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class f1 extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;
    public final AccessibleObject b;
    public final AccessibleObject c;
    public final AccessibleObject d;
    public final Executable e;

    public void super(Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/Executable;I p0) {
      super();
      this.a = p5;
      this.b = p1;
      this.c = p2;
      this.d = p3;
      this.e = p4;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_12c */ }
      :try_start_13:
      if (v0.get(p1.getThisObject()) == 0) goto :cond_a8;
      new Ljava/util/ArrayList();
      :try_end_2c:
      if (v0.get(p1.getThisObject()) == 0) goto :cond_4f;
      :try_start_35:
      :cond_39:
      :goto_39:
      if (v0.get(p1.getThisObject()).hasNext() == 0) goto :cond_4f;
      if ((Field) p0.d.getInt(v0.get(p1.getThisObject()).next()) == -0x35efa) goto :cond_39;
      goto :goto_39;
      :catchall_4d:
      goto :goto_a3;
      :cond_4f:
      :goto_54:
      if (v0.get(p1.getThisObject()).next() >= p1.getThisObject().size()) goto :cond_6b;
      if ((Field) p0.d.getInt(p1.getThisObject().get(v0.get(p1.getThisObject()).next())) != 0x1a) goto :cond_68;
      goto :goto_6b;
      :cond_68:
      goto :goto_54;
      :cond_6b:
      :goto_6b:
      (Field) p0.d.setInt(v2.newInstance(new Object[][] {Ljava/lang/Integer.valueOf(0x33), "复制文案", Boolean.FALSE}), -0x35efa);
      p1.getThisObject().add(v0.get(p1.getThisObject()).next(), v2.newInstance(new Object[][] {Ljava/lang/Integer.valueOf(0x33), "复制文案", Boolean.FALSE}));
      v1.set(p1.getThisObject(), p1.getThisObject());
      new Ljava/lang/StringBuilder();
      :try_end_a2:
      goto :goto_a8;
      :goto_a3:
      :cond_a8:
      :goto_a8:
      return v0.get(p1.getThisObject()).next();
      :pswitch_ad:
      :try_start_c3:
      if (p1.getThisObject() == 0) goto :cond_126;
      if ((String) v0.get(p1.getThisObject()).next().invoke((Field) p0.d, (Field) p0.d) == 0) goto :cond_126;
      if ((String) v0.get(p1.getThisObject()).next().invoke((Field) p0.d, (Field) p0.d).isEmpty() != 0) goto :cond_126;
      if ((String) v0.get(p1.getThisObject()).next().invoke((Field) p0.d, (Field) p0.d).equals(v1.invoke(p1.getThisObject(), (Field) p0.d)) == 0) goto :cond_126;
      if ((Boolean) v2.invoke(p1.getThisObject(), (Field) p0.d).booleanValue() != 0) goto :cond_126;
      :try_end_fa:
      if ((Boolean) v2.invoke(p1.getThisObject(), (Field) p0.d).booleanValue() == 0) goto :cond_126;
      g1.b.set(p1.getThisObject());
      :try_start_105:
      :try_end_109:
      if (v1.invoke(p1.getThisObject(), (Field) p0.d) != 0) goto :cond_10f;
      (Boolean) v2.invoke(p1.getThisObject(), (Field) p0.d).booleanValue().remove();
      goto :goto_12a;
      :cond_10f:
      (Boolean) v2.invoke(p1.getThisObject(), (Field) p0.d).booleanValue().set(v1.invoke(p1.getThisObject(), (Field) p0.d));
      goto :goto_12a;
      :catchall_113:
      if (v1.invoke(p1.getThisObject(), (Field) p0.d) != 0) goto :cond_11a;
      (Boolean) v2.invoke(p1.getThisObject(), (Field) p0.d).booleanValue().remove();
      goto :goto_11d;
      :cond_11a:
      (Boolean) v2.invoke(p1.getThisObject(), (Field) p0.d).booleanValue().set(v1.invoke(p1.getThisObject(), (Field) p0.d));
      :goto_11d:
      throw e;
      :catchall_11e:
      :cond_126:
      :goto_12a:
      return (Boolean) v2.invoke(p1.getThisObject(), (Field) p0.d).booleanValue();
      :pswitch_data_12c:

      // .packed-switch 0x0
      // :pswitch_ad

      // .catchall {:try_start_13 .. :try_end_2c} :catchall_4d
      // .catchall {:try_start_35 .. :try_end_a2} :catchall_4d
      // .catchall {:try_start_c3 .. :try_end_fa} :catchall_11e
      // .catchall {:try_start_105 .. :try_end_109} :catchall_113
    }

}