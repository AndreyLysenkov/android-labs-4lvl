package edu.bmstu.stas.lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

class DrawView extends View {

    Paint paint;
    Canvas canvas;
    DrawOption option;


    public DrawView(Context context, DrawOption option) {
        super(context);

        this.paint = new Paint();
        this.paint.setColor(option.Color);
        this.option = option;
    }


    class Position {
        public int X;
        public int Y;
    }

    private Position calculatePosition(int width, int height) {
        Position result = new Position();

        int shiftX = 100;
        int shiftY = 100;
        int padding = 50;

        result.X = 0;
        switch (this.option.PositionX) {
            case LEFT:
                result.X = padding;
                break;
            case CENTER:
                result.X = width / 2 - shiftX;
                break;
            case RIGHT:
                result.X = width - shiftX * 2 - padding;
                break;
        }

        result.Y = 0;
        switch (this.option.PositionY) {
            case TOP:
                result.Y = padding;
                break;
            case CENTER:
                result.Y = height / 2 - shiftY;
                break;
            case BOTTOM:
                result.Y = height - shiftY * 2 - padding;
                break;
        }

        Log.d("figure", "calculated position as X:" + Integer.toString(result.X) +
                " and Y:" + Integer.toString(result.Y));
        return result;
    }

    private void drawTriangle(Canvas canvas, Position position) {
        // https://stackoverflow.com/questions/20544668/how-to-draw-filled-triangle-on-android-canvas/22690364

        Point p1 = new Point(position.X, position.Y);
        Point p2 = new Point(position.X + 200, position.Y + 100);
        Point p3 = new Point(position.X, position.Y + 200);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(p1.x, p1.y);
        //path.lineTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        canvas.drawPath(path, paint);

        /* TODO;
        */

    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        // clear screen
        this.canvas.drawARGB(255, 255, 255, 255);
        Log.d("canvas", "canvas cleared");

        Position position = calculatePosition(canvas.getWidth(), canvas.getHeight());

        Log.d("figure", "calculating figure and output it");
        switch (this.option.Figure) {
            case CIRCLE:
                Log.d("canvas", "draw circle");
                canvas.drawCircle(position.X + 100, position.Y + 100, 100, this.paint);
                break;
            case SQUARE:
                Log.d("canvas", "draw rectangle");
                canvas.drawRect(position.X, position.Y, position.X + 200, position.Y + 200, this.paint);
                break;
            case TRIANGLE:
                Log.d("canvas", "draw triangle");
                this.drawTriangle(canvas, position);
                break;
        }
    }

}