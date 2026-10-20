package dev.local.newsoul.feature.profile.disguise;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.ui.ViewKit;
import io.github.libxposed.api.XposedModule;

/**
 * 资料页伪装安装与状态注册表（原 {@code p0}）。
 *
 * <p>只改变本机自己个人主页的「关注 / 被关注 / 访客 / 加入天数」四项显示，不改变真实关系或数据。
 * 计数展示在宿主两类资料页实现上各挂一组 hook：</p>
 * <ul>
 *   <li>关注数：revision 分支（{@code RevisionProfileProvider.S1}）与 meta 分支
 *       （{@code MetaFollowFansContainerView.w}）—— hooker {@link FollowCountDisguiseHooker}</li>
 *   <li>资料页头部计数：{@code UserHomeHeaderHelper.setUserFollowData} ——
 *       hooker {@link ProfileHeaderCountDisguiseHooker}</li>
 *   <li>注册天数：revision 分支（{@code RevisionProfileProvider.Q1}）与 meta 分支
 *       （{@code UserInfoProvider.t0}）—— hooker {@link RegisterDaysDisguiseHooker}</li>
 * </ul>
 *
 * <p>全部宿主类以 {@link HostClasses} 常量名反射加载，任一环境缺失即降级为「未就绪」并记录日志，
 * 不做版本锁定。安装失败统一走 {@link #reportUnavailable}。</p>
 */
final class ProfileDisguiseRegistry {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";
    /** 上报文案前缀（原 {@code "follow disguise "}）。 */
    private static final String LOG_PREFIX = "follow disguise ";

    /** 绑定注册表：TextView → 伪装状态（原 {@code p0.f}）。 */
    static final WeakHashMap<TextView, DisguiseBinding> BINDINGS = new WeakHashMap<>();
    /** 事件去重集合：同一 name 只上报一次（原 {@code p0.h}）。 */
    static final HashSet<String> REPORTED = new HashSet<>();

    /** 当前安装使用的模块实例；为 {@code null} 时不做事件上报（原 {@code p0.g}）。 */
    static XposedModule hostModule;

    /** {@code UserFollowCount.followNum}：关注数（原 {@code p0.i}）。 */
    static Field followNumField;
    /** {@code UserFollowCount.beFollowNum}：被关注数（原 {@code p0.j}）。 */
    static Field beFollowNumField;
    /** {@code UserFollowCount.beViewNum}：访客数（原 {@code p0.k}）。 */
    static Field beViewNumField;
    /** 计数格式化方法 {@code m2.f(int)}，供 meta 分支使用（原 {@code p0.l}）。 */
    static Method metaFormatter;
    /** 计数格式化方法 {@code m2.e(int)}，供 revision / header 分支使用（原 {@code p0.m}）。 */
    static Method revisionFormatter;

    /** 关注数伪装（revision 分支）是否就绪（原 {@code p0.a}）。 */
    static boolean followRevisionReady;
    /** 关注数伪装（meta 分支）是否就绪（原 {@code p0.b}）。 */
    static boolean followMetaReady;
    /** 资料页头部计数伪装是否就绪（原 {@code p0.c}）。 */
    static boolean headerReady;
    /** 注册天数伪装（revision 分支）是否就绪（原 {@code p0.d}）。 */
    static boolean daysRevisionReady;
    /** 注册天数伪装（meta 分支）是否就绪（原 {@code p0.e}）。 */
    static boolean daysMetaReady;

    private ProfileDisguiseRegistry() {
    }

    // ------------------------------------------------------------ 文本绑定与刷新

    /**
     * 绑定并立即应用单个计数文本的伪装（原 {@code p0.a}）。
     *
     * <p>先注销旧绑定，{@code enabled = flag && count != null}；仅在启用时快照原文与原始数值
     * 并登记新绑定，随后刷新文本。</p>
     *
     * <p>原实现在此还调用「主页数量编辑入口」的长按附着机制（原 {@code r0.a}）；该入口依赖设置
     * 编辑对话框子系统，不在本域复刻范围内，故省略 —— 不影响文本伪装本身。</p>
     */
    static void applyBinding(TextView tv, Object count, boolean flag, String prefix,
                             Field countField, Method formatter) throws Exception {
        if (tv == null) {
            return;
        }
        BINDINGS.remove(tv);
        boolean enabled = flag && count != null;
        if (!enabled) {
            return;
        }
        DisguiseBinding state =
                new DisguiseBinding(countField.getInt(count), prefix, tv.getText(), formatter, null);
        BINDINGS.put(tv, state);
        reportBound("bound " + prefix);
        applyBindingText(tv, state);
    }

