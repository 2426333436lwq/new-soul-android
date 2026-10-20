package dev.local.newsoul.feature.chat.forward;

import android.app.Activity;
import android.app.Dialog;
import android.os.SystemClock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 转发确认框的确认回调（原模块 {@code b1}）。
 *
 * <p>用户点「确认」时再次核对账号、令牌与选择集合是否被改动，然后逐个目标发送；
 * 任一目标失败即中断，并提示已提交数量与总数，便于用户自行核对。</p>
 */
final class ForwardConfirmHandler implements InvocationHandler {

    private static final String TO_STRING = "NewSoulMessageForwardConfirm";

    private static final String METHOD_CONFIRM = "onClickConfirm";

    private static final String STATE_SELECTION_CHANGED = "Selection changed";
    private static final String STATE_EXPIRED = "Expired confirmation";
    private static final String STATE_ACCOUNT_CHANGED = "Account changed";

    private final MessageForward forward;
    private final Activity activity;
    private final String token;
    private final ForwardContext context;
    private final LinkedHashMap<String, String> labels;
    private final ArrayList<String> targets;
    private final Dialog dialog;

    ForwardConfirmHandler(MessageForward forward, Activity activity, String token,
                          ForwardContext context, LinkedHashMap<String, String> labels,
                          ArrayList<String> targets, Dialog dialog) {
        this.forward = forward;
        this.activity = activity;
        this.token = token;
        this.context = context;
        this.labels = labels;
        this.targets = targets;
        this.dialog = dialog;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            String name = method.getName();
            if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            }
            if ("equals".equals(name)) {
                return proxy == args[0];
            }
            return TO_STRING;
        }
        if (METHOD_CONFIRM.equals(method.getName())) {
            confirm();
        }
        return null;
    }

    /** 校验选择是否仍然有效并提交；无论成功或失败都关闭确认框与页面。 */
    private void confirm() throws Throwable {
        int submitted = 0;
        try {
            if (activity.isFinishing() || activity.isDestroyed()) {
                throw new IllegalStateException(STATE_SELECTION_CHANGED);
            }
            if (!token.equals(forward.token())) {
                throw new IllegalStateException(STATE_SELECTION_CHANGED);
            }
            if (forward.resolveContext(activity) != context) {
                throw new IllegalStateException(STATE_SELECTION_CHANGED);
            }
            if (!MessageForward.buildLabels(activity, context).keySet().equals(labels.keySet())) {
                throw new IllegalStateException(STATE_SELECTION_CHANGED);
            }
            String accountId = forward.currentAccountId();
            ArrayList<String> selection = context.registerSelection(accountId, targets,
                    SystemClock.elapsedRealtime());
            if (selection == null) {
                throw new IllegalStateException(STATE_EXPIRED);
            }
            for (String peerId : selection) {
                if (!context.accountId().equals(forward.currentAccountId())) {
                    throw new IllegalStateException(STATE_ACCOUNT_CHANGED);
                }
                forward.sendTo(context, peerId);
                submitted++;
            }
            MessageForward.reportSubmitted(activity, submitted);
        } catch (Throwable throwable) {
            try {
                MessageForward.reportInterrupted(activity, submitted, targets.size());
            } catch (Throwable fatal) {
                dialog.dismiss();
                activity.finish();
                throw fatal;
            }
            dialog.dismiss();
            activity.finish();
            return;
        }
        dialog.dismiss();
        activity.finish();
    }
}
