package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.R;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.adapter.GridRecyclerViewAdapter;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.adapter.HomePageAdapters.MyOfferSlider;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.HomePageModel.DATA;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.HomePageModel.HIGHTLIGHTItem;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.HomePageModel.Response;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.network.Retrofit.RetrofitBuilder;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView recyclerView;
    private TabLayout top_tab;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;

    int current_position=0;
    int tv_size=0;
    private ViewPager topVP;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.home_recyclerView);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);



        top_tab=view.findViewById(R.id.top_tab_indicator);
        topVP=view.findViewById(R.id.offer_viewpager);
        // swipe listener..
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
                fetchHomePageData();

            }
        });

        return view;



    }

    private void fetchHomePageData() {

        Map<String,Object>map=new HashMap<>();
        map.put("UID","1");
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchHomePageContent(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull  Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok data received

                        DATA dataItemList=response.body().getDATA();

                        bindDataToAdapter(dataItemList);

                    }else {

                        // no data
                    }

                }else {

                    // something failed
                }
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }

    private void autoSlider(){
        final Handler handler=new Handler();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

//                if(current_position==tv_size){
//                    current_position=1;
//                    topVP.setCurrentItem(current_position);
//                }else {
//                    topVP.setCurrentItem(current_position++);
//                }
//
                int numPages = tv_size;
                current_position = (current_position + 1) % numPages;
                topVP.setCurrentItem(current_position,true);
//
//                if(current_position2==tv_size-1){
//                    current_position2=-1;
//                }
//                topVP.setCurrentItem(current_position2+1,true);
                handler.postDelayed(this,3000);
            }
        };

        handler.post(runnable);
    }

    private void bindDataToAdapter(DATA dataItemList) {

        List<HIGHTLIGHTItem> topviewsItemList=dataItemList.getHIGHTLIGHT();


        MyOfferSlider myOfferSlider=new MyOfferSlider(topviewsItemList,context);
        topVP.setClipToPadding(false);
        topVP.setPageMargin(2);
        topVP.setAdapter(myOfferSlider);
        top_tab.setupWithViewPager(topVP);
        tv_size=topviewsItemList.size();
        autoSlider();


        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        GridRecyclerViewAdapter gridRecyclerViewAdapter=new GridRecyclerViewAdapter(dataItemList.getALLPRODUCTS(),context,32);
        recyclerView.setAdapter(gridRecyclerViewAdapter);
        recyclerView.setNestedScrollingEnabled(false);


    }

    @Override
    public void onRefresh() {
        fetchHomePageData();
    }
}
