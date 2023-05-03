package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.fragments.OrderedItemsBottomSheet;
import com.dailyneeds.vugido.models.OnTheWay;
import com.dailyneeds.vugido.receivers.NetworkCallBack;

import java.util.ArrayList;
import java.util.List;

public class MyPurchasesDetailedActivity extends AppCompatActivity implements View.OnClickListener {
    private Button SeeItems;
    TextView ID, ON, COUNT, ITEM_PRICE,DC, FP, L,DM;
    private int OID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_purchases_detailed_activity);
        Toolbar toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SeeItems=findViewById(R.id.SI);
        SeeItems.setOnClickListener(this);
        ID=findViewById(R.id.oid);
        ON=findViewById(R.id.on);
        COUNT=findViewById(R.id.count_);
        ITEM_PRICE=findViewById(R.id.IP);
        DC=findViewById(R.id.DC);
        FP=findViewById(R.id.FP);
        L=findViewById(R.id.OrderLocation);
        DM=findViewById(R.id.DM);


        Bundle bundle=getIntent().getBundleExtra("BUNDLE");
        assert bundle!=null;
        OnTheWay onTheWay=bundle.getParcelable("PRODUCT");


        if(onTheWay!=null){

            OID= Integer.parseInt(onTheWay.getOID());

            ID.setText(onTheWay.getOID());
            ON.setText(String.format("%s %s", onTheWay.getDATE(), onTheWay.getTIME()));
            COUNT.setText(onTheWay.getCount());
            ITEM_PRICE.setText(String.format("Rs.%s/-", onTheWay.getItemPrice()));
           // DC.setText(String.format("Rs.%s/-", onTheWay.getDeliveryCharges()));
            L.setText(onTheWay.getLocation());
           // FP.setText(String.format("Rs.%s/-", onTheWay.getFinalPay()));

            if(onTheWay.getDMODE()==1){
                // slot mode
                DC.setText(R.string.ten_rupees);
                FP.setText(String.format("Rs.%s/-", String.valueOf(Double.parseDouble(onTheWay.getItemPrice()) + 10.0)));
                DM.setText(String.format("Slot booked at %s", slotText(onTheWay.getSlot())));



            }else {
                //express mode..
                DC.setText(String.format("Rs.%s/-", onTheWay.getDeliveryCharges()));
                FP.setText(String.format("Rs.%s/-", onTheWay.getFinalPay()));
                DM.setText(R.string.e_d_20min);


            }

        }


        // get the order id here and fetch the data ...





    }
    private String slotText(int slot) {



        List<String> strings=new ArrayList<>();
        String S="";
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
                S=strings.get(i);
                break;

            }

        }

        return S;
    }

    @Override
    protected void onResume() {
        super.onResume();

        int TIME = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!NetworkCallBack.NetworkAvailability){

                    startActivity(new Intent(MyPurchasesDetailedActivity.this, NetworkErrorActivity.class));

                }
            }
        }, TIME);
    }

    @Override
    public void onClick(View view) {



        if(view.getId()==R.id.SI){


            // send the request to server to get order items... using oid and uid...
                     OrderedItemsBottomSheet orderedItemsBottomSheet=new OrderedItemsBottomSheet();
                            Bundle bundle=new Bundle();
                            bundle.putInt("OID",OID);
                            orderedItemsBottomSheet.setArguments(bundle);
                            orderedItemsBottomSheet.show(getSupportFragmentManager(),"BOTTOM_SHEET");
        }


    }



}
