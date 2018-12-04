package beamteam.geotalk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationCategorizer {

    private static final Map<String, String> typeToCategoryMap = initCatMap();
    //private static final Map<String, String[]> categoryToSubcategoriesMap = initSubcatMap();

    private static Map<String, String> initCatMap() {
        Map<String,String> map = new HashMap<>();
        map.put("airport", "airport");
        map.put("amusement_park", "entertainment");
        map.put("aquarium", "entertainment");
        map.put("art_gallery", "entertainment");
        map.put("atm", "bank");
        map.put("bakery", "grocery");
        map.put("bank", "bank");
        map.put("bar", "nightlife");
        map.put("bicycle_store", "store");
        map.put("book_store", "store");
        map.put("bowling_alley", "entertainment");
        map.put("bus_station", "transportation");
        map.put("cafe", "restaurant");
        map.put("casino", "nightlife");
        map.put("clothing store", "clothing_store");
        map.put("convenience_store", "grocery");
        map.put("electronics_store", "store");
        map.put("florist", "store");
        map.put("furniture_store", "store");
        map.put("hardware_store", "store");
        map.put("home_goods_store", "store");
        map.put("hospital", "medical");
        map.put("jewelry_store", "store");
        map.put("liquor_store", "grocery");
        map.put("lodging", "hotel");
        map.put("meal_delivery", "restaurant");
        map.put("meal_takeaway", "restaurant");
        map.put("movie_theater", "entertainment");
        map.put("museum", "entertainment");
        map.put("night_club", "nightlife");
        map.put("pet_store", "store");
        map.put("pharmacy", "medical");
        map.put("physiotherapist", "medical");
        map.put("restaurant", "restaurant");
        map.put("school", "school");
        map.put("shoe_store", "clothing_store");
        map.put("shopping_mall", "clothing_store");
        map.put("stadium", "entertainment");
        map.put("store", "store");
        map.put("subway_station", "transportation");
        map.put("supermarket", "grocery");
        map.put("taxi_stand", "transportation");
        map.put("train_station", "transportation");
        map.put("zoo", "entertainment");
        return map;
    }

    /*private static Map<String, String[]> initSubcatMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("entertainment", new String[] {"all"});
        map.put("bank", new String[] {"all"});
        map.put("grocery", new String[] {"all"});
        map.put("store", new String[] {"all"});
        map.put("transportation", new String[] {"all"});
        map.put("restaurant", new String[] {"food", "drink"});
        map.put("clothing_store", new String[] {"all"});
        map.put("medical", new String[] {"all"});
        map.put("hotel", new String[] {"all"});
        map.put("school", new String[] {"all"});
        return map;
    }*/

    static boolean supportsType(String type) {
        return typeToCategoryMap.containsKey(type);
    }

    static String getCategory(String type) {
        return typeToCategoryMap.get(type);
    }

    /*static String[] getSubcategories(String category) {
        return categoryToSubcategoriesMap.get(category);
    }*/

}
