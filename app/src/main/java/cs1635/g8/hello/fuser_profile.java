package cs1635.g8.hello;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.widget.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class fuser_profile extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    Context c;
    Button save_btn;
    ImageButton pro_pic;

    public fuser_profile() {
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
        View v = inflater.inflate(R.layout.fragment_fuser_profile, container, false);

        c = v.getContext();

        pro_pic = (ImageButton) v.findViewById(R.id.imageButton);
        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        save_btn = (Button) v.findViewById(R.id.button);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

//      if(profile_info.containsKey("Name"))
//          save_btn.setEnabled(false);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK){
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(c.getContentResolver(), selectedImage);
                setProfilePicture(bitmap);

                Log.d("GET_IMAGE", "success");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                Log.d("GET_IMAGE", "failed");
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.d("GET_IMAGE", "failed");
                e.printStackTrace();
            }
        }

    }

    private void saveInfo(){
        LinearLayout ll = (LinearLayout)getActivity().findViewById(R.id.textFields);
        Log.d("Were", "in save info");

        if(ll != null) {
            EditText name = (EditText) ll.getChildAt(0);
            EditText phNum = (EditText) ll.getChildAt(1);

            if (name.getText().toString().equals("") || phNum.getText().toString().equals("")) {
                AlertDialog.Builder adBuild = new AlertDialog.Builder(getContext());

                adBuild.setTitle("Missing Info");

                adBuild
                        .setMessage("Please enter at least a name and phone number")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog ad = adBuild.create();

                ad.show();
            } else {
                Log.d("Were", "here");
                for (int i = 0; i < ll.getChildCount(); i++) {
                    EditText tb = (EditText) ll.getChildAt(i);
                    if (tb instanceof EditText) {
                        //TODO: send to text file
                        Log.d("SAVED", tb.getTag().toString()+ "," + tb.getText().toString());
                    }
                }

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new fhome_Screen())
                        .commit();
            }
        }
    }

    public void setProfilePicture(Bitmap bmap) {
        ImageButton btn;
        btn = (ImageButton) getActivity().findViewById(R.id.imageButton);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmap);
        btn.setBackground(bitmapDrawable);
    }
}
