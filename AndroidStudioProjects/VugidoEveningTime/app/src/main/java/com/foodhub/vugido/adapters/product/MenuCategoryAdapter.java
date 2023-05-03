package com.foodhub.vugido.adapters.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.models.product.MenuModel;

import java.util.List;


public class MenuCategoryAdapter extends RecyclerView.Adapter<MenuCategoryAdapter.MyViewHolder> {

    List<MenuModel> squareMediumModelList;
    Context context;
    MenuCatClicked menuCatClicked;

    public MenuCategoryAdapter(List<MenuModel> squareMediumModelList, Context context) {
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

        MenuModel squareMediumModel=squareMediumModelList.get(position);


        holder.price.setText(squareMediumModel.getTitle());

        holder.price.setOnClickListener(v -> menuCatClicked.menuClicked(squareMediumModel.getId()));

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

    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    public interface MenuCatClicked{
        void menuClicked(int id);
    }
}
