package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;

import java.util.Objects;

public class MyPocketCart extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button CartContinueButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pocket_cart);



        toolbar=findViewById(R.id.cart_toolbar);
        toolbar.setTitle("My Pocket Cart");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        recyclerView=findViewById(R.id.cart_recycler_view);
        CartContinueButton=findViewById(R.id.Cart_Continue_Button);
        CartContinueButton.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
     /*   List<CartProductModel> cartProductModelList=new ArrayList<>();
        cartProductModelList.add(new CartProductModel(R.drawable.notebook));
        cartProductModelList.add(new CartProductModel(R.drawable.spiral));
        cartProductModelList.add(new CartProductModel(R.drawable.notebook_spiral));
        cartProductModelList.add(new CartProductModel(R.drawable.notebook));
        cartProductModelList.add(new CartProductModel(R.drawable.notebook_spiral));
        cartProductModelList.add(new CartProductModel(R.drawable.spiral));
        MyCartProductsAdapter myCartProductsAdapter=new MyCartProductsAdapter(cartProductModelList);*/
      //  recyclerView.setLayoutManager(linearLayoutManager);
      //  recyclerView.setAdapter(myCartProductsAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.Cart_Continue_Button:
                startActivity(new Intent(MyPocketCart.this,DeliveryActivity.class));
                break;

        }
    }
}
