package treichel.screensharingmobile.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import treichel.screensharingmobile.Entities.User;

/**
 * Created by Brian on 27/11/2017.
 */

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addUser(User user);

    @Query("select * from user")
    public List<User> getAllUsers();

    @Query("select * from user where username = :loginName")
    public User getUser(String loginName);
}
