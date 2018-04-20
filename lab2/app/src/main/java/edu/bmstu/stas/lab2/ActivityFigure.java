package edu.bmstu.stas.lab2;

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
}
