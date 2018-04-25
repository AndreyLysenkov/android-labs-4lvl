package edu.bmstu.stas.lab6;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.requestPermission();
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS},
                0);
    }

    public void onApply(View view) {
        Intent intent = new Intent(MainActivity.this, OutputActivity.class);

        intent.putExtra("name", ((EditText) findViewById(R.id.activity_main_name)).getText().toString());

        startActivity(intent);
    }

    private void insertItem(String firstName, String secondName, String phone) {
        ContentValues values = new ContentValues();

        values.put(PeopleContentProvider.FIRST_NAME_FIELD, firstName);
        values.put(PeopleContentProvider.SECOND_NAME_FIELD, secondName);
        values.put(PeopleContentProvider.PHONE_FIELD, phone);

        Uri uri = getContentResolver().insert(PeopleContentProvider.URI, values);

        Log.d("insert_item", uri.toString());
    }

    private void addDummy() {
        insertItem("Дожрдж", "Оруэлл", "84842277313");
        insertItem("Станислав", "Турченко", "89066547898");
        insertItem("Елена", "Турченко", "89066555643");
        insertItem("Аднрей", "Турченко", "89035446464");
        insertItem("Ростислав", "Турников", "89066554896");
    }

    public void onDummy(View view) {
        this.addDummy();
    }
}
