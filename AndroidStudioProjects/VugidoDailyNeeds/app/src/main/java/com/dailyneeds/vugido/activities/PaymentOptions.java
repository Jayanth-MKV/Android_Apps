package com.dailyneeds.vugido.activities;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.dialogs.PlaceOrder;
import com.dailyneeds.vugido.dialogs.ResponseStatus;
import com.dailyneeds.vugido.models.FinalOrderableProducts;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaymentOptions extends AppCompatActivity implements View.OnClickListener, PlaceOrder.InitiatePlaceOrder,
        NetworkQueries.NetworkQueryInterface {
    Button COD;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_options);
        toolbar=findViewById(R.id.toolbar);
        COD=findViewById(R.id.cod_button);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        COD.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.cod_button:
                showFragment();
                break;
        }
    }

    private void showFragment(){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        Fragment fragment=getSupportFragmentManager().findFragmentByTag("PLACE_ORDER");
        if(fragment!=null){
            fragmentTransaction.remove(fragment);

        }
        PlaceOrder placeOrder=new PlaceOrder();
        placeOrder.show(fragmentTransaction,"PLACE_ORDER");

    }


    @Override
    public void initiatePlaceOrder(boolean status) {


        if(status){
            // send network request

            NetworkQueries networkQueries=new NetworkQueries(this, ConfigVariables.PLACE_ORDER,buildParams());
            networkQueries.sendRequest("Placing Order");


        }


    }



    @Override
    public void networkQueryInterface(String Response) {


        Log.e("DATA",Response);


        //check the response and show user ....

        try {
            JSONObject jsonObject=new JSONObject(Response);



            try {
                final boolean error=jsonObject.getBoolean("error");
                final ResponseStatus responseStatus=new ResponseStatus();
                Bundle bundle=new Bundle();
                bundle.putBoolean("error",error);

                if(!error){
                    bundle.putString("msg","Order Placed Successfully");
                    // verified successfully..

                }else {

                    bundle.putString("msg","Order Not Placed, Try again..");
                    // not a valid otp..

                }
                responseStatus.setArguments(bundle);
                responseStatus.show(getSupportFragmentManager(),"STATUS");

                int TIME = 2000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        responseStatus.dismiss();
                        if(!error){
                            // also here set all the preferences...


                           // now here remove all activities from the stack....

                            setResult(ConfigVariables.ORDER_PLACED_RESPONSE_CODE);
                            finish();


                        }
                    }
                }, TIME);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void networkQueryError(String error) {

        Log.e("error",error);

    }

    private Map<String,String> buildParams(){
        FinalOrderableProducts finalOrderableProducts= Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")).getParcelable("FINAL");
        Map<String,String> params=new HashMap<>();
        if(finalOrderableProducts!=null) {
            params.put("UID", new MyPreferences(this).getUID());
            params.put("TIME", ConfigVariables.getCurrentTime());
            params.put("DATE", ConfigVariables.getCurrentDate());
            params.put("COUNT", String.valueOf(finalOrderableProducts.getCartProductsList().size()));
            params.put("LOCATION", finalOrderableProducts.getLocation());
            params.put("LAT", String.valueOf(finalOrderableProducts.getLATITUDE()));
            params.put("LON", String.valueOf(finalOrderableProducts.getLONGITUDE()));
            params.put("DC", String.valueOf(finalOrderableProducts.getDeliveryCharges()));
            params.put("D", String.valueOf(finalOrderableProducts.getDistance()));
            params.put("TOTAL_PRICE", String.valueOf(finalOrderableProducts.getActualTotalPrice()));
            params.put("DMODE", String.valueOf(finalOrderableProducts.getDMode()));
            params.put("SLOT", String.valueOf(finalOrderableProducts.getSlotTime()));
            Gson gson=new Gson();
            String MyFinalProducts=gson.toJson(finalOrderableProducts.getCartProductsList());

            params.put("LIST",MyFinalProducts);
        }
        return  params;

    }

}
