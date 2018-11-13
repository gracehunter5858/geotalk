package beamteam.geotalk.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {LocationCategory.class, Language.class, Phrase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LocationCategoryDAO getLocationCategoryDAO();
    public abstract LanguageDAO getLanguageDAO();
    public abstract PhraseDAO getPhraseDAO();

}
