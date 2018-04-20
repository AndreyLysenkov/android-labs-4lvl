package edu.bmstu.stas.lab2;

import android.app.Activity;
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
        RECTANGLE
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


    public void setFigureForm(Activity activity, ImageView view) {
        int form = R.drawable.square;
        switch (this.Form) {
            case SQUARE:
                form = R.drawable.square;
                break;
            case CIRCLE:
                form = R.drawable.circle;
                break;
            case RECTANGLE:
                form = R.drawable.rectangle;
                break;
        }
        view.setImageDrawable(ContextCompat.getDrawable(activity, form));
    }


    public void setFigurePosition(ImageView view) {
        // pick up at
        // https://stackoverflow.com/questions/4472429/change-the-right-margin-of-a-view-programmatically
        // https://developer.android.com/reference/android/view/ViewGroup.MarginLayoutParams.html
        // TODO
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        switch (this.Position) {
            case CENTER:
                params.setMargins(-1, -1, -1, -1);
                break;
            case BOTTOM:
                params.setMargins(-1, -1, -1, 0);
                break;
            case TOP:
                params.setMargins(-1, 0, -1, -1);
                break;
            case LEFT:
                params.setMargins(0, -1, -1, -1);
                break;
            case RIGHT:
                params.setMargins(-1, -1, 0, -1);
                break;
        }
        view.setLayoutParams(params);
        view.requestLayout();
    }

    public void applyOn(Activity activity, ImageView view) {
        this.setFigureColor(view);
        this.setFigureForm(activity, view);
        this.setFigurePosition(view);
        view.setVisibility(View.VISIBLE);
    }
}
