package edu.bmstu.stas.lab6;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class OutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        this.bind(this.getCursor());
    }

    private Cursor getCursor() {
        Uri peopleUri = Uri.parse(PeopleContentProvider.URL);
        return getContentResolver().query(
                peopleUri,
                null,
                null,
                null,
                PeopleContentProvider.FIRST_NAME_FIELD);
    }

    private void bind(Cursor cursor) {
        ListView view = (ListView) findViewById(R.id.output);
        PeopleAdapter adapter = new PeopleAdapter(this, cursor);
        view.setAdapter(adapter);
    }

}
