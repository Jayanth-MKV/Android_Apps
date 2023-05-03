package com.vugido.brain_cord.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.activities.VideoPlayActivity;
import com.vugido.brain_cord.models.Quizz;
import com.vugido.brain_cord.models.Videos;

import java.util.List;

public class HomeVideosRecyclerViewAdapter extends RecyclerView.Adapter<HomeVideosRecyclerViewAdapter.MyViewHolder> {

    private List<Videos> CategoryModelList;
    private Context context;
    //private String des,name;
    private Activity activity;


    public HomeVideosRecyclerViewAdapter(List<Videos> CategoryModelList, Context context){

        this.CategoryModelList = CategoryModelList;
        this.context=context;
        //this.des=des;
        activity= (Activity) context;
        //this.name=name;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_view, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.webView.loadData(CategoryModelList.get(position).getUrl(),"text/html","utf-8");
//        Glide.with(context).load(CategoryModelList.get(position).getImage()).into(holder.imageView);

        holder.imageButton.setOnClickListener(v -> {

            Log.e("ok","clicked");

            Intent intent=new Intent(context, VideoPlayActivity.class);
            intent.putExtra("URL",CategoryModelList.get(position).getUrl());
            activity.startActivity(intent);

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

        WebView webView;

       Button imageButton;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            webView=itemView.findViewById(R.id.webview);
            imageButton=itemView.findViewById(R.id.button2);
            webView.getSettings().setJavaScriptEnabled(true);

        }
    }
}
