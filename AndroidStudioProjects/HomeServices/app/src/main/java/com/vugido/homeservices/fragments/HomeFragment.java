package com.vugido.homeservices.fragments;

import android.app.Activity;
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
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vugido.homeservices.R;
import com.vugido.homeservices.activities.SearchActivity;
import com.vugido.homeservices.activities.Services;
import com.vugido.homeservices.adapters.homepage.HomePageMainAdapter;
import com.vugido.homeservices.adapters.homepage.MyOfferSlider;
import com.vugido.homeservices.app.MyPreferences;
import com.vugido.homeservices.dialogs.MyStatusDialog;
import com.vugido.homeservices.models.homepage.DATA;
import com.vugido.homeservices.models.homepage.HomePageMainModel;
import com.vugido.homeservices.models.homepage.Response;
import com.vugido.homeservices.models.homepage.SERVICECATEGORIESItem;
import com.vugido.homeservices.models.homepage.SERVICEPRODUCTSItem;
import com.vugido.homeservices.models.homepage.SLIDERItem;
import com.vugido.homeservices.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.homeservices.activities.DateAndTimePicker.SUCCESS_BOOKED;
import static com.vugido.homeservices.adapters.homepage.SquareMediumRecyclerViewAdapter.SERVICE_CODE;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int ORDERS_CHECKING = 123;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter;
    MyStatusDialog myStatusDialog;

    ImageView  search;
    int current_position = 0;
    private RecyclerView recyclerView;


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


        top_tab = view.findViewById(R.id.top_tab_indicator);
        topVP = view.findViewById(R.id.offer_viewpager);
        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchHomePageData);

        search=view.findViewById(R.id.imageView);

        search.setOnClickListener(v -> {

            Intent intent=new Intent(context, SearchActivity.class);
            intent.putExtra("V",1);
            startActivityForResult(intent,ORDERS_CHECKING);

        });

        return view;
    }



    private void fetchHomePageData() {



        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(context).getUID());

        Call<Response> responseCall= RetrofitBuilder.getInstance().getRetrofit().fetchHomePage(map);
        responseCall.enqueue(new Callback<Response>() {
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

        List<SLIDERItem> slidersModelList = data.getSLIDER();


        MyOfferSlider myOfferSlider = new MyOfferSlider(slidersModelList, context);
        topVP.setClipToPadding(false);
        topVP.setPageMargin(2);

        topVP.setAdapter(myOfferSlider);
        top_tab.setupWithViewPager(topVP);
        tv_size = slidersModelList.size();
        autoSlider();


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

        homePageMainAdapter = new HomePageMainAdapter(homePageMainModelList, context);
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
