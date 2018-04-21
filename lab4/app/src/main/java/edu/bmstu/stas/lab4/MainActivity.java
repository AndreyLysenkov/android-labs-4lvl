package edu.bmstu.stas.lab4;

import android.content.ContentValues;
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
    }


    public void initializeDatabase() {
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

    public void onCreateAndAddExampleDatabase(View view) {
        this.initializeDatabase();
        boolean success = this.addExampleData();
        if (success)
            Toast.makeText(this, R.string.database_message_onCreateExample, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.database_message_onCreateExample_fail, Toast.LENGTH_SHORT).show();
    }

    // TODO;
}
