package com.seek.smallgamedemo.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.seek.smallgamedemo.R;
import com.seek.smallgamedemo.model.Boy;
import com.seek.smallgamedemo.model.Face;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by admin on 2017/9/2.
 */

public class GameUI extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder mHolder;
    private boolean mIsDrawing;
    private Thread thread;

    private Canvas mCanvas;

    private Boy mBoy;
    private List<Face> mFaceList;

    public GameUI(Context context) {
        super(context);
        initView();
    }


    public GameUI(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);

        setFocusable(true);

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap boyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar_boy);
        mBoy = new Boy(boyBitmap, new Point(0,0));
        mFaceList = new CopyOnWriteArrayList<>();
        mIsDrawing = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing){
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
//
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            mCanvas.drawRect(0, 0, getWidth(), getHeight(), paint);

            if (mBoy != null){
                mBoy.drawSelf(mCanvas);
            }

//            if (mFace != null){
//                mFace.drawSelf(mCanvas);
//                mFace.move();
//            }

            for (Face face : mFaceList){
                face.drawSelf(mCanvas);
                face.move();

                if (face.point.x < 0 || face.point.x > getWidth() || face.point.y < 0 || face.point.y > getHeight()){
                    mFaceList.remove(face);
                }
            }
        }catch (Exception e){

        }finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    /**
     * 处理屏幕的点击事件
     * @param event
     */
    public void handleTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN://当按下的shih，创建笑脸
                Face face = mBoy.createFace(getContext(), new Point((int)(event.getRawX()), (int)(event.getRawY())));
                mFaceList.add(face);
                break;
            default:
                break;
        }
    }
}
