package com.vugido.com.vugidoeats.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.dialogs.MyDialogLoader;
import com.vugido.com.vugidoeats.fragments.login_fragments.LoginFragment;
import com.vugido.com.vugidoeats.fragments.login_fragments.SignUpFragment;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener , SignUpFragment.LoginFragmentInterface, LoginFragment.SignUpFragmentInterface ,
        LoginFragment.ShowProgress, SignUpFragment.BottomLoader, LoginFragment.LoginLoader {

    private MyDialogLoader myDialogLoader;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    Toolbar toolbar;
    //View Progress;
    public static final int CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frameLayout=findViewById(R.id.login_fragment_container);
        fragmentManager=getSupportFragmentManager();
       // Progress=findViewById(R.id.my_progress);
       // Progress.setVisibility(View.INVISIBLE);
        loadDefaultSignUpFragment();



       /* AppSignatureHelper appSignatureHelper=new AppSignatureHelper(this);
        ArrayList<String> stringArrayList=appSignatureHelper.getAppSignatures();*/


       // Log.e("HashString",stringArrayList.toString());


        toolbar=findViewById(R.id.login_activity_toolbar);


        toolbar.setTitle("Sign-Up");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });







    }

    private void loadDefaultSignUpFragment() {

        fragment=new SignUpFragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_fragment_container,fragment,"SIGN_UP");
        fragmentTransaction.commit();

    }



    @Override
    public void onClick(View view) {




    }


    @Override
    public void loginFragment() {
        // replace the sign up with  login fragment


        fragment=new LoginFragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment_container,fragment,"LOGIN");
        fragmentTransaction.commit();

        toolbar.setTitle("Login");

    }

    @Override
    public void signUpFragment() {


        fragment=new SignUpFragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment_container,fragment,"SIGN_UP");
        fragmentTransaction.commit();

        toolbar.setTitle("Sign-Up");

    }



    @Override
    public void showProgress(boolean show) {

        if(show){
          //  Progress.setVisibility(View.VISIBLE);
        }else {
          //  Progress.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void bottomLoader(boolean state,String message) {


        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",message);
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
    } else {
            myDialogLoader.dismiss();
        }

    }

    @Override
    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }
}
