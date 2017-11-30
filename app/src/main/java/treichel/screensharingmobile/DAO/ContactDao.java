package treichel.screensharingmobile.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import treichel.screensharingmobile.Entities.Contact;
import treichel.screensharingmobile.Entities.User;

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
    public Contact getContact(int id, int friendId);

    //Select Contact name using a join between user and contact table off of the contact id
    @Query("select contact.id, user.username, user.Status " +
            "from contact " +
            "join user on contact.contactId = user.id where contact.userId = :incomingId")
    public List<User> getUserContacts(int incomingId);
}
