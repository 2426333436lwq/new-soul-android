package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/E1 ====
// super: Ljava/lang/Object
// implements: Runnabl
// source: "SourceFile"

public final  class E1 extends Ljava/lang/Object implements Ljava/lang/Runnable {

    public final Activity a;
    public final View b;
    public final TextView c;

    public void super(TextView p0) {
      super();
      this.a = p1;
      this.b = p2;
      this.c = p3;
      return;
    }

    public final void run() {
      if (p0.a.isFinishing() != 0) goto :cond_7b;
      if (p0.a.isDestroyed() != 0) goto :cond_7b;
      if (p0.b.isAttachedToWindow() != 0) goto :cond_17;
      goto :goto_7b;
      :cond_17:
      if (p0.b.isShown() == 0) goto :cond_76;
      if ("设置".contentEquals(p0.c.getText()) == 0) goto :cond_76;
      if (Ldev/local/newsoul/Q0.a0(p0.b) == 0) goto :cond_76;
      if (compare(((double) Landroid/graphics/Color.luminance(p0.c.getCurrentTextColor())), 0x3fdccccccccccccdL    # 0.45) <= 0) goto :cond_45;
      goto :goto_46;
      :cond_45:
      :goto_46:
      if (0x1 == 0) goto :cond_4e;
      goto :goto_4f;
      :cond_4e:
      :goto_4f:
      if (0x1 == 0) goto :cond_52;
      goto :goto_53;
      :cond_52:
      :goto_53:
      new Ljava/lang/StringBuilder("(()=>{if(location.origin!==\\'https://app.soulapp.cn\\'||location.pathname!=\\'/setting/\\'||!document.body)return false;const want=\\'");
      new Ldev/local/newsoul/D1(0x1);
      Ldev/local/newsoul/Q0.a0(p0.b).evaluateJavascript(new Ljava/lang/StringBuilder("(()=>{if(location.origin!==\\'https://app.soulapp.cn\\'||location.pathname!=\\'/setting/\\'||!document.body)return false;const want=\\'").toString(), new Ldev/local/newsoul/D1(0x1));
      :cond_76:
      :cond_7b:
      :goto_7b:
      return;
    }

}