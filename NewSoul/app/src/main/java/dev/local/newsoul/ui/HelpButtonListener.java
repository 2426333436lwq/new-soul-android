package dev.local.newsoul.ui;

/**
 * 「帮助说明」按钮监听（原 {@code G1} 的索引 0 分支）。
 *
 * <p>点击后在当前对话框之上弹出一个仅含说明正文的说明对话框。</p>
 */
final class HelpButtonListener implements android.view.View.OnClickListener {

    private final ModuleDialog dialog;
    private final CharSequence description;
    private final String title;

    HelpButtonListener(ModuleDialog dialog, CharSequence description, String title) {
        this.dialog = dialog;
        this.description = description;
        this.title = title;
    }

    @Override
    public void onClick(android.view.View view) {
        ModuleDialog help = new ModuleDialog(dialog.activity(), dialog.dark());
        android.widget.TextView body = ViewKit.text(help.context(), "", 0xF, help.titleColor());
        body.setText(description);
        body.setLineSpacing(ViewKit.dp(help.context(), 4), 1.0f);
        body.setGravity(0x800003);
        body.setBreakStrategy(0);
        body.setHyphenationFrequency(0);
        help.content().addView(body);
        help.show(title, "知道了");
    }
}
