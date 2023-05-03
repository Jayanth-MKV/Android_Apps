package com.vugido.vugidoupdate.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.fragments.FactsFragment;
import com.vugido.vugidoupdate.fragments.HomeFragment;
import com.vugido.vugidoupdate.fragments.SearchFragment;


public class MainActivity extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    private Toolbar toolbar;
    private ChipNavigationBar chipNavigationBar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private FactsFragment factsFragment;
    private com.vugido.StudentTime.fragments.bottom_nav_fragments.HomeFragmentOldDb homeFragment;
    private SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigationBar=findViewById(R.id.chipNavigationBar);
        toolbar=findViewById(R.id.activity_toolbar);

        toolbar.setTitle("Vugido");
        setSupportActionBar(toolbar);


        chipNavigationBar.setOnItemSelectedListener(this);


        chipNavigationBar.setItemSelected(R.id.bottom_home,false);

        fragmentManager=getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        defaultFragment();
    }

    private void defaultFragment() {
        homeFragment=new com.vugido.StudentTime.fragments.bottom_nav_fragments.HomeFragmentOldDb();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,homeFragment,"HOME");
        fragmentTransaction.commit();
    }

    @Override
    public void onItemSelected(int id) {

      //  chipNavigationBar.setItemSelected(id,false);
        switch (id){
            case R.id.bottom_home:
                Log.e("home","yes home");

                if(homeFragment==null){
                    Log.e("home","null");

                    hideAllFragments("HOME");
                    defaultFragment();
                }else {
                    if(!homeFragment.isVisible()){
                        Log.e("Not VISIBLE","yes home");
                        hideAllFragments("HOME");
                        fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.commit();
                    }else {

                        Log.e("VISIBLE","yes home");
                        // do nothing... user is already in the same fragment
                    }
                }

                break;
           /* case R.id.bottom_read:
                if(factsFragment==null){
                    factsFragment=new FactsFragment();
                    hideAllFragments("FACT");
                    fragmentTransaction=fragmentManager.beginTransaction();
                    //hide all other fragments...
                    //show this fragment
                    fragmentTransaction.add(R.id.fragment_container,factsFragment,"FACTS");
                    fragmentTransaction.addToBackStack("FACTS");
                    fragmentTransaction.commit();
                }else {

                    if(!factsFragment.isVisible()){
                        //hide all fragments..

                        hideAllFragments("FACT");
                        //show current fragment
                        fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.show(factsFragment);
                        fragmentTransaction.commit();
                    }

                }
                break;*/
            case R.id.bottom_search:
                if(searchFragment==null){
                    searchFragment=new SearchFragment();
                    hideAllFragments("SEARCH");
                    fragmentTransaction=fragmentManager.beginTransaction();
                    //hide all other fragments...
                    //show this fragment
                    fragmentTransaction.add(R.id.fragment_container,searchFragment,"SEARCH");
                    fragmentTransaction.addToBackStack("SEARCH");
                    fragmentTransaction.commit();
                }else {

                    if(!searchFragment.isVisible()){
                        //hide all fragments..

                        hideAllFragments("SEARCH");
                        //show current fragment
                        fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.show(searchFragment);
                        fragmentTransaction.commit();
                    }

                }
                break;
            case R.id.bottom_orders:

                break;
            case R.id.bottom_account:

                break;
        }
    }



    private void hideAllFragments(String TAG){
       fragmentTransaction=fragmentManager.beginTransaction();
        switch (TAG){
            case "HOME":
                if(searchFragment!=null)
                fragmentTransaction.hide(searchFragment);
                fragmentTransaction.commit();
                break;
            /*case "FACT":
                fragmentTransaction.hide(homeFragment);
                fragmentTransaction.commit();
                break;*/
            case "SEARCH":
                if (homeFragment!=null)
                fragmentTransaction.hide(homeFragment);
                fragmentTransaction.commit();
                break;

        }
   }

    @Override
    public void onBackStackChanged() {


    }
}