package beamteam.geotalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.Set;

import beamteam.geotalk.db.LocationDAO;

public class ContextualActivity extends AppCompatActivity implements  OnCategoryClickListner {
    private static final String TAG = "ContextualAct";
    private LocationProcessor locationProcessor = new LocationProcessor();
    private Map<String, String[]> phrases;
    private String[] currPhrases;
    private String[] categories;
    private String[] selectedCategories;
    private double lat = -33.8670522;
    private double lon = 151.1957362;

    // TODO: Get current location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_contextual);

        //get a map of subcategories -> phrases
        phrases = locationProcessor.getPhrases(lat,lon, this);

        //get a String list of subcategories
        Set<String> categoryKeys = phrases.keySet();
        categories = categoryKeys.toArray(new String[categoryKeys.size()]);

        //get all phrases
        String[] allPhraseList = phrases.get("all");



        currPhrases = allPhraseList;


        Log.d(TAG,"Setting Categories");
        initCategoryRecyclerView(categories);
        Log.d(TAG,"Setting Phrases based on Categories");
        initPhraseRecyclerView(currPhrases);

        /*** What it do down here
        String allPhrasesConcat = "";
        for (String phrase: allPhraseList) {
            allPhrasesConcat += phrase;
        }
        TextView phraseTextView = findViewById(R.id.phrases);
        phraseTextView.setText(allPhrasesConcat);
         **/
    }
    @Override
    public void onCatClick(int position){
        //handle click
    }
    /**Initialize Recyclerview for Categories and Phrases**/
    private void initPhraseRecyclerView(String[] currPhrases){
        Log.d(TAG,"init PhraseRecView");
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_PHRASES);
        Phrases_Recycler_Adapter adapter = new Phrases_Recycler_Adapter(currPhrases,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCategoryRecyclerView(String[] categories){
        Log.d(TAG,"init CategoryRecView");
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_CATEGORY);
        Phrases_Recycler_Adapter adapter = new Phrases_Recycler_Adapter(categories,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

