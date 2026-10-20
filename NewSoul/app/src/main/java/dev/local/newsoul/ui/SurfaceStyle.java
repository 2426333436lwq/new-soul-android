package dev.local.newsoul.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 对话框形状与状态样式工厂（原 {@code L2}）。
 *
 * <p>负责生成对话框、列表项、按钮等视图所用的圆角/描边/涟漪背景，并在视图回收时释放不再使用的
 * drawable。视图类型编号（{@code type}）与原模块一致，语义如下：</p>
 * <ul>
 *   <li>1 —— 对话框/滚动容器背景（纯圆角，无涟漪）</li>
 *   <li>2 —— 圆角矩形（仅上方圆角）</li>
 *   <li>3 —— 卡片行（可选高度阴影）</li>
 *   <li>4 —— 描边按钮（带涟漪）</li>
 *   <li>5 / 7 —— 文本按钮（含状态列表与文字色）</li>
 *   <li>6 / 12 —— 无内容涟漪（覆盖层）</li>
 *   <li>8 —— 列表行（带描边状态）</li>
 *   <li>9 / 10 —— 操作行按钮 / 大按钮</li>
 *   <li>11 —— 次级背景块</li>
 *   <li>13 —— 透明（返回 {@code null}）</li>
 * </ul>
 */
public final class SurfaceStyle {

    // ------------------------------------------------------------ 圆角半径（dp）

    /** {@link #radiusFor(int)} 的默认圆角半径（原 0x41600000）。 */
    private static final float RADIUS_DEFAULT = 14.0f;
    /** type 1 / 10 圆角半径（原 0x41800000）。 */
    private static final float RADIUS_16 = 16.0f;
    /** type 2 / 5 圆角半径（原 0x41a00000）。 */
    private static final float RADIUS_20 = 20.0f;
    /** type 7 / 11 圆角半径（原 0x41b00000）。 */
    private static final float RADIUS_22 = 22.0f;
    /** type 8 圆角半径（原 0x41400000）。 */
    private static final float RADIUS_12 = 12.0f;

    // ------------------------------------------------------------ 颜色常量
    // 以下十六进制值取自原 smali 的字面量（形如 -0xXXXXXX，即其 32 位补码）。

    private static final int COLOR_SECONDARY_DARK = 0xFF282838;      // -0xd7d7c8
    private static final int COLOR_SECONDARY_LIGHT = 0xFFEDEDED;     // -0x121213
    private static final int COLOR_WHITE = 0xFFFFFFFF;               // -1
    private static final int COLOR_CARD_DARK = 0xFF181828;           // -0xe7e7d8

    private static final int COLOR_ACCENT_DARK = 0xFF00B5A2;         // -0xff4a5e
    private static final int COLOR_ACCENT_LIGHT = 0xFF00796B;        // -0xff8695

    // create(type) 相关
    private static final int COLOR_OUTLINE_DARK = 0xFF242436;        // -0xdbdbca
    private static final int COLOR_OUTLINE_LIGHT = 0xFFF5F6F8;       // -0xa0908
    private static final int STROKE_DARK = 0x0FFFFFFF;               // 0xfffffff
    private static final int STROKE_LIGHT = 0x08000000;              // 0x8000000
    /** 通用深色涟漪（原 0x22ffffff，用于 type 5/6/7/8/9/10/11/12）。 */
    private static final int RIPPLE_DARK = 0x22FFFFFF;
    /** type 4 深色涟漪（原 0x18ffffff）。 */
    private static final int RIPPLE_OUTLINE_DARK = 0x18FFFFFF;
    /** type 4 浅色涟漪（原 0x1400796b）。 */
    private static final int RIPPLE_OUTLINE_LIGHT = 0x1400796B;
    /** type 9/10 浅色涟漪（原 0x2200796b）。 */
    private static final int RIPPLE_TEAL_LIGHT = 0x2200796B;
    /** type 5/6/7/8/11/12 浅色涟漪（原 0x2200b5a2）。 */
    private static final int RIPPLE_GREEN_LIGHT = 0x2200B5A2;

