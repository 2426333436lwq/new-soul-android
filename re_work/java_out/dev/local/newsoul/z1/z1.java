package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/z1 ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class z1 extends Ljava/lang/Object {

    public static final WeakHashMap a;

    static void static {}() {
      new Ljava/util/WeakHashMap();
      z1.a = new Ljava/util/WeakHashMap();
      return;
    }

    public static void a(String p0) {
      Ldev/local/newsoul/z1.b(p0);
      if (Ldev/local/newsoul/z1.e(p0, "container") == 0) goto :cond_143;
      if ((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename") == 0) goto :cond_143;
      if ((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename_flag") != 0) goto :cond_21;
      goto :goto_143;
      :cond_21:
      new Ljava/lang/Object();
      if ("nickname".equals(p1) == 0) goto :cond_93;
      new Ljava/lang/ref/WeakReference((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename"));
      new Ljava/lang/Object().c = new Ljava/lang/ref/WeakReference((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename"));
      new Ljava/lang/ref/WeakReference((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename_flag"));
      new Ljava/lang/Object().d = new Ljava/lang/ref/WeakReference((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename_flag"));
      new Ljava/lang/Object().h = (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename").getText();
      new Ljava/lang/Object().i = (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename_flag").getText();
      new Ljava/lang/Object().f = (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename").getVisibility();
      new Ljava/lang/Object().g = (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename_flag").getVisibility();
      if ((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename").getVisibility() != 0) goto :cond_6a;
      if ((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename").getText().length() != 0) goto :cond_6d;
      :cond_6a:
      (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename").setText(p2);
      :cond_6d:
      (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename").setVisibility(0x0);
      if (new Ljava/lang/Object().g != 0) goto :cond_89;
      if (new Ljava/lang/Object().i.length() <= 0) goto :cond_89;
      goto :goto_8b;
      :cond_89:
      :goto_8b:
      (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename_flag").setText(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回"));
      (TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename_flag").setVisibility(0x0);
      goto :goto_143;
      :cond_93:
      new Ljava/lang/Object().e = Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getField("bottomToBottom").getInt(Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams());
      new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext());
      new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()).setId(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext());
      new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()).setText("对方已撤回 · 已保留");
      new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()).setTextSize(0x41200000    # 10.0f);
      new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()).setTextColor((TextView) Ldev/local/newsoul/z1.e(p0, "tv_nikename").getTextColors());
      new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()).setImportantForAccessibility(0x1);
      Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getField("topToBottom").setInt((ViewGroup$MarginLayoutParams) Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getConstructor(new Class[][] {Integer.TYPE, Integer.TYPE}).newInstance(new Object[][] {Ljava/lang/Integer.valueOf(-0x2), Ljava/lang/Integer.valueOf(-0x2)}), Ldev/local/newsoul/z1.e(p0, "container").getId());
      Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getField("startToStart").setInt((ViewGroup$MarginLayoutParams) Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getConstructor(new Class[][] {Integer.TYPE, Integer.TYPE}).newInstance(new Object[][] {Ljava/lang/Integer.valueOf(-0x2), Ljava/lang/Integer.valueOf(-0x2)}), Ldev/local/newsoul/z1.e(p0, "container").getId());
      Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getField("bottomToBottom").setInt((ViewGroup$MarginLayoutParams) Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getConstructor(new Class[][] {Integer.TYPE, Integer.TYPE}).newInstance(new Object[][] {Ljava/lang/Integer.valueOf(-0x2), Ljava/lang/Integer.valueOf(-0x2)}), 0x0);
      (ViewGroup$MarginLayoutParams) Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getConstructor(new Class[][] {Integer.TYPE, Integer.TYPE}).newInstance(new Object[][] {Ljava/lang/Integer.valueOf(-0x2), Ljava/lang/Integer.valueOf(-0x2)}).topMargin = Ldev/local/newsoul/z1.d(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext(), 0x2);
      (ViewGroup$MarginLayoutParams) Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getConstructor(new Class[][] {Integer.TYPE, Integer.TYPE}).newInstance(new Object[][] {Ljava/lang/Integer.valueOf(-0x2), Ljava/lang/Integer.valueOf(-0x2)}).bottomMargin = Ldev/local/newsoul/z1.d(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext(), 0x8);
      new Ljava/lang/ref/WeakReference(Ldev/local/newsoul/z1.e(p0, "container"));
      new Ljava/lang/Object().a = new Ljava/lang/ref/WeakReference(Ldev/local/newsoul/z1.e(p0, "container"));
      new Ljava/lang/ref/WeakReference(new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()));
      new Ljava/lang/Object().b = new Ljava/lang/ref/WeakReference(new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()));
      Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getField("bottomToBottom").setInt(Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams(), -0x1);
      Ldev/local/newsoul/z1.e(p0, "container").setLayoutParams(Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams());
      (ViewGroup) Ldev/local/newsoul/z1.e(p0, "container").getParent().addView(new Landroid/widget/TextView(Ljava/lang/String.valueOf(new Ljava/lang/Object().i).concat(" · 已撤回").getContext()), (ViewGroup$MarginLayoutParams) Ldev/local/newsoul/z1.e(p0, "container").getLayoutParams().getClass().getConstructor(new Class[][] {Integer.TYPE, Integer.TYPE}).newInstance(new Object[][] {Ljava/lang/Integer.valueOf(-0x2), Ljava/lang/Integer.valueOf(-0x2)}));
      :cond_143:
      :goto_143:
      return;
    }

    public static void b(View p0) {
      if ((y1) z1.a.remove(p0) != 0) goto :cond_b;
      return;
      :cond_b:
      :try_start_b:
      if ((y1) z1.a.remove(p0).b == 0) goto :cond_4b;
      if ((View) (y1) z1.a.remove(p0).b.get() == 0) goto :cond_33;
      if (((View) (y1) z1.a.remove(p0).b.get().getParent() instanceof ViewGroup) == 0) goto :cond_33;
      (ViewGroup) (View) (y1) z1.a.remove(p0).b.get().getParent().removeView((View) (y1) z1.a.remove(p0).b.get());
      goto :goto_33;
      :catchall_31:
      goto :goto_76;
      :cond_33:
      :goto_33:
      if ((View) (y1) z1.a.remove(p0).a.get() == 0) goto :cond_4b;
      (View) (y1) z1.a.remove(p0).a.get().getLayoutParams().getClass().getField("bottomToBottom").setInt((View) (y1) z1.a.remove(p0).a.get().getLayoutParams(), e.e);
      (View) (y1) z1.a.remove(p0).a.get().setLayoutParams((View) (y1) z1.a.remove(p0).a.get().getLayoutParams());
      :cond_4b:
      if (e.c == 0) goto :cond_7d;
      if ((TextView) e.c.get() == 0) goto :cond_69;
      (TextView) e.c.get().setText(e.h);
      (TextView) e.c.get().setVisibility(e.f);
      :cond_69:
      if ((TextView) e.d.get() == 0) goto :cond_7d;
      (TextView) e.d.get().setText(e.i);
      (TextView) e.d.get().setVisibility(e.g);
      :try_end_75:
      goto :goto_7d;
      :goto_76:
      :cond_7d:
      :goto_7d:
      return;

      // .catchall {:try_start_b .. :try_end_75} :catchall_31
    }

    public static void c(View p0) {
      p0.setClickable(0x0);
      p0.setLongClickable(0x0);
      p0.setFocusable(0x0);
      if ((p0 instanceof ViewGroup) == 0) goto :cond_20;
      :goto_10:
      if (0x0 >= p0.getChildCount()) goto :cond_20;
      Ldev/local/newsoul/z1.c(p0.getChildAt(0x0));
      goto :goto_10;
      :cond_20:
      return;
    }

    public static int d(Landroid/content/Context;I p0) {
      return v0;
    }

    public static View e(String p0) {
      return p0.getContext();
    }

    public static View f(String p0) {
      new Landroid/widget/FrameLayout(p0);
      (TextView) Ldev/local/newsoul/z1.e(Landroid/view/LayoutInflater.from(p0).inflate(Ldev/local/newsoul/z1.g(p0, "item_chat_message_receive", "layout"), new Landroid/widget/FrameLayout(p0), 0x0), "tv_nikename").setText("示例好友");
      (TextView) Ldev/local/newsoul/z1.e(Landroid/view/LayoutInflater.from(p0).inflate(Ldev/local/newsoul/z1.g(p0, "item_chat_message_receive", "layout"), new Landroid/widget/FrameLayout(p0), 0x0), "tv_nikename").setVisibility(0x0);
      (TextView) Ldev/local/newsoul/z1.e(Landroid/view/LayoutInflater.from(p0).inflate(Ldev/local/newsoul/z1.g(p0, "item_chat_message_receive", "layout"), new Landroid/widget/FrameLayout(p0), 0x0), "tv_nikename_flag").setVisibility(0x8);
      new Landroid/widget/TextView(p0);
      new Landroid/widget/TextView(p0).setText("这是一条已撤回的消息");
      new Landroid/widget/TextView(p0).setTextSize(0x41600000    # 14.0f);
      new Landroid/widget/TextView(p0).setTextColor(p0.getResources().getColor(Ldev/local/newsoul/z1.g(p0, "color_s_02", "color"), p0.getTheme()));
      new Landroid/widget/TextView(p0).setBackgroundResource(Ldev/local/newsoul/z1.g(p0, "icon_bubble_white", "drawable"));
      new Landroid/widget/TextView(p0).setPadding(Ldev/local/newsoul/z1.d(p0, 0xc), Ldev/local/newsoul/z1.d(p0, 0xa), Ldev/local/newsoul/z1.d(p0, 0xc), Ldev/local/newsoul/z1.d(p0, 0xa));
      new Landroid/widget/FrameLayout$LayoutParams(-0x2, -0x2);
      (FrameLayout) Ldev/local/newsoul/z1.e(Landroid/view/LayoutInflater.from(p0).inflate(Ldev/local/newsoul/z1.g(p0, "item_chat_message_receive", "layout"), new Landroid/widget/FrameLayout(p0), 0x0), "container").addView(new Landroid/widget/TextView(p0), new Landroid/widget/FrameLayout$LayoutParams(-0x2, -0x2));
      Ldev/local/newsoul/z1.c(Landroid/view/LayoutInflater.from(p0).inflate(Ldev/local/newsoul/z1.g(p0, "item_chat_message_receive", "layout"), new Landroid/widget/FrameLayout(p0), 0x0));
      Ldev/local/newsoul/z1.a(Landroid/view/LayoutInflater.from(p0).inflate(Ldev/local/newsoul/z1.g(p0, "item_chat_message_receive", "layout"), new Landroid/widget/FrameLayout(p0), 0x0), p1, "示例好友");
      return Landroid/view/LayoutInflater.from(p0).inflate(Ldev/local/newsoul/z1.g(p0, "item_chat_message_receive", "layout"), new Landroid/widget/FrameLayout(p0), 0x0);
    }

    public static int g(String p0) {
      if (p0.getResources().getIdentifier(p1, p2, "cn.soulapp.android") == 0) goto :cond_d;
      return "cn.soulapp.android";
      :cond_d:
      new Ljava/lang/IllegalStateException("Missing Soul resource: ".concat(p1));
      throw new Ljava/lang/IllegalStateException("Missing Soul resource: ".concat(p1));
    }

}