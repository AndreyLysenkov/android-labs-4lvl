package edu.bmstu.stas.lab6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(
                context,
                PeopleContentProvider.DATABASE_NAME,
                null,
                PeopleContentProvider.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(PeopleContentProvider.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + PeopleContentProvider.PEOPLE_TABLE_NAME);
        onCreate(database);
    }

}