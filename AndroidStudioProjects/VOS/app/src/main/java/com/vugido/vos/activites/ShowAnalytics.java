package com.vugido.vos.activites;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.vugido.vos.R;
import com.vugido.vos.adapters.DashBoardMenuAdapter;
import com.vugido.vos.design.Space;
import com.vugido.vos.design.VerticalTextView;
import com.vugido.vos.fragments.FragmentHome;
import com.vugido.vos.fragments.ShowAnalyticsFragments.OrdersFragment;
import com.vugido.vos.fragments.ShowAnalyticsFragments.ProfitsFragment;
import com.vugido.vos.fragments.ShowAnalyticsFragments.SalesFragment;
import com.vugido.vos.models.Analytics.AllOrders.DATAItem;
import com.vugido.vos.models.Analytics.AllOrders.Response;
import com.vugido.vos.models.DashBoardMenuModel.DashBoardMenuModel;
import com.vugido.vos.network.RetrofitBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;


public class ShowAnalytics extends AppCompatActivity  implements DashBoardMenuAdapter.DashBoardItemState , SalesFragment.UIUpdater, OrdersFragment.UiUpdater {

    int Value;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    Fragment fragment;
    private RecyclerView dashboardMenu;
    private List<DashBoardMenuModel> dashBoardMenuModelList;
    private DashBoardMenuAdapter dashBoardMenuAdapter;
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_analytics);

        constraintLayout=findViewById(R.id.analytics_background);
        dashboardMenu=findViewById(R.id.recyclerView);
        Value = getIntent().getIntExtra("VALUE", 0);
        fragmentManager=getSupportFragmentManager();
       // setFragmentDashBoardMenu(Value);
        setFragment(Value);






    }

    private void setFragmentDashBoardMenu(int value) {
        dashBoardMenuModelList=getDashBoardMenus(value);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dashboardMenu.setLayoutManager(linearLayoutManager);
        dashboardMenu.addItemDecoration(new Space(1,10,true,0));
        dashBoardMenuAdapter=new DashBoardMenuAdapter(dashBoardMenuModelList,this);
        dashboardMenu.setAdapter(dashBoardMenuAdapter);
    }
    private List<DashBoardMenuModel> getDashBoardMenus(int value){
        List<DashBoardMenuModel> dashBoardMenuModelList = new ArrayList<>();
        switch (value) {
            case 1:
            dashBoardMenuModelList.add(new DashBoardMenuModel(R.drawable.time_line, 1, R.drawable.time_line_white, true,"Orders"));

            return dashBoardMenuModelList;
            case 2:
                dashBoardMenuModelList.add(new DashBoardMenuModel(R.drawable.time_line, 1, R.drawable.time_line_white, true,"Sales"));
                dashBoardMenuModelList.add(new DashBoardMenuModel(R.drawable.time_line, 2, R.drawable.time_line_white, false,"Profits"));
                return dashBoardMenuModelList;
        }
        return null;
    }

    private void setFragment(int value) {

        switch (value){

            case 1:
                fragment=new OrdersFragment();
                addFragment(fragment,"O_A");
                break;
            case 2:
                fragment=new SalesFragment();
                addFragment(fragment,"S_L");
                break;
            case 3:
                fragment=new ProfitsFragment();
                addFragment(fragment,"P_A");
                break;

        }
    }

    private void addFragment(Fragment fragment,String TAG) {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.analytics_fragment_container,fragment,TAG);
        fragmentTransaction.commit();
    }


    @Override
    public void dashBoardMenuSate(int id) {

        Toast.makeText(this,"Clicked"+id,Toast.LENGTH_SHORT).show();
       for(int index=0;index<dashBoardMenuModelList.size();index++){
           if(dashBoardMenuModelList.get(index).getId()==id){
               dashBoardMenuModelList.get(index).setState(true);
           }else {
               dashBoardMenuModelList.get(index).setState(false);
           }
       }

       dashBoardMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void uiUpdater() {
        dashboardMenu.setVisibility(View.VISIBLE);
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.gradient_start_color));

    }

    @Override
    public void UiUpdater() {
        dashboardMenu.setVisibility(View.VISIBLE);
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.gradient_start_color));
    }
}
