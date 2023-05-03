package com.foodhub.vugido.adapters.product;

import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.models.homepage.HOMEPRODUCTSItem;
import com.foodhub.vugido.models.homepage.SECTIONSItem;
import com.foodhub.vugido.models.product.ProductModel;
import com.foodhub.vugido.models.product.client_products.FOODSItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.MyViewHolder> implements Filterable {

    //List<SECTIONSItem> categoriesItemList;
    List<FOODSItem> squareMediumModelList;
    Context context;
    int cid;
    INC_DEC inc_dec;
    List<FOODSItem> filteredList;
    MediaPlayer mMediaPlayer;

    public ProductListRecyclerViewAdapter(List<FOODSItem> squareMediumModelList, Context context,int cid) {
        this.squareMediumModelList = new ArrayList<>(squareMediumModelList);
        this.context = context;
        inc_dec= (INC_DEC) context;
        filteredList=squareMediumModelList;
        this.cid=cid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FOODSItem squareMediumModel=filteredList.get(position);

        Glide.with(context).load(squareMediumModel.getIMAGE()).into(holder.imageView);
        holder.title.setText(squareMediumModel.getENAME());

        if (squareMediumModel.getINSTOCK()==1){
//            holder.add.setEnabled(false);
            holder.ofs.setVisibility(View.VISIBLE);
        }else
        {
//            holder.add.setEnabled(true);
            holder.ofs.setVisibility(View.INVISIBLE);
        }


        holder.cc.setText(String.valueOf(squareMediumModel.getCC()));

        holder.inc.setOnClickListener(v -> {
            inc_dec.inc_dec(squareMediumModel.getID(),true,position,cid);
            try {
                Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.inc);

                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(context, sound);
                final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                assert audioManager != null;
                if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                    //mMediaPlayer.setLooping(true);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
//                    Vibrator v1 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//// Vibrate for 500 milliseconds
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        v1.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.EFFECT_CLICK));
//                    } else {
//                        //deprecated in API 26
//                        v1.vibrate(500);
//                    }

                }
            } catch(Exception e) {
            }
            
        });

        holder.dec.setOnClickListener(v -> {
            if(squareMediumModel.getCC()!=0){
                inc_dec.inc_dec(squareMediumModel.getID(),false,position,cid);

                try {
                    Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.inc);

                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource(context, sound);
                    final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    assert audioManager != null;
                    if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                        //mMediaPlayer.setLooping(true);
                        mMediaPlayer.prepare();
                        mMediaPlayer.start();
//                        Vibrator v1 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//// Vibrate for 500 milliseconds
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                            v1.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
//                        } else {
//                            //deprecated in API 26
//                            v1.vibrate(500);
//                        }

                    }
                } catch(Exception e) {
                }
            }
        });



        if(squareMediumModel.getVEG()==0){
            //veg
            Glide.with(context).load(R.drawable.veg_mark).into(holder.veg);
        }else {
            //non veg
            Glide.with(context).load(R.drawable.non_veg_mark).into(holder.veg);

        }

        if (squareMediumModel.getOFFER()>0)
        {

            holder.price.setText("Rs."+offerPercentagePrice(squareMediumModel.getOFFER(),squareMediumModel.getPRICE())+"/-");
            holder.cp.setText("Rs."+squareMediumModel.getPRICE()+"/-");
            holder.cl.setVisibility(View.VISIBLE);
            holder.cp.setVisibility(View.VISIBLE);
            holder.offer.setVisibility(View.VISIBLE);
            holder.offer.setText(squareMediumModel.getOFFER()+"%");

        }else
        {
            holder.offer.setVisibility(View.GONE);
            holder.cp.setVisibility(View.GONE);
            holder.cl.setVisibility(View.GONE);
            holder.price.setText("Rs."+squareMediumModel.getPRICE()+"/-");
        }

    }

    private int offerPercentagePrice(int offer, int price) {

        return  ((price*offer)/100);

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

    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FOODSItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(squareMediumModelList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (FOODSItem item : squareMediumModelList) {
                    if (item.getENAME().toLowerCase().contains(filterPattern) ||
                            item.getDESCRIPTION().toLowerCase().contains(filterPattern)) {
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
            filteredList.addAll((Collection<? extends FOODSItem>) results.values);
            notifyDataSetChanged();
        }
    };

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,veg;
        TextView title,price,cp,offer,cc;
        View cl;
      //  Button add;
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
            inc=itemView.findViewById(R.id.inc);
            dec=itemView.findViewById(R.id.dec);
            cc=itemView.findViewById(R.id.textView2);

        }
    }


    public interface INC_DEC{

        void inc_dec(int pid,boolean is_inc,int position,int cid);
    }

}
