package cs1635.g8.hello;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String[] mOptions;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        setFragment(new fhome_Screen());

//        createNearbyNotification();
//        createShareRequestNotification();
    }


    //Launches new fragment when an option is selected inside the navigation drawer
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home_screen) {
            setFragment(new fhome_Screen());
        }else if(id == R.id.my_profile) {
            setFragment(new fuser_profile());
        }else if(id == R.id.settings) {
            setFragment(new fsettings());
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Closes navigation drawer if back is pressed while the drawer is open, otherwise default
    public void onBackPressed() {
        assert mDrawerLayout != null;
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void createNearbyNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.sharebtn)
                .setContentTitle("Hello! Nearby contact")
                .setContentText("Rachel is nearby");

        Intent openThisIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        openThisIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        notificationBuilder.setContentIntent(resultPendingIntent);
        final Notification notification = notificationBuilder.build();
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch(InterruptedException exception) {
                    System.out.println("nearby notification thread interrupted");
                }

                manager.notify(1, notification);

            }
        });

        thread.start();
    }

    private void createShareRequestNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.sharebtn)
                .setContentTitle("Hello! Share Request")
                .setContentText("Daniel wants to share their contact info");

        Intent shareInfoIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        shareInfoIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        notificationBuilder.setContentIntent(resultPendingIntent);
        final Notification notification = notificationBuilder.build();
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch(InterruptedException exception) {
                    System.out.println("share request thread interrupted");
                }

                manager.notify(2, notification);

            }
        });

        thread.start();

    }
}
