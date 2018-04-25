package edu.bmstu.stas.lab6;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class PeopleAdapter extends CursorAdapter {


    public PeopleAdapter (Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.people_record, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String first_name = cursor.getString(cursor.getColumnIndexOrThrow(
                PeopleContentProvider.FIRST_NAME_FIELD
        ));
        TextView firstNameView = view.findViewById(R.id.people_first_name_content);
        firstNameView.setText(first_name);

        String second_name = cursor.getString(cursor.getColumnIndexOrThrow(
                PeopleContentProvider.SECOND_NAME_FIELD
        ));
        TextView secondNameView = view.findViewById(R.id.people_second_name_content);
        secondNameView.setText(second_name);

        String phone = cursor.getString(cursor.getColumnIndexOrThrow(
                PeopleContentProvider.PHONE_FIELD
        ));
        TextView phoneView = view.findViewById(R.id.people_phone_content);
        phoneView.setText(phone);

    }

}