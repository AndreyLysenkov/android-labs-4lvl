package edu.bmstu.stas.lab3_next;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Random;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
    }

    public void onApply(View view) {
        EditText colorEditText = (EditText) findViewById(R.id.activity_color_field);
        String colorText = colorEditText.getText().toString();

        int color = Color.argb(
                255,
                Integer.parseInt(colorText.substring(0, 2), 16),
                Integer.parseInt(colorText.substring(2, 4), 16),
                Integer.parseInt(colorText.substring(4, 6), 16)
        );

        Intent intent = getIntent();
        intent.putExtra("color", color);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
}
