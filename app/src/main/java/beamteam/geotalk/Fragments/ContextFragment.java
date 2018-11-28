package beamteam.geotalk.Fragments;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beamteam.geotalk.ContextualActivity;
import beamteam.geotalk.LocationProcessor;
import beamteam.geotalk.R;
import beamteam.geotalk.Recycler.CategoryRecyclerAdapter;
import beamteam.geotalk.Recycler.PhrasesRecyclerAdapter;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class ContextFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String TAG = "ContextualAct";

    private double lat = -33.8670522;
    private double lon = 151.1957362;
    private LocationProcessor locationProcessor;

    static final int REQUEST_LOCATION = 1;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    String currentLocationCategory = null;
    private GoogleApiClient mGoogleApiClient;

    String sourceLanguage;
    String targetLanguage;

    PhrasesRecyclerAdapter phraseAdapter;
    CategoryRecyclerAdapter catoegoryAdapter;
    RecyclerView phraseRecyclerView;
    RecyclerView categoryRecyclerView;



    public ContextFragment() {
    }

    public static ContextFragment newInstance() {
        ContextFragment fragment = new ContextFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Context fragment created");
        // Placeholder
        sourceLanguage = "English";
        targetLanguage = "Spanish";
        locationProcessor = new LocationProcessor(getActivity());
        //locationProcessor.getUpdatedPhrases(lat, lon);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(),
                ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_context, container, false);
    }

    public void onCatClick(int position){
        //handle click
    }
    @Override
    public void onResume() {
        super.onResume();
        startLocationUpdates();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }


    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }
    void updateUI(String category, Map<String, List<String>> phraseMapSourceLang, Map<String, List<String>> phraseMapTargetLang) {

        currentLocationCategory = category;

        List<String> categories = new ArrayList(phraseMapSourceLang.keySet());
        List<String> targetPhrases = phraseMapTargetLang.get(categories.get(0));
        List<String> sourcePhrases = phraseMapSourceLang.get(categories.get(0));

        // Initialize Recyclerviews
        initCategoryRecyclerView(categories,getView());
        initPhraseRecyclerView(sourcePhrases, targetPhrases,getView());
    }
    /**
     *
     * So according to stackoverflow, I should be able to get away with using this??
     *
     * **/
    private void initPhraseRecyclerView(List<String> sourcePhrases, List<String> targetPhrases, View view){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_PHRASES);
        PhrasesRecyclerAdapter adapter = new PhrasesRecyclerAdapter(sourcePhrases, targetPhrases, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initCategoryRecyclerView(List<String> categories, View view){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_CATEGORY);
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(categories,getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

}
