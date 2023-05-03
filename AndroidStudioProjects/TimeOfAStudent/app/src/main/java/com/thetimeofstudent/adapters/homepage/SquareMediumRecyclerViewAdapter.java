package com.thetimeofstudent.adapters.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thetimeofstudent.R;
import com.thetimeofstudent.models.MAIN_MODEL.HOMEPRODUCTSItem;
import com.thetimeofstudent.models.MAIN_MODEL.SECTIONSItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder>implements Filterable {

    List<SECTIONSItem> categoriesItemList;
    List<HOMEPRODUCTSItem> squareMediumModelList;
    Context context;
     List<HOMEPRODUCTSItem> filteredList;

    public SquareMediumRecyclerViewAdapter(List<HOMEPRODUCTSItem> squareMediumModelList, Context context, List<SECTIONSItem> categoriesItemList) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        this.categoriesItemList=categoriesItemList;
        filteredList=squareMediumModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ebook_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HOMEPRODUCTSItem squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getENAME());
        holder.description.setText(String.format("%s",squareMediumModel.getDESCRIPTION()));




    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<HOMEPRODUCTSItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(squareMediumModelList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (HOMEPRODUCTSItem item : squareMediumModelList) {
                    if ( item.getENAME().toLowerCase().contains(filterPattern) || getCat(item.getSID()).contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredList.clear();
            filteredList.addAll((Collection<? extends HOMEPRODUCTSItem>) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,description;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView3);
            title=itemView.findViewById(R.id.textView);
            description=itemView.findViewById(R.id.textView2);


        }
    }


    private  String getCat(int id){
        for (SECTIONSItem categoriesItem:categoriesItemList){
            if (categoriesItem.getSID()==id){
return categoriesItem.getCNAME();
            }
        }
        return "";
    }

    public interface AddToCart{

        void eat(int pid, ImageView l,String url);
    }
}
