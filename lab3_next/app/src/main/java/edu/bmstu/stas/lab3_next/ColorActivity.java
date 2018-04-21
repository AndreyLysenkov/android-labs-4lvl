package edu.bmstu.stas.lab3_next;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.Random;

public class ColorActivity extends AppCompatActivity {

    int color = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
    }

    private static int getRandomColor() {
        // thnx https://stackoverflow.com/questions/5280367/android-generate-random-color-on-click
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void onColorChoose(View view) {
        // thnx https://developer.android.com/guide/topics/ui/controls/radiobutton.html
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.activity_main_color_black:
                if (checked)
                    color = Color.BLACK;
                break;
            case R.id.activity_main_color_gray:
                if (checked)
                    color = Color.GRAY;
                break;
            case R.id.activity_main_color_blue:
                if (checked)
                    color = Color.BLUE;
                break;
            case R.id.activity_main_color_red:
                if (checked)
                    color = Color.RED;
                break;
            case R.id.activity_main_color_green:
                if (checked)
                    color = Color.GREEN;
                break;
            case R.id.activity_main_color_random:
                if (checked)
                    color = getRandomColor();
                break;
        }

        Intent intent = getIntent();
        intent.putExtra("color", color);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
}
