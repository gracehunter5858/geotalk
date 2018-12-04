package beamteam.geotalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.SavedPhrase;
import beamteam.geotalk.db.SavedPhraseDAO;


public class SavedFragment extends Fragment {
    private SavedPhraseDAO savedPhraseDAO;
    private SavedPhrasesRecyclerAdapter adapter;
    private ArrayList<String> savedPhrases;
    private ArrayList<String> targetPhrases;

    public SavedFragment() {
        savedPhrases = new ArrayList<String>();
        targetPhrases = new ArrayList<String>();
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

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        savedPhraseDAO = AppDatabase.getInMemoryDatabase(this.getContext()).getSavedPhraseDAO();
        List<PhraseTuple> phrases = savedPhraseDAO.getSavedPhrases(1, 1);

        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        for (int i = 0; i < phrases.size(); i++) {
            savedPhrases.add(phrases.get(i).source);
            targetPhrases.add(phrases.get(i).target);
        }
        initSavedPhraseRecyclerView(savedPhrases, targetPhrases, view);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(SavedFragment.this.getContext(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(SavedFragment.this.getContext(), "Removed Phrase", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                String source = savedPhrases.get(position);
                String target = targetPhrases.get(position);
                int phraseID = savedPhraseDAO.getPhraseID(source);

                savedPhraseDAO.delete(new SavedPhrase(1, phraseID, 1, source, target));
                savedPhrases.remove(position);
                targetPhrases.remove(position);
                adapter.notifyDataSetChanged();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView((RecyclerView) view.findViewById(R.id.RECYCLERVIEW_SAVED));
        // Inflate the layout for this fragment

        return view;
    }


    private void initSavedPhraseRecyclerView(List<String> sourcePhrases, List<String> targetPhrases, View view){
        RecyclerView recyclerView = view.findViewById(R.id.RECYCLERVIEW_SAVED);
        adapter = new SavedPhrasesRecyclerAdapter(sourcePhrases, targetPhrases, this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
