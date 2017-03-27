package cs1635.g8.hello;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import layout.TestFragment;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
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
        mTitle = "Test title";

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mOptions));

        mDrawerList.setOnItemClickListener(new MainActivity.DrawerItemClickListener());


    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag_user_profile);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

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
        }
    }
}
