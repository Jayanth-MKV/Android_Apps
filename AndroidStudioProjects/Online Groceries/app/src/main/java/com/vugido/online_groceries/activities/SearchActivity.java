package com.vugido.online_groceries.activities;

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

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.SearchPage.SearchAdapter;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.models.base_product.BaseProduct;
import com.vugido.online_groceries.models.search_clicked.DATA;
import com.vugido.online_groceries.models.search_clicked.PRODUCT;
import com.vugido.online_groceries.models.search_indexer.DATAItem;
import com.vugido.online_groceries.models.search_indexer.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.online_groceries.activities.MainActivity.ORDER_PLACED_CODE;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener , SearchAdapter.SEARCH_ID_CLICKED {

    private SearchView searchView;
    private List<DATAItem> searchSuggestionsList;
    private SearchAdapter searchAdapter;
    private Toolbar toolbar;
    //View Progress;
    private RecyclerView searchRecyclerView;
    private boolean SF=false;
    private String pendingString="";
    private boolean f=false;

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
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar=findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Search");
//        Progress=findViewById(R.id.my_progress);
//        Progress.setVisibility(View.GONE);
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
        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("SEARCH_ON",newText);


       // loginLoader(true,"Searching...");

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().searchSuggestionsUrl(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
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
                        Toast.makeText(getApplicationContext(),"No Results", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    // error
                    SF=false;
                    if(f){
                        fetchSuggestions(pendingString);
                        f=false;
                    }
                }
               // loginLoader(false,"Searching...");

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
               SF=false;
                //loginLoader(false,"Searching...");

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
    public void searchID_Clicked(int SID,int CID,int source_id,String key) {


        if(CID==1){

            // single product
            fetchProductsData(SID,source_id);

        }else {

            // launch category of products...
            launchCategoryActivity(SID,source_id,key);

        }


    }

    private void launchCategoryActivity(int sid, int source_id, String key) {

        //launch categories activity here ...
        Intent intent=new Intent(this, AllProductsActivity.class);
        intent.putExtra("NAME",key);
        intent.putExtra("CID",source_id);
        startActivityForResult(intent,ORDER_PLACED_CODE);

    }


    private void fetchProductsData(int sid,int source_id){

        loginLoader(true,"Searching...");

        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("SID", String.valueOf(sid));

        Call<com.vugido.online_groceries.models.search_clicked.Response> call=
                RetrofitBuilder.getInstance().getRetrofit().getSearchOnClickResults(map);

        call.enqueue(new Callback<com.vugido.online_groceries.models.search_clicked.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.online_groceries.models.search_clicked.Response> call, @NonNull retrofit2.Response<com.vugido.online_groceries.models.search_clicked.Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok data got

                        DATA data= response.body().getDATA();
                        callProductDetailsActivity(data);
                    }else {
                        // no data
                        Toast.makeText(getApplicationContext(),"No Data", Toast.LENGTH_LONG).show();
                    }
                }
                loginLoader(false,"Searching...");

            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.online_groceries.models.search_clicked.Response> call, @NonNull Throwable t) {
                loginLoader(false,"Searching...");

            }
        });


    }


    private void callProductDetailsActivity(DATA d) {
        Intent intent=new Intent(this, ProductDetailsActivity.class);
        Bundle bundle=new Bundle();
       //
        BaseProduct baseProduct =new BaseProduct();

        PRODUCT data=d.getPRODUCT();
        baseProduct.setbID(data.getBID());
        baseProduct.setiD(data.getID());
        baseProduct.setcARTCOUNT(data.getCARTCOUNT());
        baseProduct.setcARTID(data.getCARTID());
        baseProduct.setcID(data.getCID());
        baseProduct.setdESCRIPTION(data.getDESCRIPTION());
        baseProduct.sethOMEENABLED(data.getHOMEENABLED());
        baseProduct.settITLE(data.getTITLE());
        baseProduct.setqUANTITY(data.getQUANTITY());
        baseProduct.setpRICE(data.getPRICE());
        baseProduct.setoFFERENABLED(data.getOFFERENABLED());
        baseProduct.setiNSTOCK(data.getINSTOCK());
        baseProduct.setiMAGE(data.getIMAGE());
        baseProduct.setoFFER(data.getOFFER());

        bundle.putParcelable("BASE_OBJECT",baseProduct);
        intent.putExtra("BUNDLE",bundle);
        startActivityForResult(intent,ORDER_PLACED_CODE);
    }



}
