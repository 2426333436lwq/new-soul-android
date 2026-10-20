/** Minimal android.app stubs for compile-verification only. */
package android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity extends Context {
    public boolean isDestroyed() { return false; }

    public boolean isFinishing() { return false; }

    public void finish() {}

    public Application getApplication() { return null; }

    public Intent getIntent() { return null; }

    public Window getWindow() { return null; }

    public <T extends View> T findViewById(int id) { return null; }

    public void runOnUiThread(Runnable r) {}

    public void setContentView(View v) {}
}
