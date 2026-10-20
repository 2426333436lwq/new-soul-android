package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/z0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class z0 extends Ljava/lang/Object {

    public static final WeakHashMap a;

    static void static {}() {
      new Ljava/util/WeakHashMap();
      z0.a = new Ljava/util/WeakHashMap();
      return;
    }

    public static void a(Landroid/app/Activity;Z p0) {
      if (p0.isFinishing() != 0) goto :cond_105;
      if (p0.isDestroyed() == 0) goto :cond_10;
      goto :goto_105;
      :cond_10:
      if ((WeakReference) z0.a.get(p0) != 0) goto :cond_1c;
      goto :goto_22;
      :cond_1c:
      :goto_22:
      if (0x0 == 0) goto :cond_2b;
      if (0x0.isShowing() == 0) goto :cond_2b;
      return;
      :cond_2b:
      new Ldev/local/newsoul/q2(p0, p1);
      new Landroid/text/SpannableString("只改变本机自己个人主页的关注、被关注、访客数量及加入天数，不改变真实关注关系、访问记录或注册时间，也不会改变他人看到的数据。\n\n长按自己主页的对应区域，也可以快速打开此页面。");
      new Landroid/text/style/ForegroundColorSpan(new Ldev/local/newsoul/q2(p0, p1).k);
      new Landroid/text/SpannableString("只改变本机自己个人主页的关注、被关注、访客数量及加入天数，不改变真实关注关系、访问记录或注册时间，也不会改变他人看到的数据。\n\n长按自己主页的对应区域，也可以快速打开此页面。").setSpan(new Landroid/text/style/ForegroundColorSpan(new Ldev/local/newsoul/q2(p0, p1).k), 0x40, new Landroid/text/SpannableString("只改变本机自己个人主页的关注、被关注、访客数量及加入天数，不改变真实关注关系、访问记录或注册时间，也不会改变他人看到的数据。\n\n长按自己主页的对应区域，也可以快速打开此页面。").length(), 0x21);
      new Landroid/text/style/StyleSpan(0x1);
      new Landroid/text/SpannableString("只改变本机自己个人主页的关注、被关注、访客数量及加入天数，不改变真实关注关系、访问记录或注册时间，也不会改变他人看到的数据。\n\n长按自己主页的对应区域，也可以快速打开此页面。").setSpan(new Landroid/text/style/StyleSpan(0x1), 0x40, new Landroid/text/SpannableString("只改变本机自己个人主页的关注、被关注、访客数量及加入天数，不改变真实关注关系、访问记录或注册时间，也不会改变他人看到的数据。\n\n长按自己主页的对应区域，也可以快速打开此页面。").length(), 0x21);
      new Ldev/local/newsoul/q2(p0, p1).t = new Landroid/text/SpannableString("只改变本机自己个人主页的关注、被关注、访客数量及加入天数，不改变真实关注关系、访问记录或注册时间，也不会改变他人看到的数据。\n\n长按自己主页的对应区域，也可以快速打开此页面。");
      if (p0.a != 0) goto :cond_6d;
      if (p0.b != 0) goto :cond_6d;
      if (p0.c == 0) goto :cond_6e;
      :cond_6d:
      :cond_6e:
      if (0x0 != 0) goto :cond_81;
      new Ldev/local/newsoul/q2(p0, p1).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0, p1).g, "当前版本的主页数量显示入口未就绪", 0xe, new Ldev/local/newsoul/q2(p0, p1).j));
      :cond_81:
      new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "关注", "profile_disguise_follow_");
      new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "被关注", "profile_disguise_followed_");
      new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "访客", "profile_disguise_visitor_");
      new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "天数", "profile_disguise_days_");
      if (p0.d != 0) goto :cond_b7;
      if (p0.e == 0) goto :cond_ae;
      goto :goto_b7;
      :cond_ae:
      new Ldev/local/newsoul/q2(p0, p1).h.addView(Ldev/local/newsoul/w2.n(new Ldev/local/newsoul/q2(p0, p1).g, "当前版本的天数显示入口未就绪", 0xe, new Ldev/local/newsoul/q2(p0, p1).j));
      :cond_b7:
      :goto_b7:
      0xe.<init>(new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "关注", "profile_disguise_follow_"), new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "被关注", "profile_disguise_followed_"), new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "访客", "profile_disguise_visitor_"), new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "天数", "profile_disguise_days_"), 0x0);
      new Ldev/local/newsoul/q2(p0, p1).c(0xe);
      new Ldev/local/newsoul/q2(p0, p1).g("信息伪装", "保存");
      new Ljava/lang/ref/WeakReference(new Ldev/local/newsoul/q2(p0, p1).e);
      new Ldev/local/newsoul/q2(p0, p1).e.getWindow().setSoftInputMode(0x13);
      new Ldev/local/newsoul/q2(p0, p1).h.setFocusableInTouchMode(0x1);
      0xe.<init>(new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "关注", "profile_disguise_follow_"), new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "被关注", "profile_disguise_followed_"), new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "访客", "profile_disguise_visitor_"), new Ldev/local/newsoul/y0(new Ldev/local/newsoul/q2(p0, p1), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), "天数", "profile_disguise_days_"), p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0), new Ldev/local/newsoul/q2(p0, p1), p0);
      new Ldev/local/newsoul/q2(p0, p1).e.getButton(-0x1).setOnClickListener(0xe);
      :cond_105:
      :goto_105:
      return;
    }

}