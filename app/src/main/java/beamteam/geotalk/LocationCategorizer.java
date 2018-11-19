package beamteam.geotalk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationCategorizer {

    private HashMap<String, String> typeToCategoryMap = new HashMap<>();
    private HashMap<String, List<String>> categoryToSubcategoriesMap = new HashMap<>();

    LocationCategorizer() {
        // DEBUG
        typeToCategoryMap.put("cafe", "cafe");
        List subcategories = new ArrayList();
        subcategories.add("food");
        subcategories.add("drink");
        categoryToSubcategoriesMap.put("cafe", subcategories);
    }

    public boolean supportsType(String type) {
        return typeToCategoryMap.containsKey(type);
    }

    public String getCategory(String type) {
        return typeToCategoryMap.get(type);
    }

    public List<String> getSubcategories(String category) {
        return categoryToSubcategoriesMap.get(category);
    }

}
