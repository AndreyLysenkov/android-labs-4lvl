package edu.bmstu.stas.lab3;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DrawOption option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.option = new DrawOption();
    }

    public void onPositionPick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.activity_main_position_top_left:
                this.option.PositionX = DrawOption.ePosition.LEFT;
                this.option.PositionY = DrawOption.ePosition.TOP;
                break;
            case R.id.activity_main_position_top_center:
                this.option.PositionX = DrawOption.ePosition.CENTER;
                this.option.PositionY = DrawOption.ePosition.TOP;
                break;
            case R.id.activity_main_position_top_right:
                this.option.PositionX = DrawOption.ePosition.RIGHT;
                this.option.PositionY = DrawOption.ePosition.TOP;
                break;
            case R.id.activity_main_position_center_left:
                this.option.PositionX = DrawOption.ePosition.LEFT;
                this.option.PositionY = DrawOption.ePosition.CENTER;
                break;
            case R.id.activity_main_position_center_center:
                this.option.PositionX = DrawOption.ePosition.CENTER;
                this.option.PositionY = DrawOption.ePosition.CENTER;
                break;
            case R.id.activity_main_position_center_right:
                this.option.PositionX = DrawOption.ePosition.RIGHT;
                this.option.PositionY = DrawOption.ePosition.CENTER;
                break;
            case R.id.activity_main_position_bottom_left:
                this.option.PositionX = DrawOption.ePosition.LEFT;
                this.option.PositionY = DrawOption.ePosition.BOTTOM;
                break;
            case R.id.activity_main_position_bottom_center:
                this.option.PositionX = DrawOption.ePosition.CENTER;
                this.option.PositionY = DrawOption.ePosition.BOTTOM;
                break;
            case R.id.activity_main_position_bottom_right:
                this.option.PositionX = DrawOption.ePosition.RIGHT;
                this.option.PositionY = DrawOption.ePosition.BOTTOM;
                break;
        }
    }

    public void parseColor() {
        EditText colorEditText = (EditText) findViewById(R.id.activity_main_color);
        String colorText = colorEditText.getText().toString();

        try {
            this.option.Color = Color.parseColor(colorText);
        }
        catch (Exception e) {
            return;
        }
    }

    public void onClick(View view) {
        this.parseColor();

        Intent intent = new Intent();
        intent.setAction("edu.bmstu.stas.lab3.VIEW_TEXT");
        intent = this.option.addToIntent(intent);
        startActivity(intent);
    }

    public void onFigurePick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.activity_main_figure_circle:
                this.option.Figure = DrawOption.eFigure.CIRCLE;
                break;
            case R.id.activity_main_figure_square:
                this.option.Figure = DrawOption.eFigure.SQUARE;
                break;
            case R.id.activity_main_figure_triangle:
                this.option.Figure = DrawOption.eFigure.TRIANGLE;
                break;
        }
    }
}
