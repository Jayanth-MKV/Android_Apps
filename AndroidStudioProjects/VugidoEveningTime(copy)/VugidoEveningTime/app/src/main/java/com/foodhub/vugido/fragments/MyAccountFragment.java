package com.foodhub.vugido.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.Account.AccountAdapter;
import com.foodhub.vugido.adapters.homepage.HomePageMainAdapter;
import com.foodhub.vugido.models.account.AccountOptionsModel;
import com.foodhub.vugido.models.homepage.HOMEPRODUCTSItem;
import com.foodhub.vugido.models.homepage.HomePageMainModel;
import com.foodhub.vugido.models.homepage.SECTIONSItem;

import java.util.ArrayList;
import java.util.List;

public class MyAccountFragment extends Fragment {

    RecyclerView recyclerView;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        recyclerView=view.findViewById(R.id.acc_rec);

        setAccountOptions();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    private void setAccountOptions() {

        List<AccountOptionsModel> accountOptionsModelList=new ArrayList<>();

        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.loc,"Manage Addresses","View your saved addresses",1));
        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.msg,"Chat Helpline","Chat with us here",2));
        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.food,"Food Preferences","Setting for your foods tastes ",3));
        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.his,"Order History","View your orders",4));
        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.refer,"Refer & Earn","Earn money by referring friends",5));
        accountOptionsModelList.add(new AccountOptionsModel(R.drawable.faq,"Help & FAQs","Need Help , contact us here",6));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);

        recyclerView.setLayoutManager(linearLayoutManager);

        AccountAdapter accountAdapter=new AccountAdapter(accountOptionsModelList,context);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(accountAdapter);





    }


}
