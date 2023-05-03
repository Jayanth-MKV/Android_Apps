package com.foodhub.vugido.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.homepage.CategoryRecyclerViewAdapter;
import com.foodhub.vugido.adapters.homepage.HomePageMainAdapter;
import com.foodhub.vugido.adapters.homepage.SliderAdapter;
import com.foodhub.vugido.adapters.homepage.SquareMediumRecyclerViewAdapter;
import com.foodhub.vugido.app.ConfigVariables;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.fragments.HomeFragment;
import com.foodhub.vugido.fragments.bottom_sheet.HomeOptionsBottomSheet;
import com.foodhub.vugido.models.Response;
import com.foodhub.vugido.models.homepage.DATA;
import com.foodhub.vugido.models.homepage.HomePageMainModel;
import com.foodhub.vugido.models.homepage.SECTIONSItem;
import com.foodhub.vugido.models.homepage.SERVICECLIENTSItem;
import com.foodhub.vugido.models.homepage.SERVICECLIENTSTYPEItem;
import com.foodhub.vugido.network.RetrofitBuilder;
import com.foodhub.vugido.receivers.NetworkCallBack;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MapsActivity.ORDER;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener {


    private HomeFragment homePageFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
   // private BottomAppBar bottomAppBar;
    private static final int NETWORK_BACK_CODE = 1;

    private FloatingActionButton floatingActionButton;

    private MenuItem Cart;
    public static final int ORDER_PLACED_CODE = 10000;
    private boolean NetworkFlag=false;


    private Toolbar toolbar;
    private TextView CartCount;


    //--------------------\
    private TabLayout tabIndicator;
    private ViewPager viewPager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView CategoriesRecyclerView,rc;
    private List<HomePageMainModel> homePageMainModelList;
    private HomePageMainAdapter homePageMainAdapter;

    private LinearLayoutManager layoutManager;

    private TextView cdes,ctitle;

    ImageView imageView;
     DrawerLayout drawerLayout;
     ActionBarDrawerToggle actionBarDrawerToggle;

    boolean slider_flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 23) {


            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.mred));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        }else if(Build.VERSION.SDK_INT>23){
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.mred));

        }

        setContentView(R.layout.activity_main);


        Log.e("called ","called onCreate");
        fragmentManager=getSupportFragmentManager();
        toolbar=findViewById(R.id.activity_toolbar);
       // bottomAppBar = findViewById(R.id.bottom_app_bar);
        drawerLayout=findViewById(R.id.navbar);
//        actionBarDrawerToggle=
//                new ActionBarDrawerToggle(this,drawerLayout,
//                        R.string.drawer_open,R.string.drawer_close);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
       // drawerLayout.addDrawerListener(actionBarDrawerToggle);


        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
               // getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
               // getActionBar().setTitle(mDrawerTitle);
            }
        };
        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle.syncState();
//        bottomAppBar.setNavigationOnClickListener(v -> {
//
//            HomeOptionsBottomSheet homeOptionsBottomSheet=new HomeOptionsBottomSheet();
//            homeOptionsBottomSheet.show(getSupportFragmentManager(),"HBSM");
//            // Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
//        });
        NavigationView navigationView=findViewById(R.id.nav_items_action);

        navigationView.setNavigationItemSelectedListener(this);
      //  toolbar.setNavigationIcon(R.drawable.top_menu);


       // bottomAppBar.setOnMenuItemClickListener(this);
