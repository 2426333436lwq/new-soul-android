package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/S0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class S0 extends Ljava/lang/Object {

    public final long a;

    public void super(long p0) {
      super();
      this.a = p1;
      return;
    }

    public final String a(String p0) {
      if (compare(((p0.a - __result__) / 0xf4240), 0x0) <= 0) goto :cond_108;
      if (__result__.isInterrupted() != 0) goto :cond_108;
      new Ljava/net/URL(p1);
      :try_start_2a:
      p1.setInstanceFollowRedirects(__result__.isInterrupted());
      p1.setConnectTimeout(((int) Ljava/lang/Math.min(0x1f40, v7, ((p0.a - __result__) / 0xf4240), v1)));
      p1.setReadTimeout(((int) Ljava/lang/Math.min(0x1f40, v7, ((p0.a - __result__) / 0xf4240), v1)));
      p1.setUseCaches(__result__.isInterrupted());
      p1.setRequestProperty("User-Agent", "Mozilla/5.0 NewSoul-UpdateCheck");
      p1.setRequestProperty("Referer", "https://wwbnj.lanzoul.com/b007utki2d");
      if (p2 == 0) goto :cond_86;
      p1.setRequestMethod("POST");
      p1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
      p1.setDoOutput(0x1);
      p1.setFixedLengthStreamingMode(p2.getBytes(StandardCharsets.UTF_8).length);
      :try_end_70:
      :try_start_70:
      "Referer".write(p2);
      :try_end_73:
      :try_start_73:
      "Referer".close();
      :try_end_76:
      goto :goto_86;
      :catchall_77:
      goto :goto_104;
      :catchall_7a:
      if ("Referer" == 0) goto :cond_85;
      :try_start_7d:
      "Referer".close();
      :try_end_80:
      goto :goto_85;
      :catchall_81:
      :try_start_82:
      e.addSuppressed("Referer");
      :cond_85:
      :goto_85:
      throw e;
      :cond_86:
      :goto_86:
      if (p1.getResponseCode() != 0xc8) goto :cond_fc;
      :try_end_92:
      :try_start_92:
      new Ljava/io/ByteArrayOutputStream();
      :try_end_97:
      :try_start_99:
      :goto_9b:
      if (p1.getResponseCode().read("https://wwbnj.lanzoul.com/b007utki2d") == -0x1) goto :cond_d1;
      if ((0xc8.size() + p1.getResponseCode().read("https://wwbnj.lanzoul.com/b007utki2d")) > 0x80000) goto :cond_c9;
      if (compare(0xc8.size(), p0.a) >= 0) goto :cond_c3;
      if (0xc8.size().isInterrupted() != 0) goto :cond_c3;
      0xc8.write("https://wwbnj.lanzoul.com/b007utki2d", __result__.isInterrupted(), p1.getResponseCode().read("https://wwbnj.lanzoul.com/b007utki2d"));
      goto :goto_9b;
      :catchall_c1:
      goto :goto_e8;
      :cond_c3:
      new Ljava/io/IOException("检测超时");
      throw new Ljava/io/IOException("检测超时");
      :cond_c9:
      new Ljava/io/IOException("发布页响应过大");
      throw new Ljava/io/IOException("发布页响应过大");
      :cond_d1:
      new Ljava/lang/String(0xc8.toByteArray(), StandardCharsets.UTF_8);
      :try_end_dc:
      :try_start_dc:
      0xc8.close();
      :try_end_df:
      :try_start_df:
      p1.getResponseCode().close();
      :try_end_e2:
      p1.disconnect();
      return 0xc8;
      :catchall_e6:
      goto :goto_f1;
      :goto_e8:
      :try_start_e8:
      e.close();
      :try_end_eb:
      goto :goto_f0;
      :catchall_ec:
      :try_start_ed:
      e.addSuppressed(e);
      :goto_f0:
      throw e;
      :try_end_f1:
      :goto_f1:
      if (p1.getResponseCode() == 0) goto :cond_fb;
      :try_start_f3:
      p1.getResponseCode().close();
      :try_end_f6:
      goto :goto_fb;
      :catchall_f7:
      :try_start_f8:
      e.addSuppressed(p1.getResponseCode());
      :cond_fb:
      :goto_fb:
      throw e;
      :cond_fc:
      new Ljava/io/IOException("发布页连接失败");
      throw new Ljava/io/IOException("发布页连接失败");
      :try_end_104:
      :goto_104:
      p1.disconnect();
      throw p1.getResponseCode();
      :cond_108:
      new Ljava/io/IOException("检测超时");
      throw new Ljava/io/IOException("检测超时");

      // .catchall {:try_start_2a .. :try_end_70} :catchall_77
      // .catchall {:try_start_70 .. :try_end_73} :catchall_7a
      // .catchall {:try_start_73 .. :try_end_76} :catchall_77
      // .catchall {:try_start_7d .. :try_end_80} :catchall_81
      // .catchall {:try_start_82 .. :try_end_92} :catchall_77
      // .catchall {:try_start_92 .. :try_end_97} :catchall_e6
      // .catchall {:try_start_99 .. :try_end_dc} :catchall_c1
      // .catchall {:try_start_dc .. :try_end_df} :catchall_e6
      // .catchall {:try_start_df .. :try_end_e2} :catchall_77
      // .catchall {:try_start_e8 .. :try_end_eb} :catchall_ec
      // .catchall {:try_start_ed .. :try_end_f1} :catchall_e6
      // .catchall {:try_start_f3 .. :try_end_f6} :catchall_f7
      // .catchall {:try_start_f8 .. :try_end_104} :catchall_77
    }

}