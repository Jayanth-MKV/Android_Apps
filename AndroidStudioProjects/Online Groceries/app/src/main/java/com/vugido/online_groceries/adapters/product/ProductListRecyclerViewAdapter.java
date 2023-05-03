package com.vugido.online_groceries.adapters.product;

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
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.models.clients_menu.FOODSItem;

import java.util.ArrayList;
import java.util.List;

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.MyViewHolder>{

    //List<SECTIONSItem> categoriesItemList;
    List<FOODSItem> squareMediumModelList;
    Context context;
    CLICKED_ADD clicked_add;
    int cid;

    public ProductListRecyclerViewAdapter(List<FOODSItem> squareMediumModelList, Context context,int cid) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        clicked_add= (CLICKED_ADD) context;
       // this.categoriesItemList=categoriesItemList;
        //filteredList=squareMediumModelList;
        this.cid=cid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FOODSItem squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getENAME());

        if (squareMediumModel.getINSTOCK()==1){
            holder.add.setEnabled(false);
            holder.ofs.setVisibility(View.VISIBLE);
        }else
        {
            holder.add.setEnabled(true);
            holder.ofs.setVisibility(View.INVISIBLE);
        }

        holder.add.setOnClickListener(v ->

                clicked_add.clicked_add(cid,squareMediumModel.getID(),squareMediumModel.getPRICE())

        );




        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (squareMediumModel.getOFFER()>0)
                    clicked_add.clicked_add(cid,squareMediumModel.getID(),offerPercentagePrice(squareMediumModel.getOFFER(),squareMediumModel.getPRICE()));
                else
                    clicked_add.clicked_add(cid,squareMediumModel.getID(),squareMediumModel.getPRICE());



            }
        });


        if(squareMediumModel.getVEG()==0){
            //veg
            Glide.with(context).load(R.drawable.veg_mark).into(holder.veg);
        }else {
            //non veg
            Glide.with(context).load(R.drawable.non_veg_mark).into(holder.veg);

        }

        if (squareMediumModel.getOFFER()>0)
        {

            holder.price.setText("Rs."+offerPercentagePrice(squareMediumModel.getOFFER(),squareMediumModel.getPRICE())+"/-");
            holder.cp.setText("Rs."+squareMediumModel.getPRICE()+"/-");
            holder.cl.setVisibility(View.VISIBLE);
            holder.cp.setVisibility(View.VISIBLE);
            holder.offer.setVisibility(View.VISIBLE);
            holder.offer.setText(squareMediumModel.getOFFER()+"%");

        }else
        {
            holder.offer.setVisibility(View.GONE);
            holder.cp.setVisibility(View.GONE);
            holder.cl.setVisibility(View.GONE);
            holder.price.setText("Rs."+squareMediumModel.getPRICE()+"/-");
        }

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
        Button add;

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
           // add=itemView.findViewById(R.id.button2);

        }
    }


    public interface  CLICKED_ADD{

        void clicked_add(int cid,int pid,int price
        );
    }

}
