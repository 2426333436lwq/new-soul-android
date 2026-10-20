package android.content;

public interface DialogInterface {
    void dismiss();

    interface OnClickListener {
        void onClick(DialogInterface dialog, int which);
    }

    interface OnDismissListener {
        void onDismiss(DialogInterface dialog);
    }

    interface OnCancelListener {
        void onCancel(DialogInterface dialog);
    }
}
