package dev.local.newsoul;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class U implements Runnable {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Object c;

    public /* synthetic */ U(Activity activity, boolean z) {
        this.c = activity;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                boolean z = this.b;
                Runnable runnable = (Runnable) this.c;
                try {
                    if (z) {
                        if (V.k == null) {
                            V.k = new Y(new File(V.i.getNoBackupFilesDir(), "newsoul-debug"), Q0.M());
                        }
                        if (V.k.b() >= 4186112) {
                            V.l("记录已满，请先导出并清除");
                        } else {
                            V.f = System.currentTimeMillis() + 1800000;
                            V.h = "正在记录";
                            if (!V.j.edit().putLong("until", V.f).putString("status", V.h).commit()) {
                                throw new IOException();
                            }
                            V.k("session_start");
                            V.f();
                        }
                    } else {
                        V.l("已停止记录");
                        V.f();
                    }
                } catch (Throwable unused) {
                    V.l("记录失败，已停止");
                }
                V.b.post(runnable);
                return;
            default:
                Activity activity = (Activity) this.c;
                if (activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                View decorView = activity.getWindow().getDecorView();
                if (decorView instanceof ViewGroup) {
                    View viewFindViewWithTag = decorView.findViewWithTag("new.soul.privacy.photo.download");
                    if (viewFindViewWithTag != null) {
                        viewFindViewWithTag.bringToFront();
                        viewFindViewWithTag.setElevation(Q0.o(activity, 32));
                        viewFindViewWithTag.setVisibility(0);
                        return;
                    }
                    TextView textView = new TextView(activity);
                    textView.setTag("new.soul.privacy.photo.download");
                    boolean z2 = this.b;
                    textView.setContentDescription(z2 ? "保存原图" : "保存视频");
                    textView.setText(z2 ? "保存原图" : "保存视频");
                    textView.setTextColor(-1);
                    textView.setTextSize(14.0f);
                    textView.setGravity(17);
                    textView.setPadding(Q0.o(activity, 16), 0, Q0.o(activity, 16), 0);
                    new L2(true, activity.getResources().getDisplayMetrics().density).b(textView, 11, 22.0f);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, Q0.o(activity, 44), 8388693);
                    layoutParams.rightMargin = Q0.o(activity, 20);
                    layoutParams.bottomMargin = Q0.o(activity, 76);
                    activity.addContentView(textView, layoutParams);
                    AbstractC0038j1.b(textView, true);
                    textView.setElevation(Q0.o(activity, 32));
                    textView.bringToFront();
                    textView.setOnClickListener(new ViewOnClickListenerC0046l1(z2, activity, textView));
                    Q0.w("privacy save action attached: ".concat(z2 ? "photo" : "video"));
                    return;
                }
                return;
        }
    }

    public /* synthetic */ U(boolean z, RunnableC0013d0 runnableC0013d0) {
        this.b = z;
        this.c = runnableC0013d0;
    }
}
