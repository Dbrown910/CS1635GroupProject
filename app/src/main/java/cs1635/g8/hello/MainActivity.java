package cs1635.g8.hello;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;

import layout.fuser_profile;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private String[] mOptions;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOptions = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer);
        mTitle = "Test title";

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mOptions));

        mDrawerList.setOnItemClickListener(new MainActivity.DrawerItemClickListener());

        FragmentManager fragmentManager = 
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                if(fragmentManager.findFragmentById(R.id.frag_user_profile) != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentById(R.id.frag_user_profile));
                } else {
                    fragmentManager.beginTransaction().add(R.id.content_frame, new fuser_profile(), "frag_user_profile").commit();
                }
                if (fragmentManager.findFragmentById(R.id.test_frag)  != null){
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentById(R.id.test_frag));
                }
                break;
            case 1:
                if(fragmentManager.findFragmentById(R.id.test_frag) != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentById(R.id.test_frag));
                } else {
                    fragmentManager.beginTransaction().add(R.id.content_frame, new fuser_profile(), "test_frag").commit();
                }
                if (fragmentManager.findFragmentById(R.id.frag_user_profile)  != null){
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentById(R.id.frag_user_profile));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag_user_profile);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
