package com.example.chris.assignment1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;


/**
 * Created by chris on 3/16/2018.
 */

public class drawCircles extends View {

    float x, y;
    int radius;
    Paint paint = new Paint();
    Random rand = new Random();
    PointF pointf = new PointF();
    ArrayList<PointF> locations = new ArrayList<PointF>();
    MainActivity main = new MainActivity();



    public drawCircles(Context context) {
        super(context);
    }

    public drawCircles(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public drawCircles(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setRadius(int radius) {
        this.radius = radius;
   }

    public int getRadius() {
        return radius;
    }

    public ArrayList<PointF> getLocations() {
        return locations;
    }

    public void setPaint()
    {
        int n = rand.nextInt(4)+1;

            switch (n) {
                case 1:
                    paint.setColor(GREEN);
                    break;

                case 2:
                    paint.setColor(BLUE);
                    break;

                case 3:
                    paint.setColor(RED);
                    break;

                case 4:
                    paint.setColor(YELLOW);
                    break;

            }

    }

    public Paint getPaint() {
        return paint;
    }

    @Override
     public boolean onTouchEvent(MotionEvent event) {

            //code to execute when more than 1 pointer is detected
            if(event.getPointerCount() > 1)
            {
                for(int index = 0;index < event.getPointerCount(); index++)
                {
                    int pointerIndex = event.findPointerIndex(index);
                    x = event.getX(pointerIndex);
                    y = event.getY(pointerIndex);
                    pointf = new PointF(x,y);
                    locations.add(pointf);
                }
                 invalidate();


            }

            //use old code for single pointer event
            else
            {
                x = event.getX();
                y = event.getY();
                pointf = new PointF(x, y);
                locations.add(pointf);
                invalidate();
                return true;
            }


        return true;

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = 0;

        while (i < locations.size())
        {
            paint.setColor(BLUE);

            //calls setPaint to set a random colour once the random colours button has been clicked
            if(main.getRandomColoursCheck() == true)
            {
                setPaint();
            }

            canvas.drawCircle(locations.get(i).x, locations.get(i).y, getRadius(), getPaint());
            i = i + 1;

        }


    }
}
