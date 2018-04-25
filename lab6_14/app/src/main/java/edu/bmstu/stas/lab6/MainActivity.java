package edu.bmstu.stas.lab6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onApply(View view) {
        Intent intent = new Intent(MainActivity.this, OutputActivity.class);

        intent.putExtra("name", ((EditText) findViewById(R.id.activity_main_name)).getText().toString());

        startActivity(intent);
    }
}
