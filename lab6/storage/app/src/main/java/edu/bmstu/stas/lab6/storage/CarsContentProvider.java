package edu.bmstu.stas.lab6.storage;

import android.content.ContentProvider;
import android.database.sqlite.SQLiteDatabase;

public class CarsContentProvider extends ContentProvider {


    SQLiteDatabase database;

    public CarsContentProvider() {

    }
    


    /*this.database = getBaseContext().openOrCreateDatabase
                ("car.db", MODE_PRIVATE, null);
        this.database.execSQL("CREATE TABLE IF NOT EXISTS cars (" +
                "id INTEGER, " +
                "type TEXT, " +
                "manufacture TEXT, " +
                "model TEXT, " +
                "baggage INTEGER, " +
                "abs BOOLEAN, " +
                "safety INTEGER, " +
                "consumption REAL)");*/


    // query(), insert(), update(), delete(), getType(), onCreate()




}
