package beamteam.geotalk;

import android.arch.persistence.room.Database;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beamteam.geotalk.Recycler.CategoryRecyclerAdapter;
import beamteam.geotalk.Recycler.PhrasesRecyclerAdapter;
import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.Category;
import beamteam.geotalk.db.CategoryDAO;
import beamteam.geotalk.db.PhraseByCategory;
import beamteam.geotalk.db.PhraseByCategoryDAO;
import beamteam.geotalk.db.SavedPhrase;
import beamteam.geotalk.db.SavedPhraseDAO;
import beamteam.geotalk.db.Translation;
import beamteam.geotalk.db.TranslationDAO;
import beamteam.geotalk.db.UserDAO;

public class GeneralFragment extends Fragment {
    private SavedPhraseDAO savedPhraseDAO;
    private CategoryDAO categoryDAO;
    private PhraseByCategoryDAO phraseByCategoryDAO;
    private TranslationDAO translationDAO;
    private ContextFragment contextFrag;
    private HashMap<String, String> phraseToCat;

    private String sourceLanguage;
    private String targetLanguage;

    public GeneralFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment GeneralFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralFragment newInstance() {
        GeneralFragment fragment = new GeneralFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //contextFrag = new ContextFragment();
        phraseToCat = new HashMap<>();
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        this.savedPhraseDAO = db.getSavedPhraseDAO();
        this.categoryDAO = db.getCategoryDAO();
        this.phraseByCategoryDAO = db.getPhraseByCategoryDAO();
        this.translationDAO = db.getTranslationDAO();

        UserDAO userDAO = AppDatabase.getInstance(getContext()).getUserDao();
        sourceLanguage = userDAO.getUserByID(1).sourceLanguage;
        targetLanguage = userDAO.getUserByID(1).targetLanguage;

        //addDatabaseContent();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        Map<String, List<String>> phraseMapSourceLang = new HashMap<>();
        Map<String, List<String>> phraseMapTargetLang = new HashMap<>();

        // Get all subcategories
        List<String> categories = categoryDAO.getSubcategories("general");
        for (String ct : categories) {
            List<String> phraseListSourceLang = new ArrayList<>();
            List<String> phraseListTargetLang = new ArrayList<>();

            int catID = categoryDAO.getCatID("general", ct);
            List<Integer> phraseIDs = phraseByCategoryDAO.getPhraseIDsForCatID(catID);
            for (int id : phraseIDs) {
                phraseListSourceLang.add(translationDAO.getTranslation(id, sourceLanguage));
                phraseListTargetLang.add(translationDAO.getTranslation(id, targetLanguage));
            }
            phraseMapSourceLang.put(ct, phraseListSourceLang);
            phraseMapTargetLang.put(ct, phraseListTargetLang);

        }

        List<String> savedPhrases = savedPhraseDAO.getSourcePhrases(1, 1);

        /*
        savedPhraseDAO.getPhraseIDs();

        List<String> phrases = savedPhraseDAO.getSourcePhrases(1, 1);
        if (phrases.contains(((TextView) view.findViewById(R.id.bathroom)).getText().toString())) {
            ((CheckBox) view.findViewById(R.id.savestar1)).setChecked(true);
        } else {
            ((CheckBox) view.findViewById(R.id.savestar1)).setChecked(false);
        }
        if (phrases.contains(((TextView) view.findViewById(R.id.terminal)).getText().toString())) {
            ((CheckBox) view.findViewById(R.id.savestar2)).setChecked(true);
        } else {
            ((CheckBox) view.findViewById(R.id.savestar2)).setChecked(false);
        }


        ((CheckBox) view.findViewById(R.id.savestar1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String source = ((TextView) view.findViewById(R.id.bathroom)).getText().toString();
                String target = "¿Dónde está el baño?";
                if (isChecked) {
                    if (!isDuplicate(source)) {
                        savedPhraseDAO.insert(new SavedPhrase(1, 1, 1, source, target));
                    }

                } else {
                    savedPhraseDAO.delete(new SavedPhrase(1, 1, 1, source, target));
                }
            }
        });
        ((CheckBox) view.findViewById(R.id.savestar2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String source = ((TextView) view.findViewById(R.id.terminal)).getText().toString();
                String target = "¿Dónde está la terminal?";
                if (isChecked) {
                    if (!isDuplicate(source)) {
                        savedPhraseDAO.insert(new SavedPhrase(1, 1, 1, source, target));
                    }

                } else {
                    savedPhraseDAO.delete(new SavedPhrase(1, 1, 1, source, target));
                }
            }
        });*/

        List<String> targetPhrases = new ArrayList<>();
        List<String> sourcePhrases = new ArrayList<>();

        List<String> categoryNames = new ArrayList(phraseMapSourceLang.keySet());
        for (int i = 0; i < categoryNames.size(); i++) {
            if (!phraseMapSourceLang.isEmpty()) {
                for (String phrase : phraseMapSourceLang.get(categoryNames.get(i))) {
                    phraseToCat.put(phrase, categoryNames.get(i));
                }
                targetPhrases.addAll(phraseMapTargetLang.get(categoryNames.get(i)));
                sourcePhrases.addAll(phraseMapSourceLang.get(categoryNames.get(i)));
            }
        }

        initCategoryRecyclerView(categoryNames, view, initPhraseRecyclerView(sourcePhrases, targetPhrases, savedPhrases, view, phraseToCat));
        return view;
    }

    public boolean isDuplicate(String phrase) {
        List<String> phrases = savedPhraseDAO.getSourcePhrases(1, 1);
        return phrases.contains(phrase);
    }

    private PhrasesRecyclerAdapter initPhraseRecyclerView(List<String> sourcePhrases, List<String> targetPhrases, List<String> savedPhrases, View view, HashMap<String, String> phraseToCat){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_PHRASES);
        PhrasesRecyclerAdapter adapter = new PhrasesRecyclerAdapter(sourcePhrases, targetPhrases, savedPhrases, getActivity(), phraseToCat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return adapter;
    }

    private void initCategoryRecyclerView(List<String> categories, View view, PhrasesRecyclerAdapter phraseAdapter){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_CATEGORY);
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(categories, getActivity(), phraseAdapter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }


}
