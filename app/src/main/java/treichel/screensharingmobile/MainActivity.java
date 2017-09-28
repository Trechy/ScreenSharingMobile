package treichel.screensharingmobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
    implements OnClickListener {

    private Button addContactPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getExtras().getString("username");
        Context context = getApplicationContext();
        CharSequence text = "Welcome " + username;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        addContactPageButton = (Button)
                findViewById(R.id.addContactPageButton);
        addContactPageButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v){
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivity(intent);
    }
}
