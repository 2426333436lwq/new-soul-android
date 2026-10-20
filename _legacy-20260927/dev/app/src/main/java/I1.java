package dev.local.newsoul;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class I1 implements Runnable {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ TextView b;
    public final /* synthetic */ Activity c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ View e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Serializable h;

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ I1(Activity activity, RelativeLayout relativeLayout, TextView textView, View view, RelativeLayout.LayoutParams layoutParams, boolean[] zArr, View view2) {
        this.c = activity;
        this.d = relativeLayout;
        this.b = textView;
        this.e = view;
        this.g = layoutParams;
        this.h = zArr;
        this.f = view2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                Activity activity = this.c;
                RelativeLayout relativeLayout = (RelativeLayout) this.d;
                TextView textView = this.b;
                View view = (View) this.f;
                try {
                    if (!activity.isFinishing() && !activity.isDestroyed()) {
                        View viewFindViewWithTag = relativeLayout.findViewWithTag("dev.local.newsoul.settings.entry.v2");
                        boolean zContentEquals = "设置".contentEquals(textView.getText());
                        View view2 = this.e;
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.g;
                        if (zContentEquals) {
                            boolean z = ((double) Color.luminance(textView.getCurrentTextColor())) > 0.45d;
                            boolean[] zArr = (boolean[]) this.h;
                            if (viewFindViewWithTag == null || zArr[0] != z) {
                                if (viewFindViewWithTag != null) {
                                    relativeLayout.removeView(viewFindViewWithTag);
                                }
                                zArr[0] = z;
                                FrameLayout frameLayoutJ = AbstractC0090w2.j(activity, z);
                                frameLayoutJ.setId(View.generateViewId());
                                frameLayoutJ.setTag("dev.local.newsoul.settings.entry.v2");
                                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, AbstractC0090w2.i(activity, 66));
                                layoutParams2.addRule(3, view.getId());
                                relativeLayout.addView(frameLayoutJ, layoutParams2);
                                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(layoutParams);
                                layoutParams3.removeRule(10);
                                layoutParams3.addRule(3, frameLayoutJ.getId());
                                view2.setLayoutParams(layoutParams3);
                                Log.i("SoulHook102", "settings entry inserted; trigger=private H5Activity.m1");
                            }
                        } else if (viewFindViewWithTag != null) {
                            relativeLayout.removeView(viewFindViewWithTag);
                            view2.setLayoutParams(new RelativeLayout.LayoutParams(layoutParams));
                        }
                    }
                } catch (Throwable th) {
                    Log.e("SoulHook102", "Settings entry failed", th);
                    return;
                }
                break;
            default:
                TextView textView2 = this.b;
                textView2.setEnabled(false);
                textView2.setAlpha(0.5f);
                String[] strArr = (String[]) this.d;
                strArr[0] = null;
                TextView textView3 = (TextView) this.e;
                textView3.setText("正在检查…");
                Thread thread = new Thread(new RunnableC0058o1(this.c, (C0067q2) this.g, (Button) textView2, textView3, (String) this.h, strArr), "NewSoul-UpdateCheck");
                ((Thread[]) this.f)[0] = thread;
                thread.start();
                break;
        }
    }

    public /* synthetic */ I1(Button button, String[] strArr, TextView textView, Thread[] threadArr, Activity activity, C0067q2 c0067q2, String str) {
        this.b = button;
        this.d = strArr;
        this.e = textView;
        this.f = threadArr;
        this.c = activity;
        this.g = c0067q2;
        this.h = str;
    }
}
