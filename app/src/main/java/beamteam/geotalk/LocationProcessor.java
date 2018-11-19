package beamteam.geotalk;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.Language;
import beamteam.geotalk.db.LanguageDAO;
import beamteam.geotalk.db.Location;
import beamteam.geotalk.db.LocationDAO;
import beamteam.geotalk.db.Phrase;
import beamteam.geotalk.db.PhraseDAO;

public class LocationProcessor {

    // Location Type: the FIRST type listed for the FIRST location the Google Places API returns
    // Location Category: determines which set of phrases to display.
    // Multiple Google location types may map to the same location category. Some types map to null, in which case
    // only general phrases will be displayed (the same as when no location information is available).

    private static final int SEARCH_RADIUS = 20; // in meters
    private static final String API_KEY = "AIzaSyDVp_TuxAxGKQT1gzrZGimApVQgNJoBxh4";
    private static LocationCategorizer locationCategorizer = new LocationCategorizer();

    private LanguageDAO languageDAO;
    private LocationDAO locationDAO;
    private PhraseDAO phraseDAO;

    private ContextualActivity context;


    LocationProcessor(ContextualActivity context) {
        this.context = context;
        languageDAO = AppDatabase.getInMemoryDatabase(context).getLanguageDAO();
        locationDAO = AppDatabase.getInMemoryDatabase(context).getLocationDAO();
        phraseDAO = AppDatabase.getInMemoryDatabase(context).getPhraseDAO();

        // DEBUG
        addDatabaseContent();
    }

    // DEBUG
    private void addDatabaseContent() {
        languageDAO.insert(new Language("English"));
        languageDAO.insert(new Language("Spanish"));
        locationDAO.insert(new Location("cafe"));
        phraseDAO.insert(new Phrase(1, "muffin", "English", "cafe", "food"));
        phraseDAO.insert(new Phrase(2, "coffee", "English", "cafe", "drink"));
        phraseDAO.insert(new Phrase(3, "mollete", "Spanish", "cafe", "food"));
        phraseDAO.insert(new Phrase(4, "café", "Spanish", "cafe", "drink"));
    }

    void getUpdatedPhrases(double lat, double lon) {
        String requestUrl = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%d&key=%s",
                lat, lon, SEARCH_RADIUS, API_KEY);
        System.out.println(requestUrl);
        RequestQueue queue = Volley.newRequestQueue(context);
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String type = extractLocationType(response);
                if (type != null) {
                    String category = typeToCategory(type);
                    if (category != context.currentLocationCategory) {
                        Map<String, List<Phrase>> phraseMapSourceLang = new HashMap<>();
                        Map<String, List<Phrase>> phraseMapTargetLang = new HashMap<>();
                        for (String subcategory : locationCategorizer.getSubcategories(category)) {
                            phraseMapSourceLang.put(subcategory, getPhrasesForCategory(context.sourceLanguage, category, subcategory));
                            phraseMapTargetLang.put(subcategory, getPhrasesForCategory(context.targetLanguage, category, subcategory));
                        }
                        context.updateUI(category, phraseMapSourceLang, phraseMapTargetLang);
                    }
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, listener, errorListener);
        queue.add(jsonObjectRequest);
    }

    // returns the FIRST type listed for the FIRST supported location returned
    // if none of the locations returned are listed, returns null
    private String extractLocationType(JSONObject response) {
        try {
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                String type = results.getJSONObject(i).getJSONArray("types").getString(0);
                if (locationCategorizer.supportsType(type)) {
                    return type;
                }
            }
        } catch (JSONException e) {
            System.out.println("JSON error");
        }
        return null;
    }

    private String typeToCategory(String type) {
        return locationCategorizer.getCategory(type);
    }

    private List<Phrase> getPhrasesForCategory(String language, String category, String subcategory) {
        return phraseDAO.getLocationPhrasesByCategory(language, category, subcategory);
    }

}
