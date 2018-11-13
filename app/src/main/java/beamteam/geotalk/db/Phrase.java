package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = {
        @ForeignKey(entity = LocationCategory.class,
                parentColumns = "name",
                childColumns = "location"),
        @ForeignKey(entity = Language.class,
                parentColumns = "name",
                childColumns = "language")
})
public class Phrase {

    @PrimaryKey
    @NonNull
    private int id;
    private String phrase;
    private String location;
    private String language;
    private String generalCategory;
    private String locationSubcategory;

    // General phrase constructor
    public Phrase(int id, String phrase, String language, String generalCategory) {
        this.id = id;
        this.phrase = phrase;
        this.language = language;
        this.generalCategory = generalCategory;
    }

    // Location-based phrase constructor
    // locationSubcategory can be null for locations that don't need subcategories
    public Phrase(int id, String phrase, String language, String location, String locationSubcategory) {
        this.id = id;
        this.phrase = phrase;
        this.language = language;
        this.location = location;
        this.locationSubcategory = locationSubcategory;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public String getLocation() {
        return location;
    }

    public String getLanguage() {
        return language;
    }

    public String getGeneralCategory() {
        return generalCategory;
    }

    public String getLocationSubcategory() {
        return locationSubcategory;
    }

}


