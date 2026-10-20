/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.Insets
 *  android.graphics.RecordingCanvas
 *  android.graphics.RenderNode
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.StateListDrawable
 */
package dev.local.newsoul;

import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public abstract class H0 {
    public static /* bridge */ /* synthetic */ int a(Insets insets) {
        return insets.top;
    }

    public static /* bridge */ /* synthetic */ int b(StateListDrawable stateListDrawable) {
        return stateListDrawable.getStateCount();
    }

    public static /* bridge */ /* synthetic */ RecordingCanvas c(RenderNode renderNode, int n, int n2) {
        return renderNode.beginRecording(n, n2);
    }

    public static /* synthetic */ RenderNode d() {
        return new RenderNode("NewSoul glass backdrop");
    }

    public static /* bridge */ /* synthetic */ Drawable e(StateListDrawable stateListDrawable, int n) {
        return stateListDrawable.getStateDrawable(n);
    }

    public static /* bridge */ /* synthetic */ void f(Canvas canvas, RenderNode renderNode) {
        canvas.drawRenderNode(renderNode);
    }

    public static /* bridge */ /* synthetic */ void g(RenderNode renderNode) {
        renderNode.discardDisplayList();
    }

    public static /* bridge */ /* synthetic */ void h(RenderNode renderNode, int n, int n2) {
        renderNode.setPosition(0, 0, n, n2);
    }

    public static /* bridge */ /* synthetic */ int i(Insets insets) {
        return insets.bottom;
    }

    public static /* bridge */ /* synthetic */ void j(RenderNode renderNode) {
        renderNode.endRecording();
    }
}

