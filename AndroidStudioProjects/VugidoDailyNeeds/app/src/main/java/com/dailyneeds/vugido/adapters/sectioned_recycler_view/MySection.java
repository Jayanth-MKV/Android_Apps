package com.dailyneeds.vugido.adapters.sectioned_recycler_view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.fragments.ProductAddOnDialog;
import com.dailyneeds.vugido.models.Product;
import com.dailyneeds.vugido.models.SubCategoryModel.SubCategoryItem;
import java.util.List;
import java.util.Objects;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import static com.dailyneeds.vugido.models.Product.getProductQualifierPrice;
import static com.dailyneeds.vugido.models.Product.getProductQualifierQuantity;

public class MySection extends Section {
    private String title;
  //  private List<String> itemList = Arrays.asList("Item1", "Item2", "Item3");
    private List<SubCategoryItem > subCategoryItemList;
    private FragmentManager fragmentManager;
    private Context context;
    private Product product;
    private int CID;

    public MySection(String title, List<SubCategoryItem> subCategoryItemList, Context context, FragmentManager fragmentManager,int CID) {
        // call constructor with layout resources for this Section header and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_item)
                .headerResourceId(R.layout.section_header)
                .build());

        this.title=title;
        this.CID=CID;
        this.fragmentManager=fragmentManager;
        this.subCategoryItemList=subCategoryItemList;
        this.context=context;
    }

    @Override
    public int getContentItemsTotal() {
        return subCategoryItemList.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder h, int i) {
        MyItemViewHolder holder = (MyItemViewHolder) h;
        SubCategoryItem subCategoryItem=subCategoryItemList.get(i);

        // bind your view here
      //  productAddDialog(subCategoryItem);


        Product product=new Product();
        product.setProductCatID(String.valueOf(CID));
        product.setProductID(String.valueOf(subCategoryItem.getID()));
        product.setProductEName(subCategoryItem.getENAME());
        product.setProductTNAme(subCategoryItem.getTNAME());
        product.setProductInStock(String.valueOf(subCategoryItem.getINSTOCK()));
        product.setProductImage(subCategoryItem.getIMAGE());
        product.setProductOffer(String.valueOf(subCategoryItem.getOFFER()));
        product.setProductOfferStatus(String.valueOf(subCategoryItem.getOFFERSTATUS()));
        product.setProductPrice(subCategoryItem.getpRICE());
        product.setProductQuantity(subCategoryItem.getqUANTITY());
        product.setProductUnit(subCategoryItem.getqUANTITYUNIT());
        product.setProductTag(String.valueOf(subCategoryItem.getTAG()));
        product.setDefaultSize(String.valueOf(subCategoryItem.getDEFAULTSIZE()));
        product.setUnitInterval(String.valueOf(subCategoryItem.getUNITINTERVAL()));
        product.setSmallPrice(String.valueOf(subCategoryItem.getS()));
        product.setLargePrice(String.valueOf(subCategoryItem.getL()));
        product.setMediumPrice(String.valueOf(subCategoryItem.getM()));
        product.setProductQualifier(String.valueOf(subCategoryItem.getQUALIFIER()));




        // use product qualifier quantity....


        //holder.quantity.setText(getProductQualifierQuantity(product));



        //display offer price if exists...

        // Offer settings...
        if(Integer.parseInt(product.getProductOfferStatus())==0){
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

        }


        holder.vege_name.setText(subCategoryItemList.get(i).getTNAME());
        Glide.with(context).load(subCategoryItemList.get(i).getIMAGE()).into(holder.imageView);
       // holder.price.setText(subCategoryItemList.get(i).getpRICE());
       // holder.gram.setText(subCategoryItemList.get(i).getqUANTITY());
        holder.gram.setText(getProductQualifierQuantity(product));



        holder.imageView.setOnClickListener(view -> {



            FragmentTransaction ft = fragmentManager.beginTransaction();
            ProductAddOnDialog productAddOnDialog=new ProductAddOnDialog();
            Bundle bundle=new Bundle();
            bundle.putParcelable("PRODUCT",product);
            productAddOnDialog.setArguments(bundle);
            productAddOnDialog.show(ft,"ADD_ON");

        });

        holder.add.setOnClickListener(view -> {




            FragmentTransaction ft = fragmentManager.beginTransaction();
            ProductAddOnDialog productAddOnDialog=new ProductAddOnDialog();
            Bundle bundle=new Bundle();
            bundle.putParcelable("PRODUCT",product);
            productAddOnDialog.setArguments(bundle);
            productAddOnDialog.show(ft,"ADD_ON");


        });











    }





    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder myHeaderViewHolder= (MyHeaderViewHolder) holder;
        myHeaderViewHolder.title.setText(title);

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, add_to_cart, remove_cart;
        TextView vege_name, price, gram,offer,striked_price;
        Button add;


        MyItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_sub_category_image);
            vege_name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
           // add_to_cart = itemView.findViewById(R.id._iv_add_to_cart);
           // remove_cart = itemView.findViewById(R.id._iv_remove_to_cart);
            gram = itemView.findViewById(R.id.product_quantity);
            add = itemView.findViewById(R.id.btn_add_sub_vege);
            offer=itemView.findViewById(R.id.Offer_Tag);
            striked_price=itemView.findViewById(R.id.striked_price);
            fadeInOut();
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

    class  MyHeaderViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        MyHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_sectioned);


        }
    }


}