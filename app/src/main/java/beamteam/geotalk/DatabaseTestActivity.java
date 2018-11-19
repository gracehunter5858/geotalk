package beamteam.geotalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.Language;
import beamteam.geotalk.db.LanguageDAO;
import beamteam.geotalk.db.Location;
import beamteam.geotalk.db.LocationDAO;
import beamteam.geotalk.db.Phrase;
import beamteam.geotalk.db.PhraseDAO;

public class DatabaseTestActivity extends AppCompatActivity {

    private LanguageDAO languageDAO;
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
    }
    public void ContextActivity(View view) {
        Intent intent = new Intent(this, ContextualActivity.class);
        startActivity(intent);
    }

}