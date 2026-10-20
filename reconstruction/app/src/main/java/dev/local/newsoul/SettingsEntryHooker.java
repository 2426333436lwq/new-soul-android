package dev.local.newsoul;

import android.app.Activity;

import io.github.libxposed.api.XposedInterface;

/**
 * 设置入口注入器。
 *
 * 对应原始 DEX 的 x2.intercept + I1.run + E1.run。
 *
 * 触发链路：
 *   1. 用户打开 Soul 自身 H5 设置页
 *   2. 宿主调用私有方法 H5Activity.m1(boolean)
 *   3. 本 Hooker 被调用，在宿主 View 树中定位锚点：
 *        topic_title      (TextView)      —— 页面标题
 *        rlWebRoot        (RelativeLayout)—— 根容器
 *        contentLayout    (View)          —— 内容区
 *        titleLay         (View)          —— 标题栏
 *   4. 校验类型与父子关系后，插入模块入口按钮
 *
 * 原实现的守卫（缺一不可，否则放弃注入）：
 *   - Activity 未销毁、未 finishing
 *   - WeakHashMap 里没有这个 Activity（防重复注入）
 *   - topic_title 是 TextView
 *   - rlWebRoot 是 RelativeLayout
 *   - contentLayout / titleLay 非空
 *   - contentLayout.getParent() == rlWebRoot
 *   - contentLayout 的 LayoutParams 是 RelativeLayout.LayoutParams
 */
public final class SettingsEntryHooker implements XposedInterface.Hooker {

    private final SoulHook module;

    public SettingsEntryHooker(SoulHook module) {
        this.module = module;
    }

    @Override
    public Object intercept(XposedInterface.Chain chain) {
        Object self = chain.getThisObject();
        if (!(self instanceof Activity a)) {
            return chain.proceed();
        }
        if (a.isDestroyed() || a.isFinishing()) {
            return chain.proceed();
        }
        // 注入要等 View 树就绪，post 到消息队列
        a.post(() -> inject(a));
        return chain.proceed();
    }

    private void inject(Activity a) {
        try {
            android.view.View title = Reflect.hostView(a, "topic_title");
            android.view.View root = Reflect.hostView(a, "rlWebRoot");
            android.view.View content = Reflect.hostView(a, "contentLayout");
            android.view.View titleLay = Reflect.hostView(a, "titleLay");

            if (!(title instanceof android.widget.TextView)
                    || !(root instanceof android.widget.RelativeLayout)
                    || content == null || titleLay == null) {
                return;
            }
            if (content.getParent() != root) {
                return;
            }
            if (!(content.getLayoutParams()
                    instanceof android.widget.RelativeLayout.LayoutParams)) {
                return;
            }

            // ---- 构造入口按钮并插入 ----
            android.widget.Button btn = new android.widget.Button(a);
            btn.setText("New Soul");
            btn.setId(android.view.View.generateViewId());

            android.widget.RelativeLayout.LayoutParams lp =
                    new android.widget.RelativeLayout.LayoutParams(
                            android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
                            android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(android.widget.RelativeLayout.ALIGN_PARENT_END);
            lp.addRule(android.widget.RelativeLayout.CENTER_VERTICAL);

            root.addView(btn, lp);

            btn.setOnClickListener(v -> SettingsUi.open(a));

        } catch (Throwable t) {
            module.log(6, "SoulHook102", "Settings entry failed", t);
        }
    }
}
