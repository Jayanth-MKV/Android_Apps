package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.baoyachi.stepview.VerticalStepView;
import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.models.tracking.DATA;
import com.foodhub.vugido.models.tracking.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;

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
    View Progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        Progress=findViewById(R.id.my_progress);
        Progress.setVisibility(View.GONE);
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


        Progress.setVisibility(View.VISIBLE);
        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(this).getUID());
        map.put("OID",String.valueOf(OID));


        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).getOrderTrackingInfo(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                Progress.setVisibility(View.GONE);
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
                Progress.setVisibility(View.GONE);
            }
        });
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
