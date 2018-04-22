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


    public void onQuery(View view) {
        Intent intent = new Intent(MainActivity.this, QueryViewActivity.class);

        int id = 0;
        boolean isLog = false;
        boolean isFile = false;
        boolean isView = false;
        String query = "* FROM cars";
        eOutputMode mode = eOutputMode.ALL;

        // TODO; перепроверить isView, isLog, isFile согласно методичке;
        switch (view.getId()) {
            case R.id.activity_query_button_1:
                id = 1;
                isView = false;
                isLog = true;
                isFile = true;
                query = "* FROM cars ORDER BY safety";
                break;
            case R.id.activity_query_button_2:
                id = 2;
                isView = true;
                isLog = true;
                isFile = false;
                query = "* FROM cars GROUP BY safety, abs";
                break;
            case R.id.activity_query_button_3:
                id = 3;
                isView = false;
                isLog = true;
                isFile = true;
                query = "SUM(baggage) FROM cars";
                mode = eOutputMode.NUMBER;
                break;
            case R.id.activity_query_button_4:
                id = 4;
                isView = true;
                isLog = true;
                isFile = true;
                query = ""; // TODO;
                mode = eOutputMode.NUMBER;
                break;
            case R.id.activity_query_button_5:
                id = 5;
                isView = false;
                isLog = true;
                isFile = false;
                query = "* FROM cars WHERE safety = (SELECT MAX(safety) FROM cars)";
                break;
            case R.id.activity_query_button_6:
                id = 6;
                isView = true;
                isLog = true;
                isFile = false;
                query = "* FROM cars WHERE " +
                        "consumption > (SELECT AVG(consumption) FROM cars)" +
                        "AND baggage > (SELECT AVG(baggage) FROM cars)" +
                        "AND safety > (SELECT AVG(safety) FROM cars)";
                break;
            case R.id.activity_query_button_7:
                id = 7;
                isView = true;
                isLog = true;
                isFile = false;
                query = "* FROM cars WHERE " +
                        "consumption < (SELECT AVG(consumption) FROM cars)" +
                        "AND baggage < (SELECT AVG(baggage) FROM cars)" +
                        "AND safety < (SELECT AVG(safety) FROM cars)";
                break;
            case R.id.activity_query_button_8:
                id = 8;
                isView = false;
                isLog = true;
                isFile = false;
                query = "* FROM cars WHERE consumption > 5";
                mode = eOutputMode.FIRST;
                break;
        }

        intent.putExtra("query", "SELECT rowid _id, " + query);
        intent.putExtra("log", isLog);
        intent.putExtra("file", isFile);
        intent.putExtra("view", isView);
        intent.putExtra("id", id);
        intent.putExtra("mode", mode);

        String logRecord = "query" +
                "request query #" + Integer.toString(id) +
                "\nlog output: " + Boolean.toString(isLog) +
                "\nfile output: " + Boolean.toString(isFile) +
                "\nview output: " + Boolean.toString(isView) +
                "\nquery: " + query;

        Log.i("query", logRecord);

        startActivity(intent);

    }

}