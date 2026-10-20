package dev.local.newsoul.feature.post;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.github.libxposed.api.XposedInterface;

/**
 * 帖子复制动作 hooker（原 {@code f1} case 1，default 分支）。
 *
 * <p>挂在分享面板视图初始化 {@code SeedsShareDialogFragment.initViews(View)} 上：原方法执行前
 * 往 {@code BaseSeedsDialogFragment.q} 持有的操作项列表里插入「复制文案」项。</p>
 *
 * <ol>
 *   <li>分享帖子字段 {@code E} 为空时不追加，直接放行；</li>
 *   <li>把列表中标识（{@code Operate.a}）不等于 {@link PostCopyFeature#OPERATE_COPY_MARK}
 *       的项保留为新列表；</li>
 *   <li>若新列表中存在 id 为 {@code 0x1a} 的项，则「复制文案」插入到该项之前，否则追加到末尾；</li>
 *   <li>用构造器 {@code (int, String, boolean)} 新建项 {@code (0x33, "复制文案", false)}，
 *       把其 {@code Operate.a} 写为 {@code -0x35efa} 后插入列表，并写回 {@code q} 字段；</li>
 *   <li>记录 {@code "post copy action added; count=<列表大小>"}；异常时记录
 *       {@code "post copy action unavailable"}。</li>
 * </ol>
 *
 * <p>无论成功、失败还是提前放行，最终都调用 {@code proceed()} —— 副作用先于原方法生效。</p>
 */
public final class PostCopyActionHooker implements XposedInterface.Hooker {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    private static final String LOG_ADDED_PREFIX = "post copy action added; count=";
    private static final String LOG_UNAVAILABLE = "post copy action unavailable";

    private final Field sharePostField;
    private final Field operateListField;
    private final Field operateIdField;
    private final Constructor<?> operateConstructor;

    public PostCopyActionHooker(Field sharePostField, Field operateListField,
                                Field operateIdField, Constructor<?> operateConstructor) {
        this.sharePostField = sharePostField;
        this.operateListField = operateListField;
        this.operateIdField = operateIdField;
        this.operateConstructor = operateConstructor;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        try {
            Object sharePost = sharePostField.get(chain.getThisObject());
            if (sharePost != null) {
                List<Object> operates = (List<Object>) operateListField.get(chain.getThisObject());
                List<Object> kept = new ArrayList<>();
                if (operates != null) {
                    for (Object operate : operates) {
                        if (operateIdField.getInt(operate) != PostCopyFeature.OPERATE_COPY_MARK) {
                            kept.add(operate);
                        }
                    }
                }
                int index = kept.size();
                for (int i = 0; i < kept.size(); i++) {
                    if (operateIdField.getInt(kept.get(i))
                            == PostCopyFeature.OPERATE_ID_ANCHOR) {
                        index = i;
                        break;
                    }
                }
                Object copyOperate = operateConstructor.newInstance(
                        PostCopyFeature.OPERATE_ID_COPY_TEXT,
                        PostCopyFeature.OPERATE_COPY_LABEL, Boolean.FALSE);
                operateIdField.setInt(copyOperate, PostCopyFeature.OPERATE_COPY_MARK);
                kept.add(index, copyOperate);
                operateListField.set(chain.getThisObject(), kept);
                Log.i(LOG_TAG, LOG_ADDED_PREFIX + kept.size());
            }
        } catch (Throwable throwable) {
            Log.e(LOG_TAG, LOG_UNAVAILABLE, throwable);
        }
        return chain.proceed();
    }
}
