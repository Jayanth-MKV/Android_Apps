package com.vugido.com.vugidoeats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.app_config.MyPreferences;
import com.vugido.com.vugidoeats.models.HomePage.PRODUCTSMENUItem;

import java.util.List;

public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder> {

    List<PRODUCTSMENUItem> squareMediumModelList;
    Context context;
    AddToCart addToCart;

    public SquareMediumRecyclerViewAdapter(List<PRODUCTSMENUItem> squareMediumModelList, Context context) {
        this.squareMediumModelList = squareMediumModelList;
        this.context = context;
        addToCart= (AddToCart) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.square_horizontal_medium_view_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PRODUCTSMENUItem squareMediumModel=squareMediumModelList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getTNAME());
        holder.price.setText("Rs."+squareMediumModel.getPRICE()+"/-");
        holder.description.setText(String.format("%s %s", squareMediumModel.getQUANTITY(), squareMediumModel.getDESCRIPTION()));


        if(new MyPreferences(context).getTableNumber()==0){
            holder.eat.setVisibility(View.INVISIBLE);
        }
        holder.eat.setOnClickListener(v -> {

            addToCart.eat(squareMediumModel.getPID(),holder.imageView,squareMediumModel.getIMAGE());
        });

    }

    @Override
    public int getItemCount() {
        return squareMediumModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,description,price;
        Button eat;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.price);
            eat=itemView.findViewById(R.id.button2);
        }
    }


    public interface AddToCart{

        void eat(int pid, ImageView l,String url);
    }
}
