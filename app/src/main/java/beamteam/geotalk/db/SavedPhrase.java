package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity (tableName = "saved_phrases", primaryKeys = {"source", "target"})
public class SavedPhrase {

    public int userID;
    public int phraseID;
    public int language;
    @NonNull
    public String source;
    @NonNull
    public String target;

    public SavedPhrase(int userID, int phraseID, int language, String source, String target) {
        this.userID = userID;
        this.phraseID = phraseID;
        this.language = language;
        this.source = source;
        this.target = target;
    }

}