    /**
     * 绑定「标签 + 计数」文本对（原 {@code p0.b}）。
     *
     * <p>标签为「看过我 / 访客 / 访客数」之一时才按访客前缀伪装；否则同样以禁用态走一遍绑定流程
     * （用于清除该文本上的旧绑定）。</p>
     */
    static void bindLabeledCount(Object tvRaw, Object labelRaw, Object count, boolean flag,
                                 Method formatter) throws Exception {
        TextView tv = (TextView) tvRaw;
        CharSequence labelText =
                labelRaw instanceof TextView ? ((TextView) labelRaw).getText() : null;
        boolean visitor = false;
        if (flag && labelText != null) {
            String label = labelText.toString().trim();
            visitor = "看过我".equals(label) || "访客".equals(label) || "访客数".equals(label);
        }
        applyBinding(tv, count, visitor, SettingsKeys.PROFILE_DISGUISE_VISITOR, beViewNumField,
                formatter);
    }

    /** 按当前设置刷新全部已绑定文本（原 {@code p0.h}）。 */
    static void refreshBindings() {
        for (Map.Entry<TextView, DisguiseBinding> entry : BINDINGS.entrySet()) {
            try {
                applyBindingText(entry.getKey(), entry.getValue());
            } catch (Exception error) {
                reportFailure("refresh", error);
            }
        }
    }

    /**
     * 依据当前设置把伪装结果显示到文本（原 {@code p0.i}）。
     *
     * <p>未启用时恢复原文；启用时按 {@code mode}（absolute / delta）与对应值计算显示值，
     * 结果收敛到 {@code [0, 0x7fffffff]}：有锚点则替换原文中的锚点子串，无锚点则交给格式化方法。</p>
     */
    static void applyBindingText(TextView tv, DisguiseBinding state) throws Exception {
        SharedPreferences prefs = ViewKit.prefs(tv.getContext());
        String prefix = state.settingPrefix();
        if (!prefs.getBoolean(prefix + SettingsKeys.SUFFIX_ENABLED, false)) {
            tv.setText(state.originalText());
            return;
        }
        String mode = prefs.getString(prefix + SettingsKeys.SUFFIX_MODE,
                SettingsKeys.DISGUISE_MODE_ABSOLUTE);
        int value = prefs.getInt(prefix + mode, 0);
        long result = SettingsKeys.DISGUISE_MODE_DELTA.equals(mode)
                ? (long) state.originalValue() + (long) value
                : (long) value;
        int displayed = (int) Math.min(0x7fffffffL, Math.max(0L, result));
        CharSequence text;
        if (state.anchor() == null) {
            text = (CharSequence) state.formatter().invoke(null, displayed);
        } else {
            String original = state.originalText().toString();
            int index = state.anchor().isEmpty() ? -1 : original.indexOf(state.anchor());
            if (index < 0) {
                text = original;
            } else {
                text = original.substring(0, index) + displayed
                        + original.substring(index + state.anchor().length());
            }
        }
        tv.setText(text);
    }

    // ------------------------------------------------------------ 安装

    /**
     * 安装资料页伪装（原 {@code p0.e}）。
     *
     * <p>顺序与原实现一致：加载计数字段与格式化方法 → 关注数伪装（revision / meta）→
     * 资料页头部计数伪装 → 注册天数伪装（revision / meta）→ 打印两条就绪日志。</p>
     */
    static void install(XposedModule module, ClassLoader loader) {
        hostModule = module;

        try {
            Class<?> followCountClass = loader.loadClass(HostClasses.HOME_USER_FOLLOW_COUNT);
            followNumField = declaredField(followCountClass, "followNum");
            beFollowNumField = declaredField(followCountClass, "beFollowNum");
            beViewNumField = declaredField(followCountClass, "beViewNum");

            Class<?> formatterClass = loader.loadClass(HostClasses.LIB_MIDDLE_PLATFORM_UTILS_M2);
            metaFormatter = formatterClass.getDeclaredMethod("f", int.class);
            revisionFormatter = formatterClass.getDeclaredMethod("e", int.class);
            metaFormatter.setAccessible(true);
            revisionFormatter.setAccessible(true);
        } catch (Throwable throwable) {
            reportUnavailable("fields", throwable);
        }

        followRevisionReady = installFollowDisguise(module, loader, "revision",
                HostClasses.HOME_REVISION_PROFILE_PROVIDER, "S1",
                HostClasses.HOME_REVISION_PROFILE_VIEW_HOLDER, "o", "q", "g0", "h0", "b", "d", true);
        followMetaReady = installFollowDisguise(module, loader, "meta",
                HostClasses.HOME_META_FOLLOW_FANS_CONTAINER_VIEW, "w",
                HostClasses.HOME_META_FOLLOW_FANS_CONTAINER_BINDING, "e", "g", "q", "r", "k", "b",
                false);

        headerReady = installHeaderDisguise(module, loader);

        daysRevisionReady = installDaysDisguise(module, loader,
                HostClasses.HOME_REVISION_PROFILE_PROVIDER, "Q1",
                HostClasses.HOME_REVISION_PROFILE_VIEW_HOLDER, "V", true);
        daysMetaReady = installDaysDisguise(module, loader,
                HostClasses.HOME_META_USER_INFO_PROVIDER, "t0",
                HostClasses.HOME_META_USER_INFO_BINDING, "y", false);

        Logger.i("days disguise registered: revision=" + daysRevisionReady + "; meta="
                + daysMetaReady);
        Logger.i("follow disguise registered: revision=" + followRevisionReady + "; meta="
                + followMetaReady + "; header=" + headerReady);
    }

