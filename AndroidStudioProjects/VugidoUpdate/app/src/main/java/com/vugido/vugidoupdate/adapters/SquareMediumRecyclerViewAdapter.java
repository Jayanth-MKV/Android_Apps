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
import com.vugido.vugidoupdate.models.home_page.SquareMediumModel;

import java.util.List;

public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder> {

    List<SquareMediumModel> squareMediumModelList;
    Context context;

    public SquareMediumRecyclerViewAdapter(List<SquareMediumModel> squareMediumModelList, Context context) {
        this.squareMediumModelList = squareMediumModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.square_horizontal_medium_view_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SquareMediumModel squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getImage()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getTitle());
        holder.price.setText(squareMediumModel.getPrice());
        holder.description.setText(squareMediumModel.getDes());


    }

    @Override
    public int getItemCount() {
        return squareMediumModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,description,price;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.price);

        }
    }
}
