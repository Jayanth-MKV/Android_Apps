package com.vugido.vugidoccs.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.vugido.vugidoccs.R;
import com.vugido.vugidoccs.Retrofit.RetrofitBuilder;
import com.vugido.vugidoccs.adapters.InActiveUsersAdapter;
import com.vugido.vugidoccs.adapters.OrderCountUsersAdapter;
import com.vugido.vugidoccs.models.InActiveUsers.DATAItem;
import com.vugido.vugidoccs.models.InActiveUsers.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class UsersActivity extends AppCompatActivity implements InActiveUsersAdapter.MarkUsers {

    LottieAnimationView lottieAnimationView;
    Toolbar toolbar;
    TextView toolbar_title;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerView=findViewById(R.id.in_active_users_recycler);
        toolbar = findViewById(R.id.activity_users_toolbar);
        setSupportActionBar(toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("");
        lottieAnimationView = findViewById(R.id.lottie_progress_center);

        int action=getIntent().getIntExtra("ACTION",0);
        if(action==1){
            myDialogBoxUpdater();
        }else if(action==2){

            // zero users list....
            fetchZeroOrdersUsers(1);
        }

    }

    private void fetchZeroOrdersUsers(int i) {

        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        lottieAnimationView.setSpeed(1f);
        Map<String,Object> map=new HashMap<>();
        map.put("COUNT",i);

        Call<com.vugido.vugidoccs.models.OrderCountUsers.Response> call=RetrofitBuilder.getInstance().getRetrofit().getZerOrderedUsers(map);
        call.enqueue(new Callback<com.vugido.vugidoccs.models.OrderCountUsers.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vugidoccs.models.OrderCountUsers.Response> call, retrofit2.Response<com.vugido.vugidoccs.models.OrderCountUsers.Response> response) {

                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isSTATUS()){

                        bindOrderedCountData(response.body().getDATA());
                    }else{

                        // no data
                    }
                }
            }

            @Override
            public void onFailure(Call<com.vugido.vugidoccs.models.OrderCountUsers.Response> call, Throwable t) {
                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
            }
        });


    }

    private void bindOrderedCountData(List<com.vugido.vugidoccs.models.OrderCountUsers.DATAItem> data) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        OrderCountUsersAdapter orderCountUsersAdapter=new OrderCountUsersAdapter(data,this);
        recyclerView.setAdapter(orderCountUsersAdapter);

    }

    private void myDialogBoxUpdater() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView=null;

        dialogView = inflater.inflate(R.layout.in_active_users_dialog, null);


        // set pre data.....
        final TextInputEditText f=dialogView.findViewById(R.id.from);
        final TextInputEditText t=dialogView.findViewById(R.id.to);

        Button cancel =  dialogView.findViewById(R.id.button_cancel);
        Button update = dialogView.findViewById(R.id.button_update);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fetchInActiveUsersByGap(Objects.requireNonNull(f.getText()).toString(), Objects.requireNonNull(t.getText()).toString());
                dialogBuilder.dismiss();

                toolbar_title.setText("In-Active Users ("+ f.getText().toString()+"-"+t.getText().toString()+") days ");


            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();




    }

    private void fetchInActiveUsersByGap(final String toString, String toString1) {

        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        lottieAnimationView.setSpeed(1f);
        Map<String,Object> map=new HashMap<>();
        map.put("LL",toString1);
        map.put("GAP",toString);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getInActiveUsersByGap(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // data exists
                        List<DATAItem> dataItemList= response.body().getDATA();
                        bindInActiveUsersData(dataItemList,toString);

                    }else {
                        // no data
                    }
                }else {
                    //error
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
            }
        });


    }

    private void bindInActiveUsersData(List<DATAItem> dataItemList,String from) {

        ArrayList<String> uids=new ArrayList<>();
        for(DATAItem dataItem:dataItemList){
            uids.add(String.valueOf(dataItem.getUSERID()));
        }
        LinkedHashSet<String> linkedHashSet=new LinkedHashSet<>(uids);
        List<String> uuids=new ArrayList<>(linkedHashSet);

        for(String uid:uuids){
            int count=0;
            for (int i =0;i<dataItemList.size();i++){

                if(dataItemList.get(i).getUSERID()==Integer.parseInt(uid)){
                    count+=1;
                }
                if(count>1){
                    dataItemList.remove(i);
                }
            }
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        InActiveUsersAdapter inActiveUsersAdapter=new InActiveUsersAdapter(this,dataItemList,from);
        recyclerView.setAdapter(inActiveUsersAdapter);

    }


    @Override
    public void markUser(int uid) {

        fetchMarkOptions();
    }

    private void fetchMarkOptions() {

        
    }
}
