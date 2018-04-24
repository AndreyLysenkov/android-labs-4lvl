package edu.bmstu.stas.lab6.client;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showRecordActivity(RecordActivity.Mode mode) {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.class.},
                0);


        Intent intent = new Intent(MainActivity.this, RecordActivity.class);

        intent.putExtra("mode", mode);

        startActivity(intent);
    }

    public void onAdd(View view) {
        // TODO;
        this.showRecordActivity(RecordActivity.Mode.ADD);
    }

    public void onEdit(View view) {
        // TODO;
        this.showRecordActivity(RecordActivity.Mode.EDIT);
    }

    public void onDelete(View view) {
        // TODO;
        this.showRecordActivity(RecordActivity.Mode.DELETE);
    }

    public void onView(View view) {
        // TODO;
    }

}
