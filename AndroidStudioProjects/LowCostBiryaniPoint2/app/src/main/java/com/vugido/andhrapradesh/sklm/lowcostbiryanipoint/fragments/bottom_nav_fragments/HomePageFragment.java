package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.foodhub.vugido.activities.ReferEarnPolicy;
import com.foodhub.vugido.adapters.OfferAdapters.MyOfferSlider;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.TOPVIEWSItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.AllProductsActivity;
import com.foodhub.vugido.activities.SearchActivity;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.CategoriesRecyclerViewAdapter;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniDairy;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniDryFruits;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniFoodsRecyclerViewAdapter;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniFruitsRecyclerViewAdapter;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniGroceriesRecyclerViewAdapter;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniNonVegRecyclerViewAdapter;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniTiffins;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MiniVegRecyclerViewAdapter;
import com.foodhub.vugido.adapters.HomeMainRecyclerViewAdapters.MyHomeSlider;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.ALLPRODUCTSItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.CATEGORIESItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.DAIRYPRODUCTSItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.DATA;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.DRYFRUITSItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.FOODSItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.FRUITSItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.GROCERIESItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.HIGHTLIGHTItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.NONVEGItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.Response;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.SNACKSItem;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.VEGETABLESItem;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;

public class HomePageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private TabLayout tabIndicator,top_tab;
    private ImageView WhatsAppOrder, CallOrder;
    private ViewPager viewPager,topVP;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    FloatingActionButton search;
    private View VegtablesLayout,FruitsLayout,GroceriesLayout,NonVegLayout,FoodsLayout,SnacksLayout,DryFruitsLayout,DairyProductsLayout;
    private RecyclerView VegetablesRecyclerView,FruitsRecyclerView,GroceriesRecyclerView,NonVegRecyclerView,FoodsRecyclerView,CategoriesRecyclerView,
    DryFruitsRecycler,TiffinsRecycler,DairyProductsRecycler;
    private Button VegButtonViewAll,FruitsViewAll,GroceriesViewAll,NonVegViewAll,FoodsViewAll,DryFruitsViewAll,DairyProductsViewAll,TiffinsViewAll;
    private FragmentActivity fragmentActivity;


    //slider...
    Timer timer;
    int current_position=0;
    int tv_size=0;
    int current_position2=0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        fragmentActivity=getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_fragment);
        WhatsAppOrder=view.findViewById(R.id.whats_app_order);
        CallOrder=view.findViewById(R.id.call_order);
        search=view.findViewById(R.id.floating_search);

        // all containers layout..
        VegtablesLayout=view.findViewById(R.id.veg_layout);
        FruitsLayout=view.findViewById(R.id.fruits_layout);
        GroceriesLayout=view.findViewById(R.id.groceries_layout);
        NonVegLayout=view.findViewById(R.id.non_veg_layout);
        FoodsLayout=view.findViewById(R.id.food_layout);
        SnacksLayout=view.findViewById(R.id.tiffins);
        DryFruitsLayout=view.findViewById(R.id.dry_fruits);
        DairyProductsLayout=view.findViewById(R.id.dairy_products);


        // binding all recycler views
        CategoriesRecyclerView=view.findViewById(R.id.grid_recycler_view);
        VegetablesRecyclerView=view.findViewById(R.id.veg_recycler_view);
        GroceriesRecyclerView=view.findViewById(R.id.groceries_recycler_view);
        FruitsRecyclerView=view.findViewById(R.id.fruits_recycler_view);
        NonVegRecyclerView=view.findViewById(R.id.non_veg_recycler_view);
        FoodsRecyclerView=view.findViewById(R.id.foods_recycler_view);
        TiffinsRecycler=view.findViewById(R.id.tiffins_recycler_view);
        DryFruitsRecycler=view.findViewById(R.id.dry_fruits_recycler_view);
        DairyProductsRecycler=view.findViewById(R.id.dairy_products_recycler_view);


        // view all button ids..

        VegButtonViewAll=view.findViewById(R.id.veg_view_all);
        FruitsViewAll=view.findViewById(R.id.fruits_view_all);
        GroceriesViewAll=view.findViewById(R.id.groceries_view_all);
        NonVegViewAll=view.findViewById(R.id.non_veg_view_all);
        FoodsViewAll=view.findViewById(R.id.foods_view_all);
        DairyProductsViewAll=view.findViewById(R.id.dairy_products_view_all);
        TiffinsViewAll=view.findViewById(R.id.snacks_view_all);
        DryFruitsViewAll=view.findViewById(R.id.dry_fruits_view_all);

        // setting button view all
        VegButtonViewAll.setOnClickListener(this);
        FruitsViewAll.setOnClickListener(this);
        GroceriesViewAll.setOnClickListener(this);
        NonVegViewAll.setOnClickListener(this);
        FoodsViewAll.setOnClickListener(this);
        DryFruitsViewAll.setOnClickListener(this);
        TiffinsViewAll.setOnClickListener(this);
        DairyProductsViewAll.setOnClickListener(this);

        // view pager
        tabIndicator = view.findViewById(R.id.tab_indicator);
        viewPager =view.findViewById(R.id.home_viewpager);

        top_tab=view.findViewById(R.id.top_tab_indicator);
        topVP=view.findViewById(R.id.offer_viewpager);
        // swipe listener..
        swipeRefreshLayout.setOnRefreshListener(this);
        WhatsAppOrder.setOnClickListener(this);
        CallOrder.setOnClickListener(this);

        search.setOnClickListener(this);
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


        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(context).getUID());

        Call<Response> responseCall= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(context).getBaseLocationURL()).fetchHomePageContent(map);

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
        });



    }

    private void autoSlider(){
        Handler handler=new Handler();

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



    private void autoSliderHightlight(int tv_size){
        Handler handler2=new Handler();

        Runnable runnable2=new Runnable() {
            @Override
            public void run() {

//                if(current_position==tv_size){
//                    current_position=1;
//                    topVP.setCurrentItem(current_position);
//                }else {
//                    topVP.setCurrentItem(current_position++);
//                }

//                int numPages = tv_size;
//                current_position = (current_position + 1) % numPages;
//                viewPager.setCurrentItem(current_position,true);
                if(current_position==tv_size-1){
                    current_position=-1;
                }
                viewPager.setCurrentItem(current_position+1,true);
                handler2.postDelayed(this,3000);
            }
        };

        handler2.post(runnable2);
    }


    private void bindData(DATA myHomeData) {

        List<HIGHTLIGHTItem> hightlightItemList=myHomeData.getHIGHTLIGHT();

        MyHomeSlider myHomeSlider = new MyHomeSlider(hightlightItemList,context);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(10);
        viewPager.setAdapter(myHomeSlider);
        tabIndicator.setupWithViewPager(viewPager);

      //  autoSliderHightlight(hightlightItemList.size());


        List<TOPVIEWSItem> topviewsItemList=myHomeData.getTOPVIEWS();


        MyOfferSlider myOfferSlider=new MyOfferSlider(topviewsItemList,context);
        topVP.setClipToPadding(false);
        topVP.setPageMargin(2);
        topVP.setAdapter(myOfferSlider);
        top_tab.setupWithViewPager(topVP);
        tv_size=topviewsItemList.size();
        autoSlider();



        // CATEGORIES DATA
        List<CATEGORIESItem> myHomeDataCATEGORIES=myHomeData.getCATEGORIES();
        //categories
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        CategoriesRecyclerView.setLayoutManager(linearLayoutManager);
        CategoriesRecyclerView.setAdapter(new CategoriesRecyclerViewAdapter(myHomeDataCATEGORIES,context));





        // items grid
        List<ALLPRODUCTSItem> allproductsItemList = myHomeData.getALLPRODUCTS();


        for(int i=0;i<allproductsItemList.size();i++){

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
                case "dry-fruits":
                    DryFruitsLayout.setVisibility(View.VISIBLE);
                    // dairy
                    List<DRYFRUITSItem> dryfruitsItemList=allproductsItemList.get(i).getDRYFRUITS();
                    DryFruitsRecycler.setLayoutManager(new GridLayoutManager(context,2));
                    DryFruitsRecycler.setAdapter(new MiniDryFruits(dryfruitsItemList,context));
                    DryFruitsRecycler.setNestedScrollingEnabled(false);

                    break;
                case "snacks":
                    SnacksLayout.setVisibility(View.VISIBLE);
                    List<SNACKSItem> snacksItemList=allproductsItemList.get(i).getSNACKS();
                    TiffinsRecycler.setLayoutManager(new GridLayoutManager(context,2));
                    TiffinsRecycler.setAdapter(new MiniTiffins(snacksItemList,context));
                    TiffinsRecycler.setNestedScrollingEnabled(false);

                    break;
                case "dairy-products":
                    DairyProductsLayout.setVisibility(View.VISIBLE);
                    // dairy
                    List<DAIRYPRODUCTSItem> dairyproductsItemList=allproductsItemList.get(i).getDAIRYPRODUCTS();
                    DairyProductsRecycler.setLayoutManager(new GridLayoutManager(context,2));
                    DairyProductsRecycler.setAdapter(new MiniDairy(dairyproductsItemList,context));
                    DairyProductsRecycler.setNestedScrollingEnabled(false);
                    break;
            }

        }










    }

    private void refreshLayouts(){

        /// add more

        VegtablesLayout.setVisibility(View.GONE);
        GroceriesLayout.setVisibility(View.GONE);
        FoodsLayout.setVisibility(View.GONE);
        FruitsLayout.setVisibility(View.GONE);
        NonVegLayout.setVisibility(View.GONE);
        DryFruitsLayout.setVisibility(View.GONE);
        SnacksLayout.setVisibility(View.GONE);
        DairyProductsLayout.setVisibility(View.GONE);

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
        fragmentActivity.startActivityForResult(intent,0);

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
            case R.id.snacks_view_all:
                fetchViewAll(35,"Tiffins");
                break;
            case R.id.dry_fruits_view_all:
                fetchViewAll(33,"Dry-Fruits");
                break;
            case R.id.dairy_products_view_all:
                fetchViewAll(34,"Dairy-Products");
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
            case R.id.floating_search:
                fragmentActivity.startActivityForResult(new Intent(context, SearchActivity.class),0);
                break;

        }
    }
}
