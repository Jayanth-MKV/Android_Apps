package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.OfferAdapters.EarnCoins.SquareMiniRecyclerViewAdapter;
import com.foodhub.vugido.adapters.OfferAdapters.EarnCoins.SquareMiniRecyclerViewAdapterEntire;
import com.foodhub.vugido.adapters.OfferAdapters.UseCoins.SquareMediumRecyclerViewAdapter;
import com.foodhub.vugido.adapters.OfferAdapters.UseCoins.SquareMediumRecyclerViewAdapterEntire;
import com.foodhub.vugido.app_config_variables.ConfigVariables;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.models.AffiliatedProductModel.Entire.DATAItem;
import com.foodhub.vugido.models.AffiliatedProductModel.Entire.Response;
import com.foodhub.vugido.models.CoinsModel.COINSEARNABLEPRODUCTSItem;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductsShowForPocket extends AppCompatActivity implements SquareMiniRecyclerViewAdapterEntire.DynaMIcLink {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private  View Progress;
    private MyDialogLoader myDialogLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_pocket);
        toolbar=findViewById(R.id.checkOutToolbar);
        recyclerView=findViewById(R.id.recyler);
        toolbar.setTitle("All Products");
        Progress=findViewById(R.id.my_progress);
        Progress.setVisibility(View.GONE);
        int value=getIntent().getIntExtra("VALUE",0);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());
        if(value==1){
            // load affiliated products..

            fetchAffiliatedProducts();
//
        }else {
            // load all purchasable products..
            fetchProductPurchasable();
//
        }

    }

    private void fetchProductPurchasable() {

        Map<String,Object> map=new HashMap<>();

        map.put("UID",new MyPreferences(this).getUID());
        Progress.setVisibility(View.VISIBLE);

        Call<com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.Response> call=RetrofitBuilder.getInstance().getRetrofit("").fetchEntireCoinedProducts(map);
        call.enqueue(new Callback<com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.Response>() {
            @Override
            public void onResponse(Call<com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.Response> call, retrofit2.Response<com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.Response> response) {
                Progress.setVisibility(View.GONE);
                Log.e("res",response.toString());

                if(response.isSuccessful()){
                    assert response.body() != null;
                    if(response.body().isSTATUS())
                    {
                        setCoinedAdapter(response.body().getDATA());
                    }else{
                        //no data
                    }
                }

            }

            @Override
            public void onFailure(Call<com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.Response> call, Throwable t) {
                Progress.setVisibility(View.GONE);

            }
        });
    }

    private void setCoinedAdapter(List<com.foodhub.vugido.models.OffersPageModel.AllCoinedProducts.DATAItem> data) {


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
           SquareMediumRecyclerViewAdapterEntire squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapterEntire(this,data);
            recyclerView.setAdapter(squareMediumRecyclerViewAdapter);
    }

    private void fetchAffiliatedProducts() {

        Progress.setVisibility(View.VISIBLE);

        Map<String,Object> map=new HashMap<>();

        map.put("UID",new MyPreferences(this).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("").fetchEntireAffiliatedProducts(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Progress.setVisibility(View.GONE);

                if(response.isSuccessful()){
                    assert response.body() != null;
                    if(response.body().isSTATUS())
                    {
                       setAffiliatedAdapter(response.body().getDATA());
                    }else{
                        //no data
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Progress.setVisibility(View.GONE);

            }
        });

    }

    private void setAffiliatedAdapter(List<DATAItem> data) {

            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            SquareMiniRecyclerViewAdapterEntire squareMiniRecyclerViewAdapter=new SquareMiniRecyclerViewAdapterEntire(this,data,this);
            recyclerView.setAdapter(squareMiniRecyclerViewAdapter);
    }

    @Override
    public void affiliateProduct(int pid, int coins, String tname, String description, int price, int quantity, String image) {

        createDynamicLink(pid,coins,tname,description,price,quantity,image);


    }
    private void createDynamicLink(int pid, int coins, String tname, String description, int price, int quantity, String image){

        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("http://www.vugido.com/"))
                .setDomainUriPrefix("https://vugido.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                .buildDynamicLink();

        Uri dynamicLinkUri = dynamicLink.getUri();

        Log.e("Long DLink",dynamicLinkUri.toString());

        shortenLongDynamicLink(dynamicLink.getUri(),pid,coins,tname,description,price,quantity,image);
    }

    private void shortenLongDynamicLink(Uri longLink, int pid, int coins, String tname, String description, int price, int quantity, String image){

        String manualLink="https://vugido.page.link/?"+
                "link="+createProductAffiliateLink(new MyPreferences(this).getUID(),String.valueOf(pid),String.valueOf(coins),"2")+
                "&apn="+getPackageName()+
                "&st="+"Buy Online In Vugido +"+"%0A"+tname+
                "&sd="+"Rs."+price+"/-"+
                "&si="+image;

        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Generating Share Link");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");

        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                //.setLongLink(longLink) // automatic long link..
                //manually
                .setLongLink(Uri.parse(manualLink))
                .buildShortDynamicLink()

                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Short link created
                        Uri shortLink = task.getResult().getShortLink();
                        Uri flowchartLink = task.getResult().getPreviewLink();

                        if(myDialogLoader!=null)
                            myDialogLoader.dismiss();



                        //initiate intent here...
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        assert shortLink != null;
                        intent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
                        startActivity(intent);
                    } else {
                        // Error
                        // ...
                        Log.e("Error","Short link not got");
                        if(myDialogLoader!=null)
                            myDialogLoader.dismiss();
                    }
                });
    }
    private String createProductAffiliateLink(String UID,String PID,String COINS,String TYPE){

        return "http://www.vugido.com/AFFITIATE_PRODUCT.php?AUID="+TYPE+"--"+UID+"--"+PID+"--"+COINS+"--"+ ConfigVariables.getCurrentDate()+"_"+ConfigVariables.getCurrentTime()+"_"+Math.abs(ConfigVariables.getMilliSeconds());

    }

}