    /**
     * 安装关注数伪装（原 {@code p0.g}）。
     *
     * <p>加载目标类、view 类与用户 bean；{@code hasGetter} 时先取 view 类的无参 {@code b()}
     * 作为解包方法，字段宿主取它的返回类型，否则取 view 类本身。目标方法签名为
     * {@code (User, viewClass)}。</p>
     *
     * <p>Hook ID 为 {@code "new-soul/follow-disguise-" + name}。</p>
     */
    private static boolean installFollowDisguise(XposedModule module, ClassLoader loader, String name,
            String targetClassName, String methodName, String viewClassName, String field1Name,
            String field2Name, String field3Name, String field4Name, String targetTypeFieldName,
            String targetCountFieldName, boolean hasGetter) {
        try {
            Class<?> targetClass = loader.loadClass(targetClassName);
            Class<?> viewClass = loader.loadClass(viewClassName);
            Class<?> userClass = loader.loadClass(HostClasses.SOUL_COMPONENT_USER);

            Method getter = null;
            if (hasGetter) {
                getter = viewClass.getDeclaredMethod("b");
                getter.setAccessible(true);
            }
            Class<?> fieldOwner = hasGetter ? getter.getReturnType() : viewClass;
            Field field1 = declaredField(fieldOwner, field1Name);
            Field field2 = declaredField(fieldOwner, field2Name);
            Field field3 = declaredField(fieldOwner, field3Name);
            Field field4 = declaredField(fieldOwner, field4Name);
            Field targetTypeField = declaredField(targetClass, targetTypeFieldName);
            Field targetCountField = declaredField(targetClass, targetCountFieldName);

            Method target = targetClass.getDeclaredMethod(methodName, userClass, viewClass);
            String id = HookIds.FOLLOW_DISGUISE_PREFIX + name;
            if (!Hooks.intercept(module, id, target,
                    new FollowCountDisguiseHooker(hasGetter, getter, name, targetTypeField,
                            targetCountField, field1, field2, field3, field4))) {
                throw new IllegalStateException("hook failed: " + id);
            }
            Hooks.deoptimize(module, id, target);
            for (Method method : targetClass.getDeclaredMethods()) {
                Hooks.deoptimize(module, id, method);
            }
            return true;
        } catch (Throwable throwable) {
            reportUnavailable(name, throwable);
            return false;
        }
    }

