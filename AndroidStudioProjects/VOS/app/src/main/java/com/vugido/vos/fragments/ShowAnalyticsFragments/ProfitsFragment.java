package com.vugido.vos.fragments.ShowAnalyticsFragments;

import android.content.Context;
import android.graphics.Color;
import android.hardware.camera2.params.OisSample;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.vugido.vos.R;
import com.vugido.vos.design.VerticalTextView;
import com.vugido.vos.models.Analytics.CustomOrders.DATAItem;
import com.vugido.vos.models.Analytics.CustomOrders.Response;
import com.vugido.vos.models.Analytics.Products.PRODUCTSItem;
import com.vugido.vos.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class ProfitsFragment extends Fragment implements View.OnDragListener {
    List<DATAItem> dataItemList;
    VerticalTextView verticalTextView;
    BarChart barChart;
    TextView textView;
    LottieAnimationView lottieAnimationView;
    ArrayList<Integer> OrderIds;
    ArrayList<String> ErrorOrder;
    RelativeLayout StatsCard;
    TextView t1,t2,t3,error;
    private Context context;
    Button button;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profit_analysis, container, false);

        verticalTextView = view.findViewById(R.id.verticalTextView);
        textView = view.findViewById(R.id.textView2);
        button=view.findViewById(R.id.button);
        lottieAnimationView = view.findViewById(R.id.lottie_progress_center);
        barChart=view.findViewById(R.id.barChart);
        StatsCard=view.findViewById(R.id.stats_card);
        t1=view.findViewById(R.id.textView3);
        t2=view.findViewById(R.id.textView4);
        t3=view.findViewById(R.id.textView5);
        barChart.setOnDragListener(this);
        error=view.findViewById(R.id.textView6);
        ErrorOrder=new ArrayList<>();
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0 || event.getAction()==2){
                    LinkedHashSet<String> linkedHashSet=new LinkedHashSet<>(ErrorOrder);
                    ArrayList<String> errors=new ArrayList<>(linkedHashSet);
                    error.setVisibility(View.VISIBLE);
                    StringBuilder stringBuilder=new StringBuilder();
                    for (String e:errors){
                        stringBuilder.append("\n");
                        stringBuilder.append(e);
                    }
                    error.setText("Error DATA With Zero Purchase Prices Included In OID's"+stringBuilder.toString());
                }else {
                    error.setVisibility(View.GONE);
                }
                return false;            }
        });

        barChart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0 || event.getAction()==2){
                    StatsCard.setVisibility(View.GONE);
                }else {
                    StatsCard.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });


        showDialogBox();

        //first fetch order id data today....
       // fetchOrderIds(0);
        return view;
    }

    private void showDialogBox() {

        myDialogBoxUpdater();

    }


    private void myDialogBoxUpdater() {

        String Price;

        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView=null;

        dialogView = inflater.inflate(R.layout.profits_get_orders_dialog, null);
        //Price= String.valueOf(price);


        // set pre data.....
        final TextInputEditText f=dialogView.findViewById(R.id.from);
        final TextInputEditText t=dialogView.findViewById(R.id.to);
        final TextInputEditText l=dialogView.findViewById(R.id.leave);

        Button cancel =  dialogView.findViewById(R.id.button_cancel);
        Button update = dialogView.findViewById(R.id.button_update);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fetchOrderIds(Objects.requireNonNull(f.getText()).toString(), Objects.requireNonNull(t.getText()).toString(), Objects.requireNonNull(l.getText()).toString());
                //updatePrice(pid,ppp_id, Objects.requireNonNull(p.getText()).toString());
                dialogBuilder.dismiss();



            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();




    }


    private void fetchOrderIds(String from,String to,String leave) {
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        lottieAnimationView.setSpeed(1f);
        Map<String,Object> map=new HashMap<>();
        map.put("START",from);
        map.put("TO",to);
        map.put("LEAVE",leave);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().getCustomOrderIds(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        dataItemList=response.body().getDATA();
                        fetchProductsData(dataItemList);
                        //setGraphData(dataItemList);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    private void fetchProductsData(final List<DATAItem> dataItemList) {

       // ArrayList<> OrderIds=new ArrayList<>();

        StringBuilder stringBuilder=new StringBuilder();
        OrderIds=new ArrayList<>();
            boolean checked=false;
            for(int o=0;o<dataItemList.size();o++){
                if(o!=0)
                stringBuilder.append(",");
                stringBuilder.append(dataItemList.get(o).getID());
                OrderIds.add(dataItemList.get(o).getID());
            }

        Map<String,Object> map=new HashMap<>();
        map.put("OID_STR",stringBuilder.toString());

        Call<com.vugido.vos.models.Analytics.Products.Response> call=RetrofitBuilder.getInstance().getRetrofit().getProductByOrderIds(map);
        call.enqueue(new Callback<com.vugido.vos.models.Analytics.Products.Response>() {
            @Override
            public void onResponse(Call<com.vugido.vos.models.Analytics.Products.Response> call, retrofit2.Response<com.vugido.vos.models.Analytics.Products.Response> response) {

                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.pauseAnimation();
                verticalTextView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        List<com.vugido.vos.models.Analytics.Products.DATAItem> dataItems=response.body().getDATA();
                        Log.e("Data",dataItems.toString());

                        setGraphData(dataItemList,dataItems);
                    }
                }
            }

            @Override
            public void onFailure(Call<com.vugido.vos.models.Analytics.Products.Response> call, Throwable t) {

            }
        });


    }


    private void setGraphData(List<DATAItem> dataItemList, List<com.vugido.vos.models.Analytics.Products.DATAItem> dataItems) {

        //Collections.reverse(dataItems);
        verticalTextView.setText("Amount Analysis");
        textView.setText("Today's Sales Analysis By Order ID ");
        barChart.setVisibility(View.VISIBLE);
        barChart.setBackgroundColor(Color.parseColor("#4c84ff"));


        /////preparing data...
        final ArrayList<String> OIDs=new ArrayList<>();
        ArrayList<Float> TotalSale=new ArrayList<>();
        ArrayList<Float> Investment= new ArrayList<>();
        ArrayList<Float> Profit=new ArrayList<>();
        for(com.vugido.vos.models.Analytics.Products.DATAItem dataItem:dataItems){
            OIDs.add(String.valueOf(dataItem.getOID()));
            Investment.add(getInvestment(dataItem));
            Profit.add(getProfit(dataItem));
            TotalSale.add(getTotalSale(dataItem));

        }

        setStatsCard(TotalSale,Investment,Profit);
        ////setting data
        ArrayList<BarEntry> InvestmentEntries=new ArrayList<>();
        ArrayList<BarEntry> ProfitEntries=new ArrayList<>();
        ArrayList<BarEntry> TotalSaleEntries=new ArrayList<>();
        for(int i=0;i<dataItems.size();i++){
            InvestmentEntries.add(new BarEntry(i,Investment.get(i)));
            ProfitEntries.add(new BarEntry(i,Profit.get(i)));
            TotalSaleEntries.add(new BarEntry(i,TotalSale.get(i)));
        }
        BarDataSet barDataSet1=new BarDataSet(InvestmentEntries,"Investments");
        barDataSet1.setColor(Color.WHITE);
        barDataSet1.setDrawIcons(false);
        BarDataSet barDataSet2=new BarDataSet(ProfitEntries,"Profits");
        barDataSet2.setColor(Color.GREEN);
        barDataSet2.setDrawIcons(false);


        BarDataSet barDataSet3=new BarDataSet(TotalSaleEntries,"Total Sales");
        barDataSet3.setColor(Color.RED);
        barDataSet3.setDrawIcons(false);

        barDataSet1.setHighlightEnabled(true);
        barDataSet2.setHighlightEnabled(true);
        barDataSet3.setHighlightEnabled(true);
       /* barDataSet1.setHighLightColor(Color.WHITE);
        barDataSet2.setHighLightColor(Color.GREEN);
        barDataSet3.setHighLightColor(Color.RED);*/

        ArrayList<IBarDataSet> barDataSets=new ArrayList<>();
        barDataSets.add(barDataSet2);
        barDataSets.add(barDataSet3);
        barDataSets.add(barDataSet1);
        //barDataSets.add(barDataSet2);




        BarData barData=new BarData(barDataSets);
        barData.setBarWidth(0.15f);
        barData.setValueTextColor(Color.WHITE);
        barData.setHighlightEnabled(true);
        barData.setValueTextSize(10f);
        barData.setValueTextColor(Color.WHITE);
        barChart.getAxisRight().setEnabled(false);
       barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(OIDs));

       barChart.setExtraRightOffset(10);
        barChart.setData(barData);
        barChart.setScaleEnabled(true);
        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.setHighlightFullBarEnabled(true);
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextColor(Color.WHITE);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1f);
        barChart.animateY(5000);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.setVisibleXRangeMaximum(7);
        barChart.getXAxis().setCenterAxisLabels(true);
        barChart.groupBars(0,0.40f,0.05f);
        barChart.getXAxis().setAxisMaximum(OIDs.size());
        barChart.setFitBars(true);
        barChart.invalidate();

        if (ErrorOrder.size()==0){
            //no errors in data..
            Toast.makeText(context,"No Error All Products In Orders Are Priced(Non-Zero)",Toast.LENGTH_LONG).show();
        }else {

            Toast.makeText(context,"Error In DATA Tap Error Button",Toast.LENGTH_LONG).show();
            button.setVisibility(View.VISIBLE);
        }


    }

    private void setStatsCard(ArrayList<Float> totalSale, ArrayList<Float> investment, ArrayList<Float> profit) {
        float ts=0,i=0,p=0;
        for(int index=0;index<totalSale.size();index++){
            ts+=totalSale.get(index);
            i+=investment.get(index);
            p+=profit.get(index);
        }

        t1.setText("Sale : Rs."+ts+"/-");
        t2.setText("Investment: Rs."+i+"/-");
        t3.setText("Profit : Rs."+p+"/-");
        t1.setTextColor(Color.RED);
        t2.setTextColor(Color.WHITE);
        t3.setTextColor(Color.GREEN);

        StatsCard.setVisibility(View.VISIBLE);
    }

    private Float getTotalSale(com.vugido.vos.models.Analytics.Products.DATAItem dataItem) {
        List<PRODUCTSItem>productsItemList=dataItem.getPRODUCTS();
        float ts=0;
        for(PRODUCTSItem productsItem:productsItemList){
                ts+=(productsItem.getTOTALPRICE());
        }

        return ts;

    }


    private Float getProfit(com.vugido.vos.models.Analytics.Products.DATAItem dataItem) {


        List<PRODUCTSItem>productsItemList=dataItem.getPRODUCTS();
        float profit=0;
        for(PRODUCTSItem productsItem:productsItemList){
            int q=productsItem.getQUALIFIER();
            if(q==0){
                //in grams

                profit+=(productsItem.getTOTALPRICE()-(calculateInvestment(productsItem,1000f,dataItem.getOID())));



            }else {
                //in units..
                profit+=(productsItem.getTOTALPRICE()-(calculateInvestment(productsItem,productsItem.getQUANTITY(),dataItem.getOID())));

            }
        }

        return profit;

    }

    private Float getInvestment(com.vugido.vos.models.Analytics.Products.DATAItem dataItem) {

        List<PRODUCTSItem>productsItemList=dataItem.getPRODUCTS();
        float investment=0;
        for(PRODUCTSItem productsItem:productsItemList){
            int q=productsItem.getQUALIFIER();
            if(q==0){
                //in grams

                investment+=calculateInvestment(productsItem,1000f,dataItem.getOID());
            }else {
                //in units..
                investment+=calculateInvestment(productsItem,productsItem.getQUANTITY(),dataItem.getOID());
            }
        }

        return investment;
    }

    private float calculateInvestment(PRODUCTSItem productsItem, float v,int oid) {

        if(Integer.parseInt(productsItem.getPP())==0){
            // error ..
            ErrorOrder.add("OID: "+(oid)+"Product Name :"+productsItem.getTNAME());
        }
        float pp=Float.parseFloat(productsItem.getPP());
        return Float.parseFloat(String.valueOf(productsItem.getQUANTITY()))*(pp/v);
    }



    @Override
    public boolean onDrag(View v, DragEvent event) {

        Toast.makeText(getContext(),"event"+event.getAction(),Toast.LENGTH_LONG).show();
        return true;
    }
}


