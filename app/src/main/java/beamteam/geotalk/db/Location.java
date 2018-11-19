package beamteam.geotalk.db;

        import android.arch.persistence.room.Entity;

        import java.util.List;


@Entity (primaryKeys = {"category", "subcategory"})
public class Location {

    public String category;
    public String subcategory;
    public List<Integer> phraseIDs;

    public Location(String category, String subcategory, List<Integer> phraseIDs) {
        this.category = category;
        this.subcategory = subcategory;
        this.phraseIDs = phraseIDs;
    }

}
