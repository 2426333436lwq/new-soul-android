package android.content;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.view.View;

public class Context {

    public static final int MODE_PRIVATE = 0;

    public Context getApplicationContext() { return this; }

    public SharedPreferences getSharedPreferences(String name, int mode) { return null; }

    public Resources getResources() { return null; }

    public String getPackageName() { return null; }

    public Object getSystemService(String name) { return null; }

    public <T> T getSystemService(Class<T> cls) { return null; }

    public Context createPackageContext(String pkg, int flags)
            throws android.content.pm.PackageManager.NameNotFoundException { return null; }

    public android.content.pm.PackageManager getPackageManager() { return null; }

    public Context createConfigurationContext(
            android.content.res.Configuration c) { return null; }

    public int checkPermission(String perm, int pid, int uid) { return 0; }
}
