package ehb.be.eindprojectrjd_brusselsguru.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
import ehb.be.eindprojectrjd_brusselsguru.model.Villo;

/**
 * Created by dajian on 19/03/18.
 */
@Dao
public interface VilloDAO {

    @Query("SELECT * FROM Villo")
    List<Villo> getAllVillo();

    @Insert
    void insert(Villo villo);
}
