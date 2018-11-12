package beamteam.geotalk;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LocationProcessor{

    // Location Type: the FIRST (primary) type listed in the "types" array returned by the Google Places API
    // Location Category: determines which set of phrases to display.
    // Multiple Google location types may map to the same location category. Some types map to null, in which case
    // only general phrases will be displayed (the same as when no location information is available).

    private HashMap<String, String> LocationTypeToCategory = new HashMap<>();

    // Category -> [Subcategory, [Phrases]]
    private HashMap<String, HashMap<String, String[]>> CategoryToPhrases = new HashMap<>();
    private String locationType = null;


    // Subcategory -> [Phrases]
    // Places for which subcategories are not needed: all phrases stored under subcategory "all"
    HashMap<String, String[]> getPhrases(double lat, double lon, Context context) {
        getLocationType(lat,lon);
        while (locationType == null) {

        }
        String locationCategory = getLocationCategory(locationType);
        locationType = null;
        return getPhraseList(locationCategory);
    }


    private void getLocationType(double lat, double lon) {
        // Request and JSON processing here
    }

    private String getLocationCategory(String locationType) {
        return LocationTypeToCategory.get(locationType);
    }

    private HashMap<String, String[]> getPhraseList(String locationCategory) {
        return CategoryToPhrases.get(locationCategory);
    }

    private void sendZipRequest(String url, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processResponse(response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener);
        queue.add(jsonObjectRequest);
    }

    private void processResponse(JSONObject response) {
        locationType = "not null";
    }

}
