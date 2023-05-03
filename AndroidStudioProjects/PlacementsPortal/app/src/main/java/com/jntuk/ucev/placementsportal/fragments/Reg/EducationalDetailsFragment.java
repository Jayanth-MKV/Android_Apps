package com.jntuk.ucev.placementsportal.fragments.Reg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jntuk.ucev.placementsportal.R;

public class EducationalDetailsFragment extends Fragment implements View.OnClickListener {
    Button Proceed;
    EF ef;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ef= (EF) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_educational,container,false);
        Proceed=view.findViewById(R.id.button4);
        Proceed.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        ef.ef();
    }

    public interface EF{
        void ef();
    }
}
