package com.vugido.com.vugidoeats.activities;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.adapters.HomePageMainAdapter;
import com.vugido.com.vugidoeats.adapters.MenuCategoryAdapter;
import com.vugido.com.vugidoeats.adapters.SquareMediumRecyclerViewAdapter;
import com.vugido.com.vugidoeats.app_config.ConfigVariables;
import com.vugido.com.vugidoeats.app_config.MyPreferences;
import com.vugido.com.vugidoeats.dialogs.ReVerifyAccount;
import com.vugido.com.vugidoeats.dialogs.UpdateDialog;
import com.vugido.com.vugidoeats.models.HomePage.CATEGORIESItem;
import com.vugido.com.vugidoeats.models.HomePage.DATA;
import com.vugido.com.vugidoeats.models.HomePage.HomePageMainModel;
import com.vugido.com.vugidoeats.models.HomePage.PRODUCTSMENUItem;
import com.vugido.com.vugidoeats.models.HomePage.Response;
import com.vugido.com.vugidoeats.receivers.NetworkCallBack;
import com.vugido.com.vugidoeats.retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MenuActivity extends AppCompatActivity implements SquareMediumRecyclerViewAdapter.AddToCart,SwipeRefreshLayout.OnRefreshListener, Animator.AnimatorListener, MenuCategoryAdapter.MenuCatClicked, View.OnClickListener {
    private static final int ORDER_PLACED_CODE = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    private String URL;
    private CardView menu;
    private Button seeCategories;
    private RecyclerView HomeMainRecyclerView,catRecycler;
    private Toolbar toolbar;
    private LinearLayoutManager layoutManager;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter=null;
    private MenuItem Cart;
    private TextView CartCount;


    private static final int NETWORK_BACK_CODE = 1;
    private boolean NetworkFlag=false;
    private boolean isOpen=false;
    FloatingActionButton close;
    NestedScrollView nestedScrollView;
    View view1;
    ImageView imageView;
    View eats;

//    private ChipNavigationBar chipNavigationBar;
//    private FragmentManager fragmentManager;
//    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        view1=findViewById(R.id.hidden);
        toolbar=findViewById(R.id.activity_toolbar);
        menu=findViewById(R.id.seecatrec);
        imageView=findViewById(R.id.image);
        //chipNavigationBar=findViewById(R.id.chipNavigationBar);

        seeCategories=findViewById(R.id.button3);
        toolbar.setTitle("Royal Garden Dhaba");
        setSupportActionBar(toolbar);
        textView=findViewById(R.id.vision_text_bottom);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);
        textView.setVisibility(View.GONE);
        nestedScrollView=findViewById(R.id.nested_rec);
        catRecycler=findViewById(R.id.cat_recycler_view);
        HomeMainRecyclerView=findViewById(R.id.menu_recyclerView);
        URL=getIntent().getStringExtra("URL");

        seeCategories.setVisibility(View.GONE);
        close=findViewById(R.id.floatingActionButton);
        close.setOnClickListener(v -> {
            circularRevealCard(menu);
            seeCategories.setVisibility(View.VISIBLE);
            close.setVisibility(View.GONE);
        });
        seeCategories.setOnClickListener(v -> {
            circularRevealCard(menu);
            seeCategories.setVisibility(View.GONE);
            close.setVisibility(View.VISIBLE);
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.black);
        // called first time.. with out pulling..
        // make sure network connection before calling..

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchMenu);

        if(NetworkCallBack.NetworkAvailability){
            // network is available..
            if(new MyPreferences(this).isTokenChanged()){
                //  To store...
                sendFCMToken(0);
            }else {

                //check user authentication..
                sendFCMToken(1);

            }
           // defaultFragment();
           // sendNotificationClickedData();
            checkAppUpdate();
           // AffiliatedProduct();

            // referral activity...
        }else {

            NetworkFlag=true;

        }

