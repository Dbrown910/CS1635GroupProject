package cs1635.g8.hello;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class FhomeScreen extends Fragment {

    Context c;

    public FhomeScreen() {
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
        ScrollView sv;

        sv = (ScrollView)v.findViewById(R.id.topSV);
        sv.addView(setConnectedUserList((LinearLayout)v.findViewById(R.id.topLL)));
        sv = (ScrollView)v.findViewById(R.id.botSV);
        sv.addView(setUnknownUserList((LinearLayout)v.findViewById(R.id.botLL)));

        return v;
    }


    private LinearLayout setConnectedUserList(LinearLayout ll) {

        String[] user_list = getResources().getStringArray(R.array.known_users);

        // Create a LinearLayout element
        LinearLayout row = new LinearLayout(c);
        row.setOrientation(LinearLayout.HORIZONTAL);

        // Add text
        for(int i = 0; i < user_list.length; i++) {
            TextView tv = new TextView(c);
            tv.setText(user_list[i]);
            Button btn = new Button(c);
            btn.setText("Share");
            btn.setBackgroundColor(Color.YELLOW);
            btn.setGravity(Gravity.RIGHT);
            ll.addView(tv);
            ll.addView(btn);
        }

        return ll;
    }

    private LinearLayout setUnknownUserList(LinearLayout ll) {
        String[] user_list = getResources().getStringArray(R.array.unk_users);

        // Create a LinearLayout element
        LinearLayout row = new LinearLayout(c);
        row.setOrientation(LinearLayout.HORIZONTAL);

        // Add text
        for(int i = 0; i < user_list.length; i++) {
            TextView tv = new TextView(c);
            tv.setText(user_list[i]);
            Button btn = new Button(c);
            btn.setText("Share");
            btn.setBackgroundColor(Color.YELLOW);
            btn.setGravity(Gravity.RIGHT);
            ll.addView(tv);
            ll.addView(btn);
        }

        return ll;
    }
}
