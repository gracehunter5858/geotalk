package beamteam.geotalk;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Category_Recycler_Adapter extends RecyclerView.Adapter<Category_Recycler_Adapter.ViewHolder>{
    private static final String TAG = "CatRecAdapt";
    private String[] categoryList;
    private Context mContext;
    private OnCategoryClickListner OnCatClickListner;
    public Category_Recycler_Adapter(String[] categoryList,Context context) {
        this.categoryList = categoryList;
        this.mContext = context;
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
        catViewHolder.category_layout.setOnClickListener(new View.OnClickListener() {
            /**REQUIRES API21 OR HIGHER*/
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

            /**onClick, depending on the current state (selected or not) of button,
             * change the background**/
            public void onClick(View v) {
                Drawable selected = mContext.getDrawable(R.drawable.selectedbuttonbackground);
                if(catViewHolder.category_layout.getBackground().getConstantState().equals(
                        selected.getConstantState()
                )){
                    Drawable unselected = mContext.getDrawable(R.drawable.buttonbackground);
                    catViewHolder.category_layout.setBackground(unselected);
                }else{
                    catViewHolder.category_layout.setBackground(selected);
                }
                int position  = i;
                OnCatClickListner.onCatClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView category_item_text;
        ConstraintLayout category_layout;
        public ViewHolder(View itemView){
            super(itemView);
            category_item_text = itemView.findViewById(R.id.TEXT_CATEGORY);
            category_layout = itemView.findViewById(R.id.ITEM_CATEGORY_LAYOUT);
        }
    }
}
