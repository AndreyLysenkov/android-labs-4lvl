package edu.bmstu.stas.lab6;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class OutputActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        this.fetchData();

        this.fetchContacts();
    }

    public void fetchData() {
        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
    }

    public void fetchContacts() {
        ArrayList<String> contacts = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null,null, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0)
            return;
        do {

            String contact = cursor.getString(cursor.getColumnIndexOrThrow(
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            ));
            if (contact != null)
                contacts.add(contact);


            contact = cursor.getString(cursor.getColumnIndexOrThrow(
                    ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE
            ));
            if (contact != null)
                contacts.add(contact);


        } while (cursor.moveToNext());
        for (int i = 0; i < contacts.size(); i++) {
            Log.d("contact", contacts.get(i));
        }
        Log.d("done", "done");
    }

    /*
    *

    while (cursor.moveToNext()) {
        // получить каждый контакт
        String contact = cursor.getString(cursor.getColumnIndex
(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
        // добавить контакт в список
        contacts.add(contact);
    cursor.close();
}

    * */
}
