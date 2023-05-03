package com.imgideongo.vfp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imgideongo.vfp.ConfigVariables.Config;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.helper.MyPreferences;

public class Account extends Fragment implements View.OnClickListener {
private TextView name,address,timing,email,contact,credentials;
Context context;
private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_account,container,false);
        name=view.findViewById(R.id.ClientName);
        address=view.findViewById(R.id.ClientAddress);
        timing=view.findViewById(R.id.ClientTimings);
        email=view.findViewById(R.id.ClientEmail);
        contact=view.findViewById(R.id.ClientContacts);
        credentials=view.findViewById(R.id.credentials);
        imageView=view.findViewById(R.id.clientLogo);
        name.setOnClickListener(this);
        timing.setOnClickListener(this);
        email.setOnClickListener(this);
        contact.setOnClickListener(this);
        credentials.setOnClickListener(this);
        MyPreferences myPreferences=new MyPreferences(context);
        name.setText(myPreferences.getClientName());
        address.setText(myPreferences.getUserPrimaryAddress());
        timing.setText(myPreferences.getClientTimings());
        email.setText(myPreferences.getUserName());
        contact.setText(myPreferences.getUserMobile());
        Glide.with(context).load(Config.URL_CLIENT_LOGO+myPreferences.getLogoString()).into(imageView);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ClientName:
                break;
            case R.id.ClientTimings:
                break;
            case R.id.ClientEmail:
                break;
            case R.id.ClientContacts:
                break;
            case R.id.credentials:
                //
                break;

        }


    }
}
