package com.drolegames.jespercv.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drolegames.jespercv.R;
import com.drolegames.jespercv.models.CVCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jesper Turesson on 2015-02-10.
 */
public class AboutMeCVAdapter extends RecyclerView.Adapter<AboutMeCVAdapter.AboutMeCVAdapterHolder> {
    private static final String TAG = AboutMeCVAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private Context mContext;
    public List<CVCategory> cvCategoryList = Collections.emptyList();

    public AboutMeCVAdapter(Context context, List<CVCategory> cvCategoryList) {
        this.cvCategoryList = cvCategoryList;

        inflater = LayoutInflater.from(context);
        mContext = context;
        Log.d("TESTING", "constructor");
    }

    @Override
    public AboutMeCVAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_cv, parent, false);
        AboutMeCVAdapterHolder holder = new AboutMeCVAdapterHolder(view);
        Log.d("TESTING", "oncreateViewHolder");
        return holder;
    }

    public void removeAt(int position) {
        cvCategoryList.remove(position);
        notifyItemRemoved(position);
        Log.d("REMOVE", "cvCatergoryList size is " + cvCategoryList.size());
    }

    @Override
    public void onBindViewHolder(AboutMeCVAdapterHolder holder, int position) {
        holder.title.setText(cvCategoryList.get(position).title);
        Log.d("TESTING", "Binding");
    }

    @Override
    public int getItemCount() {
        Log.d("TESTING", "getItemCount, returns " + cvCategoryList.size());
        return cvCategoryList.size();
    }

    class AboutMeCVAdapterHolder extends RecyclerView.ViewHolder {
        TextView title;

        public AboutMeCVAdapterHolder(View itemView) {
            super(itemView);
            Log.d("TESTING", "ITEMVIEW");
            title = (TextView) itemView.findViewById(R.id.cv_row_title);
        }
    }
}
