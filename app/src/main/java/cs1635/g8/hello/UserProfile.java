package cs1635.g8.hello;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.*;
import android.widget.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class UserProfile extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    Button save_btn;
    ImageButton pro_pic;

    HashMap<String, String> profile_info = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        pro_pic = (ImageButton) findViewById(R.id.imageButton);
        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProfilePicture();
            }
        });

        save_btn = (Button) findViewById(R.id.button);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

//      if(profile_info.containsKey("Name"))
//          save_btn.setEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK){
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void saveInfo(){
        LinearLayout ll = (LinearLayout)findViewById(R.id.textFields);
        EditText name = (EditText)ll.getChildAt(0);
        EditText phNum = (EditText)ll.getChildAt(1);

        if(name.getText().toString().equals("") || phNum.getText().toString().equals("")){
            AlertDialog.Builder adBuild = new AlertDialog.Builder(this);

            adBuild.setTitle("Missing Info");

            adBuild
                .setMessage("Please enter at least a name and phone number")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });

            AlertDialog ad = adBuild.create();

            ad.show();
        } else {
            Intent intent = new Intent(this, HomeScreen.class);

            for (int i = 0; i < ll.getChildCount(); i++) {
                EditText tb = (EditText) ll.getChildAt(i);
                if (tb instanceof EditText) {
                    intent.putExtra(tb.getTag().toString(), tb.getText().toString());
                }
            }

            startActivity(intent);
        }
    }

    private void setProfilePicture(){
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
}
