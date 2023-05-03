package com.vugido.brain_cord.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.models.Quizz;

import java.util.List;

public class QuizzRecyclerViewAdapter extends RecyclerView.Adapter<QuizzRecyclerViewAdapter.MyViewHolder> {

    private List<Quizz> CategoryModelList;
    private Context context;
    //private String des,name;
    private Activity activity;


    public QuizzRecyclerViewAdapter(List<Quizz> CategoryModelList, Context context){

        this.CategoryModelList = CategoryModelList;
        this.context=context;
        //this.des=des;
        activity= (Activity) context;
        //this.name=name;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.login_reg_design, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(CategoryModelList.get(position).getImage()).into(holder.imageView);

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
