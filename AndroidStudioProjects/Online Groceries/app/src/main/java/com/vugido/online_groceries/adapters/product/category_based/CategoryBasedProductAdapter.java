package com.vugido.online_groceries.adapters.product.category_based;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.homepage.ProductsAdapter;
import com.vugido.online_groceries.models.homepage.updated.HOMEPRODUCTSItem;
import com.vugido.online_groceries.models.product.categories.PRODUCTSItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryBasedProductAdapter extends RecyclerView.Adapter<CategoryBasedProductAdapter.MyViewHolder>implements Filterable {

    //List<SECTIONSItem> categoriesItemList;
    List<PRODUCTSItem> squareMediumModelList;
    Context context;
    ProductsAdapter.INC_DEC inc_dec;
    List<PRODUCTSItem> filteredList;


    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PRODUCTSItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(squareMediumModelList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PRODUCTSItem item : squareMediumModelList) {
                    if (item.getTITLE().toLowerCase().contains(filterPattern) || item.getTITLE().toLowerCase().contains(filterPattern)) {
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
            filteredList.addAll((Collection<? extends PRODUCTSItem>) results.values);
            notifyDataSetChanged();
        }
    };

    public interface INC_DEC{

        void inc_dec(int pid,boolean is_inc,int position);
    }
    public CategoryBasedProductAdapter(List<PRODUCTSItem> squareMediumModelList, Context context) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        inc_dec= (ProductsAdapter.INC_DEC) context;
        filteredList=squareMediumModelList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PRODUCTSItem squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getTITLE());
        holder.des.setText(squareMediumModel.getQUANTITY());

        if(squareMediumModel.getINSTOCK()==1){
            //ok
            holder.ofs.setVisibility(View.INVISIBLE);
        }else {
            holder.ofs.setVisibility(View.VISIBLE);
        }

        if(squareMediumModel.getOFFERENABLED()==1){
            //offer enabled..
            holder.offer.setVisibility(View.VISIBLE);
            holder.cl.setVisibility(View.VISIBLE);
            holder.cp.setVisibility(View.VISIBLE);
            holder.offer.setText(String.valueOf(Float.valueOf(squareMediumModel.getOFFER()))+"%");
            holder.cp.setText(String.valueOf(Float.parseFloat(squareMediumModel.getPRICE())));
            holder.price.setText("Rs."+offerPercentagePrice(Float.parseFloat(squareMediumModel.getOFFER()),Float.parseFloat(squareMediumModel.getPRICE()))+"/-");


        }else {
            holder.offer.setVisibility(View.INVISIBLE);
            holder.cl.setVisibility(View.INVISIBLE);
            holder.cp.setVisibility(View.INVISIBLE);

                  holder.price.setText("Rs."+Float.parseFloat(squareMediumModel.getPRICE())+"/-");
        }
        holder.cc.setText(String.valueOf(squareMediumModel.getCARTCOUNT()));

        holder.inc.setOnClickListener(v -> {
            inc_dec.inc_dec(squareMediumModel.getID(),true,position);
        });

        holder.dec.setOnClickListener(v -> {
            if(squareMediumModel.getCARTCOUNT()!=0){
                inc_dec.inc_dec(squareMediumModel.getID(),false,position);
            }
        });

    }

    private float offerPercentagePrice(float offer, float price) {

       float x=((price*offer)/100f);
       return price-x;

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

        ImageView imageView,veg;
        TextView title,price,cp,offer,des,cc;
        View cl;
        ImageButton inc,dec;
        RelativeLayout ofs;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.product_image);
            title=itemView.findViewById(R.id.product_title);

            ofs=itemView.findViewById(R.id.out_of_stock_layout);
            veg=itemView.findViewById(R.id.imageView3);
            price=itemView.findViewById(R.id.product_price);
            cp=itemView.findViewById(R.id.cutted_price);
            cl=itemView.findViewById(R.id.cut_price_line);
            offer=itemView.findViewById(R.id.offer_text);
            des=itemView.findViewById(R.id.textView45);
            inc=itemView.findViewById(R.id.inc);
            dec=itemView.findViewById(R.id.dec);
            cc=itemView.findViewById(R.id.textView2);

        }
    }


}
