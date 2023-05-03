package com.vugido.ap.sklm.home_interiors.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.vugido.ap.sklm.home_interiors.R;
import com.vugido.ap.sklm.home_interiors.Retrofit.RetrofitBuilder;
import com.vugido.ap.sklm.home_interiors.adapters.homepage.HomePageMainAdapter;
import com.vugido.ap.sklm.home_interiors.adapters.homepage.MyOfferSlider;
import com.vugido.ap.sklm.home_interiors.adapters.homepage.SquareMediumRecyclerViewAdapter;
import com.vugido.ap.sklm.home_interiors.app_config.ConfigVariables;
import com.vugido.ap.sklm.home_interiors.app_config.MyPreferences;
import com.vugido.ap.sklm.home_interiors.fragments.bottom_sheet.HomeOptionsBottomSheet;
import com.vugido.ap.sklm.home_interiors.models.HomePageModels.ServiceCategoroesModel;
import com.vugido.ap.sklm.home_interiors.models.HomePageModels.ServiceCategoryProducts;
import com.vugido.ap.sklm.home_interiors.models.HomePageModels.SlidersModel;
import com.vugido.ap.sklm.home_interiors.models.MAIN_MODEL.HIGHTLIGHTItem;
import com.vugido.ap.sklm.home_interiors.models.MAIN_MODEL.HomePageMainModel;
import com.vugido.ap.sklm.home_interiors.models.MAIN_MODEL.PRODUCTSMENUItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Toolbar.OnMenuItemClickListener {
    private LinearLayoutManager layoutManager;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomAppBar bottomAppBar;
    int current_position = 0;
    int tv_size = 0;
    private ViewPager topVP;
    private TabLayout top_tab;
    private RecyclerView recyclerView;

    private FloatingActionButton floatingActionButton;
    ImageView imageView;
    View eats;
    private MenuItem Cart;

    private TextView CartCount;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.activity_toolbar);
        bottomAppBar = findViewById(R.id.bottom_app_bar);

        bottomAppBar.setNavigationOnClickListener(v -> {

            HomeOptionsBottomSheet homeOptionsBottomSheet = new HomeOptionsBottomSheet();
            homeOptionsBottomSheet.show(getSupportFragmentManager(), "HBSM");
            // Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        });
        bottomAppBar.setOnMenuItemClickListener(this);
        recyclerView = findViewById(R.id.home_recyclerView);
        imageView = findViewById(R.id.image);
        floatingActionButton = findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent(MainActivity.this,SearchActivity.class),1);
//            }
//        });
        top_tab = findViewById(R.id.top_tab_indicator);
        topVP = findViewById(R.id.offer_viewpager);
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
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {


                // get the data here
                //fetchHomePageData();
                //fetchMenu();
                bindDataToAdapter();


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_cart:
                // startActivity(new Intent(MainActivity.this,CartProductsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        Cart = menu.findItem(R.id.my_cart);
        final MenuItem cart = menu.findItem(R.id.my_cart);
        eats = cart.getActionView();
        CartCount = eats.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(), CartCount);
        eats.setOnClickListener(view1 -> onOptionsItemSelected(cart));
        return super.onCreateOptionsMenu(menu);
    }

    public void toggleFabMode(View view) {
        //check the fab alignment mode and toggle accordingly
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }
    }

    @Override
    public void onRefresh() {

        // fetchHomePageData();
        //fetchMenu();
        bindDataToAdapter();
    }



    private void bindDataToAdapter() {

        swipeRefreshLayout.setRefreshing(false);

        List<SlidersModel> slidersModelList = new ArrayList<>();
        slidersModelList.add(new SlidersModel(R.drawable.es,"1",1));
        slidersModelList.add(new SlidersModel(R.drawable.repairs,"2",2));
        slidersModelList.add(new SlidersModel(R.drawable.mobile_repairs_slider,"3",3));



        MyOfferSlider myOfferSlider = new MyOfferSlider(slidersModelList, this);
        topVP.setClipToPadding(false);
        topVP.setPageMargin(2);

        topVP.setAdapter(myOfferSlider);
        top_tab.setupWithViewPager(topVP);
        tv_size = slidersModelList.size();
        autoSlider();


        setMenuData();


    }

    private void setMenuData() {

        Log.e("ok", "3");


        homePageMainModelList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        // segment the total products


        List<ServiceCategoroesModel> serviceCategoroesModelList=new ArrayList<>();
        serviceCategoroesModelList.add(new ServiceCategoroesModel(1,"Electrical Works"));
        serviceCategoroesModelList.add(new ServiceCategoroesModel(2,"Electric Repairs "));
        serviceCategoroesModelList.add(new ServiceCategoroesModel(3,"Home Shifting"));
        serviceCategoroesModelList.add(new ServiceCategoroesModel(4,"Mechanic Works"));


        List<ServiceCategoryProducts> serviceCategoryProductsList=new ArrayList<>();
        serviceCategoryProductsList.add(new ServiceCategoryProducts(1,"AC Repairs",R.drawable.acr,2,""));
        serviceCategoryProductsList.add(new ServiceCategoryProducts(2,"Car",R.drawable.carm,4,""));
        serviceCategoryProductsList.add(new ServiceCategoryProducts(3,"Bike",R.drawable.motorm,4,""))               ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(4,"Computer",R.drawable.computerrepair,2,""))           ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(5,"Laptop",R.drawable.laptop_repair,2,""))             ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(6,"Mobile Phone",R.drawable.mobile_repair,2,""))       ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(7,"Refrigerator",R.drawable.ref_repair,2,""))       ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(8,"Television",R.drawable.tv_repair,2,""))         ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(9,"Washing Machine",R.drawable.washing_repair,2,""))    ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(10,"Water Purifier",R.drawable.water_pur_rep,2,""))    ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(11,"CCTV",R.drawable.cctv_service,1,""))              ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(12,"Gas Stove",R.drawable.gas_stove_rep,1,""))         ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(13,"Carpenter",R.drawable.carpenter_service,3,""))         ;
        serviceCategoryProductsList.add(new ServiceCategoryProducts(14,"Taxi",R.drawable.taxi_service,4,""));
        serviceCategoryProductsList.add(new ServiceCategoryProducts(15,"Painter",R.drawable.painter,3,""));
        serviceCategoryProductsList.add(new ServiceCategoryProducts(16,"House Shifting",R.drawable.house_shifting,3,""));





        for (ServiceCategoroesModel dataItem : serviceCategoroesModelList) {


            homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(serviceCategoroesModelList, HomePageMainModel.SQUARE_MEDIUM_VIEW, getCategoryType(dataItem,serviceCategoryProductsList), dataItem.getTitle(), dataItem.getId(), "", "#8E2DE2"));

            homePageMainAdapter = new HomePageMainAdapter(homePageMainModelList, this);
            recyclerView.setAdapter(homePageMainAdapter);
            recyclerView.setNestedScrollingEnabled(false);


        }
    }

        private List<ServiceCategoryProducts> getCategoryType (ServiceCategoroesModel dataItem, List < ServiceCategoryProducts > productsmenuItemList){

            List<ServiceCategoryProducts> productsmenuItemList1 = new ArrayList<>();

            for (ServiceCategoryProducts productsmenuItem : productsmenuItemList) {

                if (productsmenuItem.getCid() == dataItem.getId()) {
                    productsmenuItemList1.add(productsmenuItem);
                }
            }

            return productsmenuItemList1;


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

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (CartCount != null)
                ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(), CartCount);
        }


        @Override
        public boolean onMenuItemClick (MenuItem item){

            switch (item.getItemId()) {

                case R.id.notification:
                    Toast.makeText(this, "notification", Toast.LENGTH_LONG).show();
                    break;
            }

            return false;
        }
    }
