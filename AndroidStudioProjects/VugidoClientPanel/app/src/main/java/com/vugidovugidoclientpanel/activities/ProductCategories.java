package com.vugidovugidoclientpanel.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.vugidovugidoclientpanel.R;
import com.vugidovugidoclientpanel.Retrofit.RetrofitBuilder;
import com.vugidovugidoclientpanel.adapter.MainCategoriesRecyclerViewAdapter;
import com.vugidovugidoclientpanel.app_congif.MyPreferences;
import com.vugidovugidoclientpanel.models.Categories.mainCategories.DATAItem;
import com.vugidovugidoclientpanel.models.Categories.mainCategories.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductCategories extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView recyclerView;
    LottieAnimationView progress;
    TextView toolbar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_categories);


        toolbar=findViewById(R.id.product_categories_toolbar);
        progress=findViewById(R.id.lottieProgress);

        toolbar_title=findViewById(R.id.toolbar_title);

        toolbar_title.setText("My Inventory");
        setSupportActionBar(toolbar);




        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.admin_action_recycler_view);


       // fetchAllAdminCategories();


    }

  /*  private void fetchAllAdminCategories() {

        progress.setVisibility(View.VISIBLE);
        progress.setSpeed(1.5f);
        progress.playAnimation();
        Map<String, Object> map=new HashMap<>();
        map.put("CID",new MyPreferences(this).getCID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchMainCategories(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        List<DATAItem> dataItemList=response.body().getDATA();

                        bindToRecyclerView(dataItemList);


                    }else {

                        Toast.makeText(getApplicationContext(),"No Categories",Toast.LENGTH_LONG).show();

                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Error .. Try Again",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                Toast.makeText(getApplicationContext(),"Network Error .. Try Again",Toast.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
            }
        });

    }*/

    private void bindToRecyclerView(List<DATAItem> dataItemList) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MainCategoriesRecyclerViewAdapter mainCategoriesRecyclerViewAdapter=new MainCategoriesRecyclerViewAdapter(dataItemList,this);
        recyclerView.setAdapter(mainCategoriesRecyclerViewAdapter);



    }
}
