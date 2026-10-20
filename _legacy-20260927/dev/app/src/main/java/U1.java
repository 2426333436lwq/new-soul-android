package dev.local.newsoul;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: U1.class */
public final /* synthetic */ class U1 implements CompoundButton.OnCheckedChangeListener {
    public final SharedPreferences a;
    public final C0070r2 b;
    public final String c;

    public /* synthetic */ U1(SharedPreferences sharedPreferences, C0070r2 r2Var, String str) {
        this.a = sharedPreferences;
        this.b = r2Var;
        this.c = str;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null || this.b.b) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String str = this.c;
        editorEdit.putBoolean(str, z).apply();
        AbstractC0036j.i.post(new RunnableC0028h(0));
        B2.d();
        Log.i("SoulHook102", "setting changed: " + str + "=" + z);
    }
}
