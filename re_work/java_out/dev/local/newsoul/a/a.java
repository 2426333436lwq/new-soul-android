package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/a ====
// super: Ljava/lang/Object
// implements: XposedInterface$Hooke
// source: "SourceFile"

public final  class a extends Ljava/lang/Object implements Lio/github/libxposed/api/XposedInterface$Hooker {

    public final int a;

    public void super(int p0) {
      super();
      this.a = p1;
      return;
    }

    public final Object intercept(XposedInterface$Chain p0) {
      switch (p0.a) { /* :pswitch_data_52e */ }
      if (Ldev/local/newsoul/Q0.i((View) p1.getThisObject()) == 0) goto :cond_2a;
      Ldev/local/newsoul/Q0.i((View) p1.getThisObject()).m();
      :cond_2a:
      return p1.proceed();
      :pswitch_2b:
      new Ldev/local/newsoul/D2(0x0);
      (View) p1.getThisObject().addOnAttachStateChangeListener(new Ldev/local/newsoul/D2(0x0));
      return p1.proceed();
      :pswitch_3e:
      if (Q0.e0 != 0) goto :cond_5b;
      new Ljava/lang/Object();
      (Activity) p1.getThisObject().getApplication().registerActivityLifecycleCallbacks(new Ljava/lang/Object());
      Q0.e0 = 0x1;
      :cond_5b:
      return p1.proceed();
      :pswitch_60:
      if (Ldev/local/newsoul/Q0.q("chat_remove_social_score_limit") == 0) goto :cond_6e;
      goto :goto_72;
      :cond_6e:
      :goto_72:
      return "social score dialog suppressed";
      :pswitch_73:
      new Ljava/lang/StringBuilder("privacy snap preview: repeat=");
      Ldev/local/newsoul/Q0.w(new Ljava/lang/StringBuilder("privacy snap preview: repeat=").toString());
      return p1.proceed();
      :pswitch_a1:
      if (Ldev/local/newsoul/Q0.R((Activity) p1.getThisObject()) == 0) goto :cond_ba;
      :cond_ba:
      if (Ldev/local/newsoul/w2.o((Activity) p1.getThisObject()).getBoolean("privacy_allow_screenshot", 0x0) == 0) goto :cond_cf;
      (Activity) p1.getThisObject().getWindow().clearFlags(0x2000);
      :cond_cf:
      if (Ldev/local/newsoul/w2.o((Activity) p1.getThisObject()).getBoolean("privacy_allow_download", 0x0) == 0) goto :cond_11b;
      if ((Activity) p1.getThisObject().getIntent().getIntExtra("type", -0x1) < 0x1) goto :cond_11d;
      if ((Activity) p1.getThisObject().getIntent().getIntExtra("type", -0x1) > 0x4) goto :cond_11d;
      if ((Activity) p1.getThisObject().getIntent().getIntExtra("type", -0x1) == 0x1) goto :cond_fb;
      if ((Activity) p1.getThisObject().getIntent().getIntExtra("type", -0x1) != 0x3) goto :cond_f9;
      goto :goto_fb;
      :cond_f9:
      goto :goto_fc;
      :cond_fb:
      :goto_fb:
      :goto_fc:
      new long[][0x4] = /* array-data :array_55c */;
      :goto_10a:
      if (v14 >= 0x4) goto :cond_11b;
      new Ldev/local/newsoul/U((Activity) p1.getThisObject(), 0x0);
      goto :goto_10a;
      :cond_11b:
      goto :goto_11e;
      :cond_11d:
      :goto_11e:
      new Ljava/lang/StringBuilder("privacy photo preview: download=");
      Ldev/local/newsoul/Q0.w(new Ljava/lang/StringBuilder("privacy photo preview: download=").toString());
      return new Ljava/lang/StringBuilder("privacy photo preview: download=").toString();
      :pswitch_154:
      if (g1.b.get() == p1.getThisObject()) goto :cond_165;
      goto :goto_186;
      :cond_165:
      g1.b.remove();
      if (compare((p1.getThisObject() - (Long) p1.proceed().longValue()), 0x1d4c0) > 0) goto :cond_17d;
      goto :goto_186;
      :cond_17d:
      :goto_186:
      return p1.proceed();
      :pswitch_187:
      if ((a0) g0.d.get((Activity) p1.getThisObject()) == 0) goto :cond_1ed;
      if ((a0) g0.d.get((Activity) p1.getThisObject()).a == (Integer) p1.getThisObject().getArg(0x0).intValue()) goto :cond_1a7;
      goto :goto_1ed;
      :cond_1a7:
      if ((a0) g0.d.get((Activity) p1.getThisObject()).d != 0) goto :cond_1ac;
      goto :goto_1f1;
      :cond_1ac:
      (a0) g0.d.get((Activity) p1.getThisObject()).d = 0x0;
      if ((Integer) p1.getThisObject().getArg(0x1).intValue() != 0x3) goto :cond_1e7;
      if ((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())) == 0) goto :cond_1e7;
      if ((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())).getData() != 0) goto :cond_1c9;
      goto :goto_1e7;
      :cond_1c9:
      if ((f0) (a0) g0.d.get((Activity) p1.getThisObject()).b.get() == 0) goto :cond_1dc;
      (f0) (a0) g0.d.get((Activity) p1.getThisObject()).b.get().a("正在保存记录…");
      :cond_1dc:
      new Ldev/local/newsoul/H((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())).getData(), (Activity) p1.getThisObject(), (a0) g0.d.get((Activity) p1.getThisObject()), 0x1);
      V.a.execute(new Ldev/local/newsoul/H((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())).getData(), (Activity) p1.getThisObject(), (a0) g0.d.get((Activity) p1.getThisObject()), 0x1));
      goto :goto_1f1;
      :cond_1e7:
      :goto_1e7:
      Ldev/local/newsoul/g0.b((Activity) p1.getThisObject(), (a0) g0.d.get((Activity) p1.getThisObject()), "已取消导出");
      goto :goto_1f1;
      :cond_1ed:
      :goto_1ed:
      :goto_1f1:
      return (Activity) p1.getThisObject();
      :pswitch_1f2:
      Ldev/local/newsoul/V.h((Context) p1.getThisObject());
      return p1.proceed();
      :pswitch_200:
      if ((P) S.d.get((Activity) p1.getThisObject()) == 0) goto :cond_273;
      if ((P) S.d.get((Activity) p1.getThisObject()).a == (Integer) "已取消导出".getArg(0x0).intValue()) goto :cond_220;
      goto :goto_273;
      :cond_220:
      if ((P) S.d.get((Activity) p1.getThisObject()).f != 0) goto :cond_225;
      goto :goto_277;
      :cond_225:
      (P) S.d.get((Activity) p1.getThisObject()).f = 0x0;
      if ((Integer) "已取消导出".getArg(0x1).intValue() != 0x3) goto :cond_26d;
      if ((Intent) "已取消导出".getArg(new Ldev/local/newsoul/H((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())).getData(), (Activity) p1.getThisObject(), (a0) g0.d.get((Activity) p1.getThisObject()), 0x1)) == 0) goto :cond_267;
      if ((Intent) "已取消导出".getArg(new Ldev/local/newsoul/H((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())).getData(), (Activity) p1.getThisObject(), (a0) g0.d.get((Activity) p1.getThisObject()), 0x1)).getData() == 0) goto :cond_267;
      if ((P) S.d.get((Activity) p1.getThisObject()).c == 0) goto :cond_24c;
      goto :goto_24e;
      :cond_24c:
      :goto_24e:
      if ((Q) (P) S.d.get((Activity) p1.getThisObject()).e.get() == 0) goto :cond_25b;
      (Q) (P) S.d.get((Activity) p1.getThisObject()).e.get().a("正在导出配置…", 0x1);
      :cond_25b:
      new Ldev/local/newsoul/H((Intent) "已取消导出".getArg(new Ldev/local/newsoul/H((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())).getData(), (Activity) p1.getThisObject(), (a0) g0.d.get((Activity) p1.getThisObject()), 0x1)).getData(), (P) S.d.get((Activity) p1.getThisObject()), (Activity) p1.getThisObject(), 0x0);
      S.c.execute(new Ldev/local/newsoul/H((Intent) "已取消导出".getArg(new Ldev/local/newsoul/H((Intent) p1.getThisObject().getArg(Ldev/local/newsoul/w2.o((Activity) p1.getThisObject())).getData(), (Activity) p1.getThisObject(), (a0) g0.d.get((Activity) p1.getThisObject()), 0x1)).getData(), (P) S.d.get((Activity) p1.getThisObject()), (Activity) p1.getThisObject(), 0x0));
      goto :goto_277;
      :cond_267:
      Ldev/local/newsoul/S.b(S.c, (P) S.d.get((Activity) p1.getThisObject()), "未取得配置文件，请重新选择");
      goto :goto_277;
      :cond_26d:
      Ldev/local/newsoul/S.b(S.c, (P) S.d.get((Activity) p1.getThisObject()), "已取消配置操作");
      goto :goto_277;
      :cond_273:
      :goto_273:
      :goto_277:
      return S.c;
      :pswitch_278:
      new Ldev/local/newsoul/u(p1.getThisObject(), "已取消配置操作".getArg(0x1), "已取消配置操作".getArg(0x0));
      :try_start_28a:
      :try_end_28e:
      0x0.close();
      return S.c;
      :catchall_292:
      :try_start_294:
      0x0.close();
      :try_end_297:
      goto :goto_29d;
      :catchall_298:
      "已取消配置操作".addSuppressed(e);
      :goto_29d:
      throw "已取消配置操作";
      :pswitch_29e:
      :try_start_29e:
      if (Ldev/local/newsoul/w2.o((Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot")).getBoolean(S.c, 0x1) == 0) goto :cond_2ec;
      if (Ldev/local/newsoul/Q0.v("已取消配置操作".getArg(0x0)) == 0) goto :cond_2ec;
      if (Ldev/local/newsoul/Q0.u("已取消配置操作".getArg(0x0)) == 0) goto :cond_2ec;
      if ((Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot").getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0x0).contains(Ldev/local/newsoul/Q0.L("已取消配置操作".getArg(0x0))) != 0) goto :cond_2e0;
      if ((Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot").getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0x0).edit().putBoolean(Ldev/local/newsoul/Q0.L("已取消配置操作".getArg(0x0)), 0x1).commit() == 0) goto :cond_2ec;
      :cond_2e0:
      :try_end_2e5:
      goto :goto_2f0;
      :catchall_2e6:
      :cond_2ec:
      :goto_2f0:
      return (Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot").getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0x0).edit().putBoolean(Ldev/local/newsoul/Q0.L("已取消配置操作".getArg(0x0)), 0x1).commit();
      :pswitch_2f1:
      :try_start_2f1:
      if (Ldev/local/newsoul/w2.o((Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot")).getBoolean((Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot").getSharedPreferences("dev.local.soulhookdemo.recalled.v1", 0x0).edit().putBoolean(Ldev/local/newsoul/Q0.L("已取消配置操作".getArg(0x0)), 0x1).commit(), 0x1) == 0) goto :cond_330;
      if (("已取消配置操作".getArg(0x0) instanceof ArrayList) == 0) goto :cond_330;
      :try_end_312:
      :goto_314:
      if (("已取消配置操作".getArg(0x0) instanceof ArrayList) < 0) goto :cond_337;
      :try_start_316:
      if (Ldev/local/newsoul/Q0.S((Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot"), p1.getThisObject(), "已取消配置操作".getArg(0x0).get(("已取消配置操作".getArg(0x0) instanceof ArrayList))) == 0) goto :cond_32b;
      :try_end_327:
      goto :goto_32b;
      :catchall_329:
      goto :goto_332;
      :cond_32b:
      :goto_32b:
      goto :goto_314;
      :catchall_32d:
      goto :goto_332;
      :cond_330:
      goto :goto_337;
      :goto_332:
      :cond_337:
      :goto_337:
      if (0x0 == 0) goto :cond_353;
      if (Q0.y != 0) goto :cond_342;
      goto :goto_353;
      :cond_342:
      new Landroid/os/Handler(p1.proceed());
      new Ldev/local/newsoul/h(0x1);
      :cond_353:
      :goto_353:
      return p1.proceed();
      :pswitch_354:
      if (Ldev/local/newsoul/Q0.e(Q0.y.getArg(0x0)) == 0) goto :cond_365;
      goto :goto_369;
      :cond_365:
      :goto_369:
      return "gift chat dialog suppressed";
      :pswitch_36a:
      if (Ldev/local/newsoul/Q0.q("chat_remove_bell_gift_gate") == 0) goto :cond_385;
      if (Boolean.TRUE.equals(p1.proceed()) == 0) goto :cond_383;
      :cond_383:
      :cond_385:
      return Boolean.TRUE.equals(p1.proceed());
      :pswitch_386:
      if ("chat_limit_session_type".equals(Boolean.TRUE.getArg(0x0)) == 0) goto :cond_3af;
      if ((Integer) p1.proceed().intValue() < Ldev/local/newsoul/Q0.S((Context) Q0.l.invoke("privacy_allow_screenshot", "privacy_allow_screenshot"), p1.getThisObject(), "已取消配置操作".getArg(0x0).get(("已取消配置操作".getArg(0x0) instanceof ArrayList)))) goto :cond_3af;
      if ((Integer) p1.proceed().intValue() > 0x5) goto :cond_3af;
      if (Ldev/local/newsoul/Q0.q("chat_remove_gift_gate") == 0) goto :cond_3af;
      :cond_3af:
      return p1.proceed();
      :pswitch_3b0:
      if (Boolean.TRUE.equals(p1.proceed()) == 0) goto :cond_3c7;
      if (Ldev/local/newsoul/Q0.e(p1.getThisObject()) == 0) goto :cond_3c7;
      goto :goto_3c8;
      :cond_3c7:
      :goto_3c8:
      if (Boolean.TRUE.equals(p1.proceed()) == 0) goto :cond_3f5;
      new Ljava/lang/StringBuilder("chat gate observed; type=");
      :cond_3f5:
      if (0x1 == 0) goto :cond_3f9;
      :cond_3f9:
      return p1.proceed();
      :pswitch_3fa:
      if ((Integer) t.c.get() == 0) goto :cond_429;
      if ((Integer) t.c.get().intValue() <= 0) goto :cond_429;
      new Ljava/lang/StringBuilder("game message constructed with selected result=");
      t.f.log(p1.getThisObject(), (P) S.d.get((Activity) p1.getThisObject()).a, new Ljava/lang/StringBuilder("game message constructed with selected result=").toString());
      goto :goto_42d;
      :cond_429:
      :goto_42d:
      return Ldev/local/newsoul/Q0.q("chat_remove_gift_gate").proceed(new Object[][] {(Integer) t.c.get()});
      :pswitch_42e:
      if (Ldev/local/newsoul/Q0.q("match_mute_background_music") == 0) goto :cond_43c;
      goto :goto_440;
      :cond_43c:
      :goto_440:
      return "soul match background music blocked";
      :pswitch_441:
      :try_start_44b:
      new Ldev/local/newsoul/l("soul match background music blocked");
      "soul match background music blocked".getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new Ldev/local/newsoul/l("soul match background music blocked"));
      new Ldev/local/newsoul/m("soul match background music blocked", 0x0);
      :try_end_468:
      goto :goto_46f;
      :catchall_469:
      :goto_46f:
      return "soul match background music blocked";
      :pswitch_470:
      :goto_47e:
      if (0x0 >= t.f) goto :cond_4e2;
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().equals("pi.d") == 0) goto :cond_491;
      goto :goto_4e2;
      :cond_491:
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().startsWith("cn.soulapp.android.component.chat.limitdialog.RewardAdHelper") == 0) goto :cond_49c;
      goto :goto_4e2;
      :cond_49c:
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().startsWith("cn.soulapp.android.component.square.video.drama.o0") == 0) goto :cond_4a7;
      goto :goto_4e2;
      :cond_4a7:
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().startsWith("cn.soulapp.android.ad.h5.moduel.") != 0) goto :cond_4e0;
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().equals("cc.c") != 0) goto :cond_4e0;
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().startsWith("cn.soul.android.soul_rn_sdk.plugin.SRNAdPlugin") != 0) goto :cond_4e0;
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().startsWith("cn.soul.android.soul_rn_sdk.plugin.SoulRnAdPlugin") == 0) goto :cond_4c8;
      goto :goto_4e0;
      :cond_4c8:
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().startsWith("cn.soulapp.android.ad.base.view.AdFloatViewProvider") == 0) goto :cond_4d3;
      goto :goto_4e2;
      :cond_4d3:
      if (new Ljava/lang/StringBuilder("game message constructed with selected result=").toString()[0x0].getClassName().equals("cn.soulapp.android.ad.base.view.e") == 0) goto :cond_4de;
      goto :goto_4e2;
      :cond_4de:
      goto :goto_47e;
      :cond_4e0:
      :goto_4e0:
      :cond_4e2:
      :goto_4e2:
      if ("ad_interstitial" == 0) goto :cond_4ed;
      :cond_4ed:
      return "soul match background music blocked";
      :pswitch_4ee:
      if ((i) j.d.get((View) p1.getThisObject()) == 0) goto :cond_528;
      if (Ldev/local/newsoul/j.b((View) p1.getThisObject().getContext(), (i) j.d.get((View) p1.getThisObject()).a) == 0) goto :cond_528;
      (i) j.d.get((View) p1.getThisObject()).b = (Integer) Ldev/local/newsoul/Q0.q("chat_remove_gift_gate").getArg(0x0).intValue();
      (i) j.d.get((View) p1.getThisObject()).c = 0x1;
      goto :goto_52c;
      :cond_528:
      :goto_52c:
      return Ldev/local/newsoul/Q0.q("chat_remove_gift_gate").proceed(new Object[][] {Ljava/lang/Integer.valueOf(0x8)});
      :pswitch_data_52e:
      :array_55c:

      // .array-data 8
      // 0x0
      // 0xfa
      // 0x2ee
      // 0x5dc

      // .catchall {:try_start_28a .. :try_end_28e} :catchall_292
      // .catchall {:try_start_294 .. :try_end_297} :catchall_298
      // .catchall {:try_start_29e .. :try_end_2e5} :catchall_2e6
      // .catchall {:try_start_2f1 .. :try_end_312} :catchall_32d
      // .catchall {:try_start_316 .. :try_end_327} :catchall_329
      // .catchall {:try_start_44b .. :try_end_468} :catchall_469
    }

}