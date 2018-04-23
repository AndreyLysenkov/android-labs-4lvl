package edu.bmstu.stas.lab6.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// thnx https://developer.android.com/guide/topics/providers/content-provider-creating.html
final class CarsDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "cars";

    private static final String DATABASE_NAME = "cars";


    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
            " (_ID INTEGER PRIMARY KEY, " +
            " manufacture TEXT," +
            " model TEXT," +
            " year INTEGER )";

    CarsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.rawQuery(CREATE_TABLE, null);
    }

    // https://stackoverflow.com/questions/24356650/android-errors-when-using-code-from-stackoverflow-to-implement-sqlite-database
    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
