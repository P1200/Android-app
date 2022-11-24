package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class AppIntro extends View {

    private final static Paint paint1 = new Paint(),
            paint2 = new Paint(),
            paint3 = new Paint(),
            paint = new Paint();
    private final static Path path = new Path();

    private Rect rect, rect2;

    public AppIntro(Context context)
    {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canva)
    {
        super.onDraw(canva);
        int vWidth = getWidth();
        int vHeight = getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;
        rect = new Rect(vWidth/2,getTop(),getRight(),getBottom());
        rect2 = new Rect(getLeft(),getTop(),vWidth/2,getBottom());
        paint1.setColor(Color.BLUE);
        paint1.setStrokeWidth(100);
        paint1.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.YELLOW);
        paint2.setStrokeWidth(200);
        paint2.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.RED);
        paint3.setStrokeWidth(100);
        paint3.setStyle(Paint.Style.FILL);
        canva.drawRect(rect,paint2);
        canva.drawRect(rect2,paint3);

        Point point1 = new Point(halfWidth, 0);
        Point point2 = new Point(halfWidth-80, 380);
        Point point3 = new Point(halfWidth+80, 380);
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point1.x, point1.y);
        path.close();
        canva.drawPath(path, paint2);


        canva.drawCircle(halfWidth, halfHeight, halfWidth / 3f, paint2);
        canva.drawCircle(halfWidth, halfHeight, halfWidth / 3f-40, paint1);

        paint.setColor(Color.BLUE);
        paint.setTextSize(120);
        // draw some text using FILL style
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(true);
        //turn antialiasing on
        paint.setAntiAlias(true);
        canva.drawText("NoteExtended", 150, halfHeight-270, paint);

    }
}
