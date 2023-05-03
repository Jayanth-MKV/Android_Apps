package com.jntuk.ucev.placementsportal.adapters.jobs;

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
import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.models.home.HomePostsModel;
import com.jntuk.ucev.placementsportal.models.jobs.job;

import java.util.ArrayList;
import java.util.List;


public class JobsPostAdapter extends RecyclerView.Adapter<JobsPostAdapter.MyViewHolder>{

    List<job> squareMediumModelList;
    Context context;
    Activity activity;

    public JobsPostAdapter(List<job> squareMediumModelList, Context context) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        activity= (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.jobs_post_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        job squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getImage()).into(holder.imageView);
        holder.name.setText(squareMediumModel.getJobName());
        holder.des.setText(squareMediumModel.getDescription());
        holder.tag.setText(squareMediumModel.getTag());
        holder.company.setText(squareMediumModel.getCompany());




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
        TextView name,des,tag,company;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView3);
            constraintLayout=itemView.findViewById(R.id.cl);
            tag=itemView.findViewById(R.id.tag);
            des=itemView.findViewById(R.id.textView13);
            name=itemView.findViewById(R.id.textView11);
            company=itemView.findViewById(R.id.textView12);



        }
    }



}
