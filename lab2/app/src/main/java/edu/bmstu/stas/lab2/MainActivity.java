package edu.bmstu.stas.lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

// tyhnx http://startandroid.ru/ru/uroki/vse-uroki-spiskom/312-urok-142-risovanie-prostye-figury-tekst.html
public class MainActivity extends AppCompatActivity {

    DrawView draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        draw = new DrawView(this);
        setContentView(draw);
    }

    enum eFigure {
        SQUARE,
        CIRCLE,
        RECTANGLE
    }

    public enum ePosition {
        CENTER,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    class DrawView extends View {

        Paint p;
        Canvas canvas;
        eFigure figure;
        ePosition position;


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setColor(Color.BLACK);
            figure = eFigure.CIRCLE;
            position = ePosition.CENTER;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            this.canvas = canvas;
        }

        public void Clear() {
            this.canvas.drawARGB(255, 255, 255, 255);
        }


        public void setColor(int color) {
            this.p.setColor(color);
        }

        public void setFigure(eFigure figure) {
            this.figure = figure;
        }

        public  void setPosition(ePosition position) {
            this.position = position;
        }

        public void draw() {
            this.Clear();
            switch (this.figure) {
                case CIRCLE:
                    canvas.drawCircle(100, 200, 50, p);
                    break;
                case SQUARE:
                    canvas.drawRect(200, 150, 400, 350, p);
                    break;
                case RECTANGLE:
                    canvas.drawRect(200, 150, 400, 200, p);
                    break;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_color_black:
                draw.setColor(Color.BLACK);
                return true;
            case R.id.menu_color_gray:
                draw.setColor(Color.GRAY);
                return true;
            case R.id.menu_color_blue:
                draw.setColor(Color.BLUE);
                return true;
            case R.id.menu_color_red:
                draw.setColor(Color.RED);
                return true;
            case R.id.menu_form_circle:
                draw.setFigure(eFigure.CIRCLE);
                drawFigure();
                return true;
            case R.id.menu_form_square:
                draw.setFigure(eFigure.SQUARE);
                drawFigure();
                return true;
            case R.id.menu_form_rectangle:
                draw.setFigure(eFigure.RECTANGLE);
                drawFigure();
                return true;
            case R.id.menu_position_center:
                draw.setPosition(ePosition.CENTER);
                return true;
            case R.id.menu_position_left:
                draw.setPosition(ePosition.LEFT);
                return true;
            case R.id.menu_position_right:
                draw.setPosition(ePosition.RIGHT);
                return true;
            case R.id.menu_position_bottom:
                draw.setPosition(ePosition.BOTTOM);
                return true;
            case R.id.menu_position_top:
                draw.setPosition(ePosition.TOP);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onScreenClear(MenuItem item) {
        draw.Clear();
    }

    public  void drawFigure() {
        draw.draw();
    }

    public void onDrawFigure(MenuItem item) {
        this.drawFigure();
    }
}
