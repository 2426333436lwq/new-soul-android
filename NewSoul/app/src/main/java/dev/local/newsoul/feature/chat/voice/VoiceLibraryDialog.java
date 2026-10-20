package dev.local.newsoul.feature.chat.voice;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;

import dev.local.newsoul.ui.ModuleDialog;
import dev.local.newsoul.ui.SurfaceStyle;
import dev.local.newsoul.ui.ViewKit;

/**
 * 语音库对话框（原混淆类 {@code b3}）。
 *
 * <p>一个实例绑定「会话 Fragment + 当前账号」，承载语音库的全部界面流程：列表、搜索、
 * 详情、修改备注、删除确认、保存与发送。构造时即完成账号/会话校验（{@link #requireValid()}），
 * 因此校验不通过会直接在构造阶段抛出 {@link IllegalStateException}。</p>
 *
 * <p>状态字段与原实现一一对应（字段名后标注原混淆名）：关闭标志 {@code closed}（{@code m}）、
 * 忙碌标志 {@code busy}（{@code n}）、后台进行标志 {@code backgroundActive}（{@code o}）。
 * 三个标志的组合语义：</p>
 * <ul>
 *   <li>{@code busy} —— 已提交一个后台任务，界面按钮切换为「处理中…」；</li>
 *   <li>{@code backgroundActive} —— 存在尚未回收的后台任务，关闭时给出提示；</li>
 *   <li>{@code closed} —— 对话框已关闭，后续回调直接短路。</li>
 * </ul>
 *
 * <p>同包协作类（任务、监听、动作）直接读取这些包级字段，以忠实还原原实现内部类对
 * {@code b3} 字段的访问方式。</p>
 */
public final class VoiceLibraryDialog {

    /** 标题文本色亮度判定阈值（原 {@code 0.45d}）。 */
    private static final double DARK_LUMINANCE_THRESHOLD = 0.45d;

    /** 宿主标题视图资源名（原 {@code "tv_title"}）。 */
    private static final String TITLE_VIEW_ID = "tv_title";

    /** 夜间模式位掩码（原 {@code 48}）。 */
    private static final int NIGHT_MODE_MASK = 0x30;

    /** 夜间模式取值（原 {@code 32}）。 */
    private static final int NIGHT_MODE_YES = 0x20;

    /** 备注输入最大长度：120 字（原 {@code 120}）。 */
    private static final int REMARK_MAX_LENGTH = 120;

    /** 列表项行距（dp，原 {@code 4}）。 */
    private static final int LIST_LINE_SPACING_DP = 4;

    /** 列表项主文本字号（px，原 {@code 16.0f}）。 */
    private static final float LIST_ITEM_TEXT_SIZE = 16.0f;

    /** 列表项次要文本相对字号（原 {@code 0.87f}）。 */
    private static final float LIST_SECONDARY_SIZE = 0.87f;

    /** 删除按钮颜色（深色，原 {@code -23645}）。 */
    private static final int DELETE_COLOR_DARK = 0xFFFFA3A3;

    /** 删除按钮颜色（浅色，原 {@code -5036514}）。 */
    private static final int DELETE_COLOR_LIGHT = 0xFFB3261E;

    /** 详情页帮助文案（原字面量）。 */
    private static final String DETAIL_HELP = "点击试听可播放本机语音。修改备注和删除只影响本机语音库；只有点击“发送到当前聊天”才会发送。";

    /** 语音库页帮助文案（原字面量）。 */
    private static final String LIBRARY_HELP = "保存语音\n长按聊天中的语音消息，选择「存入语音库」。\n\n打开语音库\n进入普通私聊，长按聊天输入区的语音图标。\n\n使用已保存的语音\n点击条目可试听、修改备注或删除。只有点击「发送到当前聊天」才会发送。\n\n语音保存在本机，按账号分别管理。";

    /** 提交后台任务提示（原字面量）。 */
    private static final String SUBMIT_NOTICE = "操作已提交，关闭后仍会继续；请稍后打开语音库查看结果。";

    /** 后台进行中的关闭提示（原字面量）。 */
    private static final String BACKGROUND_BUSY_NOTICE = "操作仍在后台进行，请稍后打开语音库查看结果";

    /** 账号/会话失效提示（原字面量）。 */
    private static final String STALE_STATE_MESSAGE = "聊天或账号已变化，请重新打开语音库";

    /** 备注输入提示（原字面量）。 */
    private static final String REMARK_HINT = "给这条语音写个备注";

