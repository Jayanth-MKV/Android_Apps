package com.vugido.vugidoupdate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.models.home_page.RectangularMediumViewModel;

import java.util.List;

public class RectangularMediumRecyclerViewAdapter extends RecyclerView.Adapter<RectangularMediumRecyclerViewAdapter.MyViewHolder> {

    List<RectangularMediumViewModel> rectangularMediumViewModelList;
    Context context;

    public RectangularMediumRecyclerViewAdapter(List<RectangularMediumViewModel> rectangularMediumViewModelList, Context context) {
        this.rectangularMediumViewModelList = rectangularMediumViewModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rectangular_medium_item_design_horizontal_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RectangularMediumViewModel rectangularMediumViewModel=rectangularMediumViewModelList.get(position);

        holder.Title.setText(rectangularMediumViewModel.getTitle());
        Glide.with(context).load(rectangularMediumViewModel.getImage()).into(holder.imageView);
        holder.ratingBar.setRating(rectangularMediumViewModel.getRating());


    }

    @Override
    public int getItemCount() {
        return rectangularMediumViewModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        RatingBar ratingBar;
        TextView Title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            Title=itemView.findViewById(R.id.title);
        }
    }
}
