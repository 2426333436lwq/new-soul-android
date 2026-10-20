package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/a3 ====
// super: Ljava/lang/Object
// implements: TextWatche
// source: "SourceFile"

public final  class a3 extends Ljava/lang/Object implements Landroid/text/TextWatcher {

    public final q2 a;
    public final LinearLayout b;
    public final List c;
    public final b3 d;

    public void super(ArrayList p0) {
      super();
      this.d = p1;
      this.a = p2;
      this.b = p3;
      this.c = p4;
      return;
    }

    public final void afterTextChanged(Editable p0) {
      return;
    }

    public final void beforeTextChanged(Ljava/lang/CharSequence;III p0) {
      return;
    }

    public final void onTextChanged(Ljava/lang/CharSequence;III p0) {
      if (p0.d.m != 0) goto :cond_32;
      if (p0.d.h == p0.a) goto :cond_d;
      goto :goto_32;
      :cond_d:
      p0.d.k = p1.toString();
      p0.d.j = 0x0;
      p0.d.h(p0.a, p0.b, (ArrayList) p0.c);
      if ((p0.a.h.getParent() instanceof ScrollView) == 0) goto :cond_32;
      (ScrollView) p0.a.h.getParent().scrollTo(0x0, 0x0);
      :cond_32:
      :goto_32:
      return;
    }

}