package com.vugido.brain_cord.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.fragments.main.FragmentHome;
import com.vugido.brain_cord.fragments.main.FragmentProfile;
import com.vugido.brain_cord.fragments.main.FragmentQuizz;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FragmentHome fragmentHome;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentProfile fragmentProfile;
    private FragmentQuizz fragmentQuizz;
    private BottomNavigationView bottomNavigationView;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.include);

        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);
        fragmentManager=getSupportFragmentManager();
        bottomNavigationView=findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        defaultFragment();
    }

    private void defaultFragment() {
        fragmentHome=new FragmentHome();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragmentHome,"Home");
        fragmentTransaction.commit();

    }

    // creating a method to show and hide fragment..
    private void showAndHideFragment(Fragment fragment, String TAG){

        fragmentTransaction=fragmentManager.beginTransaction();

        // there may be multiple fragments.. being created..so we need to know which fragment is visible
        if(fragmentHome!=null && fragmentHome.isVisible())
            fragmentTransaction.hide(fragmentHome);
        if(fragmentProfile!=null && fragmentProfile.isVisible())
            fragmentTransaction.hide(fragmentProfile);
        if(fragmentQuizz!=null && fragmentQuizz.isVisible())
            fragmentTransaction.hide(fragmentQuizz);

        // now have hided all existing fragments...
        // now using the fragment value passed to this method we will show that fragment..

        if(fragment!=null&& fragment.isAdded())
            fragmentTransaction.show(fragment);
        else {
            assert fragment != null;
            fragmentTransaction.add(R.id.fragment_container,fragment,TAG);
        }

        // finally commit..
        toolbar.setTitle(TAG);
        fragmentTransaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()){

            case R.id.home:

                if(fragmentHome==null)
                    fragmentHome=new FragmentHome();
                // now instead replacing fragments we can call hide and show method..

                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");


                showAndHideFragment(fragmentHome,"Home");
                break;
            case R.id.Quizz:
                // Cart.setVisible(true);

                if(fragmentQuizz==null)
                    fragmentQuizz=new FragmentQuizz();

                showAndHideFragment(fragmentQuizz,"Quizz");
                break;
            case R.id.account:
                // Cart.setVisible(true);

                if(fragmentProfile==null)
                    fragmentProfile=new FragmentProfile();

                showAndHideFragment(fragmentProfile,"Profile");
                // invoke earn coupons fragment
                break;


        }



        return true;



    }


}