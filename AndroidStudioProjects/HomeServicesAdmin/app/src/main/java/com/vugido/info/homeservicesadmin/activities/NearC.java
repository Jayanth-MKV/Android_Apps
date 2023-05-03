package com.vugido.info.homeservicesadmin.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.adapters.near.NearPageMainAdapter;
import com.vugido.info.homeservicesadmin.app_config_variables.MyPreferences;
import com.vugido.info.homeservicesadmin.models.near_page.DATA;
import com.vugido.info.homeservicesadmin.models.near_page.NEARSERVICECATEGORIESItem;
import com.vugido.info.homeservicesadmin.models.near_page.NEARSERVICESItem;
import com.vugido.info.homeservicesadmin.models.near_page.NearPageMainModel;
import com.vugido.info.homeservicesadmin.models.near_page.Response;
import com.vugido.info.homeservicesadmin.network.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class NearC extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout swipeRefreshLayout;
    private List<NearPageMainModel> homePageMainModelList;
    private NearPageMainAdapter homePageMainAdapter;

    private RecyclerView recyclerView;


    private LinearLayoutManager layoutManager;
    int v;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_orders);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);
        // view pager
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.near_rc);

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchNearPageData);
        v=getIntent().getIntExtra("V",0);

    }


    private void fetchNearPageData() {

        Map<String,Object> map=new HashMap<>();
        map.put("UID","1");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchNearPage(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                Log.e("okok",response.toString());
                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful())
                {

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        bindData(response.body().getDATA());
                    }else{

                        //no daa..
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                Log.e("error",t.getMessage().toString());
                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }


    private void bindData(DATA data) {


        homePageMainModelList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        // segment the total products


        List<NEARSERVICECATEGORIESItem> serviceCategoroesModelList=data.getNEARSERVICECATEGORIES();



        List<NEARSERVICESItem> serviceCategoryProductsList=data.getNEARSERVICES();



        for (NEARSERVICECATEGORIESItem dataItem : serviceCategoroesModelList) {


            homePageMainModelList.add(NearPageMainModel.createSquareMediumViewModelList(serviceCategoroesModelList, NearPageMainModel.SQUARE_MEDIUM_VIEW, getCategoryType(dataItem,serviceCategoryProductsList), dataItem.getTITLE(), dataItem.getID(), "", "#8E2DE2"));



        }

        homePageMainAdapter = new NearPageMainAdapter(homePageMainModelList, this,v);
        recyclerView.setAdapter(homePageMainAdapter);
        recyclerView.setNestedScrollingEnabled(false);




    }

    private List<NEARSERVICESItem> getCategoryType (NEARSERVICECATEGORIESItem dataItem, List < NEARSERVICESItem > productsmenuItemList){

        List<NEARSERVICESItem> productsmenuItemList1 = new ArrayList<>();

        for (NEARSERVICESItem productsmenuItem : productsmenuItemList) {

            if (productsmenuItem.getTYPEID() == dataItem.getID()) {
                productsmenuItemList1.add(productsmenuItem);
            }
        }

        return productsmenuItemList1;


    }


    @Override
    public void onRefresh() {
        fetchNearPageData();
    }
}
