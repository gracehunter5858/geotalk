package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface LanguageDAO {

    @Query("SELECT * FROM Language WHERE name=:name")
    Language getLanguage(String name);

    @Insert
    void insert(Language language);

    @Delete
    void delete(Language language);

    @Update
    void update(Language language);

}