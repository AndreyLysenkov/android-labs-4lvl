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

// thnx http://startandroid.ru/ru/uroki/vse-uroki-spiskom/312-urok-142-risovanie-prostye-figury-tekst.html
// http://startandroid.ru/ru/uroki/vse-uroki-spiskom/322-urok-146-risovanie-canvas-preobrazovanija.html
public class MainActivity extends AppCompatActivity {

    DrawView draw;
    DrawOption option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setDraw(new DrawOption(true));
        this.option = new DrawOption(false);
    }

    public void setDraw(DrawOption option)
    {
        draw = new DrawView(this, option);
        setContentView(draw);
    }

    class DrawView extends View {

        Paint p;
        Canvas canvas;
        DrawOption option;


        public DrawView(Context context, DrawOption option) {
            super(context);
            p = new Paint();
            p.setColor(option.Clear ? Color.WHITE : option.Color);
            this.option = option;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            this.canvas = canvas;

            // clear screen
            this.canvas.drawARGB(255, 255, 255, 255);

            if (this.option.Clear)
                return;

            switch (this.option.Figure) {
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
                option.Color = Color.BLACK;
                return true;
            case R.id.menu_color_gray:
                option.Color = Color.GRAY;
                return true;
            case R.id.menu_color_blue:
                option.Color = Color.BLUE;
                return true;
            case R.id.menu_color_red:
                option.Color = Color.RED;
                return true;
            case R.id.menu_form_circle:
                option.Figure = DrawOption.eFigure.CIRCLE;
                drawFigure();
                return true;
            case R.id.menu_form_square:
                option.Figure = DrawOption.eFigure.SQUARE;
                drawFigure();
                return true;
            case R.id.menu_form_rectangle:
                option.Figure = DrawOption.eFigure.RECTANGLE;
                drawFigure();
                return true;
            case R.id.menu_position_center:
                option.Position = DrawOption.ePosition.CENTER;
                return true;
            case R.id.menu_position_left:
                option.Position = DrawOption.ePosition.LEFT;
                return true;
            case R.id.menu_position_right:
                option.Position = DrawOption.ePosition.RIGHT;
                return true;
            case R.id.menu_position_bottom:
                option.Position = DrawOption.ePosition.BOTTOM;
                return true;
            case R.id.menu_position_top:
                option.Position = DrawOption.ePosition.TOP;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onScreenClear(MenuItem item) {
        this.setDraw(new DrawOption(true));
    }

    public  void drawFigure() {
        this.setDraw(this.option);
    }

    public void onDrawFigure(MenuItem item) {
        this.drawFigure();
    }
}
