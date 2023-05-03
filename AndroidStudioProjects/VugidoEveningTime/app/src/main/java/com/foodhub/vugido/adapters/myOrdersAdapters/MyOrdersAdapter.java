package com.foodhub.vugido.adapters.myOrdersAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.DMaps;
import com.foodhub.vugido.activities.TrackingActivity;
import com.foodhub.vugido.models.orders.ORDERSItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;


public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder> {


    public MyOrdersAdapter(Context context, List<ORDERSItem> dataItemList) {
        this.context = context;
        this.dataItemList=dataItemList;
    }

    private Context context;
    private List<ORDERSItem> dataItemList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ordered,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ORDERSItem dataItem=dataItemList.get(position);

        holder.totalItemsText.setText(String.format(Locale.getDefault(),"%d Items", dataItem.getITEMCOUNT()));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(new OrderedItemsAdapter(dataItem.getPRODUCTS(),context));
        holder.oid.setText(String.format(Locale.getDefault(),"OID : %d", dataItem.getOID()));

        holder.TotalPrice.setText(String.format(Locale.getDefault(),"Rs.%s/-", round(dataItem.getITEMSPRICE()+dataItem.getDC(),2)));

        if(dataItem.getSTATUS()==0){
            holder.orderStatusText.setText("Packing");
        }else if(dataItem.getSTATUS()==1){
            holder.orderStatusText.setText("On Way");
        }else if(dataItem.getSTATUS()==2){
            holder.orderStatusText.setText("Delivered");
        }else
            holder.orderStatusText.setText("Failed/Cancelled");


        Intent intent=new Intent(context, TrackingActivity.class);
        intent.putExtra("STATUS",dataItem.getSTATUS());
        intent.putExtra("OID",dataItem.getOID());
        intent.putExtra("LA",dataItem.getLATITUDE());
        intent.putExtra("LO",dataItem.getLONGITUDE());

        holder.track.setOnClickListener(v -> context.startActivity(intent));
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

        RecyclerView recyclerView;
        ImageView statusImage;
        TextView orderStatusText,totalItemsText,DeliveredAT,TotalPrice,oid;
        Button track;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.recyclerView);
            statusImage=itemView.findViewById(R.id.imageView2);
            orderStatusText=itemView.findViewById(R.id.textView3);
            totalItemsText=itemView.findViewById(R.id.textView8);
            DeliveredAT=itemView.findViewById(R.id.textView11);
            track=itemView.findViewById(R.id.button3);
            oid=itemView.findViewById(R.id.textView13);
            TotalPrice=itemView.findViewById(R.id.textView10);
        }



    }
}
