package dev.local.newsoul.feature.chat.game;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import dev.local.newsoul.config.ConfigStore;
import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.Reflect;

/**
 * 骰子 / 猜拳结果控制（原 {@code t} 安装器 + {@code p.intercept} + {@code q.run} + {@code F0.c} 选择器）。
 *
 * <p><b>原始证据</b>：</p>
 * <ul>
 *   <li>{@code t.a(Class, Field, String, boolean)} —— 安装器，hook id 前缀
 *       {@code new-soul/game-picker/}，签名不符时记录
 *       {@code "game hook unavailable: ... send signature changed"}</li>
 *   <li>{@code p.intercept} —— 读 {@code game_finger} 开关，用 {@code getActivity()}
 *       取宿主 Activity，并通过 {@code ThreadLocal.get()} + {@code Field.get()} 传递
 *       「本次应出什么」</li>
 *   <li>{@code F0.c(Activity, boolean, boolean, s, m)} —— 手势选择器对话框，
 *       资源名 {@code finger_stone} / {@code finger_scissor} / {@code finger_cloth}、
 *       {@code point}（点数）、{@code tick}</li>
 * </ul>
 *
 * <p><b>为什么用 ThreadLocal</b>：骰子/猜拳的结果在消息对象构造时写入，而「用户选了什么」
 * 由 UI 线程产生。两条路径之间没有参数通道，原实现用 {@link ThreadLocal} 做线程内传递，
 * 避免并发会话串味。本实现沿用同一策略。</p>
 *
 * <p><b>兜底</b>：找不到宿主骰子消息类时，功能整体降级并记录日志，不影响其它聊天功能。</p>
 */
public final class GameResultFeature implements dev.local.newsoul.feature.FeatureInstaller {

    /** 猜拳三手势的资源名（原 {@code F0.c} 证据）。 */
    public static final String RES_FINGER_STONE = "finger_stone";
    public static final String RES_FINGER_SCISSOR = "finger_scissor";
    public static final String RES_FINGER_CLOTH = "finger_cloth";

    /** 骰子点数上限（宿主标准骰子为 1..6）。 */
    public static final int DICE_MAX = 6;

    /** 猜拳取值：0=石头 1=剪刀 2=布（与宿主资源名顺序一致）。 */
    public static final int FINGER_STONE = 0;
    public static final int FINGER_SCISSOR = 1;
    public static final int FINGER_CLOTH = 2;

    /** 本次发送要使用的游戏结果；{@code null} 表示不干预。 */
    private static final ThreadLocal<Integer> PENDING_RESULT = new ThreadLocal<>();

    /** 待重写的消息引用（原实现用 {@code Field} + {@code Method} 组合）。 */
    private static final List<String> CONTENT_FIELDS = new ArrayList<>();

    static {
        CONTENT_FIELDS.add("content");
        CONTENT_FIELDS.add("msgContent");
        CONTENT_FIELDS.add("text");
        CONTENT_FIELDS.add("result");
        CONTENT_FIELDS.add("value");
    }

    @Override
    public String id() {
        return "chat-game";
    }

    @Override
    public void install(dev.local.newsoul.framework.ModuleContext context) {
        Class<?> diceMsg = context.loadHostClass(dev.local.newsoul.framework.HostClasses.IMLIB_DICE_FINGER_MSG);
        if (diceMsg == null) {
            Logger.e("game hook unavailable: DiceFingerMsg not found");
            return;
        }
        // 记录消息内容字段，供结果注入使用。
        // 字段发现：先按已知候选名找；找不到就按「类型 + 可写」兜底扫描。
        // 只依赖字段名在宿主改字段名时会失效（实测 host 6.37.0 的 DiceFingerMsg
        // 没有 content/msgContent/text/result/value 任何一个），形状兜底是必需的。
        List<Field> candidates = new ArrayList<>();
        for (String name : CONTENT_FIELDS) {
            Field field = Reflect.findField(diceMsg, name);
            if (field != null) {
                candidates.add(field);
            }
        }
        if (candidates.isEmpty()) {
            candidates = discoverWritableFields(diceMsg);
        }
        if (candidates.isEmpty()) {
            Logger.e("game hook unavailable: no content field; send signature changed");
            return;
        }
        contentFields = candidates;
        Logger.i("game hook ready; fields=" + candidates.size()
                + " names=" + fieldNames(candidates));
    }

    private static List<Field> contentFields = new ArrayList<>();

    // ================================================================ 结果注入

