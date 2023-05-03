package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.OfferAdapters.UseCoins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.ProductDetailsActivity;
import com.foodhub.vugido.models.CoinsModel.COINSSPENDINGItem;
import com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.DATAItem;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;

import java.util.List;
import java.util.Locale;

public class SquareMediumRecyclerViewAdapterEntire extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapterEntire.MyViewHolder> {

    Context context;
    List<DATAItem> coinsspendingItemList;



    public SquareMediumRecyclerViewAdapterEntire(Context context, List<DATAItem> coinsspendingItemList) {
        this.context = context;
        this.coinsspendingItemList=coinsspendingItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.square_horizontal_medium_view_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        DATAItem coinsspendingItem=coinsspendingItemList.get(position);
        Glide.with(context).load(coinsspendingItem.getIMAGE()).into(holder.imageView);
        holder.title.setText(coinsspendingItem.getTNAME());
        holder.price.setText("Rs."+coinsspendingItem.getPRICE()+"/-");
        holder.description.setText(String.format(Locale.getDefault(),"%d %s", coinsspendingItem.getQUANTITY(), coinsspendingItem.getDESCRIPTION()));
        holder.add.setOnClickListener(v -> {

            //ad to cart check  coins
           // coinProductsToCart.addCoinProductsCart(43,coinsspendingItem.getPID(),coinsspendingItem.getQUANTITY());

            callProductDetailsActivity(coinsspendingItem);
        });


    }

    @Override
    public int getItemCount() {
        return coinsspendingItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,description,price;
        Button add;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.price);
            add=itemView.findViewById(R.id.button9);
        }
    }

    private void callProductDetailsActivity(DATAItem ProductItem) {
        Intent intent=new Intent(context, ProductDetailsActivity.class);
        Bundle bundle=new Bundle();
        BaseProduct baseProduct =new BaseProduct();
        baseProduct.setOFFERSTATUS(ProductItem.getOFFERSTATUS());
        baseProduct.setENAME(ProductItem.getENAME());
        baseProduct.setTNAME(ProductItem.getTNAME());
        baseProduct.setCARTLIMIT(ProductItem.getCARTLIMIT());
        baseProduct.setDESCRIPTION(ProductItem.getDESCRIPTION());
        baseProduct.setIMAGE(ProductItem.getIMAGE());
        baseProduct.setINSTOCK(ProductItem.getINSTOCK());
        baseProduct.setISGRAMSET(ProductItem.getISGRAMSET());
        baseProduct.setOFFER(ProductItem.getOFFER());
        baseProduct.setOFFERLIMIT(ProductItem.getOFFERLIMIT());
        baseProduct.setPID(ProductItem.getPID());
        baseProduct.setPRICE(ProductItem.getPRICE());
        baseProduct.setQUANTITY(ProductItem.getQUANTITY());
        baseProduct.setSID(ProductItem.getSID());
        bundle.putParcelable("BASE_OBJECT",baseProduct);
        bundle.putInt("CID",44);
        intent.putExtra("BUNDLE",bundle);
        context.startActivity(intent);
    }


}
