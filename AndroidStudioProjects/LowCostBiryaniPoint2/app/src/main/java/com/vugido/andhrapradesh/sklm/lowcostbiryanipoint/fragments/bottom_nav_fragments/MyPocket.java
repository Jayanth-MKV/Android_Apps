package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.fragments.bottom_nav_fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.CoinsTransactionActivity;
import com.foodhub.vugido.activities.ProductsShowForPocket;
import com.foodhub.vugido.adapters.OfferAdapters.EarnCoins.SquareMiniRecyclerViewAdapter;
import com.foodhub.vugido.adapters.OfferAdapters.MyOfferSlider;
import com.foodhub.vugido.adapters.OfferAdapters.SingleProductOffersAdapter;
import com.foodhub.vugido.adapters.OfferAdapters.UseCoins.SquareMediumRecyclerViewAdapter;
import com.foodhub.vugido.app_config_variables.ConfigVariables;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.CoinsModel.COINSDATAItem;
import com.foodhub.vugido.models.CoinsModel.COINSEARNABLEPRODUCTSItem;
import com.foodhub.vugido.models.CoinsModel.COINSSPENDINGItem;
import com.foodhub.vugido.models.CoinsModel.DATA;
import com.foodhub.vugido.models.CoinsModel.Response;
import com.foodhub.vugido.models.OffersPageModel.SINGLEPRODUCTSItem;
import com.foodhub.vugido.models.OffersPageModel.SLIDERItem;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class MyPocket extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, SquareMediumRecyclerViewAdapter.CoinProductsToCart, SquareMiniRecyclerViewAdapter.DynaMIcLink {

    private RecyclerView EarnrecyclerView,UseRecyclerView;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TabLayout tabIndicator;
    private ViewPager viewPager;
    private TextView Coins,ViewHistory;
    private Button NotActivated;
    TextView Viewmore1,ViewMore2;
    private FragmentActivity activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_wallet, container, false);
        Coins=view.findViewById(R.id.pocket_points_value);
        EarnrecyclerView=view.findViewById(R.id.pocket_recycler_view);
        UseRecyclerView=view.findViewById(R.id.use_recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_offers_fragment);
        ViewHistory=view.findViewById(R.id.textView27);
        NotActivated=view.findViewById(R.id.button8);
        Viewmore1=view.findViewById(R.id.textView32);
        Viewmore1.setOnClickListener(v -> {
            Intent intent=new Intent(context, ProductsShowForPocket.class);
            intent.putExtra("VALUE",1);
            activity.startActivityForResult(intent,0);

        });
        ViewMore2=view.findViewById(R.id.textView41);
        ViewMore2.setOnClickListener(v -> {
            Intent intent=new Intent(context, ProductsShowForPocket.class);
            intent.putExtra("VALUE",2);
            activity.startActivityForResult(intent,0);

        });
        // view pager
      /*  tabIndicator = view.findViewById(R.id.tab_indicator);
        viewPager =view.findViewById(R.id.home_viewpager);*/

        // swipe listener..
        NotActivated.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(this);
        ViewHistory.setOnClickListener(this);
        NotActivated.setOnClickListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.gradient_start_color,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.black);
        // called first time.. with out pulling..
        // make sure network connection before calling..
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.post(this::fetchOffersPageData);

        NotActivated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"When You Order Above Rs.100/- Coins will be activated",Toast.LENGTH_LONG).show();
            }
        });











        return view;
    }

    private void fetchOffersPageData() {


        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(context).getUID());

        Call<Response> call=RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(context).getBaseLocationURL()).fetchCoinsPage(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                swipeRefreshLayout.setRefreshing(false);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if (response.body().isSTATUS()){

                        Log.e("CoinsData","ok");
                        com.foodhub.vugido.models.CoinsModel.DATA data=response.body().getDATA();
                        bindOffersDAta(data);

                    }else {
                        //no data fetched....

                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void bindOffersDAta(DATA data) {

        List<COINSDATAItem> coinsdataItemList=data.getCOINSDATA();
        List<COINSEARNABLEPRODUCTSItem> coinsearnableproductsItemList = data.getCOINSEARNABLEPRODUCTS();
        List<COINSSPENDINGItem> coinsspendingItemList=data.getCOINSSPENDING();


        if(coinsdataItemList!=null){
            //set the coins data....
            if(coinsdataItemList.get(0).getCOINS()!=0)
                mediaPlayer();
            animateTextView(0,coinsdataItemList.get(0).getCOINS(),Coins);
            new MyPreferences(context).setCoinsCount(coinsdataItemList.get(0).getCOINS());
            if(coinsdataItemList.get(0).getACTIVATED()==0){
                NotActivated.setVisibility(View.VISIBLE);
                new MyPreferences(context).setCoinsActivated(false);
            }else {
                NotActivated.setVisibility(View.GONE);
                new MyPreferences(context).setCoinsActivated(true);

            }

        }else{
            Coins.setText("0");
            NotActivated.setVisibility(View.GONE);
        }


        if(coinsearnableproductsItemList!=null){
            //set affiliate products...
            EarnrecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            SquareMiniRecyclerViewAdapter squareMiniRecyclerViewAdapter=new SquareMiniRecyclerViewAdapter(context,coinsearnableproductsItemList,this);
            EarnrecyclerView.setAdapter(squareMiniRecyclerViewAdapter);
            EarnrecyclerView.setNestedScrollingEnabled(false);

        }

        if(coinsspendingItemList!=null){
            UseRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
            SquareMediumRecyclerViewAdapter squareMediumRecyclerViewAdapter=new SquareMediumRecyclerViewAdapter(context,coinsspendingItemList,this);
            UseRecyclerView.setAdapter(squareMediumRecyclerViewAdapter);
            UseRecyclerView.setNestedScrollingEnabled(false);

        }



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
                "link="+createProductAffiliateLink(new MyPreferences(context).getUID(),String.valueOf(pid),String.valueOf(coins),"2")+
                "&apn="+context.getPackageName()+
                "&st="+"Buy Online In Vugido +"+"%0A"+tname+
                "&sd="+"Rs."+price+"/-"+
                "&si="+image;

        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                //.setLongLink(longLink) // automatic long link..
                //manually
                .setLongLink(Uri.parse(manualLink))
                .buildShortDynamicLink()

                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Short link created
                        Uri shortLink = task.getResult().getShortLink();
                        Uri flowchartLink = task.getResult().getPreviewLink();

//                            Log.e("Short Link",shortLink.toString());
//                            Log.e("flowchartLink",flowchartLink.toString());


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
                    }
                });
    }
    private String createProductAffiliateLink(String UID,String PID,String COINS,String TYPE){

        return "http://www.vugido.com/AFFITIATE_PRODUCT.php?AUID="+TYPE+"--"+UID+"--"+PID+"--"+COINS+"--"+ ConfigVariables.getCurrentDate()+"_"+ConfigVariables.getCurrentTime()+"_"+Math.abs(ConfigVariables.getMilliSeconds());

    }








    public void animateTextView(int initialValue, int finalValue, final TextView textview) {
            DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
            int start = Math.min(initialValue, finalValue);
            int end = Math.max(initialValue, finalValue);
            int difference = Math.abs(finalValue - initialValue);
            Handler handler = new Handler();
            for (int count = start; count <= end; count++) {
                int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 100) * count;
                final int finalCount = ((initialValue > finalValue) ? initialValue - count : count);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textview.setText(String.valueOf(finalCount));
                    }
                }, time);
            }
        }


    private void animateText(int initialValue, int finalValue, final TextView textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(1500);



        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                textview.setText(valueAnimator.getAnimatedValue().toString());


            }




        });
        valueAnimator.start();

    }

    @Override
    public void onRefresh() {

        fetchOffersPageData();
    }

    public void mediaPlayer (){

//        Uri uriPlayer = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "coin_drop.mp3/" );
//        final MediaPlayer mp = MediaPlayer.create(context, uriPlayer);
//        mp.start();

        int resID=getResources().getIdentifier("coin_drop", "raw", context.getPackageName());

        MediaPlayer mediaPlayer=MediaPlayer.create(context,resID);
        mediaPlayer.start();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.textView27:
                startActivity(new Intent(context, CoinsTransactionActivity.class));
                break;

        }
    }

    @Override
    public void addCoinProductsCart(int cid, int pid, int q) {

    }


    @Override
    public void affiliateProduct(int pid, int coins, String tname, String description, int price, int quantity, String image) {

        createDynamicLink(pid,coins,tname,description,price,quantity,image);
    }

}
