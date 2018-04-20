package edu.bmstu.stas.lab2;

import android.graphics.Color;
import android.graphics.ColorFilter;
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
        TRIANGLE
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
        view.setColorFilter(color);
    }
}
