package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.OfferAdapters;

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
import com.foodhub.vugido.models.OffersPageModel.SINGLEPRODUCTSItem;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;

import java.util.List;
import java.util.Locale;


public class SingleProductOffersAdapter extends RecyclerView.Adapter<SingleProductOffersAdapter.MyViewHolder>{

    private     List<SINGLEPRODUCTSItem> dataItemList;
    private Context context;
    private FragmentActivity fragmentActivity;


    public SingleProductOffersAdapter(List<SINGLEPRODUCTSItem> singleproductsItemList, Context context) {
        this.context=context;
        dataItemList=singleproductsItemList;
        fragmentActivity= (FragmentActivity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.pocket_product_item_design, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final SINGLEPRODUCTSItem ProductItem=dataItemList.get(position);

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
            holder.AddButton.setOnClickListener(v -> callProductDetailsActivity(ProductItem));
            holder.Item.setOnClickListener(v -> callProductDetailsActivity(ProductItem));



        }






    }



    private void callProductDetailsActivity(SINGLEPRODUCTSItem ProductItem) {
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
        fragmentActivity.startActivityForResult(intent,0);
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
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
            imageView = itemView.findViewById(R.id.pocket_product_image);
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
