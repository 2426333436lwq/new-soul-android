/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$AccessibilityDelegate
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.view.accessibility.AccessibilityNodeInfo$CollectionInfo
 *  android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo
 *  android.widget.RadioButton
 *  android.widget.RadioGroup
 */
package dev.local.newsoul;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*
 * Renamed from dev.local.newsoul.f2
 */
public final class f2_0
extends View.AccessibilityDelegate {
    public final int a;
    public final int b;

    public /* synthetic */ f2_0(int n, int n2) {
        this.a = n2;
        this.b = n;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.a) {
            default: {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                return;
            }
            case 1: 
        }
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)RadioButton.class.getName());
        accessibilityEvent.setChecked(view.isSelected());
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        switch (this.a) {
            default: {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName((CharSequence)RadioButton.class.getName());
                accessibilityNodeInfo.setCheckable(true);
                accessibilityNodeInfo.setChecked(view.isSelected());
                boolean bl = view.isSelected();
                accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain((int)0, (int)1, (int)this.b, (int)1, (boolean)false, (boolean)bl));
                return;
            }
            case 0: 
        }
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName((CharSequence)RadioGroup.class.getName());
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain((int)1, (int)this.b, (boolean)false, (int)1));
    }
}

