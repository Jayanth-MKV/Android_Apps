package com.vugido.ap.sklm.home_interiors.adapters.homepage;

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
import com.vugido.ap.sklm.home_interiors.R;
import com.vugido.ap.sklm.home_interiors.models.HomePageModels.ServiceCategoroesModel;
import com.vugido.ap.sklm.home_interiors.models.HomePageModels.ServiceCategoryProducts;
import com.vugido.ap.sklm.home_interiors.models.MAIN_MODEL.PRODUCTSMENUItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder>implements Filterable {

   List<ServiceCategoroesModel> categoriesItemList;
    List<ServiceCategoryProducts> squareMediumModelList;
    Context context;
     List<ServiceCategoryProducts> filteredList;

    public SquareMediumRecyclerViewAdapter(List<ServiceCategoryProducts> squareMediumModelList, Context context,List<ServiceCategoroesModel> categoriesItemList) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        this.categoriesItemList=categoriesItemList;
        filteredList=squareMediumModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.square_horizontal_medium_view_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ServiceCategoryProducts squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getImage()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getTitle());
//        holder.price.setText("Rs."+squareMediumModel.getPRICE()+"/-");
//        holder.description.setText(String.format("%s %s", squareMediumModel.getQUANTITY(), squareMediumModel.getDESCRIPTION()));


//        if(new MyPreferences(context).getTableNumber()==0){
//            holder.eat.setVisibility(View.INVISIBLE);
//        }
//        holder.eat.setOnClickListener(v -> {
//
//            addToCart.eat(squareMediumModel.getPID(),holder.imageView,squareMediumModel.getIMAGE());
//        });

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ServiceCategoryProducts> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(squareMediumModelList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ServiceCategoryProducts item : squareMediumModelList) {
                    if (item.getTitle().toLowerCase().contains(filterPattern) || item.getTitle().toLowerCase().contains(filterPattern) || getCat(item.getCid()).contains(filterPattern)) {
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
            filteredList.addAll((Collection<? extends ServiceCategoryProducts>) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            //description=itemView.findViewById(R.id.description);
           // eat=itemView.findViewById(R.id.button2);
        }
    }


    private  String getCat(int id){
        for (ServiceCategoroesModel categoriesItem:categoriesItemList){
            if (categoriesItem.getId()==id){
return categoriesItem.getTitle();
            }
        }
        return "";
    }


}
