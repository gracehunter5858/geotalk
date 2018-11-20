
package beamteam.geotalk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PhrasesRecyclerAdapter extends RecyclerView.Adapter<PhrasesRecyclerAdapter.ViewHolder>{
    private static final String TAG = "PhrRecAdapt";
    private List<String> sourcePhrases;
    private List<String> targetPhrases;
    private Context mContext;

    public PhrasesRecyclerAdapter(List<String> sourcePhrases, List<String> targetPhrases, Context mContext) {
        this.sourcePhrases = sourcePhrases;
        this.targetPhrases = targetPhrases;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.phrase_layout_item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG,"Phrase onBindVH Called");
        viewHolder.source_phrase_textview.setText(sourcePhrases.get(i));
        viewHolder.target_phrase_textview.setText(targetPhrases.get(i));
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