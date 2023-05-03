package com.vugido.com.vugidoeats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.vugido.com.vugidoeats.app_config.MyPreferences;
import com.vugido.com.vugidoeats.dialogs.MyDialogLoader;
import com.vugido.com.vugidoeats.models.BaseProduct;
import com.vugido.com.vugidoeats.models.cart.CATEGORIESItem;
import com.vugido.com.vugidoeats.models.cart.DATA;
import com.vugido.com.vugidoeats.models.cart.PRODUCTSItem;
import com.vugido.com.vugidoeats.models.cart.Response;
import com.vugido.com.vugidoeats.retrofit.RetrofitBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
public class CartProductsActivity extends AppCompatActivity implements MyCartProductsAdapter.CartDelete {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView parcel,eat_now,price;
    private MyDialogLoader myDialogLoader;
    MyCartProductsAdapter myCartProductsAdapter;
    List<PRODUCTSItem> dataItemList;
    List<Integer> count;
    List<String> categories;
    private float CURRENT_PRICE=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        toolbar=findViewById(R.id.cart_toolbar);
        toolbar.setTitle("My Eats");

        setSupportActionBar(toolbar);

        price=findViewById(R.id.price);
        parcel=findViewById(R.id.parcel);
        eat_now=findViewById(R.id.eat_now);

        parcel.setOnClickListener(v -> {
            parcel.setEnabled(false);
            eatNow(0);
        });

        eat_now.setOnClickListener(v -> {

            eat_now.setEnabled(false);
            //notify the admin...
            eatNow(1);
        });
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        recyclerView=findViewById(R.id.cart_recycler_view);



        if(new MyPreferences(getApplicationContext()).getCartProducts()!=null)
            fetchCartProducts();
        else
            Toast.makeText(getApplicationContext(),"Cart Empty ",Toast.LENGTH_LONG).show();




    }

    private void eatNow(int mode) {

        MyPreferences myPreferences=new MyPreferences(this);
        Map<String,Object> map=new HashMap<>();
        map.put("UID",myPreferences.getUID());
        map.put("TNO",String.valueOf(myPreferences.getTableNumber()));
        map.put("PIDS",myPreferences.getCartProducts());

        StringBuilder stringBuilder=new StringBuilder();
        int ic=0;
        for (Integer c:count){
            ic+=c;
            stringBuilder.append(c);
            stringBuilder.append(",");
        }

        map.put("COUNT",stringBuilder.toString());
        map.put("IC",String.valueOf(ic));
        map.put("CID",myPreferences.getCID());
        // mode = 1  eat at table else parcel
        map.put("MODE",String.valueOf(mode));
        map.put("TP",String.valueOf(CURRENT_PRICE));

        Call<com.vugido.com.vugidoeats.models.status.Response> call=RetrofitBuilder.getInstance().getRetrofit("").sendOrderRequest(map);

        call.enqueue(new Callback<com.vugido.com.vugidoeats.models.status.Response>() {
            @Override
            public void onResponse(Call<com.vugido.com.vugidoeats.models.status.Response> call, retrofit2.Response<com.vugido.com.vugidoeats.models.status.Response> response) {
                //Log.e("status",response.toString());

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //Log.e("it's ok","ok");
                        finish();
                        startActivity(new Intent(CartProductsActivity.this,RequestAcceptanceActivity.class));
                        new MyPreferences(getApplicationContext()).setCartProducts(null);

                    }else {
                       // Log.e("it's ok","no");

                    }
                }
            }

            @Override
            public void onFailure(Call<com.vugido.com.vugidoeats.models.status.Response> call, Throwable t) {
                Log.e("failed",t.toString());

            }
        });

    }

    private void fetchCartProducts() {




        Log.e("pids",new MyPreferences(this).getCartProducts());
        MyPreferences myPreferences=new MyPreferences(this);
        Map<String,Object> map=new HashMap<>();
        map.put("CID",String.valueOf(myPreferences.getCID()));
        map.put("TNO",String.valueOf(myPreferences.getTableNumber()));
        map.put("PIDS",myPreferences.getCartProducts());
        map.put("UID",myPreferences.getUID());


        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Getting Your Food");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("").getCartPids(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                Log.e("status",response.toString());
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        setData(response.body().getDATA());

                    }else {
                        //no data
                    }
                }
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Log.e("status",t.toString());
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }
        });

    }

    private void setData(DATA data1) {


        List<PRODUCTSItem> data=data1.getPRODUCTS();
        Log.e("res",data.toString());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Integer> pids=new ArrayList<>();




        for(PRODUCTSItem dataItem:data){
           pids.add(dataItem.getPID());
        }
        LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>(pids);

        List<Integer> Ulist=new ArrayList<>(linkedHashSet);


        dataItemList=new ArrayList<>();
        count=new ArrayList<>();
        categories=new ArrayList<>();
        for(Integer pid:Ulist){
            int c=0;
            for(PRODUCTSItem dataItem:data){

                if(dataItem.getPID()==pid){
                    if(c==0)
                        dataItemList.add(dataItem);
                    c=c+1;
                }
            }
            count.add(c);
        }

        for (PRODUCTSItem productsItem:dataItemList)
        {
            for (CATEGORIESItem categoriesItem:data1.getCATEGORIES()){
                if(productsItem.getSID()==categoriesItem.getSID()){
                    categories.add(categoriesItem.getCNAME());
                    break;
                }
            }

        }

//        Log.e("data",dataItemList.toString());
//        Log.e("count",count.toString());

        myCartProductsAdapter=new MyCartProductsAdapter(dataItemList,count,this,categories);

        recyclerView.setAdapter(myCartProductsAdapter);

        setPrice(dataItemList,count);
    }

    @Override
    public void cartDelete(int pid) {
        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Removing..");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        String pids=new MyPreferences(this).getCartProducts();

        String[] s=pids.split(",");

        String p=null;
        for (String ss:s){

            if(ss.equals(String.valueOf(pid))){
                continue;
            }

            if(p==null){
                p=ss;
            }else {
                p=p+","+ss;
            }

        }
        new MyPreferences(this).setCartProducts(p);

        for (int x=0;x<dataItemList.size();x++){

            if(dataItemList.get(x).getPID()==pid){
                dataItemList.remove(x);
                count.remove(x);
                myCartProductsAdapter.notifyItemRemoved(x);

            }

        }



        if(myDialogLoader!=null)
            myDialogLoader.dismiss();

        setPrice(dataItemList,count);
    }
    private int  getQuantityPrice(float price, PRODUCTSItem baseProduct,int count){

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

    private void setPrice(List<PRODUCTSItem> productsItemList,List<Integer> count){
        float p=0f;
        for (int i=0;i<productsItemList.size();i++){

            if(productsItemList.get(i).getOFFER()>0){

                // offer exists..
                // show offer tag

                // offer exists..
                 p+=BaseProduct.offerPercentagePrice(productsItemList.get(i).getOFFER(), getQuantityPrice(productsItemList.get(i).getPRICE(),productsItemList.get(i),count.get(i)), productsItemList.get(i).getOFFERLIMIT());



            }else{

                p+=getQuantityPrice(productsItemList.get(i).getPRICE(),productsItemList.get(i),count.get(i));
            }
        }

        price.setText("Rs."+p+"/-");

        CURRENT_PRICE=p;
        if(p==0f){
            //hide the data
        }

    }
}
