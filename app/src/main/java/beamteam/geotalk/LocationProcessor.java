package beamteam.geotalk;

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
import beamteam.geotalk.db.Category;
import beamteam.geotalk.db.CategoryDAO;
import beamteam.geotalk.db.PhraseByCategory;
import beamteam.geotalk.db.PhraseByCategoryDAO;
import beamteam.geotalk.db.Translation;
import beamteam.geotalk.db.TranslationDAO;

public class LocationProcessor {

    // Location Type: the FIRST type listed for the FIRST location the Google Places API returns
    // Location Category: determines which set of phrases to display.
    // Multiple Google location types may map to the same location category. Some types map to null, in which case
    // only general phrases will be displayed (the same as when no location information is available).

    private static final int SEARCH_RADIUS = 20; // in meters
    private static final String API_KEY = "AIzaSyDVp_TuxAxGKQT1gzrZGimApVQgNJoBxh4";

    private CategoryDAO categoryDAO;
    private PhraseByCategoryDAO phraseByCategoryDAO;
    private TranslationDAO translationDAO;

    private ContextFragment contextFrag;

    private Map<String, List<String>> phraseMapSourceLang;
    private Map<String, List<String>> phraseMapTargetLang;


    public LocationProcessor(ContextFragment contextFrag) {
        this.contextFrag = contextFrag;
        AppDatabase instance = AppDatabase.getInstance(contextFrag.getContext());
        categoryDAO = instance.getCategoryDAO();
        phraseByCategoryDAO = instance.getPhraseByCategoryDAO();
        translationDAO = instance.getTranslationDAO();

        //addDatabaseContent();
    }

    // DEBUG
    private void addDatabaseContent() {
        /*System.out.println("Adding food phrases");
        categoryDAO.insert(new Category("restaurant", "food"));
        int catID = categoryDAO.getCatID("restaurant", "food");
        translationDAO.insert(new Translation(1, "English", "muffin"));
        translationDAO.insert(new Translation(1, "Spanish", "mollete"));
        phraseByCategoryDAO.insert(new PhraseByCategory(catID, 1));

        System.out.println("Adding drink phrases");
        categoryDAO.insert(new Category("restaurant", "drink"));
        catID = categoryDAO.getCatID("restaurant", "drink");
        translationDAO.insert(new Translation(2, "English", "water"));
        translationDAO.insert(new Translation(2, "Spanish", "agua"));
        phraseByCategoryDAO.insert(new PhraseByCategory(catID, 2));*/
    }

    void getUpdatedPhrases(double lat, double lon) {
        System.out.println("getUpdatedphrases");
        String requestUrl = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%d&key=%s",
                lat, lon, SEARCH_RADIUS, API_KEY);
        System.out.println(requestUrl);
        RequestQueue queue = Volley.newRequestQueue(contextFrag.getContext());
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("request successful");
                String type = extractLocationType(response);
                if (type != null) {
                    String category = typeToCategory(type);
                    //Hard set location as airport for purpose of demonstration
                    category = "airport";
                    if (category != null) {
                        System.out.println(category);
                        setPhrasesForCategory(category);
                    }
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("request failed");
            }
        };
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, listener, errorListener);
        queue.add(jsonObjectRequest);
    }


    private void setPhrasesForCategory(String category) {
        if (!category.equals(contextFrag.currentLocationCategory)) {
            phraseMapSourceLang = new HashMap<>();
            phraseMapTargetLang = new HashMap<>();

            for (String subcategory : categoryDAO.getSubcategories(category)) {
                System.out.println("Subcategory:" + subcategory);
                List<String> phraseListSourceLang = new ArrayList<>();
                List<String> phraseListTargetLang = new ArrayList<>();
                int catID = categoryDAO.getCatID(category, subcategory);
                List<Integer> phraseIDs = phraseByCategoryDAO.getPhraseIDsForCatID(catID);

                for (int id: phraseIDs) {
                    System.out.println(id);
                    phraseListSourceLang.add(translationDAO.getTranslation(id, contextFrag.sourceLanguage));
                    phraseListTargetLang.add(translationDAO.getTranslation(id, contextFrag.targetLanguage));
                }

                phraseMapSourceLang.put(subcategory, phraseListSourceLang);
                phraseMapTargetLang.put(subcategory, phraseListTargetLang);
                System.out.println("Subcat and source langs" + phraseMapSourceLang);
                System.out.println("Subcat and target langs" + phraseMapTargetLang);
            }
        }
        contextFrag.updateUI(category, phraseMapSourceLang, phraseMapTargetLang);
    }

    // returns the FIRST type listed for the FIRST supported location returned
    // if none of the locations returned are listed, returns null
    private String extractLocationType(JSONObject response) {
        try {
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                String type = results.getJSONObject(i).getJSONArray("types").getString(0);
                if (LocationCategorizer.supportsType(type)) {
                    return type;
                }
            }
        } catch (JSONException e) {
            System.out.println("JSON error");
        }
        return null;
    }

    private String typeToCategory(String type) {
        return LocationCategorizer.getCategory(type);
    }

}
