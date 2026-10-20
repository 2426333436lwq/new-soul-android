package dev.local.newsoul.feature.privacy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.ui.ViewKit;
import io.github.libxposed.api.XposedInterface;

/**
 * 隐私照片/视频预览 hooker（原 {@code a} case 16，label {@code pswitch_a1}）。
 *
 * <p>挂在 {@code PrivacyProtectPreviewActivity.init(Bundle)} 上，放行前后依次：</p>
 * <ul>
 *   <li>允许重复查看时把 {@code limitViewTimes} 重置为 {@code 0}；</li>
 *   <li>允许截屏时清除 {@code FLAG_SECURE}（{@code 0x2000}）；</li>
 *   <li>允许下载且查看次数（Intent extra {@code type}）落在 {@code 1..4} 时，在 decor 视图上
 *       按 {@code {0, 250, 750, 1500}} 毫秒四个延时点注入保存按钮。</li>
 * </ul>
 *
 * <p>结尾读取三个开关并记录 {@code privacy photo preview: ... type=<查看次数>} —— 读取默认值
 * 恒为 {@code false}（与原实现的寄存器语义一致）。注意与原实现一致：<b>写入</b>用
 * {@code limitViewTimes}，而<b>读取</b>用 {@code type}，二者是不同的 extra。</p>
 */
public final class PrivacyPhotoPreviewHooker implements XposedInterface.Hooker {

    /** 重复查看时写回的 extra 名。 */
    private static final String EXTRA_LIMIT_VIEW_TIMES = "limitViewTimes";
    /** 查看次数读取所用的 extra 名（原 {@code v3 = "type"}）。 */
    private static final String EXTRA_TYPE = "type";
    /** {@code WindowManager.LayoutParams.FLAG_SECURE}。 */
    private static final int FLAG_SECURE = 0x2000;
    /** 查看次数读取默认值（原 {@code -1}）。 */
    private static final int DEFAULT_LIMIT_VIEW_TIMES = -1;
    /** 注入保存按钮的查看次数下界。 */
    private static final int LIMIT_MIN = 1;
    /** 注入保存按钮的查看次数上界。 */
    private static final int LIMIT_MAX = 4;
    /** 保存按钮的注入延时（毫秒）。 */
    private static final long[] SAVE_BUTTON_DELAYS = {0L, 250L, 750L, 1500L};

    private final ClassLoader hostClassLoader;

    public PrivacyPhotoPreviewHooker(ClassLoader hostClassLoader) {
        this.hostClassLoader = hostClassLoader;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) throws Throwable {
        Activity activity = (Activity) chain.getThisObject();
        SharedPreferences prefs = ViewKit.prefs(activity);

        if (PrivacyPreferences.allowRepeat(activity)) {
            activity.getIntent().putExtra(EXTRA_LIMIT_VIEW_TIMES, 0);
        }
        Object result = chain.proceed();

        if (prefs.getBoolean(SettingsKeys.PRIVACY_ALLOW_SCREENSHOT, false)) {
            activity.getWindow().clearFlags(FLAG_SECURE);
        }

        int limitViewTimes =
                activity.getIntent().getIntExtra(EXTRA_TYPE, DEFAULT_LIMIT_VIEW_TIMES);
        if (prefs.getBoolean(SettingsKeys.PRIVACY_ALLOW_DOWNLOAD, false)) {
            int times = activity.getIntent().getIntExtra(EXTRA_TYPE, DEFAULT_LIMIT_VIEW_TIMES);
            if (times >= LIMIT_MIN && times <= LIMIT_MAX) {
                boolean savePhoto = times == 1 || times == 3;
                View decor = activity.getWindow().getDecorView();
                for (long delay : SAVE_BUTTON_DELAYS) {
                    decor.postDelayed(
                            new PrivacySaveButtonTask(activity, savePhoto, hostClassLoader), delay);
                }
            }
        }

        boolean download = prefs.getBoolean(SettingsKeys.PRIVACY_ALLOW_DOWNLOAD, false);
        boolean screenshot = prefs.getBoolean(SettingsKeys.PRIVACY_ALLOW_SCREENSHOT, false);
        boolean repeat = prefs.getBoolean(SettingsKeys.PRIVACY_ALLOW_REPEAT, false);
        Logger.i("privacy photo preview: download=" + download + " screenshot=" + screenshot
                + " repeat=" + repeat + " type=" + limitViewTimes);
        return result;
    }
}
