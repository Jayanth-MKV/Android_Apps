package com.vugido.foodhub.v_dm.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.foodhub.v_dm.R;
import com.vugido.foodhub.v_dm.activities.OrderedItemsActivity;
import com.vugido.foodhub.v_dm.activities.UserInfoActivity;
import com.vugido.foodhub.v_dm.models.active_orders.DATAItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

public class MyPendingOrdersAdapter extends RecyclerView.Adapter<MyPendingOrdersAdapter.MyViewHolder> {

    private List<DATAItem> dataItemList;
    private UpdateStatus updateStatus;

    public MyPendingOrdersAdapter(Context context, List<DATAItem> dataItemList, UpdateStatus updateStatus) {
        this.context = context;
        this.dataItemList=dataItemList;
        this.updateStatus= updateStatus;
    }

    private Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pending_ordered_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DATAItem dataItem=dataItemList.get(position);
        holder.oid.setText(String.format(Locale.getDefault(),"OID : %d", dataItem.getOID()));
        holder.ordered_on.setText(dataItem.getDATED());
        holder.delivery_charge.setText(String.format(Locale.getDefault(),"Delivery Charge : Rs.%d/-", dataItem.getDC()));
        holder.item_price.setText(String.format(Locale.getDefault(),"Items Price : Rs.%d/-", dataItem.getITEMSPRICE()));
        //holder.deliver_date.setText("Deliver Date : "+dataItem.getDELIVERDATE());
        holder.item_count.setText(String.format(Locale.getDefault(),"Item Count : %d", dataItem.getITEMCOUNT()));
        //holder.time.setText(String.format("Deliver Time : %s", dataItem.getTIME()));
        holder.address_info.setText(String.format("Address : %s %s  ", dataItem.getADDRESS(), dataItem.getLM()));

        holder.address_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show detailed address..


                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+dataItem.getLATITUDE()+","+dataItem.getLONGITUDE()));
                context.startActivity(intent);
//                String uri=String.format(Locale.ENGLISH,"geo:%f,%f",Double.parseDouble(dataItem.getLATITUDE()),Double.parseDouble(dataItem.getLONGITUDE()));
//                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
//                context.startActivity(intent);
            }
        });

        if(dataItem.getSTATUS()==1){
            holder.OrderStatus.setText("Packing");
        }else if(dataItem.getSTATUS()==2){
            holder.OrderStatus.setText("Packed");
        }else if(dataItem.getSTATUS()==3){
            holder.OrderStatus.setText("Dispatched");
        }else if(dataItem.getSTATUS()==4){
            holder.OrderStatus.setText("Processing");
        }else if(dataItem.getSTATUS()==5){
            holder.OrderStatus.setText("Completed");

        }

        holder.OrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus.updateStatus(dataItem.getOID(),dataItem.getUID().getUID());
            }
        });
        holder.ButtonUserInfo.setOnClickListener(v -> {


            Intent intent=new Intent(context, UserInfoActivity.class);
            intent.putExtra("UID",dataItem.getUID().getUID());
            intent.putExtra("EMAIL",dataItem.getUID().getGMAIL());
            intent.putExtra("CALL",dataItem.getUID().getPHONE());
            intent.putExtra("NAME",dataItem.getUID().getUSERNAME());
            context.startActivity(intent);
        });
        holder.ButtonSeeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderedItemsActivity.class);
                intent.putExtra("OID",dataItem.getOID());
                context.startActivity(intent);
            }
        });


    }
    private String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return String.valueOf(bd.doubleValue());
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView oid,ordered_on,time,item_count,deliver_date,item_price,delivery_charge,address_info;
        Button OrderStatus,ButtonSeeItems,ButtonUserInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            oid=itemView.findViewById(R.id.OID);
            ordered_on=itemView.findViewById(R.id.OrderedOn);
            time=itemView.findViewById(R.id.Time);
            item_count=itemView.findViewById(R.id.ItemCount);
            deliver_date=itemView.findViewById(R.id.DeliverDate);
            item_price=itemView.findViewById(R.id.ItemPrice);
            delivery_charge=itemView.findViewById(R.id.DeliveryCharge);
            address_info=itemView.findViewById(R.id.AddressInfo);
            OrderStatus=itemView.findViewById(R.id.OrderStatus);
            ButtonSeeItems=itemView.findViewById(R.id.ButtonSeeItems);
            ButtonUserInfo=itemView.findViewById(R.id.ButtonUserInfo);

        }



    }
    public interface UpdateStatus{

        void updateStatus(int oid,int uid);

    }
}
