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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.models.home_page.CircularViewModel;

import java.util.List;

public class CircularViewRecyclerAdapter extends RecyclerView.Adapter<CircularViewRecyclerAdapter.MyViewHolder> {

    private Context context;
    List<CircularViewModel> circularViewModelList;

    public CircularViewRecyclerAdapter(Context context, List<CircularViewModel> circularViewModelList) {
        this.context = context;
        this.circularViewModelList = circularViewModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.circular_item_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CircularViewModel circularViewModel=circularViewModelList.get(position);
        holder.Title.setText(circularViewModel.getTitle());
        Glide.with(context).load(circularViewModel.getImage()).into(holder.Image);


    }

    @Override
    public int getItemCount() {
        return circularViewModelList.size();
    }

     static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView Image;
        TextView Title;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        Image=itemView.findViewById(R.id.image);
        Title=itemView.findViewById(R.id.title);
    }


}
}