    /**
     * 安装资料页头部计数伪装（原 {@code p0.e} 头部段落）。
     *
     * <p>取 {@code UserFollowCount} 声明类作为 {@code setUserFollowData} 的形参类型，
     * 并绑定头部助手的 7 个字段。Hook ID 为 {@code "new-soul/follow-disguise-header"}。</p>
     */
    private static boolean installHeaderDisguise(XposedModule module, ClassLoader loader) {
        try {
            Class<?> headerHelper = loader.loadClass(HostClasses.HOME_USER_HOME_HEADER_HELPER);
            Class<?> userClass = followNumField.getDeclaringClass();

            Field headerTypeField = declaredField(headerHelper, "M");
            Field followField = declaredField(headerHelper, "j");
            Field followedField = declaredField(headerHelper, "l");
            Field visitorCountField1 = declaredField(headerHelper, "n");
            Field visitorLabelField1 = declaredField(headerHelper, "q");
            Field visitorCountField2 = declaredField(headerHelper, "o");
            Field visitorLabelField2 = declaredField(headerHelper, "r");

            Method target = headerHelper.getDeclaredMethod("setUserFollowData", userClass);
            if (!Hooks.intercept(module, HookIds.FOLLOW_DISGUISE_HEADER, target,
                    new ProfileHeaderCountDisguiseHooker(headerTypeField, followField, followedField,
                            visitorCountField1, visitorLabelField1, visitorCountField2,
                            visitorLabelField2))) {
                throw new IllegalStateException("hook failed: " + HookIds.FOLLOW_DISGUISE_HEADER);
            }
            Hooks.deoptimize(module, HookIds.FOLLOW_DISGUISE_HEADER, target);
            for (Method method : headerHelper.getDeclaredMethods()) {
                Hooks.deoptimize(module, HookIds.FOLLOW_DISGUISE_HEADER, method);
            }
            return true;
        } catch (Throwable throwable) {
            reportUnavailable("header", throwable);
            return false;
        }
    }

    /**
     * 安装注册天数伪装（原 {@code p0.f}）。
     *
     * <p>用户 bean 的 {@code registerDay} 用公开 {@code getField} 读取（非 declared），
     * 目标类的计数类型字段名为 {@code "b"}；{@code isRevision} 时以 view 类的无参 {@code b()}
     * 解包并取其返回类型作为字段宿主，否则直接取 view 类。</p>
     *
     * <p>Hook ID 为 {@code "new-soul/days-revision"} 或 {@code "new-soul/days-meta"}。</p>
     */
    private static boolean installDaysDisguise(XposedModule module, ClassLoader loader,
            String targetClassName, String methodName, String viewClassName, String fieldName,
            boolean isRevision) {
        String idName = isRevision ? "days-revision" : "days-meta";
        try {
            Class<?> targetClass = loader.loadClass(targetClassName);
            Class<?> viewClass = loader.loadClass(viewClassName);
            Class<?> userClass = loader.loadClass(HostClasses.SOUL_COMPONENT_USER);

            Field registerDayField = userClass.getField("registerDay");
            Field targetTypeField = declaredField(targetClass, "b");

            Method getter = null;
            if (isRevision) {
                getter = viewClass.getDeclaredMethod("b");
                getter.setAccessible(true);
            }
            Class<?> fieldOwner = isRevision ? getter.getReturnType() : viewClass;
            Field viewField = declaredField(fieldOwner, fieldName);

            Method target = targetClass.getDeclaredMethod(methodName, userClass, viewClass);
            String id = isRevision
                    ? HookIds.DAYS_DISGUISE_REVISION
                    : HookIds.DAYS_DISGUISE_META;
            if (!Hooks.intercept(module, id, target,
                    new RegisterDaysDisguiseHooker(isRevision, getter, viewField, targetTypeField,
                            registerDayField, idName))) {
                throw new IllegalStateException("hook failed: " + id);
            }
            Hooks.deoptimize(module, id, target);
            for (Method method : targetClass.getDeclaredMethods()) {
                Hooks.deoptimize(module, id, method);
            }
            return true;
        } catch (Throwable throwable) {
            reportUnavailable(idName, throwable);
            return false;
        }
    }

    // ------------------------------------------------------------ 上报

    /** 上报安装失败（原 {@code p0.c}）：先按模块日志，再必定写入 Android 日志。 */
    static void reportUnavailable(String name, Throwable throwable) {
        if (hostModule != null) {
            Logger.e(LOG_PREFIX + name + " unavailable", throwable);
        }
        Log.e(LOG_TAG, LOG_PREFIX + name + " unavailable", throwable);
    }

    /** 运行期失败上报，同名只报一次（原 {@code p0.j}）。 */
    static void reportFailure(String name, Throwable throwable) {
        if (hostModule != null && REPORTED.add("error:" + name)) {
            Logger.e(LOG_PREFIX + name + " failed", throwable);
        }
    }

    /** 绑定成功上报，同名只报一次（原 {@code p0.k}）。 */
    static void reportBound(String name) {
        if (hostModule != null && REPORTED.add(name)) {
            Logger.i(LOG_PREFIX + name);
        }
    }

    /** 读取宿主类的声明字段并放开访问（原 {@code p0.d}）。 */
    private static Field declaredField(Class<?> type, String name) throws NoSuchFieldException {
        Field field = type.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }
}
