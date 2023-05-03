package com.foodhub.vugido.adapters.coins;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.models.coins.DataItem;

import java.util.List;

public class CoinsTransactionAdapter extends RecyclerView.Adapter<CoinsTransactionAdapter.MyViewholder> {
    Context context;
    List<DataItem> dataItems;
    public CoinsTransactionAdapter(Context context, List<DataItem> data) {
        this.context = context;
        dataItems=data;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.coin_transaction_item_design,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        DataItem dataItem=dataItems.get(position);
        holder.Reason.setText(dataItem.getREASON());
        holder.Datetime.setText(dataItem.getDATED());

        if(dataItem.getISCREDIT()==1){
            //credited
                // successfully credited...
                holder.view.setBackgroundColor(Color.GREEN);
                holder.amount.setText("+"+dataItem.getCOINS());
                holder.amount.setTextColor(Color.GREEN);

        }else {
            //debited
            holder.view.setBackgroundColor(Color.RED);
            holder.amount.setText("-"+dataItem.getCOINS());
            holder.amount.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    static class MyViewholder extends RecyclerView.ViewHolder{
        View view;
        TextView Reason,Datetime,amount;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.divider15);
            Reason=itemView.findViewById(R.id.textView29);
            Datetime=itemView.findViewById(R.id.textView30);
            amount=itemView.findViewById(R.id.textView31);
        }
    }
}
