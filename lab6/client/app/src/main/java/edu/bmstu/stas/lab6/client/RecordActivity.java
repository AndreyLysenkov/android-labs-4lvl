package edu.bmstu.stas.lab6.client;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    public enum Mode {
        ADD,
        EDIT,
        DELETE
    }

    private Mode mode;

    private int editId;

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
        int label = R.string.activity_record_mode_label_placeholder;
        int button = R.string.activity_record_accept_placeholder;

        switch (this.mode) {
            case DELETE:
                label = R.string.activity_record_mode_label_delete;
                button = R.string.activity_record_accept_delete;
                makeInvisible(R.id.activity_record_form_manufacture);
                makeInvisible(R.id.activity_record_form_model);
                makeInvisible(R.id.activity_record_form_year);
                makeInvisible(R.id.activity_record_field_id_fixed);
                break;
            case EDIT:
                label = R.string.activity_record_mode_label_edit;
                button = R.string.activity_record_accept_edit;
                this.showEditDialog(this.makeEditDialog());
                makeInvisible(R.id.activity_record_field_id_editable);
                break;
            case ADD:
                label = R.string.activity_record_mode_label_add;
                button = R.string.activity_record_accept_add;
                makeInvisible(R.id.activity_record_field_id_editable);
                break;
        }

        TextView text = findViewById(R.id.activity_record_mode_label);
        text.setText(label);

        Button buttonView = findViewById(R.id.activity_record_accept);
        buttonView.setText(button);
    }

    public void setEditRecordId(String id) {
        TextView view = findViewById(R.id.activity_record_field_id_fixed);
        view.setText(id);
        this.editId = Integer.getInteger(id);
    }

    public void showEditDialog(AlertDialog editDialog) {
        editDialog.show();
    }

    // https://developer.android.com/guide/topics/ui/dialogs.html
    public AlertDialog makeEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RecordActivity.this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.activity_record_edit_dialog, null, false);

        final EditText input = (EditText) view.findViewById(R.id.activity_record_dialog_edit_input);

        builder.setView(view);
        builder.setMessage(R.string.activity_record_edit_dialog_message)
                .setTitle(R.string.activity_record_edit_dialog_title)
                .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                RecordActivity.this.setEditRecordId(input.getText().toString());
            }
        });

        return builder.create();
    }

    public void onAccept(View view) throws RemoteException {
        // TODO;

        Uri uri = Uri.parse("content://edu.bmstu.stas.lab6.storage/");
        ContentProviderClient provider = getContentResolver().acquireContentProviderClient(uri);
        Cursor cursor = provider.query(Uri.parse("content://edu.bmstu.stas.lab.storage/fetch/all"), null, null, null, null);

        /*
        mCursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,   // The content URI of the words table
                mProjection,                        // The columns to return for each row
                mSelectionClause                    // Selection criteria
                mSelectionArgs,                     // Selection criteria
                mSortOrder);*/
    }
}
