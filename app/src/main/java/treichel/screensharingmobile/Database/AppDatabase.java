package treichel.screensharingmobile.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import treichel.screensharingmobile.DAO.ContactDao;
import treichel.screensharingmobile.DAO.StatusDao;
import treichel.screensharingmobile.DAO.UserDao;

import treichel.screensharingmobile.Entities.Contact;
import treichel.screensharingmobile.Entities.Status;
import treichel.screensharingmobile.Entities.User;

/**
 * Created by Brian on 27/11/2017.
 */
@Database(entities = {User.class, Contact.class, Status.class}, version = 16, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract ContactDao contactDao();
    public abstract StatusDao statusDao();

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
                            //I'm not supposed to use this?
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destoryInstance(){
        INSTANCE = null;
    }
}
