package edu.bmstu.stas.lab3_next;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PositionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
    }

    public void onPositionPick(View view) {
        int id = view.getId();

        DrawOption.ePosition positionX = DrawOption.ePosition.CENTER;
        DrawOption.ePosition positionY = DrawOption.ePosition.CENTER;

        switch (id) {
            case R.id.activity_main_position_top_left:
                positionX = DrawOption.ePosition.LEFT;
                positionY = DrawOption.ePosition.TOP;
                break;
            case R.id.activity_main_position_top_center:
                positionX = DrawOption.ePosition.CENTER;
                positionY = DrawOption.ePosition.TOP;
                break;
            case R.id.activity_main_position_top_right:
                positionX = DrawOption.ePosition.RIGHT;
                positionY = DrawOption.ePosition.TOP;
                break;
            case R.id.activity_main_position_center_left:
                positionX = DrawOption.ePosition.LEFT;
                positionY = DrawOption.ePosition.CENTER;
                break;
            case R.id.activity_main_position_center_center:
                positionX = DrawOption.ePosition.CENTER;
                positionY = DrawOption.ePosition.CENTER;
                break;
            case R.id.activity_main_position_center_right:
                positionX = DrawOption.ePosition.RIGHT;
                positionY = DrawOption.ePosition.CENTER;
                break;
            case R.id.activity_main_position_bottom_left:
                positionX = DrawOption.ePosition.LEFT;
                positionY = DrawOption.ePosition.BOTTOM;
                break;
            case R.id.activity_main_position_bottom_center:
                positionX = DrawOption.ePosition.CENTER;
                positionY = DrawOption.ePosition.BOTTOM;
                break;
            case R.id.activity_main_position_bottom_right:
                positionX = DrawOption.ePosition.RIGHT;
                positionY = DrawOption.ePosition.BOTTOM;
                break;
        }

        Intent intent = getIntent();
        intent.putExtra("position_x", positionX);
        intent.putExtra("position_y", positionY);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
}