package dev.local.newsoul;

import android.content.Context;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * 个人主页计数伪装。
 * 对应原始 DEX 的 l0 / m0 / n0 / p0 三个 Hooker + p0.a/p0.b。
 *
 * 设计约束（来自 UI 文案）：
 *   「只改变本机自己个人主页的关注、被关注、访客数量及加入天数」
 *   —— 仅改本地 TextView 显示，不发送任何网络请求。
 *
 * 键格式：profile_disguise_<field>_<uid>
 */
public final class ProfileDisguise {

    private ProfileDisguise() {}

    public static final String P_DAYS = "profile_disguise_days_";
    public static final String P_FOLLOW = "profile_disguise_follow_";
    public static final String P_FOLLOWED = "profile_disguise_followed_";
    public static final String P_VISITOR = "profile_disguise_visitor_";

    /** 对 UserFollowCount 实例应用伪装值。 */
    public static void apply(Object followCount) {
        if (followCount == null) return;
        String uid = currentUid(followCount);
        if (uid == null) return;

        setIfPresent(followCount, new String[]{"followCount", "follow"},
                FeatureCatalog.getInt(ctx(), P_FOLLOW + uid, -1));
        setIfPresent(followCount, new String[]{"fansCount", "followed", "beFollowCount"},
                FeatureCatalog.getInt(ctx(), P_FOLLOWED + uid, -1));
    }

    /** 访客数伪装（对应 p0.b）。 */
    public static void applyVisitor(Object target) {
        String uid = currentUid(target);
        if (uid == null) return;
        setIfPresent(target, new String[]{"visitorCount", "visitors", "visitCount"},
                FeatureCatalog.getInt(ctx(), P_VISITOR + uid, -1));
    }

    /** 加入天数伪装。 */
    public static void applyDays(Object target) {
        String uid = currentUid(target);
        if (uid == null) return;
        setIfPresent(target, new String[]{"days", "joinDays", "registerDays"},
                FeatureCatalog.getInt(ctx(), P_DAYS + uid, -1));
    }

    /** 把伪装值写进 TextView。 */
    public static void applyTo(TextView tv, String prefix, String uid) {
        if (tv == null) return;
        int v = FeatureCatalog.getInt(ctx(), prefix + uid, -1);
        if (v >= 0) {
            tv.setText(String.valueOf(v));
        }
    }

    // ------------------------------------------------------------------

    private static void setIfPresent(Object target, String[] names, int value) {
        if (value < 0) return;
        Class<?> c = target.getClass();
        for (String n : names) {
            Field f = Reflect.fieldOrNull(c, n);
            if (f != null) {
                try {
                    Class<?> t = f.getType();
                    if (t == int.class) Reflect.setInt(f, target, value);
                    else if (t == long.class) Reflect.setLong(f, target, value);
                    else if (t == Integer.class || t == Long.class) Reflect.set(f, target, value);
                    return;
                } catch (Throwable ignore) {
                }
            }
        }
    }

    private static String currentUid(Object target) {
        // 原实现从 SharedPreferences 的 puid 键取当前登录 uid
        return FeatureCatalog.getString(ctx(), "puid", null);
    }

    private static Context ctx() {
        return ContextHolder.get();
    }
}
