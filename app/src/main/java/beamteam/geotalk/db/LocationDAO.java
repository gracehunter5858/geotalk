
package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


@Dao
public interface LocationDAO {

    @Query("SELECT * FROM Location WHERE category=:locationCategory AND subcategory=:locationSubcategory")
    int getLocation(String locationCategory, String locationSubcategory);

    @Insert
    void insert(Location location);

    @Delete
    void delete(Location location);

    @Update
    void update(Location location);

}

