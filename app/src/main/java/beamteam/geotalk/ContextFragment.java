package beamteam.geotalk;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beamteam.geotalk.Recycler.CategoryRecyclerAdapter;
import beamteam.geotalk.Recycler.PhrasesRecyclerAdapter;
import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.SavedPhraseDAO;
import beamteam.geotalk.db.UserDAO;

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

    private SavedPhraseDAO savedPhraseDAO;

    String sourceLanguage = "English";
    String targetLanguage = "Korean";

    PhrasesRecyclerAdapter phraseAdapter;
    CategoryRecyclerAdapter catoegoryAdapter;
    RecyclerView phraseRecyclerView;
    RecyclerView categoryRecyclerView;

    private HashMap<String, String> phraseToCat;


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

        /*UserDAO userDAO = AppDatabase.getInstance(getContext()).getUserDao();
        sourceLanguage = userDAO.getUserByID(1).sourceLanguage;
        targetLanguage = userDAO.getUserByID(1).targetLanguage;*/

        locationProcessor = new LocationProcessor(this);
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        this.savedPhraseDAO = db.getSavedPhraseDAO();

        phraseToCat = new HashMap<>();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(),
                ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }

//        locationProcessor.getUpdatedPhrases(lat, lon);
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
//        startLocationUpdates();

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
        locationProcessor.getUpdatedPhrases(lat, lon);
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
        List<String> targetPhrases = new ArrayList<>();
        List<String> sourcePhrases = new ArrayList<>();

        List<String> categories = new ArrayList(phraseMapSourceLang.keySet());
        for (int i = 0; i < categories.size(); i++) {
            if (!phraseMapSourceLang.isEmpty()) {
                for (String phrase : phraseMapSourceLang.get(categories.get(i))) {
                    phraseToCat.put(phrase, categories.get(i));
                }
                targetPhrases.addAll(phraseMapTargetLang.get(categories.get(i)));
                sourcePhrases.addAll(phraseMapSourceLang.get(categories.get(i)));
            }
        }

        List<String> savedPhrases = savedPhraseDAO.getSourcePhrases(1, 1);

        // Initialize Recyclerviews
        initCategoryRecyclerView(categories, getView(), initPhraseRecyclerView(sourcePhrases, targetPhrases, savedPhrases, getView(), phraseToCat));

    }
    /**
     *
     * So according to stackoverflow, I should be able to get away with using this??
     *
     * **/
    private PhrasesRecyclerAdapter initPhraseRecyclerView(List<String> sourcePhrases, List<String> targetPhrases, List<String> savedPhrases, View view, HashMap<String, String> phraseToCat){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_PHRASES);
        PhrasesRecyclerAdapter adapter = new PhrasesRecyclerAdapter(sourcePhrases, targetPhrases, savedPhrases, getActivity(), phraseToCat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return adapter;
    }

    private void initCategoryRecyclerView(List<String> categories, View view, PhrasesRecyclerAdapter phraseAdapter){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_CATEGORY);
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(categories, getActivity(), phraseAdapter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

}
