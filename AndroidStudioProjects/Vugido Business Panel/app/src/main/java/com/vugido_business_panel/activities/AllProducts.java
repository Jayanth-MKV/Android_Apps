package com.vugido_business_panel.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.vugido_business_panel.R;
import com.vugido_business_panel.Retrofit.RetrofitBuilder;
import com.vugido_business_panel.adapter.AllProductsRecyclerViewAdapter;
import com.vugido_business_panel.app_congif.MyPreferences;
import com.vugido_business_panel.models.All_Products.DATA;
import com.vugido_business_panel.models.All_Products.ITEMSItem;
import com.vugido_business_panel.models.All_Products.MENUItem;
import com.vugido_business_panel.models.All_Products.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class AllProducts extends AppCompatActivity implements AllProductsRecyclerViewAdapter.Updater {

    private static final int EDIT_REQUEST_CODE = 1;
    private  int SMOOTH_SCROLL_POSITION=-1 ;
    AllProductsRecyclerViewAdapter allProductsRecyclerViewAdapter;
    private int CID;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView toolbar_title;
    private LottieAnimationView progress;
    private DATA dataItems;
    private SweetAlertDialog s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        toolbar=findViewById(R.id.all_products_toolbar);
        toolbar_title=findViewById(R.id.toolbar_title);
        recyclerView=findViewById(R.id.all_products_recycler_view);
        progress=findViewById(R.id.lottieProgress);

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        // get cid
        toolbar_title.setText("All Products");

        //send request to server to fetch the data....

        fetchAllProducts();


    }

    private void fetchAllProducts() {


        progress.setVisibility(View.VISIBLE);
        progress.setSpeed(1.5f);
        progress.playAnimation();
        Map<String, Object> map=new HashMap<>();
        map.put("CID",new MyPreferences(this).getCID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getAllProducts(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull  Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok data there

                        dataItems=response.body().getDATA();

                        bindDataToRecyclerView();

                    }else {

                        //no data

                        Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Error... Try Again",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call,@NonNull Throwable t) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
            }
        });

    }

    private void bindDataToRecyclerView() {


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        allProductsRecyclerViewAdapter=new AllProductsRecyclerViewAdapter(dataItems,this);
        recyclerView.setAdapter(allProductsRecyclerViewAdapter);

        if(SMOOTH_SCROLL_POSITION!= -1)
        recyclerView.smoothScrollToPosition(SMOOTH_SCROLL_POSITION);


    }

    private void showAlertDialog(final int pid) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Warning It will Delete Product ?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initDelete(pid);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        alertDialog.show();

    }

    private void initDelete(final int pid) {

        progress.setVisibility(View.VISIBLE);
        progress.setSpeed(3f);
        progress.playAnimation();

        Map<String, Object> map=new HashMap<>();
        map.put("CID",new MyPreferences(this).getCID());
        map.put("PID",String.valueOf(pid));

        Call<com.vugido_business_panel.models.Updater.delete.Response> call=RetrofitBuilder.getInstance().getRetrofit().delete(map);

        call.enqueue(new Callback<com.vugido_business_panel.models.Updater.delete.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugido_business_panel.models.Updater.delete.Response> call, @NonNull retrofit2.Response<com.vugido_business_panel.models.Updater.delete.Response> response) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok all right
                        showDialogStatus(true,"Deleted Successfully","Error!! Try Again..");

                        //refresh adapter

                        refreshAdapterAfterDeletion(pid);
                        //refreshToggleAdapterList(pid);

                    }else {

                        showDialogStatus(false,"Deleted Successfully","Error!! Try Again..");

                    }

                }else {

                    showDialogStatus(false,"Deleted Successfully","Error!! Try Again..");
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugido_business_panel.models.Updater.delete.Response> call, @NonNull Throwable t) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
                showDialogStatus(false,"Deleted Successfully","Error!! Try Again..");
            }
        });
    }


    @Override
    public void delete(int pid) {


        showAlertDialog(pid);


    }

    @Override
    public void edit(int pid) {
        List<ITEMSItem> itemsItemList=dataItems.getITEMS();
        ITEMSItem itemsItem=null;
        for(int index=0;index<itemsItemList.size();index++){

            if(itemsItemList.get(index).getPID()==pid){
                itemsItem=itemsItemList.get(index);
                SMOOTH_SCROLL_POSITION=index;
                break;
            }
        }

        assert itemsItem != null;
        Bundle bundle=new Bundle();
        bundle.putInt("PID",pid);
        bundle.putString("N",itemsItem.getENAME());
        bundle.putString("I",itemsItem.getIMAGE());
        bundle.putString("Q",itemsItem.getQUANTITY());
        bundle.putString("D",itemsItem.getDESCRIPTION());
        bundle.putInt("O",itemsItem.getOFFER());
        bundle.putInt("CL",itemsItem.getCARTLIMIT());
        bundle.putInt("OL",itemsItem.getOFFERLIMIT());
        bundle.putInt("PRICE",itemsItem.getPRICE());

        List<MENUItem> menuItemList=dataItems.getMENU();
        for (MENUItem menuItem:menuItemList){

            if(menuItem.getCID()==itemsItem.getSID()){
                bundle.putString("CAT_NAME",menuItem.getSUBNAME());
                break;
            }
        }

        ArrayList<String> category_names=new ArrayList<>();
        ArrayList<Integer> category_id=new ArrayList<>();
        for (MENUItem menuItem:menuItemList){
            category_id.add(menuItem.getCID());
            category_names.add(menuItem.getSUBNAME());
        }

        bundle.putStringArrayList("ARRAY_CAT_NAME",category_names);
        bundle.putIntegerArrayList("ARRAY_CAT_ID",category_id);

        Intent intent=new Intent(AllProducts.this,EditProductActivity.class);
        intent.putExtra("BUNDLE",bundle);
        startActivityForResult(intent,EDIT_REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {

                // reload the data....
                recyclerView.removeAllViews();
                allProductsRecyclerViewAdapter=null;
                fetchAllProducts();
            }
        }

    }

    private void refreshAdapterAfterDeletion(int pid) {


        List<ITEMSItem> itemsItemList=dataItems.getITEMS();
        // after successful update in server refresh the list with new data set...
        for(int index=0;index<itemsItemList.size();index++){
            ITEMSItem item=itemsItemList.get(index);
            if(pid==item.getPID()){

                // ok we got the id...

                itemsItemList.remove(index);


                assert allProductsRecyclerViewAdapter!=null;
                allProductsRecyclerViewAdapter.notifyItemRemoved(index);

                Toast.makeText(this,"DataSet Changed",Toast.LENGTH_LONG).show();

                break;
            }




    }

    }
