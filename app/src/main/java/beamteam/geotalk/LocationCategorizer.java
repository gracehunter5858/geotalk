package beamteam.geotalk;

import java.util.HashMap;

public class LocationCategorizer {

    private HashMap<String, String> typeToCategoryMap = new HashMap<>();

    LocationCategorizer() {
        typeToCategoryMap.put("cafe", "cafe");
    }

    public boolean supportsType(String type) {
        return typeToCategoryMap.containsKey(type);
    }

    public String getCategory(String type) {
        return typeToCategoryMap.get(type);
    }

}
