package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/d3 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class d3 extends Ljava/lang/Object {

    public static boolean g;
    public final Handler a;
    public final ExecutorService b;
    public final WeakHashMap c;
    public Method d;
    public Method e;
    public b3 f;

    public void super() {
      super();
      new Landroid/os/Handler(__result__);
      this.a = new Landroid/os/Handler(__result__);
      this.b = __result__;
      new Ljava/util/WeakHashMap();
      this.c = new Ljava/util/WeakHashMap();
      return;
    }

    public static TextView a(c3 p0) {
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setGravity(0x10);
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setPadding(Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0xc), Ldev/local/newsoul/w2.i(p0.g, 0xc));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setMinHeight(Ldev/local/newsoul/w2.i(p0.g, 0x30));
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setSingleLine(0x0);
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setEllipsize(0x0);
      Ldev/local/newsoul/w2.g(Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i));
      p0.c.b(Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i), 0x9, Ldev/local/newsoul/L2.e(0x9));
      new Ldev/local/newsoul/C0(p2, p0, 0x5);
      Ldev/local/newsoul/w2.n(p0.g, p1, 0xf, p0.i).setOnClickListener(new Ldev/local/newsoul/C0(p2, p0, 0x5));
      return new Ldev/local/newsoul/C0(p2, p0, 0x5);
    }

    public static void b(String p0) {
      :goto_2:
      if (v1 >= 0x6) goto :cond_c1;
      if (Ldev/local/newsoul/Q0.N(p1) == 0) goto :cond_b9;
      new Ljava/net/URL(p1);
      (HttpURLConnection) new Ljava/net/URL(p1).openConnection().setConnectTimeout(0x3a98);
      (HttpURLConnection) new Ljava/net/URL(p1).openConnection().setReadTimeout(0x4e20);
      (HttpURLConnection) new Ljava/net/URL(p1).openConnection().setInstanceFollowRedirects(v0);
      :try_start_23:
      if (Ldev/local/newsoul/Q0.N(p1).getResponseCode() < 0x12c) goto :cond_55;
      if (Ldev/local/newsoul/Q0.N(p1).getResponseCode() >= 0x190) goto :cond_55;
      if (Ldev/local/newsoul/Q0.N(p1).getHeaderField("Location") == 0) goto :cond_4d;
      new Ljava/net/URL(p1);
      new Ljava/net/URL(new Ljava/net/URL(p1), Ldev/local/newsoul/Q0.N(p1).getHeaderField("Location"));
      :try_end_45:
      Ldev/local/newsoul/Q0.N(p1).disconnect();
      goto :goto_2;
      :catchall_4b:
      goto :goto_b5;
      :cond_4d:
      :try_start_4d:
      new Ljava/io/IOException("Invalid redirect");
      throw new Ljava/io/IOException("Invalid redirect");
      :cond_55:
      if (Ldev/local/newsoul/Q0.N(p1).getHeaderField("Location") != 0xc8) goto :cond_99;
      if (compare(Ldev/local/newsoul/Q0.N(p1).getContentLengthLong(), 0x1400000) > 0) goto :cond_91;
      :try_end_68:
      :try_start_68:
      new Ljava/io/FileOutputStream(e);
      :try_end_6d:
      :try_start_6d:
      Ldev/local/newsoul/x1.b(compare(Ldev/local/newsoul/Q0.N(p1).getContentLengthLong(), 0x1400000), Ldev/local/newsoul/Q0.N(p1).getContentLengthLong());
      :try_end_70:
      :try_start_70:
      Ldev/local/newsoul/Q0.N(p1).getContentLengthLong().close();
      :try_end_73:
      :try_start_73:
      compare(Ldev/local/newsoul/Q0.N(p1).getContentLengthLong(), 0x1400000).close();
      :try_end_76:
      Ldev/local/newsoul/Q0.N(p1).disconnect();
      return;
      :catchall_7a:
      goto :goto_86;
      :catchall_7c:
      :try_start_7d:
      Ldev/local/newsoul/Q0.N(p1).getContentLengthLong().close();
      :try_end_80:
      goto :goto_85;
      :catchall_81:
      :try_start_82:
      e.addSuppressed(Ldev/local/newsoul/Q0.N(p1).getContentLengthLong());
      :goto_85:
      throw e;
      :try_end_86:
      :goto_86:
      if (compare(Ldev/local/newsoul/Q0.N(p1).getContentLengthLong(), 0x1400000) == 0) goto :cond_90;
      :try_start_88:
      compare(Ldev/local/newsoul/Q0.N(p1).getContentLengthLong(), 0x1400000).close();
      :try_end_8b:
      goto :goto_90;
      :catchall_8c:
      :try_start_8d:
      e.addSuppressed(compare(Ldev/local/newsoul/Q0.N(p1).getContentLengthLong(), 0x1400000));
      :cond_90:
      :goto_90:
      throw e;
      :cond_91:
      new Ljava/io/IOException("语音文件过大");
      throw new Ljava/io/IOException("语音文件过大");
      :cond_99:
      new Ljava/lang/StringBuilder();
      new Ljava/io/IOException(new Ljava/lang/StringBuilder().toString());
      throw new Ljava/io/IOException(new Ljava/lang/StringBuilder().toString());
      :try_end_b5:
      :goto_b5:
      Ldev/local/newsoul/Q0.N(p1).disconnect();
      throw e;
      :cond_b9:
      new Ljava/io/IOException("Invalid audio address");
      throw new Ljava/io/IOException("Invalid audio address");
      :cond_c1:
      new Ljava/io/IOException("Too many redirects");
      throw new Ljava/io/IOException("Too many redirects");

      // .catchall {:try_start_23 .. :try_end_45} :catchall_4b
      // .catchall {:try_start_4d .. :try_end_68} :catchall_4b
      // .catchall {:try_start_68 .. :try_end_6d} :catchall_7a
      // .catchall {:try_start_6d .. :try_end_70} :catchall_7c
      // .catchall {:try_start_70 .. :try_end_73} :catchall_7a
      // .catchall {:try_start_73 .. :try_end_76} :catchall_4b
      // .catchall {:try_start_7d .. :try_end_80} :catchall_81
      // .catchall {:try_start_82 .. :try_end_86} :catchall_7a
      // .catchall {:try_start_88 .. :try_end_8b} :catchall_8c
      // .catchall {:try_start_8d .. :try_end_b5} :catchall_4b
    }

    public static Object c(Object p0) {
      :goto_8:
      if (v2 == 0) goto :cond_40;
      :goto_10:
      if (v6 >= v5) goto :cond_3b;
      if (Ljava/lang/reflect/Modifier.isStatic(v4[v6].getModifiers()) != 0) goto :cond_39;
      if (v4[v6].getType().getName().equals(v1) != 0) goto :cond_2d;
      goto :goto_39;
      :cond_2d:
      if (v3 != 0) goto :cond_31;
      goto :goto_39;
      :cond_31:
      new Ljava/lang/IllegalStateException("Ambiguous conversation field");
      throw new Ljava/lang/IllegalStateException("Ambiguous conversation field");
      :cond_39:
      :goto_39:
      goto :goto_10;
      :cond_3b:
      goto :goto_8;
      :cond_40:
      if (v4[v6] == 0) goto :cond_4a;
      v4[v6].setAccessible(v0);
      return v0;
      :cond_4a:
      new Ljava/lang/NoSuchFieldException(v1);
      throw new Ljava/lang/NoSuchFieldException(v1);
    }

    public static Object e(String p0) {
      return p0.getClass();
    }

    public static void f(Throwable p0) {
      Ldev/local/newsoul/d3.i(p0, p1);
      return;
    }

    public static Object g(String p0) {
      :goto_4:
      if (v0 == 0) goto :cond_18;
      :try_start_6:
      v0.getDeclaredField(p1).setAccessible(0x1);
      :try_end_12:
      return v0;
      :catch_13:
      goto :goto_4;
      :cond_18:
      new Ljava/lang/NoSuchFieldException(p1);
      throw new Ljava/lang/NoSuchFieldException(p1);

      // try :try_start_6..:try_end_12 catch (Ljava/lang/NoSuchFieldException) -> :catch_13
    }

    public static void i(String p0) {
      Landroid/widget/Toast.makeText(p0, p1, 0x0).show();
      return;
    }

    public final void d(Object p0) {
      if ((Activity) Ldev/local/newsoul/d3.e(p1, "getActivity") == 0) goto :cond_7a;
      if ((Boolean) Ldev/local/newsoul/d3.e(p1, "isResumed").booleanValue() != 0) goto :cond_19;
      goto :goto_7a;
      :cond_19:
      if ((View) Ldev/local/newsoul/d3.e(p1, "getView") != 0) goto :cond_24;
      return;
      :cond_24:
      :goto_2f:
      if (v4 >= 0x3) goto :cond_7a;
      if ((Activity) Ldev/local/newsoul/d3.e(p1, "getActivity").getResources().getIdentifier(v3[v4], "id", (Activity) Ldev/local/newsoul/d3.e(p1, "getActivity").getPackageName()) != 0) goto :cond_47;
      goto :goto_4b;
      :cond_47:
      :goto_4b:
      if (0x0 == 0) goto :cond_77;
      if (p0.c.containsKey(0x0) != 0) goto :cond_77;
      if (Ldev/local/newsoul/k.h(0x0) == 0) goto :cond_5c;
      goto :goto_77;
      :cond_5c:
      new Ljava/lang/ref/WeakReference(p1);
      new Ldev/local/newsoul/O2(p0, new Ljava/lang/ref/WeakReference(p1), (Activity) Ldev/local/newsoul/d3.e(p1, "getActivity"));
      0x0.setOnLongClickListener(new Ldev/local/newsoul/O2(p0, new Ljava/lang/ref/WeakReference(p1), (Activity) Ldev/local/newsoul/d3.e(p1, "getActivity")));
      if (v2.equals(v3[v4]) == 0) goto :cond_72;
      0x0.setOnTouchListener(0x0);
      :cond_72:
      :cond_77:
      :goto_77:
      goto :goto_2f;
      :cond_7a:
      :goto_7a:
      return;
    }

    public final void h(Object p0) {
      new Ldev/local/newsoul/b3(p0, p1);
      if (Ldev/local/newsoul/Q0.N((String) Ldev/local/newsoul/d3.g(p2, "url")) == 0) goto :cond_3d;
      if ((Integer) Ldev/local/newsoul/d3.g(p2, "duration").intValue() <= 0) goto :cond_3d;
      if (p0.f == 0) goto :cond_28;
      p0.f.b();
      :cond_28:
      p0.f = new Ldev/local/newsoul/b3(p0, p1);
      new Ldev/local/newsoul/H2(new Ldev/local/newsoul/b3(p0, p1), (String) Ldev/local/newsoul/d3.g(p2, "url"), (Integer) Ldev/local/newsoul/d3.g(p2, "duration").intValue());
      new Ldev/local/newsoul/U2(0x1, new Ldev/local/newsoul/b3(p0, p1));
      new Ldev/local/newsoul/b3(p0, p1).e("保存到语音库", "", new Ldev/local/newsoul/H2(new Ldev/local/newsoul/b3(p0, p1), (String) Ldev/local/newsoul/d3.g(p2, "url"), (Integer) Ldev/local/newsoul/d3.g(p2, "duration").intValue()), new Ldev/local/newsoul/U2(0x1, new Ldev/local/newsoul/b3(p0, p1)));
      return;
      :cond_3d:
      new Ljava/lang/IllegalArgumentException("Invalid voice");
      throw new Ljava/lang/IllegalArgumentException("Invalid voice");
    }

}