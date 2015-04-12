package com.drolegames.jespercv.listeners;

import android.view.View;

/**
 * Created by Jesper Turesson on 2015-01-29.
 */
public interface RecycleClickListenerInterface {
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

}
