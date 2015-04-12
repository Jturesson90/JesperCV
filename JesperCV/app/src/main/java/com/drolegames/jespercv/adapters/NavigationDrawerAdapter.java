package com.drolegames.jespercv.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drolegames.jespercv.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jesper Turesson on 2015-01-25.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavigationViewHolder> {


    private LayoutInflater inflater;
    List<String> titles = Collections.emptyList();
    private static final String TAG = "ApplicationsAdapter";

    public NavigationDrawerAdapter(Context context, List<String> titles) {
        inflater = LayoutInflater.from(context);
        this.titles = titles;
        Log.d(TAG, "NavigationDrawerAdapter Constructor Complete");
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = inflater.inflate(R.layout.list_item_navigation_drawer, viewGroup, false);

        NavigationViewHolder holder = new NavigationViewHolder(view);
        Log.d(TAG, "NavigationDrawerAdapter onCreateViewHolder");
        return holder;
    }

    @Override
    public void onBindViewHolder(NavigationViewHolder navigationViewHolder, int i) {
        navigationViewHolder.title.setText(titles.get(i));
        Log.d(TAG, "NavigationDrawerAdapter onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    class NavigationViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public NavigationViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.navigation_drawer_clickable_title);
        }


    }
}




