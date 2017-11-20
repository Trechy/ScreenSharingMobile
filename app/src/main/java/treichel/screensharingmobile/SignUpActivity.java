package treichel.screensharingmobile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity
    implements OnClickListener {

    private EditText suUsernameText;
    private EditText suPasswordText;
    private EditText suConfirmText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
        String username = suUsernameText.getText().toString();
        String password = suPasswordText.getText().toString();
        String confirm = suConfirmText.getText().toString();
        if(password != confirm){
            Context context = getApplicationContext();
            CharSequence text = "Passwords do not match";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        //else if username is taken
        //else create account
        else
        {

        }
    }
}
