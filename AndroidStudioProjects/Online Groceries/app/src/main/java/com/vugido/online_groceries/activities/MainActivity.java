package com.vugido.online_groceries.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.homepage.BrandsRecyclerViewAdapter;
import com.vugido.online_groceries.adapters.homepage.CategoryRecyclerViewAdapter;
import com.vugido.online_groceries.adapters.homepage.ProductsAdapter;
import com.vugido.online_groceries.adapters.homepage.SliderAdapter;
import com.vugido.online_groceries.app.ConfigVariables;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.design.Space;
import com.vugido.online_groceries.fragments.HomeFragment;
import com.vugido.online_groceries.fragments.bottom_sheet.HomeOptionsBottomSheet;
import com.vugido.online_groceries.models.Response;
import com.vugido.online_groceries.models.homepage.HomePageMainModel;
import com.vugido.online_groceries.models.homepage.updated.BRANDSItem;
import com.vugido.online_groceries.models.homepage.updated.CATEGORIESItem;
import com.vugido.online_groceries.models.homepage.updated.DATA;
import com.vugido.online_groceries.models.homepage.updated.HOMEPRODUCTSItem;
import com.vugido.online_groceries.models.homepage.updated.SLIDERItem;
import com.vugido.online_groceries.network.RetrofitBuilder;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements ProductsAdapter.INC_DEC, Toolbar.OnMenuItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    private HomeFragment homePageFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BottomAppBar bottomAppBar;
    private static final int NETWORK_BACK_CODE = 1;

    private FloatingActionButton floatingActionButton;

    private MenuItem Cart;
    public static final int ORDER_PLACED_CODE = 10000;
    private boolean NetworkFlag=false;


    private Toolbar toolbar;
    private TextView CartCount,ctitle,cdes,btitle,htitle,hdes;

    //--------------------
    private List<HomePageMainModel> homePageMainModelList;
    private RecyclerView recyclerView,crecyclerView,brandsRecyclerView;



    int current_position = 0;
    int tv_size = 0;

    private SwipeRefreshLayout swipeRefreshLayout;
     Handler handler;
     Runnable runnable;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView post_image;
    boolean slider_flag=false;
    List<HOMEPRODUCTSItem> productModelList1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctitle=findViewById(R.id.ctitle);
        cdes=findViewById(R.id.cdes);
        htitle=findViewById(R.id.grid_category_title);
        hdes=findViewById(R.id.textView);
        btitle=findViewById(R.id.btitle);


        fragmentManager=getSupportFragmentManager();
        toolbar=findViewById(R.id.activity_toolbar);
        bottomAppBar = findViewById(R.id.bottom_app_bar);

        viewPager=findViewById(R.id.offer_viewpager);
        tabLayout=findViewById(R.id.top_tab_indicator);
        bottomAppBar.setNavigationOnClickListener(v -> {

            HomeOptionsBottomSheet homeOptionsBottomSheet=new HomeOptionsBottomSheet();
            homeOptionsBottomSheet.show(getSupportFragmentManager(),"HBSM");
        });


        bottomAppBar.setOnMenuItemClickListener(this);

        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
