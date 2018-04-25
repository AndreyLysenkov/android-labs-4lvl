package edu.bmstu.stas.lab3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class AboutActivity extends Activity {

    DrawOption option;
    DrawView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Intent intent = getIntent();

        this.option = new DrawOption(intent.getExtras());

        this.draw();
    }


    private void draw() {
        this.view = new DrawView(this, this.option);
        setContentView(this.view);
    }


}
