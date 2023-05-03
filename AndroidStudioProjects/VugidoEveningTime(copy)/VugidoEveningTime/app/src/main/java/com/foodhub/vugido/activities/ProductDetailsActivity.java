package com.foodhub.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.models.base_product.BaseProduct;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.foodhub.vugido.activities.MainActivity.ORDER_PLACED_CODE;


public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView ProductImage;
    TextView ProductName,ProductDes,ProductOffer,ProductPrice,ProductStrikedPrice,ItemCounter,QQ;
    View HorizontalDivider;
    private Button AddToCart;

    int price;
    int CID,pid;    TextView cc,cp;

    private RelativeLayout OutOfStock;

    BaseProduct baseProduct;

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
        AddToCart=findViewById(R.id.add_to_cart_button);


        AddToCart.setOnClickListener(this);

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
       CID= bundle.getInt("CID");

        assert baseProduct != null;
        bindItemToLayout(baseProduct);

        ////////////////////////////////////////////






    }

    private void bindItemToLayout(BaseProduct ProductItem) {

        pid=ProductItem.getPID();
        price=ProductItem.getPRICE();
        ProductName.setText(ProductItem.getENAME());
        ProductDes.setText(ProductItem.getQUANTITY()+" "+ProductItem.getDESCRIPTION());
        Glide.with(this).load(ProductItem.getIMAGE()).into(ProductImage);


        //check for in stock...
        if(ProductItem.getINSTOCK()==1){
         //out of stock
            AddToCart.setEnabled(false);
            OutOfStock.setVisibility(View.VISIBLE);
        }else{

            AddToCart.setEnabled(true);
            OutOfStock.setVisibility(View.GONE);
        }

        if(ProductItem.getOFFER()>0){

            // offer exists..
            // show offer tag
            ProductOffer.setVisibility(View.VISIBLE);
            ProductOffer.setText(String.format(Locale.getDefault(),"%d%%", ProductItem.getOFFER()));
            // show cut price and also  final price
            ProductStrikedPrice.setVisibility(View.VISIBLE);
            HorizontalDivider.setVisibility(View.VISIBLE);
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", offerPercentagePrice(ProductItem.getOFFER(),ProductItem.getPRICE())));

            ProductStrikedPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));



        }else{

            // no offer..
            ProductStrikedPrice.setVisibility(View.INVISIBLE);
            HorizontalDivider.setVisibility(View.INVISIBLE);
            ProductOffer.setVisibility(View.INVISIBLE);
            ProductPrice.setText(String.format(Locale.getDefault(),"Rs.%d/-", ProductItem.getPRICE()));
        }




    }

    private int offerPercentagePrice(int offer, int price) {

        return  ((price/100)*offer);

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


        String x=new MyPreferences(this).getCartProducts();

        if (x==null)
            x=CID+","+pid;
        else
            x=x+";"+CID+","+pid;

       // Log.e("ss",x.toString());

        new MyPreferences(this).setCartProducts(x);

        new MyPreferences(this).setCartCount(x.split(";").length);

        new MyPreferences(this).setCartPrice(new MyPreferences(this).getCartPrice()+price);

        cc.setText(String.valueOf(new MyPreferences(this).getCartCount()));
        cp.setText(String.format("%s/-", new MyPreferences(this).getCartPrice()));
    }


}