    /**
     * 设定本次要发送的游戏结果。
     *
     * <p>由手势选择器 / 点数选择器调用，随后宿主发送消息时被
     * {@link #applyPending(Object)} 取用并清除。</p>
     *
     * @param value 骰子点数 1..6，或猜拳 0/1/2
     */
    public static void setPendingResult(int value) {
        PENDING_RESULT.set(value);
    }

    /** 读取并清除待用结果。 */
    public static Integer consumePendingResult() {
        Integer value = PENDING_RESULT.get();
        PENDING_RESULT.remove();
        return value;
    }

    /** 是否存在待用结果（用于 UI 提示）。 */
    public static boolean hasPendingResult() {
        return PENDING_RESULT.get() != null;
    }

    /**
     * 把待用结果写入宿主消息对象。
     *
     * <p>在宿主构造/发送骰子消息前调用。写入失败只记日志 —— 结果不生效比抛异常打断
     * 发送流程要好。</p>
     *
     * @return 是否成功写入
     */
    public static boolean applyPending(Object message) {
        if (message == null) {
            return false;
        }
        Integer value = consumePendingResult();
        if (value == null) {
            return false;
        }
        for (Field field : contentFields) {
            if (field.getDeclaringClass().isInstance(message)) {
                try {
                    Class<?> type = field.getType();
                    if (type == int.class) {
                        field.setAccessible(true);
                        field.setInt(message, value);
                        Logger.i("game result applied: " + value);
                        return true;
                    }
                    if (type == Integer.class) {
                        Reflect.setFieldValue(message, field, value);
                        Logger.i("game result applied: " + value);
                        return true;
                    }
                    if (type == String.class) {
                        Reflect.setFieldValue(message, field, String.valueOf(value));
                        Logger.i("game result applied: " + value);
                        return true;
                    }
                } catch (Throwable throwable) {
                    Logger.w("game result write failed", throwable);
                }
            }
        }
        return false;
    }

    // ================================================================ 开关

    /** 骰子控制是否开启。 */
    public static boolean isDiceEnabled(Activity activity) {
        return activity != null && ConfigStore.from(activity).getBoolean(SettingsKeys.GAME_DICE);
    }

    /** 猜拳控制是否开启（原 {@code p.intercept} 读的就是这个键）。 */
    public static boolean isFingerEnabled(Activity activity) {
        return activity != null && ConfigStore.from(activity).getBoolean(SettingsKeys.GAME_FINGER);
    }

    /**
     * 取宿主 Activity：原实现用 {@code getActivity()} 反射从 hook 对象上取。
     *
     * @param host hook 的 receiver（可能是 Fragment / View / 内部类）
     */
    public static Activity resolveActivity(Object host) {
        if (host == null) {
            return null;
        }
        if (host instanceof Activity activity) {
            return activity;
        }
        Method getActivity = Reflect.findAnyMethod(host.getClass(), "getActivity");
        if (getActivity != null) {
            Object value = Reflect.invoke(host, getActivity);
            if (value instanceof Activity activity) {
                return activity;
            }
        }
        // Fragment 的 mActivity / mHost 字段
        for (String name : new String[]{"mActivity", "activity", "host", "mHost"}) {
            Field field = Reflect.findField(host.getClass(), name);
            if (field != null) {
                Object value = Reflect.getFieldValue(host, field);
                if (value instanceof Activity activity) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 兜底扫描：找出消息类上所有「可写的 int / Integer / String / long」实例字段。
     *
     * <p>用于宿主改字段名的场景。刻意排除 static / final，只保留可能是
     * 「点数 / 手势结果」的标量字段。</p>
     */
    private static List<Field> discoverWritableFields(Class<?> clazz) {
        List<Field> found = new ArrayList<>();
        try {
            for (Field field : Reflect.allFields(clazz)) {
                int mod = field.getModifiers();
                if (java.lang.reflect.Modifier.isStatic(mod)
                        || java.lang.reflect.Modifier.isFinal(mod)) {
                    continue;
                }
                Class<?> type = field.getType();
                if (type == int.class || type == Integer.class
                        || type == String.class || type == long.class) {
                    found.add(field);
                }
            }
        } catch (Throwable throwable) {
            Logger.w("game field scan failed", throwable);
        }
        return found;
    }

    private static String fieldNames(List<Field> fields) {
        StringBuilder builder = new StringBuilder();
        for (Field field : fields) {
            if (builder.length() > 0) {
                builder.append(',');
            }
            builder.append(field.getName());
        }
        return builder.toString();
    }
}
