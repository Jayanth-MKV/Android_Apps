package com.vugido.vpm.adapters.pending;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.vugido.vpm.R;
import com.vugido.vpm.model.active_orders.DataItem;

import java.util.List;

public class ActiveOrdersRecyclerViewAdapter extends RecyclerView.Adapter <ActiveOrdersRecyclerViewAdapter.MyViewHolder>{

    private List<DataItem> activeOrderItemList;
    private Context context;
    private UpdateStatus updateStatus;

    public ActiveOrdersRecyclerViewAdapter(Context context, List<DataItem> activeOrderItemList, UpdateStatus updateStatus){
        this.context=context;
        this.activeOrderItemList=activeOrderItemList;
        this.updateStatus= updateStatus;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pending_order_item_design, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DataItem activeItem=activeOrderItemList.get(position);


        holder.OID.setText(String.format("OID :%s", String.valueOf(activeItem.getOID())));
        holder.TIME.setText(String.format("%s %s", activeItem.getTIME(), activeItem.getDATE()));
        holder.MODE.setText(String.format("Delivery Mode : %s",activeItem.getSLOT()));
        holder.ADDRESS.setText(activeItem.getADDRESS());
        holder.ITEM_COUNT.setText(String.format("Item Count : %s",activeItem.getCOUNT()));
        holder.TOTAL_PRICE.setText(String.format("Total Price : Rs.%s/-",activeItem.getTP()));

        switch (activeItem.getSTATUS()){
            case 0:
            case 1:
                holder.OrderStatus.setText("Packing");
                break;
            case 2:
                holder.OrderStatus.setText("Packed");
                break;

        }


        // optional
        holder.ADDRESS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // show in map

            }
        });

        holder.TOTAL_PRICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



        holder.SeeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(context, OrderedItemsActivity.class);
                intent.putExtra("OID",activeItem.getOID());
                context.startActivity(intent);


            }
        });

        holder.OrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update Order Status..
                updateStatus.updateStatus(activeItem.getOID());

            }
        });


    }

    @Override
    public int getItemCount() {
        return activeOrderItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        MaterialButton UserInfo,SeeItems,OrderStatus;
        TextView OID, TIME,ITEM_COUNT,MODE,TOTAL_PRICE,ADDRESS;



        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            OID=itemView.findViewById(R.id.OID);
            TIME=itemView.findViewById(R.id.TIME);
            ITEM_COUNT=itemView.findViewById(R.id.Item_Count);
            MODE=itemView.findViewById(R.id.Delivery_Mode);
            TOTAL_PRICE=itemView.findViewById(R.id.Total_Price);
            ADDRESS=itemView.findViewById(R.id.Address);
            SeeItems=itemView.findViewById(R.id.ButtonSeeItems);
            OrderStatus=itemView.findViewById(R.id.OrderStatus);



        }
    }

    public interface UpdateStatus{

        void updateStatus(int oid);

    }

}
