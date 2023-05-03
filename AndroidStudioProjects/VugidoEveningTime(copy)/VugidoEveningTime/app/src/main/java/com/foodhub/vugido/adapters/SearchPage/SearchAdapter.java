package com.foodhub.vugido.adapters.SearchPage;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.models.search_indexer.DATAItem;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    List<DATAItem> searchSuggestionList;
    private String key;
    private SEARCH_ID_CLICKED search_id_clicked;

    public SearchAdapter(List<DATAItem> searchSuggestionList, Context context, String key) {
        this.searchSuggestionList = searchSuggestionList;
        this.context = context;
        search_id_clicked= (SEARCH_ID_CLICKED) context;
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
        Glide.with(context).load(searchSuggestionList.get(position).getIMAGE()).into(holder.imageView);;
        holder.textView.setText(Html.fromHtml(searchSuggestionList.get(position).getSEARCHKEY()));

        holder.searchItem.setOnClickListener(v -> {
            Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show();
            search_id_clicked.searchID_Clicked(searchSuggestionList.get(position).getSEARCHID(),
                    searchSuggestionList.get(position).getCID());
        });


    }

    @Override
    public int getItemCount() {
        return searchSuggestionList.size();
    }



    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        ConstraintLayout searchItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView10);
            imageView=itemView.findViewById(R.id.imageView3);
            searchItem=itemView.findViewById(R.id.search_item);
        }
    }

    public interface SEARCH_ID_CLICKED{

        void searchID_Clicked(int SID,int CID);
    }
}
