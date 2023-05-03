package com.vugido.vugidoupdate.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.vugidoupdate.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AllProductsActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    int CID;
    //private View Progress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        toolbar=findViewById(R.id.all_products_toolbar);
        recyclerView=findViewById(R.id.all_products_recycler_view);
        //Progress=findViewById(R.id.my_progress);

       // Progress.setVisibility(View.INVISIBLE);


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

    private void fetchAllCategoryProducts(int cid) {

        Map<String, Object> map=new HashMap<>();
        map.put("CID", String.valueOf(cid));
      //  Progress.setVisibility(View.VISIBLE);


       /* Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchAllProducts(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //  ok data fetched
                        List<com.vugido.StudentTime.models.updated.all_products_of_category.DATAItem> dataItemList=response.body().getDATA();
                        setRecyclerViewItems(dataItemList);

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
        });*/



    }

  /*  private void setRecyclerViewItems(List<DATAItem> dataItemList) {

      // List<AllProductsItem> productsItemList= dataItemList.get(0).getAllProducts();
       recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        GridRecyclerViewAdapter gridRecyclerViewAdapter=new GridRecyclerViewAdapter(dataItemList,this,CID);
        recyclerView.setAdapter(gridRecyclerViewAdapter);


    }*/
}
