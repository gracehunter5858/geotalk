package beamteam.geotalk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beamteam.geotalk.db.Phrase;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class
ContextualActivity extends AppCompatActivity implements OnCategoryClickListener{

    private static final String TAG = "ContextualAct";

    //private double lat = -33.8670522;
    //private double lon = 151.1957362;
    private LocationProcessor locationProcessor;

    static final int REQUEST_LOCATION = 1;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    String currentLocationCategory = null;
    private GoogleApiClient mGoogleApiClient;
    String sourceLanguage;
    String targetLanguage;

    // TODO: Get source and target language when activity loads (can do this after the Wed deadline)
    // TODO: Get current location
    // TODO: Listen for changes in location
    // TODO: Call locationProcessor.getUpdatedPhrases(lat, lon) when the activity loads and every time the location changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contextual);

        // Placeholder
        sourceLanguage = "English";
        targetLanguage = "Spanish";






        locationProcessor = new LocationProcessor(this);
        //locationProcessor.getUpdatedPhrases(lat, lon);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(ContextualActivity.this,
                ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }

      /**  fusedLocationClient.getLastLocation().addOnSuccessListener(ContextualActivity.this,
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            locationProcessor.getUpdatedPhrases(location.getLatitude(), location.getLongitude());
                        }else{
                            System.out.println("Location Null");
                        }
                    }
                }
        );**/
        locationRequest = LocationRequest.create();
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                Location location = locationResult.getLocations().get(0);
                locationProcessor.getUpdatedPhrases(location.getLatitude(), location.getLongitude());
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
    void updateUI(String category, Map<String, List<Phrase>> phraseMapSourceLang, Map<String, List<Phrase>> phraseMapTargetLang) {

        currentLocationCategory = category;

        // With updated database (after Wed), instead of Map<String, List<Phrase>>, LocationProcessor will provide Map<String, List<String>> directly
        List<String> categories = new ArrayList(phraseMapSourceLang.keySet());
        String firstCat = categories.get(0);
        List<Phrase> sourcePhrases = phraseMapSourceLang.get(firstCat);
        List<Phrase> targetPhrases = phraseMapTargetLang.get(firstCat);
        List<String> targetPhraseStrings = new ArrayList<>();
        List<String> sourcePhraseStrings = new ArrayList<>();
        for (int i = 0; i < sourcePhrases.size(); i++) {
            sourcePhraseStrings.add(sourcePhrases.get(i).phrase);
            targetPhraseStrings.add(targetPhrases.get(i).phrase);
        }

        // Initialize Recyclerviews
        initCategoryRecyclerView(categories);
        initPhraseRecyclerView(sourcePhraseStrings, targetPhraseStrings);
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
