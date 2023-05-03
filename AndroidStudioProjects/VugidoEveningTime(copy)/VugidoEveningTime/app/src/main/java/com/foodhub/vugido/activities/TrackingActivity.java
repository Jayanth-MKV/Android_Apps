package com.foodhub.vugido.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.baoyachi.stepview.VerticalStepView;
import com.foodhub.vugido.R;
import com.foodhub.vugido.app.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.models.order_info.DATA;
import com.foodhub.vugido.models.order_info.Response;
import com.foodhub.vugido.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class TrackingActivity extends AppCompatActivity {

    VerticalStepView verticalStepView;

    Toolbar toolbar;
    int Status, OID;
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
        setContentView(R.layout.activity_tracking);

        verticalStepView=findViewById(R.id.verticalStepViewer);
        toolbar=findViewById(R.id.trackingToolbar);
        toolbar.setTitle("Order Status");
        setSupportActionBar(toolbar);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        OID=getIntent().getIntExtra("OID",0);
        Status=getIntent().getIntExtra("STATUS",0);


        fetchOrderTrackingInfo();

    }

    private void fetchOrderTrackingInfo() {


        loginLoader(true,"Please wait !! ");
        Map<String, Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("OID", String.valueOf(OID));


        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getOrderTrackingInfo(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                loginLoader(false,"Please wait !! ");

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok data received
                        //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                        setDataSsets(response.body().getDATA());

                    }else{
                        //Toast.makeText(getApplicationContext(),"NO tracking info",Toast.LENGTH_LONG).show();
                        // no order
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                loginLoader(false,"Please wait !! ");
                Toast.makeText(getApplicationContext(),"Please try again!!",Toast.LENGTH_LONG).show();

            }
        });
    }

    MyDialogLoader myDialogLoader;
    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }
    private void setDataSsets(DATA data) {

        List<String> timeLineModelList=new ArrayList<>();


        timeLineModelList.add(data.getPACKING());
        timeLineModelList.add(data.getPACKED());
        timeLineModelList.add(data.getDISPATCHED());
        timeLineModelList.add(data.getPROCESSING());
        timeLineModelList.add(data.getCOMPLETED());


        verticalStepView.setStepViewTexts(timeLineModelList)

                .setStepsViewIndicatorComplectingPosition(Status)
                .reverseDraw(false)
                .setLinePaddingProportion(2f)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#ffffff"))
                .setStepViewComplectedTextColor(Color.parseColor("#ffffff"))
                .setStepViewUnComplectedTextColor(R.color.quantum_grey)
                .setStepsViewIndicatorUnCompletedLineColor(R.color.quantum_grey)
                .setStepsViewIndicatorCompleteIcon(getDrawable(R.drawable.complted))
                .setStepsViewIndicatorDefaultIcon(getDrawable(R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(getDrawable(R.drawable.attention));



    }
}
