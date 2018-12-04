package beamteam.geotalk.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities = {Category.class, PhraseByCategory.class, SavedPhrase.class, Translation.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CategoryDAO getCategoryDAO();
    public abstract PhraseByCategoryDAO getPhraseByCategoryDAO();
    public abstract TranslationDAO getTranslationDAO();
    public abstract UserDAO getUserDao();
    public abstract SavedPhraseDAO getSavedPhraseDAO();

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
            // DEBUG
            /*INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();*/
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {

            return Room.databaseBuilder(context, AppDatabase.class, "database")
                    .addCallback(new Callback() {
                    @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    AppDatabase instance = getInstance(context);
                                    instance.getTranslationDAO().insertAll(Translation.getInitialData());
                                    instance.getCategoryDAO().insertAll(Category.getInitialData());
                                    instance.getPhraseByCategoryDAO().insertAll(PhraseByCategory.getInitialData());
                                    instance.getUserDao().insert(User.getTestUser());
                                }
                            });
                        }
                    })
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
    }

}
