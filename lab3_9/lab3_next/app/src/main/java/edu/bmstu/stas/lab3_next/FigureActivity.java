package edu.bmstu.stas.lab3_next;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FigureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);
    }

    public void onChoice(View view) {
        int id = view.getId();

        DrawOption.eFigure figure = DrawOption.eFigure.CIRCLE;

        switch (id) {
            case R.id.activity_figure_circle:
                figure = DrawOption.eFigure.CIRCLE;
                break;
            case R.id.activity_figure_square:
                figure = DrawOption.eFigure.SQUARE;
                break;
            case R.id.activity_figure_triangle:
                figure = DrawOption.eFigure.TRIANGLE;
                break;
        }

        Intent intent = getIntent();
        intent.putExtra("figure", figure);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
}
