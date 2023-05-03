package com.foodhub.vugido.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.models.cart.PRESENTItem;
import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Locale;


public class MyCartProductsAdapter extends RecyclerView.Adapter<MyCartProductsAdapter.MyViewHolder> {


    private List<PRESENTItem> cartProductModelList;
    private Context context;
    private CartDelete cartDelete;
    public MyCartProductsAdapter(List<PRESENTItem> cartProductModelList, Context context){

        this.cartProductModelList=cartProductModelList;
        this.context=context;
        cartDelete= (CartDelete) context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item_row_design, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {




        final PRESENTItem ProductItem=cartProductModelList.get(position);

        holder.ProductName.setText(ProductItem.getENAME());
        Glide.with(context).load(ProductItem.getIMAGE()).into(holder.ProductImage);



        holder.ProductQuantity.setText(ProductItem.getQUANTITY()+""+ProductItem.getDESCRIPTION());

        if(ProductItem.getOFFER()>0){

            // offer exists..
            // show offer tag
            holder.OfferText.setVisibility(View.VISIBLE);
            holder.OfferText.setText(String.format(Locale.getDefault(),"%d%%", ProductItem.getOFFER()));
            // show cut price and also  final price
            holder.StrikedPrice.setVisibility(View.VISIBLE);
            holder.HorizontalLine.setVisibility(View.VISIBLE);

            // offer exists..
            holder.ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", offerPercentagePrice(ProductItem.getOFFER(),ProductItem.getPRICE())));
            holder.StrikedPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));

           // holder.ProductDescription.setText(ProductItem.getOFFER()+"%");


        }else{

            // no offer..
            holder.StrikedPrice.setVisibility(View.INVISIBLE);
            holder.HorizontalLine.setVisibility(View.INVISIBLE);
            holder.OfferText.setVisibility(View.INVISIBLE);
            holder.ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));
            holder.ProductDescription.setText(ProductItem.getDESCRIPTION());
        }



        // checking out of stock aand setting onclick listener
       if(ProductItem.getINSTOCK()==1){
            // out of stock
            holder.CartOutOfStock.setVisibility(View.VISIBLE);
            holder.OfferText.setVisibility(View.INVISIBLE);

        }else {
            //in stock
            holder.CartOutOfStock.setVisibility(View.INVISIBLE);


        }
        holder.ProductDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // delete the product from here..
                holder.ProductDeleteButton.setEnabled(false);
                cartDelete.cartDelete(position,ProductItem.getID(),ProductItem.getcID());

            }
        });





    }


    private int offerPercentagePrice(int offer, int price) {

        return  ((price*offer)/100);

    }


    @Override
    public int getItemCount() {
        return cartProductModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ProductImage;
        TextView OfferText,ProductName,ProductDescription,StrikedPrice,ProductPrice,ProductQuantity;
        View HorizontalLine;
        MaterialCardView ProductDeleteButton;
        RelativeLayout CartOutOfStock;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ProductImage=itemView.findViewById(R.id.cart_product_image);
            OfferText=itemView.findViewById(R.id.offer_text);
            ProductName=itemView.findViewById(R.id.cart_product_title);
            ProductDescription=itemView.findViewById(R.id.cart_product_description);
            StrikedPrice=itemView.findViewById(R.id.cart_product_cutted_price);
            ProductPrice=itemView.findViewById(R.id.cart_product_price);
            ProductQuantity=itemView.findViewById(R.id.cart_product_quantity);
            HorizontalLine=itemView.findViewById(R.id.cart_price_cut_divider);
            ProductDeleteButton=itemView.findViewById(R.id.cart_del_button);
            CartOutOfStock=itemView.findViewById(R.id.CartOutOfStock);
        }
    }


    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    public interface CartDelete{

        void cartDelete(int Card_ID,int pid,int cid);
    }
}
