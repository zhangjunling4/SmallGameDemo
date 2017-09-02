package com.seek.smallgamedemo.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * 精灵 最小单位的基类
 *
 * Created by admin on 2017/9/2.
 */

public abstract class Sprite {
    protected Bitmap defaultBitmap;
    public Point point;

    public Sprite(Bitmap defaultBitmap, Point point) {
        super();
        this.defaultBitmap = defaultBitmap;
        this.point = point;
    }

    public void drawSelf(Canvas canvas){
        canvas.drawBitmap(defaultBitmap, point.x, point.y, null);
    }
}
