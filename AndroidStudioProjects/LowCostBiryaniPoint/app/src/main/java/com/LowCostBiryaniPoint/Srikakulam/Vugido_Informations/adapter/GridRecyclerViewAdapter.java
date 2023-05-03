package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.adapter;

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

import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.R;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.activities.ProductDetailsActivity;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.HomePageModel.ALLPRODUCTSItem;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.base_product.BaseProduct;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.MyViewHolder> implements Filterable {

    private     List<ALLPRODUCTSItem> dataItemList;
    private Context context;
    private int CID;
    private List<ALLPRODUCTSItem> filteredList;

    //all products lists

    public GridRecyclerViewAdapter(List<ALLPRODUCTSItem> dataItemList, Context context, int CID) {
        filteredList=dataItemList;
        this.dataItemList=new ArrayList<>(dataItemList);
        this.context=context;
        this.CID=CID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_design, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final ALLPRODUCTSItem ProductItem=filteredList.get(position);

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
//                    holder.InsDecBtn.setVisibility(View.VISIBLE);
//                    holder.AddButton.setVisibility(View.INVISIBLE);
                   callProductDetailsActivity(ProductItem);
                }
            });
            holder.Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  callProductDetailsActivity(ProductItem);
                }
            });

             //set here on click listener

        }

//        holder.Ins.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        holder.Dec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });






    }


    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ALLPRODUCTSItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataItemList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ALLPRODUCTSItem item : dataItemList) {
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
            filteredList.addAll((Collection<? extends ALLPRODUCTSItem>) results.values);
            notifyDataSetChanged();
        }
    };

    private void callProductDetailsActivity(ALLPRODUCTSItem ProductItem) {
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
        bundle.putInt("CID",CID);
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
        TextView title,OfferText,Cutted_Price,OriginalPrice,ProductQuantity,Ins,Dec,Count;
        View horizontal_line;
        //Button
        RelativeLayout OutOfStockLayout;
        Button AddButton;
        ConstraintLayout Item,InsDecBtn;



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
            InsDecBtn=itemView.findViewById(R.id.inc_dec_btn);
            Ins=itemView.findViewById(R.id.textView);
            Dec=itemView.findViewById(R.id.textView3);
            Count=itemView.findViewById(R.id.textView2);

        }



    }

    public interface ItemCount{

        void itemCount(boolean isIncrement,int cid,int pid);
    }
}
