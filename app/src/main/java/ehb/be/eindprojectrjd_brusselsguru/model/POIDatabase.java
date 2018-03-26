package ehb.be.eindprojectrjd_brusselsguru.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.ArrayList;

import ehb.be.eindprojectrjd_brusselsguru.interfaces.MuseumDAO;
import ehb.be.eindprojectrjd_brusselsguru.interfaces.VilloDAO;

/**
 * Created by dajian on 19/03/18.
 */
@Database(entities = {Museum.class, Villo.class},version = 1, exportSchema = false)
public abstract class POIDatabase extends RoomDatabase {

private ArrayList<Museum> museumList;

    private static final String DB_Name = "POIDatabase.db";
    private static POIDatabase instance;

    public static POIDatabase getInstance(Context context) {
        if (instance == null){
            instance =  Room.databaseBuilder(context, POIDatabase.class,DB_Name)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract MuseumDAO getMuseumDao();
    public abstract VilloDAO getVilloDAO();

}