    // createStroked(...) 相关
    private static final int COLOR_11 = 0xDB181828;                  // -0x24e7e7d8
    private static final int COLOR_ELEVATED_FILL_DARK = COLOR_OUTLINE_DARK;  // -0xdbdbca
    private static final int COLOR_TEXT_2_DARK = 0xFF181828;         // -0xe7e7d8（type 2 深色）
    private static final int COLOR_TEXT_2_LIGHT = 0xFFF7F8FA;        // -0x80706（type 2 浅色）
    /** type 5/7 高亮填充：深色（原 -0xbd9fa6）。 */
    private static final int COLOR_EMPHASIS_DARK = 0xFF42605A;
    /** type 5/7 高亮填充：浅色（原 -0x4b2a32）。 */
    private static final int COLOR_EMPHASIS_LIGHT = 0xFFB4D5CE;
    private static final int COLOR_CHECKED = 0xFF00B5A2;             // -0xff4a5e
    private static final int COLOR_CHECKED_DARK = 0xFF54DFCB;        // -0xab2035
    private static final int COLOR_STROKE_ON = 0xFF81958E;           // -0x7e6a72

    // apply(...) 文本色状态
    private static final int COLOR_DISABLED_DARK = 0xFF777788;       // -0x888878
    private static final int COLOR_DISABLED_LIGHT = 0xFF92929E;      // -0x6d6d62
    private static final int COLOR_STATE_MID = 0xFF183C3B;           // -0xe7c3c5
    private static final int COLOR_STATE_DARK = 0xFFE6E6EF;          // -0x191911
    private static final int COLOR_STATE_LIGHT = 0xFF333333;         // -0xcccccd

    /** 已应用到视图的背景 drawable，用于在视图回收时释放（原字段 {@code d}）。 */
    private static final Map<View, Drawable> ATTACHED_DRAWABLES = new WeakHashMap<>();
    /** 已挂接回收监听器的视图集合，避免重复添加（原字段 {@code e}）。 */
    private static final Map<View, Boolean> WATCHED_VIEWS = new WeakHashMap<>();

    /** 是否深色主题（原字段 {@code a}）。 */
    private final boolean dark;
    /** 屏幕密度（原字段 {@code b}）。 */
    private final float density;
    /** 卡片/背景模式开关，由外部写入（原字段 {@code c}，默认 {@code false}）。 */
    public boolean elevated;

    /**
     * @param dark    是否深色
     * @param density {@code DisplayMetrics.density}
     */
    public SurfaceStyle(boolean dark, float density) {
        this.dark = dark;
        this.density = density;
    }

    // ------------------------------------------------------------ 静态工具

    /**
     * 视图类型到圆角半径（dp）的映射（原静态方法 {@code e(I)F}）。
     *
     * <p>与原实现一致：{@code type == 0} 直接抛出空指针异常。</p>
     */
    public static float radiusFor(int type) {
        switch (type) {
            case 1:
                return RADIUS_16;
            case 2:
            case 5:
                return RADIUS_20;
            case 7:
            case 11:
                return RADIUS_22;
            case 8:
                return RADIUS_12;
            case 10:
                return RADIUS_16;
            case 0:
                throw new NullPointerException();
            default:
                return RADIUS_DEFAULT;
        }
    }

