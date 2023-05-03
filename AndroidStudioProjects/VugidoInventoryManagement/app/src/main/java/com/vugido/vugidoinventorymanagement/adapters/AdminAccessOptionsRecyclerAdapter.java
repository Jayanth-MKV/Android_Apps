package com.vugido.vugidoinventorymanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.activities.AdminActionActivity;
import com.vugido.vugidoinventorymanagement.models.AdminAccessOptionsModel;
import java.util.List;


public class AdminAccessOptionsRecyclerAdapter extends RecyclerView.Adapter<AdminAccessOptionsRecyclerAdapter.MyViewHolder> {

   private Context context;
   private List<AdminAccessOptionsModel> adminAccessOptionsModelList;
    public AdminAccessOptionsRecyclerAdapter(Context context, List<AdminAccessOptionsModel> adminAccessOptionsModelList){
        this.context=context;
        this.adminAccessOptionsModelList=adminAccessOptionsModelList;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.admin_access_options_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final AdminAccessOptionsModel adminAccess=adminAccessOptionsModelList.get(position);

        holder.Title.setText(adminAccess.getAccessName());
        holder.Title.setBackgroundColor(Color.parseColor(adminAccess.getBackgroundColor()));
        holder.Title.setTextColor(Color.parseColor(adminAccess.getTextColor()));

        holder.Option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, AdminActionActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("AID",adminAccess.getAID());
                bundle.putString("ACTION",adminAccess.getAccessName());
                intent.putExtra("BUNDLE",bundle);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return adminAccessOptionsModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Title;
        CardView Option;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.admin_access_option_title);
            Option=itemView.findViewById(R.id.actionOptionCardView);
        }
    }
}
