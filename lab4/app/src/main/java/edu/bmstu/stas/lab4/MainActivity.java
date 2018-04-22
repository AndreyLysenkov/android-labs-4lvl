package edu.bmstu.stas.lab4;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeDatabase();

        String query = "SELECT * FROM cars";
        Cursor elements = this.database.rawQuery(query, null);
        if (elements.getCount() == 0)
            this.onAddExampleDatabase();

        this.requestStoragePermission();
    }

    private void initializeDatabase() {
        this.database = getBaseContext().openOrCreateDatabase
                ("car.db", MODE_PRIVATE, null);
        this.database.execSQL("CREATE TABLE IF NOT EXISTS cars (" +
                "id INTEGER, " +
                "type TEXT, " +
                "manufacture TEXT, " +
                "model TEXT, " +
                "baggage INTEGER, " +
                "abs BOOLEAN, " +
                "safety INTEGER, " +
                "consumption REAL)");
        Log.i("database", "created or opened");
    }

    private boolean addExampleRecord(
            String type,
            String manufacture,
            String model,
            int baggage,
            boolean abs,
            int safety,
            float consumption) {
        ContentValues value = new ContentValues();
        value.put("type", type);
        value.put("manufacture", manufacture);
        value.put("model", model);
        value.put("baggage", baggage);
        value.put("abs", abs);
        value.put("safety", safety);
        value.put("consumption", consumption);
        value.put("id", this.count);
        this.count++;
        return this.database.insert("cars", null, value) >= 0;
    }

    private boolean addExampleData() {
        boolean success = true;

        success &= this.addExampleRecord("легковая", "ваз", "лада", 5, false, 2, 3);
        success &= this.addExampleRecord("легковая", "toyota", "камри", 7, true, 10, 2);
        success &= this.addExampleRecord("легковая", "chevrolet", "нисан", 3, false, 4, 1);
        success &= this.addExampleRecord("легковая", "ford", "генри", 4, false, 7, 4);
        success &= this.addExampleRecord("легковая", "kia", "корея", 9, true, 6, 3);

        success &= this.addExampleRecord("фургон", "kia", "форгон", 40, true, 4, 9);
        success &= this.addExampleRecord("фургон", "toyota", "камри", 30, false, 5, 8);
        success &= this.addExampleRecord("фургон", "kia", "форгон", 30, true, 3, 7);
        success &= this.addExampleRecord("фургон", "ваз", "газель", 40, true, 1, 10);
        success &= this.addExampleRecord("фургон", "ford", "мандео", 35, false, 4, 8);

        success &= this.addExampleRecord("седан", "ford", "генриX", 6, true, 8, 6);
        success &= this.addExampleRecord("седан", "kia", "лада", 7, true, 7, 7);
        success &= this.addExampleRecord("седан", "chevrolet", "нисани", 4, false, 6, 5);
        success &= this.addExampleRecord("седан", "ваз", "седан", 6, false, 3, 6);
        success &= this.addExampleRecord("седан", "toyota", "камриХ", 10, true, 15, 7);

        return success;
    }

    public void onAddExampleDatabase() {
        boolean success = this.addExampleData();
        if (success)
            Toast.makeText(this, R.string.database_message_onCreateExample, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.database_message_onCreateExample_fail, Toast.LENGTH_SHORT).show();
    }

    private String recordToString(Cursor cursor) {
        StringBuilder result = new StringBuilder();

        result.append("\n---");
        result.append("\nrecord #" + Integer.toString(cursor.getInt(cursor.getColumnIndex("id"))));
        result.append("\ntype: " + cursor.getString(cursor.getColumnIndex("type")));
        result.append("\nmanufacture: " + cursor.getString(cursor.getColumnIndex("manufacture")));
        result.append("\nmodel: " + cursor.getString(cursor.getColumnIndex("model")));
        result.append("\nbaggage: " + Integer.toString(cursor.getInt(cursor.getColumnIndex("baggage"))));
        //result.append("abs" + Integer.toString(cursor.getInt(cursor.getColumnIndex("abs"))));
        result.append("\nsafety: " + Integer.toString(cursor.getInt(cursor.getColumnIndex("safety"))));
        result.append("\nconsumption: " + Float.toString(cursor.getFloat(cursor.getColumnIndex("consumption"))));
        result.append("\n---");

        return result.toString();
    }

    private void logCursor(Cursor cursor) {
        do {
            Log.i("database/cursor", this.recordToString(cursor));
        } while (cursor.moveToNext());
        Toast.makeText(this, R.string.database_message_output_log_success, Toast.LENGTH_SHORT).show();
    }

    public void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                0);
    }


    public void fileCursor(Cursor cursor) {
        StringBuilder result = new StringBuilder();

        do {
            result.append(this.recordToString(cursor));
        } while (cursor.moveToNext());

        // TODO; change on download user folder
        File cursorFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "query1.txt");
        Log.i("file", "write in `" + cursorFile.getAbsolutePath() + "` folder");
        try {

            cursorFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(cursorFile, false);
            outputStream.write(result.toString().getBytes(), 0, result.toString().getBytes().length);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.database_message_output_file_fail, Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, R.string.database_message_output_file_success, Toast.LENGTH_SHORT).show();
    }

    public void onQuery(View view) {
        /*String queryString =
                "SELECT column1, (SELECT max(column1) FROM table1) AS max FROM table1 " +
                        "WHERE column1 = ? OR column1 = ? ORDER BY column1";String queryString =
                "SELECT column1, (SELECT max(column1) FROM table1) AS max FROM table1 " +
                        "WHERE column1 = ? OR column1 = ? ORDER BY column1";

                        sqLiteDatabase.rawQuery(queryString, whereArgs);
                        */

        String query = "SELECT * FROM cars ORDER BY safety";
        Cursor result = this.database.rawQuery(query, null);
        result.moveToFirst();
        this.logCursor(result);
        result.moveToFirst();
        this.fileCursor(result);


        /*
        * public void onCursorClick(View v) {
	String query = "SELECT " + CatsDataBase._ID + ", "
			+ CatsDataBase.CATNAME + " FROM " + CatsDataBase.TABLE_NAME;
	Cursor catCursor = sqdb.rawQuery(query, null);
	catCursor.moveToFirst(); // переходим на первую строку
	// извлекаем данные из курсора
	int item_id = catCursor
			.getInt(catCursor.getColumnIndex(CatsDataBase._ID));
	String item_content = catCursor.getString(catCursor
			.getColumnIndex(CatsDataBase.CATNAME));
	catCursor.close();

	txtData.setText("id: " + item_id + " Имя кота: " + item_content);
}
        * */

    }


    // TODO;
}