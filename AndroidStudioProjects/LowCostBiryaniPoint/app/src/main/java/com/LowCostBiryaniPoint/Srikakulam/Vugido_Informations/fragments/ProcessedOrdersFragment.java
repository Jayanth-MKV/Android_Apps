package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.R;

public class ProcessedOrdersFragment extends Fragment {

    public ProcessedOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_processed_orders, container, false);
    }
}
