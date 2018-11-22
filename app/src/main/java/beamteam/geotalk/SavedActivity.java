package beamteam.geotalk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SavedActivity extends AppCompatActivity {

    private SavedPhrasesRecyclerAdapter adapter;
    private ArrayList<String> savedPhrases = new ArrayList<>();
    private ArrayList<String> targetPhrases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);


        savedPhrases.add("Thank you for your time.");
        savedPhrases.add("What time is it?");
        savedPhrases.add("Where is the bathroom?");

        ArrayList<String> targetPhrases = new ArrayList<>();
        targetPhrases.add("Gracias por tu tiempo");
        targetPhrases.add("¿Qué hora es?");
        targetPhrases.add("¿Dónde está el baño?");

        initSavedPhraseRecyclerView(savedPhrases, targetPhrases);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(SavedActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(SavedActivity.this, "Removed Phrase", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                savedPhrases.remove(position);
                adapter.notifyDataSetChanged();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView((RecyclerView) findViewById(R.id.RECYCLERVIEW_SAVED));
    }

    private void initSavedPhraseRecyclerView(List<String> sourcePhrases, List<String> targetPhrases){
        RecyclerView recyclerView = findViewById(R.id.RECYCLERVIEW_SAVED);
        adapter = new SavedPhrasesRecyclerAdapter(sourcePhrases, targetPhrases, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
