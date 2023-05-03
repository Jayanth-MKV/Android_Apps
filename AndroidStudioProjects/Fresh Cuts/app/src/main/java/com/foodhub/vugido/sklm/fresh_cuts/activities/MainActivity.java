package com.foodhub.vugido.sklm.fresh_cuts.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.foodhub.vugido.sklm.fresh_cuts.R;
import com.foodhub.vugido.sklm.fresh_cuts.adapters.homepage.CategoriesRecyclerViewAdapter;
import com.foodhub.vugido.sklm.fresh_cuts.adapters.homepage.HomePageMainAdapter;
import com.foodhub.vugido.sklm.fresh_cuts.adapters.homepage.MyOfferSlider;
import com.foodhub.vugido.sklm.fresh_cuts.adapters.homepage.SquareMediumRecyclerViewAdapter;
import com.foodhub.vugido.sklm.fresh_cuts.app_config.ConfigVariables;
import com.foodhub.vugido.sklm.fresh_cuts.app_config.MyPreferences;
import com.foodhub.vugido.sklm.fresh_cuts.fragments.bottom_sheet.HomeOptionsBottomSheet;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.CATEGORIESItem;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.DATA;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.HIGHTLIGHTItem;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.HOMEPRODUCTSItem;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.HomePageMainModel;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.SECTIONSItem;
import com.foodhub.vugido.sklm.fresh_cuts.network.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, SquareMediumRecyclerViewAdapter.AddToCart, Toolbar.OnMenuItemClickListener {
    private LinearLayoutManager layoutManager;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter=null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomAppBar bottomAppBar;
    int current_position=0;
    int tv_size=0;
    private ViewPager topVP,viewPager;
    private TabLayout top_tab, tabIndicator;
    private RecyclerView recyclerView, CategoriesRecyclerView;

    private FloatingActionButton floatingActionButton;
    View view1;
    ImageView imageView;
    View eats;
    private MenuItem Cart;

    private TextView CartCount;
    private Toolbar toolbar;
    private ImageView WhatsAppOrder, CallOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.activity_toolbar);
        bottomAppBar = findViewById(R.id.bottom_app_bar);

        WhatsAppOrder=findViewById(R.id.whats_app_order);
        CallOrder=findViewById(R.id.call_order);
        bottomAppBar.setNavigationOnClickListener(v -> {

            HomeOptionsBottomSheet homeOptionsBottomSheet=new HomeOptionsBottomSheet();
            homeOptionsBottomSheet.show(getSupportFragmentManager(),"HBSM");
           // Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        });
        CategoriesRecyclerView=findViewById(R.id.grid_recycler_view);

        tabIndicator = findViewById(R.id.tab_indicator);
        viewPager =findViewById(R.id.home_viewpager);

        bottomAppBar.setOnMenuItemClickListener(this);
        recyclerView=findViewById(R.id.home_recyclerView);
        view1=findViewById(R.id.hidden);
        imageView=findViewById(R.id.product_image);
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,SearchActivity.class),1);
            }
        });
        top_tab=findViewById(R.id.top_tab_indicator);
        topVP=findViewById(R.id.offer_viewpager);
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
                fetchHomePageData();
                //fetchMenu();

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_cart:
                startActivity(new Intent(MainActivity.this,CartProductsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        Cart=menu.findItem(R.id.my_cart);
        final MenuItem cart = menu.findItem(R.id.my_cart);
        eats=  cart.getActionView();
        CartCount=eats.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);
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

        fetchHomePageData();
    }




    private void fetchHomePageData() {


        //textView.setVisibility(View.GONE);
        Map<String, Object> map = new HashMap<>();
        map.put("UID", "1");

        Call<Response> call = RetrofitBuilder.getInstance().getRetrofit().fetchHomePageContent(map);

        call.enqueue(new Callback<com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response> response) {

                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {

                    assert response.body() != null;
                    if (response.body().isSTATUS()) {
                        // ok data received



                        DATA dataItemList = response.body().getDATA();

                        bindDataToAdapter(dataItemList);

                        Log.e("ok","1");
                    } else {

                        // no data
                    }

                } else {

                    // something failed
                }
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable s) {

                Log.e("failed","ok");
                Log.e("msg",s.toString());
                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }


    private void bindDataToAdapter(DATA dataItemList) {

        Log.e("ok","2");

        List<HIGHTLIGHTItem> topviewsItemList=dataItemList.getHIGHTLIGHT();


        MyOfferSlider myOfferSlider=new MyOfferSlider(topviewsItemList,this);
        topVP.setClipToPadding(false);
        topVP.setPageMargin(2);

        topVP.setAdapter(myOfferSlider);
        top_tab.setupWithViewPager(topVP);
        tv_size=topviewsItemList.size();
        autoSlider();





//        List<SLIDERSItem> hightlightItemList=dataItemList.getSLIDERS();
//
//        MyHomeSlider myHomeSlider = new MyHomeSlider(hightlightItemList,this);
//        viewPager.setClipToPadding(false);
//        viewPager.setPageMargin(10);
//        viewPager.setAdapter(myHomeSlider);
//        tabIndicator.setupWithViewPager(viewPager);



        // CATEGORIES DATA
        List<CATEGORIESItem> myHomeDataCATEGORIES=dataItemList.getCATEGORIES();
        //categories
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        CategoriesRecyclerView.setLayoutManager(linearLayoutManager);
        CategoriesRecyclerView.setAdapter(new CategoriesRecyclerViewAdapter(myHomeDataCATEGORIES,this));


        setMenuData(dataItemList);

    }

    private void setMenuData(DATA data) {

        Log.e("ok","3");


        homePageMainModelList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);



        // segment the total products

        List<SECTIONSItem> categoriesItemList=data.getSECTIONS();
        List<HOMEPRODUCTSItem> productsmenuItemList=data.getHOMEPRODUCTS();



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(CartCount!=null)
            ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);
    }

    @Override
    public void eat(int pid, ImageView loc, String url,int s) {


        if(s==1){
        new MyPreferences(this).setVerified(true);
        if(new MyPreferences(this).getVerified()){
            view1.setVisibility(View.VISIBLE);
            Glide.with(this).load(url).into(imageView);
            new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()+1);

            if(CartCount!=null)
                ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);
            addToCartAnimation(loc,eats);
            setCart(pid);
        }else {

            //startActivity(new Intent(MenuActivity.this,LoginActivity.class));
        }
        }else{

            removePID(pid);
            new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()-1);

            if(CartCount!=null)
                ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);

        }


    }

    private void removePID(int pid) {
        MyPreferences myPreferences=new MyPreferences(this);
        String pids=myPreferences.getCartProducts();
        String[] a=pids.split(",");

        for (int i=0;i<a.length;i++){

            if(a[i].equals(String.valueOf(pid)))
            {
                a[i]="0";
                break;
        }

        }
        myPreferences.setCartProducts(null);
        for (int i=0;i<a.length;i++){

            if(!a[i].equals("0"))
            {
                setCart(Integer.parseInt(a[i]));
            }
    }
    }

    private void setCart(int pid){

        MyPreferences myPreferences=new MyPreferences(this);

        if(myPreferences.getCartProducts()==null){
            myPreferences.setCartProducts(String.valueOf(pid));
        }else {
            myPreferences.setCartProducts(myPreferences.getCartProducts()+","+ pid);
        }
    }

    private void addToCartAnimation(View view,View cart){
        // get location of the clicked view
        int[] fromLoc=new int[2];
        view.getLocationOnScreen(fromLoc);
        float startX = fromLoc[0];
        float startY = fromLoc[1];

// get location of cart icon
        int[] toLoc = new int[2];
        cart.getLocationOnScreen(toLoc);
        float destX = toLoc[0];
        float destY = toLoc[1];

        AnimationSet animationToLeft = new AnimationSet(true);

        ScaleAnimation scaleToLeftAnimation = new ScaleAnimation(1, (float) 0.8, 1, (float) 0.8);
        scaleToLeftAnimation.setInterpolator(new BounceInterpolator());
        scaleToLeftAnimation.setDuration(200);
        scaleToLeftAnimation.setFillAfter(true);
        animationToLeft.addAnimation(scaleToLeftAnimation);

        TranslateAnimation translateToLeftAnimation = new TranslateAnimation(startX, destX, startY, startY);
        translateToLeftAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateToLeftAnimation.setDuration(200);
        animationToLeft.addAnimation(translateToLeftAnimation);

        AlphaAnimation alpha = new AlphaAnimation(1, (float) 0.8);
        alpha.setDuration(200);
        animationToLeft.addAnimation(alpha);

        final AnimationSet animationToBottom = new AnimationSet(true);
        // animationToBottom.setInterpolator(new BounceInterpolator());

        ScaleAnimation scaleToBottomAnimation = new ScaleAnimation((float) 0.8, (float) 0.1, (float) 0.8, (float) 0.1);
        scaleToBottomAnimation.setDuration(400);
        scaleToBottomAnimation.setFillAfter(true);
        animationToBottom.addAnimation(scaleToBottomAnimation);

        TranslateAnimation translateToBottomAnimation = new TranslateAnimation(destX, destX, startY, destY);
        translateToBottomAnimation.setDuration(800);
        animationToBottom.addAnimation(translateToBottomAnimation);

        AlphaAnimation alphaToBottom = new AlphaAnimation((float) 0.8, (float) 0.6);
        alphaToBottom.setDuration(800);
        animationToBottom.addAnimation(alphaToBottom);

//        animationToLeft.start();
//        animationToBottom.start();


        view1.setAnimation(animationToLeft);
        animationToLeft.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                view1.setAnimation(animationToBottom);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                view1.setVisibility(View.VISIBLE);
            }
        });



        animationToBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                view1.setVisibility(View.GONE);

//                new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()+1);
//
//                if(CartCount!=null)
//                    ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){

            case R.id.notification:
                Toast.makeText(this,"notification",Toast.LENGTH_LONG).show();
                break;
        }

        return false;
    }
}