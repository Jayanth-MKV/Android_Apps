package com.dailyneeds.vugido.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.models.CartProducts;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter <CartAdapter.MyViewHolder>{

    private Context context;
    private List<CartProducts> cartProductsList;
    private SetPriceTag setPriceTag;
    private CartDelete cartDelete;
    public CartAdapter(Context context, List<CartProducts> cartProductsList){
        this.context=context;
        this.cartProductsList=cartProductsList;
        setPriceTag= (SetPriceTag) context;
        cartDelete= (CartDelete) context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.cart_design,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        final CartProducts cartProducts=cartProductsList.get(i);
        myViewHolder.name.setText(cartProducts.getProductTname());
        myViewHolder.price.setText(String.format("Rs.%s/-", cartProducts.getProductTotalPrice()));
        myViewHolder.quantity.setText(CartProducts.getProductQualifierQuantity(cartProducts));
        myViewHolder.description.setText(cartProducts.getDescription());

        Glide.with(context).load(cartProducts.getImage()).into(myViewHolder.image);

        if(Integer.parseInt(cartProducts.getACTIVE())!=0){

            myViewHolder.Active.setVisibility(View.VISIBLE);

        }else {

            myViewHolder.Active.setVisibility(View.INVISIBLE);
        }






        myViewHolder.Remove.setOnClickListener(view -> {


            //remove functionality...

            cartDelete.cartDelete(Integer.parseInt(cartProducts.getUNIQUE_ID()));


        });





       /* myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(cartProducts.isChecked()){
                    myViewHolder.checkBox.setChecked(false);
                    cartProducts.setChecked(false);
                    setPriceTag.setPriceTag(cartProducts,i);


                }else {

                    cartProducts.setChecked(true);
                    myViewHolder.checkBox.setChecked(true);
                    setPriceTag.setPriceTag(cartProducts,i);

                }


            }
        });*/

    }

    @Override
    public int getItemCount() {
        return cartProductsList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,quantity,price,description,Active;
      //  CheckBox checkBox;
        Button SaveForLater;
        ImageView image;
        MaterialCardView Remove;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.product_name);
            quantity=itemView.findViewById(R.id.product_quantity);
            price=itemView.findViewById(R.id.product_price);
          //  Remove=itemView.findViewById(R.id.remove);
            Active=itemView.findViewById(R.id.not_active);
            image=itemView.findViewById(R.id.product_image);
            description=itemView.findViewById(R.id.product_description);
            Remove=itemView.findViewById(R.id.cart_del_button);


          //  checkBox=itemView.findViewById(R.id.cartCheckBox);


        }
    }

    public  interface  SetPriceTag{

        void setPriceTag(CartProducts cartProducts,int position);
    }

    public interface CartDelete{

        void cartDelete(int Card_ID);
    }

}
