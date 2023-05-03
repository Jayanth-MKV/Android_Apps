package com.vugido.foodhub.hungrybirdsadmin.adapters.home_adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.foodhub.hungrybirdsadmin.R;
import com.vugido.foodhub.hungrybirdsadmin.models.homepage.slider.DATAItem;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.VH> {

    private Context context;
    private List<DATAItem> dataItemList;
    private SLIDER_FUN slider_fun;

    public SliderAdapter(Context context, List<DATAItem> dataItemList,SLIDER_FUN slider_fun) {
        this.context = context;
        this.dataItemList = dataItemList;
        this.slider_fun=  slider_fun;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.slider_row_design,parent,false);

       return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        DATAItem dataItem=dataItemList.get(position);

        Glide.with(context).load(dataItem.getIMG()).into(holder.img);

        if (dataItem.getHS()==1){
            holder.hs.setBackgroundColor(Color.GREEN);
            holder.hs.setText("Showing");
        }
        else{holder.hs.setBackgroundColor(Color.RED);
            holder.hs.setText("Hided");
        }

        holder.del.setOnClickListener(v -> {


            slider_fun.delSlider(dataItem.getID());
        });


        holder.hs.setOnClickListener(v -> {


            slider_fun.toggleSlider(dataItem.getID());
        });

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    static  class VH extends RecyclerView.ViewHolder{

        Button del,hs;
        ImageView img;

        public VH(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            del=itemView.findViewById(R.id.button);
            hs=itemView.findViewById(R.id.button3);


        }
    }

    public interface SLIDER_FUN{

        void delSlider(int id);
        void  toggleSlider(int id);
    }
}
