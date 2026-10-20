/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.RecordingCanvas
 *  android.graphics.RectF
 *  android.graphics.RenderNode
 *  android.graphics.RuntimeShader
 */
package dev.local.newsoul;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.RuntimeShader;
import dev.local.newsoul.H0;
import dev.local.newsoul.I0;
import dev.local.newsoul.J0;
import dev.local.newsoul.u2_0;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

public final class Y {
    public final Object a;
    public final Object b;

    public Y() {
        this.a = H0.d();
        this.b = I0.b();
    }

    public Y(File fileArray, PublicKey object) {
        this.a = fileArray;
        this.b = object;
        if (!fileArray.isDirectory() && !fileArray.mkdirs()) {
            throw new IOException("Storage unavailable");
        }
        object = fileArray.listFiles();
        int n = 0;
        fileArray = object;
        if (object == null) {
            fileArray = new File[]{};
        }
        int n2 = fileArray.length;
        while (n < n2) {
            object = fileArray[n];
            if (((File)object).getName().endsWith(".tmp")) {
                ((File)object).delete();
            }
            ++n;
        }
    }

    public Y(String string, String string2) {
        this.a = string;
        this.b = string2;
    }

    public void a(Canvas canvas, int n, int n2, RectF rectF, float f, float f2, float f4, boolean bl, u2_0 u2_02) {
        H0.h((RenderNode)this.a, n, n2);
        RecordingCanvas recordingCanvas = H0.c((RenderNode)this.a, n, n2);
        u2_02.a((Canvas)recordingCanvas);
        I0.e((RuntimeShader)this.b, rectF.centerX(), rectF.centerY());
        I0.h((RuntimeShader)this.b, rectF.width() / 2.0f, rectF.height() / 2.0f);
        I0.k((RuntimeShader)this.b, n, n2);
        I0.d((RuntimeShader)this.b, Math.min(f, Math.min(rectF.width(), rectF.height()) / 2.0f));
        I0.g((RuntimeShader)this.b, f2);
        I0.j((RuntimeShader)this.b, f4);
        rectF = (RuntimeShader)this.b;
        f = bl ? 1.0f : 0.0f;
        I0.l((RuntimeShader)rectF, f);
        J0.a((RenderNode)this.a, I0.a((RuntimeShader)this.b));
        H0.f(canvas, (RenderNode)this.a);
        return;
        finally {
            H0.j((RenderNode)this.a);
        }
    }

    public long b() {
        File[] fileArray = ((File)this.a).listFiles();
        int n = 0;
        File[] fileArray2 = fileArray;
        if (fileArray == null) {
            fileArray2 = new File[]{};
        }
        int n2 = fileArray2.length;
        long l = 0L;
        while (n < n2) {
            l += fileArray2[n].length();
            ++n;
        }
        return l;
    }
}

