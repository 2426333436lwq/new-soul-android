package dev.local.newsoul;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.RuntimeShader;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public final class Y {
    public final Object a;
    public final Object b;

    public Y(File file, PublicKey publicKey) throws IOException {
        this.a = file;
        this.b = publicKey;
        if (!file.isDirectory() && !file.mkdirs()) {
            throw new IOException("Storage unavailable");
        }
        File[] fileArrListFiles = file.listFiles();
        for (File file2 : fileArrListFiles == null ? new File[0] : fileArrListFiles) {
            if (file2.getName().endsWith(".tmp")) {
                file2.delete();
            }
        }
    }

    public void a(Canvas canvas, int i, int i2, RectF rectF, float f, float f2, float f3, boolean z, U2 u2) {
        ((RenderNode) this.a).setPosition(0, 0, i, i2);
        try {
            u2.a(((RenderNode) this.a).beginRecording(i, i2));
            ((RenderNode) this.a).endRecording();
            ((RuntimeShader) this.b).setFloatUniform("center", rectF.centerX(), rectF.centerY());
            ((RuntimeShader) this.b).setFloatUniform("halfSize", rectF.width() / 2.0f, rectF.height() / 2.0f);
            ((RuntimeShader) this.b).setFloatUniform("sourceSize", i, i2);
            ((RuntimeShader) this.b).setFloatUniform("radius", Math.min(f, Math.min(rectF.width(), rectF.height()) / 2.0f));
            ((RuntimeShader) this.b).setFloatUniform("strength", f2);
            ((RuntimeShader) this.b).setFloatUniform("density", f3);
            ((RuntimeShader) this.b).setFloatUniform("dark", z ? 1.0f : 0.0f);
            ((RenderNode) this.a).setRenderEffect(RenderEffect.createRuntimeShaderEffect((RuntimeShader) this.b, "content"));
            canvas.drawRenderNode((RenderNode) this.a);
        } catch (Throwable th) {
            ((RenderNode) this.a).endRecording();
            throw th;
        }
    }

    public long b() {
        File[] fileArrListFiles = ((File) this.a).listFiles();
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        long length = 0;
        for (File file : fileArrListFiles) {
            length += file.length();
        }
        return length;
    }

    public Y(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public Y() {
        this.a = H0.d();
        this.b = I0.b();
    }
}
