package com.vugido.foods.vugidodeliveryagent.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.foods.vugidodeliveryagent.R;
import com.vugido.foods.vugidodeliveryagent.adapters.ProductItemAdapter;
import com.vugido.foods.vugidodeliveryagent.models.orderedProducts.DATAItem;
import com.vugido.foods.vugidodeliveryagent.models.orderedProducts.Response;
import com.vugido.foods.vugidodeliveryagent.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class OrderedItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);
        recyclerView=findViewById(R.id.ordered_items_recycler_view);
        toolbar=findViewById(R.id.activity_toolbar);

        toolbar.setTitle("Ordered Items");


        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



        int OID= getIntent().getIntExtra("OID",0);


        fetchOrderedItems(OID);



    }

    private void fetchOrderedItems(int oid) {


        Log.e("OID",String.valueOf(oid));
        HashMap<String, Object> request = new HashMap<>();
        request.put("OID", String.valueOf(oid));

        Call<com.vugido.foods.vugidodeliveryagent.models.orderedProducts.Response> response= RetrofitBuilder.getInstance().getRetrofit().getOrderedProducts(request);

        response.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        bindDataToAdapter(response.body().getDATA());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });



    }

    private void bindDataToAdapter(List<DATAItem> data) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ProductItemAdapter productItemAdapter=new ProductItemAdapter(data,this);
        recyclerView.setAdapter(productItemAdapter);


    }


}
