package com.jntuk.ucev.attendance_management_system_faculty_app.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jntuk.ucev.attendance_management_system_faculty_app.R;
import com.jntuk.ucev.attendance_management_system_faculty_app.fragments.Dashboard;
import com.jntuk.ucev.attendance_management_system_faculty_app.fragments.Settings;
import com.jntuk.ucev.attendance_management_system_faculty_app.fragments.Timeline;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {



    private Dashboard dashboard;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Settings settings;
    private Timeline timeline;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager=getSupportFragmentManager();
        bottomNavigationView=findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        defaultFragment();

    }





    private void defaultFragment() {
        dashboard=new Dashboard();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,dashboard,"DB");
        fragmentTransaction.commit();

    }


    // creating a method to show and hide fragment..
    private void showAndHideFragment(Fragment fragment, String TAG){

        fragmentTransaction=fragmentManager.beginTransaction();

        // there may be multiple fragments.. being created..so we need to know which fragment is visible
        if(dashboard!=null && dashboard.isVisible())
            fragmentTransaction.hide(dashboard);
        if(timeline!=null && timeline.isVisible())
            fragmentTransaction.hide(timeline);
        if(settings!=null && settings.isVisible())
            fragmentTransaction.hide(settings);

        // now have hided all existing fragments...
        // now using the fragment value passed to this method we will show that fragment..

        if(fragment!=null&& fragment.isAdded())
            fragmentTransaction.show(fragment);
        else {
            assert fragment != null;
            fragmentTransaction.add(R.id.fragment_container,fragment,TAG);
        }

        // finally commit..
        fragmentTransaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()){

            case R.id.home:
               // Cart.setVisible(true);
                // Notifications.setVisible(true);
                // PocketCart.setVisible(false);
                if(dashboard==null)
                    dashboard=new Dashboard();
                // now instead replacing fragments we can call hide and show method..

                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");


                showAndHideFragment(dashboard,"DB");
                break;
            case R.id.Line:
               // Cart.setVisible(true);

                if(timeline==null)
                    timeline=new Timeline();

                showAndHideFragment(timeline,"LINE");

                break;
            case R.id.se:
                //Cart.setVisible(false);
                // PocketCart.setVisible(false);
                // Notifications.setVisible(false);
                if(settings==null)
                    settings=new Settings();
                // now instead replacing fragments we can call hide and show method..

                //fragmentTransaction(fragmentOne,"FRAGMENT_ONE");


                showAndHideFragment(settings,"SE");
                break;


        }



        return true;



    }

}