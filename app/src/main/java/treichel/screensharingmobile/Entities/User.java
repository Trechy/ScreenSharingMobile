package treichel.screensharingmobile.Entities;

import android.arch.persistence.room.ColumnInfo;
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
                )
        },
        indices = {@Index(value = "id")}
)

public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;
    public String password;
    public int status;

    public User(String username, String password, int status){
        this.username = username;
        this.password = password;
        this.status = status;
    }
}
