package dev.local.newsoul.feature.chat.gift;

import android.content.Context;

import java.lang.reflect.Method;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.GlobalContextProvider;
import dev.local.newsoul.framework.Prefs;

/**
 * 礼物 / 铃声礼物「限制模型」判定（原 {@code Q0.e}）。
 *
 * <p>宿主通过 {@code ChatLimitModel} 描述一次会话级限制：{@code getType()} 给出限制类型
 * （数值区间与礼物、铃声礼物相关），{@code getItemIdentity()} 给出被限制对象标识。
 * 模块在 {@code isLimit()}（模型是否处于限制状态）与礼物对话框入口处据此判断
 * 「当前限制是否属于本功能域可绕过的类型」，并读取对应开关键。</p>
 *
 * <p>类型判定语义（逐条对齐原实现）：
 * <ul>
 *   <li>type 越界（{@code < 0} 或 {@code >= 19}）或 type == 6 时一律不适用；</li>
 *   <li>type ∈ {0, 1, 5, 10, 11} 直接适用；</li>
 *   <li>type >= 14 时要求 itemIdentity 非空才适用；其余 type 不适用。</li>
 * </ul>
 * type 为 10 / 11 时走「铃声礼物」开关键，否则走「礼物」开关键。</p>
 *
 * <p>反射调用失败会向上抛出（原实现未捕获），由调用方的 PROTECTIVE 异常模式兜底。</p>
 */
final class GiftLimitModel {

    /** type 上界（不含），原 {@code 0x13}。 */
    private static final int MAX_TYPE_EXCLUSIVE = 0x13;

    /** 排除的 type：专属铃声礼物类型，另行判定。 */
    private static final int EXCLUDED_TYPE = 0x6;

    /** 需要 itemIdentity 的 type 下界，原 {@code 0xe}。 */
    private static final int IDENTITY_REQUIRED_TYPE = 0xe;

    /** 无需 itemIdentity 即可适用的 type 集合。 */
    private static final int[] DIRECT_APPLICABLE_TYPES = {0x0, 0x1, 0x5, 0xa, 0xb};

    /** {@code ChatLimitModel.getType()}。 */
    private final Method typeGetter;

    /** {@code ChatLimitModel.getItemIdentity()}。 */
    private final Method identityGetter;

    GiftLimitModel(Method typeGetter, Method identityGetter) {
        this.typeGetter = typeGetter;
        this.identityGetter = identityGetter;
    }

    /** 当前模型的限制类型（用于日志；原 {@code Q0.i.invoke}）。 */
    int typeOf(Object model) throws ReflectiveOperationException {
        return (Integer) typeGetter.invoke(model);
    }

    /**
     * 该模型当前是否处于「本功能域可绕过」的限制状态。
     *
     * <p>type 属于适用集合且对应开关键开启时才返回 {@code true}；开关读不到
     * （全局 Context 不可用）时按原实现返回 {@code false}（保守，不绕过）。</p>
     */
    boolean isActive(Object model, GlobalContextProvider globalContext)
            throws ReflectiveOperationException {
        if (model == null) {
            return false;
        }
        int type = typeOf(model);
        if (type < 0 || type >= MAX_TYPE_EXCLUSIVE || type == EXCLUDED_TYPE) {
            return false;
        }
        boolean applicable = false;
        for (int directType : DIRECT_APPLICABLE_TYPES) {
            if (type == directType) {
                applicable = true;
                break;
            }
        }
        if (!applicable) {
            if (type < IDENTITY_REQUIRED_TYPE) {
                return false;
            }
            String identity = (String) identityGetter.invoke(model);
            applicable = identity != null && !identity.isEmpty();
        }
        if (!applicable) {
            return false;
        }
        String key = (type == 0xa || type == 0xb)
                ? SettingsKeys.CHAT_REMOVE_BELL_GIFT_GATE
                : SettingsKeys.CHAT_REMOVE_GIFT_GATE;
        return isEnabled(globalContext, key);
    }

    /** 读取功能开关；全局 Context 不可用时返回 {@code false}（原 {@code Q0.q} 的失败路径）。 */
    private static boolean isEnabled(GlobalContextProvider globalContext, String key) {
        Context context = globalContext != null ? globalContext.get() : null;
        if (context == null) {
            return false;
        }
        return Prefs.getBoolean(context, key, true);
    }
}
