package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.myOrdersAdapters.MyOrdersAdapter;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.my_orders.DATAItem;
import com.foodhub.vugido.models.my_orders.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MyOrders extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView textView;
    private LottieAnimationView lottieAnimationView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_orders,container,false);
        recyclerView=view.findViewById(R.id.orders_recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_orders_fragment);
        lottieAnimationView=view.findViewById(R.id.lottie_no_orders);
        textView=view.findViewById(R.id.textView14);

        swipeRefreshLayout.setOnRefreshListener(this);


        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.black);
        // called first time.. with out pulling..
        // make sure network connection before calling..
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {


                // get the data here
                fetchOrdersData();

            }
        });

        return view;
    }

    private void fetchOrdersData() {

        lottieAnimationView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(context).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(context).getBaseLocationURL()).getOrdersData(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful()){
                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok orders there

                        List<DATAItem> dataItemList=response.body().getDATA();
                        setOrdersData(dataItemList);
                    }else {

                        // no orders..
                        textView.setVisibility(View.VISIBLE);
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        lottieAnimationView.setAnimation(R.raw.no_active_orders);
                        lottieAnimationView.playAnimation();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void setOrdersData(List<DATAItem> dataItemList) {
        Collections.reverse(dataItemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyOrdersAdapter(context,dataItemList));

    }

    @Override
    public void onRefresh() {
        fetchOrdersData();
    }
}
