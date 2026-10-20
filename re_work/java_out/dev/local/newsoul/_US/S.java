package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/S ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class S extends Ljava/lang/Object {

    public static XposedModule a;
    public static final Handler b;
    public static final ExecutorService c;
    public static final WeakHashMap d;
    public static final HashSet e;
    public static final SecureRandom f;

    static void static {}() {
      new Landroid/os/Handler(__result__);
      S.b = new Landroid/os/Handler(__result__);
      S.c = __result__;
      new Ljava/util/WeakHashMap();
      S.d = new Ljava/util/WeakHashMap();
      new Ljava/util/HashSet();
      S.e = new Ljava/util/HashSet();
      new Ljava/security/SecureRandom();
      S.f = new Ljava/security/SecureRandom();
      return;
    }

    public static void a(Activity p0) {
      if (S.a == 0) goto :cond_50;
      :goto_8:
      if (p0 == 0) goto :cond_1e;
      :try_start_a:
      :try_end_18:
      goto :goto_1f;
      :catch_19:
      goto :goto_8;
      :cond_1e:
      :goto_1f:
      if (p0.getSuperclass() == 0) goto :cond_4a;
      if (S.e.contains(p0.getSuperclass()) == 0) goto :cond_2a;
      return;
      :cond_2a:
      new Ldev/local/newsoul/a(0xc);
      return;
      :cond_4a:
      new Ljava/lang/NoSuchMethodException();
      throw new Ljava/lang/NoSuchMethodException();
      :cond_50:
      new Ljava/lang/IllegalStateException();
      throw new Ljava/lang/IllegalStateException();

      // try :try_start_a..:try_end_18 catch (Ljava/lang/NoSuchMethodException) -> :catch_19
    }

    public static void b(String p0) {
      if (S.d.get(p0) == p1) goto :cond_9;
      return;
      :cond_9:
      if ((Q) p1.e.get() == 0) goto :cond_1b;
      (Q) p1.e.get().a(p2, 0x0);
      goto :goto_2f;
      :cond_1b:
      if (0x0.isDestroyed() != 0) goto :cond_2f;
      if (0x0.isDestroyed() != 0) goto :cond_2f;
      Landroid/widget/Toast.makeText(0x0, p2, 0x1).show();
      :cond_2f:
      :goto_2f:
      return;
    }

    public static void c(Ldev/local/newsoul/q2;Ldev/local/newsoul/Q;Z p0) {
      :try_start_7:
      if ((P) v2.get(v1) == 0) goto :cond_1d;
      if ((P) v2.get(v1).e.get() == p1) goto :cond_1c;
      p1.a("配置操作进行中，请先完成或取消", v3);
      :cond_1c:
      return;
      :cond_1d:
      Ldev/local/newsoul/S.a(v1);
      if (p2 == 0) goto :cond_33;
      goto :goto_34;
      :cond_33:
      :goto_34:
      new Ldev/local/newsoul/P((P) v2.get(v1).e.get(), p1, Ldev/local/newsoul/G.a(v1.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", v3).getAll()));
      if (p2 == 0) goto :cond_40;
      goto :goto_42;
      :cond_40:
      :goto_42:
      new Landroid/content/Intent("android.intent.action.CREATE_DOCUMENT");
      if (p2 == 0) goto :cond_4f;
      goto :goto_51;
      :cond_4f:
      :goto_51:
      if (p2 == 0) goto :cond_7e;
      new Ljava/text/SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ROOT);
      new Ljava/util/Date();
      new Ljava/lang/StringBuilder(v0);
      :cond_7e:
      if (p2 == 0) goto :cond_86;
      goto :goto_88;
      :cond_86:
      :goto_88:
      p1.a("请选择保存位置", 0x1);
      :try_end_8c:
      :try_start_8c:
      v1.startActivityForResult(new Landroid/content/Intent("android.intent.action.CREATE_DOCUMENT"), new Ldev/local/newsoul/P((P) v2.get(v1).e.get(), p1, Ldev/local/newsoul/G.a(v1.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", v3).getAll())).a);
      :try_end_91:
      goto :goto_9c;
      :catch_92:
      :try_start_93:
      throw new Landroid/content/Intent("android.intent.action.CREATE_DOCUMENT");
      :try_end_97:
      :catch_97:
      p1.a("无法打开配置文件操作，请重试", v3);
      :goto_9c:
      return;

      // try :try_start_7..:try_end_8c catch (Ljava/lang/Exception) -> :catch_97
      // try :try_start_8c..:try_end_91 catch (Ljava/lang/RuntimeException) -> :catch_92
      // try :try_start_8c..:try_end_91 catch (Ljava/lang/Exception) -> :catch_97
      // try :try_start_93..:try_end_97 catch (Ljava/lang/Exception) -> :catch_97
    }

    public static boolean d(P p0) {
      if (p0.isFinishing() != 0) goto :cond_39;
      if (p0.isDestroyed() != 0) goto :cond_39;
      if ((q2) p1.d.get() == 0) goto :cond_39;
      if ((q2) p1.d.get().a != p0) goto :cond_39;
      if ((q2) p1.d.get().e == 0) goto :cond_39;
      if ((q2) p1.d.get().e.isShowing() != 0) goto :cond_26;
      goto :goto_39;
      :cond_26:
      if ((q2) p1.d.get().e.getWindow() == 0) goto :cond_39;
      if ((q2) p1.d.get().e.getWindow().getDecorView().isAttachedToWindow() == 0) goto :cond_39;
      :cond_39:
      :goto_39:
      return (q2) p1.d.get().a;
    }

}