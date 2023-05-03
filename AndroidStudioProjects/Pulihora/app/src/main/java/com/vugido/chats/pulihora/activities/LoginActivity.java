package com.vugido.chats.pulihora.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vugido.chats.pulihora.R;
import com.vugido.chats.pulihora.dialogs.MyDialogLoader;
import com.vugido.chats.pulihora.fragments.login_fragments.LoginFragment;
import com.vugido.chats.pulihora.fragments.login_fragments.SignUpFragment;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener , SignUpFragment.LoginFragmentInterface, LoginFragment.SignUpFragmentInterface ,
        LoginFragment.ShowProgress, SignUpFragment.BottomLoader, LoginFragment.LoginLoader{

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

        loadDefaultSignUpFragment();



        toolbar=findViewById(R.id.login_activity_toolbar);


        toolbar.setTitle("Sign-Up");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationOnClickListener(view -> finish());
//

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
