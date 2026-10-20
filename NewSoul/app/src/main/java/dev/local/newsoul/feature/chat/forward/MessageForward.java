package dev.local.newsoul.feature.chat.forward;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.local.newsoul.feature.chat.ChatBridges;
import dev.local.newsoul.feature.chat.HostAccess;
import dev.local.newsoul.feature.chat.MessageRewrite;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 消息转发（原模块 {@code d1}，配合 hooker {@code a1}）。
 *
 * <p>宿主的「转发」入口是选择会话页 {@code SelectConversationActivity}：模块借用该页面，
 * 通过 intent 额外项 {@value #TOKEN_EXTRA} 携带一次性令牌，串联「长按菜单 → 选择会话 → 确认框 → 发送」。</p>
 *
 * <p>负责三类工作：</p>
 * <ol>
 *   <li><b>安装</b>（{@link #install}）—— 反射解析宿主类与方法，并在选择会话页的点击、列表加载、
 *       页面初始化、完成按钮、返回键与 {@code Activity.onDestroy} 上安装 hook；</li>
 *   <li><b>发起</b>（{@link #launch}）—— 复读菜单点击「转发」时构造 {@link ForwardContext}，
 *       跳转到选择会话页；</li>
 *   <li><b>页面接管</b>—— 只保留可作为目标的会话、把列表切到多选态、维护「完成(n)」按钮，
 *       并在确认后就地发送消息。</li>
 * </ol>
 *
 * <p>与复读功能域通过 {@link ChatBridges#registerForwardLauncher} 衔接：安装成功才登记能力，
 * 复读菜单据此决定是否显示「转发」项。</p>
 */
public final class MessageForward implements ChatBridges.ForwardLauncher {

    /** 串联转发流程的一次性令牌所使用的 intent 额外项名。 */
    static final String TOKEN_EXTRA = "dev.local.newsoul.message_forward_token";

    private static final String EXTRA_IS_CHOICE = "isChoice";
    private static final String EXTRA_FROM_PREVIEW = "isFromPreview";
    private static final String EXTRA_ONLY_FINISH_AFTER_SHARE = "onlyFinishAfterShare";

    /** 宿主选择会话页上与转发相关的字段。 */
    private static final String FIELD_PRESENTER = "presenter";
    private static final String FIELD_ADAPTER = "b";
    private static final String FIELD_CONFIRM_BUTTON = "e";

    /** 会话条目与其持有者的字段名。 */
    private static final String FIELD_CONVERSATION = "conversation";
    private static final String FIELD_USER = "user";
    private static final String FIELD_ALIAS = "alias";
    private static final String FIELD_SIGNATURE = "signature";

    private static final String METHOD_ALL_DATA = "getAllData";
    private static final String METHOD_NOTIFY_CHANGED = "notifyDataSetChanged";
    private static final String METHOD_MULTI_SELECT = "k";
    private static final String METHOD_PRESENTER_MAP = "m0";
    private static final String METHOD_TO_USER_ID = "getToUserId";
    private static final String METHOD_CHAT_TYPE = "getChatType";

    /** 选择会话页上需要隐藏的宿主控件（关注、多选提示、分享）。 */
    private static final String[] VIEWS_TO_HIDE = {"ic_user_follow", "tv_mutily_choose", "tv_share"};

    private static final int VIEW_VISIBLE = 0;
    private static final int VIEW_GONE = 8;

    private static final int MAX_TARGETS = 9;

    private static final String DEFAULT_LABEL = "所选用户";
    private static final String LABEL_SEPARATOR = "、";

    private static final int TYPE_TEXT = 1;
    private static final int TYPE_AUDIO = 5;
    private static final String LABEL_TEXT = "文字消息";
    private static final String LABEL_AUDIO = "语音";
    private static final String LABEL_PHOTO = "照片";

    private static final String BUTTON_DONE = "完成";
    private static final String BUTTON_DONE_PREFIX = "完成(";
    private static final String BUTTON_DONE_SUFFIX = ")";
    private static final float ALPHA_ENABLED = 1.0f;
    private static final float ALPHA_DISABLED = 0.5f;

    private static final String CONFIRM_PREFIX = "将这条";
    private static final String CONFIRM_MIDDLE = "转发给以下 ";
    private static final String CONFIRM_SUFFIX = " 人？\n\n";

    private static final String TOAST_FAILED = "转发未完成，请重新选择";
    private static final String TOAST_EXPIRED = "转发已过期，请重新选择";
    private static final String TOAST_LIMIT = "最多选择9人";
    private static final String TOAST_DONE_PREFIX = "已提交转发给 ";
    private static final String TOAST_DONE_SUFFIX = " 人，请在目标会话查看";
    private static final String TOAST_INTERRUPTED_PREFIX = "转发中断，已提交 ";
    private static final String TOAST_INTERRUPTED_MIDDLE = "/";
    private static final String TOAST_INTERRUPTED_SUFFIX = "，请先核对目标会话";

    private Class<?> selectActivityClass;
    private Class<?> confirmListenerClass;
    private Class<?> itemClickListenerClass;
    private Constructor<?> confirmDialogConstructor;
    private Method setItemClickListener;
    private Method setConfirmListener;
    private Method currentAccountMethod;
    private Method createChatMessageMethod;
    private Method setMsgContentMethod;
    private Method setMsgTypeMethod;
    private Method setTransExtMapMethod;
    private Method persistChatMessageMethod;
    private Method createSendMessageMethod;
    private Method hubInstanceMethod;
    private Method hubSessionMethod;
    private Method dispatchMessageMethod;
    private Method selectConversationMethod;

    /** 一次转发流程的一次性令牌；与选择会话页 intent 中的额外项比对。 */
    private String token;

    /** 当前进行中的转发上下文。 */
    private ForwardContext context;

    /** 当前展示的确认对话框。 */
    private Dialog dialog;

    /**
     * 反射解析宿主成员并安装 hook（原模块 {@code d1.e}）。
     *
     * <p>任一签名不匹配或 hook 安装失败即抛异常，由 {@code MessageForwardFeature} 捕获后
     * 放弃整个功能域 —— 与原实现「异常则不置就绪标志」一致。</p>
     */
    void install(XposedModule module, ClassLoader classLoader) throws Exception {
        Class<?> chatMessage = classLoader.loadClass(HostClasses.IMLIB_CHAT_MESSAGE);
        Class<?> imMessage = classLoader.loadClass(HostClasses.IMLIB_IM_MESSAGE);
        Class<?> dialogClass = classLoader.loadClass(HostClasses.CHAT_CONFIRM_DIALOG);
        Class<?> confirmListener = classLoader.loadClass(HostClasses.CHAT_ON_CLICK_CONFIRM_LISTENER);
        Constructor<?> dialogConstructor = dialogClass.getConstructor(Context.class, String.class);
        Method confirmSetter = dialogClass.getMethod("d", confirmListener);

        Method account = classLoader.loadClass(HostClasses.IMLIB_SESSION_PROVIDER)
                .getMethod("g", (Class<?>[]) null);
        Method create = chatMessage.getMethod("create", String.class);
        Method setContent = chatMessage.getMethod("setMsgContent", Serializable.class);
        Method setType = chatMessage.getMethod("setMsgType", int.class);
        Method setExt = chatMessage.getMethod("setTransExtMap", Map.class);
        Method persist = classLoader.loadClass(HostClasses.CHAT_UTILS_Y4).getMethod("u", chatMessage);
        Method send = imMessage.getMethod("createChatSendMsg", chatMessage, String.class);

        Class<?> hub = classLoader.loadClass(HostClasses.IMLIB_MESSAGE_HUB);
        Method hubInstance = hub.getMethod("v", (Class<?>[]) null);
        Method hubSession = hub.getMethod("o", (Class<?>[]) null);
        Method dispatch = classLoader.loadClass(HostClasses.IMLIB_CHAT_MANAGER)
                .getMethod("q0", imMessage);

        Class<?> viewModel = classLoader.loadClass(HostClasses.UJ_K1);
        Method selectConversation = viewModel.getMethod("g2",
                classLoader.loadClass(HostClasses.CHAT_USER_CONVERSATION));
        // 仅作签名存在性检查：缺失即视为宿主不兼容
        viewModel.getMethod(METHOD_PRESENTER_MAP, (Class<?>[]) null);

        Class<?> adapter = classLoader.loadClass(HostClasses.CHAT_CONVERSATION_ADAPTER);
        // 仅作签名存在性检查
        adapter.getMethod(METHOD_MULTI_SELECT, boolean.class);
        Class<?> itemClickListener = classLoader.loadClass(HostClasses.EASY_RECYCLER_ITEM_CLICK_LISTENER);
        Method itemClickSetter = adapter.getMethod("setOnItemClickListener", itemClickListener);

        Class<?> selectActivity = classLoader.loadClass(HostClasses.CHAT_SELECT_CONVERSATION_ACTIVITY);
        // 仅作字段存在性检查
        selectActivity.getDeclaredField(FIELD_ADAPTER);
        selectActivity.getDeclaredField(FIELD_CONFIRM_BUTTON);
        Method itemClickHandler = selectActivity.getDeclaredMethod("F", int.class);

        MessageForward self = this;
        installHook(module, HookIds.MESSAGE_FORWARD_SELECT, itemClickHandler,
                MessageForwardFeature.selectHooker(self));
        installHook(module, HookIds.MESSAGE_FORWARD_LIST,
                selectActivity.getDeclaredMethod("loadConversationList", List.class, List.class, boolean.class),
                MessageForwardFeature.listHooker(self));
        installHook(module, HookIds.MESSAGE_FORWARD_UI,
                selectActivity.getDeclaredMethod("init", Bundle.class),
                MessageForwardFeature.uiHooker(self));
        for (Method complete : new Method[]{
                selectActivity.getDeclaredMethod("E", View.class),
                selectActivity.getDeclaredMethod("S", (Class<?>[]) null)}) {
            installHook(module, HookIds.MESSAGE_FORWARD_COMPLETE_PREFIX + complete.getName(), complete,
                    MessageForwardFeature.completeHooker(self));
        }
        for (Method navigation : new Method[]{
                selectActivity.getDeclaredMethod("D", Object.class),
                selectActivity.getDeclaredMethod("onClick", View.class)}) {
            installHook(module, HookIds.MESSAGE_FORWARD_NAVIGATION_PREFIX + navigation.getName(), navigation,
                    MessageForwardFeature.navigationHooker());
        }
        // 清理 hook 不参与去优化：Activity.onDestroy 属系统路径，只需拦一次
        installHookNoDeopt(module, HookIds.MESSAGE_FORWARD_CLEANUP,
                Activity.class.getDeclaredMethod("onDestroy", (Class<?>[]) null),
                MessageForwardFeature.cleanupHooker(self));

        this.selectActivityClass = selectActivity;
        this.confirmListenerClass = confirmListener;
        this.itemClickListenerClass = itemClickListener;
        this.confirmDialogConstructor = dialogConstructor;
        this.setItemClickListener = itemClickSetter;
        this.setConfirmListener = confirmSetter;
        this.currentAccountMethod = account;
        this.createChatMessageMethod = create;
        this.setMsgContentMethod = setContent;
        this.setMsgTypeMethod = setType;
        this.setTransExtMapMethod = setExt;
        this.persistChatMessageMethod = persist;
        this.createSendMessageMethod = send;
        this.hubInstanceMethod = hubInstance;
        this.hubSessionMethod = hubSession;
        this.dispatchMessageMethod = dispatch;
        this.selectConversationMethod = selectConversation;
    }

    /** 安装单个 hook 并去优化；失败抛出以使整个功能域放弃安装。 */
    private static void installHook(XposedModule module, String id, Method target,
                                    XposedInterface.Hooker hooker) {
        if (!Hooks.intercept(module, id, target, hooker)) {
            throw new IllegalStateException("Hook unavailable: " + id);
        }
        Hooks.deoptimize(module, id, target);
    }

    /** 安装单个 hook（不去优化）；失败抛出以使整个功能域放弃安装。 */
    private static void installHookNoDeopt(XposedModule module, String id, Method target,
                                           XposedInterface.Hooker hooker) {
        if (!Hooks.intercept(module, id, target, hooker)) {
            throw new IllegalStateException("Hook unavailable: " + id);
        }
    }

    /**
     * 发起一次转发（原模块 {@code d1.g}）：构造上下文并跳转到选择会话页。
     *
     * @throws IllegalStateException 宿主不可用、账号已切换或尚未安装
     */
    @Override
    public void launch(Activity activity, String accountId, String peerId, int messageType, Object body)
            throws Exception {
        if (activity.isFinishing() || activity.isDestroyed() || !accountId.equals(currentAccountId())) {
            throw new IllegalStateException("Forward unavailable");
        }
        ForwardContext created = new ForwardContext(accountId, peerId, messageType,
                MessageRewrite.rebuild(messageType, body), SystemClock.elapsedRealtime());
        this.context = created;
        String createdToken = UUID.randomUUID().toString();
        this.token = createdToken;

        Intent intent = new Intent(activity, selectActivityClass);
        intent.putExtra(TOKEN_EXTRA, createdToken);
        intent.putExtra(EXTRA_IS_CHOICE, false);
        intent.putExtra(EXTRA_FROM_PREVIEW, true);
        intent.putExtra(EXTRA_ONLY_FINISH_AFTER_SHARE, true);
        try {
            activity.startActivity(intent);
        } catch (RuntimeException exception) {
            this.context = null;
            this.token = null;
            throw exception;
        }
    }

    /**
     * 解析当前选择会话页对应的转发上下文（原模块 {@code d1.i}）。
     *
     * <p>令牌需与 intent 携带的一致，且上下文仍在 10 分钟有效期内、未被消费。</p>
     */
    ForwardContext resolveContext(Activity activity) throws ReflectiveOperationException {
        String pendingToken = this.token;
        ForwardContext pending = this.context;
        if (pending == null || pendingToken == null) {
            return null;
        }
        if (!pendingToken.equals(activity.getIntent().getStringExtra(TOKEN_EXTRA))) {
            return null;
        }
        if (!pending.isStillValid(currentAccountId(), SystemClock.elapsedRealtime())) {
            return null;
        }
        return pending;
    }

    /**
     * 弹出转发确认框（原模块 {@code d1.b}）。
     *
     * <p>文案形如「将这条文字消息转发给以下 N 人？」并列出目标昵称；确认回调由
     * {@link ForwardConfirmHandler} 接收。</p>
     */
    void showConfirmation(Activity activity) throws Exception {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        ForwardContext pending = resolveContext(activity);
        if (pending == null) {
            toast(activity, TOAST_EXPIRED);
            activity.finish();
            return;
        }
        LinkedHashMap<String, String> labels = buildLabels(activity, pending);
        if (labels.isEmpty() || labels.size() > MAX_TARGETS) {
            return;
        }
        ArrayList<String> targets = new ArrayList<>(labels.keySet());
        String text = CONFIRM_PREFIX + typeLabel(pending.messageType()) + CONFIRM_MIDDLE
                + targets.size() + CONFIRM_SUFFIX + String.join(LABEL_SEPARATOR, labels.values());
        Dialog created = (Dialog) confirmDialogConstructor.newInstance(activity, text);
        Object listener = Proxy.newProxyInstance(confirmListenerClass.getClassLoader(),
                new Class<?>[]{confirmListenerClass},
                new ForwardConfirmHandler(this, activity, token, pending, labels, targets, created));
        setConfirmListener.invoke(created, listener);
        this.dialog = created;
        created.setOnDismissListener(new ForwardDialogDismissListener(this, created));
        created.show();
    }

    /**
     * 选择会话页条目点击（原模块 {@code d1.m}）：切换目标的选中状态，并同步「完成(n)」按钮。
     */
    void handleItemClick(Activity activity, int position) throws Exception {
        ForwardContext pending = resolveContext(activity);
        if (pending == null) {
            toast(activity, TOAST_EXPIRED);
            activity.finish();
            return;
        }
        Object adapter = readField(activity, FIELD_ADAPTER);
        List<?> all = (List<?>) HostAccess.call(adapter, METHOD_ALL_DATA);
        if (position < 0 || position >= all.size()) {
            return;
        }
        Object item = all.get(position);
        Object conversation = readField(item, FIELD_CONVERSATION);
        if (conversation == null || readField(item, FIELD_USER) == null) {
            return;
        }
        String peerId = (String) HostAccess.call(conversation, METHOD_TO_USER_ID);
        int chatType = ((Integer) HostAccess.call(conversation, METHOD_CHAT_TYPE)).intValue();
        if (!pending.allowsTarget(peerId, chatType)) {
            return;
        }
        Map<?, ?> selection = recipientMap(activity);
        if (!selection.containsValue(peerId) && selection.size() >= MAX_TARGETS) {
            toast(activity, TOAST_LIMIT);
            return;
        }
        selectConversationMethod.invoke(readField(activity, FIELD_PRESENTER), item);
        HostAccess.call(adapter, METHOD_NOTIFY_CHANGED);
        refreshConfirmButton(activity);
    }

    /** 向单个目标发送已复刻的消息（原模块 {@code d1.k}）。 */
    void sendTo(ForwardContext pending, String peerId) throws Exception {
        Object message = createChatMessageMethod.invoke(null, peerId);
        setMsgContentMethod.invoke(message, MessageRewrite.rebuild(pending.messageType(), pending.body()));
        setTransExtMapMethod.invoke(message, new HashMap<>());
        setMsgTypeMethod.invoke(message, pending.messageType());
        persistChatMessageMethod.invoke(null, message);
        Object imMessage = createSendMessageMethod.invoke(null, message, peerId);
        Object hub = hubInstanceMethod.invoke(null);
        Object session = hubSessionMethod.invoke(hub);
        dispatchMessageMethod.invoke(session, imMessage);
    }

    /**
     * 选择会话页初始化后的界面接管（原模块 {@code a1} case 2）。
     *
     * <p>把会话列表切到多选态、只响应可作为目标的条目，隐藏宿主自带的关注 / 分享控件，
     * 并让「完成」按钮走模块自己的确认框。</p>
     */
    void prepareSelectionUi(Activity activity) throws Exception {
        if (resolveContext(activity) == null) {
            activity.finish();
            return;
        }
        Object adapter = readField(activity, FIELD_ADAPTER);
        adapter.getClass().getMethod(METHOD_MULTI_SELECT, boolean.class)
                .invoke(adapter, Boolean.TRUE);
        Object listener = Proxy.newProxyInstance(itemClickListenerClass.getClassLoader(),
                new Class<?>[]{itemClickListenerClass},
                new ForwardRecipientClickHandler(this, activity));
        setItemClickListener.invoke(adapter, listener);
        for (String name : VIEWS_TO_HIDE) {
            int id = activity.getResources().getIdentifier(name, "id", activity.getPackageName());
            if (id == 0) {
                continue;
            }
            View view = activity.findViewById(id);
            if (view != null) {
                view.setVisibility(VIEW_GONE);
            }
        }
        View confirm = (View) readField(activity, FIELD_CONFIRM_BUTTON);
        confirm.setVisibility(VIEW_VISIBLE);
        confirm.setOnClickListener(view -> {
            try {
                showConfirmation(activity);
            } catch (Throwable throwable) {
                reportFailure(activity, throwable);
            }
        });
        refreshConfirmButton(activity);
    }

    /**
     * 过滤会话列表：把可作为转发目标的条目追加到 {@code target}（原模块 {@code a1} case 1）。
     *
     * <p>结果累加到调用方传入的列表 —— 与原实现一致：中途失败时已选出的条目仍然保留。</p>
     */
    void filterTargets(Activity activity, List<?> items, List<Object> target) throws Exception {
        ForwardContext pending = resolveContext(activity);
        if (pending == null) {
            return;
        }
        for (Object item : items) {
            Object conversation = readField(item, FIELD_CONVERSATION);
            Object user = readField(item, FIELD_USER);
            if (user == null || conversation == null) {
                continue;
            }
            String peerId = (String) HostAccess.call(conversation, METHOD_TO_USER_ID);
            int chatType = ((Integer) HostAccess.call(conversation, METHOD_CHAT_TYPE)).intValue();
            if (pending.allowsTarget(peerId, chatType)) {
                target.add(item);
            }
        }
    }

    /** 选择会话页销毁时清理转发状态（原模块 {@code a1} case 5）。 */
    void cleanup(Activity activity) {
        if (selectActivityClass.isInstance(activity)
                && hasForwardToken(activity)
                && !activity.isChangingConfigurations()
                && token != null
                && token.equals(activity.getIntent().getStringExtra(TOKEN_EXTRA))) {
            context = null;
            token = null;
        }
        if (dialog != null && dialog.getContext() == activity) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /** 关闭确认框时清空引用（原模块 {@code c1} case 0）。 */
    void clearDialog(Dialog dismissed) {
        if (dialog == dismissed) {
            dialog = null;
        }
    }

    /** 供确认回调复用的当前令牌。 */
    String token() {
        return token;
    }

    /** 当前账号 id（原模块 {@code cn.soulapp.imlib.j0.g()}）。 */
    String currentAccountId() throws ReflectiveOperationException {
        return (String) currentAccountMethod.invoke(null, (Object[]) null);
    }

    /** 页面是否由转发流程打开（原模块 {@code d1.f}）。 */
    static boolean hasForwardToken(Activity activity) {
        return activity.getIntent().hasExtra(TOKEN_EXTRA);
    }

    /**
     * 收敛目标昵称（原模块 {@code d1.h}）：按宿主 presenter 中的选中集合逐个取昵称。
     *
     * <p>昵称缺失时依次回退到签名、再回退到占位文案；选中集合与会话列表不一致时抛异常，
     * 交由调用方提示「转发未完成」。</p>
     */
    static LinkedHashMap<String, String> buildLabels(Activity activity, ForwardContext pending) throws Exception {
        LinkedHashMap<String, String> labels = new LinkedHashMap<>();
        for (Object value : recipientMap(activity).values()) {
            if (!(value instanceof String) || labels.containsKey(value)) {
                throw new IllegalStateException("Invalid selection");
            }
            String peerId = (String) value;
            List<?> all = (List<?>) HostAccess.call(readField(activity, FIELD_ADAPTER), METHOD_ALL_DATA);
            Object matched = null;
            for (Object item : all) {
                Object conversation = readField(item, FIELD_CONVERSATION);
                if (conversation == null
                        || !peerId.equals(HostAccess.call(conversation, METHOD_TO_USER_ID))) {
                    continue;
                }
                int chatType = ((Integer) HostAccess.call(conversation, METHOD_CHAT_TYPE)).intValue();
                if (!pending.allowsTarget(peerId, chatType) || readField(item, FIELD_USER) == null) {
                    continue;
                }
                matched = item;
                break;
            }
            if (matched == null) {
                throw new IllegalStateException("Recipient changed");
            }
            Object user = readField(matched, FIELD_USER);
            String label = (String) readField(user, FIELD_ALIAS);
            if (label == null || label.isEmpty()) {
                label = (String) readField(user, FIELD_SIGNATURE);
            }
            if (label == null || label.isEmpty()) {
                label = DEFAULT_LABEL;
            }
            labels.put(peerId, label);
        }
        return labels;
    }

    /** 宿主 presenter 中的选中集合（原模块 {@code d1.j}）。 */
    static Map<?, ?> recipientMap(Activity activity) throws ReflectiveOperationException {
        return (Map<?, ?>) HostAccess.call(readField(activity, FIELD_PRESENTER), METHOD_PRESENTER_MAP);
    }

    /** 同步「完成」按钮的文案、可用状态与透明度（原模块 {@code d1.n}）。 */
    static void refreshConfirmButton(Activity activity) throws ReflectiveOperationException {
        int count = recipientMap(activity).size();
        TextView button = (TextView) readField(activity, FIELD_CONFIRM_BUTTON);
        button.setText(count == 0 ? BUTTON_DONE : BUTTON_DONE_PREFIX + count + BUTTON_DONE_SUFFIX);
        button.setEnabled(count > 0);
        button.setAlpha(count > 0 ? ALPHA_ENABLED : ALPHA_DISABLED);
    }

    /** 转发过程中的可预期失败提示（原模块 {@code d1.c}）。 */
    static void reportFailure(Context context, Throwable throwable) {
        toast(context, TOAST_FAILED);
    }

    /** 提示已完成的目标数与总目标数（原模块 {@code b1} 的中断分支）。 */
    static void reportInterrupted(Context context, int submitted, int total) {
        toast(context, TOAST_INTERRUPTED_PREFIX + submitted + TOAST_INTERRUPTED_MIDDLE + total
                + TOAST_INTERRUPTED_SUFFIX);
    }

    /** 提示转发已提交（原模块 {@code b1} 的成功分支）。 */
    static void reportSubmitted(Context context, int submitted) {
        toast(context, TOAST_DONE_PREFIX + submitted + TOAST_DONE_SUFFIX);
    }

    /** 消息类型对应的中文标签。 */
    static String typeLabel(int messageType) {
        if (messageType == TYPE_TEXT) {
            return LABEL_TEXT;
        }
        if (messageType == TYPE_AUDIO) {
            return LABEL_AUDIO;
        }
        return LABEL_PHOTO;
    }

    static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 读取宿主对象的声明字段（原模块 {@code d1.d}）：沿父类链查找，找不到抛
     * {@link NoSuchFieldException}。
     */
    static Object readField(Object target, String name) throws ReflectiveOperationException {
        Class<?> type = target.getClass();
        while (type != null) {
            try {
                Field field = type.getDeclaredField(name);
                field.setAccessible(true);
                return field.get(target);
            } catch (NoSuchFieldException ignored) {
                type = type.getSuperclass();
            }
        }
        throw new NoSuchFieldException(name);
    }
}
