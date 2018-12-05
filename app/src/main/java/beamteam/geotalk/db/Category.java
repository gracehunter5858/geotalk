package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (tableName = "categories", indices = {@Index(value = {"catName", "subCatName"}, unique = true)})
public class Category {

    @PrimaryKey
    @NonNull
    public int id;
    @NonNull
    public String catName;
    @NonNull
    public String subCatName;

    public Category(int id, String catName, String subCatName) {
        this.id = id;
        this.catName = catName;
        this.subCatName = subCatName;
    }

    public static Category[] getInitialData() {
        return new Category[] {
                // Locations
                new Category(0, "airport", "Baggage"),
//                new Category(1, "airport", "On the Plane"),
//                new Category(2, "airport", "Customs"),
//                new Category(3, "airport", "Transportation"),
                new Category(4, "airport", "Getting to the Plane"),
//                new Category(5, "airport", "Problems"),

                // General
                new Category(6, "general", "Basics"),
                new Category(7, "general", "Numbers"),
                new Category(8, "general", "Dates & Times"),
                new Category(9, "general", "Directions"),
                new Category(10, "general", "Transportation"),
                new Category(11, "general", "Money"),
                new Category(12, "general", "Problems"),
                new Category(13, "general", "Food"),
        };
    }

}
