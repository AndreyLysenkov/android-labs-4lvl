package edu.bmstu.stas.lab6.client;

import android.app.Activity;
import android.app.DialogFragment;

public class EditDialog extends DialogFragment {

    public interface Listener {
        public void onEditDialogAccept(DialogFragment dialog);

        public void onEditDialogCancel(DialogFragment dialog);
    }

    Listener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (Listener) activity;
    }

}