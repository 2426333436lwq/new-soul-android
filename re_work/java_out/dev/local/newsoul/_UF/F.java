package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/F ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class F extends Ljava/lang/Object {

    public static boolean o;
    public static boolean p;
    public final WeakHashMap a;
    public Method b;
    public Method c;
    public Method d;
    public Method e;
    public Method f;
    public Method g;
    public Method h;
    public Method i;
    public Method j;
    public Method k;
    public Constructor l;
    public d1 m;
    public d3 n;

    public void super() {
      super();
      new Ljava/util/WeakHashMap();
      this.a = new Ljava/util/WeakHashMap();
      return;
    }

    public static Object b(String p0) {
      return p0.getClass();
    }

    public static Object c(ILjava/lang/Object; p0) {
      if (p1 == 0) goto :cond_105;
      if (p0 != 0x1) goto :cond_4c;
      if (p1.getClass().getName().equals("cn.soulapp.imlib.msg.chat.TextMsg") == 0) goto :cond_4c;
      if ((String) Ldev/local/newsoul/F.g(p1, "text") == 0) goto :cond_44;
      if ((String) Ldev/local/newsoul/F.g(p1, "text").trim().isEmpty() != 0) goto :cond_44;
      if ((String) Ldev/local/newsoul/F.g(p1, "text").length() > 0x1f4) goto :cond_44;
      return p1.getClass();
      :cond_44:
      new Ljava/lang/IllegalArgumentException("Invalid text");
      throw new Ljava/lang/IllegalArgumentException("Invalid text");
      :cond_4c:
      if ((String) Ldev/local/newsoul/F.g(p1, "text") != 0x2) goto :cond_60;
      if (p1.getClass().getName().equals("cn.soulapp.imlib.msg.chat.ImgMsg") == 0) goto :cond_60;
      return p1.getClass();
      :cond_60:
      if ((String) Ldev/local/newsoul/F.g(p1, "text") != 0x3) goto :cond_b5;
      if (p1.getClass().getName().equals("cn.soulapp.imlib.msg.chat.ImgMsgs") == 0) goto :cond_b5;
      if ((List) Ldev/local/newsoul/F.g((String) Ldev/local/newsoul/F.g(p1, "text").length(), "imgMsgList") == 0) goto :cond_ad;
      if ((List) Ldev/local/newsoul/F.g((String) Ldev/local/newsoul/F.g(p1, "text").length(), "imgMsgList").isEmpty() != 0) goto :cond_ad;
      new Ljava/util/ArrayList();
      :goto_88:
      if ((List) Ldev/local/newsoul/F.g((String) Ldev/local/newsoul/F.g(p1, "text").length(), "imgMsgList").hasNext() == 0) goto :cond_9a;
      goto :goto_88;
      :cond_9a:
      return p1.getClass();
      :cond_ad:
      new Ljava/lang/IllegalArgumentException("Empty images");
      throw new Ljava/lang/IllegalArgumentException("Empty images");
      :cond_b5:
      if ((List) Ldev/local/newsoul/F.g((String) Ldev/local/newsoul/F.g(p1, "text").length(), "imgMsgList") != 0x5) goto :cond_fd;
      if (p1.getClass().getName().equals("cn.soulapp.imlib.msg.chat.AudioMsg") == 0) goto :cond_fd;
      if (Ldev/local/newsoul/Q0.N((String) Ldev/local/newsoul/F.g((List) Ldev/local/newsoul/F.g((String) Ldev/local/newsoul/F.g(p1, "text").length(), "imgMsgList").isEmpty(), "url")) == 0) goto :cond_f5;
      if ((Integer) Ldev/local/newsoul/F.g((List) Ldev/local/newsoul/F.g((String) Ldev/local/newsoul/F.g(p1, "text").length(), "imgMsgList").isEmpty(), "duration").intValue() <= 0) goto :cond_f5;
      return p1.getClass().getConstructor(new Class[][] {String.class, String.class, Integer.TYPE, String.class});
      :cond_f5:
      new Ljava/lang/IllegalArgumentException("Invalid audio");
      throw new Ljava/lang/IllegalArgumentException("Invalid audio");
      :cond_fd:
      new Ljava/lang/IllegalArgumentException("Unsupported message body");
      throw new Ljava/lang/IllegalArgumentException("Unsupported message body");
      :cond_105:
      new Ljava/lang/IllegalArgumentException("Missing body");
      throw new Ljava/lang/IllegalArgumentException("Missing body");
    }

    public static Object d(Object p0) {
      return (Integer) Ldev/local/newsoul/F.b(p0, "getMsgType").intValue();
    }

    public static Object e(Object p0) {
      if (p0 == 0) goto :cond_6e;
      if (p0.getClass().getName().equals("cn.soulapp.imlib.msg.chat.ImgMsg") == 0) goto :cond_6e;
      if (Ldev/local/newsoul/Q0.N((String) Ldev/local/newsoul/F.g(p0, "imageUrl")) == 0) goto :cond_66;
      if ((Integer) Ldev/local/newsoul/F.g(p0, "imageW").intValue() <= 0) goto :cond_66;
      if ((Integer) Ldev/local/newsoul/F.g(p0, "imageH").intValue() <= 0) goto :cond_66;
      :goto_4e:
      if ("imageW" >= 0x3) goto :cond_65;
      p0.getClass().getField("imageUrl"["imageW"]).set((Integer) Ldev/local/newsoul/F.g(p0, "imageH").intValue(), Ldev/local/newsoul/F.g(p0, "imageUrl"["imageW"]));
      goto :goto_4e;
      :cond_65:
      return "imageUrl";
      :cond_66:
      new Ljava/lang/IllegalArgumentException("Image not uploaded");
      throw new Ljava/lang/IllegalArgumentException("Image not uploaded");
      :cond_6e:
      new Ljava/lang/IllegalArgumentException("Invalid image");
      throw new Ljava/lang/IllegalArgumentException("Invalid image");
    }

    public static boolean f(String p0) {
      if (p0 != 0) goto :cond_b;
      Ldev/local/newsoul/Q0.r("repeat.eligible", "reason=null_message");
      return "repeat.eligible";
      :cond_b:
      if (p1 == 0) goto :cond_117;
      if (p1.isEmpty() != 0) goto :cond_117;
      if (p2 == 0) goto :cond_117;
      if (p2.isEmpty() != 0) goto :cond_117;
      if (p1.equals((String) Ldev/local/newsoul/F.b(p0, "getFrom")) == 0) goto :cond_38;
      if (p2.equals((String) Ldev/local/newsoul/F.b(p0, "getTo")) != 0) goto :cond_44;
      :cond_38:
      if (p2.equals((String) Ldev/local/newsoul/F.b(p0, "getFrom")) == 0) goto :cond_117;
      if (p1.equals((String) Ldev/local/newsoul/F.b(p0, "getTo")) == 0) goto :cond_117;
      :cond_44:
      if (Ldev/local/newsoul/F.b(p0, "getChatMessage") != 0) goto :cond_52;
      Ldev/local/newsoul/Q0.r("repeat.eligible", "reason=null_chat");
      return "repeat.eligible";
      :cond_52:
      if ((Integer) Ldev/local/newsoul/F.b(p0, "getMsgType").intValue() != 0x1) goto :cond_c7;
      if ((Boolean) Ldev/local/newsoul/F.b(p0, "isAnonymityChat").booleanValue() != 0) goto :cond_c7;
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p0, "getChatMessage"), "getSnapChat").intValue() != 0) goto :cond_c7;
      if ((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"}) == 0) goto :cond_ba;
      if ((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"}).isEmpty() != 0) goto :cond_ba;
      if ("0".equals((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"})) != 0) goto :cond_ba;
      if ("false".equals((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"})) == 0) goto :cond_c7;
      :cond_ba:
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p0, "getChatMessage"), "getMsgType").intValue() == 0x1) goto :cond_c5;
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p0, "getChatMessage"), "getMsgType").intValue() == 0x2) goto :cond_c5;
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p0, "getChatMessage"), "getMsgType").intValue() == 0x3) goto :cond_c5;
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p0, "getChatMessage"), "getMsgType").intValue() != 0x5) goto :cond_c7;
      :cond_c5:
      goto :goto_c8;
      :cond_c7:
      :goto_c8:
      if ((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"}) == 0) goto :cond_dd;
      if ((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"}).isEmpty() != 0) goto :cond_dd;
      if ("0".equals((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"})) != 0) goto :cond_dd;
      if ("false".equals((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"})) != 0) goto :cond_dd;
      :cond_dd:
      new Ljava/lang/StringBuilder("chat_match=true; envelope=");
      Ldev/local/newsoul/Q0.r("repeat.eligible", new Ljava/lang/StringBuilder("chat_match=true; envelope=").toString());
      return "repeat.eligible";
      :cond_117:
      if ("false".equals((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"})) == 0) goto :cond_121;
      if ("false".equals((String) Ldev/local/newsoul/F.b(p0, "getChatMessage").getClass().getMethod("getStringTransExt", new Class[][] {String.class}).invoke(Ldev/local/newsoul/F.b(p0, "getChatMessage"), new Object[][] {"isSnap"})).isEmpty() != 0) goto :cond_121;
      goto :goto_122;
      :cond_121:
      :goto_122:
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p0, "getChatMessage"), "getMsgType").intValue() == 0) goto :cond_12b;
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p0, "getChatMessage"), "getMsgType").intValue().isEmpty() != 0) goto :cond_12b;
      goto :goto_12c;
      :cond_12b:
      :goto_12c:
      new Ljava/lang/StringBuilder("reason=chat_mismatch; account_present=");
      Ldev/local/newsoul/Q0.r("repeat.eligible", new Ljava/lang/StringBuilder("reason=chat_mismatch; account_present=").toString());
      return "repeat.eligible";
    }

    public static Object g(String p0) {
      p0.getClass().getDeclaredField(p1).setAccessible(0x1);
      return 0x1;
    }

    public final Object a(Object p0) {
      if ((p1 instanceof List) != 0) goto :cond_14;
      Ldev/local/newsoul/Q0.r("repeat.menu.skip", "reason=not_list");
      return p0;
      :cond_14:
      new Ljava/lang/StringBuilder("count=");
      Ldev/local/newsoul/Q0.r("repeat.menu.original", new Ljava/lang/StringBuilder("count=").toString());
      if ((List) p1.isEmpty() == 0) goto :cond_3a;
      Ldev/local/newsoul/Q0.r("repeat.menu.skip", "reason=empty_menu");
      return p0;
      :cond_3a:
      :cond_3e:
      if ((List) p1.isEmpty().hasNext() == 0) goto :cond_6a;
      if ((Integer) p0.c.invoke((List) p1.isEmpty().next(), 0x0).intValue() == 0x4e5301) goto :cond_64;
      if ((Integer) p0.c.invoke((List) p1.isEmpty().next(), 0x0).intValue() == 0x4e5302) goto :cond_64;
      if ((Integer) p0.c.invoke((List) p1.isEmpty().next(), 0x0).intValue() != 0x4e5303) goto :cond_3e;
      :cond_64:
      Ldev/local/newsoul/Q0.r("repeat.menu.skip", "reason=already_added");
      return p0;
      :cond_6a:
      if (Ldev/local/newsoul/F.f(p3, (String) p0.b.invoke(0x0, 0x0), (String) Ldev/local/newsoul/F.g(p2, "L")) != 0) goto :cond_81;
      return p0;
      :cond_81:
      if ((Context) Ldev/local/newsoul/F.b(p2, "getContext").getResources().getIdentifier("c_ct_resend_menu", "drawable", (Context) Ldev/local/newsoul/F.b(p2, "getContext").getPackageName()) != 0) goto :cond_aa;
      Ldev/local/newsoul/Q0.r("repeat.menu.skip", "reason=missing_repeat_icon");
      return p0;
      :cond_aa:
      new Ljava/lang/StringBuilder("repeat_icon=true; forward_ready=");
      Ldev/local/newsoul/Q0.r("repeat.menu.resources", new Ljava/lang/StringBuilder("repeat_icon=true; forward_ready=").toString());
      new Ljava/util/ArrayList((List) p1);
      new Ldev/local/newsoul/E(p2, p3, (String) p0.b.invoke(0x0, 0x0), (String) Ldev/local/newsoul/F.g(p2, "L"));
      if (p0.m.u == 0) goto :cond_143;
      if ((Context) Ldev/local/newsoul/F.b(p2, "getContext").getResources().getIdentifier("c_ct_longclick_menu_share", "drawable", (Context) Ldev/local/newsoul/F.b(p2, "getContext").getPackageName()) == 0) goto :cond_103;
      goto :goto_104;
      :cond_103:
      :goto_104:
      new Ljava/lang/StringBuilder("share_icon=");
      Ldev/local/newsoul/Q0.r("repeat.menu.forward", new Ljava/lang/StringBuilder("share_icon=").toString());
      if ((Context) Ldev/local/newsoul/F.b(p2, "getContext").getResources().getIdentifier("c_ct_longclick_menu_share", "drawable", (Context) Ldev/local/newsoul/F.b(p2, "getContext").getPackageName()) == 0) goto :cond_147;
      new Ldev/local/newsoul/E(p2, p3, (String) p0.b.invoke(0x0, 0x0), (String) Ldev/local/newsoul/F.g(p2, "L"));
      goto :goto_147;
      :cond_143:
      :cond_147:
      :goto_147:
      if (d3.g == 0) goto :cond_192;
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p3, "getChatMessage"), "getMsgType").intValue() != 0x5) goto :cond_192;
      if ((Context) Ldev/local/newsoul/F.b(p2, "getContext").getResources().getIdentifier("c_ct_chat_icon_photo_download", "drawable", (Context) Ldev/local/newsoul/F.b(p2, "getContext").getPackageName()) == 0) goto :cond_172;
      :cond_172:
      new Ldev/local/newsoul/E(p2, p3, (String) p0.b.invoke(0x0, 0x0), (String) Ldev/local/newsoul/F.g(p2, "L"));
      :cond_192:
      new Ljava/lang/StringBuilder("repeat menu added; type=");
      new Ljava/lang/StringBuilder("count=");
      Ldev/local/newsoul/Q0.r("repeat.menu.return", new Ljava/lang/StringBuilder("count=").toString());
      Ldev/local/newsoul/A.b(new Ljava/util/ArrayList((List) p1));
      return p0;
    }

    public final void h(ClassLoader p0) {
      :goto_3a:
      if (v12 >= 0x4) goto :cond_45;
      goto :goto_3a;
      :cond_45:
      v1.l = v15.getConstructor(new Class[][] {String.class, Integer.TYPE, Integer.TYPE});
      v1.c = v15.getDeclaredMethod("b", 0x0);
      v1.b = v2.loadClass("cn.soulapp.imlib.j0").getDeclaredMethod("g", 0x0);
      v1.d = v0.getMethod("create", new Class[][] {String.class});
      v1.e = v7.getMethod("createChatSendMsg", new Class[][] {v0, String.class});
      v1.f = v0.getMethod("setMsgType", new Class[][] {Integer.TYPE});
      v1.g = v0.getMethod("setMsgContent", new Class[][] {Serializable.class});
      v1.h = v6.getDeclaredMethod("u", new Class[][] {v0});
      v1.i = v6.getDeclaredMethod("p1", new Class[][] {v7});
      v6.getDeclaredMethod("p1", new Class[][] {v7}).setAccessible(0x1);
      v1.j = v6.getDeclaredMethod("Y1", new Class[][] {String.class, v2.loadClass("cn.soulapp.lib_input.view.IMediaKeyBoard"), v2.loadClass("cn.soulapp.android.client.component.middle.platform.model.api.user.ImUserBean")});
      v1.k = v2.loadClass("cn.soulapp.android.chat.utils.f").getMethod("a", new Class[][] {v7});
      new Ljava/lang/Object();
      :try_start_f5:
      (v12 + 0x1).e(v9, v2);
      (v12 + 0x1).u = v10;
      Ldev/local/newsoul/V.i(v6, 0x0);
      :try_end_fe:
      goto :goto_108;
      :catchall_ff:
      Ldev/local/newsoul/V.i(v6, e);
      :goto_108:
      v1.m = (v12 + 0x1);
      F.p = (v12 + 0x1).u;
      new Ldev/local/newsoul/d3();
      :try_start_113:
      v6.d = v2.loadClass(v14).getMethod(v8, 0x0);
      v6.e = v2.loadClass(v5).getMethod("q2", new Class[][] {v10[v12], Integer.TYPE, v2.loadClass(v4)});
      new Ldev/local/newsoul/N2(v6, 0x0);
      new Ldev/local/newsoul/N2(v6, 0x1);
      d3.g = v10;
      :try_end_183:
      goto :goto_18a;
      :catchall_184:
      :goto_18a:
      v1.n = v6;
      new Ldev/local/newsoul/C(v1, 0x0);
      Ldev/local/newsoul/Q0.k(v9, v23.getDeclaredMethod("p", new Class[][] {v7, v24, Context.class, v2.loadClass("uj.c0"), View.class, Integer.TYPE, View.class, v16, Integer.TYPE}), "repeat.click");
      new Ldev/local/newsoul/C(v1, 0x1);
      Ldev/local/newsoul/Q0.k(v9, v23.getDeclaredMethod("k", new Class[][] {v24, View.class, v7}), "repeat.menu");
      :try_start_1fd:
      new Ldev/local/newsoul/a(0xb);
      Ldev/local/newsoul/Q0.k(v9, v8.getDeclaredMethod("ac", new Class[][] {v10[v12], v7, v0}), v3);
      Ldev/local/newsoul/V.i(v3, 0x0);
      :try_end_226:
      goto :goto_22b;
      :catchall_227:
      Ldev/local/newsoul/V.i(v3, e);
      :goto_22b:
      :try_start_22d:
      :goto_23b:
      if (v5 >= v4) goto :cond_265;
      if (v2[v5].getReturnType() != v0) goto :cond_261;
      if (Ljava/util/Arrays.equals(v2[v5].getParameterTypes(), new Class[][] {v21}) == 0) goto :cond_261;
      if (v6 != 0) goto :cond_257;
      goto :goto_261;
      :cond_257:
      new Ljava/lang/IllegalStateException("Ambiguous menu renderer");
      throw new Ljava/lang/IllegalStateException("Ambiguous menu renderer");
      :catchall_25f:
      goto :goto_290;
      :cond_261:
      :goto_261:
      goto :goto_23b;
      :cond_265:
      if (v2[v5] == 0) goto :cond_288;
      new Ldev/local/newsoul/D(v1, e, 0x0);
      Ldev/local/newsoul/Q0.k(v9, v2[v5], v3);
      Ldev/local/newsoul/V.i(v3, 0x0);
      goto :goto_298;
      :cond_288:
      new Ljava/lang/NoSuchMethodException("Menu renderer");
      throw new Ljava/lang/NoSuchMethodException("Menu renderer");
      :try_end_290:
      :goto_290:
      Ldev/local/newsoul/V.i(v3, e);
      :goto_298:
      p1.G(v8, "ac", new Class[][] {v10[v12], v7, Integer.TYPE}, 0x1, 0x1, -0x1);
      p1.G((v12 + 0x1), "y", new Class[][] {v8, v21, v10[v12], v7, Integer.TYPE}, 0x0, 0x3, 0x1);
      F.o = 0x1;
      Ldev/local/newsoul/V.i("repeat", 0x0);
      return;

      // .catchall {:try_start_f5 .. :try_end_fe} :catchall_ff
      // .catchall {:try_start_113 .. :try_end_183} :catchall_184
      // .catchall {:try_start_1fd .. :try_end_226} :catchall_227
      // .catchall {:try_start_22d .. :try_end_290} :catchall_25f
    }

    public final Object i(Object p0) {
      if ((u) u.g.get() == 0) goto :cond_1a;
      if ((u) u.g.get().e != 0) goto :cond_1a;
      if ((u) u.g.get().f == 0) goto :cond_17;
      goto :goto_1a;
      :cond_17:
      (u) u.g.get().e = 0x1;
      goto :goto_1b;
      :cond_1a:
      :goto_1a:
      :goto_1b:
      if ((u) u.g.get() == 0) goto :cond_6f;
      if (F.o != 0) goto :cond_26;
      goto :goto_6f;
      :cond_26:
      :try_start_26:
      if (((u) u.g.get().c instanceof View) == 0) goto :cond_69;
      if ((View) (u) u.g.get().c.isAttachedToWindow() != 0) goto :cond_35;
      goto :goto_69;
      :cond_35:
      new Ldev/local/newsoul/E((u) u.g.get().a, (u) u.g.get().b, (String) p0.b.invoke(0x0, 0x0), (String) Ldev/local/newsoul/F.g((u) u.g.get().a, "L"));
      if (p0.a(p1, (u) u.g.get().a, (u) u.g.get().b) == p1) goto :cond_56;
      goto :goto_57;
      :cond_56:
      :goto_57:
      new Ljava/lang/StringBuilder("added=");
      Ldev/local/newsoul/Q0.r("repeat.fallback", new Ljava/lang/StringBuilder("added=").toString());
      :try_end_66:
      return "added=";
      :catchall_67:
      goto :goto_6a;
      :cond_69:
      :goto_69:
      return e;
      :goto_6a:
      Ldev/local/newsoul/Q0.s("repeat.fallback.skipped", e);
      :cond_6f:
      :goto_6f:
      return e;

      // .catchall {:try_start_26 .. :try_end_66} :catchall_67
    }

    public final void j(E p0) {
      if (Ldev/local/newsoul/F.g(p1.a, "Q") == 0) goto :cond_b2;
      if ((Integer) Ldev/local/newsoul/F.b(Ldev/local/newsoul/F.b(p1.b, "getChatMessage"), "getMsgType").intValue() != 0x1) goto :cond_50;
      if (Ldev/local/newsoul/F.g(p1.a, "O") == 0) goto :cond_48;
      if (Ldev/local/newsoul/F.g(p1.a, "s") == 0) goto :cond_48;
      goto :goto_9e;
      :cond_48:
      new Ljava/lang/IllegalStateException("Chat not ready");
      throw new Ljava/lang/IllegalStateException("Chat not ready");
      :cond_50:
      if ((Boolean) p0.k.invoke(0x0, new Object[][] {p0.e.invoke(0x0, new Object[][] {p0.d.invoke(0x0, new Object[][] {new Object[][] {Ldev/local/newsoul/F.g(Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b(p1.b, "getChatMessage")), "text"), Ldev/local/newsoul/F.g(p1.a, "s"), Ldev/local/newsoul/F.g(p1.a, "O")}.d}), new Object[][] {Ldev/local/newsoul/F.g(Ldev/local/newsoul/F.d(Ldev/local/newsoul/F.b(p1.b, "getChatMessage")), "text"), Ldev/local/newsoul/F.g(p1.a, "s"), Ldev/local/newsoul/F.g(p1.a, "O")}.d})}).booleanValue() != 0) goto :cond_95;
      return;
      :cond_95:
      :goto_9e:
      new Ljava/lang/StringBuilder("repeat dispatched; type=");
      return;
      :cond_b2:
      new Ljava/lang/IllegalStateException("Sender unavailable");
      throw new Ljava/lang/IllegalStateException("Sender unavailable");
    }

    public final Activity k(E p0) {
      if ((Activity) Ldev/local/newsoul/F.b(p1.a, "getActivity") == 0) goto :cond_4c;
      if ((Activity) Ldev/local/newsoul/F.b(p1.a, "getActivity").isFinishing() != 0) goto :cond_4c;
      if ((Activity) Ldev/local/newsoul/F.b(p1.a, "getActivity").isDestroyed() != 0) goto :cond_4c;
      if ((Boolean) Ldev/local/newsoul/F.b(p1.a, "isResumed").booleanValue() == 0) goto :cond_4c;
      if (p1.c.equals(p0.b.invoke(0x0, 0x0)) == 0) goto :cond_4c;
      if (p1.d.equals(Ldev/local/newsoul/F.g(p1.a, "L")) == 0) goto :cond_4c;
      if (Ldev/local/newsoul/F.f(p1.b, p1.c, p1.d) == 0) goto :cond_4c;
      return (Activity) Ldev/local/newsoul/F.b(p1.a, "getActivity");
      :cond_4c:
      new Ljava/lang/IllegalStateException("Chat changed");
      throw new Ljava/lang/IllegalStateException("Chat changed");
    }

}