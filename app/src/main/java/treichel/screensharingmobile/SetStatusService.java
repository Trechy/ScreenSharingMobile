package treichel.screensharingmobile;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import treichel.screensharingmobile.Database.AppDatabase;
import treichel.screensharingmobile.Entities.User;

/**
 * Created by Brian on 02/01/2018.
 */

public class SetStatusService extends IntentService {
    private AppDatabase database;

    public SetStatusService() {
        super("SetStatusService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        String dataString = workIntent.getDataString();
        database = AppDatabase.getDatabase(getApplicationContext());
        SharedPreferences activeUser = getApplication()
                .getSharedPreferences("ACTIVE_USER", Context.MODE_PRIVATE);
        User currentUser = database.userDao().getUser(activeUser.getString("Username", null));
        currentUser.status = 2;
        database.userDao().updateUser(currentUser);
    }
}
