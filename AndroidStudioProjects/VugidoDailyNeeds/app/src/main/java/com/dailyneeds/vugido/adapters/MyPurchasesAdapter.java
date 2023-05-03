package com.dailyneeds.vugido.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.activities.MyPurchasesDetailedActivity;
import com.dailyneeds.vugido.models.OnTheWay;
import java.util.List;



public class MyPurchasesAdapter extends RecyclerView.Adapter<MyPurchasesAdapter.MyViewHolder> {

    private Context context;
    private List<OnTheWay> onTheWayList;
    public MyPurchasesAdapter(Context context, List<OnTheWay> onTheWayList){
        this.context=context;
        this.onTheWayList=onTheWayList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.my_purchases_row_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final OnTheWay onTheWay=onTheWayList.get(position);

        holder.Date.setText(onTheWay.getDATE());
        holder.Price.setText(String.format("Rs.%s/-", onTheWay.getFinalPay()));
       /* holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // initiate the deletion process over



            }
        });*/


        holder.Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // show the details of the order.. here.
                Intent intent=new Intent(context,MyPurchasesDetailedActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("PRODUCT",onTheWay);
                intent.putExtra("BUNDLE",bundle);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return onTheWayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Date,Price,Details;
        ImageButton Delete;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Date=itemView.findViewById(R.id.date_purchased);
            Price=itemView.findViewById(R.id.price_purchased);
            Details=itemView.findViewById(R.id.details);
           // Delete=itemView.findViewById(R.id.delete_purchased);


        }
    }

}
