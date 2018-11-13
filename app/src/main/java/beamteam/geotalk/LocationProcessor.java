package beamteam.geotalk;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LocationProcessor {

    // Location Type: the FIRST type listed for the FIRST location the Google Places API returns
    // Location Category: determines which set of phrases to display.
    // Multiple Google location types may map to the same location category. Some types map to null, in which case
    // only general phrases will be displayed (the same as when no location information is available).

    private static final int SEARCH_RADIUS = 20; // in meters
    private static final String API_KEY = "AIzaSyDVp_TuxAxGKQT1gzrZGimApVQgNJoBxh4";

    private HashMap<String, String> locationTypeToCategory = new HashMap<>();

    // Category -> [Subcategory, [Phrases]]
    private HashMap<String, HashMap<String, String[]>> categoryToPhrases = new HashMap<>();
    private String locationType = null;


    LocationProcessor() {
        // DEBUG:
        /**locationTypeToCategory.put("storetype", "storecategory");
        locationTypeToCategory.put("not available", "category not available");
        HashMap<String, String[]> phrases = new HashMap<>();
        HashMap<String, String[]> notAvail = new HashMap<>();
        phrases.put("all", new String[]{"phrase1", "phrase2"});
        notAvail.put("all", new String[]{"not available"});
        categoryToPhrases.put("storecategory", phrases);
        categoryToPhrases.put("category not available", notAvail);**/
    }


    // Subcategory -> [Phrases]
    // Places for which subcategories are not needed: all phrases stored under subcategory "all"
    Map<String, String[]> getPhrases(double lat, double lon, Context context) {
        getLocationType(lat,lon, context);
        while (locationType == null) {

        }
        String locationCategory = getLocationCategory(locationType);
        locationType = null;
        return getPhraseList(locationCategory);
    }


    // TODO: Request never completes? Does not reach either the responseListener or the errorListener.
    // TODO: Confirmed the request URL isn't the problem, and other known working requests also don't complete.
    // TODO: So the problem is somewhere outside of this function.
    private void getLocationType(double lat, double lon, Context context) {
        String requestUrl = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%d&key=%s",
                lat, lon, SEARCH_RADIUS, API_KEY);
        System.out.println(requestUrl);
        RequestQueue queue = Volley.newRequestQueue(context);
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                extractLocationType(response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                locationType = "not available";
            }
        };
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, listener, errorListener);
        queue.add(jsonObjectRequest);
    }

    // TODO: Process JSON response, setting locationType to the FIRST type of the FIRST location in list
    private void extractLocationType(JSONObject response) {
        locationType = "storetype";
    }

    private String getLocationCategory(String locationType) {
        return locationTypeToCategory.get(locationType);
    }

    private HashMap<String, String[]> getPhraseList(String locationCategory) {
        return categoryToPhrases.get(locationCategory);
    }

}
