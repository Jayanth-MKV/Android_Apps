package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.models.CategoryModel;
import java.util.List;



public class CategoryRecyclerViewAdapter  extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder> {

    private List<CategoryModel> CategoryModelList;
    private Context context;


    public CategoryRecyclerViewAdapter(List<CategoryModel> CategoryModelList,Context context){

        this.CategoryModelList = CategoryModelList;
        this.context=context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.category_main_item_design, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(CategoryModelList.get(position).getImage()).into(holder.imageView);
        holder.title.setText(CategoryModelList.get(position).getTitle());


    }


    @Override
    public int getItemCount() {
        return CategoryModelList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.staggered_category_image);
            title=itemView.findViewById(R.id.staggered_category_title);
        }
    }
}
