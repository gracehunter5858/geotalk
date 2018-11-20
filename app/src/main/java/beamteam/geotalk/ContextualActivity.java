package beamteam.geotalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beamteam.geotalk.db.Phrase;

public class
ContextualActivity extends AppCompatActivity implements OnCategoryClickListener {

    private static final String TAG = "ContextualAct";

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
        System.out.println("updating UI");

        currentLocationCategory = category;

        // With updated database (after Wed), instead of Map<String, List<Phrase>>, LocationProcessor will provide Map<String, List<String>> directly
        List<String> categories = new ArrayList(phraseMapSourceLang.keySet());
        List<Phrase> currPhrases = phraseMapSourceLang.get(categories.get(0));
        List<String> currPhraseStrings = new ArrayList<>();
        for (Phrase phrase : currPhrases) {
            currPhraseStrings.add(phrase.phrase);
        }

        // Initialize Recyclerviews
        initCategoryRecyclerView(categories);
        initPhraseRecyclerView(currPhraseStrings);
    }

    @Override
    public void onCatClick(int position){
        //handle click
    }

    /**Initialize Recyclerview for Categories and Phrases**/
    private void initPhraseRecyclerView(List<String> currPhrases){
        Log.d(TAG,"init PhraseRecView");
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_PHRASES);
        PhrasesRecyclerAdapter adapter = new PhrasesRecyclerAdapter(currPhrases,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCategoryRecyclerView(List<String> categories){
        Log.d(TAG,"init CategoryRecView");
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_CATEGORY);
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(categories,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}
