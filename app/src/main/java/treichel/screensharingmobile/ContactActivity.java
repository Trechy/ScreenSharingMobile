package treichel.screensharingmobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity
        implements OnClickListener {

    private TextView usernameTextView;
    private TextView statusTextView;
    private Button callButton;
    private Button messageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        usernameTextView = (TextView)
                findViewById(R.id.contactUsernameTextView);
        statusTextView = (TextView)
                findViewById(R.id.contactStatusTextView);
        callButton = (Button)
                findViewById(R.id.callButton);
        callButton.setOnClickListener(this);
        messageButton = (Button)
                findViewById(R.id.messageButton);
        messageButton.setOnClickListener(this);


        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String status = intent.getStringExtra("status");

        usernameTextView.setText(username);
        statusTextView.setText(status);
    }

    @Override
    public void onClick (View v){
        switch(v.getId()){
            case R.id.callButton:
                Intent phoneIntent = new Intent(Intent.ACTION_MAIN);
                phoneIntent.addCategory(Intent.CATEGORY_APP_CONTACTS);
                startActivity(phoneIntent);
                break;
            case R.id.messageButton:
                Intent smsIntent = new Intent(Intent.ACTION_MAIN);
                smsIntent.addCategory(Intent.CATEGORY_APP_MESSAGING);
                startActivity(smsIntent);
                break;
        }
    }
}
