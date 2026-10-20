package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/Q0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class Q0 extends Ljava/lang/Object {

    public static Method A;
    public static boolean B;
    public static Class C;
    public static Class D;
    public static Class E;
    public static Constructor F;
    public static Method G;
    public static Method H;
    public static Method I;
    public static Method J;
    public static Method K;
    public static boolean L;
    public static boolean M;
    public static boolean N;
    public static boolean O;
    public static boolean P;
    public static boolean Q;
    public static boolean R;
    public static XposedModule S;
    public static ClassLoader T;
    public static boolean U;
    public static boolean V;
    public static boolean W;
    public static boolean X;
    public static boolean Y;
    public static Class Z;
    public static boolean a;
    public static Field a0;
    public static boolean b;
    public static Method b0;
    public static boolean c;
    public static Method c0;
    public static Method d;
    public static Field d0;
    public static boolean e;
    public static boolean e0;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public static Method i;
    public static Method j;
    public static boolean k;
    public static Method l;
    public static Method m;
    public static Method n;
    public static Method o;
    public static Method p;
    public static Method q;
    public static Method r;
    public static Method s;
    public static Method t;
    public static Method u;
    public static Method v;
    public static Method w;
    public static Method x;
    public static Method y;
    public static Method z;

    public static void A(ClassLoader p0) {
      :try_start_8:
      p0.H(p1.loadClass("cn.soulapp.android.square.utils.ImageDownloader").getDeclaredMethod("f", new Class[][] {v1, Boolean.TYPE, Boolean.TYPE, p1.loadClass("cn.soulapp.android.square.utils.ImageMarkProcessor"), p1.loadClass("cn.soulapp.android.square.utils.b")}), "download_image_no_watermark", 0x2, -0x1, p1.loadClass("lf.b").getDeclaredMethod(v2, 0x0));
      Q0.L = v4;
      :try_end_3b:
      goto :goto_42;
      :catchall_3c:
      p0.log(v3, v0, "image watermark control unavailable", e);
      :goto_42:
      :try_start_42:
      p0.H(p1.loadClass("cn.soulapp.android.square.utils.h2").getDeclaredMethod(v2, new Class[][] {v1, Context.class, Long.TYPE, Boolean.TYPE}), "download_video_no_watermark", 0x3, 0x1, 0x0);
      Q0.M = v4;
      :try_end_61:
      goto :goto_68;
      :catchall_62:
      p0.log(v3, v0, "video watermark control unavailable", e);
      :goto_68:
      return;

      // .catchall {:try_start_8 .. :try_end_3b} :catchall_3c
      // .catchall {:try_start_42 .. :try_end_61} :catchall_62
    }

    public static void B(ClassLoader p0) {
      :try_start_a:
      v0.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredField("E").setAccessible(0x1);
      v0.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getDeclaredField("q").setAccessible(0x1);
      Ldev/local/newsoul/Q0.I(v1, v0, v0.loadClass(v0.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getName().concat(v4)), v0.loadClass(v0.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getName().concat(v4)).getField("a"));
      v0.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").<init>(v0.loadClass(v0.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getName().concat(v4)).getField("a"), v0.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredField("E"), v0.loadClass("cn.soulapp.android.square.post.bean.Post").getField("content"), v0.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getMethod("dismiss", 0x0), 0x1);
      XposedInterface$ExceptionMode.PROTECTIVE.<init>(v0.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredField("E"), v0.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getDeclaredField("q"), v0.loadClass(v0.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getName().concat(v4)).getField("a"), v0.loadClass(v0.loadClass("cn.soulapp.android.square.BaseSeedsDialogFragment").getName().concat(v4)).getConstructor(new Class[][] {Integer.TYPE, String.class, Boolean.TYPE}), 0x1);
      Q0.N = 0x1;
      :try_end_bb:
      goto :goto_c3;
      :catchall_bc:
      v1.log(0x6, v2, "post copy hooks unavailable", e);
      :goto_c3:
      return;

      // .catchall {:try_start_a .. :try_end_bb} :catchall_bc
    }

    public static void C(ClassLoader p0) {
      :try_start_2:
      if (p1.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredField("E").getType() != p1.loadClass("cn.soulapp.android.square.post.bean.Post")) goto :cond_71;
      p1.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredField("E").setAccessible(0x1);
      if (p1.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredMethod("r0", 0x0).getReturnType() != Void.TYPE) goto :cond_69;
      if (Ljava/lang/reflect/Modifier.isPrivate(p1.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredMethod("r0", 0x0).getModifiers()) == 0) goto :cond_69;
      new Ldev/local/newsoul/k1(p1.loadClass("cn.soulapp.android.square.SeedsShareDialogFragment").getDeclaredField("E"), 0x0);
      Q0.O = 0x1;
      goto :goto_7f;
      :catchall_67:
      goto :goto_79;
      :cond_69:
      new Ljava/lang/NoSuchMethodException("Unexpected share permission signature");
      throw new Ljava/lang/NoSuchMethodException("Unexpected share permission signature");
      :cond_71:
      new Ljava/lang/NoSuchFieldException("Unexpected share post field");
      throw new Ljava/lang/NoSuchFieldException("Unexpected share post field");
      :try_end_79:
      :goto_79:
      p0.log(0x6, v0, "post share control unavailable", e);
      :goto_7f:
      return;

      // .catchall {:try_start_2 .. :try_end_79} :catchall_67
    }

    public static void D(ClassLoader p0) {
      Q0.S = p0;
      Q0.T = p1;
      :try_start_6:
      new Ldev/local/newsoul/a(0x10);
      Q0.P = 0x1;
      Ldev/local/newsoul/Q0.w("privacy photo controls installed");
      :try_end_38:
      goto :goto_44;
      :catchall_39:
      Q0.S.log(0x6, "SoulHook102", "privacy photo controls unavailable", e);
      :goto_44:
      Q0.Q = Ldev/local/newsoul/Q0.J(p1, v0, "G", "privacy");
      Q0.R = Ldev/local/newsoul/Q0.J(p1, "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", "snap");
      return;

      // .catchall {:try_start_6 .. :try_end_38} :catchall_39
    }

    public static void E(ClassLoader p0) {
      :try_start_5:
      :goto_1b:
      if (v8 >= v6) goto :cond_4d;
      v7[v8] = v4.getDeclaredMethod(v5[v8], 0x0);
      if (v4.getDeclaredMethod(v5[v8], 0x0).getReturnType() != Boolean.TYPE) goto :cond_31;
      goto :goto_1b;
      :cond_31:
      new Ljava/lang/StringBuilder();
      new Ljava/lang/NoSuchMethodException(new Ljava/lang/StringBuilder().toString());
      throw new Ljava/lang/NoSuchMethodException(new Ljava/lang/StringBuilder().toString());
      :catchall_4a:
      goto :goto_c1;
      :cond_4d:
      :goto_4e:
      if (v5 >= v6) goto :cond_81;
      new Ljava/lang/StringBuilder();
      new Ldev/local/newsoul/f(0x2, v7[v5]);
      goto :goto_4e;
      :cond_81:
      :goto_8c:
      if (v6 >= 0x3) goto :cond_be;
      :goto_9b:
      if (new Ldev/local/newsoul/f(0x2, v7[v5]) >= p0.hook(v7[v5]).setId(new Ljava/lang/StringBuilder().toString()).setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE)) goto :cond_bb;
      if (Ljava/util/Arrays.asList(v7[v5][new Ldev/local/newsoul/f(0x2, v7[v5])].getParameterTypes()).contains(e) != 0) goto :cond_b5;
      if (0x3.endsWith("$d1") == 0) goto :cond_b8;
      :cond_b5:
      :cond_b8:
      goto :goto_9b;
      :cond_bb:
      goto :goto_8c;
      :cond_be:
      Q0.U = v3;
      :try_end_c0:
      goto :goto_c6;
      :goto_c1:
      p0.log(v1, v0, "social score model unavailable", e);
      :goto_c6:
      :try_start_c6:
      if (Ljava/lang/reflect/Modifier.isStatic(p1.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils").getDeclaredMethod("b", new Class[][] {p1.loadClass("androidx.fragment.app.FragmentManager"), p1.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a")}).getModifiers()) == 0) goto :cond_122;
      if (p1.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils").getDeclaredMethod("b", new Class[][] {p1.loadClass("androidx.fragment.app.FragmentManager"), p1.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a")}).getReturnType() != Void.TYPE) goto :cond_122;
      new Ldev/local/newsoul/a(0x12);
      :goto_113:
      if (v2 >= p1.loadClass("cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils")) goto :cond_11f;
      goto :goto_113;
      :catchall_11d:
      goto :goto_12a;
      :cond_11f:
      Q0.V = v3;
      goto :goto_12f;
      :cond_122:
      new Ljava/lang/NoSuchMethodException("Unexpected social score dialog signature");
      throw new Ljava/lang/NoSuchMethodException("Unexpected social score dialog signature");
      :try_end_12a:
      :goto_12a:
      p0.log(v1, v0, "social score dialog unavailable", e);
      :goto_12f:
      new Ljava/lang/StringBuilder("social score hooks: model=");
      return;

      // .catchall {:try_start_5 .. :try_end_c0} :catchall_4a
      // .catchall {:try_start_c6 .. :try_end_12a} :catchall_11d
    }

    public static void F(ClassLoader p0) {
      :try_start_0:
      Q0.Z = p1.loadClass("cn.soulapp.android.square.post.bean.Post");
      Q0.a0 = p1.loadClass("cn.soulapp.android.square.post.bean.Post").getField("id");
      Q0.b0 = Q0.Z.getMethod("hasDeclare", 0x0);
      Ldev/local/newsoul/Q0.y(p1, Q0.Z);
      Q0.c0 = Q0.Z.getMethod("getAdResponse", 0x0);
      Q0.d0 = Q0.Z.getField("incentiveModel");
      new Ldev/local/newsoul/a(0x13);
      new Ldev/local/newsoul/a(0x14);
      new Ldev/local/newsoul/a(0x15);
      Q0.Y = 0x1;
      :try_end_b3:
      goto :goto_c0;
      :catchall_b4:
      p0.log(0x6, "SoulHook102", "square like unavailable", e);
      :goto_c0:
      return;

      // .catchall {:try_start_0 .. :try_end_b3} :catchall_b4
    }

    public static void G(Lio/github/libxposed/api/XposedModule;Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;ZII p0) {
      if (p4 == 0) goto :cond_7;
      goto :goto_9;
      :cond_7:
      :goto_9:
      :try_start_9:
      if (p1.getDeclaredMethod(p2, p3).getReturnType() != Void.TYPE) goto :cond_43;
      if (Ljava/lang/reflect/Modifier.isStatic(p1.getDeclaredMethod(p2, p3).getModifiers()) == p4) goto :cond_43;
      new Ldev/local/newsoul/v(p6, p4, "repeat.caller", p5);
      Ldev/local/newsoul/V.i("repeat.caller", 0x0);
      Ldev/local/newsoul/Q0.k(p0, p1.getDeclaredMethod(p2, p3), "repeat.caller");
      goto :goto_4e;
      :catchall_41:
      goto :goto_4b;
      :cond_43:
      new Ljava/lang/NoSuchMethodException("Unexpected boundary signature");
      throw new Ljava/lang/NoSuchMethodException("Unexpected boundary signature");
      :try_end_4b:
      :goto_4b:
      Ldev/local/newsoul/V.i("repeat.caller", e);
      :goto_4e:
      return;

      // .catchall {:try_start_9 .. :try_end_4b} :catchall_41
    }

    public static void H(Method p0) {
      if (Ljava/lang/reflect/Modifier.isStatic(p1.getModifiers()) == 0) goto :cond_41;
      if (p1.getReturnType() != Void.TYPE) goto :cond_41;
      v6.<init>(p4, p5, p2, p0, p3);
      p0.log(0x4, "SoulHook102", "watermark control installed: ".concat(p2));
      return;
      :cond_41:
      new Ljava/lang/IllegalStateException("Unexpected download signature: ".concat(Ljava/lang/String.valueOf(p1)));
      throw new Ljava/lang/IllegalStateException("Unexpected download signature: ".concat(Ljava/lang/String.valueOf(p1)));
    }

    public static void I(Field p0) {
      :try_start_0:
      p1.loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a").getDeclaredField("c").setAccessible(0x1);
      new Ldev/local/newsoul/B(p1.loadClass("cn.soulapp.android.square.adapter.TypeAdapter$a").getDeclaredField("c"), p3, 0x1);
      :try_end_36:
      goto :goto_3f;
      :catchall_37:
      :goto_3f:
      return;

      // .catchall {:try_start_0 .. :try_end_36} :catchall_37
    }

    public static boolean J(String p0) {
      :try_start_5:
      if (p0.loadClass(p1).getDeclaredMethod(p2, 0x0).getReturnType() != Void.TYPE) goto :cond_98;
      if (Ljava/lang/reflect/Modifier.isStatic(p0.loadClass(p1).getDeclaredMethod(p2, 0x0).getModifiers()) != 0) goto :cond_98;
      new Ldev/local/newsoul/f(0x1, p3);
      Ldev/local/newsoul/Q0.m(p0.loadClass(p1));
      :goto_46:
      if (v0 >= Ljava/lang/reflect/Modifier.isStatic(p0.loadClass(p1).getDeclaredMethod(p2, 0x0).getModifiers())) goto :cond_52;
      Ldev/local/newsoul/Q0.m(p0.loadClass(p1).getDeclaredMethod(p2, 0x0)[v0]);
      goto :goto_46;
      :catchall_50:
      goto :goto_a6;
      :cond_52:
      if ("snap".equals(p3) == 0) goto :cond_82;
      new Ldev/local/newsoul/a(0x11);
      :cond_82:
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/Q0.w(new Ljava/lang/StringBuilder().toString());
      return (v0 + 0x1);
      :cond_98:
      new Ljava/lang/NoSuchMethodException(p0.loadClass(p1).getDeclaredMethod(p2, 0x0)[v0].concat(Ljava/lang/String.valueOf("snap".equals(p3))));
      throw new Ljava/lang/NoSuchMethodException(p0.loadClass(p1).getDeclaredMethod(p2, 0x0)[v0].concat(Ljava/lang/String.valueOf("snap".equals(p3))));
      :try_end_a6:
      :goto_a6:
      Q0.S.log(0x6, "SoulHook102", "privacy repeat guard unavailable: ".concat(p3), e);
      return "SoulHook102";

      // .catchall {:try_start_5 .. :try_end_a6} :catchall_50
    }

    public static void K(Class p0) {
      p1.loadClass("cn.soulapp.android.component.chat.widget.AbsChatDualItem").getDeclaredField("e").setAccessible(0x1);
      :goto_2c:
      if (v7 >= v4) goto :cond_5d;
      if (v0[v7].getParameterTypes().length != 0x4) goto :cond_5a;
      if (v0[v7].getParameterTypes()[v6] != p1) goto :cond_5a;
      if (v0[v7].getParameterTypes()[v3] != p2) goto :cond_5a;
      if (v0[v7].getParameterTypes()[0x2] != Integer.TYPE) goto :cond_5a;
      if (v0[v7].getParameterTypes()[0x3] != List.class) goto :cond_5a;
      if (v5 != 0) goto :cond_52;
      goto :goto_5a;
      :cond_52:
      new Ljava/lang/IllegalStateException("Ambiguous chat row binding");
      throw new Ljava/lang/IllegalStateException("Ambiguous chat row binding");
      :cond_5a:
      :goto_5a:
      goto :goto_2c;
      :cond_5d:
      if (v0[v7] == 0) goto :cond_7c;
      new Ldev/local/newsoul/B(v1, v2, 0x0);
      return;
      :cond_7c:
      new Ljava/lang/NoSuchMethodException("AbsChatDualItem bind");
      throw new Ljava/lang/NoSuchMethodException("AbsChatDualItem bind");
    }

    public static String L(Object p0) {
      new Ljava/lang/StringBuilder();
      new Ljava/lang/StringBuilder();
      :goto_60:
      if (v2 >= v1) goto :cond_7c;
      goto :goto_60;
      :cond_7c:
      return v0;
    }

    public static PublicKey M() {
      new Ljava/security/spec/X509EncodedKeySpec(Ljava/security/KeyFactory.getInstance("RSA").decode("MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqrDmgObzyP43bK71v6HgV0OBDB6+HIfkXftRMhNOIwfWbfaNZRR0/HCNnI2QSn+bFWyGHUdu7fh67Cy9n0hCnhDQh36aa3nq7+jogQqSEquEqJHqyFIfxi+4/HRJXyWAhs39M6sKyS4fJGK2xKP6gjN3oDzsunro5+ldPx9hfy/J38zm5u7qIhx5s37KKn9JoE58czyAoGp6a476JcrqUf1z5F+dhRiFa/Q8Cs/Sa1U26OxSZ+EOSauQj19rZBaXPtHh7AAXyakRJfznNPHbaH9OWg5loOB0nWHLyb/RYhlmdx/WTb+n2MiYOI2EkY4g2NanTLW4/Hydi6tM4gVTzs1xfqjdhHaI52iOXEvLdEnnOlk06ha0hFbfULxfDS4usDDs3J5G70kte+qrMmeqrcEmygaZnIUqKbVW6Qx6WIP8yVdNY3sBh1PwHy3LnXdsCFQbdhFocSSjZqvx5AUVi0Z74E7WAQsvQioFR3oyuGjJdxaXF3PX5bE0gVX80wzXAgMBAAE="));
      return Ljava/security/KeyFactory.getInstance("RSA").generatePublic(new Ljava/security/spec/X509EncodedKeySpec(Ljava/security/KeyFactory.getInstance("RSA").decode("MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqrDmgObzyP43bK71v6HgV0OBDB6+HIfkXftRMhNOIwfWbfaNZRR0/HCNnI2QSn+bFWyGHUdu7fh67Cy9n0hCnhDQh36aa3nq7+jogQqSEquEqJHqyFIfxi+4/HRJXyWAhs39M6sKyS4fJGK2xKP6gjN3oDzsunro5+ldPx9hfy/J38zm5u7qIhx5s37KKn9JoE58czyAoGp6a476JcrqUf1z5F+dhRiFa/Q8Cs/Sa1U26OxSZ+EOSauQj19rZBaXPtHh7AAXyakRJfznNPHbaH9OWg5loOB0nWHLyb/RYhlmdx/WTb+n2MiYOI2EkY4g2NanTLW4/Hydi6tM4gVTzs1xfqjdhHaI52iOXEvLdEnnOlk06ha0hFbfULxfDS4usDDs3J5G70kte+qrMmeqrcEmygaZnIUqKbVW6Qx6WIP8yVdNY3sBh1PwHy3LnXdsCFQbdhFocSSjZqvx5AUVi0Z74E7WAQsvQioFR3oyuGjJdxaXF3PX5bE0gVX80wzXAgMBAAE=")));
    }

    public static boolean N(String p0) {
      if (p0 != 0) goto :cond_4;
      return 0x0;
      :cond_4:
      :try_start_4:
      new Ljava/net/URI(p0);
      if ("https".equalsIgnoreCase(new Ljava/net/URI(p0).getScheme()) != 0) goto :cond_21;
      if ("http".equalsIgnoreCase(new Ljava/net/URI(p0).getScheme()) == 0) goto :cond_38;
      :cond_21:
      if (new Ljava/net/URI(p0).getHost() == 0) goto :cond_38;
      if (new Ljava/net/URI(p0).getHost().isEmpty() != 0) goto :cond_38;
      :try_end_35:
      if (new Ljava/net/URI(p0).getHost().isEmpty() != 0) goto :cond_38;
      :catch_38:
      :cond_38:
      return 0x0;

      // try :try_start_4..:try_end_35 catch (Ljava/net/URISyntaxException) -> :catch_38
    }

    public static String O(Object p0) {
      if (__result__ != 0) goto :cond_b;
      return "message_present=true; type=";
      :cond_b:
      if (p0 != 0) goto :cond_10;
      return "message_present=true; type=";
      :cond_10:
      :try_start_10:
      if (p0.getClass().getMethod("getChatMessage", 0x0).invoke(p0, 0x0) != 0) goto :cond_26;
      return "message_present=true; type=";
      :catchall_24:
      goto :goto_44;
      :cond_26:
      if ((e.getClass().getMethod("getMsgType", 0x0).invoke(e, 0x0) instanceof Integer) == 0) goto :cond_39;
      goto :goto_3b;
      :cond_39:
      :goto_3b:
      :try_end_43:
      return "message_present=true; type=";
      :goto_44:
      return "message_present=true; type=unavailable; error=";

      // .catchall {:try_start_10 .. :try_end_43} :catchall_24
    }

    public static int P(Ljava/lang/String;Z p0) {
      :try_start_0:
      if (p1 != 0) goto :cond_13;
      if (Ljava/lang/Integer.parseInt(p0.trim()) < 0) goto :cond_d;
      goto :goto_13;
      :cond_d:
      new Ljava/lang/NumberFormatException();
      throw new Ljava/lang/NumberFormatException();
      :try_end_13:
      :cond_13:
      :goto_13:
      return v0;
      :catch_14:
      if (p1 == 0) goto :cond_1b;
      goto :goto_1d;
      :cond_1b:
      :goto_1d:
      new Ljava/lang/IllegalArgumentException("请输入 -2147483648 至 2147483647 的整数");
      throw new Ljava/lang/IllegalArgumentException("请输入 -2147483648 至 2147483647 的整数");

      // try :try_start_0..:try_end_13 catch (Ljava/lang/NumberFormatException) -> :catch_14
    }

    public static int Q(String p0) {
      new Ljava/lang/StringBuilder();
      :try_start_27:
      :goto_30:
      if (v4 >= v1) goto :cond_60;
      if ("boolean".equals(p6[v4]) == 0) goto :cond_3f;
      goto :goto_59;
      :cond_3f:
      if ("int".equals(Boolean.TYPE) == 0) goto :cond_4a;
      goto :goto_59;
      :cond_4a:
      if ("long".equals(Integer.TYPE) == 0) goto :cond_55;
      goto :goto_59;
      :cond_55:
      :goto_59:
      v2[v4] = Long.TYPE;
      :try_end_5b:
      goto :goto_30;
      :catchall_5e:
      goto :goto_92;
      :cond_60:
      :try_start_60:
      :try_end_63:
      goto :goto_67;
      :catch_64:
      :try_start_64:
      :goto_67:
      if (p5.booleanValue() == 0) goto :cond_70;
      goto :goto_72;
      :cond_70:
      :goto_72:
      new Ljava/lang/StringBuilder();
      :try_end_91:
      return v0;
      :goto_92:
      new Ljava/lang/StringBuilder();
      return v0;

      // .catchall {:try_start_27 .. :try_end_5b} :catchall_5e
      // try :try_start_60..:try_end_63 catch (Ljava/lang/NoSuchMethodException) -> :catch_64
      // .catchall {:try_start_60 .. :try_end_63} :catchall_5e
      // .catchall {:try_start_64 .. :try_end_91} :catchall_5e
    }

    public static boolean R(Activity p0) {
      if (p0.getIntent().getBooleanExtra("isSender", 0x0) != 0) goto :cond_1e;
      if (p0.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0x0).getBoolean("privacy_allow_repeat", 0x0) == 0) goto :cond_1e;
      :cond_1e:
      return "privacy_allow_repeat";
    }

    public static boolean S(Object p0) {
      if (Ldev/local/newsoul/Q0.v(p2) != 0) goto :cond_8;
      return Ldev/local/newsoul/Q0.v(p2);
      :cond_8:
      if ((Integer) Q0.o.invoke(Q0.n.invoke(p2, 0x0), 0x0).intValue() != 0x9) goto :cond_e1;
      if ("1".equals(Q0.u.invoke(Q0.n.invoke(p2, 0x0), new Object[][] {"isSnap"})) == 0) goto :cond_35;
      goto :goto_e1;
      :cond_35:
      if (Q0.v.invoke(p1, new Object[][] {Q0.p.invoke(p2, 0x0)}) != 0) goto :cond_51;
      goto :goto_5b;
      :cond_51:
      :goto_5b:
      if (0x0 != 0) goto :cond_67;
      :cond_67:
      if (Ldev/local/newsoul/Q0.v(0x0) == 0) goto :cond_e1;
      if (Ldev/local/newsoul/Q0.u(0x0) == 0) goto :cond_e1;
      if (Q0.p.invoke(p2, 0x0).equals(Q0.p.invoke(0x0, 0x0)) == 0) goto :cond_e1;
      if (Q0.q.invoke(p2, 0x0).equals(Q0.q.invoke(0x0, 0x0)) == 0) goto :cond_e1;
      if ((String) Q0.r.invoke(p2, 0x0).equals(Q0.r.invoke(0x0, 0x0)) != 0) goto :cond_a4;
      goto :goto_e1;
      :cond_a4:
      if (p0.getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0x0).contains(Ldev/local/newsoul/Q0.L(0x0)) != 0) goto :cond_c5;
      if (p0.getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0x0).edit().putBoolean(Ldev/local/newsoul/Q0.L(0x0), 0x1).commit() == 0) goto :cond_c4;
      goto :goto_c5;
      :cond_c4:
      return 0x1;
      :cond_c5:
      :goto_c5:
      return 0x1;
      :cond_e1:
      :goto_e1:
      return 0x1;
    }

    public static Uri T(Bitmap p0) {
      new Landroid/content/ContentValues();
      new Ljava/lang/StringBuilder("NewSoul_");
      new Landroid/content/ContentValues().put("_display_name", new Ljava/lang/StringBuilder("NewSoul_").toString());
      new Landroid/content/ContentValues().put("mime_type", "image/png");
      new Landroid/content/ContentValues().put("relative_path", "Pictures/New Soul");
      new Landroid/content/ContentValues().put("is_pending", Ljava/lang/Integer.valueOf(0x1));
      if (p0.getContentResolver().insert(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, new Landroid/content/ContentValues()) != 0) goto :cond_47;
      return new Landroid/content/ContentValues();
      :cond_47:
      :try_start_48:
      :try_end_4e:
      if (v6 == 0) goto :cond_6f;
      :try_start_50:
      :try_end_58:
      if (p1 == 0) goto :cond_6f;
      :try_start_5a:
      v6.close();
      :try_end_5d:
      new Landroid/content/ContentValues().clear();
      new Landroid/content/ContentValues().put("is_pending", Ljava/lang/Integer.valueOf(v5));
      return new Landroid/content/ContentValues();
      :catchall_6b:
      goto :goto_86;
      :catchall_6d:
      goto :goto_77;
      :cond_6f:
      :try_start_6f:
      new Ljava/lang/IllegalStateException("Bitmap compression failed");
      throw new Ljava/lang/IllegalStateException("Bitmap compression failed");
      :try_end_77:
      :goto_77:
      if (v6 == 0) goto :cond_85;
      :try_start_79:
      v6.close();
      :try_end_7c:
      goto :goto_85;
      :catchall_7d:
      :try_start_7e:
      e.addSuppressed(new Landroid/content/ContentValues());
      goto :goto_85;
      :catchall_82:
      goto :goto_86;
      :cond_85:
      :goto_85:
      throw e;
      :try_end_86:
      :goto_86:
      if (v5 != 0) goto :cond_8b;
      :cond_8b:
      throw e;

      // .catchall {:try_start_48 .. :try_end_4e} :catchall_82
      // .catchall {:try_start_50 .. :try_end_58} :catchall_6d
      // .catchall {:try_start_5a .. :try_end_5d} :catchall_6b
      // .catchall {:try_start_6f .. :try_end_77} :catchall_6d
      // .catchall {:try_start_79 .. :try_end_7c} :catchall_7d
      // .catchall {:try_start_7e .. :try_end_86} :catchall_82
    }

    public static P0 U(Context p0) {
      :try_start_1:
      if (Build$VERSION.SDK_INT < 0x1c) goto :cond_18;
      goto :goto_1b;
      :cond_18:
      :goto_1b:
      new Ljava/lang/StringBuilder();
      :try_end_36:
      goto :goto_39;
      :catch_37:
      :goto_39:
      new Ljava/util/ArrayList();
      :goto_b5:
      if (v5 >= 0x13) goto :cond_e3;
      if (j.b.contains(j.a[v5][v0]) == 0) goto :cond_ca;
      goto :goto_e0;
      :cond_ca:
      new Ljava/lang/StringBuilder();
      :goto_e0:
      goto :goto_b5;
      :cond_e3:
      if (p0.a != 0) goto :cond_f2;
      if (p0.b != 0) goto :cond_f2;
      if (p0.c == 0) goto :cond_f0;
      goto :goto_f2;
      :cond_f0:
      :cond_f2:
      :goto_f2:
      if (v16 == 0) goto :cond_fa;
      goto :goto_fc;
      :cond_fa:
      :goto_fc:
      new Ljava/lang/StringBuilder("信息伪装 · ");
      if (p0.d != 0) goto :cond_136;
      if (p0.e == 0) goto :cond_134;
      goto :goto_136;
      :cond_134:
      :cond_136:
      :goto_136:
      new Ljava/lang/StringBuilder("天数伪装 · ");
      new Ljava/lang/StringBuilder("广告子项已注册 ");
      if (Q0.h == 0) goto :cond_236;
      :cond_236:
      Ldev/local/newsoul/N0.a(p0.getPackageManager().getPackageInfo(p0.getPackageName(), v0)).edit().putString(0x1c, p0.getPackageManager().getPackageInfo(p0.getPackageName(), v0)).apply();
      new Ldev/local/newsoul/P0(p0.getPackageManager().getPackageInfo(p0.getPackageName(), v0), v4, v13, (((((((((((((((((((((((((v15 + v13.Q(v12, "自定义骰子", "cn.soulapp.android.component.chat.utils.y4", "w0", Ljava/lang/Boolean.valueOf(t.a), new String[][0x0])) + v13.Q(v12, "自定义猜拳", "cn.soulapp.android.component.chat.utils.y4", "h1", Ljava/lang/Boolean.valueOf(t.b), new String[][0x0])) + v13.Q(v12, "消息防撤回", "cn.soulapp.imlib.ChatManager", "O", Ljava/lang/Boolean.valueOf(Q0.k), new String[][] {"java.util.List"})) + v13.Q(v12, "自己撤回", "cn.soulapp.android.component.chat.helper.WithDrawHandler", "d", Ljava/lang/Boolean.valueOf(g1.a), new String[][] {"cn.soulapp.imlib.msg.ImMessage"})) + v13.Q(v12, "隐藏输入状态", "cn.soulapp.imlib.ChatManager", "p0", Ljava/lang/Boolean.valueOf(Q0.b), new String[][] {"cn.soulapp.imlib.msg.ImMessage", v14})) + v13.Q(v12, "匹配音乐", "cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper", "k", Ljava/lang/Boolean.valueOf(Q0.c), new String[][0x0])) + v13.Q(v12, "聊天礼物限制", "cn.soulapp.android.lib.common.bean.ChatLimitModel", "isLimit", Ljava/lang/Boolean.valueOf(Q0.e), new String[][0x0])) + v13.Q(v12, "奇遇铃送礼限制", "cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig", "getShowPopup", Ljava/lang/Boolean.valueOf(Q0.f), new String[][0x0])) + v13.Q(v12, "聊天礼物会话额度", "cn.soulapp.imlib.Conversation", "getIntExt", Ljava/lang/Boolean.valueOf(Q0.g), new String[][] {p0})) + v13.Q(v12, "聊天复读/转发", "cn.soulapp.android.component.chat.utils.u0", "k", Ljava/lang/Boolean.valueOf(F.o), new String[][] {"cn.soulapp.android.component.chat.fragment.ConversationFragment", "android.view.View", "cn.soulapp.imlib.msg.ImMessage"})) + v13.Q(v12, "底栏长按设置", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", Ljava/lang/Boolean.valueOf(o.a), new String[][] {"android.os.Bundle"})) + v13.Q(v12, "消息转发选择", "cn.soulapp.android.component.chat.SelectConversationActivity", "F", Ljava/lang/Boolean.valueOf(F.p), new String[][] {"int"})) + v13.Q(v12, "语音库发送", "cn.soulapp.android.component.chat.utils.y4", "q2", Ljava/lang/Boolean.valueOf(d3.g), new String[][] {p0, "int", "cn.soulapp.imlib.msg.ImMessage"})) + v13.Q(v12, "聊天隐私媒体", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "init", Ljava/lang/Boolean.valueOf(Q0.P), new String[][] {"android.os.Bundle"})) + v13.Q(v12, "隐私媒体多次查看", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "G", Ljava/lang/Boolean.valueOf(Q0.Q), new String[][0x0])) + v13.Q(v12, "闪照多次查看", "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", Ljava/lang/Boolean.valueOf(Q0.R), new String[][0x0])) + v13.Q(v12, "图片下载", "cn.soulapp.android.square.utils.ImageDownloader", "f", Ljava/lang/Boolean.valueOf(Q0.L), new String[][] {p0, v14, v14, "cn.soulapp.android.square.utils.ImageMarkProcessor", "cn.soulapp.android.square.utils.b"})) + v13.Q(v12, "视频下载", "cn.soulapp.android.square.utils.h2", "b", Ljava/lang/Boolean.valueOf(Q0.M), new String[][] {p0, "android.content.Context", "long", v14})) + v13.Q(v12, "帖子分享限制", "cn.soulapp.android.square.SeedsShareDialogFragment", "r0", Ljava/lang/Boolean.valueOf(Q0.O), new String[][0x0])) + v13.Q(v12, "礼仪分本地限制", "cn.soulapp.android.component.chat.bean.SocialGraceScoreBean", "getCreateSessionLimit", Ljava/lang/Boolean.valueOf(Q0.U), new String[][0x0])) + v13.Q(v12, "礼仪分限制弹窗", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils", "b", Ljava/lang/Boolean.valueOf(Q0.V), new String[][] {"androidx.fragment.app.FragmentManager", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a"})) + v13.Q(v12, "帖子文案复制", "cn.soulapp.android.square.SeedsShareDialogFragment", "initViews", Ljava/lang/Boolean.valueOf(Q0.N), new String[][] {"android.view.View"})) + v13.Q(v12, "广场帖子筛选", "cn.soulapp.android.component.square.main.squarepost.SquareAdapter", "d", Ljava/lang/Boolean.valueOf(B2.i), new String[][] {"cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder", "java.lang.Object"})) + v13.Q(v12, "广场一键点赞", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", Ljava/lang/Boolean.valueOf(Q0.Y), new String[][] {"android.os.Bundle"})) + v13.Q(v12, "已加载帖子点赞请求", "gs0.g", "subscribe", Ljava/lang/Boolean.valueOf(Q0.B), new String[][] {"io.reactivex.functions.Consumer", "io.reactivex.functions.Consumer"})));
      return new Ldev/local/newsoul/P0(p0.getPackageManager().getPackageInfo(p0.getPackageName(), v0), v4, v13, (((((((((((((((((((((((((v15 + v13.Q(v12, "自定义骰子", "cn.soulapp.android.component.chat.utils.y4", "w0", Ljava/lang/Boolean.valueOf(t.a), new String[][0x0])) + v13.Q(v12, "自定义猜拳", "cn.soulapp.android.component.chat.utils.y4", "h1", Ljava/lang/Boolean.valueOf(t.b), new String[][0x0])) + v13.Q(v12, "消息防撤回", "cn.soulapp.imlib.ChatManager", "O", Ljava/lang/Boolean.valueOf(Q0.k), new String[][] {"java.util.List"})) + v13.Q(v12, "自己撤回", "cn.soulapp.android.component.chat.helper.WithDrawHandler", "d", Ljava/lang/Boolean.valueOf(g1.a), new String[][] {"cn.soulapp.imlib.msg.ImMessage"})) + v13.Q(v12, "隐藏输入状态", "cn.soulapp.imlib.ChatManager", "p0", Ljava/lang/Boolean.valueOf(Q0.b), new String[][] {"cn.soulapp.imlib.msg.ImMessage", v14})) + v13.Q(v12, "匹配音乐", "cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper", "k", Ljava/lang/Boolean.valueOf(Q0.c), new String[][0x0])) + v13.Q(v12, "聊天礼物限制", "cn.soulapp.android.lib.common.bean.ChatLimitModel", "isLimit", Ljava/lang/Boolean.valueOf(Q0.e), new String[][0x0])) + v13.Q(v12, "奇遇铃送礼限制", "cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig", "getShowPopup", Ljava/lang/Boolean.valueOf(Q0.f), new String[][0x0])) + v13.Q(v12, "聊天礼物会话额度", "cn.soulapp.imlib.Conversation", "getIntExt", Ljava/lang/Boolean.valueOf(Q0.g), new String[][] {p0})) + v13.Q(v12, "聊天复读/转发", "cn.soulapp.android.component.chat.utils.u0", "k", Ljava/lang/Boolean.valueOf(F.o), new String[][] {"cn.soulapp.android.component.chat.fragment.ConversationFragment", "android.view.View", "cn.soulapp.imlib.msg.ImMessage"})) + v13.Q(v12, "底栏长按设置", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", Ljava/lang/Boolean.valueOf(o.a), new String[][] {"android.os.Bundle"})) + v13.Q(v12, "消息转发选择", "cn.soulapp.android.component.chat.SelectConversationActivity", "F", Ljava/lang/Boolean.valueOf(F.p), new String[][] {"int"})) + v13.Q(v12, "语音库发送", "cn.soulapp.android.component.chat.utils.y4", "q2", Ljava/lang/Boolean.valueOf(d3.g), new String[][] {p0, "int", "cn.soulapp.imlib.msg.ImMessage"})) + v13.Q(v12, "聊天隐私媒体", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "init", Ljava/lang/Boolean.valueOf(Q0.P), new String[][] {"android.os.Bundle"})) + v13.Q(v12, "隐私媒体多次查看", "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity", "G", Ljava/lang/Boolean.valueOf(Q0.Q), new String[][0x0])) + v13.Q(v12, "闪照多次查看", "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", Ljava/lang/Boolean.valueOf(Q0.R), new String[][0x0])) + v13.Q(v12, "图片下载", "cn.soulapp.android.square.utils.ImageDownloader", "f", Ljava/lang/Boolean.valueOf(Q0.L), new String[][] {p0, v14, v14, "cn.soulapp.android.square.utils.ImageMarkProcessor", "cn.soulapp.android.square.utils.b"})) + v13.Q(v12, "视频下载", "cn.soulapp.android.square.utils.h2", "b", Ljava/lang/Boolean.valueOf(Q0.M), new String[][] {p0, "android.content.Context", "long", v14})) + v13.Q(v12, "帖子分享限制", "cn.soulapp.android.square.SeedsShareDialogFragment", "r0", Ljava/lang/Boolean.valueOf(Q0.O), new String[][0x0])) + v13.Q(v12, "礼仪分本地限制", "cn.soulapp.android.component.chat.bean.SocialGraceScoreBean", "getCreateSessionLimit", Ljava/lang/Boolean.valueOf(Q0.U), new String[][0x0])) + v13.Q(v12, "礼仪分限制弹窗", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils", "b", Ljava/lang/Boolean.valueOf(Q0.V), new String[][] {"androidx.fragment.app.FragmentManager", "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a"})) + v13.Q(v12, "帖子文案复制", "cn.soulapp.android.square.SeedsShareDialogFragment", "initViews", Ljava/lang/Boolean.valueOf(Q0.N), new String[][] {"android.view.View"})) + v13.Q(v12, "广场帖子筛选", "cn.soulapp.android.component.square.main.squarepost.SquareAdapter", "d", Ljava/lang/Boolean.valueOf(B2.i), new String[][] {"cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder", "java.lang.Object"})) + v13.Q(v12, "广场一键点赞", "cn.soulapp.android.component.startup.main.MainActivity", "onCreate", Ljava/lang/Boolean.valueOf(Q0.Y), new String[][] {"android.os.Bundle"})) + v13.Q(v12, "已加载帖子点赞请求", "gs0.g", "subscribe", Ljava/lang/Boolean.valueOf(Q0.B), new String[][] {"io.reactivex.functions.Consumer", "io.reactivex.functions.Consumer"})));

      // try :try_start_1..:try_end_36 catch (Ljava/lang/Exception) -> :catch_37
    }

    public static void V(String p0) {
      :try_start_1:
      if (p0.createPackageContext("dev.local.newsoul.public", 0x2).getResources().getIdentifier(p6, "drawable", p0.createPackageContext("dev.local.newsoul.public", 0x2).getPackageName()) != 0) goto :cond_1a;
      :catchall_18:
      goto :goto_22;
      :cond_1a:
      :try_end_22:
      :goto_22:
      if (v0 != 0) goto :cond_41;
      p3.setVisibility(0x0);
      p2.setImageDrawable(v0);
      p2.setVisibility(0x8);
      p3.setText("收款码加载失败，请重新打开此页面");
      p3.setTextColor(p1.j);
      Ldev/local/newsoul/Q0.Y(p1, p4, 0x0);
      Ldev/local/newsoul/Q0.Y(p1, p5, 0x0);
      return;
      :cond_41:
      p2.setImageDrawable(v0);
      p2.setContentDescription(p7);
      p2.setVisibility(0x0);
      p3.setVisibility(0x8);
      Ldev/local/newsoul/Q0.Y(p1, p4, 0x1);
      Ldev/local/newsoul/Q0.Y(p1, p5, 0x0);
      return;

      // .catchall {:try_start_1 .. :try_end_22} :catchall_18
    }

    public static void W(H2 p0) {
      new Ldev/local/newsoul/U0(p1, p3, 0x0);
      new Ldev/local/newsoul/V0(p0, new Ldev/local/newsoul/U0(p1, p3, 0x0));
      new Ldev/local/newsoul/U0(p1, p3, 0x1);
      new Ldev/local/newsoul/V0(p0, new Ldev/local/newsoul/U0(p1, p3, 0x1));
      return;
    }

    public static String X() {
      new Ljava/lang/StringBuilder();
      :goto_10:
      if (v3 >= v2) goto :cond_75;
      if (v1[v3].getClassName().startsWith("dev.local.newsoul.") != 0) goto :cond_72;
      if (v1[v3].getClassName().equals(Thread.class.getName()) == 0) goto :cond_2d;
      goto :goto_72;
      :cond_2d:
      if (v1[v3].getClassName().startsWith("cn.soulapp.") != 0) goto :cond_46;
      if (v1[v3].getClassName().startsWith("uj.") != 0) goto :cond_46;
      if (v1[v3].getClassName().contains("patch") != 0) goto :cond_46;
      goto :goto_72;
      :cond_46:
      if (v0.length() <= 0) goto :cond_51;
      :cond_51:
      if ((v4 + 0x1) != 0x6) goto :cond_72;
      goto :goto_75;
      :cond_72:
      :goto_72:
      goto :goto_10;
      :cond_75:
      :goto_75:
      if (v0.length() != 0) goto :cond_7e;
      goto :goto_82;
      :cond_7e:
      :goto_82:
      return "unavailable";
    }

    public static void Y(Ldev/local/newsoul/q2;Landroid/widget/TextView;Z p0) {
      p0.c.b(p1, 0x5, 0x41200000    # 10.0f);
      p1.setSelected(p2);
      return;
    }

    public static void Z(Throwable p0) {
      new Ljava/lang/StringBuilder("gift chat ");
      return;
    }

    public static boolean a(Object p0) {
      if (Q0.c0.invoke(p0, 0x0) != 0) goto :cond_13;
      if (Q0.d0.get(p0) != 0) goto :cond_13;
      goto :goto_14;
      :cond_13:
      :goto_14:
      return Q0.d0;
    }

    public static WebView a0(View p0) {
      if ((p0 instanceof WebView) == 0) goto :cond_7;
      return (p0 instanceof WebView);
      :cond_7:
      if ((p0 instanceof ViewGroup) == 0) goto :cond_23;
      :goto_c:
      if ((p0 instanceof ViewGroup) >= (ViewGroup) p0.getChildCount()) goto :cond_23;
      if (Ldev/local/newsoul/Q0.a0((ViewGroup) p0.getChildAt((p0 instanceof ViewGroup))) == 0) goto :cond_20;
      return (p0 instanceof ViewGroup);
      :cond_20:
      goto :goto_c;
      :cond_23:
      return ((p0 instanceof ViewGroup) + 0x1);
    }

    public static boolean b(View p0) {
      if (p0 == 0) goto :cond_31;
      if (p0.isAttachedToWindow() == 0) goto :cond_31;
      if (p0.isShown() == 0) goto :cond_31;
      if (compare(p0.getAlpha(), 0x0) != 0) goto :cond_19;
      goto :goto_31;
      :cond_19:
      new Landroid/graphics/Rect();
      if (p0.getGlobalVisibleRect(new Landroid/graphics/Rect()) == 0) goto :cond_31;
      if (new Landroid/graphics/Rect().width() <= 0) goto :cond_31;
      if (new Landroid/graphics/Rect().height() <= 0) goto :cond_31;
      :cond_31:
      :goto_31:
      return 0x0;
    }

    public static boolean c(FFFZ p0) {
      if (p3 != 0) goto :cond_1e;
      if (compare(p1, -p2) > 0) goto :cond_9;
      goto :goto_1e;
      :cond_9:
      if (compare(p1, p2) < 0) goto :cond_16;
      if (compare(p0, 0x3ca3d70a    # 0.02f) < 0) goto :cond_16;
      return 0x0;
      :cond_16:
      if (compare(p0, 0x3e8f5c29    # 0.28f) < 0) goto :cond_1e;
      :cond_1e:
      :goto_1e:
      return 0x0;
    }

    public static void d(View p0) {
      if (p0 == 0) goto :cond_bc;
      :try_start_4:
      if (p0.isAttachedToWindow() == 0) goto :cond_bc;
      if ((p0.getParent() instanceof RelativeLayout) != 0) goto :cond_14;
      goto :goto_bc;
      :cond_14:
      :goto_18:
      if (((p0.getParent() instanceof RelativeLayout) instanceof ContextWrapper) == 0) goto :cond_2a;
      if (((p0.getParent() instanceof RelativeLayout) instanceof Activity) != 0) goto :cond_2a;
      goto :goto_18;
      :catchall_27:
      goto :goto_b7;
      :cond_2a:
      if (((ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext() instanceof Activity) != 0) goto :cond_2f;
      return;
      :cond_2f:
      if ("cn.soulapp.android.component.startup.main.MainActivity".equals((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getClass().getName()) == 0) goto :cond_b6;
      if (e.getId() == (Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getIdentifier("message_button", "id", (Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getPackageName())) goto :cond_58;
      goto :goto_b6;
      :cond_58:
      if (Ldev/local/newsoul/Q0.i(e) == 0) goto :cond_62;
      Ldev/local/newsoul/Q0.i(e).i();
      return;
      :cond_62:
      new Ldev/local/newsoul/I2((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext(), e);
      new Ldev/local/newsoul/I2((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext(), e).c.setTag("new.soul.square.like");
      new Landroid/widget/RelativeLayout$LayoutParams(Ljava/lang/Math.round(((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density * ((float) 0x44))), Ljava/lang/Math.round((((float) 0x44) * (Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density)));
      new Landroid/widget/RelativeLayout$LayoutParams(Ljava/lang/Math.round(((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density * ((float) 0x44))), Ljava/lang/Math.round((((float) 0x44) * (Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density))).addRule(0x2, e.getId());
      new Landroid/widget/RelativeLayout$LayoutParams(Ljava/lang/Math.round(((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density * ((float) 0x44))), Ljava/lang/Math.round((((float) 0x44) * (Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density))).addRule(0x7, e.getId());
      (RelativeLayout) e.getParent().addView(new Ldev/local/newsoul/I2((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext(), e).c, new Landroid/widget/RelativeLayout$LayoutParams(Ljava/lang/Math.round(((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density * ((float) 0x44))), Ljava/lang/Math.round((((float) 0x44) * (Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext().getResources().getDisplayMetrics().density))));
      new Ldev/local/newsoul/I2((Activity) (ContextWrapper) (p0.getParent() instanceof RelativeLayout).getBaseContext(), e).i();
      :try_end_b5:
      goto :goto_bc;
      :cond_b6:
      :goto_b6:
      return;
      :goto_b7:
      :cond_bc:
      :goto_bc:
      return;

      // .catchall {:try_start_4 .. :try_end_b5} :catchall_27
    }

    public static boolean e(Object p0) {
      if (p0 != 0) goto :cond_4;
      return 0x0;
      :cond_4:
      if ((String) Q0.j.invoke(p0, 0x0) == 0) goto :cond_24;
      if ((String) Q0.j.invoke(p0, 0x0).isEmpty() != 0) goto :cond_24;
      goto :goto_25;
      :cond_24:
      :goto_25:
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() < 0) goto :cond_55;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() >= 0x13) goto :cond_55;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() == 0x6) goto :cond_55;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() == 0) goto :cond_44;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() == 0x1) goto :cond_44;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() == 0x5) goto :cond_44;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() == 0xa) goto :cond_44;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() != 0xb) goto :cond_3e;
      goto :goto_44;
      :cond_3e:
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() >= 0xe) goto :cond_44;
      if (0x1 == 0) goto :cond_55;
      :cond_44:
      :goto_44:
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() == 0xa) goto :cond_4c;
      if ((Integer) Q0.i.invoke(p0, 0x0).intValue() != 0xb) goto :cond_49;
      goto :goto_4c;
      :cond_49:
      goto :goto_4e;
      :cond_4c:
      :goto_4c:
      :goto_4e:
      if (Ldev/local/newsoul/Q0.q("chat_remove_gift_gate") == 0) goto :cond_55;
      :cond_55:
      return 0x0;
    }

    public static void f(Activity p0) {
      if (Q0.a == 0) goto :cond_9;
      return;
      :cond_9:
      Q0.a = 0x1;
      :try_start_c:
      :try_end_10:
      :try_start_16:
      :try_end_24:
      if (v7 != 0) goto :cond_35;
      :try_start_28:
      if (v3.a.startsWith("6.37.0") != 0) goto :cond_31;
      goto :goto_3b;
      :cond_31:
      goto :goto_3b;
      :catchall_33:
      goto :goto_a4;
      :cond_35:
      if (v3.a.startsWith("6.37.0").equals(v3.a) != 0) goto :cond_31;
      :goto_3b:
      new Ljava/lang/StringBuilder("hook scan: host=");
      if (v6 == 0) goto :cond_6e;
      if (v6.equals(v3.a) == 0) goto :cond_6e;
      if (v5 > 0) goto :cond_6e;
      if (v4 <= 0) goto :cond_95;
      :cond_6e:
      :goto_74:
      if (v6.equals(v3.a).hasNext() == 0) goto :cond_95;
      new Ljava/lang/StringBuilder();
      goto :goto_74;
      :cond_95:
      if (v8 == 0) goto :cond_a9;
      new Ldev/local/newsoul/O0(e.getApplication(), v6);
      e.getApplication().registerActivityLifecycleCallbacks(new Ldev/local/newsoul/O0(e.getApplication(), v6));
      :try_end_a3:
      goto :goto_a9;
      :goto_a4:
      :cond_a9:
      :goto_a9:
      return;

      // .catchall {:try_start_c .. :try_end_10} :catchall_33
      // .catchall {:try_start_16 .. :try_end_24} :catchall_33
      // .catchall {:try_start_28 .. :try_end_a3} :catchall_33
    }

    public static TextView g(String p0) {
      new Landroid/widget/TextView(p0.g);
      new Landroid/widget/TextView(p0.g).setText(p1);
      new Landroid/widget/TextView(p0.g).setTextSize(((float) 0xf));
      new Landroid/widget/TextView(p0.g).setTextColor(p0.i);
      new Landroid/widget/TextView(p0.g).setGravity(0x11);
      new Landroid/widget/TextView(p0.g).setMinHeight(Ldev/local/newsoul/Q0.p(p0.g, 0x30));
      new Landroid/widget/TextView(p0.g).setFocusable(0x1);
      new Landroid/widget/TextView(p0.g).setContentDescription(p1);
      Ldev/local/newsoul/Q0.Y(p0, new Landroid/widget/TextView(p0.g), 0x0);
      return new Landroid/widget/TextView(p0.g);
    }

    public static void h(ArrayList p0) {
      new Landroid/graphics/Rect();
      if (p0.isShown() == 0) goto :cond_49;
      if (p0.getGlobalVisibleRect(new Landroid/graphics/Rect()) != 0) goto :cond_12;
      goto :goto_49;
      :cond_12:
      if (Q0.C.isInstance(p0) == 0) goto :cond_32;
      :try_start_1a:
      if (Q0.D.isInstance(Q0.G.invoke(p0, 0x0)) == 0) goto :cond_32;
      new Ldev/local/newsoul/W0(p0, Q0.G.invoke(p0, 0x0));
      :try_end_31:
      return;
      :catch_32:
      :cond_32:
      if ((p0 instanceof ViewGroup) == 0) goto :cond_49;
      :goto_39:
      if ((p0 instanceof ViewGroup) >= p0.getChildCount()) goto :cond_49;
      Ldev/local/newsoul/Q0.h(p0.getChildAt((p0 instanceof ViewGroup)), p1);
      goto :goto_39;
      :cond_49:
      :goto_49:
      return;

      // try :try_start_1a..:try_end_31 catch (Ljava/lang/ReflectiveOperationException) -> :catch_32
    }

    public static I2 i(View p0) {
      if (p0 == 0) goto :cond_26;
      if ((p0.getParent() instanceof ViewGroup) != 0) goto :cond_c;
      goto :goto_26;
      :cond_c:
      if (((ViewGroup) p0.getParent().findViewWithTag("new.soul.square.like") instanceof E2) != 0) goto :cond_1d;
      return 0x0;
      :cond_1d:
      if ((E2) (ViewGroup) p0.getParent().findViewWithTag("new.soul.square.like").a.b != p0) goto :cond_26;
      :cond_26:
      :goto_26:
      return 0x0;
    }

    public static void j(Method p0) {
      :try_start_0:
      :try_end_3:
      goto :goto_1a;
      :catchall_4:
      new Ljava/lang/StringBuilder("deopt ");
      Ldev/local/newsoul/Q0.Z(new Ljava/lang/StringBuilder("deopt ").toString(), e);
      :goto_1a:
      return;

      // .catchall {:try_start_0 .. :try_end_3} :catchall_4
    }

    public static void k(String p0) {
      :try_start_4:
      new Ljava/lang/StringBuilder(v1);
      Ldev/local/newsoul/V.j(p2.concat(v0), new Ljava/lang/StringBuilder(v1).toString());
      :try_end_37:
      goto :goto_40;
      :catchall_38:
      Ldev/local/newsoul/V.i(p2.concat(v0), e);
      :goto_40:
      return;

      // .catchall {:try_start_4 .. :try_end_37} :catchall_38
    }

    public static void l(String p0) {
      :try_start_0:
      :goto_a:
      if (v1 >= v0) goto :cond_40;
      if (p3 == 0) goto :cond_1d;
      if (p3.equals(p1[v1].getName()) == 0) goto :cond_34;
      goto :goto_1d;
      :catchall_1b:
      goto :goto_37;
      :cond_1d:
      :goto_1d:
      if (Ljava/lang/reflect/Modifier.isAbstract(p1[v1].getModifiers()) != 0) goto :cond_34;
      if (Ljava/lang/reflect/Modifier.isNative(p1[v1].getModifiers()) != 0) goto :cond_34;
      Ldev/local/newsoul/Q0.j(e, p1[v1]);
      :try_end_34:
      :cond_34:
      goto :goto_a;
      :goto_37:
      Ldev/local/newsoul/Q0.Z("callers ".concat(p2), e);
      :cond_40:
      return;

      // .catchall {:try_start_0 .. :try_end_34} :catchall_1b
    }

    public static void m(Class p0) {
      :goto_6:
      if (v1 >= v0) goto :cond_22;
      if (Ljava/lang/reflect/Modifier.isAbstract(p0[v1].getModifiers()) != 0) goto :cond_1f;
      if (Ljava/lang/reflect/Modifier.isNative(p0[v1].getModifiers()) != 0) goto :cond_1f;
      :cond_1f:
      goto :goto_6;
      :cond_22:
      return;
    }

    public static void n(String p0) {
      if (p2 == 0) goto :cond_31;
      if (Ldev/local/newsoul/w2.o(p1).getBoolean("block_splash", 0x1) == 0) goto :cond_31;
      new Ljava/lang/StringBuilder("new soul: ");
      p0.log(0x4, "SoulHook102", new Ljava/lang/StringBuilder("new soul: ").toString());
      :cond_31:
      return;
    }

    public static int o(Landroid/app/Activity;I p0) {
      return v0;
    }

    public static int p(Landroid/content/Context;I p0) {
      return v0;
    }

    public static boolean q(String p0) {
      :try_start_0:
      :try_end_12:
      return v0;
      :catchall_13:
      return v0;

      // .catchall {:try_start_0 .. :try_end_12} :catchall_13
    }

    public static void r(String p0) {
      if (__result__ != 0) goto :cond_7;
      return;
      :cond_7:
      if ((z) A.b.get() != 0) goto :cond_14;
      goto :goto_16;
      :cond_14:
      :goto_16:
      new Ljava/lang/StringBuilder("trace=");
      Ldev/local/newsoul/V.d(p0, new Ljava/lang/StringBuilder("trace=").toString());
      return;
    }

    public static void s(Throwable p0) {
      if ((z) A.b.get() != 0) goto :cond_d;
      goto :goto_f;
      :cond_d:
      :goto_f:
      new Ljava/lang/StringBuilder("trace=");
      new Ljava/lang/StringBuilder();
      Ldev/local/newsoul/V.d(p0, new Ljava/lang/StringBuilder().toString());
      return;
    }

    public static W0 t(View p0) {
      if (Q0.B == 0) goto :cond_48;
      if (p0 == 0) goto :cond_48;
      if (p0.isShown() != 0) goto :cond_e;
      goto :goto_48;
      :cond_e:
      new Ljava/util/ArrayList();
      Ldev/local/newsoul/Q0.h(p0, new Ljava/util/ArrayList());
      :cond_1c:
      :goto_1c:
      if (p0.hasNext() == 0) goto :cond_48;
      new Landroid/graphics/Rect();
      if ((W0) p0.next().a.getGlobalVisibleRect(new Landroid/graphics/Rect()) != 0) goto :cond_36;
      goto :goto_1c;
      :cond_36:
      if (compare((((long) new Landroid/graphics/Rect().width()) * ((long) new Landroid/graphics/Rect().height())), v2) <= 0) goto :cond_1c;
      goto :goto_1c;
      :cond_48:
      :goto_48:
      return (W0) p0.next();
    }

    public static boolean u(Object p0) {
      if ((Integer) Q0.o.invoke(Q0.n.invoke(p0, 0x0), 0x0).intValue() == 0x9) goto :cond_21;
      if (Q0.t.invoke(Q0.n.invoke(p0, 0x0), 0x0) == 0) goto :cond_21;
      goto :goto_22;
      :cond_21:
      :goto_22:
      return Q0.t;
    }

    public static boolean v(Object p0) {
      if (p0 == 0) goto :cond_50;
      if ((Boolean) Q0.s.invoke(p0, 0x0).booleanValue() != 0) goto :cond_50;
      if (Q0.n.invoke(p0, 0x0) != 0) goto :cond_1b;
      goto :goto_50;
      :cond_1b:
      if ((String) Q0.m.invoke(0x0, 0x0) == 0) goto :cond_50;
      if ((String) Q0.m.invoke(0x0, 0x0).isEmpty() != 0) goto :cond_50;
      if ((String) Q0.p.invoke(p0, 0x0) == 0) goto :cond_50;
      if ((String) Q0.p.invoke(p0, 0x0).isEmpty() != 0) goto :cond_50;
      if ((String) Q0.p.invoke(p0, 0x0).equals((String) Q0.m.invoke(0x0, 0x0)) != 0) goto :cond_50;
      if ((String) Q0.m.invoke(0x0, 0x0).equals((String) Q0.q.invoke(p0, 0x0)) == 0) goto :cond_50;
      :cond_50:
      :goto_50:
      return 0x0;
    }

    public static void w(String p0) {
      Q0.S.log(0x4, "SoulHook102", p0);
      return;
    }

    public static void x(ClassLoader p0) {
      :try_start_7:
      Q0.d = p1.loadClass("lf.b").getDeclaredMethod("b", v4);
      new Ldev/local/newsoul/e(p1.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod(v0, v4), p1.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("getChatMessage", v4), p1.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod(v0, v4));
      Q0.b = v3;
      :try_end_70:
      goto :goto_77;
      :catchall_71:
      p0.log(v2, v1, "typing control unavailable", e);
      :goto_77:
      :try_start_77:
      :goto_94:
      if (v5 >= 0x2) goto :cond_cd;
      new Ljava/lang/StringBuilder();
      new Ldev/local/newsoul/a(0x3);
      goto :goto_94;
      :catchall_cb:
      goto :goto_de;
      :cond_cd:
      Q0.c = v3;
      :try_end_dd:
      goto :goto_e3;
      :goto_de:
      p0.log(v2, v1, "match music control unavailable", e);
      :goto_e3:
      return;

      // .catchall {:try_start_7 .. :try_end_70} :catchall_71
      // .catchall {:try_start_77 .. :try_end_dd} :catchall_cb
    }

    public static void y(Class p0) {
      :try_start_0:
      Q0.C = p0.loadClass("androidx.recyclerview.widget.RecyclerView");
      Q0.D = p0.loadClass("cn.soulapp.android.component.square.main.squarepost.SquareAdapter");
      Q0.E = p0.loadClass("io.reactivex.functions.Consumer");
      Q0.F = p0.loadClass("cn.soulapp.android.component.square.post.b1").getConstructor(new Class[][] {p1});
      Q0.I = p0.loadClass("cn.soulapp.android.component.square.post.b1").getMethod("h", new Class[][] {Integer.TYPE});
      Q0.G = Q0.C.getMethod("getAdapter", 0x0);
      Q0.H = Q0.D.getMethod("getData", 0x0);
      Q0.K = Q0.D.getMethod("notifyDataSetChanged", 0x0);
      Q0.J = Q0.I.getReturnType().getMethod("subscribe", new Class[][] {Q0.E, Q0.E});
      Q0.B = 0x1;
      :try_end_6c:
      goto :goto_75;
      :catchall_6d:
      :goto_75:
      return;

      // .catchall {:try_start_0 .. :try_end_6c} :catchall_6d
    }

    public static void z(ClassLoader p0) {
      :try_start_6:
      Q0.i = p1.loadClass(v1).getMethod("getType", v2);
      Q0.j = p1.loadClass(v1).getMethod("getItemIdentity", v2);
      new Ldev/local/newsoul/a(0x5);
      Q0.e = v3;
      Ldev/local/newsoul/Q0.j(p0, p1.loadClass(v1).getMethod("isLimit", v2));
      :try_end_3e:
      goto :goto_45;
      :catchall_3f:
      Ldev/local/newsoul/Q0.Z("model", e);
      :goto_45:
      :try_start_45:
      new Ldev/local/newsoul/a(0x6);
      Q0.g = v3;
      Ldev/local/newsoul/Q0.j(p0, p1.loadClass(v0).getMethod("getIntExt", new Class[][] {String.class}));
      :try_end_73:
      goto :goto_7a;
      :catchall_74:
      Ldev/local/newsoul/Q0.Z("session", e);
      :goto_7a:
      if (Q0.e == 0) goto :cond_104;
      :try_start_7f:
      :goto_a4:
      if (v6 >= Q0.e) goto :cond_d5;
      if (Ljava/lang/reflect/Modifier.isStatic(v1[v6].getModifiers()) == 0) goto :cond_d2;
      if (v1[v6].getReturnType() != Void.TYPE) goto :cond_d2;
      if (Ljava/util/Arrays.equals(v1[v6].getParameterTypes(), v0) == 0) goto :cond_d2;
      if (v7 != 0) goto :cond_c8;
      goto :goto_d2;
      :cond_c8:
      new Ljava/lang/IllegalStateException("ambiguous gift dialog entry");
      throw new Ljava/lang/IllegalStateException("ambiguous gift dialog entry");
      :catchall_d0:
      goto :goto_ff;
      :cond_d2:
      :goto_d2:
      goto :goto_a4;
      :cond_d5:
      if (v1[v6] == 0) goto :cond_f7;
      new Ldev/local/newsoul/a(0x8);
      Q0.h = v3;
      Ldev/local/newsoul/Q0.j(p0, v1[v6]);
      goto :goto_104;
      :cond_f7:
      new Ljava/lang/NoSuchMethodException("gift dialog signature");
      throw new Ljava/lang/NoSuchMethodException("gift dialog signature");
      :try_end_ff:
      :goto_ff:
      Ldev/local/newsoul/Q0.Z("dialog", p0.hook(v1[v6]).setId("new-soul/gift-chat-dialog").setExceptionMode(XposedInterface$ExceptionMode.PROTECTIVE));
      :cond_104:
      :goto_104:
      :try_start_104:
      if (p1.loadClass("cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig").getDeclaredMethod("getShowPopup", v2).getReturnType() == Boolean.class) goto :cond_12b;
      if (p1.loadClass("cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig").getDeclaredMethod("getShowPopup", v2).getReturnType() != Boolean.TYPE) goto :cond_121;
      goto :goto_12b;
      :cond_121:
      new Ljava/lang/NoSuchMethodException("unexpected getShowPopup return type");
      throw new Ljava/lang/NoSuchMethodException("unexpected getShowPopup return type");
      :catchall_129:
      goto :goto_183;
      :cond_12b:
      :goto_12b:
      new Ldev/local/newsoul/a(0x7);
      Q0.f = v3;
      Ldev/local/newsoul/Q0.j(p0, p1.loadClass("cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig").getDeclaredMethod("getShowPopup", v2));
      :try_end_149:
      :try_start_149:
      :goto_155:
      if (p1.loadClass("cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig").getDeclaredMethod("getShowPopup", v2).getReturnType() >= v3) goto :cond_188;
      :goto_15f:
      if (v1[v6] >= v1[v6]) goto :cond_17a;
      if (Ljava/util/Arrays.asList(Boolean.TYPE[v1[v6]].getParameterTypes()).contains(e) == 0) goto :cond_177;
      Ldev/local/newsoul/Q0.j(p0, Boolean.TYPE[v1[v6]]);
      :try_end_174:
      goto :goto_177;
      :catchall_175:
      goto :goto_17d;
      :cond_177:
      :goto_177:
      goto :goto_15f;
      :cond_17a:
      goto :goto_155;
      :goto_17d:
      :try_start_17d:
      Ldev/local/newsoul/Q0.Z("bell callers", e);
      :try_end_182:
      goto :goto_188;
      :goto_183:
      Ldev/local/newsoul/Q0.Z("bell popup config", e);
      :cond_188:
      :goto_188:
      Ldev/local/newsoul/Q0.l(p0, p1, "cn.soulapp.android.component.chat.style.BaseConversationStyleProcess", "checkLimitOpt");
      Ldev/local/newsoul/Q0.l(p0, p1, "cn.soulapp.android.component.chat.helper.MsgLimitManager", v2);
      Ldev/local/newsoul/Q0.l(p0, p1, "cn.soulapp.android.component.chat.fragment.ConversationFragment", "v4");
      new Ljava/lang/StringBuilder("gift chat hooks: model=");
      return;

      // .catchall {:try_start_6 .. :try_end_3e} :catchall_3f
      // .catchall {:try_start_45 .. :try_end_73} :catchall_74
      // .catchall {:try_start_7f .. :try_end_ff} :catchall_d0
      // .catchall {:try_start_104 .. :try_end_149} :catchall_129
      // .catchall {:try_start_149 .. :try_end_174} :catchall_175
      // .catchall {:try_start_17d .. :try_end_182} :catchall_129
    }

}