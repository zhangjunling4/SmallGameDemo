package com.seek.smallgamedemo.model;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by admin on 2017/9/2.
 */

public class Face extends Sprite {
    private int speed = 6;
    private int tempX;
    private int tempY;

    public Face(Bitmap defaultBitmap, Point position, Point toutchPosition) {
        super(defaultBitmap, position);

        int x = toutchPosition.x - position.x;
        int y = toutchPosition.y - position.y;

        int d = (int) Math.sqrt(x*x+y*y);

        tempX = speed * x / d;
        tempY = speed * y / d;


    }

    public void move(){
        this.point.x += tempX;
        this.point.y += tempY;
    }
}
