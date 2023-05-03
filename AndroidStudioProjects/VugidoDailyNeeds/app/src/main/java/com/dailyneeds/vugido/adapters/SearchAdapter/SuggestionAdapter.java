package com.dailyneeds.vugido.adapters.SearchAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyneeds.vugido.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> implements Filterable {

    @NonNull
    private LayoutInflater inflater;
    private List<String> data;
    private List<String> FilteredProductList;


    public SuggestionAdapter(@NonNull Context context,List<String> data) {
        inflater = LayoutInflater.from(context);
        this.data=data;
        FilteredProductList=new ArrayList<>(data);
    }

  /*  public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }*/

  /*  private String getItem(int position) {
        return data.get(position);
    }*/

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.reel_search_recycler_row,parent,false);

        return new SuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {

        holder.textView.setText(data.get(position));


       // holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class SuggestionViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        SuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tvSuggestion);
        }
    }


      @Override
    public Filter getFilter() {


        return ProductsFiltered;
    }

    private Filter ProductsFiltered=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> FilteredProducts=new ArrayList<>();

            if(charSequence== null || charSequence.length()==0){

                FilteredProducts.addAll(FilteredProductList);

            }else {
                String pattern=charSequence.toString().toLowerCase().trim();

                for(String product:FilteredProductList){

                    if(product.toLowerCase().contains(pattern)){

                        FilteredProducts.add(product);

                    }

                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=FilteredProducts;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {


            data.clear();
            data.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();

        }
    };


}