package beamteam.geotalk;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SavedPhrasesRecyclerAdapter extends RecyclerView.Adapter<SavedPhrasesRecyclerAdapter.ViewHolder> {

    private static final String TAG = "SavedPhrRecAdapt";
    private List<String> sourcePhrases;
    private List<String> targetPhrases;
    private Context mContext;
    private int mExpandedPosition = -1;
    private int position;


    public SavedPhrasesRecyclerAdapter(List<String> sourcePhrases, List<String> targetPhrases, Context mContext) {
        this.sourcePhrases = sourcePhrases;
        this.targetPhrases = targetPhrases;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SavedPhrasesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.saved_phrase_layout_item, viewGroup,false);
        SavedPhrasesRecyclerAdapter.ViewHolder viewHolder = new SavedPhrasesRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPhrasesRecyclerAdapter.ViewHolder viewHolder, int i) {
        Log.d(TAG,"Phrase onBindVH Called");
        viewHolder.source_phrase_textview.setText(sourcePhrases.get(i));
        viewHolder.target_phrase_textview.setText(targetPhrases.get(i));
        viewHolder.target_phrase_textview.setTextColor(Color.parseColor("#808080"));
        /*viewHolder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Log.d(TAG, "Phrase Clicked On");
        }
        });*/
        /*
        position = i;

        final boolean isExpanded = i==mExpandedPosition;
        viewHolder.target_phrase_textview.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        viewHolder.itemView.setActivated(isExpanded);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(position);
            }
        });*/

    }

    //Called when new new list created via filters
    public void newPhraseList(List<String> newSourceList, List<String> newTargetList){
        this.sourcePhrases = newSourceList;
        //this.targetPhrases = newTargetList;
        //notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sourcePhrases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView source_phrase_textview;
        TextView target_phrase_textview;
        ConstraintLayout phrase_layout;
        public ViewHolder(View itemView){
            super(itemView);
            source_phrase_textview = itemView.findViewById(R.id.SOURCE_LANG_PHRASE);
            target_phrase_textview = itemView.findViewById(R.id.TARGET_LANG_PHRASE);
        }
    }
}
