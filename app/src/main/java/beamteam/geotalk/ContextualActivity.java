package beamteam.geotalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import beamteam.geotalk.db.Phrase;

public class ContextualActivity extends AppCompatActivity {

    private Map<String, String[]> phrases;
    private double lat = -33.8670522;
    private double lon = 151.1957362;
    private LocationProcessor locationProcessor;

    String currentLocationCategory = null;
    String sourceLanguage;
    String targetLanguage;

    // TODO: Get source and target language when activity loads (can do this after the Wed deadline)
    // TODO: Get current location
    // TODO: Listen for changes in location
    // TODO: Call locationProcessor.getUpdatedPhrases(lat, lon) when the activity loads and every time the location changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextual);

        // Placeholder
        sourceLanguage = "English";
        targetLanguage = "Spanish";

        locationProcessor = new LocationProcessor(this);
        locationProcessor.getUpdatedPhrases(lat, lon);
    }

    // TODO
    // LocationProcessor calls updateUI after each call to getUpdatedPhrases completes
    void updateUI(String category, Map<String, List<Phrase>> phraseMapSourceLang, Map<String, List<Phrase>> phraseMapTargetLang) {
        currentLocationCategory = category;

        //DEBUG:
        TextView phraseTextView = findViewById(R.id.phrases);
        phraseTextView.setText(phraseMapSourceLang.get("food").get(0).phrase + phraseMapTargetLang.get("food").get(0).phrase);
    }
}
