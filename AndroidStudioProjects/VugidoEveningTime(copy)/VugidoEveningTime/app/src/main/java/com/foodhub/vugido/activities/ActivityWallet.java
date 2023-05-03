package com.foodhub.vugido.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodhub.vugido.R;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.models.scratch_cards.Data;
import com.foodhub.vugido.models.scratch_cards.Response;
import com.foodhub.vugido.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class ActivityWallet extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView Coins,viewHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 23) {


            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        }else if(Build.VERSION.SDK_INT>23){
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        setContentView(R.layout.pocket_coins_data);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_home_fragment);

        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        Coins=findViewById(R.id.pocket_points_value);
        swipeRefreshLayout.post(this::fetchScratchInfo);
        swipeRefreshLayout.setOnRefreshListener(this::fetchScratchInfo);
        viewHistory=findViewById(R.id.textView27);

        viewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityWallet.this,CoinsTransactionActivity.class);
                startActivity(intent);
            }
        });




    }

    private void fetchScratchInfo() {

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getScratchCardInfo(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful()){


                    assert response.body() != null;
                       if(!response.body().isError()){

                        // data success

                        bindData(response.body().getData());
                    }else{

                        // error no data
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void bindData(Data data) {
        mediaPlayer();
        animateTextView(0,data.getCOINS(),Coins);
       // new MyPreferences(this).setCoinsCount(coinsdataItemList.get(0).getCOINS());





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

    public void mediaPlayer (){

//        Uri uriPlayer = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "coin_drop.mp3/" );
//        final MediaPlayer mp = MediaPlayer.create(context, uriPlayer);
//        mp.start();

        int resID=getResources().getIdentifier("coin_drop", "raw", getPackageName());

        MediaPlayer mediaPlayer=MediaPlayer.create(this,resID);
        mediaPlayer.start();
    }


    @Override
    public void onRefresh() {
        fetchScratchInfo();
    }
}
