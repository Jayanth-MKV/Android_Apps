package com.vugido.homeservices.adapters.service_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.homeservices.R;
import com.vugido.homeservices.models.homepage.SERVICECATEGORIESItem;
import com.vugido.homeservices.models.homepage.SERVICEPRODUCTSItem;
import com.vugido.homeservices.models.homepage.ServiceDetailsModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceDetailsAdapter extends RecyclerView.Adapter<ServiceDetailsAdapter.MyViewHolder> {

    Context context;

    List<ServiceDetailsModel> serviceDetailsModelList;
    CHECKBOX checkbox;
    List<String> images;

    public ServiceDetailsAdapter(List<ServiceDetailsModel> serviceDetailsModelList, Context context,List<String> images) {
        this.context = context;
        this.serviceDetailsModelList=serviceDetailsModelList;
        checkbox= (CHECKBOX) context;
        this.images=images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.service_details_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ServiceDetailsModel serviceDetailsModel=serviceDetailsModelList.get(position);
        holder.x.setText(serviceDetailsModel.getService());

        holder.checkBox.setOnClickListener(v -> {

            checkbox.checkService(serviceDetailsModel.getService_id());
        });
        Glide.with(context).load("http://homeservices.vugido.com/IMAGES/"+images.get(position)).into(holder.imageView);

    }


    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public int getItemCount() {
        return serviceDetailsModelList.size();
    }



    static class MyViewHolder extends RecyclerView.ViewHolder{


        CheckBox checkBox;
        TextView x;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkBox);
            x=itemView.findViewById(R.id.textView18);
            imageView=itemView.findViewById(R.id.imageView9);

        }
    }


    public interface  CHECKBOX {
        void checkService(int id);
    }



}
