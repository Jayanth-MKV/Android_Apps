package com.vugido.vos.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.vugido.vos.R;
import com.vugido.vos.activites.AllProducts;
import com.vugido.vos.models.Categories.DATAItem;

import java.util.List;

public class InventoryMainAdapter extends RecyclerView.Adapter<InventoryMainAdapter.MyViewHolder> {
    private List<DATAItem> dataItemList;
    private Context context;

    public InventoryMainAdapter(List<DATAItem> dataItemList, Context context) {
        this.dataItemList = dataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.inventory_cat_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DATAItem dataItem=dataItemList.get(position);

        Glide.with(context).load(dataItem.getICONS()).into(holder.imageView);
        holder.Title.setText(dataItem.getCATEGORY());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start new activity..
                Intent intent=new Intent(context, AllProducts.class);
                intent.putExtra("CID",dataItem.getID());
                intent.putExtra("NAME",dataItem.getCATEGORY());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }


    static  class MyViewHolder extends RecyclerView.ViewHolder{
        MaterialCardView cardView;
        ImageView imageView;
        TextView Title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            Title=itemView.findViewById(R.id.textView);
            cardView=itemView.findViewById(R.id.cat_card);

        }
    }
}
