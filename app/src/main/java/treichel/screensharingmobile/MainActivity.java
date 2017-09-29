package treichel.screensharingmobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
    implements OnClickListener {

    private Button addContactPageButton;
    private EditText numberText1;
    private EditText numberText2;
    private TextView numberOutputText;
    private Button checkButton;

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

        numberText1 = (EditText)
                findViewById(R.id.numberText1);
        numberText2 = (EditText)
                findViewById(R.id.numberText2);
        numberOutputText = (TextView)
                findViewById(R.id.numberOutputText);
        checkButton = (Button)
                findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);
    }

    public String sizeCheck(int number1, int number2){
        if(number1 > number2){
            return numberText1.getText() + " is bigger than " + numberText2.getText();
        }
        else {
            return numberText2.getText() + " is bigger than " + numberText1.getText();
        }
    }

    @Override
    public void onClick (View v){
        switch(v.getId()){
            case R.id.addContactPageButton:
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
                break;
            case R.id.checkButton:
                if(numberText1.getText().length() != 0 && numberText2.getText().length() != 0) {
                    int number1 = Integer.parseInt(numberText1.getText().toString());
                    int number2 = Integer.parseInt(numberText2.getText().toString());
                    if (number1 != 0 && number2 != 0) {
                        numberOutputText.setText(sizeCheck(number1, number2));
                    }
                }
                else
                {
                    numberOutputText.setText("Please enter some numbers");
                }
                break;
        }
    }
}