/*
    @Override
    public void priceUpdater(int pid) {


        List<ITEMSItem> itemsItemList=dataItems.getITEMS();
        ITEMSItem itemsItem=null;
        for(int index=0;index<itemsItemList.size();index++){

            if(itemsItemList.get(index).getID()==pid){
                itemsItem=itemsItemList.get(index);
                break;
            }
        }

        assert itemsItem != null;

       myDialogBoxUpdater(itemsItem);



    }

    @Override
    public void toggleInStock(final int pid) {

        progress.setVisibility(View.VISIBLE);
        progress.setSpeed(3f);
        progress.playAnimation();

        Map<String, Object> map=new HashMap<>();
        map.put("CID",String.valueOf(CID));
        map.put("PID",String.valueOf(pid));

       Call<com.vugidovugidoclientpanel.models.Updater.update_in_stock.Response> call=RetrofitBuilder.getInstance().getRetrofit().toggleStock(map);

        call.enqueue(new Callback<com.vugidovugidoclientpanel.models.Updater.update_in_stock.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.vugidovugidoclientpanel.models.Updater.update_in_stock.Response> call, @NonNull retrofit2.Response<com.vugidovugidoclientpanel.models.Updater.update_in_stock.Response> response) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        // ok all right
                        showDialogStatus(true);

                        //refresh adapter

                        refreshToggleAdapterList(pid);

                    }else {

                        showDialogStatus(false);

                    }

                }else {

                    showDialogStatus(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.vugidovugidoclientpanel.models.Updater.update_in_stock.Response> call,@NonNull Throwable t) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
                showDialogStatus(false);
            }
        });


    }

    private void refreshToggleAdapterList(int pid) {

        List<ITEMSItem> itemsItemList=dataItems.getITEMS();
        // after successful update in server refresh the list with new data set...
        for(int index=0;index<itemsItemList.size();index++){
            ITEMSItem item=itemsItemList.get(index);
            if(pid==item.getID()){

                // ok we got the id...

               if(itemsItemList.get(index).getINSTOCK()==1){

                   itemsItemList.get(index).setINSTOCK(0);
               }else {
                   itemsItemList.get(index).setINSTOCK(1);

               }

                assert allProductsRecyclerViewAdapter!=null;

                allProductsRecyclerViewAdapter.notifyItemChanged(index,itemsItemList.get(index));

                Toast.makeText(this,"DataSet Changed",Toast.LENGTH_LONG).show();

                break;
            }
        }


    }

*/
   /* private void myDialogBoxUpdater(final ITEMSItem itemsItem) {

        String Price,Offer;

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView=null;

        switch (itemsItem.getQUANTITYUNIT()){

            case 1:
                dialogView = inflater.inflate(R.layout.kg_g_add_product_layout, null);
                // Quantity="1000";
                Price= String.valueOf(itemsItem.getPRICE());

                if(itemsItem.getOFFERSTATUS()==1){
                    Offer= String.valueOf(itemsItem.getOFFER());
                }else {
                    Offer= "0";
                }



                // set pre data.....
                final TextInputEditText price=dialogView.findViewById(R.id.kgprice);
                final TextInputEditText offer=dialogView.findViewById(R.id.kgproductsoffer);
                Button cancel =  dialogView.findViewById(R.id.button_cancel);
                Button update = dialogView.findViewById(R.id.button_update);
                cancel.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
                price.setText(Price);
                offer.setText(Offer);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String off,OfferStatus,pr,unit_interval="0";
                                off= Objects.requireNonNull(offer.getText()).toString();
                                pr= Objects.requireNonNull(price.getText()).toString();
                        if(off.equals("0")){
                            OfferStatus="0";
                        }else {
                            OfferStatus="1";
                        }

                        dialogBuilder.dismiss();
                        //updatePrice(off,pr,OfferStatus,unit_interval,itemsItem);



                    }
                });
                // fetch kg/g data

                break;
            case -1:
                dialogView = inflater.inflate(R.layout.units_product_layout, null);

                // Quantity="0";
                Price= String.valueOf(itemsItem.getPRICE());
                if(itemsItem.getOFFERSTATUS()==1){
                    Offer= String.valueOf(itemsItem.getOFFER());
                }else {
                    Offer= "0";
                }


                // set pre data.....
                final TextInputEditText unitPrice=dialogView.findViewById(R.id.unitprice);
                final TextInputEditText unitOffer =dialogView.findViewById(R.id.unitsproductoffer);


                final TextInputEditText unitInterval=dialogView.findViewById(R.id.unitinterval);
                Button unitCancel =  dialogView.findViewById(R.id.buttonCancel);
                Button unitUpdate = dialogView.findViewById(R.id.button_update);
                unitCancel.setVisibility(View.VISIBLE);
                unitUpdate.setVisibility(View.VISIBLE);


                unitOffer.setText(Offer);
                unitPrice.setText(Price);
                unitInterval.setText(String.valueOf(itemsItem.getUNITINTERVAL()));

                unitCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });

                unitUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String off,OfferStatus,pr, U_Interval;

                        U_Interval= Objects.requireNonNull(unitInterval.getText()).toString();
                        off= Objects.requireNonNull(unitOffer.getText()).toString();
                        pr= Objects.requireNonNull(unitPrice.getText()).toString();
                        if(off.equals("0")){
                            OfferStatus="0";
                        }else {
                            OfferStatus="1";
                        }

                        dialogBuilder.dismiss();
                      //  updatePrice(off,pr,OfferStatus,U_Interval,itemsItem);



                    }
                });

                //fetch units data..
                break;



        }

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
*/
    /*private void updatePrice(final String off, final String pr, final String offerStatus, final String unit_interval, final ITEMSItem itemsItem) {


        progress.setVisibility(View.VISIBLE);
        progress.setSpeed(1.5f);
        progress.playAnimation();
        String PID= String.valueOf(itemsItem.getID());
        // and cid.... also...


        Map<String, Object> map=new HashMap<>();
        map.put("CID",String.valueOf(CID));
        map.put("PID",PID);
        map.put("OFFER",off);
        map.put("OFFER_STATUS",offerStatus);
        map.put("PRICE",pr);
        map.put("UNIT_INTERVAL",unit_interval);


        Call<com.vugidovugidoclientpanel.models.Updater.edit_price.Response> call=RetrofitBuilder.getInstance().getRetrofit().updatePrice(map);

        call.enqueue(new Callback<com.vugidovugidoclientpanel.models.Updater.edit_price.Response>() {
            @Override
            public void onResponse(@NonNull  Call<com.vugidovugidoclientpanel.models.Updater.edit_price.Response> call, @NonNull retrofit2.Response<com.vugidovugidoclientpanel.models.Updater.edit_price.Response> response) {

                if(response.isSuccessful()){

                    progress.setVisibility(View.GONE);
                    progress.cancelAnimation();
                    //
                    assert response.body() != null;
                    if(!response.body().isError()){

                        // successfully

                        showDialogStatus(true);


                        refreshAdapter(off, pr, offerStatus, unit_interval,itemsItem);

                        // also refresh the list adapter

                    }else {

                        //error

                        showDialogStatus(false);
                    }
                }else {

                    showDialogStatus(false);
                    // error
                }

            }

            @Override
            public void onFailure(@NonNull Call<com.vugidovugidoclientpanel.models.Updater.edit_price.Response> call, @NonNull Throwable t) {

                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
                showDialogStatus(false);

            }
        });



        Toast.makeText(this,off+pr+offerStatus+unit_interval,Toast.LENGTH_LONG).show();

    }*/

    /*private void refreshAdapter(String off, String pr, String offerStatus, String unit_interval, ITEMSItem itemsItem) {

        List<ITEMSItem> itemsItemList=dataItems.getITEMS();
        // after successful update in server refresh the list with new data set...
        for(int index=0;index<itemsItemList.size();index++){
            ITEMSItem item=itemsItemList.get(index);
            if(itemsItem.getID()==item.getID()){

                // ok we got the id...

                itemsItemList.get(index).setOFFER(Integer.parseInt(off));
                itemsItemList.get(index).setOFFERSTATUS(Integer.parseInt(offerStatus));
                itemsItemList.get(index).setUNITINTERVAL(Integer.parseInt(unit_interval));
                itemsItemList.get(index).setPRICE(Integer.parseInt(pr));

                assert allProductsRecyclerViewAdapter!=null;

                allProductsRecyclerViewAdapter.notifyItemChanged(index,itemsItemList.get(index));

                Toast.makeText(this,"DataSet Changed",Toast.LENGTH_LONG).show();

                break;
            }
        }

    }*/

    private void showDialogStatus(boolean status,String msg_ok,String msg_not_ok){
        if(status){
            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText(msg_ok)
            ;
            s.show();
        }else{
            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(msg_not_ok)
            ;
            s.show();

        }

    }


}
