package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "categories", indices = {@Index(value = {"catName", "subCatName"}, unique = true)})
public class Category {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    public int id;
    @NonNull
    public String catName;
    @NonNull
    public String subCatName;

    public Category(String catName, String subCatName) {
        this.catName = catName;
        this.subCatName = subCatName;
    }

}
