package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/r0 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class r0 extends Ljava/lang/Object {

    public static final WeakHashMap a;
    public static final WeakHashMap b;

    static void static {}() {
      new Ljava/util/WeakHashMap();
      r0.a = new Ljava/util/WeakHashMap();
      new Ljava/util/WeakHashMap();
      r0.b = new Ljava/util/WeakHashMap();
      return;
    }

    public static void a(Landroid/widget/TextView;Z p0) {
      new Ljava/lang/Object();
      if (p1 != 0) goto :cond_2d;
      if ((q0) r0.a.remove(p0) == 0) goto :cond_2c;
      if ((View) (q0) r0.a.remove(p0).b.get() == 0) goto :cond_2c;
      (View) (q0) r0.a.remove(p0).b.get().setOnLongClickListener(0x0);
      (View) (q0) r0.a.remove(p0).b.get().setLongClickable((q0) r0.a.remove(p0).c);
      :cond_2c:
      return;
      :cond_2d:
      new Ldev/local/newsoul/g((q0) r0.a.remove(p0), new Ljava/lang/Object(), 0x2);
      return;
    }

}