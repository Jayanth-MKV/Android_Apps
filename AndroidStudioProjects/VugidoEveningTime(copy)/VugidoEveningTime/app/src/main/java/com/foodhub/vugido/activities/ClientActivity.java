package com.foodhub.vugido.activities;

import android.animation.Animator;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.product.MenuCategoryAdapter;
import com.foodhub.vugido.adapters.product.ProductListRecyclerViewAdapter;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.models.clients_menu.Response;
import com.foodhub.vugido.models.product.MenuModel;
import com.foodhub.vugido.models.product.ProductModel;
import com.foodhub.vugido.models.product.client_products.DATA;
import com.foodhub.vugido.models.product.client_products.FOODSItem;
import com.foodhub.vugido.network.RetrofitBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;

public class ClientActivity  extends AppCompatActivity implements ProductListRecyclerViewAdapter.INC_DEC,
        MenuCategoryAdapter.MenuCatClicked, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private Toolbar toolbar;
    RecyclerView recyclerView;
//    private boolean isOpen=false;
//    FloatingActionButton close;
//    ExtendedFloatingActionButton seeCategories;
//    private CardView menu;
    //ConstraintLayout yourView;
    TextView cc,cp;

    List<FOODSItem> foodsItemList;

    SearchView searchView;


    View goCart;

    MyDialogLoader myDialogLoader;
    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 23) {


            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        }else if(Build.VERSION.SDK_INT>23){
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        setContentView(R.layout.client_activity);

        toolbar=findViewById(R.id.include2);
        cc=findViewById(R.id.textView16);
        cp=findViewById(R.id.textView17);
        recyclerView=findViewById(R.id.rcc);

        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));
        goCart=findViewById(R.id.include3);
        goCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClientActivity.this,MyCartActivity.class);
                intent.putExtra("CID",getIntent().getIntExtra("CID",0));
                startActivityForResult(intent,ORDER_PLACED_CODE);
            }
        });
//        seeCategories=findViewById(R.id.ex);
//        crec=findViewById(R.id.cat_recycler_view);
//        close=findViewById(R.id.floatingActionButton);
        toolbar.setTitle(getIntent().getStringExtra("NAME"));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back_w);
        toolbar.setNavigationOnClickListener(view -> finish());

//        menu=findViewById(R.id.seecatrec);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchProducts);

//        close.setOnClickListener(v -> {
//            circularRevealCard(menu);
//            seeCategories.setVisibility(View.VISIBLE);
//            close.setVisibility(View.GONE);
//        });
//        seeCategories.setOnClickListener(v -> {
//            circularRevealCard(menu);
//            seeCategories.setVisibility(View.GONE);
//            close.setVisibility(View.VISIBLE);
//        });


        fetchProducts();



