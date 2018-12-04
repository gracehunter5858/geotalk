
package beamteam.geotalk.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import beamteam.geotalk.PhraseTuple;


@Dao
public interface SavedPhraseDAO {

    @Query("SELECT source, target FROM saved_phrases WHERE userID=:userID AND language=:targetLanguage")
    List<PhraseTuple> getSavedPhrases(int userID, int targetLanguage);

    @Query("SELECT source FROM saved_phrases WHERE userID=:userID AND language=:targetLanguage")
    List<String> getSourcePhrases(int userID, int targetLanguage);

    @Query("SELECT phraseID FROM saved_phrases WHERE source=:source")
    int getPhraseID(String source);

    @Insert
    void insert(SavedPhrase savedPhrase);

    @Delete
    void delete(SavedPhrase savedPhrase);

    @Update
    void update(SavedPhrase savedPhrase);

}
