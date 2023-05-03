package com.foodhub.vugido.activities;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.fragments.bottom_sheet.PayMethodsBottomSheet;
import com.foodhub.vugido.models.check_o.Response;
import com.foodhub.vugido.network.RetrofitBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class CheckOutActivity extends AppCompatActivity implements OnClickListener, PayMethodsBottomSheet.PAY_METHOD_INTERFACE, CompoundButton.OnCheckedChangeListener {

    private   int DEL_C = 0;
    Toolbar toolbar;
    ScrollView scrollView;
    TextView DeliveryTime,DC,ItemPrice,TotalAmount,ItemsCount,d,da,Coupon,ct,ctp;
    MyPreferences myPreferences;
    Button pay;
    int CID,COUPON_CODE=0;
    ImageView coupon_image;

    CheckBox coupon_box;


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

    private void setAllPrices(float tp) {

        d.setText(new MyPreferences(getApplicationContext()).getDelTimeMsg());
        DC.setText(String.valueOf(new MyPreferences(getApplicationContext()).getDC()));
        TotalAmount.setText("Rs."+tp+"/-");
        //TotalAmount.setText(String.format("Rs.%s/-", (new MyPreferences(getApplicationContext()).getDC() + new MyPreferences(getApplicationContext()).getCartPrice())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_check_out);

        d=findViewById(R.id.textView37);
        pay=findViewById(R.id.button4);

        DeliveryTime=findViewById(R.id.dt);
        coupon_image=findViewById(R.id.imageView10);
        toolbar=findViewById(R.id.checkOutToolbar);
        ItemPrice=findViewById(R.id.ItemsPrice);
        DC=findViewById(R.id.delivery_charges);
        Coupon=findViewById(R.id.textView50);
        ct=findViewById(R.id.textView53);
        ctp=findViewById(R.id.textView54);
        TotalAmount=findViewById(R.id.total_amount);
        ItemsCount=findViewById(R.id.textView5);
        da=findViewById(R.id.textView38);
        myPreferences=new MyPreferences(this);
        toolbar.setTitle("Order CheckOut");
        CID=getIntent().getIntExtra("CID",0);
//        DC.setText("Rs."+getIntent().getFloatExtra("DC",0)+"/-");
//        ItemPrice.setText("Rs."+new MyPreferences(this).getCartPrice()+"/-");
//        ItemsCount.setText("Price ("+new MyPreferences(this).getOrderableCartCount()+" items)");
//        float t=new MyPreferences(this).getCartPrice()+getIntent().getFloatExtra("DC",0);
//        TotalAmount.setText("Rs."+t+"/-");
        coupon_box=findViewById(R.id.checkBox3);

        coupon_box.setOnCheckedChangeListener(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scrollView=findViewById(R.id.CheckOutScrollView);

        da.setText(String.format("%s,%s,%s,%s", new MyPreferences(getApplicationContext()).getUserPrimaryAddress(),
                new MyPreferences(getApplicationContext()).getLM(),new MyPreferences(getApplicationContext()).getDNO(),
                new MyPreferences(getApplicationContext()).getUserMobile()));
        ItemsCount.setText(String.format("Price(%s Items)",
                new MyPreferences(getApplicationContext()).getCartCount()));
        ItemPrice.setText(String.valueOf(new MyPreferences(getApplicationContext()).getCartPrice()));
        pay.setOnClickListener(this);
        fetchPrimaryAddress();


    }

    private void fetchPrimaryAddress() {

        loginLoader(true,"Please wait..");

        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("CID",String.valueOf(CID));

        Call<Response>call= RetrofitBuilder.getInstance().getRetrofit().fetchCheckOut(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                loginLoader(false,"");

                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(!response.body().isError()){

                        Log.e("ok","done");
                        new MyPreferences(getApplicationContext()).setDC(Integer.parseInt(response.body().getDC()));
                        new MyPreferences(getApplicationContext()).setDelTimeMsg(response.body().getTIME());


                        if(response.body().getCOUPON().isEXISTS()){

                            // ok show coupon....

                            if(response.body().getCOUPON().getCOUPONTHRESHOLD()<=(
                                    new MyPreferences(getApplicationContext()).getCartPrice())){


                                // ok we can apply coupon...
                                coupon_box.setVisibility(View.VISIBLE);
                                ctp.setVisibility(View.VISIBLE);
                                coupon_image.setVisibility(View.VISIBLE);
                                ct.setVisibility(View.VISIBLE);
                                Coupon.setVisibility(View.VISIBLE);
                                COUPON_CODE=response.body().getCOUPON().getCOUPONID();
                                Coupon.setText(response.body().getCOUPON().getCOUPONTAG());
                                new MyPreferences(getApplicationContext()).setCouponValue(
                                        (float) response.body().getCOUPON().getCOUPONVALUE());


                            }else{

                                // hide coupon ...

                                // ok we can apply coupon...
                                ct.setVisibility(View.GONE);
                                ctp.setVisibility(View.GONE);
                                coupon_box.setVisibility(View.INVISIBLE);
                                coupon_image.setVisibility(View.INVISIBLE);
                                Coupon.setVisibility(View.INVISIBLE);
                            }
                        }else{
                            // hide coupon view...

                            // ok we can apply coupon...
                            ct.setVisibility(View.GONE);
                            ctp.setVisibility(View.GONE);
                            coupon_box.setVisibility(View.INVISIBLE);
                            coupon_image.setVisibility(View.INVISIBLE);
                            Coupon.setVisibility(View.INVISIBLE);

                        }

                        setAllPrices(new MyPreferences(getApplicationContext()).getDC() +
                                new MyPreferences(getApplicationContext()).getCartPrice());


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

                //call the bottom sheet here

                PayMethodsBottomSheet payMethodsBottomSheet=new PayMethodsBottomSheet();
                payMethodsBottomSheet.show(getSupportFragmentManager(),"PAY_SHEET");

                break;
        }
    }

    public void mediaPlayer (){

//        Uri uriPlayer = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "coin_drop.mp3/" );
//        final MediaPlayer mp = MediaPlayer.create(context, uriPlayer);
//        mp.start();

//        int resID=getResources().getIdentifier("coin_drop", "raw", getPackageName());
//
//        MediaPlayer mediaPlayer= MediaPlayer.create(this,resID);
//        mediaPlayer.start();
    }
    private void placeOrder() {

        loginLoader(true,"Placing Order...just a moment!");
        pay.setEnabled(false);
        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("CID", String.valueOf(CID));
        map.put("ITEMS_PRICE", String.valueOf(new MyPreferences(this).getCartPrice()));
        map.put("DC", String.valueOf(new MyPreferences(getApplicationContext()).getDC()));
        map.put("ITEM_COUNT", String.valueOf(new MyPreferences(getApplicationContext()).getCartCount()));
        map.put("S", String.valueOf(1));
        map.put("LATITUDE",new MyPreferences(this).getLocLat());
        map.put("LONGITUDE",new MyPreferences(this).getLocLan());
        map.put("ADDRESS",new MyPreferences(this).getUserPrimaryAddress());
        map.put("LM",new MyPreferences(this).getLM()+","+new MyPreferences(this).getDNO());
        map.put("COUPON",String.valueOf(COUPON_CODE));



        Call<com.foodhub.vugido.models.Response>call=RetrofitBuilder.getInstance().getRetrofit().placeOrder(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.Response> response) {

                loginLoader(false,"");
                pay.setEnabled(true);

                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok order placed successfully
                        Log.e("ok","order placed");



                        setResult(RESULT_OK);
                        finish();

                    }else {

                      //  Log.e("ok","not done");

                        // order not placed..

                    }
                }else {

                    //error
                   // Log.e("ok","not done");

                }

            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.Response> call, @NonNull Throwable t) {
                loginLoader(false,"");

            }
        });





    }

    @Override
    public void payMethodInterface() {
        placeOrder();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        float tp=0;
        if(buttonView.getId()==R.id.checkBox3){

            if(isChecked){
                // add coupon value...
                tp=new MyPreferences(getApplicationContext()).getDC() + new MyPreferences(getApplicationContext()).getCartPrice()- new MyPreferences(this).getCouponValue();
                ctp.setText("-"+String.valueOf(new MyPreferences(this).getCouponValue()));

                TotalAmount.setText(String.format("Rs.%s/-", (new MyPreferences(getApplicationContext()).getDC() +
                        new MyPreferences(getApplicationContext()).getCartPrice())));

            }else{

                //remove coupon value...
                tp=new MyPreferences(getApplicationContext()).getDC() +
                        new MyPreferences(getApplicationContext()).getCartPrice();
                ctp.setText("-"+String.valueOf(0));


            }

            setAllPrices(tp);

        }

    }
}
