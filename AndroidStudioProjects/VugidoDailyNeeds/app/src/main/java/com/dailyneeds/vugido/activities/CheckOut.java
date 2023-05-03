package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.dialogs.ExpressDelivery;
import com.dailyneeds.vugido.dialogs.SlotBooking;
import com.dailyneeds.vugido.models.FinalOrderableProducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckOut extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SlotBooking.Slot {

    Toolbar toolbar;
    Button Pay,EditLocation;
    FinalOrderableProducts finalOrderableProducts;
    TextView CartCount,CartPrice,DeliveryCharges,OrderLocation,FinalPrice,ET,ST,SST;
    RadioButton Slot,Express;
    private  boolean SlotBoolean,ExpressBoolean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Pay=findViewById(R.id.pay_button);
        EditLocation=findViewById(R.id.edit_location);
        Pay.setOnClickListener(this);
        EditLocation.setOnClickListener(this);
        toolbar.setNavigationIcon(R.drawable.back);
        CartCount=findViewById(R.id.CartItemCount);
        CartPrice=findViewById(R.id.CartItemPrice);
        DeliveryCharges=findViewById(R.id.DeliveryCharges);
        OrderLocation=findViewById(R.id.OrderLocation);
        FinalPrice=findViewById(R.id.FinalPrice);
        Slot=findViewById(R.id.slotButton);
        Express=findViewById(R.id.ExpressDeliveryButton);
        ET=findViewById(R.id.ET);
        ST=findViewById(R.id.ST);
        SST=findViewById(R.id.SelectedSlot);
        ET.setOnClickListener(this);
        ST.setOnClickListener(this);
        Slot.setOnCheckedChangeListener(this);
        Express.setOnCheckedChangeListener(this);
        SlotBoolean=true;
        ExpressBoolean=true;
        Pay.setEnabled(false);

        finalOrderableProducts= Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")).getParcelable("FINAL");


        setAllData();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setAllData() {


        if(finalOrderableProducts!=null){


            CartCount.setText(String.format("%s Items", String.valueOf(finalOrderableProducts.getCartProductsList().size())));
            CartPrice.setText(String.format("Rs.%s/-", String.valueOf(finalOrderableProducts.getActualTotalPrice())));
            OrderLocation.setText(finalOrderableProducts.getLocation());
          //  FinalPrice.setText(String.format("Rs.%s", finalOrderableProducts.getDeliveryCharges() + finalOrderableProducts.getActualTotalPrice() + "/-"));


        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay_button:
                // check here the slot checked or not..


                Intent intent=new Intent(CheckOut.this,PaymentOptions.class);
               /* Bundle bundle=getIntent().getBundleExtra("BUNDLE");
                if(bundle!=null){
                    bundle.putParcelable("FINAL",);
                }*/
                intent.putExtra("BUNDLE", Objects.requireNonNull(getIntent().getBundleExtra("BUNDLE")));
                startActivityForResult(intent, ConfigVariables.ORDER_PLACED_CODE);
                break;
            case R.id.edit_location:
                finish();
                break;

            case R.id.ST:


                    slotBooking();
                    SlotBoolean=false;



                Express.setChecked(false);
                Slot.setChecked(true);

                //  for slot delivery ...

                break;
            case R.id.ET:

               if(ConfigVariables.expressDeliveryAvailability()){

                       expressDelivery();
                       ExpressBoolean=false;

                   // available..
                   Express.setChecked(true);
                   Slot.setChecked(false);
                   // for express booking...



               }else{

                   ExpressDelivery expressDelivery=new ExpressDelivery();
                   Bundle bundle=new Bundle();
                   bundle.putInt("A",1);
                   expressDelivery.setArguments(bundle);
                   expressDelivery.show(getSupportFragmentManager(),"EXPRESS");
                  // not available
                   // show express delivery not available after 9pm- 5am
               }

                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== ConfigVariables.ORDER_PLACED_CODE && resultCode== ConfigVariables.ORDER_PLACED_RESPONSE_CODE){

            setResult(ConfigVariables.ORDER_PLACED_RESPONSE_CODE);
            finish();

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        if(b){
            if(compoundButton.getId()==R.id.ExpressDeliveryButton){


                Log.e("checked","called");

                if(ConfigVariables.expressDeliveryAvailability()){
                    Express.setChecked(true);
                    Slot.setChecked(false);
                    // for slot booking...

                    if(ExpressBoolean){
                        expressDelivery();
                        ExpressBoolean=false;

                    }
                    Pay.setEnabled(true);
                    Pay.setBackground(getDrawable(R.drawable.button));

                }else {

                    Express.setChecked(false);
                    ExpressDelivery expressDelivery=new ExpressDelivery();
                    Bundle bundle=new Bundle();
                    bundle.putInt("A",1);
                    expressDelivery.setArguments(bundle);
                    expressDelivery.show(getSupportFragmentManager(),"EXPRESS");

                    // not available
                    // show express delivery not available after 9pm- 5am
                }


            }else {


                // check for slot availability


                Log.e("checked","called");
                Express.setChecked(false);
                Slot.setChecked(true);

                //  for express delivery ...

                if(SlotBoolean){
                    slotBooking();
                    SlotBoolean=false;
                }
            }



        }

        DeliveryCharges.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        FinalPrice.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        // now here set the correct mode and delivery charges....



    }

    private void slotBooking() {

        // launch slot fragment here.. and get the proper slot ...
        SlotBooking slotBooking=new SlotBooking();
        slotBooking.show(getSupportFragmentManager(),"SLOT");




    }

    private void expressDelivery() {
        if (finalOrderableProducts!=null){
            // 2 indicates express delivery
            ExpressDelivery expressDelivery=new ExpressDelivery();
            Bundle bundle=new Bundle();
            bundle.putString("D", String.valueOf(Math.round(finalOrderableProducts.getDistance()/1000)));
            bundle.putString("DC", String.valueOf(finalOrderableProducts.getDeliveryCharges()));
            bundle.putInt("A",0);
            expressDelivery.setArguments(bundle);
            expressDelivery.show(getSupportFragmentManager(),"EXPRESS");

            finalOrderableProducts.setDMode(2);
            DeliveryCharges.setText(String.format("Rs.%s/-", String.valueOf(finalOrderableProducts.getDeliveryCharges())));
            // also set the mode...to express delivery..
            SST.setText("");
            SST.setVisibility(View.GONE);
            FinalPrice.setText(String.format("Rs.%s", finalOrderableProducts.getDeliveryCharges() + finalOrderableProducts.getActualTotalPrice() + "/-"));




        }

    }

    @Override
    public void slot(int slot) {


        if(finalOrderableProducts!=null){
            // set slot to true and dc to 10..
            // 1 indicates slot book..
            finalOrderableProducts.setDMode(1);
            finalOrderableProducts.setSlotTime(slot);
            // show user which slot selected..
            setSSTText(slot);

            DeliveryCharges.setText(R.string.ten_rupees);
            FinalPrice.setText(String.format("Rs.%s", 10.0 + finalOrderableProducts.getActualTotalPrice() + "/-"));
            Pay.setEnabled(true);
            Pay.setBackground(getDrawable(R.drawable.button));



        }



    }

    private void setSSTText(int slot) {



        List<String> strings=new ArrayList<>();
        strings.add(getResources().getString(R.string._5_00_am_6_00_am));
        strings.add(getResources().getString(R.string._6_00_am_7_00_am));
        strings.add(getResources().getString(R.string._7_00_am_8_00_am));
        strings.add(getResources().getString(R.string._8_00_am_9_00_am));
        strings.add(getResources().getString(R.string._9_00_am_10_00_am));
        strings.add(getResources().getString(R.string._10_00_am_11_00_am));
        strings.add(getResources().getString(R.string._11_00am_12_00_pm));
        strings.add(getResources().getString(R.string._12_00pm_1_00pm));
        strings.add(getResources().getString(R.string._1_00_pm_2_00_pm));
        strings.add(getResources().getString(R.string._2_00_pm_3_00_pm));
        strings.add(getResources().getString(R.string._3_00_pm_4_00_pm));
        strings.add(getResources().getString(R.string._4_00_pm_5_00_pm));
        strings.add(getResources().getString(R.string._5_00_pm_6_00_pm));
        strings.add(getResources().getString(R.string._6_00_pm_7_00_pm));
        strings.add(getResources().getString(R.string._7_00_pm_8_00_pm));
        strings.add(getResources().getString(R.string._8_00_pm_9_00pm));

       for(int i=0;i<strings.size();i++){

           if(slot-1==i){

               SST.setText(strings.get(i));
               break;

           }

       }

       SST.setVisibility(View.VISIBLE);

    }
}
