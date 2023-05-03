package com.vugido_business_panel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido_business_panel.R;
import com.vugido_business_panel.activities.AllProducts;
import com.vugido_business_panel.models.Categories.mainCategories.DATAItem;

import java.util.List;

public class MainCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<MainCategoriesRecyclerViewAdapter.MyViewHolder> {

    List<DATAItem> dataItemList;
    Context context;

    public MainCategoriesRecyclerViewAdapter(List<DATAItem> dataItemList, Context context) {
        this.dataItemList = dataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.admin_access_options_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DATAItem dataItem=dataItemList.get(position);

        holder.title.setText(dataItem.getCATEGORY());
        Glide.with(context).load(dataItem.getICONS()).into(holder.image);
        if(dataItem.getACTIVESTATE()==1){
            // active..
            holder.button.setText("Active");
            holder.button.setBackgroundColor(context.getResources().getColor(R.color.gradient_start_color));


        }else {
            holder.button.setText("In-Active");
            holder.button.setBackgroundColor(context.getResources().getColor(R.color.red));
            // not active
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        Button button;
        CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.cat_image);
        title=itemView.findViewById(R.id.admin_access_option_title);
        button=itemView.findViewById(R.id.active_state);
        cardView=itemView.findViewById(R.id.actionOptionCardView);
    }
}

}
