package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity (primaryKeys = {"phraseID", "language"})
public class User {

    @PrimaryKey
    @NonNull
    public int id;
    public String sourceLanguage;
    public String targetLanguage;

    public User(int id, String sourceLanguage, String targetLanguage) {
        this.id = id;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

}
