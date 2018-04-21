package edu.bmstu.stas.lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

// tyhnx http://startandroid.ru/ru/uroki/vse-uroki-spiskom/312-urok-142-risovanie-prostye-figury-tekst.html
public class MainActivity extends AppCompatActivity {

    ActivityFigure mFigure = new ActivityFigure();
    ImageView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
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
        Rect rect;


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setColor(Color.BLACK);
            figure = eFigure.CIRCLE;
            position = ePosition.CENTER;
            rect = 
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





        /*

        // заливка канвы цветом
            canvas.drawARGB(80, 102, 204, 255);

            // настройка кисти
            // красный цвет
            p.setColor(Color.RED);
            // толщина линии = 10
            p.setStrokeWidth(10);

            // рисуем точку (50,50)
            canvas.drawPoint(50, 50, p);

            // рисуем линию от (100,100) до (500,50)
            canvas.drawLine(100,100,500,50,p);

            // рисуем круг с центром в (100,200), радиус = 50
            canvas.drawCircle(100, 200, 50, p);

            // рисуем прямоугольник
            // левая верхняя точка (200,150), нижняя правая (400,200)
            canvas.drawRect(200, 150, 400, 200, p);

            // настройка объекта Rect
            // левая верхняя точка (250,300), нижняя правая (350,500)
            rect.set(250, 300, 350, 500);
            // рисуем прямоугольник из объекта rect
            canvas.drawRect(rect, p);
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selection, menu);

        /*this.mView = findViewById(R.id.figure);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_color_black:
                mFigure.Color = ActivityFigure.eColor.BLACK;
                return true;
            case R.id.menu_color_gray:
                mFigure.Color = ActivityFigure.eColor.GRAY;
                return true;
            case R.id.menu_color_blue:
                mFigure.Color = ActivityFigure.eColor.BLUE;
                return true;
            case R.id.menu_color_red:
                mFigure.Color = ActivityFigure.eColor.RED;
                return true;
            case R.id.menu_form_circle:
                mFigure.Form = ActivityFigure.eForm.CIRCLE;
                drawFigure();
                return true;
            case R.id.menu_form_square:
                mFigure.Form = ActivityFigure.eForm.SQUARE;
                drawFigure();
                return true;
            case R.id.menu_form_rectangle:
                mFigure.Form = ActivityFigure.eForm.RECTANGLE;
                drawFigure();
                return true;
            case R.id.menu_position_center:
                mFigure.Position = ActivityFigure.ePosition.CENTER;
                return true;
            case R.id.menu_position_left:
                mFigure.Position = ActivityFigure.ePosition.LEFT;
                return true;
            case R.id.menu_position_right:
                mFigure.Position = ActivityFigure.ePosition.RIGHT;
                return true;
            case R.id.menu_position_bottom:
                mFigure.Position = ActivityFigure.ePosition.BOTTOM;
                return true;
            case R.id.menu_position_top:
                mFigure.Position = ActivityFigure.ePosition.TOP;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onScreenClear(MenuItem item) {
        mView.setVisibility(View.INVISIBLE);
    }

    public  void drawFigure() {
        this.mFigure.applyOn(this, mView);
    }

    public void onDrawFigure(MenuItem item) {
        this.drawFigure();
    }
}
