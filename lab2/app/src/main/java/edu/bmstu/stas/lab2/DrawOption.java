package edu.bmstu.stas.lab2;

public class DrawOption {


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

    public Boolean Clear;
    public int Color;
    public eFigure Figure;
    public ePosition Position;

    public DrawOption(Boolean isClear) {
        this.Clear = isClear;
        this.Figure = eFigure.CIRCLE;
        this.Position = ePosition.CENTER;
        this.Color = android.graphics.Color.BLACK;
    }

}
