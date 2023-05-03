package com.vugido.vos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.vos.R;
import com.vugido.vos.models.AllProducts.DATA;
import com.vugido.vos.models.AllProducts.ITEMSItem;
import com.vugido.vos.models.AllProducts.MENUItem;


import java.util.List;
import java.util.Locale;

public class AllProductsRecyclerViewAdapter extends RecyclerView.Adapter<AllProductsRecyclerViewAdapter.MyViewHolder> {

    DATA data;
    Context context;
    List<ITEMSItem> itemsItemList;
    private Updater updater;

    public AllProductsRecyclerViewAdapter(DATA data, Context context) {
        this.data = data;
        itemsItemList=data.getITEMS();
        this.context = context;
        updater= (Updater) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final String quantity;
        final ITEMSItem itemsItem=itemsItemList.get(position);
        Glide.with(context).load(itemsItem.getIMAGE()).into(holder.ItemImage);
        holder.ItemName.setText(itemsItem.getTNAME());
        holder.Price.setText(String.format(Locale.getDefault(),"Rs.%s/-", itemsItem.getPP()));

        if(itemsItem.getQUANTITYUNIT()==1){
            //grams
            quantity=String.format(Locale.getDefault(),"%dg", itemsItem.getQUANTITY());
            holder.ItemQuantity.setText(quantity);
        }else {
            //units..
            quantity=String.format(Locale.getDefault(),"%d Units", itemsItem.getUNITINTERVAL());
            holder.ItemQuantity.setText(quantity);
        }

        holder.PriceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updater.priceEdit(itemsItem.getID(),itemsItem.getPP(),itemsItem.getPPPID(),quantity);
            }
        });






    }

    @Override
    public int getItemCount() {
        return itemsItemList.size();
    }

    static  class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView ItemImage;
        TextView ItemName,ItemQuantity,Price;
        Button PriceEdit;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemImage=itemView.findViewById(R.id.product_image);
            ItemName=itemView.findViewById(R.id.product_title);
            ItemQuantity=itemView.findViewById(R.id.product_quantity);
            Price=itemView.findViewById(R.id.product_price);
            PriceEdit=itemView.findViewById(R.id.button3);
        }
    }

    public interface Updater{

        void priceEdit(int pid, String price, int ppp_id, String quantity);

    }


}
