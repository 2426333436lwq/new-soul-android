package dev.local.newsoul.feature.chat.recall;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;

/**
 * 撤回消息列表刷新（原 {@code h} 的 case 1）。
 *
 * <p>从撤回列表中摘除通知后，聊天页持有的消息视图仍显示旧状态，
 * 需要遍历宿主消息中心并逐个触发刷新。该动作在 hook 回调中排队到主线程执行。</p>
 */
final class RecallRefresher {

    private final Method messageHub;
    private final Method messageList;
    private final Method refreshUi;

    private RecallRefresher(Method messageHub, Method messageList, Method refreshUi) {
        this.messageHub = messageHub;
        this.messageList = messageList;
        this.refreshUi = refreshUi;
    }

    static RecallRefresher resolve(ModuleContext context) {
        Class<?> hub = context.loadHostClass(HostClasses.IMLIB_MESSAGE_HUB);
        Class<?> listener = context.loadHostClass(HostClasses.IMLIB_MSG_LISTENER);
        return new RecallRefresher(
                Reflect.findCompatibleMethod(hub, "v"),
                Reflect.findCompatibleMethod(hub, "w"),
                Reflect.findCompatibleMethod(listener, "onRefreshUi"));
    }

    boolean isReady() {
        return messageHub != null && messageList != null && refreshUi != null;
    }

    /** 遍历消息中心并逐个刷新；单个条目失败只记录警告。 */
    void refresh() {
        try {
            Object hub = Reflect.invoke(null, messageHub);
            Object raw = hub == null ? null : Reflect.invoke(hub, messageList);
            if (!(raw instanceof Collection)) {
                return;
            }
            for (Object message : new ArrayList<>((Collection<?>) raw)) {
                try {
                    refreshUi.invoke(message);
                } catch (Throwable throwable) {
                    Logger.w("recall view refresh skipped", throwable);
                }
            }
        } catch (Throwable throwable) {
            Logger.w("recall view refresh unavailable", throwable);
        }
    }
}