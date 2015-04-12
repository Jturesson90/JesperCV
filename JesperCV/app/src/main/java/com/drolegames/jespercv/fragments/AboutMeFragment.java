package com.drolegames.jespercv.fragments;


import android.app.Activity;
import android.os.AsyncTask;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;


import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


import com.drolegames.jespercv.R;
import com.drolegames.jespercv.adapters.AboutMeCVAdapter;
import com.drolegames.jespercv.custom.layouts.CustomLinearLayoutManager;
import com.drolegames.jespercv.custom.layouts.SlidingTabLayout;
import com.drolegames.jespercv.listeners.RecycleClickListener;
import com.drolegames.jespercv.listeners.RecycleClickListenerInterface;
import com.drolegames.jespercv.models.CVCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper Turesson on 2015-02-03.
 */
public class AboutMeFragment extends Fragment {

    private static final String TAG = AboutMeFragment.class.getSimpleName();

    private Button testButton;
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private ScrollView mScrollView;
    private com.nirhart.parallaxscroll.views.ParallaxScrollView myScrollView;
    public static AboutMePagerAdapter aboutMePagerAdapter;
    private static AboutMeFragment aboutMeFragment;

    private int tabStartPos = 0;

    public static AboutMeFragment getInstance() {

        if (aboutMeFragment == null) {
            aboutMeFragment = new AboutMeFragment();
        }

        return aboutMeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView ");
        View view = inflater.inflate(R.layout.fragment_about_me, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.about_me_viewpager);

        testButton = (Button) view.findViewById(R.id.about_me_test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        aboutMePagerAdapter = new AboutMePagerAdapter(getActivity().getSupportFragmentManager());

        mViewPager.setAdapter(aboutMePagerAdapter);
        initTabs(view);
        initScrollView(view);
        new SetAdapterTask().execute();
        return view;

    }

    private void initScrollView(View view) {
        myScrollView = (com.nirhart.parallaxscroll.views.ParallaxScrollView) view.findViewById(R.id.about_me_scrollview);

        myScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                moveTabBarIfNeeded();

            }
        });


    }

    private void moveTabBarIfNeeded() {
        ViewGroup viewsHolder = (ViewGroup) myScrollView.getChildAt(0);

        com.drolegames.jespercv.custom.CustomViewPager scroll = (com.drolegames.jespercv.custom.CustomViewPager) viewsHolder.getChildAt(1);
        int offset = 5;
        int[] scrollVals = new int[2];
        scroll.getLocationOnScreen(scrollVals);
        int tabBarHeight = mSlidingTabLayout.getHeight();
        if (tabStartPos != 0) {
            int scrollY = scrollVals[1];

            android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mSlidingTabLayout.getLayoutParams();
            int topMargin = clamp(scrollY - (tabStartPos + tabBarHeight - offset), -tabBarHeight, 0);
            if (params.topMargin != topMargin) {
                params.topMargin = topMargin;
                mSlidingTabLayout.requestLayout();
            }


        } else {
            int[] tabValues = new int[2];
            mSlidingTabLayout.getLocationOnScreen(tabValues);
            tabStartPos = tabValues[1];
        }
    }

    private int clamp(int val, int min, int max) {
        int valToReturn;
        if (val < min) {
            valToReturn = min;
        } else if (val > max) {
            valToReturn = max;
        } else {
            valToReturn = val;
        }
        return valToReturn;

    }

    private void initTabs(View view) {
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.about_me_slidingtab);
        mSlidingTabLayout.setDistributeEvenly(true);

        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.orange_500));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_bar_view, R.id.custom_tab_textview);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    public class AboutMePagerAdapter extends FragmentStatePagerAdapter {
        private final static String TAG = "AboutMePagerAdapter";
        private final static int NUM_OF_TABS = 2;
        String[] tabTitles;
        private View mCurrentView;

        public AboutMePagerAdapter(FragmentManager fm) {
            super(fm);
            tabTitles = getResources().getStringArray(R.array.tabs);
            Log.d(TAG, "New AboutMePagerAdapter. ");


        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }

        @Override
        public Fragment getItem(int position) {
            InfoFragment innerfragment = InfoFragment.getInstance(position);
            Log.d(TAG, "getItem. " + position);
            return innerfragment;
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);

        }


        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(TAG, "getPageTitle. " + tabTitles[position]);
            return tabTitles[position];
   
        }

        @Override
        public int getCount() {
            return NUM_OF_TABS;
        }


    }

    public static class InfoFragment extends Fragment {
        private static final String TAG = InfoFragment.class.getSimpleName();


        public static InfoFragment getInstance(int position) {
            InfoFragment infoFragment = new InfoFragment();
            Log.d(TAG, "New InnerFragment. Pos: " + position);
            Bundle args = new Bundle();
            args.putInt("position", position);
            infoFragment.setArguments(args);

            return infoFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view;
            Log.d(TAG, "onCreateView");
            Bundle bundle = getArguments();
            if (bundle != null) {
                switch (bundle.getInt("position", 0)) {
                    case 0:
                        view = inflater.inflate(R.layout.fragment_about_me_cv, container, false);
                        createRecyclerView(view);

                        break;
                    case 1:
                        view = inflater.inflate(R.layout.fragment_about_me_cover_letter, container, false);

                        break;
                    default:
                        view = inflater.inflate(R.layout.fragment_about_me_cover_letter, container, false);
                        break;
                }
            } else {
                view = inflater.inflate(R.layout.fragment_about_me_cover_letter, container, false);
            }
            return view;
        }

        RecyclerView cvRecyclerView;
        AboutMeCVAdapter aboutMeCVAdapter;

        private void createRecyclerView(View view) {
            aboutMeCVAdapter = new AboutMeCVAdapter(getActivity(), getCVData());


            cvRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_cv_recycler_view);
            cvRecyclerView.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            cvRecyclerView.setLayoutManager(llm);
            cvRecyclerView.setItemAnimator(new DefaultItemAnimator());
            // cvRecyclerView.setLayoutManager(new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


            cvRecyclerView.addOnItemTouchListener(new RecycleClickListener.RecycleTouchListener(getActivity(), cvRecyclerView, new RecycleClickListenerInterface.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    handleRecyclerClick(view, position);


                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
            cvRecyclerView.setAdapter(aboutMeCVAdapter);


        }

        private void handleRecyclerClick(View view, int position) {
            // Toast.makeText(getActivity(), aboutMeCVAdapter.cvCategoryList.get(position).title, Toast.LENGTH_LONG).show();
            TextView tv = (TextView) view.findViewById(R.id.textViewdd);

            if (tv.getVisibility() == View.VISIBLE) {
                tv.setVisibility(View.GONE);
            } else {
                tv.setVisibility(View.VISIBLE);
            }
        }

        private List<CVCategory> getCVData() {
            List<CVCategory> cvCategories = new ArrayList<>();
            String[] titles = getResources().getStringArray(R.array.cv_headers);
            int len = titles.length;
            for (int i = 0; i < len; i++) {
                CVCategory cvCategory = new CVCategory();
                cvCategory.title = titles[i];
                cvCategories.add(cvCategory);
            }
            return cvCategories;
        }


    }


    private class SetAdapterTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mViewPager.setAdapter(aboutMePagerAdapter);

        }
    }
}
