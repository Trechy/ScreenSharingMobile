package treichel.screensharingmobile.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

/**
 * Created by Brian on 27/11/2017.
 */

@Entity(tableName = "contact",
    foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "contactId",
                onDelete = ForeignKey.CASCADE
        )
    },
    indices = {@Index(value = "id")}
)

public class Contact {
    public int userId;
    public int contactId;

    public Contact(int userId, int contactId){
        this.userId = userId;
        this.contactId = contactId;
    }
}
