package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/q0 ====
// super: Ljava/lang/Object
// implements: View$OnLongClickListene
// source: "SourceFile"

public final  class q0 extends Ljava/lang/Object implements Landroid/view/View$OnLongClickListener {

    public final WeakReference a;
    public final WeakReference b;
    public final boolean c;

    public void super(View p0) {
      super();
      new Ljava/lang/ref/WeakReference(p1);
      this.a = new Ljava/lang/ref/WeakReference(p1);
      new Ljava/lang/ref/WeakReference(p2);
      this.b = new Ljava/lang/ref/WeakReference(p2);
      this.c = p2.isLongClickable();
      return;
    }

    public final boolean onLongClick(View p0) {
      if ((TextView) p0.a.get() == 0) goto :cond_65;
      if (r0.a.get((TextView) p0.a.get()) != p0) goto :cond_65;
      if ((TextView) p0.a.get().isShown() == 0) goto :cond_65;
      if ((TextView) p0.a.get().isAttachedToWindow() == 0) goto :cond_65;
      if (p1.isEnabled() != 0) goto :cond_26;
      goto :goto_65;
      :cond_26:
      :goto_2b:
      if (p1.isEnabled() >= 0xc) goto :cond_4d;
      if (p1 == 0) goto :cond_4d;
      if ((p1 instanceof Activity) == 0) goto :cond_3a;
      goto :goto_4d;
      :cond_3a:
      if ((p1 instanceof ContextWrapper) != 0) goto :cond_3f;
      goto :goto_4d;
      :cond_3f:
      if ((ContextWrapper) p1.getBaseContext() != p1) goto :cond_49;
      goto :goto_4d;
      :cond_49:
      goto :goto_2b;
      :cond_4d:
      :goto_4d:
      if ((Activity) p1 == 0) goto :cond_65;
      if ((Activity) p1.isFinishing() != 0) goto :cond_65;
      if ((Activity) p1.isDestroyed() == 0) goto :cond_5c;
      goto :goto_65;
      :cond_5c:
      Ldev/local/newsoul/z0.a((Activity) p1, Ldev/local/newsoul/o.b((Activity) p1));
      return (p1.isEnabled() + 0x1);
      :cond_65:
      :goto_65:
      return (p1.isEnabled() + 0x1);
    }

}