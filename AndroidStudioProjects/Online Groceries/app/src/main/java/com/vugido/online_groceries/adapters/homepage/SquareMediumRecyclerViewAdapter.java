package com.vugido.online_groceries.adapters.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.activities.ClientActivity;
import com.vugido.online_groceries.models.homepage.SECTIONSItem;
import com.vugido.online_groceries.models.homepage.SERVICECLIENTSItem;

import java.util.ArrayList;
import java.util.List;

import static com.vugido.online_groceries.activities.MainActivity.ORDER_PLACED_CODE;

public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder>{

    List<SECTIONSItem> categoriesItemList;
    List<SERVICECLIENTSItem> squareMediumModelList;
    Context context;
     List<SERVICECLIENTSItem> filteredList;
     Activity activity;

    public SquareMediumRecyclerViewAdapter(List<SERVICECLIENTSItem> squareMediumModelList, Context context, List<SECTIONSItem> categoriesItemList) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        this.categoriesItemList=categoriesItemList;
        filteredList=squareMediumModelList;
        activity= (Activity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SERVICECLIENTSItem squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getLOGO()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getBUSINESSNAME());
        holder.description.setText(String.format("%s",squareMediumModel.getLOCATIONNAME()));

        holder.x.setText(String.valueOf("~"+squareMediumModel.getMINPRICE()+"/-"));

        holder.ratingBar.setRating(Float.parseFloat(squareMediumModel.getRATING()));

        holder.ratingBar.setVisibility(View.INVISIBLE);
        holder.constraintLayout.setOnClickListener(v -> {



            Intent intent=new Intent(context,ClientActivity.class);
            intent.putExtra("NAME",squareMediumModel.getBUSINESSNAME());
            intent.putExtra("CID",squareMediumModel.getID());

            activity.startActivityForResult(intent,ORDER_PLACED_CODE);
        });

        if (squareMediumModel.getOFFER()>0)
        {
            holder.o.setVisibility(View.VISIBLE);
            holder.o.setText(String.format("%d%%", squareMediumModel.getOFFER()));
        }else {

            holder.o.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView title,description,x,o;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView6);
            title=itemView.findViewById(R.id.textView13);
            description=itemView.findViewById(R.id.textView14);

           o=itemView.findViewById(R.id.offer_text);
           ratingBar=itemView.findViewById(R.id.ratingBar);
            constraintLayout=itemView.findViewById(R.id.ccl);
           x=itemView.findViewById(R.id.textView15);
        }
    }



}