//
//        floatingActionButton=findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(v -> {
//
//            try {
//                String text = "Dear Vugido Food-Hub Team:\n";// Replace with your message.
//
//                String toNumber = "919346834541"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
//                //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
//
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
//                startActivity(i);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//
//
////            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
////            startActivityForResult(intent,ORDER_PLACED_CODE);
//        });




            // network is available..
            if(new MyPreferences(this).isTokenChanged()){
                //  To store...
                sendFCMToken(0);
            }else {

                //check user authentication..
                sendFCMToken(1);

            }
            //defaultFragment();
           // sendNotificationClickedData();
           // checkAppUpdate();
            //AffiliatedProduct();

            // referral activity...

        //defaultFragment();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);
        CategoriesRecyclerView=findViewById(R.id.grid_recycler_view);
        rc=findViewById(R.id.home_recyclerView);
        // view pager
        tabIndicator = findViewById(R.id.tab_indicator);
        viewPager =findViewById(R.id.home_viewpager);
        swipeRefreshLayout.setOnRefreshListener(this);

        ctitle=findViewById(R.id.grid_category_title);
        cdes=findViewById(R.id.textView);

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchHomePageData);


        imageView=findViewById(R.id.strip_ad_image);
    }

    //--------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }


    private void fetchHomePageData() {



        Map<String,Object> map=new HashMap<>();

        map.put("UID",new MyPreferences(this).getUID());

        Call<com.foodhub.vugido.models.homepage.Response> call= RetrofitBuilder.getInstance().getRetrofit().homePage(map);
        call.enqueue(new Callback<com.foodhub.vugido.models.homepage.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.homepage.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.homepage.Response> response) {

                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isSTATUS()){


                        bindData(response.body().getDATA());


                    }else
                    {

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.homepage.Response> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }
    Handler handler;
    Runnable runnable;
    int current_position = 0;
    int tv_size = 0;
    private void autoSlider () {
        if (handler==null) {
            handler = new Handler();
            runnable=null;
            runnable= new Runnable() {
                @Override
                public void run() {

                    int numPages = tv_size;
                    current_position = (current_position + 1) % numPages;
                    viewPager.setCurrentItem(current_position, true);
                    handler.postDelayed(this, 3000);
                }
            };

            handler.post(runnable);
        }

    }

    private void bindData(DATA data) {



        cdes.setText(data.getSERVICEDES());
        ctitle.setText(data.getSERVICETITLE());
        SliderAdapter myHomeSlider = new SliderAdapter(data.getSLIDER(),this);
        viewPager.setClipToPadding(false);
//        viewPager.setPageMargin(10);
        viewPager.setAdapter(myHomeSlider);
        tabIndicator.setupWithViewPager(viewPager);

        tv_size=data.getSLIDER().size();
        if(!slider_flag){
            slider_flag=true;
            autoSlider();
        }
        Glide.with(this).load(data.getiMAGE()).into(imageView);



//        // CATEGORIES DATA
//        List<CategoryModel> myHomeDataCATEGORIES=new ArrayList<>();
//        //categories
//
//        myHomeDataCATEGORIES.add(new CategoryModel("Pizzas",R.drawable.demo_img,1));
//        myHomeDataCATEGORIES.add(new CategoryModel("Dosas",R.drawable.a,2));
//        myHomeDataCATEGORIES.add(new CategoryModel("Burgers",R.drawable.b,3));
//        myHomeDataCATEGORIES.add(new CategoryModel("Sandwich",R.drawable.c,4));
//        myHomeDataCATEGORIES.add(new CategoryModel("Pani Puris",R.drawable.d,5));
//        myHomeDataCATEGORIES.add(new CategoryModel("Pizzas",R.drawable.demo_img,6));


        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        CategoriesRecyclerView.setLayoutManager(gridLayoutManager);
        CategoriesRecyclerView.setAdapter(new CategoryRecyclerViewAdapter(data.getSERVICECATEGORIES(),
                this,data.getSERVICEDES(),data.getSERVICETITLE()));

        CategoriesRecyclerView.setNestedScrollingEnabled(false);




        homePageMainModelList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(layoutManager);


        //////////////////
        List<SECTIONSItem> categoriesItemList=new ArrayList<>();

        for(SERVICECLIENTSTYPEItem serviceclientstypeItem: data.getSERVICECLIENTSTYPE()){
            categoriesItemList.add(new SECTIONSItem(serviceclientstypeItem.getTITLE(),serviceclientstypeItem.getID()
                    ,serviceclientstypeItem.getDESCRIBER()));
        }


        List<SERVICECLIENTSItem> productsmenuItemList = new ArrayList<>(data.getSERVICECLIENTS());


//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.demo_img,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.e,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.a,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.b,"~ 80/-","Near Balaga","Low Cost Birani Point",1,1));
//
//
//
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.c,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.b,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.d,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));
//        productsmenuItemList.add(new HOMEPRODUCTSItem(R.drawable.demo_img,"~ 80/-","Near Balaga","Low Cost Birani Point",1,2));








        for(SECTIONSItem dataItem:categoriesItemList){

            homePageMainModelList.add(HomePageMainModel.createSquareMediumViewModelList(categoriesItemList,HomePageMainModel.SQUARE_MEDIUM_VIEW,getCategoryType(dataItem,productsmenuItemList),dataItem.getCNAME(),dataItem.getSID(),"","#000000",dataItem.getDes()));

        }
        homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,this);
        rc.setAdapter(homePageMainAdapter);
        rc.setNestedScrollingEnabled(false);








    }

    private List<SERVICECLIENTSItem> getCategoryType(SECTIONSItem dataItem, List<SERVICECLIENTSItem> productsmenuItemList) {

        List<SERVICECLIENTSItem> productsmenuItemList1=new ArrayList<>();

        for(SERVICECLIENTSItem productsmenuItem:productsmenuItemList){

            if(productsmenuItem.getTYPEID()==dataItem.getSID()){
                productsmenuItemList1.add(productsmenuItem);
            }
        }

        return productsmenuItemList1;




    }


    @Override
    public void onRefresh() {

        fetchHomePageData();
    }
    //---------------------------------
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

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().sendUserFCM(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // updated


                        new MyPreferences(getApplicationContext()).tokenChanged(false);


                    }else {

                        if(check==1){

                            // show re-verify dialog..

                            //showReVerifyDialogBox();
                        }
                        // not updated

                    }
                }else {

                    // error
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

            }
        });
    }

