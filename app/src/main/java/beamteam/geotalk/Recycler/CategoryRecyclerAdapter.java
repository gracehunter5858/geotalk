package beamteam.geotalk.Recycler;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import beamteam.geotalk.R;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>{
    private static final String TAG = "CatRecAdapt";
    private List<String> categoryList;
    private Context mContext;
    private PhrasesRecyclerAdapter phraseAdapter;
    private String selCat;
    private HashSet<String> selCatset = new HashSet<>();


    public CategoryRecyclerAdapter(List<String> categoryList,Context context, PhrasesRecyclerAdapter adapter) {
        this.categoryList = categoryList;
        this.mContext = context;
        this.phraseAdapter = adapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_layout_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder catViewHolder, final int i) {
        Log.d(TAG,"Category onBindVH Called");
        catViewHolder.category_item_button.setText(categoryList.get(i));
        catViewHolder.category_item_button.setTextOff(categoryList.get(i));
        catViewHolder.category_item_button.setTextOn(categoryList.get(i));
        catViewHolder.category_item_button.setOnClickListener(new ToggleButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = catViewHolder.category_item_button.getText().toString();
                System.out.println("Is state checked? " + catViewHolder.category_item_button.isChecked());
                selCatset.add(category);
                if (catViewHolder.category_item_button.isChecked()) {
                    System.out.println("BUTTON IS ON");
                    System.out.println("Category to be selected: " + category);

                    Drawable on = mContext.getDrawable(R.drawable.selectedbuttonbackground);
                    catViewHolder.category_layout.setBackground(on);

                    phraseAdapter.removeAt2(category);
                } else {
                    if (selCatset.contains(category)) {
                        selCatset.remove(category);
                        System.out.println("BUTTON IS OFF");

                        Drawable off = mContext.getDrawable(R.drawable.buttonbackground);
                        catViewHolder.category_layout.setBackground(off);
                        // Adds back categories that were removed due to this category
                        phraseAdapter.addBack2(category);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ToggleButton category_item_button;
        ConstraintLayout category_layout;
        public ViewHolder(View itemView){
            super(itemView);
            category_item_button = itemView.findViewById(R.id.TEXT_CATEGORY);
            category_layout = itemView.findViewById(R.id.ITEM_CATEGORY_LAYOUT);
        }
    }
}