package com.drolegames.jespercv.custom.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jesper Turesson on 2015-04-12.
 */
public class ExpandingCircleView extends View {

    private SparseArray<PointF> mCurrentPointers;
    private SparseArray<PointF> mStartPointers;
    private Paint mPaint;

    private boolean mHasTouch = false;

    public ExpandingCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mCurrentPointers = new SparseArray<PointF>();
        mStartPointers = new SparseArray<PointF>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // set painter color to a color you like
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);
        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:
                mHasTouch = true;
            case MotionEvent.ACTION_POINTER_DOWN: {
                // We have a new pointer. Lets add it to the list of pointers
                PointF f = new PointF();
                PointF startF = new PointF();
                startF.x = event.getX(pointerIndex);
                startF.y = event.getY(pointerIndex);
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mStartPointers.put(pointerId, startF);
                mCurrentPointers.put(pointerId, f);
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    PointF point = mCurrentPointers.get(event.getPointerId(i));
                    if (point != null) {
                        point.x = event.getX(i);
                        point.y = event.getY(i);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
                mHasTouch = false;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                mStartPointers.remove(pointerId);
                mCurrentPointers.remove(pointerId);
                break;
            }
        }
        invalidate();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(mHasTouch){
            canvas.drawColor(Color.WHITE);
        }
        int len = mStartPointers.size() > mCurrentPointers.size() ? mCurrentPointers.size() : mStartPointers.size();
        for (int i = 0; i < len; i++) {
            PointF start = mStartPointers.valueAt(i);
            PointF end = mCurrentPointers.valueAt(i);
            final float size = (float) Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));

            float angle = (float) getAngle(start, end);
            float[] hsv = {angle, 1, 1};
            mPaint.setColor(Color.HSVToColor(hsv));
            canvas.drawCircle(start.x, start.y, size, mPaint);


        }

    }


    private double getAngle(PointF start, PointF end) {
        double theta = Math.atan2(end.y - start.y, end.x - start.x);

        theta += Math.PI / 2.0;

        double angle = Math.toDegrees(theta);

        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }
}

