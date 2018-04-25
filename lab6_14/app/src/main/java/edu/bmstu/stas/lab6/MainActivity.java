package edu.bmstu.stas.lab6;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.requestPermission();
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS},
                0);
    }

    public void onApply(View view) {
        Intent intent = new Intent(MainActivity.this, OutputActivity.class);

        intent.putExtra("name", ((EditText) findViewById(R.id.activity_main_name)).getText().toString());

        startActivity(intent);
    }
}