    /** 详情页标题 / 确认按钮（原字面量）。 */
    private static final String DETAIL_TITLE = "语音详情";

    /** 详情页确认按钮文本（原字面量）。 */
    private static final String DETAIL_POSITIVE = "返回语音库";

    /** 语音库标题 / 确认按钮（原字面量）。 */
    private static final String LIBRARY_TITLE = "语音库";

    /** 语音库确认按钮文本（原字面量）。 */
    private static final String LIBRARY_POSITIVE = "关闭";

    /** 读取中占位文案（原字面量）。 */
    private static final String LIBRARY_READING = "正在读取语音库…";

    /** 删除确认标题 / 确认按钮（原字面量）。 */
    private static final String DELETE_TITLE = "删除语音";

    /** 删除确认按钮文本（原字面量）。 */
    private static final String DELETE_POSITIVE = "删除";

    /** 忙碌时负按钮文本（原字面量）。 */
    private static final String BUSY_NEGATIVE_LABEL = "关闭";

    /** 忙碌时正按钮文本（原字面量）。 */
    private static final String BUSY_POSITIVE_LABEL = "处理中…";

    // ------------------------------------------------------------ 实例字段（原 b3）

    /** 所属语音库功能域（原字段 {@code q}）。 */
    final VoiceLibrary library;

    /** 宿主会话 Fragment（原字段 {@code a}）。 */
    final Object host;

    /** 会话对象（原字段 {@code b}）。 */
    final Object conversation;

    /** 宿主 Activity（原字段 {@code c}）。 */
    final Activity activity;

    /** 当前账号 id（原字段 {@code d}）。 */
    final String account;

    /** 会话标识字段 {@code L} 的取值（原字段 {@code e}）。 */
    final String hostKey;

    /** 当前账号的本地语音库（原字段 {@code f}）。 */
    final VoiceLibraryStore store;

    /** 是否深色主题（原字段 {@code g}）。 */
    final boolean dark;

    /** 当前对话框（原字段 {@code h}）。 */
    ModuleDialog dialog;

    /** 当前语音库列表对话框（原字段 {@code i}）。 */
    ModuleDialog listDialog;

    /** 列表滚动位置（原字段 {@code j}）。 */
    int scrollY;

    /** 搜索关键词（原字段 {@code k}）。 */
    String keyword = "";

    /** 当前试听播放器（原字段 {@code l}）。 */
    MediaPlayer mediaPlayer;

    /** 是否已关闭（原字段 {@code m}）。 */
    boolean closed;

    /** 是否已提交后台任务（原字段 {@code n}）。 */
    boolean busy;

    /** 是否存在尚未回收的后台任务（原字段 {@code o}）。 */
    boolean backgroundActive;

    /** 详情页试听按钮（原字段 {@code p}）。 */
    TextView previewButton;

    /**
     * 解析账号与宿主成员，校验会话状态并创建本地语音库。
     *
     * @throws ReflectiveOperationException 宿主成员签名不匹配
     * @throws IOException                  本地语音库目录无法创建
     * @throws IllegalStateException        会话或账号已变化（{@link #requireValid()}）
     */
    public VoiceLibraryDialog(VoiceLibrary library, Object host)
            throws ReflectiveOperationException, IOException {
        this.library = library;
        this.host = host;
        this.activity = (Activity) VoiceLibrary.call(host, "getActivity");
        this.account = (String) library.accountGetter().invoke(null, (Object[]) null);
        this.hostKey = (String) VoiceLibrary.readField(host, "L");
        this.conversation = VoiceLibrary.conversation(host);
        requireValid();
        this.store = new VoiceLibraryStore(activity.getFilesDir(), account);

        int identifier = activity.getResources()
                .getIdentifier(TITLE_VIEW_ID, "id", activity.getPackageName());
        View titleView = identifier == 0 ? null : activity.findViewById(identifier);
        boolean isDark;
        if (!(titleView instanceof TextView)) {
            isDark = (activity.getResources().getConfiguration().uiMode & NIGHT_MODE_MASK)
                    == NIGHT_MODE_YES;
        } else {
            isDark = Color.luminance(((TextView) titleView).getCurrentTextColor())
                    > DARK_LUMINANCE_THRESHOLD;
        }
        this.dark = isDark;
    }

    // ------------------------------------------------------------ 流程（原 a-k）

