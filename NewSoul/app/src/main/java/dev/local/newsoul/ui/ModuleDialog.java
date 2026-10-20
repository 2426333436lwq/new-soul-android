package dev.local.newsoul.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

/**
 * 自研对话框框架（原 {@code q2}）。
 *
 * <p>在 {@link AlertDialog.Builder} 之上统一了标题栏、圆角窗口背景、按钮样式、草稿保护与
 * 菜单弹出等能力。深色/浅色两套主题由构造参数 {@code dark} 决定，并通过对宿主主题色的
 * 对比度计算得到可读的次级色与强调色。</p>
 *
 * <p>视图中「对话框框架」以外的功能域（设置开关行、自更新等）不在本包范围内。</p>
 */
public final class ModuleDialog {

    // ------------------------------------------------------------ 主题与颜色

    /** 浅色主题标题色（原 {@code Color.rgb(0x33, 0x33, 0x33)}）。 */
    private static final int TITLE_COLOR_LIGHT = 0xFF333333;
    /** 深色主题标题色（原 {@code -0x191911}）。 */
    private static final int TITLE_COLOR_DARK = 0xFFE6E6EF;
    /** 深色主题次级基色（原 {@code -0xd7d7c8}）。 */
    private static final int SECONDARY_BASE_DARK = 0xFF282838;
    /** 浅色主题次级基色（原 {@code -0x121213}）。 */
    private static final int SECONDARY_BASE_LIGHT = 0xFFEDEDED;
    /** 深色对比参照色（原 {@code -0xe7e7d8}）。 */
    private static final int REFERENCE_PRIMARY_DARK = 0xFF181828;
    /** 浅色对比参照色（原 {@code -1}）。 */
    private static final int REFERENCE_PRIMARY_LIGHT = 0xFFFFFFFF;
    /** 深色次级色种子（原 {@code -0x7d7d6a}）。 */
    private static final int SECONDARY_SEED_DARK = 0xFF828296;
    /** 浅色次级色种子（原 {@code -0x99998d}）。 */
    private static final int SECONDARY_SEED_LIGHT = 0xFF666673;
    /** 浅色强调色种子（原 {@code -0xff8695}）。 */
    private static final int ACCENT_SEED_LIGHT = 0xFF00796B;
    /** 深色自定义对话框主题（原 {@code 0x1030226}）。 */
    private static final int THEME_DARK = 0x01030226;
    /** 浅色自定义对话框主题（原 {@code 0x103023a}）。 */
    private static final int THEME_LIGHT = 0x0103023A;
    /** 宿主次级色资源名（原 {@code "color_s_04"}）。 */
    private static final String HOST_SECONDARY_COLOR = "color_s_04";

    // ------------------------------------------------------------ 标题栏与按钮

    /** 标题字号（px，原 {@code 0x12}）。 */
    private static final int TITLE_TEXT_SIZE = 0x12;
    /** 标题自建字体资源（原 {@code "fonts/tivf-bold-round.ttf"}）。 */
    private static final String TITLE_FONT_ASSET = "fonts/tivf-bold-round.ttf";
    /** 标题字体字重（原 {@code 0x1f4}）。 */
    private static final int TITLE_FONT_WEIGHT = 0x1F4;
    /** 标题字体回退族名（原 {@code "sans-serif-medium"}）。 */
    private static final String TITLE_FONT_FALLBACK = "sans-serif-medium";
    /** 标题栏无子面板时最小高度（dp，原 {@code 0x3a}）。 */
    private static final int TITLE_MIN_HEIGHT_PLAIN = 0x3A;
    /** 标题栏含子面板时最小高度（dp，原 {@code 0x46}）。 */
    private static final int TITLE_MIN_HEIGHT_WITH_CHILDREN = 0x46;
    /** 对话框按钮最小边长（dp，原 {@code 0x30}）。 */
    private static final int DIALOG_BUTTON_MIN_SIZE = 0x30;

    // ------------------------------------------------------------ 草稿保护

