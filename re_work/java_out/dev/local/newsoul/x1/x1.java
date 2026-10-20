package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/x1 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public final  class x1 extends Ljava/lang/Object {

    public static final Object b;
    public final File a;

    static void static {}() {
      new Ljava/lang/Object();
      x1.b = new Ljava/lang/Object();
      return;
    }

    public void super(Ljava/io/File;Ljava/lang/String;I p0) {
      super();
      switch (p3) { /* :pswitch_data_d4 */ }
      if (p2 == 0) goto :cond_5e;
      if (p2.trim().isEmpty() != 0) goto :cond_5e;
      new Ljava/lang/StringBuilder();
      :goto_29:
      if (v1 >= v0) goto :cond_45;
      goto :goto_29;
      :cond_45:
      new Ljava/io/File(p1, "newsoul-profile-bookmarks");
      new Ljava/io/File(new Ljava/io/File(p1, "newsoul-profile-bookmarks"), Ljava/lang/String.valueOf(p2.trim().isEmpty()).concat(".bin"));
      this.a = new Ljava/io/File(new Ljava/io/File(p1, "newsoul-profile-bookmarks"), Ljava/lang/String.valueOf(p2.trim().isEmpty()).concat(".bin"));
      return;
      :cond_5e:
      new Ljava/lang/IllegalArgumentException("请先登录 Soul");
      throw new Ljava/lang/IllegalArgumentException("请先登录 Soul");
      :pswitch_66:
      new Ljava/lang/Object();
      if (p2 == 0) goto :cond_cb;
      if (p2.isEmpty() != 0) goto :cond_cb;
      new Ljava/lang/StringBuilder();
      :goto_88:
      if ((v1 + 0x1) >= v0) goto :cond_a4;
      goto :goto_88;
      :cond_a4:
      new Ljava/io/File(p1, "newsoul-voice-library/".concat(Ljava/lang/String.valueOf(p2.isEmpty())));
      this.a = new Ljava/io/File(p1, "newsoul-voice-library/".concat(Ljava/lang/String.valueOf(p2.isEmpty())));
      if (new Ljava/io/File(p1, "newsoul-voice-library/".concat(Ljava/lang/String.valueOf(p2.isEmpty()))).isDirectory() != 0) goto :cond_ca;
      if (new Ljava/io/File(p1, "newsoul-voice-library/".concat(Ljava/lang/String.valueOf(p2.isEmpty()))).mkdirs() == 0) goto :cond_c2;
      goto :goto_ca;
      :cond_c2:
      new Ljava/io/IOException("Cannot create library");
      throw new Ljava/io/IOException("Cannot create library");
      :cond_ca:
      :goto_ca:
      return;
      :cond_cb:
      new Ljava/lang/IllegalArgumentException("Missing account");
      throw new Ljava/lang/IllegalArgumentException("Missing account");
      :pswitch_data_d4:

      // .packed-switch 0x1
      // :pswitch_66
    }

    public static void b(FileOutputStream p0) {
      :goto_7:
      if (p0.read(v0) == -0x1) goto :cond_24;
      if (compare((v3 + ((long) p0.read(v0))), 0x1400000) > 0) goto :cond_1c;
      p1.write(v0, 0x0, p0.read(v0));
      goto :goto_7;
      :cond_1c:
      new Ljava/io/IOException("语音文件超过 20 MB");
      throw new Ljava/io/IOException("语音文件超过 20 MB");
      :cond_24:
      if (compare((v3 + ((long) p0.read(v0))), v1) == 0) goto :cond_29;
      return;
      :cond_29:
      new Ljava/io/IOException("语音文件为空");
      throw new Ljava/io/IOException("语音文件为空");
    }

    public static String c(File p0) {
      new Ljava/io/FileInputStream(p0);
      :try_start_9:
      :try_end_d:
      v1.close();
      new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1);
      if (new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1).startsWith("#!AMR") == 0) goto :cond_27;
      return v0;
      :cond_27:
      if (new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1).startsWith("RIFF") == 0) goto :cond_32;
      return v0;
      :cond_32:
      if (new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1).startsWith("OggS") == 0) goto :cond_3d;
      return v0;
      :cond_3d:
      if (p0 < 0x8) goto :cond_51;
      if (new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1).substring(0x4, 0x8).equals("ftyp") == 0) goto :cond_51;
      return v0;
      :cond_51:
      if (new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1).startsWith("fLaC") == 0) goto :cond_5c;
      return v0;
      :cond_5c:
      if (p0 < 0x2) goto :cond_72;
      if ((v0[0x0] & 0xff) != 0xff) goto :cond_72;
      if ((v0[0x1] & 0xf6) != 0xf0) goto :cond_72;
      return v0;
      :cond_72:
      if (new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1).startsWith("ID3") != 0) goto :cond_8c;
      if (p0 < 0x2) goto :cond_89;
      if ((v0[0x0] & 0xff) != 0xff) goto :cond_89;
      if ((v0[0x1] & 0xe0) != 0xe0) goto :cond_89;
      goto :goto_8c;
      :cond_89:
      return 0xe0;
      :cond_8c:
      :goto_8c:
      return 0xe0;
      :catchall_8f:
      :try_start_90:
      new Ljava/lang/String(v0, 0x0, Ljava/lang/Math.max(0x0, p0), StandardCharsets.ISO_8859_1).startsWith("ID3").close();
      :try_end_93:
      goto :goto_98;
      :catchall_94:
      (v0[0x1] & 0xe0).addSuppressed(e);
      :goto_98:
      throw (v0[0x1] & 0xe0);

      // .catchall {:try_start_9 .. :try_end_d} :catchall_8f
      // .catchall {:try_start_90 .. :try_end_93} :catchall_94
    }

    public static String e(String p0) {
      if (p0 != 0) goto :cond_5;
      goto :goto_9;
      :cond_5:
      :goto_9:
      if ("".isEmpty() != 0) goto :cond_20;
      if ("".length() > 0x78) goto :cond_18;
      return "".length();
      :cond_18:
      new Ljava/lang/IllegalArgumentException("备注最多 120 字");
      throw new Ljava/lang/IllegalArgumentException("备注最多 120 字");
      :cond_20:
      new Ljava/lang/IllegalArgumentException("请填写备注");
      throw new Ljava/lang/IllegalArgumentException("请填写备注");
    }

    public static void j(String p0) {
      if (p0 == 0) goto :cond_1f;
      if (p0.trim().isEmpty() != 0) goto :cond_1f;
      if (p0.length() > 0x200) goto :cond_1f;
      if (p0.equals(p0.trim()) == 0) goto :cond_1f;
      return;
      :cond_1f:
      new Ljava/lang/IllegalArgumentException("主页标识不可用");
      throw new Ljava/lang/IllegalArgumentException("主页标识不可用");
    }

    public synchronized void a(Ljava/io/FileInputStream;Ljava/lang/String;I p0) {
      synchronized (p0) {
      :try_start_1:
      if (p3 <= 0) goto :cond_cc;
      :goto_11:
      if (v0.hasNext() == 0) goto :cond_28;
      goto :goto_11;
      :catchall_25:
      goto :goto_d4;
      :cond_28:
      if (p2.size() >= 0xc8) goto :cond_c4;
      if (compare(((v3 + (h3) v0.next().e.length()) + 0x1400000), 0x1f400000) > 0) goto :cond_c4;
      new Ljava/lang/StringBuilder();
      new Ljava/io/File(p0.a, new Ljava/lang/StringBuilder().toString());
      new Ljava/lang/StringBuilder();
      new Ljava/io/File(p0.a, new Ljava/lang/StringBuilder().toString());
      v8.<init>(p2.size().toString(), Ldev/local/newsoul/x1.e(p2), p3, new Ljava/lang/StringBuilder().toString(), (h3) v0.next().e.length(), new Ljava/io/File(p0.a, new Ljava/lang/StringBuilder().toString()));
      :try_end_7f:
      :try_start_7f:
      new Ljava/io/FileOutputStream(v7);
      :try_end_84:
      :try_start_84:
      Ldev/local/newsoul/x1.b(e, p3);
      p3.getFD().sync();
      :try_end_8e:
      :try_start_8e:
      p3.close();
      new CopyOption[][0x1][0x0] = StandardCopyOption.ATOMIC_MOVE;
      p0.k(v8);
      :try_end_a7:
      }
      return;
      :catchall_a9:
      goto :goto_b5;
      :catchall_ab:
      :try_start_ac:
      p3.close();
      :try_end_af:
      goto :goto_b4;
      :catchall_b0:
      :try_start_b1:
      e.addSuppressed(p3);
      :goto_b4:
      throw e;
      :try_end_b5:
      :goto_b5:
      :try_start_b5:
      throw e;
      :cond_c4:
      new Ljava/io/IOException("语音库已满，请先删除部分语音");
      throw new Ljava/io/IOException("语音库已满，请先删除部分语音");
      :cond_cc:
      new Ljava/lang/IllegalArgumentException("Invalid duration");
      throw new Ljava/lang/IllegalArgumentException("Invalid duration");
      :try_end_d4:
      :goto_d4:
      }
      throw e;

      // .catchall {:try_start_1 .. :try_end_7f} :catchall_25
      // .catchall {:try_start_7f .. :try_end_84} :catchall_a9
      // .catchall {:try_start_84 .. :try_end_8e} :catchall_ab
      // .catchall {:try_start_8e .. :try_end_a7} :catchall_a9
      // .catchall {:try_start_ac .. :try_end_af} :catchall_b0
      // .catchall {:try_start_b1 .. :try_end_b5} :catchall_a9
      // .catchall {:try_start_b5 .. :try_end_d4} :catchall_25
    }

    public synchronized ArrayList d() {
      synchronized (p0) {
      :try_start_1:
      new Ljava/lang/Object();
      if (p0.a.listFiles(new Ljava/lang/Object()) == 0) goto :cond_55;
      new Ljava/util/ArrayList();
      :goto_16:
      if (v4 >= v2) goto :cond_43;
      :try_end_2c:
      :try_start_2c:
      :try_end_33:
      goto :goto_16;
      :catchall_36:
      goto :goto_5d;
      :catch_38:
      goto :goto_3b;
      :catch_3a:
      :goto_3b:
      :try_start_3b:
      new Ljava/io/IOException("语音库记录损坏，请保留数据后排查", e);
      throw new Ljava/io/IOException("语音库记录损坏，请保留数据后排查", e);
      :cond_43:
      new Ljava/lang/Object();
      new Ljava/lang/Object().sort(Ljava/util/Comparator.comparingLong(new Ljava/lang/Object()).reversed());
      :try_end_53:
      }
      return e;
      :cond_55:
      :try_start_55:
      new Ljava/io/IOException("Cannot read library");
      throw new Ljava/io/IOException("Cannot read library");
      :try_end_5d:
      :goto_5d:
      }
      throw e;

      // .catchall {:try_start_1 .. :try_end_2c} :catchall_36
      // try :try_start_2c..:try_end_33 catch (Ljava/lang/IllegalArgumentException) -> :catch_3a
      // try :try_start_2c..:try_end_33 catch (Ljava/io/IOException) -> :catch_38
      // .catchall {:try_start_2c .. :try_end_33} :catchall_36
      // .catchall {:try_start_3b .. :try_end_53} :catchall_36
      // .catchall {:try_start_55 .. :try_end_5d} :catchall_36
    }

    public void f(String p0) {
      Ldev/local/newsoul/x1.j(p1);
      if (p2.trim().isEmpty() != 0) goto :cond_6f;
      if (p2.trim().length() > 0x78) goto :cond_6f;
      synchronized (x1.b) {
      :try_start_18:
      :goto_1e:
      if (v3 >= 0x78.size()) goto :cond_4c;
      if ((w1) 0x78.get(v3).a.equals(p1) == 0) goto :cond_49;
      new Ldev/local/newsoul/w1(p1, p2.trim(), (w1) 0x78.get(v3).c, v5);
      p0.l(0x78);
      }
      return;
      :catchall_47:
      goto :goto_6d;
      :cond_49:
      goto :goto_1e;
      :cond_4c:
      if (0x78.size() >= 0x3e8) goto :cond_65;
      new Ldev/local/newsoul/w1(e, p2.trim(), 0x78.size(), v5);
      0x78.add(v2, new Ldev/local/newsoul/w1(e, p2.trim(), 0x78.size(), v5));
      p0.l(0x78);
      }
      return;
      :cond_65:
      new Ljava/io/IOException("插眼列表已满，请先移除部分记录");
      throw new Ljava/io/IOException("插眼列表已满，请先移除部分记录");
      :goto_6d:
      }
      :try_end_6e:
      throw e;
      :cond_6f:
      new Ljava/lang/IllegalArgumentException("昵称不可用");
      throw new Ljava/lang/IllegalArgumentException("昵称不可用");

      // .catchall {:try_start_18 .. :try_end_6e} :catchall_47
    }

    public synchronized h3 g(String p0) {
      synchronized (p0) {
      if (p1 == 0) goto :cond_92;
      :try_start_7:
      if (p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}") == 0) goto :cond_92;
      new Ljava/io/File(p0.a, p1.concat(".meta"));
      new Ljava/io/FileInputStream(new Ljava/io/File(p0.a, p1.concat(".meta")));
      new Ljava/io/DataInputStream(new Ljava/io/FileInputStream(new Ljava/io/File(p0.a, p1.concat(".meta"))));
      :try_end_24:
      :try_start_24:
      if (p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").readInt() != 0x4e535631    # 8.864103E8f) goto :cond_81;
      new Ljava/io/File(p0.a, p1.concat(".audio"));
      if (p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").readInt() <= 0) goto :cond_79;
      if (p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").readUTF().length() > 0x78) goto :cond_79;
      if (new Ljava/io/File(p0.a, p1.concat(".audio")).isFile() == 0) goto :cond_79;
      if (compare(new Ljava/io/File(p0.a, p1.concat(".audio")).length(), 0x0) <= 0) goto :cond_79;
      if (compare(new Ljava/io/File(p0.a, p1.concat(".audio")).length(), 0x1400000) > 0) goto :cond_79;
      compare(new Ljava/io/File(p0.a, p1.concat(".audio")).length(), 0x1400000).<init>(p1, p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").readUTF(), p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").readInt(), p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").readLong(), v9, new Ljava/io/File(p0.a, p1.concat(".audio")));
      :try_end_70:
      :try_start_70:
      p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").close();
      :try_end_73:
      }
      return compare(new Ljava/io/File(p0.a, p1.concat(".audio")).length(), 0x1400000);
      :catchall_75:
      goto :goto_9a;
      :catchall_77:
      goto :goto_89;
      :cond_79:
      :try_start_79:
      new Ljava/io/IOException("Missing voice file");
      throw new Ljava/io/IOException("Missing voice file");
      :cond_81:
      new Ljava/io/IOException("Unknown voice record");
      throw new Ljava/io/IOException("Unknown voice record");
      :try_end_89:
      :goto_89:
      :try_start_89:
      p1.matches("[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}").close();
      :try_end_8c:
      goto :goto_91;
      :catchall_8d:
      :try_start_8e:
      e.addSuppressed(compare(new Ljava/io/File(p0.a, p1.concat(".audio")).length(), 0x1400000));
      :goto_91:
      throw e;
      :cond_92:
      new Ljava/lang/IllegalArgumentException("Invalid voice id");
      throw new Ljava/lang/IllegalArgumentException("Invalid voice id");
      :try_end_9a:
      :goto_9a:
      }
      throw e;

      // .catchall {:try_start_7 .. :try_end_24} :catchall_75
      // .catchall {:try_start_24 .. :try_end_70} :catchall_77
      // .catchall {:try_start_70 .. :try_end_73} :catchall_75
      // .catchall {:try_start_79 .. :try_end_89} :catchall_77
      // .catchall {:try_start_89 .. :try_end_8c} :catchall_8d
      // .catchall {:try_start_8e .. :try_end_9a} :catchall_75
    }

    public ArrayList h() {
      new Ljava/util/ArrayList();
      if (p0.a.exists() != 0) goto :cond_10;
      return "插眼记录损坏";
      :cond_10:
      new Ljava/io/FileInputStream(p0.a);
      new Ljava/io/DataInputStream(new Ljava/io/FileInputStream(p0.a));
      :try_start_1a:
      :try_end_1e:
      if (p0.a != 0x4e535031    # 8.86312E8f) goto :cond_97;
      :try_start_25:
      if (p0.a.exists().readInt() < 0) goto :cond_8f;
      if (p0.a.exists().readInt() > 0x3e8) goto :cond_8f;
      new Ljava/util/HashSet();
      :goto_35:
      if (v6 >= p0.a.exists().readInt()) goto :cond_7e;
      :try_end_43:
      :try_start_43:
      Ldev/local/newsoul/x1.j(v7);
      :try_end_46:
      :try_start_46:
      if (v8.trim().isEmpty() != 0) goto :cond_71;
      if (v8.length() > 0x78) goto :cond_71;
      if (compare(v9, 0x0) < 0) goto :cond_71;
      if (0x3e8.add(v7) == 0) goto :cond_71;
      new Ldev/local/newsoul/w1(v7, v8, v9, v10);
      goto :goto_35;
      :catchall_6f:
      goto :goto_9d;
      :cond_71:
      new Ljava/io/IOException(e);
      throw new Ljava/io/IOException(e);
      :catch_77:
      new Ljava/io/IOException(e, e);
      throw new Ljava/io/IOException(e, e);
      :cond_7e:
      :try_end_82:
      if (e != -0x1) goto :cond_89;
      p0.a.exists().close();
      return e;
      :cond_89:
      :try_start_89:
      new Ljava/io/IOException("插眼记录格式异常");
      throw new Ljava/io/IOException("插眼记录格式异常");
      :cond_8f:
      new Ljava/io/IOException("插眼记录数量异常");
      throw new Ljava/io/IOException("插眼记录数量异常");
      :cond_97:
      new Ljava/io/IOException("插眼记录格式异常");
      throw new Ljava/io/IOException("插眼记录格式异常");
      :try_end_9d:
      :goto_9d:
      :try_start_9d:
      p0.a.exists().close();
      :try_end_a0:
      goto :goto_a5;
      :catchall_a1:
      e.addSuppressed(e);
      :goto_a5:
      throw e;

      // .catchall {:try_start_1a .. :try_end_1e} :catchall_6f
      // .catchall {:try_start_25 .. :try_end_43} :catchall_6f
      // try :try_start_43..:try_end_46 catch (Ljava/lang/IllegalArgumentException) -> :catch_77
      // .catchall {:try_start_43 .. :try_end_46} :catchall_6f
      // .catchall {:try_start_46 .. :try_end_82} :catchall_6f
      // .catchall {:try_start_89 .. :try_end_9d} :catchall_6f
      // .catchall {:try_start_9d .. :try_end_a0} :catchall_a1
    }

    public void i(String p0) {
      Ldev/local/newsoul/x1.j(p1);
      synchronized (x1.b) {
      :try_start_6:
      new Ldev/local/newsoul/w(0x1, p1);
      if (p0.h().removeIf(new Ldev/local/newsoul/w(0x1, p1)) == 0) goto :cond_19;
      p0.l(p0.h());
      :cond_19:
      }
      return;
      :catchall_1b:
      }
      :try_end_1d:
      throw p0.h().removeIf(new Ldev/local/newsoul/w(0x1, p1));

      // .catchall {:try_start_6 .. :try_end_1d} :catchall_1b
    }

    public void k(h3 p0) {
      new Ljava/lang/StringBuilder();
      new Ljava/io/File(p0.a, new Ljava/lang/StringBuilder().toString());
      :try_start_1a:
      new Ljava/io/FileOutputStream(v0);
      :try_end_1f:
      :try_start_1f:
      new Ljava/io/DataOutputStream(v2);
      :try_end_24:
      :try_start_27:
      v4.writeInt(v5);
      v4.writeUTF(p1.b);
      v4.writeInt(p1.c);
      v4.writeLong(p1.d, v6);
      v4.flush();
      v2.getFD().sync();
      :try_end_43:
      :try_start_43:
      v4.close();
      :try_end_46:
      :try_start_46:
      v2.close();
      new Ljava/lang/StringBuilder();
      new Ljava/io/File(v1, new Ljava/lang/StringBuilder().toString());
      new CopyOption[][0x2][0x0] = StandardCopyOption.ATOMIC_MOVE;
      new CopyOption[][0x2][0x1] = StandardCopyOption.REPLACE_EXISTING;
      :try_end_77:
      return;
      :catchall_7f:
      goto :goto_96;
      :catchall_81:
      goto :goto_8d;
      :catchall_83:
      :try_start_84:
      v4.close();
      :try_end_87:
      goto :goto_8c;
      :catchall_88:
      :try_start_89:
      e.addSuppressed(v1);
      :goto_8c:
      throw e;
      :try_end_8d:
      :goto_8d:
      :try_start_8d:
      v2.close();
      :try_end_90:
      goto :goto_95;
      :catchall_91:
      :try_start_92:
      e.addSuppressed(v1);
      :goto_95:
      throw e;
      :try_end_96:
      :goto_96:
      throw e;

      // .catchall {:try_start_1a .. :try_end_1f} :catchall_7f
      // .catchall {:try_start_1f .. :try_end_24} :catchall_81
      // .catchall {:try_start_27 .. :try_end_43} :catchall_83
      // .catchall {:try_start_43 .. :try_end_46} :catchall_81
      // .catchall {:try_start_46 .. :try_end_77} :catchall_7f
      // .catchall {:try_start_84 .. :try_end_87} :catchall_88
      // .catchall {:try_start_89 .. :try_end_8d} :catchall_81
      // .catchall {:try_start_8d .. :try_end_90} :catchall_91
      // .catchall {:try_start_92 .. :try_end_96} :catchall_7f
    }

    public void l(ArrayList p0) {
      if (p0.a.getParentFile().isDirectory() != 0) goto :cond_1b;
      if (p0.a.getParentFile().mkdirs() == 0) goto :cond_13;
      goto :goto_1b;
      :cond_13:
      new Ljava/io/IOException("无法创建插眼列表");
      throw new Ljava/io/IOException("无法创建插眼列表");
      :cond_1b:
      :goto_1b:
      new Ljava/lang/StringBuilder();
      new Ljava/io/File(p0.a.getParentFile(), new Ljava/lang/StringBuilder().toString());
      :try_start_35:
      new Ljava/io/FileOutputStream(p0.a.getParentFile().mkdirs());
      :try_end_3a:
      :try_start_3a:
      new Ljava/io/DataOutputStream(p0.a.getParentFile());
      :try_end_3f:
      :try_start_42:
      v3.writeInt(v4);
      v3.writeInt(p1.size());
      :goto_50:
      if (p1.hasNext() == 0) goto :cond_6e;
      v3.writeUTF((w1) p1.next().a);
      v3.writeUTF((w1) p1.next().b);
      v3.writeLong((w1) p1.next().c, (w1) p1.next().b);
      goto :goto_50;
      :catchall_6c:
      goto :goto_a2;
      :cond_6e:
      v3.flush();
      p0.a.getParentFile().getFD().sync();
      :try_end_78:
      :try_start_78:
      v3.close();
      :try_end_7b:
      :try_start_7b:
      p0.a.getParentFile().close();
      new CopyOption[][0x2][0x0] = StandardCopyOption.ATOMIC_MOVE;
      new CopyOption[][0x2][0x1] = StandardCopyOption.REPLACE_EXISTING;
      :try_end_96:
      return;
      :catchall_9e:
      goto :goto_b4;
      :catchall_a0:
      goto :goto_ab;
      :goto_a2:
      :try_start_a2:
      v3.close();
      :try_end_a5:
      goto :goto_aa;
      :catchall_a6:
      :try_start_a7:
      e.addSuppressed(p0.a);
      :goto_aa:
      throw e;
      :try_end_ab:
      :goto_ab:
      :try_start_ab:
      p0.a.getParentFile().close();
      :try_end_ae:
      goto :goto_b3;
      :catchall_af:
      :try_start_b0:
      e.addSuppressed(p0.a);
      :goto_b3:
      throw e;
      :try_end_b4:
      :goto_b4:
      throw e;

      // .catchall {:try_start_35 .. :try_end_3a} :catchall_9e
      // .catchall {:try_start_3a .. :try_end_3f} :catchall_a0
      // .catchall {:try_start_42 .. :try_end_78} :catchall_6c
      // .catchall {:try_start_78 .. :try_end_7b} :catchall_a0
      // .catchall {:try_start_7b .. :try_end_96} :catchall_9e
      // .catchall {:try_start_a2 .. :try_end_a5} :catchall_a6
      // .catchall {:try_start_a7 .. :try_end_ab} :catchall_a0
      // .catchall {:try_start_ab .. :try_end_ae} :catchall_af
      // .catchall {:try_start_b0 .. :try_end_b4} :catchall_9e
    }

}