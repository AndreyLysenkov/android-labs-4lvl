package edu.bmstu.stas.lab3_next;

import android.content.Intent;
import android.os.Bundle;

public class DrawOption {

    enum eFigure {
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

    public int Color;
    public eFigure Figure;
    public ePosition PositionX;
    public ePosition PositionY;

    public DrawOption() {
        this.Figure = eFigure.CIRCLE;
        this.PositionX = ePosition.CENTER;
        this.PositionY = ePosition.CENTER;
        this.Color = android.graphics.Color.BLUE;
    }

    public DrawOption(Bundle extras) {
        this.Figure = (eFigure) extras.getSerializable("figure");
        this.PositionX = (ePosition) extras.getSerializable("position_x");
        this.PositionY = (ePosition) extras.getSerializable("position_y");
        this.Color = extras.getInt("color");
    }

    public Intent addToIntent(Intent intent) {
        intent.putExtra("figure", this.Figure);
        intent.putExtra("position_x", this.PositionX);
        intent.putExtra("position_y", this.PositionY);
        intent.putExtra("color", this.Color);
        return intent;
    }

}