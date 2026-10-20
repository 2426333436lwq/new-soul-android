package dev.local.newsoul.feature.chat.voice;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import dev.local.newsoul.ui.ModuleDialog;

/**
 * 搜索框文本监听（原 {@code a3}）。
 *
 * <p>输入变化时即刷新列表：把关键词写回对话框、重置滚动位置、按新关键词重渲染，并把列表
 * 滚回顶部。对话框已关闭或当前对话框已不是挂接该监听的那个时直接短路。</p>
 */
final class VoiceSearchWatcher implements TextWatcher {

    private final VoiceLibraryDialog dialog;
    private final ModuleDialog target;
    private final LinearLayout list;
    private final ArrayList<VoiceRecord> records;

    VoiceSearchWatcher(VoiceLibraryDialog dialog, ModuleDialog target, LinearLayout list,
            ArrayList<VoiceRecord> records) {
        this.dialog = dialog;
        this.target = target;
        this.list = list;
        this.records = records;
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void beforeTextChanged(CharSequence text, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        if (dialog.closed || dialog.dialog != target) {
            return;
        }
        dialog.keyword = text.toString();
        dialog.scrollY = 0;
        dialog.renderList(target, list, records);
        if (list.getParent() instanceof ScrollView) {
            ((ScrollView) list.getParent()).scrollTo(0, 0);
        }
    }
}
