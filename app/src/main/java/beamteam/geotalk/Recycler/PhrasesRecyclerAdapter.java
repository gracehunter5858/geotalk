
package beamteam.geotalk.Recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import beamteam.geotalk.R;
import beamteam.geotalk.db.AppDatabase;
import beamteam.geotalk.db.SavedPhrase;
import beamteam.geotalk.db.SavedPhraseDAO;


public class PhrasesRecyclerAdapter extends RecyclerView.Adapter<PhrasesRecyclerAdapter.ViewHolder>{
    private static final String TAG = "PhrRecAdapt";
    private List<String> sourcePhrases;
    private List<String> targetPhrases;
    private List<String> savedPhrases;
    private Context mContext;
    private SavedPhraseDAO savedPhraseDAO;
    private String category;
    private HashMap<String, String> phraseToCat;
    private HashMap<String, String> delSourcetoTarget;
    private HashMap<String, Integer> phraseToPosition;
    private LinkedList<String> filteredCats;
    final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public PhrasesRecyclerAdapter(List<String> sourcePhrases, List<String> targetPhrases, List<String> savedPhrases, Context mContext, HashMap<String, String> phraseToCat) {
        this.sourcePhrases = sourcePhrases;
        this.targetPhrases = targetPhrases;
        this.savedPhrases = savedPhrases;
        this.mContext = mContext;
        this.savedPhraseDAO = AppDatabase.getInstance(mContext).getSavedPhraseDAO();

        this.phraseToCat = phraseToCat;

        this.delSourcetoTarget = new HashMap<>();
        this.phraseToPosition = new HashMap<>();
        this.filteredCats = new LinkedList<>();
        this.sharedPreferences = mContext.getSharedPreferences("preferences", 0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.phrase_layout_item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    public boolean isDuplicate(String phrase) {
        List<String> phrases = savedPhraseDAO.getSourcePhrases(1, 1);
        return phrases.contains(phrase);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG,"Phrase onBindVH Called");

        viewHolder.source_phrase_textview.setText(sourcePhrases.get(i));
        viewHolder.target_phrase_textview.setText(targetPhrases.get(i));

        final int position = i;
        final CheckBox chkSelected = (CheckBox) viewHolder.chkSelected;

        if (savedPhrases.contains(sourcePhrases.get(i))) {
            chkSelected.setChecked(true);
        } else {
            System.out.println("NO");
            chkSelected.setChecked(false);
        }

        //editor = sharedPreferences.edit();
        //chkSelected.setChecked(sharedPreferences.getBoolean("checked", false));

        viewHolder.chkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                String source = sourcePhrases.get(position);
                String target = targetPhrases.get(position);
                int phraseID = savedPhraseDAO.getPhraseID(source);
                //chkSelected.setChecked(checked);
                if (chkSelected.isChecked()) {
                    if (!isDuplicate(source)) {
                        savedPhraseDAO.insert(new SavedPhrase(1, phraseID, 1, source, target));
                        //editor.putBoolean("checked", true).apply();
                    }
                } else {
                    savedPhraseDAO.delete(new SavedPhrase(1, phraseID, 1, source, target));
                    //editor.putBoolean("checked", false).apply();
                }
            }
        });

        /*viewHolder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Log.d(TAG, "Phrase Clicked On");
        }
        });*/
    }



    //Called when new new list created via filters
    public void newPhraseList(List<String> newSourceList, List<String> newTargetList){
        this.sourcePhrases = newSourceList;
        this.targetPhrases = newTargetList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sourcePhrases.size();
    }

    public String getPhraseCat(String phrase) {
        return phraseToCat.get(phrase);
    }

    public List<String> getSourcePhrases() {
        return sourcePhrases;
    }

    public LinkedList<String> getFilteredCats() {
        return filteredCats;
    }

    public void removeAt(int position, String phraseCat) {
        System.out.println("Filtered cats when removing " + filteredCats);
        String source = sourcePhrases.remove(position);
        String target = targetPhrases.remove(position);
        delSourcetoTarget.put(source, target);
        filteredCats.addLast(getPhraseCat(source));

        System.out.println("delSourceToTarget contains " + delSourcetoTarget);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void addBack(String category) {
        System.out.println("Filtered cats when adding back " + filteredCats);
        List<String> deletedSources = new ArrayList<>(delSourcetoTarget.keySet());

        for (int i = 0; i < deletedSources.size(); i++) {
            String phrase = deletedSources.get(i);
            String phraseCat = getPhraseCat(phrase);
            if (!category.equals(phraseCat) && filteredCats.contains(phraseCat)) {
                sourcePhrases.add(phrase);
                targetPhrases.add(delSourcetoTarget.get(phrase));
                delSourcetoTarget.remove(phrase);
                filteredCats.remove(phraseCat);

                notifyItemInserted(getItemCount() - 1);
            }
            System.out.println("Size of deletedSources" + deletedSources.size());
            System.out.println("Size of filtered" + filteredCats.size());
        }

    }

    public Boolean filtered() {
        return delSourcetoTarget.size() > 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView source_phrase_textview;
        TextView target_phrase_textview;
        CheckBox chkSelected;

        public ViewHolder(View itemView){
            super(itemView);
            source_phrase_textview = itemView.findViewById(R.id.SOURCE_LANG_PHRASE);
            target_phrase_textview = itemView.findViewById(R.id.TARGET_LANG_PHRASE);
            chkSelected = itemView.findViewById(R.id.chkSelected);
            System.out.println(chkSelected.isChecked());
        }
    }
}