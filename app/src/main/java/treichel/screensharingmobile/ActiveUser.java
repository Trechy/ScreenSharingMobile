package treichel.screensharingmobile;

import android.app.Application;

/**
 * Created by Brian on 29/11/2017.
 */

class ActiveUser extends Application {
    private int id;
    private String username;

    public int getActiveUserId(){
        return id;
    }
    public void setActiveUserId(int incomingId){
        id = incomingId;
    }
    public String getActiveUsername(){
        return username;
    }
    public void setActiveUsername(String incomingUsername){
        username = incomingUsername;
    }
}
