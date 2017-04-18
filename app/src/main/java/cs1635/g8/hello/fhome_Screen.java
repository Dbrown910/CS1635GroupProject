package cs1635.g8.hello;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
//import android.util.Log;

public class fhome_Screen extends Fragment {
    private static final String TAG = fhome_Screen.class.getSimpleName();
    Context c;

    private ViewPager mViewPager;
    private CustAdapter mCustAdapter;

    public fhome_Screen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fhome_screen, container, false);
        c = v.getContext();

        mCustAdapter = new CustAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustAdapter);
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        mCustAdapter = new CustAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustAdapter);

        //TODO: set the tab frame with the known user list by default

        //final ActionBar ab = getActivity().getSupport;
    }


    public static class CustAdapter extends FragmentPagerAdapter {
        FragmentManager fm;
        public CustAdapter(FragmentManager _fm) {
            super(_fm);
            fm = _fm;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new fKnown_Users();
                case 1:
                    return new fUnknown_Users();
            }

            return null;
        }
    }
}