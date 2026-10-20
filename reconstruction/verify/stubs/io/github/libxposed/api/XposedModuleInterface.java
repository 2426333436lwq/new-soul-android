package io.github.libxposed.api;

public interface XposedModuleInterface {

    interface ModuleLoadedParam {
        String getProcessName();

        boolean isSystemServer();
    }

    interface PackageReadyParam {
        java.lang.ClassLoader getClassLoader();

        boolean isFirstPackage();

        String getPackageName();
    }
}
