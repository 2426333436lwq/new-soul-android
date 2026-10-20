package dev.local.newsoul.feature.profile.disguise;

import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.ValueCodec;
import io.github.libxposed.api.XposedInterface;

/**
 * 注册天数伪装 hooker（原 {@code n0}）。
 *
 * <p>挂在资料页两种实现的用户信息绑定方法上（revision 分支 {@code Q1}、meta 分支 {@code t0}），
 * 目标方法签名为 {@code (User, viewClass)}。先执行原方法，再：</p>
 * <ol>
 *   <li>取第 2 个实参作为 view / holder；{@code isRevision} 时用 view 类的无参 {@code b()}
 *       解包，再取文本字段；</li>
 *   <li>读取目标类的计数类型字段（为 {@code 0} 时启用）与用户 {@code registerDay}；</li>
 *   <li>仅当原文<b>包含</b> {@code registerDay} 且 {@code registerDay} 非空时，把该片段解析为整数
 *       （无符号）并登记带锚点的伪装状态 —— 锚点即 {@code registerDay} 原文，替换显示值而非整段文本。</li>
 * </ol>
 *
 * <p>副作用异常仅以 {@code "<idLabel> bind"} 降级，返回值仍是原方法结果。</p>
 */
public final class RegisterDaysDisguiseHooker implements XposedInterface.Hooker {

    /** revision 分支为 {@code true}：以 view 类的无参 {@code b()} 解包。 */
    private final boolean isRevision;
    /** view 类的解包方法 {@code b()}；非 revision 分支为 {@code null}。 */
    private final Method getter;
    /** 文本字段（解包后的宿主上取得）。 */
    private final Field viewField;
    /** 目标类的计数类型字段（为 {@code 0} 时启用）。 */
    private final Field targetTypeField;
    /** 用户 bean 的注册天数字段 {@code registerDay}。 */
    private final Field registerDayField;
    /** 分支上报名（原 {@code n0.f}）：{@code "days-revision"} / {@code "days-meta"}，用于日志。 */
    private final String idLabel;

    public RegisterDaysDisguiseHooker(boolean isRevision, Method getter, Field viewField,
            Field targetTypeField, Field registerDayField, String idLabel) {
        this.isRevision = isRevision;
        this.getter = getter;
        this.viewField = viewField;
        this.targetTypeField = targetTypeField;
        this.registerDayField = registerDayField;
        this.idLabel = idLabel;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Object result = chain.proceed();
        try {
            Object view = chain.getArg(1);
            Object user = chain.getArg(0);
            if (view != null) {
                if (isRevision) {
                    view = getter.invoke(view, (Object[]) null);
                }
                TextView tv = (TextView) viewField.get(view);
                if (tv != null) {
                    ProfileDisguiseRegistry.BINDINGS.remove(tv);
                    int type = targetTypeField.getInt(chain.getThisObject());
                    if (type == 0 && user != null) {
                        String day = (String) registerDayField.get(user);
                        if (day != null) {
                            String text = tv.getText().toString();
                            if (text.contains(day) && !day.isEmpty()) {
                                DisguiseBinding state = new DisguiseBinding(
                                        ValueCodec.parseInt(day, false),
                                        SettingsKeys.PROFILE_DISGUISE_DAYS, tv.getText(), null, day);
                                ProfileDisguiseRegistry.BINDINGS.put(tv, state);
                                ProfileDisguiseRegistry.applyBindingText(tv, state);
                                ProfileDisguiseRegistry.reportBound("bound " + idLabel);
                            }
                        }
                    }
                }
            }
        } catch (Throwable throwable) {
            ProfileDisguiseRegistry.reportFailure(idLabel + " bind", throwable);
        }
        return result;
    }
}
