package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.FreeProductsAdapter.FreeProductsAdapter;
import com.foodhub.vugido.adapters.MyCartProductsAdapter;
import com.foodhub.vugido.app_config_variables.AppUtils;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.dialogs.MyStatusDialog;
import com.foodhub.vugido.fragments.Bottom_Model_Fragments.CartDiscountBottomSheet;
import com.foodhub.vugido.fragments.Bottom_Model_Fragments.FreeProductsBottomModalSheet;
import com.foodhub.vugido.models.CartOfferModel.CARTDATA;
import com.foodhub.vugido.models.CartOfferModel.CATEGORYDATAItem;
import com.foodhub.vugido.models.CartOfferModel.DATA;
import com.foodhub.vugido.models.CartOfferModel.ELIGIBILITY;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;
import com.foodhub.vugido.models.updated.cart.FetchCart.DATAItem;
import com.foodhub.vugido.models.updated.cart.FetchCart.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;


public class MyCartActivity extends AppCompatActivity implements OnClickListener , MyCartProductsAdapter.CartDelete , FreeProductsAdapter.FreeProductAddToCart {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button CartContinueButton;
    private MyCartProductsAdapter myCartProductsAdapter;
    List<com.foodhub.vugido.models.updated.cart.FetchCart.DATAItem> dataItemList;
    private TextView TotalPrice,TextCartEmpty,CartOfferEligibility,FreeProducts;
    LinearLayout PriceLayout;
    View Progress;
    ImageView CartEmpty;
    private DATA  data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_my_cart);
        toolbar=findViewById(R.id.cart_toolbar);
        CartOfferEligibility=findViewById(R.id.cart_offer_eligibility_text);
        FreeProducts=findViewById(R.id.free_products_available);
        CartOfferEligibility.setOnClickListener(this);
        FreeProducts.setOnClickListener(this);
        FreeProducts.setVisibility(View.GONE);
        CartOfferEligibility.setVisibility(View.GONE);
        toolbar.setTitle("My Cart");

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        new MyPreferences(this).setOrderableCartCount(0);
        new MyPreferences(this).setCartCount(0);
        new MyPreferences(this).setCartPrice(0);

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
        Progress=findViewById(R.id.my_progress);

        fetchUserCartData();



