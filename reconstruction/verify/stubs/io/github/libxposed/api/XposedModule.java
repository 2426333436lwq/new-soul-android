/**
 * Android + LSPosed API stubs, used ONLY to compile-verify the module sources
 * with plain javac on a machine that has no Android SDK.
 *
 * These are NOT the real APIs. They carry just enough signature surface for
 * dev.local.newsoul.* to type-check. The real build uses
 * io.github.libxposed:api from LSPosed and android.jar from the SDK.
 *
 * Do not ship this file in a real APK.
 */
package io.github.libxposed.api;

public class XposedModule {

    public String getFrameworkName() { return null; }

    public String getFrameworkVersion() { return null; }

    public int getApiVersion() { return 0; }

    public void log(int level, String tag, String message) {}

    public void log(int level, String tag, String message, Throwable throwable) {}

    public boolean deoptimize(java.lang.reflect.Executable executable) { return true; }

    public XposedInterface.HookBuilder hook(java.lang.reflect.Executable executable) {
        return null;
    }

    public void invokeOrigin(java.lang.reflect.Executable executable, Object receiver,
                             Object... args) {}
}
