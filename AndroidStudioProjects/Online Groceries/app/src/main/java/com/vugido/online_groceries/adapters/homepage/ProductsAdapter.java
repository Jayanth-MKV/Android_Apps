package com.vugido.online_groceries.adapters.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.models.homepage.updated.HOMEPRODUCTSItem;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>{

    //List<SECTIONSItem> categoriesItemList;
    List<HOMEPRODUCTSItem> squareMediumModelList;
    INC_DEC inc_dec;
    Context context;

    public ProductsAdapter(List<HOMEPRODUCTSItem> squareMediumModelList, Context context) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        inc_dec= (INC_DEC) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HOMEPRODUCTSItem squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getTITLE());
        holder.des.setText(squareMediumModel.getQUANTITY());

        if(squareMediumModel.getINSTOCK()==1){
            //ok
            holder.ofs.setVisibility(View.INVISIBLE);
        }else {
            holder.ofs.setVisibility(View.VISIBLE);
        }
        holder.cc.setText(String.valueOf(squareMediumModel.getCARTCOUNT()));

        if(squareMediumModel.getOFFERENABLED()==1){
            //offer enabled..
            holder.offer.setVisibility(View.VISIBLE);
            holder.cl.setVisibility(View.VISIBLE);
            holder.cp.setVisibility(View.VISIBLE);
            holder.offer.setText(String.valueOf(Float.valueOf(squareMediumModel.getOFFER()))+"%");
            holder.cp.setText(String.valueOf(Float.parseFloat(squareMediumModel.getPRICE())));
            holder.price.setText("Rs."+offerPercentagePrice(Float.parseFloat(squareMediumModel.getOFFER()),Float.parseFloat(squareMediumModel.getPRICE()))+"/-");


        }else {
            holder.offer.setVisibility(View.INVISIBLE);
            holder.cl.setVisibility(View.INVISIBLE);
            holder.cp.setVisibility(View.INVISIBLE);
            holder.price.setText("Rs."+Float.parseFloat(squareMediumModel.getPRICE())+"/-");
        }

        holder.inc.setOnClickListener(v -> {
            inc_dec.inc_dec(squareMediumModel.getID(),true,position);
        });

        holder.dec.setOnClickListener(v -> {

            if(squareMediumModel.getCARTCOUNT()!=0){
                inc_dec.inc_dec(squareMediumModel.getID(),false,position);
            }
        });



    }

    private float offerPercentagePrice(float offer, float price) {

        float x=((price*offer)/100f);
        return price-x;

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
        TextView title,price,cp,offer,des,cc;
        View cl;
        ImageButton inc,dec;
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
            des=itemView.findViewById(R.id.textView45);
            inc=itemView.findViewById(R.id.inc);
            dec=itemView.findViewById(R.id.dec);
            cc=itemView.findViewById(R.id.textView2);

        }
    }

    public interface INC_DEC{

        void inc_dec(int pid,boolean is_inc,int position);
    }

}
