package com.vugido.info.homeservicesadmin.adapters.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.activities.AddNewServiceInfoActivity;
import com.vugido.info.homeservicesadmin.activities.MainActivity;
import com.vugido.info.homeservicesadmin.activities.ServiceDetails;
import com.vugido.info.homeservicesadmin.models.homepage.SERVICECATEGORIESItem;
import com.vugido.info.homeservicesadmin.models.homepage.SERVICEPRODUCTSItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareMediumRecyclerViewAdapter extends RecyclerView.Adapter<SquareMediumRecyclerViewAdapter.MyViewHolder>implements Filterable {

    public static final int SERVICE_CODE = 10;
    List<SERVICECATEGORIESItem> categoriesItemList;
    List<SERVICEPRODUCTSItem> squareMediumModelList;
    Context context;
     List<SERVICEPRODUCTSItem> filteredList;
     Activity activity;
     int v;

    public SquareMediumRecyclerViewAdapter(List<SERVICEPRODUCTSItem> squareMediumModelList, Context context, List<SERVICECATEGORIESItem> categoriesItemList,int v) {
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
        View view= LayoutInflater.from(context).inflate(R.layout.square_horizontal_medium_view_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SERVICEPRODUCTSItem squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getTITLE());

        holder.itemView.setOnClickListener(view -> {

            if (v==1){

                Intent i=new Intent(context, AddNewServiceInfoActivity.class);
                i.putExtra("V",1);
                i.putExtra("SERVICES", squareMediumModel.getDESCRIPTION());
                i.putExtra("SID", squareMediumModel.getID());
                i.putExtra("N", squareMediumModel.getTITLE());
                i.putExtra("CID",squareMediumModel.getID());
                activity.startActivity(i);


            }else {
                Intent intent = new Intent(context, ServiceDetails.class);
                intent.putExtra("SERVICES", squareMediumModel.getTAGS());
                intent.putExtra("SID", squareMediumModel.getID());
                intent.putExtra("N", squareMediumModel.getTITLE());
                activity.startActivityForResult(intent, SERVICE_CODE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SERVICEPRODUCTSItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(squareMediumModelList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SERVICEPRODUCTSItem item : squareMediumModelList) {
                    if (item.getTITLE().toLowerCase().contains(filterPattern) || item.getTITLE().toLowerCase().contains(filterPattern) || getCat(item.getSID()).contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredList.clear();
            filteredList.addAll((Collection<? extends SERVICEPRODUCTSItem>) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            //description=itemView.findViewById(R.id.description);
           // eat=itemView.findViewById(R.id.button2);
        }
    }


    private String getCat(int id){
        for (SERVICECATEGORIESItem categoriesItem:categoriesItemList){
            if (categoriesItem.getID()==id){
return categoriesItem.getTITLE();
            }
        }
        return "";
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
