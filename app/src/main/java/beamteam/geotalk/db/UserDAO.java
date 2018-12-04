
package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


@Dao
public interface UserDAO {

    @Query("SELECT * FROM users WHERE id=:id")
    User getUserByID(int id);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

}