//        chipNavigationBar.setOnItemSelectedListener(this);
//
//
//        chipNavigationBar.setItemSelected(R.id.bottom_home,false);
//
//        fragmentManager=getSupportFragmentManager();
//        fragmentManager.addOnBackStackChangedListener(this);
//        defaultFragment();
    }

    private void checkAppUpdate(){
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        Call<com.vugido.com.vugidoeats.models.AppUpdate.Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getAppUpdateStatus(map);
        call.enqueue(new Callback<com.vugido.com.vugidoeats.models.AppUpdate.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.com.vugidoeats.models.AppUpdate.Response> call, @NonNull retrofit2.Response<com.vugido.com.vugidoeats.models.AppUpdate.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok
                        if(!response.body().isUPDATED()){
                            showUpdateDialogBox();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.com.vugidoeats.models.AppUpdate.Response> call, @NonNull Throwable t) {

            }
        });
    }
    private void showUpdateDialogBox() {
        UpdateDialog updateDialog=new UpdateDialog();
        updateDialog.show(getSupportFragmentManager(),"UPDATE");
    }
    private void sendFCMToken(int check) {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("TOKEN",new MyPreferences(this).getFireBaseToken());
        map.put("IS_CHECK",String.valueOf(check));
       /* FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.e("Stored",new MyPreferences(this).getFireBaseToken());
            Log.e("newToken",newToken);

        });*/

        Call<com.vugido.com.vugidoeats.models.status.Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").sendUserFCM(map);

        call.enqueue(new Callback<com.vugido.com.vugidoeats.models.status.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.com.vugidoeats.models.status.Response> call, @NonNull retrofit2.Response<com.vugido.com.vugidoeats.models.status.Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // updated


                        new MyPreferences(getApplicationContext()).tokenChanged(false);


                    }else {

                        if(check==1){

                            // show re-verify dialog..

                            showReVerifyDialogBox();
                        }
                        // not updated

                    }
                }else {

                    // error
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.com.vugidoeats.models.status.Response> call, @NonNull Throwable t) {

            }
        });
    }

    private void showReVerifyDialogBox() {

        ReVerifyAccount reVerifyAccount=new ReVerifyAccount();
        reVerifyAccount.show(getSupportFragmentManager(),"REVERIFY");

    }
    private void circularRevealCard(View view){
        float finalRadius = Math.max(view.getWidth(), view.getHeight());


        if(!isOpen) {
            int x = view.getRight();
            int y = view.getBottom();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
            //circularReveal.setDuration(300);

            // make the view visible and start the animation
            view.setVisibility(View.VISIBLE);

            circularReveal.start();
            circularReveal.addListener(this);

            isOpen=true;
            seeCategories.setText("Close");
            seeCategories.setBackgroundColor(Color.WHITE);
            seeCategories.setTextColor(getResources().getColor(R.color.gradient_start_color));
        }else {
            int x = view.getRight();
            int y = view.getBottom();

            int startRadius =(int) Math.hypot(view.getWidth(), view.getHeight()) ;
            int endRadius = 0;
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);

            circularReveal.start();
           // view.setVisibility(View.INVISIBLE);
            isOpen=false;

            circularReveal.addListener(this);
            seeCategories.setBackgroundColor(getResources().getColor(R.color.gradient_start_color));
            seeCategories.setText("See Menu");
            seeCategories.setTextColor(Color.WHITE);
        }
    }

    private void fetchMenu() {
        Map<String,Object> map=new HashMap<>();

        try {


            if(!new MyPreferences(getApplicationContext()).isOnGoing()){
            String[] s=URL.split("=");
            String[] s1=s[1].split("&");
            String A=s1[0];
            String B= s[2];
            map.put("A",String.valueOf(A));
            map.put("B",String.valueOf(B));
            //            new MyPreferences(getApplicationContext()).setTableNumber(Integer.parseInt(B));
//            new MyPreferences(getApplicationContext()).setCID(Integer.parseInt(A));
                new MyPreferences(this).setOnGoing(true);

            }else{
                map.put("A",String.valueOf(new MyPreferences(getApplicationContext()).getCID()));
                map.put("B",String.valueOf(new MyPreferences(getApplicationContext()).getTableNumber()));

            }
            Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("").fetchHomePage(map);

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                    swipeRefreshLayout.setRefreshing(false);
                    if(response.isSuccessful()){

                        assert response.body() != null;
                        if(!response.body().isError()){

                            setMenuData(response.body().getDATA());
                        }else {
                            //can't load this qr code
                            Toast.makeText(getApplicationContext(),"failed Can't Load this Qr Code"+new MyPreferences(getApplicationContext()).getCID()+new MyPreferences(getApplicationContext()).getTableNumber(),Toast.LENGTH_LONG).show();
                        }
                    }else {

                    }

                }

                @Override
                public void onFailure(@NonNull Call<Response> call,@NonNull Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }catch (Exception e){
            Log.e("error",e.toString());

            Toast.makeText(this,"try Can't Load this Qr Code"+e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    private void setMenuData(DATA data) {


        MyPreferences myPreferences=new MyPreferences(this);

        myPreferences.setTableNumber(Integer.parseInt(data.getTABLENUMBER()));
        myPreferences.setCID(Integer.parseInt(data.getCID()));

        homePageMainModelList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        HomeMainRecyclerView.setLayoutManager(layoutManager);



        // segment the total products

        List<CATEGORIESItem> categoriesItemList=data.getCATEGORIES();
        List<PRODUCTSMENUItem> productsmenuItemList=data.getPRODUCTSMENU();


        if(categoriesItemList!=null){
            seeCategories.setVisibility(View.VISIBLE);
        }
        MenuCategoryAdapter menuCategoryAdapter=new MenuCategoryAdapter(categoriesItemList,this);
        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        catRecycler.setLayoutManager(l);
        catRecycler.setAdapter(menuCategoryAdapter);
        catRecycler.setNestedScrollingEnabled(false);

        for(CATEGORIESItem dataItem:categoriesItemList){


            //here get the appropriate list...

//            List<SquareMediumModel> squareMediumModelList=new ArrayList<>();
//            squareMediumModelList.add(new SquareMediumModel())
            homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(HomePageMainModel.SQUARE_MEDIUM_VIEW,getCategoryType(dataItem,productsmenuItemList),dataItem.getCNAME(),dataItem.getSID(),"","#8E2DE2"));

//            homePageMainModelList.add(getViewTypeData(dataItem));
//            callSourceData(dataItem);
        }
        homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,this);
        HomeMainRecyclerView.setAdapter(homePageMainAdapter);
        HomeMainRecyclerView.setNestedScrollingEnabled(false);
        Log.e("adaopter","attached");



    }

    private List<PRODUCTSMENUItem> getCategoryType(CATEGORIESItem dataItem, List<PRODUCTSMENUItem> productsmenuItemList) {

        List<PRODUCTSMENUItem> productsmenuItemList1=new ArrayList<>();

        for(PRODUCTSMENUItem productsmenuItem:productsmenuItemList){

            if(productsmenuItem.getSID()==dataItem.getSID()){
                productsmenuItemList1.add(productsmenuItem);
            }
        }

        return productsmenuItemList1;




    }

    @Override
    public void onBackPressed() {
        if(new MyPreferences(getApplicationContext()).isOnGoing())
            setResult(RESULT_OK);

        super.onBackPressed();
    }

    @Override
    public void onRefresh() {
        fetchMenu();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

        if(!isOpen){
            menu.setVisibility(View.INVISIBLE);
        }else {
            menu.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void menuClicked(int id) {


        circularRevealCard(menu);
        seeCategories.setVisibility(View.VISIBLE);
        close.setVisibility(View.GONE);

        //now using id navigate to that menu...

        //homePageMainAdapter
        int p=0;
        for(int i =0 ;i<homePageMainModelList.size();i++){
            if(homePageMainModelList.get(i).getHID()==id){
                p=i;
                break;
            }
        }

        float y = HomeMainRecyclerView.getChildAt(p).getY();

        nestedScrollView.fling(0);
        nestedScrollView.setSmoothScrollingEnabled(true);
        nestedScrollView.smoothScrollTo(0, (int) y);
      //  layoutManager.smoothScrollToPosition(HomeMainRecyclerView,null,3);
        //HomeMainRecyclerView.smoothScrollToPosition(3);
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

                new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()+1);

                if(CartCount!=null)
                    ConfigVariables.setupBadge(new MyPreferences(getApplicationContext()).getCartCount(),CartCount);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        new MyPreferences(this).setCartProducts(null);
//        new MyPreferences(this).setCartCount(0);
//        new MyPreferences(this).setCID(0);
//        new MyPreferences(this).setTableNumber(0);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.my_cart:
                // start activity..
                Intent intent=new Intent(this,CartProductsActivity.class);
                if(new MyPreferences(getApplicationContext()).getCartProducts()!=null)
                    startActivityForResult(intent,ORDER_PLACED_CODE);
                else
                    Toast.makeText(getApplicationContext(),"Cart Empty ",Toast.LENGTH_LONG).show();
                return true;
            case R.id.account:
                return true;

            case R.id.orders:
                startActivity(new Intent(this,OnGoingActivity.class));
                return true;
            //return actionBarDrawerToggle.onOptionsItemSelected(item);

        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        //  PocketCart = menu.findItem(R.id.pocket_cart);
        //  PocketCart.setVisible(false);
        Cart=menu.findItem(R.id.my_cart);
        final MenuItem cart = menu.findItem(R.id.my_cart);
        eats=  cart.getActionView();
        CartCount=eats.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);
        eats.setOnClickListener(view1 -> onOptionsItemSelected(cart));

        //  Notifications=menu.findItem(R.id.notification);
        // menuItem.setVisible(false);

        return true;
    }


    @Override
    public void eat(int pid, ImageView loc,String url) {

        if(new MyPreferences(this).getVerified()){
            view1.setVisibility(View.VISIBLE);
            Glide.with(this).load(url).into(imageView);
            addToCartAnimation(loc,eats);
            setCart(pid);
        }else {

            startActivity(new Intent(MenuActivity.this,LoginActivity.class));
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


}
