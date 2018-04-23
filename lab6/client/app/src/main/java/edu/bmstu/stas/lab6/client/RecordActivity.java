package edu.bmstu.stas.lab6.client;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class RecordActivity extends AppCompatActivity
    implements EditDialog.Listener {

    public enum Mode {
        ADD,
        EDIT,
        DELETE
    }

    private Mode mode;

    private int editRecordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Bundle extras = getIntent().getExtras();

        this.mode = (Mode) extras.getSerializable("mode");

        this.applyMode();
    }

    private void makeInvisible(int id) {
        View view = findViewById(id);
        view.setVisibility(View.GONE);
    }

    private void makeVisible(int id) {
        View view = findViewById(id);
        view.setVisibility(View.VISIBLE);
    }

    public void applyMode() {
        switch (this.mode) {
            case DELETE:
                makeInvisible(R.id.activity_record_form_manufacture);
                makeInvisible(R.id.activity_record_form_model);
                makeInvisible(R.id.activity_record_form_year);
                makeInvisible(R.id.activity_record_field_id_fixed);
                break;
            case EDIT:
                this.makeEditDialog();
            case ADD:
                makeInvisible(R.id.activity_record_field_id_editable);
                break;
        }
    }

    // https://developer.android.com/guide/topics/ui/dialogs.html
    public void makeEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RecordActivity.this);

        LayoutInflater inflater = RecordActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_record_edit_dialog, null))
                .setMessage(R.string.activity_record_field_id_editable_placeholder) // TODO;
                .setTitle(R.string.activity_record_field_id_editable_placeholder) // TODO;
                .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditText view = findViewById(R.id.activity_record_dialog_edit); // TODO;
                editRecordId = Integer.getInteger(view.getText().toString());
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void showEditDialog() {
        DialogFragment dialog = new EditDialog();
        dialog.show(getFragmentManager(), "edit_dialog");
    }

    public void onEditDialogAccept(DialogFragment dialog) {
        // TODO;

    }

    public void onEditDialogCancel(DialogFragment dialog) {
        // TODO;
    }


    public void onAccept(View view) {
        // TODO;
    }
}
