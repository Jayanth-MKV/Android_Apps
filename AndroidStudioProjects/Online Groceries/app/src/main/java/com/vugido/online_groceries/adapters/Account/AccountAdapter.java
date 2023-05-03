package com.vugido.online_groceries.adapters.Account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.models.account.AccountOptionsModel;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    List<AccountOptionsModel> accountOptionsModelList;
    Context context;

    public AccountAdapter(List<AccountOptionsModel> accountOptionsModelList, Context context) {
        this.accountOptionsModelList = accountOptionsModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.account_options, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AccountOptionsModel accountOptionsModel=accountOptionsModelList.get(position);

        Glide.with(context).load(accountOptionsModel.getImage()).into(holder.imageView);
        holder.T1.setText(accountOptionsModel.getT1());
        holder.T2.setText(accountOptionsModel.getT2());


        holder.constraintLayout.setOnClickListener(v -> {



        });


    }

    @Override
    public int getItemCount() {
        return accountOptionsModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView T1,T2;
        ConstraintLayout constraintLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView4);
            T1=itemView.findViewById(R.id.textView7);
            T2=itemView.findViewById(R.id.textView8);
            constraintLayout=itemView.findViewById(R.id.acc_op);


        }
    }

}
