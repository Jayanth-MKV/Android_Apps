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
import com.vugido.vugidoupdate.models.home_page.ChipsRectangluarModel;

import java.util.List;

public class ChipRecatangularRecyclerViewAdapter extends RecyclerView.Adapter<ChipRecatangularRecyclerViewAdapter.MyViewHolder> {

    List<ChipsRectangluarModel> chipsRectangluarModelList;
    Context context;

    public ChipRecatangularRecyclerViewAdapter(List<ChipsRectangluarModel> chipsRectangluarModelList, Context context) {
        this.chipsRectangluarModelList = chipsRectangluarModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rectangular_chips_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ChipsRectangluarModel chipsRectangluarModel=chipsRectangluarModelList.get(position);

        Glide.with(context).load(chipsRectangluarModel.getImage()).into(holder.imageView);
        holder.title.setText(chipsRectangluarModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return chipsRectangluarModelList.size();
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
