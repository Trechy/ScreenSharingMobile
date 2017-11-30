package treichel.screensharingmobile.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import treichel.screensharingmobile.Entities.Status;

/**
 * Created by Brian on 27/11/2017.
 */

@Dao
public interface StatusDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addStatus(Status status);

    @Query("select type from status where id = :incomingId")
    public String getStatusType(int incomingId);

    @Query("select * from status")
    public List<Status> getAllStatus();

    @Query("select * from status where id = :statusId")
    public Status getStatus(int statusId);
}
