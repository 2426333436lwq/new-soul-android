import io

path = r"E:\student\NX\new-soul-1.34.0\NewSoul\app\src\main\java\dev\local\newsoul\feature\square\SquareLikeFeature.java"
with io.open(path, encoding="utf-8") as f:
    t = f.read()

old = """        boolean installed = false;
        for (Method method : Reflect.findMethods(mainActivity, "onResume")) {
            if (Hooks.intercept(context.module(), HookIds.SQUARE_LIKE_LIFECYCLE, method,
                    new SquareLifecycleHooker())) {
                installed = true;
            }
        }"""

new = """        // 直接反射 android.app.Activity.onResume，不用 Reflect.findMethods(mainActivity, ...)。
        // 原因：宿主 MainActivity 自己不声明 onResume（继承自 MartianActivity），
        // 而 Reflect 带框架类守卫（防止误 hook 框架基类），allMethods 走到
        // android.app.Activity 就停，永远找不到 onResume。
        // 原模块同样是 hook Activity.onResume 基类，由 LSPosed 分发到所有实例。
        Method onResume;
        try {
            onResume = android.app.Activity.class.getDeclaredMethod("onResume");
            onResume.setAccessible(true);
        } catch (Throwable throwable) {
            Logger.e("square like unavailable: Activity.onResume not found", throwable);
            return;
        }

        boolean installed = Hooks.intercept(context.module(), HookIds.SQUARE_LIKE_LIFECYCLE,
                onResume, new SquareLifecycleHooker());"""

if old in t:
    t = t.replace(old, new)
    with io.open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write(t)
    print("PATCHED OK")
else:
    print("PATTERN NOT FOUND")
    i = t.find("boolean installed = false")
    print(t[max(0, i - 200): i + 500] if i >= 0 else t[-800:])
