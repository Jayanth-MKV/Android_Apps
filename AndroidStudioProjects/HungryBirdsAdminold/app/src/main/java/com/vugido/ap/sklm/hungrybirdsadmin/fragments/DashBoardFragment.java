package com.vugido.ap.sklm.hungrybirdsadmin.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.ap.sklm.hungrybirdsadmin.R;
import com.vugido.ap.sklm.hungrybirdsadmin.adapters.DashboardREcyclerAdapter;
import com.vugido.ap.sklm.hungrybirdsadmin.models.DashBoardModel;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    RecyclerView recyclerView;
    List<DashBoardModel> dashBoardModelList;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dashboard,container,false);
        recyclerView=view.findViewById(R.id.dashboard_recycler);



        //adapter
        //data set source...
        //binding data with adapter...


        dashBoardModelList=new ArrayList<>();

        dashBoardModelList.add(new DashBoardModel(R.drawable.ic_launcher_background,"Active Orders","20"));
        dashBoardModelList.add(new DashBoardModel(R.drawable.ic_launcher_background,"Failed Orders","3"));
        dashBoardModelList.add(new DashBoardModel(R.drawable.ic_launcher_background,"Processed Orders","30"));
        dashBoardModelList.add(new DashBoardModel(R.drawable.ic_launcher_background,"Today Floating","53"));
        dashBoardModelList.add(new DashBoardModel(R.drawable.ic_launcher_background,"Sales","Rs.10,000/-"));

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        DashboardREcyclerAdapter dashboardREcyclerAdapter=new DashboardREcyclerAdapter(context,dashBoardModelList);
        recyclerView.setAdapter(dashboardREcyclerAdapter);
        return view;
    }
}
