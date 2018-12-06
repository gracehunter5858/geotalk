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
    private HashMap<String, String> phraseToCat;

    private String sourceLanguage = "English";
    private String targetLanguage = "Korean";

    public GeneralFragment() {
        // Required empty public constructor
    }

    public static GeneralFragment newInstance() {
        GeneralFragment fragment = new GeneralFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phraseToCat = new HashMap<>();
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        this.savedPhraseDAO = db.getSavedPhraseDAO();
        this.categoryDAO = db.getCategoryDAO();
        this.phraseByCategoryDAO = db.getPhraseByCategoryDAO();
        this.translationDAO = db.getTranslationDAO();

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
            System.out.println("Source lang: " + sourceLanguage);
            for (int id : phraseIDs) {
                System.out.print("ID: " + id);
                System.out.println("Getting translation " + translationDAO.getTranslation(id, sourceLanguage));
                phraseListSourceLang.add(translationDAO.getTranslation(id, sourceLanguage));
                phraseListTargetLang.add(translationDAO.getTranslation(id, targetLanguage));
            }
            phraseMapSourceLang.put(ct, phraseListSourceLang);
            phraseMapTargetLang.put(ct, phraseListTargetLang);

        }

        List<String> savedPhrases = savedPhraseDAO.getSourcePhrases(1, 1);
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
