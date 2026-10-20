package dev.local.newsoul.feature.profile;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 资料页书签（插眼）功能域。
 *
 * <p>安装 {@code new-soul/profile-bookmark-icon}、{@code new-soul/profile-bookmark-click}、
 * {@code new-soul/profile-bookmark-menu} 三个 hook，并在资料页分享面板注入「插眼」菜单项、
 * 绑定图标、拦截点击以维护本机插眼记录列表。</p>
 *
 * <p>实际安装由 {@link ProfileBookmarkActions#install} 完成；其对内自行兜底，
 * 任一环节失败只记录日志并保持未就绪，不会抛出到 {@code Features.installAll}。</p>
 */
public final class ProfileBookmarkFeature implements FeatureInstaller {

    private static final String ID = "profile.bookmark";

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void install(ModuleContext context) {
        try {
            ProfileBookmarkActions.install(context.module(), context.classLoader());
        } catch (Throwable throwable) {
            Logger.e("profile bookmarks unavailable", throwable);
        }
    }
}
