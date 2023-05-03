package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.HomeMainRecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.ProductDetailsActivity;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.GROCERIESItem;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;

import java.util.List;
import java.util.Locale;

public class MiniGroceriesRecyclerViewAdapter extends RecyclerView.Adapter<MiniGroceriesRecyclerViewAdapter.MyViewHolder> {

    private List<GROCERIESItem> groceriesItemList;
    private Context context;
    private FragmentActivity fragmentActivity;

    public MiniGroceriesRecyclerViewAdapter(List<GROCERIESItem> groceriesItemList, Context context) {
        this.groceriesItemList = groceriesItemList;
        this.context = context;
        fragmentActivity= (FragmentActivity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final GROCERIESItem ProductItem=groceriesItemList.get(position);
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
        /*if(ProductItem.getISGRAMSET()==1){
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
        }else {
            //in stock
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

    private void callProductDetailsActivity(GROCERIESItem ProductItem) {
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
        bundle.putInt("CID",31);
        intent.putExtra("BUNDLE",bundle);
        fragmentActivity.startActivityForResult(intent,0);
    }
    @Override
    public int getItemCount() {
        return groceriesItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,OfferText,Cutted_Price,OriginalPrice,ProductQuantity;
        View horizontal_line;
        ConstraintLayout Item;
        RelativeLayout OutOfStockLayout;
        Button AddButton;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            OfferText=itemView.findViewById(R.id.offer_text);
            horizontal_line=itemView.findViewById(R.id.cut_price_line);
            Cutted_Price=itemView.findViewById(R.id.cutted_price);
            OriginalPrice=itemView.findViewById(R.id.product_price);
            ProductQuantity=itemView.findViewById(R.id.product_quantity);
            Item=itemView.findViewById(R.id.product_item);
            OutOfStockLayout=itemView.findViewById(R.id.out_of_stock_layout);
            AddButton=itemView.findViewById(R.id.Add_Button);
        }
    }
}
