package com.vugido.vugidoinventorymanagement.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vugido.vugidoinventorymanagement.R;
import com.vugido.vugidoinventorymanagement.adapters.AdminAccessOptionsRecyclerAdapter;
import com.vugido.vugidoinventorymanagement.dialogs.MyStatusDialog;
import com.vugido.vugidoinventorymanagement.models.AdminAccessOptionsModel;
import com.vugido.vugidoinventorymanagement.models.new_categories.Response;
import com.vugido.vugidoinventorymanagement.network.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AdminAccessOptions extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    CardView affiliate, coined, groceries,edit;
    TextView close;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_access_options_item_design);
        toolbar=findViewById(R.id.admin_options_toolbar);
        toolbar.setTitle("Choose Action");
        setSupportActionBar(toolbar);

        close=findViewById(R.id.close);
        affiliate=findViewById(R.id.affiliate);
        coined=findViewById(R.id.coined);
        groceries=findViewById(R.id.actionOptionCardView);
        edit=findViewById(R.id.editactionOptionCardView);
        final Intent intent=new Intent(AdminAccessOptions.this,MainActivity.class);
        affiliate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("OPTION",1);
                startActivity(intent);
            }
        });
        coined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("OPTION",2);
                startActivity(intent);
            }
        });
        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("OPTION",3);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminAccessOptions.this,AllProductsActivity.class);
                i.putExtra("CID",32);
                i.putExtra("TITLE","Edit Foods");
                startActivity(i);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAppService();
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       // recyclerView=findViewById(R.id.admin_action_recycler_view);
        getAppService();


        //loadAllAdminActions();


    }

    private void toggleAppService() {


        Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call= RetrofitBuilder.getInstance().getRetrofit().toggleAppService();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<com.vugido.vugidoinventorymanagement.models.new_categories.Response> response) {

               // Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        showDialog(true,"Updated Successfully");
                    }else {
                        showDialog(false,"Not Updated ! Try Again !!");
                    }
                }

            }

            @Override
            public void onFailure(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, Throwable t) {

               // Progress.setVisibility(View.GONE);
                showDialog(false,"Not Updated ! Try Again !!");


            }
        });




    }


    private void getAppService() {


        Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call= RetrofitBuilder.getInstance().getRetrofit().getAppService();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<com.vugido.vugidoinventorymanagement.models.new_categories.Response> response) {

                // Progress.setVisibility(View.GONE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok service on
                       // showDialog(true,"Updated Successfully");
                        close.setText("Service On");
                        close.setBackgroundColor(Color.GREEN);
                    }else {

                        //service off...
                        close.setText("Service Off");
                        close.setBackgroundColor(Color.RED);
                       // showDialog(false,"Not Updated ! Try Again !!");
                    }
                }

            }

            @Override
            public void onFailure(Call<com.vugido.vugidoinventorymanagement.models.new_categories.Response> call, Throwable t) {

                // Progress.setVisibility(View.GONE);
               // showDialog(false,"Not Updated ! Try Again !!");


            }
        });




    }

    private void showDialog(boolean status,String msg){

        final MyStatusDialog s;
        s=new MyStatusDialog();
        Bundle bundle=new Bundle();

        getAppService();
        if(status){
            bundle.putString("MSG",msg);
            bundle.putBoolean("STATUS",true);


           // refreshData();


        }else {
            bundle.putString("MSG",msg);
            bundle.putBoolean("STATUS",false);

        }

        s.setArguments(bundle);
        s.show(getSupportFragmentManager(),"STATUS");


    }

    private void loadAllAdminActions() {

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<AdminAccessOptionsModel> adminAccessOptionsModelList=new ArrayList<>();
        adminAccessOptionsModelList.add(new AdminAccessOptionsModel("New Product",1,"#ffffff","#000000"));
        adminAccessOptionsModelList.add(new AdminAccessOptionsModel("New Images",2,"#3700B3","#ffffff"));
        adminAccessOptionsModelList.add(new AdminAccessOptionsModel("New Category",3,"#6200EE","#ffffff"));
        adminAccessOptionsModelList.add(new AdminAccessOptionsModel("New Client",4,"#ffffff","#000000"));
        adminAccessOptionsModelList.add(new AdminAccessOptionsModel("New Sub-Cartegory",5,"#ffc000","#ffffff"));
        AdminAccessOptionsRecyclerAdapter adminAccessOptionsRecyclerAdapter=new AdminAccessOptionsRecyclerAdapter(this,adminAccessOptionsModelList);
        recyclerView.setAdapter(adminAccessOptionsRecyclerAdapter);

    }
}
