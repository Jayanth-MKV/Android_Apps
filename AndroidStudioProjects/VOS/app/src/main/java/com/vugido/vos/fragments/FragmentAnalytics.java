package com.vugido.vos.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.vugido.vos.R;
import com.vugido.vos.activites.ShowAnalytics;

public class FragmentAnalytics extends Fragment implements View.OnClickListener {

    private Context context;
    MaterialCardView orderCountAnalysis;
    MaterialCardView salesAnalysis,profitAnalysis;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_analytics,container,false);
        orderCountAnalysis=view.findViewById(R.id.OrderCountAnalysis);
        salesAnalysis=view.findViewById(R.id.SalesAnalysis);
        profitAnalysis=view.findViewById(R.id.profitAnalysis);
        salesAnalysis.setOnClickListener(this);
        orderCountAnalysis.setOnClickListener(this);
        profitAnalysis.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context,ShowAnalytics.class);
        switch (v.getId()){
            case R.id.OrderCountAnalysis:
                intent.putExtra("VALUE",1);
                startActivity(intent);
                break;
            case R.id.SalesAnalysis:
                intent.putExtra("VALUE",2);
                startActivity(intent);
                break;
            case R.id.profitAnalysis:
                intent.putExtra("VALUE",3);
                startActivity(intent);
                break;
        }
    }
}