    /** 草稿保护取消按钮文案（原 {@code "取消"}）。 */
    private static final String DRAFT_CANCEL_LABEL = "取消";
    /** 放弃草稿提示标题（原 {@code "放弃未保存的修改？"}）。 */
    private static final String DISCARD_TITLE = "放弃未保存的修改？";
    /** 放弃草稿提示正文（原 smali 字面量）。 */
    private static final String DISCARD_MESSAGE = "有尚未保存的修改。放弃后这些修改不会生效。";
    /** 放弃草稿确认按钮（原 {@code "放弃修改"}）。 */
    private static final String DISCARD_CONFIRM_LABEL = "放弃修改";
    /** 放弃草稿继续按钮（原 {@code "继续编辑"}）。 */
    private static final String DISCARD_KEEP_LABEL = "继续编辑";
    /** 放弃草稿提示正文字号（px，原 {@code 0xf}）。 */
    private static final int DISCARD_MESSAGE_TEXT_SIZE = 0xF;
    /** 在对话框已显示后再保护草稿时抛出的异常信息（原字面量）。 */
    private static final String ERROR_TOO_LATE = "Protect the draft before showing it";

    // ------------------------------------------------------------ 弹出菜单

    /** 菜单项字号（px，原 {@code 0x10}）。 */
    private static final int MENU_ITEM_TEXT_SIZE = 0x10;
    /** 菜单初始最大宽度（dp，原 {@code 0xb0}）。 */
    private static final int MENU_MAX_WIDTH_DP = 0xB0;
    /** 菜单最小宽度/高度（dp，原 {@code 0x30}）。 */
    private static final int MENU_MIN_SIZE_DP = 0x30;
    /** 菜单水平安全边距（dp，原 {@code 0x18}）。 */
    private static final int MENU_SIDE_MARGIN_DP = 0x18;
    /** 菜单文本宽度补偿（dp，原 {@code 0x20}）。 */
    private static final int MENU_TEXT_PADDING_DP = 0x20;
    /** 分隔线颜色（深色，原 {@code -0xc9c9ba}）。 */
    private static final int MENU_DIVIDER_DARK = 0xFF363646;
    /** 分隔线颜色（浅色，原 {@code -0x161612}）。 */
    private static final int MENU_DIVIDER_LIGHT = 0xFFE9E9EE;
    /** 菜单垂直内边距（dp，原 {@code 4}）。 */
    private static final int MENU_VERTICAL_PADDING_DP = 0x4;
    /** 菜单项水平内边距（dp，原 {@code 0x10}）。 */
    private static final int MENU_ITEM_HORIZONTAL_PADDING_DP = 0x10;
    /** 菜单项垂直内边距（dp，原 {@code 0xc}）。 */
    private static final int MENU_ITEM_VERTICAL_PADDING_DP = 0xC;
    /** 菜单项文本对齐方式（原 {@code 0x800013}，即 {@code START|CENTER_VERTICAL}）。 */
    private static final int MENU_ITEM_GRAVITY = Gravity.START | Gravity.CENTER_VERTICAL;
    /** 弹出菜单在锚点下方偏移（dp，原 {@code 4}）。 */
    private static final int MENU_DROP_DOWN_Y_DP = 0x4;
    /** popup 显示对齐方式（原 {@code 0x800005}，即 {@code END}）。 */
    private static final int MENU_POPUP_GRAVITY = Gravity.END;
    /** 输入法模式 INPUT_METHOD_NOT_NEEDED（原 {@code 2}）。 */
    private static final int INPUT_METHOD_NOT_NEEDED = 2;
    /** 菜单圆角半径（dp，原 {@code 14.0f}）。 */
    private static final float MENU_RADIUS = 14.0f;

    // ------------------------------------------------------------ 其他常量

    /** 返回键 keyCode（原 {@code 4}）。 */
    static final int KEYCODE_BACK = KeyEvent.KEYCODE_BACK;
    /** 部分设备兼容返回键 keyCode（原 {@code 0x6f}）。 */
    static final int KEYCODE_BACK_ALT = 0x6F;
    /** 需统一样式的对话框按钮 id（原 {@code {-1, -2, -3}}）。 */
    private static final int[] DIALOG_BUTTON_IDS = {
            DialogInterface.BUTTON_POSITIVE,
            DialogInterface.BUTTON_NEGATIVE,
            DialogInterface.BUTTON_NEUTRAL
    };

