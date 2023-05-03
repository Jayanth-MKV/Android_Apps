package com.vugido.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LearningRecyclerViewAdapter extends RecyclerView.Adapter<LearningRecyclerViewAdapter.MyViewHolder> {

    List<LearningModel> learningModelList;
    Context context;

    public LearningRecyclerViewAdapter(List<LearningModel> learningModelList, Context context) {
        this.learningModelList = learningModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.learn_item_design,parent,false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        LearningModel learningModel=learningModelList.get(position);
        holder.imageView.setImageDrawable(context.getDrawable(learningModel.getImage()));
        holder.title.setText(learningModel.getLearning_title());

    }

    @Override
    public int getItemCount() {
        return learningModelList.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        return (position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return (position);
//    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView3);
            title=itemView.findViewById(R.id.textView8);
        }
    }
}
