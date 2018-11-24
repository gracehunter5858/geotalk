package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity (tableName = "translations", primaryKeys = {"phraseID", "language"})
public class Translation {

    @NonNull
    public String translation;
    public int phraseID;
    @NonNull
    public String language;

    public Translation(int phraseID, String language, String translation) {
        this.translation = translation;
        this.phraseID = phraseID;
        this.language = language;
    }

}
