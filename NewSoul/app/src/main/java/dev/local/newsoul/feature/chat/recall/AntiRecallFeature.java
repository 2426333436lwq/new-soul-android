package dev.local.newsoul.feature.chat.recall;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.GlobalContextProvider;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.MessageValues;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Prefs;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 私聊防撤回。
 *
 * <p>两条独立链路：</p>
 * <ol>
 *   <li><b>实时会话</b>：宿主 {@code ChatManager.O(List)} 收到「对方撤回」时会把通知项放进列表；
 *       模块在放行前把能取回原文的通知项从列表中摘除，并在主线程刷新消息视图。</li>
 *   <li><b>漫游记录</b>：宿主 {@code RoamHandler.a(ImMessage)} 负责落盘撤回，
 *       模块对可保留的通知直接拦截（不调用 {@code proceed}），使其不写入漫游库。</li>
 * </ol>
 *
 * <p>两条链路共享同一个开关（{@link SettingsKeys#CHAT_ANTI_RECALL}，默认开启）。</p>
 */
public final class AntiRecallFeature implements FeatureInstaller {

    private static final String UNAVAILABLE_LOG = "private chat anti-recall unavailable";
    private static final String INSTALLED_LOG = "private chat anti-recall hooks installed";
    private static final String INTERCEPT_FAILED_LOG = "recall interception failed";
    private static final String ROAM_INTERCEPT_FAILED_LOG = "roam recall interception failed";
    private static final String ROAM_RETAINED_LOG = "recall retained; source=roam";

    @Override
    public String id() {
        return "chat.anti-recall";
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        Class<?> chatManager = context.loadHostClass(HostClasses.IMLIB_CHAT_MANAGER);
        Class<?> roamHandler = context.loadHostClass(HostClasses.IMLIB_ROAM_HANDLER);
        Class<?> imMessage = context.loadHostClass(HostClasses.IMLIB_IM_MESSAGE);
        Method onMessages = Reflect.findDeclaredMethod(chatManager, "O", List.class);
        Method onRoam = Reflect.findDeclaredMethod(roamHandler, "a", imMessage);

        MessageValues messages = MessageValues.from(context);
        RecallRules rules = RecallRules.resolve(context, messages);
        GlobalContextProvider globalContext = GlobalContextProvider.resolve(context);
        RecallRefresher refresher = RecallRefresher.resolve(context);
        if (onMessages == null || onRoam == null || !rules.isReady() || !globalContext.isReady()) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }

        try {
            Hooks.intercept(module, HookIds.CHAT_ANTI_RECALL, onMessages,
                    privateRecallHooker(rules, globalContext, refresher));
            Hooks.deoptimize(module, HookIds.CHAT_ANTI_RECALL, onMessages);

            Hooks.intercept(module, HookIds.CHAT_ANTI_RECALL_ROAM, onRoam,
                    roamRecallHooker(messages, globalContext));
            Hooks.deoptimize(module, HookIds.CHAT_ANTI_RECALL_ROAM, onRoam);
        } catch (Throwable throwable) {
            Logger.e(UNAVAILABLE_LOG, throwable);
            return;
        }
        Logger.i(INSTALLED_LOG);
    }

    /** 实时会话链路：摘除可保留的撤回通知，随后放行并刷新视图。 */
    private static XposedInterface.Hooker privateRecallHooker(RecallRules rules,
                                                             GlobalContextProvider globalContext,
                                                             RecallRefresher refresher) {
        return chain -> {
            boolean removed = false;
            try {
                Context context = globalContext.get();
                if (Prefs.getBoolean(context, SettingsKeys.CHAT_ANTI_RECALL, true)) {
                    Object arg = chain.getArg(0);
                    if (arg instanceof ArrayList) {
                        List<?> items = (List<?>) arg;
                        for (int index = items.size() - 1; index >= 0; index--) {
                            if (rules.shouldRetain(context, chain.getThisObject(), items.get(index))) {
                                items.remove(index);
                                removed = true;
                            }
                        }
                    }
                }
            } catch (Throwable throwable) {
                Logger.e(INTERCEPT_FAILED_LOG, throwable);
                removed = false;
            }
            Object result = chain.proceed();
            if (removed && refresher.isReady()) {
                new Handler(Looper.getMainLooper()).post(refresher::refresh);
            }
            return result;
        };
    }

    /** 漫游链路：可保留的撤回通知直接拦截，不写入漫游记录。 */
    private static XposedInterface.Hooker roamRecallHooker(MessageValues messages,
                                                           GlobalContextProvider globalContext) {
        return chain -> {
            try {
                Context context = globalContext.get();
                Object message = chain.getArg(0);
                if (Prefs.getBoolean(context, SettingsKeys.CHAT_ANTI_RECALL, true)
                        && messages.matchesRecallSession(message)
                        && messages.hasContent(message)) {
                    String fingerprint = messages.recallFingerprint(message);
                    if (RecallStore.contains(context, fingerprint)
                            || RecallStore.remember(context, fingerprint)) {
                        Logger.i(ROAM_RETAINED_LOG);
                        return null;
                    }
                }
            } catch (Throwable throwable) {
                Logger.e(ROAM_INTERCEPT_FAILED_LOG, throwable);
            }
            return chain.proceed();
        };
    }
}