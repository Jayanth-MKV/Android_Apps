package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.SearchPage.SearchAdapter;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.search.search_submit.DATA;
import com.foodhub.vugido.models.search.search_suggestions.DATAItem;
import com.foodhub.vugido.models.search.search_suggestions.Response;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchAdapter.SEARCH_ID_CLICKED {

    private SearchView searchView;
    private List<DATAItem> searchSuggestionsList;
    private SearchAdapter searchAdapter;
    private Toolbar toolbar;
    View Progress;
    private RecyclerView searchRecyclerView;
    private boolean SF=false;
    private String pendingString="";
    private boolean f=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar=findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Search");
        Progress=findViewById(R.id.my_progress);
        Progress.setVisibility(View.GONE);
        searchRecyclerView=findViewById(R.id.search_recycler_view);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

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
        fetchSuggestions(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        //Toast.makeText(getContext(),newText,Toast.LENGTH_SHORT).show();
        if(!SF) {
            fetchSuggestions(newText);
        }else {
            f=true;
            pendingString=newText;
        }
        return true;
    }

    private void fetchSuggestions(final String newText) {

        SF=true;
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("SEARCH_ON",newText);
        Progress.setVisibility(View.VISIBLE);



        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).searchSuggestionsUrl(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
              Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //data got
                        searchRecyclerView.setVisibility(View.VISIBLE);
                        searchSuggestionsList=response.body().getDATA();
                        bindSuggestionsData(newText);
                    }else {
                        SF=false;
                        //no data..
                        searchRecyclerView.setVisibility(View.GONE);
                       // fetchUsersRecentSearches();
                        Toast.makeText(getApplicationContext(),"No Results",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    // error
                    SF=false;
                    if(f){
                        fetchSuggestions(pendingString);
                        f=false;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
               Progress.setVisibility(View.GONE);
               SF=false;
                if(f){
                    fetchSuggestions(pendingString);
                    f=false;
                }
            }
        });

    }

    private void bindSuggestionsData(String key) {

        if(searchAdapter==null){
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            searchRecyclerView.setLayoutManager(linearLayoutManager);
            //searchRecyclerView.addItemDecoration(new Space(1,5,false,0));
            searchAdapter=new SearchAdapter(searchSuggestionsList,this,key);
            searchRecyclerView.setAdapter(searchAdapter);


        }else {
            searchAdapter.setItems(searchSuggestionsList);
            searchAdapter.notifyDataSetChanged();
        }

        SF=false;

        if(f){
            fetchSuggestions(pendingString);
            f=false;
        }

    }

    @Override
    public void searchID_Clicked(int SID,int CID) {


        if(CID==1){

            // single product
            fetchProductsData(SID);

        }else {
            // for multiple products..
            Intent intent=new Intent(this,SearchOnClickResultDisplayActivity.class);
            intent.putExtra("SID",SID);
            startActivity(intent);
        }


    }


    private void fetchProductsData(int sid){

        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("SID",String.valueOf(sid));

        Call<com.foodhub.vugido.models.search.search_submit.Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getSearchOnClickResults(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.search.search_submit.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.search.search_submit.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.search.search_submit.Response> response) {


                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok data got

                        DATA data= response.body().getDATA();
                        callProductDetailsActivity(data.getDATA().get(0));
                    }else {
                        // no data
                        Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.search.search_submit.Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
            }
        });


    }


    private void callProductDetailsActivity(com.foodhub.vugido.models.search.search_submit.DATAItem ProductItem) {
        Intent intent=new Intent(this, ProductDetailsActivity.class);
        Bundle bundle=new Bundle();
        BaseProduct baseProduct =new BaseProduct();
        baseProduct.setOFFERSTATUS(ProductItem.getOFFERSTATUS());
        baseProduct.setENAME(ProductItem.getENAME());
        baseProduct.setTNAME(ProductItem.getTNAME());
        baseProduct.setCARTLIMIT(ProductItem.getCARTLIMIT());
        baseProduct.setDESCRIPTION(ProductItem.getDESCRIPTION());
        baseProduct.setIMAGE(ProductItem.getIMAGE());
        baseProduct.setINSTOCK(ProductItem.getINSTOCK());
        baseProduct.setISGRAMSET(ProductItem.getISGRAMSET());
        baseProduct.setOFFER(ProductItem.getOFFER());
        baseProduct.setOFFERLIMIT(ProductItem.getOFFERLIMIT());
        baseProduct.setPID(ProductItem.getPID());
        baseProduct.setPRICE(ProductItem.getPRICE());
        baseProduct.setQUANTITY(ProductItem.getQUANTITY());
        baseProduct.setSID(ProductItem.getSID());
        bundle.putParcelable("BASE_OBJECT",baseProduct);
        bundle.putInt("CID",ProductItem.getCID());
        intent.putExtra("BUNDLE",bundle);
        startActivity(intent);
    }



}
