package com.foodhub.vugido.adapters.myOrdersAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
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
        View view= LayoutInflater.from(context).inflate(R.layout.ordered_item_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ORDERSItem dataItem=dataItemList.get(position);
        holder.oid.setText(String.format(Locale.getDefault(),"OID : %d", dataItem.getOID()));
        holder.TotalPrice.setText("Rs."+String.valueOf(dataItem.getITEMSPRICE()+dataItem.getDC())+"/-");
        holder.client_name.setText(dataItem.getBUSINESSNAME());
        holder.item_count.setText(dataItem.getITEMCOUNT()+" items");
   //     Glide.with(context).load(dataItem.getLOGO()).into(holder.clientImage);
        holder.ratingBar.setRating(Float.parseFloat(dataItem.getRATING()));

        Intent intent=new Intent(context, TrackingActivity.class);
        intent.putExtra("STATUS",dataItem.getSTATUS());
       intent.putExtra("OID",dataItem.getOID());
//        intent.putExtra("LA",dataItem.getLATITUDE());
//        intent.putExtra("LO",dataItem.getLONGITUDE());

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

        ImageView clientImage;
        TextView TotalPrice,oid,client_name,item_count;
        Button track;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            oid=itemView.findViewById(R.id.textView13);
            client_name=itemView.findViewById(R.id.textView47);
            item_count=itemView.findViewById(R.id.textView48);
            clientImage=itemView.findViewById(R.id.imageView2);
            track=itemView.findViewById(R.id.button3);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            TotalPrice=itemView.findViewById(R.id.textView10);
        }



    }
}
