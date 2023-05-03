package com.vugido.vugidoccs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.vugidoccs.R;
import com.vugido.vugidoccs.models.MainCCSOptionsModel;

import java.util.List;

public class MainRecyclerViewAdpater extends RecyclerView.Adapter<MainRecyclerViewAdpater.MyViewHolder> {

    List<MainCCSOptionsModel> mainCCSOptionsModelList;
    Context context;

    public MainRecyclerViewAdpater(List<MainCCSOptionsModel> mainCCSOptionsModelList, Context context) {
        this.mainCCSOptionsModelList = mainCCSOptionsModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_item_ccs_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainCCSOptionsModel mainCCSOptionsModel=mainCCSOptionsModelList.get(position);

        holder.title.setText(mainCCSOptionsModel.getTitle());

    }

    @Override
    public int getItemCount() {
        return mainCCSOptionsModelList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
        }
    }

}