//            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
            Intent intent=new Intent(MainActivity.this,AllProductsActivity.class);
            intent.putExtra("NAME","Search Any Product");
            intent.putExtra("CID",0);
            startActivityForResult(intent,ORDER_PLACED_CODE);
        });
        setSupportActionBar(toolbar);




            // network is available..
            if(new MyPreferences(this).isTokenChanged()){
                //  To store...
                sendFCMToken(0);
            }else {

                //check user authentication..
                sendFCMToken(1);

            }
           // sendNotificationClickedData();
           // checkAppUpdate();
            //AffiliatedProduct();

            // referral activity...


        recyclerView=findViewById(R.id.crec);
        crecyclerView=findViewById(R.id.grid_recycler_view);
        brandsRecyclerView=findViewById(R.id.brand_recycler_view);
        post_image=findViewById(R.id.grid_category_image);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchHomeContent);
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,4);
        crecyclerView.setLayoutManager(gridLayoutManager);
        crecyclerView.addItemDecoration(new Space(4,10,false,0));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        brandsRecyclerView.setLayoutManager(linearLayoutManager);


        GridLayoutManager gridLayoutManager1=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager1);
        recyclerView.addItemDecoration(new Space(2,10,true,0));

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(10);
        fetchHomeContent();
    }

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


    private void fetchHomeContent() {


        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());

        Call<com.vugido.online_groceries.models.homepage.updated.Response> call=RetrofitBuilder.getInstance().getRetrofit().homePage(map);
        call.enqueue(new Callback<com.vugido.online_groceries.models.homepage.updated.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.online_groceries.models.homepage.updated.Response> call, retrofit2.Response<com.vugido.online_groceries.models.homepage.updated.Response> response) {
                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful()){

                    if (!response.body().isSTATUS()){
                        //data fetched...
                        bindHomePageData(response.body().getDATA());

                    }else{
                        //no data
                    }

                }

            }

            @Override
            public void onFailure(Call<com.vugido.online_groceries.models.homepage.updated.Response> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }
    ProductsAdapter productsAdapter;

    private void bindHomePageData(DATA data) {

        cdes.setText(data.getCDES());
        ctitle.setText(data.getCTITLE());
        htitle.setText(data.getHTITLE());
        hdes.setText(data.getHDES());
        btitle.setText(data.getBTITLE());

        homePageMainModelList=new ArrayList<>();

        //sliders

        List<SLIDERItem> sliderItemList=data.getSLIDER();
        SliderAdapter sliderViewAdapter=new SliderAdapter(sliderItemList,this);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(sliderViewAdapter);

        tv_size=sliderItemList.size();
        if(!slider_flag){
            slider_flag=true;
            autoSlider();
        }

        //categories..

        List<CATEGORIESItem> categoryModelList=data.getCATEGORIES();
        CategoryRecyclerViewAdapter categoryRecyclerViewAdapter=new CategoryRecyclerViewAdapter(categoryModelList,this,data.getCDES(),data.getCTITLE());
        crecyclerView.setAdapter(categoryRecyclerViewAdapter);
        crecyclerView.setNestedScrollingEnabled(false);


        // brands recycler view...

        List<BRANDSItem> brandsItemList=data.getBRANDS();

        BrandsRecyclerViewAdapter brandsRecyclerViewAdapter=new BrandsRecyclerViewAdapter(brandsItemList,this,"",data.getBTITLE());
        brandsRecyclerView.setAdapter(brandsRecyclerViewAdapter);
        brandsRecyclerView.setNestedScrollingEnabled(false);

        //posts


        Glide.with(this).load(data.getIMAGE()).into(post_image);



        //grid sectioned products..

        productModelList1=data.getHOMEPRODUCTS();
        productsAdapter=new ProductsAdapter(productModelList1,this);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setNestedScrollingEnabled(false);

//        List<ProductModel> productModelList2=new ArrayList<>();
//        productModelList2.add(new ProductModel(R.drawable.a,"Dalchini Chekka","35","",""));
//        productModelList2.add(new ProductModel(R.drawable.b,"Dalchini Chekka","35","",""));
//        productModelList2.add(new ProductModel(R.drawable.c,"Dalchini Chekka","35","",""));
//        productModelList2.add(new ProductModel(R.drawable.d,"Dalchini Chekka","35","",""));
//
//
//
//        List<ProductModel> productModelList3=new ArrayList<>();
//        productModelList3.add(new ProductModel(R.drawable.a,"Dalchini Chekka","35","",""));
//        productModelList3.add(new ProductModel(R.drawable.b,"Dalchini Chekka","35","",""));
//        productModelList3.add(new ProductModel(R.drawable.c,"Dalchini Chekka","35","",""));
//        productModelList3.add(new ProductModel(R.drawable.d,"Dalchini Chekka","35","",""));




//        homePageMainModelList.add(HomePageMainModel.createSliderModel(sliderItemList,HomePageMainModel.SLIDER_VIEW,"",1,"","",""));
//        homePageMainModelList.add(HomePageMainModel.createCategoryModelView(categoryModelList,HomePageMainModel.CATEGORY_VIEW,"",2,"","",""));
//        homePageMainModelList.add(HomePageMainModel.createHomePostsModelPost(HomePageMainModel.HOME_POSTS,h2,"",3,"","",""));
//
//
//        homePageMainModelList.add(HomePageMainModel.createProductsSection(productModelList1,HomePageMainModel.GRID_VIEW,"",4,"","",""));
//        homePageMainModelList.add(HomePageMainModel.createProductsSection(productModelList2,HomePageMainModel.GRID_VIEW,"",5,"","",""));
//        homePageMainModelList.add(HomePageMainModel.createProductsSection(productModelList3,HomePageMainModel.GRID_VIEW,"",6,"","",""));

//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        recyclerView.setLayoutManager(linearLayoutManager);
//        homePageMainAdapter=new HomePageMainAdapter(homePageMainModelList,this);
//        recyclerView.setAdapter(homePageMainAdapter);


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

    private void defaultFragment() {
        Log.e("called ","called defaultFragment");

//        homePageFragment=new HomeFragment();
//        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container,homePageFragment,"HOME");
//        fragmentTransaction.commit();

    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.my_cart:
                // start activity..
                Intent intent=new Intent(this,MyCartActivity.class);
                startActivityForResult(intent,ORDER_PLACED_CODE);
                return true;
            case R.id.whatsapp:
                try {
                    String text = "Hi Team: \n";// Replace with your message.

                    String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            return true;

        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        Cart=menu.findItem(R.id.my_cart);
        final MenuItem cart = menu.findItem(R.id.my_cart);
        View view=  cart.getActionView();
        CartCount=view.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(this).getCartCount(),CartCount);
        view.setOnClickListener(view1 -> onOptionsItemSelected(cart));


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

        if (item.getItemId()==R.id.active_orders)
            startActivity(new Intent(MainActivity.this,OrdersActivity.class));
        return false;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        slider_flag=false;
        fetchHomeContent();
    }

    @Override
    public void inc_dec(int pid, boolean is_inc,int current_position) {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        if (is_inc)
            map.put("IS_INC",String.valueOf(1));
        else
            map.put("IS_INC",String.valueOf(0));

        map.put("PID",String.valueOf(pid));

        Call<com.vugido.online_groceries.models.cart.addtocart.Response> call=RetrofitBuilder.getInstance().getRetrofit().addToCart(map);

        call.enqueue(new Callback<com.vugido.online_groceries.models.cart.addtocart.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.online_groceries.models.cart.addtocart.Response> call,
                                   @NonNull retrofit2.Response<com.vugido.online_groceries.models.cart.addtocart.Response> response) {


                if(response.isSuccessful()){

                    if(response.body().isSTATUS()){
                        //ok update the cc count..
                        productModelList1.get(current_position).setCARTCOUNT(response.body().getCC());
                        productsAdapter.notifyItemChanged(current_position);

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.online_groceries.models.cart.addtocart.Response> call, @NonNull Throwable t) {

            }
        });


    }


}

