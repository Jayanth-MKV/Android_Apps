package com.foodhub.vugido.sklm.fresh_cuts.adapters.homepage;

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
import com.foodhub.vugido.sklm.fresh_cuts.R;
import com.foodhub.vugido.sklm.fresh_cuts.app_config.MyPreferences;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.HOMEPRODUCTSItem;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.SECTIONSItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder>implements Filterable {

    List<SECTIONSItem> categoriesItemList;
    List<HOMEPRODUCTSItem> squareMediumModelList;
    Context context;
     List<HOMEPRODUCTSItem> filteredList;
     AddToCart addToCart;

    public SquareMediumRecyclerViewAdapter(List<HOMEPRODUCTSItem> squareMediumModelList, Context context,List<SECTIONSItem> categoriesItemList) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        this.categoriesItemList=categoriesItemList;
        filteredList=squareMediumModelList;
        addToCart= (AddToCart) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HOMEPRODUCTSItem squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getENAME());

        if(squareMediumModel.getOFFER()==0){
            holder.offer.setVisibility(View.GONE);
            holder.price.setText("Rs."+squareMediumModel.getPRICE()+"/-");




        }else {
            holder.offer.setText(squareMediumModel.getOFFER()+"%");

        }
        holder.description.setText(String.format("%s %s", squareMediumModel.getQUANTITY(), squareMediumModel.getDESCRIPTION()));



        if(squareMediumModel.getINSTOCK()==0){

            holder.no_stock.setVisibility(View.VISIBLE);
        }else
            holder.no_stock.setVisibility(View.GONE);

        holder.add.setOnClickListener(v -> {

            holder.ll.setVisibility(View.VISIBLE);
            holder.add.setVisibility(View.INVISIBLE);
            addToCart.eat(squareMediumModel.getPID(),holder.imageView,squareMediumModel.getIMAGE(),1);

        });

        holder.inc.setOnClickListener(v -> {
            addToCart.eat(squareMediumModel.getPID(),holder.imageView,squareMediumModel.getIMAGE(),1);
            holder.value.setText(String.valueOf(checkPids(squareMediumModel.getPID())+1));
        });

        holder.dec.setOnClickListener(v -> {

            int c=checkPids(squareMediumModel.getPID());

            if (c==1)
            {
                holder.ll.setVisibility(View.GONE);
                holder.add.setVisibility(View.VISIBLE);
            }
            addToCart.eat(squareMediumModel.getPID(),holder.imageView,squareMediumModel.getIMAGE(),0);
            holder.value.setText(String.valueOf(c-1));


                    });

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
        TextView title,description,price,cutp,offer,value;
        Button add,inc,dec;
        View cline,no_stock;
        View ll;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_image);
            title=itemView.findViewById(R.id.product_title);
            description=itemView.findViewById(R.id.product_quantity);
            price=itemView.findViewById(R.id.product_price);
            cutp=itemView.findViewById(R.id.cutted_price);
            cline=itemView.findViewById(R.id.cut_price_line);
            offer=itemView.findViewById(R.id.offer_text);
            add=itemView.findViewById(R.id.Add_Button);
            no_stock=itemView.findViewById(R.id.out_of_stock_layout);

            inc=itemView.findViewById(R.id.inc);
            dec=itemView.findViewById(R.id.dec);

            value=itemView.findViewById(R.id.textView2);


            ll=itemView.findViewById(R.id.ll);

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

    private int checkPids(int pid){

    MyPreferences myPreferences=new MyPreferences(context);
    String pids=myPreferences.getCartProducts();
    String[] a=pids.split(",");
        int c=0;
        for (int i=0;i<a.length;i++){

        if(a[i].equals(String.valueOf(pid)))
        {
            c+=1;
        }
    }
        return c;

    }
    public interface AddToCart{

        void eat(int pid, ImageView l,String url,int s);
    }
}
