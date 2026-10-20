package dev.local.newsoul.feature.chat.repeat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.feature.chat.ChatBridges;
import dev.local.newsoul.feature.chat.HostAccess;
import dev.local.newsoul.feature.chat.MessageRewrite;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Reflect;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 复读功能域（原模块 {@code F.h} 的复读部分与 hooker {@code C}、{@code a} case {@code 0xb}、{@code D}）。
 *
 * <p>安装四组 hook：</p>
 * <ol>
 *   <li>{@code u0.p(...)} —— 宿主构建长按菜单时的点击入口，接管自定义项（{@link HookIds#REPEAT_CLICK}）；</li>
 *   <li>{@code u0.k(...)} —— 宿主渲染长按菜单，向菜单追加自定义项（{@link HookIds#REPEAT_MENU}）；</li>
 *   <li>{@code ConversationFragment.ac(...)} —— 菜单渲染前的上下文入口，压入线程私有栈
 *       （{@link HookIds#REPEAT_CONTEXT}）；</li>
 *   <li>{@code ChatLongClickPopUp} 的菜单渲染方法（返回自身、参数为 {@code List}）—— 菜单渲染
 *       回退路径（{@link HookIds#ACTION_MARKS_CHAT}）。</li>
 * </ol>
 *
 * <p>第 3、4 组是可选的：宿主签名不匹配时只记录日志并跳过，不影响前两组主路径。
 * 前两组任一安装失败则整个功能域放弃。</p>
 *
 * <p>本功能域无开关 —— 与原模块一致，安装成功即生效。</p>
 */
public final class RepeatFeature implements FeatureInstaller {

    private static final String UNAVAILABLE_LOG = "repeat unavailable";
    private static final String INSTALLED_LOG = "repeat installed; text/image/audio";
    private static final String CONTEXT_HOOK_LOG = "repeat.context";
    private static final String MENU_HOOK_LOG = "chat action marks unavailable";
    private static final String CLICK_FAILED_LOG = "repeat failed; no automatic retry";
    private static final String MENU_SKIPPED_LOG = "repeat menu skipped";

    private static final String TOAST_VOICE_FAILED = "无法保存语音，请重新选择";
    private static final String TOAST_FORWARD_FAILED = "无法打开转发，请重新选择";
    private static final String TOAST_REPEAT_FAILED = "复读未完成，请检查消息状态";

    /** {@code u0.p} 中长按菜单项的实参下标。 */
    private static final int ARG_MENU_ITEM = 7;

    /** {@code u0.p} 中上下文实参下标（失败提示的宿主）。 */
    private static final int ARG_CONTEXT = 2;

    @Override
    public String id() {
        return "chat.repeat";
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        Class<?> fragment = context.loadHostClass(HostClasses.CHAT_CONVERSATION_FRAGMENT);
        Class<?> message = context.loadHostClass(HostClasses.IMLIB_IM_MESSAGE);
        Class<?> chatMessage = context.loadHostClass(HostClasses.IMLIB_CHAT_MESSAGE);
        Class<?> popUp = context.loadHostClass(HostClasses.CHAT_LONG_CLICK_POPUP);
        Class<?> menuItemClass = context.loadHostClass(HostClasses.CHAT_LONG_CLICK_ITEM);
        Class<?> sessionProvider = context.loadHostClass(HostClasses.IMLIB_SESSION_PROVIDER);
        Class<?> chatUtils = context.loadHostClass(HostClasses.CHAT_UTILS_U0);
        Class<?> sendUtils = context.loadHostClass(HostClasses.CHAT_UTILS_Y4);
        Class<?> sendHelper = context.loadHostClass(HostClasses.CHAT_SEND_HELPER);
        Class<?> popUpContext = context.loadHostClass(HostClasses.UJ_C0);
        Class<?> keyboard = context.loadHostClass(HostClasses.LIB_INPUT_MEDIA_KEYBOARD);
        Class<?> userBean = context.loadHostClass(HostClasses.IMLIB_USER_BEAN);

        Method currentAccount = Reflect.findDeclaredMethod(sessionProvider, "g");
        Method actionCode = Reflect.findDeclaredMethod(menuItemClass, "b");
        Constructor<?> menuItem = Reflect.findConstructor(menuItemClass,
                String.class, int.class, int.class);
        Method createChatMessage = Reflect.findDeclaredMethod(chatMessage, "create", String.class);
        Method setMsgContent = Reflect.findDeclaredMethod(chatMessage, "setMsgContent", Serializable.class);
        Method setMsgType = Reflect.findDeclaredMethod(chatMessage, "setMsgType", int.class);
        Method persistChatMessage = Reflect.findDeclaredMethod(sendUtils, "u", chatMessage);
        Method createSendMessage = Reflect.findDeclaredMethod(message, "createChatSendMsg",
                chatMessage, String.class);
        Method canSendMessage = Reflect.findDeclaredMethod(sendHelper, "a", message);
        Method dispatchMessage = Reflect.findDeclaredMethod(sendUtils, "p1", message);
        Method fillText = Reflect.findDeclaredMethod(sendUtils, "Y1", String.class, keyboard, userBean);
        Method click = Reflect.findDeclaredMethod(chatUtils, "p", message, fragment, Context.class,
                popUpContext, View.class, int.class, View.class, menuItemClass, int.class);
        Method render = Reflect.findDeclaredMethod(chatUtils, "k", fragment, View.class, message);

        if (currentAccount == null || actionCode == null || menuItem == null
                || createChatMessage == null || setMsgContent == null || setMsgType == null
                || persistChatMessage == null || createSendMessage == null || canSendMessage == null
                || dispatchMessage == null || fillText == null || click == null || render == null) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }

        RepeatAction action = new RepeatAction(currentAccount, createChatMessage, setMsgContent,
                setMsgType, persistChatMessage, createSendMessage, canSendMessage, dispatchMessage, fillText);
        RepeatMenu menu = new RepeatMenu(currentAccount, actionCode, menuItem, action);

        boolean installed = Hooks.intercept(module, HookIds.REPEAT_CLICK, click, clickHooker(menu, action))
                & Hooks.intercept(module, HookIds.REPEAT_MENU, render, menuHooker(menu));
        if (!installed) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }
        installContextHook(module, fragment, message);
        installFallbackHook(module, popUp, menu);
        Logger.i(INSTALLED_LOG);
    }

    /** 安装长按上下文压栈 hook（可选，失败仅记录日志）。 */
    private static void installContextHook(XposedModule module, Class<?> fragment, Class<?> message) {
        try {
            Method target = Reflect.findDeclaredMethod(fragment, "ac", View.class, message, int.class);
            if (target == null) {
                throw new NoSuchMethodException("ac");
            }
            Hooks.intercept(module, HookIds.REPEAT_CONTEXT, target, contextHooker());
        } catch (Throwable throwable) {
            Logger.w(CONTEXT_HOOK_LOG, throwable);
        }
    }

    /** 安装菜单渲染回退 hook（可选，失败仅记录日志）。 */
    private static void installFallbackHook(XposedModule module, Class<?> popUp, RepeatMenu menu) {
        try {
            Method renderer = findMenuRenderer(popUp);
            Hooks.intercept(module, HookIds.ACTION_MARKS_CHAT, renderer, fallbackHooker(menu));
            RepeatMenu.markNativeMenuInstalled();
        } catch (Throwable throwable) {
            Logger.w(MENU_HOOK_LOG, throwable);
        }
    }

    /**
     * 查找宿主的长按菜单渲染方法：返回类型为自身、且形参恰为一个 {@link List}。
     *
     * @throws IllegalStateException 存在多个候选（宿主签名有歧义）
     * @throws NoSuchMethodException 无候选（宿主签名不匹配）
     */
    private static Method findMenuRenderer(Class<?> popUp) throws NoSuchMethodException {
        Method found = null;
        for (Method method : popUp.getDeclaredMethods()) {
            if (method.getReturnType() != popUp) {
                continue;
            }
            if (!Arrays.equals(method.getParameterTypes(), new Class<?>[]{List.class})) {
                continue;
            }
            if (found != null) {
                throw new IllegalStateException("Ambiguous menu renderer");
            }
            found = method;
        }
        if (found == null) {
            throw new NoSuchMethodException("Menu renderer");
        }
        return found;
    }

    /** 菜单点击接管：识别自定义动作并分派，其余动作原样放行。 */
    private static XposedInterface.Hooker clickHooker(RepeatMenu menu, RepeatAction action) {
        return chain -> {
            Object item = chain.getArg(ARG_MENU_ITEM);
            if (item == null) {
                return chain.proceed();
            }
            int code = menu.actionCodeOf(item);
            if (code != RepeatMenu.ACTION_REPEAT && code != RepeatMenu.ACTION_FORWARD
                    && code != RepeatMenu.ACTION_VOICE_LIBRARY) {
                return chain.proceed();
            }
            RepeatEntry entry = RepeatEntry.find(item);
            if (entry == null || !entry.markHandled()) {
                return null;
            }
            try {
                dispatch(action, entry, code);
            } catch (Throwable throwable) {
                Logger.w(CLICK_FAILED_LOG, throwable);
                notifyFailure(chain.getArg(ARG_CONTEXT), code);
            }
            return null;
        };
    }

    /** 按动作码路由到复读 / 转发 / 存入语音库。 */
    private static void dispatch(RepeatAction action, RepeatEntry entry, int code) throws Exception {
        if (code == RepeatMenu.ACTION_VOICE_LIBRARY) {
            action.requireActivity(entry);
            ChatBridges.voiceLibrarySaver().save(entry.owner(), bodyOf(entry));
        } else if (code == RepeatMenu.ACTION_FORWARD) {
            Activity activity = action.requireActivity(entry);
            Object chat = HostAccess.call(entry.message(), "getChatMessage");
            int messageType = ((Integer) HostAccess.call(chat, "getMsgType")).intValue();
            ChatBridges.forwardLauncher().launch(activity, entry.accountId(), entry.peerId(),
                    messageType, MessageRewrite.fromChatMessage(chat));
        } else {
            action.repeat(entry);
        }
    }

    /** 复刻消息体（转发 / 语音库路径共用）。 */
    private static Object bodyOf(RepeatEntry entry) throws Exception {
        return MessageRewrite.fromChatMessage(HostAccess.call(entry.message(), "getChatMessage"));
    }

    /** 动作失败提示。 */
    private static void notifyFailure(Object context, int code) {
        String message;
        if (code == RepeatMenu.ACTION_VOICE_LIBRARY) {
            message = TOAST_VOICE_FAILED;
        } else if (code == RepeatMenu.ACTION_FORWARD) {
            message = TOAST_FORWARD_FAILED;
        } else {
            message = TOAST_REPEAT_FAILED;
        }
        Toast.makeText((Context) context, message, Toast.LENGTH_SHORT).show();
    }

    /** 菜单渲染：追加自定义项；渲染失败时回退为原菜单。 */
    private static XposedInterface.Hooker menuHooker(RepeatMenu menu) {
        return chain -> {
            Object result = chain.proceed();
            try {
                return menu.render(result, chain.getArg(0), chain.getArg(2));
            } catch (Throwable throwable) {
                Logger.w(MENU_SKIPPED_LOG, throwable);
                return result;
            }
        };
    }

    /** 长按上下文压栈：宿主方法返回后立即出栈。 */
    private static XposedInterface.Hooker contextHooker() {
        return chain -> {
            RepeatContext context = new RepeatContext(chain.getThisObject(), chain.getArg(1),
                    chain.getArg(0));
            try {
                return chain.proceed();
            } finally {
                context.close();
            }
        };
    }

    /** 菜单渲染回退：未改动时放行原参数，改动时以新菜单替换实参。 */
    private static XposedInterface.Hooker fallbackHooker(RepeatMenu menu) {
        return chain -> {
            Object original = chain.getArg(0);
            Object rendered = menu.renderFallback(original);
            if (rendered == original) {
                return chain.proceed();
            }
            return chain.proceed(new Object[]{rendered});
        };
    }
}