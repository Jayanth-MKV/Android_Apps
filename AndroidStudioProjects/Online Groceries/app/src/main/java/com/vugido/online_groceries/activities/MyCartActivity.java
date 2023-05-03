package com.vugido.online_groceries.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.online_groceries.R;
import com.vugido.online_groceries.adapters.CartAdapter;
import com.vugido.online_groceries.adapters.MyCartProductsAdapter;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.dialogs.MyStatusDialog;
import com.vugido.online_groceries.models.cart.DATA;
import com.vugido.online_groceries.models.cart.PRESENTItem;
import com.vugido.online_groceries.models.cart.get_cart.DATAItem;
import com.vugido.online_groceries.models.cart.get_cart.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.online_groceries.activities.MainActivity.ORDER_PLACED_CODE;


public class MyCartActivity extends AppCompatActivity implements CartAdapter.CartDelete,
        CartAdapter.INC_DEC,OnClickListener {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button CartContinueButton;
    private CartAdapter myCartProductsAdapter;
    private TextView TotalPrice,TextCartEmpty;
    LinearLayout PriceLayout;
    ImageView CartEmpty;
    private DATA data;
    List<DATAItem> dataItemList;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_my_cart);
        toolbar=findViewById(R.id.cart_toolbar);

        toolbar.setTitle("My Cart");

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


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
            fetchUserCartData();



    }






    private void fetchUserCartData() {


        loginLoader(true,"Getting your food...");
        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        Call<Response> call=RetrofitBuilder.getInstance().getRetrofit().fetchCartItems(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                loginLoader(false,"");

                if(response.isSuccessful()){

                    if(response.body().isSTATUS()){

                        //ok data exists...
                        CartEmpty.setVisibility(View.GONE);
                        TextCartEmpty.setVisibility(View.GONE);
                        bindCartData(response.body().getDATA());

                    }else {

                        //no data...
                        CartEmpty.setVisibility(View.VISIBLE);
                        TextCartEmpty.setVisibility(View.VISIBLE);

                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                loginLoader(false,"");

            }
        });
    }

    private void bindCartData(List<DATAItem> data) {
        dataItemList=data;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCartProductsAdapter=new CartAdapter(data,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCartProductsAdapter);

        setTotalPrice(dataItemList);



    }


    private void setTotalPrice(List<DATAItem> dataItemList) {

        float price=0;
        int cc=0;
        for(DATAItem dataItem:dataItemList){

            if(dataItem.getINSTOCK()==1){

                cc+=1;
                if(dataItem.getOFFERENABLED()==1){
                    price+= offerPercentagePrice(Float.parseFloat(dataItem.getOFFER()),Float.parseFloat(dataItem.getPRICE())*dataItem.getCARTCOUNT());


                }else {

                    price+= Float.parseFloat(dataItem.getPRICE())*dataItem.getCARTCOUNT();

                }

            }

        }




        // now set total price...
        PriceLayout.setVisibility(View.VISIBLE);
        TotalPrice.setText(String.format("Rs.%s/-", price));

        new MyPreferences(this).setCartCount(cc);
        new MyPreferences(this).setCartPrice((float) price);
        checkCartContinueButton();

        if(price<=0){
            CartEmpty.setVisibility(View.VISIBLE);
            TextCartEmpty.setVisibility(View.VISIBLE);
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
                    startActivityForResult(intent,ORDER_PLACED_CODE);

                break;


        }
    }





    private void checkCartContinueButton() {

       // Toast.makeText(this,String.valueOf(new MyPreferences(this).getCartCount()),Toast.LENGTH_LONG).show();
        //new MyPreferences(this).getCartCount();
        boolean activeInCart=false;
        for(DATAItem dataItem:dataItemList)
        {
            if (dataItem.getINSTOCK()==1){
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
            refreshRecyclerViewAdapter(id);

        }else {
            bundle.putString("MSG","Error Try Again !!");
            bundle.putBoolean("STATUS",false);

        }

        s.setArguments(bundle);
        s.show(getSupportFragmentManager(),"STATUS");


    }

    private void refreshRecyclerViewAdapter(int id) {

        recyclerView.removeAllViews();
        dataItemList.remove(id);

        if(dataItemList.size()<0){
            // show cart empty...


        }else {
            myCartProductsAdapter = null;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            myCartProductsAdapter = new CartAdapter(dataItemList, this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(myCartProductsAdapter);

        }

        setTotalPrice(dataItemList);

    }
    @Override
    public void inc_dec(int pid, boolean is_inc,int current_position) {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        if (is_inc)
            map.put("IS_INC",String.valueOf(1));
        else
            map.put("IS_INC",String.valueOf(0));

        map.put("PID",String.valueOf(pid));

        Call<com.vugido.online_groceries.models.cart.addtocart.Response> call=RetrofitBuilder.getInstance().getRetrofit().addToCart(map);

        call.enqueue(new Callback<com.vugido.online_groceries.models.cart.addtocart.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.online_groceries.models.cart.addtocart.Response> call,
                                   @NonNull retrofit2.Response<com.vugido.online_groceries.models.cart.addtocart.Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok update the cc count..
                        dataItemList.get(current_position).setCARTCOUNT(response.body().getCC());
                        myCartProductsAdapter.notifyItemChanged(current_position);
                        setTotalPrice(dataItemList);

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.online_groceries.models.cart.addtocart.Response> call, @NonNull Throwable t) {

            }
        });


    }


    @Override
    public void cartDelete(int CART_ID, int position) {
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("CART_ID",String.valueOf(CART_ID));

        loginLoader(true,"Removing Product");
        Call<com.vugido.online_groceries.models.cart.cart_delete.Response> call=RetrofitBuilder.getInstance().getRetrofit().deleteFromCart(map);

        call.enqueue(new Callback<com.vugido.online_groceries.models.cart.cart_delete.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.online_groceries.models.cart.cart_delete.Response> call,
                                   @NonNull retrofit2.Response<com.vugido.online_groceries.models.cart.cart_delete.Response> response) {

                loginLoader(false,"Removing Product");

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        refreshRecyclerViewAdapter(position);
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.online_groceries.models.cart.cart_delete.Response> call,
                                  @NonNull Throwable t) {

                loginLoader(false,"Removing Product");

            }
        });


    }
}
