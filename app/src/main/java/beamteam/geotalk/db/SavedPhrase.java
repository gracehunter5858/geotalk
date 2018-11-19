package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;

@Entity (primaryKeys = {"userID", "phraseID", "language"})
public class SavedPhrase {

    public int userID;
    public int phraseID;
    public String language;

    public SavedPhrase(int userID, int phraseID, String language) {
        this.userID = userID;
        this.phraseID = phraseID;
        this.language = language;
    }

}
