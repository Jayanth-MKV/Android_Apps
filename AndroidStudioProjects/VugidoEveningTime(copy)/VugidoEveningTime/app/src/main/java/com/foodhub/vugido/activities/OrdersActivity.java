package com.foodhub.vugido.activities;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.myOrdersAdapters.MyOrdersAdapter;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.models.orders.ORDERSItem;
import com.foodhub.vugido.models.orders.Response;
import com.foodhub.vugido.network.RetrofitBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class OrdersActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    Button button;
    SwipeRefreshLayout swipeRefreshLayout;
    MyDialogLoader myDialogLoader;

    private void myDialogBox() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout_add_sub_cat, null);

        final TextView e =  dialogView.findViewById(R.id.textView24);


        if (new MyPreferences(this).getGmail()==null)
            e.setText(new MyPreferences(this).getGmail());
        else
            e.setText(new MyPreferences(this).getGmail());


        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 23) {


            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        }else if(Build.VERSION.SDK_INT>23){
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        setContentView(R.layout.activity_orders);

        toolbar=findViewById(R.id.include2);
        recyclerView=findViewById(R.id.rcc);
        button=findViewById(R.id.button4);


        toolbar.setTitle("Your Orders");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back_w);
        toolbar.setNavigationOnClickListener(view -> finish());

        button.setOnClickListener(v -> finish());

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);



        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchUserOrders);



    }

    private void fetchUserOrders() {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());


        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().orders(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok order placed successfully


                        bindData(response.body().getORDERS());
                    }else {


                        // order not placed..

                    }
                }else {

                    //error
                    Log.e("ok","not done");

                }
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void bindData(List<ORDERSItem> orders) {

        Collections.reverse(orders);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new MyOrdersAdapter(this,orders));


    }

    @Override
    public void onRefresh() {

        fetchUserOrders();
    }
}
