package treichel.screensharingmobile.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import treichel.screensharingmobile.Entities.Contact;

/**
 * Created by Brian on 27/11/2017.
 */

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void addContact(Contact contact);

    @Query("select * from contact where userId = :id")
    public List<Contact> getAllContact(int id);

    @Query("select * from contact where userId =:id and contactId = :friendId")
    public List<Contact> getContact(int id, int friendId);
}
