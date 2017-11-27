package treichel.screensharingmobile.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import treichel.screensharingmobile.Entities.User;

/**
 * Created by Brian on 27/11/2017.
 */

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addUser(User user);
}
