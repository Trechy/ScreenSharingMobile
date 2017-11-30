package treichel.screensharingmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import treichel.screensharingmobile.Database.AppDatabase;
import treichel.screensharingmobile.Entities.Contact;
import treichel.screensharingmobile.Entities.User;

public class MainActivity extends AppCompatActivity
    implements OnClickListener {

    private AppDatabase database;

    private Button addContactPageButton;
    private ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getDatabase(getApplicationContext());

        SharedPreferences activeUser = getApplication()
                .getSharedPreferences("ACTIVE_USER", Context.MODE_PRIVATE);

        String username = activeUser.getString("Username", null);
        Context context = getApplicationContext();
        CharSequence text = "Welcome " + username;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        addContactPageButton = (Button)
                findViewById(R.id.addContactPageButton);
        addContactPageButton.setOnClickListener(this);

        contactListView = (ListView)
                findViewById(R.id.contactListView);

        SimpleAdapter contactAdaptor = new SimpleAdapter(this, MapContacts(activeUser),
                android.R.layout.two_line_list_item, new String[] {"username", "status"},
                new int[] {android.R.id.text1, android.R.id.text2});

        contactListView.setAdapter(contactAdaptor);

    }

    private List<Map<String, String>> MapContacts(SharedPreferences activeUser){
        List<Map<String, String>> displayContacts = new ArrayList<Map<String, String>>();
        List<User> userContacts = database.contactDao().getUserContacts(activeUser.getInt("UserID", 0));

        for(User c:userContacts){
            HashMap<String, String> cMap = new HashMap<String, String>();
            cMap.put("username", c.username);
            cMap.put("status", database.statusDao().getStatusType(c.status));
            displayContacts.add(cMap);
        }
        return displayContacts;
    }

    @Override
    public void onClick (View v){
        switch(v.getId()){
            case R.id.addContactPageButton:
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
                break;
            case R.id.checkButton:

        }
    }
}
