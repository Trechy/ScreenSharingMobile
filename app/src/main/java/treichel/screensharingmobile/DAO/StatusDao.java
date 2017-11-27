package treichel.screensharingmobile.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import treichel.screensharingmobile.Entities.Status;

/**
 * Created by Brian on 27/11/2017.
 */

@Dao
public interface StatusDao {
    @Query("select * from status where id = :statusId")
    public List<Status> getStatus(int id);
}
