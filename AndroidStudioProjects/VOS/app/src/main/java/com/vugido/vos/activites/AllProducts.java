package com.vugido.vos.activites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.vugido.vos.R;
import com.vugido.vos.adapters.AllProductsRecyclerViewAdapter;
import com.vugido.vos.design.Space;
import com.vugido.vos.models.AllProducts.DATA;
import com.vugido.vos.models.AllProducts.ITEMSItem;
import com.vugido.vos.models.AllProducts.Response;
import com.vugido.vos.network.RetrofitBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class AllProducts extends AppCompatActivity implements AllProductsRecyclerViewAdapter.Updater {

    private DATA data;
    Toolbar toolbar;
    TextView toolbar_title;
    RecyclerView recyclerView;
    private int CID;
    private SweetAlertDialog s;
    private AllProductsRecyclerViewAdapter allProductsRecyclerViewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        toolbar = findViewById(R.id.all_products_toolbar);
        setSupportActionBar(toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);

        toolbar_title.setText(getIntent().getStringExtra("NAME"));

        recyclerView=findViewById(R.id.all_products_recycler);

        CID=getIntent().getIntExtra("CID",0);
        fetchAllProducts(CID,false,0);


    }

    private void fetchAllProducts(int cid, final boolean refresh, final int p) {

        Map<String, Object> map=new HashMap<>();
        map.put("CID",String.valueOf(cid));// cid

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getAllProducts(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){


                        data=response.body().getDATA();

                        if(!refresh)
                            bindDataToAdapter(data);
                        else {
                            resetData(data,p);

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    private void resetData(DATA data, int p) {
        recyclerView.removeAllViews();
        allProductsRecyclerViewAdapter=null;
        allProductsRecyclerViewAdapter=new AllProductsRecyclerViewAdapter(data,this);
        recyclerView.setAdapter(allProductsRecyclerViewAdapter);
        recyclerView.smoothScrollToPosition(p);
    }

    private void bindDataToAdapter(DATA data) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Space(1,10,true,0));
        allProductsRecyclerViewAdapter=new AllProductsRecyclerViewAdapter(data,this);
        recyclerView.setAdapter(allProductsRecyclerViewAdapter);

    }

    @Override
    public void priceEdit(int pid, String price, int ppp_id,String quantity) {


        myDialogBoxUpdater(pid,ppp_id,price,quantity);

    }



        private void myDialogBoxUpdater(final int pid, final int ppp_id, String price, String quantity) {

        String Price;

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView=null;

                dialogView = inflater.inflate(R.layout.pp_dialog_view, null);
                Price= String.valueOf(price);


                // set pre data.....
                final TextInputEditText q=dialogView.findViewById(R.id.quantity);
                final TextInputEditText p=dialogView.findViewById(R.id.kgprice);
                Button cancel =  dialogView.findViewById(R.id.button_cancel);
                Button update = dialogView.findViewById(R.id.button_update);
                p.setText(Price);
                q.setText(quantity);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        updatePrice(pid,ppp_id, Objects.requireNonNull(p.getText()).toString());
                        dialogBuilder.dismiss();



                    }
                });
            dialogBuilder.setView(dialogView);
            dialogBuilder.show();




        }

    private void updatePrice(int pid, final int ppp_id, final String toString) {

        Map<String, Object> map=new HashMap<>();
        map.put("CID",String.valueOf(CID));// cid
        map.put("PID",String.valueOf(pid));
        map.put("PPP_ID",String.valueOf(ppp_id));
        map.put("PP",toString);

        Call<com.vugido.vos.models.Status.Response> call=RetrofitBuilder.getInstance().getRetrofit().updatePPP(map);
        call.enqueue(new Callback<com.vugido.vos.models.Status.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido.vos.models.Status.Response> call, @NonNull retrofit2.Response<com.vugido.vos.models.Status.Response> response) {

                if(response.isSuccessful()){
                    //ok
                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok successfully updated
                        refreshAdapter(ppp_id,toString);
                        showDialogStatus(true,"Updated Successfully","Not Updated !Try Again !!");
                    }else {

                        ////not updated...
                        showDialogStatus(false,"Updated Successfully","Not Updated !Try Again !!");

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido.vos.models.Status.Response> call,@NonNull Throwable t) {

            }
        });


    }

    private void refreshAdapter(int ppp_id, String toString) {

        List<ITEMSItem> itemsItemList=data.getITEMS();
        int position=0;
        for (int index=0;index<itemsItemList.size();index++){
            if(data.getITEMS().get(index).getPPPID()==ppp_id){
               // data.getITEMS().get(index).setPP(toString);
                position=index;
                break;
            }
        }
        fetchAllProducts(CID,true,position);

    }

    private void showDialogStatus(boolean status,String msg_ok,String msg_not_ok){
        if(status){
            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText(msg_ok)
            ;
        }else{
            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(msg_not_ok)
            ;

        }
        s.show();

    }

}
