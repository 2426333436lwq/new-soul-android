package android.content;

public class Intent {
    public String getStringExtra(String name) { return null; }

    public int getIntExtra(String name, int defaultValue) { return 0; }

    public boolean getBooleanExtra(String name, boolean defaultValue) { return false; }

    public android.net.Uri getData() { return null; }

    public Intent putExtra(String name, int value) { return this; }
}
