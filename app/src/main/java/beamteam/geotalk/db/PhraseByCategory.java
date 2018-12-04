package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity (tableName = "phrases_by_category", primaryKeys = {"catID", "phraseID"},
        foreignKeys = {
                @ForeignKey(entity = Category.class,
                        parentColumns = "id",
                        childColumns = "catID"),
        })
public class PhraseByCategory {

    public int catID;
    public int phraseID;

    public PhraseByCategory(int catID, int phraseID) {
        this.catID = catID;
        this.phraseID = phraseID;
    }

    public static PhraseByCategory[] getInitialData() {
        return new PhraseByCategory[] {
                new PhraseByCategory(1, 0), // airport/baggage, "baggage claim"
        };
}

}
