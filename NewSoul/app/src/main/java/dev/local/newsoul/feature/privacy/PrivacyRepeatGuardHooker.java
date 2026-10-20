package dev.local.newsoul.feature.privacy;

import android.app.Activity;

import dev.local.newsoul.framework.Logger;
import io.github.libxposed.api.XposedInterface;

/**
 * 重复消费守卫 hooker（原 {@code f} case 1，label {@code pswitch_37}）。
 *
 * <p>当 {@link PrivacyPreferences#allowRepeat(Activity)} 为真时直接拦截该次消费（返回
 * {@code null}），使已查看过的隐私内容不会被再次消耗；否则原样放行。</p>
 *
 * <p>构造时传入的 {@code route} 仅用于日志区分两条安装路由（{@code privacy} / {@code snap}）。</p>
 */
public final class PrivacyRepeatGuardHooker implements XposedInterface.Hooker {

    private final String route;

    public PrivacyRepeatGuardHooker(String route) {
        this.route = route;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Activity activity = (Activity) chain.getThisObject();
        if (!PrivacyPreferences.allowRepeat(activity)) {
            return chain.proceed();
        }
        Logger.i("privacy repeat blocked consume: route=" + route);
        return null;
    }
}
