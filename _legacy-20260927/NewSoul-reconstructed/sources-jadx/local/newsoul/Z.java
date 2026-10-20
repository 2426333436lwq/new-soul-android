package dev.local.newsoul;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Z implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ Z(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
        this.e = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        View viewFindViewWithTag;
        String str;
        switch (this.a) {
            case 0:
                byte[] bArr = (byte[]) this.e;
                Activity activity = (Activity) this.b;
                boolean zIsDestroyed = activity.isDestroyed();
                C0001a0 c0001a0 = (C0001a0) this.d;
                if (!zIsDestroyed && !activity.isFinishing()) {
                    C0021f0 c0021f0 = (C0021f0) this.c;
                    if (c0021f0.a.e.isShowing()) {
                        try {
                            AbstractC0025g0.a(activity);
                            c0001a0.c = bArr;
                            c0001a0.d = true;
                            activity.startActivityForResult(new Intent("android.intent.action.CREATE_DOCUMENT").addCategory("android.intent.category.OPENABLE").setType("application/octet-stream").putExtra("android.intent.extra.TITLE", "NewSoul-debug-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ROOT).format(new Date()) + ".nsdebug"), c0001a0.a);
                            c0021f0.a("请选择保存位置");
                        } catch (Exception unused) {
                            AbstractC0025g0.b(activity, c0001a0, "无法打开保存位置，请重试");
                            return;
                        }
                    }
                }
                AbstractC0025g0.b(activity, c0001a0, "已取消导出");
                break;
            case 1:
                int i = 0;
                while (true) {
                    ArrayList arrayList = (ArrayList) this.b;
                    int size = arrayList.size();
                    int[] iArr = (int[]) this.d;
                    if (i >= size) {
                        z = iArr[0] >= 0;
                        TextView textView = (TextView) this.e;
                        textView.setEnabled(z);
                        textView.setAlpha(iArr[0] >= 0 ? 1.0f : 0.35f);
                    } else {
                        View view = (View) arrayList.get(i);
                        z = ((Integer) ((ArrayList) this.c).get(i)).intValue() == iArr[0];
                        view.setSelected(z);
                        if ((view instanceof FrameLayout) && (viewFindViewWithTag = ((FrameLayout) view).findViewWithTag("tick")) != null) {
                            viewFindViewWithTag.setVisibility(z ? 0 : 4);
                        }
                        i++;
                    }
                    break;
                }
                break;
            default:
                Switch r0 = (Switch) this.b;
                boolean z2 = false;
                boolean z3 = r0.isEnabled() && r0.isChecked();
                Switch r4 = (Switch) this.c;
                boolean z4 = r4.isEnabled() && r4.isChecked();
                Switch r6 = (Switch) this.d;
                if (r6.isEnabled() && r6.isChecked()) {
                    z2 = true;
                }
                if (z3) {
                    str = "自动点赞已开启 · 范围：已加载的全部帖子。\n一键点赞配置暂不生效。";
                } else if (z4) {
                    str = z2 ? "一键点赞可用 · 范围：已加载的全部帖子。" : "一键点赞可用 · 范围：屏幕内帖子。";
                } else {
                    str = (r0.isChecked() || r4.isChecked()) ? "已开启的点赞功能尚未就绪。" : "一键点赞和自动点赞均已关闭。";
                }
                AbstractC0090w2.s((TextView) this.e, str);
                break;
        }
    }
}
