package dev.local.newsoul.feature.chat.recall;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;

/**
 * 撤回气泡样式渲染（原 {@code z1}）。
 *
 * <p>聊天列表的每一行在绑定消息时都会被还原为宿主原始外观，再按需重新套用撤回样式。
 * 因此本类的入口是「先 {@link #reset(View)} 还原、后 {@link #apply(View, String, String)} 套用」，
 * 并用弱键缓存记录被改动的视图与原始属性，保证行复用时不残留上一次的改动。</p>
 *
 * <p>两种样式：</p>
 * <ul>
 *   <li>{@code nickname} —— 把「已撤回」并入昵称行（{@code tv_nikename} /
 *       {@code tv_nikename_flag}），不改动布局结构。</li>
 *   <li>其余（默认 {@code below}）—— 在消息容器下方插入一行小字
 *       {@code 对方已撤回 · 已保留}。</li>
 * </ul>
 *
 * <p>视图查找依赖宿主资源 id 名（{@code tv_nikename}、{@code tv_nikename_flag}、
 * {@code container}），资源缺失时抛 {@link IllegalStateException} 并由调用方降级处理。</p>
 */
final class RecallBubbleStyle {

    private static final String ID_NICKNAME = "tv_nikename";
    private static final String ID_NICKNAME_FLAG = "tv_nikename_flag";
    private static final String ID_CONTAINER = "container";

    private static final String BELOW_TEXT = "对方已撤回 · 已保留";
    private static final String FLAG_SUFFIX = " · 已撤回";
    private static final String FLAG_TEXT = "已撤回";

    private static final float BELOW_TEXT_SIZE = 10.0f;
    private static final int BELOW_TOP_MARGIN_DP = 2;
    private static final int BELOW_BOTTOM_MARGIN_DP = 8;

    /** 行视图到「被改动前状态」的映射；随视图回收自动失效。 */
    private static final WeakHashMap<View, Snapshot> SNAPSHOTS = new WeakHashMap<>();

    private RecallBubbleStyle() {
    }

    /**
     * 套用撤回样式；{@code style} 为 {@link SettingsKeys#RECALL_STYLE_NICKNAME} 时并入昵称行。
     *
     * <p>与原实现一致：宿主视图结构不符合预期时直接抛出异常，由调用方统一记录并降级。</p>
     */
    static void apply(View view, String style, String name) throws Exception {
        reset(view);

        TextView nickname = viewById(view, ID_NICKNAME, TextView.class);
        TextView flag = viewById(view, ID_NICKNAME_FLAG, TextView.class);
        View container = viewById(view, ID_CONTAINER, View.class);
        if (nickname == null || flag == null || container == null) {
            return;
        }

        Snapshot snapshot = new Snapshot();
        SNAPSHOTS.put(view, snapshot);

        if (SettingsKeys.RECALL_STYLE_NICKNAME.equals(style)) {
            applyNicknameStyle(snapshot, nickname, flag, name);
        } else {
            applyBelowStyle(snapshot, container, nickname);
        }
    }

    /**
     * 还原视图为宿主原始外观。
     *
     * <p>未套用过样式的行不做任何处理。</p>
     */
    static void reset(View view) {
        Snapshot snapshot = SNAPSHOTS.remove(view);
        if (snapshot == null) {
            return;
        }
        try {
            restoreContainer(snapshot);
            restoreNickname(snapshot);
        } catch (Throwable throwable) {
            Logger.w(LOG_RESET_FAILED, throwable);
        }
    }

    // ------------------------------------------------------------ 样式实现

    /** 昵称样式：把撤回标记并入昵称行，不改动布局。 */
    private static void applyNicknameStyle(Snapshot snapshot, TextView nickname, TextView flag,
                                           String name) {
        snapshot.nicknameRef = new WeakReference<>(nickname);
        snapshot.flagRef = new WeakReference<>(flag);
        snapshot.nicknameText = nickname.getText();
        snapshot.flagText = flag.getText();
        snapshot.nicknameVisibility = nickname.getVisibility();
        snapshot.flagVisibility = flag.getVisibility();

        if (snapshot.nicknameVisibility != View.VISIBLE || snapshot.nicknameText.length() == 0) {
            nickname.setText(name);
        }
        nickname.setVisibility(View.VISIBLE);

        boolean flagVisible = snapshot.flagVisibility == View.VISIBLE
                && snapshot.flagText.length() > 0;
        flag.setText(flagVisible ? String.valueOf(snapshot.flagText) + FLAG_SUFFIX : FLAG_TEXT);
        flag.setVisibility(View.VISIBLE);
    }

