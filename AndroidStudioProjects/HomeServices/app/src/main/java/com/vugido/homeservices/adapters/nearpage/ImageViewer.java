package com.vugido.homeservices.adapters.nearpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.homeservices.R;
import com.vugido.homeservices.activities.ServiceDetails;
import com.vugido.homeservices.models.homepage.SERVICECATEGORIESItem;
import com.vugido.homeservices.models.homepage.SERVICEPRODUCTSItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageViewer extends RecyclerView.Adapter<ImageViewer.MyViewHolder> {

    Context context;
     List<String> stringList;

    public ImageViewer(List<String> stringList, Context context) {
        this.context = context;
        this.stringList=stringList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_screen,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String s=stringList.get(position);

        Glide.with(context).load("http://homeservices.vugido.com/IMAGES/"+s).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }




    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.intro_img);

        }
    }




    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public int getItemViewType(int position) {
        return (position);
    }
}
