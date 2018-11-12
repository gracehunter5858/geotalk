package beamteam.geotalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;

public class ContextualActivity extends AppCompatActivity {

    private LocationProcessor locationProcessor = new LocationProcessor();
    private Map<String, String[]> phrases;
    private double lat = -33.8670522;
    private double lon = 151.1957362;

    // TODO: Get current location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextual);
        phrases = locationProcessor.getPhrases(lat,lon, this);
        String[] allPhraseList = phrases.get("all");
        String allPhrasesConcat = "";
        for (String phrase: allPhraseList) {
            allPhrasesConcat += phrase;
        }
        TextView phraseTextView = findViewById(R.id.phrases);
        phraseTextView.setText(allPhrasesConcat);
    }
}
