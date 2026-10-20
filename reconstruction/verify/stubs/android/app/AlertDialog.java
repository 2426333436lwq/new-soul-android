package android.app;

import android.content.Context;
import android.view.View;

public class AlertDialog {
    public void dismiss() {}

    public Context getContext() { return null; }

    public android.widget.Button getButton(int whichButton) { return null; }

    public void setOnDismissListener(OnDismissListener l) {}

    public interface OnDismissListener {
        void onDismiss(android.content.DialogInterface dialog);
    }

    public static class Builder {
        public Builder(Context c) {}

        public Builder setTitle(CharSequence t) { return this; }

        public Builder setView(View v) { return this; }

        public Builder setMessage(CharSequence m) { return this; }

        public Builder setPositiveButton(CharSequence t,
                                         OnClickListener l) { return this; }

        public Builder setNegativeButton(CharSequence t,
                                         OnClickListener l) { return this; }

        public Builder setNeutralButton(CharSequence t,
                                        OnClickListener l) { return this; }

        public AlertDialog show() { return null; }

        public AlertDialog create() { return null; }

        public interface OnClickListener {
            void onClick(android.content.DialogInterface d, int which);
        }
    }
}
