package beamteam.geotalk;

import java.util.ArrayList;


public class User {

    String userID;
    String name;
    String profilePicture;

    String sourceLanguage;
    String targetLanguage;
    boolean senseTargetLanguage;

    private ArrayList<Integer> savedPhrases; // phrase IDs

    void getSavedPhrases() {

    }

    void savePhrase(Integer phraseID) {
        if (!savedPhrases.contains(phraseID)) {
            savedPhrases.add(phraseID);
        }
    }

    void unsavePhrase(Integer phraseID) {
        savedPhrases.remove(phraseID);
    }

}
