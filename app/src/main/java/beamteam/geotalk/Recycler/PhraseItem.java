package beamteam.geotalk.Recycler;

public class PhraseItem {
    private String phraseText;
    private boolean saved = false;
    public PhraseItem(String phraseText) {
        this.phraseText = phraseText;
    }
    public String getPhraseText() {
        return phraseText;
    }
}