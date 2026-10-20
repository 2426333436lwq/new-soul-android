package dev.local.newsoul.feature.chat.typing;

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
 * 隐藏「正在输入」提示（原 {@code Q0.x} 的聊天部分，hooker {@code e} case 1）。
 *
 * <p>宿主通过 {@code ChatManager.p0(ImMessage, boolean)} 下发聊天指令。模块在放行前识别
 * 「正在输入」指令：外层消息类型为 {@value #COMMAND_ENVELOPE_TYPE}，且其内层聊天消息类型为
 * {@value #TYPING_START}（开始输入）或 {@value #TYPING_STOP}（停止输入）时直接拦截，
 * 使对方看不到输入状态。</p>
 *
 * <p>开关 {@link SettingsKeys#CHAT_HIDE_TYPING} 默认开启；开关读取不到时不拦截。
 * 同一类中的 {@code o0(ImMessage)} 只作去优化，用于确保指令链路不被内联跳过。</p>
 */
public final class HideTypingFeature implements FeatureInstaller {

    private static final String UNAVAILABLE_LOG = "typing control unavailable";
    private static final String INSTALLED_LOG = "typing command control installed";
    private static final String BLOCKED_LOG = "typing command blocked; type=";

    /** 「正在输入」指令的外层消息类型。 */
    private static final int COMMAND_ENVELOPE_TYPE = 1;

    /** 内层聊天消息类型：开始输入。 */
    private static final int TYPING_START = 0x16;

    /** 内层聊天消息类型：停止输入。 */
    private static final int TYPING_STOP = 0x17;

    @Override
    public String id() {
        return "chat.hide-typing";
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        Class<?> chatManager = context.loadHostClass(HostClasses.IMLIB_CHAT_MANAGER);
        Class<?> message = context.loadHostClass(HostClasses.IMLIB_IM_MESSAGE);
        Class<?> chatMessage = context.loadHostClass(HostClasses.IMLIB_CHAT_MESSAGE);

        Method dispatch = Reflect.findDeclaredMethod(chatManager, "p0", message, boolean.class);
        Method observe = Reflect.findDeclaredMethod(chatManager, "o0", message);
        Method envelopeType = Reflect.findDeclaredMethod(message, "getMsgType");
        Method chatOf = Reflect.findDeclaredMethod(message, "getChatMessage");
        Method innerType = Reflect.findDeclaredMethod(chatMessage, "getMsgType");
        GlobalContextProvider globalContext = GlobalContextProvider.resolve(context);

        if (dispatch == null || observe == null || envelopeType == null
                || chatOf == null || innerType == null || !globalContext.isReady()) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }

        try {
            Hooks.intercept(module, HookIds.HIDE_TYPING, dispatch,
                    typingHooker(globalContext, envelopeType, chatOf, innerType));
            Hooks.deoptimize(module, HookIds.HIDE_TYPING, observe);
            Hooks.deoptimize(module, HookIds.HIDE_TYPING, dispatch);
        } catch (Throwable throwable) {
            Logger.e(UNAVAILABLE_LOG, throwable);
            return;
        }
        Logger.i(INSTALLED_LOG);
    }

    /** 识别并拦截正在输入指令；其余指令原样放行。 */
    private static XposedInterface.Hooker typingHooker(GlobalContextProvider globalContext,
                                                      Method envelopeType, Method chatOf,
                                                      Method innerType) {
        return chain -> {
            Object message = chain.getArg(0);
            Context context = globalContext.get();
            if (message != null && context != null
                    && Prefs.getBoolean(context, SettingsKeys.CHAT_HIDE_TYPING, true)) {
                Object envelope = envelopeType.invoke(message);
                if (envelope instanceof Integer && (Integer) envelope == COMMAND_ENVELOPE_TYPE) {
                    Object chat = chatOf.invoke(message);
                    if (chat != null) {
                        Object type = innerType.invoke(chat);
                        if (type instanceof Integer) {
                            int value = (Integer) type;
                            if (value == TYPING_START || value == TYPING_STOP) {
                                Logger.i(BLOCKED_LOG + value);
                                return null;
                            }
                        }
                    }
                }
            }
            return chain.proceed();
        };
    }
}