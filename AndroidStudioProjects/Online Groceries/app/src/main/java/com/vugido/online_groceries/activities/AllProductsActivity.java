package com.vugido.online_groceries.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.homepage.ProductsAdapter;
import com.vugido.online_groceries.adapters.product.category_based.CategoryBasedProductAdapter;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.design.Space;
import com.vugido.online_groceries.models.homepage.updated.HOMEPRODUCTSItem;
import com.vugido.online_groceries.models.product.categories.DATA;
import com.vugido.online_groceries.models.product.categories.PRODUCTSItem;
import com.vugido.online_groceries.models.product.categories.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AllProductsActivity extends AppCompatActivity implements ProductsAdapter.INC_DEC,SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    int CID;
    private SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        toolbar=findViewById(R.id.all_products_toolbar);
        recyclerView=findViewById(R.id.all_products_recycler_view);
        // get the title from the intent ... also the category intent..

        CID=getIntent().getIntExtra("CID",0);
        String title=getIntent().getStringExtra("NAME");

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        GridLayoutManager gridLayoutManager1=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager1);
        recyclerView.addItemDecoration(new Space(2,10,true,0));

        fetchAllCategoryProducts(CID);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(() -> fetchAllCategoryProducts(CID));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
    List<PRODUCTSItem> productModelList1;
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
           /* searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);*/
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }

    private void fetchAllCategoryProducts(int cid) {

        Map<String, Object> map=new HashMap<>();
        map.put("CID", String.valueOf(cid));
       // map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        Call<Response>call= RetrofitBuilder.getInstance().getRetrofit().getCategoryProducts(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                swipeRefreshLayout.setRefreshing(false);
                if(response.isSuccessful()){

                    if(!response.body().isSTATUS()){
                        //ok data
                        bindCategoriesData(response.body().getDATA());
                    }else{

                        //else no data
                    }

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });



    }
    CategoryBasedProductAdapter productsAdapter;
    private void bindCategoriesData(DATA data) {


        productModelList1=data.getPRODUCTS();
        productsAdapter=new CategoryBasedProductAdapter(productModelList1,this);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

//    private void setRecyclerViewItems(List<DATAItem> dataItemList) {
//
//      // List<AllProductsItem> productsItemList= dataItemList.get(0).getAllProducts();
//       recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//       gridRecyclerViewAdapter=new GridRecyclerViewAdapter(dataItemList,this,CID);
//        recyclerView.setAdapter(gridRecyclerViewAdapter);
//
//
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();

        if (productsAdapter!=null)
            productsAdapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public void onRefresh() {
        fetchAllCategoryProducts(CID);
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
