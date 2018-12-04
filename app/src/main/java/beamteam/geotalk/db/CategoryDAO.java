
package beamteam.geotalk.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface CategoryDAO {

    @Query("SELECT id FROM categories WHERE catName=:catName AND subCatName=:subCatName")
    Integer getCatID(String catName, String subCatName);

    @Query("SELECT subCatName FROM categories WHERE catName=:catName ORDER BY id")
    List<String> getSubcategories(String catName);

    @Insert
    void insertAll(Category... categories);

    @Delete
    void delete(Category category);

    @Update
    void update(Category category);

}