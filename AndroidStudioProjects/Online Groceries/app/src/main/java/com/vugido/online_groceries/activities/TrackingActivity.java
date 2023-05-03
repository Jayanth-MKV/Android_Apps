package com.vugido.online_groceries.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.baoyachi.stepview.VerticalStepView;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.models.orders.tracking.DATA;
import com.vugido.online_groceries.models.orders.tracking.Response;
import com.vugido.online_groceries.network.RetrofitBuilder;

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
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("OID", String.valueOf(24));


        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getOrderTrackingInfo(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                loginLoader(false,"Please wait !! ");

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(response.body().isSTATUS()){
                        //ok data received

                        setDataSsets(response.body().getDATA());

                    }else{

                        // no order
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                loginLoader(false,"Please wait !! ");

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
        timeLineModelList.add(data.getDELIVERED());
//        timeLineModelList.add(data.get());


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
