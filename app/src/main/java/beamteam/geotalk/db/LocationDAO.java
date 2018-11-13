
package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


@Dao
public interface LocationDAO {

    @Query("SELECT * FROM Location WHERE name=:name")
    Language getLocationCategory(String name);

    @Insert
    void insert(Location location);

    @Delete
    void delete(Location location);

    @Update
    void update(Location location);

}

