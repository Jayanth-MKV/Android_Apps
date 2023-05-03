package vugido.foodhub.ap.sklm.hungrybirds.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.adapters.cart.MyCartProductsAdapter;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.MyPreferences;
import vugido.foodhub.ap.sklm.hungrybirds.dialogs.MyDialogLoader;
import vugido.foodhub.ap.sklm.hungrybirds.models.cart.CATEGORIESItem;
import vugido.foodhub.ap.sklm.hungrybirds.models.cart.DATA;
import vugido.foodhub.ap.sklm.hungrybirds.models.cart.PRODUCTSItem;
import vugido.foodhub.ap.sklm.hungrybirds.models.cart.Response;
import vugido.foodhub.ap.sklm.hungrybirds.network.Retrofit.RetrofitBuilder;

import static vugido.foodhub.ap.sklm.hungrybirds.activities.MapsActivity.ORDER;

public class CartProductsActivity extends AppCompatActivity implements MyCartProductsAdapter.CartDelete {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView price;
    private MyDialogLoader myDialogLoader;
    MyCartProductsAdapter myCartProductsAdapter;
    List<PRODUCTSItem> dataItemList;
    List<Integer> count;
    List<String> categories;
    private float CURRENT_PRICE=0;
    LinearLayout PriceLayout;
    ImageView CartEmpty;
    private Button cartButton;
    private TextView TextCartEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        PriceLayout=findViewById(R.id.linearLayout);
        CartEmpty=findViewById(R.id.imageView3);
        TextCartEmpty=findViewById(R.id.textView6);
        toolbar = findViewById(R.id.cart_toolbar);
        toolbar.setTitle("My Cart");

        cartButton = findViewById(R.id.Cart_Continue_Button);
        setSupportActionBar(toolbar);

        price = findViewById(R.id.total_cart_price);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.cart_recycler_view);


        cartButton.setOnClickListener(v -> startActivityForResult(new Intent(CartProductsActivity.this, MapsActivity.class),ORDER));

        if (new MyPreferences(getApplicationContext()).getCartProducts() != null)
            fetchCartProducts();
        else{
            PriceLayout.setVisibility(View.INVISIBLE);
        CartEmpty.setVisibility(View.VISIBLE);
        TextCartEmpty.setVisibility(View.VISIBLE);
    }




    }

