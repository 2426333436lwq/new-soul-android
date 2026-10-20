package android.app;

public interface ActivityLifecycleCallbacks {
    default void onActivityCreated(Activity a, android.os.Bundle b) {}
    default void onActivityStarted(Activity a) {}
    default void onActivityResumed(Activity a) {}
    default void onActivityPaused(Activity a) {}
    default void onActivityStopped(Activity a) {}
    default void onActivityDestroyed(Activity a) {}
}
