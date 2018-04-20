package edu.bmstu.stas.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ActivityFigure mFigure = new ActivityFigure();
    ImageView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selection, menu);

        this.mView = findViewById(R.id.figure);
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
