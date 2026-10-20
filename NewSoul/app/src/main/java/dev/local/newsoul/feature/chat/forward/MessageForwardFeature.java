package dev.local.newsoul.feature.chat.forward;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.feature.chat.ChatBridges;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import io.github.libxposed.api.XposedInterface;

/**
 * 消息转发功能域（原模块 {@code F.h} 的转发安装段与 hooker {@code a1}）。
 *
 * <p>安装成功才把转发入口登记到 {@link ChatBridges} —— 与原模块的「就绪标志」等价：
 * 宿主任一签名不匹配时整域放弃，复读菜单随之不显示「转发」项。</p>
 *
 * <p>{@link MessageForward#install} 已负责解析宿主成员并安装（选择会话页）上的各阶段 hook，
 * 本类只提供对应的 hooker 实现。</p>
 */
public final class MessageForwardFeature implements FeatureInstaller {

    private static final String UNAVAILABLE_LOG = "message forward unavailable";

    /** {@code loadConversationList} 中会话列表实参下标。 */
    private static final int ARG_CONVERSATION_LIST = 1;

    @Override
    public String id() {
        return "chat.forward";
    }

    @Override
    public void install(ModuleContext context) {
        MessageForward forward = new MessageForward();
        try {
            forward.install(context.module(), context.classLoader());
        } catch (Throwable throwable) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }
        ChatBridges.registerForwardLauncher(forward);
    }

    /**
     * 条目点击（原 {@code a1} case 0）：接管选择会话页列表项的点击。
     *
     * <p>命中转发令牌时交由模块切换选中态并吞掉事件，否则原样放行。</p>
     */
    static XposedInterface.Hooker selectHooker(MessageForward forward) {
        return chain -> {
            Activity activity = (Activity) chain.getThisObject();
            if (!MessageForward.hasForwardToken(activity)) {
                return chain.proceed();
            }
            try {
                forward.handleItemClick(activity, ((Integer) chain.getArg(0)).intValue());
            } catch (Throwable throwable) {
                MessageForward.reportFailure(activity, throwable);
            }
            return null;
        };
    }

    /**
     * 会话列表加载（原 {@code a1} case 1）：只把可作为目标的会话交给宿主渲染。
     *
     * <p>过滤中途失败时，已选出的条目仍然生效 —— 与原实现一致。</p>
     */
    static XposedInterface.Hooker listHooker(MessageForward forward) {
        return chain -> {
            Activity activity = (Activity) chain.getThisObject();
            if (!MessageForward.hasForwardToken(activity)) {
                return chain.proceed();
            }
            List<Object> targets = new ArrayList<>();
            try {
                Object conversations = chain.getArg(ARG_CONVERSATION_LIST);
                if (conversations instanceof List) {
                    forward.filterTargets(activity, (List<?>) conversations, targets);
                }
            } catch (Throwable throwable) {
                MessageForward.reportFailure(activity, throwable);
            }
            Object[] args = chain.getArgs().toArray();
            args[ARG_CONVERSATION_LIST] = targets;
            return chain.proceed(args);
        };
    }

    /**
     * 页面初始化（原 {@code a1} case 2）：先让宿主完成原始初始化，再接管界面。
     *
     * <p>接管失败时提示并结束页面，避免停留在半接管状态。</p>
     */
    static XposedInterface.Hooker uiHooker(MessageForward forward) {
        return chain -> {
            Object result = chain.proceed();
            Activity activity = (Activity) chain.getThisObject();
            if (!MessageForward.hasForwardToken(activity)) {
                return result;
            }
            try {
                forward.prepareSelectionUi(activity);
            } catch (Throwable throwable) {
                MessageForward.reportFailure(activity, throwable);
                activity.finish();
            }
            return result;
        };
    }

    /**
     * 完成按钮（原 {@code a1} case 3）：以模块自己的确认框替代宿主动作。
     */
    static XposedInterface.Hooker completeHooker(MessageForward forward) {
        return chain -> {
            Activity activity = (Activity) chain.getThisObject();
            if (!MessageForward.hasForwardToken(activity)) {
                return chain.proceed();
            }
            try {
                forward.showConfirmation(activity);
            } catch (Throwable throwable) {
                MessageForward.reportFailure(activity, throwable);
            }
            return null;
        };
    }

    /**
     * 返回导航（原 {@code a1} case 4）：转发流程中吞掉返回事件。
     */
    static XposedInterface.Hooker navigationHooker() {
        return chain -> {
            Activity activity = (Activity) chain.getThisObject();
            return MessageForward.hasForwardToken(activity) ? null : chain.proceed();
        };
    }

    /**
     * 页面销毁清理（原 {@code a1} case 5）：清空令牌与上下文后放行。
     *
     * <p>配置变更（旋转）时不清理 —— 令牌与上下文需要留给重建后的页面。</p>
     */
    static XposedInterface.Hooker cleanupHooker(MessageForward forward) {
        return chain -> {
            forward.cleanup((Activity) chain.getThisObject());
            return chain.proceed();
        };
    }
}
