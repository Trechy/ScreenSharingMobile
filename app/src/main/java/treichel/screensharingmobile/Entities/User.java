package treichel.screensharingmobile.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Brian on 27/11/2017.
 */

@Entity(tableName = "user",
        foreignKeys = {
                @ForeignKey(
                        entity = Status.class,
                        parentColumns = "id",
                        childColumns = "status",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = {@Index(value = "id")}
)
public class User {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    public String username;
    public String password;

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
