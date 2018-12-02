package beamteam.geotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.SavedPhraseDAO;


public class SavedFragment extends Fragment {
    private SavedPhraseDAO savedPhraseDAO;
    private SavedPhrasesRecyclerAdapter adapter;

    public SavedFragment() {

    }

    public static SavedFragment newInstance() {
        SavedFragment fragment = new SavedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedPhraseDAO = AppDatabase.getInMemoryDatabase(this.getContext()).getSavedPhraseDAO();
        List<PhraseTuple> phrases = savedPhraseDAO.getSavedPhrases(1, 1);
        System.out.println(phrases.get(0).source);
        System.out.println(phrases.get(0).target);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }


    private void initSavedPhraseRecyclerView(List<String> sourcePhrases, List<String> targetPhrases, View view){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_SAVED);
        adapter = new SavedPhrasesRecyclerAdapter(sourcePhrases, targetPhrases, this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
