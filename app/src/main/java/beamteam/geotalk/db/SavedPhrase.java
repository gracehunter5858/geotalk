package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity (tableName = "saved_phrases", primaryKeys = {"userID", "phraseID", "language"})
public class SavedPhrase {

    public int userID;
    public int phraseID;
    @NonNull
    public String language;

    public SavedPhrase(int userID, int phraseID, String language) {
        this.userID = userID;
        this.phraseID = phraseID;
        this.language = language;
    }

}
