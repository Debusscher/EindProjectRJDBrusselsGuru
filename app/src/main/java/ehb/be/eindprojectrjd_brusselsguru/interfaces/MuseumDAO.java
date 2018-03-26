package ehb.be.eindprojectrjd_brusselsguru.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ehb.be.eindprojectrjd_brusselsguru.model.Museum;

/**
 * Created by dajian on 19/03/18.
 */
@Dao
public interface MuseumDAO {

    @Query("SELECT * FROM Museum")
    List<Museum> getAllMuseum();

    @Insert
    void insert(Museum museum);
}
