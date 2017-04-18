package cs1635.g8.hello;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.*;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Toolbar;
//import android.util.Log;

public class fhome_Screen extends Fragment {

    private String[] pageTitle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;

    private static final String TAG_FRAGMENT = fhome_Screen.class.getSimpleName();
    Context c;

    public fhome_Screen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerAdapter = new ViewPagerAdapter(getFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fhome_screen, container, false);
        c = v.getContext();
        pagerAdapter.notifyDataSetChanged();
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        pageTitle = getResources().getStringArray(R.array.tabs_array);

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        for (int i = 0; i < 2; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //set viewpager adapter
        viewPager.setAdapter(pagerAdapter);


        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Do nothing
            }
        });

        pagerAdapter.notifyDataSetChanged();
        return v;
    }


}
