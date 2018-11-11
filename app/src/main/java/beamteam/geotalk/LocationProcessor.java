package beamteam.geotalk;

import java.util.HashMap;

public class LocationProcessor {

    // Location Type: the FIRST (primary) type listed in the "types" array returned by the Google Places API
    // Location Category: determines which set of phrases to display.
    // Multiple location types may map to the same location category. Some types map to null, in which case
    // only general phrases will be displayed (the same as when no location information is available).

    private HashMap<String, String> LocationTypeToCategory = new HashMap<>();

    // Category -> [Subcategory, [Phrases]]
    private HashMap<String, HashMap<String, String[]>> CategoryToPhraseList = new HashMap<>();


    // Subcategory -> [Phrases]
    // Places for which subcategories are not needed: all phrases stored under subcategory "all"
    HashMap<String, String[]> getPhrases(double lat, double lon) {
        String locationType = getLocationType(lat,lon);
        String locationCategory = getLocationCategory(locationType);
        return getPhraseList(locationCategory);
    }


    private String getLocationType(double lat, double lon) {
        // Request and JSON processing here
        return "";
    }

    private String getLocationCategory(String locationType) {
        return LocationTypeToCategory.get(locationType);
    }

    private HashMap<String, String[]> getPhraseList(String locationCategory) {
        return CategoryToPhraseList.get(locationCategory);
    }

}
