package com.foodhub.vugido.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
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

        da.setText(String.format("%s,%s,%s,%s", new MyPreferences(getApplicationContext()).getUserPrimaryAddress(), new MyPreferences(getApplicationContext()).getLM(),new MyPreferences(getApplicationContext()).getDNO(),new MyPreferences(getApplicationContext()).getUserMobile()));
        ItemsCount.setText(String.format("Price(%s Items)", new MyPreferences(getApplicationContext()).getOrderablePro().split(";").length));
        ItemPrice.setText(String.valueOf(new MyPreferences(getApplicationContext()).getCartPrice()));
        pay.setOnClickListener(this);
        fetchPrimaryAddress();


    }

    private void fetchPrimaryAddress() {

        loginLoader(true,"Please wait..");

        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

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
        map.put("CP", new MyPreferences(this).getOrderablePro());
        map.put("ITEMS_PRICE", String.valueOf(new MyPreferences(this).getCartPrice()));
        map.put("DC", String.valueOf(new MyPreferences(getApplicationContext()).getDC()));
        map.put("ITEM_COUNT", String.valueOf(new MyPreferences(getApplicationContext()).getOrderablePro().split(";").length));
        map.put("S", String.valueOf(1));
        map.put("LATITUDE",new MyPreferences(this).getLocLat());
        map.put("LONGITUDE",new MyPreferences(this).getLocLan());
        map.put("ADDRESS",new MyPreferences(this).getUserPrimaryAddress());
        map.put("LM",new MyPreferences(this).getLM()+","+new MyPreferences(this).getDNO());
        map.put("TODAY",String.valueOf(new MyPreferences(getApplicationContext()).getIsTomorrow()));


        Log.e("CP",new MyPreferences(this).getOrderablePro());

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

//                        new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()-new MyPreferences(getApplicationContext()).getOrderablePro().split(";").length);
//                       new  MyPreferences(getApplicationContext()).setCartPrice(0);

                       String[] strings= new MyPreferences(getApplicationContext()).getOrderablePro().split(";");
                        ArrayList<String> arrayList=new ArrayList<>();
                        Collections.addAll(arrayList,strings);

                        String[] strings1=new MyPreferences(getApplicationContext()).getCartProducts().split(";");
                        ArrayList<String> stringArrayList=new ArrayList<>();
                        Collections.addAll(stringArrayList,strings1);

                        if (stringArrayList.size()==1 || stringArrayList.size()==arrayList.size()) {
                            stringArrayList.clear();
                            new MyPreferences(getApplicationContext()).setOrderablePro(null);
                            new MyPreferences(getApplicationContext()).setCartProducts(null);
                            new MyPreferences(getApplicationContext()).setCartCount(0);
                            new MyPreferences(getApplicationContext()).setOrderableCartCount(0);
                            new MyPreferences(getApplicationContext()).setCartPrice(0);

                        }else {

                            for (int i = 0; i < arrayList.size(); i++) {
                                for (int j = 0; j < stringArrayList.size(); j++) {
                                    if (arrayList.get(i).equals(stringArrayList.get(j))) {
                                        stringArrayList.remove(j);
                                        break;
                                    }
                                }

                            }

                            StringBuilder x = new StringBuilder();
                            for (int i = 0; i < stringArrayList.size(); i++) {

                                String s4 = stringArrayList.get(i);
                                if (i == 0)
                                    x.append(s4);
                                else
                                    x.append(";").append(s4);


                            }
                            new MyPreferences(getApplicationContext()).setOrderablePro(null);
                            new MyPreferences(getApplicationContext()).setCartProducts(x.toString());
                            new MyPreferences(getApplicationContext()).setCartCount(stringArrayList.size());
                            new MyPreferences(getApplicationContext()).setCartPrice(0);



                        }

                        setResult(RESULT_OK);
                        finish();

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
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.Response> call, @NonNull Throwable t) {
                loginLoader(false,"");

            }
        });




       // Call<com.foodhub.vugido.models.status.Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).placeOrder(map);

//        call.enqueue(new Callback<com.foodhub.vugido.models.status.Response>() {
//            @Override
//            public void onResponse(@NonNull Call<com.foodhub.vugido.models.status.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.status.Response> response) {
//
//                pay.setEnabled(true);
//                Progress.setVisibility(View.GONE);
//
//                Log.e("response",response.toString());
//                if(response.isSuccessful()){
//                    // ok
//
//                    assert response.body() != null;
//                    if(!response.body().isError()){
//                        // ok order placed successfully
//
//                        Log.e("ok","done");
//                        if(myPreferences!=null)
//                            myPreferences.setCartCount((myPreferences.getCartCount()-myPreferences.getOrderableCartCount()));
//                        else
//                           new  MyPreferences(getApplicationContext()).setCartCount((myPreferences.getCartCount()-myPreferences.getOrderableCartCount()));
//
//                        setResult(RESULT_OK);
//                        finish();
//
//                    }else {
//
//                        Log.e("ok","not done");
//
//                        // order not placed..
//
//                    }
//                }else {
//
//                    //error
//                    Log.e("ok","not done");
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<com.foodhub.vugido.models.status.Response> call, @NonNull Throwable t) {
//
//                Progress.setVisibility(View.GONE);
//                pay.setEnabled(true);
//                // error not placed order...
//                Log.e("ok","not done");
//
//
//            }
//        });
    }

    @Override
    public void payMethodInterface() {
        placeOrder();
    }
}
