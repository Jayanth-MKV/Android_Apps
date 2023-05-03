package com.dailyneeds.vugido.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.models.DataItem;

import java.util.List;

import static com.dailyneeds.vugido.models.Product.getProductQualifierPrice;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>  {

    private Context context;
    private FragmentManager fragmentManager;
    private List<DataItem> productList;
    private List<DataItem> FilteredProductList;
    public ProductAdapter(Context context, FragmentManager fragmentManager, List<DataItem> productList){
        this.context=context;
        this.fragmentManager=fragmentManager;
        this.productList=productList;
       // FilteredProductList=new ArrayList<>(productList);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.product_design,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final DataItem product=productList.get(position);




        holder.name.setText(product.getTNAME());
        // use product qualifier quantity....


        //holder.quantity.setText(getProductQualifierQuantity(product));


       Glide.with(context).load(String.format("%s%s", ConfigVariables.VEGETABLES_IMG_URL, product.getIMAGE())).into(holder.image);

        //display offer price if exists...

        // Offer settings...
     /*   if(Integer.parseInt(product.getProductOfferStatus())==0){
            //  no offer
            holder.offer.setVisibility(View.GONE);
            holder.price.setText(String.format("Rs.%s/-", getProductQualifierPrice(product)));
            holder.striked_price.setText("");

        }else {
            // offer exists
            holder.offer.setVisibility(View.VISIBLE);
            holder.offer.setText(String.format("%s%%", product.getProductOffer()));
            holder.striked_price.setText(String.format("Rs.%s/-", getProductQualifierPrice(product)));
            holder.price.setText(String.format("Rs.%s/-", String.valueOf(Product.offerPercentage(Float.parseFloat(product.getProductOffer()), Float.parseFloat(String.valueOf(Objects.requireNonNull(getProductQualifierPrice(product))))))));
            holder.striked_price.setPaintFlags(holder.striked_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //now set original price..

        }*/


        // add the on click listener....

       /* holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              //  StartDateDialog startDateDialog=new StartDateDialog();
              //  startDateDialog.show(fragmentManager,"START_DIALOG");

               FragmentTransaction ft = fragmentManager.beginTransaction();
                ProductAddOnDialog productAddOnDialog=new ProductAddOnDialog();
                Bundle bundle=new Bundle();
                bundle.putParcelable("PRODUCT",product);
                productAddOnDialog.setArguments(bundle);
                productAddOnDialog.show(ft,"ADD_ON");


            }
        });*/




        //In - Stock settings..
        /*if(){


        }*/






    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

   /* @Override
    public Filter getFilter() {


        return ProductsFiltered;
    }*/

  /*  private Filter ProductsFiltered=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Product> FilteredProducts=new ArrayList<>();

            if(charSequence== null || charSequence.length()==0){

                FilteredProducts.addAll(FilteredProductList);

            }else {
                String pattern=charSequence.toString().toLowerCase().trim();

                for(Product product:FilteredProductList){

                    if(product.getProductEName().toLowerCase().contains(pattern) || product.getProductTNAme().contains(pattern)){


                        FilteredProducts.add(product);

                    }

                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=FilteredProducts;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {


            productList.clear();
            productList.addAll((Collection<? extends Product>) filterResults.values);
            notifyDataSetChanged();

        }
    };*/

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView add,image;
        TextView name,price,quantity,offer,striked_price;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            add=itemView.findViewById(R.id.product_add);
            image=itemView.findViewById(R.id.product_image);
            name=itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_price);
            quantity=itemView.findViewById(R.id.product_quantity);
            offer=itemView.findViewById(R.id.Offer_Tag);
            striked_price=itemView.findViewById(R.id.striked_price);

          //  fadeInOut();

        }

        private void fadeInOut(){

            final AnimatorSet mAnimationSet;
            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(offer, "alpha", .5f, .1f);
            fadeOut.setDuration(200);
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(offer, "alpha", .1f, .5f);
            fadeIn.setDuration(200);

            mAnimationSet = new AnimatorSet();

            mAnimationSet.play(fadeIn).after(fadeOut);

            mAnimationSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mAnimationSet.start();
                }
            });

            mAnimationSet.start();
        }
    }





}
