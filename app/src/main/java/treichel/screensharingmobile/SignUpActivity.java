package treichel.screensharingmobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import treichel.screensharingmobile.Database.AppDatabase;
import treichel.screensharingmobile.Entities.User;

public class SignUpActivity extends AppCompatActivity
    implements OnClickListener {

    private AppDatabase database;

    private EditText suUsernameText;
    private EditText suPasswordText;
    private EditText suConfirmText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = AppDatabase.getDatabase(getApplicationContext());

        suUsernameText = (EditText)
                findViewById(R.id.usernameSignUpText);
        suPasswordText = (EditText)
                findViewById(R.id.passwordSignUpText);
        suConfirmText = (EditText)
                findViewById(R.id.confirmSignUpText);
        signUpButton = (Button)
                findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        String username = suUsernameText.getText().toString().trim();
        String password = suPasswordText.getText().toString().trim();
        String confirm = suConfirmText.getText().toString().trim();
        Context context = getApplicationContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        User userCheck = database.userDao().getUser(username);
        if(username.matches("") || password.matches("") || confirm.matches(""))
        {
            text = "One or more fields are blank";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(!(password.equals(confirm))){
            text = "Passwords do not match";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(userCheck != null){
            text = "This username is already taken";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {
            User newUser = new User(username, password, 1);
            database.userDao().addUser(newUser);
            text = "User created";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
