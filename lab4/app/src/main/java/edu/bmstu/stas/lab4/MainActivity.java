package edu.bmstu.stas.lab4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.onCreateAndAddExampleDatabase();
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

    private boolean addExampleRecord (
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

    public void onCreateAndAddExampleDatabase() {
        this.initializeDatabase();
        boolean success = this.addExampleData();
        if (success)
            Toast.makeText(this, R.string.database_message_onCreateExample, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.database_message_onCreateExample_fail, Toast.LENGTH_SHORT).show();
    }

    public String recordToString(Cursor cursor) {
        StringBuilder result = new StringBuilder();
        result.append("record #" + Integer.toString(cursor.getInt(cursor.getColumnIndex("id"))));
        result.append("\n");
        result.append("type: " + cursor.getString(cursor.getColumnIndex("type")));
        result.append("manufacture: " + cursor.getString(cursor.getColumnIndex("manufacture")));
        result.append("model: " + cursor.getString(cursor.getColumnIndex("model")));
        result.append("baggage" + Integer.toString(cursor.getInt(cursor.getColumnIndex("baggage"))));
        //result.append("abs" + Integer.toString(cursor.getInt(cursor.getColumnIndex("abs"))));
        result.append("safety" + Integer.toString(cursor.getInt(cursor.getColumnIndex("safety"))));
        result.append("consumption" + Float.toString(cursor.getFloat(cursor.getColumnIndex("consumption"))));

        return result.toString();
    }

    public void logCursor(Cursor cursor) {
        while (cursor.moveToNext()) {
            Log.i("database/cursor", this.recordToString(cursor));
        }
        cursor.moveToFirst();
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