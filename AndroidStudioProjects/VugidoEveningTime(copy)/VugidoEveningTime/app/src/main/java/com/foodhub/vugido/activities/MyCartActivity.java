package com.foodhub.vugido.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.CartAdapter;
import com.foodhub.vugido.adapters.MyCartProductsAdapter;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.dialogs.MyStatusDialog;
import com.foodhub.vugido.models.cart.get_cart.DATAItem;
import com.foodhub.vugido.models.cart.get_cart.Response;
import com.foodhub.vugido.models.product.client_products.FOODSItem;
import com.foodhub.vugido.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;


public class MyCartActivity extends AppCompatActivity implements CartAdapter.CartDelete,CartAdapter.INC_DEC,
        OnClickListener {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button CartContinueButton;
    private CartAdapter myCartProductsAdapter;
    private TextView TotalPrice,TextCartEmpty;
    LinearLayout PriceLayout;
    //View Progress;
    ImageView CartEmpty;
    private List<DATAItem> data;

    MyDialogLoader myDialogLoader;
    int CID=0;

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
        setContentView(R.layout.fragment_my_cart);
        toolbar=findViewById(R.id.cart_toolbar);

        toolbar.setTitle("My Cart");
        CID=getIntent().getIntExtra("CID",0);

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

//        new MyPreferences(this).setOrderableCartCount(0);
//        new MyPreferences(this).setCartCount(0);
//        new MyPreferences(this).setCartPrice(0);

        recyclerView=findViewById(R.id.cart_recycler_view);
        CartContinueButton=findViewById(R.id.Cart_Continue_Button);
        TotalPrice=findViewById(R.id.total_cart_price);
        PriceLayout=findViewById(R.id.linearLayout);
        CartEmpty=findViewById(R.id.imageView3);
        TextCartEmpty=findViewById(R.id.textView6);
        CartEmpty.setVisibility(View.GONE);
        TextCartEmpty.setVisibility(View.GONE);
        CartContinueButton.setOnClickListener(this);
        PriceLayout.setVisibility(View.INVISIBLE);

        new MyPreferences(getApplicationContext()).setOrderablePro(null);
        if (new MyPreferences(getApplicationContext()).getCartCount()==0)
            CartEmpty.setVisibility(View.VISIBLE);
        else
            fetchUserCartData();



    }






    private void fetchUserCartData() {


        loginLoader(true,"Getting your food...");
        Map<String, Object> map=new HashMap<>();
        map.put("UID", new MyPreferences(this).getUID());// user id
        map.put("CID",String.valueOf(CID));

//        Log.e("cp",new MyPreferences(this).getCartProducts());

        if (new MyPreferences(this).getCartProducts()!=null)
            Log.e("cp",new MyPreferences(this).getCartProducts());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().fetchCartItems(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                loginLoader(false,"");
                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok in cart products..

                        Log.e("response",response.toString());
                        data=response.body().getDATA();
                        setAdapter(data);

                        ///////// CHECK CART ELIGIBILITY REQUEST...

                    }else {

                        // empty cart..
                        Log.e("response",response.toString());

                        PriceLayout.setVisibility(View.INVISIBLE);
                        CartEmpty.setVisibility(View.VISIBLE);
                        TextCartEmpty.setVisibility(View.VISIBLE);
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

    private void setAdapter(List<DATAItem> dataItem) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCartProductsAdapter=new CartAdapter(dataItem,this,CID);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCartProductsAdapter);
        setTotalPrice(dataItem);


            }

    private void setTotalPrice(List<DATAItem> dataItemList) {

        float price=0;
        int ccc=0;
        for(DATAItem dataItem:dataItemList){

            if(dataItem.getINSTOCK()==0){

                ccc+=dataItem.getCC();
                if(dataItem.getOFFER()>0){
                    price+= offerPercentagePrice(dataItem.getOFFER(),dataItem.getPRICE()*dataItem.getCC());


                }else {

                    price+= dataItem.getPRICE()*dataItem.getCC();

                }

            }

        }



        // now set total price...
        PriceLayout.setVisibility(View.VISIBLE);
        TotalPrice.setText(String.format("Rs.%s/-", price));

        new MyPreferences(this).setCartCount(ccc);
        new MyPreferences(this).setCartPrice((float) price);
        checkCartContinueButton();



        if(price<=0){
            CartEmpty.setVisibility(View.VISIBLE);
            TextCartEmpty.setVisibility(View.VISIBLE);
            PriceLayout.setVisibility(View.INVISIBLE);
        }else{
            CartEmpty.setVisibility(View.GONE);
            TextCartEmpty.setVisibility(View.GONE);

        }



    }

    private float offerPercentagePrice(float offer, float price) {

        float x=((price*offer)/100f);
        return price-x;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ORDER_PLACED_CODE){

            if(resultCode==RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.Cart_Continue_Button:

                    Intent intent=new Intent(MyCartActivity.this,MapsActivity.class);
                    intent.putExtra("CID",CID);
                    startActivityForResult(intent,ORDER_PLACED_CODE);

                break;


        }
    }




    private void checkCartContinueButton() {

       // Toast.makeText(this,String.valueOf(new MyPreferences(this).getCartCount()),Toast.LENGTH_LONG).show();
        //new MyPreferences(this).getCartCount();
        boolean activeInCart=false;
        for(DATAItem dataItem:data)
        {
            if (dataItem.getINSTOCK()==0){
                activeInCart=true;
                break;
            }
        }

        if (!activeInCart)
            CartContinueButton.setEnabled(false);
        else
            CartContinueButton.setEnabled(true);

    }

    private void showDialog(boolean status,int id){



        final MyStatusDialog s;
        s=new MyStatusDialog();
        Bundle bundle=new Bundle();

        if(status){
            bundle.putString("MSG","Removed Successfully");
            bundle.putBoolean("STATUS",true);

            // refresh the data set of..adapter..
           // refreshRecyclerViewAdapter(id);

        }else {
            bundle.putString("MSG","Error Try Again !!");
            bundle.putBoolean("STATUS",false);

        }

        s.setArguments(bundle);
        s.show(getSupportFragmentManager(),"STATUS");


    }

    @Override
    public void inc_dec(int pid, boolean is_inc, int position,int cid) {
        Log.e("clicked","ok");
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        if (is_inc)
            map.put("IS_INC",String.valueOf(1));
        else
            map.put("IS_INC",String.valueOf(0));

        map.put("PID",String.valueOf(pid));
        map.put("CID",String.valueOf(cid));

        Call<com.foodhub.vugido.models.cart.add_to_cart.Response> call=RetrofitBuilder.getInstance().getRetrofit().toCart(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.cart.add_to_cart.Response>() {
            @Override
            public void onResponse(Call<com.foodhub.vugido.models.cart.add_to_cart.Response> call,
                                   retrofit2.Response<com.foodhub.vugido.models.cart.add_to_cart.Response> response) {

                if(response.isSuccessful()){

                    if(response.body().isSTATUS()){
                        //ok update the cc count..
                        data.get(position).setCC(response.body().getCC());
                        myCartProductsAdapter.notifyItemChanged(position);

                        setTotalPrice(data);
                    }
                }
            }

            @Override
            public void onFailure(Call<com.foodhub.vugido.models.cart.add_to_cart.Response> call,
                                  Throwable t) {

            }
        });
    }
    private void refreshRecyclerViewAdapter(int id) {

        recyclerView.removeAllViews();
        data.remove(id);

        if(data.size()<0){
            // show cart empty...


        }else {
            myCartProductsAdapter = null;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            myCartProductsAdapter = new CartAdapter(data, this,CID);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(myCartProductsAdapter);

        }

        setTotalPrice(data);

    }

    @Override
    public void cartDelete(int CART_ID, int position) {
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("CART_ID",String.valueOf(CART_ID));

        loginLoader(true,"Removing Product");
        Call<com.foodhub.vugido.models.cart.cart_delete.Response> call=
                RetrofitBuilder.getInstance().getRetrofit().deleteFromCart(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.cart.cart_delete.Response>() {
            @Override
            public void onResponse(Call<com.foodhub.vugido.models.cart.cart_delete.Response> call,
                                   retrofit2.Response<com.foodhub.vugido.models.cart.cart_delete.Response> response) {
                loginLoader(false,"Removing Product");

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        refreshRecyclerViewAdapter(position);
                    }

                }
            }

            @Override
            public void onFailure(Call<com.foodhub.vugido.models.cart.cart_delete.Response> call, Throwable t) {
                loginLoader(false,"Removing Product");

            }
        });




    }





}
