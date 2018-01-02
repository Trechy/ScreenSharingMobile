package treichel.screensharingmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.InputStream;

public class ContactActivity extends AppCompatActivity
        implements OnClickListener {

    private TextView usernameTextView;
    private TextView statusTextView;
    private Button callButton;
    private Button messageButton;
    private ImageView avatarImageView;

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
        avatarImageView = (ImageView)
                findViewById(R.id.avatarImageView);
        avatarImageView.setOnClickListener(this);


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
            case R.id.avatarImageView:
                AlertDialog.Builder avatarBuilder = new AlertDialog.Builder(this);
                avatarBuilder.setTitle("Enter image URL");
                final EditText imageURL = new EditText(this);
                imageURL.setInputType(InputType.TYPE_CLASS_TEXT);
                avatarBuilder.setView(imageURL);

                avatarBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DownloadImageTask(avatarImageView)
                                .execute(imageURL.getText().toString());
                    }
                });
                avatarBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                avatarBuilder.show();
        }
    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
