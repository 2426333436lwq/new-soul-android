package android.content.res;

public class Resources {
    public int getIdentifier(String name, String defType, String defPackage) { return 0; }

    public String getString(int resId) { return null; }

    public android.graphics.drawable.Drawable getDrawable(int resId) { return null; }

    public android.graphics.drawable.Drawable getDrawable(int resId, Theme theme) {
        return null;
    }

    public DisplayMetrics getDisplayMetrics() { return null; }

    public String getString(int resId, Object... formatArgs) { return null; }

    public static class Theme {}

    public static class DisplayMetrics {
        public float density = 1f;
        public int widthPixels = 0;
        public int heightPixels = 0;
    }
}
