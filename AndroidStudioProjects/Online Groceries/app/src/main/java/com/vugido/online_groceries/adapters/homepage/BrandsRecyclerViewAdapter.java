package com.vugido.online_groceries.adapters.homepage;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.models.homepage.CategoryModel;
import com.vugido.online_groceries.models.homepage.updated.BRANDSItem;

import java.util.List;


public class BrandsRecyclerViewAdapter extends RecyclerView.Adapter<BrandsRecyclerViewAdapter.MyViewHolder> {

    private List<BRANDSItem> CategoryModelList;
    private Context context;
    private String des,name;
    private Activity activity;


    public BrandsRecyclerViewAdapter(List<BRANDSItem> CategoryModelList, Context context, String des, String name){

        this.CategoryModelList = CategoryModelList;
        this.context=context;
        this.des=des;
        activity= (Activity) context;
        this.name=name;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item_design, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(CategoryModelList.get(position).getIMAGE()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {

//            Intent intent=new Intent(context, ClientActivity.class);
//            intent.putExtra("NAME",CategoryModelList.get(position).getTITLE());
//            intent.putExtra("CID",CategoryModelList.get(position).getID());
//            activity.startActivityForResult(intent,ORDER_PLACED_CODE);
        });

    }


    @Override
    public int getItemCount() {
        return CategoryModelList.size();
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.grid_category_image);
        }
    }
}
