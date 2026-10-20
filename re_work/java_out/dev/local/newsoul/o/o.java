package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/o ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class o extends Ljava/lang/Object {

    public static boolean a;
    public static final String[] b;
    public static final WeakHashMap c;
    public static Method d;

    static void static {}() {
      o.b = new String[][] {"main_tab_planet", "main_tab_square", "main_tab_msg", "main_tab_me"};
      new Ljava/util/WeakHashMap();
      o.c = new Ljava/util/WeakHashMap();
      return;
    }

    public static void a(Activity p0) {
      if (p0.isFinishing() != 0) goto :cond_71;
      if (p0.isDestroyed() == 0) goto :cond_f;
      goto :goto_71;
      :cond_f:
      :try_start_f:
      if (p0.getResources().getIdentifier("main_bottom_tab_bar", "id", p0.getPackageName()) != 0) goto :cond_22;
      goto :goto_26;
      :cond_22:
      :goto_26:
      if (0x0 != 0) goto :cond_29;
      return;
      :cond_29:
      :goto_2c:
      if (v4 >= 0x4) goto :cond_71;
      if (p0.getResources().getIdentifier(p0.getPackageName()[v4], "id", p0.getPackageName()) != 0) goto :cond_41;
      goto :goto_45;
      :cond_41:
      :goto_45:
      if (0x0 == 0) goto :cond_67;
      if (o.c.containsKey(0x0) != 0) goto :cond_67;
      if (Ldev/local/newsoul/k.h(0x0) == 0) goto :cond_56;
      goto :goto_67;
      :cond_56:
      new Ldev/local/newsoul/n(0x0, p0);
      0x0.setOnLongClickListener(new Ldev/local/newsoul/n(0x0, p0));
      :try_end_64:
      goto :goto_67;
      :catchall_65:
      goto :goto_6a;
      :cond_67:
      :goto_67:
      goto :goto_2c;
      :goto_6a:
      :cond_71:
      :goto_71:
      return;

      // .catchall {:try_start_f .. :try_end_64} :catchall_65
    }

    public static boolean b(Activity p0) {
      :try_start_0:
      if (o.d == 0) goto :cond_10;
      :try_end_f:
      return o.d;
      :catch_10:
      :cond_10:
      if ((p0.getResources().getConfiguration().uiMode & 0x30) != 0x20) goto :cond_22;
      goto :goto_23;
      :cond_22:
      :goto_23:
      return 0x20;

      // try :try_start_0..:try_end_f catch (Ljava/lang/ReflectiveOperationException) -> :catch_10
    }

    public static void c(ClassLoader p0) {
      :try_start_2:
      o.d = p1.loadClass("cn.soulapp.android.lib.common.utils.settings.SoulSettings").getMethod("isNightMode", 0x0);
      :try_end_11:
      goto :goto_14;
      :catchall_12:
      goto :goto_4b;
      :catch_14:
      :goto_14:
      :try_start_14:
      new Ldev/local/newsoul/a(0x2);
      o.a = 0x1;
      :try_end_4a:
      goto :goto_50;
      :goto_4b:
      :goto_50:
      return;

      // try :try_start_2..:try_end_11 catch (Ljava/lang/ReflectiveOperationException) -> :catch_14
      // .catchall {:try_start_2 .. :try_end_11} :catchall_12
      // .catchall {:try_start_14 .. :try_end_4a} :catchall_12
    }

}