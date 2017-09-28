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

public class AddContactActivity extends AppCompatActivity
    implements View.OnClickListener {

    private EditText contactNameText;
    private Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contactNameText = (EditText)
                findViewById(R.id.contactNameText);
        addContactButton = (Button)
                findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(this);
    }

    public void onClick (View v){
        String contactName = contactNameText.getText().toString();
        Context context = getApplicationContext();
        CharSequence text = contactName + " added";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        contactNameText.setText("");
        toast.show();
    }
}
