package com.vugido.foods.vugidodeliveryagent.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.vugido.foods.vugidodeliveryagent.R;
import com.vugido.foods.vugidodeliveryagent.adapters.MyPendingOrdersAdapter;
import com.vugido.foods.vugidodeliveryagent.app_config_variables.MyPreferences;
import com.vugido.foods.vugidodeliveryagent.models.orders.pending_orders.DATAItem;
import com.vugido.foods.vugidodeliveryagent.models.orders.pending_orders.Response;
import com.vugido.foods.vugidodeliveryagent.network.Retrofit.RetrofitBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class PendingOrders extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MyPendingOrdersAdapter.UpdateStatus {

    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MyPendingOrdersAdapter myPendingOrdersAdapter;
    private List<DATAItem> dataItemList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pending_orders,container,false);

        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh_layout);
        recyclerView=view.findViewById(R.id.activeItemsRecyclerView);

        // swipe listener..
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        // called first time.. with out pulling..
        // make sure network connection before calling..
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {


                // get the data here
                fetchActiveOrders();

            }
        });






        return view;
    }

    private void fetchActiveOrders() {

        Map<String,Object> map=new HashMap<>();
        map.put("MID",new MyPreferences(context).getDID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getPendingOrders(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call,@NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok data got..
                        bindPendingOrdersData(response.body().getDATA());
                    }else {
                        Toast.makeText(context, "No Pending Orders", Toast.LENGTH_SHORT).show();
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<Response> call,@NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void bindPendingOrdersData(List<DATAItem> data) {

        Collections.reverse(data);
        dataItemList=data;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // adapter..
        myPendingOrdersAdapter=new MyPendingOrdersAdapter(context,data,this);
        recyclerView.setAdapter(myPendingOrdersAdapter);


    }

    @Override
    public void onRefresh() {

        fetchActiveOrders();
    }

    private void myDialogBoxUpdater(final int oid, int initial) {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView=null;

        dialogView = inflater.inflate(R.layout.update_status_dialog, null);

        final int[] S = new int[1];
        // set pre data.....
        RadioGroup radioGroup=dialogView.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int state=0;
                switch (checkedId){
                    case R.id.state1:
                        state =0;
                        break;
                    case R.id.state2:
                        state=1;
                        break;
                    case R.id.state3:
                        state=2;
                        break;
                    case R.id.state4:
                        state=3;
                        break;
                    case R.id.state5:
                        state=4;
                        break;
                }

                S[0] =state;

            }
        });
        Button cancel =  dialogView.findViewById(R.id.button);
        Button update = dialogView.findViewById(R.id.button2);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateStatusRequest(oid,S);
                dialogBuilder.dismiss();



            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();




    }


    private void updateStatusRequest(final int oid, final int[] s) {

        Map<String,Object> map=new HashMap<>();
        map.put("OID",String.valueOf(oid));
        map.put("STATUS",String.valueOf(s[0]));

        Call<com.vugido.foods.vugidodeliveryagent.models.status.Response> call=RetrofitBuilder.getInstance().getRetrofit().updateStatus(map);

        call.enqueue(new Callback<com.vugido.foods.vugidodeliveryagent.models.status.Response>() {
            @Override
            public void onResponse(Call<com.vugido.foods.vugidodeliveryagent.models.status.Response> call, retrofit2.Response<com.vugido.foods.vugidodeliveryagent.models.status.Response> response) {
                if (response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok updated
                        refreshAdapter(oid,s[0]);
                    }else{

                        //not updated...
                    }
                }
            }

            @Override
            public void onFailure(Call<com.vugido.foods.vugidodeliveryagent.models.status.Response> call, Throwable t) {

            }
        });

    }


    private void refreshAdapter(int oid, int s) {

        for(int i=0;i<dataItemList.size();i++){
            if (dataItemList.get(i).getOID()==oid){

                if(s==4){
                    dataItemList.remove(i);
                    myPendingOrdersAdapter.notifyDataSetChanged();
                }else {
                    dataItemList.get(i).setSTATUS(s);
                    myPendingOrdersAdapter.notifyItemChanged(i);

                }
                break;
            }

        }



    }


    @Override
    public void updateStatus(int oid) {
        myDialogBoxUpdater(oid,0);
    }
}
