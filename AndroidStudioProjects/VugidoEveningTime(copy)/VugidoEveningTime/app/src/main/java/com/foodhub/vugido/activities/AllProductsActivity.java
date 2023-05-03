package com.foodhub.vugido.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AllProductsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    int CID;
   // private View Progress;
    private SearchView searchView;
  //  private GridRecyclerViewAdapter gridRecyclerViewAdapter;

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
        setContentView(R.layout.activity_all_products);
        toolbar=findViewById(R.id.all_products_toolbar);
        recyclerView=findViewById(R.id.all_products_recycler_view);
//        Progress=findViewById(R.id.my_progress);
//
//        Progress.setVisibility(View.INVISIBLE);


        // get the title from the intent ... also the category intent..

        CID=getIntent().getIntExtra("CID",0);
        String title=getIntent().getStringExtra("TITLE");

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        fetchAllCategoryProducts(CID);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





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
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        //Progress.setVisibility(View.VISIBLE);


//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchAllProducts(map);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(response.body().isSTATUS()){
//                        //  ok data fetched
//                        List<com.foodhub.vugido.models.updated.all_products_of_category.DATAItem> dataItemList=response.body().getDATA();
//                        setRecyclerViewItems(dataItemList);
//
//                    }else{
//
//                        // no data
//                    }
//
//
//                }else {
//
//                    // no
//                }
//
//
//                //Progress.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//
//                //Progress.setVisibility(View.INVISIBLE);
//            }
//        });



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

//        if(gridRecyclerViewAdapter!=null)
//            gridRecyclerViewAdapter.getFilter().filter(newText);
        return true;
    }
}