    /** 默认样式：在消息容器下方插入一行小字，并把容器对齐方式改为「顶到顶」。 */
    private static void applyBelowStyle(Snapshot snapshot, View container, TextView nickname)
            throws Exception {
        ViewGroup parent = (ViewGroup) container.getParent();
        ViewGroup.LayoutParams containerParams = container.getLayoutParams();
        Class<?> paramsClass = containerParams.getClass();
        Field bottomToBottom = field(paramsClass, "bottomToBottom");
        snapshot.containerMargin = bottomToBottom.getInt(containerParams);

        TextView bubble = new TextView(container.getContext());
        bubble.setId(View.generateViewId());
        bubble.setText(BELOW_TEXT);
        bubble.setTextSize(BELOW_TEXT_SIZE);
        bubble.setTextColor(nickname.getTextColors());
        bubble.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);

        ViewGroup.MarginLayoutParams bubbleParams = newMarginLayoutParams(paramsClass);
        field(paramsClass, "topToBottom").setInt(bubbleParams, container.getId());
        field(paramsClass, "startToStart").setInt(bubbleParams, container.getId());
        bottomToBottom.setInt(bubbleParams, 0);
        bubbleParams.topMargin = dp(container.getContext(), BELOW_TOP_MARGIN_DP);
        bubbleParams.bottomMargin = dp(container.getContext(), BELOW_BOTTOM_MARGIN_DP);

        snapshot.containerRef = new WeakReference<>(container);
        snapshot.bubbleRef = new WeakReference<>(bubble);

        bottomToBottom.setInt(containerParams, -1);
        container.setLayoutParams(containerParams);
        parent.addView(bubble, bubbleParams);
    }

    // ------------------------------------------------------------ 还原实现

    private static void restoreContainer(Snapshot snapshot) throws Exception {
        if (snapshot.bubbleRef == null) {
            return;
        }
        View bubble = snapshot.bubbleRef.get();
        View container = snapshot.containerRef.get();
        if (bubble != null && bubble.getParent() instanceof ViewGroup) {
            ((ViewGroup) bubble.getParent()).removeView(bubble);
        }
        if (container == null) {
            return;
        }
        ViewGroup.LayoutParams params = container.getLayoutParams();
        field(params.getClass(), "bottomToBottom").setInt(params, snapshot.containerMargin);
        container.setLayoutParams(params);
    }

    private static void restoreNickname(Snapshot snapshot) {
        if (snapshot.nicknameRef == null) {
            return;
        }
        TextView nickname = snapshot.nicknameRef.get();
        TextView flag = snapshot.flagRef.get();
        if (nickname != null) {
            nickname.setText(snapshot.nicknameText);
            nickname.setVisibility(snapshot.nicknameVisibility);
        }
        if (flag != null) {
            flag.setText(snapshot.flagText);
            flag.setVisibility(snapshot.flagVisibility);
        }
    }

    // ------------------------------------------------------------ 资源与度量

    /** 按资源名查找子视图并转型；未找到时返回 {@code null}。 */
    private static <T extends View> T viewById(View root, String name, Class<T> type) {
        View found = root.findViewById(resolveId(root.getContext(), name, "id"));
        return type.isInstance(found) ? type.cast(found) : null;
    }

    /** 解析宿主资源 id；缺失时抛 {@link IllegalStateException}。 */
    private static int resolveId(Context context, String name, String type) {
        int id = context.getResources().getIdentifier(name, type, HostClasses.PACKAGE);
        if (id == 0) {
            throw new IllegalStateException("Missing Soul resource: " + name);
        }
        return id;
    }

    private static Field field(Class<?> type, String name) throws NoSuchFieldException {
        return type.getField(name);
    }

    private static ViewGroup.MarginLayoutParams newMarginLayoutParams(Class<?> paramsClass)
            throws Exception {
        Constructor<?> constructor = paramsClass.getConstructor(int.class, int.class);
        return (ViewGroup.MarginLayoutParams) constructor.newInstance(-2, -2);
    }

    /** 把 dp 值换算为像素。 */
    private static int dp(Context context, int value) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(value * density);
    }

    private static final String LOG_RESET_FAILED = "recall style reset failed";

    /** 单行视图被改动前的属性快照（原 {@code y1}）。 */
    private static final class Snapshot {

        /** 默认样式插入的小字视图。 */
        private WeakReference<View> bubbleRef;
        /** 默认样式改动了布局参数的容器视图。 */
        private WeakReference<View> containerRef;
        /** 昵称样式改动的昵称视图。 */
        private WeakReference<TextView> nicknameRef;
        /** 昵称样式改动的标记视图。 */
        private WeakReference<TextView> flagRef;

        /** 容器原始 {@code bottomToBottom} 值。 */
        private int containerMargin;
        /** 昵称原始可见性。 */
        private int nicknameVisibility;
        /** 标记原始可见性。 */
        private int flagVisibility;
        /** 昵称原始文案。 */
        private CharSequence nicknameText;
        /** 标记原始文案。 */
        private CharSequence flagText;
    }
}