package com.vugido.vugidoupdate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.models.home_page.SquareMiniModel;

import java.util.List;

public class SquareMiniRecyclerViewAdapter extends RecyclerView.Adapter<SquareMiniRecyclerViewAdapter.MyViewHolder> {

    List<SquareMiniModel> squareMiniModelList;
    Context context;

    public SquareMiniRecyclerViewAdapter(List<SquareMiniModel> squareMiniModelList, Context context) {
        this.squareMiniModelList = squareMiniModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mini_square_grid_item_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SquareMiniModel squareMiniModel=squareMiniModelList.get(position);
        Glide.with(context).load(squareMiniModel.getImage()).into(holder.imageView);
        holder.title.setText(squareMiniModel.getTitle());

    }

    @Override
    public int getItemCount() {
        return squareMiniModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
        }
    }
}
