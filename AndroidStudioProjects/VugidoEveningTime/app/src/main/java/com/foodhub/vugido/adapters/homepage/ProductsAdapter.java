package com.foodhub.vugido.adapters.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.models.clients_menu.FOODSItem;
import com.foodhub.vugido.models.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>{

    //List<SECTIONSItem> categoriesItemList;
    List<ProductModel> squareMediumModelList;
    Context context;

    public ProductsAdapter(List<ProductModel> squareMediumModelList, Context context) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProductModel squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getImage()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getTitle());



    }

    private int offerPercentagePrice(int offer, int price) {

        return  ((price*offer)/100);

    }
    @Override
    public int getItemCount() {
        return squareMediumModelList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,veg;
        TextView title,price,cp,offer;
        View cl;

        RelativeLayout ofs;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.product_image);
            title=itemView.findViewById(R.id.product_title);

            ofs=itemView.findViewById(R.id.out_of_stock_layout);
            veg=itemView.findViewById(R.id.imageView3);
            price=itemView.findViewById(R.id.product_price);
            cp=itemView.findViewById(R.id.cutted_price);
            cl=itemView.findViewById(R.id.cut_price_line);
            offer=itemView.findViewById(R.id.offer_text);

        }
    }


}
