package com.vugido.homeservices.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vugido.homeservices.R;
import com.vugido.homeservices.activities.SearchActivity;
import com.vugido.homeservices.adapters.homepage.HomePageMainAdapter;
import com.vugido.homeservices.adapters.homepage.MyOfferSlider;
import com.vugido.homeservices.adapters.nearpage.NearPageMainAdapter;
import com.vugido.homeservices.app.MyPreferences;
import com.vugido.homeservices.models.homepage.HomePageMainModel;
import com.vugido.homeservices.models.homepage.SERVICECATEGORIESItem;
import com.vugido.homeservices.models.homepage.SERVICEPRODUCTSItem;
import com.vugido.homeservices.models.homepage.SLIDERItem;
import com.vugido.homeservices.models.near_page.DATA;
import com.vugido.homeservices.models.near_page.NEARSERVICECATEGORIESItem;
import com.vugido.homeservices.models.near_page.NEARSERVICESItem;
import com.vugido.homeservices.models.near_page.NearPageMainModel;
import com.vugido.homeservices.models.near_page.Response;
import com.vugido.homeservices.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class MyOrdersFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private List<NearPageMainModel> homePageMainModelList;
    private NearPageMainAdapter homePageMainAdapter;

    private RecyclerView recyclerView;


    private LinearLayoutManager layoutManager;
    ImageView search;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        search=view.findViewById(R.id.imageView);

        search.setOnClickListener(v -> {

            Intent intent=new Intent(context, SearchActivity.class);
            intent.putExtra("V",2);
            startActivity(intent);

        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);
        // view pager
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.near_rc);

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchNearPageData);



        return view;
    }

    private void fetchNearPageData() {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(context).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchNearPage(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful())
                {

                    if(response.body().isSTATUS()){

                        bindData(response.body().getDATA());
                    }else{

                        //no daa..
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });



    }


    private void bindData(DATA data) {


        homePageMainModelList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        // segment the total products


        List<NEARSERVICECATEGORIESItem> serviceCategoroesModelList=data.getNEARSERVICECATEGORIES();



        List<NEARSERVICESItem> serviceCategoryProductsList=data.getNEARSERVICES();



        for (NEARSERVICECATEGORIESItem dataItem : serviceCategoroesModelList) {


            homePageMainModelList.add(NearPageMainModel.createSquareMediumViewModelList(serviceCategoroesModelList, NearPageMainModel.SQUARE_MEDIUM_VIEW, getCategoryType(dataItem,serviceCategoryProductsList), dataItem.getTITLE(), dataItem.getID(), "", "#8E2DE2"));



        }

        homePageMainAdapter = new NearPageMainAdapter(homePageMainModelList, context);
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
