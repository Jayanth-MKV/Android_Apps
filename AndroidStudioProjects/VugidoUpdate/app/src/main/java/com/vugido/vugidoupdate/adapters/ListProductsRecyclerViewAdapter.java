package com.vugido.vugidoupdate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.models.home_page.ListProductsViewModel;

import java.util.List;

public class ListProductsRecyclerViewAdapter extends RecyclerView.Adapter<ListProductsRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<ListProductsViewModel> listProductsViewModelList;

    public ListProductsRecyclerViewAdapter(Context context, List<ListProductsViewModel> listProductsViewModelList) {
        this.context = context;
        this.listProductsViewModelList = listProductsViewModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_product_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ListProductsViewModel listProductsViewModel=listProductsViewModelList.get(position);

        Glide.with(context).load(listProductsViewModel.getImage()).into(holder.imageView);
        holder.type.setText(listProductsViewModel.getType());
        holder.offer.setText(listProductsViewModel.getOffer());
        holder.description.setText(listProductsViewModel.getDescription());
        holder.title.setText(listProductsViewModel.getTitle());
        holder.price.setText(listProductsViewModel.getPrice());

    }

    @Override
    public int getItemCount() {
        return listProductsViewModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,price,description,offer,type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
            title=itemView.findViewById(R.id.textView3);
            price=itemView.findViewById(R.id.textView6);
            description=itemView.findViewById(R.id.textView5);
            offer=itemView.findViewById(R.id.textView7);
            type=itemView.findViewById(R.id.textView4);
        }
    }
}
