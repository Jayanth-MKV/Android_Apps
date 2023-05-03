package com.foodhub.vugido.sklm.fresh_cuts.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.sklm.fresh_cuts.R;
import com.foodhub.vugido.sklm.fresh_cuts.adapters.homepage.SquareMediumRecyclerViewAdapter;
import com.foodhub.vugido.sklm.fresh_cuts.app_config.ConfigVariables;
import com.foodhub.vugido.sklm.fresh_cuts.app_config.MyPreferences;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.DATA;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.HOMEPRODUCTSItem;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.SECTIONSItem;
import com.foodhub.vugido.sklm.fresh_cuts.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener, SquareMediumRecyclerViewAdapter.AddToCart {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private SquareMediumRecyclerViewAdapter squareMediumRecyclerViewAdapter=null;
    SwipeRefreshLayout swipeRefreshLayout;
    View view1;
    ImageView imageView;
    View eats;
    private MenuItem Cart;

    private TextView CartCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar=findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Search");
        view1=findViewById(R.id.hidden);
        imageView=findViewById(R.id.image);
        recyclerView=findViewById(R.id.home_recyclerView);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
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

    private void fetchHomePageData() {

        //textView.setVisibility(View.GONE);
        Map<String, Object> map = new HashMap<>();
        map.put("UID", "1");
        Call<Response> call = RetrofitBuilder.getInstance().getRetrofit().fetchHomePageContent(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.Response> response) {

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


                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }


    private void bindDataToAdapter(DATA dataItemList) {







        setMenuData(dataItemList);



    }

    private void setMenuData(DATA data) {







        // segment the total products

        List<SECTIONSItem> categoriesItemList=data.getSECTIONS();
        List<HOMEPRODUCTSItem> productsmenuItemList=data.getHOMEPRODUCTS();






        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapter(productsmenuItemList,this,categoriesItemList);
        recyclerView.setAdapter(squareMediumRecyclerViewAdapter);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        Cart=menu.findItem(R.id.my_cart);
        final MenuItem cart = menu.findItem(R.id.my_cart);
        eats=  cart.getActionView();
        CartCount=eats.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);
        eats.setOnClickListener(view1 -> onOptionsItemSelected(cart));
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

    @Override
    public void onRefresh() {
        fetchHomePageData();
    }

    @Override
    public void eat(int pid, ImageView loc, String url,int s) {
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


}
