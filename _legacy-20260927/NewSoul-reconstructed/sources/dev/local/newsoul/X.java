/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import java.io.File;
import java.util.function.Function;

public final class X
implements Function {
    public final Object apply(Object object) {
        return ((File)object).getName();
    }
}

