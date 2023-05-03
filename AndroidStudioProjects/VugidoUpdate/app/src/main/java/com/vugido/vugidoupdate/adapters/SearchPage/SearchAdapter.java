package com.vugido.vugidoupdate.adapters.SearchPage;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.models.search.SEarchDemoModel;
import com.vugido.vugidoupdate.models.search.search_suggestions.DATAItem;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    List<DATAItem> searchSuggestionList;
    private String key;

    public SearchAdapter(List<DATAItem> searchSuggestionList, Context context,String key) {
        this.searchSuggestionList = searchSuggestionList;
        this.context = context;
        this.key=key;
    }
    public void setItems(List<DATAItem> searchSuggestionList) {
        this.searchSuggestionList=searchSuggestionList;
    }

    Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_row_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // Glide.with(context).load(searchSuggestionList.get(position).getImage()).into(holder.imageView);;
     //   holder.textView.setText( searchSuggestionList.get(position).getSEARCHKEY());
        holder.textView.setText(Html.fromHtml(searchSuggestionList.get(position).getSEARCHKEY()));
    }

    @Override
    public int getItemCount() {
        return searchSuggestionList.size();
    }



    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView10);
            imageView=itemView.findViewById(R.id.imageView3);
        }
    }
}
