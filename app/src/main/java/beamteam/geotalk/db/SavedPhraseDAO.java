
package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface SavedPhraseDAO {

    @Query("SELECT phraseID FROM saved_phrases WHERE userID=:userID AND language=:targetLanguage")
    List<Integer> getSavedPhraseIDs(int userID, int targetLanguage);

    @Insert
    void insert(SavedPhrase savedPhrase);

    @Delete
    void delete(SavedPhrase savedPhrase);

    @Update
    void update(SavedPhrase savedPhrase);

}