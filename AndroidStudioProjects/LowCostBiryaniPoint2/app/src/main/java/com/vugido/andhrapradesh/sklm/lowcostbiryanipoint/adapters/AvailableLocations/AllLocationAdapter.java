package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.AvailableLocations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.ProductDetailsActivity;
import com.foodhub.vugido.models.CoinsModel.COINSSPENDINGItem;
import com.foodhub.vugido.models.ServiceAvailability.LOCATIONSItem;
import com.foodhub.vugido.models.ServiceAvailability.LocationSelector;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;

import java.util.List;
import java.util.Locale;

public class AllLocationAdapter extends RecyclerView.Adapter<AllLocationAdapter.MyViewHolder> {

    Context context;
    List<LocationSelector> locationsItemList;
    LocationSetter locationSetter;
    public AllLocationAdapter(Context context, List<LocationSelector> locationsItemList) {
        this.context = context;
        this.locationsItemList = locationsItemList;
        locationSetter= (LocationSetter) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.select_location_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        LocationSelector locationsItem=locationsItemList.get(position);


        if(locationsItem.isChecked()){
            holder.radioButton.setChecked(true);
        }else {
            holder.radioButton.setChecked(false);
        }
        holder.radioButton.setText(locationsItem.getNAME());
        holder.radioButton.setOnClickListener(v -> {

            locationSetter.locationSetter(String.valueOf(locationsItem.getID()),locationsItem.getNAME(), String.valueOf(locationsItem.getZIP()),locationsItem.getID());

        });

    }

    @Override
    public int getItemCount() {
        return locationsItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        RadioButton radioButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.radioButton);
        }
    }

    public interface LocationSetter{

        void locationSetter(String URL, String Location, String Zip, int id);
    }



}
