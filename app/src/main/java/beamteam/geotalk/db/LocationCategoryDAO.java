package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


@Dao
public interface LocationCategoryDAO {

    @Query("SELECT * FROM LocationCategory WHERE name=name")
    Language getLocationCategory(String name);

    @Insert
    void insert(LocationCategory locationCategory);

    @Delete
    void delete(LocationCategory locationCategory);

    @Update
    void update(LocationCategory locationCategory);

}