//    private void eatNow(int mode) {
//
//        MyPreferences myPreferences=new MyPreferences(this);
//        Map<String,Object> map=new HashMap<>();
//        map.put("UID",myPreferences.getUID());
//        map.put("TNO",String.valueOf(myPreferences.getTableNumber()));
//        map.put("PIDS",myPreferences.getCartProducts());
//
//        StringBuilder stringBuilder=new StringBuilder();
//        int ic=0;
//        for (Integer c:count){
//            ic+=c;
//            stringBuilder.append(c);
//            stringBuilder.append(",");
//        }
//
//        map.put("COUNT",stringBuilder.toString());
//        map.put("IC",String.valueOf(ic));
//        map.put("CID",myPreferences.getCID());
//        // mode = 1  eat at table else parcel
//        map.put("MODE",String.valueOf(mode));
//        map.put("TP",String.valueOf(CURRENT_PRICE));
//
//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("").getCartPids(map);
//
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//
//            }
//        });
//        call.enqueue(new Callback<com.vugido.com.vugidoeats.models.status.Response>() {
//            @Override
//            public void onResponse(Call<com.vugido.com.vugidoeats.models.status.Response> call, retrofit2.Response<com.vugido.com.vugidoeats.models.status.Response> response) {
//                //Log.e("status",response.toString());
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(!response.body().isError()){
//                        //Log.e("it's ok","ok");
//                        finish();
//                        startActivity(new Intent(CartProductsActivity.this,RequestAcceptanceActivity.class));
//                        new MyPreferences(getApplicationContext()).setCartProducts(null);
//
//                    }else {
//                       // Log.e("it's ok","no");
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<com.vugido.com.vugidoeats.models.status.Response> call, Throwable t) {
//                Log.e("failed",t.toString());
//
//            }
//        });
//
//    }

    private void fetchCartProducts() {




        MyPreferences myPreferences=new MyPreferences(this);
        Map<String,Object> map=new HashMap<>();

        map.put("PIDS",myPreferences.getCartProducts());
        map.put("UID","1");


        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Getting Your Food");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getCartProducts(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


                Log.e("status",response.toString());
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        setData(response.body().getDATA());

                    }else {
                        //no data
                    }
                }
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Log.e("status",t.toString());
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }
        });

    }

    private void setData(DATA data1) {


        List<PRODUCTSItem> data=data1.getPRODUCTS();
        Log.e("res",data.toString());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Integer> pids=new ArrayList<>();




        for(PRODUCTSItem dataItem:data){
           pids.add(dataItem.getPID());
        }
        LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>(pids);

        List<Integer> Ulist=new ArrayList<>(linkedHashSet);


        dataItemList=new ArrayList<>();
        count=new ArrayList<>();
        categories=new ArrayList<>();
        for(Integer pid:Ulist){
            int c=0;
            for(PRODUCTSItem dataItem:data){

                if(dataItem.getPID()==pid){
                    if(c==0)
                        dataItemList.add(dataItem);
                    c=c+1;
                }
            }
            count.add(c);
        }

        for (PRODUCTSItem productsItem:dataItemList)
        {
            for (CATEGORIESItem categoriesItem:data1.getCATEGORIES()){
                if(productsItem.getSID()==categoriesItem.getSID()){
                    categories.add(categoriesItem.getCNAME());
                    break;
                }
            }

        }

//        Log.e("data",dataItemList.toString());
//        Log.e("count",count.toString());

        myCartProductsAdapter=new MyCartProductsAdapter(dataItemList,count,this,categories);

        recyclerView.setAdapter(myCartProductsAdapter);

        setPrice(dataItemList,count);
    }

    @Override
    public void cartDelete(int pid) {
        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Removing..");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        String pids=new MyPreferences(this).getCartProducts();

        String[] s=pids.split(",");

        String p=null;
        for (String ss:s){

            if(ss.equals(String.valueOf(pid))){
                continue;
            }

            if(p==null){
                p=ss;
            }else {
                p=p+","+ss;
            }

        }
        new MyPreferences(this).setCartProducts(p);

        int l=0;
        for (int x=0;x<dataItemList.size();x++){

            if(dataItemList.get(x).getPID()==pid){
                dataItemList.remove(x);
                l+=count.get(x);
                count.remove(x);

                myCartProductsAdapter.notifyItemRemoved(x);

            }

        }

        new MyPreferences(this).setCartCount(new MyPreferences(this).getCartCount()-l);



        if(myDialogLoader!=null)
            myDialogLoader.dismiss();

        setPrice(dataItemList,count);
    }


    private int Offer(int p,int o){

        return (p-(o*100/p));

    }

    private void setPrice(List<PRODUCTSItem> productsItemList,List<Integer> count){
        float p=0f;
        for (int i=0;i<productsItemList.size();i++){

            if(productsItemList.get(i).getOFFER()>0){

                // offer exists..
                // show offer tag

                // offer exists..

                 p+=Offer(productsItemList.get(i).getPRICE()*count.get(i),productsItemList.get(i).getOFFER());



            }else{

                p+=productsItemList.get(i).getPRICE()*count.get(i);
                //p+=getQuantityPrice(productsItemList.get(i).getPRICE()*count.get(i),productsItemList.get(i),count.get(i));
            }
        }

        price.setText("Rs."+p+"/-");

        CURRENT_PRICE=p;
        new MyPreferences(getApplicationContext()).setCartPrice(CURRENT_PRICE);
//        new  MyPreferences(getApplicationContext()).setCartCount();
        if(p==0f){
            //hide the data


            PriceLayout.setVisibility(View.INVISIBLE);
            CartEmpty.setVisibility(View.VISIBLE);
            TextCartEmpty.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ORDER && resultCode==RESULT_OK)
        {
            setResult(RESULT_OK);
            finish();
        }


    }
}
