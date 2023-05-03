package com.jntuk.ucev.placementsportal.fragments.Reg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.activities.MainActivity;

public class CredentialsFragment extends Fragment implements View.OnClickListener {

    CF cf;
    Button register;
    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
       // cf= (CF) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_credentials,container,false);
        register=view.findViewById(R.id.button4);
        register.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.button4){

            startActivity(new Intent(context, MainActivity.class));

        }
    }


    public interface CF{

        void cf();
    }
}
