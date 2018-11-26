package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PhraseByCategoryDAO {

    @Query("SELECT phraseID FROM phrases_by_category WHERE catID=:catID")
    List<Integer> getPhraseIDsForCatID(int catID);

    @Insert
    void insert(PhraseByCategory phraseByCategory);

    @Delete
    void delete(PhraseByCategory phraseByCategory);

    @Update
    void update(PhraseByCategory phraseByCategory);

}