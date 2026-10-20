package dev.local.newsoul.feature.post;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import io.github.libxposed.api.XposedModule;

/**
 * 帖子复制功能域（原 {@code Q0.B} 帖子复制安装 + {@code Q0.I} 动作标记安装）。
 *
 * <p>在帖子分享面板上追加「复制文案」菜单项，并在点击该项时把帖子正文写入剪贴板：</p>
 * <ul>
 *   <li><b>动作标记段</b>（原 {@code Q0.I}）：hook 帖子列表适配器内部类
 *       {@code TypeAdapter$a} 的 {@code a(Operate)} 方法，按操作项标识在条目视图上挂角标
 *       （{@code new-soul/action-marks-post}）。该段失败仅以 Android 日志
 *       {@code "post action mark unavailable"} 降级，不影响复制段。</li>
 *   <li><b>复制段</b>：hook 分享面板的点击入口 {@code P0(Operate, View, int)}
 *       （{@code new-soul/post-copy-click}，{@link PostCopyClickHooker}）与视图初始化
 *       {@code initViews(View)}（{@code new-soul/post-copy-action}，
 *       {@link PostCopyActionHooker}），并对 {@code P0} 去优化。</li>
 * </ul>
 *
 * <p>{@code Operate} 是宿主运行时以 {@code BaseSeedsDialogFragment.getName() + "$Operate"}
 * 拼出的内部类，因此不硬编码完整类名；其构造器为 {@code (int, String, boolean)}。</p>
 */
public final class PostCopyFeature implements FeatureInstaller {

    private static final String ID = "post.copy";

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    /** 「复制文案」操作项标识（原 {@code -0x35efa}）。 */
    static final int OPERATE_COPY_MARK = -0x35efa;
    /** 新增「复制文案」操作项的 id（原 {@code 0x33}）。 */
    static final int OPERATE_ID_COPY_TEXT = 0x33;
    /** 追加复制项时用作锚点的操作项 id（原 {@code 0x1a}）。 */
    static final int OPERATE_ID_ANCHOR = 0x1a;
    /** 复制菜单项文案。 */
    static final String OPERATE_COPY_LABEL = "复制文案";

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        ClassLoader loader = context.classLoader();
        try {
            Class<?> shareFragment = loader.loadClass(HostClasses.SQUARE_SEEDS_SHARE_DIALOG_FRAGMENT);
            Class<?> baseFragment = loader.loadClass(HostClasses.SQUARE_BASE_SEEDS_DIALOG_FRAGMENT);
            // 内部类名 = BaseSeedsDialogFragment.getName() + "$Operate"，运行时拼出
            Class<?> operateClass = loader.loadClass(baseFragment.getName() + "$Operate");

            Field sharePostField = shareFragment.getDeclaredField("E");
            Field operateListField = baseFragment.getDeclaredField("q");
            sharePostField.setAccessible(true);
            operateListField.setAccessible(true);

            Class<?> postClass = loader.loadClass(HostClasses.SQUARE_POST_BEAN);
            Field postContentField = postClass.getField("content");
            Field operateIdField = operateClass.getField("a");

            // 动作标记段：独立降级，不阻断复制段
            installActionMark(module, loader, operateClass, operateIdField);

            Constructor<?> operateConstructor =
                    operateClass.getConstructor(int.class, String.class, boolean.class);
            Method initViews = shareFragment.getDeclaredMethod("initViews", View.class);
            Method click = shareFragment.getDeclaredMethod("P0", operateClass, View.class, int.class);
            Method dismiss = shareFragment.getMethod("dismiss", (Class<?>[]) null);

            if (!Hooks.intercept(module, HookIds.POST_COPY_CLICK, click,
                    new PostCopyClickHooker(operateIdField, sharePostField, postContentField, dismiss))) {
                throw new IllegalStateException("post copy click hook failed");
            }
            if (!Hooks.intercept(module, HookIds.POST_COPY_ACTION, initViews,
                    new PostCopyActionHooker(sharePostField, operateListField, operateIdField,
                            operateConstructor))) {
                throw new IllegalStateException("post copy action hook failed");
            }
            Hooks.deoptimize(module, HookIds.POST_COPY_CLICK, click);

            Log.i(LOG_TAG, "post copy hooks installed");
        } catch (Throwable throwable) {
            Logger.e("post copy hooks unavailable", throwable);
        }
    }

    /** 动作标记段（原 {@code Q0.I}）：hook {@code TypeAdapter$a.a(Operate)} 并去优化。 */
    private static void installActionMark(XposedModule module, ClassLoader loader,
                                          Class<?> operateClass, Field operateIdField) {
        try {
            Class<?> adapterInner = loader.loadClass(HostClasses.SQUARE_TYPE_ADAPTER_INNER);
            Method bind = adapterInner.getDeclaredMethod("a", operateClass);
            Field holderViewField = adapterInner.getDeclaredField("c");
            holderViewField.setAccessible(true);
            Hooks.intercept(module, HookIds.ACTION_MARKS_POST, bind,
                    new PostActionMarkHooker(holderViewField, operateIdField));
            Hooks.deoptimize(module, HookIds.ACTION_MARKS_POST, bind);
        } catch (Throwable throwable) {
            Log.w(LOG_TAG, "post action mark unavailable", throwable);
        }
    }
}
