package com.vugido.com.vugidoeats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.adapters.cart.MyCartProductsAdapter;
import com.vugido.com.vugidoeats.adapters.ongoing.OnGoingAdapter;
import com.vugido.com.vugidoeats.app_config.MyPreferences;
import com.vugido.com.vugidoeats.dialogs.MyDialogLoader;
import com.vugido.com.vugidoeats.fragments.login_fragments.PayMethodsBottomSheet;
import com.vugido.com.vugidoeats.models.BaseProduct;
import com.vugido.com.vugidoeats.models.Orders.DATAItem;
import com.vugido.com.vugidoeats.models.Orders.Response;
import com.vugido.com.vugidoeats.models.cart.CATEGORIESItem;
import com.vugido.com.vugidoeats.models.cart.DATA;
import com.vugido.com.vugidoeats.models.cart.PRODUCTSItem;
import com.vugido.com.vugidoeats.retrofit.RetrofitBuilder;
import com.vugido.com.vugidoeats.services.RingToneService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class OnGoingActivity extends AppCompatActivity implements PayMethodsBottomSheet.PAY_METHOD_INTERFACE {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView parcel,eat_now,price;
    private MyDialogLoader myDialogLoader;
    OnGoingAdapter myCartProductsAdapter;
    List<DATAItem> dataItemList;

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
        parcel.setOnClickListener(v -> {
            // eat_now.setEnabled(false);
            PayMethodsBottomSheet payMethodsBottomSheet=new PayMethodsBottomSheet();
            payMethodsBottomSheet.show(getSupportFragmentManager(),"PAY");
            //eatNow(0);
        });

        //eat more
        eat_now.setOnClickListener(v -> {

          finish();
            //notify the admin...
            // call menu activity
        });
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        recyclerView=findViewById(R.id.cart_recycler_view);


        fetchOnGoingOrders();


    }

    private void fetchOnGoingOrders() {
        MyPreferences myPreferences=new MyPreferences(this);
        Map<String,Object> map=new HashMap<>();
        map.put("CID",String.valueOf(myPreferences.getCID()));
        map.put("UID",myPreferences.getUID());


        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Getting Your Ordered Foods ");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        Call<com.vugido.com.vugidoeats.models.Orders.Response> call= RetrofitBuilder.getInstance().getRetrofit("").getOnGoingOrder(map);
        call.enqueue(new Callback<com.vugido.com.vugidoeats.models.Orders.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.com.vugidoeats.models.Orders.Response> call, @NonNull retrofit2.Response<com.vugido.com.vugidoeats.models.Orders.Response> response) {


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


    @Override
    public void payMethodInterface() {

        //initiate the pay request to the admin and send him the bill....
        //pay request on at table..

        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Getting Waiter to your table ");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
Map<String,Object> mapMap=new HashMap<>();
mapMap.put("CID",new MyPreferences(this).getCID());
mapMap.put("UID",new MyPreferences(this).getUID());

Call<com.vugido.com.vugidoeats.models.Response        > call=RetrofitBuilder.getInstance().getRetrofit("").parequesed(mapMap);

call.enqueue(new Callback<com.vugido.com.vugidoeats.models.Response>() {
    @Override
    public void onResponse(Call<com.vugido.com.vugidoeats.models.Response> call, retrofit2.Response<com.vugido.com.vugidoeats.models.Response> response) {

        assert response.body() != null;
        if(response.body().isSTATUS()){

/*
no errorf
*/
finish();
        } // Log.e("status",t.toString());
        if(myDialogLoader!=null)
            myDialogLoader.dismiss();
    }

    @Override
    public void onFailure(Call<com.vugido.com.vugidoeats.models.Response> call, Throwable t) {
        // Log.e("status",t.toString());
        if(myDialogLoader!=null)
            myDialogLoader.dismiss();
    }
});

    }
}
