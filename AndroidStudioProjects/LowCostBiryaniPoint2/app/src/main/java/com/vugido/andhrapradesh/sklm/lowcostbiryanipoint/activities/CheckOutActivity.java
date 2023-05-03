package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.fragments.Bottom_Model_Fragments.PayMethodsBottomSheet;
import com.foodhub.vugido.models.primary_address.DATA;
import com.foodhub.vugido.models.primary_address.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class CheckOutActivity extends AppCompatActivity implements OnClickListener, PayMethodsBottomSheet.PAY_METHOD_INTERFACE {

    Toolbar toolbar;
    ScrollView scrollView;
    TextView DeliveryTime,DC,ItemPrice,TotalAmount,ItemsCount,CoinsUsed;
    CheckBox AddressCheckBox;
    private TextView UserName,DoorNo,Area, ActivePhone, ZipCode,CountryState,YouPay;
    MyPreferences myPreferences;
    View Progress;
    Button pay;
    ExtendedFloatingActionButton extendedFloatingActionButton;
    DATA data;
    int Coins=0;

    private void showCoinsDialogBox() {

        myDialogBoxUpdater();

    }
    private void myDialogBoxUpdater() {

        String Price;

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView=null;

        dialogView = inflater.inflate(R.layout.profits_get_orders_dialog, null);
        //Price= String.valueOf(price);


        // set pre data.....
        TextView textView=dialogView.findViewById(R.id.textView33);
        final TextInputEditText t=dialogView.findViewById(R.id.to);

        Button cancel =  dialogView.findViewById(R.id.button_cancel);
        Button update = dialogView.findViewById(R.id.button_update);

        textView.setText("Coins Available\n"+new MyPreferences(this).getCoinsCount());

        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        update.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                //updatePrice(pid,ppp_id, Objects.requireNonNull(p.getText()).toString());
                if(Objects.requireNonNull(t.getText()).toString()!=null && !t.getText().toString().equals("")){
                    int cc=Integer.parseInt(Objects.requireNonNull(t.getText()).toString());
                    if(cc<=new MyPreferences(getApplicationContext()).getCoinsCount()){
                        //ok valid
                        dialogBuilder.dismiss();
                        setAllPrices(cc);
                    }else {
                        Toast.makeText(getApplicationContext(),"Enter Coins as per your balance",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Enter Coins as per your balance",Toast.LENGTH_LONG).show();
                }




            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();




    }

    private void setAllPrices(int cc) {

        CoinsUsed.setText(String.valueOf(cc));
        YouPay.setText(String.valueOf((new MyPreferences(this).getCartPrice()+getIntent().getFloatExtra("DC",0)-cc)));
        Coins=cc;
        mediaPlayer();
        extendedFloatingActionButton.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        extendedFloatingActionButton=findViewById(R.id.exFb);

        Progress=findViewById(R.id.my_progress);
        CoinsUsed=findViewById(R.id.textView36);
        YouPay=findViewById(R.id.textView39);
        pay=findViewById(R.id.button4);
        extendedFloatingActionButton.setVisibility(View.GONE);

        pay.setEnabled(false);
        // get address ids...

        UserName=findViewById(R.id.address_user_name);
        DoorNo=findViewById(R.id.address_user_door);
        Area=findViewById(R.id.address_user_street);
        ActivePhone=findViewById(R.id.address_user_phone);
        ZipCode=findViewById(R.id.address_user_pincode);
        CountryState=findViewById(R.id.address_user_country);



        extendedFloatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                showCoinsDialogBox();
            }
        });

        /////////

        DeliveryTime=findViewById(R.id.delivery_time);
        toolbar=findViewById(R.id.checkOutToolbar);
        ItemPrice=findViewById(R.id.ItemsPrice);
        DC=findViewById(R.id.delivery_charges);
        TotalAmount=findViewById(R.id.total_amount);
        ItemsCount=findViewById(R.id.textView5);
        AddressCheckBox=findViewById(R.id.address_user_select_check_box);
        myPreferences=new MyPreferences(this);
        toolbar.setTitle("Order CheckOut");
        AddressCheckBox.setVisibility(View.INVISIBLE);

        DC.setText("Rs."+getIntent().getFloatExtra("DC",0)+"/-");
        ItemPrice.setText("Rs."+new MyPreferences(this).getCartPrice()+"/-");
        ItemsCount.setText("Price ("+new MyPreferences(this).getOrderableCartCount()+" items)");
        float t=new MyPreferences(this).getCartPrice()+getIntent().getFloatExtra("DC",0);
        TotalAmount.setText("Rs."+t+"/-");
        YouPay.setText("Rs."+t+"/-");

        if(myPreferences.getSlotStatus()){
            if(myPreferences.getIsTomorrow())
                DeliveryTime.setText("Tomorrow : "+myPreferences.getSlotName());
            else
                DeliveryTime.setText("Today : "+myPreferences.getSlotName());
        }
        else
            DeliveryTime.setText("Express Delivery");

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scrollView=findViewById(R.id.CheckOutScrollView);


        pay.setOnClickListener(this);
        fetchPrimaryAddress();

      /*  scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });


        scrollView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });*/

    }

    private void fetchPrimaryAddress() {


        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getUserPrimaryAddress(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok

                        data=response.body().getDATA();

                        bindAddressData(data);
                        pay.setEnabled(true);

                    }else{

                        //error
                    }
                }else {

                    //error
                }
                Progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
            }
        });

    }



    private void bindAddressData(DATA dataItem) {


        String door,area,active_ph,zip,county_st;
        door = "<font color=#000000>Door N.o/ Office: </font> <font color=#656565>"+ dataItem.getDOORNO()+"</font>";
        area= "<font color=#000000>Area / Street Name: </font> <font color=#656565>"+ dataItem.getAREANAME()+"</font>";
        active_ph="<font color=#000000>Active Phone: </font> <font color=#656565>"+ dataItem.getACTIVEPHONE()+"</font>";
        zip="<font color=#000000>ZipCode : </font> <font color=#656565>"+ dataItem.getZIP()+"</font>";
        county_st="<font color=#000000>Country / State: </font> <font color=#656565>"+ dataItem.getCOUNTRYSTATE()+"</font>";

        UserName.setText(dataItem.getFULLNAME());
        DoorNo.setText(Html.fromHtml(door));
        Area.setText(Html.fromHtml(area));
        ActivePhone.setText(Html.fromHtml(active_ph));
        CountryState.setText(Html.fromHtml(county_st));
        ZipCode.setText(Html.fromHtml(zip));

        if(new MyPreferences(this).getCoinsCount()!=0 && new MyPreferences(this).isCoinsActivated()){
           extendedFloatingActionButton.setVisibility(View.VISIBLE);
           extendedFloatingActionButton.setText("Use your "+new MyPreferences(this).getCoinsCount()+" Coins To Reduce Price");

        }
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

        int resID=getResources().getIdentifier("coin_drop", "raw", getPackageName());

        MediaPlayer mediaPlayer=MediaPlayer.create(this,resID);
        mediaPlayer.start();
    }
    private void placeOrder() {

        pay.setEnabled(false);
        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("AID",String.valueOf(data.getAID()));
        map.put("ITEMS_PRICE",String.valueOf(new MyPreferences(this).getCartPrice()));
        map.put("DC",String.valueOf(getIntent().getFloatExtra("DC",0)));
        map.put("ITEM_COUNT",String.valueOf(new MyPreferences(this).getOrderableCartCount()));
        map.put("COINS",String.valueOf(Coins));


        if(myPreferences.getSlotStatus()){
            if(myPreferences.getIsTomorrow()) {
                map.put("IS_TODAY",String.valueOf(1) );
            }
            else {
                map.put("IS_TODAY", String.valueOf(0));
            }
            map.put("TIME",String.valueOf(new MyPreferences(this).getSlotId()));

        }
        else{
            map.put("IS_TODAY", String.valueOf(0));
            // -1 for express delivery
            map.put("TIME",String.valueOf(-1));

        }


        Call<com.foodhub.vugido.models.status.Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).placeOrder(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.status.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.status.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.status.Response> response) {

                pay.setEnabled(true);
                Progress.setVisibility(View.GONE);

                Log.e("response",response.toString());
                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok order placed successfully

                        Log.e("ok","done");
                        if(myPreferences!=null)
                            myPreferences.setCartCount((myPreferences.getCartCount()-myPreferences.getOrderableCartCount()));
                        else
                           new  MyPreferences(getApplicationContext()).setCartCount((myPreferences.getCartCount()-myPreferences.getOrderableCartCount()));

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
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.status.Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
                pay.setEnabled(true);
                // error not placed order...
                Log.e("ok","not done");


            }
        });
    }

    @Override
    public void payMethodInterface() {
        placeOrder();
    }
}
