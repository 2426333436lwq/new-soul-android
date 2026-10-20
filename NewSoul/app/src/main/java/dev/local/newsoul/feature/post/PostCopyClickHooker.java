package dev.local.newsoul.feature.post;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.github.libxposed.api.XposedInterface;

/**
 * 帖子复制点击 hooker（原 {@code b} case 1，default 分支；label 位于 switch 之后的代码）。
 *
 * <p>挂在 {@code SeedsShareDialogFragment.P0(Operate, View, int)} 上：</p>
 * <ul>
 *   <li>第 0 个实参的 {@code Operate.a} 不等于 {@link PostCopyFeature#OPERATE_COPY_MARK}
 *       （{@code -0x35efa}）时直接放行；</li>
 *   <li>命中时读取分享页帖子的 {@code content}：为空或仅含空白则 Toast
 *       {@code "该帖子暂无可复制文案"} 并返回 {@code Boolean.FALSE}；</li>
 *   <li>否则把文案写入剪贴板（label 为 {@code "帖子文案"}），校验
 *       {@code getPrimaryClip} 的内容与文案一致，记录
 *       {@code "post text copied; chars=<长度>; verified=<布尔>"}，Toast {@code "已复制文案"}，
 *       并反射调用宿主 {@code dismiss()} 关闭分享面板；</li>
 *   <li>剪贴板不可用时抛 {@link IllegalStateException}（{@code "Clipboard unavailable"}），
 *       由下方 catch 统一记录 {@code "post text copy failed"} 并 Toast
 *       {@code "复制失败，请重新打开分享页"}；</li>
 *   <li>成功与异常路径都返回 {@code Boolean.FALSE}，避免宿主继续处理该次点击。</li>
 * </ul>
 */
public final class PostCopyClickHooker implements XposedInterface.Hooker {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    /** 剪贴板条目 label。 */
    private static final String CLIP_LABEL = "帖子文案";
    private static final String TOAST_EMPTY = "该帖子暂无可复制文案";
    private static final String TOAST_COPIED = "已复制文案";
    private static final String TOAST_FAILED = "复制失败，请重新打开分享页";
    private static final String ERROR_CLIPBOARD = "Clipboard unavailable";
    private static final String LOG_COPIED_PREFIX = "post text copied; chars=";
    private static final String LOG_COPIED_SUFFIX = "; verified=";
    private static final String LOG_FAILED = "post text copy failed";

    private final Field operateIdField;
    private final Field sharePostField;
    private final Field postContentField;
    private final Method dismiss;

    public PostCopyClickHooker(Field operateIdField, Field sharePostField,
                               Field postContentField, Method dismiss) {
        this.operateIdField = operateIdField;
        this.sharePostField = sharePostField;
        this.postContentField = postContentField;
        this.dismiss = dismiss;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object operate = chain.getArg(0);
        if (operate == null || operateIdField.getInt(operate) != PostCopyFeature.OPERATE_COPY_MARK) {
            return chain.proceed();
        }
        Context context = ((View) chain.getArg(1)).getContext();
        try {
            Object sharePost = sharePostField.get(chain.getThisObject());
            String content = sharePost == null ? null : (String) postContentField.get(sharePost);
            if (content == null || content.trim().isEmpty()) {
                Toast.makeText(context, TOAST_EMPTY, Toast.LENGTH_SHORT).show();
                return Boolean.FALSE;
            }
            ClipboardManager clipboard =
                    (ClipboardManager) context.getSystemService(ClipboardManager.class);
            if (clipboard == null) {
                throw new IllegalStateException(ERROR_CLIPBOARD);
            }
            clipboard.setPrimaryClip(ClipData.newPlainText(CLIP_LABEL, content));
            boolean verified = verifyClipboard(clipboard, content);
            Log.i(LOG_TAG, LOG_COPIED_PREFIX + content.length() + LOG_COPIED_SUFFIX + verified);
            Toast.makeText(context, TOAST_COPIED, Toast.LENGTH_SHORT).show();
            dismiss.invoke(chain.getThisObject(), (Object[]) null);
        } catch (Throwable throwable) {
            Log.e(LOG_TAG, LOG_FAILED, throwable);
            Toast.makeText(context, TOAST_FAILED, Toast.LENGTH_SHORT).show();
        }
        return Boolean.FALSE;
    }

    /** 校验剪贴板内容与文案一致（原实现的内联比对：单条目且文本相等）。 */
    private static boolean verifyClipboard(ClipboardManager clipboard, String content) {
        ClipData primary = clipboard.getPrimaryClip();
        if (primary == null || primary.getItemCount() != 1) {
            return false;
        }
        return content.contentEquals(primary.getItemAt(0).getText());
    }
}