//    private void defaultFragment() {
//        Log.e("called ","called defaultFragment");
//
//        homePageFragment=new HomeFragment();
//        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container,homePageFragment,"HOME");
//        fragmentTransaction.commit();
//
//    }




//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.wallet:
//                // start activity..
//                Intent intent=new Intent(this,ActivityWallet.class);
//                startActivity(intent);
//                return true;
////            case R.id.whatsapp:
////                try {
////                    String text = "Dear Vugido Food-Hub Team:\n";// Replace with your message.
////
////                    String toNumber = "919346834541"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
////                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
////
////                    Intent i = new Intent(Intent.ACTION_VIEW);
////                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
////                    startActivity(i);
////                }
////                catch (Exception e){
////                    e.printStackTrace();
////                }
////                return  true;
//
//        }
//
//        return false;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

//        Cart=menu.findItem(R.id.my_cart);
//        final MenuItem cart = menu.findItem(R.id.my_cart);
//        View view=  cart.getActionView();
//        CartCount=view.findViewById(R.id.cart_badge);
//        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);
//        view.setOnClickListener(view1 -> onOptionsItemSelected(cart));
//

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);


        if(requestCode==ORDER_PLACED_CODE){

            if(resultCode==RESULT_OK){
               // setResult(RESULT_OK);
               // finish();

                //show order placed success...
                Toast.makeText(this,"Order Placed Successfully",Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this,OrdersActivity.class));
            }
        }
//        }else if(requestCode==NETWORK_BACK_CODE){
//
//            if(NetworkFlag && NetworkCallBack.NetworkAvailability)
//            {
//                retryAllInitialNetworkOperations();
//            }
//        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getItemId()==R.id.notification)
            startActivity(new Intent(MainActivity.this,OrdersActivity.class));
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

