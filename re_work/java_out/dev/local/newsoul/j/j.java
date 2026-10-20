package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/j ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class j extends Ljava/lang/Object {

    public static final String[][] a;
    public static final Set b;
    public static final HashMap c;
    public static final Map d;
    public static final Map e;
    public static Method f;
    public static XposedModule g;
    public static Context h;
    public static final Handler i;

    static void static {}() {
      j.a = new String[][][] {new String[][] {"block_splash", "冷启动与热启动开屏", "包括首次启动和从后台返回时的广告。"}, new String[][] {"ad_feed", "广场信息流", ""}, new String[][] {"ad_detail", "帖子详情", ""}, new String[][] {"ad_video", "竖屏视频流", ""}, new String[][] {"ad_tag", "话题横幅", ""}, new String[][] {"ad_tag_web", "话题网页广告", ""}, new String[][] {"ad_search", "搜索顶部横幅", ""}, new String[][] {"ad_group", "群组横幅推广", ""}, new String[][] {"ad_planet", "星球游戏推广", ""}, new String[][] {"ad_me", "我的页面推广", ""}, new String[][] {"ad_conversation", "聊天会话列表", "保留聊天消息，只隐藏广告。"}, new String[][] {"ad_envelope", "通知信封广告", ""}, new String[][] {"ad_notice", "系统通知广告", ""}, new String[][] {"ad_chat_tools", "聊天面板推广", "已缓存的推广图标可能需要重启 Soul 才会消失。"}, new String[][] {"ad_float", "悬浮广告", ""}, new String[][] {"ad_interstitial", "插屏与底部弹窗", ""}, new String[][] {"ad_reward_chat", "聊天激励广告", ""}, new String[][] {"ad_reward_drama", "短剧激励广告", ""}, new String[][] {"ad_reward_web", "网页与任务激励", ""}};
      new Ljava/util/HashSet();
      j.b = Ljava/util/Collections.synchronizedSet(new Ljava/util/HashSet());
      new Ljava/util/HashMap();
      j.c = new Ljava/util/HashMap();
      new Ljava/util/WeakHashMap();
      j.d = Ljava/util/Collections.synchronizedMap(new Ljava/util/WeakHashMap());
      new Ljava/util/WeakHashMap();
      j.e = Ljava/util/Collections.synchronizedMap(new Ljava/util/WeakHashMap());
      new Landroid/os/Handler(Ljava/util/Collections.synchronizedMap(new Ljava/util/WeakHashMap()));
      j.i = new Landroid/os/Handler(Ljava/util/Collections.synchronizedMap(new Ljava/util/WeakHashMap()));
      :goto_112:
      if (v2 >= 0xb) goto :cond_122;
      goto :goto_112;
      :cond_122:
      return;
    }

    public static void a(i p0) {
      if (Ldev/local/newsoul/j.b(p0.getContext(), p1.a) == 0) goto :cond_1f;
      if (p1.c != 0) goto :cond_2b;
      p0.setVisibility(0x8);
      p1.b = p0.getVisibility();
      p1.c = 0x1;
      goto :goto_2b;
      :cond_1f:
      if (p1.c == 0) goto :cond_2b;
      p1.c = 0x0;
      0x1.setVisibility(p1.b);
      :cond_2b:
      :goto_2b:
      return;
    }

    public static boolean b(String p0) {
      return "block_splash".equals(p1);
    }

    public static void c(Throwable p0) {
      new Ljava/lang/StringBuilder("ad control unavailable: ");
      new Ljava/lang/StringBuilder("ad control unavailable: ");
      j.g.log(0x6, "SoulHook102", new Ljava/lang/StringBuilder("ad control unavailable: ").toString(), p1);
      return;
    }

    public static Field d(String p0) {
      :goto_0:
      if (p0 == 0) goto :cond_10;
      :try_start_2:
      p0.getDeclaredField(p1).setAccessible(0x1);
      :try_end_a:
      return v0;
      :catch_b:
      goto :goto_0;
      :cond_10:
      new Ljava/lang/NoSuchFieldException(p1);
      throw new Ljava/lang/NoSuchFieldException(p1);

      // try :try_start_2..:try_end_a catch (Ljava/lang/NoSuchFieldException) -> :catch_b
    }

    public static void e(String p0) {
      j.g.log(0x4, "SoulHook102", p0);
      return;
    }

    public static void f(String p0) {
      :try_start_0:
      new Ldev/local/newsoul/e(p0.loadClass(p1), p0.loadClass(p1).getMethod("getContext", 0x0), p0.loadClass(p1).getMethod("dismiss", 0x0), 0x0);
      :try_end_33:
      goto :goto_38;
      :catchall_34:
      Ldev/local/newsoul/j.c(p1, e);
      :goto_38:
      return;

      // .catchall {:try_start_0 .. :try_end_33} :catchall_34
    }

    public static void g(Ljava/lang/ClassLoader;Ljava/lang/String;Ljava/lang/String;Z p0) {
      :try_start_0:
      :goto_c:
      if (v2 >= v0) goto :cond_67;
      if (p3 == 0) goto :cond_36;
      if (p0[v2].getReturnType() != Void.TYPE) goto :cond_64;
      if (p0[v2].getParameterCount() < 0x2) goto :cond_64;
      if (p0[v2].getParameterTypes()[v1].getSimpleName().equals("BaseViewHolder") == 0) goto :cond_64;
      goto :goto_4e;
      :catchall_34:
      goto :goto_77;
      :cond_36:
      if (p0[v2].getName().equals("onBind") != 0) goto :cond_4e;
      if (p0[v2].getName().equals("onCreate") == 0) goto :cond_64;
      :cond_4e:
      :goto_4e:
      new Ldev/local/newsoul/d(p2, p3);
      :cond_64:
      goto :goto_c;
      :cond_67:
      if (v3 == 0) goto :cond_6f;
      goto :goto_7a;
      :cond_6f:
      new Ljava/lang/IllegalStateException("no bind methods");
      throw new Ljava/lang/IllegalStateException("no bind methods");
      :try_end_77:
      :goto_77:
      Ldev/local/newsoul/j.c(p1, j.b);
      :goto_7a:
      return;

      // .catchall {:try_start_0 .. :try_end_77} :catchall_34
    }

    public static void h(String p0) {
      :try_start_0:
      :goto_16:
      if (p4 >= p3) goto :cond_35;
      new Ldev/local/newsoul/a(0x1);
      goto :goto_16;
      :catchall_32:
      goto :goto_ab;
      :cond_35:
      new Ljava/lang/StringBuilder();
      XposedInterface$ExceptionMode.PROTECTIVE.<init>(v2, v3, v4, e.loadClass("cn.soulapp.android.ad.core.services.plaforms.listener.SoulAdRequestListener").getMethod("onAdFailed", new Class[][] {Integer.TYPE, String.class}), 0x0);
      :try_end_81:
      if (e == 0) goto :cond_97;
      :try_start_85:
      goto :goto_ae;
      :cond_97:
      :try_end_aa:
      goto :goto_ae;
      :goto_ab:
      Ldev/local/newsoul/j.c(p1, Ljava/util/Arrays.asList(new String[][] {"ad_reward_chat", "ad_reward_drama", "ad_reward_web"}));
      :goto_ae:
      return;

      // .catchall {:try_start_0 .. :try_end_81} :catchall_32
      // .catchall {:try_start_85 .. :try_end_aa} :catchall_32
    }

    public static void i(String p0) {
      if ((i) j.d.get(p0) == 0) goto :cond_12;
      if ((i) j.d.get(p0).a.equals(p1) != 0) goto :cond_1a;
      :cond_12:
      new Ldev/local/newsoul/i(p0, p1);
      :cond_1a:
      Ldev/local/newsoul/j.a(p0, (i) j.d.get(p0));
      return;
    }

}