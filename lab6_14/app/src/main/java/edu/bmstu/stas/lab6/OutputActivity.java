package edu.bmstu.stas.lab6;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

public class OutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);


        Cursor cursor = this.getCursor(this.getName());

        if ((cursor == null) || (cursor.getCount() == 0)) {
            this.nothingFound();
        } else {
            this.bind(cursor);
        }

    }

    private String getName() {
        return getIntent().getExtras().getString("name");
    }

    private Cursor getCursor(String name) {
        String selection = null;

        if (!TextUtils.isEmpty(name)) {
            selection = PeopleContentProvider.SECOND_NAME_FIELD + " like '%" + name + "%'";
        }


        Uri peopleUri = Uri.parse(PeopleContentProvider.URL);
        return getContentResolver().query(
                peopleUri,
                new String[] {
                    PeopleContentProvider.ID_FIELD,
                    PeopleContentProvider.FIRST_NAME_FIELD,
                    PeopleContentProvider.SECOND_NAME_FIELD,
                    PeopleContentProvider.PHONE_FIELD
                },
                selection,
                null,
                PeopleContentProvider.FIRST_NAME_FIELD);
    }

    private void bind(Cursor cursor) {
        ListView view = (ListView) findViewById(R.id.output);
        PeopleAdapter adapter = new PeopleAdapter(this, cursor);
        view.setAdapter(adapter);
    }

    private void nothingFound() {
        View view = findViewById(R.id.output_nothing_found);
        view.setVisibility(View.VISIBLE);
    }

}