    /**
     * 提交后台任务并把当前对话框切换为「处理中」状态（原方法 {@code a}）。
     *
     * @param work   后台执行的动作
     * @param onDone 成功后执行的动作
     */
    void submitBackground(RunnableAction work, RunnableAction onDone) {
        if (busy) {
            return;
        }
        busy = true;
        backgroundActive = true;
        ModuleDialog current = dialog;
        TextView notice = ViewKit.text(current.context(), SUBMIT_NOTICE, 13,
                current.secondaryColor());
        current.content().addView(notice);
        Button negative = current.alert().getButton(DialogInterface.BUTTON_NEGATIVE);
        if (negative != null) {
            negative.setText(BUSY_NEGATIVE_LABEL);
        }
        Button positive = current.alert().getButton(DialogInterface.BUTTON_POSITIVE);
        if (positive != null) {
            positive.setEnabled(false);
            positive.setText(BUSY_POSITIVE_LABEL);
        }
        library.worker().execute(
                new VoiceBackgroundTask(this, work, current, notice, onDone));
    }

    /** 关闭对话框并释放播放器（原方法 {@code b}）。 */
    void close() {
        if (closed) {
            return;
        }
        closed = true;
        stopPlayback();
        ModuleDialog current = dialog;
        if (current != null && current.alert() != null) {
            current.alert().dismiss();
        }
        if (library.currentDialog() == this) {
            library.setCurrentDialog(null);
        }
    }

    /** 后台仍在进行时给出提示后再关闭（原方法 {@code c}）。 */
    void closeWhileBusy() {
        if (backgroundActive) {
            VoiceLibrary.toast(activity, BACKGROUND_BUSY_NOTICE);
        }
        close();
    }

