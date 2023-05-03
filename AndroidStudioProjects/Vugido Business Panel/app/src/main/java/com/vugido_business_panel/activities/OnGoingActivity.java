package com.vugido_business_panel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido_business_panel.R;
import com.vugido_business_panel.Retrofit.RetrofitBuilder;
import com.vugido_business_panel.adapter.ongoing.OnGoingAdapter;
import com.vugido_business_panel.app_congif.MyPreferences;
import com.vugido_business_panel.fragments.MyDialogLoader;
import com.vugido_business_panel.models.BaseProduct;
import com.vugido_business_panel.models.newOrders.DATAItem;
import com.vugido_business_panel.models.newOrders.Response;
import com.vugido_business_panel.services.RingToneService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class OnGoingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView parcel,eat_now,price;
    private MyDialogLoader myDialogLoader;
    OnGoingAdapter myCartProductsAdapter;
    List<DATAItem> dataItemList;

    int UID;
    private float CURRENT_PRICE=0;
    public void stopSService() {
        Intent serviceIntent = new Intent(this, RingToneService.class);
        stopService(serviceIntent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);
        toolbar=findViewById(R.id.cart_toolbar);
        toolbar.setTitle("On-Going Eats");

        setSupportActionBar(toolbar);
        stopSService();

        price=findViewById(R.id.price);
        parcel=findViewById(R.id.parcel);
        eat_now=findViewById(R.id.eat_now);

        //finish
        parcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // eat_now.setEnabled(false);
//            PayMethodsBottomSheet payMethodsBottomSheet=new PayMethodsBottomSheet();
//            payMethodsBottomSheet.show(getSupportFragmentManager(),"PAY");
                //eatNow(0);
            }
        });

        //eat more
        eat_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OnGoingActivity.this.finish();

            }
        });
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnGoingActivity.this.finish();
            }
        });
        recyclerView=findViewById(R.id.cart_recycler_view);


        fetchOnGoingOrders(getIntent().getIntExtra("UID",0));


    }

    private void fetchOnGoingOrders(int UID) {
        MyPreferences myPreferences=new MyPreferences(this);
        Map<String,Object> map=new HashMap<>();
        map.put("CID",String.valueOf(myPreferences.getCID()));
        map.put("UID",UID);


        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Getting Your Ordered Foods ");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getOnGoingOrder(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


               // Log.e("status",response.toString());
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){


                        setData(response.body().getDATA());

                    }else {
                        //no data
                        Toast.makeText(getApplicationContext(),"No Orders",Toast.LENGTH_LONG).show();
                    }
                }
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
               // Log.e("status",t.toString());
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }
        });


    }

    private void setData(List<DATAItem> data1) {



       // List<DATAItem> data=data1;
      //  Log.e("res",data.toString());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //List<Integer> pids=new ArrayList<>();




//        for(DATAItem dataItem:data){
//            pids.add(dataItem.getPID());
//        }
      //  LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>(pids);

       // List<Integer> Ulist=new ArrayList<>(linkedHashSet);


       // dataItemList=new ArrayList<>();
//        count=new ArrayList<>();
//        categories=new ArrayList<>();
//        for(Integer pid:Ulist){
//            int c=0;
//            for(DATAItem dataItem:data){
//
//                if(dataItem.getPID()==pid){
//                    if(c==0)
//                        dataItemList.add(dataItem);
//                    c=c+1;
//                }
//            }
//            count.add(c);
//        }

//        for (DATAItem productsItem:dataItemList)
//        {
//            for (CATEGORIESItem categoriesItem:data1.getCATEGORIES()){
//                if(productsItem.getSID()==categoriesItem.getSID()){
//                    categories.add(categoriesItem.getCNAME());
//                    break;
//                }
//            }
//
//        }

//        Log.e("data",dataItemList.toString());
//        Log.e("count",count.toString());

        myCartProductsAdapter=new OnGoingAdapter(data1,this);

        recyclerView.setAdapter(myCartProductsAdapter);

        setPrice(data1);
    }

    private void setPrice(List<DATAItem> productsItemList){
        float p=0f;
        for (int i=0;i<productsItemList.size();i++){

            if(productsItemList.get(i).getOFFER()>0){

                // offer exists..
                // show offer tag

                // offer exists..
                p+= BaseProduct.offerPercentagePrice(productsItemList.get(i).getOFFER(), getQuantityPrice(productsItemList.get(i).getPRICE(),productsItemList.get(i),productsItemList.get(i).getCOUNT()), productsItemList.get(i).getOFFERLIMIT());



            }else{

                p+=getQuantityPrice(productsItemList.get(i).getPRICE(),productsItemList.get(i),productsItemList.get(i).getCOUNT());
            }
        }

        price.setText("Rs."+p+"/-");

        CURRENT_PRICE=p;
        if(p==0f){
            //hide the data
        }

    }
    private int  getQuantityPrice(float price, DATAItem baseProduct,int count){

//        if(baseProduct.getISGRAMSET()==1){
//            // in grams..
//            float kg = baseProduct.getCARTQUANTITY()/1000f;
//            return Math.round(kg*price);
//
//        }else {
        // in units or packets etc
        return Math.round(count*price);

        // }

    }


}
