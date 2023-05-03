package com.vugido.vos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.vugido.vos.R;
import com.vugido.vos.models.DashBoardMenuModel.DashBoardMenuModel;

import java.util.List;

public class DashBoardMenuAdapter extends RecyclerView.Adapter<DashBoardMenuAdapter.MyViewHolder>{

    private List<DashBoardMenuModel> dashBoardMenuModelList;
    private Context context;
    private DashBoardItemState state;

    public DashBoardMenuAdapter(List<DashBoardMenuModel> dashBoardMenuModelList, Context context) {
        this.dashBoardMenuModelList = dashBoardMenuModelList;
        this.context = context;
        state= (DashBoardItemState) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dashboard_menu_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final DashBoardMenuModel dashBoardMenuModel=dashBoardMenuModelList.get(position);

        if(dashBoardMenuModel.isState()){
            holder.outerCard.setCardBackgroundColor(Color.WHITE);
            holder.innerCard.setCardBackgroundColor(context.getResources().getColor(R.color.gradient_start_color));
            Glide.with(context).load(dashBoardMenuModel.getActiveImage()).into(holder.imageView);
           // holder.title.setText(dashBoardMenuModel.getTitle());
           // holder.title.setVisibility(View.VISIBLE);
           // holder.title.setTextColor(Color.WHITE);
        }else {
           // holder.title.setVisibility(View.GONE);
            holder.outerCard.setCardBackgroundColor(context.getResources().getColor(R.color.gradient_start_color));
            holder.innerCard.setCardBackgroundColor(Color.WHITE);
            Glide.with(context).load(dashBoardMenuModel.getImage()).into(holder.imageView);
        }
        holder.outerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //disable all
                Log.e("Card","clicked");
                state.dashBoardMenuSate(dashBoardMenuModel.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dashBoardMenuModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        MaterialCardView outerCard,innerCard;
       // TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView2);
            outerCard=itemView.findViewById(R.id.outerCard);
            innerCard=itemView.findViewById(R.id.menu_card);
            //title=itemView.findViewById(R.id.textView3);

        }
    }

    public interface DashBoardItemState{

        void dashBoardMenuSate(int id);
    }
}
