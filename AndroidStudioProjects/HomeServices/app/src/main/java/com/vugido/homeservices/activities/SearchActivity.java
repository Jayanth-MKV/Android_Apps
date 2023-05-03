package com.vugido.homeservices.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.homeservices.R;
import com.vugido.homeservices.adapters.homepage.HomePageMainAdapter;
import com.vugido.homeservices.adapters.homepage.MyOfferSlider;
import com.vugido.homeservices.adapters.homepage.SquareMediumRecyclerViewAdapter;
import com.vugido.homeservices.adapters.nearpage.NearMediumRecyclerViewAdapter;
import com.vugido.homeservices.adapters.nearpage.NearPageMainAdapter;
import com.vugido.homeservices.app.MyPreferences;
import com.vugido.homeservices.models.homepage.DATA;
import com.vugido.homeservices.models.homepage.HomePageMainModel;
import com.vugido.homeservices.models.homepage.Response;
import com.vugido.homeservices.models.homepage.SERVICECATEGORIESItem;
import com.vugido.homeservices.models.homepage.SERVICEPRODUCTSItem;
import com.vugido.homeservices.models.homepage.SLIDERItem;
import com.vugido.homeservices.models.near_page.NEARSERVICECATEGORIESItem;
import com.vugido.homeservices.models.near_page.NEARSERVICESItem;
import com.vugido.homeservices.models.near_page.NearPageMainModel;
import com.vugido.homeservices.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private Toolbar toolbar;
    private boolean SF=false;
    private String pendingString="";
    private boolean f=false;

    private RecyclerView recyclerView;


    private int k=0;

    SquareMediumRecyclerViewAdapter squareMediumRecyclerViewAdapter=null;
    NearMediumRecyclerViewAdapter nearMediumRecyclerViewAdapter=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar=findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Search");

        recyclerView=findViewById(R.id.search_recycler_view);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        k=getIntent().getIntExtra("V",0);

        if (k==1)
            fetchHomePageData();
        else
            fetchNearPageData();

    }


    private void fetchNearPageData() {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        Call<com.vugido.homeservices.models.near_page.Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchNearPage(map);

        call.enqueue(new Callback<com.vugido.homeservices.models.near_page.Response>() {
            @Override
            public void onResponse(Call<com.vugido.homeservices.models.near_page.Response> call, retrofit2.Response<com.vugido.homeservices.models.near_page.Response> response) {


               // swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful())
                {

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        bindData1(response.body().getDATA());
                    }else{

                        //no daa..
                    }
                }
            }

            @Override
            public void onFailure(Call<com.vugido.homeservices.models.near_page.Response> call, Throwable t) {

            }
        });



    }

    private void bindData1(com.vugido.homeservices.models.near_page.DATA data) {


//        homePageMainModelList = new ArrayList<>();
//        layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);


        // segment the total products


        List<NEARSERVICECATEGORIESItem> serviceCategoroesModelList=data.getNEARSERVICECATEGORIES();



        List<NEARSERVICESItem> serviceCategoryProductsList=data.getNEARSERVICES();



        for (NEARSERVICECATEGORIESItem dataItem : serviceCategoroesModelList) {


           // homePageMainModelList.add(NearPageMainModel.createSquareMediumViewModelList(serviceCategoroesModelList, NearPageMainModel.SQUARE_MEDIUM_VIEW, getCategoryType(dataItem,serviceCategoryProductsList), dataItem.getTITLE(), dataItem.getID(), "", "#8E2DE2"));



        }

        nearMediumRecyclerViewAdapter=new NearMediumRecyclerViewAdapter(serviceCategoryProductsList,this,serviceCategoroesModelList);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(nearMediumRecyclerViewAdapter);

//        homePageMainAdapter = new NearPageMainAdapter(homePageMainModelList, context);
//        recyclerView.setAdapter(homePageMainAdapter);
//        recyclerView.setNestedScrollingEnabled(false);




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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null && searchManager!=null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static void hideKeyboard(Activity activity) {

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {

      //  Toast.makeText(getContext(),query,Toast.LENGTH_SHORT).show();
        //send the submit request
        hideKeyboard(this);
        //fetchSuggestions(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (squareMediumRecyclerViewAdapter!=null)
            squareMediumRecyclerViewAdapter.getFilter().filter(newText);
        return true;
    }
//
//    private void fetchSuggestions(final String newText) {
//
//        SF=true;
//        Map<String, Object> map=new HashMap<>();
//        map.put("UID",new MyPreferences(this).getUID());
//        map.put("SEARCH_ON",newText);
//        Progress.setVisibility(View.VISIBLE);
//
//
//
//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).searchSuggestionsUrl(map);
//
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//              Progress.setVisibility(View.GONE);
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(!response.body().isError()){
//                        //data got
//                        searchRecyclerView.setVisibility(View.VISIBLE);
//                        searchSuggestionsList=response.body().getDATA();
//                        bindSuggestionsData(newText);
//                    }else {
//                        SF=false;
//                        //no data..
//                        searchRecyclerView.setVisibility(View.GONE);
//                       // fetchUsersRecentSearches();
//                        Toast.makeText(getApplicationContext(),"No Results", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    // error
//                    SF=false;
//                    if(f){
//                        fetchSuggestions(pendingString);
//                        f=false;
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//               Progress.setVisibility(View.GONE);
//               SF=false;
//                if(f){
//                    fetchSuggestions(pendingString);
//                    f=false;
//                }
//            }
//        });
//
//    }
//
//    private void bindSuggestionsData(String key) {
//
//        if(searchAdapter==null){
//            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            searchRecyclerView.setLayoutManager(linearLayoutManager);
//            //searchRecyclerView.addItemDecoration(new Space(1,5,false,0));
//            searchAdapter=new SearchAdapter(searchSuggestionsList,this,key);
//            searchRecyclerView.setAdapter(searchAdapter);
//
//
//        }else {
//            searchAdapter.setItems(searchSuggestionsList);
//            searchAdapter.notifyDataSetChanged();
//        }
//
//        SF=false;
//
//        if(f){
//            fetchSuggestions(pendingString);
//            f=false;
//        }
//
//    }
//
//    @Override
//    public void searchID_Clicked(int SID,int CID) {
//
//
//        if(CID==1){
//
//            // single product
//            fetchProductsData(SID);
//
//        }else {
//            // for multiple products..
////            Intent intent=new Intent(this,SearchOnClickResultDisplayActivity.class);
////            intent.putExtra("SID",SID);
////            startActivity(intent);
//        }
//
//
//    }
//
//
//    private void fetchProductsData(int sid){
//
//        Progress.setVisibility(View.VISIBLE);
//        Map<String, Object> map=new HashMap<>();
//        map.put("UID",new MyPreferences(this).getUID());
//        map.put("SID", String.valueOf(sid));
//
//        Call<com.foodhub.vugido.models.search.search_submit.Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getSearchOnClickResults(map);
//
//        call.enqueue(new Callback<com.foodhub.vugido.models.search.search_submit.Response>() {
//            @Override
//            public void onResponse(@NonNull Call<com.foodhub.vugido.models.search.search_submit.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.search.search_submit.Response> response) {
//
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(response.body().isSTATUS()){
//                        // ok data got
//
//                        DATA data= response.body().getDATA();
//                        callProductDetailsActivity(data.getDATA().get(0));
//                    }else {
//                        // no data
//                        Toast.makeText(getApplicationContext(),"No Data", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<com.foodhub.vugido.models.search.search_submit.Response> call, @NonNull Throwable t) {
//
//            }
//        });
//
//
//    }
//
//
//    private void callProductDetailsActivity(com.foodhub.vugido.models.search.search_submit.DATAItem ProductItem) {
//        Intent intent=new Intent(this, ProductDetailsActivity.class);
//
//        startActivity(intent);
//    }
//


    private void fetchHomePageData() {



        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());

        Call<Response> responseCall= RetrofitBuilder.getInstance().getRetrofit().fetchHomePage(map);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                //swipeRefreshLayout.setRefreshing(false);

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

              //  swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void bindData(DATA data) {

        List<SLIDERItem> slidersModelList = data.getSLIDER();


//        MyOfferSlider myOfferSlider = new MyOfferSlider(slidersModelList, context);
//        topVP.setClipToPadding(false);
//        topVP.setPageMargin(2);
//
//        topVP.setAdapter(myOfferSlider);
//        top_tab.setupWithViewPager(topVP);
//        tv_size = slidersModelList.size();
//        autoSlider();


        //homePageMainModelList = new ArrayList<>();
//        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);


        // segment the total products


        List<SERVICECATEGORIESItem> serviceCategoroesModelList=data.getSERVICECATEGORIES();



        List<SERVICEPRODUCTSItem> serviceCategoryProductsList=data.getSERVICEPRODUCTS();



//        for (SERVICECATEGORIESItem dataItem : serviceCategoroesModelList) {
//
//
//           // homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(serviceCategoroesModelList, HomePageMainModel.SQUARE_MEDIUM_VIEW, getCategoryType(dataItem,serviceCategoryProductsList), dataItem.getTITLE(), dataItem.getID(), "", "#8E2DE2"));
//
//
//
//        }

//        homePageMainAdapter = new HomePageMainAdapter(homePageMainModelList, this);
//        recyclerView.setAdapter(homePageMainAdapter);
//        recyclerView.setNestedScrollingEnabled(false);

        squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapter(serviceCategoryProductsList,this,serviceCategoroesModelList);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(squareMediumRecyclerViewAdapter);



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



}
