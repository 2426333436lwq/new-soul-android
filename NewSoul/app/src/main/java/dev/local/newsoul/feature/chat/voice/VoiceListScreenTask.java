package dev.local.newsoul.feature.chat.voice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.ui.ModuleDialog;
import dev.local.newsoul.ui.SurfaceStyle;
import dev.local.newsoul.ui.ViewKit;

/**
 * 语音库列表页渲染任务（原 {@code H} 末位分支，索引 13）。
 *
 * <p>在主线程构建搜索框与列表容器：搜索框带系统搜索图标与备注搜索提示，输入即时过滤
 * （见 {@link VoiceSearchWatcher}）；随后恢复上次滚动位置并记录
 * {@code voice library shown; count=}。任一步异常即关闭对话框。</p>
 */
final class VoiceListScreenTask implements Runnable {

    /** 搜索框提示（原字面量）。 */
    private static final String SEARCH_HINT = "搜索备注";

    /** 搜索框无障碍描述（原字面量）。 */
    private static final String SEARCH_DESCRIPTION = "搜索语音备注";

    /** 搜索框字号（px，原 {@code 15.0f}）。 */
    private static final float SEARCH_TEXT_SIZE = 15.0f;

    /** 搜索框最小高度（dp，原 {@code 48}）。 */
    private static final int SEARCH_MIN_HEIGHT_DP = 48;

    /** 搜索图标尺寸（dp，原 {@code 20}）。 */
    private static final int SEARCH_ICON_SIZE_DP = 20;

    /** 图标与文本间距（dp，原 {@code 8}）。 */
    private static final int SEARCH_ICON_PADDING_DP = 8;

    /** 列表容器上内边距（dp，原 {@code 12}）。 */
    private static final int LIST_PADDING_TOP_DP = 12;

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;
    private final List<VoiceRecord> records;

    VoiceListScreenTask(VoiceLibraryDialog dialog, ModuleDialog target, List<VoiceRecord> records) {
        this.dialog = dialog;
        this.target = target;
        this.records = records;
    }

    @Override
    public void run() {
        if (dialog.closed || dialog.dialog != target) {
            return;
        }
        try {
            dialog.requireValid();
            target.content().removeAllViews();
            Context context = target.context();

            EditText search = new EditText(context);
            search.setTextColor(target.titleColor());
            search.setHintTextColor(target.secondaryColor());
            search.setHint(SEARCH_HINT);
            search.setContentDescription(SEARCH_DESCRIPTION);
            search.setSingleLine(true);
            search.setMinHeight(ViewKit.dp(context, SEARCH_MIN_HEIGHT_DP));
            search.setTextSize(SEARCH_TEXT_SIZE);
            search.setPadding(ViewKit.dp(context, 14), ViewKit.dp(context, 10),
                    ViewKit.dp(context, 14), ViewKit.dp(context, 10));
            target.surfaceStyle().apply(search, 8, SurfaceStyle.radiusFor(8));
            Drawable icon = context.getDrawable(android.R.drawable.ic_menu_search).mutate();
            icon.setTint(target.secondaryColor());
            int iconSize = ViewKit.dp(context, SEARCH_ICON_SIZE_DP);
            icon.setBounds(0, 0, iconSize, iconSize);
            search.setCompoundDrawablesRelative(icon, null, null, null);
            search.setCompoundDrawablePadding(ViewKit.dp(context, SEARCH_ICON_PADDING_DP));
            search.setText(dialog.keyword);
            search.setSelection(search.length());
            target.content().addView(search, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout list = new LinearLayout(context);
            list.setOrientation(LinearLayout.VERTICAL);
            list.setPadding(0, ViewKit.dp(context, LIST_PADDING_TOP_DP), 0, 0);
            target.content().addView(list, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            ArrayList<VoiceRecord> items = new ArrayList<>(records);
            dialog.renderList(target, list, items);
            search.addTextChangedListener(new VoiceSearchWatcher(dialog, target, list, items));
            list.setFocusableInTouchMode(true);
            list.requestFocus();

            if (target.content().getParent() instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) target.content().getParent();
                scrollView.post(new VoiceScrollRestoreAction(dialog, target, scrollView));
            }
            Logger.i("voice library shown; count=" + records.size());
        } catch (Exception error) {
            dialog.close();
        }
    }
}
