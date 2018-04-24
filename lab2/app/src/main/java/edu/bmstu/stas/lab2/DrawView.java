package edu.bmstu.stas.lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

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


    class Position {
        public int X;
        public int Y;
    }

    private Position calculatePosition(int width, int height) {
        Position result = new Position();
        DrawOption.ePosition position = this.option.Position;

        int shiftX = 100;
        int shiftY = 100;
        int padding = 50;

        if (position == DrawOption.ePosition.CENTER) {
            result.X = width / 2 - shiftX;
            result.Y = height / 2 - shiftY;
        }

        if (position == DrawOption.ePosition.TOP) {
            result.X = width / 2 - shiftX;
            result.Y = padding;
        }

        if (position == DrawOption.ePosition.BOTTOM) {
            result.X = width / 2 - shiftX;
            result.Y = height - shiftY * 2 - padding;
        }

        if (position == DrawOption.ePosition.LEFT) {
            result.X = padding;
            result.Y = height / 2 - shiftY;
        }

        if (position == DrawOption.ePosition.RIGHT) {
            result.X = width - shiftX * 2 - padding;
            result.Y = height / 2 - shiftY;
        }

        Log.d("figure", "calculated position as X:" + Integer.toString(result.X) +
            " and Y:" + Integer.toString(result.Y));
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        // clear screen
        this.canvas.drawARGB(255, 255, 255, 255);
        Log.d("canvas", "canvas cleared");

        if (this.option.Clear)
            return;

        Position position = calculatePosition(canvas.getWidth(), canvas.getHeight());

        Log.d("figure", "calculating figure and output it");
        switch (this.option.Figure) {
            case CIRCLE:
                Log.d("canvas", "draw circle");
                canvas.drawCircle(position.X + 100, position.Y + 100, 100, p);
                break;
            case SQUARE:
                Log.d("canvas", "draw rectangle");
                canvas.drawRect(position.X, position.Y, position.X + 200, position.Y + 200, p);
                break;
            case RECTANGLE:
                Log.d("canvas", "draw square");
                canvas.drawRect(position.X, position.Y, position.X + 150, position.Y + 200, p);
                break;
        }
    }

}