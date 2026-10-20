package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/T0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class T0 extends Ljava/lang/Object {

    public static final Pattern a;
    public static final Pattern b;

    static void static {}() {
      T0.a = Ljava/util/regex/Pattern.compile("[0-9]{1,9}\\\\.[0-9]{1,9}\\\\.[0-9]{1,9}");
      T0.b = Ljava/util/regex/Pattern.compile("new-soul-([0-9]{1,9}\\\\.[0-9]{1,9}\\\\.[0-9]{1,9})\\\\.zip");
      return;
    }

    public static String a(String p0) {
      if (Ljava/util/regex/Pattern.compile(p1).matcher(p0).find() == 0) goto :cond_14;
      return v0;
      :cond_14:
      new Ljava/io/IOException("发布页格式已变化，请直接打开发布页");
      throw new Ljava/io/IOException("发布页格式已变化，请直接打开发布页");
    }

    public static Y b() {
      new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1);
      new Ljava/lang/StringBuilder("=");
      if (Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]").endsWith(new Ljava/lang/StringBuilder("=").toString()) == 0) goto :cond_1f5;
      new Ljava/lang/StringBuilder("lx=2&fid=");
      new Ljava/lang/StringBuilder(new Ljava/lang/StringBuilder("lx=2&fid=").toString());
      :goto_64:
      if (v9 >= 0x7) goto :cond_e2;
      new Ljava/lang/StringBuilder("[\\'\\\"]");
      if (Ldev/local/newsoul/T0.a(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "data\\\\s*:\\\\s*\\\\{([^}]{1,4096})\\\\}"), new Ljava/lang/StringBuilder("[\\'\\\"]").toString()).trim().matches("[\\'\\\"][A-Za-z0-9_=-]{1,256}[\\'\\\"]") == 0) goto :cond_97;
      goto :goto_c9;
      :cond_97:
      if (Ldev/local/newsoul/T0.a(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "data\\\\s*:\\\\s*\\\\{([^}]{1,4096})\\\\}"), new Ljava/lang/StringBuilder("[\\'\\\"]").toString()).trim().substring(0x1, (Ldev/local/newsoul/T0.a(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "data\\\\s*:\\\\s*\\\\{([^}]{1,4096})\\\\}"), new Ljava/lang/StringBuilder("[\\'\\\"]").toString()).trim().length() - 0x1)).matches("[0-9]{1,12}") != 0) goto :cond_c9;
      if (Ldev/local/newsoul/T0.a(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "data\\\\s*:\\\\s*\\\\{([^}]{1,4096})\\\\}"), new Ljava/lang/StringBuilder("[\\'\\\"]").toString()).trim().substring(0x1, (Ldev/local/newsoul/T0.a(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "data\\\\s*:\\\\s*\\\\{([^}]{1,4096})\\\\}"), new Ljava/lang/StringBuilder("[\\'\\\"]").toString()).trim().length() - 0x1)).matches("[A-Za-z_$][A-Za-z0-9_$]{0,63}") == 0) goto :cond_c3;
      new Ljava/lang/StringBuilder("\\\\bvar\\\\s+");
      goto :goto_c9;
      :cond_c3:
      new Ljava/io/IOException("发布页格式已变化");
      throw new Ljava/io/IOException("发布页格式已变化");
      :cond_c9:
      :goto_c9:
      goto :goto_64;
      :cond_e2:
      new Ljava/util/HashSet();
      :goto_ed:
      if (Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "data\\\\s*:\\\\s*\\\\{([^}]{1,4096})\\\\}") > 0x14) goto :cond_1ed;
      new Ljava/lang/StringBuilder();
      new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString()));
      if ("2".equals(Ljava/lang/String.valueOf(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).get("zt"))) == 0) goto :cond_136;
      if (0x0 == 0) goto :cond_130;
      goto :goto_1d2;
      :cond_130:
      new Ljava/io/IOException("发布目录中没有可识别的安装包");
      throw new Ljava/io/IOException("发布目录中没有可识别的安装包");
      :cond_136:
      if ("1".equals(Ljava/lang/String.valueOf(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).get("zt"))) == 0) goto :cond_1e5;
      if (new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").length() > 0x32) goto :cond_1dd;
      if (new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString()) == 0) goto :cond_1dd;
      :goto_157:
      if (new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString()) >= new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").length()) goto :cond_1ca;
      if ("1".equals(Ljava/lang/String.valueOf(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").getJSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString())).get("t"))) == 0) goto :cond_172;
      goto :goto_1b7;
      :cond_172:
      if ("0".equals(Ljava/lang/String.valueOf(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").getJSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString())).get("t"))) == 0) goto :cond_1c2;
      if (T0.b.matcher(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").getJSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString())).getString("name_all")).matches() != 0) goto :cond_18d;
      goto :goto_1b7;
      :cond_18d:
      if (new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").getJSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString())).getString("id").matches("i[A-Za-z0-9]{5,63}") == 0) goto :cond_1ba;
      new Ldev/local/newsoul/Y(T0.b.matcher(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").getJSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString())).getString("name_all")).group(0x26), "https://wwbnj.lanzoul.com/".concat(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").getJSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString())).getString("id")));
      if (0x0 == 0) goto :cond_1b6;
      if (Ldev/local/newsoul/T0.c(T0.b.matcher(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").getJSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString())).getString("name_all")).group(0x26), (String) 0x0.a) <= 0) goto :cond_1b7;
      :cond_1b6:
      :cond_1b7:
      :goto_1b7:
      goto :goto_157;
      :cond_1ba:
      new Ljava/io/IOException("安装包链接格式异常");
      throw new Ljava/io/IOException("安装包链接格式异常");
      :cond_1c2:
      new Ljava/io/IOException("发布目录格式已变化");
      throw new Ljava/io/IOException("发布目录格式已变化");
      :cond_1ca:
      if (new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").length() >= 0x32) goto :cond_1d9;
      if (0x0 == 0) goto :cond_1d3;
      :goto_1d2:
      return new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0);
      :cond_1d3:
      new Ljava/io/IOException("发布目录中没有可识别的安装包");
      throw new Ljava/io/IOException("发布目录中没有可识别的安装包");
      :cond_1d9:
      goto :goto_ed;
      :cond_1dd:
      new Ljava/io/IOException("发布目录分页异常");
      throw new Ljava/io/IOException("发布目录分页异常");
      :cond_1e5:
      new Ljava/io/IOException("发布目录暂时无法读取，请稍后重试");
      throw new Ljava/io/IOException("发布目录暂时无法读取，请稍后重试");
      :cond_1ed:
      new Ljava/io/IOException("发布目录文件过多，请直接打开发布页");
      throw new Ljava/io/IOException("发布目录文件过多，请直接打开发布页");
      :cond_1f5:
      new Ljava/io/IOException((new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString()) + 0x1));
      throw new Ljava/io/IOException((new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0).add(new Lorg/json/JSONObject(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com".concat(Ldev/local/newsoul/T0.a(new Ldev/local/newsoul/S0((__result__ + 0xa7a358200L), v1).a("https://wwbnj.lanzoul.com/b007utki2d", 0x0), "url\\\\s*:\\\\s*[\\'\\\"](/filemoreajax\\\\.php\\\\?file=[0-9]{1,12})[\\'\\\"]")), new Ljava/lang/StringBuilder().toString())).getJSONArray("text").toString()) + 0x1));
    }

    public static int c(String p0) {
      if (T0.a.matcher(p0).matches() == 0) goto :cond_3e;
      if (T0.a.matcher(p1).matches() == 0) goto :cond_3e;
      :goto_22:
      if (T0.a.matcher(p0).matches() >= 0x3) goto :cond_3d;
      new Ljava/math/BigInteger(p0[T0.a.matcher(p0).matches()]);
      new Ljava/math/BigInteger(p1[T0.a.matcher(p0).matches()]);
      if (new Ljava/math/BigInteger(p0[T0.a.matcher(p0).matches()]).compareTo(new Ljava/math/BigInteger(p1[T0.a.matcher(p0).matches()])) == 0) goto :cond_3a;
      return T0.a.matcher(p1).matches();
      :cond_3a:
      goto :goto_22;
      :cond_3d:
      return T0.a.matcher(p1).matches();
      :cond_3e:
      new Ljava/lang/IllegalArgumentException("无法识别版本号");
      throw new Ljava/lang/IllegalArgumentException("无法识别版本号");
    }

}