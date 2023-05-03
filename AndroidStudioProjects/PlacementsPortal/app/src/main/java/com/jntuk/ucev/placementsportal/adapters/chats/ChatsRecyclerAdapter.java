package com.jntuk.ucev.placementsportal.adapters.chats;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.models.discussion_board.ChatsModel;
import com.jntuk.ucev.placementsportal.models.home.HomePostsModel;

import java.util.ArrayList;
import java.util.List;


public class ChatsRecyclerAdapter extends RecyclerView.Adapter<ChatsRecyclerAdapter.MyViewHolder>{
    List<ChatsModel> squareMediumModelList;
    Context context;
    Activity activity;

    public ChatsRecyclerAdapter(List<ChatsModel> squareMediumModelList, Context context) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        activity= (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chats_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsRecyclerAdapter.MyViewHolder holder, int position) {

        ChatsModel squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getImage()).into(holder.imageView);
        holder.name.setText(squareMediumModel.getName());
        holder.des.setText(squareMediumModel.getText());
        holder.tag.setText(squareMediumModel.getDate());
        holder.chip.setText(squareMediumModel.getMcount());

        if (squareMediumModel.getMcount().equals("0")){
            holder.chip.setVisibility(View.INVISIBLE);
        }



    }

    @Override
    public int getItemCount() {
        return squareMediumModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView name,des,tag;
        Chip chip;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.grid_category_image);
            constraintLayout=itemView.findViewById(R.id.category_item);
            tag=itemView.findViewById(R.id.textView8);
            des=itemView.findViewById(R.id.textView9);
            name=itemView.findViewById(R.id.textView10);
            chip=itemView.findViewById(R.id.chip4);


        }
    }



}
