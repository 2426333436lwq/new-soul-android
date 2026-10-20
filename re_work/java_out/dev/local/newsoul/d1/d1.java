package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/d1 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class d1 extends Ljava/lang/Object {

    public Class a;
    public Class b;
    public Class c;
    public Constructor d;
    public Method e;
    public Method f;
    public Method g;
    public Method h;
    public Method i;
    public Method j;
    public Method k;
    public Method l;
    public Method m;
    public Method n;
    public Method o;
    public Method p;
    public Method q;
    public String r;
    public e1 s;
    public Dialog t;
    public boolean u;

    public static Object a(String p0) {
      return p0.getClass();
    }

    public static void c(Throwable p0) {
      Ldev/local/newsoul/V.d("message.forward.failed", Ldev/local/newsoul/V.c(p1));
      Ldev/local/newsoul/d1.l(p0, "转发未完成，请重新选择消息");
      return;
    }

    public static Object d(String p0) {
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

    public static boolean f(Activity p0) {
      return "dev.local.newsoul.message_forward_token";
    }

    public static LinkedHashMap h(e1 p0) {
      new Ljava/util/LinkedHashMap();
      :goto_11:
      if (v1.hasNext() == 0) goto :cond_b1;
      if ((v1.next() instanceof String) == 0) goto :cond_a9;
      if (v0.containsKey(v1.next()) != 0) goto :cond_a9;
      :cond_39:
      if (v0.containsKey(v1.next()).hasNext() == 0) goto :cond_72;
      if (Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "conversation") == 0) goto :cond_39;
      if (v1.next().equals(Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "conversation"), "getToUserId")) == 0) goto :cond_39;
      if (p1.c(v1.next(), (Integer) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "conversation"), "getChatType").intValue()) == 0) goto :cond_39;
      if (Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "user") == 0) goto :cond_39;
      goto :goto_73;
      :cond_72:
      :goto_73:
      if (v0.containsKey(v1.next()).next() == 0) goto :cond_a1;
      if ((String) Ldev/local/newsoul/d1.d(Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "user"), "alias") == 0) goto :cond_89;
      if ((String) Ldev/local/newsoul/d1.d(Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "user"), "alias").isEmpty() == 0) goto :cond_92;
      :cond_89:
      :cond_92:
      if ((String) Ldev/local/newsoul/d1.d(Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "user"), "alias") == 0) goto :cond_9a;
      if ((String) Ldev/local/newsoul/d1.d(Ldev/local/newsoul/d1.d(v0.containsKey(v1.next()).next(), "user"), "alias").isEmpty() == 0) goto :cond_9c;
      :cond_9a:
      :cond_9c:
      goto :goto_11;
      :cond_a1:
      new Ljava/lang/IllegalStateException("Recipient changed");
      throw new Ljava/lang/IllegalStateException("Recipient changed");
      :cond_a9:
      new Ljava/lang/IllegalStateException("Invalid selection");
      throw new Ljava/lang/IllegalStateException("Invalid selection");
      :cond_b1:
      return v0;
    }

    public static Map j(Activity p0) {
      return "m0";
    }

    public static void l(String p0) {
      Landroid/widget/Toast.makeText(p0, p1, 0x0).show();
      return;
    }

    public static void n(Activity p0) {
      if (Ldev/local/newsoul/d1.j(p0).size() != 0) goto :cond_15;
      goto :goto_28;
      :cond_15:
      new Ljava/lang/StringBuilder("完成(");
      :goto_28:
      (TextView) Ldev/local/newsoul/d1.d(p0, "e").setText("完成");
      if (Ldev/local/newsoul/d1.j(p0).size() <= 0) goto :cond_2f;
      goto :goto_30;
      :cond_2f:
      :goto_30:
      (TextView) Ldev/local/newsoul/d1.d(p0, "e").setEnabled(0x1);
      if (Ldev/local/newsoul/d1.j(p0).size() <= 0) goto :cond_38;
      goto :goto_3a;
      :cond_38:
      :goto_3a:
      (TextView) Ldev/local/newsoul/d1.d(p0, "e").setAlpha(0x3f800000    # 1.0f);
      return;
    }

    public final void b(Activity p0) {
      if (p1.isFinishing() != 0) goto :cond_df;
      if (p1.isDestroyed() != 0) goto :cond_df;
      if (p0.t == 0) goto :cond_18;
      if (p0.t.isShowing() == 0) goto :cond_18;
      goto :goto_df;
      :cond_18:
      if (p0.i(p1) != 0) goto :cond_27;
      Ldev/local/newsoul/d1.l(p1, "转发已过期，请重新选择消息");
      p1.finish();
      return;
      :cond_27:
      if (Ldev/local/newsoul/d1.h(p1, p0.i(p1)).isEmpty() != 0) goto :cond_df;
      if (Ldev/local/newsoul/d1.h(p1, p0.i(p1)).size() <= 0x9) goto :cond_3b;
      goto :goto_df;
      :cond_3b:
      new Ljava/util/ArrayList(Ldev/local/newsoul/d1.h(p1, p0.i(p1)).keySet());
      if (p0.i(p1).c != 0x1) goto :cond_4e;
      goto :goto_56;
      :cond_4e:
      if (p0.i(p1).c != 0x5) goto :cond_54;
      goto :goto_56;
      :cond_54:
      :goto_56:
      new Ljava/lang/StringBuilder("将这条");
      v13.<init>(p0, p1, p0.r, p0.i(p1), Ldev/local/newsoul/d1.h(p1, p0.i(p1)), new Ljava/util/ArrayList(Ldev/local/newsoul/d1.h(p1, p0.i(p1)).keySet()), (Dialog) p0.d.newInstance(new Object[][] {p1, new Ljava/lang/StringBuilder("将这条").toString()}));
      p0.t = (Dialog) p0.d.newInstance(new Object[][] {p1, new Ljava/lang/StringBuilder("将这条").toString()});
      new Ldev/local/newsoul/c1(p0, (Dialog) p0.d.newInstance(new Object[][] {p1, new Ljava/lang/StringBuilder("将这条").toString()}), 0x0);
      (Dialog) p0.d.newInstance(new Object[][] {p1, new Ljava/lang/StringBuilder("将这条").toString()}).setOnDismissListener(new Ldev/local/newsoul/c1(p0, (Dialog) p0.d.newInstance(new Object[][] {p1, new Ljava/lang/StringBuilder("将这条").toString()}), 0x0));
      (Dialog) p0.d.newInstance(new Object[][] {p1, new Ljava/lang/StringBuilder("将这条").toString()}).show();
      new Ljava/lang/StringBuilder("type=");
      Ldev/local/newsoul/V.d("message.forward.confirmation", new Ljava/lang/StringBuilder("type=").toString());
      :cond_df:
      :goto_df:
      return;
    }

    public final void e(ClassLoader p0) {
      p0.a = p2.loadClass("cn.soulapp.android.component.chat.SelectConversationActivity");
      p0.b = p2.loadClass("cn.soulapp.android.chat.listener.OnClickConfirmListener");
      p0.d = p2.loadClass("cn.soulapp.android.component.chat.dialog.j1").getConstructor(new Class[][] {Context.class, String.class});
      p0.f = p2.loadClass("cn.soulapp.android.component.chat.dialog.j1").getMethod("d", new Class[][] {p0.b});
      p0.g = p2.loadClass("cn.soulapp.imlib.j0").getMethod("g", 0x0);
      p0.h = p2.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("create", new Class[][] {String.class});
      p0.i = p2.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("setMsgContent", new Class[][] {Serializable.class});
      p0.j = p2.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("setMsgType", new Class[][] {Integer.TYPE});
      p0.k = p2.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage").getMethod("setTransExtMap", new Class[][] {Map.class});
      p0.l = p2.loadClass("cn.soulapp.android.component.chat.utils.y4").getMethod("u", new Class[][] {p2.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage")});
      p0.m = p2.loadClass("cn.soulapp.imlib.msg.ImMessage").getMethod("createChatSendMsg", new Class[][] {p2.loadClass("cn.soulapp.imlib.msg.chat.ChatMessage"), String.class});
      p0.n = p2.loadClass("cn.soulapp.imlib.i0").getMethod("v", 0x0);
      p0.o = p2.loadClass("cn.soulapp.imlib.i0").getMethod("o", 0x0);
      p0.p = p2.loadClass("cn.soulapp.imlib.ChatManager").getMethod("q0", new Class[][] {p2.loadClass("cn.soulapp.imlib.msg.ImMessage")});
      p0.q = p2.loadClass("uj.k1").getMethod("g2", new Class[][] {p2.loadClass("cn.soulapp.android.chat.bean.UserConversation")});
      p0.c = p2.loadClass("com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter$OnItemClickListener");
      p0.e = p2.loadClass("cn.soulapp.android.component.chat.adapter.ConversationAdapter").getMethod("setOnItemClickListener", new Class[][] {p2.loadClass("com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter$OnItemClickListener")});
      new Ldev/local/newsoul/a1(p0, 0x0);
      new Ldev/local/newsoul/a1(p0, 0x1);
      new Ldev/local/newsoul/a1(p0, 0x2);
      :goto_1ac:
      if (v1 >= v2) goto :cond_1de;
      new Ljava/lang/StringBuilder("new-soul/message-forward-complete-");
      new Ldev/local/newsoul/a1(p0, 0x3);
      goto :goto_1ac;
      :cond_1de:
      :goto_1fc:
      if (v5 >= v2) goto :cond_22e;
      new Ljava/lang/StringBuilder("new-soul/message-forward-navigation-");
      new Ldev/local/newsoul/a1(p0, 0x4);
      goto :goto_1fc;
      :cond_22e:
      new Ldev/local/newsoul/a1(p0, 0x5);
      return;
    }

    public final void g(Object p0) {
      if (p0.u == 0) goto :cond_7a;
      if (p1.isFinishing() != 0) goto :cond_7a;
      if (p1.isDestroyed() != 0) goto :cond_7a;
      if (p2.equals(p0.g.invoke(0x0, 0x0)) == 0) goto :cond_7a;
      p2.equals(p0.g.invoke(0x0, 0x0)).<init>(p2, p3, p4, Ldev/local/newsoul/F.c(p4, p5), Ldev/local/newsoul/F.c(p4, p5), v8);
      p0.s = p2.equals(p0.g.invoke(0x0, 0x0));
      p0.r = Ldev/local/newsoul/F.c(p4, p5).toString();
      new Landroid/content/Intent(p1, p0.a);
      :try_start_5d:
      p1.startActivity(p2);
      :try_end_60:
      new Ljava/lang/StringBuilder("type=");
      Ldev/local/newsoul/V.d("message.forward.picker_open", new Ljava/lang/StringBuilder("type=").toString());
      return;
      :catch_74:
      p0.s = 0x0;
      p0.r = 0x0;
      throw e;
      :cond_7a:
      new Ljava/lang/IllegalStateException("Forward unavailable");
      throw new Ljava/lang/IllegalStateException("Forward unavailable");

      // try :try_start_5d..:try_end_60 catch (Ljava/lang/RuntimeException) -> :catch_74
    }

    public final e1 i(Activity p0) {
      if (p0.s == 0) goto :cond_2f;
      if (p0.r == 0) goto :cond_2f;
      if (p0.r.equals(p1.getIntent().getStringExtra("dev.local.newsoul.message_forward_token")) == 0) goto :cond_2f;
      if (p0.s.b((String) p0.g.invoke(0x0, 0x0), p0.g.invoke(0x0, 0x0), v3) == 0) goto :cond_2f;
      :cond_2f:
      return (String) p0.g.invoke(0x0, 0x0);
    }

    public final void k(String p0) {
      new Ljava/util/HashMap();
      return;
    }

    public final void m(Landroid/app/Activity;I p0) {
      if (p0.i(p1) != 0) goto :cond_f;
      Ldev/local/newsoul/d1.l(p1, "转发已过期，请重新选择消息");
      p1.finish();
      return;
      :cond_f:
      if (p2 < 0) goto :cond_a5;
      if (p2 < (List) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(p1, "b"), "getAllData").size()) goto :cond_27;
      goto :goto_a5;
      :cond_27:
      if (Ldev/local/newsoul/d1.d((List) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(p1, "b"), "getAllData").get(p2), "conversation") == 0) goto :cond_a5;
      if (Ldev/local/newsoul/d1.d((List) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(p1, "b"), "getAllData").get(p2), "user") != 0) goto :cond_3c;
      goto :goto_a5;
      :cond_3c:
      if (p0.i(p1).c((String) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d((List) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(p1, "b"), "getAllData").get(p2), "conversation"), "getToUserId"), (Integer) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d((List) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(p1, "b"), "getAllData").get(p2), "conversation"), "getChatType").intValue()) != 0) goto :cond_57;
      return;
      :cond_57:
      if (Ldev/local/newsoul/d1.j(p1).containsValue((String) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d((List) Ldev/local/newsoul/d1.a(Ldev/local/newsoul/d1.d(p1, "b"), "getAllData").get(p2), "conversation"), "getToUserId")) != 0) goto :cond_6f;
      if (Ldev/local/newsoul/d1.j(p1).size() < 0x9) goto :cond_6f;
      Ldev/local/newsoul/d1.l(p1, "最多选择9人");
      return;
      :cond_6f:
      Ldev/local/newsoul/d1.n(p1);
      new Ljava/lang/StringBuilder("count=");
      Ldev/local/newsoul/V.d("message.forward.selection", new Ljava/lang/StringBuilder("count=").toString());
      :cond_a5:
      :goto_a5:
      return;
    }

}