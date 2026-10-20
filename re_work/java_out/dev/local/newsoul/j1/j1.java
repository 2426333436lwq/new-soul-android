package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/j1 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class j1 extends Ljava/lang/Object {

    public static final WeakHashMap a;

    static void static {}() {
      new Ljava/util/WeakHashMap();
      j1.a = new Ljava/util/WeakHashMap();
      return;
    }

    public static ImageView a(View p0) {
      if ((p0 instanceof ImageView) == 0) goto :cond_7;
      return (p0 instanceof ImageView);
      :cond_7:
      if ((p0 instanceof ViewGroup) == 0) goto :cond_22;
      :goto_e:
      if ((p0 instanceof ViewGroup) >= p0.getChildCount()) goto :cond_22;
      if (Ldev/local/newsoul/j1.a(p0.getChildAt((p0 instanceof ViewGroup))) == 0) goto :cond_1f;
      return (p0 instanceof ViewGroup);
      :cond_1f:
      goto :goto_e;
      :cond_22:
      return ((p0 instanceof ViewGroup) + 0x1);
    }

    public static void b(Landroid/view/View;Z p0) {
      if (p0 != 0) goto :cond_3;
      return;
      :cond_3:
      if (p1 != 0) goto :cond_1a;
      if ((i1) j1.a.get(p0) == 0) goto :cond_19;
      p0.getOverlay().remove((i1) j1.a.get(p0));
      (i1) j1.a.get(p0).d = 0x0;
      :cond_19:
      return;
      :cond_1a:
      if ((i1) j1.a.get(p0) == 0) goto :cond_2b;
      if ((i1) j1.a.get(p0).d != 0) goto :cond_2a;
      p0.getOverlay().add((i1) j1.a.get(p0));
      (i1) j1.a.get(p0).d = 0x1;
      :cond_2a:
      return;
      :cond_2b:
      new Ldev/local/newsoul/i1(p0.getResources().getDisplayMetrics().density);
      p0.getOverlay().add(new Ldev/local/newsoul/i1(p0.getResources().getDisplayMetrics().density));
      new Ldev/local/newsoul/i1(p0.getResources().getDisplayMetrics().density).setBounds(0x0, 0x0, p0.getWidth(), p0.getHeight());
      new Ljava/lang/Object();
      p0.addOnLayoutChangeListener(new Ljava/lang/Object());
      p0.invalidate();
      return;
    }

}