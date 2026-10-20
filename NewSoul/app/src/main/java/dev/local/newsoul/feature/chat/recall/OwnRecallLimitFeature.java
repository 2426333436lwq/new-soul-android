package dev.local.newsoul.feature.chat.recall;

import android.content.Context;

import java.lang.reflect.Method;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.GlobalContextProvider;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Prefs;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 自己消息撤回时限解除（原 {@code g1.a}）。
 *
 * <p>宿主只允许在 2 分钟内撤回自己的消息，判断依据是消息的本地时间戳。
 * 模块的做法是：当用户对自己在<u>当前会话</u>里发出的非匿名消息打开撤回入口、
 * 且开关开启时，在调用线程上标记「即将读取该消息的本地时间」；
 * 随后宿主读取时间戳时，若已超过 2 分钟则把结果替换为「当前时间」，从而绕过时限。</p>
 *
 * <p>标记用 {@link ThreadLocal} 传递：撤回入口与实际读取时间戳发生在同一线程、同一消息上。</p>
 */
public final class OwnRecallLimitFeature implements FeatureInstaller {

    private static final String UNAVAILABLE_LOG = "own recall time limit unavailable";
    private static final String INSTALLED_LOG = "own recall time limit hook installed";
    private static final String BYPASS_LOG = "own recall local age check bypassed";
    private static final String ELIGIBILITY_LOG = "own recall eligibility unavailable";

    /** 宿主允许的撤回时限：2 分钟（0x1d4c0 毫秒）。 */
    private static final long RECALL_WINDOW_MILLIS = 0x1d4c0L;

    /** 待绕过时限的消息；由撤回入口写入，由时间戳读取回调消费。 */
    private static final ThreadLocal<Object> PENDING_MESSAGE = new ThreadLocal<>();

    @Override
    public String id() {
        return "chat.own-recall-limit";
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        Class<?> imMessage = context.loadHostClass(HostClasses.IMLIB_IM_MESSAGE);
        Class<?> sessionProvider = context.loadHostClass(HostClasses.IMLIB_SESSION_PROVIDER);
        Class<?> withdrawHandler = context.loadHostClass(HostClasses.CHAT_WITH_DRAW_HANDLER);
        Class<?> globalContextOwner = context.loadHostClass(HostClasses.PRIVATE_CONTEXT_PROVIDER);

        Method localTime = Reflect.findDeclaredMethod(imMessage, "getLocalTime");
        Method from = Reflect.findDeclaredMethod(imMessage, "getFrom");
        Method anonymity = Reflect.findDeclaredMethod(imMessage, "isAnonymityChat");
        Method session = Reflect.findDeclaredMethod(sessionProvider, "g");
        Method globalContext = Reflect.findDeclaredMethod(globalContextOwner, "b");
        Method withdrawEntry = Reflect.findDeclaredMethod(withdrawHandler, "d", imMessage);

        if (localTime == null || from == null || anonymity == null || session == null
                || globalContext == null || withdrawEntry == null) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }

        try {
            Hooks.intercept(module, HookIds.OWN_RECALL_AGE_READ, localTime, ageReadHooker());

            Hooks.intercept(module, HookIds.OWN_RECALL_ENTRY, withdrawEntry,
                    withdrawEntryHooker(session, from, anonymity, globalContext));
            Hooks.deoptimize(module, HookIds.OWN_RECALL_ENTRY, withdrawEntry);
        } catch (Throwable throwable) {
            Logger.e(UNAVAILABLE_LOG, throwable);
            return;
        }
        Logger.i(INSTALLED_LOG);
    }

    /** 时间戳读取：被标记且已超时限时，用当前时间替换返回值。 */
    private static XposedInterface.Hooker ageReadHooker() {
        return chain -> {
            Object result = chain.proceed();
            if (PENDING_MESSAGE.get() != chain.getThisObject()) {
                return result;
            }
            PENDING_MESSAGE.remove();
            long now = System.currentTimeMillis();
            if (!(result instanceof Long) || now - (Long) result <= RECALL_WINDOW_MILLIS) {
                return result;
            }
            Logger.i(BYPASS_LOG);
            return now;
        };
    }

    /** 撤回入口：对自己在当前会话发出的非匿名消息标记待放行状态。 */
    private static XposedInterface.Hooker withdrawEntryHooker(Method session, Method from,
                                                              Method anonymity, Method globalContext) {
        return chain -> {
            Object message = chain.getArg(0);
            try {
                Object sessionId = session.invoke(null);
                if (message != null && sessionId instanceof String && !((String) sessionId).isEmpty()
                        && sessionId.equals(from.invoke(message))
                        && !Boolean.TRUE.equals(anonymity.invoke(message))) {
                    Context context = (Context) globalContext.invoke(null);
                    if (Prefs.getBoolean(context, SettingsKeys.CHAT_OWN_RECALL_NO_TIME_LIMIT, true)) {
                        Object previous = PENDING_MESSAGE.get();
                        PENDING_MESSAGE.set(message);
                        try {
                            return chain.proceed();
                        } finally {
                            if (previous == null) {
                                PENDING_MESSAGE.remove();
                            } else {
                                PENDING_MESSAGE.set(previous);
                            }
                        }
                    }
                }
            } catch (Throwable throwable) {
                Logger.w(ELIGIBILITY_LOG, throwable);
            }
            return chain.proceed();
        };
    }
}