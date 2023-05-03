package com.vugido.online_groceries.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.fragments.bottom_sheet.PayMethodsBottomSheet;
import com.vugido.online_groceries.models.check_o.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class CheckOutActivity extends AppCompatActivity implements OnClickListener, PayMethodsBottomSheet.PAY_METHOD_INTERFACE {

    private   int DEL_C = 0;
    Toolbar toolbar;
    ScrollView scrollView;
    TextView DeliveryTime,DC,ItemPrice,TotalAmount,ItemsCount,d,da;
    MyPreferences myPreferences;
    Button pay;


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

    private void setAllPrices() {

        d.setText(new MyPreferences(getApplicationContext()).getDelTimeMsg());
        DC.setText(String.valueOf(new MyPreferences(getApplicationContext()).getDC()));
        TotalAmount.setText(String.format("Rs.%s/-", (new MyPreferences(getApplicationContext()).getDC() + new MyPreferences(getApplicationContext()).getCartPrice())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        d=findViewById(R.id.textView37);
        pay=findViewById(R.id.button4);

        DeliveryTime=findViewById(R.id.dt);
        toolbar=findViewById(R.id.checkOutToolbar);
        ItemPrice=findViewById(R.id.ItemsPrice);
        DC=findViewById(R.id.delivery_charges);
        TotalAmount=findViewById(R.id.total_amount);
        ItemsCount=findViewById(R.id.textView5);
        da=findViewById(R.id.textView38);
        myPreferences=new MyPreferences(this);
        toolbar.setTitle("Order CheckOut");

//        DC.setText("Rs."+getIntent().getFloatExtra("DC",0)+"/-");
//        ItemPrice.setText("Rs."+new MyPreferences(this).getCartPrice()+"/-");
//        ItemsCount.setText("Price ("+new MyPreferences(this).getOrderableCartCount()+" items)");
//        float t=new MyPreferences(this).getCartPrice()+getIntent().getFloatExtra("DC",0);
//        TotalAmount.setText("Rs."+t+"/-");


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scrollView=findViewById(R.id.CheckOutScrollView);

        da.setText(String.format("%s,%s,%s,%s", new MyPreferences(
                getApplicationContext()).getUserPrimaryAddress(),
                new MyPreferences(getApplicationContext()).getLM(),
                new MyPreferences(getApplicationContext()).getDNO(),
                new MyPreferences(getApplicationContext()).getUserMobile()));
        ItemsCount.setText(String.format("Price(%s Items)",
                new MyPreferences(getApplicationContext()).getCartCount()));
        ItemPrice.setText(String.valueOf(
                new MyPreferences(getApplicationContext()).getCartPrice()));
        pay.setOnClickListener(this);
        fetchCheckoutDC();


    }

    private void fetchCheckoutDC() {

        loginLoader(true,"Please wait..");

        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());

        Call<Response>call= RetrofitBuilder.getInstance().getRetrofit().fetchCheckOut(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                loginLoader(false,"");

                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(!response.body().isError()){

                        new MyPreferences(getApplicationContext()).setDC(Integer.parseInt(response.body().getDC()));
                        new MyPreferences(getApplicationContext()).setDelTimeMsg(response.body().getTIME());


                        setAllPrices();


                    }else {

                        Log.e("ok","not done");

                        // order not placed..

                    }
                }else {

                    //error
                    Log.e("ok","not done");

                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, Throwable t) {
                loginLoader(false,"");
            }
        });



    }





    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button4:

                // address aid, uid, delivery mode,slot id, slot name, dc, today or tomorrow


                //call the bottom sheet here

                PayMethodsBottomSheet payMethodsBottomSheet=new PayMethodsBottomSheet();
                payMethodsBottomSheet.show(getSupportFragmentManager(),"PAY_SHEET");

                break;
        }
    }

    private void placeOrder() {

        loginLoader(true,"Placing your order...just a moment!");
        pay.setEnabled(false);
        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("ITEMS_PRICE", String.valueOf(new MyPreferences(this).getCartPrice()));
        map.put("DC", String.valueOf(new MyPreferences(getApplicationContext()).getDC()));
        map.put("ITEM_COUNT", String.valueOf(new MyPreferences(getApplicationContext()).getCartCount()));
        map.put("LATITUDE",new MyPreferences(this).getLocLat());
        map.put("LONGITUDE",new MyPreferences(this).getLocLan());
        map.put("ADDRESS",new MyPreferences(this).getUserPrimaryAddress());
        map.put("LM",new MyPreferences(this).getLM()+","+new MyPreferences(this).getDNO());
        map.put("TODAY",String.valueOf(new MyPreferences(getApplicationContext()).getIsTomorrow()));



        Call<com.vugido.online_groceries.models.orders.order_placed.Response>call=
                RetrofitBuilder.getInstance().getRetrofit().placeOrder(map);

        call.enqueue(new Callback<com.vugido.online_groceries.models.orders.order_placed.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.online_groceries.models.orders.order_placed.Response> call,
                                   @NonNull retrofit2.Response<com.vugido.online_groceries.models.orders.order_placed.Response> response) {

                loginLoader(false,"");
                if(response.isSuccessful()){

                    if(!response.body().isError()){


                        // order placed successfully..
                        setResult(RESULT_OK);
                        finish();

                    }else {

                        // order placing error...


                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.online_groceries.models.orders.order_placed.Response> call, @NonNull Throwable t) {
                loginLoader(false,"");

            }
        });

    }

    @Override
    public void payMethodInterface() {
        placeOrder();
    }
}
