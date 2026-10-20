/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.SharedPreferences
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.EditText
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import dev.local.newsoul.d2_0;
import dev.local.newsoul.g_0;
import dev.local.newsoul.q2;
import java.util.ArrayList;

public final class W1
implements View.OnClickListener {
    public final int a;
    public final ArrayList b;
    public final Runnable c;
    public final EditText d;
    public final Object e;
    public final Object f;
    public final Object g;

    public /* synthetic */ W1(ArrayList arrayList, SharedPreferences sharedPreferences, g_0 g_02, Activity activity, q2 q22, EditText editText) {
        this.a = 1;
        this.b = arrayList;
        this.e = sharedPreferences;
        this.c = g_02;
        this.f = activity;
        this.g = q22;
        this.d = editText;
    }

    public /* synthetic */ W1(String[] stringArray, ArrayList arrayList, EditText editText, int[] nArray, TextView textView, d2_0 d2_02) {
        this.a = 0;
        this.e = stringArray;
        this.b = arrayList;
        this.d = editText;
        this.f = nArray;
        this.g = textView;
        this.c = d2_02;
    }

    /*
     * Exception decompiling
     */
    public final void onClick(View var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Back jump on a try block [egrp 3[TRYBLOCK] [5 : 294->318)] java.lang.IllegalArgumentException
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.insertExceptionBlocks(Op02WithProcessedDataAndRefs.java:2283)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:415)
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

