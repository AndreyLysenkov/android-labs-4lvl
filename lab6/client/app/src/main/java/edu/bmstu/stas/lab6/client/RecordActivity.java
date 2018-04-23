package edu.bmstu.stas.lab6.client;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecordActivity extends AppCompatActivity {

    public enum Mode {
        ADD,
        EDIT,
        DELETE
    }

    private Mode mode;

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

    public void applyMode() {
        int mode = R.string.activity_record_mode_label_placeholder;
        switch (this.mode) {
            case DELETE:
                mode = R.string.activity_record_mode_label_delete;
                makeInvisible(R.id.activity_record_form_manufacture);
                makeInvisible(R.id.activity_record_form_model);
                makeInvisible(R.id.activity_record_form_year);
                makeInvisible(R.id.activity_record_field_id_fixed);
                break;
            case EDIT:
                mode = R.string.activity_record_mode_label_edit;
                this.makeEditDialog();
                // break; not
            case ADD:
                mode = R.string.activity_record_mode_label_add;
                makeInvisible(R.id.activity_record_field_id_editable); // TODO; placeholders to add;
                break;
        }

        TextView text = findViewById(R.id.activity_record_mode_label);
        text.setText(mode);
    }

    public void setEditRecordId(String id) {
        TextView view = findViewById(R.id.activity_record_field_id_fixed);
        view.setText(id);
    }

    // https://developer.android.com/guide/topics/ui/dialogs.html
    public void makeEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RecordActivity.this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.activity_record_edit_dialog, null, false);


        final EditText input = (EditText) view.findViewById(R.id.activity_record_dialog_edit_input);

        builder.setView(view);
        builder.setMessage(R.string.activity_record_field_id_editable_placeholder) // TODO;
                .setTitle(R.string.activity_record_field_id_editable_placeholder) // TODO;
                .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                RecordActivity.this.setEditRecordId(input.getText().toString());
            }
        });

        builder.create().show();
    }

    public void onAccept(View view) {
        // TODO;
    }
}
