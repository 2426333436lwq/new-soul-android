package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/B2 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class B2 extends Ljava/lang/Object {

    public static boolean a;
    public static Field b;
    public static Field c;
    public static Field d;
    public static Field e;
    public static Field f;
    public static String g;
    public static k0 h;
    public static boolean i;
    public static final Handler j;
    public static final WeakHashMap k;
    public static Class l;
    public static Field m;
    public static Field n;
    public static boolean o;
    public static int p;

    static void static {}() {
      new Ldev/local/newsoul/k0("");
      B2.h = new Ldev/local/newsoul/k0("");
      new Landroid/os/Handler(__result__);
      B2.j = new Landroid/os/Handler(__result__);
      new Ljava/util/WeakHashMap();
      B2.k = new Ljava/util/WeakHashMap();
      return;
    }

    public static boolean a(Object p0) {
      if (B2.i == 0) goto :cond_117;
      if (B2.l.isInstance(p1) != 0) goto :cond_f;
      goto :goto_117;
      :cond_f:
      if (B2.a == 0) goto :cond_c9;
      if (Ldev/local/newsoul/w2.o(p0).getBoolean("feed_keywords_enabled_v1", 0x0) == 0) goto :cond_c9;
      synchronized (B2.class) {
      :try_start_23:
      :try_end_31:
      if (v4 != 0) goto :cond_49;
      :try_start_33:
      new Ldev/local/newsoul/k0(v3);
      B2.h = new Ldev/local/newsoul/k0(v3);
      :try_end_3a:
      goto :goto_47;
      :catchall_3b:
      goto :goto_c7;
      :catch_3e:
      :try_start_3e:
      new Ldev/local/newsoul/k0("");
      B2.h = new Ldev/local/newsoul/k0("");
      :goto_47:
      B2.g = v3;
      :cond_49:
      :try_end_53:
      if (v3 == 0) goto :cond_58;
      }
      goto :goto_c9;
      :cond_58:
      :try_start_58:
      if (B2.h.a((String) B2.b.get(p1)) != 0) goto :cond_c3;
      if (B2.h.a((String) B2.c.get(p1)) != 0) goto :cond_c3;
      if (B2.h.a((String) B2.d.get(p1)) == 0) goto :cond_89;
      goto :goto_c3;
      :cond_89:
      if ((B2.e.get(p1) instanceof Iterable) == 0) goto :cond_c5;
      :cond_99:
      if (B2.e.get(p1).hasNext() == 0) goto :cond_c5;
      if (B2.e.get(p1).next() == 0) goto :cond_99;
      if (B2.f.getDeclaringClass().isInstance(B2.e.get(p1).next()) == 0) goto :cond_99;
      :try_end_bf:
      if (B2.e.get(p1).next() == 0) goto :cond_99;
      }
      goto :goto_c4;
      :cond_c3:
      :goto_c3:
      }
      :goto_c4:
      return Ldev/local/newsoul/w2.o(p0).getBoolean("feed_keywords_enabled_v1", 0x0);
      :catch_c5:
      :cond_c5:
      }
      goto :goto_c9;
      :goto_c7:
      }
      throw e;
      :cond_c9:
      :goto_c9:
      if (e.getBoolean("feed_filter_enabled", 0x0) != 0) goto :cond_d2;
      return e.getBoolean("feed_filter_enabled", 0x0);
      :cond_d2:
      :try_start_db:
      if (B2.m.get(p1) == 0) goto :cond_f2;
      if (B2.n.getInt(B2.m.get(p1)) != 0x1) goto :cond_ee;
      goto :goto_f2;
      :cond_ee:
      if (B2.n.getInt(B2.m.get(p1)) != 0) goto :cond_f2;
      :try_end_f2:
      :catch_f2:
      :cond_f2:
      :goto_f2:
      if ("ANY".equals(e.getBoolean("feed_filter_enabled", 0x0)) == 0) goto :cond_101;
      goto :goto_117;
      :cond_101:
      if ("MALE".equals("FEMALE") != 0) goto :cond_113;
      if ("FEMALE".equals("FEMALE") != 0) goto :cond_113;
      goto :goto_117;
      :cond_113:
      :cond_117:
      :goto_117:
      return e.getBoolean("feed_filter_enabled", 0x0);

      // .catchall {:try_start_23 .. :try_end_31} :catchall_3b
      // try :try_start_33..:try_end_3a catch (Ljava/lang/IllegalArgumentException) -> :catch_3e
      // .catchall {:try_start_33 .. :try_end_3a} :catchall_3b
      // .catchall {:try_start_3e .. :try_end_53} :catchall_3b
      // try :try_start_58..:try_end_bf catch (Ljava/lang/ReflectiveOperationException) -> :catch_c5
      // .catchall {:try_start_58 .. :try_end_bf} :catchall_3b
      // try :try_start_db..:try_end_f2 catch (Ljava/lang/ReflectiveOperationException) -> :catch_f2
    }

    public static void b(A2 p0) {
      if (B2.i == 0) goto :cond_9c;
      :goto_5:
      if (B2.i == 0) goto :cond_9c;
      if (B2.i.getId() == -0x1) goto :cond_89;
      :try_start_e:
      :try_end_20:
      if (B2.i.getId() == 0) goto :cond_89;
      if (Ldev/local/newsoul/B2.a(p0.getContext(), p1.a) != 0) goto :cond_83;
      if (p1.b != 0) goto :cond_83;
      if (p0.getLayoutParams() == 0) goto :cond_83;
      p1.c = p0.getLayoutParams().height;
      p1.d = p0.getVisibility();
      if ((p0.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) == 0) goto :cond_56;
      p1.e = (ViewGroup$MarginLayoutParams) p0.getLayoutParams().topMargin;
      p1.f = (ViewGroup$MarginLayoutParams) p0.getLayoutParams().bottomMargin;
      (ViewGroup$MarginLayoutParams) p0.getLayoutParams().bottomMargin = 0x0;
      (ViewGroup$MarginLayoutParams) p0.getLayoutParams().topMargin = 0x0;
      :cond_56:
      p1.b = 0x1;
      B2.p = (B2.p + 0x1);
      if ((B2.p + 0x1) <= 0x5) goto :cond_65;
      if (((B2.p + 0x1) % 0x19) != 0) goto :cond_78;
      :cond_65:
      new Ljava/lang/StringBuilder("feed filter rows hidden=");
      :cond_78:
      p0.getLayoutParams().height = 0x0;
      p0.setVisibility(0x8);
      p0.setLayoutParams(p0.getLayoutParams());
      goto :goto_88;
      :cond_83:
      if (((B2.p + 0x1) % 0x19) == 0) goto :cond_88;
      Ldev/local/newsoul/B2.e(p0, 0x8);
      :cond_88:
      :goto_88:
      return;
      :catch_89:
      :cond_89:
      if ((((B2.p + 0x1) % 0x19).getParent() instanceof View) == 0) goto :cond_99;
      goto :goto_5;
      :cond_99:
      goto :goto_5;
      :cond_9c:
      Ldev/local/newsoul/B2.e(p0, 0x8);
      return;

      // try :try_start_e..:try_end_20 catch (Ljava/lang/RuntimeException) -> :catch_89
    }

    public static void c(ClassLoader p0) {
      :try_start_2:
      B2.l = p1.loadClass("cn.soulapp.android.square.post.bean.Post");
      B2.m = p1.loadClass("cn.soulapp.android.square.post.bean.Post").getField("postAuthorModel");
      B2.n = p1.loadClass("cn.soulapp.android.square.post.bean.Post").getField("postAuthorModel").getType().getField("gender");
      :try_end_1e:
      :try_start_1f:
      B2.b = B2.l.getField("content");
      B2.c = B2.l.getField("title");
      B2.d = B2.l.getField("contentTitle");
      B2.e = B2.l.getField("tags");
      B2.f = p1.loadClass("cn.soulapp.android.square.bean.Tag").getField("name");
      B2.a = v1;
      :try_end_57:
      goto :goto_61;
      :catchall_58:
      goto :goto_dc;
      :catch_5b:
      :try_start_5c:
      :goto_61:
      new Ldev/local/newsoul/z2(p1.loadClass("cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder"), 0x0);
      new Ldev/local/newsoul/z2(p1.loadClass("cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder"), 0x1);
      new Ldev/local/newsoul/z2(p1.loadClass("cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder"), 0x2);
      B2.i = v1;
      :try_end_db:
      goto :goto_e1;
      :goto_dc:
      :goto_e1:
      return;

      // .catchall {:try_start_2 .. :try_end_1e} :catchall_58
      // try :try_start_1f..:try_end_57 catch (Ljava/lang/ReflectiveOperationException) -> :catch_5b
      // .catchall {:try_start_1f .. :try_end_57} :catchall_58
      // .catchall {:try_start_5c .. :try_end_db} :catchall_58
    }

    public static void d() {
      if (B2.o == 0) goto :cond_5;
      return;
      :cond_5:
      B2.o = 0x1;
      new Ldev/local/newsoul/h(0x4);
      return;
    }

    public static void e(A2 p0) {
      if (p1 == 0) goto :cond_2b;
      if (p1.b != 0) goto :cond_7;
      goto :goto_2b;
      :cond_7:
      if (p0.getLayoutParams() == 0) goto :cond_23;
      p0.getLayoutParams().height = p1.c;
      if ((p0.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) == 0) goto :cond_20;
      (ViewGroup$MarginLayoutParams) p0.getLayoutParams().topMargin = p1.e;
      (ViewGroup$MarginLayoutParams) p0.getLayoutParams().bottomMargin = p1.f;
      :cond_20:
      p0.setLayoutParams(p0.getLayoutParams());
      :cond_23:
      p0.setVisibility(p1.d);
      p1.b = 0x0;
      :cond_2b:
      :goto_2b:
      return;
    }

}