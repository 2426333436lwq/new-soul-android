package dev.local.newsoul.feature.post;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

import dev.local.newsoul.ui.ActionMarkBadge;
import io.github.libxposed.api.XposedInterface;

/**
 * 帖子动作标记 hooker（原 {@code B} case 1，default 分支）。
 *
 * <p>挂在帖子列表适配器内部类 {@code TypeAdapter$a.a(Operate)} 上，先执行原方法，再按操作项
 * 标识在条目视图上挂「新内容」角标：</p>
 * <ul>
 *   <li>角标目标视图取自 {@code TypeAdapter$a.c} 字段；</li>
 *   <li>是否显示取决于第 0 个实参的 {@code Operate.a} 是否等于
 *       {@link PostCopyFeature#OPERATE_COPY_MARK}（{@code -0x35efa}）；</li>
 *   <li>副作用异常仅以 {@code "post action mark unavailable"} 降级，返回值仍是原方法的结果。</li>
 * </ul>
 */
public final class PostActionMarkHooker implements XposedInterface.Hooker {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    private static final String LOG_UNAVAILABLE = "post action mark unavailable";

    private final Field holderViewField;
    private final Field operateIdField;

    public PostActionMarkHooker(Field holderViewField, Field operateIdField) {
        this.holderViewField = holderViewField;
        this.operateIdField = operateIdField;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object result = chain.proceed();
        try {
            View view = (View) holderViewField.get(chain.getThisObject());
            boolean marked = operateIdField.getInt(chain.getArg(0))
                    == PostCopyFeature.OPERATE_COPY_MARK;
            ActionMarkBadge.apply(view, marked);
        } catch (Throwable throwable) {
            Log.w(LOG_TAG, LOG_UNAVAILABLE, throwable);
        }
        return result;
    }
}
