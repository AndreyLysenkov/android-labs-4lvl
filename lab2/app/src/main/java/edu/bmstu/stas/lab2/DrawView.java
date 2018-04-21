package edu.bmstu.stas.lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private Position CalculatePosition() {
        Position result = new Position();
        DrawOption.ePosition position = this.option.Position;

        if (position == DrawOption.ePosition.CENTER) {
            result.Y = 800;
            result.X = 400;
        }

        if (position == DrawOption.ePosition.TOP) {
            result.Y = 0;
            result.X = 400;
        }

        if (position == DrawOption.ePosition.BOTTOM) {
            result.Y = 1500;
            result.X = 400;
        }

        if (position == DrawOption.ePosition.LEFT) {
            result.Y = 800;
            result.X = 0;
        }

        if (position == DrawOption.ePosition.RIGHT) {
            result.Y = 800;
            result.X = 800;
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        // clear screen
        this.canvas.drawARGB(255, 255, 255, 255);

        Position position = CalculatePosition();

        if (this.option.Clear)
            return;

        switch (this.option.Figure) {
            case CIRCLE:
                canvas.drawCircle(position.X + 100, position.Y + 100, 100, p);
                break;
            case SQUARE:
                canvas.drawRect(position.X, position.Y, position.X + 200, position.Y + 200, p);
                break;
            case RECTANGLE:
                canvas.drawRect(position.X, position.Y, position.X + 150, position.Y + 200, p);
                break;
        }
    }

}