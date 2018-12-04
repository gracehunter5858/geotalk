
package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


@Dao
public interface TranslationDAO {

    @Query("SELECT translation FROM translations WHERE phraseID=:phraseID AND language=:language")
    String getTranslation(int phraseID, String language);

    @Insert
    void insertAll(Translation... translation);

    @Delete
    void delete(Translation translation);

    @Update
    void update(Translation translation);

}