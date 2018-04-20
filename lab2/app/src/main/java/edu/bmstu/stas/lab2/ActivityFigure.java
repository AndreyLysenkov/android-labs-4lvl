package edu.bmstu.stas.lab2;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ActivityFigure {

    public enum eColor {
        BLACK,
        GRAY,
        BLUE,
        RED
    }

    public enum eForm {
        CIRCLE,
        SQUARE,
        LINE
    }

    public enum ePosition {
        CENTER,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    public eForm Form;

    public eColor Color;

    public ePosition Position;

    public ActivityFigure() {
        this.Form = eForm.CIRCLE;
        this.Color = eColor.BLACK;
        this.Position = ePosition.CENTER;
    }

    public void setFigureColor(ImageView view) {
        int color = android.graphics.Color.BLACK;
        switch (this.Color) {
            case BLACK:
                color = android.graphics.Color.BLACK;
                break;
            case GRAY:
                color = android.graphics.Color.GRAY;
                break;
            case BLUE:
                color = android.graphics.Color.BLUE;
                break;
            case RED:
                color = android.graphics.Color.RED;
                break;
        }
        view.setBackgroundColor(color);
    }


    public void setFigureForm(ImageView view, Activity activity) {
        int form = R.drawable.square;
        switch (this.Form) {
            case SQUARE:
                form = R.drawable.square;
                break;
            case CIRCLE:
                form = R.drawable.circle;
                break;
            case LINE:
                form = R.drawable.line;
                break;
        }
        view.setImageDrawable(ContextCompat.getDrawable(activity, form));
    }


    public void setPosition(ImageView view) {
        switch (this.Position) {
            case CENTER:
                view.setX(0);
                view.setY(0);
                break;
            case LEFT:
                view.setX(-1);
                view.setY(0);
                break;
            case RIGHT:
                view.setX(1);
                view.setY(0);
                break;
            case BOTTOM:
                view.setX(0);
                view.setY(1);
                break;
            case TOP:
                view.setX(0);
                view.setY(-1);
                break;
        }
    }
}
