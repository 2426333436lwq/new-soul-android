package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class K implements Runnable {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ Map c;
    public final /* synthetic */ P d;

    public /* synthetic */ K(Activity activity, P p, Map map) {
        this.b = activity;
        this.d = p;
        this.c = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final boolean zCommit = false;
        switch (this.a) {
            case 0:
                final Activity activity = this.b;
                final P p = this.d;
                if (S.d(activity, p)) {
                    Q q = (Q) p.e.get();
                    if (q != null) {
                        q.a("配置已读取，等待确认导入", true);
                    }
                    final C0067q2 c0067q2 = new C0067q2(activity, p.b);
                    LinearLayout linearLayout = c0067q2.h;
                    final Map map = this.c;
                    String str = "将导入 " + map.size() + " 项设置。文件中未包含的设置和现有密钥保持不变。";
                    Context context = c0067q2.g;
                    int i = c0067q2.i;
                    linearLayout.addView(AbstractC0090w2.n(context, str, 15, i));
                    if (Boolean.TRUE.equals(map.get("square_auto_like"))) {
                        linearLayout.addView(AbstractC0090w2.n(context, "此配置会开启自动点赞。", 14, i));
                    }
                    linearLayout.addView(AbstractC0090w2.n(context, "当前不可用的配置项仅保存，不启用对应功能。", 13, c0067q2.j));
                    c0067q2.d.setNegativeButton("取消", new L(0));
                    c0067q2.g("导入配置", "确认导入");
                    final boolean[] zArr = {false};
                    c0067q2.e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: dev.local.newsoul.M
                        @Override // android.content.DialogInterface.OnDismissListener
                        public final void onDismiss(DialogInterface dialogInterface) {
                            if (zArr[0]) {
                                return;
                            }
                            S.b(activity, p, "已取消导入");
                        }
                    });
                    c0067q2.e.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: dev.local.newsoul.N
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            boolean[] zArr2 = zArr;
                            if (zArr2[0]) {
                                return;
                            }
                            zArr2[0] = true;
                            P p2 = p;
                            Q q2 = (Q) p2.e.get();
                            if (q2 != null) {
                                q2.a("正在导入配置…", true);
                            }
                            c0067q2.e.dismiss();
                            S.c.execute(new K(activity, map, p2));
                        }
                    });
                } else {
                    S.b(activity, p, "已取消导入");
                }
                break;
            default:
                final Activity activity2 = this.b;
                Map map2 = this.c;
                try {
                    SharedPreferences.Editor editorEdit = AbstractC0090w2.o(activity2).edit();
                    for (Map.Entry entry : map2.entrySet()) {
                        Object value = entry.getValue();
                        if (value instanceof Boolean) {
                            editorEdit.putBoolean((String) entry.getKey(), ((Boolean) value).booleanValue());
                        } else if (value instanceof Integer) {
                            editorEdit.putInt((String) entry.getKey(), ((Integer) value).intValue());
                        } else {
                            editorEdit.putString((String) entry.getKey(), (String) value);
                        }
                    }
                    zCommit = editorEdit.commit();
                    break;
                } catch (Exception unused) {
                }
                Handler handler = S.b;
                final P p2 = this.d;
                handler.post(new Runnable() { // from class: dev.local.newsoul.O
                    @Override // java.lang.Runnable
                    public final void run() {
                        P p3 = p2;
                        C0067q2 c0067q3 = (C0067q2) p3.d.get();
                        boolean z = zCommit;
                        Activity activity3 = activity2;
                        if (z) {
                            if (c0067q3 != null && S.d(activity3, p3)) {
                                c0067q3.e();
                            }
                            AbstractC0061p0.h();
                        }
                        S.b(activity3, p3, z ? "配置已导入\n部分功能重启 Soul 后生效" : "配置保存失败，请重新导入");
                    }
                });
                break;
        }
    }

    public /* synthetic */ K(Activity activity, Map map, P p) {
        this.b = activity;
        this.c = map;
        this.d = p;
    }
}
