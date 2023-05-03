package com.vugido.vos.fragments.ShowAnalyticsFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.vugido.vos.R;
import com.vugido.vos.design.VerticalTextView;
import com.vugido.vos.models.Analytics.AllOrders.DATAItem;
import com.vugido.vos.models.Analytics.AllOrders.Response;
import com.vugido.vos.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class OrdersFragment extends Fragment {

    VerticalTextView verticalTextView;
    TextView textView;
    LineChart lineChart;
    LottieAnimationView lottieAnimationView;
    private UiUpdater uiUpdater;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiUpdater= (UiUpdater) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_orders_analysis,container,false);
        verticalTextView = view.findViewById(R.id.verticalTextView);
        textView = view.findViewById(R.id.textView2);
        lottieAnimationView = view.findViewById(R.id.lottie_progress_center);
        lineChart = view.findViewById(R.id.lineChart);


        //first fetch data....
        fetchAllOrdersData();
        return view;
    }

    private void setLineChart(List<DATAItem> dataItemList) {

        verticalTextView.setText("Number Of Orders");
        textView.setText("Orders By Date ");
        lineChart.setVisibility(View.VISIBLE);
        lineChart.setBackgroundColor(Color.parseColor("#4c84ff"));
       // lineChart.setOnChartGestureListener(this);
       // lineChart.setOnChartValueSelectedListener(this);
        ArrayList<String> dates=getUniqueDates(dataItemList);

        getOrderCountByDate(dates,dataItemList);


    }

    private void getOrderCountByDate(final ArrayList<String> dates, List<DATAItem> dataItemList) {

        // reversed... list..
        Collections.reverse(dates);
        Collections.reverse(dataItemList);
        final ArrayList<Integer> OrderCount=new ArrayList<>();

        for(int d=0;d<30;d++){
            int Orders=0;
            boolean checked=false;
            for(int o=0;o<dataItemList.size();o++){
                if(dates.get(d).equals(dataItemList.get(o).getDATE().replace(".",""))){
                    checked=true;
                    Orders+=1;
                }else {
                    if(checked){
                        break;
                    }
                }
            }
            OrderCount.add(Orders);
        }

        ArrayList<Entry> OrderStats=new ArrayList<>();

        for(int i=0;i<30;i++){
            OrderStats.add(new Entry(i,OrderCount.get(i)));
        }

        LineDataSet set1=new LineDataSet(OrderStats,"Orders Count");


        set1.setFillAlpha(1);
        set1.setColor(Color.WHITE);
        set1.setLineWidth(5f);
        set1.setDrawCircles(true);
        set1.setCircleHoleColor(Color.BLACK);
        set1.setCircleRadius(10f);
        set1.setCircleHoleRadius(5f);
        set1.setCircleColor(Color.WHITE);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.WHITE);
        set1.setHighlightEnabled(true);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setGradientColor(R.color.gradient_start_color,R.color.gradient_end_color);
        set1.setHighlightEnabled(true);
        set1.setHighLightColor(Color.WHITE);





        ArrayList<ILineDataSet> iLineDataSets=new ArrayList<>();
        iLineDataSets.add(set1);

        LineData lineData=new LineData(iLineDataSets);
        lineData.setDrawValues(true);
        lineData.setHighlightEnabled(true);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return dates.get((int)value);
            }
        });
       /* final ArrayList<String> orders=new ArrayList<>();
        for(Integer integer:OrderCount){
            orders.add(String.valueOf(integer));
        }

        lineChart.getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return orders.get((int) value);
            }
        });*/


        lineChart.setHovered(true);
        lineChart.setScaleEnabled(true);
        lineChart.getXAxis().setGranularity(1f);
        lineChart.getXAxis().setLabelRotationAngle(350);
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.getXAxis().setAxisLineColor(Color.WHITE);
        lineChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getLegend().setEnabled(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setData(lineData);
        lineChart.animateXY(5000,3000);
        lineChart.invalidate();



    }



    private ArrayList<String> getUniqueDates(List<DATAItem> dataItemList){

        ArrayList<String> dates=new ArrayList<>();

        // filtering dates
        for(DATAItem dataItem:dataItemList){
            String d=dataItem.getDATE();
            dates.add(d.replace(".",""));
        }

        LinkedHashSet<String> uniqueDates=new LinkedHashSet<>(dates);

        return new ArrayList<>(uniqueDates);

    }
    private void fetchAllOrdersData() {

        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        lottieAnimationView.setSpeed(1f);
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getAllOrders();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
                if(response.isSuccessful()){


                    assert response.body() != null;
                    if(!response.body().isError()){

                       // uiUpdater.UiUpdater();
                        List<DATAItem> dataItemList=response.body().getDATA();

                        verticalTextView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                            setLineChart(dataItemList);
                    }else {
                        //error
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                lottieAnimationView.pauseAnimation();
                lottieAnimationView.setVisibility(View.GONE);
            }
        });

    }
    public interface UiUpdater{
        void UiUpdater();
    }

}
