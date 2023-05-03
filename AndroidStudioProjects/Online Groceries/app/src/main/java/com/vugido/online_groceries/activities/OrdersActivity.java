package com.vugido.online_groceries.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.myOrdersAdapters.MyOrdersAdapter;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.models.orders.all_orders.ORDERSItem;
import com.vugido.online_groceries.models.orders.all_orders.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

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
        setContentView(R.layout.activity_orders);

        toolbar=findViewById(R.id.include2);
        recyclerView=findViewById(R.id.rcc);
        button=findViewById(R.id.button4);


        toolbar.setTitle("Your Orders");
        toolbar.setTitleTextColor(getResources().getColor(R.color.gradient_end_color));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
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


        Call<com.vugido.online_groceries.models.orders.all_orders.Response>
                call= RetrofitBuilder.getInstance().getRetrofit().orders(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call,
                                   @NonNull retrofit2.Response<Response> response) {


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

    }
}
