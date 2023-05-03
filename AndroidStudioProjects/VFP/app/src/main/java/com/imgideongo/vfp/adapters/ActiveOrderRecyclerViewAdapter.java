package com.imgideongo.vfp.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imgideongo.vfp.R;
import com.imgideongo.vfp.models.ActiveOrder;

import java.util.List;

public class ActiveOrderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ActiveOrder> activeOrderList;
    private LayoutInflater layoutInflater;

    ActiveOrderRecyclerViewAdapter(Context context,List<ActiveOrder> activeOrdersList){
        this.activeOrderList=activeOrdersList;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.order_design,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


    }

    @Override
    public int getItemCount() {
        return activeOrderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView OID,Q;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }
}
