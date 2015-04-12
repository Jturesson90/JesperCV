package com.drolegames.jespercv.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


import com.drolegames.jespercv.R;
import com.drolegames.jespercv.activities.MainActivity;

import com.drolegames.jespercv.models.GooglePlayApplication;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by Jesper Turesson on 2015-01-28.
 */
public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.ApplicationAdapterViewHolder> {
    private List<GooglePlayApplication> myApps;
    private LayoutInflater inflater;
    private static final String TAG = ApplicationsAdapter.class.getSimpleName();
    private Context mContext;


    public ApplicationsAdapter(Context context, List myApps) {
        this.myApps = myApps;
        inflater = LayoutInflater.from(context);
        mContext = context;
        Log.d(TAG, "Constructor Complete");
    }

    @Override
    public ApplicationAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.list_item_application, viewGroup, false);
        ApplicationAdapterViewHolder holder = new ApplicationAdapterViewHolder(view);
        Log.d(TAG, "onCreateViewHolder");

        return holder;

    }

    @Override
    public void onBindViewHolder(final ApplicationAdapterViewHolder applicationAdapterViewHolder, final int i) {

        int width = MainActivity.SCREEN_WIDTH_IN_DP;
        Picasso.with(mContext).load(myApps.get(i).imageResource).resize(width, width).centerInside().into(applicationAdapterViewHolder.imageView);
    }


    @Override
    public int getItemCount() {
        return myApps.size();
    }

    class ApplicationAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ApplicationAdapterViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ApplicationAdapterViewHolder");
            imageView = (ImageView) itemView.findViewById(R.id.application_image);
            Log.d("IMAGE", "W: " + imageView.getWidth() + "  H: " + imageView.getHeight());
        }
    }


}

