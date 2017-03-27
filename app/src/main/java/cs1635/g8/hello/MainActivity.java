package cs1635.g8.hello;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    private String[] mOptions;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(new fhome_Screen());

        mOptions = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer);

        /*mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            *//** Called when a drawer has settled in a completely closed state. *//*
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            *//** Called when a drawer has settled in a completely open state. *//*
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mOptions));
        mDrawerList.setOnItemClickListener(new MainActivity.DrawerItemClickListener());

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int stackHeight = getSupportFragmentManager().getBackStackEntryCount();
                if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
                    getSupportActionBar().setHomeButtonEnabled(true);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setHomeButtonEnabled(false);
                }
            }

        });

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


   /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.[option for current fragment]).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }*/

    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void selectItem(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if(getSupportFragmentManager().findFragmentById(R.id.frag_user_profile) != null)
                    getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new fuser_profile()).commit();

                ft.replace(R.id.content_frame, new fuser_profile());
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 1:
                if(getSupportFragmentManager().findFragmentById(R.id.test_frag) != null)
                    getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new TestFragment()).commit();

                ft.replace(R.id.content_frame, new TestFragment());
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }
}
