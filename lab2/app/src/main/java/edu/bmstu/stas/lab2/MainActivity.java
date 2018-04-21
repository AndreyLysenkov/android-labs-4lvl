package edu.bmstu.stas.lab2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d("activity", "create menu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("activity", "selected option");
        switch (item.getItemId()) {
            case R.id.menu_color_black:
                option.Color = Color.BLACK;
                Toast.makeText(this, R.string.menu_action_color_black_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_color_gray:
                option.Color = Color.GRAY;
                Toast.makeText(this, R.string.menu_action_color_gray_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_color_blue:
                option.Color = Color.BLUE;
                Toast.makeText(this, R.string.menu_action_color_blue_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_color_red:
                option.Color = Color.RED;
                Toast.makeText(this, R.string.menu_action_color_red_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_form_circle:
                option.Figure = DrawOption.eFigure.CIRCLE;
                Toast.makeText(this, R.string.menu_action_figure_circle_hint, Toast.LENGTH_SHORT).show();
                drawFigure();
                return true;
            case R.id.menu_form_square:
                option.Figure = DrawOption.eFigure.SQUARE;
                Toast.makeText(this, R.string.menu_action_figure_square_hint, Toast.LENGTH_SHORT).show();
                drawFigure();
                return true;
            case R.id.menu_form_rectangle:
                option.Figure = DrawOption.eFigure.RECTANGLE;
                Toast.makeText(this, R.string.menu_action_figure_rectangle_hint, Toast.LENGTH_SHORT).show();
                drawFigure();
                return true;
            case R.id.menu_position_center:
                option.Position = DrawOption.ePosition.CENTER;
                Toast.makeText(this, R.string.menu_action_position_center_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_position_left:
                option.Position = DrawOption.ePosition.LEFT;
                Toast.makeText(this, R.string.menu_action_position_left_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_position_right:
                option.Position = DrawOption.ePosition.RIGHT;
                Toast.makeText(this, R.string.menu_action_position_right_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_position_bottom:
                option.Position = DrawOption.ePosition.BOTTOM;
                Toast.makeText(this, R.string.menu_action_position_bottom_hint, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_position_top:
                option.Position = DrawOption.ePosition.TOP;
                Toast.makeText(this, R.string.menu_action_position_top_hint, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onScreenClear(MenuItem item) {
        Log.d("activity", "clicked on clear button");
        this.setDraw(new DrawOption(true));
    }

    public  void drawFigure() {
        this.setDraw(this.option);
    }

    public void onDrawFigure(MenuItem item) {
        Log.d("activity", "clicked on draw button");
        this.drawFigure();
    }
}
