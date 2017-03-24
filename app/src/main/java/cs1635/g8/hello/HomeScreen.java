package cs1635.g8.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import java.util.HashMap;

public class HomeScreen extends AppCompatActivity {
    HashMap<String, String> profile_info = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String name = b.getString("Name");
        String cell_num = b.getString("Cell");

        TextView tb1 = (TextView) findViewById(R.id.textView);
        TextView tb2 = (TextView) findViewById(R.id.textView2);

        tb1.setText(name);
        tb2.setText(cell_num);
    }
}