    // ------------------------------------------------------------ 实例字段

    /** 宿主 Activity（原字段 {@code a}）。 */
    final Activity activity;
    /** 是否深色主题（原字段 {@code b}）。 */
    final boolean dark;
    /** 形状样式工厂（原字段 {@code c}）。 */
    final SurfaceStyle style;
    /** 底层 Builder（原字段 {@code d}）。 */
    final AlertDialog.Builder builder;
    /** 已创建的对话框（原字段 {@code e}）。 */
    AlertDialog alert;
    /** 自定义标题栏（原字段 {@code f}）。 */
    LinearLayout titleBar;
    /** Builder 派生的上下文（原字段 {@code g}）。 */
    final Context context;
    /** 内容容器（原字段 {@code h}）。 */
    final LinearLayout content;
    /** 标题颜色（原字段 {@code i}）。 */
    final int titleColor;
    /** 次级颜色（原字段 {@code j}）。 */
    final int secondaryColor;
    /** 按钮颜色（原字段 {@code k}）。 */
    final int buttonColor;
    /** 待派发动作（原字段 {@code m}）。 */
    final ArrayList<Runnable> pendingActions;
    /** 折叠面板（原字段 {@code n}）。 */
    final ArrayList<AnimatedPanel> panels;
    /** 父对话框（原字段 {@code o}）。 */
    ModuleDialog parent;
    /** 子对话框（原字段 {@code p}）。 */
    final ArrayList<ModuleDialog> children;
    /** 未保存草稿判定（原字段 {@code r}）。 */
    BooleanSupplier hasUnsavedChanges;
    /** 放弃草稿确认对话框（原字段 {@code s}）。 */
    ModuleDialog discardDialog;
    /** 标题栏帮助文案（原字段 {@code t}）。 */
    CharSequence helpText;

    /**
     * @param activity 宿主 Activity
     * @param dark     是否深色主题
     */
    public ModuleDialog(Activity activity, boolean dark) {
        this.pendingActions = new ArrayList<>();
        this.panels = new ArrayList<>();
        this.children = new ArrayList<>();
        this.activity = activity;
        this.dark = dark;

        this.titleColor = dark ? TITLE_COLOR_DARK : TITLE_COLOR_LIGHT;

        int secondaryBase = dark ? SECONDARY_BASE_DARK : SECONDARY_BASE_LIGHT;
        Resources resources = activity.getResources();
        int colorId = resources.getIdentifier(HOST_SECONDARY_COLOR, "color",
                activity.getPackageName());
        if (colorId != 0) {
            try {
                secondaryBase = resources.getColor(colorId, activity.getTheme());
            } catch (RuntimeException ignored) {
                // 宿主未提供该主题色时沿用默认次级基色。
            }
        }
        int referencePrimary = dark ? REFERENCE_PRIMARY_DARK : REFERENCE_PRIMARY_LIGHT;
        this.secondaryColor = ViewKit.contrastingColor(
                dark ? SECONDARY_SEED_DARK : SECONDARY_SEED_LIGHT, dark,
                referencePrimary, secondaryBase);
        this.buttonColor = ViewKit.contrastingColor(
                dark ? ViewKit.ACCENT_COLOR : ACCENT_SEED_LIGHT, dark,
                referencePrimary, secondaryBase);

        this.builder = new AlertDialog.Builder(activity, dark ? THEME_DARK : THEME_LIGHT);
        this.context = builder.getContext();
        this.style = new SurfaceStyle(dark, context.getResources().getDisplayMetrics().density);

        this.content = new LinearLayout(context);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setTag(this);
        int horizontal = ViewKit.dp(activity, 0x10);
        content.setPadding(horizontal, ViewKit.dp(activity, 0x8), horizontal,
                ViewKit.dp(activity, 0x10));
    }

    // ------------------------------------------------------------ 契约访问器

    /** 宿主 Activity（原字段 {@code a}）。 */
    public Activity activity() {
        return activity;
    }

