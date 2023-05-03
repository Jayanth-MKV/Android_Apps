package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.AllProductsActivity;
import com.foodhub.vugido.models.VEG_HOME_PAGE_MODEL.CATEGORIESItem;
import java.util.List;


public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.MyHorizontalViewHolder> {

    private Context context;
    private List<CATEGORIESItem> horizontalCategoryModelList;

    public HorizontalRecyclerViewAdapter(List<CATEGORIESItem> horizontalCategoryModelList,Context context) {

        this.horizontalCategoryModelList = horizontalCategoryModelList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_design, parent, false);


        return new MyHorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHorizontalViewHolder holder, int position) {
        final CATEGORIESItem horizontalCategoryModel=horizontalCategoryModelList.get(position);


        Glide.with(context)
                .load(horizontalCategoryModel.getIMAGE())
                .into(holder.imageView);

        holder.title.setText(horizontalCategoryModel.getTITLE());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent=new Intent(context, AllProductsActivity.class);
                intent.putExtra("CID",horizontalCategoryModel.getID());
                intent.putExtra("TITLE",horizontalCategoryModel.getTITLE());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return horizontalCategoryModelList.size();
    }

    static class  MyHorizontalViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        ConstraintLayout constraintLayout;


        MyHorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.grid_category_image);
            title=itemView.findViewById(R.id.grid_category_title);
            constraintLayout=itemView.findViewById(R.id.category_item);

        }

    }
}
