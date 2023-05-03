package com.thetimeofstudent.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.thetimeofstudent.R;
import com.thetimeofstudent.adapters.homepage.CategoriesRecyclerViewAdapter;
import com.thetimeofstudent.adapters.homepage.HomePageMainAdapter;
import com.thetimeofstudent.fragments.bottom_sheet.HomeOptionsBottomSheet;
import com.thetimeofstudent.models.MAIN_MODEL.CATEGORIESItem;
import com.thetimeofstudent.models.MAIN_MODEL.HOMEPRODUCTSItem;
import com.thetimeofstudent.models.MAIN_MODEL.HomePageMainModel;
import com.thetimeofstudent.models.MAIN_MODEL.SECTIONSItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Toolbar.OnMenuItemClickListener {
    private LinearLayoutManager layoutManager;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter=null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomAppBar bottomAppBar;
    private RecyclerView recyclerView, CategoriesRecyclerView;

    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.activity_toolbar);
        bottomAppBar = findViewById(R.id.bottom_app_bar);
        bottomAppBar.setNavigationOnClickListener(v -> {

            HomeOptionsBottomSheet homeOptionsBottomSheet=new HomeOptionsBottomSheet();
            homeOptionsBottomSheet.show(getSupportFragmentManager(),"HBSM");
            // Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        });
        CategoriesRecyclerView=findViewById(R.id.grid_recycler_view);
        bottomAppBar.setOnMenuItemClickListener(this);
        recyclerView=findViewById(R.id.home_recyclerView);
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
           // startActivityForResult(new Intent(MainActivity.this,SearchActivity.class),1);
        });


    setSupportActionBar(toolbar);
    swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
    android.R.color.holo_green_dark,
    android.R.color.holo_orange_dark,
    android.R.color.black);
    // called first time.. with out pulling..
    // make sure network connection before calling..
        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        //fetchMenu();
        swipeRefreshLayout.post(this::fetchHomePageData);
}

    @Override
    public void onRefresh() {

        fetchHomePageData();
    }

    private void fetchHomePageData() {
        swipeRefreshLayout.setRefreshing(false);

        // CATEGORIES DATA
        List<CATEGORIESItem> myHomeDataCATEGORIES=new ArrayList<>();
        myHomeDataCATEGORIES.add(new CATEGORIESItem(R.drawable.ml1,"Machine Learning",1));
        myHomeDataCATEGORIES.add(new CATEGORIESItem(R.drawable.ml2,"Machine Learning",1));
        myHomeDataCATEGORIES.add(new CATEGORIESItem(R.drawable.ml3,"Machine Learning",1));
        myHomeDataCATEGORIES.add(new CATEGORIESItem(R.drawable.ml1,"Machine Learning",1));


        //categories
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        CategoriesRecyclerView.setLayoutManager(linearLayoutManager);
        CategoriesRecyclerView.setAdapter(new CategoriesRecyclerViewAdapter(myHomeDataCATEGORIES,this));


        homePageMainModelList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);



        // segment the total products

        List<SECTIONSItem> categoriesItemList=new ArrayList<>();

        categoriesItemList.add(new SECTIONSItem(R.drawable.ml1,"Book 1",1));
        categoriesItemList.add(new SECTIONSItem(R.drawable.ml2,"Book 2",2));
        categoriesItemList.add(new SECTIONSItem(R.drawable.ml3,"Book 3",3));
        categoriesItemList.add(new SECTIONSItem(R.drawable.ml2,"Book 4",4));
        categoriesItemList.add(new SECTIONSItem(R.drawable.dl1,"Book 5",5));
//        categoriesItemList.add(new SECTIONSItem(R.drawable.l1,"Book 6",6));
//        categoriesItemList.add(new SECTIONSItem(R.drawable.l1,"Book 7",7));
//        categoriesItemList.add(new SECTIONSItem(R.drawable.l1,"Book 8",8));


        List<HOMEPRODUCTSItem> productsmenuItemList=new ArrayList<>();

        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dm1,"des 1","Book One",1,1));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dm2,"des 1","Book One",1,1));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dm3,"des 1","Book One",1,1));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dl1,"des 1","Book One",1,1));


        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.j1,"des 1","Book One",1,2));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.j2,"des 1","Book One",1,2));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.j3,"des 1","Book One",1,2));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.ml2,"des 1","Book One",1,2));


        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dl1,"des 1","Book One",1,3));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dm3,"des 1","Book One",1,3));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.ml2,"des 1","Book One",1,3));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.j3,"des 1","Book One",1,3));

        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.ml3,"des 1","Book One",1,4));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.ml2,"des 1","Book One",1,4));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.ml1,"des 1","Book One",1,4));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dl1,"des 1","Book One",1,4));

        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dm3,"des 1","Book One",1,5));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.dm1,"des 1","Book One",1,5));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.j3,"des 1","Book One",1,5));
        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.j1,"des 1","Book One",1,5));





        for(SECTIONSItem dataItem:categoriesItemList){

            homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(categoriesItemList,HomePageMainModel.SQUARE_MEDIUM_VIEW,getCategoryType(dataItem,productsmenuItemList),dataItem.getCNAME(),dataItem.getSID(),"","#8E2DE2"));

        }
        homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,this);
        recyclerView.setAdapter(homePageMainAdapter);
        recyclerView.setNestedScrollingEnabled(false);


    }

    private List<HOMEPRODUCTSItem> getCategoryType(SECTIONSItem dataItem, List<HOMEPRODUCTSItem> productsmenuItemList) {

        List<HOMEPRODUCTSItem> productsmenuItemList1=new ArrayList<>();

        for(HOMEPRODUCTSItem productsmenuItem:productsmenuItemList){

            if(productsmenuItem.getSID()==dataItem.getSID()){
                productsmenuItemList1.add(productsmenuItem);
            }
        }

        return productsmenuItemList1;




    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}