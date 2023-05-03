package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

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
import com.foodhub.vugido.adapters.CoinsTransactionAdapter;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.Transactions.DATAItem;
import com.foodhub.vugido.models.Transactions.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class CoinsTransactionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    View Progress;
    private RecyclerView searchRecyclerView;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_transaction);
        textView=findViewById(R.id.textView40);
        Progress=findViewById(R.id.my_progress);
        textView.setVisibility(View.GONE);
        Progress.setVisibility(View.INVISIBLE);
        toolbar=findViewById(R.id.search_toolbar);
        toolbar.setTitle("Coins Transaction");
        setSupportActionBar(toolbar);
        Progress=findViewById(R.id.my_progress);
        Progress.setVisibility(View.GONE);
        searchRecyclerView=findViewById(R.id.transaction_recycler_view);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());



        fetchCoinsTransaction();


    }

    private void fetchCoinsTransaction() {

        Progress.setVisibility(View.VISIBLE);


        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").fetchCoinsTransaction(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                Progress.setVisibility(View.GONE);


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok

                        bindData(response.body().getDATA());
                    }else {
                        // no data.....
                        textView.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Progress.setVisibility(View.GONE);
            }
        });
    }

    private void bindData(List<DATAItem> data) {

        Collections.reverse(data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecyclerView.setLayoutManager(linearLayoutManager);
        CoinsTransactionAdapter coinsTransactionAdapter=new CoinsTransactionAdapter(this,data);
        searchRecyclerView.setAdapter(coinsTransactionAdapter);

    }
}
