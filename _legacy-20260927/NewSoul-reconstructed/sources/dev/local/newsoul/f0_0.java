/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.LinearLayout
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import dev.local.newsoul.b0_0;
import dev.local.newsoul.c0_0;
import dev.local.newsoul.e0_0;
import dev.local.newsoul.g0_0;
import dev.local.newsoul.q2;
import dev.local.newsoul.r2_0;
import dev.local.newsoul.w2;

/*
 * Renamed from dev.local.newsoul.f0
 */
public final class f0_0 {
    public final q2 a;
    public final r2_0 b;
    public final TextView c;
    public final TextView d;
    public final TextView e;
    public final TextView f;
    public boolean g;
    public boolean h;

    public f0_0(Activity object, boolean bl) {
        TextView textView;
        r2_0 r2_02;
        object = new q2((Activity)object, bl);
        this.a = object;
        ((q2)object).t = "\u9047\u5230\u95ee\u9898\u65f6\uff0c\u5f00\u542f\u8c03\u8bd5\u6a21\u5f0f\uff0c\u518d\u8fd4\u56de\u51fa\u73b0\u95ee\u9898\u7684\u9875\u9762\u590d\u73b0\u4e00\u6b21\uff0c\u7136\u540e\u5bfc\u51fa\u8bb0\u5f55\u5e76\u53d1\u7ed9\u4f5c\u8005\u3002\u5982\u9700\u6392\u67e5\u542f\u52a8\u95ee\u9898\uff0c\u53ef\u5728\u5f00\u542f\u540e\u91cd\u542f Soul\u3002\n\n\u6bcf\u6b21\u6700\u591a\u8bb0\u5f55 30 \u5206\u949f\uff0c\u603b\u5927\u5c0f\u4e0a\u9650 4 MB\uff0c\u5230\u8fbe\u4e0a\u9650\u4f1a\u81ea\u52a8\u505c\u6b62\u3002\u5173\u95ed\u8c03\u8bd5\u4e0d\u4f1a\u5220\u9664\u5df2\u6709\u8bb0\u5f55\uff1b\u6e05\u9664\u8bb0\u5f55\u4f1a\u540c\u65f6\u505c\u6b62\u8c03\u8bd5\u3002\n\n\u8bb0\u5f55\u5e94\u7528\u4e0e\u7cfb\u7edf\u7248\u672c\u3001\u9875\u9762\u3001\u529f\u80fd\u6267\u884c\u72b6\u6001\u53ca\u9519\u8bef\u4f4d\u7f6e\uff0c\u4e0d\u8bb0\u5f55\u804a\u5929\u6b63\u6587\u3001\u8bed\u97f3\u5185\u5bb9\u3001\u8d26\u53f7\u51ed\u636e\u6216 API \u5bc6\u94a5\u3002";
        LinearLayout linearLayout = w2.r((q2)object, "\u8bb0\u5f55", null, null);
        this.b = r2_02 = w2.d(linearLayout, null, "debug_session", "\u8c03\u8bd5\u6a21\u5f0f", "", ((q2)object).i);
        this.c = w2.c(linearLayout, "");
        Context context = ((q2)object).g;
        int n = ((q2)object).k;
        this.e = textView = w2.n(context, "\u5bfc\u51fa\u8bb0\u5f55", 15, n);
        context = w2.n(context, "\u6e05\u9664\u8bb0\u5f55", 15, n);
        this.f = context;
        w2.g((View)textView);
        w2.g((View)context);
        w2.a((q2)object, linearLayout, textView, (TextView)context);
        linearLayout = w2.c(linearLayout, "");
        this.d = linearLayout;
        linearLayout.setVisibility(8);
        linearLayout.setAccessibilityLiveRegion(1);
        r2_02.setOnCheckedChangeListener(new b0_0(this));
        textView.setOnClickListener((View.OnClickListener)new c0_0(this, 0));
        context.setOnClickListener((View.OnClickListener)new c0_0(this, 1));
        ((q2)object).g("\u8c03\u8bd5", "\u5173\u95ed");
        this.b();
        g0_0.b.postDelayed((Runnable)new e0_0(this), 1000L);
    }

    public final void a(String string) {
        TextView textView = this.d;
        textView.setText((CharSequence)string);
        textView.setVisibility(0);
        this.b();
    }

    /*
     * Exception decompiling
     */
    public final void b() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Statement already marked as first in another block
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.markFirstStatementInBlock(Op03SimpleStatement.java:461)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.markWholeBlock(Misc.java:251)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.considerAsSimpleIf(ConditionalRewriter.java:673)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.identifyNonjumpingConditionals(ConditionalRewriter.java:56)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:722)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }
}

