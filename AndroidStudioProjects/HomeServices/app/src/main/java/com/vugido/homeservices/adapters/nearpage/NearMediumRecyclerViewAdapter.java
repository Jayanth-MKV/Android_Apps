package com.vugido.homeservices.adapters.nearpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.homeservices.R;
import com.vugido.homeservices.activities.NearByServicesDetails;
import com.vugido.homeservices.activities.ServiceDetails;
import com.vugido.homeservices.models.homepage.SERVICECATEGORIESItem;
import com.vugido.homeservices.models.homepage.SERVICEPRODUCTSItem;
import com.vugido.homeservices.models.near_page.NEARSERVICECATEGORIESItem;
import com.vugido.homeservices.models.near_page.NEARSERVICESItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NearMediumRecyclerViewAdapter extends RecyclerView.Adapter<NearMediumRecyclerViewAdapter.MyViewHolder>{

    public static final int SERVICE_CODE = 10;
    List<NEARSERVICECATEGORIESItem> categoriesItemList;
    List<NEARSERVICESItem> squareMediumModelList;
    Context context;
     List<NEARSERVICESItem> filteredList;
     Activity activity;

    public NearMediumRecyclerViewAdapter(List<NEARSERVICESItem> squareMediumModelList, Context context, List<NEARSERVICECATEGORIESItem> categoriesItemList) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        activity= (Activity) context;
        this.categoriesItemList=categoriesItemList;
        filteredList=squareMediumModelList;
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

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, NearByServicesDetails.class);
            intent.putExtra("ID",squareMediumModel.getID());
            intent.putExtra("DES",squareMediumModel.getDESCRIBER());
            intent.putExtra("IMG",squareMediumModel.getLOGO());
            intent.putExtra("WAP",squareMediumModel.getCONTACT());
            intent.putExtra("N",squareMediumModel.getBUSINESSNAME());
            intent.putExtra("LA",squareMediumModel.getLATITUDE());
            intent.putExtra("LO",squareMediumModel.getLONGITUDE());
            intent.putExtra("ADD",squareMediumModel.getLOCATIONNAME());
            intent.putExtra("S",squareMediumModel.getSERVICES());
            intent.putExtra("HRS",squareMediumModel.getNAME());
            intent.putExtra("RA",squareMediumModel.getrA());

            activity.startActivityForResult(intent,SERVICE_CODE);
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
