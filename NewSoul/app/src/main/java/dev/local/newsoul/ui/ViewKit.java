package dev.local.newsoul.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 对话框视图与资源辅助（原 {@code w2} 中服务于对话框框架的方法）。
 *
 * <p>原 {@code w2} 还包含设置开关行、面板构建、自更新等属于其它功能域的方法，此处仅复刻
 * 「对话框框架 + 视图/颜色/度量辅助」部分。视图类型编号语义见 {@link SurfaceStyle}。</p>
 */
public final class ViewKit {

    /** 模块强调色（原静态字段 {@code b} = {@code Color.rgb(0, 0xb5, 0xa2)}）。 */
    public static final int ACCENT_COLOR = 0xFF00B5A2;

    /** 模块设置存储名（原 {@code o(Context)} 中的字面量）。 */
    private static final String PREFS_NAME = "dev.local.soulhookdemo.public.settings.v1";

    /** 宿主「问号帮助」图标资源名（原 {@code l} 中查找）。 */
    private static final String HOST_HELP_ICON = "c_ic_icon_question";
    /** 系统默认问号图标资源 id（原 0x1080040）。 */
    private static final int SYSTEM_HELP_ICON = 0x01080040;
    /** 前台波纹属性 id（原 0x101030e）。 */
    private static final int ATTR_SELECTABLE_ITEM_BACKGROUND = 0x0101030E;

    /** WCAG 最小对比度阈值（原 4.5）。 */
    private static final double CONTRAST_THRESHOLD = 4.5;
    /** 对比度搜索插值步数（原 100）。 */
    private static final int CONTRAST_STEPS = 100;

    private ViewKit() {
    }

    // ------------------------------------------------------------ 基础度量/文本

    /** 创建带尺寸与颜色设置的 {@link TextView}（原 {@code n}）。 */
    public static TextView text(Context context, String value, int sizePx, int color) {
        TextView view = new TextView(context);
        view.setText(value);
        view.setTextSize((float) sizePx);
        view.setTextColor(color);
        return view;
    }

    /**
     * 以 <b>sp</b> 为单位设置字号的重载。
     *
     * <p>注意 {@link TextView#setTextSize(float)} 的默认单位是 sp，不是 px。
     * 早期调用点误把 {@code dp(...)} 的结果当作 sp 传入，在 1080p 设备上
     * （density≈3）字号被放大三倍，导致文字换行、被容器裁切。需要像素级精确
     * 控制时请显式使用 {@code setTextSize(TypedValue.COMPLEX_UNIT_PX, px)}。</p>
     */
    public static TextView text(Context context, String value, float sizeSp, int color) {
        TextView view = new TextView(context);
        view.setText(value);
        view.setTextSize(sizeSp);
        view.setTextColor(color);
        return view;
    }

