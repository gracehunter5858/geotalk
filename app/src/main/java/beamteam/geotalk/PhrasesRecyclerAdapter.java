
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


public class PhrasesRecyclerAdapter extends RecyclerView.Adapter<PhrasesRecyclerAdapter.ViewHolder>{
    private static final String TAG = "PhrRecAdapt";
    private String[] phrases;
    private Context mContext;

    public PhrasesRecyclerAdapter(String[] phrases, Context mContext) {
        this.phrases = phrases;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.phrase_layout_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG,"Phrase onBindVH Called");
        /*viewHolder.phrase_item_text.setText(phrases[i]);
        viewHolder.phrase_item_text.setText(phrases[i]);
        viewHolder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Log.d(TAG, "Phrase Clicked On");
        }
        });*/
    }
    //Called when new new list created via filters
    public void newPhraseList(String[] newList){
        this.phrases = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return phrases.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView phrase_item_text;
        ConstraintLayout phrase_layout;
        public ViewHolder(View itemView){
            super(itemView);
            phrase_item_text = itemView.findViewById(R.id.TEXT_PHRASE_ITEM);
        }
    }
}