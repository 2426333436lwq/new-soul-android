package dev.local.newsoul;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {
    public static final LinkedHashMap a = new LinkedHashMap();
    public static final LinkedHashMap b = new LinkedHashMap();

    static {
        for (String str : "block_splash game_dice game_finger chat_anti_recall chat_own_recall_no_time_limit chat_remove_gift_gate chat_remove_bell_gift_gate post_remove_share_limit chat_remove_social_score_limit chat_hide_typing match_mute_background_music download_image_no_watermark download_video_no_watermark square_visible_like page_log startup_toast".split(" ")) {
            a.put(str, Boolean.TRUE);
        }
        for (String str2 : "ad_feed ad_detail ad_video ad_tag ad_tag_web ad_search ad_group ad_planet ad_me ad_conversation ad_envelope ad_notice ad_chat_tools ad_float ad_interstitial ad_reward_chat ad_reward_drama ad_reward_web privacy_allow_download privacy_allow_screenshot privacy_allow_repeat feed_filter_enabled feed_filter_keep_unknown feed_keywords_enabled_v1 square_like_all_loaded square_auto_like".split(" ")) {
            a.put(str2, Boolean.FALSE);
        }
        LinkedHashMap linkedHashMap = a;
        linkedHashMap.put("feed_filter_gender", "FEMALE");
        linkedHashMap.put("feed_keywords_v1", "");
        linkedHashMap.put("chat_recall_style", "below");
        String[] strArr = {"profile_disguise_follow_", "profile_disguise_followed_", "profile_disguise_visitor_", "profile_disguise_days_"};
        for (int i = 0; i < 4; i++) {
            String str3 = strArr[i];
            LinkedHashMap linkedHashMap2 = b;
            linkedHashMap2.put(str3 + "enabled", Boolean.FALSE);
            linkedHashMap2.put(str3 + "mode", "absolute");
            linkedHashMap2.put(str3 + "absolute", 0);
            linkedHashMap2.put(str3 + "delta", 0);
        }
        LinkedHashMap linkedHashMap3 = b;
        linkedHashMap3.put("ai_reply_enabled_v1", Boolean.TRUE);
        linkedHashMap3.put("ai_reply_auto_image_threshold_v1", 0);
        linkedHashMap3.put("ai_reply_profile_v1", "");
        linkedHashMap3.put("ai_reply_model_v1", "");
    }

    public static String a(Map map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : a.entrySet()) {
            Object value = map.containsKey(entry.getKey()) ? map.get(entry.getKey()) : entry.getValue();
            String str = (String) entry.getKey();
            d((String) entry.getKey(), value, entry.getValue());
            jSONObject.put(str, value);
        }
        for (Map.Entry entry2 : b.entrySet()) {
            if (map.containsKey(entry2.getKey())) {
                String str2 = (String) entry2.getKey();
                String str3 = (String) entry2.getKey();
                Object obj = map.get(entry2.getKey());
                d(str3, obj, entry2.getValue());
                jSONObject.put(str2, obj);
            }
        }
        String string = new JSONObject().put("format", "newsoul-config").put("schemaVersion", 1).put("settings", jSONObject).toString(2);
        if (string.getBytes(StandardCharsets.UTF_8).length <= 131072) {
            return string;
        }
        throw b();
    }

    public static IllegalArgumentException b() {
        return new IllegalArgumentException("配置格式或内容无效");
    }

    public static Map c(String str) throws JSONException {
        if (str == null || str.getBytes(StandardCharsets.UTF_8).length > 131072) {
            throw b();
        }
        JSONTokener jSONTokener = new JSONTokener(str);
        Object objNextValue = jSONTokener.nextValue();
        if (!(objNextValue instanceof JSONObject) || jSONTokener.nextClean() != 0) {
            throw b();
        }
        JSONObject jSONObject = (JSONObject) objNextValue;
        if ("newsoul-config".equals(jSONObject.opt("format"))) {
            Integer num = 1;
            if (num.equals(jSONObject.opt("schemaVersion"))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("settings");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    LinkedHashMap linkedHashMap2 = a;
                    if (!linkedHashMap2.containsKey(next)) {
                        linkedHashMap2 = b;
                    }
                    Object obj = linkedHashMap2.get(next);
                    if (obj != null) {
                        Object obj2 = jSONObject2.get(next);
                        d(next, obj2, obj);
                        linkedHashMap.put(next, obj2);
                    }
                }
                if (linkedHashMap.isEmpty()) {
                    throw b();
                }
                return Collections.unmodifiableMap(linkedHashMap);
            }
        }
        throw b();
    }

    public static void d(String str, Object obj, Object obj2) {
        int iIntValue;
        if ((obj2 instanceof Boolean) && !(obj instanceof Boolean)) {
            throw b();
        }
        if ((obj2 instanceof String) && !(obj instanceof String)) {
            throw b();
        }
        if ((obj2 instanceof Integer) && !(obj instanceof Integer)) {
            throw b();
        }
        if ((obj instanceof String) && ((String) obj).length() > 24000) {
            throw b();
        }
        if (str.startsWith("profile_disguise_")) {
            if (str.endsWith("mode") && !Arrays.asList("absolute", "delta").contains(obj)) {
                throw b();
            }
            if (str.endsWith("absolute") && ((Integer) obj).intValue() < 0) {
                throw b();
            }
        }
        if (str.equals("feed_keywords_v1")) {
            String str2 = (String) obj;
            if (str2 == null) {
                str2 = "";
            }
            if (str2.length() > 12000) {
                throw new IllegalArgumentException("屏蔽词内容过长");
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (String str3 : str2.split("\\R")) {
                String lowerCase = str3.trim().toLowerCase(Locale.ROOT);
                if (!lowerCase.isEmpty()) {
                    if (lowerCase.length() > 100) {
                        throw new IllegalArgumentException("每个屏蔽词最多100个字符");
                    }
                    linkedHashSet.add(lowerCase);
                }
            }
            if (linkedHashSet.size() > 100) {
                throw new IllegalArgumentException("最多保存100个屏蔽词");
            }
            Collections.unmodifiableList(new ArrayList(linkedHashSet));
        }
        if (str.equals("feed_filter_gender") && !Arrays.asList("ANY", "FEMALE", "MALE").contains(obj)) {
            throw b();
        }
        if (str.equals("chat_recall_style") && !Arrays.asList("below", "nickname").contains(obj)) {
            throw b();
        }
        if (!str.equals("ai_reply_auto_image_threshold_v1") || (iIntValue = ((Integer) obj).intValue()) == 0) {
            return;
        }
        if (iIntValue < 2 || iIntValue > 5) {
            throw b();
        }
    }
}
