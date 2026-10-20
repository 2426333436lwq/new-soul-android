package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/V ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class V extends Ljava/lang/Object {

    public static final ScheduledExecutorService a;
    public static final Handler b;
    public static final ArrayList c;
    public static final LinkedHashMap d;
    public static final String e;
    public static long f;
    public static long g;
    public static String h;
    public static Context i;
    public static SharedPreferences j;
    public static Y k;
    public static String l;
    public static int m;
    public static long n;

    static void static {}() {
      V.a = __result__;
      new Landroid/os/Handler(__result__);
      V.b = new Landroid/os/Handler(__result__);
      new Ljava/util/ArrayList();
      V.c = new Ljava/util/ArrayList();
      new Ljava/util/LinkedHashMap();
      V.d = new Ljava/util/LinkedHashMap();
      V.e = __result__.toString();
      V.h = "未开启";
      V.l = "unknown";
      return;
    }

    public static boolean a() {
      if (compare((V.f - __result__), 0x0) <= 0) goto :cond_16;
      if (compare((V.f - __result__), 0x1b7740) > 0) goto :cond_16;
      goto :goto_17;
      :cond_16:
      :goto_17:
      return 0x1;
    }

    public static void b(String p0) {
      synchronized (V.c) {
      :try_start_3:
      if (v0.size() < 0x100) goto :cond_15;
      V.m = (V.m + 0x1);
      }
      :try_end_12:
      return;
      :catchall_13:
      goto :goto_56;
      :cond_15:
      :try_start_15:
      new Lorg/json/JSONObject();
      V.n = (V.n + 0x1);
      if (p1.length() <= 0x1770) goto :cond_4a;
      :cond_4a:
      :try_end_54:
      :catch_54:
      :try_start_54:
      }
      return;
      :goto_56:
      }
      :try_end_57:
      throw "facts";

      // .catchall {:try_start_3 .. :try_end_12} :catchall_13
      // try :try_start_15..:try_end_54 catch (Ljava/lang/Exception) -> :catch_54
      // .catchall {:try_start_15 .. :try_end_54} :catchall_13
      // .catchall {:try_start_54 .. :try_end_57} :catchall_13
    }

    public static String c(Throwable p0) {
      new Ljava/lang/StringBuilder();
      :goto_7:
      if (p0 == 0) goto :cond_5d;
      if (v2 >= 0x3) goto :cond_5d;
      :goto_1c:
      if (v4 >= Ljava/lang/Math.min(0x3.length, 0x6)) goto :cond_51;
      goto :goto_1c;
      :cond_51:
      goto :goto_7;
      :cond_5d:
      return v0;
    }

    public static void d(String p0) {
      synchronized (V.c) {
      :try_start_3:
      if (__result__ == 0) goto :cond_f;
      Ldev/local/newsoul/V.b(p0, p1);
      goto :goto_f;
      :catchall_d:
      goto :goto_11;
      :cond_f:
      :goto_f:
      }
      return;
      :goto_11:
      }
      :try_end_12:
      throw e;

      // .catchall {:try_start_3 .. :try_end_12} :catchall_d
    }

    public static byte[] e() {
      if (V.k == 0) goto :cond_b8;
      new Ljava/io/ByteArrayOutputStream();
      if ((File) V.k.a.listFiles() != 0) goto :cond_19;
      :cond_19:
      new Ljava/lang/Object();
      Ljava/util/Arrays.sort((File) V.k.a.listFiles(), Ljava/util/Comparator.comparing(new Ljava/lang/Object()));
      new Ljava/util/zip/ZipOutputStream(new Ljava/io/ByteArrayOutputStream());
      :try_start_2a:
      :goto_2d:
      if (v6 >= v5) goto :cond_95;
      if ((File) V.k.a.listFiles()[v6].getName().endsWith(".chunk") != 0) goto :cond_3e;
      goto :goto_7f;
      :cond_3e:
      if (compare((File) V.k.a.listFiles()[v6].length(), 0x400000) > 0) goto :cond_8d;
      if (compare(((long) new Ljava/io/ByteArrayOutputStream().size()), 0x480000) > 0) goto :cond_8d;
      new Ljava/util/zip/ZipEntry((File) V.k.a.listFiles()[v6].getName());
      v4.putNextEntry(new Ljava/util/zip/ZipEntry((File) V.k.a.listFiles()[v6].getName()));
      new Ljava/io/FileInputStream((File) V.k.a.listFiles()[v6]);
      :try_end_66:
      :try_start_68:
      :goto_6a:
      if (compare(((long) new Ljava/io/ByteArrayOutputStream().size()), 0x480000).read((File) V.k.a.listFiles()[v6]) == -0x1) goto :cond_77;
      v4.write((File) V.k.a.listFiles()[v6], 0x0, compare(((long) new Ljava/io/ByteArrayOutputStream().size()), 0x480000).read((File) V.k.a.listFiles()[v6]));
      :try_end_74:
      goto :goto_6a;
      :catchall_75:
      goto :goto_84;
      :cond_77:
      :try_start_77:
      compare(((long) new Ljava/io/ByteArrayOutputStream().size()), 0x480000).close();
      v4.closeEntry();
      :try_end_7d:
      :goto_7f:
      goto :goto_2d;
      :catchall_82:
      goto :goto_af;
      :goto_84:
      :try_start_84:
      compare(((long) new Ljava/io/ByteArrayOutputStream().size()), 0x480000).close();
      :try_end_87:
      goto :goto_8c;
      :catchall_88:
      :try_start_89:
      e.addSuppressed(new Ljava/io/ByteArrayOutputStream());
      :goto_8c:
      throw e;
      :cond_8d:
      new Ljava/io/IOException("Size limit");
      throw new Ljava/io/IOException("Size limit");
      :try_end_95:
      :cond_95:
      v4.close();
      if (v7 == 0) goto :cond_a7;
      return Ldev/local/newsoul/W.a(new Ljava/io/ByteArrayOutputStream().toByteArray(), (PublicKey) e.b);
      :cond_a7:
      new Ljava/io/IOException("No records");
      throw new Ljava/io/IOException("No records");
      :goto_af:
      :try_start_af:
      v4.close();
      :try_end_b2:
      goto :goto_b7;
      :catchall_b3:
      e.addSuppressed(e);
      :goto_b7:
      throw e;
      :cond_b8:
      new Ljava/io/IOException();
      throw new Ljava/io/IOException();

      // .catchall {:try_start_2a .. :try_end_66} :catchall_82
      // .catchall {:try_start_68 .. :try_end_74} :catchall_75
      // .catchall {:try_start_77 .. :try_end_7d} :catchall_82
      // .catchall {:try_start_84 .. :try_end_87} :catchall_88
      // .catchall {:try_start_89 .. :try_end_95} :catchall_82
      // .catchall {:try_start_af .. :try_end_b2} :catchall_b3
    }

    public static void f() {
      if (V.k != 0) goto :cond_7;
      return;
      :cond_7:
      new Ljava/lang/StringBuilder();
      synchronized (V.c) {
      :try_start_f:
      if (V.m <= 0) goto :cond_3e;
      if (v2.size() != 0x100) goto :cond_24;
      :cond_24:
      new Ljava/lang/StringBuilder("count=");
      Ldev/local/newsoul/V.b("records_dropped", new Ljava/lang/StringBuilder("count=").toString());
      V.m = 0x0;
      goto :goto_3e;
      :catchall_3b:
      goto :goto_10b;
      :cond_3e:
      :goto_3e:
      :goto_42:
      if (e.hasNext() == 0) goto :cond_57;
      goto :goto_42;
      :cond_57:
      V.c.clear();
      }
      :try_end_5d:
      if (V.k.length() != 0) goto :cond_64;
      return;
      :cond_64:
      if (compare((V.k.b() + ((long) Ldev/local/newsoul/W.a(V.k.toString().getBytes(StandardCharsets.UTF_8), (PublicKey) V.k.b).length)), 0x400000) <= 0) goto :cond_94;
      Ldev/local/newsoul/V.l("记录已满，已停止");
      synchronized (e) {
      :try_start_8c:
      e.clear();
      }
      goto :goto_ea;
      :catchall_91:
      }
      :try_end_93:
      throw Ldev/local/newsoul/W.a(V.k.toString().getBytes(StandardCharsets.UTF_8), (PublicKey) V.k.b);
      :cond_94:
      new Ljava/io/File((File) V.k.a, Ljava/lang/String.format(Locale.ROOT, "%013d-%s.chunk", new Object[][] {Ljava/lang/Long.valueOf(V.k.b(), 0x400000), Ljava/lang/Long.valueOf(V.k.b(), 0x400000)}));
      new Ljava/lang/StringBuilder();
      new Ljava/io/File((File) V.k.a, new Ljava/lang/StringBuilder().toString());
      :try_start_cf:
      new Ljava/io/FileOutputStream(compare((V.k.b() + ((long) Ldev/local/newsoul/W.a(V.k.toString().getBytes(StandardCharsets.UTF_8), (PublicKey) V.k.b).length)), 0x400000));
      :try_end_d4:
      :try_start_d4:
      V.k.write(Ldev/local/newsoul/W.a(V.k.toString().getBytes(StandardCharsets.UTF_8), (PublicKey) V.k.b));
      V.k.getFD().sync();
      :try_end_de:
      :try_start_de:
      V.k.close();
      :try_end_e5:
      if (e == 0) goto :cond_f3;
      :goto_ea:
      V.g = V.k.b();
      return;
      :cond_f3:
      :try_start_f3:
      new Ljava/io/IOException("Storage rename failed");
      throw new Ljava/io/IOException("Storage rename failed");
      :try_end_fb:
      :catchall_fb:
      goto :goto_107;
      :catchall_fd:
      :try_start_fe:
      V.k.close();
      :try_end_101:
      goto :goto_106;
      :catchall_102:
      :try_start_103:
      e.addSuppressed(Ldev/local/newsoul/W.a(V.k.toString().getBytes(StandardCharsets.UTF_8), (PublicKey) V.k.b));
      :goto_106:
      throw e;
      :try_end_107:
      :goto_107:
      throw e;
      :goto_10b:
      :try_start_10b:
      }
      :try_end_10c:
      throw e;

      // .catchall {:try_start_f .. :try_end_5d} :catchall_3b
      // .catchall {:try_start_8c .. :try_end_93} :catchall_91
      // .catchall {:try_start_cf .. :try_end_d4} :catchall_fb
      // .catchall {:try_start_d4 .. :try_end_de} :catchall_fd
      // .catchall {:try_start_de .. :try_end_e5} :catchall_fb
      // .catchall {:try_start_f3 .. :try_end_fb} :catchall_fb
      // .catchall {:try_start_fe .. :try_end_101} :catchall_102
      // .catchall {:try_start_103 .. :try_end_107} :catchall_fb
      // .catchall {:try_start_10b .. :try_end_10c} :catchall_3b
    }

    public static boolean g() {
      synchronized (V.c) {
      :try_start_3:
      if (compare(V.g, 0x0) > 0) goto :cond_14;
      if (v0.isEmpty() != 0) goto :cond_12;
      goto :goto_14;
      :cond_12:
      goto :goto_15;
      :cond_14:
      :goto_14:
      :goto_15:
      }
      return v0;
      :catchall_17:
      }
      :try_end_19:
      throw 0x0;

      // .catchall {:try_start_3 .. :try_end_19} :catchall_17
    }

    public static synchronized void h(Context p0) {
      synchronized (V.class) {
      :try_start_3:
      :try_end_5:
      if (v1 == 0) goto :cond_9;
      }
      return;
      :cond_9:
      :try_start_9:
      V.i = p0.getApplicationContext();
      :try_end_f:
      if (p0 != 0) goto :cond_13;
      }
      return;
      :cond_13:
      :try_start_13:
      V.j = p0.getSharedPreferences("newsoul_debug", 0x0);
      new Ldev/local/newsoul/h(0x2);
      V.a.execute(new Ldev/local/newsoul/h(0x2));
      new Ldev/local/newsoul/h(0x3);
      :try_end_36:
      }
      return;
      :catchall_38:
      }
      throw e;

      // .catchall {:try_start_3 .. :try_end_5} :catchall_38
      // .catchall {:try_start_9 .. :try_end_f} :catchall_38
      // .catchall {:try_start_13 .. :try_end_36} :catchall_38
    }

    public static void i(Throwable p0) {
      if (p1 != 0) goto :cond_5;
      goto :goto_9;
      :cond_5:
      :goto_9:
      Ldev/local/newsoul/V.j(p0, "registered");
      return;
    }

    public static void j(String p0) {
      synchronized (V.d) {
      :try_start_3:
      }
      :try_end_7:
      new Ljava/lang/StringBuilder("registration.");
      Ldev/local/newsoul/V.d(new Ljava/lang/StringBuilder("registration.").toString(), p1);
      return;
      :catchall_19:
      :try_start_1a:
      }
      :try_end_1b:
      throw p0;

      // .catchall {:try_start_3 .. :try_end_7} :catchall_19
      // .catchall {:try_start_1a .. :try_end_1b} :catchall_19
    }

    public static void k(String p0) {
      new Ljava/lang/StringBuilder("format=1; host=");
      Ldev/local/newsoul/V.d(p0, new Ljava/lang/StringBuilder("format=1; host=").toString());
      synchronized (V.d) {
      :try_start_5e:
      :goto_66:
      if (v0.hasNext() == 0) goto :cond_95;
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/V.d(new Ljava/lang/StringBuilder().toString(), (String) (Map$Entry) v0.next().getValue());
      goto :goto_66;
      :catchall_93:
      goto :goto_e8;
      :cond_95:
      }
      :try_end_96:
      :try_start_96:
      :goto_a2:
      if (p0.hasNext() == 0) goto :cond_be;
      Ldev/local/newsoul/V.d("compatibility", (String) p0.next());
      :try_end_b3:
      goto :goto_a2;
      :catchall_b4:
      Ldev/local/newsoul/V.d("compatibility.failed", Ldev/local/newsoul/V.c(e));
      :cond_be:
      new Ljava/lang/StringBuilder("repeat=");
      Ldev/local/newsoul/V.d("ready", new Ljava/lang/StringBuilder("repeat=").toString());
      return;
      :goto_e8:
      :try_start_e8:
      }
      :try_end_e9:
      throw p0.hasNext();

      // .catchall {:try_start_5e .. :try_end_96} :catchall_93
      // .catchall {:try_start_96 .. :try_end_b3} :catchall_b4
      // .catchall {:try_start_e8 .. :try_end_e9} :catchall_93
    }

    public static void l(String p0) {
      synchronized (V.c) {
      :try_start_3:
      if (compare(V.f, 0x0) == 0) goto :cond_d;
      goto :goto_e;
      :cond_d:
      :goto_e:
      V.f = 0x0;
      V.h = p0;
      if (0x1 == 0) goto :cond_1c;
      Ldev/local/newsoul/V.b("session_stop", p0);
      goto :goto_1c;
      :catchall_1a:
      goto :goto_35;
      :cond_1c:
      :goto_1c:
      }
      :try_end_1d:
      if (V.j == 0) goto :cond_34;
      :cond_34:
      return;
      :goto_35:
      :try_start_35:
      }
      :try_end_36:
      throw e;

      // .catchall {:try_start_3 .. :try_end_1d} :catchall_1a
      // .catchall {:try_start_35 .. :try_end_36} :catchall_1a
    }

    public static String m(String p0) {
      :try_start_0:
      new Ljava/lang/StringBuilder();
      :try_end_28:
      return v0;
      :catch_29:
      return v0;

      // try :try_start_0..:try_end_28 catch (Ljava/lang/Exception) -> :catch_29
    }

}