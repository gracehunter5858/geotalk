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
                new Category( 1, "airport", "On the Plane"),
                new Category(3, "airport", "Customs"),
                new Category(4, "airport", "Transportation"),
                new Category(5, "airport", "Getting to the Plane"),
                new Category(6, "airport", "Problems"),

                // General
                new Category(0, "general", "Basics"),
                new Category( 1, "general", "Numbers"),
                new Category(3, "general", "Dates & Times"),
                new Category(4, "general", "Directions"),
                new Category(5, "general", "Food"),
                new Category(6, "general", "Money"),
                new Category(6, "general", "Problems"),
                new Category(6, "general", "Questions"),
        };
    }

}
