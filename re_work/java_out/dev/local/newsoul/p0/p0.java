package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/p0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class p0 extends Ljava/lang/Object {

    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static final WeakHashMap f;
    public static XposedModule g;
    public static final HashSet h;
    public static Field i;
    public static Field j;
    public static Field k;
    public static Method l;
    public static Method m;

    static void static {}() {
      new Ljava/util/WeakHashMap();
      p0.f = new Ljava/util/WeakHashMap();
      new Ljava/util/HashSet();
      p0.h = new Ljava/util/HashSet();
      return;
    }

    public static void a(Method p0) {
      if (p0 != 0) goto :cond_3;
      return;
      :cond_3:
      if (p2 == 0) goto :cond_e;
      if (p1 == 0) goto :cond_e;
      goto :goto_f;
      :cond_e:
      :goto_f:
      Ldev/local/newsoul/r0.a(p0, 0x1);
      if (p2 == 0) goto :cond_37;
      if (p1 != 0) goto :cond_17;
      goto :goto_37;
      :cond_17:
      p2.<init>(p4.getInt(p1), p3, p0.getText(), p5, 0x0);
      Ldev/local/newsoul/p0.k("bound ".concat(p3));
      Ldev/local/newsoul/p0.i(p0, p2);
      :cond_37:
      :goto_37:
      return;
    }

    public static void b(Method p0) {
      if ((p1 instanceof TextView) == 0) goto :cond_b;
      goto :goto_c;
      :cond_b:
      :goto_c:
      if (p3 == 0) goto :cond_37;
      if ((TextView) p1.getText() != 0) goto :cond_14;
      goto :goto_37;
      :cond_14:
      if ("看过我".equals((TextView) p1.getText().toString().trim()) != 0) goto :cond_34;
      if ("访客".equals((TextView) p1.getText().toString().trim()) != 0) goto :cond_34;
      if ("访客数".equals((TextView) p1.getText().toString().trim()) == 0) goto :cond_37;
      :cond_34:
      :goto_35:
      goto :goto_39;
      :cond_37:
      :goto_37:
      goto :goto_35;
      :goto_39:
      (TextView) p0.a(p2, "访客数".equals((TextView) p1.getText().toString().trim()), "profile_disguise_visitor_", p0.k, p4);
      return;
    }

    public static void c(Throwable p0) {
      Ldev/local/newsoul/V.i("profile.disguise.".concat(p0), p1);
      if (p0.g == 0) goto :cond_26;
      new Ljava/lang/StringBuilder("follow disguise ");
      p0.g.log(0x6, "SoulHook102", new Ljava/lang/StringBuilder("follow disguise ").toString(), p1);
      :cond_26:
      new Ljava/lang/StringBuilder("follow disguise ");
      return;
    }

    public static Field d(String p0) {
      p0.getDeclaredField(p1).setAccessible(0x1);
      return v0;
    }

    public static void e(ClassLoader p0) {
      p0.g = p0;
      :try_start_6:
      p0.i = Ldev/local/newsoul/p0.d(v15.loadClass("cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount"), "followNum");
      v15.loadClass("cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount").getDeclaredField("beFollowNum").setAccessible(0x1);
      p0.j = v15.loadClass("cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount").getDeclaredField("beFollowNum");
      v15.loadClass("cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount").getDeclaredField("beViewNum").setAccessible(0x1);
      p0.k = v15.loadClass("cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount").getDeclaredField("beViewNum");
      p0.l = v15.loadClass("cn.soulapp.android.client.component.middle.platform.utils.m2").getDeclaredMethod("f", new Class[][] {Integer.TYPE});
      p0.m = v15.loadClass("cn.soulapp.android.client.component.middle.platform.utils.m2").getDeclaredMethod("e", new Class[][] {Integer.TYPE});
      p0.l.setAccessible(0x1);
      p0.m.setAccessible(0x1);
      :try_end_55:
      p0.a = p0.g(p1, "revision", "cn.soulapp.android.component.home.revision.RevisionProfileProvider", "S1", "cn.soulapp.android.component.home.revision.RevisionProfileProvider$ViewHolder", "o", "q", "g0", "h0", "b", "d", 0x1);
      p0.b = p0.g(p1, "meta", "cn.soulapp.android.component.home.meta.view.MetaFollowFansContainerView", "w", "cn.soulapp.android.component.home.databinding.CUsrMetaFollowFansContainerBinding", "e", "g", "q", "r", "k", "b", 0x0);
      :try_start_91:
      v15.loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper").getDeclaredField("j").setAccessible(v14);
      v15.loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper").getDeclaredField("l").setAccessible(v14);
      v15.loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper").getDeclaredField("n").setAccessible(v14);
      v15.loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper").getDeclaredField("o").setAccessible(v14);
      v15.loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper").getDeclaredField("q").setAccessible(v14);
      v15.loadClass("cn.soulapp.android.component.home.me.UserHomeHeaderHelper").getDeclaredField("r").setAccessible(v14);
      :try_end_e3:
      :try_start_e6:
      v13.<init>(v4, v5, v6, v7, v8, v9, v10);
      :goto_108:
      if (v3 >= v1) goto :cond_114;
      goto :goto_108;
      :catchall_112:
      goto :goto_120;
      :cond_114:
      p0.c = v2;
      Ldev/local/newsoul/V.i("profile.disguise.header", 0x0);
      :try_end_11c:
      goto :goto_125;
      :catchall_11d:
      :goto_120:
      Ldev/local/newsoul/p0.c("header", e);
      :goto_125:
      p0.d = p0.f(p1, "cn.soulapp.android.component.home.revision.RevisionProfileProvider", "Q1", "cn.soulapp.android.component.home.revision.RevisionProfileProvider$ViewHolder", "V", 0x1);
      p0.e = p0.f(p1, "cn.soulapp.android.component.home.meta.me2.userinfo.UserInfoProvider", "t0", "cn.soulapp.android.component.home.databinding.CUsrLayoutMeInfoNewBinding", "y", 0x0);
      new Ljava/lang/StringBuilder("days disguise registered: revision=");
      v11.log(0x4, "SoulHook102", new Ljava/lang/StringBuilder("days disguise registered: revision=").toString());
      new Ljava/lang/StringBuilder("follow disguise registered: revision=");
      v11.log(0x4, "SoulHook102", new Ljava/lang/StringBuilder("follow disguise registered: revision=").toString());
      return;
      :catchall_18b:
      Ldev/local/newsoul/p0.c("fields", e);
      return;

      // .catchall {:try_start_6 .. :try_end_55} :catchall_18b
      // .catchall {:try_start_91 .. :try_end_e3} :catchall_11d
      // .catchall {:try_start_e6 .. :try_end_11c} :catchall_112
    }

    public static boolean f(Lio/github/libxposed/api/XposedModule;Ljava/lang/ClassLoader;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z p0) {
      if (p6 == 0) goto :cond_e;
      :goto_c:
      goto :goto_11;
      :cond_e:
      goto :goto_c;
      :goto_11:
      :try_start_12:
      if (p6 == 0) goto :cond_38;
      goto :goto_39;
      :catchall_35:
      goto :goto_a3;
      :cond_38:
      :goto_39:
      if (p1.loadClass(p4).getDeclaredMethod("b", 0x0) == 0) goto :cond_3f;
      p1.loadClass(p4).getDeclaredMethod("b", 0x0).setAccessible(0x1);
      :cond_3f:
      if (p6 == 0) goto :cond_48;
      goto :goto_4b;
      :cond_48:
      :goto_4b:
      p1.loadClass(p4).<init>(p6, p1.loadClass(p4).getDeclaredMethod("b", 0x0), Ldev/local/newsoul/p0.d(p1.loadClass(p4).getDeclaredMethod("b", 0x0).getReturnType(), p5), Ldev/local/newsoul/p0.d(p1.loadClass(p2), "b"), p1.loadClass("com.soul.component.componentlib.service.user.bean.User").getField("registerDay"), v4);
      :goto_84:
      if ("new-soul/" >= p1.loadClass(p4).getDeclaredMethod("b", 0x0).getReturnType()) goto :cond_8e;
      goto :goto_84;
      :cond_8e:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/V.i(new Ljava/lang/StringBuilder().toString(), 0x0);
      :try_end_a2:
      return e;
      :goto_a3:
      Ldev/local/newsoul/p0.c(v4, e);
      return e;

      // .catchall {:try_start_12 .. :try_end_a2} :catchall_35
    }

    public static boolean g(Lio/github/libxposed/api/XposedModule;Ljava/lang/ClassLoader;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z p0) {
      :try_start_a:
      if (p12 == 0) goto :cond_27;
      goto :goto_28;
      :catchall_24:
      goto :goto_c0;
      :cond_27:
      :goto_28:
      if (v1.loadClass(p5).getDeclaredMethod("b", 0x0) == 0) goto :cond_2e;
      v1.loadClass(p5).getDeclaredMethod("b", 0x0).setAccessible(0x1);
      :cond_2e:
      if (p12 == 0) goto :cond_37;
      goto :goto_3a;
      :cond_37:
      :goto_3a:
      v1.loadClass(p5).getDeclaredMethod("b", 0x0).getReturnType().getDeclaredField(p7).setAccessible(0x1);
      v1.loadClass(p5).getDeclaredMethod("b", 0x0).getReturnType().getDeclaredField(p8).setAccessible(0x1);
      v1.loadClass(p5).getDeclaredMethod("b", 0x0).getReturnType().getDeclaredField(p9).setAccessible(0x1);
      v1.loadClass(v3).getDeclaredField(p11).setAccessible(0x1);
      e.hook(v1.loadClass(v3).getDeclaredMethod(p4, new Class[][] {v1.loadClass("com.soul.component.componentlib.service.user.bean.User"), v1.loadClass(p5)})).setId(v2.concat(v11)).<init>(p12, v1.loadClass(p5).getDeclaredMethod("b", 0x0), p2, Ldev/local/newsoul/p0.d(v1.loadClass(v3), p10), v1.loadClass(v3).getDeclaredField(p11), Ldev/local/newsoul/p0.d(v1.loadClass(p5).getDeclaredMethod("b", 0x0).getReturnType(), p6), v1.loadClass(p5).getDeclaredMethod("b", 0x0).getReturnType().getDeclaredField(p7), v1.loadClass(p5).getDeclaredMethod("b", 0x0).getReturnType().getDeclaredField(p8), v1.loadClass(p5).getDeclaredMethod("b", 0x0).getReturnType().getDeclaredField(p9));
      :goto_a0:
      if (v1.loadClass(p5) >= v2) goto :cond_aa;
      goto :goto_a0;
      :cond_aa:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/V.i(new Ljava/lang/StringBuilder().toString(), 0x0);
      :try_end_be:
      return 0x1;
      :goto_c0:
      Ldev/local/newsoul/p0.c(v11, e);
      return e;

      // .catchall {:try_start_a .. :try_end_be} :catchall_24
    }

    public static void h() {
      :goto_a:
      if (v0.hasNext() == 0) goto :cond_2d;
      :try_start_16:
      Ldev/local/newsoul/p0.i((TextView) v0.hasNext().getKey(), (o0) v0.hasNext().getValue());
      :try_end_25:
      goto :goto_a;
      :catch_26:
      Ldev/local/newsoul/p0.j("refresh", e);
      goto :goto_a;
      :cond_2d:
      return;

      // try :try_start_16..:try_end_25 catch (Ljava/lang/Exception) -> :catch_26
    }

    public static void i(o0 p0) {
      new Ljava/lang/StringBuilder();
      if (Ldev/local/newsoul/w2.o(p0.getContext()).getBoolean(new Ljava/lang/StringBuilder().toString(), 0x0) != 0) goto :cond_28;
      p0.setText(p1.c);
      return;
      :cond_28:
      new Ljava/lang/StringBuilder();
      new Ljava/lang/StringBuilder();
      if ("delta".equals(Ldev/local/newsoul/w2.o(p0.getContext()).getString(new Ljava/lang/StringBuilder().toString(), "absolute")) == 0) goto :cond_62;
      goto :goto_63;
      :cond_62:
      :goto_63:
      if (p1.e != 0) goto :cond_87;
      goto :goto_bc;
      :cond_87:
      if (0x0 == 0) goto :cond_99;
      if (0x0.isEmpty() == 0) goto :cond_94;
      goto :goto_99;
      :cond_94:
      goto :goto_9a;
      :cond_99:
      :goto_99:
      :goto_9a:
      if (p1.c.toString().indexOf(0x0) >= 0) goto :cond_9d;
      goto :goto_bc;
      :cond_9d:
      new Ljava/lang/StringBuilder();
      :goto_bc:
      p0.setText(p1.c.toString());
      return;
    }

    public static void j(Throwable p0) {
      if (p0.g == 0) goto :cond_35;
      new Ljava/lang/StringBuilder("error:");
      if (p0.h.add(new Ljava/lang/StringBuilder("error:").toString()) == 0) goto :cond_35;
      new Ljava/lang/StringBuilder("follow disguise ");
      p0.g.log(0x6, "SoulHook102", new Ljava/lang/StringBuilder("follow disguise ").toString(), p1);
      :cond_35:
      return;
    }

    public static void k(String p0) {
      if (p0.g == 0) goto :cond_22;
      if (p0.h.add(p0) == 0) goto :cond_22;
      new Ljava/lang/StringBuilder("follow disguise ");
      p0.g.log(0x4, "SoulHook102", new Ljava/lang/StringBuilder("follow disguise ").toString());
      :cond_22:
      return;
    }

}