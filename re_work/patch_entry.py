import io

path = r"E:\student\NX\new-soul-1.34.0\NewSoul\app\src\main\java\dev\local\newsoul\feature\settings\SettingsEntryFeature.java"
with io.open(path, encoding="utf-8") as f:
    t = f.read()

old = """            RelativeLayout container = (RelativeLayout) root;
            TextView entry = buildEntry(activity);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            int margin = (int) (8 * activity.getResources().getDisplayMetrics().density);
            params.rightMargin = margin;

            container.addView(entry, params);
            Logger.i("settings entry inserted; trigger=private H5Activity.m1");"""

new = """            RelativeLayout container = (RelativeLayout) root;
            TextView entry = buildEntry(activity);

            // 定位：贴在标题栏（titleLay）右侧，而不是整个根容器。
            // 原因：根容器里还叠着宿主自己的设置列表，直接 addView 到根容器会被
            // 列表盖住 —— 视觉上能看到（透过透明区域），但触摸事件被列表吃掉，
            // 点了没反应。挂到标题栏这一层可以保证在列表之上。
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            if (titleLay instanceof RelativeLayout) {
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
                params.addRule(RelativeLayout.CENTER_VERTICAL);
            } else {
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
                params.addRule(RelativeLayout.CENTER_VERTICAL);
            }
            int margin = (int) (8 * activity.getResources().getDisplayMetrics().density);
            params.rightMargin = margin;

            ViewGroup target = titleLay instanceof ViewGroup ? (ViewGroup) titleLay : container;
            target.addView(entry, params);

            // 关键：抬高 z 序并确保可点击。宿主的列表可能后添加，会覆盖在普通子视图之上；
            // elevation 是让我们的按钮保持在最上层的最可靠方式（API 21+）。
            entry.setElevation(1000f);
            entry.bringToFront();
            entry.setClickable(true);
            entry.setFocusable(true);
            entry.setEnabled(true);

            // 再补一层保险：若宿主父容器拦截触摸，直接在容器上做命中测试兜底。
            installFallbackHitTest(activity, container, entry);

            Logger.i("settings entry inserted; trigger=private H5Activity.m1; host="
                    + target.getClass().getSimpleName());"""

fallback = '''
    /**
     * 兜底点击通道。
     *
     * <p>宿主设置页的容器可能注册了 {@code OnTouchListener} 或设置了
     * {@code disallowIntercept}，把触摸事件在到达我们的按钮之前吃掉。
     * 这里给容器挂一个不消费事件的监听器：只在触摸点落在按钮范围内时手动转发一次点击。</p>
     */
    private static void installFallbackHitTest(Activity activity, ViewGroup container, View entry) {
        try {
            container.setOnTouchListener((v, event) -> {
                try {
                    if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                        if (hitTest(entry, event.getRawX(), event.getRawY())) {
                            entry.performClick();
                        }
                    }
                } catch (Throwable throwable) {
                    Logger.w("settings entry hit test failed", throwable);
                }
                // 返回 false：不消费事件，宿主原有交互完全不受影响
                return false;
            });
        } catch (Throwable throwable) {
            Logger.w("settings entry fallback unavailable", throwable);
        }
    }

    /** 判断原始触摸坐标是否落在目标视图范围内。 */
    private static boolean hitTest(View target, float rawX, float rawY) {
        if (target == null || !target.isShown()) {
            return false;
        }
        int[] location = new int[2];
        target.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + target.getWidth();
        int bottom = top + target.getHeight();
        // 稍微放宽命中区域，手指点不准也能触发
        int slop = (int) (12 * target.getResources().getDisplayMetrics().density);
        return rawX >= left - slop && rawX <= right + slop
                && rawY >= top - slop && rawY <= bottom + slop;
    }
'''

if old in t:
    t = t.replace(old, new)
    idx = t.rfind("}")
    t = t[:idx] + fallback + "}\n"
    with io.open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write(t)
    print("PATCHED OK")
else:
    print("PATTERN NOT FOUND")
    i = t.find("RelativeLayout container = (RelativeLayout) root;")
    print(t[max(0, i - 300): i + 900] if i >= 0 else t[-900:])
