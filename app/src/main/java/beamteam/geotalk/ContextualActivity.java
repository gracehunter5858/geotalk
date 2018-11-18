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

    // TODO: Get current location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextual);
        locationProcessor = new LocationProcessor(this);
        locationProcessor.getUpdatedPhrases(lat, lon);
    }

    // TODO
    // LocationProcessor calls updateUI after each call to getUpdatedPhrases completes
    void updateUI(String category, List<Phrase> phrases) {
        currentLocationCategory = category;
        //DEBUG:
        TextView phraseTextView = findViewById(R.id.phrases);
        phraseTextView.setText(phrases.get(0).phrase);
    }
}
