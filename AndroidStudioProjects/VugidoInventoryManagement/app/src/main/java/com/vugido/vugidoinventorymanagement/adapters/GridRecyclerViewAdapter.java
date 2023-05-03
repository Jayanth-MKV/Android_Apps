package com.vugido.vugidoinventorymanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.models.BaseProduct;
import com.vugido.vugidoinventorymanagement.models.all_products_of_category.DATAItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.MyViewHolder> implements Filterable {

    private     List<DATAItem> dataItemList;
    private Context context;
    private int CID;
    private List<DATAItem> filteredList;
    private EditProduct editProduct;

    //all products lists

    public GridRecyclerViewAdapter(List<DATAItem> dataItemList, Context context, int CID) {
        filteredList=dataItemList;
        this.dataItemList=new ArrayList<>(dataItemList);
        this.context=context;
        this.CID=CID;
        editProduct= (EditProduct) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_design, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DATAItem ProductItem=filteredList.get(position);

        holder.title.setText(ProductItem.getTNAME());
        Glide.with(context).load(ProductItem.getIMAGE()).into(holder.imageView);


        holder.Toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editProduct.toggleProduct(CID,ProductItem.getPID());
            }
        });

        if(ProductItem.getINSTOCK()==0){
            //ok product exists
            holder.Toggle.setBackgroundColor(Color.GREEN);
            holder.Toggle.setText("In stock");

        }else {
            holder.Toggle.setBackgroundColor(Color.RED);
            holder.Toggle.setText("Out Of Stock");
            // ot of stock
        }

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
           // holder.OutOfStockLayout.setVisibility(View.VISIBLE);
            holder.OfferText.setVisibility(View.INVISIBLE);
            // disable button..
            holder.AddButton.setEnabled(false);
            holder.Item.setEnabled(false);
        }else {
            //in stock
            holder.Item.setEnabled(true);
            //holder.OutOfStockLayout.setVisibility(View.INVISIBLE);
            holder.AddButton.setEnabled(true);
            holder.AddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editProduct.editProduct(CID,ProductItem.getPID(),ProductItem.getTNAME(),ProductItem.getPRICE());
                  // callProductDetailsActivity(ProductItem);
                }
            });
            holder.Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editProduct.editProduct(CID,ProductItem.getPID(),ProductItem.getTNAME(),ProductItem.getPRICE());

                   // callProductDetailsActivity(ProductItem);
                }
            });

            // set here on click listener

        }






    }


    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DATAItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataItemList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DATAItem item : dataItemList) {
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

    private void callProductDetailsActivity(DATAItem ProductItem) {
//        Intent intent=new Intent(context, ProductDetailsActivity.class);
//        Bundle bundle=new Bundle();
//        BaseProduct baseProduct =new BaseProduct();
//        baseProduct.setOFFERSTATUS(ProductItem.getOFFERSTATUS());
//        baseProduct.setENAME(ProductItem.getENAME());
//        baseProduct.setTNAME(ProductItem.getTNAME());
//        baseProduct.setCARTLIMIT(ProductItem.getCARTLIMIT());
//        baseProduct.setDESCRIPTION(ProductItem.getDESCRIPTION());
//        baseProduct.setIMAGE(ProductItem.getIMAGE());
//        baseProduct.setINSTOCK(ProductItem.getINSTOCK());
//        baseProduct.setISGRAMSET(ProductItem.getISGRAMSET());
//        baseProduct.setOFFER(ProductItem.getOFFER());
//        baseProduct.setOFFERLIMIT(ProductItem.getOFFERLIMIT());
//        baseProduct.setPID(ProductItem.getPID());
//        baseProduct.setPRICE(ProductItem.getPRICE());
//        baseProduct.setQUANTITY(ProductItem.getQUANTITY());
//        baseProduct.setSID(ProductItem.getSID());
//        bundle.putParcelable("BASE_OBJECT",baseProduct);
//        bundle.putInt("CID",CID);
//        intent.putExtra("BUNDLE",bundle);
//        context.startActivity(intent);
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
       // RelativeLayout OutOfStockLayout;
        Button AddButton,Toggle;
        ConstraintLayout Item;


        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            Toggle=itemView.findViewById(R.id.button3);
            OfferText=itemView.findViewById(R.id.offer_text);
            horizontal_line=itemView.findViewById(R.id.cut_price_line);
            Cutted_Price=itemView.findViewById(R.id.cutted_price);
            OriginalPrice=itemView.findViewById(R.id.product_price);
            ProductQuantity=itemView.findViewById(R.id.product_quantity);
           // OutOfStockLayout=itemView.findViewById(R.id.out_of_stock_layout);
            AddButton=itemView.findViewById(R.id.Add_Button);
            Item=itemView.findViewById(R.id.product_item);

        }



    }

    public interface EditProduct{

        void editProduct(int cid,int pid,String name,float price);
        void toggleProduct(int cid,int pid);
    }
}
