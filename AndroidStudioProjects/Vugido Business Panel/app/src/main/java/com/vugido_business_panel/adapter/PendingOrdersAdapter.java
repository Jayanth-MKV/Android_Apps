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
import com.vugido_business_panel.models.orders.DATAItem;

import java.util.List;
import java.util.Locale;

public class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.MyViewHolder> {

    List<DATAItem> data;
    Context context;
    private OrderAcceptance orderAcceptancel;
    public PendingOrdersAdapter(List<DATAItem> data, Context context) {
        this.data = data;
        this.context = context;
        orderAcceptancel= (OrderAcceptance) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pending_order_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final DATAItem itemsItem=data.get(position);

        holder.t.setText("Table No : "+itemsItem.getTNO());
        holder.tc.setText("Items Count : "+itemsItem.getIC());
        holder.tp.setText("Total Price : "+itemsItem.getTP());

        if(itemsItem.getOM()==1){
            holder.em.setText("Eat Here");

        }else {
            holder.em.setText("Parcel");

        }

        if(itemsItem.getSTATUS()==0){
            holder.status.setText("Pending");
        }else{

        }
        holder.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.status.setEnabled(false);
                orderAcceptancel.orderAcceptance(itemsItem.getOID(),itemsItem.getTNO(),itemsItem.getUID());
            }
        });

        holder.items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//        Glide.with(context).load(itemsItem.getIMAGE()).into(holder.ItemImage);
//        holder.ItemName.setText(itemsItem.getTNAME());
//
//        for(MENUItem menuItem:menuItemList){
//
//            if(menuItem.getCID()==itemsItem.getSID()){
//                holder.Category.setText(String.format("Type :%s", menuItem.getSUBNAME()));
//                break;
//            }
//        }
//
//        holder.ItemQuantity.setText(String.format("%s %s", itemsItem.getQUANTITY(), itemsItem.getDESCRIPTION()));
//        if(itemsItem.getOFFER()!=0){
//            // offer exists..
//            holder.ItemOffer.setVisibility(View.VISIBLE);
//            holder.ItemOffer.setText(String.format(Locale.getDefault(),"%d%%", itemsItem.getOFFER()));
//            // set price after offer
//
//
//        }else {
//
//            // no offer
//            holder.ItemOffer.setVisibility(View.GONE);
//            holder.ItemOffer.setVisibility(View.GONE);
//            holder.Price.setText(String.format(Locale.getDefault(),"Rs.%d/-", itemsItem.getPRICE()));
//        }
//
//        if(itemsItem.getINSTOCK()==0){
//            // active..
//            holder.ItemState.setText("IN-STOCK");
//            holder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.main_green_color));
//
//
//        }else {
//            holder.ItemState.setText("OUT OF STOCK");
//            holder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.red));
//            // not active
//        }

//        holder.Delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                updater.delete(itemsItem.getPID());
//            }
//        });
//
//        holder.Edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                updater.edit(itemsItem.getPID());
//
//            }
//        });


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
        return data.size();
    }

    static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView t,tc,tp,em;
        Button user,items,status;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          user=itemView.findViewById(R.id.button4);
          status=itemView.findViewById(R.id.button5);
          items=itemView.findViewById(R.id.button6);
          t=itemView.findViewById(R.id.textView3);
          tc=itemView.findViewById(R.id.textView5);
          tp=itemView.findViewById(R.id.textView4);
          em=itemView.findViewById(R.id.textView6);

        }
    }

    public  interface OrderAcceptance{

        void orderAcceptance(int oid, int tno,int uid);
    }

}
