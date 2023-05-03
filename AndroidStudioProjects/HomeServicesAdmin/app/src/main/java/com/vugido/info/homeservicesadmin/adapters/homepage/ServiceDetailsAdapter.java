package com.vugido.info.homeservicesadmin.adapters.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.models.homepage.ServiceDetailsModel;

import java.util.List;

public class ServiceDetailsAdapter extends RecyclerView.Adapter<ServiceDetailsAdapter.MyViewHolder> {

    Context context;

    List<ServiceDetailsModel> serviceDetailsModelList;
    CHECKBOX checkbox;

    public ServiceDetailsAdapter(List<ServiceDetailsModel> serviceDetailsModelList, Context context) {
        this.context = context;
        this.serviceDetailsModelList=serviceDetailsModelList;
        checkbox= (CHECKBOX) context;

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


        Button checkBox;
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
