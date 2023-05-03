package com.vugido.info.homeservicesadmin.adapters.near;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.activities.AddNewNearInfoActivity;
import com.vugido.info.homeservicesadmin.activities.UploadImageNear;
import com.vugido.info.homeservicesadmin.models.near_page.NEARSERVICECATEGORIESItem;
import com.vugido.info.homeservicesadmin.models.near_page.NEARSERVICESItem;

import java.util.ArrayList;
import java.util.List;

public class NearMediumRecyclerViewAdapter extends RecyclerView.Adapter<NearMediumRecyclerViewAdapter.MyViewHolder>{

    public static final int SERVICE_CODE = 10;
    List<NEARSERVICECATEGORIESItem> categoriesItemList;
    List<NEARSERVICESItem> squareMediumModelList;
    Context context;
     List<NEARSERVICESItem> filteredList;
     Activity activity;
     int v;

    public NearMediumRecyclerViewAdapter(List<NEARSERVICESItem> squareMediumModelList, Context context, List<NEARSERVICECATEGORIESItem> categoriesItemList,int v) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        activity= (Activity) context;
        this.categoriesItemList=categoriesItemList;
        filteredList=squareMediumModelList;
        this.v=v;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.near_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NEARSERVICESItem squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getLOGO()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getBUSINESSNAME());
        holder.ratingBar.setRating(Float.parseFloat(squareMediumModel.getrA()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent;
            if (v==0) {
                intent = new Intent(context, UploadImageNear.class);
                intent.putExtra("V",0);

            }
            else {
                intent = new Intent(context, AddNewNearInfoActivity.class);
                intent.putExtra("V",1);

            }
            intent.putExtra("ID",squareMediumModel.getID());

            intent.putExtra("DES",squareMediumModel.getDESCRIBER());
            intent.putExtra("CID",squareMediumModel.getID());
            intent.putExtra("IMG",squareMediumModel.getLOGO());
            intent.putExtra("WAP",squareMediumModel.getCONTACT());
            intent.putExtra("N",squareMediumModel.getBUSINESSNAME());
            intent.putExtra("LA",squareMediumModel.getLATITUDE());
            intent.putExtra("LO",squareMediumModel.getLONGITUDE());
            intent.putExtra("ADD",squareMediumModel.getLOCATIONNAME());
            intent.putExtra("S",squareMediumModel.getSERVICES());
            intent.putExtra("HRS",squareMediumModel.getNAME());
            intent.putExtra("RA",squareMediumModel.getrA());
//
            activity.startActivity(intent);
        });

       // holder.des.setText(squareMediumModel.getDESCRIBER());

    }


    @Override
    public int getItemCount() {
        return filteredList.size();
    }




    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        RatingBar ratingBar;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.grid_category_image);
            title=itemView.findViewById(R.id.grid_category_title);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            //description=itemView.findViewById(R.id.description);
           // eat=itemView.findViewById(R.id.button2);
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
}
