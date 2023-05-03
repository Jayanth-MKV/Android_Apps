package com.vugido.info.homeservicesadmin.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vugido.info.homeservicesadmin.R;
import com.vugido.info.homeservicesadmin.adapters.homepage.HomePageMainAdapter;
import com.vugido.info.homeservicesadmin.app_config_variables.MyPreferences;
import com.vugido.info.homeservicesadmin.dialogs.MyStatusDialog;
import com.vugido.info.homeservicesadmin.models.homepage.DATA;
import com.vugido.info.homeservicesadmin.models.homepage.HomePageMainModel;
import com.vugido.info.homeservicesadmin.models.homepage.Response;
import com.vugido.info.homeservicesadmin.models.homepage.SERVICECATEGORIESItem;
import com.vugido.info.homeservicesadmin.models.homepage.SERVICEPRODUCTSItem;
import com.vugido.info.homeservicesadmin.network.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int ORDERS_CHECKING = 123;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter;
    MyStatusDialog myStatusDialog;

    ImageView  notifications;
    int current_position = 0;
    private RecyclerView recyclerView;


    int v;
    FragmentActivity a;
    int tv_size = 0;

    private ViewPager topVP;
    private TabLayout top_tab;
    private LinearLayoutManager layoutManager;


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if(requestCode==SERVICE_CODE&&resultCode==SUCCESS_BOOKED) {
//            if (myStatusDialog==null)
//                myStatusDialog=new MyStatusDialog();
//
//            Bundle bundle=new Bundle();
//
////                bundle.putString("MSG","Wrong OTP...");
////
////                bundle.putBoolean("STATUS",false);
////                myStatusDialog.setArguments(bundle);
////                myStatusDialog.show(getSupportFragmentManager(),"S");
//
//
//                bundle.putString("MSG","Service Booked Successfully...");
//
//                bundle.putBoolean("STATUS",true);
//                myStatusDialog.setArguments(bundle);
//
//                myStatusDialog.show(a.getSupportFragmentManager(),"S");
//
//
//
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        a=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);
        // view pager
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.home_recyclerView);

        assert getArguments() != null;
        v=getArguments().getInt("V");

//        top_tab = view.findViewById(R.id.top_tab_indicator);
//        topVP = view.findViewById(R.id.offer_viewpager);
        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchHomePageData);

//        notifications=view.findViewById(R.id.imageView);
//
//        notifications.setOnClickListener(v -> {
//
//            Intent intent=new Intent(context, Services.class);
//            startActivityForResult(intent,ORDERS_CHECKING);
//
//        });

        return view;
    }



    private void fetchHomePageData() {



        Map<String,Object> map=new HashMap<>();
        map.put("UID","1");

        Call<Response> responseCall= RetrofitBuilder.getInstance().getRetrofit().fetchHomePage(map);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

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

                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void autoSlider () {
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                int numPages = tv_size;
                current_position = (current_position + 1) % numPages;
                topVP.setCurrentItem(current_position, true);

                handler.postDelayed(this, 3000);
            }
        };

        handler.post(runnable);
    }

    private void bindData(DATA data) {

//        List<SLIDERItem> slidersModelList = data.getSLIDER();
//
//
//        MyOfferSlider myOfferSlider = new MyOfferSlider(slidersModelList, context);
//        topVP.setClipToPadding(false);
//        topVP.setPageMargin(2);
//
//        topVP.setAdapter(myOfferSlider);
//        top_tab.setupWithViewPager(topVP);
//        tv_size = slidersModelList.size();
//        autoSlider();


        homePageMainModelList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        // segment the total products


        List<SERVICECATEGORIESItem> serviceCategoroesModelList=data.getSERVICECATEGORIES();



        List<SERVICEPRODUCTSItem> serviceCategoryProductsList=data.getSERVICEPRODUCTS();



        for (SERVICECATEGORIESItem dataItem : serviceCategoroesModelList) {


            homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(serviceCategoroesModelList, HomePageMainModel.SQUARE_MEDIUM_VIEW, getCategoryType(dataItem,serviceCategoryProductsList), dataItem.getTITLE(), dataItem.getID(), "", "#8E2DE2"));



        }

        homePageMainAdapter = new HomePageMainAdapter(homePageMainModelList, context,v);
        recyclerView.setAdapter(homePageMainAdapter);
        recyclerView.setNestedScrollingEnabled(false);




    }

    private List<SERVICEPRODUCTSItem> getCategoryType (SERVICECATEGORIESItem dataItem, List < SERVICEPRODUCTSItem > productsmenuItemList){

        List<SERVICEPRODUCTSItem> productsmenuItemList1 = new ArrayList<>();

        for (SERVICEPRODUCTSItem productsmenuItem : productsmenuItemList) {

            if (productsmenuItem.getSID() == dataItem.getID()) {
                productsmenuItemList1.add(productsmenuItem);
            }
        }

        return productsmenuItemList1;


    }


    @Override
    public void onRefresh() {

        fetchHomePageData();
    }
}
