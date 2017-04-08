package cs1635.g8.hello;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cs1635.g8.hello.R;

public class fsettings extends Fragment {

    Context c;

    public fsettings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        c = v.getContext();
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
//        String name = getArguments().getString("Name");
        TextView tv = (TextView)v.findViewById(R.id.tv);
//
        tv.setText("Work In Progress");
    }

}
