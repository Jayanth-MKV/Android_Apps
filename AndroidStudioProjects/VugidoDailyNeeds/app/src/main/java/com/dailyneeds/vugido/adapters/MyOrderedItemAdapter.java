package com.dailyneeds.vugido.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.models.CartProducts;

import java.util.List;

public class MyOrderedItemAdapter extends RecyclerView.Adapter<MyOrderedItemAdapter.MyViewHolder> {

    private Context context;
    private List<CartProducts> cartProductsList;

    public MyOrderedItemAdapter(Context context, List<CartProducts> cartProductsList){
        this.context=context;
        this.cartProductsList=cartProductsList;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.ordered_item_row_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        final CartProducts cartProducts=cartProductsList.get(position);
        myViewHolder.name.setText(cartProducts.getProductTname());
        myViewHolder.price.setText(String.format("Rs.%s/-", cartProducts.getProductTotalPrice()));
        myViewHolder.quantity.setText(CartProducts.getProductQualifierQuantity(cartProducts));


    }

    @Override
    public int getItemCount() {
        return cartProductsList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,quantity,price;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CartItemName);
            quantity=itemView.findViewById(R.id.CartItemQuantity);
            price=itemView.findViewById(R.id.CartItemPrice);
        }
    }
}
