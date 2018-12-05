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
                //new PhraseByCategory(0, 0), // airport/baggage baggage claim
                new PhraseByCategory(2, 72),
                new PhraseByCategory(2, 73),
                new PhraseByCategory(3, 71),
                new PhraseByCategory(4, 70),
                // Basics
                new PhraseByCategory(6, 1),
                new PhraseByCategory(6, 2),
                new PhraseByCategory(6, 3),
                new PhraseByCategory(6, 4),
                new PhraseByCategory(6, 5),
                new PhraseByCategory(6, 6),
                new PhraseByCategory(6, 7),
                new PhraseByCategory(6, 8),
                new PhraseByCategory(6, 9),
                new PhraseByCategory(6, 10),
                new PhraseByCategory(6, 11),
                new PhraseByCategory(6, 12),

                // Numbers
                new PhraseByCategory(7, 13),
                new PhraseByCategory(7, 14),
                new PhraseByCategory(7, 15),
                new PhraseByCategory(7, 16),
                new PhraseByCategory(7, 17),
                new PhraseByCategory(7, 18),
                new PhraseByCategory(7, 19),
                new PhraseByCategory(7, 20),
                new PhraseByCategory(7, 21),
                new PhraseByCategory(7, 22),
                new PhraseByCategory(7, 23),
                new PhraseByCategory(7, 24),
                new PhraseByCategory(7, 25),

                // Dates & Times
                new PhraseByCategory(8, 26),
                new PhraseByCategory(8, 27),
                new PhraseByCategory(8, 28),
                new PhraseByCategory(8, 29),
                new PhraseByCategory(8, 30),
                new PhraseByCategory(8, 31),
                new PhraseByCategory(8, 32),
                new PhraseByCategory(8, 33),
                new PhraseByCategory(8, 34),
                new PhraseByCategory(8, 35),
                new PhraseByCategory(8, 36),
                new PhraseByCategory(8, 37),
                new PhraseByCategory(8, 38),
                new PhraseByCategory(8, 39),

                // Directions
                new PhraseByCategory(9, 40),
                new PhraseByCategory(9, 41),
                new PhraseByCategory(9, 42),
                new PhraseByCategory(9, 43),
                new PhraseByCategory(9, 44),
                new PhraseByCategory(9, 45),
                new PhraseByCategory(9, 46),

                // Transportation
                new PhraseByCategory(10, 47),
                new PhraseByCategory(10, 48),
                new PhraseByCategory(10, 49),
                new PhraseByCategory(10, 50),
                new PhraseByCategory(10, 51),
                new PhraseByCategory(10, 52),

                // Money
                new PhraseByCategory(11, 53),
                new PhraseByCategory(11, 54),

                // Problems
                new PhraseByCategory(12, 55),
                new PhraseByCategory(12, 56),
                new PhraseByCategory(12, 57),
                new PhraseByCategory(12, 58),

                // Food
                new PhraseByCategory(13, 59),
                new PhraseByCategory(13, 60),
                new PhraseByCategory(13, 61),
                new PhraseByCategory(13, 62),
                new PhraseByCategory(13, 63),
                new PhraseByCategory(13, 64),
                new PhraseByCategory(13, 65),
                new PhraseByCategory(13, 66),
                new PhraseByCategory(13, 67),
                new PhraseByCategory(13, 68),
                new PhraseByCategory(13, 69),
        };
    }
}
