package dev.local.newsoul;

import android.graphics.RuntimeShader;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class I0 {
    public static /* synthetic */ RuntimeShader b() {
        return new RuntimeShader("uniform shader content;\nuniform float2 center;\nuniform float2 halfSize;\nuniform float2 sourceSize;\nuniform float radius;\nuniform float strength;\nuniform float density;\nuniform float dark;\nhalf4 main(float2 p) {\n    float2 local = p-center;\n    float2 corner = abs(local)-halfSize+radius;\n    float2 outside = max(corner, 0.0);\n    float sd = length(outside)+min(max(corner.x,corner.y),0.0)-radius;\n    float2 n;\n    if (length(outside)>0.001) n = sign(local)*normalize(outside);\n    else n = abs(corner.x-corner.y)<0.001 ? float2(0.0,-1.0)\n            : sign(local)*(corner.x>corner.y ? float2(1.0,0.0) : float2(0.0,1.0));\n    float depth = max(-sd,0.0);\n    float rimWidth = min(min(halfSize.x,halfSize.y)*0.65, density*(4.5+strength*3.0));\n    float edge = 1.0-smoothstep(0.0,max(rimWidth,0.01),depth);\n    float curved = 1.0-sqrt(max(0.0,1.0-edge*edge));\n    float bend = curved*rimWidth*(0.35+strength*0.5);\n    float2 src = center+local/(1.0+strength*0.16)-n*bend;\n    float2 split = n*density*(0.10+strength*0.45)*edge;\n    float2 lo = float2(0.5), hi = sourceSize-float2(0.5);\n    half4 c = content.eval(clamp(src,lo,hi));\n    half4 r = content.eval(clamp(src+split,lo,hi));\n    half4 b = content.eval(clamp(src-split,lo,hi));\n    // content is premultiplied. Keep a clear film clear instead of turning\n    // transparent pixels into an opaque pastel plate during tint/highlight math.\n    half alpha = c.a;\n    half3 rgb = half3(r.r,c.g,b.b)/max(alpha,half(0.001));\n    // A thin transmission tint; highlights remain neutral rather than green.\n    rgb = mix(rgb,half3(0.46,0.86,0.79),half(0.018+dark*0.012));\n    float facing = dot(n,float2(-0.6,-0.8));\n    float front = pow(max(facing,0.0),4.0);\n    float back = pow(max(-facing,0.0),6.0);\n    float rim = 1.0-smoothstep(0.0,density*0.8,depth);\n    float inner = (1.0-smoothstep(density*0.8,density*2.8,depth))*(1.0-rim);\n    rgb *= half(1.0-inner*(0.035+0.025*strength));\n    half reflection = half(rim*(0.04+front*0.32+back*0.12));\n    return half4(mix(rgb*alpha,half3(1.0),reflection),alpha+(1.0-alpha)*reflection);\n}\n");
    }


    public static android.graphics.RenderEffect a(android.graphics.RuntimeShader p0) {
        return android.graphics.RenderEffect.createRuntimeShaderEffect(p0, "content");
    }

    public static android.window.OnBackInvokedDispatcher c(android.app.AlertDialog p0) {
        return p0.getOnBackInvokedDispatcher();
    }

    public static void d(android.graphics.RuntimeShader p0, float p1) {
        p0.setFloatUniform("radius", p1);
        return;
    }

    public static void e(android.graphics.RuntimeShader p0, float p1, float p2) {
        p0.setFloatUniform("center", p1, p2);
        return;
    }

    public static void f(android.window.OnBackInvokedDispatcher p0, dev.local.newsoul.k2 p1) {
        p0.registerOnBackInvokedCallback(0, p1);
        return;
    }

    public static void g(android.graphics.RuntimeShader p0, float p1) {
        p0.setFloatUniform("strength", p1);
        return;
    }

    public static void h(android.graphics.RuntimeShader p0, float p1, float p2) {
        p0.setFloatUniform("halfSize", p1, p2);
        return;
    }

    public static void i(android.window.OnBackInvokedDispatcher p0, dev.local.newsoul.k2 p1) {
        p0.unregisterOnBackInvokedCallback(p1);
        return;
    }

    public static void j(android.graphics.RuntimeShader p0, float p1) {
        p0.setFloatUniform("density", p1);
        return;
    }

    public static void k(android.graphics.RuntimeShader p0, float p1, float p2) {
        p0.setFloatUniform("sourceSize", p1, p2);
        return;
    }

    public static void l(android.graphics.RuntimeShader p0, float p1) {
        p0.setFloatUniform("dark", p1);
        return;
    }
}
