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

public class DocumentalFragment extends Fragment implements View.OnClickListener {

    Button Proceed;
    DF df;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        df= (DF) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_documents,container,false);

        Proceed=view.findViewById(R.id.button4);
        Proceed.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        df.df();
    }

    public interface DF{
        void df();
    }
}
