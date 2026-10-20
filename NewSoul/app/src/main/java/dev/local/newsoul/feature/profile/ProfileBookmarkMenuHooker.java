package dev.local.newsoul.feature.profile;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.framework.HostClasses;
import io.github.libxposed.api.XposedInterface;

/**
 * 资料页书签菜单注入 hooker（原 {@code s1}）。
 *
 * <p>挂在分享面板视图初始化 {@code SeedsShareDialogFragment.initViews(View)} 上，原方法执行前
 * 注入「插眼」菜单项，执行后提交插眼列表加载任务。</p>
 *
 * <p>注入的前置条件（全部满足才注入）：当前页面是 {@code UserHomeActivity}；分享帖子字段
 * {@code D} 非空；类型字段 {@code E} 为空；模式字段 {@code C} 等于 {@code 2}；目标用户的
 * {@code userIdEcpt} 与 {@code alias}（空则取 {@code signature}）均非空；
 * 资源 {@code icon_share_collect} 存在。</p>
 *
 * <p>注入内容：以构造器 {@code (int, String, boolean)} 新建 {@code (0, "插眼…", false)}，
 * 把 {@code Operate.a} 写成 {@code 0x4e5310}、{@code Operate.f} 写成图标资源 id，
 * 过滤掉列表中原有同标识项后前插到 {@code BaseSeedsDialogFragment.q}。随后登记会话，
 * 若宿主 Activity 仍在则经执行器提交加载任务。异常仅以
 * {@code "profile bookmark menu unavailable"} 降级。</p>
 */
public final class ProfileBookmarkMenuHooker implements XposedInterface.Hooker {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    private static final String LOG_UNAVAILABLE = "profile bookmark menu unavailable";

    /** 插眼操作项标识（原 {@code 0x4e5310}）。 */
    private static final int BOOKMARK_TYPE = 0x4e5310;

    /** 分享面板类型字段等于该值时才注入（原 {@code 2}）。 */
    private static final int HOME_TYPE = 2;

    private final Field postField;
    private final Field typeField;
    private final Field modeField;
    private final Constructor<?> operateConstructor;
    private final Field operateIdField;
    private final Field operateIconField;
    private final Field itemsField;
    private final Field operateTitleField;
    private final Field adapterField;

    public ProfileBookmarkMenuHooker(Field postField, Field typeField, Field modeField,
                                     Constructor<?> operateConstructor, Field operateIdField,
                                     Field operateIconField, Field itemsField,
                                     Field operateTitleField, Field adapterField) {
        this.postField = postField;
        this.typeField = typeField;
        this.modeField = modeField;
        this.operateConstructor = operateConstructor;
        this.operateIdField = operateIdField;
        this.operateIconField = operateIconField;
        this.itemsField = itemsField;
        this.operateTitleField = operateTitleField;
        this.adapterField = adapterField;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        if (!ProfileBookmarkActions.installed) {
            return chain.proceed();
        }
        Object host = chain.getThisObject();
        ProfileBookmarkSession session = null;
        try {
            Activity activity =
                    (Activity) ProfileBookmarkActions.invokeAccessor(host, "getActivity");
            Object post = postField.get(host);
            if (activity != null
                    && activity.getClass().getName().equals(HostClasses.HOME_USER_HOME_ACTIVITY)
                    && post != null
                    && typeField.get(host) == null
                    && modeField.getInt(host) == HOME_TYPE) {
                Class<?> postClass = post.getClass();
                String userId = (String) ProfileBookmarkActions
                        .requireField(postClass, "userIdEcpt").get(post);
                String displayName = (String) ProfileBookmarkActions
                        .requireField(postClass, "alias").get(post);
                if (displayName == null || displayName.trim().isEmpty()) {
                    displayName = (String) ProfileBookmarkActions
                            .requireField(postClass, "signature").get(post);
                }
                int iconResource = activity.getResources().getIdentifier(
                        "icon_share_collect", "drawable", activity.getPackageName());
                if (userId != null && !userId.trim().isEmpty()
                        && displayName != null && !displayName.trim().isEmpty()
                        && iconResource != 0) {
                    String account = ProfileBookmarkActions.currentAccount();
                    Object menuItem =
                            operateConstructor.newInstance(0, "插眼…", Boolean.FALSE);
                    operateIdField.setInt(menuItem, BOOKMARK_TYPE);
                    operateIconField.setInt(menuItem, iconResource);
                    List<Object> items = new ArrayList<>();
                    Object rawItems = itemsField.get(host);
                    if (rawItems != null) {
                        for (Object item : (List<?>) rawItems) {
                            if (operateIdField.getInt(item) != BOOKMARK_TYPE) {
                                items.add(item);
                            }
                        }
                    }
                    items.add(0, menuItem);
                    itemsField.set(host, items);
                    session = new ProfileBookmarkSession(account, userId, displayName, menuItem);
                    try {
                        ProfileBookmarkActions.SESSIONS.put(host, session);
                    } catch (Exception error) {
                        Log.w(LOG_TAG, LOG_UNAVAILABLE, error);
                    }
                }
            }
        } catch (Exception error) {
            Log.w(LOG_TAG, LOG_UNAVAILABLE, error);
        }
        Object result = chain.proceed();
        if (session != null) {
            Activity activity =
                    (Activity) ProfileBookmarkActions.invokeAccessor(host, "getActivity");
            if (activity != null) {
                ProfileBookmarkActions.EXECUTOR.execute(new ProfileBookmarkLoadTask(
                        session, activity, host, operateTitleField, adapterField));
            }
        }
        return result;
    }
}
