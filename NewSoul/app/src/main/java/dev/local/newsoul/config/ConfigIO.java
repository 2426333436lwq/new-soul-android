package dev.local.newsoul.config;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 配置的 JSON 导入 / 导出。
 *
 * <p>文件格式（与原模块完全一致）：</p>
 * <pre>
 * {
 *   "format": "newsoul-config",
 *   "schemaVersion": 1,
 *   "settings": { ... }
 * }
 * </pre>
 *
 * <p>导出时对每个键做类型与取值校验；导入时跳过未知键，并对每个已知键重新校验，
 * 任一键非法即整体拒绝（抛出 {@link #invalid()}）。</p>
 */
public final class ConfigIO {

    /** 文件标识，必须匹配才认为是本模块的配置文件。 */
    public static final String FORMAT = "newsoul-config";

    /** 当前配置结构版本。 */
    public static final int SCHEMA_VERSION = 1;

    /** 整个配置文件（UTF-8）的字节上限：128 KiB。 */
    private static final int MAX_CONFIG_BYTES = 0x20000;

    /** 单个字符串值的长度上限：24000 字符。 */
    private static final int MAX_STRING_LENGTH = 0x5dc0;

    /** 屏蔽词原文长度上限：12000 字符。 */
    private static final int MAX_KEYWORDS_RAW_LENGTH = 0x2ee0;

    private static final int MAX_KEYWORD_LENGTH = 100;
    private static final int MAX_KEYWORD_COUNT = 100;

    private static final List<String> GENDERS = Arrays.asList(
            SettingsKeys.GENDER_ANY, SettingsKeys.GENDER_FEMALE, SettingsKeys.GENDER_MALE);

    private static final List<String> RECALL_STYLES = Arrays.asList(
            SettingsKeys.RECALL_STYLE_BELOW, SettingsKeys.RECALL_STYLE_NICKNAME);

    private static final List<String> DISGUISE_MODES = Arrays.asList(
            SettingsKeys.DISGUISE_MODE_ABSOLUTE, SettingsKeys.DISGUISE_MODE_DELTA);

    private ConfigIO() {
    }

    /** 统一的「配置无效」异常。 */
    public static IllegalArgumentException invalid() {
        return new IllegalArgumentException("配置格式或内容无效");
    }

    /**
     * 将设置映射序列化为 JSON 文本。
     *
     * <p>默认值表（{@link SettingDefaults}）中的键全部输出（缺失则用默认值）；
     * 资料页伪装与 AI 回复相关的扩展键仅在映射中存在时输出。</p>
     */
    public static String export(Map<String, Object> values) {
        try {
            JSONObject settings = new JSONObject();
            for (Map.Entry<String, Object> entry : SettingDefaults.defaults().entrySet()) {
                String key = entry.getKey();
                Object fallback = entry.getValue();
                if (isExtendedKey(key) && !values.containsKey(key)) {
                    // 扩展键（资料页伪装 / AI 回复）：仅当显式存在时才导出。
                    continue;
                }
                Object value = values.containsKey(key) ? values.get(key) : fallback;
                validate(key, value, fallback);
                settings.put(key, value);
            }

            JSONObject root = new JSONObject();
            root.put("format", FORMAT);
            root.put("schemaVersion", SCHEMA_VERSION);
            root.put("settings", settings);

            String text = root.toString(2);
            if (text.getBytes(StandardCharsets.UTF_8).length > MAX_CONFIG_BYTES) {
                throw invalid();
            }
            return text;
        } catch (JSONException exception) {
            throw invalid();
        }
    }

    /**
     * 解析并校验配置 JSON，返回受支持键的只读映射。
     *
     * @throws IllegalArgumentException 格式非法、标识不匹配或任一键校验失败
     */
    public static Map<String, Object> parse(String json) {
        if (json == null || json.getBytes(StandardCharsets.UTF_8).length > MAX_CONFIG_BYTES) {
            throw invalid();
        }
        try {
            JSONTokener tokener = new JSONTokener(json);
            Object value = tokener.nextValue();
            if (!(value instanceof JSONObject) || tokener.nextClean() != 0) {
                throw invalid();
            }
            JSONObject root = (JSONObject) value;
            if (!FORMAT.equals(root.opt("format"))
                    || !Integer.valueOf(SCHEMA_VERSION).equals(root.opt("schemaVersion"))) {
                throw invalid();
            }

            JSONObject settings = root.getJSONObject("settings");
            LinkedHashMap<String, Object> result = new LinkedHashMap<>();
            java.util.Iterator<String> keys = settings.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object fallback = SettingDefaults.defaultValue(key);
                if (fallback == null) {
                    continue; // 未知键直接忽略
                }
                Object entryValue = settings.get(key);
                validate(key, entryValue, fallback);
                result.put(key, entryValue);
            }
            if (result.isEmpty()) {
                throw invalid();
            }
            return Collections.unmodifiableMap(result);
        } catch (JSONException exception) {
            throw invalid();
        }
    }

    /** 扩展键（可缺省）：资料页伪装与 AI 回复相关。 */
    private static boolean isExtendedKey(String key) {
        return key.startsWith(SettingsKeys.PROFILE_DISGUISE_PREFIX)
                || key.startsWith("ai_reply_");
    }

    /**
     * 逐键校验：类型必须与默认值一致，且满足各枚举 / 范围约束。
     */
    static void validate(String key, Object value, Object fallback) {
        if (fallback instanceof Boolean && !(value instanceof Boolean)) {
            throw invalid();
        }
        if (fallback instanceof String && !(value instanceof String)) {
            throw invalid();
        }
        if (fallback instanceof Integer && !(value instanceof Integer)) {
            throw invalid();
        }
        if (value instanceof String && ((String) value).length() > MAX_STRING_LENGTH) {
            throw invalid();
        }

        if (key.startsWith(SettingsKeys.PROFILE_DISGUISE_PREFIX)) {
            if (key.endsWith(SettingsKeys.SUFFIX_MODE) && !DISGUISE_MODES.contains(value)) {
                throw invalid();
            }
            if (key.endsWith(SettingsKeys.SUFFIX_ABSOLUTE) && ((Integer) value).intValue() < 0) {
                throw invalid();
            }
        }
        if (SettingsKeys.FEED_KEYWORDS_V1.equals(key)) {
            validateKeywords(value);
        }
        if (SettingsKeys.FEED_FILTER_GENDER.equals(key) && !GENDERS.contains(value)) {
            throw invalid();
        }
        if (SettingsKeys.CHAT_RECALL_STYLE.equals(key) && !RECALL_STYLES.contains(value)) {
            throw invalid();
        }
        if (SettingsKeys.AI_REPLY_AUTO_IMAGE_THRESHOLD_V1.equals(key)) {
            int threshold = ((Integer) value).intValue();
            if (threshold != 0 && (threshold < 2 || threshold > 5)) {
                throw invalid();
            }
        }
    }

    /** 校验屏蔽词文本：按行拆分、去空白、转小写、去重，并限制单条与总量。 */
    private static void validateKeywords(Object value) {
        String text = value == null ? "" : (String) value;
        if (text.length() > MAX_KEYWORDS_RAW_LENGTH) {
            throw new IllegalArgumentException("屏蔽词内容过长");
        }
        LinkedHashSet<String> unique = new LinkedHashSet<>();
        for (String part : text.split("\\R")) {
            String keyword = part.trim().toLowerCase(Locale.ROOT);
            if (keyword.isEmpty()) {
                continue;
            }
            if (keyword.length() > MAX_KEYWORD_LENGTH) {
                throw new IllegalArgumentException("每个屏蔽词最多100个字符");
            }
            unique.add(keyword);
        }
        if (unique.size() > MAX_KEYWORD_COUNT) {
            throw new IllegalArgumentException("最多保存100个屏蔽词");
        }
    }
}