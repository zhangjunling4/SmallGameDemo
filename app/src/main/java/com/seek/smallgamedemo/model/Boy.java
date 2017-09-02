package com.seek.smallgamedemo.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.seek.smallgamedemo.R;

/**
 * Created by admin on 2017/9/2.
 */

public class Boy extends Sprite {
    public Boy(Bitmap defaultBitmap, Point point) {
        super(defaultBitmap, point);
    }

    public Face createFace(Context context, Point touchPoint){
        Bitmap faceBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rating_small);
        Face face = new Face(faceBitmap, new Point(point.x + 50, point.y + 50), touchPoint);
        return face;
    }
}
