package com.drolegames.jespercv.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.drolegames.jespercv.fragments.AboutMeFragment;

import java.util.Random;

/*
* This Custom View pager makes it possible to set layout_height to wrap_content
*/

public class CustomViewPager extends ViewPager {
    private AboutMeFragment.AboutMePagerAdapter mAdapter;

    public CustomViewPager(Context context) {
        super(context);
        mAdapter = AboutMeFragment.aboutMePagerAdapter;
    }

    public CustomViewPager(Context context, AttributeSet attrs) {

        super(context, attrs);
        mAdapter = AboutMeFragment.aboutMePagerAdapter;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        Log.d("VIEWPAGER","Current item pos is = "+this.getCurrentItem());
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {

            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

            int h = child.getMeasuredHeight();

            if (h > height) height = h;


        }
        //  Log.d("VIEWPAGER", "child2 Height: " + child2.getMeasuredHeight());

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
/*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mAdapter == null) mAdapter = AboutMeFragment.aboutMePagerAdapter;
        int height = 0;
        Log.d("VIEWPAGER","Current item pos is = "+this.getCurrentItem());
        View v = mAdapter.getCurrentPosition();
        if (v != null) {
            v.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = v.getMeasuredHeight();
            Log.d("VIEWPAGER", "getCurrentItem FOUND");
        }else {
            Log.d("VIEWPAGER", "getCurrentItem is null");
        }
        Log.d("VIEWPAGER", "Final Height: " + height);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }*/
}