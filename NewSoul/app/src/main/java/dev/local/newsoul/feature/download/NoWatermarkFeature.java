package dev.local.newsoul.feature.download;

import android.content.Context;

import java.lang.reflect.Method;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;

/**
 * 下载无水印。
 *
 * <p>宿主在保存图片 / 视频时通过一个布尔参数决定「是否叠加水印」。本功能域分别 hook
 * 图片下载与视频下载的入口方法，在对应开关开启时把该参数改写为 {@code false}。</p>
 *
 * <ul>
 *   <li>图片：{@code ImageDownloader.f(String, boolean, boolean, ImageMarkProcessor, b)}，
 *       标志位为第 3 个参数；上下文由宿主静态方法 {@code lf.b() } 提供。</li>
 *   <li>视频：{@code h2.b(String, Context, long, boolean)}，标志位为第 4 个参数；
 *       上下文直接取第 2 个参数。</li>
 * </ul>
 *
 * <p>两个入口互相独立：任一签名变化只影响自身，另一项仍可正常工作。</p>
 */
public final class NoWatermarkFeature implements FeatureInstaller {

    /** 图片下载方法中「是否加处理」的参数下标。 */
    private static final int IMAGE_FLAG_ARG_INDEX = 2;

    /** 图片下载方法中不存在 Context 参数，改用静态提供者。 */
    private static final int IMAGE_CONTEXT_ARG_INDEX = -1;

    /** 视频下载方法中「是否加处理」的参数下标。 */
    private static final int VIDEO_FLAG_ARG_INDEX = 3;

    /** 视频下载方法中 Context 参数的下标。 */
    private static final int VIDEO_CONTEXT_ARG_INDEX = 1;

    @Override
    public String id() {
        return "download-no-watermark";
    }

    @Override
    public void install(ModuleContext context) {
        installImageDownload(context);
        installVideoDownload(context);
    }

    private void installImageDownload(ModuleContext context) {
        Method contextProvider = Reflect.findDeclaredMethod(
                context.loadHostClass(HostClasses.PRIVATE_CONTEXT_PROVIDER), "b");
        Method target = Reflect.findDeclaredMethod(
                context.loadHostClass(HostClasses.SQUARE_IMAGE_DOWNLOADER), "f",
                String.class, boolean.class, boolean.class,
                context.loadHostClass(HostClasses.SQUARE_IMAGE_MARK_PROCESSOR),
                context.loadHostClass(HostClasses.SQUARE_UTILS_B));
        Hooks.installFlagOverride(context.module(), HookIds.DOWNLOAD_IMAGE_NO_WATERMARK, target,
                SettingsKeys.DOWNLOAD_IMAGE_NO_WATERMARK,
                IMAGE_FLAG_ARG_INDEX, IMAGE_CONTEXT_ARG_INDEX, contextProvider,
                "image watermark control unavailable");
    }

    private void installVideoDownload(ModuleContext context) {
        Method target = Reflect.findDeclaredMethod(
                context.loadHostClass(HostClasses.SQUARE_UTILS_H2), "b",
                String.class, Context.class, long.class, boolean.class);
        Hooks.installFlagOverride(context.module(), HookIds.DOWNLOAD_VIDEO_NO_WATERMARK, target,
                SettingsKeys.DOWNLOAD_VIDEO_NO_WATERMARK,
                VIDEO_FLAG_ARG_INDEX, VIDEO_CONTEXT_ARG_INDEX, null,
                "video watermark control unavailable");
    }
}