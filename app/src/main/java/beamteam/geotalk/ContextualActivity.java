package beamteam.geotalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import beamteam.geotalk.db.Phrase;

public class
ContextualActivity extends AppCompatActivity implements OnCategoryClickListener {

    private static final String TAG = "ContextualAct";
    private String[] currPhrases;
    private String[] categories;

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
        System.out.println("updating UI");

        currentLocationCategory = category;

        //Get list of subcategories
        categories = phraseMapSourceLang.keySet()
                .toArray(new String[phraseMapSourceLang.keySet().size()]);

        //initialize with list of first category
        List<Phrase> phrasesList = phraseMapSourceLang.get(categories[0]);
        currPhrases = new String[phrasesList.size()];
        for(int i = 0;i<phrasesList.size();i++){
            currPhrases[i] = phrasesList.get(i).phrase;
        }
        //INITIALIZE RECYCLERVIEWS
        Log.d(TAG,"Setting Categories");
        initCategoryRecyclerView(categories);
        Log.d(TAG,"Setting Phrases based on Categories");
        initPhraseRecyclerView(currPhrases);
    }

    @Override
    public void onCatClick(int position){
        //handle click
    }

    /**Initialize Recyclerview for Categories and Phrases**/
    private void initPhraseRecyclerView(String[] currPhrases){
        Log.d(TAG,"init PhraseRecView");
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_PHRASES);
        PhrasesRecyclerAdapter adapter = new PhrasesRecyclerAdapter(currPhrases,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCategoryRecyclerView(String[] categories){
        Log.d(TAG,"init CategoryRecView");
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_CATEGORY);
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(categories,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}
