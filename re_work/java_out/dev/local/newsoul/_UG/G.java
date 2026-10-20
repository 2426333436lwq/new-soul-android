package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/G ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class G extends Ljava/lang/Object {

    public static final LinkedHashMap a;
    public static final LinkedHashMap b;

    static void static {}() {
      new Ljava/util/LinkedHashMap();
      G.a = new Ljava/util/LinkedHashMap();
      new Ljava/util/LinkedHashMap();
      G.b = new Ljava/util/LinkedHashMap();
      :goto_1d:
      if (v5 >= v2) goto :cond_2b;
      goto :goto_1d;
      :cond_2b:
      :goto_33:
      if (v2 >= v1) goto :cond_41;
      goto :goto_33;
      :cond_41:
      :goto_64:
      if (v3 >= 0x4) goto :cond_c0;
      new Ljava/lang/StringBuilder();
      new Ljava/lang/StringBuilder();
      new Ljava/lang/StringBuilder();
      new Ljava/lang/StringBuilder();
      goto :goto_64;
      :cond_c0:
      return;
    }

    public static String a(Map p0) {
      new Lorg/json/JSONObject();
      :goto_f:
      if (v1.hasNext() == 0) goto :cond_49;
      if (p0.containsKey((Map$Entry) v1.next().getKey()) == 0) goto :cond_2e;
      goto :goto_32;
      :cond_2e:
      :goto_32:
      Ldev/local/newsoul/G.d((String) (Map$Entry) v1.next().getKey(), p0.get((Map$Entry) v1.next().getKey()), (Map$Entry) v1.next().getValue());
      goto :goto_f;
      :cond_49:
      :cond_53:
      :goto_53:
      if (v1.hasNext() == 0) goto :cond_88;
      if (p0.containsKey((Map$Entry) v1.next().getKey()) == 0) goto :cond_53;
      Ldev/local/newsoul/G.d((String) (Map$Entry) v1.next().getKey(), p0.get((Map$Entry) v1.next().getKey()), (Map$Entry) v1.next().getValue());
      goto :goto_53;
      :cond_88:
      new Lorg/json/JSONObject();
      if (new Lorg/json/JSONObject().put("format", "newsoul-config").put("schemaVersion", 0x1).put("settings", v0).toString(0x2).getBytes(StandardCharsets.UTF_8).length > 0x20000) goto :cond_b3;
      return new Lorg/json/JSONObject().put("format", "newsoul-config").put("schemaVersion", 0x1).put("settings", v0).toString(0x2).getBytes(StandardCharsets.UTF_8).length;
      :cond_b3:
      throw new Lorg/json/JSONObject().put("format", "newsoul-config").put("schemaVersion", 0x1).put("settings", v0).toString(0x2).getBytes(StandardCharsets.UTF_8);
    }

    public static IllegalArgumentException b() {
      new Ljava/lang/IllegalArgumentException("配置格式或内容无效");
      return new Ljava/lang/IllegalArgumentException("配置格式或内容无效");
    }

    public static Map c(String p0) {
      if (p0 == 0) goto :cond_93;
      if (p0.getBytes(StandardCharsets.UTF_8).length > 0x20000) goto :cond_93;
      new Lorg/json/JSONTokener(p0);
      if ((new Lorg/json/JSONTokener(p0).nextValue() instanceof JSONObject) == 0) goto :cond_8e;
      if (new Lorg/json/JSONTokener(p0).nextClean() != 0) goto :cond_8e;
      if ("newsoul-config".equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("format")) == 0) goto :cond_89;
      if (Ljava/lang/Integer.valueOf(0x1).equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion")) == 0) goto :cond_89;
      new Ljava/util/LinkedHashMap();
      :cond_50:
      :goto_50:
      if ((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion").hasNext() == 0) goto :cond_79;
      if (G.a.containsKey((String) (JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion").next()) == 0) goto :cond_69;
      :goto_64:
      goto :goto_6c;
      :cond_69:
      goto :goto_64;
      :goto_6c:
      if (G.b == 0) goto :cond_50;
      Ldev/local/newsoul/G.d((String) (JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion").next(), (JSONObject) new Lorg/json/JSONTokener(p0).nextValue().get((String) (JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion").next()), G.b);
      goto :goto_50;
      :cond_79:
      if (Ljava/lang/Integer.valueOf(0x1).equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion")).isEmpty() != 0) goto :cond_84;
      return Ljava/lang/Integer.valueOf(0x1).equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion"));
      :cond_84:
      throw Ljava/lang/Integer.valueOf(0x1).equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion")).isEmpty();
      :cond_89:
      throw Ljava/lang/Integer.valueOf(0x1).equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion")).isEmpty();
      :cond_8e:
      throw Ljava/lang/Integer.valueOf(0x1).equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion")).isEmpty();
      :cond_93:
      throw Ljava/lang/Integer.valueOf(0x1).equals((JSONObject) new Lorg/json/JSONTokener(p0).nextValue().opt("schemaVersion")).isEmpty();
    }

    public static void d(Object p0) {
      if ((p2 instanceof Boolean) == 0) goto :cond_e;
      if ((p1 instanceof Boolean) == 0) goto :cond_9;
      goto :goto_e;
      :cond_9:
      throw __result__;
      :cond_e:
      :goto_e:
      if ((p2 instanceof String) == 0) goto :cond_1c;
      if ((p1 instanceof String) == 0) goto :cond_17;
      goto :goto_1c;
      :cond_17:
      throw __result__;
      :cond_1c:
      :goto_1c:
      if ((p2 instanceof Integer) == 0) goto :cond_2a;
      if ((p1 instanceof Integer) == 0) goto :cond_25;
      goto :goto_2a;
      :cond_25:
      throw __result__;
      :cond_2a:
      :goto_2a:
      if ((p1 instanceof String) == 0) goto :cond_3f;
      if ((String) p1.length() > 0x5dc0) goto :cond_3a;
      goto :goto_3f;
      :cond_3a:
      throw (String) p1.length();
      :cond_3f:
      :goto_3f:
      if (p0.startsWith("profile_disguise_") == 0) goto :cond_7c;
      if (p0.endsWith("mode") == 0) goto :cond_67;
      if (Ljava/util/Arrays.asList(new String[][] {"absolute", "delta"}).contains(p1) == 0) goto :cond_62;
      goto :goto_67;
      :cond_62:
      throw Ljava/util/Arrays.asList(new String[][] {"absolute", "delta"}).contains(p1);
      :cond_67:
      :goto_67:
      if (p0.endsWith("absolute") == 0) goto :cond_7c;
      if ((Integer) p1.intValue() < 0) goto :cond_77;
      goto :goto_7c;
      :cond_77:
      throw (Integer) p1.intValue();
      :cond_7c:
      :goto_7c:
      if (p0.equals("feed_keywords_v1") == 0) goto :cond_ea;
      if ((String) p1 != 0) goto :cond_8b;
      :cond_8b:
      if ((String) p1.length() > 0x2ee0) goto :cond_e2;
      new Ljava/util/LinkedHashSet();
      :goto_a0:
      if (v2 >= 0x2ee0) goto :cond_cb;
      if ((String) p1[v2].trim().toLowerCase(Locale.ROOT).isEmpty() == 0) goto :cond_b7;
      goto :goto_c0;
      :cond_b7:
      if ((String) p1[v2].trim().toLowerCase(Locale.ROOT).length() > 0x64) goto :cond_c3;
      :goto_c0:
      goto :goto_a0;
      :cond_c3:
      new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      throw new Ljava/lang/IllegalArgumentException("每个屏蔽词最多100个字符");
      :cond_cb:
      if ((String) p1.length().size() > 0x64) goto :cond_da;
      new Ljava/util/ArrayList((String) p1.length());
      goto :goto_ea;
      :cond_da:
      new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      throw new Ljava/lang/IllegalArgumentException("最多保存100个屏蔽词");
      :cond_e2:
      new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      throw new Ljava/lang/IllegalArgumentException("屏蔽词内容过长");
      :cond_ea:
      :goto_ea:
      if (p0.equals("feed_filter_gender") == 0) goto :cond_10c;
      if (Ljava/util/Arrays.asList(new String[][] {"ANY", "FEMALE", "MALE"}).contains(p1) == 0) goto :cond_107;
      goto :goto_10c;
      :cond_107:
      throw Ljava/util/Arrays.asList(new String[][] {"ANY", "FEMALE", "MALE"}).contains(p1);
      :cond_10c:
      :goto_10c:
      if (p0.equals("chat_recall_style") == 0) goto :cond_12c;
      if (Ljava/util/Arrays.asList(new String[][] {"below", "nickname"}).contains(p1) == 0) goto :cond_127;
      goto :goto_12c;
      :cond_127:
      throw Ljava/util/Arrays.asList(new String[][] {"below", "nickname"}).contains(p1);
      :cond_12c:
      :goto_12c:
      if (p0.equals("ai_reply_auto_image_threshold_v1") == 0) goto :cond_148;
      if ((Integer) p1.intValue() == 0) goto :cond_148;
      if ((Integer) p1.intValue() < 0x2) goto :cond_143;
      if ((Integer) p1.intValue() > 0x5) goto :cond_143;
      goto :goto_148;
      :cond_143:
      throw (Integer) p1.intValue();
      :cond_148:
      :goto_148:
      return;
    }

}