package beamteam.geotalk;

import android.support.v7.app.AppCompatActivity;

public class DatabaseTestActivity extends AppCompatActivity {

    /*private LanguageDAO languageDAO;
    private LocationDAO locationDAO;
    private PhraseDAO phraseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);
        languageDAO = AppDatabase.getInMemoryDatabase(this).getLanguageDAO();
        locationDAO = AppDatabase.getInMemoryDatabase(this).getLocationDAO();
        phraseDAO = AppDatabase.getInMemoryDatabase(this).getPhraseDAO();
        addDatabaseContent();
        getDatabaseContent();
    }

    void addDatabaseContent() {
        languageDAO.insert(new Language("English"));
        locationDAO.insert(new Location("store"));
        phraseDAO.insert(new Phrase(1, "hi", "English", "greetings"));
        phraseDAO.insert(new Phrase(2, "check out", "English", "store", null));
        phraseDAO.insert(new Phrase(3, "try on", "English", "store", "clothing store"));
    }

    void getDatabaseContent() {
        List<Phrase> greetingsList = phraseDAO.getGeneralPhrasesByCategory("English", "greetings");
        List<Phrase> storePhrasesList = phraseDAO.getAllLocationPhrases("English", "store");
        List<Phrase> clothingStorePhrasesList = phraseDAO.getLocationPhrasesByCategory("English", "store", "clothing store");
        String greetings = "";
        String storePhrases = "";
        String clothingStorePhrases = "";
        for (Phrase phrase: greetingsList) {
            greetings += (" " + phrase.phrase);
        }
        for (Phrase phrase: storePhrasesList) {
            storePhrases += (" " + phrase.phrase);
        }
        for (Phrase phrase: clothingStorePhrasesList) {
            storePhrases += (" " + phrase.phrase);
        }
        System.out.println(greetings);
        System.out.println(storePhrases);
        System.out.println(clothingStorePhrases);
    }*/
}