    /** 是否深色主题（原字段 {@code b}）。 */
    public boolean dark() {
        return dark;
    }

    /** Builder 派生的上下文（原字段 {@code g}）。 */
    public Context context() {
        return context;
    }

    /** 内容容器（原字段 {@code h}）。 */
    public LinearLayout content() {
        return content;
    }

    /** 已创建的对话框（原字段 {@code e}）。 */
    public AlertDialog alert() {
        return alert;
    }

    /** 底层 Builder（原字段 {@code d}）。 */
    public AlertDialog.Builder builder() {
        return builder;
    }

    /** 形状样式工厂（原字段 {@code c}）。 */
    public SurfaceStyle surfaceStyle() {
        return style;
    }

    /** 标题颜色（原字段 {@code i}）。 */
    public int titleColor() {
        return titleColor;
    }

    /** 次级颜色（原字段 {@code j}）。 */
    public int secondaryColor() {
        return secondaryColor;
    }

    /** 按钮颜色（原字段 {@code k}）。 */
    public int buttonColor() {
        return buttonColor;
    }

    /**
     * 是否紧凑布局（原字段 {@code q}）。
     *
     * <p>原实现该字段只读且从未写入，取值恒为 {@code false}；此处按其实际语义保留该契约，
     * 供 {@code section} 等调用方按非紧凑模式布局。</p>
     */
    public boolean compact() {
        return false;
    }

    // ------------------------------------------------------------ 契约方法

    /** 设置标题栏帮助文案；显示后会生成问号帮助按钮（原字段 {@code t}）。 */
    public void setHelpText(CharSequence text) {
        this.helpText = text;
    }

    /** 登记一个待派发动作（原方法 {@code a}）。 */
    public void addPendingAction(Runnable action) {
        pendingActions.add(action);
    }

    /**
     * 保护草稿：在对话框显示前注册未保存判定，并把「取消」按钮接入空操作
     * （原方法 {@code c}）。对话框已显示后调用会抛出 {@link IllegalStateException}。
     */
    public void protectDraft(BooleanSupplier unsavedChanges) {
        if (alert == null) {
            this.hasUnsavedChanges = unsavedChanges;
            builder.setNegativeButton(DRAFT_CANCEL_LABEL, new NoOpClickListener());
            return;
        }
        throw new IllegalStateException(ERROR_TOO_LATE);
    }

    /**
     * 关闭对话框：优先收起输入法，否则走放弃草稿流程（原方法 {@code b}）。
     */
    public void close() {
        if (Build.VERSION.SDK_INT >= 30) {
            View decorView = alert.getWindow().getDecorView();
            WindowInsets insets = decorView.getRootWindowInsets();
            if (insets != null && insets.isVisible(WindowInsets.Type.ime())) {
                WindowInsetsController controller = decorView.getWindowInsetsController();
                if (controller != null) {
                    controller.hide(WindowInsets.Type.ime());
                    return;
                }
            }
        }
        confirmDiscard();
    }

    /** 将内容放入滚动容器后构建并显示对话框（原方法 {@code g}）。 */
    public void show(String title, String positive) {
        ScrollView scroll = new ScrollView(context);
        scroll.addView(content);
        build(title, positive, scroll);
    }

