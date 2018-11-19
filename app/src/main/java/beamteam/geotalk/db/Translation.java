package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;

@Entity (primaryKeys = {"phraseID", "language"})
public class Translation {

    public String translation;
    public int phraseID;
    public String language;

    public Translation(String translation, int phraseID, String language) {
        this.translation = translation;
        this.phraseID = phraseID;
        this.language = language;
    }

}
