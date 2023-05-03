package com.vugido.ap.sklm.hungrybirdsadmin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.ap.sklm.hungrybirdsadmin.R;
import com.vugido.ap.sklm.hungrybirdsadmin.models.DashBoardModel;

import java.util.List;

public class DashboardREcyclerAdapter extends RecyclerView.Adapter<DashboardREcyclerAdapter.MyViewHolder> {

    private Context context;
    private List<DashBoardModel> dashBoardModelList;

    public DashboardREcyclerAdapter(Context context, List<DashBoardModel> dashBoardModelList) {
        this.context = context;
        this.dashBoardModelList = dashBoardModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dashboard_recycler_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DashBoardModel dashBoardModel=dashBoardModelList.get(position);

        Glide.with(context).load(dashBoardModel.getImage()).into(holder.imageView);

        holder.value.setText(dashBoardModel.getValue());
        holder.title.setText(dashBoardModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return dashBoardModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,value;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            title=itemView.findViewById(R.id.textView);
            value=itemView.findViewById(R.id.textView2);
        }
    }
}
