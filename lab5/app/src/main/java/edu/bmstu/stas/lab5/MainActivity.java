package edu.bmstu.stas.lab5;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO;
    }


    public void onApply(View view) {
        // TODO;

        Intent intent = new Intent(MainActivity.this, OutputActivity.class);

        intent.putExtra("content", ((EditText)findViewById(R.id.activity_main_content)).getText().toString());
        intent.putExtra("color", Color.BLUE);
        intent.putExtra("size", 20);

        startActivity(intent);
    }
}