//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                if(dy>0){
//                    extendedFloatingActionButton.hide();
//                }else {
//                    extendedFloatingActionButton.show();
//                }
//            }
//        });


    }






    private void fetchUserCartData() {


        Progress.setVisibility(View.VISIBLE);
        Map<String, Object> map=new HashMap<>();
        map.put("UID", new MyPreferences(this).getUID());// user id


        Call<com.foodhub.vugido.models.updated.cart.FetchCart.Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).fetchCartItems(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){
                    // ok

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok in cart products..
                        List<com.foodhub.vugido.models.updated.cart.FetchCart.DATAItem> FetcheddataItemList=response.body().getDATA();
                        dataItemList=FetcheddataItemList;
                        setAdapter(FetcheddataItemList);

                        ///////// CHECK CART ELIGIBILITY REQUEST...

                    }else {

                        // empty cart..

                        PriceLayout.setVisibility(View.INVISIBLE);
                        CartEmpty.setVisibility(View.VISIBLE);
                        TextCartEmpty.setVisibility(View.VISIBLE);
                    }
                }else {
                    // in problem

                }
                Progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
            }
        });
    }

    private void setAdapter(List<com.foodhub.vugido.models.updated.cart.FetchCart.DATAItem> dataItemList) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCartProductsAdapter=new MyCartProductsAdapter(dataItemList,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCartProductsAdapter);
        setTotalPrice(dataItemList);

    }

    private void setTotalPrice(List<com.foodhub.vugido.models.updated.cart.FetchCart.DATAItem> dataItemList) {

        double price=0;
        for(DATAItem dataItem:dataItemList){

            if(dataItem.getINSTOCK()==0){

                if(dataItem.getOFFERSTATUS()==1){
                    price+= BaseProduct.offerPercentagePrice(dataItem.getOFFER(), getQuantityPrice(dataItem.getPRICE(),dataItem), dataItem.getOFFERLIMIT());

                  //  price += ProductBaseModel.offerPercentagePrice(Double.parseDouble(String.valueOf(dataItem.getOFFER())), Double.parseDouble(String.valueOf(dataItem.getPRICE()*dataItem.getCOUNT())),dataItem.getOFFERLIMIT());

                }else {

             price+= getQuantityPrice(dataItem.getPRICE(),dataItem);

                   // price+=getProductQualifierPrice(dataItem);
                }

            }

        }


        // now set total price...
        PriceLayout.setVisibility(View.VISIBLE);
        TotalPrice.setText(String.format("Rs.%s/-", String.valueOf(price)));

        new MyPreferences(this).setCartCount(dataItemList.size());
        new MyPreferences(this).setCartPrice((float) price);
        checkCartContinueButton();
        setOrderableCartCount();



        //// CHECK CART ELIGIBILITY... network request.. this is main...


        ///FOR BETA VERSION DISABLED...
       // checkCartOffersEligibilityOfUser();


    }

    private void checkCartOffersEligibilityOfUser() {
        CartContinueButton.setEnabled(false);
        CartOfferEligibility.setVisibility(View.GONE);
        FreeProducts.setVisibility(View.GONE);
        Progress.setVisibility(View.VISIBLE);
        Map<String,Object>map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        // cart eligibility request.. to server
        Call<com.foodhub.vugido.models.CartOfferModel.Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).checkCartEligibilityOffers(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.CartOfferModel.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.CartOfferModel.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.CartOfferModel.Response> response) {

                CartContinueButton.setEnabled(true);
                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        //ok response got..
                       // Toast.makeText(getApplicationContext(),"ok res",Toast.LENGTH_SHORT).show();
                        data=response.body().getDATA();
                       // checkCartOffers(data);

                    }else {
                        // error
                        Toast.makeText(getApplicationContext(),"ok error",Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.CartOfferModel.Response> call, @NonNull Throwable t) {
                Progress.setVisibility(View.GONE);
                CartContinueButton.setEnabled(true);
            }
        });



    }

    private void checkCartOffers(DATA data) {

        ELIGIBILITY eligibility=data.getELIGIBILITY();


        if(eligibility.isCARTELIGIBILITY()){
            // cart based price..
            CARTDATA cartdata=data.getCARTDATA();
            int d1=cartdata.getREWARD().getDISCOUNT();

            if(d1>0){
                // show user the dis count
                Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
                CartOfferEligibility.setVisibility(View.VISIBLE);
                CartDiscountBottomSheet cartDiscountBottomSheet=new CartDiscountBottomSheet();
                Bundle bundle=new Bundle();
                bundle.putInt("D",d1);
                cartDiscountBottomSheet.setArguments(bundle);
                cartDiscountBottomSheet.show(getSupportFragmentManager(),"DISCOUNT");
            }else{
                // show user the products..
                FreeProducts.setVisibility(View.VISIBLE);
                Toast.makeText(this,"ok2",Toast.LENGTH_SHORT).show();

            }

        }else if(eligibility.isCATEGORYELIGIBILITY()){
            // category based...

            for (CATEGORYDATAItem categorydataItem:data.getCATEGORYDATA()){
                if(categorydataItem.getREWARD().getDISCOUNT()>0){
                    /// discount available..
                    CartOfferEligibility.setVisibility(View.VISIBLE);
                }else {
                    FreeProducts.setVisibility(View.VISIBLE);
                }

            }
            /*List<CATEGORYDATAItem> categorydataItemList=data.getCATEGORYDATA();
            int d2=categorydataItemList*/

        }else {

            //no  offer anything...
        }

    }

    private int  getQuantityPrice(float price,DATAItem baseProduct){

        if(baseProduct.getISGRAMSET()==1){
            // in grams..
            float kg = baseProduct.getCARTQUANTITY()/1000f;
            return Math.round(kg*price);

        }else {
            // in units or packets etc
            return Math.round(baseProduct.getCARTQUANTITY()*price);

        }

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

                //get all cart products ids in stock...
                StringBuilder stringBuilder=new StringBuilder();
                for(DATAItem dataItem:dataItemList){
                    if(dataItem.getINSTOCK()==0){

                        //in stock...
                        stringBuilder.append(dataItem.getCARTID());
                        stringBuilder.append(",");

                    }
                }


               // checkCartContinueButton();
              //  Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                if(stringBuilder.length()>0){
                    stringBuilder.deleteCharAt(stringBuilder.length()-1);
                   // Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                    sendOrderableData(stringBuilder);

                }
                break;
            case R.id.cart_offer_eligibility_text:
                showCartOffers(true);
                break;
            case R.id.free_products_available:
                showCartOffers(false);
                break;

        }
    }


    private void showCartOffers(boolean isDiscount) {

        Toast.makeText(getApplicationContext(),"ok check cart",Toast.LENGTH_SHORT).show();

        ELIGIBILITY eligibility=data.getELIGIBILITY();

        if(eligibility.isCARTELIGIBILITY()){
            // cart based price..
            CARTDATA cartdata=data.getCARTDATA();
            int d1=cartdata.getREWARD().getDISCOUNT();

            if(isDiscount){
                // show CartDiscountOffers
                showDiscountBootomSheet(d1);
            }else {

                // show products
                showProductsBottomSheet(String.valueOf(cartdata.getHID()),false);
            }



        }else if(eligibility.isCATEGORYELIGIBILITY()){
            // category based...

            /*List<CATEGORYDATAItem> categorydataItemList=data.getCATEGORYDATA();
            int d2=categorydataItemList*/

            if (isDiscount){
                //get all categories with discounts..
                int d=0;
                for(CATEGORYDATAItem categorydataItem:data.getCATEGORYDATA()){
                    if(categorydataItem.getREWARD().getDISCOUNT()>0){
                        d+=categorydataItem.getREWARD().getDISCOUNT();
                    }
                }
                showDiscountBootomSheet(d);
            }else {
                // get all categories  with free products..

                StringBuilder stringBuilder=new StringBuilder();
                for (int x=0;x<data.getCATEGORYDATA().size();x++){

                    if(data.getCATEGORYDATA().get(x).getREWARD().getDISCOUNT()==0){
                        if(x!=0)
                            stringBuilder.append(",");
                        stringBuilder.append(data.getCATEGORYDATA().get(x).getHID());
                    }
                }
                showProductsBottomSheet(stringBuilder.toString(),true);
            }
        }else {

            //no  offer anything...
        }

    }

    private void showProductsBottomSheet(String HidString,boolean isCategory) {


        // set the flag to false here..

        Log.e("HID MOBILE",HidString);
        FreeProductsBottomModalSheet freeProductsBottomModalSheet=new FreeProductsBottomModalSheet();
        Bundle bundle=new Bundle();
        bundle.putString("HID",HidString);
        freeProductsBottomModalSheet.setArguments(bundle);
        freeProductsBottomModalSheet.show(getSupportFragmentManager(),"FREE_PRODUCTS");
    }

    private void showDiscountBootomSheet(int d) {
        CartDiscountBottomSheet cartDiscountBottomSheet=new CartDiscountBottomSheet();
        Bundle bundle=new Bundle();
        bundle.putInt("D",d);
        cartDiscountBottomSheet.setArguments(bundle);
        cartDiscountBottomSheet.show(getSupportFragmentManager(),"DISCOUNT");
    }

    private void setOrderableCartCount(){

        int x=0;
        for(DATAItem dataItem:dataItemList){
            if(dataItem.getINSTOCK()==0){

                //in stock..
                x+=1;

            }
        }
        new MyPreferences(this).setOrderableCartCount(x);
    }

    private void sendOrderableData(StringBuilder stringBuilder) {

        Progress.setVisibility(View.VISIBLE);

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("IDS",stringBuilder);

        Call<com.foodhub.vugido.models.status.Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).transferCartToOrderable(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.status.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.status.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.status.Response> response) {

                Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        // start activity..
                        Intent intent=new Intent(MyCartActivity.this,DeliveryActivity.class);
                         startActivityForResult(intent,ORDER_PLACED_CODE);

                    }else {
                        //error
                    }
                }else {

                    //error
                }

            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.status.Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void cartDelete(final int Card_ID) {

        // initiate delete callback..
        Progress.setVisibility(View.VISIBLE);
        Map<String, Object> map=new HashMap<>();
        map.put("CART_ID", String.valueOf(Card_ID));// user id

        Call<com.foodhub.vugido.models.updated.cart.DelCart.Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).deleteCartItems(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.updated.cart.DelCart.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.updated.cart.DelCart.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.updated.cart.DelCart.Response> response) {

                Progress.setVisibility(View.GONE);

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok deleted
                        CartOfferEligibility.setVisibility(View.GONE);
                        FreeProducts.setVisibility(View.GONE);
                        showDialog(true,Card_ID);
                        checkCartContinueButton();
                    }else {

                        //error in deleting
                        showDialog(false,0);

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.updated.cart.DelCart.Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
            }
        });


    }

    private void checkCartContinueButton() {

       // Toast.makeText(this,String.valueOf(new MyPreferences(this).getCartCount()),Toast.LENGTH_LONG).show();
        //new MyPreferences(this).getCartCount();
        boolean activeInCart=false;
        for(DATAItem dataItem:dataItemList)
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


       for(com.foodhub.vugido.models.updated.cart.FetchCart.DATAItem dataItem: dataItemList){

           if(dataItem.getCARTID()==id){
               dataItemList.remove(dataItem);
               break;
           }
       }
       myCartProductsAdapter=null;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCartProductsAdapter=new MyCartProductsAdapter(dataItemList,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCartProductsAdapter);

        if(dataItemList.size()>0){
            // products exists..
            setTotalPrice(dataItemList);
        }else {

            // no products cart empty
          //  recyclerView.removeAllViews();
           // recyclerView.setVisibility(View.GONE);


            PriceLayout.setVisibility(View.INVISIBLE);
            CartEmpty.setVisibility(View.VISIBLE);
            TextCartEmpty.setVisibility(View.VISIBLE);

            // show cart empty message
        }

    }

    @Override
    public void freeProductAddToCart(int cid, int pid, int q) {
        addToCart(cid,pid,q);
    }

    private void addToCart(int cid, int pid, int q) {


        //Toast.makeText(this,baseProduct.getPID()+"pid",Toast.LENGTH_SHORT).show();
        // progress bar...


        Progress.setVisibility(View.VISIBLE);
        Map<String, Object> map=new HashMap<>();
        map.put("UID", new MyPreferences(this).getUID());// user id
        map.put("CID",String.valueOf(cid));// product cid
        map.put("PID",String.valueOf(pid));// product id
        map.put("COUNT",String.valueOf(q));// quantifier
        map.put("DATED", AppUtils.getCurrentDate()+","+AppUtils.getCurrentTime());// today's date

        Call<com.foodhub.vugido.models.updated.ToCartModel.Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).addToCart(map);
        call.enqueue(new Callback<com.foodhub.vugido.models.updated.ToCartModel.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.updated.ToCartModel.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.updated.ToCartModel.Response> response) {

               // AddToCart.setEnabled(true);
                Progress.setVisibility(View.GONE);

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        // ok successfully added to cart
                        showDialog(true);
                        new MyPreferences(getApplicationContext()).setCartCount(new MyPreferences(getApplicationContext()).getCartCount()+1);

                    }else{

                        //not added
                        showDialog(false);

                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.updated.ToCartModel.Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
               // AddToCart.setEnabled(true);
            }
        });


    }
    private void showDialog(boolean status){

        final MyStatusDialog s;
        s=new MyStatusDialog();
        Bundle bundle=new Bundle();

        if(status){
            bundle.putString("MSG","Added Successfully");
            bundle.putBoolean("STATUS",true);


        }else {
            bundle.putString("MSG","Not - Added Try Again!");
            bundle.putBoolean("STATUS",false);

        }

        s.setArguments(bundle);
        s.show(getSupportFragmentManager(),"STATUS");


    }

}
