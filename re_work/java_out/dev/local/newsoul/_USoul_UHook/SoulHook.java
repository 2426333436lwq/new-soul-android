package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/SoulHook ====
// super: Lio/github/libxposed/api/XposedModule
// source: "SourceFile"

public final  class SoulHook extends Lio/github/libxposed/api/XposedModule {

    private static final String TAG = "SoulHook102";
    private static final String TARGET = "cn.soulapp.android";
    static boolean settingsHookReady;
    private boolean installed;
    private boolean targetProcess;
    private final AtomicBoolean toastShown;

    public void super() {
      super();
      new Ljava/util/concurrent/atomic/AtomicBoolean();
      this.toastShown = new Ljava/util/concurrent/atomic/AtomicBoolean();
      return;
    }

    public static void a(Activity p0) {
      Ldev/local/newsoul/SoulHook.lambda$installSettingsHook$1(p0);
      return;
    }

    public static Object b(XposedInterface$Chain p0) {
      return v0;
    }

    public static Object c(XposedInterface$Chain p0) {
      return v0;
    }

    private void info(String p0) {
      p0.log(0x4, "SoulHook102", p1);
      return;
    }

    private void installSettingsHook(ClassLoader p0) {
      :try_start_0:
      if (Ljava/lang/reflect/Modifier.isPrivate(p1.loadClass("cn.soulapp.android.h5.activity.H5Activity").getDeclaredMethod("m1", new Class[][] {Boolean.TYPE}).getModifiers()) == 0) goto :cond_40;
      new Ldev/local/newsoul/x2(p0, 0x1);
      p0.info("private hook installed: H5Activity.m1(boolean)");
      SoulHook.settingsHookReady = 0x1;
      goto :goto_53;
      :catchall_3e:
      goto :goto_48;
      :cond_40:
      new Ljava/lang/IllegalStateException("Unexpected H5Activity.m1 signature");
      throw new Ljava/lang/IllegalStateException("Unexpected H5Activity.m1 signature");
      :try_end_48:
      :goto_48:
      p0.log(0x6, "SoulHook102", "Soul settings hook unavailable; check app version", e);
      :goto_53:
      return;

      // .catchall {:try_start_0 .. :try_end_48} :catchall_3e
    }

