package com.drolegames.jespercv.custom.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.drolegames.jespercv.R;

/**
 * Created by Jesper Turesson on 2015-01-25.
 */
public class RelativeLayoutNavigationDrawerTop extends RelativeLayout {

    private float aspect = 1.0f;

    // .. alternative constructors omitted

    public RelativeLayoutNavigationDrawerTop(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RelativeLayoutNavigationDrawerTop);
        aspect = a.getFloat(R.styleable.RelativeLayoutNavigationDrawerTop_aspectRatio, 1.0f);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("ASPECT","1");
        if (w == 0) {
            h = 0;
        } else if (h / w < aspect) {
            w = (int) (h / aspect);
        } else {
            h = (int) (w * aspect);
        }

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(w,
                        MeasureSpec.getMode(widthMeasureSpec)),
                MeasureSpec.makeMeasureSpec(h,
                        MeasureSpec.getMode(heightMeasureSpec)));
    }

}
