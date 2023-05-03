package com.vugido.vugidoinventorymanagement.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.textfield.TextInputEditText;
import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.adapters.GridRecyclerViewAdapter;
import com.vugido.vugidoinventorymanagement.dialogs.MyStatusDialog;
import com.vugido.vugidoinventorymanagement.models.all_products_of_category.DATAItem;
import com.vugido.vugidoinventorymanagement.models.all_products_of_category.Response;
import com.vugido.vugidoinventorymanagement.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AllProductsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, GridRecyclerViewAdapter.EditProduct {

    Toolbar toolbar;
    RecyclerView recyclerView;
    int CID;
    private View Progress;
    private SearchView searchView;
    private GridRecyclerViewAdapter gridRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        toolbar=findViewById(R.id.all_products_toolbar);
        recyclerView=findViewById(R.id.all_products_recycler_view);
        Progress=findViewById(R.id.my_progress);

        Progress.setVisibility(View.INVISIBLE);


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
        Progress.setVisibility(View.VISIBLE);


        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchAllProducts(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //  ok data fetched
                       // List<com.foodhub.vugido.models.updated.all_products_of_category.DATAItem> dataItemList=response.body().getDATA();
                        setRecyclerViewItems(response.body().getDATA());

                    }else{

                        // no data
                    }


                }else {

                    // no
                }


                Progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.INVISIBLE);
            }
        });



    }

    private void setRecyclerViewItems(List<DATAItem> dataItemList) {

      // List<AllProductsItem> productsItemList= dataItemList.get(0).getAllProducts();
       recyclerView.setLayoutManager(new GridLayoutManager(this,2));
       gridRecyclerViewAdapter=new GridRecyclerViewAdapter(dataItemList,this,CID);
        recyclerView.setAdapter(gridRecyclerViewAdapter);


    }

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

        if(gridRecyclerViewAdapter!=null)
            gridRecyclerViewAdapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public void editProduct(int cid, int pid,String name,float price) {

        myDialogBoxUpdater(cid,pid,name,price);

    }

    @Override
    public void toggleProduct(int cid, int pid) {

        Map<String,Object> map=new HashMap<>();
        map.put("CID",String.valueOf(cid));
        map.put("PID",String.valueOf(pid));

        Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call=RetrofitBuilder.getInstance().getRetrofit().toggleProduct(map);
        call.enqueue(new Callback<com.vugido.vugidoinventorymanagement.models.new_categories.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, retrofit2.Response<com.vugido.vugidoinventorymanagement.models.new_categories.Response> response) {

                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        showDialog(true,"Updated Successfully");
                    }else {
                        showDialog(false,"Not Updated ! Try Again !!");
                    }
                }

            }

            @Override
            public void onFailure(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, Throwable t) {

                Progress.setVisibility(View.GONE);
                showDialog(false,"Not Updated ! Try Again !!");


            }
        });



    }

    private void myDialogBoxUpdater(final int cid, final int pid, final String name, float price) {

        String Price;

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView=null;

        dialogView = inflater.inflate(R.layout.profits_get_orders_dialog, null);
        //Price= String.valueOf(price);


        // set pre data.....
        TextView textView=dialogView.findViewById(R.id.textView33);
        final TextInputEditText t=dialogView.findViewById(R.id.edit_n);
        final TextInputEditText pr=dialogView.findViewById(R.id.to);

        t.setText(name);
        pr.setText(String.valueOf(price));

        Button cancel =  dialogView.findViewById(R.id.button_cancel);
        Button update = dialogView.findViewById(R.id.button_update);
        Button delete=dialogView.findViewById(R.id.button);

        //textView.setText("Coins Available\n"+new MyPreferences(this).getCoinsCount());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete product...

                delete(cid,pid);
                dialogBuilder.dismiss();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //update ... price..name..

                updateEdit(cid,pid, Objects.requireNonNull(t.getText()).toString(), Objects.requireNonNull(pr.getText()).toString());
                dialogBuilder.dismiss();

            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();




    }

    private void delete(int cid, int pid){
        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("CID",String.valueOf(cid));
        map.put("PID",String.valueOf(pid));

        Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call=RetrofitBuilder.getInstance().getRetrofit().updateDeleteProduct(map);
        call.enqueue(new Callback<com.vugido.vugidoinventorymanagement.models.new_categories.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, retrofit2.Response<com.vugido.vugidoinventorymanagement.models.new_categories.Response> response) {

                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        showDialog(true,"Deleted Successfully");
                    }else {
                        showDialog(false,"Not Deleted ! Try Again !!");
                    }
                }

            }

            @Override
            public void onFailure(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, Throwable t) {

                Progress.setVisibility(View.GONE);
                showDialog(false,"Not Deleted ! Try Again !!");


            }
        });
    }


    private void updateEdit(int cid, int pid,String name, String price){
        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("CID",String.valueOf(cid));
        map.put("PID",String.valueOf(pid));
        map.put("NAME",name);
        map.put("PRICE",price);

        Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call=RetrofitBuilder.getInstance().getRetrofit().updateEditData(map);
        call.enqueue(new Callback<com.vugido.vugidoinventorymanagement.models.new_categories.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, retrofit2.Response<com.vugido.vugidoinventorymanagement.models.new_categories.Response> response) {

                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        showDialog(true,"Updated Successfully");
                    }else {
                        showDialog(false,"Not Updated ! Try Again !!");
                    }
                }

            }

            @Override
            public void onFailure(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, Throwable t) {

                Progress.setVisibility(View.GONE);
                showDialog(false,"Not Updated ! Try Again !!");


            }
        });
    }

    private void showDialog(boolean status,String msg){

        final MyStatusDialog s;
        s=new MyStatusDialog();
        Bundle bundle=new Bundle();

        if(status){
            bundle.putString("MSG",msg);
            bundle.putBoolean("STATUS",true);

            refreshData();


        }else {
            bundle.putString("MSG",msg);
            bundle.putBoolean("STATUS",false);

        }

        s.setArguments(bundle);
        s.show(getSupportFragmentManager(),"STATUS");


    }

    private void refreshData() {

        fetchAllCategoryProducts(CID);
    }

}
