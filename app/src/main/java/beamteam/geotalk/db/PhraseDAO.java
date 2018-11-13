package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PhraseDAO {

    @Query("SELECT * from Phrase WHERE language=:language AND location=:location ORDER BY id")
    List<Phrase> getAllLocationPhrases(String language, String location);

    @Query("SELECT * from Phrase WHERE language=:language AND location=:location AND locationSubcategory=:category ORDER BY id")
    List<Phrase> getLocationPhrasesByCategory(String language, String location, String category);

    @Query("SELECT * from Phrase WHERE language=:language AND generalCategory=:category ORDER BY id")
    List<Phrase> getGeneralPhrasesByCategory(String language, String category);

    @Insert
    void insert(Phrase phrase);

    @Delete
    void delete(Phrase phrase);

    @Update
    void update(Phrase phrase);

}