    /** 展示某条语音的详情页（原方法 {@code d}）。 */
    void showDetails(VoiceRecord record) {
        requireValid();
        ModuleDialog target = freshDialog();
        Context context = target.context();
        String remark = record.remark();
        TextView title = ViewKit.text(context, remark, 17, target.titleColor());
        target.setHelpText(DETAIL_HELP);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setPadding(0, ViewKit.dp(context, 20), 0, ViewKit.dp(context, 8));
        LinearLayout content = target.content();
        content.addView(title, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView subtitle = ViewKit.text(context, record.durationSeconds() + " 秒 · 已保存在本机",
                13, target.secondaryColor());
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 0, 0, ViewKit.dp(context, 24));
        content.addView(subtitle, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        previewButton = VoiceLibrary.actionItem(target, "试听", new VoicePlayAction(this, record));
        TextView editRemark = VoiceLibrary.actionItem(target, "修改备注",
                new VoiceEditRemarkAction(this, record));
        int buttonColor = target.buttonColor();
        previewButton.setTextColor(buttonColor);
        editRemark.setTextColor(buttonColor);
        ViewKit.styleActionRow(target, content, previewButton, editRemark);

        TextView delete = VoiceLibrary.actionItem(target, "删除",
                new VoiceDeletePromptAction(this, record));
        delete.setTextColor(dark ? DELETE_COLOR_DARK : DELETE_COLOR_LIGHT);
        delete.setGravity(Gravity.CENTER);
        content.addView(delete, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView send = VoiceLibrary.actionItem(target, "发送到当前聊天",
                new VoiceSendAction(this, record));
        send.setTextColor(buttonColor);
        send.setGravity(Gravity.CENTER);
        target.surfaceStyle().apply(send, 7, SurfaceStyle.radiusFor(7));
        LinearLayout.LayoutParams sendParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sendParams.topMargin = ViewKit.dp(context, 16);
        sendParams.bottomMargin = ViewKit.dp(context, 12);
        content.addView(send, sendParams);

        target.show(DETAIL_TITLE, DETAIL_POSITIVE);
        bindDialogListeners(new VoicePostSaveAction(VoicePostSaveAction.OPEN_LIBRARY, this));
        Button positive = target.alert().getButton(DialogInterface.BUTTON_POSITIVE);
        positive.setTextColor(buttonColor);
        positive.setMinHeight(ViewKit.dp(context, 48));
        positive.setOnClickListener(new VoiceLibraryOpenClick(this));
    }

    /**
     * 展示备注编辑页（原方法 {@code e}）。
     *
     * @param title    标题
     * @param initial  初始文本
     * @param consumer 保存回调
     * @param onCancel 取消回调
     */
    void showRemarkEditor(String title, String initial, TextConsumer consumer,
            RunnableAction onCancel) {
        ModuleDialog target = freshDialog();
        Context context = target.context();
        EditText editor = new EditText(context);
        editor.setTextColor(target.titleColor());
        editor.setHintTextColor(target.secondaryColor());
        target.surfaceStyle().apply(editor, 8, SurfaceStyle.radiusFor(8));
        editor.setPadding(ViewKit.dp(context, 12), ViewKit.dp(context, 10),
                ViewKit.dp(context, 12), ViewKit.dp(context, 10));
        editor.setHint(REMARK_HINT);
        editor.setText(initial);
        editor.setMinLines(2);
        editor.setMaxLines(4);
        editor.setFilters(new InputFilter[] {new InputFilter.LengthFilter(REMARK_MAX_LENGTH)});
        target.content().addView(editor, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        target.builder().setNegativeButton("取消", new VoiceDialogNoOpClick());
        target.show(title, "保存");
        bindDialogListeners(onCancel);
        target.alert().getButton(DialogInterface.BUTTON_NEGATIVE)
                .setOnClickListener(new VoiceDialogActionClick(this, onCancel));
        target.alert().getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(new VoiceRemarkSubmitClick(this, consumer, editor));
    }

    /** 打开语音库列表页并开始异步读取（原方法 {@code f}）。 */
    void openLibrary() {
        requireValid();
        ModuleDialog target = freshDialog();
        target.setHelpText(LIBRARY_HELP);
        target.content().addView(ViewKit.text(target.context(), LIBRARY_READING, 14,
                target.secondaryColor()));
        this.listDialog = target;
        target.show(LIBRARY_TITLE, LIBRARY_POSITIVE);
        bindDialogListeners(new VoicePostSaveAction(VoicePostSaveAction.CLOSE, this));
        library.worker().execute(new VoiceReadTask(this, target));
    }

    /** 停止试听、脱钩旧对话框并创建新对话框（原方法 {@code g}）。 */
    ModuleDialog freshDialog() {
        stopPlayback();
        ModuleDialog current = dialog;
        if (current != null && current.alert() != null) {
            if (current == listDialog && current.content().getParent() instanceof ScrollView) {
                scrollY = ((ScrollView) current.content().getParent()).getScrollY();
            }
            current.alert().setOnCancelListener(null);
            current.alert().setOnDismissListener(null);
            current.alert().dismiss();
        }
        previewButton = null;
        ModuleDialog next = new ModuleDialog(activity, dark);
        this.dialog = next;
        return next;
    }

    /** 渲染列表（原方法 {@code h}），委托 {@link VoiceListRenderer}。 */
    void renderList(ModuleDialog target, LinearLayout container, java.util.ArrayList<VoiceRecord> records) {
        VoiceListRenderer.render(this, target, container, records);
    }

    /** 停止并释放播放器，复位试听按钮（原方法 {@code i}）。 */
    void stopPlayback() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (previewButton != null) {
            previewButton.setText("试听");
        }
    }

    /**
     * 校验会话与账号仍然有效（原方法 {@code j}）。
     *
     * @throws IllegalStateException 任一条件不满足（{@value #STALE_STATE_MESSAGE}）
     */
    void requireValid() {
        if (!closed && activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            try {
                if ((Boolean) VoiceLibrary.call(host, "isResumed")) {
                    Object currentAccount = library.accountGetter().invoke(null, (Object[]) null);
                    if (account.equals(currentAccount)
                            && hostKey != null && !hostKey.isEmpty()
                            && !account.equals(hostKey)
                            && hostKey.equals(VoiceLibrary.readField(host, "L"))
                            && conversation != null
                            && conversation == VoiceLibrary.conversation(host)
                            && ((Integer) VoiceLibrary.call(conversation, "getChatType")) == 0
                            && hostKey.equals(VoiceLibrary.call(conversation, "getToUserId"))) {
                        return;
                    }
                }
            } catch (ReflectiveOperationException ignored) {
                // 与原实现一致：反射失败视为校验不通过。
            }
        }
        throw new IllegalStateException(STALE_STATE_MESSAGE);
    }

    /**
     * 绑定取消/消散监听（原方法 {@code k}）。
     *
     * @param onCancel 取消时执行的动作
     */
    void bindDialogListeners(RunnableAction onCancel) {
        ModuleDialog current = dialog;
        current.alert().setCanceledOnTouchOutside(false);
        current.alert().setOnCancelListener(new VoiceCancelListener(this, current, onCancel));
        current.alert().setOnDismissListener(new VoiceDismissListener(this, current));
    }

    /** 当前是否已关闭（原字段 {@code m}）。 */
    boolean closed() {
        return closed;
    }

    /** 当前是否已提交后台任务（原字段 {@code n}）。 */
    boolean busy() {
        return busy;
    }
}
