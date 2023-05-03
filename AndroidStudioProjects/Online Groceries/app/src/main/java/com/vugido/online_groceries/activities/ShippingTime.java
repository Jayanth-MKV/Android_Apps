package com.vugido.online_groceries.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.models.shipping.DATA;
import com.vugido.online_groceries.models.shipping.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.online_groceries.activities.MainActivity.ORDER_PLACED_CODE;

public class ShippingTime extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Toolbar toolbar;
    private TextView d1,d2,o;
    private CheckBox c1,c2;
    private Button proceed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        toolbar=findViewById(R.id.shipping_toolbar);

        toolbar.setTitle("Delivery Time");

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        proceed=findViewById(R.id.button3);
        d1=findViewById(R.id.textView42);
        d2=findViewById(R.id.textView44);


        proceed.setEnabled(false);
        proceed.setOnClickListener(v -> {

            new MyPreferences(getApplicationContext()).setIsTomorrow(c2.isChecked());

            Intent intent=new Intent(ShippingTime.this,CheckOutActivity.class);
            startActivityForResult(intent,ORDER_PLACED_CODE);

        });
        o=findViewById(R.id.textView46);

        c1=findViewById(R.id.checkBox);
        c2=findViewById(R.id.checkBox2);

        c1.setOnCheckedChangeListener(this);
        c2.setOnCheckedChangeListener(this);


        fetchShippingInfo();


    }




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


    private void fetchShippingInfo() {


        loginLoader(true,"Getting your food...");
        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());



        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getShippingInfo(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                loginLoader(false,"");
                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok in cart products..

                        DATA FetcheddataItem=response.body().getDATA();

                        setAdapter(FetcheddataItem);

                        ///////// CHECK CART ELIGIBILITY REQUEST...

                    }else {

                        c1.setEnabled(false);

                        c2.setEnabled(false);
                        d1.setText(response.body().getDATA().getTODAYDESCRIPTION());
                        o.setText(response.body().getDATA().getOTHERS());
                        d2.setText(response.body().getDATA().getTOMORROWDESCRIPTION());
                        o.setVisibility(View.VISIBLE);

                        // empty cart..
                        Log.e("response",response.toString());

//                        PriceLayout.setVisibility(View.INVISIBLE);
//                        CartEmpty.setVisibility(View.VISIBLE);
//                        TextCartEmpty.setVisibility(View.VISIBLE);
                    }
                }else {
                    // in problem
                    // Log.e("response",response.toString());

                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Log.e("response",t.toString());
                loginLoader(false,"");
            }
        });
    }

    private void setAdapter(DATA fetcheddataItem) {

        c1.setEnabled(fetcheddataItem.isTODAY());

        o.setVisibility(View.INVISIBLE);
        c2.setEnabled(fetcheddataItem.isTOMORROW());


        d1.setText(fetcheddataItem.getTODAYDESCRIPTION());
        d2.setText(fetcheddataItem.getTOMORROWDESCRIPTION());

        o.setText(fetcheddataItem.getOTHERS());



    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        if (buttonView.getId()==R.id.checkBox)
        {

            if (isChecked)
                c2.setChecked(false);

        }else {
            if (isChecked)
                c1.setChecked(false);
        }

        proceed.setEnabled(c1.isChecked() || c2.isChecked());

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==ORDER_PLACED_CODE && resultCode==RESULT_OK)
        {
            setResult(RESULT_OK);
            finish();
        }


        super.onActivityResult(requestCode, resultCode, data);


    }

}
