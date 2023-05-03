package com.vugido_business_panel.adapter;

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
import com.vugido_business_panel.R;
import com.vugido_business_panel.models.All_Products.DATA;
import com.vugido_business_panel.models.All_Products.ITEMSItem;
import com.vugido_business_panel.models.All_Products.MENUItem;

import java.util.List;
import java.util.Locale;

public class AllProductsRecyclerViewAdapter extends RecyclerView.Adapter<AllProductsRecyclerViewAdapter.MyViewHolder> {

    DATA data;
    Context context;
    List<ITEMSItem> itemsItemList;
    List<MENUItem> menuItemList;
    private Updater updater;

    public AllProductsRecyclerViewAdapter(DATA data, Context context) {
        this.data = data;
        itemsItemList=data.getITEMS();
        menuItemList=data.getMENU();
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

        final ITEMSItem itemsItem=itemsItemList.get(position);
        Glide.with(context).load(itemsItem.getIMAGE()).into(holder.ItemImage);
        holder.ItemName.setText(itemsItem.getTNAME());

        for(MENUItem menuItem:menuItemList){

            if(menuItem.getCID()==itemsItem.getSID()){
                holder.Category.setText(String.format("Type :%s", menuItem.getSUBNAME()));
                break;
            }
        }

        holder.ItemQuantity.setText(String.format("%s %s", itemsItem.getQUANTITY(), itemsItem.getDESCRIPTION()));
        if(itemsItem.getOFFER()!=0){
            // offer exists..
            holder.ItemOffer.setVisibility(View.VISIBLE);
            holder.ItemOffer.setText(String.format(Locale.getDefault(),"%d%%", itemsItem.getOFFER()));
            // set price after offer


        }else {

            // no offer
            holder.ItemOffer.setVisibility(View.GONE);
            holder.ItemOffer.setVisibility(View.GONE);
            holder.Price.setText(String.format(Locale.getDefault(),"Rs.%d/-", itemsItem.getPRICE()));
        }

        if(itemsItem.getINSTOCK()==0){
            // active..
            holder.ItemState.setText("IN-STOCK");
            holder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.main_green_color));


        }else {
            holder.ItemState.setText("OUT OF STOCK");
            holder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.red));
            // not active
        }

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updater.delete(itemsItem.getPID());
            }
        });

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updater.edit(itemsItem.getPID());

            }
        });


      /*  holder.PriceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updater.priceUpdater(itemsItem.getID());
            }
        });

        holder.ItemState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updater.toggleInStock(itemsItem.getID());
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return itemsItemList.size();
    }

    static  class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView ItemImage;
        TextView ItemName,ItemOffer,ItemQuantity,Price,CutPrice,Category;
        View CutLine;
        Button ItemState,Edit,Delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemImage=itemView.findViewById(R.id.product_image);
            ItemName=itemView.findViewById(R.id.product_title);
            ItemOffer=itemView.findViewById(R.id.offer_text);
            ItemQuantity=itemView.findViewById(R.id.product_quantity);
            Price=itemView.findViewById(R.id.product_price);
            CutPrice=itemView.findViewById(R.id.cutted_price);
            CutLine=itemView.findViewById(R.id.cut_price_line);
            ItemState=itemView.findViewById(R.id.availability);
            Category=itemView.findViewById(R.id.category_type);
            Edit=itemView.findViewById(R.id.button);
            Delete=itemView.findViewById(R.id.button3);
        }
    }

    public interface Updater{

       // void priceUpdater(int pid);
      //  void toggleInStock(int pid);
        void delete(int pid);
        void edit(int pid);

    }


}
