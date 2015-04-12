package com.drolegames.jespercv.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drolegames.jespercv.R;
import com.drolegames.jespercv.listeners.RecycleClickListener;
import com.drolegames.jespercv.listeners.RecycleClickListenerInterface;
import com.drolegames.jespercv.adapters.ApplicationsAdapter;
import com.drolegames.jespercv.models.GooglePlayApplication;

import java.util.ArrayList;
import java.util.List;

import static android.net.Uri.*;


public class ApplicationsFragment extends Fragment {
    private ViewGroup mContainerView;
    private View mLayout;

    private List<GooglePlayApplication> myApps;
    private RecyclerView recyclerView;
    private ApplicationsAdapter applicationsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

    }

    private void getData() {
        myApps = new ArrayList<>();
        Resources res = getActivity().getResources();
        TypedArray images = getResources().obtainTypedArray(R.array.app_image_resources);
        String[] packageNames = res.getStringArray(R.array.app_packagenames);

        for (int i = 0; i < images.length(); i++) {
            GooglePlayApplication app = new GooglePlayApplication(images.getResourceId(i, -1), packageNames[i]);
            myApps.add(app);
        }
        images.recycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout;
        if (savedInstanceState == null) {
            layout = inflater.inflate(R.layout.fragment_applications, container, false);
            recyclerView = (RecyclerView) layout.findViewById(R.id.application_recycle);
            applicationsAdapter = new ApplicationsAdapter(getActivity(), myApps);
            recyclerView.setAdapter(applicationsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            addTouchListener(recyclerView);
            mContainerView = container;
            mLayout = layout;
        } else {
            layout = mLayout;
        }


        return layout;
    }

    private void addTouchListener(RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(new RecycleClickListener.RecycleTouchListener(getActivity(), recyclerView, new RecycleClickListenerInterface.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                sendToGooglePlay(myApps.get(position).packageName);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void sendToGooglePlay(String packageName) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, parse("http://play.google.com/store/apps/details?id=" + packageName)));
        }

    }
}