package com.vugido.foodhub.v_c.adapter.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.foodhub.v_c.R;
import com.vugido.foodhub.v_c.models.all_products.DATA;
import com.vugido.foodhub.v_c.models.all_products.ITEMSItem;
import com.vugido.foodhub.v_c.models.all_products.MENUItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class AllProductsRecyclerViewAdapter extends RecyclerView.Adapter<AllProductsRecyclerViewAdapter.MyViewHolder>
        implements Filterable {

    DATA data;
    Context context;
    List<ITEMSItem> itemsItemList;
    List<MENUItem> menuItemList;
    List<ITEMSItem> filteredList;
    private Updater updater;

    public AllProductsRecyclerViewAdapter(DATA data, Context context) {
        this.data = data;
        filteredList=data.getITEMS();
        itemsItemList=new ArrayList<>(data.getITEMS());
        menuItemList=data.getMENU();
        this.context = context;
        updater= (Updater) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final ITEMSItem itemsItem=filteredList.get(position);
        Glide.with(context).load(itemsItem.getIMAGE()).into(holder.ItemImage);
        holder.ItemName.setText(itemsItem.getENAME());

        for(MENUItem menuItem:menuItemList){

            if(menuItem.getCID()==itemsItem.getSID()){
                holder.Category.setText(String.format("Type :%s", menuItem.getSUBNAME()));
                break;
            }
        }

        holder.ItemQuantity.setText(String.format("%s %s", itemsItem.getQUANTITY(), itemsItem.getDESCRIPTION()));
        if(itemsItem.getOFFER()!=0){
            // offer exists..
            holder.ItemOffer.setVisibility(View.VISIBLE);
            holder.ItemOffer.setText(String.format(Locale.getDefault(),"%d%%", itemsItem.getOFFER()));
            // set price after offer


        }else {

            // no offer
            holder.ItemOffer.setVisibility(View.GONE);
            holder.ItemOffer.setVisibility(View.GONE);
            holder.Price.setText(String.format(Locale.getDefault(),"Rs.%d/-", itemsItem.getPRICE()));
        }

        if(itemsItem.getINSTOCK()==0){
            // active..
            holder.ItemState.setText("IN-STOCK");
            holder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.main_green_color));


        }else {
            holder.ItemState.setText("OUT OF STOCK");
            holder.ItemState.setBackgroundColor(context.getResources().getColor(R.color.red));
            // not active
        }

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updater.delete(itemsItem.getPID());
            }
        });

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updater.edit(itemsItem.getPID());

            }
        });


      /*  holder.PriceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updater.priceUpdater(itemsItem.getID());
            }
        });

        holder.ItemState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updater.toggleInStock(itemsItem.getID());
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ITEMSItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemsItemList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ITEMSItem item : itemsItemList) {
                    if (item.getENAME().toLowerCase().contains(filterPattern) ) {
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
            filteredList.addAll((Collection<? extends ITEMSItem>) results.values);
            notifyDataSetChanged();
        }
    };


    @Override
    public Filter getFilter() {
        return filter;
    }

    static  class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView ItemImage;
        TextView ItemName,ItemOffer,ItemQuantity,Price,CutPrice,Category;
        View CutLine;
        Button ItemState,Edit,Delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemImage=itemView.findViewById(R.id.product_image);
            ItemName=itemView.findViewById(R.id.product_title);
            ItemOffer=itemView.findViewById(R.id.offer_text);
            ItemQuantity=itemView.findViewById(R.id.product_quantity);
            Price=itemView.findViewById(R.id.product_price);
            CutPrice=itemView.findViewById(R.id.cutted_price);
            CutLine=itemView.findViewById(R.id.cut_price_line);
            ItemState=itemView.findViewById(R.id.availability);
            Category=itemView.findViewById(R.id.category_type);
            Edit=itemView.findViewById(R.id.button);
            Delete=itemView.findViewById(R.id.button3);
        }
    }

    public interface Updater{

       // void priceUpdater(int pid);
      //  void toggleInStock(int pid);
        void delete(int pid);
        void edit(int pid);

    }


}
