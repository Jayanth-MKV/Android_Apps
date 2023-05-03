package com.foodhub.vugido.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.coins.CoinsTransactionAdapter;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.models.coins.DataItem;
import com.foodhub.vugido.models.coins.Response;
import com.foodhub.vugido.network.RetrofitBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class CoinsTransactionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView searchRecyclerView;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_transaction);
        textView=findViewById(R.id.textView40);
        textView.setVisibility(View.GONE);
        toolbar=findViewById(R.id.search_toolbar);
        toolbar.setTitle("Coins Transaction");
        setSupportActionBar(toolbar);
        searchRecyclerView=findViewById(R.id.transaction_recycler_view);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());



        fetchCoinsTransaction();


    }

    private void fetchCoinsTransaction() {



        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getCoinsTransactions(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {



                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok

                        bindData(response.body().getData());
                    }else {
                        // no data.....
                        textView.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
            }
        });
    }

    private void bindData(List<DataItem> data) {

        Collections.reverse(data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecyclerView.setLayoutManager(linearLayoutManager);
        CoinsTransactionAdapter coinsTransactionAdapter=new CoinsTransactionAdapter(this,data);
        searchRecyclerView.setAdapter(coinsTransactionAdapter);

    }
}
