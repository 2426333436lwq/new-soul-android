import io, re

path = r"E:\student\NX\new-soul-1.34.0\NewSoul\app\src\main\java\dev\local\newsoul\ui\SettingsPanel.java"
with io.open(path, encoding="utf-8") as f:
    t = f.read()

# 字号：ViewKit.text(ctx, str, sizePx, color) 内部用 setTextSize(float)，
# 而 setTextSize 默认单位是 SP。原来传 dp(0x0E)=14dp≈42px，在 1080p 上
# 被当成 42sp → 字体巨大导致换行/裁切。
# 修正：直接传 sp 数值（14 / 13 / 12），并让行高自适应。
repl = [
    ('ViewKit.text(context, "", ViewKit.dp(context, 0x0C),', 'ViewKit.text(context, "", 13f,'),
    ('ViewKit.text(context, title, ViewKit.dp(context, 0x0E),', 'ViewKit.text(context, title, 15f,'),
    ('ViewKit.text(context, "配置", ViewKit.dp(context, 0x0E),', 'ViewKit.text(context, "配置", 15f,'),
    ('ViewKit.text(dialog.context(), label, ViewKit.dp(dialog.context(), 0x0E),',
     'ViewKit.text(dialog.context(), label, 14f,'),
]
n = 0
for old, new in repl:
    if old in t:
        t = t.replace(old, new)
        n += 1

# 开关行：允许换行 + 限制两行，避免长标签被裁
t = t.replace('        row.setSingleLine(true);',
              '        row.setMaxLines(2);\n        row.setSingleLine(false);')

with io.open(path, "w", encoding="utf-8", newline="\n") as f:
    f.write(t)
print("replaced:", n)
print("singleLine occurrences left:", t.count("setSingleLine(true)"))
