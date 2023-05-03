package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.SearchPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.ProductDetailsActivity;
import com.foodhub.vugido.models.search.search_submit.DATAItem;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


public class SearchOnClickResultsAdapter extends RecyclerView.Adapter<SearchOnClickResultsAdapter.MyViewHolder> implements Filterable {

    private     List<com.foodhub.vugido.models.search.search_submit.DATAItem> dataItemList;
    private Context context;
    private List<com.foodhub.vugido.models.search.search_submit.DATAItem> filteredList;

    //all products lists

    public SearchOnClickResultsAdapter(List<DATAItem> dataItemList, Context context) {
        filteredList=dataItemList;
        this.dataItemList=new ArrayList<>(dataItemList);
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_design, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final com.foodhub.vugido.models.search.search_submit.DATAItem ProductItem=filteredList.get(position);

        holder.title.setText(ProductItem.getTNAME());
        Glide.with(context).load(ProductItem.getIMAGE()).into(holder.imageView);



        if(ProductItem.getOFFERSTATUS()==1){

            // offer exists..
            // show offer tag
            holder.OfferText.setVisibility(View.VISIBLE);
            holder.OfferText.setText(String.format(Locale.getDefault(),"%d%%", ProductItem.getOFFER()));
            // show cut price and also  final price
            holder.Cutted_Price.setVisibility(View.VISIBLE);
            holder.horizontal_line.setVisibility(View.VISIBLE);
            holder.OriginalPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", BaseProduct.offerPercentagePrice(ProductItem.getOFFER(), ProductItem.getPRICE(), ProductItem.getOFFERLIMIT())));
            holder.Cutted_Price.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));



        }else{

            // no offer..
            holder.Cutted_Price.setVisibility(View.INVISIBLE);
            holder.horizontal_line.setVisibility(View.INVISIBLE);
            holder.OfferText.setVisibility(View.INVISIBLE);
            holder.OriginalPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));
        }

        // quantity settings..
       /* if(ProductItem.getISGRAMSET()==1){
            // quantity is expressed in grams..
            // so check the value if it is greater than 1000 g express in kg's
            holder.ProductQuantity.setText(getGramQuantity(ProductItem.getQUANTITY(),ProductItem.getDESCRIPTION()));

        }else {*/

            // it's normal..
            // so concatenate the quantity value with description..

        holder.ProductQuantity.setText(String.format(Locale.getDefault(),"%d %s", ProductItem.getQUANTITY(), ProductItem.getDESCRIPTION()));


       // }

        // checking out of stock aand setting onclick listener
        if(ProductItem.getINSTOCK()==1){
            // out of stock
            holder.OutOfStockLayout.setVisibility(View.VISIBLE);
            holder.OfferText.setVisibility(View.INVISIBLE);
            // disable button..
            holder.AddButton.setEnabled(false);
            holder.Item.setEnabled(false);
        }else {
            //in stock
            holder.Item.setEnabled(true);
            holder.OutOfStockLayout.setVisibility(View.INVISIBLE);
            holder.AddButton.setEnabled(true);
            holder.AddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   callProductDetailsActivity(ProductItem);
                }
            });
            holder.Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  callProductDetailsActivity(ProductItem);
                }
            });

            // set here on click listener

        }






    }


    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<com.foodhub.vugido.models.search.search_submit.DATAItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataItemList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (com.foodhub.vugido.models.search.search_submit.DATAItem item : dataItemList) {
                    if (item.getTNAME().toLowerCase().contains(filterPattern) || item.getENAME().toLowerCase().contains(filterPattern) || item.getTAG().toLowerCase().contains(filterPattern)) {
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
            filteredList.addAll((Collection<? extends DATAItem>) results.values);
            notifyDataSetChanged();
        }
    };

    private void callProductDetailsActivity(com.foodhub.vugido.models.search.search_submit.DATAItem ProductItem) {
        Intent intent=new Intent(context, ProductDetailsActivity.class);
        Bundle bundle=new Bundle();
        BaseProduct baseProduct =new BaseProduct();
        baseProduct.setOFFERSTATUS(ProductItem.getOFFERSTATUS());
        baseProduct.setENAME(ProductItem.getENAME());
        baseProduct.setTNAME(ProductItem.getTNAME());
        baseProduct.setCARTLIMIT(ProductItem.getCARTLIMIT());
        baseProduct.setDESCRIPTION(ProductItem.getDESCRIPTION());
        baseProduct.setIMAGE(ProductItem.getIMAGE());
        baseProduct.setINSTOCK(ProductItem.getINSTOCK());
        baseProduct.setISGRAMSET(ProductItem.getISGRAMSET());
        baseProduct.setOFFER(ProductItem.getOFFER());
        baseProduct.setOFFERLIMIT(ProductItem.getOFFERLIMIT());
        baseProduct.setPID(ProductItem.getPID());
        baseProduct.setPRICE(ProductItem.getPRICE());
        baseProduct.setQUANTITY(ProductItem.getQUANTITY());
        baseProduct.setSID(ProductItem.getSID());
        bundle.putParcelable("BASE_OBJECT",baseProduct);
        bundle.putInt("CID",ProductItem.getCID());
        intent.putExtra("BUNDLE",bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder  {

        ImageView imageView;
        TextView title,OfferText,Cutted_Price,OriginalPrice,ProductQuantity;
        View horizontal_line;
        //Button
        RelativeLayout OutOfStockLayout;
        Button AddButton;
        ConstraintLayout Item;


        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);

            OfferText=itemView.findViewById(R.id.offer_text);
            horizontal_line=itemView.findViewById(R.id.cut_price_line);
            Cutted_Price=itemView.findViewById(R.id.cutted_price);
            OriginalPrice=itemView.findViewById(R.id.product_price);
            ProductQuantity=itemView.findViewById(R.id.product_quantity);
            OutOfStockLayout=itemView.findViewById(R.id.out_of_stock_layout);
            AddButton=itemView.findViewById(R.id.Add_Button);
            Item=itemView.findViewById(R.id.product_item);

        }



    }
}
