package android.view;

public class View {
    public static final int VISIBLE = 0;
    public static final int INVISIBLE = 4;
    public static final int GONE = 8;

    public View() {}

    public View(android.content.Context c) {}

    public View(android.content.Context c, android.util.AttributeSet a) {}

    public void setVisibility(int visibility) {}

    public int getVisibility() { return VISIBLE; }

    public void setEnabled(boolean enabled) {}

    public boolean isEnabled() { return true; }

    public void setClickable(boolean clickable) {}

    public boolean isClickable() { return false; }

    public void setAlpha(float alpha) {}

    public float getAlpha() { return 1f; }

    public int getId() { return 0; }

    public void setId(int id) {}

    public void setTag(Object tag) {}

    public Object getTag() { return null; }

    public void setTag(int key, Object tag) {}

    public Object getTag(int key) { return null; }

    public void setContentDescription(CharSequence cd) {}

    public CharSequence getContentDescription() { return null; }

    public ViewParent getParent() { return null; }

    public ViewGroup.LayoutParams getLayoutParams() { return null; }

    public void setLayoutParams(ViewGroup.LayoutParams p) {}

    public void setOnClickListener(OnClickListener l) {}

    public void setOnLongClickListener(OnLongClickListener l) {}

    public boolean performClick() { return false; }

    public boolean post(Runnable action) { return true; }

    public boolean postDelayed(Runnable action, long delay) { return true; }

    public ViewTreeObserver getViewTreeObserver() { return null; }

    public boolean isAttachedToWindow() { return false; }

    public void addOnAttachStateChangeListener(OnAttachStateChangeListener l) {}

    public void addOnLayoutChangeListener(OnLayoutChangeListener l) {}

    public android.content.Context getContext() { return null; }

    public int getWidth() { return 0; }

    public int getHeight() { return 0; }

    public void setPadding(int l, int t, int r, int b) {}

    public void measure(int w, int h) {}

    public int getMeasuredWidth() { return 0; }

    public int getMeasuredHeight() { return 0; }

    public void layout(int l, int t, int r, int b) {}

    public void invalidate() {}

    public interface OnClickListener {
        void onClick(View v);
    }

    public interface OnLongClickListener {
        boolean onLongClick(View v);
    }

    public interface OnAttachStateChangeListener {
        default void onViewAttachedToWindow(View v) {}

        default void onViewDetachedFromWindow(View v) {}
    }

    public interface OnLayoutChangeListener {
        void onLayoutChange(View v, int l, int t, int r, int b,
                            int ol, int ot, int or, int ob);
    }

    public interface OnFocusChangeListener {
        void onFocusChange(View v, boolean hasFocus);
    }

    public static class AccessibilityDelegate {}
}
