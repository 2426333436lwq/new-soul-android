import io

path = r"E:\student\NX\new-soul-1.34.0\NewSoul\app\src\main\java\dev\local\newsoul\ui\ViewKit.java"
with io.open(path, encoding="utf-8") as f:
    t = f.read()

old = """    public static TextView text(Context context, String value, int sizePx, int color) {
        TextView view = new TextView(context);
        view.setText(value);
        view.setTextSize((float) sizePx);
        view.setTextColor(color);
        return view;
    }"""

new = """    public static TextView text(Context context, String value, int sizePx, int color) {
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
    }"""

if old in t:
    t = t.replace(old, new)
    with io.open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write(t)
    print("PATCHED OK")
else:
    print("PATTERN NOT FOUND")
