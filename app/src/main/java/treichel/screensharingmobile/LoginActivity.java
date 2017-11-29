package treichel.screensharingmobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import treichel.screensharingmobile.Database.AppDatabase;
import treichel.screensharingmobile.Entities.Status;
import treichel.screensharingmobile.Entities.User;

public class LoginActivity extends AppCompatActivity
    implements OnClickListener{

    private AppDatabase database;

    private EditText usernameText;
    private EditText passwordText;
    private Button loginButton;
    private TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = AppDatabase.getDatabase(getApplicationContext());

        List<Status> statuses = database.statusDao().getAllStatus();
        if(statuses.size() == 0) {
            database.statusDao().addStatus(new Status("Available"));
            database.statusDao().addStatus(new Status("Away"));
            database.statusDao().addStatus(new Status("Busy"));
        }

        List<User> users = database.userDao().getAllUsers();
        if(users.size() == 0){
            database.userDao().addUser(new User("testUser1", "test", 1));
        }


        usernameText = (EditText)
                findViewById(R.id.usernameText);
        passwordText = (EditText)
                findViewById(R.id.passwordText);
        loginButton = (Button)
                findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        signUpLink = (TextView)
                findViewById(R.id.signUpView);
        signUpLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick (View v){
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        Context context = getApplicationContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        User user = database.userDao().getUser(username);
        if(user == null){
            text = "User does not exist";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Bundle credentials = new Bundle();
            boolean goodCred = false;
            if ((user.username.equals(username)) && (user.password.equals(password))) {
                goodCred = true;
                credentials.putString("username", username);
                credentials.putString("password", password);
                /*Something is wrong with this object declaration
                ActiveUser currentUser = ((ActiveUser)getApplicationContext());
                currentUser.setActiveUserId(user.id);
                currentUser.setActiverUsername(user.username);
                */
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtras(credentials);
                startActivity(intent);
            }
            if(goodCred == false) {
                text = "Wrong Username or Password";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}