//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        crec.setLayoutManager(layoutManager);
//        MenuCategoryAdapter menuCategoryAdapter=new MenuCategoryAdapter(menuModelList,this);
//        crec.setAdapter(menuCategoryAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));
        fetchProducts();

        if(requestCode==ORDER_PLACED_CODE){

            if(resultCode==RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }

    }

    private void fetchProducts() {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("CID",String.valueOf(getIntent().getIntExtra("CID",0)));

        Call<com.foodhub.vugido.models.product.client_products.Response> call=
                RetrofitBuilder.getInstance().getRetrofit().cid_menu(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.product.client_products.Response>() {
            @Override
            public void onResponse(Call<com.foodhub.vugido.models.product.client_products.Response> call, retrofit2.Response<com.foodhub.vugido.models.product.client_products.Response> response) {
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
            public void onFailure(Call<com.foodhub.vugido.models.product.client_products.Response> call, Throwable t) {

            }
        });




    }
    ProductListRecyclerViewAdapter productListRecyclerViewAdapter;
    private void bindData(DATA data) {


        foodsItemList=data.getFOODS();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        productListRecyclerViewAdapter=new ProductListRecyclerViewAdapter(data.getFOODS(),this,getIntent().getIntExtra("CID",0));
        recyclerView.setAdapter(productListRecyclerViewAdapter);

        setTotalPrice(foodsItemList);

    }

//    private void circularRevealCard(View view){
//        float finalRadius = Math.max(view.getWidth(), view.getHeight());
//
//
//        if(!isOpen) {
//
//            int x = view.getRight();
//            int y = view.getBottom();
//
//            int startRadius = 0;
//            int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
//            // create the animator for this view (the start radius is zero)
//            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
//            //circularReveal.setDuration(300);
//
//            // make the view visible and start the animation
//            view.setVisibility(View.VISIBLE);
//
//            circularReveal.start();
//            circularReveal.addListener(this);
//
//            isOpen=true;
//            seeCategories.setText("Close");
//            seeCategories.setBackgroundColor(Color.WHITE);
//            seeCategories.setTextColor(getResources().getColor(R.color.gradient_start_color));
//        }else {
//            int x = view.getRight();
//            int y = view.getBottom();
//
//            int startRadius =(int) Math.hypot(view.getWidth(), view.getHeight()) ;
//            int endRadius = 0;
//            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
//
//            circularReveal.start();
//            // view.setVisibility(View.INVISIBLE);
//            isOpen=false;
//
//            circularReveal.addListener(this);
//            seeCategories.setBackgroundColor(getResources().getColor(R.color.mred));
//            seeCategories.setText("Browse Food Menu");
//            seeCategories.setTextColor(Color.WHITE);
//        }
//    }
//
//
//    @Override
//    public void onAnimationStart(Animator animation) {
//
//    }
//
//    @Override
//    public void onAnimationEnd(Animator animation) {
//
//        if(!isOpen){
//            menu.setVisibility(View.INVISIBLE);
//        }else {
//            menu.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public void onAnimationCancel(Animator animation) {
//
//    }

//
//    @Override
//    public void menuClicked(int id) {
//
//
//        circularRevealCard(menu);
//        seeCategories.setVisibility(View.VISIBLE);
//        close.setVisibility(View.GONE);
//
//        //now using id navigate to that menu...
//
//        //homePageMainAdapter
//        int p=0;
//        for(int i =0 ;i<homePageMainModelList.size();i++){
//            if(homePageMainModelList.get(i).getHID()==id){
//                p=i;
//                break;
//            }
//        }
//
//        float y = HomeMainRecyclerView.getChildAt(p).getY();
//
//        nestedScrollView.fling(0);
//        nestedScrollView.setSmoothScrollingEnabled(true);
//        nestedScrollView.smoothScrollTo(0, (int) y);
//        //  layoutManager.smoothScrollToPosition(HomeMainRecyclerView,null,3);
//        //HomeMainRecyclerView.smoothScrollToPosition(3);
//    }
//    @Override
//    public void onAnimationRepeat(Animator animation) {
//
//    }


    @Override
    public void menuClicked(int id) {

    }

    @Override
    public void onRefresh() {
        fetchProducts();
    }

//    @Override
//    public void clicked_add(int cid,int pid,int price) {
//
//        String x=new MyPreferences(this).getCartProducts();
//
//        if (x==null)
//            x=cid+","+pid;
//        else
//            x=x+";"+cid+","+pid;
//
//        new MyPreferences(this).setCartProducts(x);
//
//        new MyPreferences(this).setCartCount(x.split(";").length);
//
//        new MyPreferences(this).setCartPrice(new MyPreferences(this).getCartPrice()+price);
//
//        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
//        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));
//
//    }

    @Override
    public void inc_dec(int pid, boolean is_inc, int position, int cid) {

        Log.e("clicked","ok");
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        if (is_inc)
            map.put("IS_INC",String.valueOf(1));
        else
            map.put("IS_INC",String.valueOf(0));

        map.put("PID",String.valueOf(pid));
        map.put("CID",String.valueOf(cid));

        Call<com.foodhub.vugido.models.cart.add_to_cart.Response> call=RetrofitBuilder.getInstance().getRetrofit().toCart(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.cart.add_to_cart.Response>() {
            @Override
            public void onResponse(Call<com.foodhub.vugido.models.cart.add_to_cart.Response> call,
                                   retrofit2.Response<com.foodhub.vugido.models.cart.add_to_cart.Response> response) {

                if(response.isSuccessful()){

                    if(response.body().isSTATUS()){
                        //ok update the cc count..
                        foodsItemList.get(position).setCC(response.body().getCC());
                        productListRecyclerViewAdapter.notifyItemChanged(position);

                        setTotalPrice(foodsItemList);
                    }
                }
            }

            @Override
            public void onFailure(Call<com.foodhub.vugido.models.cart.add_to_cart.Response> call,
                                  Throwable t) {

            }
        });

    }

    private void setTotalPrice(List<FOODSItem> dataItemList) {

        float price=0;
        int ccc=0;
        for(FOODSItem dataItem:dataItemList){

            if(dataItem.getINSTOCK()==0){

                ccc+=dataItem.getCC();
                if(dataItem.getOFFER()>0){
                    price+= offerPercentagePrice(dataItem.getOFFER(),dataItem.getPRICE()*dataItem.getCC());


                }else {

                    price+= dataItem.getPRICE()*dataItem.getCC();

                }

            }

        }









        new MyPreferences(this).setCartCount(ccc);

        new MyPreferences(this).setCartPrice(price);

        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));
//        checkCartContinueButton();

//        if(price<=0){
//            CartEmpty.setVisibility(View.VISIBLE);
//            TextCartEmpty.setVisibility(View.VISIBLE);
//        }else{
//            CartEmpty.setVisibility(View.GONE);
//            TextCartEmpty.setVisibility(View.GONE);
//        }



    }

    private float offerPercentagePrice(float offer, float price) {

        float x=((price*offer)/100f);
        return price-x;

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
           // searchView.setBackgroundColor(Color.RED);
           /* searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);*/
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        if(productListRecyclerViewAdapter!=null)
            productListRecyclerViewAdapter.getFilter().filter(newText);
        return false;
    }
}
