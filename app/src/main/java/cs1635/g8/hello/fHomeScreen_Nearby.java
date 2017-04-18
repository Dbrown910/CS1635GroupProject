package cs1635.g8.hello;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
//import android.util.Log;

public class fHomeScreen_Nearby extends Fragment {
    private static final String TAG = fHomeScreen_Nearby.class.getSimpleName();
    Context c;
    LinearLayout ll;

    public fHomeScreen_Nearby() {
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
        View v = inflater.inflate(R.layout.fragment_nearby, container, false);
        c = v.getContext();
        ll = (LinearLayout) v.findViewById(R.id.nearbyLL);

        //Populate user_list
        setNearbyUserList(ll);

        return v;
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
    }



    private void setNearbyUserList(LinearLayout ll) {
        //Populate user_list
        String[] user_list = getResources().getStringArray(R.array.known_users);
        for(int i = 0; i < user_list.length; i++) {
            // Create a LinearLayout element
            LinearLayout row = new LinearLayout(c);
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(params);
            row.setOrientation(LinearLayout.HORIZONTAL);

            ImageView iv = new ImageView(c);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv.setImageResource(R.mipmap.ic_person);
            iv.setLayoutParams(params);

            TextView tv = new TextView(c);
            tv.setText(user_list[i]);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
            params2.weight=1.0f;
            tv.setLayoutParams(params2);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            //tv.setTextSize(getResources().getDimension(R.dimen.fab_margin));
            row.addView(iv);
            row.addView(tv);

            ll.addView(row);
        }
    }
}