    /**
     * 构建并显示对话框（原方法 {@code i}）。
     *
     * @param title     标题文本
     * @param positive  确认按钮文本
     * @param container 内容视图（如滚动容器）
     */
    public void build(String title, String positive, ViewGroup container) {
        titleBar = new LinearLayout(context);
        titleBar.setGravity(Gravity.CENTER_VERTICAL);
        titleBar.setPadding(ViewKit.dp(activity, 0x18), ViewKit.dp(activity, 0xE),
                ViewKit.dp(activity, 0x18), ViewKit.dp(activity, 0x8));

        TextView titleView = ViewKit.text(context, title, TITLE_TEXT_SIZE, titleColor);
        if (children.isEmpty()) {
            titleView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        } else {
            Typeface typeface = null;
            try {
                typeface = new Typeface.Builder(activity.getAssets(), TITLE_FONT_ASSET)
                        .setWeight(TITLE_FONT_WEIGHT)
                        .build();
            } catch (RuntimeException ignored) {
                // 自定义字体缺失时回退到系统字体族。
            }
            if (typeface == null) {
                typeface = Typeface.create(TITLE_FONT_FALLBACK, Typeface.NORMAL);
            }
            titleView.setTypeface(typeface);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            titleView.setAccessibilityHeading(true);
        }
        titleBar.addView(titleView,
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

        if (helpText != null) {
            titleBar.addView(ViewKit.helpButton(this, title, helpText));
        }
        titleBar.setMinimumHeight(ViewKit.dp(context, children.isEmpty()
                ? TITLE_MIN_HEIGHT_PLAIN
                : TITLE_MIN_HEIGHT_WITH_CHILDREN));

        builder.setCustomTitle(titleBar)
                .setView(container)
                .setPositiveButton(positive, new NoOpClickListener());
        alert = builder.create();
        alert.show();

        if (hasUnsavedChanges != null) {
            alert.setCanceledOnTouchOutside(false);
            Button negative = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            negative.setOnClickListener(new ConfirmDiscardClickListener(this));
            alert.setOnKeyListener(new DialogKeyListener(this));
            if (Build.VERSION.SDK_INT >= 33) {
                android.window.OnBackInvokedDispatcher dispatcher =
                        alert.getOnBackInvokedDispatcher();
                BackInvokedCloseHandler handler = new BackInvokedCloseHandler(this);
                dispatcher.registerOnBackInvokedCallback(
                        android.window.OnBackInvokedDispatcher.PRIORITY_DEFAULT, handler);
                alert.getWindow().getDecorView().addOnAttachStateChangeListener(
                        new BackDispatcherAttachListener(dispatcher, handler));
            }
        }

        Window window = alert.getWindow();
        Drawable background = style.create(1, SurfaceStyle.radiusFor(1));
        SurfaceStyle.rememberDrawable(window.getDecorView(), background);
        window.setBackgroundDrawable(background);

        for (int id : DIALOG_BUTTON_IDS) {
            Button button = alert.getButton(id);
            if (button == null) {
                continue;
            }
            style.apply(button, 9, SurfaceStyle.radiusFor(9));
            button.setTextColor(buttonColor);
            int minSize = ViewKit.dp(context, DIALOG_BUTTON_MIN_SIZE);
            button.setMinHeight(minSize);
            button.setMinWidth(minSize);
        }
    }

    /**
     * 放弃草稿流程：无修改直接关闭，有修改则弹出二次确认
     * （原方法 {@code f}）。
     */
    public void confirmDiscard() {
        if (!alert.isShowing() || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (!hasUnsavedChanges.getAsBoolean()) {
            alert.dismiss();
            return;
        }
        if (discardDialog != null && discardDialog.alert().isShowing()) {
            return;
        }
        View focused = alert.getCurrentFocus();
        discardDialog = new ModuleDialog(activity, dark);
        discardDialog.content().addView(ViewKit.text(discardDialog.context(),
                DISCARD_MESSAGE, DISCARD_MESSAGE_TEXT_SIZE, discardDialog.titleColor()));
        discardDialog.builder().setNegativeButton(DISCARD_CONFIRM_LABEL,
                new DiscardDismissListener(this));
        discardDialog.show(DISCARD_TITLE, DISCARD_KEEP_LABEL);
        discardDialog.alert().setCanceledOnTouchOutside(false);
        discardDialog.alert()
                .setOnDismissListener(new DraftGuardDismissListener(this, focused));
    }

    /**
     * 生命周期通知：有父对话框时向上透传，否则派发本层与子层的动作
     * （原方法 {@code e}）。
     */
    public void notifyLifecycle() {
        if (parent != null) {
            parent.notifyLifecycle();
            return;
        }
        dispatchPending();
        for (ModuleDialog child : children) {
            child.dispatchPending();
        }
    }

    /** 派发待处理动作：先复位折叠面板，再执行登记的动作（原方法 {@code d}）。 */
    public void dispatchPending() {
        for (AnimatedPanel panel : panels) {
            panel.reset();
        }
        for (Runnable action : pendingActions) {
            action.run();
        }
    }

    /**
     * 在锚点下方弹出菜单（原方法 {@code h}）。
     *
     * @param anchor  锚点视图；未挂接或不可见时不弹出
     * @param options 选项文本
     * @param enabled 选项是否可点
     * @param onPick  选中回调，参数为选项下标
     */
    public void showMenu(View anchor, String[] options, boolean enabled, IntConsumer onPick) {
        if (!anchor.isAttachedToWindow() || !anchor.isShown()
                || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(0, ViewKit.dp(context, MENU_VERTICAL_PADDING_DP), 0,
                ViewKit.dp(context, MENU_VERTICAL_PADDING_DP));

        ScrollView scroll = new ScrollView(context);
        style.apply(scroll, 1, MENU_RADIUS);
        scroll.setClipToOutline(true);
        scroll.addView(container);

        PopupWindow popup = new PopupWindow(context);
        popup.setContentView(scroll);
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setInputMethodMode(INPUT_METHOD_NOT_NEEDED);
        popup.setElevation(ViewKit.dp(context, dark ? 0x4 : 0x8));

        int maxWidth = ViewKit.dp(context, MENU_MAX_WIDTH_DP);
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            TextView item = ViewKit.text(context, option, MENU_ITEM_TEXT_SIZE,
                    enabled ? titleColor : secondaryColor);
            item.setGravity(MENU_ITEM_GRAVITY);
            item.setPadding(ViewKit.dp(context, MENU_ITEM_HORIZONTAL_PADDING_DP),
                    ViewKit.dp(context, MENU_ITEM_VERTICAL_PADDING_DP),
                    ViewKit.dp(context, MENU_ITEM_HORIZONTAL_PADDING_DP),
                    ViewKit.dp(context, MENU_ITEM_VERTICAL_PADDING_DP));
            item.setMinHeight(ViewKit.dp(context, MENU_MIN_SIZE_DP));
            style.apply(item, 12, SurfaceStyle.radiusFor(12));
            item.setEnabled(enabled);
            ViewKit.makeFocusable(item);
            item.setOnClickListener(
                    new PopupMenuItemClickListener(this, popup, anchor, onPick, i));

            int textWidth = (int) Math.ceil(item.getPaint().measureText(option));
            maxWidth = Math.max(maxWidth, textWidth + ViewKit.dp(context, MENU_TEXT_PADDING_DP));
            container.addView(item, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            if (i < options.length - 1) {
                View divider = new View(context);
                divider.setBackgroundColor(dark ? MENU_DIVIDER_DARK : MENU_DIVIDER_LIGHT);
                LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewKit.dp(context, 1));
                int margin = ViewKit.dp(context, MENU_ITEM_HORIZONTAL_PADDING_DP);
                dividerParams.leftMargin = margin;
                dividerParams.rightMargin = margin;
                container.addView(divider, dividerParams);
            }
        }

        Rect frame = new Rect();
        anchor.getWindowVisibleDisplayFrame(frame);
        int availableWidth = Math.min(anchor.getRootView().getWidth(), frame.width());
        int width = Math.min(maxWidth, Math.max(ViewKit.dp(context, MENU_MIN_SIZE_DP),
                availableWidth - ViewKit.dp(context, MENU_SIDE_MARGIN_DP)));
        container.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        popup.setWidth(width);
        int height = Math.min(container.getMeasuredHeight(),
                Math.max(ViewKit.dp(context, MENU_MIN_SIZE_DP),
                        frame.height() - ViewKit.dp(context, MENU_SIDE_MARGIN_DP)));
        popup.setHeight(height);

        PopupAnchorListener anchorListener = new PopupAnchorListener(popup);
        anchor.addOnAttachStateChangeListener(anchorListener);
        popup.setOnDismissListener(new PopupDismissListener(anchor, anchorListener));

        try {
            popup.showAsDropDown(anchor, 0, ViewKit.dp(context, MENU_DROP_DOWN_Y_DP),
                    MENU_POPUP_GRAVITY);
        } catch (RuntimeException error) {
            anchor.removeOnAttachStateChangeListener(anchorListener);
            throw error;
        }
    }
}
