package dev.local.newsoul.feature.privacy;

import android.app.Activity;

import dev.local.newsoul.framework.Logger;
import io.github.libxposed.api.XposedInterface;

/**
 * 闪照（Snap）预览 hooker（原 {@code a} case 17，label {@code pswitch_73}）。
 *
 * <p>挂在 {@code SnapChatActivity.init(Bundle)} 上：放行前后读取「是否允许重复查看」与 Intent
 * 中的 {@code type}，仅记录日志后原样放行 —— 具体的重复消费拦截由 {@link PrivacyRepeatGuardHooker}
 * 负责。</p>
 */
public final class PrivacySnapPreviewHooker implements XposedInterface.Hooker {

    private static final String EXTRA_TYPE = "type";
    private static final int DEFAULT_TYPE = -1;

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Activity activity = (Activity) chain.getThisObject();
        boolean repeat = PrivacyPreferences.allowRepeat(activity);
        int type = activity.getIntent().getIntExtra(EXTRA_TYPE, DEFAULT_TYPE);
        Logger.i("privacy snap preview: repeat=" + repeat + " type=" + type);
        return chain.proceed();
    }
}
