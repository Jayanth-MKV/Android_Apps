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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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

public class SalesFragment extends Fragment {
    List<DATAItem> dataItemList;
    VerticalTextView verticalTextView;
    BarChart barChart;
    TextView textView;
    LottieAnimationView lottieAnimationView;
    private UIUpdater uiUpdater;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiUpdater= (UIUpdater) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales_analysis, container, false);

        verticalTextView = view.findViewById(R.id.verticalTextView);
        textView = view.findViewById(R.id.textView2);
        lottieAnimationView = view.findViewById(R.id.lottie_progress_center);
        barChart=view.findViewById(R.id.barChart);


        //first fetch data....
        fetchAllOrdersData();

        return view;
    }

    private void setBarChart(List<DATAItem> dataItemList) {
        verticalTextView.setText("Sales Amount");
        textView.setText("Sales By Date");
        barChart.setVisibility(View.VISIBLE);
        barChart.setBackgroundColor(Color.parseColor("#4c84ff"));
        ArrayList<String> dates=getUniqueDates(dataItemList);
        getTotalSalesByDate(dates,dataItemList);
    }
    private void getTotalSalesByDate(ArrayList<String> dates, List<DATAItem> dataItemList) {
        Collections.reverse(dates);
        Collections.reverse(dataItemList);

        ArrayList<Double> TotalSales=new ArrayList<>();
        for(int d=0;d<30;d++){
            double Orders=0;
            boolean checked=false;
            for(int o=0;o<dataItemList.size();o++){
                if(dates.get(d).equals(dataItemList.get(o).getDATE().replace(".",""))){
                    checked=true;
                    Orders+=dataItemList.get(o).getTOTALPRICE();
                }else {
                    if(checked){
                        break;
                    }
                }
            }
            TotalSales.add(Orders);
        }

        ArrayList<BarEntry> SalesEntry=new ArrayList<>();
        final ArrayList<String> labels=new ArrayList<>();

        for(int i=0;i<30;i++){
            SalesEntry.add(new BarEntry(i,Float.parseFloat(String.valueOf(TotalSales.get(i)))));
            labels.add(dates.get(i));
        }

        BarDataSet barDataSet=new BarDataSet(SalesEntry,"Total Sales");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setHighlightEnabled(true);
     //   barDataSet.setHighLightColor(Color.WHITE);
        ArrayList<IBarDataSet> barDataSets=new ArrayList<>();
        barDataSets.add(barDataSet);


        BarData barData=new BarData(barDataSets);
        barData.setBarWidth(0.9f);
        barData.setValueTextColor(Color.WHITE);
        barData.setHighlightEnabled(true);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return labels.get((int)value);
            }
        });
        barChart.setData(barData);
        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.setHighlightFullBarEnabled(true);
        barChart.getLegend().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.setFitBars(true);
        barChart.animateY(5000);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

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
        Call<Response> call = RetrofitBuilder.getInstance().getRetrofit().getAllOrders();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
                if (response.isSuccessful()) {


                    assert response.body() != null;
                    if (!response.body().isError()) {

                       // uiUpdater.uiUpdater();
                        dataItemList = response.body().getDATA();

                        verticalTextView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        setBarChart(dataItemList);


                    } else {
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


   /* private String getOrdersString(int past){

        if(dataItemList!=null){
            Collections.reverse(dataItemList);
            String date=dataItemList.get(0).getDATE().replace(".","");

            StringBuilder stringBuilder=new StringBuilder();
            ArrayList<Integer> OrderIds=new ArrayList();
            for(int d=0;d<1;d++){
               // double Orders=0;
                boolean checked=false;
                for(int o=0;o<dataItemList.size();o++){
                    if(date.equals(dataItemList.get(o).getDATE().replace(".",""))){
                        checked=true;
                        if(o!=0)
                            stringBuilder.append(",");
                        stringBuilder.append(dataItemList.get(o).getOID());
                        OrderIds.add(dataItemList.get(o).getOID());
                    }else {
                        if(checked){
                            break;
                        }
                    }
                }
            }
        }
    }*/

    public interface UIUpdater{

        void uiUpdater();
    }
}

