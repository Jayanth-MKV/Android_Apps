package com.foodhub.vugido.sklm.fresh_cuts.adapters.cart;

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
import com.google.android.material.card.MaterialCardView;
import com.foodhub.vugido.sklm.fresh_cuts.R;
import com.foodhub.vugido.sklm.fresh_cuts.models.BaseProduct;
import com.foodhub.vugido.sklm.fresh_cuts.models.cart.PRODUCTSItem;

import java.util.List;
import java.util.Locale;


public class MyCartProductsAdapter extends RecyclerView.Adapter<MyCartProductsAdapter.MyViewHolder> {


    private List<PRODUCTSItem> cartProductModelList;
    private Context context;
    private List<String> categoriesItemList;
    private CartDelete cartDelete;
    private List<Integer> count;
    public MyCartProductsAdapter(List<PRODUCTSItem> cartProductModelList, List<Integer> count, Context context, List<String> categories){

        this.cartProductModelList=cartProductModelList;
        this.context=context;
        this.count=count;
        this.categoriesItemList=categories;
        cartDelete= (CartDelete) context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item_row_design, parent, false);

        return new MyViewHolder(view);
    }

    private int  getQuantityPrice(float price, PRODUCTSItem baseProduct,int count){

//        if(baseProduct.getISGRAMSET()==1){
//            // in grams..
//            float kg = baseProduct.getCARTQUANTITY()/1000f;
//            return Math.round(kg*price);
//
//        }else {
            // in units or packets etc
            return Math.round(count*price);

       // }

    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {




        final PRODUCTSItem ProductItem=cartProductModelList.get(position);

        holder.ProductName.setText(ProductItem.getTNAME());
        Glide.with(context).load(ProductItem.getIMAGE()).into(holder.ProductImage);


        holder.ProductDescription.setText(categoriesItemList.get(position));


        if(ProductItem.getOFFER()>0){

            // offer exists..
            // show offer tag
            holder.OfferText.setVisibility(View.VISIBLE);
            holder.OfferText.setText(String.format(Locale.getDefault(),"%d%%", ProductItem.getOFFER()));
            // show cut price and also  final price
            holder.StrikedPrice.setVisibility(View.VISIBLE);
            holder.HorizontalLine.setVisibility(View.VISIBLE);

            // offer exists..
            holder.ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", BaseProduct.offerPercentagePrice(ProductItem.getOFFER(), getQuantityPrice(ProductItem.getPRICE(),ProductItem,count.get(position)), ProductItem.getOFFERLIMIT())));
            holder.StrikedPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", getQuantityPrice(ProductItem.getPRICE(),ProductItem,count.get(position))));



        }else{

            // no offer..
            holder.StrikedPrice.setVisibility(View.INVISIBLE);
            holder.HorizontalLine.setVisibility(View.INVISIBLE);
            holder.OfferText.setVisibility(View.INVISIBLE);
            holder.ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", getQuantityPrice(ProductItem.getPRICE(),ProductItem,count.get(position))));
        }

        // quantity settings..
       // if(ProductItem.getISGRAMSET()==1){
            // quantity is expressed in grams..
            // so check the value if it is greater than 1000 g express in kg's
            holder.ProductQuantity.setText(count.get(position)+" "+ProductItem.getDESCRIPTION());

//        }else {
//
//            // it's normal..
//            // so concatenate the quantity value with description..
//            holder.ProductQuantity.setText(String.format(Locale.getDefault(),"%d", ProductItem.getCARTQUANTITY()));
//
//
//        }

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
                cartDelete.cartDelete(ProductItem.getPID());

            }
        });



      /*  final DATAItem riceitemsItem=cartProductModelList.get(position);

        final ProductBaseModel productBaseModel=new ProductBaseModel();
        productBaseModel.setENAME(riceitemsItem.getENAME());
        productBaseModel.setIMAGE(riceitemsItem.getIMAGE());
        productBaseModel.setINSTOCK(riceitemsItem.getINSTOCK());
        productBaseModel.setOFFER(riceitemsItem.getOFFER());
        productBaseModel.setUNITINTERVAL(riceitemsItem.getUNITINTERVAL());
        productBaseModel.setSID(riceitemsItem.getSID());
        productBaseModel.setPRICE(riceitemsItem.getPRICE());
        productBaseModel.setOFFERSTATUS(riceitemsItem.getOFFERSTATUS());
        productBaseModel.setPID(riceitemsItem.getPID());
        productBaseModel.setDescription(riceitemsItem.getDESCRIPTION());
        productBaseModel.setTNAME(riceitemsItem.getTNAME());
        productBaseModel.setDEFAULTSIZE(riceitemsItem.getDEFAULTSIZE());
        productBaseModel.setQUANTITYUNIT(riceitemsItem.getQUANTITYUNIT());
        productBaseModel.setOFFERLIMIT(riceitemsItem.getOFFERLIMIT());
        productBaseModel.setQUANTITY(riceitemsItem.getQUANTITY());
        productBaseModel.setL(riceitemsItem.getL());
        productBaseModel.setS(riceitemsItem.getS());
        productBaseModel.setM(riceitemsItem.getM());
        productBaseModel.setTAG(riceitemsItem.getTAG());
        productBaseModel.setQUALIFIER(riceitemsItem.getQUALIFIER());
*/


/*

        Glide.with(context)
                .load(riceitemsItem.getIMAGE())
                .into(holder.ProductImage);
        holder.ProductName.setText(riceitemsItem.getTNAME());
        holder.ProductQuantity.setText(ProductBaseModel.getProductQualifierQuantity(productBaseModel));
        holder.ProductDescription.setText(riceitemsItem.getDESCRIPTION());

        //also check for in stock or not..

        if(riceitemsItem.getINSTOCK()==0){

            // show not available..


        }else {


            // now make all... clicks enabled..

        }


        //check offer status
        if(riceitemsItem.getOFFERSTATUS()==0){

            // no offer
            holder.OfferText.setVisibility(View.GONE);
            holder.HorizontalLine.setVisibility(View.GONE);
            holder.StrikedPrice.setVisibility(View.GONE);
            // also hide all other parameters like cut price..
            holder.ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductBaseModel.getProductQualifierPrice(productBaseModel)));



        }else{

            //offer exists
            holder.OfferText.setVisibility(View.VISIBLE);
            holder.HorizontalLine.setVisibility(View.VISIBLE);
            holder.StrikedPrice.setVisibility(View.VISIBLE);

            // setting all offer values...
            holder.ProductPrice.setText(String.format("Rs.%s/-", ProductBaseModel.offerPercentagePrice(Double.parseDouble(String.valueOf(riceitemsItem.getOFFER())), Double.parseDouble(String.valueOf(riceitemsItem.getPRICE()*riceitemsItem.getCOUNT())),riceitemsItem.getOFFERLIMIT())));
            holder.StrikedPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductBaseModel.getProductQualifierPrice(productBaseModel)));
            holder.OfferText.setText(String.format(Locale.getDefault(),"%d%%", riceitemsItem.getOFFER()));

        }




*/

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


    public interface CartDelete{

        void cartDelete(int Card_ID);
    }
}
