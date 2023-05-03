package com.vugido.foodhub.hungrybirdsadmin.adapters.orders_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.foodhub.hungrybirdsadmin.R;
import com.vugido.foodhub.hungrybirdsadmin.models.orders.ordered_items.DATAItem;

import java.util.List;
import java.util.Locale;


public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.MyViewHolder>{

    private List<DATAItem> dataItemList;
    private Context context;
    private FragmentActivity fragmentActivity;


    public ProductItemAdapter(List<DATAItem> singleproductsItemList, Context context) {
        this.context=context;
        dataItemList=singleproductsItemList;
        fragmentActivity= (FragmentActivity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ordered_item_design, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DATAItem ProductItem=dataItemList.get(position);

        holder.title.setText(ProductItem.getENAME());
        Glide.with(context).load(ProductItem.getIMAGE()).into(holder.imageView);



//        if(ProductItem.getOFFER()>0){
//
//            // offer exists..
//            // show offer tag
//            holder.OfferText.setVisibility(View.VISIBLE);
//            holder.OfferText.setText(String.format(Locale.getDefault(),"%d%%", ProductItem.getOFFER()));
//            // show cut price and also  final price
//            holder.Cutted_Price.setVisibility(View.VISIBLE);
//            holder.horizontal_line.setVisibility(View.VISIBLE);
//            holder.OriginalPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", offerPercentagePrice(ProductItem.getOFFER(), (ProductItem.getPRICE()*ProductItem.getCOUNT()), ProductItem.getOFFERLIMIT())));
//            holder.Cutted_Price.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()*ProductItem.getCOUNT()));
//
//
//
//        }else{
//
//            // no offer..
//            holder.Cutted_Price.setVisibility(View.INVISIBLE);
//            holder.horizontal_line.setVisibility(View.INVISIBLE);
//            holder.OfferText.setVisibility(View.INVISIBLE);
//            holder.OriginalPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()*ProductItem.getCOUNT()));
//        }
//
//        holder.ProductQuantity.setText(String.format(Locale.getDefault(),"%d %s", ProductItem.getCOUNT(), ProductItem.getDESCRIPTION()));
//
//
//       // }
//
//        // checking out of stock aand setting onclick listener
//        if(ProductItem.getINSTOCK()==1){
//            // out of stock
//            holder.OutOfStockLayout.setVisibility(View.VISIBLE);
//            holder.OfferText.setVisibility(View.INVISIBLE);
//            // disable button..
////            holder.AddButton.setEnabled(false);
////            holder.Item.setEnabled(false);
//        }else {
//            //in stock
////            holder.Item.setEnabled(true);
//            holder.OutOfStockLayout.setVisibility(View.INVISIBLE);
//            holder.AddButton.setEnabled(true);
//            holder.AddButton.setOnClickListener(v -> callProductDetailsActivity(ProductItem));
//            holder.Item.setOnClickListener(v -> callProductDetailsActivity(ProductItem));



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

        }



    }
}
