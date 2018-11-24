package beamteam.geotalk.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Category.class, PhraseByCategory.class, SavedPhrase.class, Translation.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CategoryDAO getCategoryDAO();
    public abstract PhraseByCategoryDAO getPhraseByCategoryDAO();
    public abstract SavedPhraseDAO getSavedPhraseDAO();
    public abstract TranslationDAO getTranslationDAO();
    public abstract UserDAO getUserDao();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

}
