package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.OfferAdapters.EarnCoins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.ProductsShowForPocket;
import com.foodhub.vugido.models.AffiliatedProductModel.Entire.DATAItem;
import com.foodhub.vugido.models.CoinsModel.COINSEARNABLEPRODUCTSItem;

import java.util.List;
import java.util.Locale;

public class SquareMiniRecyclerViewAdapterEntire extends RecyclerView.Adapter<SquareMiniRecyclerViewAdapterEntire.MyViewHolder> {

    Context context;
    List<DATAItem> coinsearnableproductsItemList;
    DynaMIcLink dynaMIcLink;

    public SquareMiniRecyclerViewAdapterEntire(Context context, List<DATAItem> coinsearnableproductsItemList, DynaMIcLink dynaMIcLink) {
        this.context = context;
        this.coinsearnableproductsItemList=coinsearnableproductsItemList;
        this.dynaMIcLink=dynaMIcLink;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mini_square_grid_item_design_grid,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DATAItem coinsearnableproductsItem=coinsearnableproductsItemList.get(position);
        Glide.with(context).load(coinsearnableproductsItem.getIMAGE()).into(holder.imageView);
        holder.title.setText(String.format(Locale.getDefault(),"Earn %d", coinsearnableproductsItem.getCOINS()));

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynaMIcLink.affiliateProduct(coinsearnableproductsItem.getPID(),coinsearnableproductsItem.getCOINS(),coinsearnableproductsItem.getTNAME(),coinsearnableproductsItem.getDESCRIPTION(),coinsearnableproductsItem.getPRICE(),coinsearnableproductsItem.getQUANTITY(),coinsearnableproductsItem.getIMAGE());
            }
        });
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynaMIcLink.affiliateProduct(coinsearnableproductsItem.getPID(),coinsearnableproductsItem.getCOINS(),coinsearnableproductsItem.getTNAME(),coinsearnableproductsItem.getDESCRIPTION(),coinsearnableproductsItem.getPRICE(),coinsearnableproductsItem.getQUANTITY(),coinsearnableproductsItem.getIMAGE());

            }
        });
    }

    @Override
    public int getItemCount() {
        return coinsearnableproductsItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        ConstraintLayout item;
        ImageButton imageButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            item=itemView.findViewById(R.id.category_item);
            imageButton=itemView.findViewById(R.id.imageButton2);
        }
    }

    public interface DynaMIcLink{
        void affiliateProduct(int pid, int coins, String tname, String description, int price, int quantity, String image);
    }
}
