package dev.local.newsoul.feature.profile.disguise;

import android.widget.TextView;

import java.lang.reflect.Field;

import dev.local.newsoul.config.SettingsKeys;
import io.github.libxposed.api.XposedInterface;

/**
 * 资料页头部计数伪装 hooker（原 {@code l0}）。
 *
 * <p>挂在 {@code UserHomeHeaderHelper.setUserFollowData(UserFollowCount)} 上，先执行原方法，
 * 再读取头部类型字段 {@code M}（为 {@code 0} 时启用伪装），把关注 / 被关注两个计数文本与两组
 * 「访客标签 + 计数」文本对交给 {@link ProfileDisguiseRegistry} 绑定，格式化方法用
 * {@code m2.e}。副作用异常仅以 {@code "header bind"} 降级，返回值仍是原方法结果。</p>
 *
 * <p>构造参数顺序与原实现一致：{@code M, j, l, n, q, o, r}。</p>
 */
public final class ProfileHeaderCountDisguiseHooker implements XposedInterface.Hooker {

    /** 头部类型字段 {@code M}（为 {@code 0} 时启用）。 */
    private final Field headerTypeField;
    /** 关注计数文本字段 {@code j}。 */
    private final Field followCountField;
    /** 被关注计数文本字段 {@code l}。 */
    private final Field followedCountField;
    /** 第一组访客计数文本字段 {@code n}。 */
    private final Field visitorCountField1;
    /** 第一组访客标签文本字段 {@code q}。 */
    private final Field visitorLabelField1;
    /** 第二组访客计数文本字段 {@code o}。 */
    private final Field visitorCountField2;
    /** 第二组访客标签文本字段 {@code r}。 */
    private final Field visitorLabelField2;

    public ProfileHeaderCountDisguiseHooker(Field headerTypeField, Field followCountField,
            Field followedCountField, Field visitorCountField1, Field visitorLabelField1,
            Field visitorCountField2, Field visitorLabelField2) {
        this.headerTypeField = headerTypeField;
        this.followCountField = followCountField;
        this.followedCountField = followedCountField;
        this.visitorCountField1 = visitorCountField1;
        this.visitorLabelField1 = visitorLabelField1;
        this.visitorCountField2 = visitorCountField2;
        this.visitorLabelField2 = visitorLabelField2;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object result = chain.proceed();
        try {
            Object self = chain.getThisObject();
            int headerType = headerTypeField.getInt(self);
            ProfileDisguiseRegistry.reportBound("header type=" + headerType);
            boolean enabled = headerType == 0;
            Object followTv = followCountField.get(self);
            Object followedTv = followedCountField.get(self);
            Object count = chain.getArg(0);
            ProfileDisguiseRegistry.applyBinding((TextView) followTv, count, enabled,
                    SettingsKeys.PROFILE_DISGUISE_FOLLOW, ProfileDisguiseRegistry.followNumField,
                    ProfileDisguiseRegistry.revisionFormatter);
            ProfileDisguiseRegistry.applyBinding((TextView) followedTv, count, enabled,
                    SettingsKeys.PROFILE_DISGUISE_FOLLOWED, ProfileDisguiseRegistry.beFollowNumField,
                    ProfileDisguiseRegistry.revisionFormatter);
            ProfileDisguiseRegistry.bindLabeledCount(visitorCountField1.get(self),
                    visitorLabelField1.get(self), count, enabled,
                    ProfileDisguiseRegistry.revisionFormatter);
            ProfileDisguiseRegistry.bindLabeledCount(visitorCountField2.get(self),
                    visitorLabelField2.get(self), count, enabled,
                    ProfileDisguiseRegistry.revisionFormatter);
        } catch (Throwable throwable) {
            ProfileDisguiseRegistry.reportFailure("header bind", throwable);
        }
        return result;
    }
}
