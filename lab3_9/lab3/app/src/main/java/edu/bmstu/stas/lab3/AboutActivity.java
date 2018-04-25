package edu.bmstu.stas.lab3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        int size = getIntent().getExtras().getInt("size");
        int color = getIntent().getExtras().getInt("color");
        String text = getIntent().getExtras().getString("text");

        TextView infoTextView =
                (TextView)findViewById(R.id.activity_about_content);
        infoTextView.setText(text);
        infoTextView.setTextColor(color);
        infoTextView.setTextSize(size);

    }
}