    /** dp 转像素（原 {@code i}）。 */
    public static int dp(Context context, int size) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(size * density);
    }

    /** 获取模块设置存储（原 {@code o}）。 */
    public static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // ------------------------------------------------------------ 视图装饰

    /** 使视图可聚焦并赋予按压反馈与按钮无障碍语义（原 {@code g}）。 */
    public static void makeFocusable(View view) {
        view.setFocusable(true);
        applyPressFeedback(view);
        view.setAccessibilityDelegate(new ButtonAccessibilityDelegate());
    }

    /**
     * 在视图缺少按压反馈时套用主题的前台波纹（原 {@code p}）。
     *
     * <p>已有前台或背景为 {@link RippleDrawable} 时跳过。</p>
     */
    public static void applyPressFeedback(View view) {
        if (view.getForeground() != null) {
            return;
        }
        if (view.getBackground() instanceof RippleDrawable) {
            return;
        }
        TypedValue value = new TypedValue();
        if (!view.getContext().getTheme()
                .resolveAttribute(ATTR_SELECTABLE_ITEM_BACKGROUND, value, true)) {
            return;
        }
        if (value.resourceId == 0) {
            return;
        }
        Drawable foreground = view.getContext().getDrawable(value.resourceId);
        view.setForeground(foreground);
    }

    /** 判断视图在无障碍层面是否可见（原 {@code h}）。 */
    public static boolean isAccessiblyVisible(View view) {
        if (!view.isAttachedToWindow()) {
            return false;
        }
        if (!view.isShown()) {
            return false;
        }
        View current = view;
        while (current != null) {
            if (current.getImportantForAccessibility()
                    == View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS) {
                return false;
            }
            ViewParent parent = current.getParent();
            current = parent instanceof View ? (View) parent : null;
        }
        return true;
    }

    // ------------------------------------------------------------ 对话框行样式

    /**
     * 把两个按钮排成一行：左右各占一半，中间以竖线分隔（原 {@code a}）。
     */
    public static void styleActionRow(ModuleDialog dialog, LinearLayout parent, TextView left,
            TextView right) {
        Context context = dialog.context();
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(android.view.Gravity.CENTER_VERTICAL);

        styleActionButton(dialog, left);
        styleActionButton(dialog, right);

        LinearLayout.LayoutParams halfWidth =
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        row.addView(left, halfWidth);

        View divider = new View(context);
        divider.setBackgroundColor(dialog.dark() ? 0xFF3A3A4A : 0xFFDCDCE2);
        divider.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        LinearLayout.LayoutParams dividerParams =
                new LinearLayout.LayoutParams(dp(context, 1), dp(context, 0x1C));
        row.addView(divider, dividerParams);

        LinearLayout.LayoutParams rightHalf =
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        row.addView(right, rightHalf);

        parent.addView(row,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    /** 单个操作按钮的样式（原私有 {@code x}）。 */
    private static void styleActionButton(ModuleDialog dialog, TextView button) {
        Context context = dialog.context();
        dialog.surfaceStyle().apply(button, 9, SurfaceStyle.radiusFor(9));
        button.setMinHeight(dp(context, 0x34));
        button.setGravity(android.view.Gravity.CENTER);
        button.setSingleLine(false);
        button.setEllipsize(null);
        button.setPadding(button.getPaddingLeft(), dp(context, 8), button.getPaddingRight(),
                dp(context, 8));
    }

    /** 列表项的样式与布局参数（原 {@code b}）。 */
    public static void styleListItem(ModuleDialog dialog, LinearLayout parent, TextView item) {
        Context context = dialog.context();
        dialog.surfaceStyle().apply(item, 4, SurfaceStyle.radiusFor(4));
        item.setMinHeight(dp(context, 0x38));
        item.setPadding(dp(context, 0x10), dp(context, 0xE), dp(context, 0x10), dp(context, 0xE));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = dp(context, 2);
        params.leftMargin = margin;
        params.rightMargin = margin;
        params.topMargin = dp(context, parent.getChildCount() == 0 ? 2 : 8);
        params.bottomMargin = margin;
        parent.addView(item, params);
    }

    /**
     * 生成标题栏右侧的说明按钮（原 {@code l}）。
     *
     * <p>优先使用宿主资源 {@code c_ic_icon_question}，缺失时回退到系统图标。</p>
     */
    public static ImageButton helpButton(ModuleDialog dialog, String title,
            CharSequence description) {
        Context context = dialog.context();
        ImageButton button = new ImageButton(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(dp(context, 0x30), dp(context, 0x30)));
        int pad = dp(context, 0xF);
        button.setPadding(pad, pad, pad, pad);
        button.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Context host = dialog.activity();
        int iconId = host.getResources()
                .getIdentifier(HOST_HELP_ICON, "drawable", host.getPackageName());
        if (iconId != 0) {
            button.setImageDrawable(host.getDrawable(iconId));
        } else {
            button.setImageResource(SYSTEM_HELP_ICON);
        }
        button.setImageTintList(ColorStateList.valueOf(dialog.secondaryColor()));
        dialog.surfaceStyle().apply(button, 12, SurfaceStyle.radiusFor(12));
        button.setContentDescription(title + "，查看说明");
        button.setOnClickListener(new HelpButtonListener(dialog, description, title));
        return button;
    }

    // ------------------------------------------------------------ 对比度计算

    /**
     * 求在给定参照色上满足 WCAG 对比度的着色（原 varargs {@code q}）。
     *
     * <p>从 {@code base} 向目标色（深色主题取白色、浅色主题取黑色）线性插值，返回第一个与所有
     * {@code references} 对比度均不低于 {@value #CONTRAST_THRESHOLD} 的颜色；若全程不满足则返回目标色。</p>
     */
    public static int contrastingColor(int base, boolean dark, int... references) {
        int target = dark ? -0x1 : 0xFF000000;
        for (int step = 0; step <= CONTRAST_STEPS; step++) {
            float ratio = step / (float) CONTRAST_STEPS;
            int candidate = Color.rgb(
                    Math.round(Color.red(base) + (Color.red(target) - Color.red(base)) * ratio),
                    Math.round(Color.green(base) + (Color.green(target) - Color.green(base)) * ratio),
                    Math.round(Color.blue(base) + (Color.blue(target) - Color.blue(base)) * ratio));
            boolean pass = true;
            for (int reference : references) {
                double first = Color.luminance(candidate);
                double second = Color.luminance(reference);
                double contrast = (Math.max(first, second) + 0.05) / (Math.min(first, second) + 0.05);
                if (contrast < CONTRAST_THRESHOLD) {
                    pass = false;
                }
            }
            if (pass) {
                return candidate;
            }
        }
        return target;
    }
}
