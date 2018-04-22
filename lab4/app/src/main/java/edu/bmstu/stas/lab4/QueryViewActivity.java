package edu.bmstu.stas.lab4;

import android.Manifest;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QueryViewActivity extends Activity {

    SQLiteDatabase database;
    String query;
    boolean logOutput;
    boolean fileOutput;
    boolean viewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_view);

        this.query = getIntent().getExtras().getString("query");
        this.logOutput= getIntent().getExtras().getBoolean("log");
        this.fileOutput= getIntent().getExtras().getBoolean("file");
        this.viewOutput= getIntent().getExtras().getBoolean("view");

        this.openDatabase();

        this.requestStoragePermission();

        this.startQuery();
    }

    private void openDatabase() {
        this.database = getBaseContext().openOrCreateDatabase
                ("car.db", MODE_PRIVATE, null);
        Log.i("database", "opened");
    }


    public void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                0);
    }

    private String recordToString(Cursor cursor) {
        StringBuilder result = new StringBuilder();

        result.append("\n---");
        result.append("\nrecord #" + Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))));
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

    public void fileCursor(Cursor cursor) {
        StringBuilder result = new StringBuilder();

        do {
            result.append(this.recordToString(cursor));
        } while (cursor.moveToNext());

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

    public void viewCursor(Cursor cursor) {
        ListView lvItems = (ListView) findViewById(R.id.activity_query_view_items);
        QueryAdapter adapter= new QueryAdapter(this, cursor);
        lvItems.setAdapter(adapter);
    }


    public void startQuery() {
        /*String queryString =
                "SELECT column1, (SELECT max(column1) FROM table1) AS max FROM table1 " +
                        "WHERE column1 = ? OR column1 = ? ORDER BY column1";String queryString =
                "SELECT column1, (SELECT max(column1) FROM table1) AS max FROM table1 " +
                        "WHERE column1 = ? OR column1 = ? ORDER BY column1";

                        sqLiteDatabase.rawQuery(queryString, whereArgs);
                        */

        Cursor result = this.database.rawQuery(this.query, null);

        if (this.logOutput) {
            result.moveToFirst();
            this.logCursor(result);
        }

        if (this.fileOutput) {
            result.moveToFirst();
            this.fileCursor(result);
        }

        if (this.viewOutput) {
            result.moveToFirst();
            this.viewCursor(result);
        }

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

	txtData.setText("id: " + item_id + " Имя кота: " + item_content);*/
    }

}
