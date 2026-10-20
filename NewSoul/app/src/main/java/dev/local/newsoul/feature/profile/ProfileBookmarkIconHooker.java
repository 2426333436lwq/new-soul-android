package dev.local.newsoul.feature.profile;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

import dev.local.newsoul.ui.ActionMarkBadge;
import io.github.libxposed.api.XposedInterface;

/**
 * 资料页书签图标绑定 hooker（原 {@code e} case 2）。
 *
 * <p>挂在分享面板条目适配器内部类 {@code TypeAdapter$a.a(Operate)} 上，先执行原方法，再：</p>
 * <ol>
 *   <li>取条目图片视图（{@code TypeAdapter$a.c}）与操作项标识（{@code Operate.a}）；</li>
 *   <li>当标识等于插眼标识 {@code 0x4e5310} 且当前 drawable 是 {@link BitmapDrawable} 时，
 *       用菜单文字色（{@code TypeAdapter$a.a}）把它包成 {@link ProfileBookmarkIconDrawable} 后回设；</li>
 *   <li>调用 {@link ActionMarkBadge#apply} 按「是否命中插眼标识」显示/隐藏角标；</li>
 *   <li>副作用异常仅以 {@code "bookmark icon binding failed"} 降级，返回值仍是原方法结果。</li>
 * </ol>
 */
public final class ProfileBookmarkIconHooker implements XposedInterface.Hooker {

    /** 与原模块一致的日志 TAG。 */
    private static final String LOG_TAG = "SoulHook102";

    private static final String LOG_FAILED = "bookmark icon binding failed";

    /** 插眼操作项标识（原 {@code 0x4e5310}）。 */
    private static final int BOOKMARK_TYPE = 0x4e5310;

    private final Field imageViewField;
    private final Field operateIdField;
    private final Field textViewField;

    public ProfileBookmarkIconHooker(Field imageViewField, Field operateIdField, Field textViewField) {
        this.imageViewField = imageViewField;
        this.operateIdField = operateIdField;
        this.textViewField = textViewField;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object result = chain.proceed();
        try {
            ImageView imageView = (ImageView) imageViewField.get(chain.getThisObject());
            boolean bookmarked = operateIdField.getInt(chain.getArg(0)) == BOOKMARK_TYPE;
            if (bookmarked && imageView.getDrawable() instanceof BitmapDrawable) {
                TextView textView = (TextView) textViewField.get(chain.getThisObject());
                imageView.setImageDrawable(new ProfileBookmarkIconDrawable(
                        (BitmapDrawable) imageView.getDrawable(), textView.getCurrentTextColor()));
            }
            ActionMarkBadge.apply(imageView, bookmarked);
        } catch (Exception error) {
            Log.w(LOG_TAG, LOG_FAILED, error);
        }
        return result;
    }
}
