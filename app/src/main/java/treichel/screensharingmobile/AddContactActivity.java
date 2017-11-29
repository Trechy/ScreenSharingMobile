package treichel.screensharingmobile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import treichel.screensharingmobile.Database.AppDatabase;
import treichel.screensharingmobile.Entities.Contact;
import treichel.screensharingmobile.Entities.User;

public class AddContactActivity extends AppCompatActivity
    implements View.OnClickListener {

    private AppDatabase database;

    private EditText contactNameText;
    private Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        database = AppDatabase.getDatabase(getApplicationContext());

        contactNameText = (EditText)
                findViewById(R.id.contactNameText);
        addContactButton = (Button)
                findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(this);
    }

    public void onClick (View v){
        ActiveUser currentUser = ((ActiveUser)getApplicationContext());
        String contactName = contactNameText.getText().toString();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text;
        User contactUser = database.userDao().getUser(contactName);
        if(contactUser == null){
            text = "User does not exist";
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            contactNameText.setText("");
            toast.show();
        }
        else if(database.contactDao().getContact(currentUser.getActiveUserId(), contactUser.id) != null){
            text = contactName + " is already a contact";
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            contactNameText.setText("");
            toast.show();
        }
        else{
            Contact contact = new Contact(currentUser.getActiveUserId(), contactUser.id);
            database.contactDao().addContact(contact);
            text = contactName + " added";
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            contactNameText.setText("");
            toast.show();
        }
    }
}
