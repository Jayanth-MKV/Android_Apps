package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.AppUtils;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.dialogs.MyStatusDialog;
import com.foodhub.vugido.models.updated.ToCartModel.Response;
import com.foodhub.vugido.models.updated.base_product.BaseProduct;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.models.updated.base_product.BaseProduct.getGramQuantity;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    View Progress;
    ImageView ProductImage;
    TextView ProductName,ProductDes,ProductOffer,ProductPrice,ProductStrikedPrice,ItemCounter,QQ;
    View HorizontalDivider;
    private Button AddToCart;
    private ImageButton QIB,QDB;
    private int QuantityValue;
    private int QuantityInterval;
    private BaseProduct baseProduct;
    int CID;
    private RelativeLayout OutOfStock;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_details);
        OutOfStock=findViewById(R.id.ots);
        OutOfStock.setVisibility(View.GONE);
        Progress=findViewById(R.id.my_progress);


        // incrementer and decrementer buttons..

        QIB=findViewById(R.id.Q_INC);
        QDB=findViewById(R.id.Q_DEC);

        // quantity value
        QQ=findViewById(R.id.QQ);


        // on click listener for inc and dec buttons

        QIB.setOnClickListener(this);
        QDB.setOnClickListener(this);


        //set all ids
        ProductImage=findViewById(R.id.product_image);
        ProductName=findViewById(R.id.product_name);
        ProductOffer=findViewById(R.id.product_offer);
        ProductDes=findViewById(R.id.product_description);
        HorizontalDivider=findViewById(R.id.strike_line);
        ProductPrice=findViewById(R.id.product_price);
        ProductStrikedPrice=findViewById(R.id.cut_down_price);
        AddToCart=findViewById(R.id.add_to_cart_button);


        AddToCart.setOnClickListener(this);


        // binding data from the bundle...

        Bundle bundle=getIntent().getBundleExtra("BUNDLE");

        assert bundle != null;
       baseProduct =bundle.getParcelable("BASE_OBJECT");
       CID= bundle.getInt("CID");

        assert baseProduct != null;
        bindItemToLayout(baseProduct);

        ////////////////////////////////////////////






    }

    private void bindItemToLayout(BaseProduct ProductItem) {

        ProductName.setText(ProductItem.getTNAME());
        Glide.with(this).load(ProductItem.getIMAGE()).into(ProductImage);
        QuantityValue=ProductItem.getQUANTITY();
        QuantityInterval=ProductItem.getQUANTITY();

        //check for in stock...
        if(ProductItem.getINSTOCK()==1){
         //out of stock
            AddToCart.setEnabled(false);
            OutOfStock.setVisibility(View.VISIBLE);
        }else{

            AddToCart.setEnabled(true);
            OutOfStock.setVisibility(View.GONE);
        }

        if(ProductItem.getOFFERSTATUS()==1){

            // offer exists..
            // show offer tag
            ProductOffer.setVisibility(View.VISIBLE);
            ProductOffer.setText(String.format(Locale.getDefault(),"%d%%", ProductItem.getOFFER()));
            // show cut price and also  final price
            ProductStrikedPrice.setVisibility(View.VISIBLE);
            HorizontalDivider.setVisibility(View.VISIBLE);
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", BaseProduct.offerPercentagePrice(ProductItem.getOFFER(), ProductItem.getPRICE(), ProductItem.getOFFERLIMIT())));
            ProductStrikedPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));



        }else{

            // no offer..
            ProductStrikedPrice.setVisibility(View.INVISIBLE);
            HorizontalDivider.setVisibility(View.INVISIBLE);
            ProductOffer.setVisibility(View.INVISIBLE);
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));
        }

        // quantity settings..
        if(ProductItem.getISGRAMSET()==1){
            // quantity is expressed in grams..
            // so check the value if it is greater than 1000 g express in kg's
            ProductDes.setText(getGramQuantity(ProductItem.getQUANTITY(),ProductItem.getDESCRIPTION()));
            QQ.setText(getGramQuantity(ProductItem.getQUANTITY(),ProductItem.getDESCRIPTION()));

        }else {

            // it's normal..
            // so concatenate the quantity value with description..

            ProductDes.setText(String.format(Locale.getDefault(),"%d %s", ProductItem.getQUANTITY(), ProductItem.getDESCRIPTION()));
            QQ.setText(String.valueOf(ProductItem.getQUANTITY()));

        }


    }









    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Q_INC:
                quantityAdjuster(true);
                break;
            case R.id.Q_DEC:
                quantityAdjuster(false);
                break;
            case R.id.add_to_cart_button:
                AddToCart.setEnabled(false);
                addToCart();
                break;

        }

    }

    private void quantityAdjuster(boolean increase){

        if(increase){
            float temp;
            if(baseProduct.getISGRAMSET()==1){
               temp = (QuantityValue+QuantityInterval)/1000f;

            }else {
                temp=QuantityValue+QuantityInterval;
            }
            if(temp > baseProduct.getCARTLIMIT()){
                // max limit reached
                Toast.makeText(this,"Tis is Maximum  Quantity",Toast.LENGTH_LONG).show();

            }else {

                // increment
                QuantityValue+=QuantityInterval;
                updatePrice(baseProduct);
            }

        }else {
            // decrease..
            if(QuantityValue==QuantityInterval){
                // reached min limit
                Toast.makeText(this,"Tis is Minimum  Quantity",Toast.LENGTH_LONG).show();

            }else {

                // decrease
                QuantityValue-=QuantityInterval;
                updatePrice(baseProduct);

            }



        }
    }

    private void updatePrice(BaseProduct ProductItem) {

        if(ProductItem.getOFFERSTATUS()==1){

            // offer exists..
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", BaseProduct.offerPercentagePrice(ProductItem.getOFFER(), getQuantityPrice(ProductItem.getPRICE()), ProductItem.getOFFERLIMIT())));
            ProductStrikedPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", getQuantityPrice(ProductItem.getPRICE())));



        }else{

            // no offer..
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", getQuantityPrice(ProductItem.getPRICE())));
        }

        // quantity settings..
        if(ProductItem.getISGRAMSET()==1){
            // quantity is expressed in grams..
            // so check the value if it is greater than 1000 g express in kg's
            QQ.setText(getGramQuantity(QuantityValue,ProductItem.getDESCRIPTION()));

        }else {

            // it's normal..
            QQ.setText(String.valueOf(QuantityValue));


        }
    }
    private int  getQuantityPrice(float price){

        if(baseProduct.getISGRAMSET()==1){
            // in grams..
            float kg = QuantityValue/1000f;
            return Math.round(kg*price);

        }else {
            // in units or packets etc
            return Math.round((QuantityValue*price)/QuantityInterval);

        }

    }

    private void addToCart() {


        //Toast.makeText(this,baseProduct.getPID()+"pid",Toast.LENGTH_SHORT).show();
        // progress bar...


        Progress.setVisibility(View.VISIBLE);
       Map<String, Object> map=new HashMap<>();
        map.put("UID", new MyPreferences(this).getUID());// user id
        map.put("CID",String.valueOf(CID));// product cid
        map.put("PID",String.valueOf(baseProduct.getPID()));// product id
        map.put("COUNT",String.valueOf(QuantityValue));// quantifier
        map.put("DATED", AppUtils.getCurrentDate()+","+AppUtils.getCurrentTime());// today's date

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).addToCart(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                AddToCart.setEnabled(true);
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
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                Progress.setVisibility(View.GONE);
                AddToCart.setEnabled(true);
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
