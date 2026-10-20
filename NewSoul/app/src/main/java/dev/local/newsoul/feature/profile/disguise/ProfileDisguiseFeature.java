package dev.local.newsoul.feature.profile.disguise;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;

/**
 * 资料页伪装功能域。
 *
 * <p>安装 {@code new-soul/follow-disguise-header}、{@code new-soul/follow-disguise-revision}、
 * {@code new-soul/follow-disguise-meta}、{@code new-soul/days-revision}、
 * {@code new-soul/days-meta} 五个 hook，把本机自己主页的「关注 / 被关注 / 访客 / 加入天数」
 * 计数按设置伪装显示（absolute 直接改写 / delta 在原值上增减）。</p>
 *
 * <p>实际安装由 {@link ProfileDisguiseRegistry#install} 完成；其对内自行兜底，任一环节失败只记录
 * 日志并保持未就绪，不会抛出到 {@code Features.installAll}。</p>
 */
public final class ProfileDisguiseFeature implements FeatureInstaller {

    private static final String ID = "profile.disguise";

    @Override
    public String id() {
        return ID;
    }

    @Override
    public void install(ModuleContext context) {
        try {
            ProfileDisguiseRegistry.install(context.module(), context.classLoader());
        } catch (Throwable throwable) {
            Logger.e("follow disguise unavailable", throwable);
        }
    }
}
