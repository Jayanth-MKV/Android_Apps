package com.foodhub.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.foodhub.vugido.adapters.MyCartProductsAdapter;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.dialogs.MyStatusDialog;
import com.foodhub.vugido.models.cart.DATA;
import com.foodhub.vugido.models.cart.PRESENTItem;
import com.foodhub.vugido.models.cart.Response;
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


public class MyCartActivity extends AppCompatActivity implements OnClickListener, MyCartProductsAdapter.CartDelete  {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button CartContinueButton;
    private MyCartProductsAdapter myCartProductsAdapter;
    private TextView TotalPrice,TextCartEmpty;
    LinearLayout PriceLayout;
    //View Progress;
    ImageView CartEmpty;
    private DATA data;

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
        map.put("CP",new MyPreferences(this).getCartProducts());

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
                        DATA FetcheddataItem=response.body().getDATA();
                        data=FetcheddataItem;
                        setAdapter(FetcheddataItem);

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

    private void setAdapter(DATA dataItem) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCartProductsAdapter=new MyCartProductsAdapter(dataItem.getPRESENT(),this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCartProductsAdapter);
        setTotalPrice(dataItem.getPRESENT());


//        String s=new MyPreferences(this).getCartProducts();
//
//        String[] s1=s.split(";");
//
//        ArrayList<String> arrayList=new ArrayList<>();
//        Collections.addAll(arrayList,s1);
//        for (int i=0;i<arrayList.size();i++){
//            List<PRESENTItem> presentItemList=dataItem.getPRESENT();
//            PRESENTItem presentItem=presentItemList.get(i);
//            String s2=arrayList.get(i);
//            String[] s3=s2.split(",");
//
//            if (s3[0].equals(String.valueOf(cid)) && s3[1].equals(String.valueOf(pid))) {
//                arrayList.remove(i);
//                break;
//            }
//
//        }

        StringBuilder x= new StringBuilder();
        boolean f=false;
        for (int i=0;i<dataItem.getPRESENT().size();i++) {

            if (dataItem.getPRESENT().get(i).getINSTOCK()==0){
                String s4=dataItem.getPRESENT().get(i).getcID()+","+dataItem.getPRESENT().get(i).getID();
                if (i==0) {
                    x.append(s4);
                    f=true;
                }
                else {
                    if (f)
                        x.append(";").append(s4);
                    else
                        x.append(s4);
                }
            }


        }

        new  MyPreferences(this).setOrderablePro(x.toString());


            }

    private void setTotalPrice(List<PRESENTItem> dataItemList) {

        double price=0;
        for(PRESENTItem dataItem:dataItemList){

            if(dataItem.getINSTOCK()==0){

                if(dataItem.getOFFER()>0){
                    price+= offerPercentagePrice(dataItem.getOFFER(),dataItem.getPRICE());


                }else {

             price+= dataItem.getPRICE();

                }

            }

        }




        // now set total price...
        PriceLayout.setVisibility(View.VISIBLE);
        TotalPrice.setText(String.format("Rs.%s/-", price));

        new MyPreferences(this).setCartCount(dataItemList.size());
        new MyPreferences(this).setCartPrice((float) price);
        checkCartContinueButton();




    }

    private int offerPercentagePrice(int offer, int price) {

        return  ((price*offer)/100);

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

                //check service availability...



                //get all cart products ids in stock...
                StringBuilder stringBuilder=new StringBuilder();
                for(PRESENTItem dataItem:data.getPRESENT()){
                    if(dataItem.getINSTOCK()==0){

                        //in stock...
                        stringBuilder.append(dataItem.getcID()+","+dataItem.getID());
                        stringBuilder.append(";");

                    }
                }


               // checkCartContinueButton();
              //  Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                if(stringBuilder.length()>0){
                    stringBuilder.deleteCharAt(stringBuilder.length()-1);
                   // Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                    //sendOrderableData(stringBuilder);
                    Intent intent=new Intent(MyCartActivity.this,MapsActivity.class);
                    startActivityForResult(intent,ORDER_PLACED_CODE);
                }
                break;


        }
    }



    @Override
    public void cartDelete(final int Card_ID,int pid,int cid) {


        String s=new MyPreferences(this).getCartProducts();

        String[] s1=s.split(";");

        //Log.e("spl", Arrays.toString(s1));
        ArrayList<String> arrayList=new ArrayList<>();
        Collections.addAll(arrayList,s1);
        for (int i=0;i<arrayList.size();i++){
            String s2=arrayList.get(i);
            String[] s3=s2.split(",");

            if (s3[0].equals(String.valueOf(cid)) && s3[1].equals(String.valueOf(pid))) {
                arrayList.remove(i);
                break;
            }

        }

        //Log.e("spl2",arrayList.toString());
        StringBuilder x= new StringBuilder();
        for (int i=0;i<arrayList.size();i++) {
            String s4=arrayList.get(i);
            if (i==0)
                x.append(s4);
            else
                x.append(";").append(s4);

        }
        Log.e("spl3",x.toString());

        if (arrayList.size()>0)
            new MyPreferences(this).setCartProducts(x.toString());
        else {
            new MyPreferences(this).setCartProducts(null);
            new MyPreferences(this).setCartCount(0);
        }

        showDialog(true,Card_ID);

    }

    private void checkCartContinueButton() {

       // Toast.makeText(this,String.valueOf(new MyPreferences(this).getCartCount()),Toast.LENGTH_LONG).show();
        //new MyPreferences(this).getCartCount();
        boolean activeInCart=false;
        for(PRESENTItem dataItem:data.getPRESENT())
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


//       for(com.foodhub.vugido.models.updated.cart.FetchCart.DATAItem dataItem: dataItemList){
//
//           if(dataItem.getCARTID()==id){
//               dataItemList.remove(dataItem);
//               break;
//           }
//       }
        data.getPRESENT().remove(id);
       myCartProductsAdapter=null;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCartProductsAdapter=new MyCartProductsAdapter(data.getPRESENT(),this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCartProductsAdapter);

        if(data.getPRESENT().size()>0){
            // products exists..
            setTotalPrice(data.getPRESENT());

            StringBuilder x= new StringBuilder();
            for (int i=0;i<data.getPRESENT().size();i++) {

                if (data.getPRESENT().get(i).getINSTOCK()==0){
                    String s4=data.getPRESENT().get(i).getcID()+","+data.getPRESENT().get(i).getID();
                    if (i==0)
                        x.append(s4);
                    else
                        x.append(";").append(s4);
                }


            }



            new  MyPreferences(this).setOrderablePro(x.toString());


        }else {

            // no products cart empty
          //  recyclerView.removeAllViews();
           // recyclerView.setVisibility(View.GONE);

            setTotalPrice(data.getPRESENT());

            PriceLayout.setVisibility(View.INVISIBLE);
            CartEmpty.setVisibility(View.VISIBLE);
            TextCartEmpty.setVisibility(View.VISIBLE);

            // show cart empty message
        }

    }



}
