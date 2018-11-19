
package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface SavedPhraseDAO {

    @Query("SELECT * FROM SavedPhrase WHERE userID=:userID AND language=:targetLanguage")
    List<SavedPhrase> getSavedPhrases(int userID, int targetLanguage);

    @Insert
    void insert(Location location);

    @Delete
    void delete(Location location);

    @Update
    void update(Location location);

}