    /**
     * 生成一个纯色圆角 drawable（原静态方法 {@code g(IFZ)}）。
     *
     * @param topOnly 为 {@code true} 时只保留上方两角圆角（{@code {r,r,r,r,0,0,0,0}}）
     */
    public static GradientDrawable shape(int color, float radius, boolean topOnly) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        if (topOnly) {
            drawable.setCornerRadii(new float[] {radius, radius, radius, radius, 0f, 0f, 0f, 0f});
        } else {
            drawable.setCornerRadius(radius);
        }
        return drawable;
    }

    /**
     * 记录视图当前背景并保证其被回收（原静态方法 {@code i(V,Drawable)V}）。
     *
     * <p>替换旧 drawable 时释放旧值；每个视图只挂接一次回收监听。</p>
     */
    public static void rememberDrawable(View view, Drawable drawable) {
        Drawable previous = ATTACHED_DRAWABLES.put(view, drawable);
        if (previous != drawable) {
            releaseDrawable(previous);
        }
        if (WATCHED_VIEWS.put(view, Boolean.TRUE) == null) {
            view.addOnAttachStateChangeListener(new DrawableAttachmentListener());
        }
    }

    /** 读取视图最近一次记录的背景 drawable（供 {@link DrawableAttachmentListener} 使用）。 */
    static Drawable rememberedDrawable(View view) {
        return ATTACHED_DRAWABLES.get(view);
    }

    /**
     * 递归释放 drawable（原静态方法 {@code f(Drawable)V}）。
     *
     * <p>对 {@link InsetDrawable}、{@link LayerDrawable}、{@link StateListDrawable} 逐层处理；
     * 其余类型不做处理（原实现中对其余类型包含一段不可达的异常分支，此处省略）。</p>
     */
    public static void releaseDrawable(Drawable drawable) {
        if (drawable instanceof InsetDrawable) {
            releaseDrawable(((InsetDrawable) drawable).getDrawable());
            return;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layers = (LayerDrawable) drawable;
            for (int i = 0; i < layers.getNumberOfLayers(); i++) {
                releaseDrawable(layers.getDrawable(i));
            }
            return;
        }
        if (drawable instanceof StateListDrawable) {
            StateListDrawable states = (StateListDrawable) drawable;
            if (Build.VERSION.SDK_INT >= 29) {
                for (int i = 0; i < states.getStateCount(); i++) {
                    releaseDrawable(states.getStateDrawable(i));
                }
            } else {
                releaseDrawable(states.getCurrent());
            }
        }
    }

    // ------------------------------------------------------------ 实例方法

    /** 是否深色主题（原字段 {@code a}）。 */
    public boolean dark() {
        return dark;
    }

    /** 屏幕密度（原字段 {@code b}）。 */
    public float density() {
        return density;
    }

    /** 主题强调色（原实例方法 {@code a()I}）。 */
    public int accent() {
        return dark ? COLOR_ACCENT_DARK : COLOR_ACCENT_LIGHT;
    }

    /** 背景色（原实例方法 {@code d()I}）。 */
    public int background() {
        if (dark) {
            return COLOR_CARD_DARK;
        }
        return elevated ? COLOR_OUTLINE_LIGHT : COLOR_WHITE;
    }

    /**
     * 生成视图背景（原实例方法 {@code c(IF)Drawable}）。
     *
     * @param type   视图类型（见类注释）；{@code 13} 返回 {@code null}
     * @param radius 圆角半径（dp）
     */
    public Drawable create(int type, float radius) {
        float radiusPx = radius * density;
        if (type == 13) {
            return null;
        }
        if (type == 4) {
            int color = dark ? COLOR_OUTLINE_DARK : COLOR_OUTLINE_LIGHT;
            GradientDrawable content = shape(color, radiusPx, false);
            int strokeWidth = Math.max(1, Math.round(density * 0.5f));
            content.setStroke(strokeWidth, dark ? STROKE_DARK : STROKE_LIGHT);
            GradientDrawable mask = shape(Color.WHITE, radiusPx, false);
            return new RippleDrawable(
                    ColorStateList.valueOf(dark ? RIPPLE_OUTLINE_DARK : RIPPLE_OUTLINE_LIGHT),
                    content, mask);
        }
        if (type == 9 || type == 10) {
            GradientDrawable mask = shape(Color.WHITE, radiusPx, false);
            return new RippleDrawable(
                    ColorStateList.valueOf(dark ? RIPPLE_DARK : RIPPLE_TEAL_LIGHT), null, mask);
        }
        if (type == 6 || type == 12) {
            GradientDrawable mask = shape(Color.WHITE, radiusPx, false);
            return new RippleDrawable(
                    ColorStateList.valueOf(dark ? RIPPLE_DARK : RIPPLE_GREEN_LIGHT), null, mask);
        }
        Drawable content;
        if (type == 5 || type == 7 || type == 8) {
            content = stateListFor(type, radius);
        } else {
            content = createStroked(type, radius, type == 7, false);
        }
        if (type == 5 || type == 7 || type == 8 || type == 11) {
            GradientDrawable mask = shape(Color.WHITE, radiusPx, false);
            return new RippleDrawable(
                    ColorStateList.valueOf(dark ? RIPPLE_DARK : RIPPLE_GREEN_LIGHT), content, mask);
        }
        return content;
    }

    /** 构建 type 5/7/8 的状态列表背景（原 {@code c()} 中的内联状态表）。 */
    private Drawable stateListFor(int type, float radius) {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[] {-0x0101009E, 0x010100A1}, createStroked(type, radius, true, true));
        states.addState(new int[] {-0x0101009E, 0x010100A0}, createStroked(type, radius, true, true));
        states.addState(new int[] {-0x0101009E}, createStroked(type, radius, type == 7, true));
        states.addState(new int[] {0x010100A1}, createStroked(type, radius, true, false));
        states.addState(new int[] {0x010100A0}, createStroked(type, radius, true, false));
        states.addState(new int[] {0x0101009C}, createStroked(type, radius, true, false));
        states.addState(new int[] {}, createStroked(type, radius, type == 7, false));
        return states;
    }

    /**
     * 生成带描边/状态色的 drawable（原实例方法 {@code h(IFZZ)Drawable}）。
     *
     * @param type      视图类型
     * @param radius    圆角半径（dp）
     * @param stroke    是否描边（仅 type 5/8 生效）
     * @param emphasize 是否使用强调色
     */
    public Drawable createStroked(int type, float radius, boolean stroke, boolean emphasize) {
        int color;
        if (type == 11) {
            color = COLOR_11;
        } else if (type == 3) {
            if (elevated) {
                color = dark ? COLOR_ELEVATED_FILL_DARK : COLOR_WHITE;
            } else {
                color = dark ? COLOR_SECONDARY_DARK : COLOR_SECONDARY_LIGHT;
            }
        } else if (type == 2) {
            color = dark ? COLOR_TEXT_2_DARK : COLOR_TEXT_2_LIGHT;
        } else if (type == 5 || type == 8) {
            color = dark ? COLOR_SECONDARY_DARK : COLOR_SECONDARY_LIGHT;
        } else {
            color = background();
        }
        if (stroke && type == 5) {
            if (emphasize) {
                color = dark ? COLOR_EMPHASIS_DARK : COLOR_EMPHASIS_LIGHT;
            } else {
                color = COLOR_CHECKED;
            }
        }
        if (type == 7) {
            if (emphasize) {
                color = dark ? COLOR_EMPHASIS_DARK : COLOR_EMPHASIS_LIGHT;
            } else {
                color = COLOR_CHECKED_DARK;
            }
        }
        GradientDrawable drawable = shape(color, radius * density, type == 2);
        if (stroke && type == 8) {
            int width = Math.max(1, Math.round(density));
            drawable.setStroke(width, emphasize ? COLOR_STROKE_ON : accent());
        }
        return drawable;
    }

    /**
     * 把背景 drawable 套用到视图并同步内边距/阴影/文字色（原实例方法 {@code b(VIF)V}）。
     *
     * @param type   视图类型
     * @param radius 圆角半径（dp）
     */
    public void apply(View view, int type, float radius) {
        Drawable drawable = create(type, radius);
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        rememberDrawable(view, drawable);
        view.setBackground(drawable);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        if (type == 4) {
            view.setForeground(null);
            view.setElevation((dark ? 0.5f : 1.0f) * density);
        }
        if (type == 3) {
            view.setElevation(elevated && !dark ? density : 0f);
        }
        boolean textViewOnly = view instanceof TextView && !(view instanceof EditText);
        if (textViewOnly && (type == 5 || type == 7 || type == 9 || type == 10)) {
            int horizontalPadding = Math.round((type == 10 ? 12 : 14) * density);
            int verticalPadding = type == 10 ? Math.round(10 * density) : 0;
            view.setPadding(
                    Math.max(view.getPaddingLeft(), horizontalPadding),
                    Math.max(view.getPaddingTop(), verticalPadding),
                    Math.max(view.getPaddingRight(), horizontalPadding),
                    Math.max(view.getPaddingBottom(), verticalPadding));
            TextView textView = (TextView) view;
            textView.setMinHeight(Math.max(textView.getMinHeight(), Math.round(48 * density)));
        }
        if (view instanceof TextView && (type == 5 || type == 7)) {
            int disabled = dark ? COLOR_DISABLED_DARK : COLOR_DISABLED_LIGHT;
            int normal = type == 7
                    ? COLOR_STATE_MID
                    : (dark ? COLOR_STATE_DARK : COLOR_STATE_LIGHT);
            ColorStateList colors = new ColorStateList(
                    new int[][] {
                            {-0x0101009E},
                            {0x010100A1},
                            {0x010100A0},
                            {}
                    },
                    new int[] {disabled, COLOR_STATE_MID, COLOR_STATE_MID, normal});
            ((TextView) view).setTextColor(colors);
        }
    }
}
