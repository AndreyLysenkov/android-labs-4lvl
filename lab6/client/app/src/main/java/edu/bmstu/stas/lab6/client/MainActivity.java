package edu.bmstu.stas.lab6.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showRecord(RecordActivity.Mode mode) {
        Intent intent = new Intent(MainActivity.this, RecordActivity.class);

        intent.putExtra("mode", mode);

        startActivity(intent);
    }

    public void onAdd(View view) {
        // TODO;
        this.showRecord(RecordActivity.Mode.ADD);
    }

    public void onEdit(View view) {
        // TODO;
        this.showRecord(RecordActivity.Mode.EDIT);
    }

    public void onDelete(View view) {
        // TODO;
        this.showRecord(RecordActivity.Mode.DELETE);
    }

    public void onView(View view) {
        // TODO;
    }

}
