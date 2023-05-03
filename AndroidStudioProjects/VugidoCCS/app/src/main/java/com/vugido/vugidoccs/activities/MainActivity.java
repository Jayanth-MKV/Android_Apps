package com.vugido.vugidoccs.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.vugido.vugidoccs.R;
import com.vugido.vugidoccs.Retrofit.RetrofitBuilder;
import com.vugido.vugidoccs.adapters.MainRecyclerViewAdpater;
import com.vugido.vugidoccs.models.InActiveUsers.DATAItem;
import com.vugido.vugidoccs.models.InActiveUsers.Response;
import com.vugido.vugidoccs.models.MainCCSOptionsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   // RecyclerView recyclerView;
    CardView InActiveUsers,ZeroUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InActiveUsers=findViewById(R.id.in_active_users);
        ZeroUsers=findViewById(R.id.zero_users);

        InActiveUsers.setOnClickListener(this);
        ZeroUsers.setOnClickListener(this);
        /*recyclerView=findViewById(R.id.MainRecyclerView);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<MainCCSOptionsModel> mainCCSOptionsModelList=new ArrayList<>();

        mainCCSOptionsModelList.add(new MainCCSOptionsModel("Active Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("In-active Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("Recent Order List Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("Active Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("In-active Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("Recent Order List Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("Active Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("In-active Users",""));
        mainCCSOptionsModelList.add(new MainCCSOptionsModel("Recent Order List Users",""));


        MainRecyclerViewAdpater mainRecyclerViewAdpater=new MainRecyclerViewAdpater(mainCCSOptionsModelList,this);
        recyclerView.setAdapter(mainRecyclerViewAdpater);*/




    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,UsersActivity.class);
        switch (v.getId()){

            case R.id.in_active_users:
                intent.putExtra("ACTION",1);
                startActivity(intent);
                break;
            case R.id.zero_users:
                intent.putExtra("ACTION",2);
                startActivity(intent);
                break;
        }
    }



}