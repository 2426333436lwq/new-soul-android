package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/f2 ====
// super: Landroid/view/View$AccessibilityDelegate
// source: "SourceFile"

public final  class f2 extends Landroid/view/View$AccessibilityDelegate {

    public final int a;
    public final int b;

    public void super(II p0) {
      super();
      this.a = p2;
      this.b = p1;
      return;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent p0) {
      switch (p0.a) { /* :pswitch_data_1e */ }
      super.onInitializeAccessibilityEvent(p1, p2);
      return;
      :pswitch_9:
      super.onInitializeAccessibilityEvent(p1, p2);
      p2.setClassName(RadioButton.class.getName());
      p2.setChecked(p1.isSelected());
      return;
      :pswitch_data_1e:

      // .packed-switch 0x1
      // :pswitch_9
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo p0) {
      switch (p0.a) { /* :pswitch_data_46 */ }
      super.onInitializeAccessibilityNodeInfo(p1, p2);
      p2.setClassName(RadioButton.class.getName());
      p2.setCheckable(0x1);
      p2.setChecked(p1.isSelected());
      p2.setCollectionItemInfo(0x0.obtain(0x1, p0.b, 0x1, 0x0, p1.isSelected()));
      return;
      :pswitch_2e:
      super.onInitializeAccessibilityNodeInfo(p1, p2);
      p2.setClassName(RadioGroup.class.getName());
      p2.setCollectionInfo(Landroid/view/accessibility/AccessibilityNodeInfo$CollectionInfo.obtain(0x1, p0.b, 0x0, 0x1));
      return;
      :pswitch_data_46:

      // .packed-switch 0x0
      // :pswitch_2e
    }

}