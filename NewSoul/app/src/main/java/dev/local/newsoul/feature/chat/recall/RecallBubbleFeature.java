package dev.local.newsoul.feature.chat.recall;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.feature.FeatureInstaller;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.framework.MessageValues;
import dev.local.newsoul.framework.ModuleContext;
import dev.local.newsoul.framework.Prefs;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 撤回气泡标记（原 {@code Q0.K}）。
 *
 * <p>聊天行绑定消息时，若该消息是「已保留的对方撤回消息」，则在行视图上补一条撤回提示。
 * 消息是否属于本行由宿主消息对象与当前会话共同判定（{@link MessageValues#matchesRecallSession}），
 * 是否已被保留则由 {@link RecallStore} 中的指纹决定。</p>
 *
 * <p>样式由设置项 {@link SettingsKeys#CHAT_RECALL_STYLE} 决定（默认
 * {@link SettingsKeys#RECALL_STYLE_BELOW}），具体渲染见 {@link RecallBubbleStyle}。
 * 显示名优先取会话对象的 {@code alias}、其次 {@code signature} public 字段，都为空时用「对方」。</p>
 *
 * <p>行视图会被 RecyclerView 复用，因此每次绑定都先还原上一次的样式改动再按需重套。</p>
 */
public final class RecallBubbleFeature implements FeatureInstaller {

    private static final String UNAVAILABLE_LOG = "chat recall mark unavailable";
    private static final String STYLE_FAILED_LOG = "recall message style unavailable";

    /** 取不到会话昵称时的兜底显示名。 */
    private static final String DEFAULT_DISPLAY_NAME = "对方";

    /** 会话对象上可用于显示名的 public 字段，按优先级排列。 */
    private static final String[] DISPLAY_NAME_FIELDS = {"alias", "signature"};

    @Override
    public String id() {
        return "chat.recall-mark";
    }

    @Override
    public void install(ModuleContext context) {
        XposedModule module = context.module();
        ClassLoader classLoader = context.classLoader();
        MessageValues messages = MessageValues.from(context);
        if (!messages.isReady()) {
            Logger.e(UNAVAILABLE_LOG);
            return;
        }
        try {
            Class<?> item = classLoader.loadClass(HostClasses.CHAT_ABS_DUAL_ITEM);
            Class<?> holder = classLoader.loadClass(item.getName() + "$ViewHolder");
            Class<?> message = classLoader.loadClass(HostClasses.IMLIB_IM_MESSAGE);

            Field itemView = holder.getField("itemView");
            Field owner = item.getDeclaredField("e");
            owner.setAccessible(true);
            Method bind = findBindMethod(item, holder, message);

            Hooks.intercept(module, HookIds.CHAT_RECALL_MARK, bind,
                    bindHooker(itemView, owner, messages));
            Hooks.deoptimize(module, HookIds.CHAT_RECALL_MARK, bind);
        } catch (Throwable throwable) {
            Logger.e(UNAVAILABLE_LOG, throwable);
        }
    }

    /**
     * 在 {@code AbsChatDualItem} 的声明方法中匹配消息行绑定方法。
     *
     * <p>签名固定为 {@code (ViewHolder, ImMessage, int, List)}；匹配到多个说明宿主结构变化，
     * 此时宁可整体不安装也不猜。</p>
     */
    private static Method findBindMethod(Class<?> item, Class<?> holder, Class<?> message)
            throws NoSuchMethodException {
        Method candidate = null;
        for (Method method : item.getDeclaredMethods()) {
            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length != 4
                    || parameters[0] != holder
                    || parameters[1] != message
                    || parameters[2] != int.class
                    || parameters[3] != List.class) {
                continue;
            }
            if (candidate != null) {
                throw new IllegalStateException("Ambiguous chat row binding");
            }
            candidate = method;
        }
        if (candidate == null) {
            throw new NoSuchMethodException("AbsChatDualItem bind");
        }
        return candidate;
    }

    /** 行绑定回调：先还原，放行绑定，再按需套用撤回样式。 */
    private static XposedInterface.Hooker bindHooker(Field itemView, Field owner,
                                                    MessageValues messages) {
        return chain -> {
            View view = (View) itemView.get(chain.getArg(0));
            RecallBubbleStyle.reset(view);
            Object result = chain.proceed();
            try {
                Object message = chain.getArg(1);
                Context context = view.getContext();
                if (messages.matchesRecallSession(message)
                        && RecallStore.contains(context, messages.recallFingerprint(message))) {
                    String style = Prefs.getString(context, SettingsKeys.CHAT_RECALL_STYLE,
                            SettingsKeys.RECALL_STYLE_BELOW);
                    RecallBubbleStyle.apply(view, style, displayName(owner.get(chain.getThisObject())));
                }
            } catch (Throwable throwable) {
                Logger.e(STYLE_FAILED_LOG, throwable);
            }
            return result;
        };
    }

    /** 取会话显示名：{@code alias} 优先，其次 {@code signature}，都为空时用「对方」。 */
    private static String displayName(Object item) throws Exception {
        if (item == null) {
            return DEFAULT_DISPLAY_NAME;
        }
        for (String name : DISPLAY_NAME_FIELDS) {
            Object value = item.getClass().getField(name).get(item);
            if (value instanceof String && !((String) value).isEmpty()) {
                return (String) value;
            }
        }
        return DEFAULT_DISPLAY_NAME;
    }
}