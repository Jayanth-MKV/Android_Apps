package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

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

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.SearchPage.SearchOnClickResultsAdapter;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.search.search_submit.DATA;
import com.foodhub.vugido.models.search.search_submit.DATAItem;
import com.foodhub.vugido.models.search.search_submit.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchOnClickResultDisplayActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    private View Progress;
    private SearchView searchView;
    private SearchOnClickResultsAdapter searchOnClickResultsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_on_click_result);
        toolbar=findViewById(R.id.all_search_data_toolbar);
        recyclerView=findViewById(R.id.all_search_recycler_view);
        Progress=findViewById(R.id.my_progress);

        Progress.setVisibility(View.INVISIBLE);


        fetchProductsData(getIntent().getIntExtra("SID",0));



    }



    private void fetchProductsData(int sid){

        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("SID",String.valueOf(sid));

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getSearchOnClickResults(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.search.search_submit.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.search.search_submit.Response> response) {


                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok data got

                        DATA data= response.body().getDATA();
                        actionOnSearchSubmitData(data);
                    }else {
                        // no data
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.search.search_submit.Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        return super.onOptionsItemSelected(item);
    }
    private void actionOnSearchSubmitData(DATA data) {


        List<DATAItem> dataItemList=data.getDATA();
        String searchKey=data.getSEARCHKEY();
        toolbar.setTitle(searchKey);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        searchOnClickResultsAdapter=new SearchOnClickResultsAdapter(dataItemList,this);
        recyclerView.setAdapter(searchOnClickResultsAdapter);



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(searchOnClickResultsAdapter!=null)
            searchOnClickResultsAdapter.getFilter().filter(newText);
        return true;

    }
}
