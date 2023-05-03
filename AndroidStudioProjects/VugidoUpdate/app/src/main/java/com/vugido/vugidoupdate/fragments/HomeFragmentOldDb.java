package com.vugido.StudentTime.fragments.bottom_nav_fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.activities.AllProductsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragmentOldDb extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private TabLayout tabIndicator;
    private ImageView WhatsAppOrder, CallOrder;
    private ViewPager viewPager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private View VegtablesLayout,FruitsLayout,GroceriesLayout,NonVegLayout,FoodsLayout;
    private RecyclerView VegetablesRecyclerView,FruitsRecyclerView,GroceriesRecyclerView,NonVegRecyclerView,FoodsRecyclerView,CategoriesRecyclerView;
    private Button VegButtonViewAll,FruitsViewAll,GroceriesViewAll,NonVegViewAll,FoodsViewAll;
   // private ProgressFragment progressFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_olddb, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);
        WhatsAppOrder=view.findViewById(R.id.whats_app_order);
        CallOrder=view.findViewById(R.id.call_order);


        // all containers layout..
        VegtablesLayout=view.findViewById(R.id.veg_layout);
        FruitsLayout=view.findViewById(R.id.fruits_layout);
        GroceriesLayout=view.findViewById(R.id.groceries_layout);
        NonVegLayout=view.findViewById(R.id.non_veg_layout);
        FoodsLayout=view.findViewById(R.id.food_layout);


        // binding all recycler views
        CategoriesRecyclerView=view.findViewById(R.id.grid_recycler_view);
        VegetablesRecyclerView=view.findViewById(R.id.veg_recycler_view);
        GroceriesRecyclerView=view.findViewById(R.id.groceries_recycler_view);
        FruitsRecyclerView=view.findViewById(R.id.fruits_recycler_view);
        NonVegRecyclerView=view.findViewById(R.id.non_veg_recycler_view);
        FoodsRecyclerView=view.findViewById(R.id.foods_recycler_view);

        // view all button ids..

        VegButtonViewAll=view.findViewById(R.id.veg_view_all);
        FruitsViewAll=view.findViewById(R.id.fruits_view_all);
        GroceriesViewAll=view.findViewById(R.id.groceries_view_all);
        NonVegViewAll=view.findViewById(R.id.non_veg_view_all);
        FoodsViewAll=view.findViewById(R.id.foods_view_all);

        // setting button view all
        VegButtonViewAll.setOnClickListener(this);
        FruitsViewAll.setOnClickListener(this);
        GroceriesViewAll.setOnClickListener(this);
        NonVegViewAll.setOnClickListener(this);
        FoodsViewAll.setOnClickListener(this);

        // view pager
        tabIndicator = view.findViewById(R.id.tab_indicator);
        viewPager =view.findViewById(R.id.home_viewpager);

        // swipe listener..
        swipeRefreshLayout.setOnRefreshListener(this);
        WhatsAppOrder.setOnClickListener(this);
        CallOrder.setOnClickListener(this);

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



        return  view;

    }

    private void fetchHomePageData() {



/*
        Call<Response> responseCall= RetrofitBuilder.getInstance().getRetrofit().fetchHomePageContent();

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        // ok data received

                        DATA myHomeData=response.body().getDATA();
                        bindData(myHomeData);


                    }else {

                        // not ok
                    }

                }else {

                    // no data
                }
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                swipeRefreshLayout.setRefreshing(false);

            }
        });*/



    }

   /* private void bindData(DATA myHomeData) {

        // CATEGORIES DATA
        List<CATEGORIESItem> myHomeDataCATEGORIES=myHomeData.getCATEGORIES();
        //categories
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        CategoriesRecyclerView.setLayoutManager(linearLayoutManager);
        CategoriesRecyclerView.setAdapter(new CategoriesRecyclerViewAdapter(myHomeDataCATEGORIES,context));*/


        // slider something healthy
        /*final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Easy Payment","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));
        mList.add(new ScreenItem("Fresh Food","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));
        mList.add(new ScreenItem("Fast Delivery","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));
        mList.add(new ScreenItem("Easy Payment","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));
        mList.add(new ScreenItem("Fresh Food","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));
        mList.add(new ScreenItem("Fast Delivery","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit"));

        // setup viewpager

        MyHomeSlider myHomeSlider = new MyHomeSlider(mList,context);
        viewPager.setAdapter(myHomeSlider);
        viewPager.setPageTransformer(  false,new DepthPageTransformer());
        tabIndicator.setupWithViewPager(viewPager);*/






        // items grid
      //  List<ALLPRODUCTSItem> allproductsItemList = myHomeData.getALLPRODUCTS();


       /* for(int i=0;i<allproductsItemList.size();i++){

            switch (myHomeDataCATEGORIES.get(i).getTITLE().toLowerCase()){
                case  "vegetables":
                    VegtablesLayout.setVisibility(View.VISIBLE);
                    // vegetables
                    List<VEGETABLESItem> vegetablesItemList= allproductsItemList.get(i).getVEGETABLES();
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
                    VegetablesRecyclerView.setLayoutManager(gridLayoutManager);
                    MiniVegRecyclerViewAdapter miniVegRecyclerViewAdapter=new MiniVegRecyclerViewAdapter(vegetablesItemList,context);
                    VegetablesRecyclerView.setAdapter(miniVegRecyclerViewAdapter);
                    VegetablesRecyclerView.setNestedScrollingEnabled(false);
                    break;
                case "fruits":
                    FruitsLayout.setVisibility(View.VISIBLE);
                    // fruits
                    List<FRUITSItem> fruitsItemList = allproductsItemList.get(i).getFRUITS();
                    FruitsRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
                    FruitsRecyclerView.setAdapter(new MiniFruitsRecyclerViewAdapter(fruitsItemList,context));
                    FruitsRecyclerView.setNestedScrollingEnabled(false);
                    break;
                case "non veg":
                    NonVegLayout.setVisibility(View.VISIBLE);
                    // Non veg
                    List<NONVEGItem> nonvegItemList= allproductsItemList.get(i).getNONVEG();
                    NonVegRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
                    NonVegRecyclerView.setAdapter(new MiniNonVegRecyclerViewAdapter(nonvegItemList,context));
                    NonVegRecyclerView.setNestedScrollingEnabled(false);
                    break;
                case "groceries":
                    GroceriesLayout.setVisibility(View.VISIBLE);

                    // groceries
                    List<GROCERIESItem> groceriesItemList= allproductsItemList.get(i).getGROCERIES();
                    GroceriesRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
                    GroceriesRecyclerView.setAdapter(new MiniGroceriesRecyclerViewAdapter(groceriesItemList,context));
                    GroceriesRecyclerView.setNestedScrollingEnabled(false);
                    break;
                case "foods":
                    FoodsLayout.setVisibility(View.VISIBLE);
                    // Foods
                    List<FOODSItem> foodsItemList= allproductsItemList.get(i).getFOODS();
                    FoodsRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
                    FoodsRecyclerView.setAdapter(new MiniFoodsRecyclerViewAdapter(foodsItemList,context));
                    FoodsRecyclerView.setNestedScrollingEnabled(false);
                    break;
            }

        }*/










   // }

    private void refreshLayouts(){

        /// add more

        VegtablesLayout.setVisibility(View.GONE);
        GroceriesLayout.setVisibility(View.GONE);
        FoodsLayout.setVisibility(View.GONE);
        FruitsLayout.setVisibility(View.GONE);
        NonVegLayout.setVisibility(View.GONE);

    }
    @Override
    public void onRefresh() {

        refreshLayouts();
        fetchHomePageData();

    }

    private void fetchViewAll(int cid,String title){

        Intent intent;
        intent=new Intent(context, AllProductsActivity.class);
        intent.putExtra("CID",cid);
        intent.putExtra("TITLE",title);
        context.startActivity(intent);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.veg_view_all:
                fetchViewAll(28,"Vegetables");
                break;
            case R.id.fruits_view_all:
                fetchViewAll(29,"Fruits");
                break;
            case R.id.groceries_view_all:
                fetchViewAll(31,"Groceries");
                break;
            case R.id.non_veg_view_all:
                fetchViewAll(30,"Non-veg");
                break;
            case R.id.foods_view_all:
                fetchViewAll(32,"Foods");
                break;
            case R.id.whats_app_order:

                // start whatsApp


                try {
                    String text = "Dear Vugido Team :";// Replace with your message.

                    String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.call_order:

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:7447337566"));
                startActivity(callIntent);

                break;

        }
    }
}