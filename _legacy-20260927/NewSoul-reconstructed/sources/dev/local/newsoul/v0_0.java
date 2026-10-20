/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.EditText
 */
package dev.local.newsoul;

import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import dev.local.newsoul.q2;
import dev.local.newsoul.y0_0;

/*
 * Renamed from dev.local.newsoul.v0
 */
public final class v0_0
implements CompoundButton.OnCheckedChangeListener {
    public final y0_0 a;
    public final q2 b;

    public /* synthetic */ v0_0(y0_0 y0_02, q2 q22) {
        this.a = y0_02;
        this.b = q22;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
        EditText editText = this.a.c;
        if (!bl) {
            editText.setError(null);
        }
        if (!bl && editText.hasFocus()) {
            compoundButton = (InputMethodManager)this.b.g.getSystemService("input_method");
            if (compoundButton != null) {
                compoundButton.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
            editText.clearFocus();
        }
    }
}

