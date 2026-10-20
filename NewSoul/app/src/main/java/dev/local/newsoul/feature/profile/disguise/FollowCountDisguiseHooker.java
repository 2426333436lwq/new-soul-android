package dev.local.newsoul.feature.profile.disguise;

import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dev.local.newsoul.config.SettingsKeys;
import io.github.libxposed.api.XposedInterface;

/**
 * 关注 / 被关注 / 访客计数伪装 hooker（原 {@code m0}）。
 *
 * <p>挂在资料页两种实现的计数绑定方法上（revision 分支 {@code S1}、meta 分支 {@code w}），
 * 目标方法签名为 {@code (User, viewClass)}。先执行原方法，再：</p>
 * <ol>
 *   <li>取第 2 个实参作为 view / holder；{@code hasGetter} 时用 view 类的无参 {@code b()}
 *       解包后再取字段；</li>
 *   <li>读取目标类的计数类型字段（为 {@code 0} 时启用伪装）与计数字段（非空才算有值）；</li>
 *   <li>把两个计数文本与一组「标签 + 计数」文本对交给 {@link ProfileDisguiseRegistry} 绑定，
 *       格式化方法用 {@code m2.f}。</li>
 * </ol>
 *
 * <p>副作用异常仅以 {@code "<name> bind"} 降级，返回值仍是原方法结果。</p>
 */
public final class FollowCountDisguiseHooker implements XposedInterface.Hooker {

    /** 是否使用 view 类的无参 {@code b()} 解包（revision 分支为 {@code true}）。 */
    private final boolean hasGetter;
    /** view 类的解包方法 {@code b()}；{@code hasGetter} 为 {@code false} 时为 {@code null}。 */
    private final Method getter;
    /** 分支名（{@code "revision"} / {@code "meta"}），用于日志。 */
    private final String name;
    /** 目标类的计数类型字段（为 {@code 0} 时启用）。 */
    private final Field targetTypeField;
    /** 目标类的计数字段（对象，非空才参与绑定）。 */
    private final Field targetCountField;
    /** 关注计数文本字段。 */
    private final Field field1;
    /** 被关注计数文本字段。 */
    private final Field field2;
    /** 访客标签文本字段。 */
    private final Field field3;
    /** 访客计数文本字段。 */
    private final Field field4;

    public FollowCountDisguiseHooker(boolean hasGetter, Method getter, String name,
            Field targetTypeField, Field targetCountField, Field field1, Field field2, Field field3,
            Field field4) {
        this.hasGetter = hasGetter;
        this.getter = getter;
        this.name = name;
        this.targetTypeField = targetTypeField;
        this.targetCountField = targetCountField;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object result = chain.proceed();
        try {
            Object self = chain.getThisObject();
            Object view = chain.getArg(1);
            if (view != null) {
                if (hasGetter) {
                    view = getter.invoke(view, (Object[]) null);
                }
                int type = targetTypeField.getInt(self);
                boolean hasCount = targetCountField.get(self) != null;
                ProfileDisguiseRegistry.reportBound(name + " type=" + type + "; count=" + hasCount);
                boolean enabled = type == 0;
                Object count = targetCountField.get(self);
                ProfileDisguiseRegistry.applyBinding((TextView) field1.get(view), count, enabled,
                        SettingsKeys.PROFILE_DISGUISE_FOLLOW, ProfileDisguiseRegistry.followNumField,
                        ProfileDisguiseRegistry.metaFormatter);
                ProfileDisguiseRegistry.applyBinding((TextView) field2.get(view), count, enabled,
                        SettingsKeys.PROFILE_DISGUISE_FOLLOWED,
                        ProfileDisguiseRegistry.beFollowNumField, ProfileDisguiseRegistry.metaFormatter);
                ProfileDisguiseRegistry.bindLabeledCount(field3.get(view), field4.get(view), count,
                        enabled, ProfileDisguiseRegistry.metaFormatter);
            }
        } catch (Throwable throwable) {
            ProfileDisguiseRegistry.reportFailure(name + " bind", throwable);
        }
        return result;
    }
}