    private static void lambda$installSettingsHook$1(Activity p0) {
      if (p0.isFinishing() != 0) goto :cond_a9;
      if (p0.isDestroyed() != 0) goto :cond_a9;
      :try_start_f:
      if (w2.a.containsKey(p0) == 0) goto :cond_19;
      goto :goto_a9;
      :cond_19:
      if ((Ldev/local/newsoul/w2.k(p0, "topic_title") instanceof TextView) == 0) goto :cond_9e;
      if ((Ldev/local/newsoul/w2.k(p0, "rlWebRoot") instanceof RelativeLayout) == 0) goto :cond_9e;
      if (Ldev/local/newsoul/w2.k(p0, "contentLayout") == 0) goto :cond_9e;
      if (Ldev/local/newsoul/w2.k(p0, "titleLay") == 0) goto :cond_9e;
      if (Ldev/local/newsoul/w2.k(p0, "contentLayout").getParent() != Ldev/local/newsoul/w2.k(p0, "rlWebRoot")) goto :cond_9e;
      if ((Ldev/local/newsoul/w2.k(p0, "contentLayout").getLayoutParams() instanceof RelativeLayout$LayoutParams) != 0) goto :cond_4c;
      goto :goto_9e;
      :cond_4c:
      new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) Ldev/local/newsoul/w2.k(p0, "contentLayout").getLayoutParams());
      new boolean[][0x1][0x0] = 0x0;
      new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title"));
      new Ldev/local/newsoul/F1(Ldev/local/newsoul/w2.k(p0, "contentLayout"), new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title")), 0x0);
      Ldev/local/newsoul/w2.k(p0, "contentLayout").addOnAttachStateChangeListener(new Ldev/local/newsoul/F1(Ldev/local/newsoul/w2.k(p0, "contentLayout"), new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title")), 0x0));
      if (Ldev/local/newsoul/w2.k(p0, "contentLayout").isAttachedToWindow() == 0) goto :cond_7b;
      :cond_7b:
      new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title")).<init>(p0, (RelativeLayout) Ldev/local/newsoul/w2.k(p0, "rlWebRoot"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title"), Ldev/local/newsoul/w2.k(p0, "contentLayout"), new Landroid/widget/RelativeLayout$LayoutParams((RelativeLayout$LayoutParams) Ldev/local/newsoul/w2.k(p0, "contentLayout").getLayoutParams()), new boolean[][0x1], Ldev/local/newsoul/w2.k(p0, "titleLay"));
      new Ldev/local/newsoul/a2(new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title")), 0x0);
      (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title").addTextChangedListener(new Ldev/local/newsoul/a2(new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title")), 0x0));
      new Ldev/local/newsoul/M1(0x0, new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title")));
      (RelativeLayout) Ldev/local/newsoul/w2.k(p0, "rlWebRoot").getViewTreeObserver().addOnPreDrawListener(new Ldev/local/newsoul/M1(0x0, new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title"))));
      new Ldev/local/newsoul/E1(p0, Ldev/local/newsoul/w2.k(p0, "contentLayout"), (TextView) Ldev/local/newsoul/w2.k(p0, "topic_title")).run();
      goto :goto_a9;
      :catchall_9c:
      goto :goto_a4;
      :cond_9e:
      :goto_9e:
      :try_end_a3:
      goto :goto_a9;
      :goto_a4:
      :cond_a9:
      :goto_a9:
      return;

      // .catchall {:try_start_f .. :try_end_a3} :catchall_9c
    }

    private Object lambda$installSettingsHook$2(XposedInterface$Chain p0) {
      new Ldev/local/newsoul/m((Activity) p1.getThisObject(), 0x5);
      p0.info("private hook hit: H5Activity.m1(boolean)");
      return p1.proceed();
    }

    private Object lambda$onPackageReady$0(XposedInterface$Chain p0) {
      if ("cn.soulapp.android".equals((Activity) p1.getThisObject().getPackageName()) == 0) goto :cond_65;
      Ldev/local/newsoul/V.h((Activity) p1.getThisObject());
      Ldev/local/newsoul/V.d("page.resume", (Activity) p1.getThisObject().getClass().getName());
      Ldev/local/newsoul/Q0.f((Activity) p1.getThisObject());
      if ((Activity) p1.getThisObject().getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0).getBoolean("page_log", 0x1) == 0) goto :cond_4c;
      p0.info("onResume: ".concat((Activity) p1.getThisObject().getClass().getName()));
      :cond_4c:
      if ((Activity) p1.getThisObject().getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0).getBoolean("startup_toast", 0x1) == 0) goto :cond_65;
      if (p0.toastShown.compareAndSet(0x0, 0x1) == 0) goto :cond_65;
      Landroid/widget/Toast.makeText((Activity) p1.getThisObject(), "New Soul 已生效", 0x1).show();
      :cond_65:
      return p1.proceed();
    }

    public void onModuleLoaded(XposedModuleInterface$ModuleLoadedParam p0) {
      if (p1.isSystemServer() != 0) goto :cond_14;
      if ("cn.soulapp.android".equals(p1.getProcessName()) == 0) goto :cond_14;
      goto :goto_15;
      :cond_14:
      :goto_15:
      p0.targetProcess = 0x1;
      if (0x1 == 0) goto :cond_46;
      new Ljava/lang/StringBuilder("module loaded; framework=");
      p0.info(new Ljava/lang/StringBuilder("module loaded; framework=").toString());
      :cond_46:
      return;
    }

    public void onPackageReady(XposedModuleInterface$PackageReadyParam p0) {
      if (p0.targetProcess == 0) goto :cond_6fa;
      if (p0.installed != 0) goto :cond_6fa;
      if (p1.isFirstPackage() == 0) goto :cond_6fa;
      if ("cn.soulapp.android".equals(p1.getPackageName()) != 0) goto :cond_23;
      goto :goto_6fa;
      :cond_23:
      synchronized ("cn.soulapp.android".equals(p1.getPackageName())) {
      :try_start_28:
      if (Ljava/lang/System.getProperty("newsoul.active.module") == 0) goto :cond_3c;
      p0.info("Skipping duplicate module; active=".concat(Ljava/lang/System.getProperty("newsoul.active.module")));
      }
      return;
      :catchall_39:
      goto :goto_6f8;
      :cond_3c:
      }
      :try_end_44:
      p0.info("active variant=dev.local.newsoul.public");
      new Ljava/lang/StringBuilder();
      V.l = new Ljava/lang/StringBuilder().toString();
      :try_start_76:
      new Ldev/local/newsoul/a(0xd);
      :try_end_98:
      goto :goto_9f;
      :catchall_99:
      Ldev/local/newsoul/V.i("debug.lifecycle", e);
      :goto_9f:
      g0.a = p0;
      if (Q0.W == 0) goto :cond_b2;
      goto :goto_1e7;
      :cond_b2:
      :try_start_b2:
      new Ldev/local/newsoul/y2(p0, 0x0);
      new Ldev/local/newsoul/y2(p0, 0x1);
      :try_end_103:
      goto :goto_10a;
      :catchall_104:
      :goto_10a:
      :try_start_10c:
      if (p1.getClassLoader().loadClass("tc.e$a").getDeclaredMethod("j", new Class[][] {Q0.W}).getReturnType() != Boolean.TYPE) goto :cond_17a;
      if (p1.getClassLoader().loadClass("tc.e$a").getDeclaredMethod("onApplicationForeground", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType() != Void.TYPE) goto :cond_17a;
      if (Context.class.isAssignableFrom(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType()) == 0) goto :cond_17a;
      new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1);
      Q0.X = 0x1;
      p0.log(0x4, "SoulHook102", "new soul: hot splash hook installed (Soul 6.37.0)");
      goto :goto_187;
      :catchall_178:
      goto :goto_182;
      :cond_17a:
      new Ljava/lang/IllegalStateException("Unexpected Soul hot startup signature");
      throw new Ljava/lang/IllegalStateException("Unexpected Soul hot startup signature");
      :try_end_182:
      :goto_182:
      p0.log(0x6, "SoulHook102", "new soul: hot splash hook unavailable", e);
      :goto_187:
      :try_start_187:
      if (p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType() != Void.TYPE) goto :cond_1d5;
      if (p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("s", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType() != Void.TYPE) goto :cond_1d5;
      p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredField("a").setAccessible(0x1);
      p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("s", "cn.soulapp.android".equals(p1.getPackageName())).setAccessible(0x1);
      new Ldev/local/newsoul/e(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredField("a"), p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("s", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x3);
      Q0.W = 0x1;
      p0.log(0x4, "SoulHook102", "new soul: splash hook installed (Soul 6.37.0)");
      goto :goto_1e7;
      :catchall_1d3:
      goto :goto_1dd;
      :cond_1d5:
      new Ljava/lang/IllegalStateException("Unexpected Soul startup method signature");
      throw new Ljava/lang/IllegalStateException("Unexpected Soul startup method signature");
      :try_end_1dd:
      :goto_1dd:
      p0.log(0x6, "SoulHook102", "new soul: splash hook unavailable", e);
      :goto_1e7:
      j.g = p0;
      :try_start_1f5:
      new Ljava/util/HashSet(Ljava/util/Arrays.asList(new String[][] {"addData", "setData", "setList", "replaceData", "setNewInstance", "setNewData", "setDiffNewData"}));
      :goto_22f:
      if (0x3 >= p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName()))) goto :cond_2f5;
      if (p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType().contains(new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getName()) == 0) goto :cond_245;
      if (new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getReturnType() == Void.TYPE) goto :cond_250;
      :cond_245:
      :goto_245:
      goto :goto_2e5;
      :cond_250:
      if (new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getParameterTypes().length != 0) goto :cond_258;
      goto :goto_245;
      :cond_258:
      :try_end_262:
      if (p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType() == 0) goto :cond_26d;
      :try_start_264:
      :try_end_265:
      if (p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType() == 0x1) goto :cond_26e;
      goto :goto_245;
      :catchall_269:
      goto :goto_317;
      :cond_26d:
      :cond_26e:
      :try_start_26e:
      if (new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getParameterTypes()[(new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getParameterTypes().length - 0x1)] != Object.class) goto :cond_283;
      goto :goto_285;
      :cond_283:
      :goto_285:
      if (Collection.class.isAssignableFrom(new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getParameterTypes()[(new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getParameterTypes().length - 0x1)]) != 0) goto :cond_293;
      if (0x1 != 0) goto :cond_293;
      goto :goto_2e5;
      :cond_293:
      new Ljava/lang/StringBuilder();
      "new-soul/feed-data/".<init>(e, p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())).getReturnType(), Collection.class.isAssignableFrom(new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getParameterTypes()[(new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3].getParameterTypes().length - 0x1)]), v20, v21, new Ldev/local/newsoul/D(p1.getClassLoader().loadClass("ea.a").getDeclaredMethod("a", "cn.soulapp.android".equals(p1.getPackageName())), p0, 0x1)[0x3], 0x1);
      goto :goto_2e6;
      :catchall_2e3:
      goto :goto_317;
      :goto_2e5:
      :goto_2e6:
      goto :goto_22f;
      :cond_2f5:
      if ((0x6 + 0x1) == 0) goto :cond_30f;
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/j.e(new Ljava/lang/StringBuilder().toString());
      goto :goto_31c;
      :cond_30f:
      new Ljava/lang/IllegalStateException("no adapter mutation methods");
      throw new Ljava/lang/IllegalStateException("no adapter mutation methods");
      :try_end_317:
      :goto_317:
      Ldev/local/newsoul/j.c("feed data filter", new Ljava/lang/StringBuilder().toString());
      :goto_31c:
      :try_start_31c:
      j.f = p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE).loadClass("fa.a").getDeclaredMethod("SoulHook102", 0x0);
      Ldev/local/newsoul/j.h(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "jb.e", "new soul: splash hook installed (Soul 6.37.0)", "SoulHook102", "e");
      Ldev/local/newsoul/j.h(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "mb.d", "new soul: splash hook installed (Soul 6.37.0)", "SoulHook102", "f");
      Ldev/local/newsoul/j.h(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "lb.h", "SoulHook102", "c", "new soul: splash hook installed (Soul 6.37.0)");
      :try_end_33e:
      goto :goto_345;
      :catchall_33f:
      Ldev/local/newsoul/j.c("request hooks", e);
      :goto_345:
      :goto_3c0:
      if (0x1 >= 0x10) goto :cond_40f;
      :try_start_3ce:
      if (View.class.isAssignableFrom(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE).loadClass("SoulHook102")) == 0) goto :cond_401;
      :goto_3de:
      if ((0x6 + 0x1) >= p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE).loadClass("SoulHook102")) goto :cond_3fc;
      new Ldev/local/newsoul/f(0x0, 0x10);
      goto :goto_3de;
      :catchall_3fa:
      goto :goto_409;
      :cond_3fc:
      :goto_3ff:
      goto :goto_40d;
      :cond_401:
      new Ljava/lang/IllegalStateException("not a View");
      throw new Ljava/lang/IllegalStateException("not a View");
      :try_end_409:
      :goto_409:
      Ldev/local/newsoul/j.c("SoulHook102", e);
      goto :goto_3ff;
      :goto_40d:
      goto :goto_3c0;
      :cond_40f:
      Ldev/local/newsoul/j.g(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "cn.soulapp.android.component.square.main.ad.AdPostViewHolder", p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("s", "cn.soulapp.android".equals(p1.getPackageName())), 0x0);
      Ldev/local/newsoul/j.g(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "cn.soulapp.android.component.square.main.ad.AdExpressViewHolder", p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("s", "cn.soulapp.android".equals(p1.getPackageName())), 0x0);
      Ldev/local/newsoul/j.g(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "pi.k", "ad_conversation", 0x1);
      Ldev/local/newsoul/j.g(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "cn.soulapp.android.component.square.video.adapter.a", "feed data filter installed; methods=", 0x1);
      Ldev/local/newsoul/j.f(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "cn.soulapp.android.ad.dialog.InterstitialAdDialog");
      Ldev/local/newsoul/j.f(p0.hook(p1.getClassLoader().loadClass("cn.soulapp.android.component.startup.main.MainScheduler").getDeclaredMethod("v", "cn.soulapp.android".equals(p1.getPackageName()))).setId("new-soul/skip-startup-ad").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE), "cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog");
      :try_start_431:
      new Ldev/local/newsoul/a(0x0);
      :try_end_452:
      goto :goto_459;
      :catchall_453:
      Ldev/local/newsoul/j.c("view visibility", e);
      :goto_459:
      new Ljava/lang/StringBuilder("ad controls installed; ready=");
      Ldev/local/newsoul/j.e(new Ljava/lang/StringBuilder("ad controls installed; ready=").toString());
      t.f = p0;
      :try_start_47b:
      t.h = e.loadClass("cn.soulapp.imlib.msg.chat.DiceFingerMsg").getDeclaredConstructor(new Class[][] {Integer.TYPE});
      t.i = e.loadClass("cn.soulapp.imlib.msg.chat.DiceFingerMsg").getField("number");
      new Ldev/local/newsoul/a(0x4);
      e.loadClass("cn.soulapp.android.component.chat.utils.y4").getDeclaredField("a").setAccessible(0x1);
      t.a = Ldev/local/newsoul/t.a(e.loadClass("cn.soulapp.android.component.chat.utils.y4"), e.loadClass("cn.soulapp.android.component.chat.utils.y4").getDeclaredField("a"), "w0", 0x1);
      t.b = Ldev/local/newsoul/t.a(e.loadClass("cn.soulapp.android.component.chat.utils.y4"), e.loadClass("cn.soulapp.android.component.chat.utils.y4").getDeclaredField("a"), "h1", 0x0);
      new Ljava/lang/StringBuilder("feed data filter installed; methods=");
      t.f.log(0x4, "SoulHook102", new Ljava/lang/StringBuilder("feed data filter installed; methods=").toString());
      :try_end_4f4:
      goto :goto_500;
      :catchall_4f5:
      t.f.log(0x6, "SoulHook102", "chat games unavailable", e);
      :goto_500:
      :try_start_508:
      Q0.l = e.loadClass("lf.b").getDeclaredMethod("b", 0x0);
      Q0.m = e.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", 0x0);
      Q0.n = e.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("getChatMessage", 0x0);
      Q0.p = e.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("getFrom", 0x0);
      Q0.q = e.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("getTo", 0x0);
      Q0.r = e.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("getMsgId", 0x0);
      Q0.s = e.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("isAnonymityChat", 0x0);
      Q0.o = e.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("getMsgType", 0x0);
      Q0.t = e.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("getMsgContent", 0x0);
      Q0.u = e.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("getStringTransExt", new Class[][] {0x0});
      Q0.v = e.loadClass("cn.soulapp.imlib.ChatManager").getMethod("F", new Class[][] {0x0});
      Q0.w = e.loadClass("cn.soulapp.imlib.Conversation").getMethod("getMessage", new Class[][] {0x0});
      Q0.x = e.loadClass("cn.soulapp.imlib.ChatManager").getMethod("K", new Class[][] {0x0});
      Q0.y = e.loadClass("cn.soulapp.imlib.i0").getMethod("v", 0x0);
      Q0.z = e.loadClass("cn.soulapp.imlib.i0").getMethod("w", 0x0);
      Q0.A = e.loadClass("cn.soulapp.imlib.listener.MsgListener").getMethod("onRefreshUi", 0x0);
      Ldev/local/newsoul/Q0.K(p0, e, e.loadClass("cn.soulapp.imlib.msg.ImMessage"));
      new Ldev/local/newsoul/a(0x9);
      new Ldev/local/newsoul/a(0xa);
      Q0.k = 0x1;
      :try_end_62f:
      goto :goto_637;
      :catchall_630:
      p0.log(0x6, "feed data filter installed; methods=", "private chat anti-recall unavailable", e);
      :goto_637:
      Ldev/local/newsoul/g1.a(p0, p1.getClassLoader());
      Ldev/local/newsoul/Q0.x(p0, p1.getClassLoader());
      :try_start_649:
      new Ldev/local/newsoul/F();
      new Ldev/local/newsoul/F().h(p0, e);
      :try_end_651:
      goto :goto_663;
      :catchall_652:
      Ldev/local/newsoul/V.i("repeat", e);
      p0.log(0x6, "SoulHook102", "repeat unavailable");
      :goto_663:
      Ldev/local/newsoul/Q0.z(p0, p1.getClassLoader());
      Ldev/local/newsoul/Q0.E(p0, p1.getClassLoader());
      Ldev/local/newsoul/Q0.D(p0, p1.getClassLoader());
      Ldev/local/newsoul/Q0.A(p0, p1.getClassLoader());
      Ldev/local/newsoul/Q0.B(p0, p1.getClassLoader());
      Ldev/local/newsoul/Q0.C(p0, p1.getClassLoader());
      Ldev/local/newsoul/u1.g(p0, p1.getClassLoader());
      Ldev/local/newsoul/p0.e(p0, p1.getClassLoader());
      Ldev/local/newsoul/B2.c(p0, p1.getClassLoader());
      Ldev/local/newsoul/Q0.F(p0, p1.getClassLoader());
      S.a = p0;
      Ldev/local/newsoul/o.c(p0, p1.getClassLoader());
      :try_start_6b5:
      new Ldev/local/newsoul/x2(p0, 0x0);
      p0.installed = 0x1;
      p0.info("hook installed: android.app.Activity.onResume");
      p0.installSettingsHook(p1.getClassLoader());
      :try_end_6e6:
      goto :goto_6f7;
      :catchall_6e7:
      p0.log(0x6, "SoulHook102", "hook installation failed", e);
      :goto_6f7:
      return;
      :goto_6f8:
      :try_start_6f8:
      }
      :try_end_6f9:
      throw e;
      :cond_6fa:
      :goto_6fa:
      return;

      // .catchall {:try_start_28 .. :try_end_44} :catchall_39
      // .catchall {:try_start_76 .. :try_end_98} :catchall_99
      // .catchall {:try_start_b2 .. :try_end_103} :catchall_104
      // .catchall {:try_start_10c .. :try_end_182} :catchall_178
      // .catchall {:try_start_187 .. :try_end_1dd} :catchall_1d3
      // .catchall {:try_start_1f5 .. :try_end_262} :catchall_2e3
      // .catchall {:try_start_264 .. :try_end_265} :catchall_269
      // .catchall {:try_start_26e .. :try_end_317} :catchall_2e3
      // .catchall {:try_start_31c .. :try_end_33e} :catchall_33f
      // .catchall {:try_start_3ce .. :try_end_409} :catchall_3fa
      // .catchall {:try_start_431 .. :try_end_452} :catchall_453
      // .catchall {:try_start_47b .. :try_end_4f4} :catchall_4f5
      // .catchall {:try_start_508 .. :try_end_62f} :catchall_630
      // .catchall {:try_start_649 .. :try_end_651} :catchall_652
      // .catchall {:try_start_6b5 .. :try_end_6e6} :catchall_6e7
      // .catchall {:try_start_6f8 .. :try_end_6f9} :catchall_39
    }

}