package com.jntuk.ucev.placementsportal.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.fragments.Reg.CredentialsFragment;
import com.jntuk.ucev.placementsportal.fragments.Reg.DocumentalFragment;
import com.jntuk.ucev.placementsportal.fragments.Reg.EducationalDetailsFragment;
import com.jntuk.ucev.placementsportal.fragments.Reg.PersonalDetailsFragment;

import java.util.Objects;


public class ActivityRegisteration extends AppCompatActivity implements View.OnClickListener , DocumentalFragment.DF,EducationalDetailsFragment.EF,PersonalDetailsFragment.PI {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    PersonalDetailsFragment personalDetailsFragment;
    DocumentalFragment documentalFragment;
    EducationalDetailsFragment educationalDetailsFragment;
    CredentialsFragment credentialsFragment;
    View v1,v2,v3,v4;
    ImageView i1,i2,i3,i4;

    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reg);


        toolbar=findViewById(R.id.toolbar);


        toolbar.setTitle("Register");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());
        fragmentManager=getSupportFragmentManager();
        v1=findViewById(R.id.p_divider);
        v2=findViewById(R.id.e_divider);
        v3=findViewById(R.id.d_divider);
        v4=findViewById(R.id.c_divider);
        i1=findViewById(R.id.i1);
        i2=findViewById(R.id.i2);
        i3=findViewById(R.id.i3);
        i4=findViewById(R.id.i4);



        defaultFragment();
    }

    private void defaultFragment() {

        if(personalDetailsFragment==null)
            personalDetailsFragment=new PersonalDetailsFragment();
        if (!personalDetailsFragment.isVisible()) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container_view_tag, personalDetailsFragment, "PF");
            fragmentTransaction.commit();
        }

    }

    private void mFragmentTransaction(Fragment fragment,Fragment f,String tag){
        fragmentTransaction=fragmentManager.beginTransaction();
        if(!fragment.isVisible()){
            fragmentTransaction.add(R.id.fragment_container_view_tag,fragment,tag);
        }else {
            fragmentTransaction.hide(f);
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();


    }


    @Override
    public void onClick(View v) {



    }

    @Override
    public void pi() {
        if (educationalDetailsFragment==null)
            educationalDetailsFragment=new EducationalDetailsFragment();
        mFragmentTransaction(educationalDetailsFragment,personalDetailsFragment,"EF");
        v1.setBackgroundColor(getResources().getColor(R.color.gradient_start_color));
        i1.setVisibility(View.VISIBLE);

    }

    @Override
    public void ef() {
        if (documentalFragment==null)
            documentalFragment=new DocumentalFragment();
        mFragmentTransaction(documentalFragment,educationalDetailsFragment,"DF");
        v2.setBackgroundColor(getResources().getColor(R.color.gradient_start_color));
        i2.setVisibility(View.VISIBLE);
    }

    @Override
    public void df() {
        if (credentialsFragment==null)
            credentialsFragment=new CredentialsFragment();
        mFragmentTransaction(credentialsFragment,documentalFragment,"CF");
        v3.setBackgroundColor(getResources().getColor(R.color.gradient_start_color));
        i3.setVisibility(View.VISIBLE);

    }
}
