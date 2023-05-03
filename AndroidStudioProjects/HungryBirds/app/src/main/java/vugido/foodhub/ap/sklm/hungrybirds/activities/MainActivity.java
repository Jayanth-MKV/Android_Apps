package vugido.foodhub.ap.sklm.hungrybirds.activities;

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

import com.bumptech.glide.Glide;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.adapters.homepage.CategoriesRecyclerViewAdapter;
import vugido.foodhub.ap.sklm.hungrybirds.adapters.homepage.HomePageMainAdapter;
import vugido.foodhub.ap.sklm.hungrybirds.adapters.homepage.MyHomeSlider;
import vugido.foodhub.ap.sklm.hungrybirds.adapters.homepage.SquareMediumRecyclerViewAdapter;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.ConfigVariables;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.MyPreferences;
import vugido.foodhub.ap.sklm.hungrybirds.fragments.bottom_sheet.HomeOptionsBottomSheet;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.CATEGORIESItem;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.DATA;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.HIGHTLIGHTItem;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.HomePageMainModel;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.PRODUCTSMENUItem;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.Response;
import vugido.foodhub.ap.sklm.hungrybirds.models.homepage_main_model.SECItem;
import vugido.foodhub.ap.sklm.hungrybirds.network.Retrofit.RetrofitBuilder;

import static vugido.foodhub.ap.sklm.hungrybirds.activities.MapsActivity.ORDER;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Toolbar.OnMenuItemClickListener, SquareMediumRecyclerViewAdapter.AddToCart {


    private LinearLayoutManager layoutManager;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter=null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomAppBar bottomAppBar;
    int current_position=0;
    int tv_size=0;
    private ViewPager topVP;
    ImageView imageView;

    private TabLayout top_tab, tabIndicator;
    private RecyclerView recyclerView, CategoriesRecyclerView;

    private FloatingActionButton floatingActionButton;
    View view1;
    ImageView q;
    View eats;
    private MenuItem Cart;

    private TextView CartCount,end;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        end=findViewById(R.id.end);

        toolbar=findViewById(R.id.activity_toolbar);
        bottomAppBar = findViewById(R.id.bottom_app_bar);

        view1=findViewById(R.id.hidden);
        imageView=findViewById(R.id.product_image);
        bottomAppBar.setNavigationOnClickListener(v -> {

            HomeOptionsBottomSheet homeOptionsBottomSheet=new HomeOptionsBottomSheet();
            homeOptionsBottomSheet.show(getSupportFragmentManager(),"HBSM");
            // Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        });
        CategoriesRecyclerView=findViewById(R.id.grid_recycler_view);

        q=findViewById(R.id.quote);

        bottomAppBar.setOnMenuItemClickListener(this);
        recyclerView=findViewById(R.id.home_recyclerView);

        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            startActivityForResult(new Intent(MainActivity.this,SearchActivity.class),1);
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
        // get the data here
        //fetchMenu();
        swipeRefreshLayout.post(this::fetchHomePageData);


    }

    private void fetchHomePageData() {

        end.setVisibility(View.GONE);

        Map<String,Object> map=new HashMap<>();
        map.put("UID","1");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getHomePage(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                swipeRefreshLayout.setRefreshing(false);

                end.setVisibility(View.VISIBLE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if (response.body().isSTATUS()){

                        bindData(response.body().getDATA());


                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private List<PRODUCTSMENUItem> getCategoryType(SECItem dataItem, List<PRODUCTSMENUItem> productsmenuItemList) {

        List<PRODUCTSMENUItem> productsmenuItemList1=new ArrayList<>();

        for(PRODUCTSMENUItem productsmenuItem:productsmenuItemList){

            if(productsmenuItem.getSID()==dataItem.getID()){
                productsmenuItemList1.add(productsmenuItem);
            }
        }

        return productsmenuItemList1;




    }

    private void bindData(DATA data) {


        List<HIGHTLIGHTItem> hightlightItemList=data.getHIGHTLIGHT();

        MyHomeSlider myHomeSlider=new MyHomeSlider(hightlightItemList,this);


        topVP.setClipToPadding(false);
        topVP.setPageMargin(2);

        topVP.setAdapter(myHomeSlider);
        top_tab.setupWithViewPager(topVP);
        tv_size=hightlightItemList.size();
        autoSlider();

        // CATEGORIES DATA
        List<CATEGORIESItem> myHomeDataCATEGORIES=data.getCATEGORIES();
        //categories
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        CategoriesRecyclerView.setLayoutManager(linearLayoutManager);
        CategoriesRecyclerView.setAdapter(new CategoriesRecyclerViewAdapter(myHomeDataCATEGORIES,this));

        CategoriesRecyclerView.setNestedScrollingEnabled(false);

        Glide.with(this).load(data.getQUOTE()).into(q);

        setMenuData(data);


    }

    private void setMenuData(DATA data) {

        Log.e("ok","3");


        homePageMainModelList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);



        // segment the total products

        List<SECItem> categoriesItemList=data.getSEC();
        List<PRODUCTSMENUItem> productsmenuItemList=data.getPRODUCTSMENU();



        for(SECItem dataItem:categoriesItemList){

            homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(categoriesItemList,HomePageMainModel.SQUARE_MEDIUM_VIEW,getCategoryType(dataItem,productsmenuItemList),dataItem.getTITLE(),dataItem.getID(),"","#8E2DE2"));

        }
        homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,this);
        recyclerView.setAdapter(homePageMainAdapter);
        recyclerView.setNestedScrollingEnabled(false);




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

    private void setCart(int pid){

        MyPreferences myPreferences=new MyPreferences(this);

        if(myPreferences.getCartProducts()==null){
            myPreferences.setCartProducts(String.valueOf(pid));
        }else {
            myPreferences.setCartProducts(myPreferences.getCartProducts()+","+ pid);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(CartCount!=null)
            ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);

        if (requestCode==ORDER && resultCode==RESULT_OK)
        {

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_cart:
                startActivityForResult(new Intent(MainActivity.this,CartProductsActivity.class),ORDER);
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


    @Override
    public void onRefresh() {


        fetchHomePageData();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getItemId()==R.id.notification)
            startActivity(new Intent(MainActivity.this,OrdersActivity.class));
        return false;
    }
}

