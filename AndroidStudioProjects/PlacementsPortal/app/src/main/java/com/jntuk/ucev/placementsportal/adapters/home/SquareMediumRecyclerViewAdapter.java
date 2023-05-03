package com.jntuk.ucev.placementsportal.adapters.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.models.home.StudentsPlaced;

import java.util.ArrayList;
import java.util.List;


public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder>{

   // List<SECTIONSItem> categoriesItemList;
    List<StudentsPlaced> squareMediumModelList;
    Context context;
    // List<StudentsPlaced> filteredList;
     Activity activity;

    public SquareMediumRecyclerViewAdapter(List<StudentsPlaced> squareMediumModelList, Context context) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
       // this.categoriesItemList=categoriesItemList;
       // filteredList=squareMediumModelList;
        activity= (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.student_placements_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StudentsPlaced squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getImage()).into(holder.imageView);

        holder.name.setText(squareMediumModel.getName());
        holder.branch.setText(squareMediumModel.getBranch());
        holder.company.setText(squareMediumModel.getCompany());
        holder.salary.setText(squareMediumModel.getSalary());




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
        TextView name,branch,company,salary;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.grid_category_image);
            constraintLayout=itemView.findViewById(R.id.category_item);
            name=itemView.findViewById(R.id.grid_category_title);
            branch=itemView.findViewById(R.id.textView2);
            company=itemView.findViewById(R.id.textView3);
            salary=itemView.findViewById(R.id.textView4);
        }
    }



}
