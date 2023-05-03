package com.vugido.foodhub.hungrybirdsadmin.fragments.manage_orders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vugido.foodhub.hungrybirdsadmin.R;
import com.vugido.foodhub.hungrybirdsadmin.adapters.orders_adapters.MyPendingOrdersAdapter;
import com.vugido.foodhub.hungrybirdsadmin.models.orders.AO.Response;
import com.vugido.foodhub.hungrybirdsadmin.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class ActiveOrdersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MyPendingOrdersAdapter myPendingOrdersAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pending_orders,container,false);

        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh_layout);
        recyclerView=view.findViewById(R.id.activeItemsRecyclerView);

        // swipe listener..
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        // called first time.. with out pulling..
        // make sure network connection before calling..
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {


                // get the data here
                fetchActiveOrders();

            }
        });






        return view;
    }

    private void fetchActiveOrders() {


        Map<String,Object> map=new HashMap<>();
        map.put("AID","1");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().GET_AO(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (response.isSuccessful()){

                    assert response.body() != null;
                    if (response.body().isSTATUS()){
                        //ok ordere
                    }else {
                        //no orders...
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    @Override
    public void onRefresh() {
        fetchActiveOrders();
    }
}
