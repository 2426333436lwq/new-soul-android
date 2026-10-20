package dev.local.newsoul.feature.privacy;

import android.os.Bundle;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import io.github.libxposed.api.XposedModule;

/**
 * 隐私功能域（原 {@code Q0.D} 照片预览安装 + {@code Q0.J} 重复消费守卫安装）。
 *
 * <p>三段独立降级：</p>
 * <ul>
 *   <li><b>照片预览</b>：对 {@code PrivacyProtectPreviewActivity.init(Bundle)} 安装
 *       {@code new-soul/privacy-photo-preview}（原 {@code a} case 16）；</li>
 *   <li><b>重复消费守卫</b>（隐私路由 {@code privacy} 与闪照路由 {@code snap}）：对两个宿主
 *       Activity 上的无参 {@code void} 守卫方法安装 {@code a} case 1（{@link PrivacyRepeatGuardHooker}），
 *       并对所在类及其内部类的全部非 abstract / native 方法去优化；</li>
 *   <li><b>闪照预览</b>：仅 {@code snap} 路由额外对 {@code SnapChatActivity.init(Bundle)} 安装
 *       {@code new-soul/privacy-repeat-snap-preview}（原 {@code a} case 17）。</li>
 * </ul>
 *
 * <p>守卫方法签名与原实现严格一致：返回类型必须是 {@code void} 且<b>非</b>静态，否则抛出
 * {@code "Unexpected repeat guard signature: <方法>"} 并记录
 * {@code "privacy repeat guard unavailable: <route>"}。</p>
 */
public final class PrivacyProtectFeature implements FeatureInstaller {

    private static final String ID = "chat.privacy";

    /** 照片预览目标方法名。 */
    private static final String METHOD_INIT = "init";
    /** 隐私路由守卫方法名（原 {@code "G"}）。 */
    private static final String METHOD_GUARD_PRIVACY = "G";
    /** 闪照路由守卫方法名（原 {@code "C"}）。 */
    private static final String METHOD_GUARD_SNAP = "C";

    private static final String ROUTE_PRIVACY = "privacy";
    private static final String ROUTE_SNAP = "snap";

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        ClassLoader loader = context.classLoader();

        boolean photoReady = installPhotoPreview(module, loader);
        boolean privacyReady = installRepeatGuard(module, loader,
                HostClasses.PRIVACY_PROTECT_PREVIEW_ACTIVITY, METHOD_GUARD_PRIVACY,
                ROUTE_PRIVACY, HookIds.PRIVACY_REPEAT_PRIVACY);
        boolean snapReady = installRepeatGuard(module, loader,
                HostClasses.SNAP_CHAT_ACTIVITY, METHOD_GUARD_SNAP,
                ROUTE_SNAP, HookIds.PRIVACY_REPEAT_SNAP);

        Logger.i("privacy hooks: photo=" + photoReady
                + "; privacy=" + privacyReady
                + "; snap=" + snapReady);
    }

    /** 照片预览段（原 {@code Q0.D} hook 部分，无去优化）。 */
    private static boolean installPhotoPreview(XposedModule module, ClassLoader loader) {
        try {
            Class<?> activity = loader.loadClass(HostClasses.PRIVACY_PROTECT_PREVIEW_ACTIVITY);
            Method init = activity.getDeclaredMethod(METHOD_INIT, Bundle.class);
            if (!Hooks.intercept(module, HookIds.PRIVACY_PHOTO_PREVIEW, init,
                    new PrivacyPhotoPreviewHooker(loader))) {
                throw new IllegalStateException("privacy photo preview hook failed");
            }
            Logger.i("privacy photo controls installed");
            return true;
        } catch (Throwable throwable) {
            Logger.e("privacy photo controls unavailable", throwable);
            return false;
        }
    }

    /** 重复消费守卫段（原 {@code Q0.J}）。 */
    private static boolean installRepeatGuard(XposedModule module, ClassLoader loader,
            String className, String methodName, String route, String hookId) {
        try {
            Class<?> clazz = loader.loadClass(className);
            Method guard = clazz.getDeclaredMethod(methodName, (Class<?>[]) null);
            if (guard.getReturnType() != Void.TYPE || Modifier.isStatic(guard.getModifiers())) {
                throw new NoSuchMethodException("Unexpected repeat guard signature: " + guard);
            }
            if (!Hooks.intercept(module, hookId, guard, new PrivacyRepeatGuardHooker(route))) {
                throw new IllegalStateException("privacy repeat guard hook failed: " + route);
            }
            deoptimizeDeclared(module, hookId, clazz);
            for (Class<?> inner : clazz.getDeclaredClasses()) {
                deoptimizeDeclared(module, hookId, inner);
            }
            if (ROUTE_SNAP.equals(route)) {
                Method init = clazz.getDeclaredMethod(METHOD_INIT, Bundle.class);
                if (!Hooks.intercept(module, HookIds.PRIVACY_REPEAT_SNAP_PREVIEW, init,
                        new PrivacySnapPreviewHooker())) {
                    throw new IllegalStateException("privacy repeat snap preview hook failed");
                }
            }
            Logger.i("privacy repeat guard installed: route=" + route);
            return true;
        } catch (Throwable throwable) {
            Logger.e("privacy repeat guard unavailable: " + route, throwable);
            return false;
        }
    }

    /** 对类中全部非 abstract / native 的声明方法去优化（原 {@code Q0.m}）。 */
    private static void deoptimizeDeclared(XposedModule module, String hookId, Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isAbstract(method.getModifiers())
                    || Modifier.isNative(method.getModifiers())) {
                continue;
            }
            Hooks.deoptimize(module, hookId, method);
        }
    }
}
