package treichel.screensharingmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        usernameTextView = (TextView)
                findViewById(R.id.contactUsernameTextView);
        statusTextView = (TextView)
                findViewById(R.id.contactStatusTextView);

        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String status = intent.getStringExtra("status");

        usernameTextView.setText(username);
        statusTextView.setText(status);
    }
}
