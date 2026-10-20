/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import java.io.File;
import java.io.FilenameFilter;

public final class f3
implements FilenameFilter {
    @Override
    public final boolean accept(File file, String string) {
        return string.endsWith(".meta");
    }
}

