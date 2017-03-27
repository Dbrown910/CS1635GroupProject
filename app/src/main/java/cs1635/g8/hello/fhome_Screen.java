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

public class fhome_Screen extends Fragment {

    Context c;

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

        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        setConnectedUserList((LinearLayout) v.findViewById(R.id.topLL));
        setUnknownUserList((LinearLayout)v.findViewById(R.id.botLL));
    }

    private void setConnectedUserList(LinearLayout ll) {

        String[] user_list = getResources().getStringArray(R.array.known_users);

        // Add text
        for(int i = 0; i < user_list.length; i++) {
            // Create a LinearLayout element
            LinearLayout row = new LinearLayout(c);
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(params);
            row.setOrientation(LinearLayout.HORIZONTAL);

            TextView tv = new TextView(c);
            tv.setText(user_list[i]);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.weight=1.0f;
            tv.setLayoutParams(params2);
            Button btn = new Button(c);
            btn.setText("Share");
            btn.setBackgroundColor(Color.YELLOW);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btn.setLayoutParams(params3);
            row.addView(tv);
            row.addView(btn);

            ll.addView(row);
        }
    }

    private void setUnknownUserList(LinearLayout ll) {
        String[] user_list = getResources().getStringArray(R.array.unk_users);

        // Add text
        for(int i = 0; i < user_list.length; i++) {
            // Create a LinearLayout element
            LinearLayout row = new LinearLayout(c);
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(params);
            row.setOrientation(LinearLayout.HORIZONTAL);

            TextView tv = new TextView(c);
            tv.setText(user_list[i]);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.weight=1.0f;
            tv.setLayoutParams(params2);
            Button btn = new Button(c);
            btn.setText("Share");
            btn.setBackgroundColor(Color.YELLOW);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btn.setLayoutParams(params3);
            row.addView(tv);
            row.addView(btn);

            ll.addView(row);
        }
    }
}