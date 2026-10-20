package dev.local.newsoul.feature.chat.voice;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import dev.local.newsoul.ui.ModuleDialog;
import dev.local.newsoul.ui.ViewKit;

/**
 * 语音库列表渲染（原 {@code b3.h}）。
 *
 * <p>按当前搜索关键词过滤记录，逐条生成「备注 + 时长」两行条目；备注加粗、次要行以
 * 次级色与 0.87 倍字号呈现。无匹配时显示空态文案（区分「库为空」与「无匹配」）。</p>
 */
final class VoiceListRenderer {

    /** 列表主文本字号（px，原 {@code 16.0f}）。 */
    private static final float ITEM_TEXT_SIZE = 16.0f;

    /** 次要行相对字号（原 {@code 0.87f}）。 */
    private static final float SECONDARY_SIZE = 0.87f;

    /** 行距（dp，原 {@code 4}）。 */
    private static final int LINE_SPACING_DP = 4;

    /** 次要行字号（px，原 {@code 14}）。 */
    private static final int EMPTY_TEXT_SIZE = 14;

    /** 库为空时的空态文案（原字面量）。 */
    private static final String EMPTY_LIBRARY_TEXT = "还没有保存语音";

    /** 无匹配时的空态文案（原字面量）。 */
    private static final String NO_MATCH_TEXT = "没有匹配的语音，请尝试其他备注";

    private VoiceListRenderer() {
    }

    /**
     * 渲染列表。
     *
     * @param dialog    语音库对话框（提供关键词与条目动作）
     * @param moduleDialog 目标对话框
     * @param container 列表容器
     * @param records   全部记录
     */
    static void render(VoiceLibraryDialog dialog, ModuleDialog moduleDialog, LinearLayout container,
            ArrayList<VoiceRecord> records) {
        container.removeAllViews();
        String keyword = dialog.keyword.trim().toLowerCase(Locale.ROOT);
        Context context = moduleDialog.context();
        int secondaryColor = moduleDialog.secondaryColor();
        int matched = 0;
        for (VoiceRecord record : records) {
            String remark = record.remark();
            if (!remark.toLowerCase(Locale.ROOT).contains(keyword)) {
                continue;
            }
            TextView item = VoiceLibrary.actionItem(moduleDialog,
                    remark + "\n" + record.durationSeconds() + " 秒",
                    new VoiceShowDetailsAction(dialog, record));
            SpannableString text = new SpannableString(item.getText());
            text.setSpan(new StyleSpan(Typeface.BOLD), 0, remark.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new ForegroundColorSpan(secondaryColor), remark.length() + 1,
                    text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new RelativeSizeSpan(SECONDARY_SIZE), remark.length() + 1,
                    text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setText(text);
            item.setTextSize(ITEM_TEXT_SIZE);
            item.setLineSpacing(ViewKit.dp(context, LINE_SPACING_DP), 1.0f);
            ViewKit.styleListItem(moduleDialog, container, item);
            matched++;
        }
        if (matched == 0) {
            container.addView(ViewKit.text(context,
                    records.isEmpty() ? EMPTY_LIBRARY_TEXT : NO_MATCH_TEXT,
                    EMPTY_TEXT_SIZE, secondaryColor));
        }
    }
}
