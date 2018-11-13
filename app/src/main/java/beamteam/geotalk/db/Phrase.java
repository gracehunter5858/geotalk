package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = {
        @ForeignKey(entity = Location.class,
                parentColumns = "name",
                childColumns = "location"),
        @ForeignKey(entity = Language.class,
                parentColumns = "name",
                childColumns = "language")
})
public class Phrase {

    @PrimaryKey
    @NonNull
    public int id;
    public String phrase;
    public String location;
    public String language;
    public String generalCategory;
    public String locationSubcategory;

    // General phrase constructor
    public Phrase(int id, String phrase, String language, String generalCategory) {
        this.id = id;
        this.phrase = phrase;
        this.language = language;
        this.generalCategory = generalCategory;
    }

    @Ignore
    // Location-based phrase constructor
    // locationSubcategory can be null for locations that don't need subcategories
    public Phrase(int id, String phrase, String language, String location, String locationSubcategory) {
        this.id = id;
        this.phrase = phrase;
        this.language = language;
        this.location = location;
        this.locationSubcategory = locationSubcategory;
    }

}


