package com.vugido.online_groceries.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.models.base_product.BaseProduct;
import com.vugido.online_groceries.models.cart.addtocart.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.vugido.online_groceries.activities.MainActivity.ORDER_PLACED_CODE;


public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView ProductImage;
    TextView ProductName,ProductDes,ProductOffer,ProductPrice,ProductStrikedPrice,ItemCounter,QQ;
    View HorizontalDivider;
   // private Button AddToCart;

    float price;
    int pid;    TextView cc,cp,incc;

    private RelativeLayout OutOfStock;

    BaseProduct baseProduct;
    ImageButton inc,dec;

    View goCart;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));

        if(requestCode==ORDER_PLACED_CODE){

            if(resultCode==RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_details);
        OutOfStock=findViewById(R.id.ots);
        OutOfStock.setVisibility(View.GONE);
           incc=findViewById(R.id.textView2);

        inc=findViewById(R.id.inc);
        dec=findViewById(R.id.dec);

        cc=findViewById(R.id.textView16);
        cp=findViewById(R.id.textView17);

        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText( String.valueOf(new MyPreferences(this).getCartPrice())+"/-");

        //Toast.makeText(this,String.valueOf(new MyPreferences(this).getCartPrice()),Toast.LENGTH_LONG).show();

        //set all ids
        ProductImage=findViewById(R.id.product_image);
        ProductName=findViewById(R.id.product_name);
        ProductOffer=findViewById(R.id.product_offer);
        ProductDes=findViewById(R.id.product_description);
        HorizontalDivider=findViewById(R.id.strike_line);
        ProductPrice=findViewById(R.id.product_price);
        ProductStrikedPrice=findViewById(R.id.cut_down_price);
//        AddToCart=findViewById(R.id.add_to_cart_button);
//
//
//        AddToCart.setOnClickListener(this);

        goCart=findViewById(R.id.include3);
        goCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductDetailsActivity.this,MyCartActivity.class);
                startActivityForResult(intent,ORDER_PLACED_CODE);
            }
        });

        // binding data from the bundle...

        Bundle bundle=getIntent().getBundleExtra("BUNDLE");

        assert bundle != null;
       baseProduct =bundle.getParcelable("BASE_OBJECT");

        assert baseProduct != null;
        bindItemToLayout(baseProduct);

        ////////////////////////////////////////////


        inc.setOnClickListener(v -> {
            inc_dec(baseProduct.getiD(),true);
        });

        dec.setOnClickListener(v -> {
            if(baseProduct.getcARTCOUNT()!=0){
                inc_dec(baseProduct.getiD(),false);
            }
        });



    }

    public void inc_dec(int pid, boolean is_inc) {

        Map<String,Object> map=new HashMap<>();
        map.put("UID","1");
        if (is_inc)
            map.put("IS_INC",String.valueOf(1));
        else
            map.put("IS_INC",String.valueOf(0));

        map.put("PID",String.valueOf(pid));

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().addToCart(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.online_groceries.models.cart.addtocart.Response> call,
                                   @NonNull retrofit2.Response<com.vugido.online_groceries.models.cart.addtocart.Response> response) {


                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok update the cc count..
                        baseProduct.setcARTCOUNT(response.body().getCC());
                        bindItemToLayout(baseProduct);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.online_groceries.models.cart.addtocart.Response> call, @NonNull Throwable t) {

            }
        });


    }

    private float offerPercentagePrice(float offer, float price) {

        float x=((price*offer)/100f);
        return price-x;

    }

    private void bindItemToLayout(BaseProduct ProductItem) {

        pid=ProductItem.getiD();
        price=Float.parseFloat(ProductItem.getpRICE());
        ProductName.setText(ProductItem.gettITLE());
        ProductDes.setText(ProductItem.getqUANTITY());
        Glide.with(this).load(ProductItem.getiMAGE()).into(ProductImage);


        //check for in stock...
               if(ProductItem.getiNSTOCK()==0){
         //out of stock
          //  AddToCart.setEnabled(false);
            OutOfStock.setVisibility(View.VISIBLE);
        }else{

          //  AddToCart.setEnabled(true);
            OutOfStock.setVisibility(View.GONE);
        }

        if(ProductItem.getoFFERENABLED()==1){

            // offer exists..
            // show offer tag
            ProductOffer.setVisibility(View.VISIBLE);
            ProductOffer.setText(String.format(Locale.getDefault(),"%f%%", Float.parseFloat(ProductItem.getoFFER())));
            // show cut price and also  final price
            ProductStrikedPrice.setVisibility(View.VISIBLE);
            HorizontalDivider.setVisibility(View.VISIBLE);
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%f/-",
                    offerPercentagePrice(Float.parseFloat(ProductItem.getoFFER()),Float.parseFloat(ProductItem.getpRICE())*ProductItem.getcARTCOUNT())));

            ProductStrikedPrice.setText(String.format(Locale.getDefault(),"Rs.%f/-", Float.parseFloat(ProductItem.getpRICE())*ProductItem.getcARTCOUNT()));



        }else{

            // no offer..
            ProductStrikedPrice.setVisibility(View.INVISIBLE);
            HorizontalDivider.setVisibility(View.INVISIBLE);
            ProductOffer.setVisibility(View.INVISIBLE);
            float t=Float.parseFloat(ProductItem.getpRICE())*ProductItem.getcARTCOUNT();
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%f/-", t));
        }
        incc.setText(String.valueOf(baseProduct.getcARTCOUNT()));



    }









    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add_to_cart_button:
                addToCart();
                break;

        }

    }

    private void addToCart() {



    }


}
