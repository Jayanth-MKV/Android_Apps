package com.vugido_business_panel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.vugido_business_panel.R;
import com.vugido_business_panel.Retrofit.RetrofitBuilder;
import com.vugido_business_panel.adapter.OrderedItemAdapter;
import com.vugido_business_panel.adapter.PendingOrdersAdapter;
import com.vugido_business_panel.app_congif.MyPreferences;
import com.vugido_business_panel.models.orders.DATAItem;
import com.vugido_business_panel.models.orders.Response;
import com.vugido_business_panel.services.RingToneService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class Orders extends AppCompatActivity implements PendingOrdersAdapter.OrderAcceptance {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView toolbar_title;
    private LottieAnimationView progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        toolbar=findViewById(R.id.all_products_toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        recyclerView=findViewById(R.id.all_products_recycler_view);
        progress=findViewById(R.id.lottieProgress);

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        // get cid
        if(getIntent().getIntExtra("V",0)==0){
            toolbar_title.setText("Pending Orders");

        }else{
            toolbar_title.setText("On-Going Orders");

        }

        stopSService();

        fetchOrders();
    }

    public void stopSService() {
        Intent serviceIntent = new Intent(this, RingToneService.class);
        stopService(serviceIntent);
    }
    private void fetchOrders() {


        Log.e("fetched","ok");
        int v=getIntent().getIntExtra("V",0);
        progress.setVisibility(View.VISIBLE);
        progress.setSpeed(1.5f);
        progress.playAnimation();
        Map<String,Object> map=new HashMap<>();

        map.put("CID",new MyPreferences(this).getCID());
        map.put("STATUS",String.valueOf(v));
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getPendingOrders(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                assert response.body() != null;
                if(!response.body().isError()){

                    setData(response.body().getDATA());

                }else {
                    //no data

                    Toast.makeText(getApplicationContext(),"No Orders",Toast.LENGTH_LONG).show();
                }

                progress.pauseAnimation();
                progress.cancelAnimation();
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progress.pauseAnimation();
                progress.cancelAnimation();
                progress.setVisibility(View.GONE);

            }
        });
    }

    private void setData(List<DATAItem> data) {

        Collections.reverse(data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        PendingOrdersAdapter pendingOrdersAdapter=new PendingOrdersAdapter(data,this);
        recyclerView.setAdapter(pendingOrdersAdapter);


    }

    @Override
    public void orderAcceptance(int oid, int tno, int uid) {

        Log.e("ok","1");
        Map<String ,Object> map=new HashMap<>();
        map.put("OID",String.valueOf(oid));
        map.put("TNO",String.valueOf(tno));
        map.put("UID",String.valueOf(uid));
        map.put("CID",new MyPreferences(this).getCID());

        Call<com.vugido_business_panel.models.status.Response> call=RetrofitBuilder.getInstance().getRetrofit().orderAcceptance(map);

        call.enqueue(new Callback<com.vugido_business_panel.models.status.Response>() {


            @Override
            public void onResponse(Call<com.vugido_business_panel.models.status.Response> call, retrofit2.Response<com.vugido_business_panel.models.status.Response> response) {

                Log.e("response",response.toString());
                assert response.body() != null;
                if(!response.body().isError()){

                    //ok refreshAdapter...
                    Log.e("ok","went");
                    fetchOrders();
                }
            }

            @Override
            public void onFailure(Call<com.vugido_business_panel.models.status.Response> call, Throwable t) {
                Log.e("failed",t.toString());
            }
        });
    }
}
