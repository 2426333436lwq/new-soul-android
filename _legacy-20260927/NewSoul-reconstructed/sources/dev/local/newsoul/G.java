/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONObject
 *  org.json.JSONTokener
 */
package dev.local.newsoul;

import java.nio.charset.StandardCharsets;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class G {
    public static final LinkedHashMap a;
    public static final LinkedHashMap b;

    static {
        Object object;
        int n;
        a = new LinkedHashMap();
        b = new LinkedHashMap();
        String[] object22 = "block_splash game_dice game_finger chat_anti_recall chat_own_recall_no_time_limit chat_remove_gift_gate chat_remove_bell_gift_gate post_remove_share_limit chat_remove_social_score_limit chat_hide_typing match_mute_background_music download_image_no_watermark download_video_no_watermark square_visible_like page_log startup_toast".split(" ");
        int n2 = object22.length;
        int n3 = 0;
        Integer n4 = 0;
        for (n = 0; n < n2; ++n) {
            object = object22[n];
            a.put(object, Boolean.TRUE);
        }
        for (String string : "ad_feed ad_detail ad_video ad_tag ad_tag_web ad_search ad_group ad_planet ad_me ad_conversation ad_envelope ad_notice ad_chat_tools ad_float ad_interstitial ad_reward_chat ad_reward_drama ad_reward_web privacy_allow_download privacy_allow_screenshot privacy_allow_repeat feed_filter_enabled feed_filter_keep_unknown feed_keywords_enabled_v1 square_like_all_loaded square_auto_like".split(" ")) {
            a.put(string, Boolean.FALSE);
        }
        object = a;
        object.put("feed_filter_gender", "FEMALE");
        object.put("feed_keywords_v1", "");
        object.put("chat_recall_style", "below");
        for (n = n3; n < 4; ++n) {
            String string = (new String[]{"profile_disguise_follow_", "profile_disguise_followed_", "profile_disguise_visitor_", "profile_disguise_days_"})[n];
            object = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(string);
            stringBuilder.append("enabled");
            object.put(stringBuilder.toString(), Boolean.FALSE);
            stringBuilder = new StringBuilder();
            stringBuilder.append(string);
            stringBuilder.append("mode");
            object.put(stringBuilder.toString(), "absolute");
            stringBuilder = new StringBuilder();
            stringBuilder.append(string);
            stringBuilder.append("absolute");
            object.put(stringBuilder.toString(), n4);
            stringBuilder = new StringBuilder();
            stringBuilder.append(string);
            stringBuilder.append("delta");
            object.put(stringBuilder.toString(), n4);
        }
        object = b;
        object.put("ai_reply_enabled_v1", Boolean.TRUE);
        object.put("ai_reply_auto_image_threshold_v1", n4);
        object.put("ai_reply_profile_v1", "");
        object.put("ai_reply_model_v1", "");
    }

    public static String a(Map object) {
        Object object2;
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry2 : a.entrySet()) {
            object2 = object.containsKey(entry2.getKey()) ? object.get(entry2.getKey()) : entry2.getValue();
            String string = (String)entry2.getKey();
            G.d((String)entry2.getKey(), object2, entry2.getValue());
            jSONObject.put(string, object2);
        }
        for (Map.Entry entry : b.entrySet()) {
            Map.Entry entry2;
            if (!object.containsKey(entry.getKey())) continue;
            String string = (String)entry.getKey();
            object2 = (String)entry.getKey();
            entry2 = object.get(entry.getKey());
            G.d(object2, entry2, entry.getValue());
            jSONObject.put(string, entry2);
        }
        object = new JSONObject().put("format", (Object)"newsoul-config").put("schemaVersion", 1).put("settings", (Object)jSONObject).toString(2);
        if (((String)object).getBytes(StandardCharsets.UTF_8).length <= 131072) {
            return object;
        }
        throw G.b();
    }

    public static IllegalArgumentException b() {
        return new IllegalArgumentException("\u914d\u7f6e\u683c\u5f0f\u6216\u5185\u5bb9\u65e0\u6548");
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Map c(String object) {
        Iterator iterator;
        LinkedHashMap<String, Object> linkedHashMap;
        JSONTokener jSONTokener;
        block7: {
            if (object != null && ((String)object).getBytes(StandardCharsets.UTF_8).length <= 131072) {
                jSONTokener = new JSONTokener((String)object);
                if ((object = jSONTokener.nextValue()) instanceof JSONObject && jSONTokener.nextClean() == '\u0000') {
                    if ("newsoul-config".equals((object = (JSONObject)object).opt("format")) && Integer.valueOf(1).equals(object.opt("schemaVersion"))) {
                        jSONTokener = object.getJSONObject("settings");
                        linkedHashMap = new LinkedHashMap<String, Object>();
                        iterator = jSONTokener.keys();
                        break block7;
                    } else {
                        throw G.b();
                    }
                }
                throw G.b();
            }
            throw G.b();
        }
        while (iterator.hasNext()) {
            Object v;
            object = a;
            String string = (String)iterator.next();
            if (!object.containsKey(string)) {
                object = b;
            }
            if ((v = ((LinkedHashMap)object).get(string)) == null) continue;
            object = jSONTokener.get(string);
            G.d(string, object, v);
            linkedHashMap.put(string, object);
        }
        if (!linkedHashMap.isEmpty()) {
            return Collections.unmodifiableMap(linkedHashMap);
        }
        throw G.b();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void d(String string, Object object, Object object2) {
        int n;
        if (object2 instanceof Boolean && !(object instanceof Boolean)) {
            throw G.b();
        }
        if (object2 instanceof String && !(object instanceof String)) {
            throw G.b();
        }
        if (object2 instanceof Integer && !(object instanceof Integer)) {
            throw G.b();
        }
        if (object instanceof String && ((String)object).length() > 24000) {
            throw G.b();
        }
        if (string.startsWith("profile_disguise_")) {
            if (string.endsWith("mode") && !Arrays.asList("absolute", "delta").contains(object)) {
                throw G.b();
            }
            if (string.endsWith("absolute") && (Integer)object < 0) {
                throw G.b();
            }
        }
        if (string.equals("feed_keywords_v1")) {
            Object object3;
            object2 = object3 = (String)object;
            if (object3 == null) {
                object2 = "";
            }
            if (((String)object2).length() > 12000) throw new IllegalArgumentException("\u5c4f\u853d\u8bcd\u5185\u5bb9\u8fc7\u957f");
            object3 = new LinkedHashSet();
            object2 = ((String)object2).split("\\R");
            int n2 = ((Object)object2).length;
            for (n = 0; n < n2; ++n) {
                String string2 = ((String)object2[n]).trim().toLowerCase(Locale.ROOT);
                if (string2.isEmpty()) continue;
                if (string2.length() > 100) throw new IllegalArgumentException("\u6bcf\u4e2a\u5c4f\u853d\u8bcd\u6700\u591a100\u4e2a\u5b57\u7b26");
                ((AbstractCollection)object3).add(string2);
            }
            if (((AbstractCollection)object3).size() > 100) throw new IllegalArgumentException("\u6700\u591a\u4fdd\u5b58100\u4e2a\u5c4f\u853d\u8bcd");
            Collections.unmodifiableList(new ArrayList(object3));
        }
        if (string.equals("feed_filter_gender") && !Arrays.asList("ANY", "FEMALE", "MALE").contains(object)) {
            throw G.b();
        }
        if (string.equals("chat_recall_style") && !Arrays.asList("below", "nickname").contains(object)) {
            throw G.b();
        }
        if (!string.equals("ai_reply_auto_image_threshold_v1") || (n = ((Integer)object).intValue()) == 0 || n >= 2 && n <= 5) return;
        throw G.b();
    }
}

