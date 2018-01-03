package treichel.screensharingmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

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
    private Button setStatusOnlineButton;
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
        setStatusOnlineButton = (Button)
                findViewById(R.id.setStatusOnlineButton);
        setStatusOnlineButton.setOnClickListener(this);

        contactListView = (ListView)
                findViewById(R.id.contactListView);

        SimpleAdapter contactAdaptor = new SimpleAdapter(this, MapContacts(activeUser),
                android.R.layout.two_line_list_item, new String[] {"username", "status"},
                new int[] {android.R.id.text1, android.R.id.text2});

        contactListView.setAdapter(contactAdaptor);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                LinearLayout contact = (LinearLayout)v;
                TextView contactIdView = (TextView) contact.getChildAt(0);
                String contactUsername = contactIdView.getText().toString();

                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                Bundle contactUser = new Bundle();

                TextView statusTextView = (TextView)
                        findViewById(android.R.id.text2);

                String status = statusTextView.getText().toString();

                contactUser.putString("username", contactUsername);
                contactUser.putString("status", status);
                intent.putExtras(contactUser);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
        Intent backgroundIntent = new Intent(this, SetStatusService.class);
        startService(backgroundIntent);
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
            case R.id.setStatusOnlineButton:
                Context context = getApplicationContext();
                String text;
                int duration = Toast.LENGTH_SHORT;
                SharedPreferences activeUser = getApplication()
                        .getSharedPreferences("ACTIVE_USER", Context.MODE_PRIVATE);
                User currentUser = database.userDao().getUser(activeUser.getString("Username", null));
                if(currentUser.status == 1){
                    text = "User is already online";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
                else{
                    currentUser.status = 1;
                    database.userDao().updateUser(currentUser);
                    text = "User is now online";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
                break;
        }
    }
}
