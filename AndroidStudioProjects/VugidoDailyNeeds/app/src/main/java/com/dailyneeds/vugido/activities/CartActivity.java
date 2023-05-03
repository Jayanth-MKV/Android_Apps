package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.Retrofit.RetrofitBuilder;
import com.dailyneeds.vugido.adapters.CartAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.JsonResponseParser;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.design.Space;
import com.dailyneeds.vugido.dialogs.AppWeeklyServiveTime;
import com.dailyneeds.vugido.models.CartDelete.Response;
import com.dailyneeds.vugido.models.CartProducts;
import com.dailyneeds.vugido.models.FinalOrderableProducts;
import com.dailyneeds.vugido.receivers.NetworkCallBack;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;


public class CartActivity extends AppCompatActivity implements View.OnClickListener , NetworkQueries.NetworkQueryInterface, CartAdapter.SetPriceTag , CartAdapter.CartDelete {
    RecyclerView recyclerView;
    RelativeLayout CartEmptyLayout;
    Toolbar toolbar;
    Button Continue,BackToMenu;
    TextView P;
    List<CartProducts> cartlist;
    CardView BottomCartInfo;
    FinalOrderableProducts finalOrderableProducts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_items);
        toolbar=findViewById(R.id.toolbar);
        Continue=findViewById(R.id.cartContinueButton);
        P=findViewById(R.id.Price);
      //  Cut=findViewById(R.id.cutOffPrice);
        setSupportActionBar(toolbar);
        Continue.setOnClickListener(this);
        BackToMenu=findViewById(R.id.backToMenu);
        BackToMenu.setOnClickListener(this);
        CartEmptyLayout= findViewById(R.id.cart_empty_layout);
        BottomCartInfo=findViewById(R.id.cartInfo);
        toolbar.setNavigationIcon(getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(view -> finish());
        recyclerView=findViewById(R.id.cart_item_recyclerView);


        // getting all cart products...


        NetworkQueries networkQueries=new NetworkQueries(this, ConfigVariables.GET_CART_PRODUCTS_URL,buildParams());
        networkQueries.sendRequest("Getting Cart Products");



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== ConfigVariables.ORDER_PLACED_CODE && resultCode== ConfigVariables.ORDER_PLACED_RESPONSE_CODE){

           // setResult(ConfigVariables.ORDER_PLACED_RESPONSE_CODE);
            setResult(1);
            finish();

        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.cartContinueButton){


            // check the service

           if(ConfigVariables.appWeeklyServiceTimings()){
                Intent intent=new Intent(CartActivity.this,ShippingActivity.class);

               // Intent intent=new Intent(CartActivity.this,MapActivity.class);
                Bundle bundle=new Bundle();
                if(finalOrderableProducts!=null){
                    // Toast.makeText(this,"not null",Toast.LENGTH_LONG).show();
                    bundle.putParcelable("FINAL",finalOrderableProducts);
                    intent.putExtra("BUNDLE",bundle);
                    startActivityForResult(intent,ConfigVariables.ORDER_PLACED_CODE);
                }else {
                    Toast.makeText(this,"null",Toast.LENGTH_LONG).show();
                }

            }else {

                // no service
                AppWeeklyServiveTime appWeeklyServiveTime=new AppWeeklyServiveTime();
                appWeeklyServiveTime.show(getSupportFragmentManager(),"WEEKLY");

            }




          //  Toast.makeText(this,"null",Toast.LENGTH_LONG).show();

        }else if(view.getId()==R.id.backToMenu){

            finish();

        }

    }



    @Override
    protected void onResume() {
        super.onResume();

        int TIME = 2000;
        new Handler().postDelayed(() -> {

            if(!NetworkCallBack.NetworkAvailability){

                startActivity(new Intent(CartActivity.this, NetworkErrorActivity.class));

            }
        }, TIME);
    }


    @Override
    public void networkQueryInterface(String Response) {


        try {
            JSONObject jsonObject=new JSONObject(Response);
            boolean error =jsonObject.getBoolean("error");


            if(!error){


                JsonResponseParser jsonResponseParser=new JsonResponseParser(Response);
                List<CartProducts> productList=jsonResponseParser.getJsonCartProductParser();
                //boolean active=true;
                int count=0;
                for(CartProducts cartProducts:productList){

                    if(!cartProducts.isChecked()){

                        count++;
                    }

                }

                if(count==productList.size()){
                    Continue.setEnabled(false);
                    Continue.setBackground(getDrawable(R.drawable.disable_button));

                }


                bindToAdapter(productList);

            }else {

                // show user no cart products available..
                CartEmptyLayout.setVisibility(View.VISIBLE);
                BottomCartInfo.setVisibility(View.GONE);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void bindToAdapter(List<CartProducts> productList) {

        List<CartProducts> list=new ArrayList<>();

        for(CartProducts cartProducts:productList){

            if(cartProducts.isChecked()){
                list.add(cartProducts);
            }

        }

        cartlist=list;



        CartAdapter cartAdapter=new CartAdapter(this,productList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,25,true,0));
        recyclerView.setAdapter(cartAdapter);

        if(finalOrderableProducts==null){
            finalOrderableProducts=new FinalOrderableProducts();
        }
        finalOrderableProducts.setActualTotalPrice(getActualPrice());
        finalOrderableProducts.setCartProductsList(cartlist);
        P.setText(String.format("Rs.%s/-", String.valueOf(getActualPrice())));

    }

    @Override
    public void networkQueryError(String error) {

    }

    private Map<String,String> buildParams(){

        Map<String,String> params=new HashMap<>();
        params.put("UID",new MyPreferences(this).getUID());
        return  params;

    }


    @Override
    public void setPriceTag(CartProducts cartProducts,int position) {

      /* for(int i=0;i<cartlist.size();i++){

           CartProducts products=cartlist.get(i);


           if(cartProducts.getPID().equals(products.getPID())){


               cartlist.get(i).setChecked(cartProducts.isChecked());


           }


           }*/

       cartlist.get(position).setChecked(cartProducts.isChecked());



       if(getActualPrice()==0){

           Continue.setEnabled(false);
           Continue.setBackground(getDrawable(R.drawable.disable_button));

       }else {

           Continue.setEnabled(true);
           Continue.setBackground(getDrawable(R.drawable.button));
       }

       if(finalOrderableProducts==null){
           finalOrderableProducts=new FinalOrderableProducts();

       }

       filterCartList(cartlist);

        P.setText(String.format("Rs.%s/-", String.valueOf(getActualPrice())));




        }

    private void filterCartList(List<CartProducts> cartlist) {

        List<CartProducts> cartProductsList= new ArrayList<>();
        for (CartProducts cartProducts:cartlist){

            if(cartProducts.isChecked()){
                cartProductsList.add(cartProducts);
            }

        }
        finalOrderableProducts.setCartProductsList(cartProductsList);
        finalOrderableProducts.setActualTotalPrice(getActualPrice());
    }


    private double getActualPrice(){

      // double Price=0;
       double ActualPrice=0;
       for(int i=0;i<cartlist.size();i++){

           CartProducts products=cartlist.get(i);
           if(products.isChecked())
           ActualPrice= ActualPrice+Double.parseDouble(products.getProductTotalPrice());


       }
       return ActualPrice;

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public void cartDelete(int Card_ID) {




        Map<String, Object> map=new HashMap<>();
        map.put("CART_ID", String.valueOf(Card_ID));// user id
        map.put("UID",new MyPreferences(this).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().deleteCartItems(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok deleted
                        showDialog(true,Card_ID);

                        int OldCount=new MyPreferences(CartActivity.this).getCartCount();
                        new MyPreferences(CartActivity.this).setCartCount(OldCount-1);

                    }else {

                        //error in deleting
                        showDialog(false,0);

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                showDialog(false,0);


            }
        });


    }

    private void showDialog(boolean status,int id){

        SweetAlertDialog s;

        if(status){
            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Removed Successfully");
            s.show();
            // refresh the data set of..adapter..
            refreshRecyclerViewAdapter(id);

        }else {
            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Not - Removed Try Again!");
            s.show();

        }



    }

    private void refreshRecyclerViewAdapter(int id) {
        recyclerView.removeAllViews();


        for(CartProducts dataItem: cartlist){

            if(Integer.parseInt(dataItem.getUNIQUE_ID())==id){
                cartlist.remove(dataItem);
                break;
            }
        }
      //  myCartProductsAdapter=null;
        /////////////////////////

        List<CartProducts> list=new ArrayList<>();

        for(CartProducts cartProducts:cartlist){

            if(cartProducts.isChecked()){
                list.add(cartProducts);
            }

        }

        cartlist=list;



        CartAdapter cartAdapter=new CartAdapter(this,cartlist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,25,true,0));
        recyclerView.setAdapter(cartAdapter);

        finalOrderableProducts=null;
        finalOrderableProducts=new FinalOrderableProducts();

        finalOrderableProducts.setActualTotalPrice(getActualPrice());
        finalOrderableProducts.setCartProductsList(cartlist);
        P.setText(String.format("Rs.%s/-", String.valueOf(getActualPrice())));


        if(getActualPrice()==0){

            Continue.setEnabled(false);
            Continue.setBackground(getDrawable(R.drawable.disable_button));
            CartEmptyLayout.setVisibility(View.VISIBLE);
            BottomCartInfo.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);


        }else {

            Continue.setEnabled(true);
            Continue.setBackground(getDrawable(R.drawable.button));
        }
      //  setPriceTag();









        //////////////////////////////////
        /*LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCartProductsAdapter=new MyCartProductsAdapter(dataItemList,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCartProductsAdapter);*/




    }


}
