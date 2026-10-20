package android.view;

public class ViewGroup extends View {
    public int getChildCount() { return 0; }

    public View getChildAt(int i) { return null; }

    public void addView(View child) {}

    public void addView(View child, int index) {}

    public void addView(View child, LayoutParams p) {}

    public void removeView(View v) {}

    public void removeAllViews() {}

    public static class LayoutParams {
        public static final int MATCH_PARENT = -1;
        public static final int WRAP_CONTENT = -2;

        public int width;
        public int height;

        public LayoutParams(int w, int h) {
            width = w;
            height = h;
        }

        public LayoutParams(LayoutParams o) {
            width = o.width;
            height = o.height;
        }
    }

    public static class MarginLayoutParams extends LayoutParams {
        public int leftMargin;
        public int topMargin;
        public int rightMargin;
        public int bottomMargin;

        public MarginLayoutParams(int w, int h) { super(w, h); }

        public MarginLayoutParams(MarginLayoutParams o) { super(o); }
    }
}
