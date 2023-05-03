package com.vugido.com.vugidoeats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.models.HomePage.CATEGORIESItem;
import com.vugido.com.vugidoeats.models.HomePage.PRODUCTSMENUItem;

import java.util.List;

public class MenuCategoryAdapter extends RecyclerView.Adapter<MenuCategoryAdapter.MyViewHolder> {

    List<CATEGORIESItem> squareMediumModelList;
    Context context;
    MenuCatClicked menuCatClicked;

    public MenuCategoryAdapter(List<CATEGORIESItem> squareMediumModelList, Context context) {
        this.squareMediumModelList = squareMediumModelList;
        this.context = context;
        menuCatClicked= (MenuCatClicked) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_rows_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CATEGORIESItem squareMediumModel=squareMediumModelList.get(position);


        holder.price.setText(squareMediumModel.getCNAME());

        holder.price.setOnClickListener(v -> menuCatClicked.menuClicked(squareMediumModel.getSID()));

    }

    @Override
    public int getItemCount() {
        return squareMediumModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView price;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            price=itemView.findViewById(R.id.price);

        }
    }

    public interface MenuCatClicked{
        void menuClicked(int id);
    }
}
