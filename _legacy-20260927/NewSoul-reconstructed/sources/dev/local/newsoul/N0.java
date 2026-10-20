/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.pm.PackageInfo
 *  android.widget.TextView
 */
package dev.local.newsoul;

import android.content.pm.PackageInfo;
import android.widget.TextView;

public abstract class N0 {
    public static /* bridge */ /* synthetic */ long a(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }

    public static /* bridge */ /* synthetic */ void b(TextView textView) {
        textView.setAccessibilityHeading(true);
    }
}

