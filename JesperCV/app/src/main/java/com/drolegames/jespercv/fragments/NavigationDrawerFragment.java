package com.drolegames.jespercv.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.drolegames.jespercv.R;
import com.drolegames.jespercv.activities.ActivityCircleFun;
import com.drolegames.jespercv.listeners.RecycleClickListener;
import com.drolegames.jespercv.adapters.NavigationDrawerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.drolegames.jespercv.listeners.RecycleClickListenerInterface.ClickListener;

/**
 * Created by Jesper Turesson on 2015-01-23.
 */
public class NavigationDrawerFragment extends Fragment implements View.OnClickListener {
    private ActionBarDrawerToggle mDrawerToggle;

    private NavigationDrawerAdapter navigationDrawerAdapter;


    private View mContainerView;
    private DrawerLayout mDrawerLayout;

    /*
    * Fragments
    */
    private ApplicationsFragment applicationsFragment;
    private Fragment currentFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationsFragment = new ApplicationsFragment();


    }


    private void openApplications() {
        Fragment fragment = null;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fragment = applicationsFragment;
        currentFragment = fragment;
        if (fragment != null) {
            fm.beginTransaction().replace(R.id.main_fragment_holder, fragment).commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        RecyclerView topRecyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView_top);
        navigationDrawerAdapter = new NavigationDrawerAdapter(getActivity(), getRecyclerViewData());
        topRecyclerView.setAdapter(navigationDrawerAdapter);
        topRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        topRecyclerView.addOnItemTouchListener(new RecycleClickListener.RecycleTouchListener(getActivity(), topRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                changeFragment(position);
                mDrawerLayout.closeDrawers();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        initContact(layout);

        openApplications();
        return layout;

    }

    private void changeFragment(int position) {
        Fragment fragment = null;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        switch (position) {
            case 0:
                fragment = applicationsFragment;
                break;
            case 1:
                startActivity(new Intent(getActivity(),ActivityCircleFun.class));
             //   fragment = AboutMeFragment.getInstance();
        }
        if (fragment != null) {
            if (!currentFragment.equals(fragment)) {
                currentFragment = fragment;
                fm.beginTransaction().replace(R.id.main_fragment_holder, fragment).commit();
            }
        }
    }

    public List<String> getRecyclerViewData() {
        List<String> publications = new ArrayList<String>();
        String[] some_array = getResources().getStringArray(R.array.publication_titles);
        for (String s : some_array) {
            publications.add(s);
        }

        return publications;
    }

    private void initContact(View layout) {
        ImageView imageGmail = (ImageView) layout.findViewById(R.id.contact_me_gmail);
        ImageView imageLinkedin = (ImageView) layout.findViewById(R.id.contact_me_linkedin);
        imageLinkedin.setOnClickListener(this);
        imageGmail.setOnClickListener(this);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().supportInvalidateOptionsMenu();
                toolbar.setTitle(getFragmentName());

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().supportInvalidateOptionsMenu();
                toolbar.setTitle(getString(R.string.app_name));
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);


        resizeDrawer();


        mDrawerLayout.post(new Runnable() {

            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    private String getFragmentName() {
        if (currentFragment instanceof ApplicationsFragment) {
            return getString(R.string.applications);
        } else if (currentFragment instanceof AboutMeFragment) {
            return getString(R.string.about_me);
        } else {
            return getString(R.string.app_name);
        }

    }

    private void resizeDrawer() {
        /**
         * Resizing the navigation drawer
         *
         * NavWidth = ScreenWidth - toolbarHeight (56dp)
         */
        Resources r = getResources();
        int toolbarHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, r.getDisplayMetrics());
        int width = getResources().getDisplayMetrics().widthPixels - toolbarHeight;
        int maxDrawbarWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, r.getDisplayMetrics());
        if (width > maxDrawbarWidth) {
            width = maxDrawbarWidth;
        }
        android.support.v4.widget.DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) mContainerView.getLayoutParams();
        params.width = width;
        mContainerView.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_me_gmail:
                openMail();
                break;
            case R.id.contact_me_linkedin:
                sendUserToLinkedIn();
                break;
        }
    }

    private void openMail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        Resources res = getResources();
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"jturesson90@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, res.getString(R.string.email_hello_jesper));
        try {
            startActivity(Intent.createChooser(i, res.getString(R.string.email_contact_me)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), res.getString(R.string.email_error), Toast.LENGTH_LONG).show();
        }
    }

    private void sendUserToLinkedIn() {
        String myLinkedIn = "https://www.linkedin.com/profile/view?id=287232973";
        Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myLinkedIn));
        try {
            startActivity(linkedInIntent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), getResources().getString(R.string.linkedin_error), Toast.LENGTH_LONG).show();
        }

    }


}
