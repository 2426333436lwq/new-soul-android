package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.github.libxposed.api.XposedModule;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class N1 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ boolean c;

    public /* synthetic */ N1(Activity activity, boolean z, int i) {
        this.a = i;
        this.b = activity;
        this.c = z;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Activity activity = this.b;
        int i = 0;
        boolean z = this.c;
        switch (this.a) {
            case 0:
                AbstractC0090w2.u(activity, z);
                break;
            case 1:
                AbstractC0090w2.t(activity, z, false, null);
                break;
            case 2:
                final Activity activity2 = this.b;
                final C0067q2 c0067q2 = new C0067q2(activity2, z);
                c0067q2.t = "选择收款方式后，可用另一台设备扫码，或保存截图后识别。";
                Context context = c0067q2.g;
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(0);
                linearLayout.setGravity(17);
                AbstractC0090w2.v(linearLayout, null, 2);
                final TextView textViewG = Q0.g(c0067q2, "微信");
                final TextView textViewG2 = Q0.g(c0067q2, "支付宝");
                AbstractC0090w2.w(textViewG, 0);
                AbstractC0090w2.w(textViewG2, 1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams.rightMargin = Q0.p(context, 6);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams2.leftMargin = Q0.p(context, 6);
                linearLayout.addView(textViewG, layoutParams);
                linearLayout.addView(textViewG2, layoutParams2);
                LinearLayout linearLayout2 = c0067q2.h;
                linearLayout2.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
                final TextView textView = new TextView(context);
                textView.setText("");
                textView.setTextSize(13);
                textView.setTextColor(c0067q2.j);
                textView.setVisibility(8);
                textView.setAccessibilityLiveRegion(1);
                textView.setGravity(17);
                textView.setPadding(0, Q0.p(context, 14), 0, Q0.p(context, 8));
                linearLayout2.addView(textView, new LinearLayout.LayoutParams(-1, -2));
                final ImageView imageView = new ImageView(context);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setVisibility(8);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.topMargin = Q0.p(context, 14);
                linearLayout2.addView(imageView, layoutParams3);
                final int i2 = 0;
                Runnable runnable = new Runnable() { // from class: dev.local.newsoul.h0
                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i2) {
                            case 0:
                                Q0.V(activity2, c0067q2, imageView, textView, textViewG, textViewG2, "donation_wechat", "微信收款码");
                                break;
                            default:
                                Q0.V(activity2, c0067q2, imageView, textView, textViewG, textViewG2, "donation_alipay", "支付宝收款码");
                                break;
                        }
                    }
                };
                final int i3 = 1;
                Runnable runnable2 = new Runnable() { // from class: dev.local.newsoul.h0
                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i3) {
                            case 0:
                                Q0.V(activity2, c0067q2, imageView, textView, textViewG2, textViewG, "donation_wechat", "微信收款码");
                                break;
                            default:
                                Q0.V(activity2, c0067q2, imageView, textView, textViewG2, textViewG, "donation_alipay", "支付宝收款码");
                                break;
                        }
                    }
                };
                textViewG.setOnClickListener(new ViewOnClickListenerC0033i0(runnable, 0));
                textViewG2.setOnClickListener(new ViewOnClickListenerC0033i0(runnable2, 1));
                c0067q2.g("支持作者", "返回");
                c0067q2.e.setOnDismissListener(new DialogInterfaceOnDismissListenerC0037j0(i, imageView));
                break;
            default:
                XposedModule xposedModule = AbstractC0025g0.a;
                V.h(activity);
                new C0021f0(activity, z);
                break;
        }
    }
}
