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

public class fHomeScreen_Unknown extends Fragment {
    private static final String TAG = fHomeScreen_Unknown.class.getSimpleName();
    Context c;

    public fHomeScreen_Unknown() {
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
        View v = inflater.inflate(R.layout.fragment_unknown, container, false);
        c = v.getContext();
        setUnknownUserList((LinearLayout)v.findViewById(R.id.unknownLL));
        return v;
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        setUnknownUserList((LinearLayout)v.findViewById(R.id.unknownLL));
    }

    private void setUnknownUserList(LinearLayout ll) {
        final String[] user_list = getResources().getStringArray(R.array.unk_users);
        // Add text
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
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.weight=1.0f;
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setLayoutParams(params2);

            Button btn = new Button(c);
            btn.setText("Share");
            btn.setBackgroundResource(R.drawable.sharebtn);
            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Log.d(TAG, "onClick: " + user_list[finalI]);
                    final Context context = getContext();
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle(R.string.share_success_title);

                    alertDialogBuilder
                            .setMessage("Are you sure you want to share your contact information with" + " " + user_list[finalI] + "?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id){
                                    //Initiate contact share
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id){
                                    // :(
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btn.setLayoutParams(params3);
            row.addView(iv);
            row.addView(tv);
            row.addView(btn);

            ll.addView(row);
        }
    }
}