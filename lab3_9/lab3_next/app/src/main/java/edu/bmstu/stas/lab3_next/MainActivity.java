package edu.bmstu.stas.lab3_next;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DrawView canvas;
    DrawOption option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas = (DrawView) findViewById(R.id.canvas);
        option = new DrawOption();
    }

    public void onColor(View view) {
        Intent intent = new Intent(MainActivity.this, ColorActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onFigure(View view) {
        Intent intent = new Intent(MainActivity.this, FigureActivity.class);
        startActivityForResult(intent, 1);

    }

    public void onPosition(View view) {
        Intent intent = new Intent(MainActivity.this, PositionActivity.class);
        startActivityForResult(intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;

        switch (requestCode) {
            case 0:
                this.option.Color = data.getExtras().getInt("color");
                break;
            case 1:
                this.option.Figure = (DrawOption.eFigure) data.getExtras().getSerializable("figure");
                break;
            case 2:
                this.option.PositionX = (DrawOption.ePosition) data.getExtras().getSerializable("position_x");
                this.option.PositionY = (DrawOption.ePosition) data.getExtras().getSerializable("position_y");
                break;
        }
        this.canvas.update(this.option);
    }
}