package beamteam.geotalk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class
ContextualActivity extends AppCompatActivity implements OnCategoryClickListener {

    private static final String TAG = "ContextualAct";

    private double lat = -33.8670522;
    private double lon = 151.1957362;
    private LocationProcessor locationProcessor;

    static final int REQUEST_LOCATION = 1;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;

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

        System.out.println("launched");

        // Placeholder
        sourceLanguage = "English";
        targetLanguage = "Spanish";

        locationProcessor = new LocationProcessor(this);
        //locationProcessor.getUpdatedPhrases(lat, lon);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();

        locationProcessor.getUpdatedPhrases(lat, lon);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                Location location = locationResult.getLocations().get(0);
                //locationProcessor.getUpdatedPhrases(location.getLatitude(), location.getLongitude());
                // DEBUG:
                locationProcessor.getUpdatedPhrases(lat, lon);
            }
        };
        startLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    // TODO
    // LocationProcessor calls updateUI after each call to getUpdatedPhrases completes
    void updateUI(String category, Map<String, List<String>> phraseMapSourceLang, Map<String, List<String>> phraseMapTargetLang) {

        currentLocationCategory = category;

        List<String> categories = new ArrayList(phraseMapSourceLang.keySet());
        List<String> targetPhrases = phraseMapTargetLang.get(categories.get(0));
        List<String> sourcePhrases = phraseMapSourceLang.get(categories.get(0));

        // Initialize Recyclerviews
        initCategoryRecyclerView(categories);
        initPhraseRecyclerView(sourcePhrases, targetPhrases);
    }

    @Override
    public void onCatClick(int position){
        //handle click
    }

    private void initPhraseRecyclerView(List<String> sourcePhrases, List<String> targetPhrases){
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_PHRASES);
        PhrasesRecyclerAdapter adapter = new PhrasesRecyclerAdapter(sourcePhrases, targetPhrases, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCategoryRecyclerView(List<String> categories){
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_CATEGORY);
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(categories,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}
