package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity (tableName = "saved_phrases", primaryKeys = {"userID", "phraseID", "language"})
public class SavedPhrase {

    public int userID;
    public int phraseID;
    @NonNull
    public int language;
    public String source;
    public String target;

    public SavedPhrase(int userID, int phraseID, int language, String source, String target) {
        this.userID = userID;
        this.phraseID = phraseID;
        this.language = language;
        this.source = source;
        this.target = target;
    }

}
