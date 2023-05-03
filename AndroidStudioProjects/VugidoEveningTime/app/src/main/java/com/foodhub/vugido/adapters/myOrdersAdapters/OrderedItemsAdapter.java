package com.foodhub.vugido.adapters.myOrdersAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.models.orders.PRODUCTSItem;

import java.util.List;


public class OrderedItemsAdapter extends RecyclerView.Adapter <OrderedItemsAdapter.MyViewHolder>{

    List<PRODUCTSItem> integerList;
    Context context;

    public OrderedItemsAdapter(List<PRODUCTSItem> integerList, Context context) {
        this.integerList = integerList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_image,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(integerList.get(position).getIMAGE()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return integerList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView6);
        }
    }
}
